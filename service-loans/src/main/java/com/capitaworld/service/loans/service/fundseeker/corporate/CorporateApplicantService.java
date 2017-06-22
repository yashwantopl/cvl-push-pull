package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.common.GraphResponse;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.SubSectorListRequest;

import java.util.List;

import org.json.simple.JSONObject;

public interface CorporateApplicantService {
	public boolean save(CorporateApplicantRequest applicantRequest,Long userId) throws Exception;

	public CorporateApplicantRequest getCorporateApplicant(Long userId, Long applicationId) throws Exception;
	
	public List<Long> getSectorListByIndustryId(List<Long> industryList) throws Exception;
	
	public List<SubSectorListRequest> getSubSectorList(List<Long> list);
	
	public GraphResponse getGraphs(Long applicationId,Long userId);
	
	public int updateLatLong(CorporateApplicantRequest request,Long userId) throws Exception;
	
	public JSONObject getLatLonByApplicationAndUserId(Long applicationId,Long userId) throws Exception;
}
