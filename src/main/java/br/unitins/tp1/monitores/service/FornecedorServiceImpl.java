package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.fornecedor.FornecedorRequestDTO;
import br.unitins.tp1.monitores.dto.fornecedor.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.monitores.model.Fornecedor;
import br.unitins.tp1.monitores.repository.FornecedorRepository;
import br.unitins.tp1.monitores.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    public FornecedorRepository fornecedorRepository;

    @Override
    public Fornecedor findById(Long id) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        Fornecedor fornecedor = fornecedorRepository.findById(id);
        if (fornecedor == null) {
            throw new ValidationException("id", "Fornecedor não encontrado.");
        }
        return fornecedor;
    }

    @Override
    public List<Fornecedor> findByNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }
        return fornecedorRepository.findByNome(nome);
    }

    @Override
    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll().list();
    }

    @Override
    @Transactional
    public Fornecedor create(FornecedorRequestDTO dto) {
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
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setEmail(dto.email());
        fornecedor.setListaTelefone(dto.telefones().stream().map(TelefoneFornecedorRequestDTO::valueOf).toList());

        fornecedorRepository.persist(fornecedor);
        return fornecedor;
    }

    @Override
    @Transactional
    public Fornecedor update(Long id, FornecedorRequestDTO dto) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
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

        Fornecedor fornecedor = fornecedorRepository.findById(id);
        if (fornecedor == null) {
            throw new ValidationException("id", "Fornecedor não encontrado.");
        }

        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setEmail(dto.email());

        return fornecedor;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        fornecedorRepository.deleteById(id);
    }
    
}
