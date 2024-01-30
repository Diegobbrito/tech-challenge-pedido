package pedido.bdd;

import br.com.fiap.pedido.api.dto.response.PedidoResponse;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import pedido.utils.PedidoHelper;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class DefinicaoPassos {

    private Response response;

    private PedidoResponse pedidoResponse;

    private String ENDPOINT_BASE = "http://localhost:8082/lanchonete/pedidos";

    @Quando("submeter um novo pedido")
    public PedidoResponse submeterUmNovoPedido() {
        var pedidoRequest = PedidoHelper.gerarPedidoRequest();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(pedidoRequest)
                .when().post(ENDPOINT_BASE);
        return response.then().extract().as(PedidoResponse.class);
    }
    @Então("o pedido é gerado com sucesso")
    public void pedidoGeradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/PedidoResponseSchema.json"));
    }

    @Dado("que um pedido já foi registrado")
    public void pedidoJaFoiRegistrado() {
        pedidoResponse = submeterUmNovoPedido();
    }
    @Quando("requisitar a busca de todos os pedidos")
    public void requisitarBuscaDeTodosOsPedidos() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_BASE);

    }
    @Então("os pedidos são exibidos com sucesso")
    public void pedidosSaoExibidosComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/PedidosResponseSchema.json"));
    }
    @Quando("requisitar a atualização de um pedido")
    public void requisitarAtualizaçãoDeUmPedido() {
        var pedidoRequest = PedidoHelper.gerarPedidoStatusRequest();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(pedidoRequest)
                .when()
                .patch(ENDPOINT_BASE + "/{pedidoId}" , 1);
    }
    @Então("o pedido é atualizado")
    public void pedidoAtualizado() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/PedidoResponseSchema.json"));
    }
}
