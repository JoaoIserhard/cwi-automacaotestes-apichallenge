package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(Acceptance.class)
    @DisplayName("Criar uma nova reserva")
    public void criarUmaNovaReserva() throws Exception {
        postBookingRequest.criarReserva01(Utils.validPayLoadBooking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 500 quando o payload da reserva estiver inválido")
    public void validar500ReservaInvalida() throws Exception{
        postBookingRequest.criarReservaInvalida(Utils.invalidPayLoadBooking()).then()
                .statusCode(500)
                .time(lessThan(3L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(E2e.class)
    @DisplayName("Validar a criação de mais de um livro em sequencia")
    public void validarCriacaoLivroSequencia() throws Exception {
        postBookingRequest.criarReserva01(Utils.validPayLoadBooking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS);
        postBookingRequest.criarReserva02(Utils.validPayLoad2Booking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS);;
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void reservaMaisParametros() throws Exception {
        postBookingRequest.criarReservaMaisParametros(Utils.extraPayLoadBooking()).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS);;
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 418 quando o header Accept for invalido ")
    public void retorno418() throws Exception {
        postBookingRequest.validarRetorno418(Utils.extraPayLoadBooking()).then()
                .statusCode(418)
                .time(lessThan(3L), TimeUnit.SECONDS);
    }

}
