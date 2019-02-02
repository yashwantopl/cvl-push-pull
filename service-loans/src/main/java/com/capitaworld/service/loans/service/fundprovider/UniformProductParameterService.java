package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.domain.fundprovider.UniformProductParamter;
import com.capitaworld.service.loans.model.corporate.UniformProductParamterRequest;

public interface UniformProductParameterService {

	/**
	 * Saving Uniform Product Parameter
	 * @param productParamterRequest
	 * @param orgId
	 * @return
	 */
	public UniformProductParamter saveOrUpdate(UniformProductParamterRequest productParamterRequest);
	
	/**
	 * Get Uniform Product
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public UniformProductParamterRequest get(Long userId,Long orgId);
	
	/**
	 * Saving Uniform Product Parameter
	 * @param productParamterRequest
	 * @param orgId
	 * @return
	 */
	public Boolean saveOrUpdateTemp(UniformProductParamterRequest productParamterRequest);
	
	/**
	 * Get Uniform Product
	 * @param userId
	 * @param orgId
	 * @param roleIds
	 * @return
	 */
	public UniformProductParamterRequest getTempObj(Long userId,Long orgId,List<Long> roleIds);
}
