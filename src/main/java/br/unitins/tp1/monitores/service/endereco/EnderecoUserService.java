package br.unitins.tp1.monitores.service.endereco;

import java.util.List;

import br.unitins.tp1.monitores.dto.endereco.EnderecoUserRequestDTO;
import br.unitins.tp1.monitores.model.EnderecoUser;
import jakarta.validation.Valid;


public interface EnderecoUserService {

    EnderecoUser findById(Long id);
    
    List<EnderecoUser> findAll();

    EnderecoUser create(@Valid EnderecoUserRequestDTO dto);

    EnderecoUser update(Long id, EnderecoUserRequestDTO dto);

    void delete(Long id); 
    
}
