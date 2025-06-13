import base.Base;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static helper.HelperMethods.*;
import static helper.ModelBuilder.createPetBody;
import static helper.ModelBuilder.createPetBodyWithStatus;
import static helper.RequestMethods.getRequest;
import static helper.RequestMethods.postRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.assertEquals;

public class FindPetsTest extends Base {

    @Test
    void it_should_get_pet_by_id() {
        long petId = getRandomPetId();
        postRequest("pet", createPetBody(petId, "maya"));

        Response response = getRequest("pet/" + petId);

        if (response.getStatusCode() == 200) {
            assertEquals(response.jsonPath().getLong("id"), petId, "Pet ID in response should match");
            assertEquals(response.jsonPath().getString("status"), "available", "Status should match");

        } else {
            System.out.println("No pets found with petId " + petId);
        }
    }

    @Test
    void it_should_get_pet_by_id_with_status_available() {
        long petId = getRandomPetId();

        Response postResponse = postRequest("pet", createPetBody(petId, "kara"));
        assertEquals(postResponse.getStatusCode(), 200);
        waitUntilPetIsExist(petId);

        Response responseFindPetByStatus = getRequest("pet/findByStatus?status=available");

        if (responseFindPetByStatus.getStatusCode() == 200) {
            List<Long> petIds = responseFindPetByStatus.jsonPath().getList("id");
            assertThat("Created pet ID is not in the response", petIds, hasItem(petId));

            Response getPetByIdResponse = getRequest("pet/" + petId);
            assertEquals(getPetByIdResponse.jsonPath().getString("status"), "available", "Status not available");
        } else {
            System.out.println("No pets found with status available");
        }
    }

    @Test
    void it_should_get_pets_with_status_sold() {
        long petId = getRandomPetId();

        Response postResponse = postRequest("pet", createPetBodyWithStatus(petId, "barbar", "sold"));
        assertEquals(postResponse.getStatusCode(), 200);
        waitUntilPetIsExist(petId);

        Response responseFindPetByStatus = getRequest("pet/findByStatus?status=sold");

        if (responseFindPetByStatus.getStatusCode() == 200) {
            List<Long> petIds = responseFindPetByStatus.jsonPath().getList("id");
            assertThat("Created pet ID is not in the response", petIds, hasItem(petId));

            Response getPetByIdResponse = getRequest("pet/" + petId);
            assertEquals(getPetByIdResponse.jsonPath().getString("status"), "sold", "Status not sold");
        } else {
            System.out.println("No pets found with status sold");
        }
    }

    @Test
    void it_should_get_error_when_pet_is_not_exist() {
        long nonExistentId = getRandomId();

        Response response = getRequest("pet/" + nonExistentId);

        assertEquals(response.getStatusCode(), 404, "Status code should be 404 Not Found");
    }
}
