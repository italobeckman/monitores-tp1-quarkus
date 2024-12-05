package br.unitins.tp1.monitores.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.monitores.model.Fabricante;
import br.unitins.tp1.monitores.model.Monitor;
import br.unitins.tp1.monitores.model.TamanhoMonitor;

public class MonitorTest {

    @Test
    public void testNome() {
        Monitor monitor = new Monitor();
        monitor.setNome("Monitor Gamer");
        assertEquals("Monitor Gamer", monitor.getNome());
    }

    @Test
    public void testMarca() {
        Monitor monitor = new Monitor();
        monitor.setMarca("Samsung");
        assertEquals("Samsung", monitor.getMarca());
    }

    @Test
    public void testModelo() {
        Monitor monitor = new Monitor();
        monitor.setModelo("Odyssey G9");
        assertEquals("Odyssey G9", monitor.getModelo());
    }

    @Test
    public void testPreco() {
        Monitor monitor = new Monitor();
        monitor.setPreco(1500.99);
        assertEquals(1500.99, monitor.getPreco());
    }

    @Test
    public void testTaxaAtualizacao() {
        Monitor monitor = new Monitor();
        monitor.setTaxaAtualizacao("244");
        assertEquals("244", monitor.getTaxaAtualizacao());
    }

    @Test
    public void testTempoResposta() {
        Monitor monitor = new Monitor();
        monitor.setTempoResposta("1ms");
        assertEquals("1ms", monitor.getTempoResposta());
    }

    @Test
    public void testAnoLancamento() {
        Monitor monitor = new Monitor();
        monitor.setAnoLancamento(2023);
        assertEquals(2023, monitor.getAnoLancamento());
    }


    @Test
    public void testListaImagem() {
        Monitor monitor = new Monitor();
        List<String> imagens = new ArrayList<>();
        imagens.add("imagem1.jpg");
        imagens.add("imagem2.png");
        monitor.setListaImagem(imagens);
        assertEquals(2, monitor.getListaImagem().size());
        assertEquals("imagem1.jpg", monitor.getListaImagem().get(0));

    }

    @Test
    public void testTamanhoMonitor() {
        Monitor monitor = new Monitor();
        TamanhoMonitor tamanhoMonitor = new TamanhoMonitor();
        tamanhoMonitor.setTamanho(27.0);
        monitor.setTamanhoMonitor(tamanhoMonitor);
        assertEquals(27.0, monitor.getTamanhoMonitor().getTamanho());

    }


    @Test
    public void testFabricante() {
        Monitor monitor = new Monitor();
        Fabricante fabricante = new Fabricante();
        fabricante.setNome("Samsung");
        monitor.setFabricante(fabricante);
        assertEquals("Samsung", monitor.getFabricante().getNome());

    }
     @Test
    public void testNomeNull() {
        Monitor monitor = new Monitor();
        monitor.setNome(null);
        assertNull(monitor.getNome());
    }

     @Test
    public void testMarcaNull() {
        Monitor monitor = new Monitor();
        monitor.setMarca(null);
        assertNull(monitor.getMarca());
    }


     @Test
    public void testModeloNull() {
        Monitor monitor = new Monitor();
        monitor.setModelo(null);
        assertNull(monitor.getModelo());
    }



}
