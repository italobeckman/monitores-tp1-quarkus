package br.unitins.tp1.monitores.dto.itemPedido;

public record ItemPedidoRequestDTO(
    Long idProduto,
    Integer quantidade,
    Double preco

) {

}
