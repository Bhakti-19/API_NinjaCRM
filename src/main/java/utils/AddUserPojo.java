package utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddUserPojo {
	
	private String accountno;
	private String departmentno;
	private String salary;
	private String pincode;
//	private String userid;
//	private String id;
	
	
	public AddUserPojo(String accountno, String departmentno, String salary, String pincode) {
		this.accountno = accountno;
		this.departmentno = departmentno;
		this.salary = salary;
		this.pincode = pincode;
//		this.userid = userid;
//		this.id = id;
	}
	@JsonProperty("wrapper")
	public String getAccountNo() {
		return accountno;
	}
	
	@JsonProperty("wrapper")
	public String getSalary() {
		return salary;
	}
	
	@JsonProperty("wrapper")
	public String getPincode() {
		return pincode;
	}
	
	@JsonProperty("wrapper")
	public String getDepartmentNo() {
		return departmentno;
	}
//	public String getuserid() {
//		return userid;
//	}
//	public String getid() {
//		return id;
//	}
	
	

}
