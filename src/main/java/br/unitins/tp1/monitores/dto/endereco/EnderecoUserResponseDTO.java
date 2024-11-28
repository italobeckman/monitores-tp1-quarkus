package br.unitins.tp1.monitores.dto.endereco;

import br.unitins.tp1.monitores.model.EnderecoUser;

public record EnderecoUserResponseDTO(
        Long id,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        Long idEstado,
        Long idMunicipio

) {

    public static EnderecoUserResponseDTO valueOf(EnderecoUser endereco) {
        return new EnderecoUserResponseDTO(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getEstado().getId(),
                endereco.getMunicipio().getId())
                
                ;
    }

}
