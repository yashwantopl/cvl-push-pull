package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.MonthlyTurnoverDetailRequest;

/**
 * @author Sanket
 *
 */
public interface MonthlyTurnoverDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<MonthlyTurnoverDetailRequest> getMonthlyTurnoverDetailList(Long id,Long userId) throws LoansException;

}
