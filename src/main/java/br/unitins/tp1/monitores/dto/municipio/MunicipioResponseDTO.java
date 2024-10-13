package br.unitins.tp1.monitores.dto.municipio;

import br.unitins.tp1.monitores.model.Municipio;
import br.unitins.tp1.monitores.dto.estado.EstadoResponseDTO;

public record MunicipioResponseDTO(
    Long id,
    String nome,
    EstadoResponseDTO estado 
) {

    public static MunicipioResponseDTO valueOf(Municipio municipio) {
        return new MunicipioResponseDTO(
            municipio.getId(),
            municipio.getNome(),
            EstadoResponseDTO.valueOf(municipio.getEstado())
        );
      
    }
    
}
