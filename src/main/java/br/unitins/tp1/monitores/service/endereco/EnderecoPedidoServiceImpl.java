package br.unitins.tp1.monitores.service.endereco;

import java.util.List;

import br.unitins.tp1.monitores.dto.endereco.EnderecoPedidoRequestDTO;
import br.unitins.tp1.monitores.model.EnderecoPedido;
import br.unitins.tp1.monitores.repository.EnderecoPedidoRepository;
import br.unitins.tp1.monitores.service.EstadoService;
import br.unitins.tp1.monitores.service.MunicipioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@ApplicationScoped
public class EnderecoPedidoServiceImpl implements EnderecoPedidoService {

    @Inject
    public EnderecoPedidoRepository enderecoPedidoRepository;

    @Inject
    public MunicipioService municipioService;

    @Inject
    public EstadoService estadoService;

    @Override
    public EnderecoPedido findById(Long id) {
        return enderecoPedidoRepository.findById(id);
    }


    @Override
    public List<EnderecoPedido> findAll() {
        return enderecoPedidoRepository.findAll().list();
    }

    @Override
    @Transactional
    public EnderecoPedido create(@Valid EnderecoPedidoRequestDTO dto) {
        
        EnderecoPedido enderecoPedido = new EnderecoPedido();
        enderecoPedido.setLogradouro(dto.logradouro());
        enderecoPedido.setNumero(dto.numero());
        enderecoPedido.setBairro(dto.bairro());
        enderecoPedido.setComplemento(dto.complemento());
        enderecoPedido.setCep(dto.cep());

        enderecoPedido.setMunicipio(municipioService.findById(dto.idMunicipio()));
        enderecoPedido.setEstado(estadoService.findById(dto.idEstado()));

       

        enderecoPedidoRepository.persist(enderecoPedido);
        return enderecoPedido;
    }


    @Override
    @Transactional
    public EnderecoPedido update(Long id, EnderecoPedidoRequestDTO dto) {
        EnderecoPedido enderecoPedido = enderecoPedidoRepository.findById(id);

        enderecoPedido.setLogradouro(dto.logradouro());
        enderecoPedido.setNumero(dto.numero());
        enderecoPedido.setBairro(dto.bairro());
        enderecoPedido.setComplemento(dto.complemento());
        enderecoPedido.setCep(dto.cep());

        enderecoPedido.setMunicipio(municipioService.findById(dto.idMunicipio()));
        enderecoPedido.setEstado(estadoService.findById(dto.idEstado()));

       


        return enderecoPedido;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        enderecoPedidoRepository.deleteById(id);
    }

}
