package _03_com.learning.RS_LibraryAPI;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class _02_GET_With_QueryParam_AuthorName {

	@Test
	public void TC_02_GET_With_QueryParam_AuthorName() {
		/*
		 * GET -> http://216.10.245.166/Library/GetBook.php?AuthorName=John foe
		 */
		RestAssured.baseURI = "http://216.10.245.166";

		Response response = given().log().all().queryParam("AuthorName", "John foe").when().get("Library/GetBook.php")
				.then().log().all().extract().response();

		System.out.println("**********************************************************");
		response.prettyPrint();
	}
}
