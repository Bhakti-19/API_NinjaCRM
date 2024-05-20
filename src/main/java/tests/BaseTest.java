package tests;

import java.util.HashMap;

import io.restassured.response.Response;
import utils.RestUtils;

public class BaseTest {
	
	
	
	
	public static String generateToken() {
		String token = "";
		String payload = "{\"username\": \"mithun@ta.com\", \"password\": \"mithun\"}";
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		Response loginRes = RestUtils.postReq(headers, payload, "/login");
		token = loginRes.jsonPath().get("[0].token");
		return token;
	}
	

}
