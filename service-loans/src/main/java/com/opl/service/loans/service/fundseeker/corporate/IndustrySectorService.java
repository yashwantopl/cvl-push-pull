package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.service.loans.domain.IndustrySectorDetail;


public interface IndustrySectorService {
	public void saveOrUpdate(IndustrySectorDetail industrySectorDetail) throws LoansException;
	
	public List<Long> getIndustryByApplicantId(Long applicantId) throws LoansException;
	
	public List<Long> getSectorByApplicantId(Long applicantId) throws LoansException;
	
	//public void getIndustryByProductId(Long applicantId);
}
