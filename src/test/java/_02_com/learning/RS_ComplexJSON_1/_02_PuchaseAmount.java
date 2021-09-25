package _02_com.learning.RS_ComplexJSON_1;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class _02_PuchaseAmount {

	@Test
	public void TC_02_PuchaseAmount() {

		String response = Payload_ComplexJSON_1.getComplexJSON_1();

		System.out.println(response);
		System.out.println("**********************************************************************");

		JsonPath jsonPath = new JsonPath(response);
		System.out.println(jsonPath);
		System.out.println("**********************************************************************");

		int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase amount: " + purchaseAmount);
		System.out.println("**********************************************************************");

	}

}
