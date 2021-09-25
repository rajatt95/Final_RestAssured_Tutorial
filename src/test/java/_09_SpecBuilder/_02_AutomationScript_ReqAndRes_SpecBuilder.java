package _09_SpecBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class _02_AutomationScript_ReqAndRes_SpecBuilder {

	String requestBody = "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n"
			+ "    \"lng\": 33.427362\r\n" + "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Verma house\",\r\n"
			+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + "  \"address\": \"Sector-15A, Hisar\",\r\n"
			+ "  \"types\": [\r\n" + "    \"Goyat Hospital\",\r\n" + "    \"HOME\"\r\n" + "  ],\r\n"
			+ "  \"website\": \"http://google.com\",\r\n" + "  \"language\": \"Hindi\"\r\n" + "}\r\n" + "";

	@Test
	public void TC_01_PostRequest_Headers_QueryParam_RequestBody_Assertion_ResponseStatusCode() {
		/*
		 * POST -> https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123
		 */
		
		RequestSpecification requestSpecBuilder=new RequestSpecBuilder().
		setBaseUri("https://rahulshettyacademy.com").
		setContentType(ContentType.JSON).
		addQueryParam("key", "qaclick123").
		build();
		
		ResponseSpecification responseSpecBuilder=new ResponseSpecBuilder().
		expectHeader("Server", "Apache/2.4.18 (Ubuntu)").
		expectHeader("Content-Type", "application/json;charset=UTF-8").
		expectContentType(ContentType.JSON).
		expectStatusCode(200).
		build();
		
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com";

		String place_id = hit_POST_Req_And_Get_PlaceID(requestSpecBuilder, responseSpecBuilder);

		hit_GET_Using_PlaceID(requestSpecBuilder,responseSpecBuilder, place_id);

	}

	private void hit_GET_Using_PlaceID(RequestSpecification requestSpecBuilder, 
			ResponseSpecification responseSpecBuilder,String place_id) {
//		String api_response_get_asString = 
//				given().log().all().
//					queryParam("key", "qaclick123").
//					header("Content-Type", "application/json").
		String api_response_get_asString = 
				given().log().all().
					spec(requestSpecBuilder).
					queryParam("place_id", place_id).
				when().
					get("maps/api/place/get/json").
				then().log().all().
					body("name", equalTo("Verma house")).
//					assertThat().statusCode(200).
//					header("Server", "Apache/2.4.18 (Ubuntu)").
//					header("Content-Type", "application/json;charset=UTF-8").
					spec(responseSpecBuilder).
					extract().response().asString();

		System.out.println("**********************************************************************");
		System.out.println("**********************************************************************");
		System.out.println(api_response_get_asString);

		System.out.println("**********************************************************************");
		System.out.println("**********************************************************************");

		JsonPath jsonPath_get = new JsonPath(api_response_get_asString);
		System.out.println("jsonPath_get.get(\"location.latitude\"): " + jsonPath_get.get("location.latitude"));
		System.out.println("jsonPath_get.get(\"location.longitude\"): " + jsonPath_get.get("location.longitude"));
		System.out.println("jsonPath_get.get(\"accuracy\"): " + jsonPath_get.get("accuracy"));
		System.out.println("jsonPath_get.get(\"name\"): " + jsonPath_get.get("name"));
		System.out.println("jsonPath_get.get(\"phone_number\"): " + jsonPath_get.get("phone_number"));
		System.out.println("jsonPath_get.get(\"address\"): " + jsonPath_get.get("address"));
		System.out.println("jsonPath_get.get(\"types\"): " + jsonPath_get.get("types"));
		System.out.println("jsonPath_get.get(\"website\"): " + jsonPath_get.get("website"));
		System.out.println("jsonPath_get.get(\"language\"): " + jsonPath_get.get("language"));
	}

	private String hit_POST_Req_And_Get_PlaceID(RequestSpecification requestSpecBuilder, ResponseSpecification responseSpecBuilder) {
//		String api_response_asString = 
//				given().log().all()
//					.queryParam("key", "qaclick123")
//					.header("Content-Type", "application/json")
		
		
		String api_response_asString = 
				given().log().all()
					.spec(requestSpecBuilder)
					.body(requestBody).when().post("maps/api/place/add/json")
				.then().log().all().
					body("scope", equalTo("APP")).
	//				assertThat().statusCode(200).
	//				header("Server", "Apache/2.4.18 (Ubuntu)").
					spec(responseSpecBuilder).
					extract().response().asString();

		System.out.println("**********************************************************************");
		System.out.println("**********************************************************************");
		System.out.println(api_response_asString);

		System.out.println("**********************************************************************");
		System.out.println("**********************************************************************");

		JsonPath jsonPath = new JsonPath(api_response_asString);
		System.out.println("jsonPath.get(\"status\"): " + jsonPath.get("status"));
		String place_id = jsonPath.get("place_id");
		System.out.println("jsonPath.get(\"place_id\"): " + place_id);

		String actual_Scope = jsonPath.get("scope");
		String expected_Scope = "APP";
		System.out.println("jsonPath.get(\"scope\"): " + actual_Scope);
		Assert.assertEquals(actual_Scope, expected_Scope);
		System.out.println("jsonPath.get(\"reference\"): " + jsonPath.get("reference"));
		System.out.println("jsonPath.get(\"id\"): " + jsonPath.get("id"));

		System.out.println("**********************************************************************");
		System.out.println("**********************************************************************");
		return place_id;
	}
}
