package swagger.petstore.petsoreinfo;


import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import swagger.petstore.testbase.TestBase;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.hasValue;

/**
 * Created by Jay
 */
@RunWith(SerenityRunner.class)
public class UsersCURDTestWithSteps extends TestBase {
    static int id = 12;
    static String userName = "pradi1993";
    static String firstName = "pradip";
    static String lastName = "kakadiya";
    static String email = "pradip199@example.com";
    static String password = "java123";
    static String phone = "8562512145";
    static String userStatus = "12";


    @Steps
    UsersSteps usersSteps;

    @Title("This test will Create a new User")
    @Test
    public void test001(){
        ValidatableResponse response = usersSteps.createNewUser(id, userName,firstName,lastName,email,password,phone,userStatus);
        response.log().all().statusCode(200);
    }

    @Title("Verify user is added")
    @Test
    public void test002(){
        HashMap<String, Object> userMap = usersSteps.getUserByUserName(userName);
        Assert.assertThat(userMap, hasValue(userName));
    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        userName = userName + "_updated";
        usersSteps.updateUser(id,userName,firstName,lastName,email,password,phone,userStatus);
        HashMap<String, Object> userMap = usersSteps.getUserByUserName(userName);
        Assert.assertThat(userMap, anything(firstName));
    }

    @Title("Verify user was deleted")
    @Test
    public void test004() {
        usersSteps.deleteUser(userName).statusCode(200).log().all();

    }

}
