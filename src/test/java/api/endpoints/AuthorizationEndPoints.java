package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class AuthorizationEndPoints {
    public static Response CreateToken() {
        HashMap<String, String> authData= new HashMap<>();
        authData.put("username", "admin");
        authData.put("password", "password123");
        return given()
                .contentType(ContentType.JSON)
                .body(authData)
                .when()
                .post(Routes.Auth.AUTHORIZATION);
    }
}
