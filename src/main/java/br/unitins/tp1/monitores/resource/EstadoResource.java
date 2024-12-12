package br.unitins.tp1.monitores.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.tp1.monitores.dto.estado.EstadoRequestDTO;
import br.unitins.tp1.monitores.dto.estado.EstadoResponseDTO;
import br.unitins.tp1.monitores.model.Estado;
import br.unitins.tp1.monitores.service.EstadoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    private static final Logger LOG = Logger.getLogger(EstadoResource.class);

    @Inject
    public EstadoService estadoService;
    
    @GET
    @Path("/search/id/{id}")
    @RolesAllowed({"Adm"})
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando estado com ID: " + id);
        Estado estado = estadoService.findById(id);
        if (estado == null) {
            LOG.warn("Estado com ID " + id + " não encontrado.");
            return Response.status(Status.NOT_FOUND).build();
        }
        LOG.info("Estado com ID " + id + " encontrado com sucesso.");
        return Response.ok(EstadoResponseDTO.valueOf(estado)).build();
    }
    @RolesAllowed({ "Adm" })
    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {

        LOG.info("Buscando estados com nome: " + nome);
        List<Estado> estados = estadoService.findByNome(nome);
        LOG.info("Encontrados " + estados.size() + " estados com nome: " + nome);
        return Response.ok(estados.stream().map(EstadoResponseDTO::valueOf).toList()).build();
    }
    @RolesAllowed({ "Adm" })
    @GET
    public Response findAll() {
        LOG.info("Buscando todos os estados.");
        List<Estado> estados = estadoService.findAll();
        LOG.info("Encontrados " + estados.size() + " estados.");
        return Response.ok(estados.stream().map(EstadoResponseDTO::valueOf).toList()).build();
    }
    @RolesAllowed({ "Adm" })
    @POST
    @Path("/create")
    public Response create(EstadoRequestDTO estado) {
        if(estado == null) {
            return Response.status(Status.BAD_REQUEST).entity("Dados inválidos.").build();
        }
        LOG.info("Criando novo estado: " + estado);
        Estado created = estadoService.create(estado);

        if (created == null) {
            LOG.error("Erro ao criar o estado: " + estado + ".");
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        
        LOG.info("Estado criado com sucesso: " + estado);

        return Response.status(Status.CREATED).entity(EstadoResponseDTO.valueOf(created)).build();

    }
    @RolesAllowed({ "Adm" })
    @PUT
    @Path("update/id/{id}")
    public Response update(@PathParam("id") Long id, EstadoRequestDTO estado) {
        if(estado == null) {
            return Response.status(Status.BAD_REQUEST).entity("Dados inválidos.").build();
        }
        LOG.info("Atualizando estado com ID: " + id + ", com dados: " + estado);
        try {
            estadoService.update(id, estado);
            LOG.info("Estado com ID: " + id + " atualizado com sucesso.");
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.error("Erro ao tentar atualizar o estado com id" + id + ": " + e.getMessage());
            return Response.serverError().entity("Sistema temporariamente indisponível.").build();
        }

    }
    @RolesAllowed({ "Adm" })
    @DELETE
    @Path("delete/id/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando estado com ID: " + id);

        try {
            estadoService.delete(id);
            LOG.info("Estado com ID: " + id + " deletado com sucesso.");
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.error("Erro ao tentar deletar o estado com id" + id + ": " + e.getMessage());
            return Response.serverError().entity("Sistema temporariamente indisponível.").build();
        }
    }
    
}
