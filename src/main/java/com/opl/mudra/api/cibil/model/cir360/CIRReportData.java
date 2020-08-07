package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "IDAndContactInfo", "retailAccountDetails", "retailAccountsSummary", "microfinanceAccountDetails",
		"microfinanceAccountsSummary", "incomeDetails", "familyDetailsInfo", "scoreDetails", "scoreAttributes", "enquiries",
		"enquirySummary", "overallAccountsSummary", "otherKeyInd", "recentActivities", "consumerDisputes", "Decision" })
public class CIRReportData {

	@JsonProperty("IDAndContactInfo")
	private IDAndContactInfo idAndContactInfo;

	@JsonProperty("RetailAccountDetails")
	private List<RetailAccountDetailsType> retailAccountDetails;

	@JsonProperty("RetailAccountsSummary")
	private AccountSummary retailAccountsSummary;

	@JsonProperty("MicrofinanceAccountDetails")
	private List<MFIAccountDetailsType> microfinanceAccountDetails;

	@JsonProperty("MicrofinanceAccountsSummary")
	private AccountSummary microfinanceAccountsSummary;

	@JsonProperty("IncomeDetails")
	private List<IncomeDetails> incomeDetails;

	@JsonProperty("familyDetailsInfo")
	private FamilyDetailsInfo familyDetailsInfo;

	@JsonProperty("ScoreDetails")
	private List<ScoreDetailsType> scoreDetails;

	@JsonProperty("ScoreAttributes")
	private List<ScoreAttributes> scoreAttributes;

	@JsonProperty("Enquiries")
	private List<EnquiryType> enquiries;

	@JsonProperty("EnquirySummary")
	private EnquirySummary enquirySummary;

	@JsonProperty("OverallAccountsSummary")
	private OverallAccountSummary overallAccountsSummary;

	@JsonProperty("OtherKeyInd")
	private OtherKeyInd otherKeyInd;

	@JsonProperty("RecentActivities")
	private RecentActivities recentActivities;

	@JsonProperty("ConsumerDisputes")
	private List<ConsumerDisputes> consumerDisputes;
	
	@JsonProperty("Decision")
	private List<RulesDecision> rulesDecisions;

	public List<ScoreAttributes> getScoreAttributes() {
		return scoreAttributes;
	}

	public void setScoreAttributes(List<ScoreAttributes> scoreAttributes) {
		this.scoreAttributes = scoreAttributes;
	}

	public List<ConsumerDisputes> getConsumerDisputes() {
		return consumerDisputes;
	}

	public void setConsumerDisputes(List<ConsumerDisputes> consumerDisputes) {
		this.consumerDisputes = consumerDisputes;
	}

	public List<IncomeDetails> getIncomeDetails() {
		return incomeDetails;
	}

	public void setIncomeDetails(List<IncomeDetails> incomeDetails) {
		this.incomeDetails = incomeDetails;
	}

	

	public FamilyDetailsInfo getFamilyDetailsInfo() {
		return familyDetailsInfo;
	}

	public void setFamilyDetailsInfo(FamilyDetailsInfo familyDetailsInfo) {
		this.familyDetailsInfo = familyDetailsInfo;
	}

	public OverallAccountSummary getOverallAccountsSummary() {
		return overallAccountsSummary;
	}

	public void setOverallAccountsSummary(OverallAccountSummary overallAccountsSummary) {
		this.overallAccountsSummary = overallAccountsSummary;
	}

	public AccountSummary getRetailAccountsSummary() {
		return retailAccountsSummary;
	}

	public void setRetailAccountsSummary(AccountSummary retailAccountsSummary) {
		this.retailAccountsSummary = retailAccountsSummary;
	}

	public AccountSummary getMicrofinanceAccountsSummary() {
		return microfinanceAccountsSummary;
	}

	public void setMicrofinanceAccountsSummary(AccountSummary microfinanceAccountsSummary) {
		this.microfinanceAccountsSummary = microfinanceAccountsSummary;
	}

	public List<RetailAccountDetailsType> getRetailAccountDetails() {
		if (retailAccountDetails == null) {
			retailAccountDetails = new ArrayList<RetailAccountDetailsType>();
		}
		return retailAccountDetails;
	}

	public void setRetailAccountDetails(List<RetailAccountDetailsType> retailAccountDetails) {
		this.retailAccountDetails = retailAccountDetails;
	}

	public List<MFIAccountDetailsType> getMicrofinanceAccountDetails() {

		if (microfinanceAccountDetails == null) {
			microfinanceAccountDetails = new ArrayList<MFIAccountDetailsType>();
		}
		return microfinanceAccountDetails;
	}

	public void setMicrofinanceAccountDetails(List<MFIAccountDetailsType> microfinanceAccountDetails) {
		this.microfinanceAccountDetails = microfinanceAccountDetails;
	}

	public IDAndContactInfo getIdAndContactInfo() {
		return idAndContactInfo;
	}

	public void setIdAndContactInfo(IDAndContactInfo idAndContactInfo) {
		this.idAndContactInfo = idAndContactInfo;
	}

	public List<ScoreDetailsType> getScoreDetails() {
		if (scoreDetails == null) {
			scoreDetails = new ArrayList<ScoreDetailsType>();
		}
		return scoreDetails;
	}

	public void setScoreDetails(List<ScoreDetailsType> scoreDetails) {
		this.scoreDetails = scoreDetails;
	}

	public RecentActivities getRecentActivities() {
		return recentActivities;
	}

	public void setRecentActivities(RecentActivities recentActivities) {
		this.recentActivities = recentActivities;
	}

	public OtherKeyInd getOtherKeyInd() {
		return otherKeyInd;
	}

	public void setOtherKeyInd(OtherKeyInd otherKeyInd) {
		this.otherKeyInd = otherKeyInd;
	}

	public EnquirySummary getEnquirySummary() {
		return enquirySummary;
	}

	public void setEnquirySummary(EnquirySummary enquirySummary) {
		this.enquirySummary = enquirySummary;
	}

	public List<EnquiryType> getEnquiries() {
		if (enquiries == null) {
			enquiries = new ArrayList<EnquiryType>();
		}
		return enquiries;
	}

	public void setEnquiries(List<EnquiryType> enquiries) {
		this.enquiries = enquiries;
	}

	public List<RulesDecision> getRulesDecisions() {
		if(null == rulesDecisions) {
			rulesDecisions = new ArrayList<RulesDecision>();
		}
		return rulesDecisions;
	}

	public void setRulesDecisions(List<RulesDecision> rulesDecisions) {
		this.rulesDecisions = rulesDecisions;
	}

}
