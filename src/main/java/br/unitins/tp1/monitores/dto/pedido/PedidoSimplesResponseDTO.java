package br.unitins.tp1.monitores.dto.pedido;

import java.util.List;
import br.unitins.tp1.monitores.model.pagamento.Pagamento;
import br.unitins.tp1.monitores.model.pedido.Pedido;



public record PedidoSimplesResponseDTO(
    Long id,
    Double total,
    List<StatusPedidoResponseDTO> status,
    Pagamento pagamento
){
    public static PedidoSimplesResponseDTO valueOf(Pedido pedido){
        return new PedidoSimplesResponseDTO(
            pedido.getId(),
            pedido.getTotal(),
            pedido.getListaStatus().stream().map(StatusPedidoResponseDTO::valueOf).toList(),
            pedido.getPagamento()
        );
    }
}