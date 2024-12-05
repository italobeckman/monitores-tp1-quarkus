package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.fabricante.FabricanteRequestDTO;
import br.unitins.tp1.monitores.dto.fabricante.TelefoneFabricanteRequestDTO;
import br.unitins.tp1.monitores.model.Fabricante;
import br.unitins.tp1.monitores.repository.FabricanteRepository;
import br.unitins.tp1.monitores.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FabricanteServiceImpl implements FabricanteService {

    @Inject
    public FabricanteRepository fabricanteRepository;

    @Override
    public Fabricante findById(Long id) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        Fabricante fabricante = fabricanteRepository.findById(id);
        if (fabricante == null) {
            throw new ValidationException("id", "Fabricante não encontrado.");
        }
        return fabricante;
    }

    @Override
    public List<Fabricante> findByNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }
        return fabricanteRepository.findByNome(nome);
    }

    @Override
    public List<Fabricante> findAll() {
        return fabricanteRepository.findAll().list();
    }

    @Override
    @Transactional
    public Fabricante create(FabricanteRequestDTO dto) {
        if (dto == null) {
            throw new ValidationException("dto", "DTO não pode ser nulo.");
        }

        if (dto.nome() == null || dto.nome().trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }

        if (dto.cnpj() == null || dto.cnpj().trim().isEmpty()) {
            throw new ValidationException("cnpj", "CNPJ é obrigatório.");
        }

         if (dto.email() == null || dto.email().trim().isEmpty()) {
            throw new ValidationException("email", "Email é obrigatório.");
        }
        Fabricante fabricante = new Fabricante();
        fabricante.setNome(dto.nome());
        fabricante.setCnpj(dto.cnpj());
        fabricante.setEmail(dto.email());
        fabricante.setListaTelefone(dto.telefones().stream().map(TelefoneFabricanteRequestDTO::toEntity).toList());

        fabricanteRepository.persist(fabricante);
        return fabricante;
    }

    @Override
    @Transactional
    public Fabricante update(Long id, FabricanteRequestDTO dto) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        
        if (dto.nome() == null || dto.nome().trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }

        if (dto.cnpj() == null || dto.cnpj().trim().isEmpty()) {
            throw new ValidationException("cnpj", "CNPJ é obrigatório.");
        }

        if (dto.email() == null || dto.email().trim().isEmpty()) {
            throw new ValidationException("email", "Email é obrigatório.");
        }

        Fabricante fabricante = fabricanteRepository.findById(id);
        if (fabricante == null) {
            throw new ValidationException("id", "Fabricante não encontrado.");
        }
        
        fabricante.setNome(dto.nome());
        fabricante.setCnpj(dto.cnpj());
        fabricante.setEmail(dto.email());

        return fabricante;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        fabricanteRepository.deleteById(id);
    }
    
}
