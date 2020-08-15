package com.opl.service.loans.service.teaser.primaryview;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.teaser.primaryview.UnsecuredLoanPrimaryViewResponse;

public interface UnsecuredLoanPrimaryViewService {
	

	 public UnsecuredLoanPrimaryViewResponse getUnsecuredLoanPrimaryViewDetails(Long toApplicationId,Integer userType,Long fundProviderUserId) throws LoansException;

}
