package br.unitins.tp1.monitores.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.monitores.model.Fornecedor;
import br.unitins.tp1.monitores.model.TelefoneFornecedor;

public class FornecedorTest {

    @Test
    public void testNome() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("Fornecedor Teste");
        assertEquals("Fornecedor Teste", fornecedor.getNome());
    }

    @Test
    public void testCnpj() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpj("12345678901234");
        assertEquals("12345678901234", fornecedor.getCnpj());
    }

    @Test
    public void testEmail() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setEmail("fornecedor@example.com");
        assertEquals("fornecedor@example.com", fornecedor.getEmail());
    }

    @Test
    public void testListaTelefone() {
        Fornecedor fornecedor = new Fornecedor();
        List<TelefoneFornecedor> telefones = new ArrayList<>();
        TelefoneFornecedor telefone1 = new TelefoneFornecedor();
        telefone1.setCodigoArea("63");
        telefone1.setNumero("999999999");
        telefones.add(telefone1);
        fornecedor.setListaTelefone(telefones);

        assertEquals(1, fornecedor.getListaTelefone().size());
        assertEquals("63", fornecedor.getListaTelefone().get(0).getCodigoArea());
        assertEquals("999999999", fornecedor.getListaTelefone().get(0).getNumero());
    }


  
    @Test
    public void testNomeNull() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(null);
        assertNull(fornecedor.getNome());
    }

    @Test
    public void testCnpjNull() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpj(null);
        assertNull(fornecedor.getCnpj());
    }

    @Test
    public void testEmailNull() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setEmail(null);
        assertNull(fornecedor.getEmail());
    }

    
    @Test
    public void testNomeVazio() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("");
        assertEquals("", fornecedor.getNome());
    }

    @Test
    public void testCnpjVazio() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpj("");
        assertEquals("", fornecedor.getCnpj());
    }

    @Test
    public void testEmailVazio() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setEmail("");
        assertEquals("", fornecedor.getEmail());
    }
}
