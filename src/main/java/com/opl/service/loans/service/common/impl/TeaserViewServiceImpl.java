package com.opl.service.loans.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.service.loans.repository.fundprovider.ProductMasterRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.service.common.TeaserViewService;

public class TeaserViewServiceImpl implements TeaserViewService {

	@Autowired
	LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	ProductMasterRepository productMasterRepository;
	
	@Override
	public Boolean checkPrimaryTeaserViewIsValid(Long applicationId, Long fpProductId, Long userType) {

		Long count;
		
		if(!(CommonUtils.UserType.FUND_PROVIDER == userType))
		{
			// check fund seeker has locked his profile and primary detailed
			count= loanApplicationRepository.checkPrimaryDetailIsLocked(applicationId);
			return count.intValue() > 0;
		}
		else
		{
			// check fund provider has filled its parameter detailed
			 count= productMasterRepository.checkParameterIsFilled(fpProductId);
			 return count.intValue() > 0;
		}
		
	}

	@Override
	public Boolean checkFinalTeaserViewIsValid(Long applicationId) {
		
		// check fund seeker has locked his final detailed
		Long count;
		count= loanApplicationRepository.checkFinalDetailIsLocked(applicationId);
		return count.intValue() > 0;
	}

}
