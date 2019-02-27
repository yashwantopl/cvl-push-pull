package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;

/**
 * @author Sanket
 *
 */
public interface FinancialArrangementDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<FinancialArrangementsDetailRequest> getFinancialArrangementDetailsList(Long id,Long userId) throws LoansException;

	public List<FinancialArrangementsDetailRequest> getManuallyAddedFinancialArrangementDetailsList(Long applicationId);

	public List<FinancialArrangementsDetailRequest> getFinancialArrangementDetailsListDirId(Long id,Long dirId) throws LoansException;
	
	public Boolean saveOrUpdate(List<FinancialArrangementsDetailRequest> existingLoanDetailRequest,Long applicationId,Long userId);
	
	public Boolean saveOrUpdate(List<FinancialArrangementsDetailRequest> existingLoanDetailRequest,Long applicationId,Long userId,Long directorId);
	
	public FinancialArrangementsDetailRequest getTotalEmiAndSanctionAmountByApplicationId(Long applicationId);

	public Double getTotalOfEmiByApplicationIdAndDirectorId(Long applicationId,Long directorId);

	public Double getTotalEmiOfAllDirByApplicationId(Long applicationId);

	public Boolean saveOrUpdateManuallyAddedLoans(List<FinancialArrangementsDetailRequest> finArrDetailRequest,Long applicationId,Long userId);

	public FinancialArrangementsDetailRequest getTotalEmiAndSanctionAmountByApplicationIdForUniforProduct(Long applicationId);

}
