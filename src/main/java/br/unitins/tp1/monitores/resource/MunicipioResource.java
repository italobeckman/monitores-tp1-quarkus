package br.unitins.tp1.monitores.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unitins.tp1.monitores.dto.municipio.MunicipioRequestDTO;
import br.unitins.tp1.monitores.dto.municipio.MunicipioResponseDTO;
import br.unitins.tp1.monitores.model.Municipio;
import br.unitins.tp1.monitores.service.MunicipioService;
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
import java.util.List;


@Path("/municipios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MunicipioResource {

    private static final Logger LOG = LoggerFactory.getLogger(MunicipioResource.class);

    @Inject
    public MunicipioService municipioService;
    @RolesAllowed({ "Adm" })
    @GET
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando município com ID: {}", id);
        Municipio municipio = municipioService.findById(id);
        if (municipio == null) {
            LOG.warn("Município com ID {} não encontrado.", id);
            return Response.status(Status.NOT_FOUND).build();
        }
        LOG.info("Município encontrado: {}", MunicipioResponseDTO.valueOf(municipio));
        return Response.ok(MunicipioResponseDTO.valueOf(municipio)).build();
    }
    @RolesAllowed({ "Adm" })
    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Buscando municípios com nome: {}", nome);
        List<Municipio> municipios = municipioService.findByNome(nome);
        LOG.info("Encontrados {} municípios com o nome: {}", municipios.size(), nome);
        return Response.ok(municipios.stream().map(MunicipioResponseDTO::valueOf).toList()).build();
    }


    @RolesAllowed({ "Adm" })
    @GET
    public Response findAll() {
        LOG.info("Buscando todos os municípios.");
        List<Municipio> municipios = municipioService.findAll();
        LOG.info("Encontrados {} municípios.", municipios.size());
        return Response.ok(municipios.stream().map(MunicipioResponseDTO::valueOf).toList()).build();
    }
    @RolesAllowed({ "Adm" })
    @POST
    @Path("/create")
    public Response create(@Valid MunicipioRequestDTO municipioDTO) {
        if(municipioDTO == null) {
            LOG.error("Erro ao tentar criar um novo município. Dados inválidos.");
            return Response.status(Status.BAD_REQUEST).entity("Dados inválidos.").build();
        }
        LOG.info("Criando novo município: {}", municipioDTO);
        Municipio created = municipioService.create(municipioDTO);
        LOG.info("Município criado com sucesso: {}", MunicipioResponseDTO.valueOf(created));
        return Response.status(Status.CREATED).entity(MunicipioResponseDTO.valueOf(created)).build();
    }
    @RolesAllowed({ "Adm" })
    @PUT
    @Path("/update/id/{id}")
    public Response update(@PathParam("id") Long id, @Valid MunicipioRequestDTO municipioDTO) {
        if(municipioDTO == null) {
            return Response.status(Status.BAD_REQUEST).entity("Dados inválidos.").build();
        }
        LOG.info("Atualizando município com ID: {} com os dados: {}", id, municipioDTO);
        try {
            municipioService.update(id, municipioDTO);
            LOG.info("Município com ID: {} atualizado com sucesso.", id);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.error("Erro ao tentar atualizar o município com ID {}: {}", id, e.getMessage());
            return Response.serverError().entity("Sistema temporariamente indisponível.").build();
        }
    }
    @RolesAllowed({ "Adm" })
    @DELETE
    @Path("/delete/id/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando município com ID: {}", id);

        try {
            municipioService.delete(id);
            LOG.info("Município com ID: {} deletado com sucesso.", id);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.error("Erro ao tentar deletar o município com ID {}: {}", id, e.getMessage());
            return Response.serverError().entity("Sistema temporariamente indisponível.").build();
        }
    }
}
