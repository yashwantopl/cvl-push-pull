package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.corporate.UniformProductParamterRequest;

public interface UniformProductParameterService {

	/**
	 * Saving Uniform Product Parameter
	 * @param productParamterRequest
	 * @param orgId
	 * @return
	 */
	public Boolean saveOrUpdate(UniformProductParamterRequest productParamterRequest);
	
	/**
	 * Get Uniform Product
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public UniformProductParamterRequest get(Long userId,Long orgId);
}
