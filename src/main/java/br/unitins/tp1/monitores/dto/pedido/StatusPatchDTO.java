package br.unitins.tp1.monitores.dto.pedido;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StatusPatchDTO(
    @NotNull(message = "O id do status não pode ser nulo ou vazio")
    @Min(value = 1, message = "O valor deve estar entre 1 e 8")
    @Max(value = 8, message = "O valor deve estar entre 1 e 8")
    Integer idStatus
) {
    
}