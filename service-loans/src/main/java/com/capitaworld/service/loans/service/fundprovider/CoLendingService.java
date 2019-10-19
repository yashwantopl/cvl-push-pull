package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.FpCoLendingBanks;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.NhbsApplicationRequest;
import com.capitaworld.service.loans.model.NhbsApplicationsResponse;
import com.capitaworld.service.loans.model.WorkflowData;
import com.capitaworld.service.loans.model.colending.CoLendingProposalResponse;
import com.capitaworld.service.loans.model.corporate.CoLendingRequest;
import org.json.simple.JSONObject;

import java.util.List;

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

	public Boolean activeCoLendingProposal(Long id) ;

	public JSONObject getFPProposalCount(NhbsApplicationRequest nhbsApplicationRequest, Long npOrgId);

	public List<CoLendingProposalResponse> getListOfCheckerProposalsFP(NhbsApplicationRequest request);
}
