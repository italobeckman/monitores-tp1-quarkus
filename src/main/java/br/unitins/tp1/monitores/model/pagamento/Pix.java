package br.unitins.tp1.monitores.model.pagamento;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Pix extends Pagamento {
    
    @Column(nullable = false)
    private String chaveDestinatario;

    @Column(nullable = false)
    private String identificador;

    public String getChaveDestinatario() {
        return chaveDestinatario;
    }

    public void setChaveDestinatario(String chaveDestinatario) {
        this.chaveDestinatario = chaveDestinatario;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}