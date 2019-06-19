package com.capitaworld.service.loans.service.sidbi;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.sidbi.PrimaryCollateralSecurityRequest;

/**
 * 
 * @author vijay.chauhan
 *
 */
public interface PrimaryCollateralSecurityService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;
	public List<PrimaryCollateralSecurityRequest> getPrimaryCollateralSecurityListAppId(Long applicationId) throws LoansException;
	
	
}
