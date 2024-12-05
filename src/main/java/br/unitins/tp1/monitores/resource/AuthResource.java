package br.unitins.tp1.monitores.resource;

import jakarta.ws.rs.core.MediaType;

import java.util.logging.Logger;

import br.unitins.tp1.monitores.dto.auth.AuthRequestDTO;
import br.unitins.tp1.monitores.dto.usuario.UsuarioResponseDTO;
import br.unitins.tp1.monitores.model.Cliente;
import br.unitins.tp1.monitores.model.Funcionario;
import br.unitins.tp1.monitores.model.Perfil;
import br.unitins.tp1.monitores.model.Usuario;
import br.unitins.tp1.monitores.service.ClienteService;
import br.unitins.tp1.monitores.service.FuncionarioService;
import br.unitins.tp1.monitores.service.HashService;
import br.unitins.tp1.monitores.service.JwtService;
import br.unitins.tp1.monitores.service.user.UsuarioService;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private static final Logger logger = Logger.getLogger(AuthResource.class.getName());

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JwtService jwtService;

    @Inject 
    FuncionarioService funcionarioService;

    @Inject
    ClienteService clienteService;
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(AuthRequestDTO authDTO) {
        logger.info("Tentativa de login efetuado por:  " + authDTO.username());
        String hash = hashService.getHashSenha(authDTO.senha());
        logger.fine("Hash de senha gerado ");

        Usuario usuario = usuarioService.findByUsernameAndSenha(authDTO.username(), hash);
        
        if (usuario == null) {
            logger.warning("User  not found: " + authDTO.username());
            return Response.status(Status.UNAUTHORIZED).entity("Usuário ou senha inválidos").build();
        }

        if ("Adm".equalsIgnoreCase(authDTO.role())) {
            Funcionario funcionario = funcionarioService.findByUsername(authDTO.username());

            if (funcionario == null) {
                return Response.status(Status.UNAUTHORIZED).entity("Não há funcionario vinculado a este login.").build();
            }
            usuario.setPerfil(Perfil.ADM); 

            
        } else if ("Cliente".equalsIgnoreCase(authDTO.role())) {
            Cliente cliente = clienteService.findByUsername(authDTO.username());
            if (cliente == null) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("Cliente não encontrado").build();
            }
            usuario.setPerfil(Perfil.USER); 
           
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Role inválida").build();
        }

        logger.info("Usuário logado com sucesso: " + usuario.getUsername());
        return Response.ok()
                .header("Authorization", jwtService.generateJwt(UsuarioResponseDTO.valueOf(usuario)))
                .build();
    }

    

}