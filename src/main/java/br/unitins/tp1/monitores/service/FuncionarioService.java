package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.pessoa.FuncionarioRequestDTO;
import br.unitins.tp1.monitores.model.Funcionario;


public interface FuncionarioService {

    Funcionario findById(Long id);

    List<Funcionario> findByNome(String nome);

    List<Funcionario> findAll();

    Funcionario findByCpf(String cpf);
    Funcionario create(FuncionarioRequestDTO dto);

    Funcionario update(Long id, FuncionarioRequestDTO dto);

    Funcionario findByUsername(String username);
    void delete(Long id);
    
    
}
