package br.unitins.tp1.monitores.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.monitores.dto.municipio.MunicipioRequestDTO;
import br.unitins.tp1.monitores.model.Municipio;
import br.unitins.tp1.monitores.service.MunicipioService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class MunicipioResourceTest {

    @Inject
    public MunicipioService municipioService;

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testFindById() {
        given()
                .when().get("/municipios/search/id/1")
                .then().statusCode(200)
                .body("id", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testFindByNome() {
        given()
                .when().pathParam("nome", "Araguaina")
                .get("/municipios/search/nome/{nome}")
                .then().statusCode(200)
                .body("nome", hasItem(is("Araguaina")));
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testFindAll() {
        given()
                .when().get("/municipios")
                .then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testCreate() {
        MunicipioRequestDTO dto = new MunicipioRequestDTO("Guarai", 1l);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/municipios/create")
                .then()
                .statusCode(201)
                .body("nome", is("Guarai"),
                        "estado.nome", is("tocantins"),
                        "estado.sigla", is("to"));

        // removendo o dado que foi inserido
        municipioService.delete(municipioService.findByNome("Guarai").getFirst().getId());
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testUpdate() {
        MunicipioRequestDTO dto = new MunicipioRequestDTO("Augustinopolis", 1l);
        long id = municipioService.create(dto).getId();

        MunicipioRequestDTO novoDto = new MunicipioRequestDTO("Palmas", 1l);

        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/municipios/update/id/" + id)
                .then()
                .statusCode(204);

        Municipio municipio = municipioService.findById(id);

        assertEquals(municipio.getNome(), "Palmas");

        municipioService.delete(id);

    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    @Transactional
    public void testDelete() {
        MunicipioRequestDTO dto = new MunicipioRequestDTO("Jacarezin Pagua", 1L);
        Long id = municipioService.create(dto).getId();

        given()
                .when()
                .delete("/municipios/delete/id/" + id)
                .then().statusCode(204);

        Municipio municipio = municipioService.findById(id);
        assertNull(municipio);

    }

}