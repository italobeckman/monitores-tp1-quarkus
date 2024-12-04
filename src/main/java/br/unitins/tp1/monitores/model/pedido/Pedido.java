package br.unitins.tp1.monitores.model.pedido;


import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.monitores.model.Cliente;
import br.unitins.tp1.monitores.model.DefaultEntity;
import br.unitins.tp1.monitores.model.EnderecoPedido;
import br.unitins.tp1.monitores.model.pagamento.Pagamento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Pedido extends DefaultEntity{
    
    private String codigo_pedido;
    @Column(nullable = false)
    private LocalDateTime data;
    
    @Column(nullable = false)
    private Double total;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_endereco", nullable = false)
    private EnderecoPedido enderecoPedido;
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pedido", nullable = false)
    private List<ItemPedido> listaItem;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pedido")
    private List<StatusPedido> listaStatus;

    @Column(name = "prazo_pagamento", nullable = false)
    private LocalDateTime prazoPagamento;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DETACH}, orphanRemoval = true)
    @JoinColumn(name = "id_pagamento")
    private Pagamento pagamento;

    
    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getListaItem() {
        return listaItem;
    }

    public void setListaItem(List<ItemPedido> listaItem) {
        this.listaItem = listaItem;
    }

    public List<StatusPedido> getListaStatus() {
        return listaStatus;
    }

    public void setListaStatus(List<StatusPedido> listaStatus) {
        this.listaStatus = listaStatus;
    }

    public LocalDateTime getPrazoPagamento() {
        return prazoPagamento;
    }

    public void setPrazoPagamento(LocalDateTime prazoPagamento) {
        this.prazoPagamento = prazoPagamento;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public EnderecoPedido getEnderecoPedido() {
        return enderecoPedido;
    }

    public void setEnderecoPedido(EnderecoPedido enderecoPedido) {
        this.enderecoPedido = enderecoPedido;
    }

    public String getCodigo_pedido() {
        return codigo_pedido;
    }

    public void setCodigo_pedido(String codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }
}