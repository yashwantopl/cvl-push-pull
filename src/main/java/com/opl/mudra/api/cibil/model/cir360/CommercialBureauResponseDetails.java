package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommercialBureauResponseDetails {

	@JsonProperty("IDAndContactInfo")
	private CommercialIDAndContactInfo commercialIDAndContactInfo;

	@JsonProperty("ScoreDetails")
	private List<ScoreDetailsType> scoreDetails;

	@JsonProperty("CreditReportSummary")
	private CommercialCreditReportSummary commercialCreditReportSummary;

	@JsonProperty("CreditFacilityDetails")
	private List<CommercialAccountDetails> commercialAccountDetails;

	@JsonProperty("CreditFacilityAsGuarantor")
	private List<CommercialAccountDetails> commercialAccountDetailsAsGuarantor;

	@JsonProperty("RelationshipDetails")
	private List<AccountRelationDetails> relationshipDetails;

	@JsonProperty("EnquirySummary")
	private EnquirySummary enquirySummary;

	@JsonProperty("RecentEnquiries")
	private List<EnquiryType> enquiries;

	public CommercialIDAndContactInfo getCommercialIDAndContactInfo() {
		return commercialIDAndContactInfo;
	}

	public void setCommercialIDAndContactInfo(CommercialIDAndContactInfo commercialIDAndContactInfo) {
		this.commercialIDAndContactInfo = commercialIDAndContactInfo;
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

	public CommercialCreditReportSummary getCommercialCreditReportSummary() {
		return commercialCreditReportSummary;
	}

	public void setCommercialCreditReportSummary(CommercialCreditReportSummary commercialCreditReportSummary) {
		this.commercialCreditReportSummary = commercialCreditReportSummary;
	}

	public List<CommercialAccountDetails> getCommercialAccountDetails() {
		if (commercialAccountDetails == null) {
			commercialAccountDetails = new ArrayList<CommercialAccountDetails>();
		}
		return commercialAccountDetails;
	}

	public void setCommercialAccountDetails(List<CommercialAccountDetails> commercialAccountDetails) {
		this.commercialAccountDetails = commercialAccountDetails;
	}

	public List<AccountRelationDetails> getRelationshipDetails() {
		if (relationshipDetails == null) {
			relationshipDetails = new ArrayList<AccountRelationDetails>();
		}
		return relationshipDetails;
	}

	public void setRelationshipDetails(List<AccountRelationDetails> relationshipDetails) {
		this.relationshipDetails = relationshipDetails;
	}

	public EnquirySummary getEnquirySummary() {
		return enquirySummary;
	}

	public void setEnquirySummary(EnquirySummary enquirySummary) {
		this.enquirySummary = enquirySummary;
	}

	public List<EnquiryType> getEnquiries() {
		return enquiries;
	}

	public void setEnquiries(List<EnquiryType> enquiries) {
		this.enquiries = enquiries;
	}

	public List<CommercialAccountDetails> getCommercialAccountDetailsAsGuarantor() {
		return commercialAccountDetailsAsGuarantor;
	}

	public void setCommercialAccountDetailsAsGuarantor(
			List<CommercialAccountDetails> commercialAccountDetailsAsGuarantor) {
		this.commercialAccountDetailsAsGuarantor = commercialAccountDetailsAsGuarantor;
	}

}