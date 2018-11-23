package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.ExistingProductDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;

/**
 * @author Sanket
 *
 */
public interface ExistingProductDetailsService {

	Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;
	
	List<ExistingProductDetailRequest> getExistingProductDetailList(Long applicationId, Long userId) throws Exception;

}
