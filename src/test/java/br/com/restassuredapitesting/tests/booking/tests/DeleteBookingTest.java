package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;


@Feature("Reservas")
public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Excluir uma reserva com sucesso")
    public void excluirReservaSucesso() throws Exception{
        deleteBookingRequest.deletarReserva(29).then()
                .statusCode(201)
                .time(lessThan(3L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Excluir uma reserva que não existe")
    public void excluirReservaNaoExiste() throws Exception{
        deleteBookingRequest.deletarReserva(1).then()
                .statusCode(405)
                .time(lessThan(3L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Excluir uma reserva sem autorização")
    public void excluirReservaSemAutorizacao() throws Exception{
        deleteBookingRequest.deletarReservaSemAuth(8).then()
                .statusCode(403)
                .time(lessThan(3L), TimeUnit.SECONDS);
    }




}
