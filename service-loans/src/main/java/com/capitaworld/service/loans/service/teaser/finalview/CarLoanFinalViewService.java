package com.capitaworld.service.loans.service.teaser.finalview;

import com.capitaworld.service.loans.model.teaser.finalview.CarLoanFinalViewResponse;

public interface CarLoanFinalViewService {

	public CarLoanFinalViewResponse getHomeLoanFinalViewDetails(Long applicantId) throws Exception;
}
