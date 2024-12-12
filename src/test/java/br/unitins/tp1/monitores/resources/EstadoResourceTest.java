package br.unitins.tp1.monitores.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import br.unitins.tp1.monitores.dto.estado.EstadoRequestDTO;
import br.unitins.tp1.monitores.model.Estado;
import br.unitins.tp1.monitores.resource.EstadoResource;
import br.unitins.tp1.monitores.service.EstadoService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EstadoResourceTest {

    @Inject
    public EstadoService estadoService;

 
    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testFindByNome() {
        given()
                .when().pathParam("nome", "mato grosso")
                .get("/estados/search/nome/{nome}")
                .then().statusCode(200)
                .body("nome", hasItem(is("mato grosso")));
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })

    public void testFindAll() {
        given()
                .when().get("/estados")
                .then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })

    public void testCreate() {
        EstadoRequestDTO dto = new EstadoRequestDTO("Carlao", "CL");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/estados/create")
                .then()
                .statusCode(201)
                .body("nome", is("Carlao"),
                        "sigla", is("CL"));
        estadoService.delete(estadoService.findByNome("Carlao").getFirst().getId());
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testUpdate() {
        EstadoRequestDTO dto = new EstadoRequestDTO("acre", "ac");

        long id = estadoService.create(dto).getId();

        EstadoRequestDTO novoDTO = new EstadoRequestDTO("Paraná", "PR");

        given()
                .contentType(ContentType.JSON)
                .body(novoDTO)
                .when()
                .put("/estados/update/id/" + id)
                .then()
                .statusCode(204);

        Estado estado = estadoService.findById(id);

        assertEquals(estado.getNome(), "Paraná");
        assertEquals(estado.getSigla(), "PR");

        estadoService.delete(id);

    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    @TestHTTPEndpoint(EstadoResource.class)
    public void testFindAll2() {
        given()
                .when().get()
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(1));
    }

}