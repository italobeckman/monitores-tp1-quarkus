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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/tamanho-monitor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TamanhoMonitorResource {

    private static final Logger LOG = LoggerFactory.getLogger(TamanhoMonitorResource.class);

    @Inject
    public TamanhoMonitorService tamanhoMonitorService;
    
    @GET
    @Path("/search/{id}")
    @RolesAllowed({"Adm", "User "})
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando TamanhoMonitor com ID: {}", id);
        TamanhoMonitorResponseDTO response = TamanhoMonitorResponseDTO.valueOf(tamanhoMonitorService.findById(id));
        LOG.info("TamanhoMonitor encontrado: {}", response);
        return Response.ok(response).build();
    }
    @RolesAllowed({ "Adm","User" })
    @GET
    @Path("/search/all")
    public Response findAll() {
        LOG.info("Buscando todos os TamanhoMonitores");
        List<TamanhoMonitor> tamanhos = tamanhoMonitorService.findAll();
        LOG.info("Total de TamanhoMonitores encontrados: {}", tamanhos.size());
        return Response.ok(tamanhos.stream().map(TamanhoMonitorResponseDTO::valueOf).toList()).build();
    }
    @RolesAllowed({ "Adm" })
    @POST
    public Response create(TamanhoMonitorRequestDTO estado) {
        LOG.info("Criando novo TamanhoMonitor: {}", estado);
        TamanhoMonitorResponseDTO response = TamanhoMonitorResponseDTO.valueOf(tamanhoMonitorService.create(estado));
        LOG.info("TamanhoMonitor criado: {}", response);
        return Response.status(Status.CREATED).entity(response).build();
    }
    @RolesAllowed({ "Adm" })
    @PUT
    @Path("update/{id}")
    public Response update(@PathParam("id") Long id, TamanhoMonitorRequestDTO estado) {
        LOG.info("Atualizando TamanhoMonitor com ID: {}", id);
        tamanhoMonitorService.update(id, estado);
        LOG.info("TamanhoMonitor com ID: {} atualizado com sucesso", id);
        return Response.noContent().build();
    }
    @RolesAllowed({ "Adm" })
    @DELETE
    @Path("delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando TamanhoMonitor com ID: {}", id);
        tamanhoMonitorService.delete(id);
        LOG.info("TamanhoMonitor com ID: {} deletado com sucesso", id);
        return Response.noContent().build();
    }
}