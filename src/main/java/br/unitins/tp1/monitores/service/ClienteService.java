package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.pessoa.ClienteRequestDTO;
import br.unitins.tp1.monitores.model.Cliente;


public interface ClienteService {

    Cliente findById(Long id);

    List<Cliente> findByNome(String nome);

    List<Cliente> findAll();

    Cliente findByCpf(String cpf);

    Cliente update(String username, ClienteRequestDTO dto);

    void delete(Long id);
    Cliente findByUsername(String username);
    Cliente updateNomeImagem(Long id, String nomeImagem);

    // Cliente create(ClienteRequestDTO dto);
    
}
