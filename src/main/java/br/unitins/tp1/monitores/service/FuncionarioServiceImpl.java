package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.pessoa.FuncionarioRequestDTO;
import br.unitins.tp1.monitores.model.Funcionario;
import br.unitins.tp1.monitores.model.Sexo;
import br.unitins.tp1.monitores.repository.FuncionarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject
    public FuncionarioRepository funcionarioRepository;

    @Override
    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id);
    }
    @Override
    public Funcionario findByCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf);
    }

    @Override
    public List<Funcionario> findByNome(String nome) {
        return funcionarioRepository.findByNome(nome);
    }

    @Override
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll().list();
    }

    @Override
    @Transactional
    public Funcionario create(FuncionarioRequestDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome());
        funcionario.setCpf(dto.cpf());
        funcionario.setSalario(dto.salario());
        funcionario.setSexo(Sexo.valueOf(dto.idSexo()));

        funcionarioRepository.persist(funcionario);
        return funcionario;
    }

    @Override
    @Transactional
    public Funcionario update(Long id, FuncionarioRequestDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id);
        funcionario.setNome(dto.nome());
        funcionario.setCpf(dto.cpf());
        funcionario.setSalario(dto.salario());
        funcionario.setSexo(Sexo.valueOf(dto.idSexo()));

        return funcionario;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }
    
    @Override
    public Funcionario findByUsername(String username) {
        return funcionarioRepository.findByUsername(username);
    }
}