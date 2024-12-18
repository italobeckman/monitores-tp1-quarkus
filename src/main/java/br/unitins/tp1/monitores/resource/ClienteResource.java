package br.unitins.tp1.monitores.resource;

import java.io.IOException;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.util.logging.Logger;

import br.unitins.tp1.monitores.dto.pessoa.ClienteRequestDTO;
import br.unitins.tp1.monitores.dto.pessoa.ClienteResponseDTO;
import br.unitins.tp1.monitores.form.ImageForm;
import br.unitins.tp1.monitores.model.Cliente;
import br.unitins.tp1.monitores.service.ClienteFileServiceImpl;
import br.unitins.tp1.monitores.service.ClienteService;
import br.unitins.tp1.monitores.validation.ValidationException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;
@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private static final Logger logger = Logger.getLogger(ClienteResource.class.getName());
    @Inject
    JsonWebToken jwt;
    @Inject
    public ClienteService clienteService;

    @Inject
    public ClienteFileServiceImpl clienteFileService;

    @GET
    @RolesAllowed({ "Adm", "Master" })
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        logger.info("Buscando cliente com ID: "+ id);
        Response response = Response.ok(ClienteResponseDTO.valueOf(clienteService.findById(id))).build();
        logger.info("Cliente encontrado: {}"+ response.getEntity());
        return response;
    }

    @GET
    @RolesAllowed({"Adm", "Master"})
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        logger.info("Execucao do metodo findByNome. Nome: " + nome);
        return Response.ok(clienteService.findByNome(nome)
                .stream()
                .map(ClienteResponseDTO::valueOf)
                .toList()).build();
    }

    @RolesAllowed({ "Adm", "Master" })
    @GET
    public Response findAll() {
        logger.info("Buscando todos os clientes");
        Response response = Response
                .ok(clienteService.findAll().stream().map(o -> ClienteResponseDTO.valueOf(o)).toList()).build();
        logger.info("Total de clientes encontrados: "+ ((List<?>) response.getEntity()).size());
        return response;
    }

    @RolesAllowed({ "User", "Master" })
    @PUT
    @Path("/update/")
    public Response update(@Valid ClienteRequestDTO dto) {
        if (dto == null) {
            return Response.status(Status.BAD_REQUEST).entity("Dados inválidos.").build();
        }
        if (jwt == null) {
            return Response.status(Status.UNAUTHORIZED).entity("Usuário não autenticado.").build();
        }
        String username = jwt.getSubject();
        try {
            Cliente cliente = clienteService.findByUsername(username);

            if (cliente == null) {
                return Response.status(Status.NOT_FOUND).entity("Cliente não encontrado.").build();
            }

            
            cliente = clienteService.update(username, dto);

            return Response.noContent().build();

        } catch (ValidationException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @RolesAllowed({ "Adm", "Master" })
    @DELETE
    @Path("/delete/id/{id}")
    public Response delete(@PathParam("id") Long id) {
        logger.info("Deletando cliente com ID: {}"+ id);
        clienteService.delete(id);
        logger.info("Cliente com ID: {} deletado com sucesso"+ id);
        return Response.noContent().build();
    }

    @RolesAllowed({ "Adm", "User" })
    @PATCH
    @Path("/{id}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        try {
            logger.info("Fazendo upload da imagem para o cliente com ID: {}"+ id);
            String nomeImagem = clienteFileService.save(form.getNomeImagem(), form.getImagem());
            clienteService.updateNomeImagem(id, nomeImagem);
            logger.info("Imagem '{}' carregada com sucesso para o cliente com ID: {}"+ nomeImagem+" "+ id);
        } catch (IOException e) {
            logger.severe("Erro ao fazer upload da imagem para o cliente com ID: {}"+ id+" "+ e);
            return Response.status(500).build();
        }
        return Response.noContent().build();
    }

    @RolesAllowed({ "Adm", "User" })
    @PATCH
    @Path("/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("nomeImagem") String nomeImagem) {
        logger.info("Fazendo download da imagem: {}"+ nomeImagem);
        ResponseBuilder response = Response.ok(clienteFileService.find(nomeImagem));
        response.header("Content-Disposition", "attachment; filename=" + nomeImagem);
        logger.info("Imagem '{}' preparada para download"+ nomeImagem);
        return response.build();
    }
    /* 
    @POST
    @Path("/create/cliente")
    public Response create(@Valid ClienteRequestDTO dto) {
        if(dto == null) {
            return Response.status(Status.BAD_REQUEST).entity("Requisição inválida.").build();
        }
        try {
            logger.info("Criando cliente: {}", dto);
            Cliente cliente = clienteService.create(dto);
            logger.info("Cliente criado com sucesso: {}", cliente);
            return Response.ok(cliente).build();
        } catch (ValidationException e) {
            logger.error("Erro ao criar cliente: {}", dto, e);
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }
     */

}   
