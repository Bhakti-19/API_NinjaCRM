package tests;

import java.io.File;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.AddUserPojo;
import utils.RestUtils;

public class ApiPortalTest extends BaseTest {
	
	@BeforeTest
	public void initialize() {
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	}
	
	@BeforeMethod
	public void generateTokenForTests() {
//		BaseTest.generateToken();
	}
	
	
//	@Test
	public void loginTest_TC01() {
		String filepath = System.getProperty("user.dir") + "/src/main/java/schemaValidations/loginResponseSchema.json";
		String payload = "{\"username\": \"mithun@ta.com\", \"password\": \"mithun\"}";
//		JsonObject jsonObject = new JsonObject();
//		jsonObject.addProperty("username", "mithun@ta.com");
//		jsonObject.addProperty("password", "mithun");
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		Response loginRes = RestUtils.postReq(headers, payload, "/login");
//		long responseTime = loginRes.getTimeIn(TimeUnit.MILLISECONDS);
		RestUtils.validateSchema(loginRes, filepath);
		Assert.assertEquals(loginRes.statusCode(), 201);
	}
	
	@Test
	public void addUserTest_TC02() throws JsonProcessingException {
		AddUserPojo userInfo = new AddUserPojo("TA-46577", "5", "6784", "67847");
		
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		String payload = objMapper.writeValueAsString(userInfo);
//		AddUserPojo user = objMapper.writer().
		
		
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("token", BaseTest.generateToken());
		Response getUsersResponse = RestUtils.postReq(headers, payload, "/addData");
		Assert.assertEquals(getUsersResponse.statusCode(), 201);
	}

	public static void main(String[] args) {
//		https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net
//		HashMap<String, String> addUserheaders = new HashMap<String, String>();
//		addUserheaders.put("Content-Type", "application/json");
//		addUserheaders.put("token", token);
//
//		Response addUserRes = RestAssured.given()
//				.headers(addUserheaders)
//				.when()
//				.body("{\"accountno\": \"TA-6789854\", \"departmentno\": \"6\", \"salary\": \"4522\", \"pincode\": \"455566\"}")
//				.post("/addData");
//		
//		addUserRes.prettyPrint();
	}

}
