package br.unitins.tp1.monitores.model.pagamento;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Cartao extends Pagamento {

    @Column(name = "titular", nullable = false)
    private String titular;

    @Column(name = "cpf_cartao", nullable = false)
    private String cpfCartao;

    @Column(nullable = false)
    private String numero;

    @Column(name = "data_validade", nullable = false)
    private LocalDate dataValidade;

    @Column(name = "codigo_seguranca", nullable = false)
    private Integer codigoSeguranca;

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCpfCartao() {
        return cpfCartao;
    }

    public void setCpfCartao(String cpfCartao) {
        this.cpfCartao = cpfCartao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Integer getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(Integer codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }
}