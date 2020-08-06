package com.opl.mudra.api.oneform.model;

import java.util.List;

/**
 * @author Sanket
 *
 */
public class CityByStateIdResponse {
	
	private MasterResponse stateId;

	private List<MasterResponse> cityIdList;

	public MasterResponse getStateId() {
		return stateId;
	}

	public List<MasterResponse> getCityIdList() {
		return cityIdList;
	}

	public void setStateId(MasterResponse stateId) {
		this.stateId = stateId;
	}

	public void setCityIdList(List<MasterResponse> cityIdList) {
		this.cityIdList = cityIdList;
	}
	
	

}
