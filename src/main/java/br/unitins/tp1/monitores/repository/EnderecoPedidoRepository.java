package br.unitins.tp1.monitores.repository;

import br.unitins.tp1.monitores.model.EnderecoPedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class EnderecoPedidoRepository implements PanacheRepository<EnderecoPedido> {
    public EnderecoPedido findById(Long id) {
        return find("id", id).firstResult();
    }
}
