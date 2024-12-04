package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.pagamento.BoletoResponseDTO;
import br.unitins.tp1.monitores.dto.pagamento.CartaoDTO;
import br.unitins.tp1.monitores.dto.pagamento.CartaoResponseDTO;
import br.unitins.tp1.monitores.dto.pagamento.PixResponseDTO;
import br.unitins.tp1.monitores.dto.pedido.PedidoDTO;
import br.unitins.tp1.monitores.dto.pedido.PedidoResponseDTO;
import jakarta.validation.Valid;

public interface PedidoService {
    PedidoResponseDTO create(@Valid PedidoDTO dto, String username);
    void update(Long id, @Valid PedidoDTO dto);
    void delete(Long id);
    void updateStatusPedido(Long idPedido, Integer idStatus);
    PixResponseDTO gerarInformacoesPix(Long idPedido);
    BoletoResponseDTO gerarInformacoesBoleto(Long idPedido);
    PixResponseDTO registrarPagamentoPix(Long idPedido, Long idPix);
    BoletoResponseDTO registrarPagamentoBoleto(Long idPedido, Long idBoleto);
    CartaoResponseDTO registrarPagamentoCartao(Long id, CartaoDTO cartao);
    PedidoResponseDTO findById(Long id);
    List<PedidoResponseDTO> findAll();
    List<PedidoResponseDTO> findByCliente(Long id);
    List<PedidoResponseDTO> findByUsername(String username);

    List<PedidoResponseDTO> findByStatus(Integer idStatus);
}