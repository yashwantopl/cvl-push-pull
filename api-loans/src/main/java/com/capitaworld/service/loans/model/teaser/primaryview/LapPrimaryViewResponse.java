package com.capitaworld.service.loans.model.teaser.primaryview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LapPrimaryViewResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RetailProfileViewResponse  applicant;
    private List<RetailProfileViewResponse> coApplicantList = new ArrayList<RetailProfileViewResponse>();
    private List<RetailProfileViewResponse> guarantorList = new ArrayList<RetailProfileViewResponse>();
    
    private LapResponse lapResponse;

	public RetailProfileViewResponse getApplicant() {
		return applicant;
	}

	public void setApplicant(RetailProfileViewResponse applicant) {
		this.applicant = applicant;
	}

	public List<RetailProfileViewResponse> getCoApplicantList() {
		return coApplicantList;
	}

	public void setCoApplicantList(List<RetailProfileViewResponse> coApplicantList) {
		this.coApplicantList = coApplicantList;
	}

	public List<RetailProfileViewResponse> getGuarantorList() {
		return guarantorList;
	}

	public void setGuarantorList(List<RetailProfileViewResponse> guarantorList) {
		this.guarantorList = guarantorList;
	}

	public LapResponse getLapResponse() {
		return lapResponse;
	}

	public void setLapResponse(LapResponse lapResponse) {
		this.lapResponse = lapResponse;
	}
    
    
	
}
