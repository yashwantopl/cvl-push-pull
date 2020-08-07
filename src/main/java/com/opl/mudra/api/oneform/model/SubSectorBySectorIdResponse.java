package com.opl.mudra.api.oneform.model;

import java.util.List;

/**
 * @author Sanket
 *
 */
public class SubSectorBySectorIdResponse {

	private MasterResponse sectorId;

	private MasterResponse industryId;

	private List<MasterResponse> subSectorIdList;

	public MasterResponse getSectorId() {
		return sectorId;
	}

	public MasterResponse getIndustryId() {
		return industryId;
	}

	public List<MasterResponse> getSubSectorIdList() {
		return subSectorIdList;
	}

	public void setSectorId(MasterResponse sectorId) {
		this.sectorId = sectorId;
	}

	public void setIndustryId(MasterResponse industryId) {
		this.industryId = industryId;
	}

	public void setSubSectorIdList(List<MasterResponse> subSectorIdList) {
		this.subSectorIdList = subSectorIdList;
	}
	
	

	
}
