package br.unitins.tp1.monitores.resource;

import java.util.List;

import br.unitins.tp1.monitores.dto.fabricante.TelefoneFabricanteRequestDTO;
import br.unitins.tp1.monitores.dto.fabricante.TelefoneFabricanteResponseDTO;
import br.unitins.tp1.monitores.model.TelefoneFabricante;
import br.unitins.tp1.monitores.service.TelefoneFabricanteService;
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

@Path("/fabricante/telefonefabricantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneFabricanteResource {

    @Inject
    public TelefoneFabricanteService telefoneFabricanteService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(TelefoneFabricanteResponseDTO.valueOf(telefoneFabricanteService.findById(id))).build();
    }
    @GET
    @Path("/numero/{numero}")
    public Response findByNumero(@PathParam("numero") String numero) {
        return Response.ok(TelefoneFabricanteResponseDTO.valueOf(telefoneFabricanteService.findByNumero(numero))).build();
    }
    
    @GET
    public Response findAll() {
        List<TelefoneFabricante> telefone = telefoneFabricanteService.findAll();
        return Response.ok(telefone.stream().map(TelefoneFabricanteResponseDTO::valueOf).toList()).build();
    }

    @POST
    public Response create(@Valid TelefoneFabricanteRequestDTO telefone) {
        return Response.status(Status.CREATED)
                .entity(TelefoneFabricanteResponseDTO.valueOf(telefoneFabricanteService.create(telefone))).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid TelefoneFabricanteRequestDTO telefone) {
        telefoneFabricanteService.update(id, telefone);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        telefoneFabricanteService.delete(id);
        return Response.noContent().build();
    }

}
