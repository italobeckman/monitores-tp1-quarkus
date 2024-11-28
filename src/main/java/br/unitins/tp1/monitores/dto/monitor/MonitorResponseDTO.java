package br.unitins.tp1.monitores.dto.monitor;

import br.unitins.tp1.monitores.dto.fabricante.FabricanteResponseDTO;
import br.unitins.tp1.monitores.model.Monitor;

public record MonitorResponseDTO(
    Long id, 
String modelo, 
String marca, 
String taxaAtualizacao, 
String tempoResposta, 
Double preco,
FabricanteResponseDTO fabricante,
TamanhoMonitorResponseDTO tamanhoMonitor
) {

    public static MonitorResponseDTO valueOf(Monitor monitor) {
        return new MonitorResponseDTO(
            monitor.getId(),
            monitor.getModelo(),
            monitor.getMarca(),
            monitor.getTaxaAtualizacao(),
            monitor.getTempoResposta(),
            monitor.getPreco(),
            FabricanteResponseDTO.valueOf(monitor.getFabricante()),
            TamanhoMonitorResponseDTO.valueOf(monitor.getTamanhoMonitor())
        );
    }

}
