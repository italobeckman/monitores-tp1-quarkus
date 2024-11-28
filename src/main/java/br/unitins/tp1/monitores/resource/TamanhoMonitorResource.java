package br.unitins.tp1.monitores.resource;

import java.util.List;

import br.unitins.tp1.monitores.dto.monitor.TamanhoMonitorRequestDTO;
import br.unitins.tp1.monitores.dto.monitor.TamanhoMonitorResponseDTO;
import br.unitins.tp1.monitores.model.TamanhoMonitor;
import br.unitins.tp1.monitores.service.TamanhoMonitorService;
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

@Path("/tamanho-monitor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TamanhoMonitorResource {

    @Inject
    public TamanhoMonitorService tamanhoMonitorService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"Adm", "User"})
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(TamanhoMonitorResponseDTO.valueOf(tamanhoMonitorService.findById(id))).build();
    }

    @GET
    public Response findAll() {
        List<TamanhoMonitor> tamanhos = tamanhoMonitorService.findAll();
        return Response.ok(tamanhos.stream().map(TamanhoMonitorResponseDTO::valueOf).toList()).build();
    }

    @POST
    public Response create(TamanhoMonitorRequestDTO estado) {
        return Response.status(Status.CREATED).entity(TamanhoMonitorResponseDTO.valueOf(tamanhoMonitorService.create(estado))).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, TamanhoMonitorRequestDTO estado) {
        tamanhoMonitorService.update(id, estado);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        tamanhoMonitorService.delete(id);
        return Response.noContent().build();
    }
    
}
