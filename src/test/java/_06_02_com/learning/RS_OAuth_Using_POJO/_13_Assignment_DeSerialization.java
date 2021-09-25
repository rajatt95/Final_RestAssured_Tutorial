package _06_02_com.learning.RS_OAuth_Using_POJO;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import _06_01_com.learning.RS_OAuth_POJO_Classes.API;
import _06_01_com.learning.RS_OAuth_POJO_Classes.GetCourse;
import _06_01_com.learning.RS_OAuth_POJO_Classes.Mobile;
import _06_01_com.learning.RS_OAuth_POJO_Classes.WebAutomation;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class _13_Assignment_DeSerialization {

	@Test
	public void TC_11_HitSecuredAPI() {

		// String codeValue = hit_Browser_AndGetCodeValue_FromURL();

		/**
		 * We are not allowed to get the Code by Automation as Google has blocked
		 * Automation for Gmail Sign in
		 * 
		 * Go to hit_Browser_AndGetCodeValue_FromURL()
		 */
		String codeValue = "4%2F0AX4XfWhBD83IJXBxcKVhE7fVXdvwbM0kTe9w49exOWeIcWj1zMU929wgwvfTlEtFHlsa-g";

		String accessToken = hitGET_Req_ToGetAccessToken(codeValue);

		/*
		 * GET -> https://rahulshettyacademy.com/getCourse.php?
		 * access_token=ya29.a0ARrdaM-
		 * b7XcElzTcQiuiWPMghUR1EGa4877yyTXLISkMqWxp9xtZ9RZATI8GE6MfOuPCXpqLl4v-
		 * GWO9l5lUgdcNeGtOY0YEegjiXGoSEmqAwI32Rp3ddu6lVRTI96ElCbUG8Toq0fqoeg5rA1TpCfZQbqlu
		 */

		
		
		Response response = given().log().all().
				// header("Content-Type", "application/json")
				// queryParam("access_token", "invalid")
						queryParam("access_token", accessToken).
						// expect().defaultParser(Parser.JSON). -> you can neglect this as well only if
						// Response Header has
						// Content-Type as application/json
						expect().defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php")
						.then()/* log().all() */
						.assertThat().statusCode(200).extract().response();
		
		GetCourse getCourse = response.as(GetCourse.class);
		

		System.out.println("************************************************************************");
		System.out.println("getCourse.getInstructor(): " + getCourse.getInstructor());
		System.out.println("getCourse.getUrl(): " + getCourse.getUrl());
		System.out.println("getCourse.getServices(): " + getCourse.getServices());
		System.out.println("getCourse.getExpertise(): " + getCourse.getExpertise());
		System.out.println("getCourse.getLinkedIn(): " + getCourse.getLinkedIn());
		System.out.println("************************************************************************");
		
		
		_01_countOfCourses(getCourse);	
		
		_03_TitleOfFirstWebAutomationCourse(getCourse);
		
		_04_getTitleAndPriceOfAllWebAutomationCourses(getCourse);
		
		
		System.out.println("************************************************************************");
		 print_ResponseDetails(response);
		 Assert.assertFalse(response.asString().contains("AUTHENTICATION FAILED !!!! PLEASE ENTER VALID ACCESS TOKEN"),
		 "Response should not have message - AUTHENTICATION FAILED !!!! PLEASE ENTER VALID ACCESS TOKEN");

	}

	private void _04_getTitleAndPriceOfAllWebAutomationCourses(GetCourse getCourse) {
		System.out.println("Title and Price of All Web Automation Course: ");
		List<WebAutomation> listOfWebAutomationCourses = getCourse.getCourses().getWebAutomation();
		for(int i=0;i<listOfWebAutomationCourses.size();i++) {
			System.out.println((i+1)+". - Title: "+listOfWebAutomationCourses.get(i).getCourseTitle());
			System.out.println((i+1)+". - Price: "+listOfWebAutomationCourses.get(i).getPrice());
		}
		System.out.println("************************************************************************");
	}

	private void _03_TitleOfFirstWebAutomationCourse(GetCourse getCourse) {
		String titleOfFirstWebAutomationCourse=	getCourse.getCourses().getWebAutomation().get(0).getCourseTitle();
		System.out.println("Title of First Web Automation Course: "+titleOfFirstWebAutomationCourse);
		System.out.println("************************************************************************");
	}

	private void _01_countOfCourses(GetCourse getCourse) {
		List<WebAutomation> listOfWebAutomation_Courses=getCourse.getCourses().getWebAutomation();
		List<API> listOfAPI_Courses=getCourse.getCourses().getApi();
		List<Mobile> listOfMobile_Courses=getCourse.getCourses().getMobile();
		
		System.out.println("Count of Automation courses: ");
		System.out.println("Web: "+listOfWebAutomation_Courses.size());
		System.out.println("API: "+listOfAPI_Courses.size());
		System.out.println("Mobile"+listOfMobile_Courses.size());
		System.out.println("************************************************************************");
	}

	private String hitGET_Req_ToGetAccessToken(String codeValue) {
		/*
		 * https://www.googleapis.com/oauth2/v4/token?
		 * code=4%2F0AX4XfWhfXGKYAYVLksD8kPOiZWMTTV7o2Cp2SwYfk_hgXEJyI-
		 * oZUQa_kodwVyREIzHboA
		 * &client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.
		 * googleusercontent.com &client_secret=erZOWM9g3UtwNRj340YYaK_W
		 * &redirect_uri=https://rahulshettyacademy.com/getCourse.php
		 * &grant_type=authorization_code#
		 */
		/**
		 * Client: BookMyShow Client ID: ID that identifies the Client Client secret ID:
		 * BookMyShow registers with Google Resource Owner: Human Resource/Authorization
		 * server: Google
		 */

		Response response1 = given().log().all().urlEncodingEnabled(false).header("Content-Type", "application/json")
				.queryParam("code", codeValue).
				// queryParam("code",
				// "4%2F0AX4XfWgbpJVUWVWxIzISs9MwybZPAzSTAFLzkHSO4fSnLTiL6r8e8AxpOcTuKUBC7IHSDg").
				// client_id -> Given by Google to RahulShettyAcademy.com
				queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when()
				.post("https://www.googleapis.com/oauth2/v4/token").then()./* log().all(). */
				// assertThat().statusCode(200).
				extract().response();

		JsonPath jsonPath = new JsonPath(response1.asString());
		System.out.println("************************************************************************");
		String accessToken = jsonPath.get("access_token");
		System.out.println("jsonPath.get(\"access_token\"): " + accessToken);
		System.out.println("************************************************************************");
		print_ResponseDetails(response1);

		return accessToken;
	}

	private String hit_Browser_AndGetCodeValue_FromURL() {

		/*
		 * You need to hit below URL in Browser get the code value from there becasue
		 * Google has blocked the Automation for Gmail Sign in
		 */
		/*
		 * https://accounts.google.com/o/oauth2/v2/auth?
		 * scope=https://www.googleapis.com/auth/userinfo.email
		 * &auth_url=https://accounts.google.com/o/oauth2/v2/auth
		 * &client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.
		 * googleusercontent.com &response_type=code
		 * &redirect_uri=https://rahulshettyacademy.com/getCourse.php
		 */

		/*
		 * Response response = given().log().all(). queryParam("scope",
		 * "https://www.googleapis.com/auth/userinfo.email"). queryParam("auth_url",
		 * "https://accounts.google.com/o/oauth2/v2/auth"). queryParam("client_id",
		 * "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		 * queryParam("response_type", "code"). queryParam("redirect_uri",
		 * "https://rahulshettyacademy.com/getCourse.php").
		 * when().get("https://accounts.google.com/o/oauth2/v2/auth").
		 * then().log().all() .assertThat().statusCode(200) .extract().response();
		 * 
		 * print_ResponseDetails(response);
		 */

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get(
				"https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		driver.findElement(By.id("identifierId")).sendKeys("your-email-address");
		driver.findElement(By.xpath("//span[text()='Next']")).click();

		driver.findElement(By.name("password")).sendKeys("your-password");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlContains("rahulshettyacademy"));
		String currentURL = driver.getCurrentUrl();
		System.out.println("currentURL: " + currentURL);

		String[] parsedURL_Code = currentURL.split("code=");
		for (String str : parsedURL_Code) {
			System.out.println(str);
		}
		String[] parsedURL_codeValue = parsedURL_Code[1].split("&");
		String codeValue = parsedURL_codeValue[0];
		System.out.println(codeValue);
		return codeValue;
	}

	private void print_ResponseDetails(Response response) {
		System.out.println("************************************************************************");
		System.out.println("respone.statusCode(): " + response.statusCode());
		System.out.println("respone.getStatusCode(): " + response.getStatusCode());
		System.out.println("************************************************************************");
		System.out.println("respone.timeIn(TimeUnit.MILLISECONDS): " + response.timeIn(TimeUnit.MILLISECONDS));
		System.out.println("respone.getTimeIn(TimeUnit.MILLISECONDS): " + response.getTimeIn(TimeUnit.MILLISECONDS));
		System.out.println("************************************************************************");
		response.prettyPrint();
		System.out.println("************************************************************************");
	}

}
