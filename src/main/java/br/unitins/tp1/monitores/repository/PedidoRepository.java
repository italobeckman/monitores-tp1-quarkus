package br.unitins.tp1.monitores.repository;

import java.util.List;

import br.unitins.tp1.monitores.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {
    
    public List<Pedido> findByUsuario(Long idUsuario) {
        return find("SELECT p FROM Pedido p WHERE p.usuario.id = ?1", idUsuario).list();
    }

    
    
}
