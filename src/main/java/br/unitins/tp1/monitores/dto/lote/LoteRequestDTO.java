package br.unitins.tp1.monitores.dto.lote;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LoteRequestDTO(

    @NotNull(message = "O id do monitor não pode ser nulo")
    @Positive(message = "O id do monitor deve ser um valor positivo")
    Long idMonitor,

    @NotNull(message = "A data não pode ser nula")
    LocalDate data,

    @NotNull(message = "A quantidade não pode ser nula")
    @Positive(message = "A quantidade deve ser um valor positivo")
    Integer quantidade,

    @NotNull(message = "O id do fornecedor não pode ser nulo")
    @Positive(message = "O id do fornecedor deve ser um valor positivo")
    Long idFornecedor

) {

}