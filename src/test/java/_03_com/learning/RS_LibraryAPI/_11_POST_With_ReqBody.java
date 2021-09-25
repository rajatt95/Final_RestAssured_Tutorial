package _03_com.learning.RS_LibraryAPI;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class _11_POST_With_ReqBody {

	
	
	@Test
	public void TC_11_POST_With_ReqBody() {
		/*
		 * POST -> http://216.10.245.166/Library/Addbook.php
		 */
		RestAssured.baseURI = "http://216.10.245.166";

		Response response =  
				given().log().all().body(Payload_LibraryAPI.getAddBook("mn4bv", "9876")).
				when().post("Library/Addbook.php").
				then().log().all().
				assertThat().statusCode(200).
				extract().response();
		
		System.out.println("**********************************************************");
		response.prettyPrint();

		System.out.println("**********************************************************");
		JsonPath jsonPath=new JsonPath(response.asString());
		System.out.println("jsonPath.get(\"Msg\"): "+jsonPath.get("Msg"));
		System.out.println("jsonPath.get(\"ID\"): "+jsonPath.get("ID"));
	}
}
