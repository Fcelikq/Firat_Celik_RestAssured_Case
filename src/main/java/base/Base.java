package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static constants.Constants.APPLICATION_JSON;
import static constants.Constants.BASE_URL;

public class Base {
    protected static RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        // Set base URI
        RestAssured.baseURI = BASE_URL;

        // Create request specification
        requestSpec = new RequestSpecBuilder()
                .setContentType(APPLICATION_JSON)
                .build();
    }

    @BeforeMethod
    public void beforeMethod() {
        RestAssured.requestSpecification = requestSpec;
    }
}
