package br.unitins.tp1.monitores.resource;

import java.util.List;

import br.unitins.tp1.monitores.dto.fabricante.TelefoneFabricanteRequestDTO;
import br.unitins.tp1.monitores.model.TelefoneFabricante;
import br.unitins.tp1.monitores.service.TelefoneFabricanteService;
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

@Path("/fabricante/telefoneFabricantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneFabricanteResource {

    @Inject
    public TelefoneFabricanteService telefoneFabricanteService;

    @GET
    @Path("/{id}")
    public TelefoneFabricante findById(@PathParam("id") Long id) {
        return telefoneFabricanteService.findById(id);
    }

    @GET
    public List<TelefoneFabricante> findAll() {
        return telefoneFabricanteService.findAll();
    }

    @POST
    public TelefoneFabricante create(TelefoneFabricanteRequestDTO telefoneFabricante) {

        return telefoneFabricanteService.create(telefoneFabricante);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, TelefoneFabricanteRequestDTO telefoneFabricante) {
        telefoneFabricanteService.update(id, telefoneFabricante);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        telefoneFabricanteService.delete(id);
    }
    
}
