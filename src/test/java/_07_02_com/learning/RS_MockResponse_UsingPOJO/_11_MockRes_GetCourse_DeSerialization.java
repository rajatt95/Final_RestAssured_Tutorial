package _07_02_com.learning.RS_MockResponse_UsingPOJO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.google.gson.Gson;

import _07_01_com.learning.RS_MockResponse_POJO_Classes.API;
import _07_01_com.learning.RS_MockResponse_POJO_Classes.GetCourse;
import io.restassured.response.Response;

public class _11_MockRes_GetCourse_DeSerialization {

	@Test
	public void TC_11_MockRes_GetCourse_DeSerialization() {

		
		
		String mockReponseInStringFormat=get_MockResponseFromJSON_File();
		
		//Converting String format to JSON object using GSON library
		GetCourse getCourse = new Gson().fromJson(mockReponseInStringFormat, GetCourse.class); 
		
		System.out.println("************************************************************************");
		System.out.println("getCourse.getInstructor(): " + getCourse.getInstructor());
		System.out.println("getCourse.getUrl(): " + getCourse.getUrl());
		System.out.println("getCourse.getServices(): " + getCourse.getServices());
		System.out.println("getCourse.getExpertise(): " + getCourse.getExpertise());
		System.out.println("getCourse.getLinkedIn(): " + getCourse.getLinkedIn());
		System.out.println("************************************************************************");
		List<API> listOfAPICourses = getCourse.getCourses().getApi();
		for (int i = 0; i < listOfAPICourses.size(); i++) {
			String courseTitle=listOfAPICourses.get(i).getCourseTitle();
			System.out.println("listOfAPICourses.get(" + i + ").getCourseTitle(): " + courseTitle);
			if(courseTitle.equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println("SoapUI Webservices testing - PRICE: " + listOfAPICourses.get(i).getPrice());
				break;
			}
			
		}
		System.out.println("************************************************************************");

		 //print_ResponseDetails(response);
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
	
	
	private String get_MockResponseFromJSON_File() {

		/* Content of the JSON file --> Bytes --> String */

		String resourcesPath = System.getProperty("user.dir");
		Path path = Paths.get(resourcesPath + "/src/test/resources/MockResponse_GetCourse.json");
		try {
			System.out.println("*******************************************");
			System.out.println(new String(Files.readAllBytes(path)));
			System.out.println("*******************************************");
			return new String(Files.readAllBytes(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
