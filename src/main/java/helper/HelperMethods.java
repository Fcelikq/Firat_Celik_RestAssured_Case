package helper;

import io.restassured.response.Response;
import models.PetDto;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static helper.ModelBuilder.createPetBody;
import static helper.RequestMethods.getRequest;
import static io.restassured.RestAssured.given;
import static org.awaitility.Awaitility.await;

public class HelperMethods {

    public static long getRandomPetId() {
        return new Random().nextLong(10000000);
    }

    public static long getRandomId() {
        return new Random().nextLong(10000000);
    }

    public static void createPet(long petId) {
        PetDto petDto = createPetBody(petId, "bekir");

        given()
                .body(petDto)
                .post("pet")
                .then()
                .statusCode(200);
    }

    public static void waitUntilPetIsExist(long petId) {
        await()
                .atMost(15, TimeUnit.SECONDS)
                .pollInterval(300, TimeUnit.MILLISECONDS)
                .until(() -> {
                    Response response = getRequest("/pet/" + petId);
                    return response.getStatusCode() == 200
                            && response.getBody().jsonPath().getLong("id") > 0;
                });
    }

}
