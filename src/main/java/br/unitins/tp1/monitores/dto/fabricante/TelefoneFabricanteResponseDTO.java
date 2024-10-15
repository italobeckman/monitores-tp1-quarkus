package br.unitins.tp1.monitores.dto.fabricante;

import br.unitins.tp1.monitores.model.TelefoneFabricante;

public record TelefoneFabricanteResponseDTO(
    Long id,
    String codigoArea,
    String numero
){
    

    public static TelefoneFabricanteResponseDTO valueOf(TelefoneFabricante Telfabricante) {
        return new TelefoneFabricanteResponseDTO(
            Telfabricante.getId(), 
            Telfabricante.getCodigoArea(),
            Telfabricante.getNumero());
    }
}
