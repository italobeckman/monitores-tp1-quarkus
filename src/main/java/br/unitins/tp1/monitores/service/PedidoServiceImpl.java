package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.pedido.PedidoRequestDTO;
import br.unitins.tp1.monitores.model.Pedido;
import br.unitins.tp1.monitores.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    public PedidoRepository pedidoRepository;

    @Inject
    public EstadoService estadoService;

    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> findByUsername(String username) {

        // return pedidoRepository.findByUsuario(usuario.getId());
        return null;
    }

    // @Override
    // public List<Pedido> findAll() {
    //     // return pedidoRepository.findAll().list();

    //     return null;
    // }

    @Override
    @Transactional
    public Pedido create(PedidoRequestDTO dto) {
        // // buscando o estado a partir de um id do municipio
        // Pedido municipio = new Pedido();
        // municipio.setEstado(estadoService.findById(dto.idEstado()));
        // municipio.setNome(dto.nome());

        // //salvando o municipio
        // pedidoRepository.persist(municipio);
        
        // return municipio;


        return null;
    }
    
}
