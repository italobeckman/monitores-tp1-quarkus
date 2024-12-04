package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.pessoa.ClienteRequestDTO;
import br.unitins.tp1.monitores.model.Cliente;
import br.unitins.tp1.monitores.model.EnderecoUser;
import br.unitins.tp1.monitores.model.Sexo;
import br.unitins.tp1.monitores.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    public ClienteRepository clienteRepository;

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
    public Cliente update(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id);


        cliente.setNome(dto.nome()); // Usando nome()
        cliente.setSexo(Sexo.valueOf(dto.idSexo())); // Usando getIdSexo()
        cliente.setEmail(dto.email()); // Usando getEmail()

        EnderecoUser enderecoUser = new EnderecoUser();
        enderecoUser.setLogradouro(dto.enderecoUser().getLogradouro()); // Usando getLogradouro()
        enderecoUser.setNumero(dto.enderecoUser().getNumero()); // Usando getNumero()
        enderecoUser.setComplemento(dto.enderecoUser().getComplemento()); // Usando getComplemento()
        enderecoUser.setCep(dto.enderecoUser().getCep()); // Usando getCep()
        enderecoUser.setMunicipio(dto.enderecoUser().getMunicipio()); // Usando getCidade()
        enderecoUser.setEstado(dto.enderecoUser().getEstado()); // Usando getEstado()
        enderecoUser.setBairro(dto.enderecoUser().getBairro()); // Usando getBairro()

        cliente.setEnderecoUser(enderecoUser); // Supondo que Cliente tenha um método setEnderecoUser


        return cliente;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Cliente updateNomeImagem(Long id, String nomeImagem) {
        Cliente cliente = clienteRepository.findById(id);
        cliente.setNomeImagem(nomeImagem);

        return cliente;
    }

    @Override
    public Cliente findByUsername(String username) {
        return clienteRepository.findByUsername(username);
    }
}