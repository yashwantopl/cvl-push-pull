package com.capitaworld.service.loans.repository;

import java.util.List;

import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalDetails;


public interface OfflineProcessedAppRepository{

	public String getInEligibleRecordList(Long userId);

	public IneligibleProposalDetails findByAppliationId(Long applicationId,Long orgId);

	public boolean updateSanctionedFlag(Long appId,Long orgId,Long branchId,Long userId);

	public Integer checkBeforeOfflineSanctioned(Long appId);
	
	public String getSanctionedApplicationList(Long userId);
	
	public String getDisbursedApplicationList(Long userId);
	
	public String getRejectProposalsList(Long userId);

	public String getOtherProposalsList(Long userId);

	public List<Object[]> getHomeCounterDetail();


	/**
	 * Getting Uniform Proposals
	 * @param userId
	 * @return
	 */
	public List<Object []> getUniformApplications(Long userId);

	/**
	 * Getting Uniform Sanctioned Proposals
	 * @param userId
	 * @return
	 */
	public List<Object []> getUniformSanctionedApplicationList(Long userId);

	/**
	 * Get Uniform Disbursed Application List
	 *
	 * @param userId
	 * @return
	 */

	public List<Object []> getUniformDisbursedApplicationList(Long userId);


	/**
	 * Getting Unfiform Rejected Application
	 * @param userId
	 * @return
	 */
	public List<Object[]> getUniformRejectProposalsList(Long userId);


	/**
	 * Getting Uniform Other Application List
	 * @param userId
	 * @return
	 */
	public List<Object[]> getUniformOtherProposalsList(Long userId);;
}