package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.model.common.FPParameterMappingRequest;

public interface FPParameterMappingService {
	/**
	 * In activate Already Exists all mapping by fpProductId and Type and save new 
	 * @param fpProductId
	 * @param type
	 * @param parameterIds
	 * @return
	 */
	public Boolean inactiveAndSave(Long fpProductId, Integer type, List<Integer> parameterIds);
	
	/**
	 * In activate Already Exists all mapping by fpProductId and Type and save new With Object
	 * @param fpProductId
	 * @param type
	 * @param parameterIds
	 * @return
	 */
	public Boolean inactiveAndSaveWithObject(Long fpProductId, Integer type, List<FPParameterMappingRequest> parameterIds);

	/**
	 * Save Give Parameter Ids and Map with the FP ProductId
	 * @param fpProductId
	 * @param type
	 * @param parameterIds
	 * @return
	 */
	public Boolean save(Long fpProductId, Integer type, List<Integer> parameterIds);
	
	/**
	 * Save Give Parameter Object List and Map with the FP ProductId
	 * @param fpProductId
	 * @param type
	 * @param parameterIds
	 * @return
	 */
	public Boolean saveWithObject(Long fpProductId, Integer type, List<FPParameterMappingRequest> parameterIds);
	
	/**
	 * Getting List of Selected Parameters by FpProductId and Type
	 * 
	 * @param fpProductId
	 * @param type
	 * @return
	 */
	public List<Integer> getParameters(Long fpProductId, Integer type);
	
	/**
	 * Getting List of Selected Parameter's Object by FpProductId and Type
	 * @param fpProductId
	 * @param type
	 * @return
	 */
	public List<FPParameterMappingRequest> getParametersWithObject(Long fpProductId, Integer type);
	
	/**
	 * Getting List of Selected Parameters by FpProductId and Type
	 * @param fpProductId
	 * @param type
	 * @return
	 */
	public List<Integer> getParametersTemp(Long fpProductId, Integer type);
	
	/**
	 * Getting List of Selected Parameter's Object by FpProductId and Type
	 * @param fpProductId
	 * @param type
	 * @return
	 */
	public List<FPParameterMappingRequest> getParametersTempWithObject(Long fpProductId, Integer type);
	
	/**
	 * Inactivate Already Exists all mapping by fpProductId and Type and save
	 * new
	 * 
	 * @param fpProductId
	 * @param type
	 * @param parameterIds
	 * @return
	 */
	public Boolean inactiveAndSaveTemp(Long fpProductId, Integer type, List<Integer> parameterIds);

	/**
	 * Save Give Parameter Ids and Map with the FP ProductId
	 * 
	 * @param fpProductId
	 * @param type
	 * @param parameterIds
	 * @return
	 */
	public Boolean saveTemp(Long fpProductId, Integer type, List<Integer> parameterIds);
	

	/**
	 * Inactivate Already Exists all mapping by fpProductId and Type and save Whole List
	 * @param fpProductId
	 * @param type
	 * @param fpParameterMappingRequests
	 * @return
	 */
	public Boolean inactiveAndSaveTempWithObject(Long fpProductId, Integer type, List<FPParameterMappingRequest> fpParameterMappingRequests);
	
	/**
	 * Save Given Parameter List and Map with the FP ProductId
	 * @param fpProductId
	 * @param type
	 * @param fpParameterMappingRequests
	 * @return
	 */
	public Boolean saveTempWithObject(Long fpProductId, Integer type, List<FPParameterMappingRequest> fpParameterMappingRequests);

	
}
