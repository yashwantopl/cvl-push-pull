/**
 * 
 */
package com.opl.mudra.api.gst.model.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vijay.chauhan
 *
 */
public class FinalCAMGSTSpecificData {
	private Integer amountOfLoan;
	private Boolean isConsolidate;
	private String gstin;
	private Map<String, Object> basicInfo;
	private Map<String, Map<String, Object>> financialYearWiseData;
	private Map<String, Map<String, Object>>  qrtlyReturnData;
	private Map<String,Map<String, List<Map<String, Object>>>> yearlyMonthWiseInvoiceWiseDetails;
	private List<QuarterlySummary> quarterlySummary;
	private Map<String, Map<String, Object>> monthlySummary; 
	private Map<String, Object> yearWisePurchasesSummary; 
	private Map<String, Map<String, Object>> financialYearWiseHSNReportData;
	
	
	private CAMGSTData camgstData;
	
	public FinalCAMGSTSpecificData() {
		this.basicInfo = new HashMap<>();
		this.financialYearWiseData= new HashMap<>();		
		this.qrtlyReturnData= new HashMap<>();		
		this.isConsolidate=false;
		this.yearlyMonthWiseInvoiceWiseDetails=new HashMap<>();
		this.quarterlySummary= new ArrayList<>();
		this.monthlySummary=new HashMap<>();
		this.yearWisePurchasesSummary=new HashMap<>();
		this.financialYearWiseHSNReportData=new HashMap<>();
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public Map<String, Object> getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(Map<String, Object> basicInfo) {
		this.basicInfo = basicInfo;
	}

	public Map<String, Map<String, Object>> getFinancialYearWiseData() {
		return financialYearWiseData;
	}

	public void setFinancialYearWiseData(Map<String, Map<String, Object>> financialYearWiseData) {
		this.financialYearWiseData = financialYearWiseData;
	}

	public Map<String, Map<String, Object>> getQrtlyReturnData() {
		return qrtlyReturnData;
	}

	public void setQrtlyReturnData(Map<String, Map<String, Object>> qrtlyReturnData) {
		this.qrtlyReturnData = qrtlyReturnData;
	}

	public Boolean getIsConsolidate() {
		return isConsolidate;
	}

	public void setIsConsolidate(Boolean isConsolidate) {
		this.isConsolidate = isConsolidate;
	}

	public Map<String, Map<String, List<Map<String, Object>>>> getYearlyMonthWiseInvoiceWiseDetails() {
		return yearlyMonthWiseInvoiceWiseDetails;
	}

	public void setYearlyMonthWiseInvoiceWiseDetails(
			Map<String, Map<String, List<Map<String, Object>>>> yearlyMonthWiseInvoiceWiseDetails) {
		this.yearlyMonthWiseInvoiceWiseDetails = yearlyMonthWiseInvoiceWiseDetails;
	}

	public Integer getAmountOfLoan() {
		return amountOfLoan;
	}

	public void setAmountOfLoan(Integer amountOfLoan) {
		this.amountOfLoan = amountOfLoan;
	}

	public List<QuarterlySummary> getQuarterlySummary() {
		return quarterlySummary;
	}

	public void setQuarterlySummary(List<QuarterlySummary> quarterlySummary) {
		this.quarterlySummary = quarterlySummary;
	}

	public Map<String, Map<String, Object>> getMonthlySummary() {
		return monthlySummary;
	}

	public void setMonthlySummary(Map<String, Map<String, Object>> monthlySummary) {
		this.monthlySummary = monthlySummary;
	}

	public Map<String, Object> getYearWisePurchasesSummary() {
		return yearWisePurchasesSummary;
	}

	public void setYearWisePurchasesSummary(Map<String, Object> yearWisePurchasesSummary) {
		this.yearWisePurchasesSummary = yearWisePurchasesSummary;
	}

	public CAMGSTData getCamgstData() {
		return camgstData;
	}

	public void setCamgstData(CAMGSTData camgstData) {
		this.camgstData = camgstData;
	}

	public Map<String, Map<String, Object>> getFinancialYearWiseHSNReportData() {
		return financialYearWiseHSNReportData;
	}

	public void setFinancialYearWiseHSNReportData(Map<String, Map<String, Object>> financialYearWiseHSNReportData) {
		this.financialYearWiseHSNReportData = financialYearWiseHSNReportData;
	}

}
