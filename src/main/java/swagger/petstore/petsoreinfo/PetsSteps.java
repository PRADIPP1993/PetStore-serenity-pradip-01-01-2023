package swagger.petstore.petsoreinfo;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import swagger.petstore.constants.EndPoints;
import swagger.petstore.model.PetsPojo;

import java.util.ArrayList;
import java.util.HashMap;

public class PetsSteps {

    @Step("Create Pet with petId:{0},category:{1},name:{2},photoUrls{3},tags:{4},status:{5}")

    public ValidatableResponse createPet(long petId, HashMap<String,Object> category, String name, ArrayList<String> photoUrls, ArrayList<HashMap<String,Object>> tags, String status) {
        PetsPojo petPojo = new PetsPojo();
        petPojo.setCategory(category);
        petPojo.setName(name);
        petPojo.setPhotoUrls(photoUrls);
        petPojo.setTags(tags);
        petPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(petPojo)
                .when()
                .post(EndPoints.CREATE_PET)
                .then();
    }

    @Step("Find the pet by petId: {0}")
    public HashMap<String, Object> findPetById(long petId){

        return SerenityRest.given().log().all()
                .pathParam("petId", petId)
                .when()
                .get(EndPoints.GET_PET_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
    }

    @Step("Update Pet information with petId: {0}, category: {1}, name: {2}, photoUrls: {3}, tag: {4}, status: {5}")
    public ValidatableResponse updatePet(long petId, HashMap<String, Object> category, String name, ArrayList<String> photoUrls, ArrayList<HashMap<String, Object>> tags, String status){
        name = name + "_updated";

        PetsPojo petPojo = new PetsPojo();
        petPojo.setCategory(category);
        petPojo.setName(name);
        petPojo.setPhotoUrls(photoUrls);
        petPojo.setTags(tags);
        petPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("name", name)
                .body(petPojo)
                .when()
                .put(EndPoints.UPDATE_PET_BY_NAME)
                .then();
    }

    @Step("Deleting the pet by petId: {0}")
    public ValidatableResponse deletePetById(long petId) {

        return SerenityRest.given().log().all()
                .pathParam("petId", petId)
                .when()
                .delete(EndPoints.DELETE_PET_BY_ID)
                .then();
    }

    @Step("Getting the pet by petId: {0}")
    public ValidatableResponse getPetById(long petId) {

        return SerenityRest.given().log().all()
                .pathParam("petId", petId)
                .when()
                .get(EndPoints.GET_PET_BY_ID)
                .then();
    }
}
