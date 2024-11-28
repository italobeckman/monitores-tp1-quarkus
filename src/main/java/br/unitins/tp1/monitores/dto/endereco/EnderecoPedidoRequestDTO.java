package br.unitins.tp1.monitores.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EnderecoPedidoRequestDTO(


    @NotBlank(message = "O nome não pode ser nulo")
    @Size(max = 100, message = "O nome deve ter no maximo 100 caracteres")
    String logradouro,
    @NotBlank(message = "A sigla não pode ser nula")
    @Size(max = 100)
    String numero,
    @NotBlank(message = "A sigla não pode ser nula")
    String complemento,
    @NotBlank(message = "A sigla não pode ser nula")
    String bairro,
    @NotBlank(message = "A sigla não pode ser nula")
    String cep,
    @Positive(message = "Informe um campo valido para o id de Fabricante")
    Long idEstado,
    @Positive(message = "Informe um campo valido para o id de tamanhoMonitor")
    Long idMunicipio

) {

}
