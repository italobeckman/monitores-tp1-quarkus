package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.model.Usuario;


public interface UsuarioService {

    Usuario findById(Long id);
    Usuario findByUsername(String username);

    Usuario findByUsernameAndSenha(String username, String senha);

    List<Usuario> findByNome(String nome);

    List<Usuario> findAll();

    // Usuario create(UsuarioRequestDTO dto);

   // Usuario update(Long id, UsuarioRequestDTO dto);

    void delete(Long id); 
    
}
