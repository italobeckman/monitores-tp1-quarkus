package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.estado.EstadoRequestDTO;
import br.unitins.tp1.monitores.model.Estado;
import br.unitins.tp1.monitores.repository.EstadoRepository;
import br.unitins.tp1.monitores.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    public EstadoRepository estadoRepository;

    @Override
    public Estado findById(Long id) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        Estado estado = estadoRepository.findById(id);
        if (estado == null) {
            throw new ValidationException("id", "Estado não encontrado.");
        }
        return estado;
    }

    @Override
    public List<Estado> findByNome(String nome) {
        return estadoRepository.findByNome(nome);
    }

    @Override
    public List<Estado> findAll() {
        return estadoRepository.findAll().list();
    }

    @Override
    @Transactional
    public Estado create(@Valid EstadoRequestDTO dto) {
        if (dto == null) {
            throw new ValidationException("dto", "DTO não pode ser nulo.");
        }

        if (dto.nome() == null || dto.nome().trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }

        if (dto.sigla() == null || dto.sigla().trim().isEmpty()) {
            throw new ValidationException("sigla", "Sigla é obrigatória.");
        }

        if (estadoRepository.findBySigla(dto.sigla()) != null) {
            throw new ValidationException("sigla", "Sigla já utilizada.");
        }


        validarSigla(dto.sigla());
        Estado estado = new Estado();
        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());

        estadoRepository.persist(estado);
        return estado;
    }

    private void validarSigla(String sigla) {
        estadoRepository.findBySigla(sigla);
        if(estadoRepository.findBySigla(sigla) != null){
            throw new ValidationException("sigla", "Esta sigla ja foi utilizada em outro estado");
        }
    }

    @Override
    @Transactional
    public Estado update(Long id, EstadoRequestDTO dto) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        if (dto == null) {
            throw new ValidationException("dto", "DTO não pode ser nulo.");
        }

        if (dto.nome() == null || dto.nome().trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }

        if (dto.sigla() == null || dto.sigla().trim().isEmpty()) {
            throw new ValidationException("sigla", "Sigla é obrigatória.");
        }

        Estado estado = estadoRepository.findById(id);
        if (estado == null) {
            throw new ValidationException("id", "Estado não encontrado.");
        }
        

        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());

        return estado;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        estadoRepository.deleteById(id);
    }

}
