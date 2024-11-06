package br.unitins.tp1.monitores.dto.pedido;

import jakarta.validation.constraints.NotNull;
import java.util.List;

import br.unitins.tp1.monitores.dto.itemPedido.ItemPedidoRequestDTO;
public record PedidoRequestDTO (
    @NotNull(message = "Este campo não pode ser nulo")
    Double valorTotal,
    List<ItemPedidoRequestDTO> listaItemPedido
){

}
