package br.unitins.tp1.monitores.resource;

import java.util.List;

import br.unitins.tp1.monitores.dto.fabricante.FabricanteRequestDTO;
import br.unitins.tp1.monitores.dto.fabricante.FabricanteResponseDTO;
import br.unitins.tp1.monitores.model.Fabricante;
import br.unitins.tp1.monitores.service.FabricanteService;
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

    @Inject
    public FabricanteService fabricanteService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(FabricanteResponseDTO.valueOf(fabricanteService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        List<Fabricante> fabricante = fabricanteService.findByNome(nome);
        return Response.ok(fabricante.stream().map(FabricanteResponseDTO::valueOf).toList()).build();
    }

    @GET
    public Response findAll() {
        List<Fabricante> fabricante = fabricanteService.findAll();
        return Response.ok(fabricante.stream().map(FabricanteResponseDTO::valueOf).toList()).build();
    }

    @POST
    public Response create(@Valid FabricanteRequestDTO fabricante) {
        return Response.status(Status.CREATED).entity(FabricanteResponseDTO.valueOf(fabricanteService.create(fabricante))).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid FabricanteRequestDTO fabricante) {
        fabricanteService.update(id, fabricante);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        fabricanteService.delete(id);
        return Response.noContent().build();
    }
    
}
