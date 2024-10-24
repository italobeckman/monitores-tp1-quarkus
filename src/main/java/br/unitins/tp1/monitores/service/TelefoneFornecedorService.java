package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.fornecedor.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.monitores.model.TelefoneFornecedor;


public interface TelefoneFornecedorService {

    TelefoneFornecedor findById(Long id);

    List<TelefoneFornecedor> findAll();

    TelefoneFornecedor create(TelefoneFornecedorRequestDTO dto);

    TelefoneFornecedor update(Long id, TelefoneFornecedorRequestDTO dto);

    void delete(Long id); 
    
    TelefoneFornecedor findByNumero(String string); 
}
