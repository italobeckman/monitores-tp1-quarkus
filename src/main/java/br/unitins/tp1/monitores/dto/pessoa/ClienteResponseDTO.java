package br.unitins.tp1.monitores.dto.pessoa;

import br.unitins.tp1.monitores.model.Cliente;
import br.unitins.tp1.monitores.model.Sexo;

public record ClienteResponseDTO(
    Long id, 
    String nome,
    String cpf,
    Sexo sexo
    ) {


    public static ClienteResponseDTO valueOf(Cliente cliente){
        return new ClienteResponseDTO(
            cliente.getId(), 
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getSexo());

    };
}
