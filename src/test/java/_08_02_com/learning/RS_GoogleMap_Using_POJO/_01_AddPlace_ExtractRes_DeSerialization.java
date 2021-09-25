package _08_02_com.learning.RS_GoogleMap_Using_POJO;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import _08_01_com.learning.RS_GoogleMap_POJO_Classes.Res_AddPlace;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class _01_AddPlace_ExtractRes_DeSerialization {

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
	public void TC_01_PostRequest_Headers_QueryParam_RequestBody() {
		/*
		 * POST -> https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123
		 */
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response response = 
			given().log().all().
				queryParam("key", "qaclick123").header("Content-Type","application/json").body(requestBody).
				// expect().defaultParser(Parser.JSON). -> you can neglect this as well only if
				// Response Header has
				// Content-Type as application/json
				expect().defaultParser(Parser.JSON).				
			when().post("maps/api/place/add/json").
			then().log().all()
			.extract().response();
		
		Res_AddPlace res_AddPlace = response.as(Res_AddPlace.class);
		
		System.out.println("************************************************************************");
		System.out.println("res_AddPlace.getStatus(): " + res_AddPlace.getStatus());
		System.out.println("res_AddPlace.getPlace_id(): " + res_AddPlace.getPlace_id());
		System.out.println("res_AddPlace.getScope(): " + res_AddPlace.getScope());
		System.out.println("res_AddPlace.getReference(): " + res_AddPlace.getReference());
		System.out.println("res_AddPlace.getId(): " + res_AddPlace.getId());		
		System.out.println("************************************************************************");
		print_ResponseDetails(response);
	}
	
	private void print_ResponseDetails(Response response) {
		System.out.println("************************************************************************");
		System.out.println("respone.statusCode(): " + response.statusCode());
		System.out.println("respone.getStatusCode(): " + response.getStatusCode());
		System.out.println("************************************************************************");
		System.out.println("respone.timeIn(TimeUnit.MILLISECONDS): " + response.timeIn(TimeUnit.MILLISECONDS));
		System.out.println("respone.getTimeIn(TimeUnit.MILLISECONDS): " + response.getTimeIn(TimeUnit.MILLISECONDS));
		System.out.println("************************************************************************");
		response.prettyPrint();
		System.out.println("************************************************************************");
	}
	
}
