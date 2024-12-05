package br.unitins.tp1.monitores.repository;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.monitores.model.pedido.Pedido;
import br.unitins.tp1.monitores.model.pedido.Status;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {
    public List<Pedido> findByCliente(Long idCliente) {
        return find("WHERE cliente.id = ?1", idCliente).list();
    }

    public List<Pedido> findByItem(Long idMonitor) {
        return find("JOIN listaItem l WHERE l.monitor.id = ?1 ", idMonitor).list();
    }

    public List<Pedido> findByStatus(Integer idStatus) {
        return find("JOIN listaStatus l WHERE l.status = ?1", Status.valueOf(idStatus)).list();
    }

    public List<Pedido> findPedidosExpirados(LocalDateTime dataVerificacao) {
        return find("WHERE ?1 > prazoPagamento AND pagamento IS NULL", dataVerificacao).list();
    }

    public List<Pedido> findByUsername(String username) {
        return find("SELECT p FROM Pedido p WHERE p.cliente.username = ?1", username).list();
    }

    /* */
    public List<Pedido> findPedidosStatusDiferente() {
        return find("select distinct p from Pedido p, StatusPedido sp where sp.status not in (1,2)").list();      }
    
    
    

}