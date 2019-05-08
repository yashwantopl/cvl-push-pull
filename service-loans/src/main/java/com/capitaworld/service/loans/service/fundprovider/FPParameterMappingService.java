package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

public interface FPParameterMappingService {
	/**
	 * Inactivate Already Exists all mapping by fpProductId and Type and save new 
	 * @param fpProductId
	 * @param type
	 * @param parameterIds
	 * @return
	 */
	public Boolean inactiveAndSave(Long fpProductId, Integer type, List<Integer> parameterIds);

	/**
	 * Save Give Parameter Ids and Map with the FP ProductId
	 * @param fpProductId
	 * @param type
	 * @param parameterIds
	 * @return
	 */
	public Boolean save(Long fpProductId, Integer type, List<Integer> parameterIds);
	
	/**
	 * Getting List of Selected Parameters by FpProductId and Type
	 * 
	 * @param fpProductId
	 * @param type
	 * @return
	 */
	public List<Integer> getParameters(Long fpProductId, Integer type);
	
	
	/**
	 * Getting List of Selected Parameters by FpProductId and Type
	 * @param fpProductId
	 * @param type
	 * @return
	 */
	public List<Integer> getParametersTemp(Long fpProductId, Integer type);
	
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

	
}
