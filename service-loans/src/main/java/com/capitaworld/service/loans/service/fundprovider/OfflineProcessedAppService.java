package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.model.OfflineProcessedApplicationRequest;

public interface OfflineProcessedAppService {

	public List<OfflineProcessedApplicationRequest> getApplicationList(Long userId);
	
	public List<OfflineProcessedApplicationRequest> getSanctionedApplicationList(Long userId);
	
	public List<OfflineProcessedApplicationRequest> getDisbursedApplicationList(Long userId);
	
	public List<OfflineProcessedApplicationRequest> getRejectProposalList(Long userId);
	
	public List<OfflineProcessedApplicationRequest> getOtherProposalList(Long userId);
}
