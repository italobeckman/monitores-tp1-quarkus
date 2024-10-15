package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.fabricante.FabricanteRequestDTO;
import br.unitins.tp1.monitores.model.Fabricante;


public interface FabricanteService {

    Fabricante findById(Long id);

    List<Fabricante> findByNome(String nome);

    List<Fabricante> findAll();

    Fabricante create(FabricanteRequestDTO dto);

    Fabricante update(Long id, FabricanteRequestDTO dto);

    void delete(Long id); 
    
}
