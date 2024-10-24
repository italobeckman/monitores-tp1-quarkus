package br.unitins.tp1.monitores.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.monitores.dto.municipio.MunicipioRequestDTO;
import br.unitins.tp1.monitores.model.Municipio;
import br.unitins.tp1.monitores.resource.MunicipioResource;
import br.unitins.tp1.monitores.service.MunicipioService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class MunicipioResourceTest {
    
    @Inject
    public MunicipioService municipioService;

    @Test
    public void testFindById() {
        given()
            .when().get("/municipios/1")
            .then().statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void testFindByNome() {
        given()
            .when().pathParam("nome", "Palmas")
            .get("/municipios/search/{nome}")
            .then().statusCode(200)
            .body("nome", hasItem(is("Palmas")));
    }

    @Test
    public void testFindAll() {
        given()
            .when().get("/municipios")
            .then().statusCode(200);
    }

    @Test
    public void testCreate() {
        MunicipioRequestDTO dto = new MunicipioRequestDTO("Guarai", 1l);

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/cidades")
            .then()
                .statusCode(201)
                .body("nome", is("Tocantinopolis"),
                      "estado.nome", is("Tocantins"),
                      "estado.sigla", is("TO"));
                
        // removendo o dado que foi inserido
        municipioService.delete(municipioService.findByNome("Tocantinopolis").getFirst().getId());
    }

    @Test
    public void testUpdate() {
        MunicipioRequestDTO dto = new MunicipioRequestDTO("Augustinopolis", 1l);
        long id = municipioService.create(dto).getId();

        MunicipioRequestDTO novoDto = new MunicipioRequestDTO("Palmas", 1l);

        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/cidades/" + id)
            .then()
                .statusCode(204);
        
        Municipio municipio = municipioService.findById(id);

        assertEquals(municipio.getNome(), "Maurilandia");

        municipioService.delete(id);

    }  

    @Test
    public void testDelete() {
        MunicipioRequestDTO dto = new MunicipioRequestDTO("Muricilandia", 1l);
        Long id = municipioService.create(dto).getId();
    
        given()
            .when()
                .delete("/cidades/" + id)
            .then().statusCode(204);

        Municipio municipio = municipioService.findById(id);
        assertNull(municipio);

    }

    @Test
    @TestHTTPEndpoint(MunicipioResource.class)
    public void testFindAll2(){
        given()
            .when().get()
            .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(1));
    }
    
}