package com.opl.service.loans.service.fundprovider;

import com.opl.mudra.api.loans.model.sanction.DisbursementHandOffDetailsReqRes;

public interface DisbursementHandOffDetailsService {
	
	public DisbursementHandOffDetailsReqRes get(Long applicationId, Long proposalId) throws Exception;
	
	public DisbursementHandOffDetailsReqRes save(DisbursementHandOffDetailsReqRes request);

}
