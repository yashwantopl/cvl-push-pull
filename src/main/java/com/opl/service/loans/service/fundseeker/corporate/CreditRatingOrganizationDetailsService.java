package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.CreditRatingOrganizationDetailRequest;
import com.opl.mudra.api.loans.model.FrameRequest;

/**
 * @author Sanket
 *
 */
public interface CreditRatingOrganizationDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<CreditRatingOrganizationDetailRequest> getcreditRatingOrganizationDetailsList(Long id,Long userId) throws LoansException;

	public List<CreditRatingOrganizationDetailRequest> getCreditRatingOrganizationDetailsListFromProposalId(Long proposalId,Long userId) throws Exception;

	public List<Integer> getShortTermCreditRatingForTeaser(Long id,Long userId) throws LoansException;
	
	public List<Integer> getLongTermCreditRatingForTeaser(Long id,Long userId) throws LoansException;
	
	public Boolean saveOrUpdateFromCibil(List<CreditRatingOrganizationDetailRequest> creditRatingList,Long applicationId, Long userId);

}
