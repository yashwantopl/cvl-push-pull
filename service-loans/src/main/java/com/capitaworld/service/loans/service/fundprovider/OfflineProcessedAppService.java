package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.model.OfflineProcessedApplicationRequest;

public interface OfflineProcessedAppService {

	public List<OfflineProcessedApplicationRequest> getApplicationList(Long orgId, Long userId);
	
	public List<OfflineProcessedApplicationRequest> getSanctionedApplicationList(Long orgId, Long userId);
	
	public List<OfflineProcessedApplicationRequest> getDisbursedApplicationList(Long orgId, Long userId);
}
