package com.opl.mudra.api.oneform.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IndustrySectorSubSectorTeaserRequest {
	
	List<Long> industryList;
	List<Long> sectorList;
	List<Long> subSectorList;
	public List<Long> getIndustryList() {
		return industryList;
	}
	public void setIndustryList(List<Long> industryList) {
		this.industryList = industryList;
	}
	public List<Long> getSectorList() {
		return sectorList;
	}
	public void setSectorList(List<Long> sectorList) {
		this.sectorList = sectorList;
	}
	public List<Long> getSubSectorList() {
		return subSectorList;
	}
	public void setSubSectorList(List<Long> subSectorList) {
		this.subSectorList = subSectorList;
	}
	
	
	

}
