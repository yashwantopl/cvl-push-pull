package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.PaymentRequest;
import com.capitaworld.service.loans.model.common.GraphResponse;
import com.capitaworld.service.loans.model.common.LongitudeLatitudeRequest;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.CorporateCoApplicantRequest;
import com.capitaworld.service.loans.model.corporate.SubSectorListRequest;
import java.util.List;

import org.json.simple.JSONObject;

public interface CorporateApplicantService {
	
	public void saveITRMappingData (CorporateApplicantRequest applicantRequest);
	
	public boolean save(CorporateApplicantRequest applicantRequest,Long userId) throws LoansException;

	public CorporateApplicantRequest getCorporateApplicant(Long userId, Long applicationId) throws LoansException;

	public CorporateApplicantRequest getCorporateApplicantByProposalId(Long userId, Long proposalId) throws Exception;

	public List<Long> getSectorListByIndustryId(List<Long> industryList) throws LoansException;
	
	public List<SubSectorListRequest> getSubSectorList(List<Long> list);
	
	public GraphResponse getGraphs(Long applicationId,Long userId);
	
	public int updateLatLong(LongitudeLatitudeRequest request,Long userId) throws LoansException;
	
	public LongitudeLatitudeRequest getLatLonByApplicationAndUserId(Long applicationId,Long userId) throws LoansException;
	
	public Integer getCorporateEstablishmentYear(Long applicationId,Long userId) throws LoansException;

	public Integer getCorporateEstablishmentYearFromProposalId(Long proposalId) throws Exception;

	public List<CorporateCoApplicantRequest> getCoApplicants(Long userId, Long applicationId) throws LoansException;
//	public boolean updateIsMsmeScoreRequired(MsmeScoreRequest msmeScoreRequest)throws Exception;
	
	//public CompanyDetails getCompanyDetails(Long applicationId,Long userId)throws Exception;
	
//	public boolean getIsMsmeScoreRequired(Long applicationId)throws Exception;
	
	public JSONObject getCoapAndGuarIds(Long userId, Long applicationId) throws LoansException;
	
	public PaymentRequest getPaymentInfor(Long userId,Long applicationId) throws LoansException;
	
	public void saveIndustry(Long applicationId, List<Long> industrylist) ;
	public void saveSector(Long applicationId, List<Long> sectorlist) ;
	public void saveSubSector(Long applicationId, List<Long> subSectorlist) ;
	
	public CorporateApplicantRequest getCorporateApplicant(Long applicationId);
	
	public JSONObject getOrgAndPanByAppId(Long applicationId);
}
