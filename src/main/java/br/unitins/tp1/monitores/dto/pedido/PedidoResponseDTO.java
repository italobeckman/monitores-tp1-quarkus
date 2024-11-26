package br.unitins.tp1.monitores.dto.pedido;

import br.unitins.tp1.monitores.dto.itemPedido.ItemPedidoResponseDTO;
import br.unitins.tp1.monitores.model.Pedido;

import java.util.List;
import java.time.LocalDateTime;

public record PedidoResponseDTO(
        Long idPedido,
        LocalDateTime data,
        Double valorTotal,
        List<ItemPedidoResponseDTO> listaItemPedido

) {

    public static PedidoResponseDTO valueOf(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getData(),
                pedido.getValorTotal(),
                pedido.getListaItemPedido().stream().map(i -> ItemPedidoResponseDTO.valueOf(i)).toList()
                
        );
    }

}
