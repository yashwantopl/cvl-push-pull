package com.capitaworld.service.loans.model.teaser.primaryview;
/**
 * @author Sanket
 *
 */

import java.util.ArrayList;
import java.util.List;

public class PersonalLoandsPrimaryViewResponse {
	
	private ProfileViewPLResponse  personalProfileRespoonse;
	private List<ProfileViewPLResponse> coApplicantResponse = new ArrayList<ProfileViewPLResponse>();
	private List<ProfileViewPLResponse> garantorResponse = new ArrayList<ProfileViewPLResponse>();
	
	
	
	public ProfileViewPLResponse getPersonalProfileRespoonse() {
		return personalProfileRespoonse;
	}
	public void setPersonalProfileRespoonse(ProfileViewPLResponse personalProfileRespoonse) {
		this.personalProfileRespoonse = personalProfileRespoonse;
	}
	public List<ProfileViewPLResponse> getCoApplicantResponse() {
		return coApplicantResponse;
	}
	public void setCoApplicantResponse(List<ProfileViewPLResponse> coApplicantResponse) {
		this.coApplicantResponse = coApplicantResponse;
	}
	
	public void addCoApplicantResponse(ProfileViewPLResponse coApplicantResponse){
		this.getCoApplicantResponse().add(coApplicantResponse);
	}
	public List<ProfileViewPLResponse> getGarantorResponse() {
		return garantorResponse;
	}
	public void setGarantorResponse(List<ProfileViewPLResponse> garantorResponse) {
		this.garantorResponse = garantorResponse;
	}
	
	public void addGarantorResponse(ProfileViewPLResponse garantorResponse){
		this.getGarantorResponse().add(garantorResponse);
	}
	
	
	
	
	
	
	
	
	
}
