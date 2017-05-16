package com.capitaworld.service.loans.model.corporate;

import java.util.List;

public class SubSectorListRequest {
	
	Long sectorId;
	
	Long industryId;
	
	List<SubSectorMappingRequest> subSectorIdList;

	public Long getSectorId() {
		return sectorId;
	}

	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}

	public List<SubSectorMappingRequest> getSubSectorIdList() {
		return subSectorIdList;
	}

	public void setSubSectorIdList(List<SubSectorMappingRequest> subSectorIdList) {
		this.subSectorIdList = subSectorIdList;
	}

	public Long getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	
		
	
	

}
