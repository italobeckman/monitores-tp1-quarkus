package br.unitins.tp1.monitores.dto.pedido.item_pedido;

import br.unitins.tp1.monitores.model.pedido.ItemPedido;

public record ItemPedidoResponseDTO(
    Long id,
    String nome,
    Integer quantidade
) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido item) {
        return new ItemPedidoResponseDTO(
            item.getId(), 
            item.getMonitor().getNome(),    
            item.getQuantidade()
        );
    }
}