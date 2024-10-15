package br.unitins.tp1.monitores.resource;

import java.util.List;

import br.unitins.tp1.monitores.dto.fornecedor.FornecedorRequestDTO;
import br.unitins.tp1.monitores.model.Fornecedor;
import br.unitins.tp1.monitores.service.FornecedorService;
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

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    @Inject
    public FornecedorService fornecedorService;

    @GET
    @Path("/{id}")
    public Fornecedor findById(@PathParam("id") Long id) {
        return fornecedorService.findById(id);
    }

    @GET
    @Path("/search/{nome}")
    public List<Fornecedor> findByNome(@PathParam("nome") String nome) {
        return fornecedorService.findByNome(nome);
    }

    @GET
    public List<Fornecedor> findAll() {
        return fornecedorService.findAll();
    }

    @POST
    public Fornecedor create(FornecedorRequestDTO fornecedor) {

        return fornecedorService.create(fornecedor);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, FornecedorRequestDTO fornecedor) {
        fornecedorService.update(id, fornecedor);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        fornecedorService.delete(id);
    }
    
}
