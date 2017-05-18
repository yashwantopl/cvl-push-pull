package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.domain.IndustrySectorDetail;


public interface IndustrySectorService {
	public void saveOrUpdate(IndustrySectorDetail industrySectorDetail);
	
	public void getIndustryByApplicantId(Long applicantId);
	
	//public void getIndustryByProductId(Long applicantId);
}
