package br.unitins.tp1.monitores.dto.monitor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TamanhoMonitorRequestDTO(
    @NotNull
    Double tamanho,

    @NotBlank
    @Size(max = 100)
    String altura,

    @NotBlank
    @Size(max = 100)
    String largura,

    @NotBlank
    @Size(max = 20)
    String peso
) {
}