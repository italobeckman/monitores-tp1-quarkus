package br.unitins.tp1.monitores.dto.monitor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record MonitorRequestDTO(
    @NotBlank
    @Size(max = 100)
    String nome,

    @NotBlank
    @Size(max = 100)
    String marca,
    
    @NotBlank
    @Size(max = 100)
    String modelo,

    @NotNull
    Double preco,

    @NotBlank
    @Size(max = 100)
    String taxaAtualizacao,
    
    @NotBlank
    @Size(max = 20)
    String tempoResposta,
    
    @NotNull
    LocalDate anoLancamento,
    
    @Positive(message = "Informe um campo valido para o id de Fabricante")
    Long idFabricante,
    
    @Positive(message = "Informe um campo valido para o id de tamanhoMonitor")
    Long idTamanhoMonitor
) {
}