package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailRequest;

/**
 * @author Sanket
 *
 */
public interface PromotorBackgroundDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;

	List<PromotorBackgroundDetailRequest> getPromotorBackgroundDetailList(Long applicationId);

}
