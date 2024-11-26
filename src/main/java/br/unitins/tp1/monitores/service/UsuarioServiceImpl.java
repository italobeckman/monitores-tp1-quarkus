package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.model.Usuario;
import br.unitins.tp1.monitores.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    public UsuarioRepository usuarioRepository;

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id);
    }
    @Override
    public Usuario findByUsernameAndSenha(String username, String senha) {
        return usuarioRepository.findByUsernameAndSenha(username, senha);
    }


    @Override
    public List<Usuario> findByNome(String nome) {
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll().list();
    }

    // @Override
    // @Transactional
    // public Usuario create(UsuarioRequestDTO dto) {
    //     Usuario usuario = new Usuario();
    //     usuario.setNome(dto.nome());
    //     usuario.setSigla(dto.sigla());

    //     usuarioRepository.persist(usuario);
    //     return usuario;
    // }

    // @Override
    // @Transactional
    // public Usuario update(Long id, UsuarioRequestDTO dto) {
    //     Usuario usuario = usuarioRepository.findById(id);

    //     usuario.setNome(dto.nome());
    //     usuario.setSigla(dto.sigla());

    //     return usuario;
    // }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
    
}
