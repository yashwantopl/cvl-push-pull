package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.OwnershipDetailRequest;

/**
 * @author Sanket
 *
 */
public interface OwnershipDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;

	public List<OwnershipDetailRequest> getOwnershipDetailList(Long applicationId) throws Exception;

}
