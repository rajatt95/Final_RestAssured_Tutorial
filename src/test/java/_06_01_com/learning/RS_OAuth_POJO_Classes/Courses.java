package _06_01_com.learning.RS_OAuth_POJO_Classes;

import java.util.List;

public class Courses {

	private List<WebAutomation> webAutomation;
	private List<API> api;
	private List<Mobile> mobile;
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<API> getApi() {
		return api;
	}
	public void setApi(List<API> api) {
		this.api = api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}

}
