package _03_com.learning.RS_LibraryAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class _12_POST_With_ReqBody_DataProvider {

	@DataProvider(name = "DataProvider_AddBook",parallel = true)
	public String[][] getData() {

		return new String[][] { 
			{ "ads", "194" }, 
			{ "lkw", "765" }, 
			{ "mnh", "234" }, 
			{ "xcv", "984" },
			{ "ajg", "237" }, 
			{ "dsa", "900" }, 
			{ "opi", "342" } };
	}

	@Test(dataProvider = "DataProvider_AddBook", dataProviderClass = _12_POST_With_ReqBody_DataProvider.class)
	public void TC_11_POST_With_ReqBody(String isbn, String aisle) {
		/*
		 * POST -> http://216.10.245.166/Library/Addbook.php
		 */
		RestAssured.baseURI = "http://216.10.245.166";

		Response response = 
				given().log().all().
					body(Payload_LibraryAPI.getAddBook(isbn, aisle)).
				when().post("Library/Addbook.php").
				then().log().all().
					assertThat().statusCode(200).
				extract().response();

		System.out.println("**********************************************************");
		response.prettyPrint();

		System.out.println("**********************************************************");
		JsonPath jsonPath = new JsonPath(response.asString());
		System.out.println("jsonPath.get(\"Msg\"): " + jsonPath.get("Msg"));
		System.out.println("jsonPath.get(\"ID\"): " + jsonPath.get("ID"));
	}
}
