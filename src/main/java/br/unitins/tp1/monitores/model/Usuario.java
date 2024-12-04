package br.unitins.tp1.monitores.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Usuario extends DefaultEntity {
        @Column(unique = true)
        private String username;
        private Perfil perfil;
        private String senha;
        private String cpf;

        
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public Perfil getPerfil() {
            return perfil;
        }
        public void setPerfil(Perfil perfil) {
            this.perfil = perfil;
        }
        public String getSenha() {
            return senha;
        }
        public void setSenha(String senha) {
            this.senha = senha;
        }
        public String getCpf() {
            return cpf;
        }
        public void setCpf(String cpf) {
            this.cpf = cpf;
        }
        

        
}
