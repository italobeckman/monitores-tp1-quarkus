package br.unitins.tp1.monitores.repository;

import br.unitins.tp1.monitores.model.EnderecoUser;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class EnderecoUserRepository implements PanacheRepository<EnderecoUser> {
    public EnderecoUser findById(Long id) {
        return find("id", id).firstResult();
    }
}
