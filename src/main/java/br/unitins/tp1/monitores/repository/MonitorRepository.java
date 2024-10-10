package br.unitins.tp1.monitores.repository;

import java.util.List;

import br.unitins.tp1.monitores.model.Monitor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MonitorRepository implements PanacheRepository<Monitor> {

    public List<Monitor> findByModelo (String modelo){
        return find("SELECT monitor FROM Monitor monitor WHERE monitor.modelo LIKE ?1", "%"+modelo+"%").list();

    }
    public List<Monitor> findByMarca (String marca){
        return find("SELECT monitor FROM Monitor monitor WHERE monitor.marca LIKE ?1", "%"+marca+"%").list();

    }
   
}
