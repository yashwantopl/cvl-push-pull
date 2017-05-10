package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.SecurityCorporateDetailRequest;

/**
 * @author Sanket
 *
 */
public interface SecurityCorporateDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;

	public List<SecurityCorporateDetailRequest> getsecurityCorporateDetailsList(Long id);

}
