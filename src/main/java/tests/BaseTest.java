package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.DataUtils;
import utils.RestUtils;

public class BaseTest {
	
//RestUtils request = null;
	
	@BeforeTest
	public void setUp() {
		RestUtils request= new RestUtils();
		RestAssured.baseURI = "http://49.249.28.218:8098";
		
		// To resolve ssl certificate error
		//RestAssured.useRelaxedHTTPSValidation();
		}
	
	@Test
	public static void login() throws IOException {
		
		/*String payload = "{\"username\": \"rmgyantra\", \"password\": \"rmgy@9999\"}";
		//Object payload = "{\"username\": \"rmgyantra\", \"password\": \"rmgy@9999\"}";
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		Response loginRes = RestUtils.getLoginReq(headers,payload, DataUtils.getTestData("$.endpoints.login").toString());
		assertEquals(loginRes.statusCode(), 202);*/
	
	/*	Object payload = DataUtils.getTestData("$.payloads.login");
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		Response loginRes = RestUtils.postReq(headers, payload, "/login");//DataUtils.getTestData("$.endpoints.login").toString());
		//System.out.println( loginRes.toString());
		System.out.println(" Logged in successfully!");
		assertEquals(loginRes.statusCode(), 201);
		//System.out.println(" Logged in successfully!");*/
				
		        // Step 1: Login (Basic Auth Example)
		        Response loginResponse = RestAssured
		                .given()
		                .auth().preemptive().basic("rmgyantra", "rmgy@9999")
		                .contentType(ContentType.JSON)
		                .when()
		                .get("/login");

		        // Verify login success (status code 200 or 202 based on API design)
		        Assert.assertEquals(loginResponse.getStatusCode(), 202, "Login failed!");

		}
	

}
