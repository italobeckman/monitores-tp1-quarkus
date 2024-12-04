package br.unitins.tp1.monitores.model.pedido;


import br.unitins.tp1.monitores.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class StatusPedido extends DefaultEntity {
    @Column(nullable = false)
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}