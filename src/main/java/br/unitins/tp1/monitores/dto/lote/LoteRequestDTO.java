package br.unitins.tp1.monitores.dto.lote;

import java.time.LocalDate;

public record LoteRequestDTO(
    Long idMonitor,
    LocalDate data,
    String codigo,
    Integer quantidade



) {

}
