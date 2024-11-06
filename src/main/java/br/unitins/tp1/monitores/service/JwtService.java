package br.unitins.tp1.monitores.service;

import br.unitins.tp1.monitores.dto.usuario.UsuarioResponseDTO;

public interface JwtService {
        String generateJwt(UsuarioResponseDTO dto);

}
