package com.capitaworld.service.loans.service.teaser.finalview;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.teaser.finalview.LapFinalViewResponse;

public interface LapFinalViewService {

	public LapFinalViewResponse getLapFinalViewDetails(Long applicantId) throws LoansException;
}
