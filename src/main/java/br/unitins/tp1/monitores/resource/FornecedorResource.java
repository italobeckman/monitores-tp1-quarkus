package br.unitins.tp1.monitores.resource;

import java.util.List;

import br.unitins.tp1.monitores.dto.fornecedor.FornecedorRequestDTO;
import br.unitins.tp1.monitores.dto.fornecedor.FornecedorResponseDTO;
import br.unitins.tp1.monitores.model.Fornecedor;
import br.unitins.tp1.monitores.service.FornecedorService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    private static final Logger LOG = LoggerFactory.getLogger(FornecedorResource.class);

    @Inject
    public FornecedorService fornecedorService;
    @RolesAllowed({ "Adm" })
    @GET
    @Path("/search/{id}")
    public Response findById(@PathParam(" id") Long id) {
        LOG.info("Buscando Fornecedor com ID: {}", id);
        FornecedorResponseDTO response = FornecedorResponseDTO.valueOf(fornecedorService.findById(id));
        LOG.info("Fornecedor encontrado: {}", response);
        return Response.ok(response).build();
    }
    @RolesAllowed({ "Adm" })
    @GET
    public Response findAll() {
        LOG.info("Buscando todos os Fornecedores");
        List<Fornecedor> fornecedores = fornecedorService.findAll();
        LOG.info("Total de Fornecedores encontrados: {}", fornecedores.size());
        return Response.ok(fornecedores.stream().map(FornecedorResponseDTO::valueOf).toList()).build();
    }
    @RolesAllowed({ "Adm" })
    @POST
    public Response create(@Valid FornecedorRequestDTO fornecedor) {
        LOG.info("Criando novo Fornecedor: {}", fornecedor);
        FornecedorResponseDTO response = FornecedorResponseDTO.valueOf(fornecedorService.create(fornecedor));
        LOG.info("Fornecedor criado: {}", response);
        return Response.status(Status.CREATED).entity(response).build();
    }
    @RolesAllowed({ "Adm" })
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid FornecedorRequestDTO fornecedor) {
        LOG.info("Atualizando Fornecedor com ID: {}", id);
        fornecedorService.update(id, fornecedor);
        LOG.info("Fornecedor com ID: {} atualizado com sucesso", id);
        return Response.noContent().build();
    }
    @RolesAllowed({ "Adm" })
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando Fornecedor com ID: {}", id);
        fornecedorService.delete(id);
        LOG.info("Fornecedor com ID: {} deletado com sucesso", id);
        return Response.noContent().build();
    }
}