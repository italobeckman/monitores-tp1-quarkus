package br.unitins.tp1.monitores.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Monitor extends DefaultEntity {
   
    private String nome;
    private String marca;
    private String modelo;
    private Double preco;
    private String taxaAtualizacao;
    private String tempoResposta;
    private LocalDate anoLancamento;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tamanho_monitor")
    private TamanhoMonitor tamanhoMonitor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fabricante")
    private Fabricante fabricante;
    


    
    public TamanhoMonitor getTamanhoMonitor() {
        return tamanhoMonitor;
    }
    
    public void setTamanhoMonitor(TamanhoMonitor tamanhoMonitor) {
        this.tamanhoMonitor = tamanhoMonitor;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(LocalDate anoLancamento) {
        this.anoLancamento = anoLancamento;
    }


    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    
    

    
}
