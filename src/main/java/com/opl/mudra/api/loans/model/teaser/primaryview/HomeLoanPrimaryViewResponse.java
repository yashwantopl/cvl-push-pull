package com.opl.mudra.api.loans.model.teaser.primaryview;
/**
 * @author Sanket
 *
 */

import java.util.ArrayList;
import java.util.List;

public class HomeLoanPrimaryViewResponse {
	
	private RetailProfileViewResponse  personalProfileRespoonse;
	private List<RetailProfileViewResponse> coApplicantResponse = new ArrayList<RetailProfileViewResponse>();
	private List<RetailProfileViewResponse> garantorResponse = new ArrayList<RetailProfileViewResponse>();
	private HomeLoanResponse homeLoanResponse;
	
	
	public RetailProfileViewResponse getPersonalProfileRespoonse() {
		return personalProfileRespoonse;
	}
	public void setPersonalProfileRespoonse(RetailProfileViewResponse personalProfileRespoonse) {
		this.personalProfileRespoonse = personalProfileRespoonse;
	}
	public List<RetailProfileViewResponse> getCoApplicantResponse() {
		return coApplicantResponse;
	}
	public void setCoApplicantResponse(List<RetailProfileViewResponse> coApplicantResponse) {
		this.coApplicantResponse = coApplicantResponse;
	}
	
	public void addCoApplicantResponse(RetailProfileViewResponse coApplicantResponse){
		this.getCoApplicantResponse().add(coApplicantResponse);
	}
	public List<RetailProfileViewResponse> getGarantorResponse() {
		return garantorResponse;
	}
	public void setGarantorResponse(List<RetailProfileViewResponse> garantorResponse) {
		this.garantorResponse = garantorResponse;
	}
	
	public void addGarantorResponse(RetailProfileViewResponse garantorResponse){
		this.getGarantorResponse().add(garantorResponse);
	}
	
	public HomeLoanResponse getHomeLoanResponse() {
		return homeLoanResponse;
	}
	public void setHomeLoanResponse(HomeLoanResponse homeLoanResponse) {
		this.homeLoanResponse = homeLoanResponse;
	}

}
