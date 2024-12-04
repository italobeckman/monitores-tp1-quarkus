package br.unitins.tp1.monitores.dto.pessoa;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record FuncionarioRequestDTO(
    @NotBlank(message = "O nome não pode ser nulo.")
    String nome, 
    @NotBlank(message = "O cpf não pode ser nulo.")
    String cpf,
    @NotBlank(message = "O email não pode ser nulo.")
    String email,
    @Min(value = 1, message = "O idSexo deve ser 1 ou 2.")
    @Max(value = 2, message = "O idSexo deve ser 1 ou 2.")
    Integer idSexo,
    @NotBlank(message = "O salario não pode ser nulo.")
    String salario
) {}
