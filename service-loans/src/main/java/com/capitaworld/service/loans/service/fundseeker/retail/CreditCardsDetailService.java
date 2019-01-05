package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailRequest;

/**
 * @author Sanket
 *
 */
public interface CreditCardsDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<CreditCardsDetailRequest> getCreditCardDetailList(Long id, int applicationType) throws LoansException;
	
	public Boolean saveOrUpdateFromCibil(List<CreditCardsDetailRequest> creditCardDetail,Long applicationId,Long userId,int applicantType) throws LoansException;

}
