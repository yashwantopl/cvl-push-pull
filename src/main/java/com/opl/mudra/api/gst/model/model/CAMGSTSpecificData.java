/**
 * 
 */
package com.opl.mudra.api.gst.model.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opl.mudra.api.gst.model.MomSales;

/**
 * @author vijay.chauhan
 *
 */
public class CAMGSTSpecificData {

	private String gstin;
	private Map<String, Object> basicInfo;
	private Map<String, Map<String, Map<String, Map<String, Object>>>> financialYearWiseData;
	private Map<String, Map<String, Map<String, Map<String, Object>>>>  qrtlyReturnData;
	private List<Map<String, Map<String, Object>>>monthWiseData;
	private Map<String,Map<String, Map<String, Object>>>yearWiseSales;
	private Map<String, List < Map < String, Object >>> financialYearWiseTop10Sales;
	private Map<String,Map<String, Map<String, Object>>>qtrWiseSales;
	private Map<String, List < Map < String, Object >>> qrterWiseTop10Sales;
	private Map<String, List < Map < String, Object >>> financialYearWiseTop10Purchases;
	private Map<String, List < Map < String, Object >>> qrterWiseTop10Purchases;	
	private Map<String,Double> yearWiseTotalPurchases;
	private Map<String,Map<String,Map<String,Object>>> qrterWiseLastYeasrTotalPurchases;
	private Map<String,Map<String, List<Map<String, Object>>>> yearMonthWiseSales;
	private List<QuarterlySummary> quarterlySummary;
	private Map<String, Map<String, Object>> monthlySummary; 
	private List<MomSales> momSales;
	
	private Map<String, Map<String, Object>> creDebInfoGstr1;
	private Map<String, Object> totalCreDebInfoGstr1;
	
	private List<Map<String, Map<String, Object>>>monthWisePurch;	
	private Map<String,Map<String, Map<String, Object>>>yearWisePurchases;
	
	private Map<String,List<Object>> top10yearWiseHsnSales=null;
	private Map<String,List<Object>> top10yearWiseHsnPurchases=null;
	
