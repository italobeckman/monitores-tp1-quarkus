package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.pessoa.ClienteRequestDTO;
import br.unitins.tp1.monitores.model.Cliente;
import br.unitins.tp1.monitores.model.EnderecoUser;
import br.unitins.tp1.monitores.model.Estado;
import br.unitins.tp1.monitores.model.Municipio;
import br.unitins.tp1.monitores.model.Sexo;
import br.unitins.tp1.monitores.repository.ClienteRepository;
import br.unitins.tp1.monitores.repository.EstadoRepository;
import br.unitins.tp1.monitores.repository.MunicipioRepository;
import br.unitins.tp1.monitores.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public EstadoRepository estadoRepository;

    @Inject
    public MunicipioRepository municipioRepository;

    @Inject 
    ClienteService clienteService;


    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Override
    public List<Cliente> findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll().list();
    }

    @Override
    @Transactional
    public Cliente update(String username, ClienteRequestDTO dto) {

        Cliente cliente = clienteService.findByUsername(username);

        if (cliente == null) {
            throw new ValidationException("cliente", "Cliente não encontrado.");
        }
        if (dto.nome() == null || dto.nome().trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }

        if (dto.idSexo() == null || dto.idSexo() < 1 || dto.idSexo() > Sexo.values().length) {
            throw new ValidationException("idSexo", "Sexo inválido.");
        }

        if (dto.email() == null || dto.email().trim().isEmpty()) {
            throw new ValidationException("email", "Email é obrigatório.");
        }

        cliente.setNome(dto.nome()); 
        cliente.setSexo(Sexo.valueOf(dto.idSexo())); 
        cliente.setEmail(dto.email());

        if (dto.enderecoUser() == null) {
            throw new ValidationException("enderecoUser", "Endereço é obrigatório.");
        }

        Estado estado = estadoRepository.findById(dto.enderecoUser().idEstado());
        Municipio municipio = municipioRepository.findById(dto.enderecoUser().idMunicipio());

        if( estado == null || dto.enderecoUser().idEstado() == null) {
            throw new ValidationException("estado", "Estado é obrigatório.");
        }
        if(municipio == null || dto.enderecoUser().idEstado() == null) {
            throw new ValidationException("municipio", "Município é obrigatório.");
        }
        if (dto.enderecoUser().logradouro() == null || dto.enderecoUser().logradouro().trim().isEmpty()) {
            throw new ValidationException("logradouro", "Logradouro é obrigatório.");
        }
        if (dto.enderecoUser().numero() == null || dto.enderecoUser().numero().trim().isEmpty()) {
            throw new ValidationException("numero", "Numero é obrigatório.");
        }
        if (dto.enderecoUser().cep() == null || dto.enderecoUser().cep().trim().isEmpty()) {
            throw new ValidationException("cep", "Cep é obrigatório.");
        }
        if (dto.enderecoUser().bairro() == null || dto.enderecoUser().bairro().trim().isEmpty()) {
            throw new ValidationException("bairro", "Bairro é obrigatório.");
        }
        EnderecoUser enderecoUser = new EnderecoUser();
        enderecoUser.setLogradouro(dto.enderecoUser().logradouro()); 
        enderecoUser.setNumero(dto.enderecoUser().numero()); 
        enderecoUser.setComplemento(dto.enderecoUser().complemento()); 
        enderecoUser.setCep(dto.enderecoUser().cep()); 

        
        enderecoUser.setMunicipio(municipio); 
        enderecoUser.setEstado(estado); 
        enderecoUser.setBairro(dto.enderecoUser().bairro()); 
        

        cliente.setEnderecoUser(enderecoUser); 
        clienteRepository.getEntityManager().merge(cliente);
        return cliente;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new ValidationException("id", "Id não pode ser nulo");

        }
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Cliente updateNomeImagem(Long id, String nomeImagem) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            throw new ValidationException("id", "Cliente não encontrado.");

        }
        cliente.setNomeImagem(nomeImagem);

        return cliente;
    }

    @Override
    public Cliente findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("username", "Username é obrigatório.");
        }
        return clienteRepository.findByUsername(username);
    }
}
