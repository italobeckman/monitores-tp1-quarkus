package br.unitins.tp1.monitores.repository;

import java.util.List;

import br.unitins.tp1.monitores.model.TelefoneFabricante;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelefoneFabricanteRepository implements PanacheRepository<TelefoneFabricante> {
    
    public List<TelefoneFabricante> findByNome(String nome) {
        return find("SELECT f FROM TelefoneFabricante f WHERE f.nome LIKE ?1", "%" + nome + "%").list();
    }
    
}
