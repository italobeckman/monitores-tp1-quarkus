package br.unitins.tp1.monitores.dto.pedido;

import br.unitins.tp1.monitores.model.pedido.Status;
import br.unitins.tp1.monitores.model.pedido.StatusPedido;


public record StatusPedidoResponseDTO(
    Status status
) {
    public static StatusPedidoResponseDTO valueOf(StatusPedido status){
        return new StatusPedidoResponseDTO(
            status.getStatus()
        );
    }
}