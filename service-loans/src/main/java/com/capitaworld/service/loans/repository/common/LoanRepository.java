package com.capitaworld.service.loans.repository.common;

import java.util.List;

public interface LoanRepository {
	
	public Object[] getRoleIdAndBranchIdByUserId(Long userId);
	
	public List<Object[]> searchProposalForHO(Long orgId,String searchString,Long listLimit);
	
	public List<Object[]> searchProposalForCheckerAndMaker(Long orgId,String searchString,Long branchId,Long listLimit);
	
	public List<Object[]> searchProposalForSMECC(Long orgId,String searchString,Long userId,Long listLimit);
	
	public Object[] fpDashBoardCountByOrgId(Long orgId);
	
	public Object[] fpDashBoardCountByOrgIdAndBranchId(Long orgId,Long branchId);
	
	public Object[] fpDashBoardCountByOrgIdAndUserId(Long orgId,Long userId);

}
