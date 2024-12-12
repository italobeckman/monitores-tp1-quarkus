package br.unitins.tp1.monitores.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unitins.tp1.monitores.dto.fabricante.TelefoneFabricanteRequestDTO;
import br.unitins.tp1.monitores.dto.fabricante.TelefoneFabricanteResponseDTO;
import br.unitins.tp1.monitores.model.TelefoneFabricante;
import br.unitins.tp1.monitores.service.TelefoneFabricanteService;
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

@Path("/fabricante/telefonefabricantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneFabricanteResource {

    private static final Logger LOG = LoggerFactory.getLogger(TelefoneFabricanteResource.class);

    @Inject
    public TelefoneFabricanteService telefoneFabricanteService;
    @RolesAllowed({ "Adm", "User" })
    @GET
    @Path("search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando telefone do fabricante com ID: {}", id);
        
        return Response.ok(TelefoneFabricanteResponseDTO.valueOf(telefoneFabricanteService.findById(id))).build();
    }
    @RolesAllowed({ "Adm", "User" })
    @GET
    @Path("search/numero/{numero}")
    public Response findByNumero(@PathParam("numero") String numero) {
        LOG.info("Buscando telefone do fabricante com número: {}", numero);
        return Response.ok(TelefoneFabricanteResponseDTO.valueOf(telefoneFabricanteService.findByNumero(numero))).build();
    }
    @RolesAllowed({ "Adm", "User" })
    @GET
    public Response findAll() {
        LOG.info("Buscando todos os telefones de fabricantes.");

        List<TelefoneFabricante> telefone = telefoneFabricanteService.findAll();
        return Response.ok(telefone.stream().map(TelefoneFabricanteResponseDTO::valueOf).toList()).build();
    }
    @RolesAllowed({ "Adm" })
    @POST
    @Path("/create")
    public Response create(@Valid TelefoneFabricanteRequestDTO telefone) {
        if(telefone == null){
            return Response.status(Status.BAD_REQUEST).entity("Dados inválidos.").build();
        }
        LOG.info("Criando novo telefone de fabricante: {}", telefone);
        
        return Response.status(Status.CREATED)
                .entity(TelefoneFabricanteResponseDTO.valueOf(telefoneFabricanteService.create(telefone))).build();
    }
    @RolesAllowed({ "Adm" })
    @PUT
    @Path("update/id/{id}")
    public Response update(@PathParam("id") Long id, @Valid TelefoneFabricanteRequestDTO telefone) {
        if(telefone == null){
            return Response.status(Status.BAD_REQUEST).entity("Dados inválidos.").build();
        }
        LOG.info("Atualizando telefone do fabricante com ID: {} com os dados: {}", id, telefone);

        try {
            telefoneFabricanteService.update(id, telefone);
            LOG.info("Telefone do fabricante com ID: {} atualizado com sucesso.", id);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.error("Erro ao tentar atualizar o telefone do fabricante com ID {}: {}", id, e.getMessage());
            return Response.serverError().entity("Sistema temporariamente indisponível.").build();
        }
    }
    
    @RolesAllowed({ "Adm" })
    @DELETE
    @Path("delete/id/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando telefone do fabricante com ID: {}", id);
        try {
            telefoneFabricanteService.delete(id);
             LOG.info("Telefone do fabricante com ID: {} deletado com sucesso.", id);
             return Response.noContent().build();
        } catch (Exception e) {
            LOG.error("Erro ao tentar deletar o telefone do fabricante com ID {}: {}", id, e.getMessage());
            return Response.serverError().entity("Sistema temporariamente indisponível.").build();
        }
    }

}
