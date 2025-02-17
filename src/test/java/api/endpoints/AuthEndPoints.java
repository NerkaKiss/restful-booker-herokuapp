package api.endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthEndPoints {

    public static Response readBookings() {
        return given()
                .when()
                .get(Routes.Auth.AUTHORIZATION);
    }
}
