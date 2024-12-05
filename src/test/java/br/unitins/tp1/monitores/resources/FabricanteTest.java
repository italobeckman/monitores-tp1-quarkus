package br.unitins.tp1.monitores.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.monitores.model.Fabricante;
import br.unitins.tp1.monitores.model.TelefoneFabricante;

public class FabricanteTest {

    @Test
    public void testNome() {
        Fabricante fabricante = new Fabricante();
        fabricante.setNome("Samsung");
        assertEquals("Samsung", fabricante.getNome());
    }

    @Test
    public void testCnpj() {
        Fabricante fabricante = new Fabricante();
        fabricante.setCnpj("12345678901234");
        assertEquals("12345678901234", fabricante.getCnpj());
    }

     @Test
    public void testEmail() {
        Fabricante fabricante = new Fabricante();
        fabricante.setEmail("email@example.com");
        assertEquals("email@example.com", fabricante.getEmail());
    }


    @Test
    public void testListaTelefone() {
        Fabricante fabricante = new Fabricante();
        List<TelefoneFabricante> telefones = new ArrayList<>();
        TelefoneFabricante telefone1 = new TelefoneFabricante();
        telefone1.setCodigoArea("63");
        telefone1.setNumero("999999999");
        telefones.add(telefone1);

        fabricante.setListaTelefone(telefones);


        assertEquals(1, fabricante.getListaTelefone().size());
        assertEquals("63", fabricante.getListaTelefone().get(0).getCodigoArea());


    }

    @Test
    public void testNomeNull() {
        Fabricante fabricante = new Fabricante();
        fabricante.setNome(null);
        assertNull(fabricante.getNome());
    }

    @Test
    public void testCnpjNull() {
        Fabricante fabricante = new Fabricante();
        fabricante.setCnpj(null);
        assertNull(fabricante.getCnpj());
    }

    @Test
    public void testEmailNull() {
        Fabricante fabricante = new Fabricante();
        fabricante.setEmail(null);
        assertNull(fabricante.getEmail());
    }


    @Test
    public void testNomeVazio() {
        Fabricante fabricante = new Fabricante();
        fabricante.setNome("");
        assertEquals("", fabricante.getNome());
    }

    @Test
    public void testCnpjVazio() {
        Fabricante fabricante = new Fabricante();
        fabricante.setCnpj("");
        assertEquals("", fabricante.getCnpj());
    }


        @Test
    public void testEmailVazio() {
        Fabricante fabricante = new Fabricante();
        fabricante.setEmail("");
        assertEquals("", fabricante.getEmail());
    }


}
