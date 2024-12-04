package br.unitins.tp1.monitores.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unitins.tp1.monitores.dto.pessoa.FuncionarioRequestDTO;
import br.unitins.tp1.monitores.dto.pessoa.FuncionarioResponseDTO;
import br.unitins.tp1.monitores.service.FuncionarioService;
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

@Path("/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioResource {

    private static final Logger logger = LoggerFactory.getLogger(FuncionarioResource.class);

    @Inject
    public FuncionarioService funcionarioService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        logger.info("Buscando funcionario com ID: ", id);
        Response response = Response.ok(FuncionarioResponseDTO.valueOf(funcionarioService.findById(id))).build();
        logger.info("Funcionario encontrado: {}", response.getEntity());
        return response;
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        logger.info("Buscando funcionario com nome: {}", nome);
        Response response = Response
                .ok(funcionarioService.findByNome(nome).stream().map(o -> FuncionarioResponseDTO.valueOf(o)).toList()).build();
        logger.info("Funcionarios encontrados: {}", response.getEntity());
        return response;
    }

    @GET
    public Response findAll() {
        logger.info("Buscando todos os funcionarios");
        Response response = Response
                .ok(funcionarioService.findAll().stream().map(o -> FuncionarioResponseDTO.valueOf(o)).toList()).build();
        logger.info("Total de funcionarios encontrados: ", ((List<?>) response.getEntity()).size());
        return response;
    }

    @POST
    public Response create(@Valid FuncionarioRequestDTO dto) {
        logger.info("Criando novo funcionario: {}", dto);
        Response response = Response.status(Status.CREATED)
                .entity(FuncionarioResponseDTO.valueOf(funcionarioService.create(dto))).build();
        logger.info("Funcionario criado: {}", response.getEntity());
        return response;
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid FuncionarioRequestDTO dto) {
        logger.info("Atualizando funcionario com ID: "+id+" com dados: ", id, dto);
        funcionarioService.update(id, dto);
        logger.info("Funcionario com ID: {} atualizado com sucesso", id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        logger.info("Deletando funcionario com ID: {}", id);
        funcionarioService.delete(id);
        logger.info("Funcionario com ID: {} deletado com sucesso", id);
        return Response.noContent().build();
    }

}