package br.unitins.tp1.monitores.dto.endereco;

import br.unitins.tp1.monitores.model.EnderecoPedido;

public record EnderecoPedidoResponseDTO(
        Long id,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        Long idEstado,
        Long idMunicipio) {

    public static EnderecoPedidoResponseDTO valueOf(EnderecoPedido endereco) {
        return new EnderecoPedidoResponseDTO(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getEstado().getId(),
                endereco.getMunicipio().getId());
    }

}
