package br.unitins.tp1.monitores.dto.fabricante.fabricante;

import br.unitins.tp1.monitores.model.Municipio;
import br.unitins.tp1.monitores.dto.estado.EstadoResponseDTO;

public record FabricanteResponseDTO(
    Long id,
    String nome,
    EstadoResponseDTO estado 
) {

    public static FabricanteResponseDTO valueOf(Municipio municipio) {
        return new FabricanteResponseDTO(
            municipio.getId(),
            municipio.getNome(),
            EstadoResponseDTO.valueOf(municipio.getEstado())
        );
      
    }
    
}
