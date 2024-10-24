package br.unitins.tp1.monitores.repository;

import java.util.List;

import br.unitins.tp1.monitores.model.TelefoneFornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelefoneFornecedorRepository implements PanacheRepository<TelefoneFornecedor> {
    
    public List<TelefoneFornecedor> findByNumero(String numero) {
        return find("SELECT f FROM TelefoneFornecedor f WHERE f.numero = ?1", numero).list();
    }
    
}
