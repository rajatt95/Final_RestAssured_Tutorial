package _04_com.learning.RS_JIRA_API;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import _11_com.learning.utilities.ReadPropertyFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class _03_01_GetIssue {

	@Test
	public void TC_02_CreateIssue() {
		/*
		 * GET -> http://localhost:8080/rest/api/2/issue/10002
		 */
		RestAssured.baseURI = "http://localhost:8080";
		
		String header_cookie_session=ReadPropertyFile.readConfigFile("JIRA_cookie_session_key");
		
		Response response = 
			given().log().all().
				header("cookie", header_cookie_session).
				//header("Content-Type", "application/json").
			when().get("rest/api/2/issue/10002").
			then().log().all()
				//.assertThat().statusCode(201)
				.extract().response();
		
		System.out.println("************************************************************************");
		System.out.println("respone.statusCode(): "+response.statusCode());
		System.out.println("respone.getStatusCode(): "+response.getStatusCode());
		System.out.println("************************************************************************");
		System.out.println("respone.timeIn(TimeUnit.MILLISECONDS): "+response.timeIn(TimeUnit.MILLISECONDS));
		System.out.println("respone.getTimeIn(TimeUnit.MILLISECONDS): "+response.getTimeIn(TimeUnit.MILLISECONDS));
		System.out.println("************************************************************************");
		System.out.println("Response: "+response.asPrettyString());
	
	}

}
