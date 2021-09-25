package com.learning.basicOperations;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import io.restassured.response.Response;

public class _41_SchemaValidation_NW {

	@Test
	public void TC_27_ResponseBody() {

		
		File schema_AddPLace_API = new File(
				System.getProperty("user.dir") + "/src/test/resources/AddPlace_Schema.json");
		
		System.out.println("schema_AddPLace_API: " +schema_AddPLace_API);
		/*
		 * POST -> https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123
		 */
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(getRequestBody_FromJSON_File()).when().post("maps/api/place/add/json").then()
				.body(matchesJsonSchema(schema_AddPLace_API));

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
