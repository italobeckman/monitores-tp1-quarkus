package br.unitins.tp1.monitores.resource;

import java.io.IOException;
import java.util.List;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unitins.tp1.monitores.dto.pessoa.ClienteRequestDTO;
import br.unitins.tp1.monitores.dto.pessoa.ClienteResponseDTO;
import br.unitins.tp1.monitores.form.ImageForm;
import br.unitins.tp1.monitores.service.ClienteFileServiceImpl;
import br.unitins.tp1.monitores.service.ClienteService;
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

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private static final Logger logger = LoggerFactory.getLogger(ClienteResource.class);

    @Inject
    public ClienteService clienteService;

    @Inject
    ClienteFileServiceImpl clienteFileService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        logger.info("Buscando cliente com ID: ", id);
        Response response = Response.ok(ClienteResponseDTO.valueOf(clienteService.findById(id))).build();
        logger.info("Cliente encontrado: {}", response.getEntity());
        return response;
    }

    @GET
    @Path("/cliente/search/{nome}")
    public Response findByUsername(@PathParam("nome") String nome) {
        logger.info("Buscando cliente com nome: ", nome);
        Response response = Response
                .ok(clienteService.findByNome(nome).stream().map(o -> ClienteResponseDTO.valueOf(o)).toList()).build();
        logger.info("Clientes encontrados: ", response.getEntity());
        return response;
    }

    @GET
    public Response findAll() {
        logger.info("Buscando todos os clientes");
        Response response = Response
                .ok(clienteService.findAll().stream().map(o -> ClienteResponseDTO.valueOf(o)).toList()).build();
        logger.info("Total de clientes encontrados: ", ((List<?>) response.getEntity()).size());
        return response;
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid ClienteRequestDTO dto) {
        logger.info("Atualizando cliente com ID: "+id+" com dados: ", id, dto);
        clienteService.update(id, dto);
        logger.info("Cliente com ID: {} atualizado com sucesso", id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        logger.info("Deletando cliente com ID: {}", id);
        clienteService.delete(id);
        logger.info("Cliente com ID: {} deletado com sucesso", id);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/{id}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        try {
            logger.info("Fazendo upload da imagem para o cliente com ID: {}", id);
            String nomeImagem = clienteFileService.save(form.getNomeImagem(), form.getImagem());
            clienteService.updateNomeImagem(id, nomeImagem);
            logger.info("Imagem '{}' carregada com sucesso para o cliente com ID: {}", nomeImagem, id);
        } catch (IOException e) {
            logger.error("Erro ao fazer upload da imagem para o cliente com ID: {}", id, e);
            return Response.status(500).build();
        }
        return Response.noContent().build();
    }

    @PATCH
    @Path("/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("nomeImagem") String nomeImagem) {
        logger.info("Fazendo download da imagem: {}", nomeImagem);
        ResponseBuilder response = Response.ok(clienteFileService.find(nomeImagem));
        response.header("Content-Disposition", "attachment; filename=" + nomeImagem);
        logger.info("Imagem '{}' preparada para download", nomeImagem);
        return response.build();
    }
}