package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.model.RetailModelRequest;
import com.capitaworld.service.loans.model.WorkflowData;


public interface RetailModelService {
	
	/**
	 * Saving Approved Model to Master Table
	 * @param modelRequest
	 * @return
	 */
	public Boolean save(RetailModelRequest modelRequest);
	
	
	/**
	 * Saving New Created Model Temp table
	 * @param modelRequest
	 * @return
	 */
	public Boolean saveToTemp(RetailModelRequest modelRequest);
	
	/**
	 * Getting List of Retail Model Temp by OrgId
	 * @param modelRequest
	 * @return
	 */
	public List<RetailModelRequest> getTemp(Long orgId);
	
	/**
	 *  Getting List of Retail Model Master by OrgId
	 * @param orgId
	 * @return
	 */
	public List<RetailModelRequest> get(Long orgId);
	
	/**
	 * Managing Maker Checker Flow
	 * @param workflowData
	 * @return
	 */
	public Boolean processWorkflow(WorkflowData workflowData,Integer businessTypeId);
}
