package br.unitins.tp1.monitores.model;

import jakarta.persistence.Entity;

@Entity
public class Cliente extends DefaultEntity {

    private String nome;
    private String cpf;
    private Sexo sexo;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    

}
