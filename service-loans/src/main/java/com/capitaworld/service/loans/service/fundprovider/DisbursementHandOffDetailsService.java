package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.sanction.DisbursementHandOffDetailsReqRes;

public interface DisbursementHandOffDetailsService {
	
	public DisbursementHandOffDetailsReqRes get(Long applicationId, Long proposalId) throws Exception;
	
	public DisbursementHandOffDetailsReqRes save(DisbursementHandOffDetailsReqRes request);

}
