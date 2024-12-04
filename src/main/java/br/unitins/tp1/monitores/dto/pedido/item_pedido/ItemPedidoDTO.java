package br.unitins.tp1.monitores.dto.pedido.item_pedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoDTO(

    @NotNull(message = "A quantidade não pode ser nula")
    @Positive(message = "A quantidade não pode ser negativa ou 0")
    Integer quantidade,

    @NotNull(message = "O id do monitor não pode ser nulo")
    @Positive(message = "O id do monitor não pode ser negativo ou 0")
    Long idMonitor
) {

}