package br.unitins.tp1.monitores.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.monitores.model.TelefoneFornecedor;

public class TelefoneFornecedorTest {

    @Test
    public void testCodigoArea() {
        TelefoneFornecedor telefone = new TelefoneFornecedor();
        telefone.setCodigoArea("63");
        assertEquals("63", telefone.getCodigoArea());
    }

    @Test
    public void testNumero() {
        TelefoneFornecedor telefone = new TelefoneFornecedor();
        telefone.setNumero("999999999");
        assertEquals("999999999", telefone.getNumero());
    }

    @Test
    public void testCodigoAreaNull() {
        TelefoneFornecedor telefone = new TelefoneFornecedor();
        telefone.setCodigoArea(null);
        assertNull(telefone.getCodigoArea());
    }

    @Test
    public void testNumeroNull() {
        TelefoneFornecedor telefone = new TelefoneFornecedor();
        telefone.setNumero(null);
        assertNull(telefone.getNumero());
    }

    @Test
    public void testCodigoAreaVazio() {
        TelefoneFornecedor telefone = new TelefoneFornecedor();
        telefone.setCodigoArea("");
        assertEquals("", telefone.getCodigoArea());
    }

    @Test
    public void testNumeroVazio() {
        TelefoneFornecedor telefone = new TelefoneFornecedor();
        telefone.setNumero("");
        assertEquals("", telefone.getNumero());
    }
}
