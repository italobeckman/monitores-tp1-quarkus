package br.unitins.tp1.monitores.dto.pedido;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import br.unitins.tp1.monitores.dto.endereco.EnderecoPedidoRequestDTO;
import br.unitins.tp1.monitores.dto.pedido.item_pedido.ItemPedidoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PedidoDTO(
    EnderecoPedidoRequestDTO endereco,
    @NotNull(message = "A lista de itens não pode ser nula")
    @Valid
    @UniqueElements(message = "A lista de itens não deve conter itens repetidos")
    List<ItemPedidoDTO> itens
){
}