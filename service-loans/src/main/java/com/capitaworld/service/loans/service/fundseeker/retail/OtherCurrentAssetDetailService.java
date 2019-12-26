package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailRequest;

/**
 * @author Sanket
 *
 */
public interface OtherCurrentAssetDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public Boolean saveOrUpdateCoApplicant(FrameRequest frameRequest) throws LoansException;

	public List<OtherCurrentAssetDetailRequest> getOtherCurrentAssetDetailList(Long id, int applicationType) throws LoansException;
	
	public List<OtherCurrentAssetDetailRequest> getOtherCurrentAssetDetailListByProposalId(Long proposalId, int applicationType) throws LoansException;

	public List<OtherCurrentAssetDetailRequest> getOtherCurrentAssetDetailListByProposalIdAndCoAppId(Long proposalId, Long coAppId) throws LoansException;

}
