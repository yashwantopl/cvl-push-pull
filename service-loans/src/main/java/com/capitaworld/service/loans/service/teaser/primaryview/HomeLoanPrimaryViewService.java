package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.HomeLoanPrimaryViewResponse;

public interface HomeLoanPrimaryViewService {

	public HomeLoanPrimaryViewResponse getHomeLoanPrimaryViewDetails(Long applicantId, Long userId)throws Exception;
}
