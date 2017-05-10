package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.BankAccountHeldDetailsRequest;

/**
 * @author Sanket
 *
 */
public interface BankAccountHeldDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;

	public List<BankAccountHeldDetailsRequest> getExistingLoanDetailList(Long id, int applicationType) throws Exception;

}
