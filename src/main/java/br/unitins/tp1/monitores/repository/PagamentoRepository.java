package br.unitins.tp1.monitores.repository;

import br.unitins.tp1.monitores.model.pagamento.Pagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {
    
}
