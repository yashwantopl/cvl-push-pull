package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;

/**
 * @author Sanket
 *
 */
public interface FinancialArrangementDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest);

	public List<FinancialArrangementsDetailRequest> getFinancialArrangementDetailsList(Long id);

}
