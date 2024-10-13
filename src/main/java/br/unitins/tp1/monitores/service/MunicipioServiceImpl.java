package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.municipio.MunicipioRequestDTO;
import br.unitins.tp1.monitores.model.Municipio;
import br.unitins.tp1.monitores.repository.MunicipioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MunicipioServiceImpl implements MunicipioService {

    @Inject
    public MunicipioRepository municipioRepository;

    @Inject
    public EstadoService estadoService;

    @Override
    public Municipio findById(Long id) {
        return municipioRepository.findById(id);
    }

    @Override
    public List<Municipio> findByNome(String nome) {
        return municipioRepository.findByNome(nome);
    }

    @Override
    public List<Municipio> findAll() {
        return municipioRepository.findAll().list();
    }

    @Override
    @Transactional
    public Municipio create(MunicipioRequestDTO dto) {
        // buscando o estado a partir de um id do municipio
        Municipio municipio = new Municipio();
        municipio.setEstado(estadoService.findById(dto.idEstado()));
        municipio.setNome(dto.nome());

        //salvando o municipio
        municipioRepository.persist(municipio);
        
        return municipio;
    }

    @Override
    @Transactional
    public Municipio update(Long id, MunicipioRequestDTO dto) {
        Municipio municipio = municipioRepository.findById(id);

        municipio.setNome(dto.nome());

        // buscando o estado a partir de um id do municipio
        municipio.setEstado(estadoService.findById(dto.idEstado()));

        return municipio;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        municipioRepository.deleteById(id);
    }
    
}
