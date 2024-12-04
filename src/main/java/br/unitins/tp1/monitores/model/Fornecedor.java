package br.unitins.tp1.monitores.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
@Entity
public class Fornecedor extends DefaultEntity {
    private String nome;
    private String cnpj;
    @Email
    private String email;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fornecedor_id")
    private List<TelefoneFornecedor> listaTelefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
