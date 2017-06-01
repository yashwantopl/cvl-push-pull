package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.LapPrimaryViewResponse;

public interface LapPrimaryViewService {

	public LapPrimaryViewResponse getLapPrimaryViewDetails(Long applicantId)throws Exception;
}
