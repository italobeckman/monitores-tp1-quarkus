package br.unitins.tp1.monitores.dto.usuario;

import br.unitins.tp1.monitores.model.Usuario;
import br.unitins.tp1.monitores.model.Perfil;

public record UsuarioResponseDTO(
    Long id, 
    String username,
    String senha,
    Perfil perfil
    ) {


    public static UsuarioResponseDTO valueOf(Usuario usuario){
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getSenha(),
            usuario.getPerfil());

    };
}
