package _03_com.learning.RS_LibraryAPI;

public class Payload_LibraryAPI {

	static public String getAddBook(String isbn, String aisle) {
		return "{\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	
	
}
