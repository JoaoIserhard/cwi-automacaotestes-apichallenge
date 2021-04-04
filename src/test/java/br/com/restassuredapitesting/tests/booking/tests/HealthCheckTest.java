package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Healthcheck;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.HealthCheckRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;

public class HealthCheckTest extends BaseTest {

    HealthCheckRequest healthCheckRequest = new HealthCheckRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Healthcheck.class)
    @DisplayName("Verificar se API está online")
    public void verificarApiOn() throws Exception{
        healthCheckRequest.HealthCheckApi().then()
                .statusCode(201)
                .time(lessThan(3L), TimeUnit.SECONDS);
    }
}
