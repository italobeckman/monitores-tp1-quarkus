package br.unitins.tp1.monitores.repository;

import br.unitins.tp1.monitores.model.Lote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoteRepository implements PanacheRepository<Lote> {
    

    /** 
    * @return retorna o monitor com o lote mais antigo e com quantidade maior que zero
    */
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
    // codigo nem sempre unico sendo por fabricante, ou seja, concatenar com o id do fabricante como exemplo --
    public Lote findByCodigo(String codigo) {
        return find("SELECT l FROM Lote l WHERE l.codigo = ?1", codigo).firstResult();    
    }
}
