package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.Contract;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das Reservas")
    public void validarIdsDasReservas() throws Exception{
        getBookingRequest.allBookings().then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    GetBookingRequest getBookingIdResquest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar ID reserva especifica")
    public void validarIdReserva() throws Exception {

        String nId = "6";

        getBookingIdResquest.specificIdBooking().then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro firstname")
    public void firstNameBooking() throws Exception {

        getBookingRequest.firstNameBooking().then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS)
                .body(
                    matchesJsonSchema(
                            new File(
                                    Utils.getContractsBasePath("booking", "bookings")
                            )
                    )
                );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro lastname")
    public void lastNameBooking() throws Exception {
        getBookingRequest.lastNameBooking().then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS)
                .body(
                    matchesJsonSchema(
                            new File(
                                    Utils.getContractsBasePath("booking", "bookings")
                            )
                    )
                );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin") //Foi observado que quando não há reservas na data especificada, são exibidas todas as IDs de reservas. Gerar reporte a equipe.
    public void checkInBooking() throws Exception {
        getBookingRequest.checkInBooking().then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS)
                .body(
                    matchesJsonSchema(
                            new File(
                                    Utils.getContractsBasePath("booking", "bookings")
                            )
                    )
                );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout")
    public void checkOutBooking() throws Exception {
        getBookingRequest.checkOutBooking().then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS)
                .body(
                    matchesJsonSchema(
                            new File(
                                    Utils.getContractsBasePath("booking", "bookings")
                            )
                    )
                );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin e checkout")
    public void checkInOutBooking() throws Exception {
        getBookingRequest.checkInOutBooking().then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS)
                .body(
                    matchesJsonSchema(
                            new File(
                                    Utils.getContractsBasePath("booking", "bookings")
                            )
                    )
                );
    }

    @Test //Já que o parametro name não está documentado, foi realizado o teste utilizando o firstname e lastname junto das datas de checkin e checkout. Recomendação: mapear variavel name como firstname&lastname.
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro name, checkin and checkout date")
    public void nameInOutBooking() throws Exception {
        getBookingRequest.nameInOutBooking().then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS)
                .body(
                    matchesJsonSchema(
                            new File(
                                    Utils.getContractsBasePath("booking", "bookings")
                            )
                    )
                );
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato do retorno da lista de reservas")
    public void garantirContratoListaReserva() throws Exception {
        getBookingRequest.allBookings().then()
                .statusCode(200)
                .assertThat()
                .body(
                    matchesJsonSchema(
                        new File(
                               Utils.getContractsBasePath("booking", "bookings")
                        )
                    )
                );
    }
    @Test //Neste teste foi encontrado um bug, uma vez que a informação 'additionalneeds' está desaparecida, sendo que na documentação da API ela deveria estar presente. Reportar a equipe. Removendo a variavel additionalneeds do contrato json, o teste funciona.
    @Severity(SeverityLevel.NORMAL)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato do retorno de uma reserva específica")
    public void garantirContratoEspecifica() throws Exception {
        getBookingRequest.specificBooking().then().log().all()
                .statusCode(200)
                .assertThat()
                .body(
                    matchesJsonSchema(
                            new File(
                                    Utils.getContractsBasePath("booking","specific")
                            )
                    )
                );
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(E2e.class)
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public void erro500() throws Exception {
        getBookingRequest.urlQuebrada().then()
                .statusCode(500)
                .time(lessThan(5L), TimeUnit.SECONDS);
    }


}
