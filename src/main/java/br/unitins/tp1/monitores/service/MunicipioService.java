package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.municipio.MunicipioRequestDTO;
import br.unitins.tp1.monitores.model.Municipio;


public interface MunicipioService {

    Municipio findById(Long id);

    List<Municipio> findByNome(String nome);

    List<Municipio> findAll();

    Municipio create(MunicipioRequestDTO dto);

    Municipio update(Long id, MunicipioRequestDTO dto);

    void delete(Long id); 
    
}
