package _04_com.learning.RS_JIRA_API;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class _01_CreateSession {

	@Test
	public void TC_01_CreateSession() {
		/*
		 * POST -> http://localhost:8080/rest/auth/1/session
		 */
		RestAssured.baseURI = "http://localhost:8080";

		Response response = given().log().all().header("Content-Type", "application/json")
				.body(getRequestBody_FromJSON_File()).when().post("rest/auth/1/session").
				// then().log().all().assertThat().statusCode(201);
				then().log().all()
				// .assertThat().statusCode(200)
				.extract().response();

		System.out.println("************************************************************************");
		System.out.println("respone.statusCode(): " + response.statusCode());
		System.out.println("respone.getStatusCode(): " + response.getStatusCode());
		System.out.println("************************************************************************");
		System.out.println("respone.timeIn(TimeUnit.MILLISECONDS): " + response.timeIn(TimeUnit.MILLISECONDS));
		System.out.println("respone.getTimeIn(TimeUnit.MILLISECONDS): " + response.getTimeIn(TimeUnit.MILLISECONDS));
		System.out.println("************************************************************************");
		System.out.println("Response: " + response.prettyPrint());
		
		System.out.println("************************************************************************");
		JsonPath jsonPath = new JsonPath(response.asString());
		String session_name = jsonPath.get("session.name");
		String session_value = jsonPath.get("session.value");

		System.out.println("session_name: " + session_name);
		System.out.println("session_value: " + session_value);

		String key = session_name + "=" + session_value;
		System.out.println("key: " + key);
		System.setProperty("JIRA_cookie_session_key", key);
	}

	private String getRequestBody_FromJSON_File() {

		/* Content of the JSON file --> Bytes --> String */

		String resourcesPath = System.getProperty("user.dir");
		Path path = Paths.get(resourcesPath + "/src/test/resources/04_JIRA_User_Credentials.json");
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
