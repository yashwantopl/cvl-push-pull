package com.opl.mudra.api.oneform.model;

import java.util.List;

public class IndustrySectorSubSectorTeaserResponse {
	MasterResponse industry;

	MasterResponse sector;
	private List<MasterResponse> subSectorList;
	public MasterResponse getIndustry() {
		return industry;
	}
	public void setIndustry(MasterResponse industry) {
		this.industry = industry;
	}
	public MasterResponse getSector() {
		return sector;
	}
	public void setSector(MasterResponse sector) {
		this.sector = sector;
	}
	public List<MasterResponse> getSubSectorList() {
		return subSectorList;
	}
	public void setSubSectorList(List<MasterResponse> subSectorList) {
		this.subSectorList = subSectorList;
	}
		
}
