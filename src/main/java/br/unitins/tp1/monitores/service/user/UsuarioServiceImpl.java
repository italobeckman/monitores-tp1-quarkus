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
        if(id == null) {
            throw new ValidationException( "id", "Id não pode ser nulo");
        }
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario findByUsernameAndSenha(String username, String senha) {
        if(username == null) {
            throw new ValidationException( "username", "username não pode ser nulo");
        }
        if(senha == null) {
            throw new ValidationException( "senha", "senha não pode ser nulo");
        }
        return usuarioRepository.findByUsernameAndSenha(username, senha);
    }

    @Override
    public List<Usuario> findByNome(String nome) {
        if(nome == null) {
            throw new ValidationException( "nome", "nome não pode ser nulo");
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        try {
            return usuarioRepository.findAll().list();
        } catch (Exception e) {
            throw new ValidationException("findAll", "Erro ao buscar usuários: " + e.getMessage());
        }
    }

    @Inject
    public HashService hashService;

    @Override
    @Transactional
    public Usuario create(UsuarioCreateRequestDTO dto) {

        if (dto.username() == null || dto.username().isBlank()) {
            throw new ValidationException("username", "Username é obrigatório.");
        }
    
        if (usuarioRepository.findByUsername(dto.username()) != null) {
            throw new ValidationException("username", "Username já cadastrado.");
        }
    
    
        if (dto.senha() == null || dto.senha().isBlank()) {
            throw new ValidationException("senha", "Senha é obrigatória.");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        usuario.setPerfil(Perfil.USER); 

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

        String hashSenhaAntiga = hashService.getHashSenha(dto.senhaAntiga());
        if (!usuario.getSenha().equals(hashSenhaAntiga)) {
            throw new ValidationException("senhaAntiga", "A senha antiga está incorreta");
        }

        if (!usuario.getUsername().equals(dto.usernameAntigo())) {
            throw new ValidationException("usernameAntigo", "O username antigo está incorreto");
        }

        usuario.setUsername(dto.novoUsername()); 
        usuario.setSenha(hashService.getHashSenha(dto.novaSenha())); 

        return usuario;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(id == null) {
            throw new ValidationException( "id", "Id não pode ser nulo");
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario findByUsername(String username) {
        if (username == null) {
            throw new ValidationException("username", "Username não pode ser nulo");
        }
        return usuarioRepository.findByUsername(username);
    }

}