	public CAMGSTSpecificData() {
		this.basicInfo = new HashMap<>();
		this.monthWiseData = new ArrayList<>();
		this.financialYearWiseData= new HashMap<>();		
		this.qrtlyReturnData= new HashMap<>();		
		this.yearWiseSales=new HashMap<>();
		this.qtrWiseSales=new HashMap<>();
		this.financialYearWiseTop10Sales=new HashMap<>();
		this.qrterWiseTop10Sales=new HashMap<>();
//		this.monthWisePurchases=new HashMap<>();
		this.financialYearWiseTop10Purchases=new HashMap<>();
		this.qrterWiseTop10Purchases=new HashMap<>();
		this.yearWiseTotalPurchases=new HashMap<>();
		this.qrterWiseLastYeasrTotalPurchases=new HashMap<>();
		this.yearMonthWiseSales=new HashMap<>();
		this.quarterlySummary= new ArrayList<>();
		this.monthlySummary=new HashMap<>();
		this.monthWisePurch = new ArrayList<>();
		this.yearWisePurchases=new HashMap<>();
		this.top10yearWiseHsnSales=new HashMap<>();
		this.top10yearWiseHsnPurchases=new HashMap<>();
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
/*
	public Map<String, Object> getOverview() {
		return overview;
	}

	public void setOverview(Map<String, Object> overview) {
		this.overview = overview;
	}
*/
	public List<Map<String, Map<String, Object>>> getMonthWiseData() {
		return monthWiseData;
	}

	public void setMonthWiseData(List<Map<String, Map<String, Object>>> monthWiseData) {
		this.monthWiseData = monthWiseData;
	}
	public Map<String,Map<String, Object>> addMonthWiseData(Map<String,Map<String, Object>> m){
		getMonthWiseData().add(m);
		return m;
	}
/*
	public Boolean getIsGSTNotApplicable() {
		return isGSTNotApplicable;
	}

	public void setIsGSTNotApplicable(Boolean isGSTNotApplicable) {
		this.isGSTNotApplicable = isGSTNotApplicable;
	}
*/
	public Map<String, Map<String, Map<String, Map<String, Object>>>> getFinancialYearWiseData() {
		return financialYearWiseData;
	}

	public void setFinancialYearWiseData(Map<String, Map<String, Map<String, Map<String, Object>>>> financialYearWiseData) {
		this.financialYearWiseData = financialYearWiseData;
	}

	public Map<String, Object> getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(Map<String, Object> basicInfo) {
		this.basicInfo = basicInfo;
	}

	public Map<String, Map<String, Map<String, Map<String, Object>>>> getQrtlyReturnData() {
		return qrtlyReturnData;
	}

	public void setQrtlyReturnData(Map<String, Map<String, Map<String, Map<String, Object>>>> qrtlyReturnData) {
		this.qrtlyReturnData = qrtlyReturnData;
	}

	

	public Map<String, Map<String, Map<String, Object>>> getYearWiseSales() {
		return yearWiseSales;
	}

	public void setYearWiseSales(Map<String, Map<String, Map<String, Object>>> yearWiseSales) {
		this.yearWiseSales = yearWiseSales;
	}

	/*public Map<String, Map<String, Map<String, Object>>> getMonthWisePurchases() {
		return monthWisePurchases;
	}

	public void setMonthWisePurchases(Map<String, Map<String, Map<String, Object>>> monthWisePurchases) {
		this.monthWisePurchases = monthWisePurchases;
	}*/

	public Map<String, List<Map<String, Object>>> getFinancialYearWiseTop10Sales() {
		return financialYearWiseTop10Sales;
	}

	public void setFinancialYearWiseTop10Sales(Map<String, List<Map<String, Object>>> financialYearWiseTop10Sales) {
		this.financialYearWiseTop10Sales = financialYearWiseTop10Sales;
	}

	public Map<String, List<Map<String, Object>>> getFinancialYearWiseTop10Purchases() {
		return financialYearWiseTop10Purchases;
	}

	public void setFinancialYearWiseTop10Purchases(Map<String, List<Map<String, Object>>> financialYearWiseTop10Purchases) {
		this.financialYearWiseTop10Purchases = financialYearWiseTop10Purchases;
	}

	public Map<String, Double> getYearWiseTotalPurchases() {
		return yearWiseTotalPurchases;
	}

	public void setYearWiseTotalPurchases(Map<String, Double> yearWiseTotalPurchases) {
		this.yearWiseTotalPurchases = yearWiseTotalPurchases;
	}

	public Map<String, Map<String, Map<String, Object>>> getQtrWiseSales() {
		return qtrWiseSales;
	}

	public void setQtrWiseSales(Map<String, Map<String, Map<String, Object>>> qtrWiseSales) {
		this.qtrWiseSales = qtrWiseSales;
	}

	public Map<String, List<Map<String, Object>>> getQrterWiseTop10Sales() {
		return qrterWiseTop10Sales;
	}

	public void setQrterWiseTop10Sales(Map<String, List<Map<String, Object>>> qrterWiseTop10Sales) {
		this.qrterWiseTop10Sales = qrterWiseTop10Sales;
	}

	public Map<String, List<Map<String, Object>>> getQrterWiseTop10Purchases() {
		return qrterWiseTop10Purchases;
	}

	public void setQrterWiseTop10Purchases(Map<String, List<Map<String, Object>>> qrterWiseTop10Purchases) {
		this.qrterWiseTop10Purchases = qrterWiseTop10Purchases;
	}

	public Map<String, Map<String, Map<String, Object>>> getQrterWiseLastYeasrTotalPurchases() {
		return qrterWiseLastYeasrTotalPurchases;
	}

	public void setQrterWiseLastYeasrTotalPurchases(
			Map<String, Map<String, Map<String, Object>>> qrterWiseLastYeasrTotalPurchases) {
		this.qrterWiseLastYeasrTotalPurchases = qrterWiseLastYeasrTotalPurchases;
	}

	public Map<String, Map<String, List<Map<String, Object>>>> getYearMonthWiseSales() {
		return yearMonthWiseSales;
	}

	public void setYearMonthWiseSales(Map<String, Map<String, List<Map<String, Object>>>> yearMonthWiseSales) {
		this.yearMonthWiseSales = yearMonthWiseSales;
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

	
	public List<MomSales> getMomSales() {
		return momSales;
	}

	public void setMomSales(List<MomSales> momSales) {
		this.momSales = momSales;
	}
	public Map<String, Map<String, Object>> getCreDebInfoGstr1() {
		if(creDebInfoGstr1==null)
			creDebInfoGstr1 = new HashMap<>();
		return creDebInfoGstr1;
	}




	public void setCreDebInfoGstr1(Map<String, Map<String, Object>> creDebInfoGstr1) {
		this.creDebInfoGstr1 = creDebInfoGstr1;
	}




	public Map<String, Object> getTotalCreDebInfoGstr1() {
		if(totalCreDebInfoGstr1==null)
			totalCreDebInfoGstr1 = new HashMap<>();
		return totalCreDebInfoGstr1;
	}

	public void setTotalCreDebInfoGstr1(Map<String, Object> totalCreDebInfoGstr1) {
		this.totalCreDebInfoGstr1 = totalCreDebInfoGstr1;
	}

	public List<Map<String, Map<String, Object>>> getMonthWisePurch() {
		return monthWisePurch;
	}

	public void setMonthWisePurch(List<Map<String, Map<String, Object>>> monthWisePurch) {
		this.monthWisePurch = monthWisePurch;
	}
	
	public Map<String,Map<String, Object>> addMonthWisePurch(Map<String,Map<String, Object>> m){
		getMonthWisePurch().add(m);
		return m;
	}

	public Map<String, Map<String, Map<String, Object>>> getYearWisePurchases() {
		return yearWisePurchases;
	}

	public void setYearWisePurchases(Map<String, Map<String, Map<String, Object>>> yearWisePurchases) {
		this.yearWisePurchases = yearWisePurchases;
	}

	
	public Map<String, List<Object>> getTop10yearWiseHsnSales() {
		return top10yearWiseHsnSales;
	}

	public void setTop10yearWiseHsnSales(Map<String, List<Object>> top10yearWiseHsnSales) {
		this.top10yearWiseHsnSales = top10yearWiseHsnSales;
	}

	public Map<String, List<Object>> getTop10yearWiseHsnPurchases() {
		return top10yearWiseHsnPurchases;
	}

	public void setTop10yearWiseHsnPurchases(Map<String, List<Object>> top10yearWiseHsnPurchases) {
		this.top10yearWiseHsnPurchases = top10yearWiseHsnPurchases;
	}

	@Override
	public String toString() {
		return "CAMGSTSpecificData [gstin=" + gstin + ", basicInfo=" + basicInfo + ", financialYearWiseData="
				+ financialYearWiseData + ", qrtlyReturnData=" + qrtlyReturnData + ", monthWiseData=" + monthWiseData
				+ ", yearWiseSales=" + yearWiseSales + ", financialYearWiseTop10Sales=" + financialYearWiseTop10Sales
				+ ", qtrWiseSales=" + qtrWiseSales + ", qrterWiseTop10Sales=" + qrterWiseTop10Sales
				+ ", financialYearWiseTop10Purchases=" + financialYearWiseTop10Purchases + ", qrterWiseTop10Purchases="
				+ qrterWiseTop10Purchases + ", yearWiseTotalPurchases=" + yearWiseTotalPurchases
				+ ", qrterWiseLastYeasrTotalPurchases=" + qrterWiseLastYeasrTotalPurchases + ", yearMonthWiseSales="
				+ yearMonthWiseSales + ", quarterlySummary=" + quarterlySummary + ", monthlySummary=" + monthlySummary
				+ ", momSales=" + momSales + ", creDebInfoGstr1=" + creDebInfoGstr1 + ", totalCreDebInfoGstr1="
				+ totalCreDebInfoGstr1 + ", monthWisePurch=" + monthWisePurch + ", yearWisePurchases="
				+ yearWisePurchases + ", top10yearWiseHsnSales=" + top10yearWiseHsnSales
				+ ", top10yearWiseHsnPurchases=" + top10yearWiseHsnPurchases + "]";
	}

	
	
	
	/*public Map<String, List<Map<String, Map<String, Object>>>> addfinancialYearWiseData(Map<String, List<Map<String, Map<String, Object>>>> map){
		if(getFinancialYearWiseData().containsKey(fyKey)) {
			getFinancialYearWiseData().get(fyKey).get(0).put(mKey, map);	
		}else {
			List<Map<String, Map<String, Object>>> list = new ArrayList<>();
			Map<String, Map<String, Object>> m = new HashMap<>();
    		m.put(mKey, map);
			list.add(m);
			getFinancialYearWiseData().put(fyKey, list);
		}
		
		return getFinancialYearWiseData(); 
		
	}*/

	
	
}
