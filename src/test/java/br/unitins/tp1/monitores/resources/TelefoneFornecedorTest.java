package br.unitins.tp1.monitores.resources;

import br.unitins.tp1.monitores.dto.fornecedor.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.monitores.service.TelefoneFornecedorService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class TelefoneFornecedorTest {

    @Inject
    TelefoneFornecedorService telefoneFornecedorService;

    @Test
    public void testFindAll() {
        given()
            .when().get("/telefonesfornecedores")
            .then()
                .statusCode(200);
    }

    @Test
    public void testCreate() {
        TelefoneFornecedorRequestDTO dto = 
            new TelefoneFornecedorRequestDTO("63", "333334443", 1L); // Assuming 1L is a valid fornecedor ID

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/telefonesfornecedors")
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "codigoArea", is("63"),
                      "numero", is("333334443"));

        // Clean up
        telefoneFornecedorService.delete(telefoneFornecedorService.findByNumero("333334443").getId());
    }

    @Test
    public void testUpdate() {
        // Create a new telefoneFornecedor to update
        TelefoneFornecedorRequestDTO createDto = 
            new TelefoneFornecedorRequestDTO("63", "333334443", 1L); // Assuming 1L is a valid fornecedor ID
        Long id = telefoneFornecedorService.create(createDto).getId();

        // Update the telefoneFornecedor
        TelefoneFornecedorRequestDTO updateDto = 
            new TelefoneFornecedorRequestDTO("64", "444445555", 1L); // Assuming 1L is a valid fornecedor ID

        given()
            .contentType(ContentType.JSON)
            .body(updateDto)
            .when()
                .put("/telefonesfornecedors/" + id)
            .then()
                .statusCode(200)
                .body("codigoArea", is("64"),
                      "numero", is("444445555"));

        // Clean up
        telefoneFornecedorService.delete(id);
    }

    @Test
    public void testDelete() {
        // Create a new telefoneFornecedor to delete
        TelefoneFornecedorRequestDTO createDto = 
            new TelefoneFornecedorRequestDTO("63", "333334443", 1L); // Assuming 1L is a valid fornecedor ID
        Long id = telefoneFornecedorService.create(createDto).getId();

        // Delete the telefoneFornecedor
        given()
            .when()
                .delete("/telefonesfornecedors/" + id)
            .then()
                .statusCode(204);
    }
}