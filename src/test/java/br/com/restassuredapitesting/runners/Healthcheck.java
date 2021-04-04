package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.booking.tests.*;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.Healthcheck.class)
@Suite.SuiteClasses({
        GetBookingTest.class,
        PutBookingTest.class,
        DeleteBookingTest.class,
        HealthCheckTest.class,
        PostBookingTest.class
})
public class Healthcheck {
}
