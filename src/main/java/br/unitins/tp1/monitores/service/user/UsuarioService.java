package br.unitins.tp1.monitores.service.user;

import java.util.List;

import br.unitins.tp1.monitores.dto.usuario.UsuarioCreateRequestDTO;
import br.unitins.tp1.monitores.dto.usuario.UsuarioUpdateRequestDTO;
import br.unitins.tp1.monitores.model.Usuario;

public interface UsuarioService {

    Usuario findById(Long id);

    Usuario findByUsername(String username);

    Usuario findByUsernameAndSenha(String username, String senha);

    List<Usuario> findByNome(String nome);

    List<Usuario> findAll();

    Usuario create(UsuarioCreateRequestDTO dto);

    Usuario update(String username, UsuarioUpdateRequestDTO dto);

    void delete(Long id);

}
