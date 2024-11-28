package br.unitins.tp1.monitores.service.endereco;

import java.util.List;

import br.unitins.tp1.monitores.dto.endereco.EnderecoPedidoRequestDTO;
import br.unitins.tp1.monitores.model.EnderecoPedido;
import jakarta.validation.Valid;


public interface EnderecoPedidoService {

    EnderecoPedido findById(Long id);

    List<EnderecoPedido> findAll();

    EnderecoPedido create(@Valid EnderecoPedidoRequestDTO dto);

    EnderecoPedido update(Long id, EnderecoPedidoRequestDTO dto);

    void delete(Long id); 
    
}
