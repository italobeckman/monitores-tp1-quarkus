package br.unitins.tp1.monitores.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequestDTO (
    @NotBlank(message = "O nome de usuário não pode estar em branco")
    String username,

    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    String senha,

    @NotBlank(message = "A role não pode estar em branco")
    String role
) {
    
}