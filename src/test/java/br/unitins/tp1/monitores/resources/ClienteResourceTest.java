package br.unitins.tp1.monitores.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;

import br.unitins.tp1.monitores.service.ClienteService;
import br.unitins.tp1.monitores.service.JwtService;
import br.unitins.tp1.monitores.service.endereco.EnderecoUserService;
import br.unitins.tp1.monitores.service.user.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;

@QuarkusTest
public class ClienteResourceTest {

        @Inject
        public ClienteService clienteService;
        @Inject
        public UsuarioService usuarioService;
        @Inject
        JwtService jwtService;
        @Inject
        EnderecoUserService enderecoUserService;

        @Test
        @TestSecurity(user = "test", roles = { "Adm" })
        public void testFindById() {
                given()
                                .when().get("/clientes/search/id/1")
                                .then().statusCode(200)
                                .body("id", is(1));
        }

        @Test
        @TestSecurity(user = "test", roles = { "Adm" })
        public void testFindByNome() {

                given()
                                .when().pathParam("nome", "Janio")
                                .get("/clientes/search/nome/{nome}")
                                .then().statusCode(200)
                                .body("nome", hasItem(is("Janio")));
        }

        @Test
        @TestSecurity(user = "test", roles = { "Adm" })
        public void testFindAll() {
                given()
                                .when().get("/clientes")
                                .then().statusCode(200);
        }

        @Test
        @TestSecurity(user = "test", roles = { "Adm" })
        public void testDelete() {
                given()
                                .when().delete("/clientes/delete/id/1")
                                .then().statusCode(204);
        }

}