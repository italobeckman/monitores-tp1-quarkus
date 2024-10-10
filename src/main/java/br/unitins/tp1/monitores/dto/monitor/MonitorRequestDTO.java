package br.unitins.tp1.monitores.dto.monitor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MonitorRequestDTO(
    @NotBlank
    @Size(max = 100)
    String modelo, 
    @NotBlank
    @Size(max = 100)
    String marca, 
    @NotBlank
    @Size(max = 100)
    String taxaAtualizacao, 
    @NotBlank
    @Size(max = 20)
    String tempoResposta, 
    @NotBlank
    @Size(max = 20)
    String peso, 
    @NotBlank
    @Size(max = 10)
    Double preco ){

}
