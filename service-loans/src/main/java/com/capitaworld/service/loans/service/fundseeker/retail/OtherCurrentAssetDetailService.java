package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailRequest;

/**
 * @author Sanket
 *
 */
public interface OtherCurrentAssetDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;

	public List<OtherCurrentAssetDetailRequest> getOtherCurrentAssetDetailList(Long id, int applicationType) throws Exception;

}
