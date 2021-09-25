package _02_com.learning.RS_ComplexJSON_1;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class _03_TitleOfFirstCourse {

	@Test
	public void TC_03_TitleOfFirstCourse() {

		String response = Payload_ComplexJSON_1.getComplexJSON_1();

		System.out.println(response);
		System.out.println("**********************************************************************");

		JsonPath jsonPath = new JsonPath(response);
		System.out.println(jsonPath);
		System.out.println("**********************************************************************");

		String title_Of_FirstCourse = jsonPath.get("courses[0].title");
		System.out.println("Title of First Course: " + title_Of_FirstCourse);
		System.out.println("**********************************************************************");

	}

}
