/**
 * 
 */
package com.opl.mudra.api.gst.model.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opl.mudra.api.gst.model.Gstr4TeaserCamDataResponse;
import com.opl.mudra.api.gst.model.MomSales;

/**
 * @author sanket
 *
 */
public class CAMGSTData {
	
	private String gstin;
	private List<Map<String, Object>> topFiveCust;

	private List<Map<String, Object>>topFiveSupp;

	private List<Map<String, Map<String, Object>>>monthWiseSales;

	private List<Map<String, Object>>cdnInfo;

	private List<Map<String, Map<String, Object>>>monthWisePurch;

	private List<Map<String, Map<String, Object>>>inwardSupp;
	
	private Map<String, Object> overview;
	
	private Map<String, Object> purchaseTotal;
	
	private Map<String, Object> salesTotal;
	
	private Map<String, Object> cdnTotal;
	
	
	private Map<String, Object> keyObservation;

	private Map<String, Object> inwardSuppTotal;

	private Map<String, Object> totalOFTopFiveCust;

	private Map<String, Object> totalOFTopFivePurchase;
	
	private Map<String,Object> gstCompositeData;
	
	
	private List<MomSales> momSales;
	
	private List<MomSales> lastTwelveMonthMomSales;
	
	private Map<String, Map<String, Object>> creDebInfoGstr1;
	private Map<String, Object> totalCreDebInfoGstr1;
	private List<Map<String, Object>> monthWisePurchGstr2;
	private Map<String, Object> totalMonthWisePurchGstr2;

	private String reasonForGstNotApplicable;
	
	private Double totalMomSales;
	
	private String totalMomSalesInString;
	
	private Boolean isPrimary;
	
	private Double totalMomSalesForNAGST;
	
	private Gstr4TeaserCamDataResponse gstr4TeaserCamDataResponse;
	private Boolean isGstr4CompositeApi;
	
	private List<MomSales> monthWiseMomSales;
	private  Double last12totalSales;
	
	private  String last12totalSalesInString;
	
	
	
	public String getLast12totalSalesInString() {
		return last12totalSalesInString;
	}




	public void setLast12totalSalesInString(String last12totalSalesInString) {
		this.last12totalSalesInString = last12totalSalesInString;
	}




	public List<MomSales> getMonthWiseMomSales() {
		return monthWiseMomSales;
	}




	public void setMonthWiseMomSales(List<MomSales> monthWiseMomSales) {
		this.monthWiseMomSales = monthWiseMomSales;
	}




	/**
	 * @return the totalMomSalesForNAGST
	 */
	public Double getTotalMomSalesForNAGST() {
		return totalMomSalesForNAGST;
	}




	/**
	 * @param totalMomSalesForNAGST the totalMomSalesForNAGST to set
	 */
	public void setTotalMomSalesForNAGST(Double totalMomSalesForNAGST) {
		this.totalMomSalesForNAGST = totalMomSalesForNAGST;
	}




	/**
	 * @return the reasonForGstNotApplicable
	 */
	public String getReasonForGstNotApplicable() {
		return reasonForGstNotApplicable;
	}




	/**
	 * @param reasonForGstNotApplicable the reasonForGstNotApplicable to set
	 */
	public void setReasonForGstNotApplicable(String reasonForGstNotApplicable) {
		this.reasonForGstNotApplicable = reasonForGstNotApplicable;
	}




	private GSTNotApplicable gstNotApplicable;

	private Boolean isGSTNotApplicable;




	public GSTNotApplicable getGstNotApplicable() {
		return gstNotApplicable;
	}




	public void setGstNotApplicable(GSTNotApplicable gstNotApplicable) {
		this.gstNotApplicable = gstNotApplicable;
	}




	public Boolean getIsGSTNotApplicable() {
		return isGSTNotApplicable;
	}




	public void setIsGSTNotApplicable(Boolean isGSTNotApplicable) {
		this.isGSTNotApplicable = isGSTNotApplicable;
	}




	/**
	 * @return the momSales
	 */
	public List<MomSales> getMomSales() {
		return momSales;
	}




	/**
	 * @param momSales the momSales to set
	 */
	public void setMomSales(List<MomSales> momSales) {
		this.momSales = momSales;
	}




	/**
	 * @return the keyObservation
	 */
	public Map<String, Object> getKeyObservation() {
		return keyObservation;
	}




	/**
	 * @param keyObservation the keyObservation to set
	 */
	public void setKeyObservation(Map<String, Object> keyObservation) {
		this.keyObservation = keyObservation;
	}


