package br.unitins.tp1.monitores.dto.pagamento;


import java.time.LocalDate;
import org.hibernate.validator.constraints.br.CPF;

import br.unitins.tp1.monitores.model.pagamento.Cartao;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CartaoDTO(

    @NotBlank(message = "O nome do titular não pode ser vazio ou nulo. Por favor, forneça um nome válido.")
    @Size(min = 5, max = 50, message = "O nome do titular deve ter entre 5 e 50 caracteres. Verifique o tamanho do nome informado.")
    String titularCartao,

    @NotBlank(message = "O CPF é obrigatório e não pode ser vazio ou nulo. Informe o CPF corretamente.")
    @CPF(message = "O CPF informado está em um formato inválido. Verifique o número e tente novamente.")
    String cpfCartao,

    @NotBlank(message = "O número do cartão não pode ser vazio ou nulo. Informe um número válido.")
    @Size(min = 14, max = 20, message = "O número do cartão deve ter entre 14 e 20 caracteres. Verifique o número do cartão informado.")
    String numero,

    @NotNull(message = "A data de validade é obrigatória e não pode ser nula. Informe a data corretamente.")
    @Future(message = "A data de validade deve ser uma data futura. Verifique se a data está correta.")
    LocalDate dataValidade,

    @NotNull(message = "O código de segurança não pode ser nulo. Informe o código corretamente.")
    @Pattern(regexp = "^\\d{3,4}$", message = "O código de segurança deve ter 3 ou 4 dígitos.")
    String  codigoSeguranca


    ) {

    public static Cartao convertToCartao(CartaoDTO dto){
        Cartao cartao = new Cartao();

        cartao.setTitular(dto.titularCartao());
        cartao.setCpfCartao(dto.cpfCartao());
        cartao.setNumero(dto.numero());
        cartao.setDataValidade(dto.dataValidade());
        cartao.setCodigoSeguranca(dto.codigoSeguranca());
 
        return cartao;
    }
}