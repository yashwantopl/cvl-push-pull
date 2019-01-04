package com.capitaworld.service.loans.repository;

import java.util.List;

import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalDetails;


public interface OfflineProcessedAppRepository{

	public List<Object []> getInEligibleRecordList(Long userId);
	
	public IneligibleProposalDetails findByAppliationId(Long applicationId);
	
	public boolean updateSanctionedFlag(Long appId,Long orgId,Long branchId,Long userId);
	
	public List<Object []> getSanctionedApplicationList(Long userId);
	
	public List<Object []> getDisbursedApplicationList(Long userId);
	
	public List<Object[]> getRejectProposalsList(Long userId);

	public List<Object[]> getHomeCounterDetail();
}