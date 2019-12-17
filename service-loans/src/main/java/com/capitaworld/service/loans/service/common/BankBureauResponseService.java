package com.capitaworld.service.loans.service.common;

import java.util.List;

import com.capitaworld.service.matchengine.model.BankBureauRequest;

public interface BankBureauResponseService {

	/**
	 * Saving List of all product response 
	 * @param bankBureauRequests
	 * @return
	 */
	public boolean save(List<BankBureauRequest> bankBureauRequests);
	
	/**
	 * Save individual Record
	 * @param bankBureauRequest
	 * @return
	 */
	public boolean save(BankBureauRequest bankBureauRequest);
	
	/**
	 * Inactive by type and Save new Entry
	 * @param bankBureauRequests
	 * @param type
	 * @return
	 */
	public boolean inActiveAndsave(List<BankBureauRequest> bankBureauRequests,Integer type);
	
	
	/**
	 * inactive Existing Row by Application and FpProductId and Type
	 * @param bankBureauRequests
	 * @return
	 */
	public boolean inActiveAndsave(BankBureauRequest bankBureauRequests);
	
	
	/**
	 * Saving New Scoring response to DB
	 * @param bankBureauRequests
	 * @return
	 */
	public boolean inActiveAndsaveScoring(BankBureauRequest bankBureauRequest);
	
	/**
	 * Get Single Latest Row.
	 * @param applicationId
	 * @param fpProductId
	 * @param type
	 * @return
	 */
	public BankBureauRequest get(Long applicationId,Long fpProductId,Integer type);
	
}
