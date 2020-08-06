package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IdentityInfo {

	@JsonProperty("PANId")
	private List<IDDetails> panIDList;

	@JsonProperty("VoterID")
	private List<IDDetails> voterIDList;

	@JsonProperty("NationalIDCard")
	private List<IDDetails> nationalIDCardList;

	@JsonProperty("Passport")
	private List<IDDetails> passportList;

	@JsonProperty("DriverLicense")
	private List<IDDetails> driverLicenseList;

	@JsonProperty("RationCard")
	private List<IDDetails> rationCardList;

	@JsonProperty("OtherId")
	private List<IDDetails> otherIdList;

	public List<IDDetails> getPanIDList() {
		if (panIDList == null) {
			panIDList = new ArrayList<IDDetails>();
		}
		return panIDList;
	}

	public void setPanIDList(List<IDDetails> panIDList) {
		this.panIDList = panIDList;
	}

	public List<IDDetails> getVoterIDList() {
		if (voterIDList == null) {
			voterIDList = new ArrayList<IDDetails>();
		}
		return voterIDList;

	}

	public void setVoterIDList(List<IDDetails> voterIDList) {
		this.voterIDList = voterIDList;
	}

	public List<IDDetails> getNationalIDCardList() {
		if (nationalIDCardList == null) {
			nationalIDCardList = new ArrayList<IDDetails>();
		}
		return nationalIDCardList;

	}

	public void setNationalIDCardList(List<IDDetails> nationalIDCardList) {
		this.nationalIDCardList = nationalIDCardList;
	}

	public List<IDDetails> getPassportList() {
		if (passportList == null) {
			passportList = new ArrayList<IDDetails>();
		}
		return passportList;

	}

	public void setPassportList(List<IDDetails> passportList) {
		this.passportList = passportList;
	}

	public List<IDDetails> getDriverLicenseList() {
		if (driverLicenseList == null) {
			driverLicenseList = new ArrayList<IDDetails>();
		}
		return driverLicenseList;

	}

	public void setDriverLicenseList(List<IDDetails> driverLicenseList) {
		this.driverLicenseList = driverLicenseList;
	}

	public List<IDDetails> getRationCardList() {
		if (rationCardList == null) {
			rationCardList = new ArrayList<IDDetails>();
		}
		return rationCardList;
	}

	public void setRationCardList(List<IDDetails> rationCardList) {
		this.rationCardList = rationCardList;
	}

	public List<IDDetails> getOtherIdList() {
		if (otherIdList == null) {
			otherIdList = new ArrayList<IDDetails>();
		}
		return otherIdList;

	}

	public void setOtherIdList(List<IDDetails> otherIdList) {
		this.otherIdList = otherIdList;
	}

}
