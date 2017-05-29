package com.capitaworld.service.loans.service.teaser.finalview;

import com.capitaworld.service.loans.model.teaser.finalview.CarLoanFinalViewResponse;

public interface CarLoanFinalViewService {

	public CarLoanFinalViewResponse getCarLoanFinalViewDetails(Long applicantId) throws Exception;
}
