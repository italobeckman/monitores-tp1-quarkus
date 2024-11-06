package br.unitins.tp1.monitores.dto.itemPedido;

import br.unitins.tp1.monitores.model.ItemPedido;

public record ItemPedidoResponseDTO(
    Long idProduto,
    String nome, 
    Integer quantidade,
    Double valor) {

    public static ItemPedidoResponseDTO valueOf(ItemPedido itemPedido) {
        return new ItemPedidoResponseDTO (
            itemPedido.getLote().getMonitor().getId(),
            itemPedido.getLote().getMonitor().getNome(),
            itemPedido.getQuantidade(),
            itemPedido.getPreco());
    }
    
}
