package br.unitins.tp1.monitores.service.user;

import java.util.List;

import br.unitins.tp1.monitores.dto.usuario.UsuarioCreateRequestDTO;
import br.unitins.tp1.monitores.dto.usuario.UsuarioUpdateRequestDTO;
import br.unitins.tp1.monitores.model.Perfil;
import br.unitins.tp1.monitores.model.Usuario;
import br.unitins.tp1.monitores.repository.UsuarioRepository;
import br.unitins.tp1.monitores.service.HashService;
import br.unitins.tp1.monitores.validation.ValidationException;
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

    @Inject
    public HashService hashService;

    @Override
    @Transactional
    public Usuario create(UsuarioCreateRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setSenha(hashService.getHashSenha(dto.senha())); // Hash da senha
        usuario.setPerfil(Perfil.valueOf(dto.perfil())); // Converta o perfil para o enum

        usuarioRepository.persist(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public Usuario update(Long id, UsuarioUpdateRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new ValidationException("usuario", "Usuário não encontrado");
        }

        // Verifica se a senha antiga está correta
        String hashSenhaAntiga = hashService.getHashSenha(dto.senhaAntiga());
        if (!usuario.getSenha().equals(hashSenhaAntiga)) {
            throw new ValidationException("senhaAntiga", "A senha antiga está incorreta");
        }

        // Verifica se o username antigo está correto
        if (!usuario.getUsername().equals(dto.usernameAntigo())) {
            throw new ValidationException("usernameAntigo", "O username antigo está incorreto");
        }

        // Atualiza o username e a senha
        usuario.setUsername(dto.novoUsername()); // Atualiza o username
        usuario.setSenha(hashService.getHashSenha(dto.novaSenha())); // Atualiza a senha com hash

        return usuario; // O usuário é atualizado automaticamente pelo Panache
    }

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
