package com.capitaworld.service.loans.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.common.TeaserViewService;
import com.capitaworld.service.loans.utils.CommonUtils;

public class TeaserViewServiceImpl implements TeaserViewService {

	@Autowired
	LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	ProductMasterRepository productMasterRepository;
	
	@Override
	public Boolean checkPrimaryTeaserViewIsValid(Long applicationId, Long fpProductId, Long userType) {
		// TODO Auto-generated method stub

		Long count=(long) 0;
		
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
		// TODO Auto-generated method stub
		
		// check fund seeker has locked his final detailed
		Long count= (long) 0;
		count= loanApplicationRepository.checkFinalDetailIsLocked(applicationId);
		return count.intValue() > 0;
	}

}
