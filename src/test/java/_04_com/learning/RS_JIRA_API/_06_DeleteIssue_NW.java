package _04_com.learning.RS_JIRA_API;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import _11_com.learning.utilities.ReadPropertyFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class _06_DeleteIssue_NW {

	
	String requestBody="{\r\n"
			+ "    \"body\": \"RestAssured - Updated Comment - How did Lal Bahadur Shastri became Prime Minister? ------------ Taking office. After the death of Nehru, several leaders of the ruling Indian National Congress nominated Shastri due to his socialist background, to stand against the conservative Morarji Desai. ... Shastri was sworn in on 9 June 1964 taking over from the Acting Prime Minister Gulzarilal Nanda.\",\r\n"
			+ "    \"visibility\": {\r\n"
			+ "        \"type\": \"role\",\r\n"
			+ "        \"value\": \"Administrators\"\r\n"
			+ "    }\r\n"
			+ "}";
	
	@Test
	public void TC_05_UpdateComment() {
		/*
		 * DELETE -> http://localhost:8080/rest/api/2/issue/10001
		 */
		RestAssured.baseURI = "http://localhost:8080";
		
		String header_cookie_session=ReadPropertyFile.readConfigFile("JIRA_cookie_session_key");
		
		Response response = 
			given().log().all().
				header("cookie", header_cookie_session).
				//body(getRequestBody_FromJSON_File()).
				body(requestBody).
			when().delete("rest/api/2/issue/10001").
			then().log().all()
				//.assertThat().statusCode(201)
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

}
