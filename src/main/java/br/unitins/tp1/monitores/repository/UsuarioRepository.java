package br.unitins.tp1.monitores.repository;

import br.unitins.tp1.monitores.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    
    public Usuario findByUsername(String username, String senha) {
        return find("SELECT u FROM Usuario u WHERE u.username LIKE ?1 and u.senha = ?2", username, senha).firstResult();
    }
    
}
