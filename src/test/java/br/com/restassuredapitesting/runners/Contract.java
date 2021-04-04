package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.booking.tests.*;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.Contract.class)
@Suite.SuiteClasses({
        GetBookingTest.class,
        PutBookingTest.class,
        PostBookingTest.class,
        DeleteBookingTest.class,
        HealthCheckTest.class
})
public class Contract {
}
