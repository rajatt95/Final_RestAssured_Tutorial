package _01_com.learning.RS_GoogleMap;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*; 

import io.restassured.RestAssured;

public class _03_POST_Assertion_ResBody_HamCrest {

	String requestBody="{\r\n"
			+ "  \"location\": {\r\n"
			+ "    \"lat\": -38.383494,\r\n"
			+ "    \"lng\": 33.427362\r\n"
			+ "  },\r\n"
			+ "  \"accuracy\": 50,\r\n"
			+ "  \"name\": \"Verma house\",\r\n"
			+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
			+ "  \"address\": \"Sector-15A, Hisar\",\r\n"
			+ "  \"types\": [\r\n"
			+ "    \"Goyat Hospital\",\r\n"
			+ "    \"HOME\"\r\n"
			+ "  ],\r\n"
			+ "  \"website\": \"http://google.com\",\r\n"
			+ "  \"language\": \"Hindi\"\r\n"
			+ "}\r\n"
			+ "";
	
	@Test
	public void TC_01_PostRequest_Headers_QueryParam_RequestBody_Assertion_ResponseStatusCode() {
		/*
		 * POST -> https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123
		 */
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().
		queryParam("key", "qaclick123").header("Content-Type","application/json").body(requestBody).
		when().post("maps/api/place/add/json").
		then().log().all().assertThat().
				statusCode(200).body("scope", equalTo("APP"));
	}
}
