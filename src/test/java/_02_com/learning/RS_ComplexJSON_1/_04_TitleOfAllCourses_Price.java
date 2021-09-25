package _02_com.learning.RS_ComplexJSON_1;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class _04_TitleOfAllCourses_Price {

	@Test
	public void TC_04_TitleOfAllCourses_Price() {

		String response = Payload_ComplexJSON_1.getComplexJSON_1();

		System.out.println(response);
		System.out.println("**********************************************************************");

		JsonPath jsonPath = new JsonPath(response);
		System.out.println(jsonPath);
		System.out.println("**********************************************************************");

		List<Object> listOfCourses = jsonPath.getList("courses");
		System.out.println("Rajat - listOfCourses.size(): " + listOfCourses.size());
		for (int i = 0; i < listOfCourses.size(); i++) {
			System.out.println("---------------");

			String title = jsonPath.get("courses[" + i + "].title");
			System.out.println("Title: " + title);

			int price = jsonPath.get("courses[" + i + "].price");
			System.out.println("Price: " + price);

		}

		System.out.println("**********************************************************************");
	}

}
