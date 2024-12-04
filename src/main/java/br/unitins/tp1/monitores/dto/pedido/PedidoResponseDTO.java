package br.unitins.tp1.monitores.dto.pedido;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.monitores.dto.pedido.item_pedido.ItemPedidoResponseDTO;
import br.unitins.tp1.monitores.dto.pessoa.ClienteResponseDTO;
import br.unitins.tp1.monitores.model.pagamento.Pagamento;
import br.unitins.tp1.monitores.model.pedido.Pedido;



public record PedidoResponseDTO(
    Long id,
    ClienteResponseDTO cliente,
    Double total,
    List<ItemPedidoResponseDTO> itens,
    List<StatusPedidoResponseDTO> status,
    LocalDateTime prazoPagamento,
    Pagamento pagamento
){
    public static PedidoResponseDTO valueOf(Pedido pedido){
        return new PedidoResponseDTO(
            pedido.getId(),
            ClienteResponseDTO.valueOf(pedido.getCliente()),
            pedido.getTotal(),
            pedido.getListaItem().stream().map(ItemPedidoResponseDTO::valueOf).toList(),
            pedido.getListaStatus().stream().map(StatusPedidoResponseDTO::valueOf).toList(),
            pedido.getPrazoPagamento(),
            pedido.getPagamento()
        );
    }
}