package br.unitins.tp1.monitores.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.monitores.model.Estado;

public class EstadoTest {

    @Test
    public void testNome() {
        Estado estado = new Estado();
        estado.setNome("Tocantins");
        assertEquals("Tocantins", estado.getNome());

    }

    @Test
    public void testSigla() {
        Estado estado = new Estado();
        estado.setSigla("TO");
        assertEquals("TO", estado.getSigla());

    }

    @Test
    public void testNomeNull() {
        Estado estado = new Estado();
        estado.setNome(null);
        assertNull(estado.getNome());
    }
    @Test
    public void testSiglaNull() {
        Estado estado = new Estado();
        estado.setSigla(null);
        assertNull(estado.getSigla());
    }

    @Test
    public void testNomeVazio() {
        Estado estado = new Estado();
        estado.setNome("");
        assertEquals("", estado.getNome());
    }
    @Test
    public void testSiglaVazio() {
        Estado estado = new Estado();
        estado.setSigla("");
        assertEquals("", estado.getSigla());
    }
    
}
