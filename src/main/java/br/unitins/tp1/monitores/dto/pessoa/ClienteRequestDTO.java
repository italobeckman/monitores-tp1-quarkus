package br.unitins.tp1.monitores.dto.pessoa;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
    @NotBlank(message = "O nome não pode ser nulo.")
    String nome, 
    @NotBlank(message = "O cpf não pode ser nulo.")
    String cpf,
    
    @Min(value = 1, message = "O idSexo deve ser 1 ou 2.")
    @Max(value = 2, message = "O idSexo deve ser 1 ou 2.")
    Integer idSexo
) {}
