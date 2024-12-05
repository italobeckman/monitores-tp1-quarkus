package br.unitins.tp1.monitores.resource;

import java.io.IOException;
import java.util.List;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unitins.tp1.monitores.dto.monitor.MonitorRequestDTO;
import br.unitins.tp1.monitores.dto.monitor.MonitorResponseDTO;
import br.unitins.tp1.monitores.form.ImageForm;
import br.unitins.tp1.monitores.model.Monitor;
import br.unitins.tp1.monitores.service.MonitorFileServiceImpl;
import br.unitins.tp1.monitores.service.MonitorService;
import br.unitins.tp1.monitores.validation.ValidationException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/monitores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MonitorResource {

    private static final Logger LOG = LoggerFactory.getLogger(MonitorResource.class);

    @Inject
    public MonitorService monitorService;

    @Inject
    public MonitorFileServiceImpl monitorFileService;
    @RolesAllowed({ "Adm", "User" })
    @GET
    @Path("/search/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando monitor com ID: {}", id);
        Monitor monitor = monitorService.findById(id);
        if (monitor == null) {
            LOG.warn("Monitor com ID {} não encontrado.", id);
            return Response.status(Status.NOT_FOUND).build();
        }
        LOG.info("Monitor encontrado: {}", MonitorResponseDTO.valueOf(monitor));

        return Response.ok(MonitorResponseDTO.valueOf(monitor)).build();
    }
    @RolesAllowed({ "Adm", "User" })
    @GET
    public Response findAll() {
        LOG.info("Buscando todos os monitores.");
        List<Monitor> monitores = monitorService.findAll();
        LOG.info("Encontrados {} monitores.", monitores.size());
        return Response.ok(monitores.stream().map(MonitorResponseDTO::valueOf).toList()).build();
    }
    @RolesAllowed({ "Adm", "User" })
    @GET
    @Path("/search/{modelo}")
    public Response findByModelo(@PathParam("modelo") String modelo) {
        LOG.info("Buscando monitores com modelo: {}", modelo);
        List<Monitor> monitores = monitorService.findByModelo(modelo);
        LOG.info("Encontrados {} monitores com modelo: {}", monitores.size(), modelo);

        return Response.ok(monitores.stream().map(MonitorResponseDTO::valueOf).toList()).build();

    }
    @RolesAllowed({ "Adm", "User" })
    @GET
    @Path("/search/{marca}") 
    public Response findByMarca(@PathParam("marca") String marca) { 
        LOG.info("Buscando monitores com marca (consider renaming to fabricante): {}", marca);
        List<Monitor> monitores = monitorService.findByMarca(marca); 
        LOG.info("Encontrados {} monitores com marca (consider renaming to fabricante): {}", monitores.size(), marca);
        return Response.ok(monitores.stream().map(MonitorResponseDTO::valueOf).toList()).build();
    }
    @RolesAllowed({ "Adm" })
    @POST
    public Response create(MonitorRequestDTO dto) {
        LOG.info("Criando novo monitor: {}", dto);
        Monitor created = monitorService.create(dto);

        if (created == null) {
            LOG.error("Erro ao criar monitor: {}", dto);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        
        LOG.info("Monitor criado: {}", MonitorResponseDTO.valueOf(created));

        return Response.status(Status.CREATED).entity(MonitorResponseDTO.valueOf(created)).build();
    }

    @RolesAllowed({ "Adm" })
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, MonitorRequestDTO dto) {
        LOG.info("Atualizando monitor com ID: {} com os dados: {}", id, dto);
        try {
            monitorService.update(id, dto);
            LOG.info("Monitor com ID: {} atualizado com sucesso.", id);
            return Response.noContent().build();
        } catch (Exception e) {
             LOG.error("Erro ao atualizar o monitor com ID {}: {}", id, e.getMessage());
             return Response.serverError().entity("Sistema temporariamente indisponível.").build();
        }
       
    }
    @RolesAllowed({ "Adm" })
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando monitor com ID: {}", id);
        try {
            monitorService.delete(id);
            LOG.info("Monitor com ID: {} deletado com sucesso.", id);
            return Response.noContent().build();

        } catch (Exception e) {
            LOG.error("Erro ao deletar o monitor com ID {}: {}", id, e.getMessage());
            return Response.serverError().entity("Sistema temporariamente indisponível.").build();
        }
    }

    @PATCH
    @Path("/{id}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        try {
            LOG.info("Fazendo upload da imagem {} para o monitor com ID: {}", form.getNomeImagem(), id);
            String nomeImagem = monitorFileService.save(form.getNomeImagem(), form.getImagem());
            monitorService.updateNomeImagem(id, nomeImagem);
            LOG.info("Imagem '{}' carregada com sucesso para o monitor com ID: {}", nomeImagem, id);
            return Response.noContent().build();
        } catch (IOException e) {
            LOG.error("Erro ao fazer upload da imagem para o monitor com ID: {}", id, e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PATCH 
    @Path("/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("nomeImagem") String nomeImagem) {
        LOG.info("Fazendo download da imagem: {}", nomeImagem);
        monitorFileService.find(nomeImagem);
        if (monitorFileService == null) {
            throw new ValidationException("monitorFile", "Imagem não encontrada");
        }
        ResponseBuilder response = Response.ok(monitorFileService.find(nomeImagem));
        
        response.header("Content-Disposition", "attachment; filename=" + nomeImagem);
        LOG.info("Download da imagem {} sucedido.", nomeImagem); 
        return response.build();
    }
}
