package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.HomeLoanModelRequest;

public interface HomeLoanModelService {
	
	/**
	 * Saving Information to Master Tables
	 * @param homeLoanModelRequest
	 * @param homeLoanModelTempRefId
	 * @return
	 */
	public Boolean save(HomeLoanModelRequest homeLoanModelRequest,Long homeLoanModelTempRefId);
	
	/**
	 * Saving Information to Temporary Tables
	 * @param homeLoanModelRequest
	 * @return
	 */
	public Boolean saveToTemp(HomeLoanModelRequest homeLoanModelRequest);
	
	/**
	 * Getting Object From Temp Table
	 * @param modelId
	 * @param role
	 * @param userId
	 * @return
	 */
	public HomeLoanModelRequest getTemp(Long modelId, Long role, Long userId);
	
	/**
	 * Getting Master Object
	 * @param modelId
	 * @param role
	 * @param userId
	 * @return
	 */
	public HomeLoanModelRequest get(Long modelId, Long role, Long userId);

	
	/**
	 * Copying Temp Table Information to Master table on Approval by Checker
	 * @param modelId
	 * @return
	 */
	public Boolean copyTempToMaster(Long modelId);
}
