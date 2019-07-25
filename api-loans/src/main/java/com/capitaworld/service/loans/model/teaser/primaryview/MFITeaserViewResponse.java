package com.capitaworld.service.loans.model.teaser.primaryview;

import java.util.List;

/**
 * @author rahul.meena
 *
 */
public class MFITeaserViewResponse {

	private String organisationName;
    private String fpProductName;
    
    /// FOR SCORING RELATED
    private String scoringModelName;
    private Object dataList;
    private Object dataObject;    
    private Object scoringResponseList;
    
    // FOR ASSESSMENT RELATED
    private Object eligibilityDataObject;
    
    // FOR MATCHES RELATED
    private List<?> matchesList;

    
    private Object cibilScore;


	public String getOrganisationName() {
		return organisationName;
	}


	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}


	public String getFpProductName() {
		return fpProductName;
	}


	public void setFpProductName(String fpProductName) {
		this.fpProductName = fpProductName;
	}


	public String getScoringModelName() {
		return scoringModelName;
	}


	public void setScoringModelName(String scoringModelName) {
		this.scoringModelName = scoringModelName;
	}


	public Object getDataList() {
		return dataList;
	}


	public void setDataList(Object dataList) {
		this.dataList = dataList;
	}


	public Object getDataObject() {
		return dataObject;
	}


	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}


	public Object getScoringResponseList() {
		return scoringResponseList;
	}


	public void setScoringResponseList(Object scoringResponseList) {
		this.scoringResponseList = scoringResponseList;
	}


	public Object getEligibilityDataObject() {
		return eligibilityDataObject;
	}


	public void setEligibilityDataObject(Object eligibilityDataObject) {
		this.eligibilityDataObject = eligibilityDataObject;
	}


	public List<?> getMatchesList() {
		return matchesList;
	}


	public void setMatchesList(List<?> matchesList) {
		this.matchesList = matchesList;
	}


	public Object getCibilScore() {
		return cibilScore;
	}


	public void setCibilScore(Object cibilScore) {
		this.cibilScore = cibilScore;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MFITeaserViewResponse [organisationName=" + organisationName + ", fpProductName=" + fpProductName
				+ ", scoringModelName=" + scoringModelName + ", dataList=" + dataList + ", dataObject=" + dataObject
				+ ", scoringResponseList=" + scoringResponseList + ", eligibilityDataObject=" + eligibilityDataObject
				+ ", matchesList=" + matchesList + ", cibilScore=" + cibilScore + "]";
	}
}
