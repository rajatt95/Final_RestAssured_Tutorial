package _03_com.learning.RS_LibraryAPI;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class _01_POST_With_ReqBody {

	String requestBody = "{\r\n" + "\"name\":\"Learn Appium Automation with Java\",\r\n" + "\"isbn\":\"xyfg\",\r\n"
			+ "\"aisle\":\"227\",\r\n" + "\"author\":\"John foe\"\r\n" + "}\r\n" + "";

	
	@Test
	public void TC_01_PostRequest_Headers_QueryParam_RequestBody() {
		/*
		 * POST -> http://216.10.245.166/Library/Addbook.php
		 */
		RestAssured.baseURI = "http://216.10.245.166";

		Response response =  
				given().log().all().body(requestBody).
				when().post("Library/Addbook.php").
				then().log().all().extract().response();
		
		System.out.println("**********************************************************");
		response.prettyPrint();
	}
}
