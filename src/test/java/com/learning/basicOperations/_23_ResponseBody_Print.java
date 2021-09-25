package com.learning.basicOperations;


import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class _23_ResponseBody_Print {

	
	@Test
	public void TC_23_ResponseBody_Print() {
		/*
		 * POST -> https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123
		 */
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response response = given().log().all().
		queryParam("key", "qaclick123").header("Content-Type","application/json").body(getRequestBody_FromJSON_File()).
		when().post("maps/api/place/add/json").
		then().log().all()
		.extract().response();
		
		System.out.println("***************************************************************************");
		System.out.println("Response: ");
		System.out.println("***************************************************************************");
		System.out.println("respone.asString(): "+response.asString());
		System.out.println("***************************************************************************");
		System.out.println("respone.asPrettyString(): "+response.asPrettyString());
		System.out.println("***************************************************************************");
		
		
	}
	
	private String getRequestBody_FromJSON_File() {

		/* Content of the JSON file --> Bytes --> String */

		String resourcesPath = System.getProperty("user.dir");
		Path path = Paths.get(resourcesPath + "/src/test/resources/AddPlace.json");
		try {
			System.out.println("*******************************************");
			System.out.println(new String(Files.readAllBytes(path)));
			System.out.println("*******************************************");
			return new String(Files.readAllBytes(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
