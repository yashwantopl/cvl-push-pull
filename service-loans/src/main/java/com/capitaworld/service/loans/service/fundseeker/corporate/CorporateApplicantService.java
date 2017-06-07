package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.SubSectorListRequest;

import java.util.List;

public interface CorporateApplicantService {
	public boolean save(CorporateApplicantRequest applicantRequest,Long userId) throws Exception;

	public CorporateApplicantRequest getCorporateApplicant(Long userId, Long applicationId) throws Exception;
	
	public List<Long> getSectorListByIndustryId(List<Long> industryList) throws Exception;
	
	public List<SubSectorListRequest> getSubSectorList(List<Long> list);
	
//	public void updateFinalCommonInformation(Long applicationId, Long userId, Boolean flag) throws Exception;
}
