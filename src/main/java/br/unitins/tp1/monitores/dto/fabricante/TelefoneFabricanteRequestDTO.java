package br.unitins.tp1.monitores.dto.fabricante;

import br.unitins.tp1.monitores.model.TelefoneFabricante;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TelefoneFabricanteRequestDTO(

	@NotBlank(message = "Código de área é obrigatório")
	@Pattern(regexp = "\\d+", message = "Código de área deve conter apenas números")
	@Size(max = 2, message = "O campo codigoArea deve conter no máximo 2 caracteres.")
	String codigoArea,

	@NotBlank(message = "Número de telefone é obrigatório")
	@Pattern(regexp = "\\d+", message = "Número de telefone deve conter apenas números")
	String numero,

	Long fabricante

	) {
	public static TelefoneFabricante toEntity(TelefoneFabricanteRequestDTO dto){
        TelefoneFabricante telefone = new TelefoneFabricante();

        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        return telefone;
    } 
}