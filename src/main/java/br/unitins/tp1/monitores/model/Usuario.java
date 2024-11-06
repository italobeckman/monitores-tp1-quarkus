package br.unitins.tp1.monitores.model;

import jakarta.persistence.Entity;

@Entity
public class Usuario extends DefaultEntity {
        private String username;
        private Perfil perfil;
        private String senha;
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
        

        
}
