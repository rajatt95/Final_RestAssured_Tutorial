package _03_com.learning.RS_LibraryAPI;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class _03_GET_With_QueryParam_ID {

	@Test
	public void TC_03_GET_With_QueryParam_ID() {
		/*
		 * GET -> http://216.10.245.166/Library/GetBook.php?ID=abc98732476227
		 */
		RestAssured.baseURI = "http://216.10.245.166";

		Response response = given().log().all().
				queryParam("ID", "xyfg227").
				when().get("Library/GetBook.php").
				then().log().all().
				assertThat().statusCode(200).
				extract().response();

		System.out.println("**********************************************************");
		response.prettyPrint();
	}
}
