package helper;

import io.restassured.response.Response;

import java.io.File;

import static constants.Constants.CONTENT_TYPE;
import static io.restassured.RestAssured.given;

public class RequestMethods {

    public static Response getRequest(String endpoint) {
        return given()
                .when()
                .get(endpoint);
    }

    public static Response postRequest(String endpoint, Object body) {
        return given()
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response postRequest(String path, String body) {
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(body)
                .post(path);
    }

    public static Response postRequestWithFile(String endpoint, String filePath) {
        File file = new File(filePath);
        return given()
                .header(CONTENT_TYPE, "multipart/form-data")
                .multiPart("file", file)
                .when()
                .post(endpoint);
    }

    public static Response putRequest(String endpoint, Object body) {
        return given()
                .body(body)
                .when()
                .put(endpoint);
    }

    public static Response deleteRequest(String endpoint) {
        return given()
                .when()
                .delete(endpoint);
    }
}
