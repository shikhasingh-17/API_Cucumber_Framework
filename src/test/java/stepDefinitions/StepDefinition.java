package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	ResponseSpecification resSpec;
	RequestSpecification req;
	Response response;
	String strResponse;
	TestDataBuild data = new TestDataBuild();
	static String placeId;
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) {
		 try {
			req = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@When("user calls {string} using http {string} request")
	public void user_calls_using_http_request(String resource, String method) {
		resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		if(method.equalsIgnoreCase("POST"))		
		 response = req.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = req.when().get(resourceAPI.getResource());
				 
	}
	@Then("API call is successful")
	public void api_call_is_successful() {
	    assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expKeyValue) {	
	    
	    assertEquals(getJsonPath(response,key),expKeyValue);
	}

	@Then("Verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
		placeId = getJsonPath(response,"place_id");
	/*	req = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_using_http_request(resource,"GET");*/
		response = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).when().get("http://rahulshettyacademy.com/maps/api/place/get/json")
				.then().assertThat().statusCode(200).extract().response();
		String actName = getJsonPath(response,"name");
		assertEquals(actName,name);
		
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
	  //create request spec
		req = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	
	}


}
