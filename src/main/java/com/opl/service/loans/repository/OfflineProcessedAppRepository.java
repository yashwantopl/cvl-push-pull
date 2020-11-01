package com.opl.service.loans.repository;

import java.util.Date;
import java.util.List;

import com.opl.service.loans.domain.fundseeker.IneligibleProposalDetails;


public interface OfflineProcessedAppRepository{

	public String getInEligibleRecordList(Long userId,Date fromDate,Date toDate);

//	public IneligibleProposalDetails findByAppliationId(Long applicationId,Long orgId);

	public boolean updateSanctionedFlag(Long appId,Long orgId,Long branchId,Long userId);

	public Integer checkBeforeOfflineSanctioned(Long appId);
	
	public String getSanctionedApplicationList(Long userId,Date fromDate,Date toDate);	
	
	public String getDisbursedApplicationList(Long userId,Date fromDate,Date toDate);
	
	public String getRejectProposalsList(Long userId,Date fromDate,Date toDate);

	public String getOtherProposalsList(Long userId);

	public List<Object[]> getHomeCounterDetail();


	/**
	 * Getting Uniform Proposals
	 * @param userId
	 * @return
	 */
	public String getUniformApplications(Long userId);

	/**
	 * Getting Uniform Sanctioned Proposals
	 * @param userId
	 * @return
	 */
	public String getUniformSanctionedApplicationList(Long userId);

	/**
	 * Get Uniform Disbursed Application List
	 *
	 * @param userId
	 * @return
	 */

	public String getUniformDisbursedApplicationList(Long userId);


	/**
	 * Getting Unfiform Rejected Application
	 * @param userId
	 * @return
	 */
	public String getUniformRejectProposalsList(Long userId);


	/**
	 * Getting Uniform Other Application List
	 * @param userId
	 * @return
	 */
	public String getUniformOtherProposalsList(Long userId);;
}