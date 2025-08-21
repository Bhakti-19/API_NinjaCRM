package utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddUserPojo {
	
	private String campaignId;
	private String campaignName;
	private String campaignStatus;
	private String description;
	private String expectedCloseDate;
	private String targetAudience;
	private String targetSize;
		
	
	public AddUserPojo(String accountno, String departmentno, String salary, String pincode) {
		this.campaignId = campaignId;
		this.campaignName = campaignName;
		this.campaignStatus = campaignStatus;
		this.targetSize = targetSize;
		this.expectedCloseDate = expectedCloseDate;
		this.targetAudience = targetAudience;
		this.description = description;
		
	}
	@JsonProperty("wrapper")
	public String getCampaignId() {
		return campaignId;
	}
	
	@JsonProperty("wrapper")
	public String getCampaignName() {
		return campaignName;
	}
	
	@JsonProperty("wrapper")
	public String getCampaignStatus() {
		return campaignStatus;
	}	

	@JsonProperty("wrapper")
	public String gettargetSize() {
		return targetSize;
	}
	
	@JsonProperty("wrapper")
	public String getExpectedCloseDate() {
		return expectedCloseDate;
	}	

	@JsonProperty("wrapper")
	public String getTargetAudience() {
		return targetAudience;
	}
	
	@JsonProperty("wrapper")
	public String getDescription() {
		return description;
	}
		

}
