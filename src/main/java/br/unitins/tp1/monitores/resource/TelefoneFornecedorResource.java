package br.unitins.tp1.monitores.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unitins.tp1.monitores.dto.fornecedor.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.monitores.dto.fornecedor.TelefoneFornecedorResponseDTO;
import br.unitins.tp1.monitores.model.TelefoneFornecedor;
import br.unitins.tp1.monitores.service.TelefoneFornecedorService;
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

@Path("/fornecedor/telefonefornecedors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneFornecedorResource {

    private static final Logger LOG = LoggerFactory.getLogger(TelefoneFornecedorResource.class);

    @Inject
    public TelefoneFornecedorService telefoneFornecedorService;
    @RolesAllowed({ "Adm", "User" })
    @GET
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando telefone do fornecedor com ID: {}", id);
        
        return Response.ok(TelefoneFornecedorResponseDTO.valueOf(telefoneFornecedorService.findById(id))).build();
    }
    @RolesAllowed({ "Adm", "User" })
    @GET
    @Path("/search/numero/{numero}")
    public Response findByNumero(@PathParam("numero") String numero) {
        LOG.info("Buscando telefone do fornecedor com número: {}", numero);
        return Response.ok(TelefoneFornecedorResponseDTO.valueOf(telefoneFornecedorService.findByNumero(numero))).build();
    }
    @RolesAllowed({ "Adm" })
    @GET
    public Response findAll() {
        LOG.info("Buscando todos os telefones de fornecedors.");

        List<TelefoneFornecedor> telefone = telefoneFornecedorService.findAll();
        return Response.ok(telefone.stream().map(TelefoneFornecedorResponseDTO::valueOf).toList()).build();
    }
    @RolesAllowed({ "Adm" })
    @POST
    @Path("/create")
    public Response create(@Valid TelefoneFornecedorRequestDTO telefone) {
        if(telefone == null) {
            return Response.status(Status.BAD_REQUEST).entity("Dados inválidos.").build();
        }
        LOG.info("Criando novo telefone de fornecedor: {}", telefone);
        
        return Response.status(Status.CREATED)
                .entity(TelefoneFornecedorResponseDTO.valueOf(telefoneFornecedorService.create(telefone))).build();
    }
    @RolesAllowed({ "Adm" })
    @PUT
    @Path("update/id/{id}")
    public Response update(@PathParam("id") Long id, @Valid TelefoneFornecedorRequestDTO telefone) {
        if(telefone == null){
            return Response.status(Status.BAD_REQUEST).entity("Dados inválidos.").build();
        }
        LOG.info("Atualizando telefone do fornecedor com ID: {} com os dados: {}", id, telefone);

        try {
            telefoneFornecedorService.update(id, telefone);
            LOG.info("Telefone do fornecedor com ID: {} atualizado com sucesso.", id);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.error("Erro ao tentar atualizar o telefone do fornecedor com ID {}: {}", id, e.getMessage());
            return Response.serverError().entity("Sistema temporariamente indisponível.").build();
        }
    }
    
    @RolesAllowed({ "Adm" })
    @DELETE
    @Path("delete/id/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando telefone do fornecedor com ID: {}", id);
        try {
            telefoneFornecedorService.delete(id);
             LOG.info("Telefone do fornecedor com ID: {} deletado com sucesso.", id);
             return Response.noContent().build();
        } catch (Exception e) {
            LOG.error("Erro ao tentar deletar o telefone do fornecedor com ID {}: {}", id, e.getMessage());
            return Response.serverError().entity("Sistema temporariamente indisponível.").build();
        }
    }

}
