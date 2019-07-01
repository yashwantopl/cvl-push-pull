package com.capitaworld.service.loans.model.sidbi;

import java.io.Serializable;
import java.util.Date;

public class SidbiBasicDetailRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long clientId;
	
	private Long applicationId;
	
	private String organisationName;
	
	private Integer constitutionId;
	
	private Long industryId;
	
	private String premiseNumber;
	
	private String streetName;
	
	private String landMark;
	
	private Long pincode;
	
	private String exisFactPremiseNumber;
	
	private String exisFactStreetName;
	
	private String exisFactLandMark;
	
	private Long exisFactPincode;
	
	private Boolean exisFactOwned;
	
	private Boolean exisFactRanted;
	
	private Boolean exisFactLeased;
	
	private String propFactPremiseNumber;
	
	private String propFactStreetName;
	
	private String propFactLandMark;
	
	private Long propFactPincode;
	
	private Boolean propFactOwned;
	
	private Boolean propFactRanted;
	
	private Boolean propFactLeased;
	
	private String landlineNo;
	
	private String mobile;
	
	private String email;
	
	private Date establishmentDate;
	
	private Date commencementDate;
	
	private String msmeUamRegistrationNumber;
	
	private Date msmeUamRegistrationDate;
	
	private String associatedGroup;
	
	private String sidbiBranch;
	
	private String exisitngActivity;
	
	private String activitySince;
	
	private String proposedActivity;
	
	private Date trialRunEndDate;
	
	private Boolean isCoveredUnderCGTMSE;

	private Integer repaymemtYears;

	private Integer repaymemtMonths;

	private Double loanAmount;

    private Integer moratoriumPeriodMonths;

    private Date declarationDate;

    private String declarationPlace;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public Integer getConstitutionId() {
		return constitutionId;
	}

	public void setConstitutionId(Integer constitutionId) {
		this.constitutionId = constitutionId;
	}

	public Long getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	public String getPremiseNumber() {
		return premiseNumber;
	}

	public void setPremiseNumber(String premiseNumber) {
		this.premiseNumber = premiseNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public String getExisFactPremiseNumber() {
		return exisFactPremiseNumber;
	}

	public void setExisFactPremiseNumber(String exisFactPremiseNumber) {
		this.exisFactPremiseNumber = exisFactPremiseNumber;
	}

	public String getExisFactStreetName() {
		return exisFactStreetName;
	}

	public void setExisFactStreetName(String exisFactStreetName) {
		this.exisFactStreetName = exisFactStreetName;
	}

	public String getExisFactLandMark() {
		return exisFactLandMark;
	}

	public void setExisFactLandMark(String exisFactLandMark) {
		this.exisFactLandMark = exisFactLandMark;
	}

	public Long getExisFactPincode() {
		return exisFactPincode;
	}

	public void setExisFactPincode(Long exisFactPincode) {
		this.exisFactPincode = exisFactPincode;
	}

	public Boolean getExisFactOwned() {
		return exisFactOwned;
	}

	public void setExisFactOwned(Boolean exisFactOwned) {
		this.exisFactOwned = exisFactOwned;
	}

	public Boolean getExisFactRanted() {
		return exisFactRanted;
	}

	public void setExisFactRanted(Boolean exisFactRanted) {
		this.exisFactRanted = exisFactRanted;
	}

	public Boolean getExisFactLeased() {
		return exisFactLeased;
	}

	public void setExisFactLeased(Boolean exisFactLeased) {
		this.exisFactLeased = exisFactLeased;
	}

	public String getPropFactPremiseNumber() {
		return propFactPremiseNumber;
	}

	public void setPropFactPremiseNumber(String propFactPremiseNumber) {
		this.propFactPremiseNumber = propFactPremiseNumber;
	}

	public String getPropFactStreetName() {
		return propFactStreetName;
	}

	public void setPropFactStreetName(String propFactStreetName) {
		this.propFactStreetName = propFactStreetName;
	}

	public String getPropFactLandMark() {
		return propFactLandMark;
	}

	public void setPropFactLandMark(String propFactLandMark) {
		this.propFactLandMark = propFactLandMark;
	}

	public Long getPropFactPincode() {
		return propFactPincode;
	}

	public void setPropFactPincode(Long propFactPincode) {
		this.propFactPincode = propFactPincode;
	}

	public Boolean getPropFactOwned() {
		return propFactOwned;
	}

	public void setPropFactOwned(Boolean propFactOwned) {
		this.propFactOwned = propFactOwned;
	}

	public Boolean getPropFactRanted() {
		return propFactRanted;
	}

	public void setPropFactRanted(Boolean propFactRanted) {
		this.propFactRanted = propFactRanted;
	}

	public Boolean getPropFactLeased() {
		return propFactLeased;
	}

	public void setPropFactLeased(Boolean propFactLeased) {
		this.propFactLeased = propFactLeased;
	}

	public String getLandlineNo() {
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEstablishmentDate() {
		return establishmentDate;
	}

	public void setEstablishmentDate(Date establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public Date getCommencementDate() {
		return commencementDate;
	}

	public void setCommencementDate(Date commencementDate) {
		this.commencementDate = commencementDate;
	}

	public String getAssociatedGroup() {
		return associatedGroup;
	}

	public void setAssociatedGroup(String associatedGroup) {
		this.associatedGroup = associatedGroup;
	}

	public String getSidbiBranch() {
		return sidbiBranch;
	}

	public void setSidbiBranch(String sidbiBranch) {
		this.sidbiBranch = sidbiBranch;
	}

	public String getExisitngActivity() {
		return exisitngActivity;
	}

	public void setExisitngActivity(String exisitngActivity) {
		this.exisitngActivity = exisitngActivity;
	}

	public String getActivitySince() {
		return activitySince;
	}

	public void setActivitySince(String activitySince) {
		this.activitySince = activitySince;
	}

	public String getProposedActivity() {
		return proposedActivity;
	}

	public void setProposedActivity(String proposedActivity) {
		this.proposedActivity = proposedActivity;
	}

	public Date getTrialRunEndDate() {
		return trialRunEndDate;
	}

	public void setTrialRunEndDate(Date trialRunEndDate) {
		this.trialRunEndDate = trialRunEndDate;
	}

	public Boolean getIsCoveredUnderCGTMSE() {
		return isCoveredUnderCGTMSE;
	}

	public void setIsCoveredUnderCGTMSE(Boolean isCoveredUnderCGTMSE) {
		this.isCoveredUnderCGTMSE = isCoveredUnderCGTMSE;
	}

	public Integer getRepaymemtYears() {
		return repaymemtYears;
	}

	public void setRepaymemtYears(Integer repaymemtYears) {
		this.repaymemtYears = repaymemtYears;
	}

	public Integer getRepaymemtMonths() {
		return repaymemtMonths;
	}

	public void setRepaymemtMonths(Integer repaymemtMonths) {
		this.repaymemtMonths = repaymemtMonths;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getMsmeUamRegistrationNumber() {
		return msmeUamRegistrationNumber;
	}

	public void setMsmeUamRegistrationNumber(String msmeUamRegistrationNumber) {
		this.msmeUamRegistrationNumber = msmeUamRegistrationNumber;
	}

	public Date getMsmeUamRegistrationDate() {
		return msmeUamRegistrationDate;
	}

	public void setMsmeUamRegistrationDate(Date msmeUamRegistrationDate) {
		this.msmeUamRegistrationDate = msmeUamRegistrationDate;
	}

    public Integer getMoratoriumPeriodMonths() {
        return moratoriumPeriodMonths;
    }

    public void setMoratoriumPeriodMonths(Integer moratoriumPeriodMonths) {
        this.moratoriumPeriodMonths = moratoriumPeriodMonths;
    }

    public Date getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(Date declarationDate) {
        this.declarationDate = declarationDate;
    }

    public String getDeclarationPlace() {
        return declarationPlace;
    }

    public void setDeclarationPlace(String declarationPlace) {
        this.declarationPlace = declarationPlace;
    }

    @Override
	public String toString() {
		return "SidbiBasicDetailRequest [id=" + id + ", clientId=" + clientId + ", applicationId=" + applicationId
				+ ", organisationName=" + organisationName + ", constitutionId=" + constitutionId + ", industryId="
				+ industryId + ", premiseNumber=" + premiseNumber + ", streetName=" + streetName + ", landMark="
				+ landMark + ", pincode=" + pincode + ", exisFactPremiseNumber=" + exisFactPremiseNumber
				+ ", exisFactStreetName=" + exisFactStreetName + ", exisFactLandMark=" + exisFactLandMark
				+ ", exisFactPincode=" + exisFactPincode + ", exisFactOwned=" + exisFactOwned + ", exisFactRanted="
				+ exisFactRanted + ", exisFactLeased=" + exisFactLeased + ", propFactPremiseNumber="
				+ propFactPremiseNumber + ", propFactStreetName=" + propFactStreetName + ", propFactLandMark="
				+ propFactLandMark + ", propFactPincode=" + propFactPincode + ", propFactOwned=" + propFactOwned
				+ ", propFactRanted=" + propFactRanted + ", propFactLeased=" + propFactLeased + ", landlineNo="
				+ landlineNo + ", mobile=" + mobile + ", email=" + email + ", establishmentDate=" + establishmentDate
				+ ", commencementDate=" + commencementDate + ", msmeUamRegistrationNumber=" + msmeUamRegistrationNumber
				+ ", msmeUamRegistrationDate=" + msmeUamRegistrationDate + ", associatedGroup=" + associatedGroup
				+ ", sidbiBranch=" + sidbiBranch + ", exisitngActivity=" + exisitngActivity + ", activitySince="
				+ activitySince + ", proposedActivity=" + proposedActivity + ", trialRunEndDate=" + trialRunEndDate
				+ ", isCoveredUnderCGTMSE=" + isCoveredUnderCGTMSE + ", repaymemtYears=" + repaymemtYears
				+ ", repaymemtMonths=" + repaymemtMonths + ", loanAmount=" + loanAmount + "]";
	}

	
}
