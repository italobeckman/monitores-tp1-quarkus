package br.unitins.tp1.monitores.dto.fornecedor;

import br.unitins.tp1.monitores.model.TelefoneFornecedor;

public record TelefoneFornecedorResponseDTO(
    Long id,
    String codigoArea,
    String numero
){
    

    public static TelefoneFornecedorResponseDTO valueOf(TelefoneFornecedor Telfornecedor) {
        return new TelefoneFornecedorResponseDTO(
            Telfornecedor.getId(), 
            Telfornecedor.getCodigoArea(),
            Telfornecedor.getNumero());
    }
}
