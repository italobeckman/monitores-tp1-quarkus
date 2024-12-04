package br.unitins.tp1.monitores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente extends DefaultEntity {

    private String username;
    private String nome;
    private String cpf;
    private Sexo sexo;
    private String nomeImagem;
    private String email;
    
   
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_endereco_user")
    private EnderecoUser enderecoUser;

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
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

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EnderecoUser getEnderecoUser() {
        return enderecoUser;
    }

    public void setEnderecoUser(EnderecoUser enderecoUser) {
        this.enderecoUser = enderecoUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isCadastroCompleto() {
        return nome != null && !nome.isEmpty() &&
               email != null && !email.isEmpty() &&
               enderecoUser  != null && enderecoUser.isCadastroCompleto();
               
    }
    

}
