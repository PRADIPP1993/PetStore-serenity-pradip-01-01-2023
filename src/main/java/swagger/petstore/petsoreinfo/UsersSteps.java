package swagger.petstore.petsoreinfo;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import swagger.petstore.constants.EndPoints;
import swagger.petstore.model.UserPojo;

import java.util.HashMap;


public class UsersSteps {
    @Step("Create user with userName : {1}, firstName:{2}, lastName: {3},email:{4}")
    public ValidatableResponse createNewUser(int id, String userName, String firstName, String lastName, String email, String password, String phone, String userStatus){

        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setUsername(userName);
        userPojo.setFirstname(firstName);
        userPojo.setLastname(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(userStatus);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();
    }

    @Step("Getting the user information with firstName: {0}")
    public HashMap<String, Object> getUserByUserName(String username) {
        return SerenityRest.given().log().all()
                .pathParam("username", username)
                .when()
                .get(EndPoints.GET_USER_BY_USERNAME)
                .then()
                .statusCode(200)
                .extract().path("");
    }

    @Step("Updating User information with userName: {1}, firstName: {2}, lastName: {3}, email: {4}, programme: {5} and courses: {6}")

    public ValidatableResponse updateUser(int id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {
        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setUsername(username);
        userPojo.setFirstname(firstName);
        userPojo.setLastname(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(userStatus);

        return  SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("username", username)
                .when()
                .body(userPojo)
                .put(EndPoints.UPDATE_USER_BY_USERNAME)
                .then();

    }

    @Step
    public ValidatableResponse deleteUser(String username) {

        return SerenityRest.given().log().all()
                .pathParam("username", username)
                .when()
                .delete(EndPoints.DELETE_USER_BY_USERNAME)
                .then();
    }

}
