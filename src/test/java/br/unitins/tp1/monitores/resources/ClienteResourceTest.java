package br.unitins.tp1.monitores.resources;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import br.unitins.tp1.monitores.model.EnderecoUser;
import br.unitins.tp1.monitores.model.Estado;
import br.unitins.tp1.monitores.model.Municipio;
import br.unitins.tp1.monitores.model.Perfil;
import br.unitins.tp1.monitores.model.Sexo;
import br.unitins.tp1.monitores.model.Cliente;
import br.unitins.tp1.monitores.model.Usuario;

@SuppressWarnings("unused")
public class ClienteResourceTest {

    /* 
     * 
     @Test
     void isCadastroCompleto_True() {
        Cliente cliente = new Cliente();
        cliente.setNome("Name");
        cliente.setEmail("email@example.com");
        cliente.setCpf("12345678901"); // Example CPF
        cliente.setSexo(Sexo.MASCULINO); // Example Sexo
        // ...set other direct fields of Cliente as needed...

        EnderecoUser endereco = new EnderecoUser();
        endereco.setLogradouro("rua 12");
        endereco.setNumero("123");
        endereco.setBairro("centro");
        endereco.setCep("12345-678");
        
        Estado estado = new Estado();
        estado.setNome("toca tins"); // Example
        estado.setSigla("tt"); // Example
        endereco.setEstado(estado);
        
        Municipio municipio = new Municipio();
        municipio.setNome("rockcity"); // Example
        municipio.setEstado(estado); // Link to the same estado object
        endereco.setMunicipio(municipio);
        
        cliente.setEnderecoUser(endereco); // Assign the fully populated endereco
        
        Usuario usuario = new Usuario();
        usuario.setPerfil(Perfil.USER); // Example Perfil
        cliente.setUsuario(usuario);
        
        // Now test
        assertTrue(cliente.isCadastroCompleto());
    }
    */
    
    @Test
    public void isCadastroCompleto_returnsFalse_whenNomeIsMissing() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setEmail("email@example.com");
        cliente.setEnderecoUser(new EnderecoUser());

        // Act
        boolean cadastroCompleto = cliente.isCadastroCompleto();

        // Assert
        assertFalse(cadastroCompleto);
    }
    // Adicione testes semelhantes para outros campos obrigatórios (email, endereco,
    // cpf, idSexo)

    @Test
    public void isCadastroCompleto_Falso() {
        Cliente cliente = new Cliente();
        cliente.setNome("Test Name");
        cliente.setEmail("test@example.com");

        assertFalse(cliente.isCadastroCompleto());
    }

    @Test
    public void isCadastroCompleto_Falso_Endereco_Incompleto() {
        Cliente cliente = new Cliente();
        cliente.setNome("Test Name");
        cliente.setEmail("test@example.com");
        EnderecoUser endereco = new EnderecoUser();
        cliente.setEnderecoUser(endereco);

        assertFalse(cliente.isCadastroCompleto());
    }

    @Test
    public void isCadastroCompleto_returnsFalse_whenEmailIsMissing() {
        Cliente cliente = new Cliente();
        cliente.setNome("Test Name");
        cliente.setEnderecoUser(new EnderecoUser());

        assertFalse(cliente.isCadastroCompleto());
    }

}
