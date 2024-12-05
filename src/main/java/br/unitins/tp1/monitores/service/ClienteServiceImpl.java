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
    public Cliente update(Cliente cliente, ClienteRequestDTO dto) {
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

        Estado estado = estadoRepository.findById(dto.enderecoUser().getEstado().getId());
        Municipio municipio = municipioRepository.findById(dto.enderecoUser().getMunicipio().getId());

        if( estado == null || dto.enderecoUser().getEstado() == null) {
            throw new ValidationException("estado", "Estado é obrigatório.");
        }
        if(municipio == null || dto.enderecoUser().getMunicipio() == null) {
            throw new ValidationException("municipio", "Município é obrigatório.");
        }
        if (dto.enderecoUser().getLogradouro() == null || dto.enderecoUser().getLogradouro().trim().isEmpty()) {
            throw new ValidationException("logradouro", "Logradouro é obrigatório.");
        }
        if (dto.enderecoUser().getNumero() == null || dto.enderecoUser().getNumero().trim().isEmpty()) {
            throw new ValidationException("numero", "Numero é obrigatório.");
        }
        if (dto.enderecoUser().getCep() == null || dto.enderecoUser().getCep().trim().isEmpty()) {
            throw new ValidationException("cep", "Cep é obrigatório.");
        }
        if (dto.enderecoUser().getBairro() == null || dto.enderecoUser().getBairro().trim().isEmpty()) {
            throw new ValidationException("bairro", "Bairro é obrigatório.");
        }
        EnderecoUser enderecoUser = new EnderecoUser();
        enderecoUser.setLogradouro(dto.enderecoUser().getLogradouro()); 
        enderecoUser.setNumero(dto.enderecoUser().getNumero()); 
        enderecoUser.setComplemento(dto.enderecoUser().getComplemento()); 
        enderecoUser.setCep(dto.enderecoUser().getCep()); 
        enderecoUser.setMunicipio(dto.enderecoUser().getMunicipio()); 
        enderecoUser.setEstado(dto.enderecoUser().getEstado()); 
        enderecoUser.setBairro(dto.enderecoUser().getBairro()); 
        

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
