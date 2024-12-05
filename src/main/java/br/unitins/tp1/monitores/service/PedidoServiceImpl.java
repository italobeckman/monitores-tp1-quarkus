package br.unitins.tp1.monitores.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unitins.tp1.monitores.dto.pagamento.BoletoResponseDTO;
import br.unitins.tp1.monitores.dto.pagamento.CartaoDTO;
import br.unitins.tp1.monitores.dto.pagamento.CartaoResponseDTO;
import br.unitins.tp1.monitores.dto.pagamento.PixResponseDTO;
import br.unitins.tp1.monitores.dto.pedido.PedidoDTO;
import br.unitins.tp1.monitores.dto.pedido.PedidoResponseDTO;
import br.unitins.tp1.monitores.dto.pedido.item_pedido.ItemPedidoDTO;
import br.unitins.tp1.monitores.model.Cliente;
import br.unitins.tp1.monitores.model.EnderecoPedido;
import br.unitins.tp1.monitores.model.Estado;
import br.unitins.tp1.monitores.model.Lote;
import br.unitins.tp1.monitores.model.Monitor;
import br.unitins.tp1.monitores.model.Municipio;
import br.unitins.tp1.monitores.model.pagamento.Boleto;
import br.unitins.tp1.monitores.model.pagamento.Cartao;
import br.unitins.tp1.monitores.model.pagamento.Pagamento;
import br.unitins.tp1.monitores.model.pagamento.Pix;
import br.unitins.tp1.monitores.model.pedido.ItemPedido;
import br.unitins.tp1.monitores.model.pedido.Pedido;
import br.unitins.tp1.monitores.model.pedido.Status;
import br.unitins.tp1.monitores.model.pedido.StatusPedido;
import br.unitins.tp1.monitores.repository.ClienteRepository;
import br.unitins.tp1.monitores.repository.EstadoRepository;
import br.unitins.tp1.monitores.repository.LoteRepository;
import br.unitins.tp1.monitores.repository.MonitorRepository;
import br.unitins.tp1.monitores.repository.MunicipioRepository;
import br.unitins.tp1.monitores.repository.PagamentoRepository;
import br.unitins.tp1.monitores.repository.PedidoRepository;
import br.unitins.tp1.monitores.validation.ValidationException;
import io.quarkus.scheduler.Scheduled;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    public PedidoRepository pedidoRepository;

    @Inject
    public PagamentoRepository pagamentoRepository;

    @Inject
    public MonitorRepository monitorRepository;

    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public LoteRepository loteRepository;

    @Inject
    MunicipioRepository municipioRepository;

    @Inject
    EstadoRepository estadoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO create(@Valid PedidoDTO dto, String username) {
        Pedido pedido = new Pedido();

        pedido.setCodigo_pedido(username + "-" + UUID.randomUUID().toString());

        Cliente cliente = clienteRepository.findByUsername(username);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado para o username: " + username);
        }
        pedido.setCliente(cliente);
        pedido.setData(LocalDateTime.now());
        pedido.setPrazoPagamento(LocalDateTime.now().plusSeconds(20));

        EnderecoPedido enderecoPedido = new EnderecoPedido();
        enderecoPedido.setLogradouro(dto.endereco().logradouro());
        enderecoPedido.setNumero(dto.endereco().numero());
        enderecoPedido.setComplemento(dto.endereco().complemento());
        enderecoPedido.setCep(dto.endereco().cep());

        Municipio municipio = municipioRepository.findById(dto.endereco().idMunicipio());
        if (municipio == null) {
            throw new IllegalArgumentException("Município inválido para o ID: " + dto.endereco().idMunicipio());
        }
        enderecoPedido.setMunicipio(municipio);

        Estado estado = estadoRepository.findById(dto.endereco().idEstado());
        if (estado == null) {
            throw new IllegalArgumentException("Estado inválido para o ID: " + dto.endereco().idEstado());
        }
        enderecoPedido.setEstado(estado);
        enderecoPedido.setBairro(dto.endereco().bairro());

        pedido.setEnderecoPedido(enderecoPedido);
        List<ItemPedido> listaItens = getItensFromDTO(dto.itens(), pedido);

        if (listaItens == null || listaItens.isEmpty()) {

            throw new IllegalArgumentException("A lista de itens não pode estar vazia.");
        }
        pedido.setListaItem(listaItens);
        pedido.setTotal(calculateTotalPedido(listaItens));
        pedidoRepository.persist(pedido);

        List<StatusPedido> listaStatus = Arrays.asList(createStatusPedido(1));
        if (listaStatus.isEmpty()) {
            throw new IllegalArgumentException("Status inicial do pedido não pode ser vazio.");
        }
        pedido.setListaStatus(listaStatus);

        return PedidoResponseDTO.valueOf(pedido);
    }

    private List<ItemPedido> getItensFromDTO(List<ItemPedidoDTO> listaItemDTO, Pedido pedido) {
        validarListaItemDTO(listaItemDTO);

        List<ItemPedido> listaItens = new ArrayList<>();
        for (ItemPedidoDTO itemDTO : listaItemDTO) {
            verificarEstoquePorIdMonitor(itemDTO.idMonitor(), itemDTO.quantidade());

            ItemPedido item = new ItemPedido();
            Monitor monitor = monitorRepository.findById(itemDTO.idMonitor());

            if (monitor != null) {
                item.setMonitor(monitor);
                item.setQuantidade(itemDTO.quantidade());
                double precoTotal = monitor.getPreco() * itemDTO.quantidade();
                item.setPreco(precoTotal);
            } else {
                throw new ValidationException("idMonitor", "Monitor com o id fornecido não foi encontrado");
            }
            Integer subEstoque = itemDTO.quantidade();

            Lote lote = loteRepository.findByIdMonitor(itemDTO.idMonitor());

            if (lote.getQuantidade() < subEstoque) {
                throw new ValidationException("idMonitor",
                        "Não há estoque suficiente para o monitor com o id fornecido");
            }
            item.setLote(lote);
            lote.setQuantidade(lote.getQuantidade() - subEstoque);
            listaItens.add(item);
        }
        return listaItens;
    }

    @Override
    @Transactional
    public void update(Long id, @Valid PedidoDTO dto) {
        Pedido pedido = pedidoRepository.findById(id);

        List<ItemPedido> itensFromDTO = getItensFromDTO(dto.itens());
        List<ItemPedido> itensFromBanco = pedido.getListaItem();

        itensFromBanco.clear();
        itensFromDTO.forEach(i -> itensFromBanco.add(i));

        pedido.setTotal(calculateTotalPedido(itensFromBanco));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateStatusPedido(Long idPedido, Integer idStatus) {
        Pedido pedido = pedidoRepository.findById(idPedido);

        if (pedido == null) {
            throw new ValidationException("idPedido", "Pedido não encontrado");
        }

      
        StatusPedido statusPedido = createStatusPedido(idStatus);
        if (statusPedido == null) {
            throw new ValidationException("idStatus", "Status inválido");
        }

    
        pedido.getListaStatus().add(statusPedido);
    }

    @Override
    @Transactional
    public PixResponseDTO gerarInformacoesPix(Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido);
        if (pedido == null) {
            throw new ValidationException("idPedido", "Pedido não encontrado");
        }
        Double total = pedidoRepository.findById(idPedido).getTotal();
        
        Pix pix = new Pix();
        pix.setValor(total);
        pix.setChaveDestinatario("Monitor_store@monitorStore.com");
        pix.setIdentificador(UUID.randomUUID().toString());
        
        pagamentoRepository.persist(pix);
        pedido.setPagamento(pix);
        
        return PixResponseDTO.valueOf(pix);
    }

    @Override
    @Transactional
    public BoletoResponseDTO gerarInformacoesBoleto(Long idPedido) {
        Double total = pedidoRepository.findById(idPedido).getTotal();

        Boleto boleto = new Boleto();
        boleto.setValor(total);
        boleto.setCodigo(UUID.randomUUID().toString());

        pagamentoRepository.persist(boleto);
        return BoletoResponseDTO.valueOf(boleto);
    }

    @Override
    @Transactional
    public PixResponseDTO registrarPagamentoPix(Long idPedido, Long idPix) {
        if (idPix == null || idPix <= 0) {
            throw new ValidationException("idPix", "ID do Pix inválido.");
        }

        Optional<Pagamento> optionalPix = pagamentoRepository.findByIdOptional(idPix);
        if (optionalPix.isEmpty()) {
            throw new ValidationException("idPix", "Pagamento Pix não encontrado.");
        }

        Pedido pedido = pedidoRepository.findById(idPedido);
        Pix pix = (Pix) optionalPix.get();

        pedido.setPagamento(pix);
        updateStatusPedido(idPedido, 3);

        return PixResponseDTO.valueOf(pix);
    }

    @Override
    @Transactional
    public BoletoResponseDTO registrarPagamentoBoleto(Long idPedido, Long idBoleto) {
        Pedido pedido = pedidoRepository.findById(idPedido);
        Boleto boleto = (Boleto) pagamentoRepository.findById(idBoleto);
        pedido.setPagamento(boleto);

        updateStatusPedido(idPedido, 3);
        return BoletoResponseDTO.valueOf(boleto);
    }

    @Override
    @Transactional
    public CartaoResponseDTO registrarPagamentoCartao(Long idPedido, CartaoDTO cartaoDTO) {
        Pedido pedido = pedidoRepository.findById(idPedido);
        updateStatusPedido(idPedido, 3);

        Cartao cartao = CartaoDTO.convertToCartao(cartaoDTO);
        cartao.setValor(pedido.getTotal());

        pagamentoRepository.persist(cartao);
        pedido.setPagamento(cartao);

        return new CartaoResponseDTO(cartao.getNumero().substring(cartao.getNumero().length() - 4));
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        return PedidoResponseDTO.valueOf(pedidoRepository.findById(id));
    }

    @Override
    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.listAll().stream().map(PedidoResponseDTO::valueOf).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByCliente(Long idCliente) {
        return pedidoRepository.findByCliente(idCliente).stream().map(PedidoResponseDTO::valueOf).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByUsername(String username) {
        Cliente cliente = clienteRepository.findByUsername(username);
        if (cliente == null) {
            throw new ValidationException("Username", "Cliente não encontrado para o username fornecido.");
        }
        return pedidoRepository.findByCliente(cliente.getId()).stream()
                .map(PedidoResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<PedidoResponseDTO> findByStatus(Integer idStatus) {
        return pedidoRepository.findByStatus(idStatus).stream().map(PedidoResponseDTO::valueOf).toList();
    }

    private StatusPedido createStatusPedido(Integer id) {
        StatusPedido statusPedido = new StatusPedido();

        statusPedido.setStatus(Status.valueOf(id));

        return statusPedido;
    }

    private Double calculateTotalPedido(List<ItemPedido> listaItem) {
        return listaItem.stream()
                .mapToDouble(ItemPedido::getPreco)
                .sum();
    }

    private List<ItemPedido> getItensFromDTO(List<ItemPedidoDTO> listaItemDTO) {
        validarListaItemDTO(listaItemDTO); 

        List<ItemPedido> listaItens = new ArrayList<>();
        for (ItemPedidoDTO itemDTO : listaItemDTO) {
            verificarEstoquePorIdMonitor(itemDTO.idMonitor(), itemDTO.quantidade());

            ItemPedido item = new ItemPedido();
            Monitor monitor = monitorRepository.findById(itemDTO.idMonitor());

            if (monitor != null) {
                item.setMonitor(monitor);
                item.setQuantidade(itemDTO.quantidade());
                double precoTotal = monitor.getPreco() * itemDTO.quantidade();
                item.setPreco(precoTotal); 
            } else {
                throw new ValidationException("idMonitor", "Monitor com o id fornecido não foi encontrado");
            }

            listaItens.add(item);
        }
        return listaItens;
    }

    private void validarListaItemDTO(List<ItemPedidoDTO> listaDTO) {
        for (ItemPedidoDTO item : listaDTO) {
            verificarEstoquePorIdMonitor(item.idMonitor(), item.quantidade());

        }
    }

    private void verificarEstoquePorIdMonitor(Long idMonitor, Integer quantidade) {
        Monitor monitor = monitorRepository.findById(idMonitor);
        if (monitor == null) {
            throw new ValidationException("idMonitor", "Monitor com o id fornecido não foi encontrado");
        }

        Lote lote = loteRepository.findByIdMonitorLote(idMonitor);
        if (lote == null || lote.getQuantidade() < quantidade) {
            throw new ValidationException("idMonitor", "Não há estoque suficiente para o monitor com o id fornecido");
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(MonitorService.class);

    @Scheduled(every = "60s")
    @Transactional
    public void atualizarPedidoExpirados() {
        LocalDateTime now = LocalDateTime.now();
        List<Pedido> pedidosExpirados = pedidoRepository.findPedidosExpirados(now);

        if (pedidosExpirados != null) {
            for (Pedido pedido : pedidosExpirados) {
                boolean expirado = false;
                for (StatusPedido statusPedido : pedido.getListaStatus()) { // Verifica se já não está expirado
                    if (statusPedido.getStatus().getId() == 2) {
                        LOG.info("Pedido com mudança de status para expirado: " + pedido.getId());
                        expirado = true;
                        break;
                    }
                }
                if (expirado)
                    continue;

                updateStatusPedido(pedido.getId(), 2); // 2 -> Pagamento Expirado

                // Devolvendo ao estoque
                for (ItemPedido item : pedido.getListaItem()) {
                    Long idLote = item.getLote().getId();
                    Lote lote = loteRepository.findById(idLote);

                    if (lote != null) {
                        Integer novaQuantidade = lote.getQuantidade() + item.getQuantidade();
                        lote.setQuantidade(novaQuantidade);
                    }

                }

            }
        }
    }

    @Scheduled(every = "60s")
    @Transactional
    public void atualizarPedidosPagos() {
        List<Pedido> pedidos = pedidoRepository.findPedidosStatusDiferente();
        if (pedidos == null) {
            throw new IllegalArgumentException("Nao há pedidos com status 3 ou 4");
        }

        for (Pedido pedido : pedidos) {
            boolean statusAtualizado = false;

            for (StatusPedido statusPedido : pedido.getListaStatus()) {
                if (statusPedido.getStatus().getId() == 3) { // Pagamento confirmado
                    int proximoStatusId = statusPedido.getStatus().getId() + 1;
                    if (proximoStatusId <= 5) {
                        updateStatusPedido(pedido.getId(), proximoStatusId);
                        LOG.info("Pedido com mudança de status para: " + proximoStatusId + " (ID: " + pedido.getId()
                                + ")");
                        statusAtualizado = true;
                    }

                }

            }

            if (statusAtualizado) {
                pedidoRepository.persist(pedido);
            }
        }
    }

}