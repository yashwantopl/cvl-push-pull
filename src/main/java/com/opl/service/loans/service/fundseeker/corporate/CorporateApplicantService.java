package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.PaymentRequest;
import com.opl.mudra.api.loans.model.common.GraphResponse;
import com.opl.mudra.api.loans.model.common.LongitudeLatitudeRequest;
import com.opl.mudra.api.loans.model.corporate.CorporateApplicantRequest;
import com.opl.mudra.api.loans.model.corporate.CorporateCoApplicantRequest;
import com.opl.mudra.api.loans.model.corporate.SubSectorListRequest;

public interface CorporateApplicantService {

    public void saveITRMappingData(CorporateApplicantRequest applicantRequest);

    public boolean save(CorporateApplicantRequest applicantRequest, Long userId) throws LoansException;

    public CorporateApplicantRequest getCorporateApplicant(Long userId, Long applicationId) throws LoansException;

    public CorporateApplicantRequest getCorporateApplicantByProposalId(Long userId, Long proposalId) throws Exception;

    public List<Long> getSectorListByIndustryId(List<Long> industryList) throws LoansException;

    public List<SubSectorListRequest> getSubSectorList(List<Long> list);

    public GraphResponse getGraphs(Long applicationId, Long userId);

    public int updateLatLong(LongitudeLatitudeRequest request, Long userId) throws LoansException;

    public LongitudeLatitudeRequest getLatLonByApplicationAndUserId(Long applicationId, Long userId) throws LoansException;

    public Integer getCorporateEstablishmentYear(Long applicationId, Long userId) throws LoansException;

    public Integer getCorporateEstablishmentYearFromProposalId(Long proposalId) throws Exception;

    public List<CorporateCoApplicantRequest> getCoApplicants(Long userId, Long applicationId) throws LoansException;
//	public boolean updateIsMsmeScoreRequired(MsmeScoreRequest msmeScoreRequest)throws Exception;

    //public CompanyDetails getCompanyDetails(Long applicationId,Long userId)throws Exception;

//	public boolean getIsMsmeScoreRequired(Long applicationId)throws Exception;

    public JSONObject getCoapAndGuarIds(Long userId, Long applicationId) throws LoansException;

    public PaymentRequest getPaymentInfor(Long userId, Long applicationId) throws LoansException;

    public void saveIndustry(Long applicationId, List<Long> industrylist);

    public void saveSector(Long applicationId, List<Long> sectorlist);

    public void saveSubSector(Long applicationId, List<Long> subSectorlist);

    public CorporateApplicantRequest getCorporateApplicant(Long applicationId);

    public JSONObject getOrgAndPanByAppId(Long applicationId);

    public Map getOrganizationNameFromApplicationId(Long applicationId);
    
    public boolean saveITRResponse(CorporateApplicantRequest applicantRequest) throws LoansException;
    
    public boolean saveSalesITRResponse(CorporateApplicantRequest applicantRequest) throws LoansException;
    
    public CorporateApplicantRequest getCorporateApplicantDetails(Long applicationId);
    
}
