package _02_com.learning.RS_ComplexJSON_1;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class _06_SumOfAllPrices_MustMatch_PurchaseAmount {

	@Test
	public void TC_06_SumOfAllPrices_MustMatch_PurchaseAmount() {

		String response = Payload_ComplexJSON_1.getComplexJSON_1();

		System.out.println(response);
		System.out.println("**********************************************************************");

		JsonPath jsonPath = new JsonPath(response);
		System.out.println(jsonPath);
		System.out.println("**********************************************************************");
		
		List<Object> listOfCourses = jsonPath.getList("courses");
		System.out.println("Rajat - listOfCourses.size(): " + listOfCourses.size());
		System.out.println("**********************************************************************");
		
		int expectedPurchaseAmount = 910;
		int actualPurchaseAmount = 0;

		for (int i = 0; i < listOfCourses.size(); i++) {
			System.out.println("---------------");
			int price = jsonPath.get("courses[" + i + "].price");
			System.out.println("Price: " + price);
			int copies = jsonPath.get("courses[" + i + "].copies");
			System.out.println("Copies: " + copies);
			String title = jsonPath.get("courses[" + i + "].title");
			int purchaseAmount = price * copies;
			System.out.println("Purchase amount for : " + title + " = " + purchaseAmount);
			actualPurchaseAmount = actualPurchaseAmount + (price * copies);
		}
		
		System.out.println("**********************************************************************");
		
		System.out.println("Actual Purchase Amount: " + actualPurchaseAmount);
		System.out.println("Expected Purchase Amount: " + expectedPurchaseAmount);
		Assert.assertEquals(actualPurchaseAmount, expectedPurchaseAmount);
	}
}
