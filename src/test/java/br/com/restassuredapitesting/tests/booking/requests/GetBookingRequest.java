package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Buscar todas reservas")
    public Response allBookings() {
        return given()
                .header("Content-Type","application/json")
                .when()
                .get("booking");
    }

    @Step("Buscar reserva especifica")
    public Response specificIdBooking() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking/2");
    }

    @Step("Buscar reserva especifica")
    public Response specificBooking() {
        return given()
                .header("Content-Type","application/json")
                .when()
                .get("booking/2");
    }

    @Step("Buscar reserva pelo filtro firstname")
    public Response firstNameBooking() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?firstname=sally");
    }

    @Step("Buscar reserva pelo filtro lastname")
    public Response lastNameBooking() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?lastname=brown");
    }

    @Step("Buscar reserva pela data de checkin")
    public Response checkInBooking() {
        return given()
                .header("Content-Type","application/json")
                .when()
                .get("booking?checkin=2014-03-13");
    }

    @Step("Buscar reserva pela data de checkout")
    public Response checkOutBooking() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkout=2014-10-23");
    }

    @Step("Buscar reserva pela data de checkin e checkout")
    public Response checkInOutBooking() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkin=2013-02-23&checkout=2014-10-23");
    }

    @Step
    public Response nameInOutBooking() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?firstname=sally&lastname=brown&checkin=2014-03-13&checkout=2014-05-21");
                //Já que o parametro name não está documentado, foi realizado o teste utilizando o firstname e lastname junto das datas de checkin e checkout.
    }

    @Step
    public Response urlQuebrada() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkin=2014-03-13&checkout=21-05-2014");
                //Foi invertida a data CCYY-MM-DD para DD-MM-CCYY para "quebrar" a url
    }



}
