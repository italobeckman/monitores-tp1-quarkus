package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.estado.EstadoRequestDTO;
import br.unitins.tp1.monitores.model.Estado;


public interface EstadoService {

    Estado findById(Long id);

    List<Estado> findByNome(String nome);

    List<Estado> findAll();

    Estado create(EstadoRequestDTO dto);

    Estado update(Long id, EstadoRequestDTO dto);

    void delete(Long id); 
    
}
