package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.domain.IndustrySectorDetail;
import com.capitaworld.service.loans.exceptions.LoansException;


public interface IndustrySectorService {
	public void saveOrUpdate(IndustrySectorDetail industrySectorDetail) throws LoansException;
	
	public List<Long> getIndustryByApplicantId(Long applicantId) throws LoansException;
	
	public List<Long> getSectorByApplicantId(Long applicantId) throws LoansException;
	
	//public void getIndustryByProductId(Long applicantId);
}
