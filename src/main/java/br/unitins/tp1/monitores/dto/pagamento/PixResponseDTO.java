package br.unitins.tp1.monitores.dto.pagamento;

import br.unitins.tp1.monitores.model.pagamento.Pix;


public record PixResponseDTO(
    Long id,
    Double valor,
    String chaveDestinatario,
    String identificador
) {
    
    public static PixResponseDTO valueOf(Pix pix){
        return new PixResponseDTO(
            pix.getId(),
            pix.getValor(),
            pix.getChaveDestinatario(),
            pix.getIdentificador()
        );
    }

}