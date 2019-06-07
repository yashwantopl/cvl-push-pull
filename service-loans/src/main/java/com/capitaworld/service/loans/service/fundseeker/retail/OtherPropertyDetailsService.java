package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.domain.fundseeker.retail.OtherPropertyDetails;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.OtherPropertyDetailsRequest;
import com.capitaworld.service.loans.model.retail.PurchasePropertyDetailsRequest;

import java.util.List;

/**
 * @author Sanket
 *
 */
public interface OtherPropertyDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest,int type) throws LoansException;

	public List<OtherPropertyDetailsRequest> getPropertyDetailListByProposalId(Long proposalId, int applicationType,int propertyType) throws LoansException;
}
