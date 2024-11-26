package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.pedido.PedidoRequestDTO;
import br.unitins.tp1.monitores.model.Pedido;


public interface PedidoService {

    Pedido findById(Long id);

    List<Pedido> findByUsername(String username);

    Pedido create(PedidoRequestDTO dto, String username);

    // implementar patch's
    // void delete(Long id); 
    
}
