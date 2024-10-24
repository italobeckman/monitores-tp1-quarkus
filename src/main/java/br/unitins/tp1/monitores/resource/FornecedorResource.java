package br.unitins.tp1.monitores.resource;

import java.util.List;

import br.unitins.tp1.monitores.dto.estado.EstadoRequestDTO;
import br.unitins.tp1.monitores.dto.estado.EstadoResponseDTO;
import br.unitins.tp1.monitores.dto.fornecedor.FornecedorRequestDTO;
import br.unitins.tp1.monitores.dto.fornecedor.FornecedorResponseDTO;
import br.unitins.tp1.monitores.model.Estado;
import br.unitins.tp1.monitores.model.Fornecedor;
import br.unitins.tp1.monitores.service.FornecedorService;
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

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    @Inject
    public FornecedorService fornecedorService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(FornecedorResponseDTO.valueOf(fornecedorService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        List<Fornecedor> fornecedor = fornecedorService.findByNome(nome);
        return Response.ok(fornecedor.stream().map(FornecedorResponseDTO::valueOf).toList()).build();
    }

    @GET
    public Response findAll() {
        List<Fornecedor> fornecedor = fornecedorService.findAll();
        return Response.ok(fornecedor.stream().map(FornecedorResponseDTO::valueOf).toList()).build();
    }

    @POST
    public Response create(@Valid FornecedorRequestDTO fornecedor) {
        return Response.status(Status.CREATED).entity(FornecedorResponseDTO.valueOf(fornecedorService.create(fornecedor))).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid FornecedorRequestDTO estado) {
        fornecedorService.update(id, estado);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        fornecedorService.delete(id);
        return Response.noContent().build();
    }
    
}
