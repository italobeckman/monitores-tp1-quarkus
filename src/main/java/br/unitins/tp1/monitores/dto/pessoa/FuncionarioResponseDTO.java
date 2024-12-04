package br.unitins.tp1.monitores.dto.pessoa;

import br.unitins.tp1.monitores.model.Funcionario;
import br.unitins.tp1.monitores.model.Sexo;

public record FuncionarioResponseDTO(
    Long id, 
    String nome,
    String cpf,
    Sexo sexo,
    String salario
    ) {


    public static FuncionarioResponseDTO valueOf(Funcionario funcionario){
        return new FuncionarioResponseDTO(
            funcionario.getId(), 
            funcionario.getNome(),
            funcionario.getCpf(),
            funcionario.getSexo(),
            funcionario.getSalario());

    };
}
