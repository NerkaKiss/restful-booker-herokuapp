package api.test;

import api.endpoints.BookingEndPoints;
import api.endpoints.HealthCheckEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingTests {
    Faker faker;
    User userPayLoad;
    public Logger logger;

    @BeforeSuite
    public void checkApiHealth() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Checking API Health");
        try {
            Response response = HealthCheckEndPoints.HealthCheck();
            if(response.getStatusCode() != 201) {
                logger.error("The API service is unavailable, all tests will be aborted.");
                throw new SkipException("The API service is unavailable, all tests will be aborted.");
            }
        } catch (Exception e) {
            logger.error("Failed to access API service, all tests will be aborted.");
            throw new SkipException("Failed to access API service, all tests will be aborted.");
        }
        logger.info("API IS LIVE");
    }

    @BeforeClass
    public void setupData() {
        logger.info("Creating fake user data");
        faker = new Faker();
        userPayLoad = new User();
        userPayLoad.setFirstName(faker.name().firstName());
        userPayLoad.setLastName(faker.name().lastName());
        userPayLoad.setTotalPrice(faker.number().randomDigitNotZero());
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] bookingDates = {currentDate
                .plusDays(1).format(formatter), currentDate.plusMonths(1).format(formatter)};
        userPayLoad.setBookingDates(bookingDates);
        String[] additionalNeeds = {"", "Breakfast", "Lunch", "Dinner"};
        userPayLoad.setAdditionalNeeds(additionalNeeds[faker.number().numberBetween(0,additionalNeeds.length)]);
        logger.info("Fake user data created");
    }

    @Test(priority = 1)
    public void testGetBookings() {
        logger.info("Getting bookings");
        Response response = BookingEndPoints.readBookings();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Getting bookings STATUS PASS");
    }
}
