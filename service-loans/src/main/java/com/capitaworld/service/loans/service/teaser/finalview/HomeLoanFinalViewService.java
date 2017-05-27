package com.capitaworld.service.loans.service.teaser.finalview;

import com.capitaworld.service.loans.model.teaser.finalview.HomeLoanFinalViewResponse;

public interface HomeLoanFinalViewService {

	public HomeLoanFinalViewResponse getHomeLoanPrimaryViewDetails(Long applicantId, Long userId) throws Exception;
}
