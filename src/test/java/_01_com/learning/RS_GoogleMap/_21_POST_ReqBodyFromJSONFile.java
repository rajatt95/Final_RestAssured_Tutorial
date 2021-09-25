package _01_com.learning.RS_GoogleMap;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.restassured.RestAssured;

public class _21_POST_ReqBodyFromJSONFile {

	@Test
	public void TC_21_POST_ReqBodyFromJSONFile() {
		/*
		 * POST -> https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123
		 */
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		given().log().all().
			queryParam("key", "qaclick123").
			header("Content-Type", "application/json").
			body(getRequestBody_FromJSON_File()).
		when().post("maps/api/place/add/json").
		// then().log().all().assertThat().statusCode(201);
		then().log().all().assertThat().statusCode(200);
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
