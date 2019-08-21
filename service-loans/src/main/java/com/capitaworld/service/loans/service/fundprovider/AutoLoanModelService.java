package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.AutoLoanModelRequest;

public interface AutoLoanModelService {
	
	/**
	 * Saving Information to Master Tables
	 * @param autoLoanModelRequest
	 * @param autoLoanModelTempRefId
	 * @return
	 */
	public Boolean save(AutoLoanModelRequest autoLoanModelRequest,Long autoLoanModelTempRefId);
	
	/**
	 * Saving Information to Temporary Tables
	 * @param autoLoanModelRequest
	 * @return
	 */
	public Boolean saveToTemp(AutoLoanModelRequest autoLoanModelRequest);
	
	/**
	 * Getting Object From Temp Table
	 * @param modelId
	 * @param role
	 * @param userId
	 * @return
	 */
	public AutoLoanModelRequest getTemp(Long modelId, Long role, Long userId);
	
	/**
	 * Getting Master Object
	 * @param modelId
	 * @param role
	 * @param userId
	 * @return
	 */
	public AutoLoanModelRequest get(Long modelId, Long role, Long userId);

	
	/**
	 * Copying Temp Table Information to Master table on Approval by Checker
	 * @param modelId
	 * @return
	 */
	public Boolean copyTempToMaster(Long modelId);
}
