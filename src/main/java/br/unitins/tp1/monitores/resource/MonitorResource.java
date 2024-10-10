package br.unitins.tp1.monitores.resource;

import java.util.List;

import br.unitins.tp1.monitores.dto.monitor.MonitorRequestDTO;
import br.unitins.tp1.monitores.dto.monitor.MonitorResponseDTO;
import br.unitins.tp1.monitores.dto.municipio.MunicipioRequestDTO;
import br.unitins.tp1.monitores.dto.municipio.MunicipioResponseDTO;
import br.unitins.tp1.monitores.model.Monitor;
import br.unitins.tp1.monitores.service.MonitorService;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/monitores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MonitorResource {
    @Inject
    public MonitorService monitorService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id")Long id){
        return Response.ok(monitorService.findById(id)).build();
        
    }
    @GET
    public Response findAll(){
        return Response.ok(monitorService.findAll()).build();
        
    }
   
    @GET
    @Path("/search/{modelo}")
    public Response findByModelo(@PathParam("modelo")String modelo){
        return Response.ok(
            monitorService.findByModelo(modelo)
            .stream()
            .map(MonitorResponseDTO::valueOf)
            .toList())
            .build();
        
    }


    @GET
    @Path("/search/{marca}")
    public Response findByMarca(@PathParam("marca")String marca){
        return Response.ok(
            monitorService.findByMarca(marca)
            .stream()
            .map(MonitorResponseDTO::valueOf)
            .toList())
            .build();
        
    }


    @POST
    public Response create (MonitorRequestDTO dto) {
        return Response.ok(MonitorResponseDTO.valueOf(monitorService.create(dto))).build();
    }
    @PUT
    @Path("/{id}")
    public Response update (@PathParam("id")Long id, MonitorRequestDTO dto) {
        monitorService.update(id, dto);
        return Response.noContent().build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete (@PathParam("id")Long id) {
        monitorService.delete(id);
        return Response.noContent().build();
    }
}

