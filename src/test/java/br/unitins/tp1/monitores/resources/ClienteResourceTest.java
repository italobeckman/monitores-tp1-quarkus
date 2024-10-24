package br.unitins.tp1.monitores.resources;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.monitores.dto.pessoa.ClienteRequestDTO;
import br.unitins.tp1.monitores.model.Cliente;
import br.unitins.tp1.monitores.model.Sexo;
import br.unitins.tp1.monitores.service.ClienteService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ClienteResourceTest {

    @Inject
    public ClienteService clienteService;
   
    @Test
    public void testFindAll(){
        given()
            .when().get("/clientes")
            .then().statusCode(200);
    }


    @Test
    public void testCreate(){
        ClienteRequestDTO dto = 
            new ClienteRequestDTO("Carlos Henrique", "333", 2);
        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/clientes")
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("Carlos Henrique"),
                      "cpf", is("333"));
        clienteService.delete(clienteService.findByCpf("333").getId());
    }

    @Test
    public void testUpdate() {
        ClienteRequestDTO dto = 
        new ClienteRequestDTO("teste", "000.000.000-00", 1);
        long id = clienteService.create(dto).getId();
        ClienteRequestDTO novoDto = 
            new ClienteRequestDTO("Leandra Cavina", "000.000.000-00", 1);

        given()
        .contentType(ContentType.JSON)
        .body(novoDto)
        .when()
            .put("/clientes/"+id)
        .then()
            .statusCode(204);

        Cliente cliente = clienteService.findById(id);

        assertEquals(cliente.getNome(), "Leandra Cavina");
        assertEquals(cliente.getSexo(), Sexo.FEMININO);
        clienteService.delete(clienteService.findByCpf("000.000.000-00").getId());

    }

    @Test
    public void testDelete() {
        ClienteRequestDTO dto = 
        new ClienteRequestDTO("teste", "000.000.000-00", 1);
        
        long id = clienteService.create(dto).getId();

        given()
        .when()
            .delete("/clientes/"+id)
        .then()
            .statusCode(204);
        Cliente cliente = clienteService.findById(id);
        assertNull(cliente);
    
    }
    



}