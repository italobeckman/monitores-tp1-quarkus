package br.unitins.tp1.monitores.resources;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import java.util.List;
import br.unitins.tp1.monitores.dto.fornecedor.FornecedorRequestDTO;
import br.unitins.tp1.monitores.dto.fornecedor.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.monitores.service.FornecedorService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class FornecedorResourceTest {

    @Inject
    FornecedorService fornecedorService;

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })

    public void testFindAll() {
        given()
                .when().get("/fornecedors")
                .then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testCreate() {
        FornecedorRequestDTO dto = new FornecedorRequestDTO("Dell", "12123211", "teste@gmail.com",
                List.of(new TelefoneFornecedorRequestDTO("63", "3231-1338", 0L))
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/fornecedors/create")
            .then()
                .statusCode(201)
                .body("nome", is("Dell"),
                      "cnpj", is("12123211"),
                      "email", is("teste@gmail.com"));

      
        fornecedorService.delete(fornecedorService.findByNome("Dell").get(0).getId());
    }

    @Test
    @TestSecurity(user = "test", roles = { "Adm" })
    public void testUpdate() {

        FornecedorRequestDTO createDto = new FornecedorRequestDTO("Acre", "12345678", "acre@gmail.com",
        List.of(new TelefoneFornecedorRequestDTO("63", "3231-1338", 0L))
        );
        Long id = fornecedorService.create(createDto).getId();


        FornecedorRequestDTO updateDto = new FornecedorRequestDTO("Paraná", "87654321", "parana@gmail.com",
        List.of(new TelefoneFornecedorRequestDTO("63", "3231-1338", 0L))
        );

        given()
            .contentType(ContentType.JSON)
            .body(updateDto)
            .when()
                .put("/fornecedors/update/id" + id)
            .then()
                .statusCode(200)
                .body("nome", is("Paraná"),
                      "cnpj", is("87654321"),
                      "email", is("parana@gmail.com"),
                      "codigoArea", is("63"),  
                      "numero", is("3231-1338"));

    
        fornecedorService.delete(id);
    }

    @Test
        @TestSecurity(user = "test", roles = { "Adm" })
    public void testDelete() {
        
        FornecedorRequestDTO createDto = new FornecedorRequestDTO(
            "Acre", 
            "12345678", 
            "acre@gmail.com",
        List.of(new TelefoneFornecedorRequestDTO("63", "3231-1338", 0L))
        );
        Long id = fornecedorService.create(createDto).getId();

        
        given()
            .when()
                .delete("/fornecedors/delete/id/" + id)
            .then()
                .statusCode(204);
    }
}