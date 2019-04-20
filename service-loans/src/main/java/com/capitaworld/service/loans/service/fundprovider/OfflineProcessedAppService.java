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
	public String getUniformApplicationList(Long userId);
	
	/**
	 * Getting Sanctioned List
	 * @param userId
	 * @return
	 */
	public String getUniformSanctionedApplicationList(Long userId);

	/**
	 * Getting Uniform Disbursed Application List
	 * @param userId
	 * @return
	 */

	public String getUniformDisbursedApplicationList(Long userId);


	/**
	 * Get Rejected Uniform Application
	 * @param userId
	 * @return
	 */
	public String getUniformRejectProposalList(Long userId);


	/**
	 * Getting Uniform Other Application List
	 * @param userId
	 * @return
	 */

	public String getUniformOtherProposalList(Long userId);
}
