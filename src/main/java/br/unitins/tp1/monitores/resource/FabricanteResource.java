package br.unitins.tp1.monitores.resource;

import java.util.List;

import br.unitins.tp1.monitores.dto.fabricante.FabricanteRequestDTO;
import br.unitins.tp1.monitores.model.Fabricante;
import br.unitins.tp1.monitores.service.FabricanteService;
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

@Path("/fabricantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FabricanteResource {

    @Inject
    public FabricanteService fabricanteService;

    @GET
    @Path("/{id}")
    public Fabricante findById(@PathParam("id") Long id) {
        return fabricanteService.findById(id);
    }

    @GET
    @Path("/search/{nome}")
    public List<Fabricante> findByNome(@PathParam("nome") String nome) {
        return fabricanteService.findByNome(nome);
    }

    @GET
    public List<Fabricante> findAll() {
        return fabricanteService.findAll();
    }

    @POST
    public Fabricante create(FabricanteRequestDTO fabricante) {

        return fabricanteService.create(fabricante);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, FabricanteRequestDTO fabricante) {
        fabricanteService.update(id, fabricante);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        fabricanteService.delete(id);
    }
    
}
