package com.opl.service.loans.service.fundseeker.retail;


import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.common.CreditCardsDetailRequest;

/**
 * @author Sanket
 *
 */
public interface CreditCardsDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<CreditCardsDetailRequest> getCreditCardDetailList(Long id, int applicationType) throws LoansException;
	
	public Boolean saveOrUpdateFromCibil(List<CreditCardsDetailRequest> creditCardDetail,Long applicationId,Long userId,int applicantType) throws LoansException;

}
