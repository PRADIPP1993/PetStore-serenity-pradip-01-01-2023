package swagger.petstore.petsoreinfo;


import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import swagger.petstore.testbase.TestBase;
import swagger.petstore.utils.TestUtils;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class PetsCRUDTest extends TestBase {
    static long petId = 115;

    static HashMap<String,Object> category;

    static String name = "minty" + TestUtils.getRandomValue();

    static ArrayList<String> photoUrls;

    static ArrayList<HashMap<String,Object>> tags;

    static String status = "available";


    @Steps
    PetsSteps petsSteps;

    @Title("This will create new Pet")
    @Test
    public void test001() {

        HashMap<String, Object> category = new HashMap<>();
        category.put("id", "2");
        category.put("name", "Pug");

        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add("String");

        ArrayList<HashMap<String, Object>> tags = new ArrayList<>();
        HashMap<String, Object> tagMap = new HashMap<>();
        tagMap.put("id", 5);
        tagMap.put("name", "Cherry");
        tags.add(tagMap);

        ValidatableResponse response = petsSteps.createPet(petId,category,name,photoUrls,tags,status);
        response.log().all().statusCode(200);
        petId = response.log().all().extract().path("id");

    }

    @Title("Verify pet was created")
    @Test
    public void test002(){
        HashMap<String, Object> petMap = petsSteps.findPetById(petId);
        Assert.assertThat(petMap, hasValue(name));
    }

    @Title("Update the pet info")
    @Test
    public void test003(){
        status = status + "_updated";

        HashMap<String, Object> category = new HashMap<>();
        category.put("id", "2");
        category.put("name", "Pug");

        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add("String");

        ArrayList<HashMap<String, Object>> tags = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tagMap = new HashMap<>();
        tagMap.put("id", 5);
        tagMap.put("name", "Cherry");
        tags.add(tagMap);

        petsSteps.updatePet(petId,category,name,photoUrls,tags,status);
        HashMap<String, Object> petMap = petsSteps.findPetById(petId);
        Assert.assertThat(petMap, anything(status));
    }

    @Title("Delete the pet")
    @Test
    public void test004(){
        petsSteps.deletePetById(petId);
        petsSteps.getPetById(petId);
    }
}