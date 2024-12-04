package br.unitins.tp1.monitores.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EnderecoUser extends DefaultEntity {

    @Column(length = 60, nullable = false)
    private String logradouro;
    @Column(length = 10, nullable = false)
    private String numero;
    @Column(length = 60)
    private String complemento;
    @Column(length = 60, nullable = false)
    private String bairro;
    
    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;
    
    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    @Column(length = 60, nullable = false)
    private String cep;
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public Municipio getMunicipio() {
        return municipio;
    }
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean isCadastroCompleto() {
        return 
        logradouro != null && !logradouro.isEmpty() &&
         numero != null && !numero.isEmpty() &&
         bairro != null && !bairro.isEmpty() &&
         estado != null && estado.getId() != null &&
         municipio != null && municipio.getId() != null &&
         cep != null && !cep.isEmpty();
    }
    
    
}
