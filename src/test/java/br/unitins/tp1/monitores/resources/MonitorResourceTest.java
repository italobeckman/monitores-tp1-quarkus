package br.unitins.tp1.monitores.resources;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;
import br.unitins.tp1.monitores.service.MonitorService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class MonitorResourceTest {

    @Inject
    MonitorService monitorService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/monitores")
                .then().statusCode(200);
    }
}
/* 
    @Test
    public void testCreate() {
        MonitorRequestDTO dto = new MonitorRequestDTO(
            "Monitor 1", 
            "Modelo 1", 
            "Marca 1", 
            "60Hz", 
            "5ms", 
            "5kg", 
            1500.00, 
            LocalDate.of(2022, 1, 1), 
            1L
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/monitores")
            .then()
                .statusCode(201)
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
    public void testUpdate() {
        MonitorRequestDTO createDto = new MonitorRequestDTO(
            "Monitor 2", 
            "Modelo 2", 
            "Marca 2", 
            "75Hz", 
            "4ms", 
            "4kg", 
            2000.00, 
            LocalDate.of(2021, 1, 1), 
            2L
        );
        Long id = monitorService.create(createDto).getId();

        MonitorRequestDTO updateDto = new MonitorRequestDTO(
            "Monitor 2 Updated", 
            "Modelo 2 Updated", 
            "Marca 2", 
            "75Hz", 
            "4ms", 
            "4kg", 
            2000.00, 
            LocalDate.of(2021, 1, 1), 
            2L
        );

        given()
            .contentType(ContentType.JSON)
            .body(updateDto)
            .when()
                .put("/monitores/" + id)
            .then()
                .statusCode(200)
                .body("nome", is("Monitor 2 Updated"),
                      "modelo", is("Modelo 2 Updated"),
                      "marca", is("Marca 2"),
                      "taxaAtualizacao", is("75Hz"),
                      "tempoResposta", is("4ms"),
                      "peso", is("4kg"),
                      "preco", is(2000.00),
                      "anoLancamento", is("2021-01-01"),
                      "fabricante", is(2));
    }

    @Test
    public void testDelete() {
        MonitorRequestDTO createDto = new MonitorRequestDTO(
            "Monitor 3", 
            "Modelo 3", 
            "Marca 3", 
            "144Hz", 
            "1ms", 
            "3kg", 
            2500.00, 
            LocalDate.of(2020, 1, 1), 
            3L
        );
        Long id = monitorService.create(createDto).getId();

        given()
            .when()
                .delete("/monitores/" + id)
            .then()
                .statusCode(204);
    }
}
    */