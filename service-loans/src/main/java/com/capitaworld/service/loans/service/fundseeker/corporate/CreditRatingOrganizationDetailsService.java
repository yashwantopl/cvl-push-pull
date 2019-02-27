package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.CreditRatingOrganizationDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;

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
