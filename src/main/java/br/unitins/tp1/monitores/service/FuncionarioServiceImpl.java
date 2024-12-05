package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.pessoa.FuncionarioRequestDTO;
import br.unitins.tp1.monitores.model.Funcionario;
import br.unitins.tp1.monitores.model.Sexo;
import br.unitins.tp1.monitores.repository.FuncionarioRepository;
import br.unitins.tp1.monitores.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject
    public FuncionarioRepository funcionarioRepository;

    @Override
    public Funcionario findById(Long id) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        Funcionario funcionario = funcionarioRepository.findById(id);
        if (funcionario == null) {
            throw new ValidationException("id", "Funcionário não encontrado.");
        }
        return funcionario;
    }
    @Override
    public Funcionario findByCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new ValidationException("cpf", "CPF é obrigatório.");
        }
        return funcionarioRepository.findByCpf(cpf);
    }

    @Override
    public List<Funcionario> findByNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }
        return funcionarioRepository.findByNome(nome);
    }

    @Override
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll().list();
    }

    @Override
    @Transactional
    public Funcionario create(FuncionarioRequestDTO dto) {
        if (dto == null) {
            throw new ValidationException("dto", "DTO não pode ser nulo.");
        }

        if (dto.nome() == null || dto.nome().trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }

        if (dto.cpf() == null || dto.cpf().trim().isEmpty()) {
            throw new ValidationException("cpf", "CPF é obrigatório.");
        }

        if (dto.idSexo() == null) {
            throw new ValidationException("idSexo", "Sexo é obrigatório.");
        }
        try {
            Sexo.valueOf(dto.idSexo());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("idSexo", "Sexo inválido.");
        }
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome());
        funcionario.setCpf(dto.cpf());
        funcionario.setSexo(Sexo.valueOf(dto.idSexo()));

        funcionarioRepository.persist(funcionario);
        return funcionario;
    }

    @Override
    @Transactional
    public Funcionario update(Long id, FuncionarioRequestDTO dto) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }

        if (dto == null) {
            throw new ValidationException("dto", "DTO não pode ser nulo.");
        }

        if (dto.nome() == null || dto.nome().trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }

        if (dto.cpf() == null || dto.cpf().trim().isEmpty()) {
            throw new ValidationException("cpf", "CPF é obrigatório.");
        }
        if (dto.idSexo() == null ) {
            throw new ValidationException("idSexo", "Sexo é obrigatório.");
        }

        try {
             Sexo.valueOf(dto.idSexo());
        } catch (IllegalArgumentException e) {
             throw new ValidationException("idSexo", "Sexo invalido.");
        }

        Funcionario funcionario = funcionarioRepository.findById(id);
        if (funcionario == null) {
            throw new ValidationException("id", "Funcionário não encontrado.");
        }

        funcionario.setNome(dto.nome());
        funcionario.setCpf(dto.cpf());
        funcionario.setSexo(Sexo.valueOf(dto.idSexo()));

        return funcionario;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        funcionarioRepository.deleteById(id);
    }
    
    @Override
    public Funcionario findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("username", "Username é obrigatório.");
        }
        return funcionarioRepository.findByUsername(username);
    }
}