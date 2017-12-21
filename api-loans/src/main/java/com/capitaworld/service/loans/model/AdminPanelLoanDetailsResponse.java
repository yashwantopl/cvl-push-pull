package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;

public class AdminPanelLoanDetailsResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String applicationId;
	private String email;
	private String createDate;
	private String name;
	private String productName;
	private String subProduct;
	private Double absoluteDisplayAmount;
	private Double absoluteAmount;
	private boolean amounInRuppes;
	private Double tenure;
	private Integer age;
	private String country;
	private String state;
	private String city;
	private String constitution;
	private Double profileCount;
	private Double primaryCount;
	private Double finalCount;
	private Double totalCount;
	private String profileAndPrimaryLocked;
	private String finalLocked;
	private String lastUpdatedDate;
	private String currency;
	private String mobile;
	private String lastLoginDate;
	private String pincode;
	private Double applicantMonthlyIncome;
	private String incomeType;
	private String coApplicant1Name;
	private Integer coApplicant1Age;
	private Double coApplicant1MonthlyIncome;
	private String coApplicant2Name;
	private Integer coApplicant2Age;
	private Double coApplicant2MonthlyIncome;
	private String campaignCode;

	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSubProduct() {
		return subProduct;
	}
	public void setSubProduct(String subProduct) {
		this.subProduct = subProduct;
	}
	public Double getAbsoluteDisplayAmount() {
		return absoluteDisplayAmount;
	}
	public void setAbsoluteDisplayAmount(Double absoluteDisplayAmount) {
		this.absoluteDisplayAmount = absoluteDisplayAmount;
	}
	public Double getAbsoluteAmount() {
		return absoluteAmount;
	}
	public void setAbsoluteAmount(Double absoluteAmount) {
		this.absoluteAmount = absoluteAmount;
	}
	public boolean isAmounInRuppes() {
		return amounInRuppes;
	}
	public void setAmounInRuppes(boolean amounInRuppes) {
		this.amounInRuppes = amounInRuppes;
	}
	public Double getTenure() {
		return tenure;
	}
	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getConstitution() {
		return constitution;
	}
	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}
	public Double getProfileCount() {
		return profileCount;
	}
	public void setProfileCount(Double profileCount) {
		this.profileCount = profileCount;
	}
	public Double getPrimaryCount() {
		return primaryCount;
	}
	public void setPrimaryCount(Double primaryCount) {
		this.primaryCount = primaryCount;
	}
	public Double getFinalCount() {
		return finalCount;
	}
	public void setFinalCount(Double finalCount) {
		this.finalCount = finalCount;
	}
	public Double getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Double totalCount) {
		this.totalCount = totalCount;
	}
	
	public String getProfileAndPrimaryLocked() {
		return profileAndPrimaryLocked;
	}
	public void setProfileAndPrimaryLocked(String profileAndPrimaryLocked) {
		this.profileAndPrimaryLocked = profileAndPrimaryLocked;
	}
	public String getFinalLocked() {
		return finalLocked;
	}
	public void setFinalLocked(String finalLocked) {
		this.finalLocked = finalLocked;
	}
	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Double getApplicantMonthlyIncome() {
		return applicantMonthlyIncome;
	}

	public void setApplicantMonthlyIncome(Double applicantMonthlyIncome) {
		this.applicantMonthlyIncome = applicantMonthlyIncome;
	}

	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

	public String getCoApplicant1Name() {
		return coApplicant1Name;
	}

	public void setCoApplicant1Name(String coApplicant1Name) {
		this.coApplicant1Name = coApplicant1Name;
	}

	public Integer getCoApplicant1Age() {
		return coApplicant1Age;
	}

	public void setCoApplicant1Age(Integer coApplicant1Age) {
		this.coApplicant1Age = coApplicant1Age;
	}

	public Double getCoApplicant1MonthlyIncome() {
		return coApplicant1MonthlyIncome;
	}

	public void setCoApplicant1MonthlyIncome(Double coApplicant1MonthlyIncome) {
		this.coApplicant1MonthlyIncome = coApplicant1MonthlyIncome;
	}

	public String getCoApplicant2Name() {
		return coApplicant2Name;
	}

	public void setCoApplicant2Name(String coApplicant2Name) {
		this.coApplicant2Name = coApplicant2Name;
	}

	public Integer getCoApplicant2Age() {
		return coApplicant2Age;
	}

	public void setCoApplicant2Age(Integer coApplicant2Age) {
		this.coApplicant2Age = coApplicant2Age;
	}

	public Double getCoApplicant2MonthlyIncome() {
		return coApplicant2MonthlyIncome;
	}

	public void setCoApplicant2MonthlyIncome(Double coApplicant2MonthlyIncome) {
		this.coApplicant2MonthlyIncome = coApplicant2MonthlyIncome;
	}

	public String getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}
}
