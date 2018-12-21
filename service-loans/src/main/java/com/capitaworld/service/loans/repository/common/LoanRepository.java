package com.capitaworld.service.loans.repository.common;

import java.util.List;

public interface LoanRepository {
	
	public Object[] getRoleIdAndBranchIdByUserId(Long userId);
	
	public List<Object[]> searchProposalByOrgNameAndAppCode(Long orgId,String searchString,Long listLimit);
	
	public List<Object[]> searchProposalByOrgNameAndAppCode(Long orgId,String searchString,Long branchId,Long listLimit);

}
