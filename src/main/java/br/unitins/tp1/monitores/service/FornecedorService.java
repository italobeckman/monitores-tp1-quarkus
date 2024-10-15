package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.fornecedor.FornecedorRequestDTO;
import br.unitins.tp1.monitores.model.Fornecedor;


public interface FornecedorService {

    Fornecedor findById(Long id);

    List<Fornecedor> findByNome(String nome);

    List<Fornecedor> findAll();

    Fornecedor create(FornecedorRequestDTO dto);

    Fornecedor update(Long id, FornecedorRequestDTO dto);

    void delete(Long id); 
    
}
