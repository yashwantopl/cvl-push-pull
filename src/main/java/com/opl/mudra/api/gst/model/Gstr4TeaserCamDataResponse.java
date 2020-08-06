/**
 * 
 */
package com.opl.mudra.api.gst.model;

import com.opl.mudra.api.gst.model.yuva.response.publicapi.TaxPayersResposne;

/**
 * @author nilay.darji
 *
 */
public class Gstr4TeaserCamDataResponse {
	
	private Long applicationId;
	
	private String gstIn;
	
	private TaxPayersResposne tpResponse;
	
	private Gstr4KeyObservation keyObservation;
	
	private Gstr4OverView gstr4Overview;
	
	private Gstr4PopUpResponse gstr4QtrWiseSales;
	
	private Gstr4Top5Suppliers gstr4top5Suppliers;

	private Gstr4QuarterWisePurchase gstr4QuarterWisePurchase;

	private Gstr4CdnInfo gstr4CdnInfo;

	private Boolean isGstr4CompositeApi;

	public TaxPayersResposne getTpResponse() {
		return tpResponse;
	}

	public void setTpResponse(TaxPayersResposne tpResponse) {
		this.tpResponse = tpResponse;
	}

	public Gstr4KeyObservation getKeyObservation() {
		return keyObservation;
	}

	public void setKeyObservation(Gstr4KeyObservation keyObservation) {
		this.keyObservation = keyObservation;
	}

	public Gstr4OverView getGstr4Overview() {
		return gstr4Overview;
	}

	public void setGstr4Overview(Gstr4OverView gstr4Overview) {
		this.gstr4Overview = gstr4Overview;
	}

	public Gstr4PopUpResponse getGstr4QtrWiseSales() {
		return gstr4QtrWiseSales;
	}

	public void setGstr4QtrWiseSales(Gstr4PopUpResponse gstr4QtrWiseSales) {
		this.gstr4QtrWiseSales = gstr4QtrWiseSales;
	}

	public Gstr4Top5Suppliers getGstr4top5Suppliers() {
		return gstr4top5Suppliers;
	}

	public void setGstr4top5Suppliers(Gstr4Top5Suppliers gstr4top5Suppliers) {
		this.gstr4top5Suppliers = gstr4top5Suppliers;
	}

	public Gstr4QuarterWisePurchase getGstr4QuarterWisePurchase() {
		return gstr4QuarterWisePurchase;
	}

	public void setGstr4QuarterWisePurchase(Gstr4QuarterWisePurchase gstr4QuarterWisePurchase) {
		this.gstr4QuarterWisePurchase = gstr4QuarterWisePurchase;
	}

	public Gstr4CdnInfo getGstr4CdnInfo() {
		return gstr4CdnInfo;
	}

	public void setGstr4CdnInfo(Gstr4CdnInfo gstr4CdnInfo) {
		this.gstr4CdnInfo = gstr4CdnInfo;
	}

	public Boolean getIsGstr4CompositeApi() {
		return isGstr4CompositeApi;
	}

	public void setIsGstr4CompositeApi(Boolean isGstr4CompositeApi) {
		this.isGstr4CompositeApi = isGstr4CompositeApi;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getGstIn() {
		return gstIn;
	}

	public void setGstIn(String gstIn) {
		this.gstIn = gstIn;
	}


}
