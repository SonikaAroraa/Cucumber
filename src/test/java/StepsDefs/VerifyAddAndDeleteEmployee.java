package StepsDefs;

import Utilities.APIDetails;
import Utilities.Utils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class VerifyAddAndDeleteEmployee extends Utils {
    private Response response;
    RequestSpecification res;
    ResponseSpecification resspec;
    int id;


    @Given("Add Employee payload from {string} file")
    public void AddEmployee(String payloadJSONFile) throws IOException {
        File jsonfile = new File("CreateEmployee.json");
        res = given().log().all().spec(requestSpecification())
                .body(jsonfile);
    }

    @When("user calls {string} with {string} http request")
    public void createNewEmployee(String apiname, String APIType) {
        APIDetails api = APIDetails.valueOf(apiname);
        if (APIType.equalsIgnoreCase("POST")) {
            response = res.when().post(api.getResource());
            id = Utils.getJsonPath(response, "data.id");
        } else if (APIType.equalsIgnoreCase("GET"))
            response = res.when().get(api.getResource());

    }


    @Then("verify created response to using {string} with {string}")
    public void verifyusingGet(String APIType, String apiname) {

        APIDetails api = APIDetails.valueOf(apiname);
        if (APIType.equalsIgnoreCase("POST")) {
            response = res.when().post(api.getResource());
            id = Utils.getJsonPath(response, "data.id");
            System.out.println(" hegfyq8gfiewbgfiuewgufihewu" + id);
        } else if (APIType.equalsIgnoreCase("GET"))
            response = res.when().get(api.getResource() + id);
        System.out.println(" hegfyq8gfiewbgfiuewgufihewu" + id);
    }

    @And("Delete the created employee using {string}")
    public void deleteCreatedEmployee(String deleteAPI) {
        APIDetails api = APIDetails.valueOf(deleteAPI);
        response = res.when().delete(api.getResource() + id);
    }
}





