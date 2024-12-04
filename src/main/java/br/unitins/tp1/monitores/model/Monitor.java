package br.unitins.tp1.monitores.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.List;

@Entity
public class Monitor extends DefaultEntity {

    private String nome;
    private String marca;
    private String modelo;
    private Double preco;
    private String taxaAtualizacao;
    private String tempoResposta;
    private Integer anoLancamento;

    @ElementCollection
    @CollectionTable(name = "imagem_monitor", joinColumns = @JoinColumn(name = "id_monitor"))
    private List<String> listaImagem;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tamanho_monitor_id")
    private TamanhoMonitor tamanhoMonitor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fabricante_id")
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

    public List<String> getListaImagem() {
        return listaImagem;
    }

    public void setListaImagem(List<String> listaImagem) {
        this.listaImagem = listaImagem;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

}
