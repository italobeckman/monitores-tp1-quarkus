package br.unitins.tp1.monitores.resource;

import java.util.List;

import br.unitins.tp1.monitores.dto.fornecedor.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.monitores.model.TelefoneFornecedor;
import br.unitins.tp1.monitores.service.TelefoneFornecedorService;
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

@Path("/fornecedor/telefoneFornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneFornecedorResource {

    @Inject
    public TelefoneFornecedorService telefoneFornecedorService;

    @GET
    @Path("/{id}")
    public TelefoneFornecedor findById(@PathParam("id") Long id) {
        return telefoneFornecedorService.findById(id);
    }

    @GET
    public List<TelefoneFornecedor> findAll() {
        return telefoneFornecedorService.findAll();
    }

    @POST
    public TelefoneFornecedor create(TelefoneFornecedorRequestDTO telefoneFornecedor) {

        return telefoneFornecedorService.create(telefoneFornecedor);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, TelefoneFornecedorRequestDTO telefoneFornecedor) {
        telefoneFornecedorService.update(id, telefoneFornecedor);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        telefoneFornecedorService.delete(id);
    }
    
}
