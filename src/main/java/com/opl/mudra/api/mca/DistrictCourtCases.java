package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author rahul.meena
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class DistrictCourtCases {

	private String year;
	

	private String state;

	private String district;

	private String courtComplex;

	private String courtName;

	private String caseType;

	private String caseNo;

	private String status;

	private String petitionerName;

	private String respondentName;

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
//	@JsonSerialize(using = CustomDateSerializer.class)
	private String decisionDate;

	private String linkOfCase;

	public String getYear() {
		return year;
	}

	public String getState() {
		return state;
	}

	public String getDistrict() {
		return district;
	}

	public String getCourtComplex() {
		return courtComplex;
	}

	public String getCourtName() {
		return courtName;
	}

	public String getCaseType() {
		return caseType;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public String getStatus() {
		return status;
	}

	public String getPetitionerName() {
		return petitionerName;
	}

	public String getRespondentName() {
		return respondentName;
	}

	public String getDecisionDate() {
		return decisionDate;
	}

	public String getLinkOfCase() {
		return linkOfCase;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setCourtComplex(String courtComplex) {
		this.courtComplex = courtComplex;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPetitionerName(String petitionerName) {
		this.petitionerName = petitionerName;
	}

	public void setRespondentName(String respondentName) {
		this.respondentName = respondentName;
	}

	/**
	 * @param decisionDate
	 */
	public void setDecisionDate(String decisionDate) {
		this.decisionDate = decisionDate;
		
	}

	/**
	 * @param linkOfCase
	 */
	public void setLinkOfCase(String linkOfCase) {
		this.linkOfCase = linkOfCase;
		
	}

}
