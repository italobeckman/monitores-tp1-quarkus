package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.municipio.MunicipioRequestDTO;
import br.unitins.tp1.monitores.model.Municipio;
import br.unitins.tp1.monitores.repository.MunicipioRepository;
import br.unitins.tp1.monitores.validation.ValidationException;
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
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        Municipio municipio = municipioRepository.findById(id);
        if (municipio == null) {
            throw new ValidationException("id", "Município não encontrado.");
        }
        return municipio;
    }

    @Override
    public List<Municipio> findByNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }
        return municipioRepository.findByNome(nome);
    }

    @Override
    public List<Municipio> findAll() {
        return municipioRepository.findAll().list();
    }

    @Override
    @Transactional
    public Municipio create(MunicipioRequestDTO dto) {
        if (dto == null) {
            throw new ValidationException("dto", "DTO não pode ser nulo.");
        }

        if (dto.nome() == null || dto.nome().trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }

        if (dto.idEstado() == null) {
            throw new ValidationException("idEstado", "Estado é obrigatório.");
        }

        // Validação se o estado existe (boa prática)
        estadoService.findById(dto.idEstado());

        Municipio municipio = new Municipio();
        municipio.setEstado(estadoService.findById(dto.idEstado()));
        municipio.setNome(dto.nome());

        municipioRepository.persist(municipio);

        return municipio;
    }

    @Override
    @Transactional
    public Municipio update(Long id, MunicipioRequestDTO dto) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        if (dto == null) {
            throw new ValidationException("dto", "DTO não pode ser nulo.");
        }

        if (dto.nome() == null || dto.nome().trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }

        if (dto.idEstado() == null) {
            throw new ValidationException("idEstado", "Estado é obrigatório.");
        }

        // Validação se o estado existe (boa prática)
        estadoService.findById(dto.idEstado());

        Municipio municipio = municipioRepository.findById(id);
        if (municipio == null) {
            throw new ValidationException("id", "Município não encontrado.");
        }

        municipio.setNome(dto.nome());
        municipio.setEstado(estadoService.findById(dto.idEstado()));

        return municipio;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        municipioRepository.deleteById(id);
    }

}
