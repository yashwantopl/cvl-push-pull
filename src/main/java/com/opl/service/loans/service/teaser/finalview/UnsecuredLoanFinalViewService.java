package com.opl.service.loans.service.teaser.finalview;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.teaser.finalview.UnsecuredLoanFinalViewResponse;

public interface UnsecuredLoanFinalViewService {
	 public UnsecuredLoanFinalViewResponse getUnsecuredLoanFinalViewDetails(Long toApplicationId,Integer userType,Long fundProviderUserId) throws LoansException;
}
