package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.FixedDepositsDetailsRequest;

/**
 * @author Sanket
 *
 */
public interface FixedDepositsDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;

	public List<FixedDepositsDetailsRequest> getFixedDepositsDetailList(Long id, int applicationType) throws Exception;

}
