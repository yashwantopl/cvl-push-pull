package com.capitaworld.service.loans.service.teaser.finalview;

import com.capitaworld.service.loans.model.teaser.finalview.PersonalLoanFinalViewResponse;

public interface PersonalLoanFinalViewService {
	public PersonalLoanFinalViewResponse getPersonalLoanFinalViewDetails(Long applicantId) throws Exception;
}
