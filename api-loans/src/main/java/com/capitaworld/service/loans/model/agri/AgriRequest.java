package com.capitaworld.service.loans.model.agri;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AgriRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long applicationId;
	private Long userId;
	private RetailApplicantRequest applicantRequest;
	private PrimaryAgriLoanDetailRequest primaryRequest;
	private List<CropDetailRequest> cropDetailRequests;
	private List<FinancialArrangementsDetailRequest> arrangementsDetailRequests;
	
	public AgriRequest() {
		this.cropDetailRequests = Collections.emptyList();
		this.arrangementsDetailRequests = Collections.emptyList();
	}

	public RetailApplicantRequest getApplicantRequest() {
		return applicantRequest;
	}

	public void setApplicantRequest(RetailApplicantRequest applicantRequest) {
		this.applicantRequest = applicantRequest;
	}

	public PrimaryAgriLoanDetailRequest getPrimaryRequest() {
		return primaryRequest;
	}

	public void setPrimaryRequest(PrimaryAgriLoanDetailRequest primaryRequest) {
		this.primaryRequest = primaryRequest;
	}

	public List<CropDetailRequest> getCropDetailRequests() {
		return cropDetailRequests;
	}

	public void setCropDetailRequests(List<CropDetailRequest> cropDetailRequests) {
		this.cropDetailRequests = cropDetailRequests;
	}

	public List<FinancialArrangementsDetailRequest> getArrangementsDetailRequests() {
		return arrangementsDetailRequests;
	}

	public void setArrangementsDetailRequests(List<FinancialArrangementsDetailRequest> arrangementsDetailRequests) {
		this.arrangementsDetailRequests = arrangementsDetailRequests;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}

