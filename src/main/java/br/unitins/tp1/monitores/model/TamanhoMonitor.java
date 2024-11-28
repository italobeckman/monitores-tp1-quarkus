package br.unitins.tp1.monitores.model;

import jakarta.persistence.Entity;

@Entity
public class TamanhoMonitor extends DefaultEntity {

    private Double tamanho;
    private String altura;
    private String largura; 
    private String peso;

    
    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getLargura() {
        return largura;
    }

    public void setLargura(String largura) {
        this.largura = largura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Double getTamanho() {
        return tamanho;
    }

    public void setTamanho(Double tamanho) {
        this.tamanho = tamanho;
    }

}
