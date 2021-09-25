package _02_com.learning.RS_ComplexJSON_1;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class _05_CopiesSoldByRPA {

	@Test
	public void TC_05_CopiesSoldByRPA() {

		String response = Payload_ComplexJSON_1.getComplexJSON_1();

		System.out.println(response);
		System.out.println("**********************************************************************");

		JsonPath jsonPath = new JsonPath(response);
		System.out.println(jsonPath);
		System.out.println("**********************************************************************");

		List<Object> listOfCourses = jsonPath.getList("courses");
		System.out.println("Rajat - listOfCourses.size(): " + listOfCourses.size());
		System.out.println("**********************************************************************");

		for (int i = 0; i < listOfCourses.size(); i++) {
			// System.out.println("---------------");

			String title = jsonPath.get("courses[" + i + "].title");
			// System.out.println("Title: " + title);

			if (title.equalsIgnoreCase("RPA")) {
				int copies = jsonPath.get("courses[" + i + "].copies");
				System.out.println("Copies sold by RPA: " + copies);
				break;
			}
		}

		System.out.println("**********************************************************************");

	}

}