	public CAMGSTData() {
		this.topFiveCust = new ArrayList<>();
		this.topFiveSupp = new ArrayList<>();
		this.monthWiseSales = new ArrayList<>();
		this.cdnInfo = new ArrayList<>();
		this.monthWisePurch = new ArrayList<>();
		this.inwardSupp = new ArrayList<>();
		this.overview = new HashMap<>();
		this.purchaseTotal = new HashMap<>();
		this.salesTotal = new HashMap<>();
		this.cdnTotal = new HashMap<>();
		this.keyObservation = new HashMap<>();
		this.inwardSuppTotal = new HashMap<>();
		this.totalOFTopFiveCust = new HashMap<>();
		this.totalOFTopFivePurchase = new HashMap<>();
		this.gstCompositeData= new HashMap<>();
		this.gstNotApplicable = new GSTNotApplicable();
		this.isGSTNotApplicable = false;
	}

	/**
	 * @return the purchaseTotal
	 */
	public Map<String, Object> getPurchaseTotal() {
		return purchaseTotal;
	}

	/**
	 * @param purchaseTotal the purchaseTotal to set
	 */
	public void setPurchaseTotal(Map<String, Object> purchaseTotal) {
		this.purchaseTotal = purchaseTotal;
	}

	/**
	 * @return the salesTotal
	 */
	public Map<String, Object> getSalesTotal() {
		return salesTotal;
	}

	/**
	 * @param salesTotal the salesTotal to set
	 */
	public void setSalesTotal(Map<String, Object> salesTotal) {
		this.salesTotal = salesTotal;
	}

	/**
	 * @return the cdnTotal
	 */
	public Map<String, Object> getCdnTotal() {
		return cdnTotal;
	}

	/**
	 * @param cdnTotal the cdnTotal to set
	 */
	public void setCdnTotal(Map<String, Object> cdnTotal) {
		this.cdnTotal = cdnTotal;
	}

	/**
	 * @return the overview
	 */
	public Map<String, Object> getOverview() {
		return overview;
	}

	/**
	 * @param overview the overview to set
	 */
	public void setOverview(Map<String, Object> overview) {
		this.overview = overview;
	}

	/**
	 * @return the topFiveCust
	 */
	public List<Map<String, Object>>getTopFiveCust() {
		return topFiveCust;
	}

	/**
	 * @param topFiveCust the topFiveCust to set
	 */
	public void setTopFiveCust(List<Map<String, Object>>topFiveCust) {
		this.topFiveCust = topFiveCust;
	}

	/**
	 * @return the topFiveSupp
	 */
	public List<Map<String, Object>>getTopFiveSupp() {
		return topFiveSupp;
	}

	/**
	 * @param topFiveSupp the topFiveSupp to set
	 */
	public void setTopFiveSupp(List<Map<String, Object>>topFiveSupp) {
		this.topFiveSupp = topFiveSupp;
	}

	/**
	 * @return the monthWiseSales
	 */
	public List<Map<String, Map<String, Object>>>getMonthWiseSales() {
		return monthWiseSales;
	}

	/**
	 * @param monthWiseSales the monthWiseSales to set
	 */
	public void setMonthWiseSales(List<Map<String,Map<String, Object>>>monthWiseSales) {
		this.monthWiseSales = monthWiseSales;
	}

	/**
	 * @return the cdnInfo
	 */
	public List<Map<String, Object>>getCdnInfo() {
		return cdnInfo;
	}

	/**
	 * @param cdnInfo the cdnInfo to set
	 */
	public void setCdnInfo(List <Map<String, Object>>cdnInfo) {
		this.cdnInfo = cdnInfo;
	}

	/**
	 * @return the monthWisePurch
	 */
	public List<Map<String, Map<String, Object>>>getMonthWisePurch() {
		return monthWisePurch;
	}

	/**
	 * @param monthWisePurch the monthWisePurch to set
	 */
	public void setMonthWisePurch(List<Map<String, Map<String, Object>>>monthWisePurch) {
		this.monthWisePurch = monthWisePurch;
	}

	/**
	 * @return the inwardSupp
	 */
	public List<Map<String, Map<String, Object>>>getInwardSupp() {
		return inwardSupp;
	}

	/**
	 * @param inwardSupp the inwardSupp to set
	 */
	public void setInwardSupp(List<Map<String, Map<String, Object>>>inwardSupp) {
		this.inwardSupp = inwardSupp;
	}
	
	public Map<String,Map<String, Object>> addTopFiveCust(Map<String,Map<String, Object>> m){
		if(topFiveCust==null)
			topFiveCust =new ArrayList<>();
		//getTopFiveCust().add(m);
		return m;
	}
	

