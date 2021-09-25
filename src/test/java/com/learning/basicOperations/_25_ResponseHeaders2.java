package com.learning.basicOperations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

/*http://makeseleniumeasy.com/2020/09/29/rest-assured-tutorial-49-how-to-retrieve-single-and-multivalue-headers-from-response-using-rest-assured/
*/
public class _25_ResponseHeaders2 {

	@Test
	public void TC_25_ResponseHeaders() {
		/*
		 * POST -> https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123
		 */
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response response = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json").body(getRequestBody_FromJSON_File()).when()
				.post("maps/api/place/add/json").then().log().all().extract().response();

		System.out.println("***************************************************************************");
		System.out.println("All Headers of response are :- ");
		Headers allHeaders = response.getHeaders();
		for(Header header : allHeaders)
		{
			System.out.print(header.getName() +" : ");
			System.out.println(header.getValue());
		}
		System.out.println("***************************************************************************");
		System.out.println("Value of Header Content-Type : "+response.getHeader("Content-Type"));
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
