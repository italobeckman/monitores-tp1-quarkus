package br.unitins.tp1.monitores.dto.pagamento;

import br.unitins.tp1.monitores.model.pagamento.Boleto;



public record BoletoResponseDTO(
    Long id, 
    Double valor,
    String codigo
){    

    public static BoletoResponseDTO valueOf(Boleto boleto){
        return new BoletoResponseDTO(
            boleto.getId(),
            boleto.getValor(),
            boleto.getCodigo()
        );
    }
    
}