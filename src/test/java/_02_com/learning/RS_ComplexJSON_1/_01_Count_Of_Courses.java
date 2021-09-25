package _02_com.learning.RS_ComplexJSON_1;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class _01_Count_Of_Courses {

	@Test
	public void TC_01_Count_Of_Courses() {

		String response = Payload_ComplexJSON_1.getComplexJSON_1();

		System.out.println(response);
		System.out.println("**********************************************************************");

		JsonPath jsonPath = new JsonPath(response);
		System.out.println(jsonPath);
		System.out.println("**********************************************************************");

		/* RS way */
		int count_courses = jsonPath.getInt("courses.size()");
		System.out.println("RS - count_courses: " + count_courses);
		//size() -> can be applied only in Arrays (we knew that courses is an Array in our case)
		System.out.println("**********************************************************************");

		/* Rajat way */
		List<Object> listOfCourses = jsonPath.getList("courses");
		System.out.println("Rajat - listOfCourses.size(): " + listOfCourses.size());

	}

}
