package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.domain.fundprovider.FpCoLendingBanks;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.WorkflowData;
import com.capitaworld.service.loans.model.corporate.CoLendingRequest;

public interface CoLendingService {

	//List<?> getList(Long userId);

	List<FpCoLendingBanks> getBankList();

	Boolean saveOrUpdate(CoLendingRequest coLendingRequest, Long userOrgId);
	
	List<CoLendingRequest> listAll(Long userId, Long userOrgId,Long role);

	Boolean clickOnWorkFlowButton(WorkflowData workflowData);
	
	Boolean removeCoLendingProposal(Long id);
	
	Boolean inactiveCoLendingProposal(Long id);

	Boolean addReasonByJobId(DataRequest dataRequest);
	

	List<CoLendingRequest> listByOrgId(Long userOrgId);
}
