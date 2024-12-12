package br.unitins.tp1.monitores.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import java.util.List;
import br.unitins.tp1.monitores.dto.fabricante.FabricanteRequestDTO;
import br.unitins.tp1.monitores.dto.fabricante.TelefoneFabricanteRequestDTO;
import br.unitins.tp1.monitores.service.FabricanteService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class FabricanteResourceTest {

    @Inject
    FabricanteService fabricanteService;

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })

    public void testFindAll() {
        given()
                .when().get("/fabricantes")
                .then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testCreate() {
        FabricanteRequestDTO dto = new FabricanteRequestDTO("Dell", "12123211", "teste@gmail.com",
                List.of(new TelefoneFabricanteRequestDTO("63", "3231-1338", 0L)));

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/fabricantes/create")
                .then()
                .statusCode(201)
                .body("nome", is("Dell"),
                        "cnpj", is("12123211"),
                        "email", is("teste@gmail.com"));

        fabricanteService.delete(fabricanteService.findByNome("Dell").get(0).getId());
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testUpdate() {

        FabricanteRequestDTO createDto = new FabricanteRequestDTO("Acre", "12345678", "acre@gmail.com",
                List.of(new TelefoneFabricanteRequestDTO("63", "3231-1338", 0L)));
        Long id = fabricanteService.create(createDto).getId();

        FabricanteRequestDTO updateDto = new FabricanteRequestDTO("Paraná", "87654321", "parana@gmail.com",
                List.of(new TelefoneFabricanteRequestDTO("63", "3231-1338", 0L)));

        given()
                .contentType(ContentType.JSON)
                .body(updateDto)
                .when()
                .put("/fabricantes/update/id" + id)
                .then()
                .statusCode(200)
                .body("nome", is("Paraná"),
                        "cnpj", is("87654321"),
                        "email", is("parana@gmail.com"),
                        "codigoArea", is("63"),
                        "numero", is("3231-1338"));

        fabricanteService.delete(id);
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testDelete() {

        FabricanteRequestDTO createDto = new FabricanteRequestDTO(
                "Acre",
                "12345678",
                "acre@gmail.com",
                List.of(new TelefoneFabricanteRequestDTO("63", "3231-1338", 0L)));
        Long id = fabricanteService.create(createDto).getId();

        given()
                .when()
                .delete("/fabricantes/delete/id/" + id)
                .then()
                .statusCode(204);
    }
}