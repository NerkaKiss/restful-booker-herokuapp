package api.test;

import api.endpoints.AuthorizationEndPoints;
import api.endpoints.BookingEndPoints;
import api.endpoints.HealthCheckEndPoints;
import api.payload.User;
import api.payload.User.BookingDates;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

import static io.restassured.RestAssured.given;


public class BookingTests {
    Faker faker;
    User userPayLoad;
    BookingDates bookingDates;
    public Logger logger;
    String token;
    int bookingId;

    @BeforeSuite
    public void checkApiHealth() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Checking API Health");
        try {
            Response response = HealthCheckEndPoints.HealthCheck();
            if (response.getStatusCode() != 201) {
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
    public void setupDataAndGetToken() {
        logger.info("Getting Token");
        Response response = AuthorizationEndPoints.CreateToken();
        if (response.getStatusCode() != 200) {
            logger.error("Cannot create new token");
            throw new SkipException("Cannot create new token.");
        }
        token = response.jsonPath().getString("token");
        if (token == null) {
            logger.error("Token is null.");
            throw new SkipException("Token is null.");
        }
        logger.info("Token created");
        logger.info("Creating fake user data");
        faker = new Faker();
        userPayLoad = new User();
        bookingDates = new BookingDates();
        userPayLoad.setBookingDates(bookingDates);
        userPayLoad.setFirstName(faker.name().firstName());
        userPayLoad.setLastName(faker.name().lastName());
        userPayLoad.setTotalPrice(faker.number().randomDigitNotZero());
        userPayLoad.setDepositPaid(faker.random().nextBoolean());
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        bookingDates.setCheckin(currentDate
                .plusDays(1).format(formatter));
        bookingDates.setCheckout(currentDate.plusMonths(1).format(formatter));
        String[] additionalNeeds = {"", "Breakfast", "Lunch", "Dinner"};
        userPayLoad.setAdditionalNeeds(additionalNeeds[faker.number().numberBetween(0, additionalNeeds.length)]);
        logger.info("Fake user data created");
    }

    @Test(priority = 1)
    public void testGetBookings() {
        logger.info("Getting bookings");
        Response response = BookingEndPoints.readBookings();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Getting bookings ended");
    }

    @Test(priority = 2)
    public void testCreateBooking() {
        logger.info("Creating booking");
        Response response = BookingEndPoints.createBooking(token, userPayLoad);
        Assert.assertEquals(response.getStatusCode(), 200);
        bookingId = response.jsonPath().getInt("bookingid");
        logger.info("Creating booking ended");
    }

    @Test(priority = 3)
    public void testGetBooking() {
        logger.info("Getting booking");
        Response response = BookingEndPoints.readBooking(bookingId);
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Getting booking ended");
    }
}
