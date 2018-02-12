package com.capitaworld.service.loans.service.networkpartner;

import java.util.List;

import org.json.simple.JSONObject;

import com.capitaworld.service.loans.model.NhbsApplicationRequest;
import com.capitaworld.service.loans.model.NhbsApplicationsResponse;

public interface NetworkPartnerService {
	
	public List<NhbsApplicationsResponse> getListOfProposals(NhbsApplicationRequest request);
	
	public List<NhbsApplicationsResponse> getListOfAssignedProposals(NhbsApplicationRequest request);
	
	public boolean setMaker(NhbsApplicationRequest request);
	
	public JSONObject getNhbsProposalCount(NhbsApplicationRequest nhbsApplicationRequest);
	
}
