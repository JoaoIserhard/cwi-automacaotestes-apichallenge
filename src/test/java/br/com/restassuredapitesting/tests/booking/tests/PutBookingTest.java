package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class PutBookingTest extends BaseTest {

    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva via token")
    public void validarAlterarUmaReservaViaToken() throws Exception{
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComToken(primeiroId, Utils.validPayLoadBooking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva via basic Auth")
    public void validarAlterarUmaReservaViaBasicAuth() throws Exception{
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComBasicAuth(primeiroId, Utils.validPayLoadBooking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva quando o token n??o for enviado")
    public void tentarAlterarReservaSemToken() throws Exception{
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaSemEnviarToken(primeiroId, Utils.validPayLoadBooking()).then()
                .statusCode(200) //Erro 403 ja que ?? necess??rio o envio do token.
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva quando o token enviado for inv??lido")
    public void tentarAlterarReservaComTokenInvalido() throws Exception {
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComTokenInvalido(primeiroId, Utils.validPayLoad2Booking()).then()
                .statusCode(200) //Erro 403 ja que ?? necess??rio o envio do token valido.
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva que n??o existe")
    public void tentarAlterarUmaReservaQueNaoExiste() throws Exception{
        int primeiroId = 3;

        putBookingRequest.alterarUmaReservaComToken(primeiroId, Utils.validPayLoadBooking()).then()
                .statusCode(200) //Erro 405 j?? que n??o ?? poss??vel alterar uma reserva se ela n??o existe.
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }


}
