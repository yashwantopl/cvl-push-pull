package com.capitaworld.service.loans.repository;

import java.util.List;

import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalDetails;


public interface OfflineProcessedAppRepository{

	public List<Object []> getInEligibleRecordList(Long orgId);
	
	public IneligibleProposalDetails findByAppliationId(Long applicationId);
	
	public List<Object []> getSanctionedApplicationList(Long orgId);
	
	public List<Object []> getDisbursedApplicationList(Long orgId);

	public List<Object[]> getHomeCounterDetail();
}