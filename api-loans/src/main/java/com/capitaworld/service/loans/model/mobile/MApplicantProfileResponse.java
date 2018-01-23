package com.capitaworld.service.loans.model.mobile;


public class MApplicantProfileResponse extends MobileLoanRequest{
	private static final long serialVersionUID = 1L;
	
	private Integer titleId;
	private String firstName;
	private String middleName;
	private String lastName;
	private Integer statusId;
	private Integer genderId;
	private String spouseName;
	private Boolean isSpouseEmployed;
	private Integer highestQualification;
	private String highestQualificationOther;
	private Integer qualifyingYear;
	private Integer occupationId;
	private String pan;
	private String aadharNumber;
	private Double residingYear;
	private Double residingMonth;
	private Double otherIncome;
	private Double otherInvestment;
	private Double taxPaidLastYear;
	private Double bonusPerAnnum;
	private Double incentivePerAnnum;
	private String companyName;
	private Long employedWithId;
	private String entityName;
	private Integer industryTypeId;
	private String industryTypeOther;
	private Integer selfEmployedOccupationId;
	private String selfEmployedOccupationOther;
	private Double landSize;
	private Integer alliedActivityId;
	
	private MPLPrimaryResponse mplPrimaryResponse;
	private MLAPPrimaryResponse mlapPrimaryResponse;
	private MHLPrimaryResponse mhlPrimaryResponse;
	
	private Object data;
	
	public Integer getTitleId() {
		return titleId;
	}
	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Integer getGenderId() {
		return genderId;
	}
	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public Boolean getIsSpouseEmployed() {
		return isSpouseEmployed;
	}
	public void setIsSpouseEmployed(Boolean isSpouseEmployed) {
		this.isSpouseEmployed = isSpouseEmployed;
	}
	public Integer getHighestQualification() {
		return highestQualification;
	}
	public void setHighestQualification(Integer highestQualification) {
		this.highestQualification = highestQualification;
	}
	public Integer getQualifyingYear() {
		return qualifyingYear;
	}
	public void setQualifyingYear(Integer qualifyingYear) {
		this.qualifyingYear = qualifyingYear;
	}
	public Integer getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public Double getResidingYear() {
		return residingYear;
	}
	public void setResidingYear(Double residingYear) {
		this.residingYear = residingYear;
	}
	public Double getResidingMonth() {
		return residingMonth;
	}
	public void setResidingMonth(Double residingMonth) {
		this.residingMonth = residingMonth;
	}
	public String getHighestQualificationOther() {
		return highestQualificationOther;
	}
	public void setHighestQualificationOther(String highestQualificationOther) {
		this.highestQualificationOther = highestQualificationOther;
	}
	public Double getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}
	public Double getOtherInvestment() {
		return otherInvestment;
	}
	public void setOtherInvestment(Double otherInvestment) {
		this.otherInvestment = otherInvestment;
	}
	public Double getTaxPaidLastYear() {
		return taxPaidLastYear;
	}
	public void setTaxPaidLastYear(Double taxPaidLastYear) {
		this.taxPaidLastYear = taxPaidLastYear;
	}
	public Double getBonusPerAnnum() {
		return bonusPerAnnum;
	}
	public void setBonusPerAnnum(Double bonusPerAnnum) {
		this.bonusPerAnnum = bonusPerAnnum;
	}
	public Double getIncentivePerAnnum() {
		return incentivePerAnnum;
	}
	public void setIncentivePerAnnum(Double incentivePerAnnum) {
		this.incentivePerAnnum = incentivePerAnnum;
	}
	public MPLPrimaryResponse getMplPrimaryResponse() {
		return mplPrimaryResponse;
	}
	public void setMplPrimaryResponse(MPLPrimaryResponse mplPrimaryResponse) {
		this.mplPrimaryResponse = mplPrimaryResponse;
	}
	public MLAPPrimaryResponse getMlapPrimaryResponse() {
		return mlapPrimaryResponse;
	}
	public void setMlapPrimaryResponse(MLAPPrimaryResponse mlapPrimaryResponse) {
		this.mlapPrimaryResponse = mlapPrimaryResponse;
	}
	public MHLPrimaryResponse getMhlPrimaryResponse() {
		return mhlPrimaryResponse;
	}
	public void setMhlPrimaryResponse(MHLPrimaryResponse mhlPrimaryResponse) {
		this.mhlPrimaryResponse = mhlPrimaryResponse;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getEmployedWithId() {
		return employedWithId;
	}
	public void setEmployedWithId(Long employedWithId) {
		this.employedWithId = employedWithId;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public Integer getIndustryTypeId() {
		return industryTypeId;
	}
	public void setIndustryTypeId(Integer industryTypeId) {
		this.industryTypeId = industryTypeId;
	}
	public String getIndustryTypeOther() {
		return industryTypeOther;
	}
	public void setIndustryTypeOther(String industryTypeOther) {
		this.industryTypeOther = industryTypeOther;
	}
	public Integer getSelfEmployedOccupationId() {
		return selfEmployedOccupationId;
	}
	public void setSelfEmployedOccupationId(Integer selfEmployedOccupationId) {
		this.selfEmployedOccupationId = selfEmployedOccupationId;
	}
	public String getSelfEmployedOccupationOther() {
		return selfEmployedOccupationOther;
	}
	public void setSelfEmployedOccupationOther(String selfEmployedOccupationOther) {
		this.selfEmployedOccupationOther = selfEmployedOccupationOther;
	}
	public Double getLandSize() {
		return landSize;
	}
	public void setLandSize(Double landSize) {
		this.landSize = landSize;
	}
	public Integer getAlliedActivityId() {
		return alliedActivityId;
	}
	public void setAlliedActivityId(Integer alliedActivityId) {
		this.alliedActivityId = alliedActivityId;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	

}
