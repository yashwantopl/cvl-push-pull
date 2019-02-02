package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.model.OfflineProcessedApplicationRequest;

public interface OfflineProcessedAppService {

	public List<OfflineProcessedApplicationRequest> getApplicationList(Long userId);
	
	public List<OfflineProcessedApplicationRequest> getSanctionedApplicationList(Long userId);
	
	public List<OfflineProcessedApplicationRequest> getDisbursedApplicationList(Long userId);
	
	public List<OfflineProcessedApplicationRequest> getRejectProposalList(Long userId);
	
	public List<OfflineProcessedApplicationRequest> getOtherProposalList(Long userId);
	
	/**
	 * Getting Application List of Uniform Product
	 * @param userId
	 * @return
	 */
	public List<OfflineProcessedApplicationRequest> getUniformApplicationList(Long userId);
	
	/**
	 * Getting Sanctioned List
	 * @param userId
	 * @return
	 */
	public List<OfflineProcessedApplicationRequest> getUniformSanctionedApplicationList(Long userId);
	
	/**
	 * Getting Uniform Disbursed Application List
	 * @param userId
	 * @return
	 */
	
	public List<OfflineProcessedApplicationRequest> getUniformDisbursedApplicationList(Long userId);
	
	
	/**
	 * Get Rejected Uniform Application
	 * @param userId
	 * @return
	 */
	public List<OfflineProcessedApplicationRequest> getUniformRejectProposalList(Long userId);
	
	
	/**
	 * Getting Uniform Other Application List
	 * @param userId
	 * @return
	 */
	
	public List<OfflineProcessedApplicationRequest> getUniformOtherProposalList(Long userId);
}
