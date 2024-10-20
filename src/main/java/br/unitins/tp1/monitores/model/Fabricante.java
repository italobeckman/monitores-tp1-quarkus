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
public class Fabricante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    private String email;

    @OneToMany(mappedBy = "fabricante", cascade = CascadeType.ALL)
    private List<Monitor> listaMonitor;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fabricante_id")
    private List<TelefoneFabricante> listaTelefone;
    
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

    public List<Monitor> getListaMonitor() {
        return listaMonitor;
    }

    public void setListaMonitor(List<Monitor> listaMonitor) {
        this.listaMonitor = listaMonitor;
    }
    
}
