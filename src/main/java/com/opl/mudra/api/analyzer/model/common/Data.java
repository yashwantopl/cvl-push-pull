package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="Data",namespace="https://www.perfios.com/PIR")
@XmlAccessorType(XmlAccessType.FIELD)
public class Data implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "CustomerInfo")
	private CustomerInfo customerInfo ;

	@XmlElement(name = "MonthlyDetails")
	private MonthlyDetailList monthlyDetailList;

	@XmlElement(name = "SummaryInfo")
	private SummaryInfo summaryInfo;

	@XmlElement(name = "Top5FundsReceived")
	private Top5FundReceivedList top5FundReceivedList;

	@XmlElement(name = "Top5FundsTransferred")
	private Top5FundTransferedList top5FundTransferedList;

	@XmlElement(name = "MonthlyTopNHighValueCreditXns")
	private MonthlyTopNHighValueCreditXns monthlyTopNHighValueCreditXns;

	@XmlElement(name = "MonthlyTopNHighValueDebitXns")
	private MonthlyTopNHighValueDebitXns monthlyTopNHighValueDebitXns;

	@XmlElement(name = "BouncedOrPenalXns")
	private BouncedOrPenalXnList bouncedOrPenalXnList;

	private BouncedOrPenalXnList penalList;

	@XmlElement(name = "Xns")
	private Xns xns;

	private Integer checkBounceForLast6Month;

	private Integer checkBounceForLast1Month;

	private Double totalCredit;

	private Long directorId;

	private Long chequeBounce;
	private Double averageSalary;

	private Boolean isManual;

	private String gstConsent;

	private Long applicationId;

	private Long bsMasterId;

	private Long coAppId;

	private String enumBank;

	private List<String> gstConsentVal;

	private String accountSince;

	private Boolean isAllowCovidMissTransaction;

	private String missingMonths;

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}





	public MonthlyDetailList getMonthlyDetailList() {
		return monthlyDetailList;
	}

	public void setMonthlyDetailList(MonthlyDetailList monthlyDetailList) {
		this.monthlyDetailList = monthlyDetailList;
	}

	public SummaryInfo getSummaryInfo() {
		return summaryInfo;
	}

	public void setSummaryInfo(SummaryInfo summaryInfo) {
		this.summaryInfo = summaryInfo;
	}


	public Top5FundReceivedList getTop5FundReceivedList() {
		return top5FundReceivedList;
	}

	public void setTop5FundReceivedList(Top5FundReceivedList top5FundReceivedList) {
		this.top5FundReceivedList = top5FundReceivedList;
	}

	public Top5FundTransferedList getTop5FundTransferedList() {
		return top5FundTransferedList;
	}

	public void setTop5FundTransferedList(Top5FundTransferedList top5FundTransferedList) {
		this.top5FundTransferedList = top5FundTransferedList;
	}

	
	public BouncedOrPenalXnList getBouncedOrPenalXnList() {
		return bouncedOrPenalXnList;
	}

	public void setBouncedOrPenalXnList(BouncedOrPenalXnList bouncedOrPenalXnList) {
		this.bouncedOrPenalXnList = bouncedOrPenalXnList;
	}

	public Xns getXns() {
		return xns;
	}

	public void setXns(Xns xns) {
		this.xns = xns;
	}

	public Integer getCheckBounceForLast1Month() {
		return checkBounceForLast1Month;
	}

	public void setCheckBounceForLast1Month(Integer checkBounceForLast1Month) {
		this.checkBounceForLast1Month = checkBounceForLast1Month;
	}

	public Integer getCheckBounceForLast6Month() {
		return checkBounceForLast6Month;
	}

	public void setCheckBounceForLast6Month(Integer checkBounceForLast6Month) {
		this.checkBounceForLast6Month = checkBounceForLast6Month;
	}

	public Double getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(Double totalCredit) {
		this.totalCredit = totalCredit;
	}

	public BouncedOrPenalXnList getPenalList() {
		return penalList;
	}

	public void setPenalList(BouncedOrPenalXnList penalList) {
		this.penalList = penalList;
	}

	/**
	 * @return the directorId
	 */
	public Long getDirectorId() {
		return directorId;
	}

	/**
	 * @param directorId the directorId to set
	 */
	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}

	public Long getChequeBounce() {
		return chequeBounce;
	}

	public void setChequeBounce(Long chequeBounce) {
		this.chequeBounce = chequeBounce;
	}

	public Double getAverageSalary() {
		return averageSalary;
	}

	public void setAverageSalary(Double averageSalary) {
		this.averageSalary = averageSalary;
	}

	public Boolean getIsManual() {
		return isManual;
	}

	public void setIsManual(Boolean isManual) {
		this.isManual = isManual;
	}

	public MonthlyTopNHighValueCreditXns getMonthlyTopNHighValueCreditXns() {
		return monthlyTopNHighValueCreditXns;
	}

	public void setMonthlyTopNHighValueCreditXns(MonthlyTopNHighValueCreditXns monthlyTopNHighValueCreditXns) {
		this.monthlyTopNHighValueCreditXns = monthlyTopNHighValueCreditXns;
	}

	public MonthlyTopNHighValueDebitXns getMonthlyTopNHighValueDebitXns() {
		return monthlyTopNHighValueDebitXns;
	}

	public void setMonthlyTopNHighValueDebitXns(MonthlyTopNHighValueDebitXns monthlyTopNHighValueDebitXns) {
		this.monthlyTopNHighValueDebitXns = monthlyTopNHighValueDebitXns;
	}

	public String getGstConsent() {
		return gstConsent;
	}

	public void setGstConsent(String gstConsent) {
		this.gstConsent = gstConsent;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getCoAppId() {
		return coAppId;
	}

	public void setCoAppId(Long coAppId) {
		this.coAppId = coAppId;
	}

	public String getEnumBank() {
		return enumBank;
	}

	public void setEnumBank(String enumBank) {
		this.enumBank = enumBank;
	}

	public List<String> getGstConsentVal() {
		return gstConsentVal;
	}

	public void setGstConsentVal(List<String> gstConsentVal) {
		this.gstConsentVal = gstConsentVal;
	}

	public String getAccountSince() {
		return accountSince;
	}

	public void setAccountSince(String accountSince) {
		this.accountSince = accountSince;
	}

	public Long getBsMasterId() {
		return bsMasterId;
	}

	public void setBsMasterId(Long bsMasterId) {
		this.bsMasterId = bsMasterId;
	}

	public Boolean getIsAllowCovidMissTransaction() {
		return isAllowCovidMissTransaction;
	}

	public void setIsAllowCovidMissTransaction(Boolean allowCovidMissTransaction) {
		isAllowCovidMissTransaction = allowCovidMissTransaction;
	}

	public String getMissingMonths() {
		return missingMonths;
	}

	public void setMissingMonths(String missingMonths) {
		this.missingMonths = missingMonths;
	}
}
