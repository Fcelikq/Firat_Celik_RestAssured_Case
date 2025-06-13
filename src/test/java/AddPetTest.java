import base.Base;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static constants.Constants.FILE_PATH_DOG_IMAGE;
import static constants.Constants.PET_ID;
import static helper.ModelBuilder.createPetBody;
import static helper.RequestMethods.postRequest;
import static helper.RequestMethods.postRequestWithFile;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddPetTest extends Base{
 

    @Test
    void it_should_add_pet_to_the_store() {
        postRequest("/pet", createPetBody(PET_ID, "leo"));
        Response response = postRequest("/pet", createPetBody(PET_ID, "leo"));

        assertEquals(response.getBody().jsonPath().getLong("id"), PET_ID);
        assertEquals(response.getBody().jsonPath().getString("category.name"), "leo", "Pet name should match");
    }

    @Test
    void it_should_add_image_to_pet() {
        Response response = postRequestWithFile("/pet/" + PET_ID + "/uploadImage", FILE_PATH_DOG_IMAGE);

        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        assertTrue(response.jsonPath().getString("message").contains("uploaded"), "Response should confirm upload");
    }

    @Test
    void it_should_get_error_when_add_pet_with_empty_body() {
        Response response = given()
                .body("")
                .post("/pet");
        assertTrue(response.getStatusCode() >= 400, "Status code should be an error code (4xx or 5xx)");
    }

}
