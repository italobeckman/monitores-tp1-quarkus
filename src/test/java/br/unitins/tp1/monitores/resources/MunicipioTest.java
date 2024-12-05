package br.unitins.tp1.monitores.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.monitores.model.Estado;
import br.unitins.tp1.monitores.model.Municipio;

public class MunicipioTest {

    @Test
    public void testNome() {
        Municipio municipio = new Municipio();
        municipio.setNome("Palmas");
        assertEquals("Palmas", municipio.getNome());
    }

    @Test
    public void testEstado() {
        Municipio municipio = new Municipio();
        Estado estado = new Estado();
        estado.setNome("Tocantins");
        municipio.setEstado(estado);
        assertEquals("Tocantins", municipio.getEstado().getNome());
    }

    @Test
    public void testNomeNull() {
        Municipio municipio = new Municipio();
        municipio.setNome(null);
        assertNull(municipio.getNome());
    }

    @Test
    public void testEstadoNull() {
        Municipio municipio = new Municipio();
        municipio.setEstado(null);
        assertNull(municipio.getEstado());
    }


    @Test
    public void testNomeVazio() {
        Municipio municipio = new Municipio();
        municipio.setNome("");
        assertEquals("", municipio.getNome());
    }


}
