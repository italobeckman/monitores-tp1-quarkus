package br.unitins.tp1.monitores.service;

import br.unitins.tp1.monitores.dto.municipio.MunicipioRequestDTO;
import br.unitins.tp1.monitores.model.Municipio;
import java.util.List;

public interface MunicipioService {

    public List<Municipio> findAll();

    public Municipio findById(Long Id);

    public List<Municipio> findByNome(String nome);

    public Municipio update(Long id, MunicipioRequestDTO dto);

    public void delete(Long id);
    
    public Municipio create(MunicipioRequestDTO dto);
}
