package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

/**
 * @author Sanket
 *
 */
public class ScotAnalysisDetailResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String concernsDetails;

	private String concernsMeasure;

	private String opportunitiesDetials;

	private String strengthDetails;

	private String weaknessDetials;

	private String weaknessMeasure;

	public String getConcernsDetails() {
		return concernsDetails;
	}

	public void setConcernsDetails(String concernsDetails) {
		this.concernsDetails = concernsDetails;
	}

	public String getConcernsMeasure() {
		return concernsMeasure;
	}

	public void setConcernsMeasure(String concernsMeasure) {
		this.concernsMeasure = concernsMeasure;
	}

	public String getOpportunitiesDetials() {
		return opportunitiesDetials;
	}

	public void setOpportunitiesDetials(String opportunitiesDetials) {
		this.opportunitiesDetials = opportunitiesDetials;
	}

	public String getStrengthDetails() {
		return strengthDetails;
	}

	public void setStrengthDetails(String strengthDetails) {
		this.strengthDetails = strengthDetails;
	}

	public String getWeaknessDetials() {
		return weaknessDetials;
	}

	public void setWeaknessDetials(String weaknessDetials) {
		this.weaknessDetials = weaknessDetials;
	}

	public String getWeaknessMeasure() {
		return weaknessMeasure;
	}

	public void setWeaknessMeasure(String weaknessMeasure) {
		this.weaknessMeasure = weaknessMeasure;
	}

	public ScotAnalysisDetailResponse() {
		super();
	}

	public ScotAnalysisDetailResponse(String concernsDetails, String concernsMeasure, String opportunitiesDetials,
			String strengthDetails, String weaknessDetials, String weaknessMeasure) {
		super();
		this.concernsDetails = concernsDetails;
		this.concernsMeasure = concernsMeasure;
		this.opportunitiesDetials = opportunitiesDetials;
		this.strengthDetails = strengthDetails;
		this.weaknessDetials = weaknessDetials;
		this.weaknessMeasure = weaknessMeasure;
	}
	
	

}
