package br.unitins.tp1.monitores.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EnderecoUserRequestDTO(
    @NotBlank(message = "O logradouro não pode ser nulo")
    @Size(max = 100, message = "deve ter no maximo 100 caracteres")
    String logradouro,
    @NotBlank(message = "o numero não pode ser nulo")
    @Size(max = 100)
    String numero,
    @NotBlank(message = "O complemento não pode ser nulo")
    String complemento,
    @NotBlank(message = "O bairro não pode ser nulo")
    String bairro,
    @NotBlank(message = "o cep não pode ser nulo")
    String cep,
    @Positive(message = "Informe um campo valido para o id de Fabricante")
    Long idEstado,
    @Positive(message = "Informe um campo valido para o id de tamanhoMonitor")
    Long idMunicipio

) {

}
