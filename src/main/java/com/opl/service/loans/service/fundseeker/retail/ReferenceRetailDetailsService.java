package com.opl.service.loans.service.fundseeker.retail;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.retail.ReferenceRetailDetailsRequest;

/**
 * @author Sanket
 *
 */
public interface ReferenceRetailDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public Boolean saveOrUpdateCoApplicant(FrameRequest frameRequest) throws LoansException;

	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailList(Long id, int applicationType) throws LoansException;

	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailListByPropsalId(Long proposalId, int applicationType) throws LoansException;

	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailListByPropsalIdAndCoAppId(Long proposalId, Long coAppId) throws LoansException;
}
