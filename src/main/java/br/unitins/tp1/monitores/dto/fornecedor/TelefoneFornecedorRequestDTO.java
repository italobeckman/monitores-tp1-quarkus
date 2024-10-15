package br.unitins.tp1.monitores.dto.fornecedor;

import br.unitins.tp1.monitores.model.TelefoneFornecedor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TelefoneFornecedorRequestDTO(

	@NotBlank(message = "Código de área é obrigatório")
	@Size(max = 2, message = "O campo codigoArea deve conter no máximo 2 caracteres.")
	@Pattern(regexp = "\\d+", message = "Código de área deve conter apenas números")
	String codigoArea,

	@NotBlank(message = "Número de telefone é obrigatório")
	@Pattern(regexp = "\\d+", message = "Número de telefone deve conter apenas números")
	String numero,

	Long fornecedor
) {
	public static TelefoneFornecedor valueOf(TelefoneFornecedorRequestDTO dto){
        TelefoneFornecedor telefone = new TelefoneFornecedor();

        telefone.setCodigoArea(dto.codigoArea);
        telefone.setNumero(dto.numero());
        return telefone;
    } 
}

