package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.OtherIncomeDetailRequest;

/**
 * @author Sanket
 *
 */
public interface OtherIncomeDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;

	public List<OtherIncomeDetailRequest> getOtherIncomeDetailList(Long id, int applicationType) throws Exception;

}
