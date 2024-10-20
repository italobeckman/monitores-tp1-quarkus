package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.fabricante.TelefoneFabricanteRequestDTO;
import br.unitins.tp1.monitores.model.TelefoneFabricante;


public interface TelefoneFabricanteService {

    TelefoneFabricante findById(Long id);

    List<TelefoneFabricante> findAll();

    TelefoneFabricante create(TelefoneFabricanteRequestDTO dto);

    TelefoneFabricante update(Long id, TelefoneFabricanteRequestDTO dto);

    void delete(Long id); 
    
}
