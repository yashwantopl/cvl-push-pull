package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.corporate.WcTlParameterRequest;

public interface WcTlParameterService {
	public boolean saveOrUpdate(WcTlParameterRequest wcTlParameterRequest);
	
	public WcTlParameterRequest getWcTlRequest(Long id);

	/**
	 * @param mappingId
	 * @return
	 */
	public Boolean saveMasterFromTempWcTl(Long mappingId) throws Exception;

	/**
	 * @param wcTlParameterRequest
	 * @return
	 */
	public Boolean saveOrUpdateTemp(WcTlParameterRequest wcTlParameterRequest);
}
