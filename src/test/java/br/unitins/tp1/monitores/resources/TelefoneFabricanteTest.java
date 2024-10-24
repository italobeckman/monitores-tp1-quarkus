package br.unitins.tp1.monitores.resources;
import br.unitins.tp1.monitores.dto.fabricante.TelefoneFabricanteRequestDTO;
import br.unitins.tp1.monitores.service.TelefoneFabricanteService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class TelefoneFabricanteTest {

    @Inject
    TelefoneFabricanteService telefoneFabricanteService;

    @Test
    public void testFindAll() {
        given()
            .when().get("/telefonesfabricantes")
            .then()
                .statusCode(200);
    }

    @Test
    public void testCreate() {
        TelefoneFabricanteRequestDTO dto = 
            new TelefoneFabricanteRequestDTO("63", "333334443", 1L); // Assuming 1L is a valid fabricante ID

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/telefonesfabricantes")
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "codigoArea", is("63"),
                      "numero", is("333334443"));

        // Clean up
        telefoneFabricanteService.delete(telefoneFabricanteService.findByNumero("333334443").getId());
    }

    @Test
    public void testUpdate() {
        // Create a new telefoneFabricante to update
        TelefoneFabricanteRequestDTO createDto = 
            new TelefoneFabricanteRequestDTO("63", "333334443", 1L); // Assuming 1L is a valid fabricante ID
        Long id = telefoneFabricanteService.create(createDto).getId();

        // Update the telefoneFabricante
        TelefoneFabricanteRequestDTO updateDto = 
            new TelefoneFabricanteRequestDTO("64", "444445555", 1L); // Assuming 1L is a valid fabricante ID

        given()
            .contentType(ContentType.JSON)
            .body(updateDto)
            .when()
                .put("/telefonesfabricantes/" + id)
            .then()
                .statusCode(200)
                .body("codigoArea", is("64"),
                      "numero", is("444445555"));

        // Clean up
        telefoneFabricanteService.delete(id);
    }

    @Test
    public void testDelete() {
        // Create a new telefoneFabricante to delete
        TelefoneFabricanteRequestDTO createDto = 
            new TelefoneFabricanteRequestDTO("63", "333334443", 1L); // Assuming 1L is a valid fabricante ID
        Long id = telefoneFabricanteService.create(createDto).getId();

        // Delete the telefoneFabricante
        given()
            .when()
                .delete("/telefonesfabricantes/" + id)
            .then()
                .statusCode(204);
    }
}