package br.unitins.tp1.monitores.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unitins.tp1.monitores.dto.fabricante.FabricanteRequestDTO;
import br.unitins.tp1.monitores.dto.fabricante.FabricanteResponseDTO;
import br.unitins.tp1.monitores.model.Fabricante;
import br.unitins.tp1.monitores.service.FabricanteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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

@Path("/fabricantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FabricanteResource {

    private static final Logger LOG = LoggerFactory.getLogger(FabricanteResource.class);

    @Inject
    public FabricanteService fabricanteService;
    @RolesAllowed({ "Adm" })
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando fabricante com ID: {}", id);

        Fabricante fabricante = fabricanteService.findById(id);

        if (fabricante == null) {
            LOG.warn("Fabricante com ID {} não encontrado.", id);
            return Response.status(Status.NOT_FOUND).build();
        }

        LOG.info("Fabricante encontrado: {}", FabricanteResponseDTO.valueOf(fabricante));
        return Response.ok(FabricanteResponseDTO.valueOf(fabricante)).build();
    }
    @RolesAllowed({ "Adm" })
    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Buscando fabricantes com nome: {}", nome);
        List<Fabricante> fabricantes = fabricanteService.findByNome(nome);
        LOG.info("Encontrados {} fabricantes com o nome: {}", fabricantes.size(), nome);
        return Response.ok(fabricantes.stream().map(FabricanteResponseDTO::valueOf).toList()).build();
    }
    @RolesAllowed({ "Adm" })
    @GET
    public Response findAll() {
        LOG.info("Buscando todos os fabricantes.");
        List<Fabricante> fabricantes = fabricanteService.findAll();
        LOG.info("Encontrados {} fabricantes.", fabricantes.size());
        return Response.ok(fabricantes.stream().map(FabricanteResponseDTO::valueOf).toList()).build();
    }
    @RolesAllowed({ "Adm" })
    @POST
    public Response create(@Valid FabricanteRequestDTO fabricanteDTO) {
        LOG.info("Criando novo fabricante: {}", fabricanteDTO);
        Fabricante created = fabricanteService.create(fabricanteDTO);
        LOG.info("Fabricante criado com sucesso: {}", FabricanteResponseDTO.valueOf(created));
        return Response.status(Status.CREATED).entity(FabricanteResponseDTO.valueOf(created)).build();
    }
    @RolesAllowed({ "Adm" })
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid FabricanteRequestDTO fabricanteDTO) {
        LOG.info("Atualizando fabricante com ID: {} com os dados: {}", id, fabricanteDTO);
        try {
            fabricanteService.update(id, fabricanteDTO);
            LOG.info("Fabricante com ID: {} atualizado com sucesso.", id);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.error("Erro ao tentar atualizar o fabricante com ID {}: {}", id, e.getMessage());
            return Response.serverError().entity("Sistema temporariamente indisponível.").build();
        }
        

    }
    @RolesAllowed({ "Adm" })
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando fabricante com ID: {}", id);
        try {
            fabricanteService.delete(id);
            LOG.info("Fabricante com ID: {} deletado com sucesso.", id);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.error("Erro ao tentar deletar o fabricante com ID {}: {}", id, e.getMessage());
            return Response.serverError().entity("Sistema temporariamente indisponível.").build();
        }
    }
}
