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

    
    @Test
    public void isCadastroCompleto_returnsFalse_whenNomeIsMissing() {
        
        Cliente cliente = new Cliente();
        cliente.setEmail("email@example.com");
        cliente.setEnderecoUser(new EnderecoUser());

        
        boolean cadastroCompleto = cliente.isCadastroCompleto();

       
        assertFalse(cadastroCompleto);
    }
    

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
    public void isCadastroCompleto_False() {
        Cliente cliente = new Cliente();
        cliente.setNome("Test Name");
        cliente.setEnderecoUser(new EnderecoUser());

        assertFalse(cliente.isCadastroCompleto());
    }

}
