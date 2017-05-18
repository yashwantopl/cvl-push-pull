package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.domain.IndustrySectorDetail;


public interface IndustrySectorService {
	public void saveOrUpdate(IndustrySectorDetail industrySectorDetail) throws Exception;
	
	public List<Long> getIndustryByApplicantId(Long applicantId) throws Exception;
	
	public List<Long> getSectorByApplicantId(Long applicantId) throws Exception;
	
	//public void getIndustryByProductId(Long applicantId);
}
