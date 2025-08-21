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
	public void initialize() throws IOException {
		RestAssured.baseURI = "http://49.249.28.218:8098";		
		BaseTest.login();
	}

//	@Test (enabled =false)
//	public void loginTest_TC01() throws IOException {
//		Object payload = DataUtils.getTestData("$.payloads.login");
//		HashMap<String, String> headers = new HashMap<String, String>();
//		headers.put("Content-Type", "application/json");
//		Response loginRes = RestUtils.postReq(headers, payload, DataUtils.getTestData("$.endpoints.login").toString());  // DataUtils.getTestData("$.endpoints.login").toString());
//		//RestUtils.validateSchema(loginRes, FileConstants.LOGIN_SCHEMA_FILE_PATH);
//		//Assert.assertEquals(loginRes.statusCode(), 201);
//		assertEquals(loginRes.statusCode(), 201);
//	}

//	@Test
//	public void addUserTest_TC02() throws JsonProcessingException {
//		AddUserPojo userInfo = new AddUserPojo("TA-46577", "5", "6784", "67847");
//
//		ObjectMapper objMapper = new ObjectMapper();
//		objMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
//		String payload = objMapper.writeValueAsString(userInfo);
//		AddUserPojo user = objMapper.writer().
//
//		HashMap<String, String> headers = new HashMap<String, String>();
//		headers.put("Content-Type", "application/json");
//		//headers.put("token", BaseTest.generateToken());
//		Response addUsersResponse = RestUtils.postReq(headers, payload, "/addData");
//		Assert.assertEquals(addUsersResponse.statusCode(), 201);
//	}

//	@Test
//	public void getUserDataTest_TC02() throws JsonProcessingException {
//
//		ObjectMapper objMapper = new ObjectMapper();
//		HashMap<String, String> headers = new HashMap<String, String>();
//		headers.put("Content-Type", "application/json");
//	//	headers.put("token", BaseTest.generateToken());
//		Response getUsersResponse = RestUtils.getReq(headers, "/getdata");
//		String data = getUsersResponse.jsonPath().get("[0]");
//		System.out.println(data);
//		objMapper.readValue(data, AddUserPojo.class);
//		Assert.assertEquals(getUsersResponse.statusCode(), 200);
//	}
	//@Test (enabled = true)
	public void TC1_Get_Campaign_with_Ten_the_records() throws IOException {
	
		ObjectMapper objMapper = new ObjectMapper();
		//String endPoint = DataUtils.getTestData("$.endpoints.getdata");
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json"); 
		Response getUsersResponse = RestUtils.getReq(headers, DataUtils.getTestData("$.endpoints.getdata").toString());//DataUtils.getTestData("$.endpoints.getdata").toString());
	
		System.out.println(getUsersResponse.asPrettyString());
		String getUser1 = objMapper.writeValueAsString(getUsersResponse.jsonPath().get("[2]"));
		//System.out.println(getUser1);
	//	DeserializeExample ds = objMapper.readValue(getUser1,DeserializeExample.class); 
		assertThat("Expecting 200 status code ", 200==getUsersResponse.getStatusCode());
        Assert.assertEquals(getUsersResponse.statusCode(), 200);
	}
	//public void TC2_Get_Records_with_Invalid_Credentials() {} 
	@Test
	public void TC3_Successful_Campaign_Creation() throws JsonProcessingException, IOException {
		
		//AddUserPojo userInfo = new AddUserPojo("TA-46577", "5", "6784", "67847");
		
				ObjectMapper objMapper = new ObjectMapper();
				objMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			    String payload = objMapper.writeValueAsString(DataUtils.getTestData("$.payloads.addUser"));
			    System.out.println(payload);		
				HashMap<String, String>  headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");
				//String path1 = objMapper.writeValueAsString(DataUtils.getTestData("$.endpoints.adddata").toString());
				Response addUsersResponse = RestUtils.postReq(headers, payload, DataUtils.getTestData("$.endpoints.adddata").toString());
				System.out.println("Response Body: " + addUsersResponse.getBody().asString());
				Assert.assertEquals(addUsersResponse.statusCode(), 201);
			
	}
	/*public void TC4_Create_records_with_mandatory_fields() {}
	public void TC5_Create_record_with_Size_lessthan_1() {}
	public void TC6_Create_record_without_mandatory_field_Name() {}
	public void TC7_Create_record_without_mandatory_field_Size() {}
	public void TC8_Update_Campaign_with_mandatory_field_change_in_Name() {}
	public void TC9_Update_Campaign_with_mandatory_field_change_in_Size() {}
	public void TC10_Update_Campaign_with_date() {}
	public void TC11_Update_Campaign_with_Status() {}
	public void TC12_Update_Campaign_with_TargetAudience() {} 
	public void TC13_Delete_Campaign_Using_Name() {}
	public void TC14_Delete_Campaign_using_ID() {}
	public void TC15_Before_delete_Update() {}
	public void TC16_Delete_Campaign_after_Updation() {} 
	public void TC17_Check_Pagination() {}
	public void TC18_Duplicate_Campaign_name() {}
	public void TC19_Create_record_with_Zero_Size() {}
	public void TC20_Update_Campaign_with_mandatory_field_change_in_Size() {}
	public void TC21_Create_Record_with_Invalid_Date() {}
	public void TC22_Create_Campaign_with_empty_fields() {}
	public void TC23_Delete_nonexisted_ID() {}
	public void TC24_Campaign_name_with_invalid_datatypes() {}*/

}
