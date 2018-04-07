package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.PaymentRequest;
import com.capitaworld.service.loans.model.common.GraphResponse;
import com.capitaworld.service.loans.model.common.LongitudeLatitudeRequest;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.CorporateCoApplicantRequest;
import com.capitaworld.service.loans.model.corporate.MsmeScoreRequest;
import com.capitaworld.service.loans.model.corporate.SubSectorListRequest;
import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import java.util.List;

import org.json.simple.JSONObject;

public interface CorporateApplicantService {
	public boolean save(CorporateApplicantRequest applicantRequest,Long userId) throws Exception;

	public CorporateApplicantRequest getCorporateApplicant(Long userId, Long applicationId) throws Exception;
	
	public List<Long> getSectorListByIndustryId(List<Long> industryList) throws Exception;
	
	public List<SubSectorListRequest> getSubSectorList(List<Long> list);
	
	public GraphResponse getGraphs(Long applicationId,Long userId);
	
	public int updateLatLong(LongitudeLatitudeRequest request,Long userId) throws Exception;
	
	public LongitudeLatitudeRequest getLatLonByApplicationAndUserId(Long applicationId,Long userId) throws Exception;
	
	public Integer getCorporateEstablishmentYear(Long applicationId,Long userId) throws Exception;
	
	public List<CorporateCoApplicantRequest> getCoApplicants(Long userId, Long applicationId) throws Exception;
	public boolean updateIsMsmeScoreRequired(MsmeScoreRequest msmeScoreRequest)throws Exception;
	
	//public CompanyDetails getCompanyDetails(Long applicationId,Long userId)throws Exception;
	
	public boolean getIsMsmeScoreRequired(Long applicationId)throws Exception;
	
	public JSONObject getCoapAndGuarIds(Long userId, Long applicationId) throws Exception;
	
	public PaymentRequest getPaymentInfor(Long userId,Long applicationId) throws Exception;
	
	public void saveIndustry(Long applicationId, List<Long> industrylist) ;
	public void saveSector(Long applicationId, List<Long> sectorlist) ;
	public void saveSubSector(Long applicationId, List<Long> subSectorlist) ;
	
	public CorporateApplicantRequest getCorporateApplicant(Long applicationId);
	
	public JSONObject getOrgAndPanByAppId(Long applicationId);
}
