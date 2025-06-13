import base.Base;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static helper.HelperMethods.*;
import static helper.RequestMethods.deleteRequest;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DeletePetTest extends Base {

    @Test
    void it_should_delete_pet() {
        long petId = getRandomPetId();
        createPet(petId);
        waitUntilPetIsExist(petId);

        Response response = deleteRequest("/pet/" + petId);
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        assertEquals(response.jsonPath().getString("message"), String.valueOf(petId), "Response message should contain pet ID");
    }

    @Test
    void it_should_get_error_when_delete_non_existent_pet() {
        long nonExistentId = getRandomId();

        Response response = given()
                .header("api_key", "test_key")
                .delete("/pet/" + nonExistentId);
        assertTrue(response.getStatusCode() >= 400, "Status code should indicate an error");
    }
}
