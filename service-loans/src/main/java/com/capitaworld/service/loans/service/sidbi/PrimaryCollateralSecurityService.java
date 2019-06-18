package com.capitaworld.service.loans.service.sidbi;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;

/**
 * 
 * @author vijay.chauhan
 *
 */
public interface PrimaryCollateralSecurityService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;
}
