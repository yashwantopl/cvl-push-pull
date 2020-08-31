/**
 * 
 */
package com.opl.service.loans.service.sidbi;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.sidbi.PersonalCorporateGuaranteeRequest;

/**
 * @author vijay.chauhan
 *
 */
public interface PersonalCorporateGuaranteeService {
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;
	public List<PersonalCorporateGuaranteeRequest> getPersonalCorporateGuaranteeListAppId(Long applicationId) throws LoansException;
}
