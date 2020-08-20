package com.opl.service.loans.service.teaser.primaryview;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.LoansResponse;

public interface CommonTeaserViewService {
	public Boolean getPrimaryViewDetails(Long applicantId,LoansResponse loansResponse)throws LoansException;

	
	public Boolean getFinalViewDetails(Long applicantId,LoansResponse loansResponse)throws LoansException;
}
