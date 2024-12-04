package br.unitins.tp1.monitores.model.pagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Boleto extends Pagamento {

    @Column(nullable = false)
    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}