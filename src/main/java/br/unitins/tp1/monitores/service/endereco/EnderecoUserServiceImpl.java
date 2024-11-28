package br.unitins.tp1.monitores.service.endereco;

import java.util.List;

import br.unitins.tp1.monitores.dto.endereco.EnderecoUserRequestDTO;
import br.unitins.tp1.monitores.model.EnderecoUser;
import br.unitins.tp1.monitores.repository.EnderecoUserRepository;
import br.unitins.tp1.monitores.service.EstadoService;
import br.unitins.tp1.monitores.service.MunicipioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class EnderecoUserServiceImpl implements EnderecoUserService {

    @Inject
    public EnderecoUserRepository enderecoUserRepository;

    @Inject
    public MunicipioService municipioService;

    @Inject
    public EstadoService estadoService;
    @Override
    public EnderecoUser findById(Long id) {
        return enderecoUserRepository.findById(id);
    }


    @Override
    public List<EnderecoUser> findAll() {
        return enderecoUserRepository.findAll().list();
    }

    @Override
    @Transactional
    public EnderecoUser create(@Valid EnderecoUserRequestDTO dto) {
        
        EnderecoUser enderecoUser = new EnderecoUser();
        enderecoUser.setLogradouro(dto.logradouro());
        enderecoUser.setNumero(dto.numero());
        enderecoUser.setBairro(dto.bairro());
        enderecoUser.setComplemento(dto.complemento());
        enderecoUser.setCep(dto.cep());

        enderecoUser.setMunicipio(municipioService.findById(dto.idMunicipio()));
        enderecoUser.setEstado(estadoService.findById(dto.idEstado()));

       

        enderecoUserRepository.persist(enderecoUser);
        return enderecoUser;
    }


    @Override
    @Transactional
    public EnderecoUser update(Long id, EnderecoUserRequestDTO dto) {
        EnderecoUser enderecoUser = enderecoUserRepository.findById(id);

        enderecoUser.setLogradouro(dto.logradouro());
        enderecoUser.setNumero(dto.numero());
        enderecoUser.setBairro(dto.bairro());
        enderecoUser.setComplemento(dto.complemento());
        enderecoUser.setCep(dto.cep());

        enderecoUser.setMunicipio(municipioService.findById(dto.idMunicipio()));
        enderecoUser.setEstado(estadoService.findById(dto.idEstado()));

       


        return enderecoUser;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        enderecoUserRepository.deleteById(id);
    }

}
