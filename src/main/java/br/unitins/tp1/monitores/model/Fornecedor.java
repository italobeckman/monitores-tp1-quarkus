package br.unitins.tp1.monitores.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    private String email;

    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fornecedor_id")
    private List<TelefoneFornecedor> listaTelefone;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fornecedor nome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Fornecedor cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<TelefoneFornecedor> getListaTelefone() {
        return listaTelefone;
    }

    public void setListaTelefone(List<TelefoneFornecedor> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }
    
}
