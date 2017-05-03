package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.GuarantorsCorporateDetailRequest;

/**
 * @author Sanket
 *
 */
public interface GuarantorsCorporateDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest);

	public List<GuarantorsCorporateDetailRequest> getGuarantorsCorporateDetailList(Long id);

}
