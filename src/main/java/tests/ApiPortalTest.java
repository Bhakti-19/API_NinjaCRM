package tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import constants.FileConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.AddUserPojo;
import utils.DataUtils;
import utils.RestUtils;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class ApiPortalTest extends BaseTest {

	@BeforeTest
	public void initialize() {
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	}

	@BeforeMethod
	public void generateTokenForTests() {
//		BaseTest.generateToken();
	}

	@Test
	public void loginTest_TC01() throws IOException {
		Object payload = DataUtils.getTestData("$.payloads.login");
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		Response loginRes = RestUtils.postReq(headers, payload, DataUtils.getTestData("$.endpoints.login").toString());
		RestUtils.validateSchema(loginRes, FileConstants.LOGIN_SCHEMA_FILE_PATH);
//		Assert.assertEquals(loginRes.statusCode(), 201);
		assertEquals(loginRes.statusCode(), 201);
	}

//	@Test
	public void addUserTest_TC02() throws JsonProcessingException {
		AddUserPojo userInfo = new AddUserPojo("TA-46577", "5", "6784", "67847");

		ObjectMapper objMapper = new ObjectMapper();
		objMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		String payload = objMapper.writeValueAsString(userInfo);
//		AddUserPojo user = objMapper.writer().

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("token", BaseTest.generateToken());
		Response addUsersResponse = RestUtils.postReq(headers, payload, "/addData");
		Assert.assertEquals(addUsersResponse.statusCode(), 201);
	}

//	@Test
	public void getUserDataTest_TC02() throws JsonProcessingException {

		ObjectMapper objMapper = new ObjectMapper();
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("token", BaseTest.generateToken());
		Response getUsersResponse = RestUtils.getReq(headers, "/getdata");
		String data = getUsersResponse.jsonPath().get("[0]");
		System.out.println(data);
		objMapper.readValue(data, AddUserPojo.class);
		Assert.assertEquals(getUsersResponse.statusCode(), 200);
	}
}
