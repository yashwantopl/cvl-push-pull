package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.HomeLoanPrimaryViewResponse;

public interface CommonTeaserViewService {
	public Boolean getPrimaryViewDetails(Long applicantId,LoansResponse loansResponse)throws Exception;

	
	public Boolean getFinalViewDetails(Long applicantId,LoansResponse loansResponse)throws Exception;
}
