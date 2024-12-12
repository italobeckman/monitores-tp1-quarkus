package br.unitins.tp1.monitores.resources;

import br.unitins.tp1.monitores.dto.fabricante.TelefoneFabricanteRequestDTO;
import br.unitins.tp1.monitores.service.TelefoneFabricanteService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
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
    @TestSecurity(user = "test", roles = { "Adm" })

    public void testFindAll() {
        given()
                .when().get("/telefonesfabricantes")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })

    public void testCreate() {
        TelefoneFabricanteRequestDTO dto = new TelefoneFabricanteRequestDTO("63", "333334443", 1L); // Assuming 1L is a
                                                                                                    // valid fabricante
                                                                                                    // ID

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/telefonesfabricantes/create")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "codigoArea", is("63"),
                        "numero", is("333334443"));

        // Clean up
        telefoneFabricanteService.delete(telefoneFabricanteService.findByNumero("333334443").getId());
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })

    public void testUpdate() {

        // Create a new telefoneFabricante to update
        TelefoneFabricanteRequestDTO createDto = new TelefoneFabricanteRequestDTO("63", "333334443", 1L); // Assuming 1L
                                                                                                          // is a valid
                                                                                                          // fabricante
                                                                                                          // ID
        Long id = telefoneFabricanteService.create(createDto).getId();

        // Update the telefoneFabricante
        TelefoneFabricanteRequestDTO updateDto = new TelefoneFabricanteRequestDTO("64", "444445555", 1L); // Assuming 1L
                                                                                                          // is a valid
                                                                                                          // fabricante
                                                                                                          // ID

        given()
                .contentType(ContentType.JSON)
                .body(updateDto)
                .when()
                .put("/telefonesfabricantes/update/id/" + id)
                .then()
                .statusCode(200)
                .body("codigoArea", is("64"),
                        "numero", is("444445555"));

        // Clean up
        telefoneFabricanteService.delete(id);
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })

    public void testDelete() {
        // Create a new telefoneFabricante to delete
        TelefoneFabricanteRequestDTO createDto = new TelefoneFabricanteRequestDTO("63", "333334443", 1L); 
                                                                                                         
        Long id = telefoneFabricanteService.create(createDto).getId();

        // Delete the telefoneFabricante
        given()
                .when()
                .delete("/telefonesfabricantes/delete/id/" + id)
                .then()
                .statusCode(204);
    }
}