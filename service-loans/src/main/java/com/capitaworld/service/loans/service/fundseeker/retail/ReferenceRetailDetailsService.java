package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;

/**
 * @author Sanket
 *
 */
public interface ReferenceRetailDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailList(Long id, int applicationType) throws LoansException;

	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailListByPropsalId(Long proposalId, int applicationType) throws LoansException;
}
