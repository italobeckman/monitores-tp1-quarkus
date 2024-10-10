package br.unitins.tp1.monitores.repository;

import java.util.List;

import br.unitins.tp1.monitores.model.Estado;
import br.unitins.tp1.monitores.model.Municipio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MunicipioRepository implements PanacheRepository<Municipio> {
    
    public List<Municipio> findByNome (String nome){
        // return find("SELECT e FROM Municipio e WHERE e.nome LIKE ?1", "%"+nome+"%").list();
        return find("SELECT e FROM Municipio m WHERE m.nome LIKE ?1", "%"+nome+"%").list();
    }
    public List<Municipio> findByEstado (Estado estado){
        return find("SELECT e FROM Municipio m WHERE m.estado.id LIKE ?1 =", estado.getId()).list();

    }
    
}
