package br.unitins.tp1.monitores.dto.fabricante;

import java.util.List;
import br.unitins.tp1.monitores.model.Fabricante;

public record FabricanteResponseDTO(

    Long id,
    String nome,
    String cnpj,
    String email,
    List<TelefoneFabricanteResponseDTO> telefones
) {
    
    public static FabricanteResponseDTO valueOf(Fabricante fabricante) {
        return new FabricanteResponseDTO(
            fabricante.getId(),
            fabricante.getNome(),
            fabricante.getCnpj(),
            fabricante.getEmail(),
            fabricante.getListaTelefone().stream()
                .map(TelefoneFabricanteResponseDTO::valueOf)
                .toList()
        );
    }
    
}
