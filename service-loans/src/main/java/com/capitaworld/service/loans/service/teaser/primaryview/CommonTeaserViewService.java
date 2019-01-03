package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.LoansResponse;

public interface CommonTeaserViewService {
	public Boolean getPrimaryViewDetails(Long applicantId,LoansResponse loansResponse)throws Exception;

	
	public Boolean getFinalViewDetails(Long applicantId,LoansResponse loansResponse)throws Exception;
}
