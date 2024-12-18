package br.unitins.tp1.monitores.dto.lote;

import java.time.LocalDate;

import br.unitins.tp1.monitores.dto.monitor.MonitorResponseDTO;
import br.unitins.tp1.monitores.model.Lote;

public record LoteResponseDTO(
    Long id,
    LocalDate data,
    String codigo,
    Integer quantidade,
    Long idFornecedor,
    MonitorResponseDTO monitor
) {

    public static LoteResponseDTO valueOf(Lote lote) {
        return new LoteResponseDTO (
            lote.getId(),
            lote.getData(),
            lote.getCodigo(),
            lote.getQuantidade(),
            lote.getFornecedor().getId(),
            MonitorResponseDTO.valueOf(lote.getMonitor())
            );

    }
    
}
