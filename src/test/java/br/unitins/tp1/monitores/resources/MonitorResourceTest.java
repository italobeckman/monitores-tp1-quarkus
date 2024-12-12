package br.unitins.tp1.monitores.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import br.unitins.tp1.monitores.dto.monitor.MonitorRequestDTO;
import br.unitins.tp1.monitores.service.MonitorService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class MonitorResourceTest {

    @Inject
    MonitorService monitorService;

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testFindAll() {
        given()
                .when().get("/monitores")
                .then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })

    public void testCreate() {
        MonitorRequestDTO dto = new MonitorRequestDTO(
                "Monitor 3",
                "Marca 3",
                "Modelo 3",
                4000.00,
                "100Hz",
                "4ms",
                2021,
                1L,
                2L);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/monitores/create")
                .then()
                .statusCode(204)
                .body("nome", is("Monitor 1"),
                        "modelo", is("Modelo 1"),
                        "marca", is("Marca 1"),
                        "taxaAtualizacao", is("60Hz"),
                        "tempoResposta", is("5ms"),
                        "peso", is("5kg"),
                        "preco", is(1500.00),
                        "anoLancamento", is("2022-01-01"),
                        "fabricante", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })

    public void testUpdate() {
        MonitorRequestDTO createDto = new MonitorRequestDTO(
                "Monitor 2",
                "Marca 2",
                "Modelo 2",
                2000.00,
                "75Hz",
                "4ms",
                2020,
                1L,
                1L);
        Long id = monitorService.create(createDto).getId();

        MonitorRequestDTO updateDto = new MonitorRequestDTO(
                "Monitor 3",
                "Marca 3",
                "Modelo 3",
                4000.00,
                "100Hz",
                "4ms",
                2021,
                1L,
                2L);

        given()
                .contentType(ContentType.JSON)
                .body(updateDto)
                .when()
                .put("/monitores/update/id/" + id)
                .then()
                .statusCode(200)
                .body("nome", is("Monitor 3"), // Atualizado para "Monitor 3"
                        "modelo", is("Modelo 3"), // Atualizado para "Modelo 3"
                        "marca", is("Marca 3"), // Atualizado para "Marca 3"
                        "modelo", is("Modelo 3"), // Atualizado para "Modelo 3"
                        "preco", is(4000.00), // Atualizado para 4000.00
                        "taxaAtualizacao", is("100Hz"), // Atualizado para "100Hz"
                        "tempoResposta", is("4ms"), // Mantido como "4ms"
                        "anoLancamento", is(2021), // Atualizado para 2021
                        "idFabricante", is(1),
                        "idTamanhoMonitor", is(2L)); // Atualizado para 2L
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testDelete() {
        MonitorRequestDTO createDto = new MonitorRequestDTO(
                "Monitor 3",
                "Marca 3",
                "Modelo 3",
                4000.00,
                "100Hz",
                "4ms",
                2021,
                1L,
                2L);
        Long id = monitorService.create(createDto).getId();

        given()
                .when()
                .delete("/monitores/delete/id/" + id)
                .then()
                .statusCode(204);
    }
}