package br.unitins.tp1.monitores.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import java.util.logging.Logger;


import br.unitins.tp1.monitores.dto.pagamento.CartaoDTO;
import br.unitins.tp1.monitores.dto.pagamento.CartaoResponseDTO;
import br.unitins.tp1.monitores.dto.pagamento.BoletoResponseDTO;
import br.unitins.tp1.monitores.dto.pagamento.PixResponseDTO;
import br.unitins.tp1.monitores.dto.pedido.PedidoDTO;
import br.unitins.tp1.monitores.dto.pedido.PedidoResponseDTO;
import br.unitins.tp1.monitores.dto.pedido.PedidoSimplesResponseDTO;
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

    @POST
    @RolesAllowed({ "User" })
    public Response create(@Valid PedidoDTO pedidoDTO, @Context SecurityContext securityContext) {
        if (pedidoDTO == null) {
            Response.status(Response.Status.BAD_REQUEST).entity("Dados invalidos").build();
        }

        if (pedidoDTO.itens().isEmpty()) {
            throw new ValidationException("RequestItensNULL ", "O request não pode ser nulo.");

        }
        String username = jwt.getSubject();
        LOG.info("Pedido gerado pelo cliente: {}" + username);

        if (securityContext.isUserInRole("Adm")) {
            LOG.warning("Usuário 'Adm' tentou efetuar um pedido: {}"+ username);
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Usuários 'Adm' não podem efetuar pedidos.")
                    .build();
        }

        Cliente cliente = clienteService.findByUsername(username);

        if (cliente == null || !cliente.isCadastroCompleto()) {
            LOG.warning("Cadastro de cliente incompleto para o usuário: {}"+ username);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Cadastro de cliente incompleto. Por favor, complete seu cadastro.")
                    .build();
        }

        PedidoResponseDTO createdPedido = pedidoService.create(pedidoDTO, username);
        LOG.info("Pedido criado com sucesso para o cliente: {}" + username);
        return Response.status(Response.Status.CREATED).entity(createdPedido).build();
    }

    @GET
    @Path("/meus-pedidos/simples")
    @RolesAllowed({ "User" })
    public Response getMeusPedidosSimples(@Context SecurityContext securityContext) {
        String username = jwt.getSubject();
        LOG.info("Buscando pedidos para o usuário: {}"+ username);
        List<PedidoSimplesResponseDTO> pedidos = pedidoService.findBySimplesUsername(username);
        LOG.info("Encontrados {} pedidos para o usuário: {} "+ pedidos.size()+", "+ username);
        return Response.ok(pedidos).build();
    }

    @GET
    @Path("/meus-pedidos/{id}")
    @RolesAllowed({ "User" })
    public Response getPedidoByIdForUser(@PathParam("id") Long id, @Context SecurityContext securityContext) {
        try {
            LOG.info("Buscando pedido com ID: {}"+ id);
            PedidoResponseDTO pedido = pedidoService.findById(id);
            if (pedido == null) {
                LOG.warning("Pedido com ID: {} não encontrado"+ id);
                throw new ValidationException("pedido", "pedido não encontrado");

            }
            if (pedido.cliente() == null) {
                LOG.warning("Pedido com ID: {} não tem cliente associado"+ id);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Pedido não possui cliente associado.")
                        .build();
            }
            String username = jwt.getSubject();
            if (username == null) {
                LOG.warning("Usuário não autenticado");
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Usuário não autenticado.")
                        .build();
            }

            LOG.info("Buscando pedido com ID: {} para o usuário: {}"+ id +", "+ username);

            Cliente cliente = clienteService.findByUsername(username);
            Long idCliente = cliente.getId();
            if (!pedido.cliente().id().equals(idCliente)) {
                LOG.warning("Pedido com ID: {} não pertence ao usuário: {}"+ id+", "+ username);
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Pedido não pertence ao usuário autenticado.")
                        .build();
            }
            LOG.info("Pedido com ID: {} encontrado para o usuário: {} "+ id+", "+ username);
            return Response.ok(pedido).build();

        } catch (ValidationException e) {
            LOG.severe("Erro de validação ao buscar pedido com ID: {}"+ id+", "+ e);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro de validação: " + e.getMessage())
                    .build();

        } catch (Exception e) {
            LOG.severe("Erro inesperado ao buscar pedido com ID: {}"+ id+","+ e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro inesperado ao processar a solicitação.")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Adm" })
    public Response findById(@PathParam("id") Long id, @Context SecurityContext securityContext) {
        LOG.info("Buscando pedido com ID: {}"+ id);
        PedidoResponseDTO pedido = pedidoService.findById(id);
        if (pedido == null) {
            LOG.severe("Pedido com ID: {} não encontrado"+ id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        LOG.info("Pedido com ID: {} encontrado"+ id);
        return Response.ok(pedido).build();
    }

    @PATCH
    @Path("/{id}/status")
    @RolesAllowed({ "Adm" })
    public Response updateStatusPedido(@PathParam("id") Long idPedido, @Valid StatusPatchDTO status) {
        LOG.info("Atualizando status do pedido com ID: {} para {}"+ idPedido);
        pedidoService.updateStatusPedido(idPedido, status.idStatus());
        LOG.info("Status do pedido com ID: {} atualizado com sucesso"+ idPedido);
        return Response.noContent().build();
    }

    @POST
    @Path("/{id}/pagamento/cartao")
    @RolesAllowed({ "User" })
    public Response pagarComCartao(@PathParam("id") Long idPedido, @Valid CartaoDTO cartaoDTO) {
        LOG.info("Processando pagamento com cartão para o pedido ID: {}"+ idPedido);
        try {
            CartaoResponseDTO cartaoResponse = pedidoService.registrarPagamentoCartao(idPedido, cartaoDTO);
            LOG.info("Pagamento com cartão processado com sucesso para o pedido ID: {}"+ idPedido);
            return Response.ok(cartaoResponse).build();
        } catch (ValidationException e) {
            LOG.severe("Erro de validação ao processar pagamento com cartão para o pedido ID: {}"+ idPedido+","+ e);
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/pagamento/pix")
    @RolesAllowed({ "User" })
    public Response pagarComPix(@PathParam("id") Long idPedido, Long idPix) {
        LOG.info("Processando pagamento com PIX para o pedido ID: {}"+ idPedido);
        try {
            PixResponseDTO pixResponse = pedidoService.registrarPagamentoPix(idPedido, idPix);
            LOG.info("Pagamento com PIX processado com sucesso para o pedido ID: {}"+ idPedido);
            return Response.ok(pixResponse).build();
        } catch (ValidationException e) {
            LOG.severe("Erro de validação ao processar pagamento com PIX para o pedido ID: {}"+ idPedido+", "+ e);
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/pagamento/boleto")
    @RolesAllowed({ "User" })
    public Response pagarComBoleto(@PathParam("id") Long idPedido, Long idBoleto) {
        LOG.info("Processando pagamento com boleto para o pedido ID: {}"+ idPedido);
        try {
            BoletoResponseDTO boletoResponse = pedidoService.registrarPagamentoBoleto(idPedido, idBoleto);
            LOG.info("Pagamento com boleto processado com sucesso para o pedido ID: {}"+ idPedido);
            return Response.ok(boletoResponse).build();
        } catch (ValidationException e) {
            LOG.severe("Erro de validação ao processar pagamento com boleto para o pedido ID: {}"+ idPedido+","+ e);
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/gerar/pix")
    @RolesAllowed({ "User" })
    public Response gerarPix(@PathParam("id") Long idPedido, @Context SecurityContext securityContext) {
        LOG.info("Gerando informações de PIX para o pedido ID: {}"+ idPedido);
        try {
            PixResponseDTO pixResponse = pedidoService.gerarInformacoesPix(idPedido);
            LOG.info("Informações de PIX geradas com sucesso para o pedido ID: {}"+ idPedido);
            return Response.ok(pixResponse).build();
        } catch (ValidationException e) {
            LOG.severe("Erro ao gerar informações de PIX para o pedido ID: {} "+ idPedido +","+ e);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}/gerar/boleto")
    @RolesAllowed({ "User" })
    public Response gerarBoleto(@PathParam("id") Long idPedido, @Context SecurityContext securityContext) {
        LOG.info("Gerando informações de boleto para o pedido ID: {}"+ idPedido);
        try {
            BoletoResponseDTO boletoResponse = pedidoService.gerarInformacoesBoleto(idPedido);
            LOG.info("Informações de boleto geradas com sucesso para o pedido ID: {}"+ idPedido);
            return Response.ok(boletoResponse).build();
        } catch (ValidationException e) {
            LOG.severe("Erro ao gerar informações de boleto para o pedido ID: {}"+ idPedido+","+ e);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}