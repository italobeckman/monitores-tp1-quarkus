package br.unitins.tp1.monitores.dto.fornecedor;

import java.util.List;
import br.unitins.tp1.monitores.model.Fornecedor;

public record FornecedorResponseDTO(

    Long id,
    String nome,
    String cnpj,
    String email,
    List<TelefoneFornecedorResponseDTO> telefones
) {
    
    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getNome(),
            fornecedor.getCnpj(),
            fornecedor.getEmail(),
            fornecedor.getListaTelefone().stream()
                .map(TelefoneFornecedorResponseDTO::valueOf)
                .toList()
        );
    }
    
}
