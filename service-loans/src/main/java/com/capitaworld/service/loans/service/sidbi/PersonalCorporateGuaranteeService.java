/**
 * 
 */
package com.capitaworld.service.loans.service.sidbi;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.sidbi.PersonalCorporateGuaranteeRequest;

/**
 * @author vijay.chauhan
 *
 */
public interface PersonalCorporateGuaranteeService {
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;
	public List<PersonalCorporateGuaranteeRequest> getPersonalCorporateGuaranteeListAppId(Long applicationId) throws LoansException;
}
