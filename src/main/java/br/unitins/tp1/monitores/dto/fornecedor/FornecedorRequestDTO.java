package br.unitins.tp1.monitores.dto.fornecedor;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FornecedorRequestDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    @Size(max = 60, message = "O campo nome deve conter no máximo 60 caracteres.")
    String nome, 
    
    @NotBlank(message = "O campo cnpj deve ser informado.")
    @Size(max = 14, message = "O campo cnpj deve conter no máximo 14 caracteres.")
    String cnpj,
    @NotBlank(message = "O campo email deve ser informado.")
    @Size(max = 60, message = "O campo email deve conter no máximo 60 caracteres.")
    String email,

    @Valid
    List<TelefoneFornecedorRequestDTO> telefones
    
) {
   

}
