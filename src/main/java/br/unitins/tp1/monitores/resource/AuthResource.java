package br.unitins.tp1.monitores.resource;

import jakarta.ws.rs.core.MediaType;

import br.unitins.tp1.monitores.dto.auth.AuthRequestDTO;
import br.unitins.tp1.monitores.dto.usuario.UsuarioResponseDTO;
import br.unitins.tp1.monitores.model.Usuario;
import br.unitins.tp1.monitores.service.HashService;
import br.unitins.tp1.monitores.service.JwtService;
import br.unitins.tp1.monitores.service.UsuarioService;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JwtService jwtService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(AuthRequestDTO authDTO) {
        String hash = hashService.getHashSenha(authDTO.senha());

        Usuario usuario = usuarioService.findByUsernameAndSenha(authDTO.username(), hash);

        if (usuario == null) {
            return Response.status(Response.Status.NO_CONTENT)
                .entity("Usuario não encontrado").build();
        } 
        return Response.ok()
            .header("Authorization", jwtService.generateJwt(UsuarioResponseDTO.valueOf(null)))
            .build();
        
    }
  
}