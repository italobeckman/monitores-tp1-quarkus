package br.unitins.tp1.monitores.dto.pessoa;

import br.unitins.tp1.monitores.model.PessoaFisica;
import br.unitins.tp1.monitores.model.Sexo;

public record PessoaFisicaResponseDTO(
    Long id, 
    String nome,
    String cpf,
    Sexo sexo
    ) {


    public static PessoaFisicaResponseDTO valueOf(PessoaFisica pessoaFisica){
        return new PessoaFisicaResponseDTO(
            pessoaFisica.getId(), 
            pessoaFisica.getNome(),
            pessoaFisica.getCpf(),
            pessoaFisica.getSexo());

    };
}
