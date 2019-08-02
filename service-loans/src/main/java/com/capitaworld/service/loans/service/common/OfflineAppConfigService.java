package com.capitaworld.service.loans.service.common;

import java.util.List;

import com.capitaworld.service.loans.model.common.OfflineAppConfigRequest;

public interface OfflineAppConfigService {
	
	/**
	 * Request to Store to Database
	 * @param appConfigRequest
	 * @param userId
	 * @return Updated Request Class
	 */
	public OfflineAppConfigRequest save(OfflineAppConfigRequest appConfigRequest,Long userId);
	
	
	/**
	 * It will give result based on OrgId and BusinessTypeid
	 * @param appConfigRequest
	 * @return
	 */
	public OfflineAppConfigRequest get(OfflineAppConfigRequest appConfigRequest);
	
	/**
	 * History (Audit Trail) based on Given data 
	 * @param appConfigRequest
	 * @return
	 */
	public List<OfflineAppConfigRequest> hisotry(OfflineAppConfigRequest appConfigRequest);
	
}
