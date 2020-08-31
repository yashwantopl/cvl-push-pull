package com.opl.service.loans.service.sidbi;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.sidbi.PrimaryCollateralSecurityRequest;

/**
 * 
 * @author vijay.chauhan
 *
 */
public interface PrimaryCollateralSecurityService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;
	public List<PrimaryCollateralSecurityRequest> getPrimaryCollateralSecurityListAppId(Long applicationId) throws LoansException;
	
	
}
