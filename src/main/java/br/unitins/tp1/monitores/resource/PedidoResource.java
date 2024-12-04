package br.unitins.tp1.monitores.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.tp1.monitores.dto.pagamento.CartaoDTO;
import br.unitins.tp1.monitores.dto.pedido.PedidoDTO;
import br.unitins.tp1.monitores.dto.pedido.PedidoResponseDTO;
import br.unitins.tp1.monitores.dto.pedido.StatusPatchDTO;
import br.unitins.tp1.monitores.model.Cliente;
import br.unitins.tp1.monitores.service.ClienteService;
import br.unitins.tp1.monitores.service.PedidoService;
import br.unitins.tp1.monitores.service.user.UsuarioService;
import br.unitins.tp1.monitores.validation.ValidationException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.Response.Status;

@Path("/pedidos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResource {

    private static final Logger LOG = Logger.getLogger(PedidoResource.class.getName());

    @Inject
    PedidoService pedidoService;

    @Inject
    JsonWebToken jwt;

    @Inject
    ClienteService clienteService;

    @Inject
    UsuarioService usuarioService;

    @POST // USER == CLIENTE
    @RolesAllowed({ "User" }) // Permissões para criação de pedidos
    public Response create(@Valid PedidoDTO pedidoDTO, @Context SecurityContext securityContext) {
        // Obtém o username do JWT
        String username = jwt.getSubject();
        LOG.info("Pedido gerado pelo cliente: " + username);

        // Verifica se o usuário é um administrador
        if (securityContext.isUserInRole("Adm")) {
            // Se for Adm, retorna uma resposta com FORBIDDEN
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Usuários 'Adm' não podem efetuar pedidos.")
                    .build();
        }

        // Busca o usuário e o cliente associados ao username
        Cliente cliente = clienteService.findByUsername(username);

        // Verifica se o cliente está cadastrado completamente
        if (cliente == null || !cliente.isCadastroCompleto()) {
            // Retorna uma resposta com BAD_REQUEST e uma mensagem explicativa
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Cadastro de cliente incompleto. Por favor, complete seu cadastro.")
                    .build();
        }

        // Criação do pedido
        PedidoResponseDTO createdPedido = pedidoService.create(pedidoDTO, username);

        return Response.status(Response.Status.CREATED).entity(createdPedido).build();
    }

    @GET
    @Path("/meus-pedidos")
    @RolesAllowed({ "User" })
    public Response getMeusPedidos(@Context SecurityContext securityContext) {
        String username = jwt.getSubject(); // Extrai o username do token JWT
        if (username == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Username não encontrado no token JWT.")
                    .build();
        }

        List<PedidoResponseDTO> pedidos = pedidoService.findByUsername(username);
        return Response.ok(pedidos).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "User", "Funcionario" }) // Single Order - User/Funcionario
    public Response findById(@PathParam("id") Long id, @Context SecurityContext securityContext) {

        PedidoResponseDTO pedido = pedidoService.findById(id);

        if (pedido == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        if (securityContext.isUserInRole("User") && !pedido.cliente().id().equals(jwt.getClaim("id"))) {
            return Response.status(Status.FORBIDDEN).build();
        }

        return Response.ok(pedido).build();
    }

    @PATCH
    @Path("/{id}/status")
    @RolesAllowed({ "Adm", "Funcionario" }) // Status Update - Adm/Funcionario
    public Response updateStatusPedido(@PathParam("id") Long idPedido, @Valid StatusPatchDTO status) {
        try {
            pedidoService.updateStatusPedido(idPedido, status.idStatus());
            return Response.status(Status.NO_CONTENT).build();
        } catch (ValidationException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/pagamento/cartao")
    @RolesAllowed({ "User" })
    public Response pagarComCartao(@PathParam("id") Long idPedido, @Valid CartaoDTO cartaoDTO) {
        try {
            pedidoService.registrarPagamentoCartao(idPedido, cartaoDTO);
            return Response.status(Status.NO_CONTENT).build();
        } catch (ValidationException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/pagamento/pix")
    @RolesAllowed({ "User" })
    public Response pagarComPix(@PathParam("id") Long idPedido, long idPix) { // PixDTO precisa ser criado
        try {
            pedidoService.registrarPagamentoPix(idPedido, idPix); // Implemente este método no PedidoService
            return Response.status(Status.NO_CONTENT).build();
        } catch (ValidationException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/pagamento/boleto")
    @RolesAllowed({ "User" })
    public Response pagarComBoleto(@PathParam("id") Long idPedido, Long idBoleto) { // BoletoDTO precisa ser criado
        try {
            pedidoService.registrarPagamentoBoleto(idPedido, idBoleto); // Implemente este método no PedidoService
            return Response.status(Status.NO_CONTENT).build();
        } catch (ValidationException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/pagamento/pix")
    @RolesAllowed({ "User" })
    public Response gerarPix(@PathParam("id") Long idPedido, @Context SecurityContext securityContext) {
        try {
            return Response.ok(pedidoService.gerarInformacoesPix(idPedido)).build();

        } catch (ValidationException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @GET
    @Path("/{id}/pagamento/boleto")
    @RolesAllowed({ "User" })
    public Response gerarBoleto(@PathParam("id") Long idPedido, @Context SecurityContext securityContext) {
        try {
            return Response.ok(pedidoService.gerarInformacoesBoleto(idPedido)).build();

        } catch (ValidationException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

}
