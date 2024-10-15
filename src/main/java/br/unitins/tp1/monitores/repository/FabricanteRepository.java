package br.unitins.tp1.monitores.repository;

import java.util.List;

import br.unitins.tp1.monitores.model.Fabricante;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FabricanteRepository implements PanacheRepository<Fabricante> {
    
    public List<Fabricante> findByNome(String nome) {
        return find("SELECT f FROM Fabricante f WHERE f.nome LIKE ?1", "%" + nome + "%").list();
    }
    
}
