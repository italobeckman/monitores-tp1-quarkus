package br.unitins.tp1.monitores.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EnderecoPedidoRequestDTO(

    @NotBlank(message = "O logradouro não pode ser nulo")
    @Size(max = 100, message = "O logradouro deve ter no máximo 100 caracteres")
    String logradouro,

    @NotBlank(message = "O número não pode ser nulo")
    @Size(max = 100, message = "O número deve ter no máximo 100 caracteres")
    String numero,

    @NotBlank(message = "O complemento não pode ser nulo")
    String complemento,

    @NotBlank(message = "O bairro não pode ser nulo")
    String bairro,

    @NotBlank(message = "O CEP não pode ser nulo")
    String cep,

    @Positive(message = "Informe um campo válido para o id de Estado")
    Long idEstado,

    @Positive(message = "Informe um campo válido para o id de Município")
    Long idMunicipio

) {

}