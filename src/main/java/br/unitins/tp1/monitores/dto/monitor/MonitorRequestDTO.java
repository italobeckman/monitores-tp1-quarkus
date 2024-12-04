package br.unitins.tp1.monitores.dto.monitor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MonitorRequestDTO(

    @NotBlank(message = "O nome não pode ser nulo")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    String nome,

    @NotBlank(message = "A marca não pode ser nula")
    @Size(max = 100, message = "A marca deve ter no máximo 100 caracteres")
    String marca,
    
    @NotBlank(message = "O modelo não pode ser nulo")
    @Size(max = 100, message = "O modelo deve ter no máximo 100 caracteres")
    String modelo,

    @NotNull(message = "O preço não pode ser nulo")
    @Positive(message = "O preço deve ser um valor positivo")
    Double preco,

    @NotBlank(message = "A taxa de atualização não pode ser nula")
    @Size(max = 100, message = "A taxa de atualização deve ter no máximo 100 caracteres")
    String taxaAtualizacao,
    
    @NotBlank(message = "O tempo de resposta não pode ser nulo")
    @Size(max = 20, message = "O tempo de resposta deve ter no máximo 20 caracteres")
    String tempoResposta,
    
    @NotNull(message = "O ano de lançamento não pode ser nulo")
    Integer anoLancamento,
    
    @NotNull(message = "O id do fabricante não pode ser nulo")
    @Positive(message = "Informe um campo válido para o id de Fabricante")
    Long idFabricante,
    
    @NotNull(message = "O id do tamanho do monitor não pode ser nulo")
    @Positive(message = "Informe um campo válido para o id de tamanhoMonitor")
    Long idTamanhoMonitor

) {
}