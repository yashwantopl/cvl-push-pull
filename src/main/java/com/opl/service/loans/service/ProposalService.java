
package com.opl.service.loans.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.opl.mudra.api.connect.ConnectRequest;
import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FundProviderProposalDetails;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.model.ProposalDetailsAdminRequest;
import com.opl.mudra.api.loans.model.ProposalResponse;
import com.opl.mudra.api.loans.model.common.ProposalSearchResponse;
import com.opl.mudra.api.loans.model.common.ReportRequest;
import com.opl.mudra.api.matchengine.model.DisbursementDetailsModel;
import com.opl.mudra.api.matchengine.model.DisbursementRequestModel;
import com.opl.mudra.api.matchengine.model.ProposalCountResponse;
import com.opl.mudra.api.matchengine.model.ProposalMappingRequest;
import com.opl.mudra.api.matchengine.model.ProposalMappingResponse;
import com.opl.mudra.api.notification.model.SchedulerDataMultipleBankRequest;
import com.opl.mudra.api.user.model.UsersRequest;
import com.opl.service.loans.domain.fundprovider.ProposalDetails;

public interface ProposalService {

	public List<?>  fundproviderProposal(ProposalMappingRequest request);

	public List<?>  basicInfoForSearch(ProposalMappingRequest request);

	public List<?>  fundproviderProposalByProposalId(ProposalMappingRequest request);

	public List<?> fundproviderProposalByAssignBy(ProposalMappingRequest request);
	
	public String getOfflineProposalList(Long applicationId);
	
	public List<FundProviderProposalDetails>  fundseekerProposal(ProposalMappingRequest request,Long userId);
	
	public ProposalCountResponse fundProviderProposalCount(ProposalMappingRequest request);
	
	public ProposalCountResponse fundSeekerProposalCount(ProposalMappingRequest request);
	
	public ProposalMappingResponse get(ProposalMappingRequest request);

	public ProposalMappingResponse getSanctionProposalByApplicationId(Long applicationId,Long userOrgId);

	public ProposalMappingResponse changeStatus(ProposalMappingRequest request);
	
	public ProposalMappingResponse getProposalId(ProposalMappingRequest reuqest);
	
	public ProposalMappingResponse sendRequest(ProposalMappingRequest request);
	
	public ProposalMappingResponse listOfFundSeekerProposal(ProposalMappingRequest request);
	
	public ProposalResponse getConectionList(ProposalMappingRequest proposalMappingRequest);
	
	public Integer getPendingProposalCount(Long applicationId);
	
	public ProposalMappingResponse updateAssignDetails(ProposalMappingRequest request)  throws LoansException;

	public ProposalMappingResponse saveDisbursementDetails(DisbursementDetailsModel request, Long userId);

	public LoansResponse checkMinMaxAmount(UsersRequest userRequest,Long userOrgId);
	
	public List<ProposalDetailsAdminRequest> getProposalsByOrgId(Long userOrgId, ProposalDetailsAdminRequest request, Long userId);
	
	public Object getHomeCounterDetail();

	public List<ProposalSearchResponse> searchProposalByAppCode(Long loginUserId,Long loginOrgId,ReportRequest reportRequest,Long businessTypeId);

	public Map<String , Double> getFpDashBoardCount(Long loginUserId,Long loginOrgId,Long businessTypeId);

	public Integer updateStatus(Long applicationId,Long fpProductId,Long status,String remarks);

    public Boolean checkAvailabilityForBankSelection(Long applicationId, Integer businessTypeId);

	public Boolean checkMainLogicForMultiBankSelection(Long applicationId, Integer businessTypeId,List<ConnectRequest> filteredAppListList);

	public Boolean checkLogicForOfflineMultiBankSelection(Long applicationId,List<ProposalDetails> proposalDetailsList,List<ConnectRequest> filteredAppListList);

	public List<SchedulerDataMultipleBankRequest> getApplicationListForMultipleBank();
	
	public String getDayDiffrenceForInprinciple(Integer loanType);

	public ProposalMappingResponse getDisbursementRequestDetails(DisbursementRequestModel request);

	public ProposalMappingResponse saveDisbursementRequestDetails(MultipartFile[] multipartFiles, String request);

}
