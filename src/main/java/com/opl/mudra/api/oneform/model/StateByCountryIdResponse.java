package com.opl.mudra.api.oneform.model;

import java.util.List;

/**
 * @author Sanket
 *
 */
public class StateByCountryIdResponse {
	
	private MasterResponse countryId;

	private List<MasterResponse> stateIdList;

	public MasterResponse getCountryId() {
		return countryId;
	}

	public List<MasterResponse> getStateIdList() {
		return stateIdList;
	}

	public void setCountryId(MasterResponse countryId) {
		this.countryId = countryId;
	}

	public void setStateIdList(List<MasterResponse> stateIdList) {
		this.stateIdList = stateIdList;
	}
	
	

}
