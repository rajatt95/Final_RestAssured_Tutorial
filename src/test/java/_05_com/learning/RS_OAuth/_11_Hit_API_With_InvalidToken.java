package _05_com.learning.RS_OAuth;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class _11_Hit_API_With_InvalidToken {

	@Test
	public void TC_11_HitSecuredAPI() {
		/*
		 * GET -> https://rahulshettyacademy.com/getCourse.php?
		 * access_token=ya29.a0ARrdaM-
		 * b7XcElzTcQiuiWPMghUR1EGa4877yyTXLISkMqWxp9xtZ9RZATI8GE6MfOuPCXpqLl4v-
		 * GWO9l5lUgdcNeGtOY0YEegjiXGoSEmqAwI32Rp3ddu6lVRTI96ElCbUG8Toq0fqoeg5rA1TpCfZQbqlu
		 */
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		Response response = given().log().all().
		// header("Content-Type", "application/json")
				queryParam("access_token", "invalid").when().get("getCourse.php").then().log().all().assertThat()
				.statusCode(200).extract().response();

		System.out.println("************************************************************************");
		System.out.println("respone.statusCode(): " + response.statusCode());
		System.out.println("respone.getStatusCode(): " + response.getStatusCode());
		System.out.println("************************************************************************");
		System.out.println("respone.timeIn(TimeUnit.MILLISECONDS): " + response.timeIn(TimeUnit.MILLISECONDS));
		System.out.println("respone.getTimeIn(TimeUnit.MILLISECONDS): " + response.getTimeIn(TimeUnit.MILLISECONDS));
		System.out.println("************************************************************************");
		response.prettyPrint();
		System.out.println("************************************************************************");

		Assert.assertFalse(response.asString().contains("AUTHENTICATION FAILED !!!! PLEASE ENTER VALID ACCESS TOKEN"),
				"Response should not have message - AUTHENTICATION FAILED !!!! PLEASE ENTER VALID ACCESS TOKEN");
	}

}
