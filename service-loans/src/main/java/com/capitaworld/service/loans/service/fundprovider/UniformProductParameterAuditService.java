package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.model.corporate.UniformProductParamterRequest;

public interface UniformProductParameterAuditService {

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
	public List<UniformProductParamterRequest> get(Long userId,Long orgId);
	
}
