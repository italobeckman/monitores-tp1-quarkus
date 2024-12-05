package br.unitins.tp1.monitores.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unitins.tp1.monitores.dto.usuario.UsuarioCreateRequestDTO;
import br.unitins.tp1.monitores.dto.usuario.UsuarioResponseDTO;
import br.unitins.tp1.monitores.dto.usuario.UsuarioUpdateRequestDTO;
import br.unitins.tp1.monitores.model.Usuario;
import br.unitins.tp1.monitores.service.user.UsuarioService;
import br.unitins.tp1.monitores.validation.ValidationException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);

    @Inject
    UsuarioService usuarioService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/search/{id}")
    @RolesAllowed("Adm") 
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando usuário por ID: %d", id);
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            LOG.warn("Usuário com ID %d não encontrado.", id);
            return Response.status(Status.NOT_FOUND).build();
        }
        LOG.info("Usuário com ID %d encontrado com sucesso.", id);
        return Response.ok(UsuarioResponseDTO.valueOf(usuario)).build();
    }

    @POST 
    public Response create(@Valid UsuarioCreateRequestDTO dto) {
        LOG.info("Criando novo usuário.");
        Usuario usuario = usuarioService.create(dto);
        LOG.info("Usuário criado com sucesso. ID: %d", usuario.getId());
        return Response.status(Status.CREATED).entity(UsuarioResponseDTO.valueOf(usuario)).build();
    }

    @PATCH
    @Path("/update")
    @RolesAllowed({"Adm", "User"})
    public Response update(@Valid UsuarioUpdateRequestDTO dto) {
        String username = jwt.getSubject();


        LOG.info("Atualizando usuário com ID: %d", username);

        try {
            usuarioService.update(username, dto);
            LOG.info("Usuário atualizado com sucesso");
            return Response.noContent().build();
        } catch (ValidationException e) {
            LOG.error("Erro ao atualizar usuário", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Adm", "User"})
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Excluindo usuário com ID: %d", id);
        try {
            usuarioService.delete(id);
            LOG.info("Usuário com ID %d excluído com sucesso.", id);
            return Response.status(Status.NO_CONTENT).build();
        } catch (ValidationException e) {
            LOG.error("Erro ao excluir usuário com ID %d: %s", id, e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();

        }
    }

    @GET
    @RolesAllowed({"Adm"}) 
    public Response findAll() {
        LOG.info("Buscando todos os usuários.");
        List<Usuario> usuarios = usuarioService.findAll();
        LOG.info("Encontrados %d usuários.", usuarios.size());

        
        List<UsuarioResponseDTO> usuariosDTO = usuarios.stream()
                .map(UsuarioResponseDTO::valueOf)
                .toList();

        return Response.ok(usuariosDTO).build();
    }
    
}
