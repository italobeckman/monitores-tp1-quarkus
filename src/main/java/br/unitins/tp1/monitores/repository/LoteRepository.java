package br.unitins.tp1.monitores.repository;

import br.unitins.tp1.monitores.model.Lote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoteRepository implements PanacheRepository<Lote> {
    
    /** 
     * @return retorna o monitor com o lote mais antigo e com quantidade maior que zero
     */
    
    /*  
    public Lote findByIdMonitor(Long idMonitor) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT ");
        jpql.append("l ");
        jpql.append("FROM ");
        jpql.append("Lote l ");
        jpql.append("WHERE ");
        jpql.append("L.monitor.id = ?1 ");
        jpql.append("AND l.estoque > 0 ");
        jpql.append("ORDER BY l.data ");


        return find(jpql.toString(), idMonitor).firstResult();
    }
    */

    public Lote findByIdMonitor(Long idMonitor){


        return find("SELECT l FROM Lote l WHERE l.monitor.id = ?1  ORDER BY l.data", idMonitor).firstResult();
    }
    public Lote findByIdMonitorLote(Long idMonitor) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT ");
        jpql.append(" l ");
        jpql.append(" FROM ");
        jpql.append(" Lote l ");
        jpql.append(" WHERE ");
        jpql.append(" l.monitor.id = ?1 ");
        jpql.append(" AND l.quantidade > 0 ");
        jpql.append(" ORDER BY l.data ");
        return find(jpql.toString(), idMonitor).firstResult();
    }

    
    public Lote findByCodigo(String codigo) {
        return find("SELECT l FROM Lote l WHERE l.codigo = ?1", codigo).firstResult();    
    }
}
