package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

@Feature("Reservas")
public class PostBookingRequest{

    @Step("Criar reserva invalida")
    public Response criarReservaInvalida(JSONObject invalidPayload) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(invalidPayload.toString())
                .post("booking/");
    }

    @Step("Criar reserva em sequencia01")
    public Response criarReserva01(JSONObject payload01) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(payload01.toString())
                .post("booking/");
    }

    @Step("Criar reserva em sequencia02")
    public Response criarReserva02(JSONObject payload02) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(payload02.toString())
                .post("booking/");
    }

    @Step("Criar reserva com mais parametros")
    public Response criarReservaMaisParametros(JSONObject extrapayload) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(extrapayload.toString())
                .post("booking/");
    }

    @Step("Validar retorno 418 quando o header Accept for invalido")
    public Response validarRetorno418(JSONObject extrapayload) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/plankton")
                .when()
                .body(extrapayload.toString())
                .post("booking/");
    }

}
