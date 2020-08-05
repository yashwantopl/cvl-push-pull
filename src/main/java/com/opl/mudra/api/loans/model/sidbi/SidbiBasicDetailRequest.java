package com.opl.mudra.api.loans.model.sidbi;

import java.io.Serializable;
import java.util.Date;

public class SidbiBasicDetailRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long clientId;
	
	private Long applicationId;
	
	private String organisationName;
	
	private Integer constitutionId;
	
	private String industryName;
	
	private String premiseNumber;
	
	private String streetName;
	
	private String landMark;
	
	private Long pincode;

	private Integer factoryPremise;
	
	private String exisFactPremiseNumber;
	
	private String exisFactStreetName;
	
	private String exisFactLandMark;
	
	private Long exisFactPincode;
	
	private Integer exisFactoryPremise;
	
	private String propFactPremiseNumber;
	
	private String propFactStreetName;
	
	private String propFactLandMark;
	
	private Long propFactPincode;
	
	private Integer propFactoryPremise;
	
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
	
	private Date activitySince;
	
	private String proposedActivity;
	
	private Date trialRunEndDate;
	
	private Date dateOfCommencementOfCommercialOperations;
	
	private Boolean isCoveredUnderCGTMSE;

	private Integer repaymemtMonths;

	private Double loanAmount;

    private Integer moratoriumPeriodMonths;

    private Date declarationDate;

    private String declarationPlace;
    
    private Boolean isCopyOfExistingFactAddr;
    
    private Boolean isCopyOfRegisteredAddr;
    
    private String activityDetail;
    
    private Integer loanTypeId;
    
    private Integer allAmountValuesIn;
    private Boolean isLockedAllAmountValuesIn;
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

	public Date getActivitySince() {
		return activitySince;
	}

	public void setActivitySince(Date activitySince) {
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

    

	public Boolean getIsCopyOfExistingFactAddr() {
		return isCopyOfExistingFactAddr;
	}

	public void setIsCopyOfExistingFactAddr(Boolean isCopyOfExistingFactAddr) {
		this.isCopyOfExistingFactAddr = isCopyOfExistingFactAddr;
	}

	public Boolean getIsCopyOfRegisteredAddr() {
		return isCopyOfRegisteredAddr;
	}

	public void setIsCopyOfRegisteredAddr(Boolean isCopyOfRegisteredAddr) {
		this.isCopyOfRegisteredAddr = isCopyOfRegisteredAddr;
	}

	public String getActivityDetail() {
		return activityDetail;
	}

	public void setActivityDetail(String activityDetail) {
		this.activityDetail = activityDetail;
	}

	
	public Date getDateOfCommencementOfCommercialOperations() {
		return dateOfCommencementOfCommercialOperations;
	}

	public void setDateOfCommencementOfCommercialOperations(Date dateOfCommencementOfCommercialOperations) {
		this.dateOfCommencementOfCommercialOperations = dateOfCommencementOfCommercialOperations;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public Integer getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(Integer loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	public Integer getFactoryPremise() {
		return factoryPremise;
	}

	public void setFactoryPremise(Integer factoryPremise) {
		this.factoryPremise = factoryPremise;
	}

	public Integer getExisFactoryPremise() {
		return exisFactoryPremise;
	}

	public void setExisFactoryPremise(Integer exisFactoryPremise) {
		this.exisFactoryPremise = exisFactoryPremise;
	}

	public Integer getPropFactoryPremise() {
		return propFactoryPremise;
	}

	public void setPropFactoryPremise(Integer propFactoryPremise) {
		this.propFactoryPremise = propFactoryPremise;
	}

	public Integer getAllAmountValuesIn() {
		return allAmountValuesIn;
	}

	public void setAllAmountValuesIn(Integer allAmountValuesIn) {
		this.allAmountValuesIn = allAmountValuesIn;
	}

	public Boolean getIsLockedAllAmountValuesIn() {
		return isLockedAllAmountValuesIn;
	}

	public void setIsLockedAllAmountValuesIn(Boolean isLockedAllAmountValuesIn) {
		this.isLockedAllAmountValuesIn = isLockedAllAmountValuesIn;
	}

}
