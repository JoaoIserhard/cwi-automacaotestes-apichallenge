package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HealthCheckRequest {

    @Step("Healthcheck api")
    public Response HealthCheckApi() {
        return given()
                .when()
                .get("ping");
    }
}
