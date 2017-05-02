package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailRequest;

/**
 * @author Sanket
 *
 */
public interface CreditCardsDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest);

	public List<CreditCardsDetailRequest> getExistingLoanDetailList(Long id, int applicationType) throws Exception;

}
