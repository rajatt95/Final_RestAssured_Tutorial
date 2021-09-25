package _05_com.learning.RS_OAuth;

public class Rough {

	public static void main(String[] args) {
		
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWi8ms-J0pwthIbwY1_NdsvC91nkQ4ceP26btDAqj9CfklW0S0EQY2dIbX7_5MXD2w&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=2&prompt=none#";
		
		String[] parsedURL_Code =url.split("code=");
		
		for(String str:parsedURL_Code) {
			System.out.println(str);
		}
		
		String[] parsedURL_codeValue=parsedURL_Code[1].split("&");
		System.out.println(parsedURL_codeValue[0]);
	}
}
