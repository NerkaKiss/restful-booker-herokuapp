package api.endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HealthCheckEndPoints {

    public static Response HealthCheck() {
        return given()
                .when()
                .get(Routes.Ping.GET_PING);
    }
}
