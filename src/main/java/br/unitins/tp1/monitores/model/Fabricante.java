package br.unitins.tp1.monitores.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
@Entity
public class Fabricante extends DefaultEntity {
    private String nome;
    private String cnpj;
    @Email
    private String email;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fabricante_id")
    private List<TelefoneFabricante> listaTelefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fabricante nome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Fabricante cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<TelefoneFabricante> getListaTelefone() {
        return listaTelefone;
    }

    public void setListaTelefone(List<TelefoneFabricante> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }
    
}