	public Map<String,Map<String, Object>> addTopFiveSupp(Map<String,Map<String, Object>> m){

		return m;
	}
	
	public Map<String,Map<String, Object>> addMonthWiseSales(Map<String,Map<String, Object>> m){
		getMonthWiseSales().add(m);
		return m;
	}
	
	public Map<String,Map<String, Object>> addCdnInfo(Map<String,Map<String, Object>> m){
		//getCdnInfo().add(m);
		return m;
	}
	
	public Map<String,Map<String, Object>> addMonthWisePurch(Map<String,Map<String, Object>> m){
		getMonthWisePurch().add(m);
		return m;
	}
	
	public Map<String,Map<String, Object>> addInwardSupp(Map<String,Map<String, Object>> m){
		getInwardSupp().add(m);
		return m;
	}

	public Map<String, Object> getInwardSuppTotal() {
		return inwardSuppTotal;
	}

	public void setInwardSuppTotal(Map<String, Object> inwardSuppTotal) {
		this.inwardSuppTotal = inwardSuppTotal;
	}

	public Map<String, Object> getTotalOFTopFiveCust() {
		return totalOFTopFiveCust;
	}

	public void setTotalOFTopFiveCust(Map<String, Object> totalOFTopFiveCust) {
		this.totalOFTopFiveCust = totalOFTopFiveCust;
	}

	public Map<String, Object> getTotalOFTopFivePurchase() {
		return totalOFTopFivePurchase;
	}

	public void setTotalOFTopFivePurchase(Map<String, Object> totalOFTopFivePurchase) {
		this.totalOFTopFivePurchase = totalOFTopFivePurchase;
	}




	public Map<String, Object> getGstCompositeData() {
		return gstCompositeData;
	}




	public void setGstCompositeData(Map<String, Object> gstCompositeData) {
		this.gstCompositeData = gstCompositeData;
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




	public List<Map<String, Object>> getMonthWisePurchGstr2() {
		if(monthWisePurchGstr2==null)
			monthWisePurchGstr2 = new ArrayList<>();
		return monthWisePurchGstr2;
	}




	public void setMonthWisePurchGstr2(List<Map<String, Object>> monthWisePurchGstr2) {
		this.monthWisePurchGstr2 = monthWisePurchGstr2;
	}




	public Map<String, Object> getTotalMonthWisePurchGstr2() {
		if(totalMonthWisePurchGstr2==null)
			totalMonthWisePurchGstr2 = new HashMap<>();
		return totalMonthWisePurchGstr2;
	}




	public void setTotalMonthWisePurchGstr2(Map<String, Object> totalMonthWisePurchGstr2) {
		this.totalMonthWisePurchGstr2 = totalMonthWisePurchGstr2;
	}




	public String getGstin() {
		return gstin;
	}




	public void setGstin(String gstin) {
		this.gstin = gstin;
	}




	public Double getTotalMomSales() {
		return totalMomSales;
	}




	public void setTotalMomSales(Double totalMomSales) {
		this.totalMomSales = totalMomSales;
	}




	/**
	 * @return the isPrimary
	 */
	public Boolean getIsPrimary() {
		return isPrimary;
	}




	/**
	 * @param isPrimary the isPrimary to set
	 */
	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Gstr4TeaserCamDataResponse getGstr4TeaserCamDataResponse() {
		return gstr4TeaserCamDataResponse;
	}

	public void setGstr4TeaserCamDataResponse(Gstr4TeaserCamDataResponse gstr4TeaserCamDataResponse) {
		this.gstr4TeaserCamDataResponse = gstr4TeaserCamDataResponse;
	}

	public Boolean getIsGstr4CompositeApi() {
		return isGstr4CompositeApi;
	}

	public void setIsGstr4CompositeApi(Boolean isGstr4CompositeApi) {
		this.isGstr4CompositeApi = isGstr4CompositeApi;
	}

	public String getTotalMomSalesInString() {
		return totalMomSalesInString;
	}

	public void setTotalMomSalesInString(String totalMomSalesInString) {
		this.totalMomSalesInString = totalMomSalesInString;
	}

	public List<MomSales> getLastTwelveMonthMomSales() {
		return lastTwelveMonthMomSales;
	}

	public void setLastTwelveMonthMomSales(List<MomSales> lastTwelveMonthMomSales) {
		this.lastTwelveMonthMomSales = lastTwelveMonthMomSales;
	}
	public Double getLast12totalSales() {
		return last12totalSales;
	}
	public void setLast12totalSales(Double last12totalSales) {
		this.last12totalSales = last12totalSales;
	}
	

}
