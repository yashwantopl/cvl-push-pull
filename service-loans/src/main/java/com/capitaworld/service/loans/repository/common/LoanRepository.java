package com.capitaworld.service.loans.repository.common;

import java.util.List;

public interface LoanRepository {

	public Object[] getRoleIdAndBranchIdByUserId(Long userId);
	
	public String getMobileNumberByUserId(Long userId);	

	public List<Object[]> searchProposalForHO(Long orgId,String searchString,Long listLimit,Long businessTypeId);

	public List<Object[]> searchProposalForCheckerAndMaker(Long orgId,String searchString,Long branchId,Long listLimit,Long businessTypeId);

	public List<Object[]> searchProposalForSMECC(Long orgId,String searchString,Long userId,Long listLimit,Long businessTypeId);

	public Object[] fpDashBoardCountByOrgId(Long orgId,Long businessTypeId);

	public Object[] fpDashBoardCountByOrgIdAndBranchId(Long orgId,Long branchId,Long businessTypeId);

	public Object[] fpDashBoardCountByOrgIdAndUserId(Long orgId,Long userId,Long businessTypeId);

	public String getGSTINByAppId(Long applicationId);

	public String getCommonPropertiesValue(String key);
	
	public Long getOfflineCountByAppId(Long applicationId);
	
	public Double getRetailLoanAmountByApplicationId(Long applicationId);
	
	public Boolean isITRUploaded(Long applicationId);
	
	public Boolean isITRUploadedForCoApp(Long applicationId,Long coAppId);
	
	public Boolean isITRSkippedForCoApp(Long applicationId, Long coAppId);
	
	public Boolean isITRMannualForCoApp(Long applicationId, Long coAppId);
	
	public String getOfflineDetailsByAppId(Long applicationId);
	
	public String getOfflineStatusByAppId(Long applicationId);
	
	public List<Double> getIncomeOfItrOf3Years(Long applicationId);

	public List<Double> getIncomeOfItrOf3YearsOfCoApplicant(Long coAppId);
}
