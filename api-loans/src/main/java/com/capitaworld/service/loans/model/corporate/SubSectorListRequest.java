package com.capitaworld.service.loans.model.corporate;

import java.util.List;

public class SubSectorListRequest {
	
	Long sectorId;
	
	List<Long> subSectorIdList;

	public Long getSectorId() {
		return sectorId;
	}

	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}

	public List<Long> getSubSectorIdList() {
		return subSectorIdList;
	}

	public void setSubSectorIdList(List<Long> subSectorIdList) {
		this.subSectorIdList = subSectorIdList;
	}

		
	
	

}
