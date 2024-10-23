package br.unitins.tp1.monitores.repository;

import java.util.List;

import br.unitins.tp1.monitores.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
    
    public List<Cliente> findByNome(String nome) {
        return find("SELECT c FROM Cliente c WHERE c.nome LIKE ?1", "%" + nome + "%").list();
    }
    public Cliente findByCpf(String cpf) {
        return find("SELECT c FROM Cliente c WHERE c.cpf = ?1",  cpf ).firstResult();
    }
    
}
