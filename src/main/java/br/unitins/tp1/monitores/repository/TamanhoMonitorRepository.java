package br.unitins.tp1.monitores.repository;

import br.unitins.tp1.monitores.model.TamanhoMonitor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TamanhoMonitorRepository implements PanacheRepository<TamanhoMonitor> {

    public TamanhoMonitor findById(Long id) {
        return find("id", id).firstResult();
    }
}
