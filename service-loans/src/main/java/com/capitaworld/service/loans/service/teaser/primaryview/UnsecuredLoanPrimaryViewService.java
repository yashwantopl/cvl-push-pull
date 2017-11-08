package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.UnsecuredLoanPrimaryViewResponse;

public interface UnsecuredLoanPrimaryViewService {
	

	 public UnsecuredLoanPrimaryViewResponse getUnsecuredLoanPrimaryViewDetails(Long toApplicationId,Integer userType,Long fundProviderUserId) throws Exception;

}
