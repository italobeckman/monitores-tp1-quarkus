package br.unitins.tp1.monitores.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Monitor extends DefaultEntity {
    
    @Column(length = 30, nullable = false)
    private String modelo;
    @Column(length = 10, nullable = false)
    private Double preco;

    @Column(length = 15, nullable = false)
    private String marca;
    @Column(length = 3, nullable = false)
    private String taxaAtualizacao;
    @Column(length = 3, nullable = false)
    private String tempoResposta;
    @Column(length = 3, nullable = false)
    private String peso;
    

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getTaxaAtualizacao() {
        return taxaAtualizacao;
    }
    public void setTaxaAtualizacao(String taxaAtualizacao) {
        this.taxaAtualizacao = taxaAtualizacao;
    }
    public String getTempoResposta() {
        return tempoResposta;
    }
    public void setTempoResposta(String tempoResposta) {
        this.tempoResposta = tempoResposta;
    }
    public String getPeso() {
        return peso;
    }
    public void setPeso(String peso) {
        this.peso = peso;
    }

    
}
