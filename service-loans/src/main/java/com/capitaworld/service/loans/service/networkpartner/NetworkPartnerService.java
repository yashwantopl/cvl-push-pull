package com.capitaworld.service.loans.service.networkpartner;

import java.util.List;
import java.util.Map;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.model.FpNpMappingRequest;
import org.json.simple.JSONObject;

import com.capitaworld.service.gateway.model.GatewayRequest;
import com.capitaworld.service.loans.model.NhbsApplicationRequest;
import com.capitaworld.service.loans.model.NhbsApplicationsResponse;

public interface NetworkPartnerService {
	
	public List<NhbsApplicationsResponse> getListOfProposals(NhbsApplicationRequest request,Long npOrgId,Long userId);
	
	public List<NhbsApplicationsResponse> getListOfAssignedProposals(NhbsApplicationRequest request);
	
	public boolean setMaker(NhbsApplicationRequest request);
	
	public JSONObject getNhbsProposalCount(NhbsApplicationRequest nhbsApplicationRequest,Long npOrgId);
	
	public GatewayRequest getPaymentStatuOfApplication(Long applicationId);
	
	public boolean sendSMSNotificationWhenCheckerAssignMaker(Long applicationId,Long assignedUserId);

	public List<LoanApplicationMaster> getApplicationListToAssignedCheckerFromBoFp(Long userId,Long appStatusId,Boolean forPagination,int pageIndex,int size);

	public String getCheckerName(FpNpMappingRequest fpNpMappingRequest);

	public List<NhbsApplicationsResponse> getListOfProposalsFP(NhbsApplicationRequest request,Long npOrgId,Long userId);

    //public List<NhbsApplicationsResponse> getListOfAssignedProposalsFP(NhbsApplicationRequest request);

	public boolean setFPMaker(NhbsApplicationRequest request);

	public boolean setFPChecker(NhbsApplicationRequest request);

    public List<NhbsApplicationsResponse> getListOfCheckerProposalsFP(NhbsApplicationRequest request);

	public JSONObject getFPProposalCount(NhbsApplicationRequest nhbsApplicationRequest,Long npOrgId);
}
