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
        logger.info("Login efetuado por:  " + authDTO.username());
        String hash = hashService.getHashSenha(authDTO.senha());
        logger.fine("Hash de senha gerado ");

        Usuario usuario = usuarioService.findByUsernameAndSenha(authDTO.username(), hash);

        if (usuario == null) {
            logger.warning("User  not found: " + authDTO.username());
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("Usuario não encontrado").build();
        }

        // Verificar a role e buscar na tabela correspondente
        if ("Adm".equalsIgnoreCase(authDTO.role())) {
            Funcionario funcionario = funcionarioService.findByUsername(authDTO.username());

            if (funcionario == null) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("Funcionário não encontrado").build();
            }
            usuario.setPerfil(Perfil.ADM); // ou o perfil adequado para Funcionário

            // Geração do JWT ou outras ações
        } else if ("Cliente".equalsIgnoreCase(authDTO.role())) {
            Cliente cliente = clienteService.findByUsername(authDTO.username());
            if (cliente == null) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("Cliente não encontrado").build();
            }
            usuario.setPerfil(Perfil.USER); // ou o perfil adequado para Funcionário
            // Geração do JWT ou outras ações
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Role inválida").build();
        }

        return Response.ok()
                .header("Authorization", jwtService.generateJwt(UsuarioResponseDTO.valueOf(usuario)))
                .build();
    }

}