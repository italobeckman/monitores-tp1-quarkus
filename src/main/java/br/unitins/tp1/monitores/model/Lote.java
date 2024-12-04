package br.unitins.tp1.monitores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
@Entity
public class Lote extends DefaultEntity {
    
    @ManyToOne
    @JoinColumn(name = "id_monitor")
    private Monitor monitor;
    private LocalDate data;
    private String codigo;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    Fornecedor fornecedor;

    public Monitor getMonitor() {
        return monitor;
    }
    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    

}
