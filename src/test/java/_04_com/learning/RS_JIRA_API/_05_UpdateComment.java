package _04_com.learning.RS_JIRA_API;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import _11_com.learning.utilities.ReadPropertyFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class _05_UpdateComment {

	
	
	@Test
	public void TC_05_UpdateComment() {
		/*
		 * POST -> http://localhost:8080/rest/api/2/issue/10003/comment/10002
		 */
		RestAssured.baseURI = "http://localhost:8080";
		
		String header_cookie_session=ReadPropertyFile.readConfigFile("JIRA_cookie_session_key");
		
		Response response = 
			given().log().all().
				header("cookie", header_cookie_session).
				header("Content-Type", "application/json").
				body(getRequestBody_FromJSON_File()).
			when().put("rest/api/2/issue/10003/comment/10002").
			then().log().all()
				.assertThat().statusCode(200)
				.extract().response();
		
		System.out.println("************************************************************************");
		System.out.println("Response: "+response.asPrettyString());
		System.out.println("************************************************************************");
		System.out.println("respone.statusCode(): "+response.statusCode());
		System.out.println("respone.getStatusCode(): "+response.getStatusCode());
		System.out.println("************************************************************************");
		System.out.println("respone.timeIn(TimeUnit.MILLISECONDS): "+response.timeIn(TimeUnit.MILLISECONDS));
		System.out.println("respone.getTimeIn(TimeUnit.MILLISECONDS): "+response.getTimeIn(TimeUnit.MILLISECONDS));
		System.out.println("************************************************************************");
		
	
	}

	private String getRequestBody_FromJSON_File() {

		/* Content of the JSON file --> Bytes --> String */

		String resourcesPath = System.getProperty("user.dir");
		Path path = Paths.get(resourcesPath + "/src/test/resources/json/04_JIRA_AddComment.json");
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
