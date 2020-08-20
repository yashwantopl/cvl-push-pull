package com.opl.service.loans.service.fundprovider;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.corporate.WcTlParameterRequest;

public interface WcTlParameterService {
	public boolean saveOrUpdate(WcTlParameterRequest wcTlParameterRequest,Long mappingId);
	
	public WcTlParameterRequest getWcTlRequest(Long id,Long role);

	/**
	 * @param mappingId
	 * @return
	 */
	public Boolean saveMasterFromTempWcTl(Long mappingId) throws LoansException;

	/**
	 * @param wcTlParameterRequest
	 * @return
	 */
	public Boolean saveOrUpdateTemp(WcTlParameterRequest wcTlParameterRequest);

	WcTlParameterRequest getWcTlRequestTemp(Long id,Long role,Long userId);
}
