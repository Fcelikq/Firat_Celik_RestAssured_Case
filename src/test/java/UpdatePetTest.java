import base.Base;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static constants.Constants.PET_ID;
import static helper.HelperMethods.*;
import static helper.ModelBuilder.createPetBody;
import static helper.RequestMethods.postRequest;
import static helper.RequestMethods.putRequest;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UpdatePetTest extends Base {

    @Test
    void it_should_update_pet_name() {
        String updatedPetName = "dog";
        Response response = putRequest("pet", createPetBody(PET_ID, updatedPetName));

        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        assertEquals(response.getBody().jsonPath().getLong("id"), PET_ID);
        assertEquals(response.getBody().jsonPath().getString("category.name"), updatedPetName, "Pet name should match");
    }

    @Test
    void it_should_update_pet_status() {
        long petId = getRandomPetId();
        createPet(petId);

        waitUntilPetIsExist(petId);

        Response response = postRequest("pet/" + petId, "status=sold");

        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        assertEquals(response.jsonPath().getString("message"), String.valueOf(petId), "Message field on response not returned pet ID");
    }

    @Test
    void it_should_get_error_when_update_non_existing_pet() {
        long nonExistentPetId = getRandomId();

        Response response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("name=test&status=test")
                .post("/pet/" + nonExistentPetId);
        assertTrue(response.getStatusCode() >= 400, "Status code should indicate an error");
    }
}
