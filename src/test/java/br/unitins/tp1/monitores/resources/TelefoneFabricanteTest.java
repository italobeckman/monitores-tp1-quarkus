package br.unitins.tp1.monitores.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.monitores.model.TelefoneFabricante;

public class TelefoneFabricanteTest {

    @Test
    public void testCodigoArea() {
        TelefoneFabricante telefone = new TelefoneFabricante();
        telefone.setCodigoArea("63");
        assertEquals("63", telefone.getCodigoArea());
    }

    @Test
    public void testNumero() {
        TelefoneFabricante telefone = new TelefoneFabricante();
        telefone.setNumero("999999999");
        assertEquals("999999999", telefone.getNumero());
    }

    @Test
    public void testCodigoAreaNull() {
        TelefoneFabricante telefone = new TelefoneFabricante();
        telefone.setCodigoArea(null);
        assertNull(telefone.getCodigoArea());
    }

    @Test
    public void testNumeroNull() {
        TelefoneFabricante telefone = new TelefoneFabricante();
        telefone.setNumero(null);
        assertNull(telefone.getNumero());
    }

    @Test
    public void testCodigoAreaVazio() {
        TelefoneFabricante telefone = new TelefoneFabricante();
        telefone.setCodigoArea("");
        assertEquals("", telefone.getCodigoArea());
    }

    @Test
    public void testNumeroVazio() {
        TelefoneFabricante telefone = new TelefoneFabricante();
        telefone.setNumero("");
        assertEquals("", telefone.getNumero());
    }
}
