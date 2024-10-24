package br.unitins.tp1.monitores.repository;

import java.util.List;

import br.unitins.tp1.monitores.model.TelefoneFabricante;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelefoneFabricanteRepository implements PanacheRepository<TelefoneFabricante> {
    
    public List<TelefoneFabricante> findByNumero(String numero) {
        return find("SELECT f FROM TelefoneFabricante f WHERE f.numero = ?1", numero).list();
    }

    
}
