package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.model.OfflineProcessedApplicationRequest;

public interface OfflineProcessedAppService {

	public String getApplicationList(Long userId);

	public String getSanctionedApplicationList(Long userId);

	public String getDisbursedApplicationList(Long userId);

	public String getRejectProposalList(Long userId);

	public String getOtherProposalList(Long userId);
	
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
