package br.unitins.tp1.monitores.dto.estado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoRequestDTO(
    @NotBlank
    @Size(max = 100)
    String nome, 
    @Size(min = 2, max = 2, message = "A sigla deve ter 2 apenas caracteres")
    String sigla) {

}

