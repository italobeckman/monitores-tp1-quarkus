package br.unitins.tp1.monitores.resource;

import java.util.List;

import br.unitins.tp1.monitores.dto.fabricante.TelefoneFabricanteResponseDTO;
import br.unitins.tp1.monitores.dto.fornecedor.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.monitores.dto.fornecedor.TelefoneFornecedorResponseDTO;
import br.unitins.tp1.monitores.model.TelefoneFornecedor;
import br.unitins.tp1.monitores.service.TelefoneFornecedorService;
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

@Path("/fornecedor/telefonefornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneFornecedorResource {

    @Inject
    public TelefoneFornecedorService telefoneFornecedorService;

    @GET
    @Path("search/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(TelefoneFornecedorResponseDTO.valueOf(telefoneFornecedorService.findById(id))).build();
    }

    @GET
    @Path("/numero/search/{numero}")
    public Response findByNumero(@PathParam("numero") String numero) {
        return Response.ok(TelefoneFornecedorResponseDTO.valueOf(telefoneFornecedorService.findByNumero(numero))).build();
    }
    @GET
    public Response findAll() {
        List<TelefoneFornecedor> telefone = telefoneFornecedorService.findAll();
        return Response.ok(telefone.stream().map(TelefoneFornecedorResponseDTO::valueOf).toList()).build();
    }

    @POST
    public Response create(@Valid TelefoneFornecedorRequestDTO telefone) {
        return Response.status(Status.CREATED)
                .entity(TelefoneFornecedorResponseDTO.valueOf(telefoneFornecedorService.create(telefone))).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid TelefoneFornecedorRequestDTO telefone) {
        telefoneFornecedorService.update(id, telefone);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        telefoneFornecedorService.delete(id);
        return Response.noContent().build();
    }

}
