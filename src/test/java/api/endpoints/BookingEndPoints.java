package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingEndPoints {

    public static Response createBooking(String token, User payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Basic " + token)
                .body(payload)
                .when()
                .post(Routes.Booking.POST_CREATE_BOOKING);
    }

    public static Response readBooking(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(Routes.Booking.GET_BOOKING);
    }

    public static Response readBookings() {
        return given()
                .when()
                .get(Routes.Booking.GET_BOOKINGS);
    }

    public static Response updateBooking(String token, int id, User payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Basic " + token)
                .body(payload)
                .pathParam("id", id)
                .when()
                .put(Routes.Booking.PUT_UPDATE_BOOKING);
    }

    public static Response partialUpdateBooking(String token, int id, User payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Basic " + token)
                .body(payload)
                .pathParam("id", id)
                .when()
                .patch(Routes.Booking.PATCH_PARTIAL_UPDATE_BOOKING);
    }

    public static Response deleteBooking(String token, int id) {
        return given()
                .headers("Authorization", "Basic " + token)
                .pathParam("id", id)
                .when()
                .delete(Routes.Booking.DELETE_BOOKING);
    }
}
