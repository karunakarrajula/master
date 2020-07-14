package stepDefinations;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	static String place_id;


	@Given("Create new employee")
	public void create_new_employee() throws Throwable {

		res=given().spec(requestSpecification())
				.header("Cookie","PHPSESSID=6533cdb6c2356af58357f1a56da3c588; active_template::133674=pub_site.1594597622; ezepvv=0; ezovuuidtime_133674=1594597623; ezCMPCCS=true")
				.contentType(ContentType.JSON)
				.body(data.addEmployee());
	}

	@When("user calls {string} with {string} http requests")
	public void user_calls_with_http_requests(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions
		//constructor will be called with value of resource which you pass
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if(method.equalsIgnoreCase("POST"))
			response =res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))		
			response =res.when().get(resourceAPI.getResource()+ Utils.id);
		else if(method.equalsIgnoreCase("DELETE"))		
			response =res.when().delete(resourceAPI.getResource()+ Utils.id);
		else if(method.equalsIgnoreCase("PUT"))		
		{
			System.out.println(resourceAPI.getResource()+ Utils.id);
			response =res.when().put(resourceAPI.getResource()+ Utils.id); 

		}
	}

	@Then("API call get success with status codes {int}")
	public void api_call_get_success_with_status_codes(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),200);

	}

	@Then("{string} in responsed body is displayed as {string}")
	public void in_responsed_body_is_displayed_as(String string, String string2){
		// Write code here that turns the phrase above into concrete actions

		assertEquals(getJsonPath(response,string),string2);
	}

	@Then("verify Id created maps to new employee using {string}")
	public void verify_id_created_maps_to_new_employee_using(String resource) {

		try {
			String employeId= getEmployeId(response);
			res=given().spec(requestSpecification());
			user_calls_with_http_requests(resource, "GET");
			//	String actualName=getJsonPath(response,"data");
			String id=getEmployeId(response);
			assertEquals(employeId,id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//////// Update employee

	@Given("Update employee details")
	public void update_employee_details() {
		try {
			res=given().spec(requestSpecification())
					.header("Cookie","PHPSESSID=6533cdb6c2356af58357f1a56da3c588; active_template::133674=pub_site.1594597622; ezepvv=0; ezovuuidtime_133674=1594597623; ezCMPCCS=true")
					.contentType(ContentType.JSON)
					.body(data.modifyEmployee());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//////Delete employee

	@Given("Delete employee record from DB")
	public void delete_employee_record_from_db() {
		try {
			res=given().spec(requestSpecification())
					.header("Cookie","PHPSESSID=6533cdb6c2356af58357f1a56da3c588")
					.contentType(ContentType.JSON);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
