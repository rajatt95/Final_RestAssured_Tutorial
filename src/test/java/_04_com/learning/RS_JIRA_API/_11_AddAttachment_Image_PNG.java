package _04_com.learning.RS_JIRA_API;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import _11_com.learning.utilities.ReadPropertyFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class _11_AddAttachment_Image_PNG {

	
	
	@Test
	public void TC_02_CreateIssue() {
		/*
		 * POST -> http://localhost:8080/rest/api/2/issue/10003/comment
		 */
		RestAssured.baseURI = "http://localhost:8080";
		
		String header_cookie_session=ReadPropertyFile.readConfigFile("JIRA_cookie_session_key");
		
		String file_Attachment=System.getProperty("user.dir")+
				"/src/test/resources/images/PM_Shri_Lal_Bahadur_Shastri.jpg";
		
		Response response = 
			given().log().all().
				header("cookie", header_cookie_session).
				header("Content-type", "multipart/form-data").
				header("X-Atlassian-Token", "no-check").
				multiPart("file",new File(file_Attachment)).
			when().post("rest/api/2/issue/10003/attachments").
			then().log().all()
				.assertThat().statusCode(200)
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
