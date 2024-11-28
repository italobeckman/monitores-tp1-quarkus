package br.unitins.tp1.monitores.dto.monitor;

import br.unitins.tp1.monitores.model.TamanhoMonitor;

public record TamanhoMonitorResponseDTO(
        Long id,
        Double tamanho,
        String altura,
        String largura,
        String peso) {

    public static TamanhoMonitorResponseDTO valueOf(TamanhoMonitor monitor) {
        return new TamanhoMonitorResponseDTO(
                monitor.getId(),
                monitor.getTamanho(),
                monitor.getAltura(),
                monitor.getLargura(),
                monitor.getPeso());
    }

}
