package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.request.PostAuthRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Step("Deletar reserva")
    public Response deletarReserva(int id) {
        return given()
                .header("Cookie", postAuthRequest.getToken())
                .when()
                .delete("booking/" + id);
    }

    @Step("Deletar reserva sem auth")
    public Response deletarReservaSemAuth(int id) {
        return given()
                .when()
                .delete("booking/" + id);
    }
}
