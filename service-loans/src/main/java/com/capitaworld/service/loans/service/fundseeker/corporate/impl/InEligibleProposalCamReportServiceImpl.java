/**
 * 
 */
package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.cibil.api.model.CibilRequest;
import com.capitaworld.cibil.api.model.CibilScoreLogRequest;
import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.itr.api.model.ITRConnectionResponse;
import com.capitaworld.itr.client.ITRClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.gst.GstCalculation;
import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.gst.yuva.request.GSTR1Request;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponseString;
import com.capitaworld.service.loans.model.DirectorPersonalDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementDetailResponseString;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.CAM.AssetDetailsString;
import com.capitaworld.service.loans.model.CAM.FinancialInputRequestDbl;
import com.capitaworld.service.loans.model.CAM.FinancialInputRequestString;
import com.capitaworld.service.loans.model.CAM.LiabilitiesDetailsString;
import com.capitaworld.service.loans.model.CAM.OperatingStatementDetailsString;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.corporate.PrimaryCorporateRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.service.common.PincodeDateService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.InEligibleProposalCamReportService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PrimaryCorporateService;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.AssessedForITMst;
import com.capitaworld.service.oneform.enums.CompetitionMst_SBI;
import com.capitaworld.service.oneform.enums.Constitution;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.DirectorRelationshipType;
import com.capitaworld.service.oneform.enums.EducationalStatusMst;
import com.capitaworld.service.oneform.enums.EstablishmentMonths;
import com.capitaworld.service.oneform.enums.FactoryPremiseMst;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.HaveLIMst;
import com.capitaworld.service.oneform.enums.Industry;
import com.capitaworld.service.oneform.enums.KnowHowMst;
import com.capitaworld.service.oneform.enums.LCBG_Status_SBI;
import com.capitaworld.service.oneform.enums.MaritalStatusMst;
import com.capitaworld.service.oneform.enums.OwningHouseMst;
import com.capitaworld.service.oneform.enums.PurposeOfLoan;
import com.capitaworld.service.oneform.enums.ResidentStatusMst;
import com.capitaworld.service.oneform.enums.SpouseDetailMst;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.enums.VisuallyImpairedMst;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

/**
 * @author nilay.darji
 *
 */
@Service
@Transactional
public class InEligibleProposalCamReportServiceImpl implements InEligibleProposalCamReportService {
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private MatchEngineClient matchEngineClient; 
	
	@Autowired
	private AnalyzerClient analyzerClient;
	
	@Autowired
	private CorporateApplicantService corporateApplicantService;
	
	@Autowired
	private PrimaryCorporateService primaryCorporateService;
	
	@Autowired
	private CorporateFinalInfoService corporateFinalInfoService;
	
	@Autowired
	private OneFormClient oneFormClient;
	
	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;
	
	@Autowired
	private DirectorBackgroundDetailsService backgroundDetailsService;
	
	@Autowired
	private UsersClient usersClient;
	
	@Autowired
	private CIBILClient cibilClient;
	
	@Autowired
	private GstClient gstClient;
	
	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;
	
	@Autowired
	private OperatingStatementDetailsRepository operatingStatementDetailsRepository;
	
	@Autowired
	private LiabilitiesDetailsRepository liabilitiesDetailsRepository;
	
	@Autowired
	private AssetsDetailsRepository assetsDetailsRepository;
	
	@Autowired
	private WorkflowClient workflowClient;
	
	@Autowired
	private ConnectClient connectClient;
	
	@Autowired
	private ITRClient itrClient;
	
	@Autowired
	private ScoringService scoringService;
	
	@Autowired
	private PincodeDateService pincodeDateService;
	
	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(InEligibleProposalCamReportServiceImpl.class);

	@Override
	public Map<String, Object> getInEligibleCamReport(Long applicationId) {
		

		Map<String, Object> map = new HashMap<String, Object>();
		DecimalFormat decim = new DecimalFormat("####");
		Long userId = loanApplicationRepository.getUserIdByApplicationId(applicationId);
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getByIdAndUserIdForInEligibleCam(applicationId, userId);
		if(loanApplicationMaster!= null) {
		
			map.put("date",!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getApprovedDate())? CommonUtils.DATE_FORMAT.format(loanApplicationMaster.getApprovedDate()):"-");
			
		}
		
		CorporateApplicantRequest corporateApplicantRequest =corporateApplicantService.getCorporateApplicant(applicationId);
		UserResponse userResponse = usersClient.getEmailMobile(userId);
		LinkedHashMap<String, Object> lm = (LinkedHashMap<String, Object>)userResponse.getData();
		try {
			UsersRequest request = MultipleJSONObjectHelper.getObjectFromMap(lm,UsersRequest.class);
			map.put("mobile", request.getMobile());
			map.put("email", StringEscapeUtils.escapeXml(request.getEmail()));
		} catch (IOException e1) {
			logger.error("Error while getting registration details : ",e1);
		}
		CorporateFinalInfoRequest corporateFinalInfoRequest;
		try {
			corporateFinalInfoRequest = corporateFinalInfoService.get(userId, applicationId);
			//ADMIN OFFICE ADDRESS
			if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress())){
				map.put("adminAddPremise", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getPremiseNumber()) ? CommonUtils.printFields(corporateFinalInfoRequest.getSecondAddress().getPremiseNumber(),null) + "," : "");
				map.put("adminAddStreetName", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getStreetName()) ? CommonUtils.printFields(corporateFinalInfoRequest.getSecondAddress().getStreetName(),null) + " " : "");
				map.put("adminAddLandmark", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getLandMark()) ? CommonUtils.printFields(corporateFinalInfoRequest.getSecondAddress().getLandMark(),null) + " " : "");
				map.put("adminAddCountry", StringEscapeUtils.escapeXml(getCountryName(corporateFinalInfoRequest.getSecondAddress().getCountryId())));
				map.put("adminAddState", StringEscapeUtils.escapeXml(getStateName(corporateFinalInfoRequest.getSecondAddress().getStateId())));
				map.put("adminAddCity", StringEscapeUtils.escapeXml(getCityName(corporateFinalInfoRequest.getSecondAddress().getCityId())));
				map.put("adminAddPincode", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getPincode())?corporateFinalInfoRequest.getSecondAddress().getPincode() : "");
				try {
					if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId())) {
						map.put("adminAddressData",CommonUtils.printFields(pincodeDateService.getById(corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId()),null));				
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			//REGISTERED OFFICE ADDRESS
			if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress())) {
				map.put("registeredAddPremise", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getPremiseNumber()) ? CommonUtils.printFields(corporateFinalInfoRequest.getFirstAddress().getPremiseNumber(),null) + "," : "");
				map.put("registeredAddStreetName", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getStreetName()) ? CommonUtils.printFields(corporateFinalInfoRequest.getFirstAddress().getStreetName(),null) + " " : "");
				map.put("registeredAddLandmark", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getLandMark()) ? CommonUtils.printFields(corporateFinalInfoRequest.getFirstAddress().getLandMark(),null) + " " : "");
				map.put("registeredAddCountry", StringEscapeUtils.escapeXml(getCountryName(corporateFinalInfoRequest.getFirstAddress().getCountryId())));
				map.put("registeredAddState", StringEscapeUtils.escapeXml(getStateName(corporateFinalInfoRequest.getFirstAddress().getStateId())));
				map.put("registeredAddCity", StringEscapeUtils.escapeXml(getCityName(corporateFinalInfoRequest.getFirstAddress().getCityId())));
				map.put("registeredAddPincode", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getPincode())?corporateFinalInfoRequest.getFirstAddress().getPincode() : "");
				try {
					if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getDistrictMappingId())) {
						map.put("registeredAddressData",CommonUtils.printFields(pincodeDateService.getById(corporateFinalInfoRequest.getFirstAddress().getDistrictMappingId()),null));				
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			map.put("corporateApplicantFinal",corporateFinalInfoRequest);
			map.put("aboutUs", StringEscapeUtils.escapeXml(corporateFinalInfoRequest.getAboutUs()));
		} catch (Exception e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
		}
		
		//TIMELINE DATES
		if(loanApplicationMaster != null && loanApplicationMaster.getCreatedDate() != null)
		{
			map.put("dateOfProposal", !CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getCreatedDate())? CommonUtils.DATE_FORMAT.format(loanApplicationMaster.getCreatedDate()):"-");
			
		}
		
		try {
			WorkflowRequest workflowRequest = new WorkflowRequest();
			workflowRequest.setApplicationId(applicationId);
			workflowRequest.setWorkflowId(WorkflowUtils.Workflow.DDR);
			workflowRequest.setUserId(userId);
			WorkflowResponse auditTrail = workflowClient.getAuditTrail(workflowRequest);
			if(!CommonUtils.isObjectNullOrEmpty(auditTrail)) {
				map.put("trailDates", auditTrail.getData());
			}
		} catch (Exception e2) {
			logger.error(CommonUtils.EXCEPTION,e2);
		}
		/*try {
			ConnectResponse connectResponse = connectClient.getByAppStageBusinessTypeId(applicationId, ConnectStage.COMPLETE.getId(), com.capitaworld.service.loans.utils.CommonUtils.BusinessType.EXISTING_BUSINESS.getId());
			if(!CommonUtils.isObjectNullOrEmpty(connectResponse)) {
				map.put("dateOfInPrincipalApproval",!CommonUtils.isObjectNullOrEmpty(connectResponse.getData())? DATE_FORMAT.format(connectResponse.getData()):"-");
			}
		} catch (Exception e2) {
			logger.error(CommonUtils.EXCEPTION,e2);
		}*/
		
		//GST DATA
		try {
			GSTR1Request gstr1Request = new GSTR1Request();
			gstr1Request.setGstin(corporateApplicantRequest.getGstIn());
			GstResponse response = gstClient.getCalculations(gstr1Request);
			GstCalculation gstData = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)response.getData(),GstCalculation.class);
			int noOfCustomer = gstData.getNoOfCustomer().intValue();
			map.put("noOfCustomer", noOfCustomer);
			map.put("projectedSales", CommonUtils.convertValueRound(gstData.getProjectedSales()));
			map.put("customerConcentration", CommonUtils.convertValue(gstData.getConcentration()));
		}catch(Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}try {
			GstResponse response = gstClient.detailCalculation(corporateApplicantRequest.getGstIn());
			if(!CommonUtils.isObjectNullOrEmpty(response)) {
				map.put("gstDetailedResp",response.getData());
			}
		}catch(Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository.getByApplicationAndUserId(applicationId, userId);
		if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)) {
			map.put("comercialOpDate",!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCommercialOperationDate())? CommonUtils.DATE_FORMAT.format(primaryCorporateDetail.getCommercialOperationDate()):"-");
			map.put("factoryPremise", !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getFactoryPremise())? StringEscapeUtils.escapeXml(FactoryPremiseMst.getById(primaryCorporateDetail.getFactoryPremise()).getValue().toString()) : "-");
			map.put("knowHow", !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getKnowHow())? StringEscapeUtils.escapeXml(KnowHowMst.getById(primaryCorporateDetail.getKnowHow()).getValue().toString()) : "-");
			map.put("competition", !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCompetition())? StringEscapeUtils.escapeXml(CompetitionMst_SBI.getById(primaryCorporateDetail.getCompetition()).getValue().toString()) : "-");
		}
		
		//ONE-FORM DATA
		try {
			//ONE-FORM DATA
    		map.put("corporateApplicant", corporateApplicantRequest);
			map.put("orgName", CommonUtils.printFields(corporateApplicantRequest.getOrganisationName(),null));
			map.put("constitution", !CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getConstitutionId()) ? StringEscapeUtils.escapeXml(Constitution.getById(corporateApplicantRequest.getConstitutionId()).getValue()) : " ");
			
			String establishMentYear = !CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getEstablishmentMonth()) ? EstablishmentMonths.getById(corporateApplicantRequest.getEstablishmentMonth()).getValue() : "";
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getEstablishmentYear())) {
				try {
					OneFormResponse establishmentYearResponse = oneFormClient.getYearByYearId(CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getEstablishmentYear()) ? null : corporateApplicantRequest.getEstablishmentYear().longValue());
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) establishmentYearResponse.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						establishMentYear += " "+ masterResponse.getValue();
					} 
				} catch (Exception e) {
					logger.error("Error in getting establishment year : ",e);
				}
			}
			map.put("establishmentYr",!CommonUtils.isObjectNullOrEmpty(establishMentYear) ? CommonUtils.printFields(establishMentYear,null) : " ");
			//INDUSTRY DATA
			Integer industry = corporateApplicantRequest.getKeyVericalFunding().intValue();
			map.put("keyVerticalFunding", !CommonUtils.isObjectNullOrEmpty(industry) ? CommonUtils.printFields(Industry.getById(industry).getValue(),null) : " ");
		}catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
			
			//DIRECTOR'S BACKGROUND
			try {
				List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = backgroundDetailsService.getDirectorBackgroundDetailList(applicationId, userId);
				List<DirectorBackgroundDetailResponseString> directorBackgroundDetailResponseList = new ArrayList<>();
				for (DirectorBackgroundDetailRequest directorBackgroundDetailRequest : directorBackgroundDetailRequestList) {
					DirectorBackgroundDetailResponseString directorBackgroundDetailResponse = new DirectorBackgroundDetailResponseString();
					//directorBackgroundDetailResponse.setAchivements(directorBackgroundDetailRequest.getAchivements());
					directorBackgroundDetailResponse.setAddress(directorBackgroundDetailRequest.getAddress());
					//directorBackgroundDetailResponse.setAge(directorBackgroundDetailRequest.getAge());
					directorBackgroundDetailResponse.setPanNo(directorBackgroundDetailRequest.getPanNo());
					directorBackgroundDetailResponse.setDirectorsName((directorBackgroundDetailRequest.getSalutationId() != null ? Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue() : null )+ " " + directorBackgroundDetailRequest.getDirectorsName());
					directorBackgroundDetailResponse.setPanNo(directorBackgroundDetailRequest.getPanNo().toUpperCase());
					String directorName = "";
					if (directorBackgroundDetailRequest.getSalutationId() != null){
						directorName = Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue();
					}
					directorName += " "+directorBackgroundDetailRequest.getDirectorsName();
					directorBackgroundDetailResponse.setDirectorsName(directorName);
					//directorBackgroundDetailResponse.setQualification(directorBackgroundDetailRequest.getQualification());
					directorBackgroundDetailResponse.setTotalExperience(CommonUtils.convertValueWithoutDecimal(directorBackgroundDetailRequest.getTotalExperience()));
					directorBackgroundDetailResponse.setNetworth(CommonUtils.convertValueRound(directorBackgroundDetailRequest.getNetworth()));
					directorBackgroundDetailResponse.setDesignation(directorBackgroundDetailRequest.getDesignation());
					directorBackgroundDetailResponse.setAppointmentDate(directorBackgroundDetailRequest.getAppointmentDate());
					directorBackgroundDetailResponse.setDin(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDin())?decim.format(directorBackgroundDetailRequest.getDin()).toString() : "");
					directorBackgroundDetailResponse.setMobile(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getMobile())?directorBackgroundDetailRequest.getMobile(): " ");
					directorBackgroundDetailResponse.setDob(directorBackgroundDetailRequest.getDob());
					if(directorBackgroundDetailRequest.getPanNo().charAt(3) == 'H' ||directorBackgroundDetailRequest.getPanNo().charAt(3) == 'h') {
						directorBackgroundDetailResponse.setCibilScore("HUF");
					}else {
						try {
							CibilRequest cibilRequest = new CibilRequest();
							cibilRequest.setPan(directorBackgroundDetailRequest.getPanNo());
							
							CibilScoreLogRequest cibilScoreByPanCard = cibilClient.getCibilScoreByPanCard(cibilRequest);
							if(!CommonUtils.isObjectNullOrEmpty(cibilScoreByPanCard)) {
								if("000-1".equalsIgnoreCase(cibilScoreByPanCard.getActualScore())) {
									directorBackgroundDetailResponse.setCibilScore("-1");
								}else {
									directorBackgroundDetailResponse.setCibilScore(Integer.valueOf(cibilScoreByPanCard.getActualScore()).toString());								
								}								
							}else {
								directorBackgroundDetailResponse.setCibilScore("-");
							}
							
						}catch(Exception e) {
							logger.error("Error while getting cibil details : ",e);
						}
					}
					directorBackgroundDetailResponse.setPincode(directorBackgroundDetailRequest.getPincode());
					directorBackgroundDetailResponse.setPersonalId(directorBackgroundDetailRequest.getPersonalId());
					try {
						if (!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDistrictMappingId())) {
							directorBackgroundDetailResponse.setPinData(pincodeDateService.getById(directorBackgroundDetailRequest.getDistrictMappingId()));
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
					directorBackgroundDetailResponse.setStateCode(directorBackgroundDetailRequest.getStateCode());
					directorBackgroundDetailResponse.setCity(directorBackgroundDetailRequest.getCity());
					directorBackgroundDetailResponse.setGender((!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getGender()) ? Gender.getById(directorBackgroundDetailRequest.getGender()).getValue() : " "));
					directorBackgroundDetailResponse.setRelationshipType((!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getRelationshipType())  ? StringEscapeUtils.escapeXml(DirectorRelationshipType.getById(directorBackgroundDetailRequest.getRelationshipType()).getValue()) : " "));
					directorBackgroundDetailResponse.setIsMainDirector(directorBackgroundDetailRequest.getIsMainDirector());
					directorBackgroundDetailResponse.setAadhar(directorBackgroundDetailRequest.getAadhar());
					directorBackgroundDetailResponse.setFatherName(directorBackgroundDetailRequest.getFatherName());
					directorBackgroundDetailResponse.setEducationalStatus(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getEducationalStatus()) ? StringEscapeUtils.escapeXml(EducationalStatusMst.getById(directorBackgroundDetailRequest.getEducationalStatus()).getValue().toString()) : "-");
					directorBackgroundDetailResponse.setVisuallyImpaired(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getVisuallyImpaired()) ? StringEscapeUtils.escapeXml(VisuallyImpairedMst.getById(directorBackgroundDetailRequest.getVisuallyImpaired()).getValue().toString()) : "-");
					directorBackgroundDetailResponse.setResidentStatus(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getResidentStatus()) ? StringEscapeUtils.escapeXml(ResidentStatusMst.getById(directorBackgroundDetailRequest.getResidentStatus()).getValue().toString()) : "-");
					directorBackgroundDetailResponse.setDirectorPersonalInfo(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest()) ? directorBackgroundDetailRequest.getDirectorPersonalDetailRequest() : " " );
					//NATIONALITY
					List<Long> countryList = new ArrayList<>();
					if (!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getNationality()))
						countryList.add(Long.valueOf(directorBackgroundDetailRequest.getNationality()));
					if (!CommonUtils.isListNullOrEmpty(countryList)) {
						try {
							OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
							List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
							if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
								MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
								map.put("dirCountry", StringEscapeUtils.escapeXml(masterResponse.getValue()));
								map.put("dirRegOfficeCountry", StringEscapeUtils.escapeXml(masterResponse.getValue()));
								directorBackgroundDetailResponse.setNationality(masterResponse.getValue());
							} else {
								directorBackgroundDetailResponse.setNationality("NA");
							}
						} catch (Exception e) {
							logger.error(CommonUtils.EXCEPTION,e);
						}
					}
					try {
						if(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getIsMainDirector()) && directorBackgroundDetailRequest.getIsMainDirector() == true) {
							DirectorPersonalDetailResponse directorPersonalDetailResponse= new DirectorPersonalDetailResponse();
							if(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest())) {
								
								directorPersonalDetailResponse.setMaritalStatus(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getMaritalStatus()) ? StringEscapeUtils.escapeXml(MaritalStatusMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getMaritalStatus()).getValue().toString()) : "-");
								directorPersonalDetailResponse.setSpouseName(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getSpouseName()) ? StringEscapeUtils.escapeXml(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getSpouseName()) : "-" );
								directorPersonalDetailResponse.setSpouseDetail(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getSpouseDetail()) ? StringEscapeUtils.escapeXml(SpouseDetailMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getSpouseDetail()).getValue().toString()) : "-");
								directorPersonalDetailResponse.setAssessedForIt(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getAssessedForIt()) ? StringEscapeUtils.escapeXml(AssessedForITMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getAssessedForIt()).getValue().toString()) : "-");
								directorPersonalDetailResponse.setOwningHouse(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getOwningHouse()) ? StringEscapeUtils.escapeXml(OwningHouseMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getOwningHouse()).getValue().toString()) : "-");
								directorPersonalDetailResponse.setNoOfChildren(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getNoOfChildren()) ? directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getNoOfChildren() : null );
								directorPersonalDetailResponse.setHaveLiPolicy(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getHaveLiPolicy()) ? StringEscapeUtils.escapeXml(HaveLIMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getHaveLiPolicy()).getValue().toString()) : "-");
								
								directorBackgroundDetailResponse.setDirectorPersonalInfo(directorPersonalDetailResponse);
							}
						}
					}catch(Exception e) {
						logger.error("error while getting main directors details : ",e);
					}
					directorBackgroundDetailResponseList.add(directorBackgroundDetailResponse);
				}
				map.put("dirBackground", !CommonUtils.isListNullOrEmpty(directorBackgroundDetailResponseList) ? CommonUtils.printFields(directorBackgroundDetailResponseList,null) : " ");
	        }
				catch (Exception e) {
					logger.error("Error in getting directors background details : ",e);
		    }
			
			//MATCHES RESPONSE
			try {
				MatchRequest matchRequest = new MatchRequest();
				matchRequest.setApplicationId(applicationId);
			/*	matchRequest.setProductId(productId);*/
				MatchDisplayResponse matchResponse= matchEngineClient.displayMatchesOfCorporate(matchRequest);
				map.put("matchesResponse", !CommonUtils.isListNullOrEmpty(matchResponse.getMatchDisplayObjectList()) ? CommonUtils.printFields(matchResponse.getMatchDisplayObjectList(),null) : " ");
			}
			catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			
		    //FINANCIAL ARRANGEMENTS
			try {
                List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService.getFinancialArrangementDetailsList(applicationId, userId);
                List<FinancialArrangementDetailResponseString> financialArrangementsDetailResponseList = new ArrayList<>();
                for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
                	FinancialArrangementDetailResponseString financialArrangementsDetailResponse = new FinancialArrangementDetailResponseString();
     				//financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
                    financialArrangementsDetailResponse.setOutstandingAmount(CommonUtils.convertValue(financialArrangementsDetailRequest.getOutstandingAmount()));
                    financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
                    financialArrangementsDetailResponse.setAmount(CommonUtils.convertValue(financialArrangementsDetailRequest.getAmount()));
                    //			financialArrangementsDetailResponse.setLenderType(LenderType.getById(financialArrangementsDetailRequest.getLenderType()).getValue());
                    financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
                    financialArrangementsDetailResponse.setLoanType(financialArrangementsDetailRequest.getLoanType());
                    financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
                    //			financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
                    //financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
                    financialArrangementsDetailResponse.setLcbgStatus(!CommonUtils.isObjectNullOrEmpty(financialArrangementsDetailRequest.getLcBgStatus()) ? LCBG_Status_SBI.getById(financialArrangementsDetailRequest.getLcBgStatus()).getValue().toString() : "-");
                    financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
                }
                	map.put("financialArrangments",!CommonUtils.isListNullOrEmpty(financialArrangementsDetailResponseList) ? CommonUtils.printFields(financialArrangementsDetailResponseList,null) : " ");

            } catch (Exception e) {
                logger.error("Problem to get Data of Financial Arrangements Details {}", e);
            }
			
		try {
			PrimaryCorporateRequest primaryCorporateRequest = primaryCorporateService.get(applicationId, userId);
			map.put("loanAmt", !CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getLoanAmount()) ? CommonUtils.convertValueWithoutDecimal(primaryCorporateRequest.getAmount()) : " ");
			map.put("loanType", !CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getProductId()) ? CommonUtils.LoanType.getType(primaryCorporateRequest.getProductId()).getName() : " ");
			map.put("promotorsContribution", CommonUtils.convertValueRound(primaryCorporateRequest.getPromoterContribution()));
			map.put("totalAmtPer", !CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getTotalAmtPercentage()) ? " ("+CommonUtils.convertValue(primaryCorporateRequest.getTotalAmtPercentage())+"%)" : null);
			if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getPurposeOfLoanId())) {
				map.put("purpose", StringEscapeUtils.escapeXml(PurposeOfLoan.getById(primaryCorporateRequest.getPurposeOfLoanId()).getValue()));
			}else {
				map.put("purpose", "");
			}
			
			
			if(primaryCorporateRequest.getHaveCollateralSecurity()) {
				map.put("amtOfSecurity",!CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getCollateralSecurityAmount()) ? CommonUtils.convertValue(primaryCorporateRequest.getCollateralSecurityAmount()) : " ");
			}
		}catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		//FINANCIALS AND NOTES TO ACCOUNTS
		try {
			PrimaryCorporateRequest primaryCorporateRequest = primaryCorporateService.get(applicationId, userId);
			int currentYear = scoringService.getFinYear(applicationId);
			map.put("currentYr",currentYear-1);
			if(loanApplicationMaster!=null && loanApplicationMaster.getDenominationId()!= null) {
				Long denominationValue = Denomination.getById(loanApplicationMaster.getDenominationId()).getDigit();
				Integer[] years = {currentYear-3, currentYear-2, currentYear-1};
				Map<Integer, Object[]> financials = new TreeMap<Integer, Object[]>(Collections.reverseOrder());
				for(Integer year : years) {
					Object[] data = calculateFinancials(userId, applicationId, null, denominationValue, year);
					financials.put(year, data);
				}
				calculateRatioAnalysis(financials, applicationId);
				map.put("financials", financials);
				if(loanApplicationMaster.getTenure()!= null) {
					Map<Integer, Object[]> projectedFin = new HashMap<Integer, Object[]>(loanApplicationMaster.getTenure().intValue());	
					if(primaryCorporateRequest.getProductId() == 1) {
						projectedFin.put(currentYear , calculateFinancials(userId, applicationId, null, denominationValue, currentYear));
						map.put("tenure", 1);
					}else {
						for(int i=0; i<=loanApplicationMaster.getTenure().intValue();i++) {
							projectedFin.put(currentYear + i, calculateFinancials(userId, applicationId, null, denominationValue, currentYear + i));
						}
						map.put("tenure", loanApplicationMaster.getTenure().intValue() +1);
					}
					map.put("projectedFinancials", projectedFin);
					
				}
			}
			
			
			
		}catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		
		
		//NAME AS PER ITR
		try{
			ITRConnectionResponse itrResponse = itrClient.getITRBasicDetails(applicationId);
			logger.info("ITR RESPONSE===========>"+itrResponse);
			map.put("nameAsPerItr", CommonUtils.printFields(itrResponse.getData(),null));
		}catch(Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		
		
		
		
		//PERFIOS API DATA (BANK STATEMENT ANALYSIS)
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(applicationId);
		reportRequest.setUserId(userId);
		
		List<Data> datas = new ArrayList<>();
		List<Object> bankStatement = new ArrayList<Object>();
		List<Object> monthlyDetails = new ArrayList<Object>();
		List<Object> top5FundReceived = new ArrayList<Object>();
		List<Object> top5FundTransfered = new ArrayList<Object>();
		List<Object> bouncedChequeList = new ArrayList<Object>();
		List<Object> customerInfo = new ArrayList<Object>();
		List<Object> summaryInfo = new ArrayList<Object>();
		
		try {
			AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
			List<HashMap<String, Object>> hashMap = (List<HashMap<String, Object>>) analyzerResponse.getData();
			
			if (!CommonUtils.isListNullOrEmpty(hashMap)) {
				for (HashMap<String, Object> rec : hashMap) {
					Data data = MultipleJSONObjectHelper.getObjectFromMap(rec, Data.class);
					datas.add(data);
					
					//bankStatement.add(!CommonUtils.isObjectNullOrEmpty(data.getXns()) ? CommonUtils.printFields(data.getXns().getXn(),null) : " ");
					monthlyDetails.add(!CommonUtils.isObjectNullOrEmpty(data.getMonthlyDetailList()) ? CommonUtils.printFields(data.getMonthlyDetailList(),null) : "");
					top5FundReceived.add(!CommonUtils.isObjectNullOrEmpty(data.getTop5FundReceivedList().getItem()) ? CommonUtils.printFields(data.getTop5FundReceivedList().getItem(),null) : "");
					top5FundTransfered.add(!CommonUtils.isObjectNullOrEmpty(data.getTop5FundTransferedList().getItem()) ? CommonUtils.printFields(data.getTop5FundTransferedList().getItem(),null) : "");
					bouncedChequeList.add(!CommonUtils.isObjectNullOrEmpty(data.getBouncedOrPenalXnList()) ? CommonUtils.printFields(data.getBouncedOrPenalXnList().getBouncedOrPenalXns(),null) : " ");
					customerInfo.add(!CommonUtils.isObjectNullOrEmpty(data.getCustomerInfo()) ? CommonUtils.printFields(data.getCustomerInfo(),null) : " ");
					summaryInfo.add(!CommonUtils.isObjectNullOrEmpty(data.getSummaryInfo()) ?CommonUtils.printFields(data.getSummaryInfo(),null) : " ");
					
				}
				

				//logger.info("bankStatement : "+bankStatement.size()+" monthlyDetails :"+monthlyDetails.size()+" top5FundReceived :"+top5FundReceived.size());
				//logger.info("top5FundTransfered : "+top5FundTransfered.size()+" bouncedChequeList :"+bouncedChequeList.size()+" customerInfo :"+customerInfo.size());
				//logger.info("summaryInfo : "+summaryInfo.size()+" bankStatementAnalysis :"+datas.size());

				
				//map.put("bankStatement", bankStatement);
				map.put("monthlyDetails", monthlyDetails);
				map.put("top5FundReceived", top5FundReceived);
				map.put("top5FundTransfered", top5FundTransfered);
				map.put("bouncedChequeList", bouncedChequeList);
				map.put("customerInfo", customerInfo);
				map.put("summaryInfo", summaryInfo);
				map.put("bankStatementAnalysis", CommonUtils.printFields(datas, null));
				
			}
		} catch (Exception e) {
			logger.error("Error while getting perfios data : ",e);
		}

		
		
		return map;
	
	}
	
	@SuppressWarnings("unchecked")
	private String getCityName(Long cityId) {
		try {
			if(CommonUtils.isObjectNullOrEmpty(cityId)) {
				return null;
			}
			List<Long> cityList = new ArrayList<>(1);
			cityList.add(cityId);
			OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
					.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
				MasterResponse masterResponse = MultipleJSONObjectHelper
						.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
				return masterResponse.getValue();
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	private String getStateName(Integer stateId) {
		try {
			if(CommonUtils.isObjectNullOrEmpty(stateId)) {
				return null;
			}
			List<Long> stateList = new ArrayList<>(1);
			stateList.add(stateId.longValue());
			OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
					.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
				MasterResponse masterResponse = MultipleJSONObjectHelper
						.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
				return masterResponse.getValue();
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	private String getCountryName(Integer country) {
		try {
			if(CommonUtils.isObjectNullOrEmpty(country)) {
				return null;
			}
			List<Long> countryList = new ArrayList<>(1);
			countryList.add(country.longValue());
			OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
					.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
				MasterResponse masterResponse = MultipleJSONObjectHelper
						.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
				return masterResponse.getValue();
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return null;
	}

	//FINANCIALS & NOTES TO ACCOUNTS
		public Object[] calculateFinancials(Long userId, Long applicationId, String industry, Long denomination, Integer year) throws Exception {
			FinancialInputRequestDbl financialInputRequestDbl = new FinancialInputRequestDbl();
			FinancialInputRequestString financialInputRequestString = new FinancialInputRequestString();
			OperatingStatementDetailsString osDetailsString = new OperatingStatementDetailsString();
			LiabilitiesDetailsString liabilitiesDetailsString = new LiabilitiesDetailsString();
			AssetDetailsString assetDetailsString = new AssetDetailsString();
			CorporateFinalInfoRequest  corporateFinalInfoRequest = corporateFinalInfoService.get(userId ,applicationId);
	        //SET SHARE FACE VALUE
			Double shareFaceVal=1.00;
			CorporateApplicantDetail corporateApplicantDetail=corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
				if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getSharePriceFace())) {
					shareFaceVal=corporateApplicantDetail.getSharePriceFace();
					financialInputRequestDbl.setShareFaceValue(shareFaceVal);
				}else{
					financialInputRequestDbl.setShareFaceValue(1.00);
				}
			} else{
				financialInputRequestDbl.setShareFaceValue(1.00);
			}

			financialInputRequestDbl.setNoOfMonth(12.0);
			
			/************************************************** OPERATING STATEMENT ***************************************************/
			OperatingStatementDetails osDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, year+"");
			if(CommonUtils.isObjectNullOrEmpty(osDetails)) {
				osDetails = new OperatingStatementDetails();
			}
			
			osDetailsString.setDomesticSales(CommonUtils.convertValueRound(osDetails.getDomesticSales()));
			osDetailsString.setExportSales(CommonUtils.convertValueRound(osDetails.getExportSales()));
			osDetailsString.setGrossSalesTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(osDetails.getDomesticSales(),osDetails.getExportSales())));
			financialInputRequestDbl.setGrossSales((osDetails.getDomesticSales()+osDetails.getExportSales()) * denomination);
			financialInputRequestString.setGrossSales(CommonUtils.convertValueRound(financialInputRequestDbl.getGrossSales()));
			
			osDetailsString.setLessExciseDuty(CommonUtils.convertValueRound(osDetails.getLessExciseDuty()));
			osDetailsString.setDeductOtherItems(CommonUtils.convertValueRound(osDetails.getDeductOtherItems()));
			osDetailsString.setExciseDutyTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(osDetails.getLessExciseDuty(),osDetails.getDeductOtherItems())));
			financialInputRequestDbl.setLessExciseDuity((osDetails.getLessExciseDuty()+osDetails.getDeductOtherItems()) * denomination);
			financialInputRequestString.setLessExciseDuity(CommonUtils.convertValueRound(financialInputRequestDbl.getLessExciseDuity()));
			
			osDetailsString.setAddOperatingStock(CommonUtils.convertValueRound(osDetails.getAddOperatingStock()));
			osDetailsString.setDeductStockInProcess(CommonUtils.convertValueRound(osDetails.getDeductStockInProcess()));
			osDetailsString.setAddOperatingStockFg(CommonUtils.convertValueRound(osDetails.getAddOperatingStockFg()));
			osDetailsString.setDeductClStockFg(CommonUtils.convertValueRound(osDetails.getDeductClStockFg()));
			osDetailsString.setIncreaseDecreaseTotal(CommonUtils.convertValueRound((osDetails.getAddOperatingStock()-osDetails.getDeductStockInProcess()) + (osDetails.getAddOperatingStockFg()-osDetails.getDeductClStockFg()) * denomination));
			financialInputRequestDbl.setIncreaseDecreaseStock(((osDetails.getAddOperatingStock()-osDetails.getDeductStockInProcess()) + (osDetails.getAddOperatingStockFg()-osDetails.getDeductClStockFg())) * denomination);
			financialInputRequestString.setIncreaseDecreaseStock(CommonUtils.convertValueRound(financialInputRequestDbl.getIncreaseDecreaseStock()));
			
			osDetailsString.setRawMaterials(CommonUtils.convertValueRound(osDetails.getRawMaterials()));
			osDetailsString.setOtherSpares(CommonUtils.convertValueRound(osDetails.getOtherSpares()));
			osDetailsString.setRawMaterialsConsumedTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(osDetails.getRawMaterials(), osDetails.getOtherSpares())));
			financialInputRequestDbl.setRawMaterialConsumed((osDetails.getRawMaterials()+osDetails.getOtherSpares()) * denomination);
			financialInputRequestString.setRawMaterialConsumed(CommonUtils.convertValueRound(financialInputRequestDbl.getRawMaterialConsumed()));
			financialInputRequestDbl.setPowerAndFuelCost(osDetails.getPowerAndFuel()  * denomination);
			financialInputRequestString.setPowerAndFuelCost(CommonUtils.convertValueRound(financialInputRequestDbl.getPowerAndFuelCost()));
			financialInputRequestDbl.setEmployeeCost(osDetails.getDirectLabour() * denomination);
			financialInputRequestString.setEmployeeCost(CommonUtils.convertValueRound(financialInputRequestDbl.getEmployeeCost()));
			financialInputRequestDbl.setGeneralAndAdminExpe(osDetails.getSellingGenlAdmnExpenses() * denomination);
			financialInputRequestString.setGeneralAndAdminExpe(CommonUtils.convertValueRound(financialInputRequestDbl.getGeneralAndAdminExpe()));
			financialInputRequestDbl.setSellingAndDistriExpe(osDetails.getSellingAndDistributionExpenses() * denomination);
			financialInputRequestString.setSellingAndDistriExpe(CommonUtils.convertValueRound(financialInputRequestDbl.getSellingAndDistriExpe()));
			financialInputRequestDbl.setLessExpeCapita(osDetails.getExpensesAmortised() * denomination);
			financialInputRequestString.setLessExpeCapita(CommonUtils.convertValueRound(financialInputRequestDbl.getLessExpeCapita()));
			financialInputRequestDbl.setMiscelExpe(osDetails.getOtherMfgExpenses() * denomination);
			financialInputRequestString.setMiscelExpe(CommonUtils.convertValueRound(financialInputRequestDbl.getMiscelExpe()));
			financialInputRequestDbl.setOtherIncome(osDetails.getAddOtherRevenueIncome() * denomination);
			financialInputRequestString.setOtherIncome(CommonUtils.convertValueRound(financialInputRequestDbl.getOtherIncome()));
			financialInputRequestDbl.setInterest(osDetails.getInterest() * denomination);
			financialInputRequestString.setInterest(CommonUtils.convertValueRound(financialInputRequestDbl.getInterest()));
			financialInputRequestDbl.setDepriciation(osDetails.getDepreciation() * denomination);
			financialInputRequestString.setDepriciation(CommonUtils.convertValueRound(financialInputRequestDbl.getDepriciation()));
			financialInputRequestDbl.setExceptionalIncome(osDetails.getNetofNonOpIncomeOrExpenses() * denomination);
			financialInputRequestString.setExceptionalIncome(CommonUtils.convertValueRound(financialInputRequestDbl.getExceptionalIncome()));
			
			osDetailsString.setOtherIncomeNeedTocCheckOp(CommonUtils.convertValueRound(osDetails.getOtherIncomeNeedTocCheckOp()));
			financialInputRequestDbl.setOtherIncomeNeedTocCheckOp(osDetails.getOtherIncomeNeedTocCheckOp() * denomination);
			financialInputRequestString.setOtherIncomeNeedTocCheckOp(CommonUtils.convertValueRound(financialInputRequestDbl.getOtherIncomeNeedTocCheckOp()));
			
			osDetailsString.setProvisionForTaxes(CommonUtils.convertValueRound(osDetails.getProvisionForTaxes()));
			osDetailsString.setProvisionForDeferredTax(CommonUtils.convertValueRound(osDetails.getProvisionForDeferredTax()));
			osDetailsString.setProvisionForTaxTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(osDetails.getProvisionForDeferredTax(), osDetails.getProvisionForTaxes())));
			financialInputRequestDbl.setProvisionForTax((osDetails.getProvisionForTaxes() + osDetails.getProvisionForDeferredTax()) * denomination);
			financialInputRequestString.setProvisionForTax(CommonUtils.convertValueRound(financialInputRequestDbl.getProvisionForTax()));
			financialInputRequestDbl.setDividendPayOut(osDetails.getEquityDeividendPaidAmt() * denomination);
			financialInputRequestString.setDividendPayOut(CommonUtils.convertValueRound(financialInputRequestDbl.getDividendPayOut()));

			/************************************************ LIABILITIES DETAIL ***************************************************/
			LiabilitiesDetails liabilitiesDetails = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, year+"");
			if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)) {
				liabilitiesDetails = new LiabilitiesDetails();
			}
			liabilitiesDetailsString.setOrdinarySharesCapital(CommonUtils.convertValueRound(liabilitiesDetails.getOrdinarySharesCapital()));
			liabilitiesDetailsString.setPreferencesShares(CommonUtils.convertValueRound(liabilitiesDetails.getPreferencesShares()));
			liabilitiesDetailsString.setShareCapitalTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(liabilitiesDetails.getOrdinarySharesCapital(), liabilitiesDetails.getPreferencesShares())));
			financialInputRequestDbl.setShareCapital((liabilitiesDetails.getPreferencesShares() + liabilitiesDetails.getOrdinarySharesCapital()) * denomination);
			financialInputRequestString.setShareCapital(CommonUtils.convertValueRound(financialInputRequestDbl.getShareCapital()));
			financialInputRequestDbl.setShareWarrantOutstandings((liabilitiesDetails.getShareWarrentsOutstanding()) * denomination);
			financialInputRequestString.setShareWarrantOutstandings(CommonUtils.convertValueRound(financialInputRequestDbl.getShareWarrantOutstandings()));
			financialInputRequestDbl.setRevalationReserve((liabilitiesDetails.getRevaluationReservse()) * denomination);
			financialInputRequestString.setRevalationReserve(CommonUtils.convertValueRound(financialInputRequestDbl.getRevalationReserve()));
			
			liabilitiesDetailsString.setGeneralReserve(CommonUtils.convertValueRound(liabilitiesDetails.getGeneralReserve()));
			liabilitiesDetailsString.setOtherReservse(CommonUtils.convertValueRound(liabilitiesDetails.getOtherReservse()));
			liabilitiesDetailsString.setSurplusOrDeficit(CommonUtils.convertValueRound(liabilitiesDetails.getSurplusOrDeficit()));
			liabilitiesDetailsString.setOthers(CommonUtils.convertValueRound(liabilitiesDetails.getOthers()));
			liabilitiesDetailsString.setOtherReservesTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(liabilitiesDetails.getGeneralReserve(),liabilitiesDetails.getOtherReservse(), liabilitiesDetails.getSurplusOrDeficit(),liabilitiesDetails.getOthers())));
			financialInputRequestDbl.setOtherReserveAndSurplus((liabilitiesDetails.getGeneralReserve() + liabilitiesDetails.getOtherReservse() + liabilitiesDetails.getSurplusOrDeficit() + liabilitiesDetails.getOthers()) * denomination);
			financialInputRequestString.setOtherReserveAndSurplus(CommonUtils.convertValueRound(financialInputRequestDbl.getOtherReserveAndSurplus()));
			financialInputRequestDbl.setMinorityInterest(liabilitiesDetails.getMinorityInterest() * denomination);
			financialInputRequestString.setMinorityInterest(CommonUtils.convertValueRound(financialInputRequestDbl.getMinorityInterest()));
			financialInputRequestDbl.setSecuredLoans(liabilitiesDetails.getTermLiabilitiesSecured() * denomination);
			financialInputRequestString.setSecuredLoans(CommonUtils.convertValueRound(financialInputRequestDbl.getSecuredLoans()));
			financialInputRequestDbl.setUnsecuredLoansPromoters(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters() * denomination);
			financialInputRequestString.setUnsecuredLoansPromoters(CommonUtils.convertValueRound(financialInputRequestDbl.getUnsecuredLoansPromoters()));
			
			liabilitiesDetailsString.setTermLiabilitiesUnsecured(CommonUtils.convertValueRound(liabilitiesDetails.getTermLiabilitiesUnsecured()));
			liabilitiesDetailsString.setOtherNclUnsecuredLoansFromOther(CommonUtils.convertValueRound(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther()));
			liabilitiesDetailsString.setUnsecuredLoansOthersTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(liabilitiesDetails.getTermLiabilitiesUnsecured(), liabilitiesDetails.getOtherNclUnsecuredLoansFromOther())));
			financialInputRequestDbl.setUnsecuredLoansOthers((liabilitiesDetails.getOtherNclUnsecuredLoansFromOther() + liabilitiesDetails.getTermLiabilitiesUnsecured()) * denomination);
			financialInputRequestString.setUnsecuredLoansOthers(CommonUtils.convertValueRound(financialInputRequestDbl.getUnsecuredLoansOthers()));

			liabilitiesDetailsString.setSubTotalA(CommonUtils.convertValueRound(liabilitiesDetails.getSubTotalA()));
			liabilitiesDetailsString.setShortTermBorrowingFromOthers(CommonUtils.convertValueRound(liabilitiesDetails.getShortTermBorrowingFromOthers()));
			liabilitiesDetailsString.setOtherBorrowingsTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(liabilitiesDetails.getSubTotalA(), liabilitiesDetails.getShortTermBorrowingFromOthers())));
			financialInputRequestDbl.setOtherBorrowing((liabilitiesDetails.getSubTotalA() + liabilitiesDetails.getShortTermBorrowingFromOthers()) * denomination);
			financialInputRequestString.setOtherBorrowing(CommonUtils.convertValueRound(financialInputRequestDbl.getOtherBorrowing()));
			financialInputRequestDbl.setDeferredTaxLiablities(liabilitiesDetails.getDeferredTaxLiability() * denomination);
			financialInputRequestString.setDeferredTaxLiablities(CommonUtils.convertValueRound(financialInputRequestDbl.getDeferredTaxLiablities()));

			liabilitiesDetailsString.setOtherNcl(CommonUtils.convertValueRound(liabilitiesDetails.getOtherNcl()));
			liabilitiesDetailsString.setDeferredPaymentsCredits(CommonUtils.convertValueRound(liabilitiesDetails.getDeferredPaymentsCredits()));
			liabilitiesDetailsString.setTermDeposits(CommonUtils.convertValueRound(liabilitiesDetails.getTermDeposits()));
			liabilitiesDetailsString.setDebentures(CommonUtils.convertValueRound(liabilitiesDetails.getDebentures()));
			liabilitiesDetailsString.setOtherTermLiabilies(CommonUtils.convertValueRound(liabilitiesDetails.getOtherTermLiabilies()));
			liabilitiesDetailsString.setOtherLongTermLiabilitiesTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(liabilitiesDetails.getOtherNcl(),liabilitiesDetails.getDeferredPaymentsCredits(),liabilitiesDetails.getTermDeposits(), liabilitiesDetails.getDebentures(), liabilitiesDetails.getOtherTermLiabilies())));
			financialInputRequestDbl.setOtherLongTermLiablities((liabilitiesDetails.getOtherNclOthers() + liabilitiesDetails.getDeferredPaymentsCredits() + liabilitiesDetails.getTermDeposits() + liabilitiesDetails.getDebentures() + liabilitiesDetails.getOtherTermLiabilies()) * denomination);
			financialInputRequestString.setOtherLongTermLiablities(CommonUtils.convertValueRound(financialInputRequestDbl.getOtherLongTermLiablities()));
			financialInputRequestDbl.setLongTermProvision(liabilitiesDetails.getOtherNclLongTermProvisions() * denomination);
			financialInputRequestString.setLongTermProvision(CommonUtils.convertValueRound(financialInputRequestDbl.getLongTermProvision()));
			financialInputRequestDbl.setTradePayables(liabilitiesDetails.getSundryCreditors() * denomination);
			financialInputRequestString.setTradePayables(CommonUtils.convertValueRound(financialInputRequestDbl.getTradePayables()));
			
			liabilitiesDetailsString.setAdvancePaymentsFromCustomers(CommonUtils.convertValueRound(liabilitiesDetails.getAdvancePaymentsFromCustomers()));
			liabilitiesDetailsString.setDividendPayable(CommonUtils.convertValueRound(liabilitiesDetails.getDividendPayable()));
			liabilitiesDetailsString.setOtherStatutoryLiability(CommonUtils.convertValueRound(liabilitiesDetails.getOtherStatutoryLiability()));
			liabilitiesDetailsString.setDepositsOrInstalmentsOfTermLoans(CommonUtils.convertValueRound(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()));
			liabilitiesDetailsString.setOtherCurrentLiability(CommonUtils.convertValueRound(liabilitiesDetails.getOtherCurrentLiability()));
			liabilitiesDetailsString.setOtherCurrentLiabilitiesTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(liabilitiesDetails.getAdvancePaymentsFromCustomers(),liabilitiesDetails.getDividendPayable(),liabilitiesDetails.getOtherStatutoryLiability(),liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans(),liabilitiesDetails.getOtherCurrentLiability())));
			financialInputRequestDbl.setOtherCurruntLiablities((liabilitiesDetails.getAdvancePaymentsFromCustomers() + liabilitiesDetails.getDividendPayable() + liabilitiesDetails.getOtherStatutoryLiability() + liabilitiesDetails.getOtherCurrentLiability() + liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()) * denomination);
			financialInputRequestString.setOtherCurruntLiablities(CommonUtils.convertValueRound(financialInputRequestDbl.getOtherCurruntLiablities()));
			financialInputRequestDbl.setShortTermProvision(liabilitiesDetails.getProvisionalForTaxation() * denomination);
			financialInputRequestString.setShortTermProvision(CommonUtils.convertValueRound(financialInputRequestDbl.getShortTermProvision()));
			
			liabilitiesDetailsString.setOtherIncomeNeedTocCheckLia(CommonUtils.convertValueRound(liabilitiesDetails.getOtherIncomeNeedTocCheckLia()));
			financialInputRequestDbl.setOtherIncomeNeedTocCheckLia(liabilitiesDetails.getOtherIncomeNeedTocCheckLia() * denomination);
			financialInputRequestString.setOtherIncomeNeedTocCheckLia(CommonUtils.convertValueRound(financialInputRequestDbl.getOtherIncomeNeedTocCheckLia()));
			
			/************************************************ ASSETS DETAIL ***************************************************/
			AssetsDetails assetsDetails = assetsDetailsRepository.getAssetsDetails(applicationId, year+"");
			if(CommonUtils.isObjectNullOrEmpty(assetsDetails)) {
				assetsDetails = new AssetsDetails();
			}
			
			financialInputRequestDbl.setGrossBlock(assetsDetails.getGrossBlock() * denomination);
			financialInputRequestString.setGrossBlock(CommonUtils.convertValueRound(financialInputRequestDbl.getGrossBlock()));
			financialInputRequestDbl.setLessAccumulatedDepre(assetsDetails.getDepreciationToDate() * denomination);
			financialInputRequestString.setLessAccumulatedDepre(CommonUtils.convertValueRound(financialInputRequestDbl.getLessAccumulatedDepre()));
			financialInputRequestDbl.setImpairmentofAsset(assetsDetails.getImpairmentAsset() * denomination);
			financialInputRequestString.setImpairmentofAsset(CommonUtils.convertValueRound(financialInputRequestDbl.getImpairmentofAsset()));
			financialInputRequestDbl.setCapitalWorkInProgress(assetsDetails.getOtherNcaOtherCapitalWorkInprogress() * denomination);
			financialInputRequestString.setCapitalWorkInProgress(CommonUtils.convertValueRound(financialInputRequestDbl.getCapitalWorkInProgress()));
			financialInputRequestDbl.setIntengibleAssets(assetsDetails.getIntangibleAssets() * denomination);
			financialInputRequestString.setIntengibleAssets(CommonUtils.convertValueRound(financialInputRequestDbl.getIntengibleAssets()));
			financialInputRequestDbl.setPreOperativeExpe(assetsDetails.getOthersPreOperativeExpensesPending() * denomination);
			financialInputRequestString.setPreOperativeExpe(CommonUtils.convertValueRound(financialInputRequestDbl.getPreOperativeExpe()));
			financialInputRequestDbl.setAssetInTransit(assetsDetails.getOthersAssetsInTransit() * denomination);
			financialInputRequestString.setAssetInTransit(CommonUtils.convertValueRound(financialInputRequestDbl.getAssetInTransit()));
			financialInputRequestDbl.setInvestmentInSubsidiaries(assetsDetails.getInvestmentsInSubsidiary() * denomination);
			financialInputRequestString.setInvestmentInSubsidiaries(CommonUtils.convertValueRound(financialInputRequestDbl.getInvestmentInSubsidiaries()));
			
			assetDetailsString.setInvestmentsOrBookDebtsString(CommonUtils.convertValueRound(assetsDetails.getInvestmentsOrBookDebts()));
			assetDetailsString.setDeferredReceviables(CommonUtils.convertValueRound(assetsDetails.getDeferredReceviables()));
			assetDetailsString.setOthers(CommonUtils.convertValueRound(assetsDetails.getOthers()));
			assetDetailsString.setOtherInvestmentsTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(assetsDetails.getInvestmentsOrBookDebts(), assetsDetails.getDeferredReceviables(),assetsDetails.getOthersOther())));
			assetDetailsString.setOthersOther(CommonUtils.convertValueRound(assetsDetails.getOthersOther()));
			financialInputRequestDbl.setOtherInvestment((assetsDetails.getOthersOther() + assetsDetails.getDeferredReceviables() + assetsDetails.getOthers()) * denomination);
			financialInputRequestString.setOtherInvestment(CommonUtils.convertValueRound(financialInputRequestDbl.getOtherInvestment()));
			
			financialInputRequestDbl.setLongTermLoansAndAdva(assetsDetails.getAdvanceToSuppliersCapitalGoods() * denomination);
			financialInputRequestString.setLongTermLoansAndAdva(CommonUtils.convertValueRound(financialInputRequestDbl.getLongTermLoansAndAdva()));
			
			assetDetailsString.setNonConsumableStoreAndSpares(CommonUtils.convertValueRound(assetsDetails.getNonConsumableStoreAndSpares()));
			assetDetailsString.setOtherNonCurrentAssets(CommonUtils.convertValueRound(assetsDetails.getOtherNonCurrentAssets()));
			assetDetailsString.setOtherNonCurrentAssestsTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(assetsDetails.getNonConsumableStoreAndSpares(),assetsDetails.getOtherNonCurrentAssets())));
			financialInputRequestDbl.setOtheNonCurruntAsset((assetsDetails.getNonConsumableStoreAndSpares() + assetsDetails.getOtherNonCurrentAssets()) * denomination);
			financialInputRequestString.setOtheNonCurruntAsset(CommonUtils.convertValueRound(financialInputRequestDbl.getOtheNonCurruntAsset()));
			financialInputRequestDbl.setInventories(assetsDetails.getInventory() * denomination);
			financialInputRequestString.setInventories(CommonUtils.convertValueRound(financialInputRequestDbl.getInventories()));
			
			assetDetailsString.setReceivableOtherThanDefferred(CommonUtils.convertValueRound(assetsDetails.getReceivableOtherThanDefferred()));
			assetDetailsString.setExportReceivables(CommonUtils.convertValueRound(assetsDetails.getExportReceivables()));
			assetDetailsString.setSundryDebtorsTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(assetsDetails.getReceivableOtherThanDefferred(),assetsDetails.getExportReceivables())));
			financialInputRequestDbl.setSundryDebtors((assetsDetails.getReceivableOtherThanDefferred() + assetsDetails.getExportReceivables()) * denomination);
			financialInputRequestString.setSundryDebtors(CommonUtils.convertValueRound(financialInputRequestDbl.getSundryDebtors()));
			financialInputRequestDbl.setCashAndBank(assetsDetails.getCashAndBankBalance() * denomination);
			financialInputRequestString.setCashAndBank(CommonUtils.convertValueRound(financialInputRequestDbl.getCashAndBank()));
			
			assetDetailsString.setInvestments(CommonUtils.convertValueRound(assetsDetails.getInvestments()));
			assetDetailsString.setInstalmentsDeferred(CommonUtils.convertValueRound(assetsDetails.getInstalmentsDeferred()));
			assetDetailsString.setOtherCurrentAssets(CommonUtils.convertValueRound(assetsDetails.getOtherCurrentAssets()));
			assetDetailsString.setOtherCurrentAssetsTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(assetsDetails.getInvestments(),assetsDetails.getInstalmentsDeferred(), assetsDetails.getOtherCurrentAssets())));
			financialInputRequestDbl.setOtherCurruntAsset((assetsDetails.getInvestments() + assetsDetails.getInstalmentsDeferred() + assetsDetails.getOtherCurrentAssets()) * denomination);
			financialInputRequestString.setOtherCurruntAsset(CommonUtils.convertValueRound(financialInputRequestDbl.getOtherCurruntAsset()));
			
			assetDetailsString.setAdvanceToSupplierRawMaterials(CommonUtils.convertValueRound(assetsDetails.getAdvanceToSupplierRawMaterials()));
			assetDetailsString.setAdvancePaymentTaxes(CommonUtils.convertValueRound(assetsDetails.getAdvancePaymentTaxes()));
			assetDetailsString.setShortTermLoansAndAdvancesTotal(CommonUtils.convertValueRound(CommonUtils.addNumbers(assetsDetails.getAdvanceToSupplierRawMaterials(),assetsDetails.getAdvancePaymentTaxes() )));
			financialInputRequestDbl.setShortTermLoansAdvances((assetsDetails.getAdvanceToSupplierRawMaterials() + assetsDetails.getAdvancePaymentTaxes()) * denomination);
			financialInputRequestString.setShortTermLoansAdvances(CommonUtils.convertValueRound(financialInputRequestDbl.getShortTermLoansAdvances()));
			if(corporateFinalInfoRequest == null) {
				financialInputRequestDbl.setContingentLiablities(null);
				financialInputRequestString.setContingentLiablities(null);
			}
			else {
				financialInputRequestDbl.setContingentLiablities(CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getContLiabilityFyAmt()) ? 0.0 : (corporateFinalInfoRequest.getContLiabilityFyAmt()* denomination));
				financialInputRequestString.setContingentLiablities(CommonUtils.convertValueRound(financialInputRequestDbl.getContingentLiablities()));
			}
			
			assetDetailsString.setOtherIncomeNeedTocCheckAsset(CommonUtils.convertValueRound(assetsDetails.getOtherIncomeNeedTocCheckAsset()));
			financialInputRequestDbl.setOtherIncomeNeedTocCheckAsset(assetsDetails.getOtherIncomeNeedTocCheckAsset() * denomination);
			financialInputRequestString.setOtherIncomeNeedTocCheckAsset(CommonUtils.convertValueRound(financialInputRequestDbl.getOtherIncomeNeedTocCheckAsset()));
			assetDetailsString.setCurrentRatio(CommonUtils.convertValueRound(assetsDetails.getCurrentRatio()));
			
			/************************************************** OTHER CALCULATIONS *******************************************************/ 
			//Profit & Loss Statement
	        financialInputRequestDbl.setNetSale(CommonUtils.substractNumbers(financialInputRequestDbl.getGrossSales(), financialInputRequestDbl.getLessExciseDuity()));
	      	financialInputRequestString.setNetSale(CommonUtils.convertValueRound(financialInputRequestDbl.getNetSale()));
			financialInputRequestDbl.setTotalExpenditure(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequestDbl.getIncreaseDecreaseStock(),financialInputRequestDbl.getRawMaterialConsumed(),financialInputRequestDbl.getPowerAndFuelCost(),financialInputRequestDbl.getEmployeeCost(), financialInputRequestDbl.getGeneralAndAdminExpe(),financialInputRequestDbl.getSellingAndDistriExpe(),financialInputRequestDbl.getMiscelExpe()), financialInputRequestDbl.getLessExpeCapita()));
			financialInputRequestString.setTotalExpenditure(CommonUtils.convertValueRound(financialInputRequestDbl.getTotalExpenditure()));
			financialInputRequestDbl.setOperatingProfitExclOi(CommonUtils.substractNumbers(financialInputRequestDbl.getNetSale(),financialInputRequestDbl.getTotalExpenditure()));
			financialInputRequestString.setOperatingProfitExclOi(CommonUtils.convertValueRound(financialInputRequestDbl.getOperatingProfitExclOi()));
			financialInputRequestDbl.setOperatingProfitEbitadOi(CommonUtils.addNumbers(financialInputRequestDbl.getOperatingProfitExclOi(),financialInputRequestDbl.getOtherIncome()));
			financialInputRequestString.setOperatingProfitEbitadOi(CommonUtils.convertValueRound(financialInputRequestDbl.getOperatingProfitEbitadOi()));
			financialInputRequestDbl.setPbdt(CommonUtils.substractNumbers(financialInputRequestDbl.getOperatingProfitEbitadOi(), financialInputRequestDbl.getInterest()));
			financialInputRequestString.setPbdt(CommonUtils.convertValueRound(financialInputRequestDbl.getPbdt()));
			financialInputRequestDbl.setProfitBeforeTaxation(CommonUtils.substractNumbers(financialInputRequestDbl.getPbdt(), financialInputRequestDbl.getDepriciation()));
			financialInputRequestString.setProfitBeforeTaxation(CommonUtils.convertValueRound(financialInputRequestDbl.getProfitBeforeTaxation()));
			financialInputRequestDbl.setProfitBeforeTax(CommonUtils.addNumbers(financialInputRequestDbl.getProfitBeforeTaxation(), financialInputRequestDbl.getExceptionalIncome()));
			financialInputRequestString.setProfitBeforeTax(CommonUtils.convertValueRound(financialInputRequestDbl.getProfitBeforeTax()));
			financialInputRequestDbl.setProfitAfterTax(CommonUtils.substractNumbers(financialInputRequestDbl.getProfitBeforeTax(), financialInputRequestDbl.getProvisionForTax()) + financialInputRequestDbl.getOtherIncomeNeedTocCheckOp());
			financialInputRequestString.setProfitAfterTax(CommonUtils.convertValueRound(financialInputRequestDbl.getProfitAfterTax()));
			if(financialInputRequestDbl.getDividendPayOut() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getDividendPayOut()) || financialInputRequestDbl.getShareFaceValue() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getShareFaceValue()) || financialInputRequestDbl.getShareCapital() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getShareCapital()))
				financialInputRequestString.setEquityDividend("0.0");
			else
				financialInputRequestString.setEquityDividend(CommonUtils.convertValueRound((financialInputRequestDbl.getDividendPayOut()*financialInputRequestDbl.getShareFaceValue()/financialInputRequestDbl.getShareCapital())));
			
			if(financialInputRequestDbl.getShareFaceValue() !=0 && financialInputRequestDbl.getShareCapital() !=0) {
				double total = financialInputRequestDbl.getShareFaceValue()/financialInputRequestDbl.getShareCapital();
				if(!CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getProfitAfterTax()) && financialInputRequestDbl.getProfitAfterTax() !=0) {
					financialInputRequestString.setEarningPerShare(CommonUtils.convertValueRound(financialInputRequestDbl.getProfitAfterTax() * total));
				}
			}
			
			//Balance Sheet -Equities and Liabilities
			
			financialInputRequestDbl.setShareHolderFunds(CommonUtils.addNumbers(financialInputRequestDbl.getShareCapital(),financialInputRequestDbl.getShareWarrantOutstandings(),financialInputRequestDbl.getRevalationReserve(),financialInputRequestDbl.getOtherReserveAndSurplus()));
			financialInputRequestString.setShareHolderFunds(CommonUtils.convertValueRound(financialInputRequestDbl.getShareHolderFunds()));
			financialInputRequestDbl.setTotalNonCurruntLiablities(CommonUtils.addNumbers(financialInputRequestDbl.getMinorityInterest(), financialInputRequestDbl.getSecuredLoans(), financialInputRequestDbl.getUnsecuredLoansOthers(),financialInputRequestDbl.getUnsecuredLoansPromoters(),financialInputRequestDbl.getDeferredTaxLiablities(),financialInputRequestDbl.getOtherLongTermLiablities(),financialInputRequestDbl.getOtherBorrowing(),financialInputRequestDbl.getLongTermProvision()));
			financialInputRequestString.setTotalNonCurruntLiablities(CommonUtils.convertValueRound(financialInputRequestDbl.getTotalNonCurruntLiablities()));
			financialInputRequestDbl.setTotalCurruntLiablities(CommonUtils.addNumbers(financialInputRequestDbl.getTradePayables(), financialInputRequestDbl.getOtherCurruntLiablities(), financialInputRequestDbl.getShortTermProvision()));
			financialInputRequestString.setTotalCurruntLiablities(CommonUtils.convertValueRound(financialInputRequestDbl.getTotalCurruntLiablities()));
			financialInputRequestDbl.setTotalLiablities(CommonUtils.addNumbers(financialInputRequestDbl.getShareHolderFunds(), financialInputRequestDbl.getTotalNonCurruntLiablities(), financialInputRequestDbl.getTotalCurruntLiablities(), financialInputRequestDbl.getOtherIncomeNeedTocCheckLia()));
			financialInputRequestString.setTotalLiablities(CommonUtils.convertValueRound(financialInputRequestDbl.getTotalLiablities()));

			//Balance Sheet -ASSETS
			financialInputRequestDbl.setNetBlock(CommonUtils.substractThreeNumbers(financialInputRequestDbl.getGrossBlock(), financialInputRequestDbl.getLessAccumulatedDepre(),financialInputRequestDbl.getImpairmentofAsset()));
			financialInputRequestString.setNetBlock(CommonUtils.convertValueRound(financialInputRequestDbl.getNetBlock()));
			financialInputRequestDbl.setTotalNonCurruntAsset(CommonUtils.addNumbers(financialInputRequestDbl.getCapitalWorkInProgress(), financialInputRequestDbl.getIntengibleAssets(), financialInputRequestDbl.getPreOperativeExpe(), financialInputRequestDbl.getAssetInTransit(), financialInputRequestDbl.getInvestmentInSubsidiaries(), financialInputRequestDbl.getOtherInvestment(), financialInputRequestDbl.getLongTermLoansAndAdva(), financialInputRequestDbl.getOtheNonCurruntAsset()));
			financialInputRequestString.setTotalNonCurruntAsset(CommonUtils.convertValueRound(financialInputRequestDbl.getTotalNonCurruntAsset()));
			financialInputRequestDbl.setTotalCurruntAsset(CommonUtils.addNumbers(financialInputRequestDbl.getInventories(), financialInputRequestDbl.getSundryDebtors(), financialInputRequestDbl.getCashAndBank(), financialInputRequestDbl.getOtherCurruntAsset(), financialInputRequestDbl.getShortTermLoansAdvances()));
			financialInputRequestString.setTotalCurruntAsset(CommonUtils.convertValueRound(financialInputRequestDbl.getTotalCurruntAsset()));
			financialInputRequestDbl.setTotalAsset(CommonUtils.addNumbers(financialInputRequestDbl.getNetBlock(), financialInputRequestDbl.getTotalCurruntAsset(), financialInputRequestDbl.getTotalNonCurruntAsset(), financialInputRequestDbl.getOtherIncomeNeedTocCheckAsset()));
			financialInputRequestString.setTotalAsset(CommonUtils.convertValueRound(financialInputRequestDbl.getTotalAsset()));
			if(financialInputRequestDbl.getShareFaceValue() !=0 && financialInputRequestDbl.getShareCapital() !=0) {
				double total = financialInputRequestDbl.getShareCapital()/financialInputRequestDbl.getShareFaceValue();
				if(!CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getShareHolderFunds()) && financialInputRequestDbl.getShareHolderFunds() !=0) {
					financialInputRequestString.setBookValue(CommonUtils.convertValueRound(financialInputRequestDbl.getShareHolderFunds() / total));
				}
			}
			
			return new Object[] {osDetailsString, liabilitiesDetailsString, assetDetailsString , financialInputRequestString , financialInputRequestDbl };
		}
		
		
		public void calculateRatioAnalysis(Map<Integer, Object[]>financials,Long applicationId) {
			int currentYear = scoringService.getFinYear(applicationId);
			DecimalFormat decim = new DecimalFormat("###.##");
			Object[] curFinYear = financials.get(currentYear - 1);
			Object[] prevFinYear = financials.get(currentYear - 2);
			Object[] yrBeforePrevFinYear = financials.get(currentYear - 3);
			
			FinancialInputRequestString curFinYearString =  (FinancialInputRequestString)curFinYear[curFinYear.length - 2];
			FinancialInputRequestString prevFinYearString =  (FinancialInputRequestString)prevFinYear[prevFinYear.length - 2];
			FinancialInputRequestString yrBeforePrevFinYearString =  (FinancialInputRequestString)yrBeforePrevFinYear[yrBeforePrevFinYear.length - 2];
			
			FinancialInputRequestDbl curFinYearDouble =  (FinancialInputRequestDbl)curFinYear[curFinYear.length - 1];
			FinancialInputRequestDbl prevFinYearDouble =  (FinancialInputRequestDbl)prevFinYear[prevFinYear.length - 1];
			FinancialInputRequestDbl yrBeforePrevFinYearDouble =  (FinancialInputRequestDbl)yrBeforePrevFinYear[yrBeforePrevFinYear.length - 1];
			
			 //CASH FLOW 
			curFinYearDouble.setEbitda(curFinYearDouble.getOperatingProfitEbitadOi());
			prevFinYearDouble.setEbitda(prevFinYearDouble.getOperatingProfitEbitadOi());
			curFinYearString.setEbitda(CommonUtils.convertValue(curFinYearDouble.getEbitda()));
			prevFinYearString.setEbitda(CommonUtils.convertValue(prevFinYearDouble.getEbitda()));
			
			curFinYearDouble.setInterestPaid(curFinYearDouble.getInterest());
			prevFinYearDouble.setInterestPaid(prevFinYearDouble.getInterest());
			curFinYearString.setInterestPaid(CommonUtils.convertValue(curFinYearDouble.getInterestPaid()));
			prevFinYearString.setInterestPaid(CommonUtils.convertValue(prevFinYearDouble.getInterestPaid()));
			
			curFinYearDouble.setIncreaseWorkingCapital(curFinYearDouble.getInventories()+curFinYearDouble.getSundryDebtors()+curFinYearDouble.getOtherCurruntAsset()-prevFinYearDouble.getInventories()-prevFinYearDouble.getSundryDebtors()-prevFinYearDouble.getOtherCurruntAsset()+prevFinYearDouble.getTradePayables()+prevFinYearDouble.getOtherCurruntLiablities()+prevFinYearDouble.getShortTermProvision()-curFinYearDouble.getTradePayables()-curFinYearDouble.getOtherCurruntLiablities()-curFinYearDouble.getShortTermProvision());
			prevFinYearDouble.setIncreaseWorkingCapital(prevFinYearDouble.getInventories()+prevFinYearDouble.getSundryDebtors()+prevFinYearDouble.getOtherCurruntAsset()-yrBeforePrevFinYearDouble.getInventories()-yrBeforePrevFinYearDouble.getSundryDebtors()-yrBeforePrevFinYearDouble.getOtherCurruntAsset()+yrBeforePrevFinYearDouble.getTradePayables()+yrBeforePrevFinYearDouble.getOtherCurruntLiablities()+yrBeforePrevFinYearDouble.getShortTermProvision()-prevFinYearDouble.getTradePayables()-prevFinYearDouble.getOtherCurruntLiablities()-prevFinYearDouble.getShortTermProvision());
			curFinYearString.setIncreaseWorkingCapital(CommonUtils.convertValue(curFinYearDouble.getIncreaseWorkingCapital()));
			prevFinYearString.setIncreaseWorkingCapital(CommonUtils.convertValue(prevFinYearDouble.getIncreaseWorkingCapital()));
			
			curFinYearDouble.setTaxPaid(curFinYearDouble.getProvisionForTax());
			prevFinYearDouble.setTaxPaid(prevFinYearDouble.getProvisionForTax());
			curFinYearString.setTaxPaid(CommonUtils.convertValue(curFinYearDouble.getTaxPaid()));
			prevFinYearString.setTaxPaid(CommonUtils.convertValue(prevFinYearDouble.getTaxPaid()));
			
			curFinYearDouble.setCashFromOperating(curFinYearDouble.getEbitda()-curFinYearDouble.getInterestPaid()-curFinYearDouble.getIncreaseWorkingCapital()-curFinYearDouble.getTaxPaid());
			prevFinYearDouble.setCashFromOperating(prevFinYearDouble.getEbitda()-prevFinYearDouble.getInterestPaid()-prevFinYearDouble.getIncreaseWorkingCapital()-prevFinYearDouble.getTaxPaid());
			curFinYearString.setCashFromOperating(CommonUtils.convertValue(curFinYearDouble.getCashFromOperating()));
			prevFinYearString.setCashFromOperating(CommonUtils.convertValue(prevFinYearDouble.getCashFromOperating()));
			
			//RATIO ANALYSIS
			curFinYearString.setEbitadPercentage(CommonUtils.convertValue(CommonUtils.divideNumbers(curFinYearDouble.getOperatingProfitEbitadOi(), curFinYearDouble.getNetSale())* 100));
			prevFinYearString.setEbitadPercentage(CommonUtils.convertValue(CommonUtils.divideNumbers(prevFinYearDouble.getOperatingProfitEbitadOi(), prevFinYearDouble.getNetSale())* 100));
			yrBeforePrevFinYearString.setEbitadPercentage(CommonUtils.convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getOperatingProfitEbitadOi(), yrBeforePrevFinYearDouble.getNetSale())* 100));
			
			curFinYearString.setPatm(CommonUtils.convertValue(CommonUtils.divideNumbers(curFinYearDouble.getProfitAfterTax(), curFinYearDouble.getNetSale())* 100));
			prevFinYearString.setPatm(CommonUtils.convertValue(CommonUtils.divideNumbers(prevFinYearDouble.getProfitAfterTax(), prevFinYearDouble.getNetSale())* 100));
			yrBeforePrevFinYearString.setPatm(CommonUtils.convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getProfitAfterTax(), yrBeforePrevFinYearDouble.getNetSale())* 100));
			
			curFinYearString.setRoce(CommonUtils.convertValue(((curFinYearDouble.getOperatingProfitEbitadOi()*2/(CommonUtils.addNumbers(curFinYearDouble.getShareHolderFunds(), prevFinYearDouble.getShareHolderFunds(), curFinYearDouble.getTotalNonCurruntLiablities(), prevFinYearDouble.getTotalNonCurruntLiablities())))*12/curFinYearDouble.getNoOfMonth())* 100));
			prevFinYearString.setRoce(CommonUtils.convertValue(((prevFinYearDouble.getOperatingProfitEbitadOi()*2/(CommonUtils.addNumbers(prevFinYearDouble.getShareHolderFunds(), yrBeforePrevFinYearDouble.getShareHolderFunds(), prevFinYearDouble.getTotalNonCurruntLiablities(), yrBeforePrevFinYearDouble.getTotalNonCurruntLiablities())))*12/prevFinYearDouble.getNoOfMonth())* 100));
			yrBeforePrevFinYearString.setRoce("-");
			
			curFinYearString.setAssetTurnOver(CommonUtils.convertValue(CommonUtils.divideNumbers(curFinYearDouble.getNetSale() * 12, (CommonUtils.multiplyNumbers(curFinYearDouble.getTotalAsset(), curFinYearDouble.getNoOfMonth())))));
			prevFinYearString.setAssetTurnOver(CommonUtils.convertValue(CommonUtils.divideNumbers(prevFinYearDouble.getNetSale() * 12, (CommonUtils.multiplyNumbers(prevFinYearDouble.getTotalAsset(), prevFinYearDouble.getNoOfMonth())))));
			yrBeforePrevFinYearString.setAssetTurnOver(CommonUtils.convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getNetSale() * 12, (CommonUtils.multiplyNumbers(yrBeforePrevFinYearDouble.getTotalAsset(), yrBeforePrevFinYearDouble.getNoOfMonth())))));
			
			curFinYearString.setInventoryTurnOver(CommonUtils.convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(curFinYearDouble.getTotalExpenditure()*12, (curFinYearDouble.getInventories()*curFinYearDouble.getNoOfMonth()))))));
			prevFinYearString.setInventoryTurnOver(CommonUtils.convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(prevFinYearDouble.getTotalExpenditure()*12, (prevFinYearDouble.getInventories()*prevFinYearDouble.getNoOfMonth()))))));
			yrBeforePrevFinYearString.setInventoryTurnOver(CommonUtils.convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getTotalExpenditure()*12, (yrBeforePrevFinYearDouble.getInventories()*yrBeforePrevFinYearDouble.getNoOfMonth()))))));
			
			curFinYearString.setDebtorsTurnover(CommonUtils.convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(curFinYearDouble.getNetSale()*12, (curFinYearDouble.getSundryDebtors()*curFinYearDouble.getNoOfMonth()))))));
			prevFinYearString.setDebtorsTurnover(CommonUtils.convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(prevFinYearDouble.getNetSale()*12, (prevFinYearDouble.getSundryDebtors()*prevFinYearDouble.getNoOfMonth()))))));
			yrBeforePrevFinYearString.setDebtorsTurnover(CommonUtils.convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getNetSale()*12, (yrBeforePrevFinYearDouble.getSundryDebtors()*yrBeforePrevFinYearDouble.getNoOfMonth()))))));
			
			curFinYearString.setCreditorsTurnover(CommonUtils.convertValue((CommonUtils.divideNumbers(365.0, CommonUtils.divideNumbers((CommonUtils.addNumbers(curFinYearDouble.getRawMaterialConsumed(), curFinYearDouble.getPowerAndFuelCost())), curFinYearDouble.getTradePayables())))*12/curFinYearDouble.getNoOfMonth()));
			prevFinYearString.setCreditorsTurnover(CommonUtils.convertValue((CommonUtils.divideNumbers(365.0, CommonUtils.divideNumbers((CommonUtils.addNumbers(prevFinYearDouble.getRawMaterialConsumed(), prevFinYearDouble.getPowerAndFuelCost())), prevFinYearDouble.getTradePayables())))*12/prevFinYearDouble.getNoOfMonth()));
			yrBeforePrevFinYearString.setCreditorsTurnover(CommonUtils.convertValue((CommonUtils.divideNumbers(365.0, CommonUtils.divideNumbers((CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getRawMaterialConsumed(), yrBeforePrevFinYearDouble.getPowerAndFuelCost())), yrBeforePrevFinYearDouble.getTradePayables())))*12/yrBeforePrevFinYearDouble.getNoOfMonth()));
			
			curFinYearString.setSalesWorkingCapital(CommonUtils.convertValue((CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(curFinYearDouble.getNetSale(), (CommonUtils.addNumbers(curFinYearDouble.getInventories(),curFinYearDouble.getSundryDebtors())-curFinYearDouble.getTradePayables())))))*12 /curFinYearDouble.getNoOfMonth()));
			prevFinYearString.setSalesWorkingCapital(CommonUtils.convertValue((CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(prevFinYearDouble.getNetSale(), (CommonUtils.addNumbers(prevFinYearDouble.getInventories(),prevFinYearDouble.getSundryDebtors())-prevFinYearDouble.getTradePayables())))))*12 /prevFinYearDouble.getNoOfMonth()));
			yrBeforePrevFinYearString.setSalesWorkingCapital(CommonUtils.convertValue((CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getNetSale(), (CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getInventories(),yrBeforePrevFinYearDouble.getSundryDebtors())-yrBeforePrevFinYearDouble.getTradePayables())))))*12 /yrBeforePrevFinYearDouble.getNoOfMonth()));
			
			curFinYearString.setNetSalesGrowthCapital(CommonUtils.convertValue((CommonUtils.divideNumbers(curFinYearDouble.getNetSale(),prevFinYearDouble.getNetSale())-1)* 100));
			prevFinYearString.setNetSalesGrowthCapital(CommonUtils.convertValue((CommonUtils.divideNumbers(prevFinYearDouble.getNetSale(),yrBeforePrevFinYearDouble.getNetSale())-1)* 100));
			yrBeforePrevFinYearString.setNetSalesGrowthCapital("-");
			
			curFinYearString.setPatGrowthCapital(CommonUtils.convertValue((CommonUtils.divideNumbers(curFinYearDouble.getProfitAfterTax(),prevFinYearDouble.getProfitAfterTax())-1)* 100));
			prevFinYearString.setPatGrowthCapital(CommonUtils.convertValue((CommonUtils.divideNumbers(prevFinYearDouble.getProfitAfterTax(),yrBeforePrevFinYearDouble.getProfitAfterTax())-1)* 100));
			yrBeforePrevFinYearString.setPatGrowthCapital("-");
			
			curFinYearDouble.setAdjustedTotalDebtEquity(Double.parseDouble(decim.format(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(curFinYearDouble.getTotalNonCurruntLiablities(),curFinYearDouble.getLongTermProvision(),curFinYearDouble.getUnsecuredLoansPromoters())), (CommonUtils.addNumbers(curFinYearDouble.getShareHolderFunds(),curFinYearDouble.getUnsecuredLoansPromoters()))))));
			prevFinYearDouble.setAdjustedTotalDebtEquity(Double.parseDouble(decim.format(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(prevFinYearDouble.getTotalNonCurruntLiablities(),prevFinYearDouble.getLongTermProvision(),prevFinYearDouble.getUnsecuredLoansPromoters())), (CommonUtils.addNumbers(prevFinYearDouble.getShareHolderFunds(),prevFinYearDouble.getUnsecuredLoansPromoters()))))));
			yrBeforePrevFinYearDouble.setAdjustedTotalDebtEquity(Double.parseDouble(decim.format(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(yrBeforePrevFinYearDouble.getTotalNonCurruntLiablities(),yrBeforePrevFinYearDouble.getLongTermProvision(),yrBeforePrevFinYearDouble.getUnsecuredLoansPromoters())), (CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getShareHolderFunds(),yrBeforePrevFinYearDouble.getUnsecuredLoansPromoters()))))));
			curFinYearString.setAdjustedTotalDebtEquity(CommonUtils.convertValue(curFinYearDouble.getAdjustedTotalDebtEquity()));
			prevFinYearString.setAdjustedTotalDebtEquity(CommonUtils.convertValue(prevFinYearDouble.getAdjustedTotalDebtEquity()));
			yrBeforePrevFinYearString.setAdjustedTotalDebtEquity(CommonUtils.convertValue(yrBeforePrevFinYearDouble.getAdjustedTotalDebtEquity()));
			
			curFinYearString.setGrowthDebtEquity(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(curFinYearDouble.getAdjustedTotalDebtEquity(),prevFinYearDouble.getAdjustedTotalDebtEquity())), prevFinYearDouble.getAdjustedTotalDebtEquity())*100));
			prevFinYearString.setGrowthDebtEquity(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(prevFinYearDouble.getAdjustedTotalDebtEquity(),yrBeforePrevFinYearDouble.getAdjustedTotalDebtEquity())), yrBeforePrevFinYearDouble.getAdjustedTotalDebtEquity())*100));
			yrBeforePrevFinYearString.setGrowthDebtEquity("-");
			
			curFinYearString.setCurruntRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(curFinYearDouble.getInventories(),curFinYearDouble.getSundryDebtors())), curFinYearDouble.getTradePayables())));
			prevFinYearString.setCurruntRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(prevFinYearDouble.getInventories(),prevFinYearDouble.getSundryDebtors())), prevFinYearDouble.getTradePayables())));
			yrBeforePrevFinYearString.setCurruntRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getInventories(),yrBeforePrevFinYearDouble.getSundryDebtors())), yrBeforePrevFinYearDouble.getTradePayables())));
			
			curFinYearString.setQuickRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers(curFinYearDouble.getSundryDebtors(),curFinYearDouble.getTradePayables())));
			prevFinYearString.setQuickRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers(prevFinYearDouble.getSundryDebtors(),prevFinYearDouble.getTradePayables())));
			yrBeforePrevFinYearString.setQuickRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getSundryDebtors(),yrBeforePrevFinYearDouble.getTradePayables())));
			
			curFinYearString.setCashInterestCover(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(curFinYearDouble.getOperatingProfitEbitadOi(),curFinYearDouble.getProvisionForTax())), curFinYearDouble.getInterest())));
			prevFinYearString.setCashInterestCover(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(prevFinYearDouble.getOperatingProfitEbitadOi(),prevFinYearDouble.getProvisionForTax())), prevFinYearDouble.getInterest())));
			yrBeforePrevFinYearString.setCashInterestCover(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(yrBeforePrevFinYearDouble.getOperatingProfitEbitadOi(),yrBeforePrevFinYearDouble.getProvisionForTax())), yrBeforePrevFinYearDouble.getInterest())));
			
			curFinYearString.setDebtEbitad(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(curFinYearDouble.getTotalNonCurruntLiablities(),curFinYearDouble.getUnsecuredLoansPromoters(),curFinYearDouble.getLongTermProvision())), (12*CommonUtils.divideNumbers(curFinYearDouble.getOperatingProfitEbitadOi(),curFinYearDouble.getNoOfMonth())))));
			prevFinYearString.setDebtEbitad(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(prevFinYearDouble.getTotalNonCurruntLiablities(),prevFinYearDouble.getUnsecuredLoansPromoters(),prevFinYearDouble.getLongTermProvision())), (12*CommonUtils.divideNumbers(prevFinYearDouble.getOperatingProfitEbitadOi(),prevFinYearDouble.getNoOfMonth())))));
			yrBeforePrevFinYearString.setDebtEbitad(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(yrBeforePrevFinYearDouble.getTotalNonCurruntLiablities(),yrBeforePrevFinYearDouble.getUnsecuredLoansPromoters(),yrBeforePrevFinYearDouble.getLongTermProvision())), (12*CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getOperatingProfitEbitadOi(),yrBeforePrevFinYearDouble.getNoOfMonth())))));
			
			curFinYearString.setFreeReserveEquity(CommonUtils.convertValue(CommonUtils.divideNumbers(curFinYearDouble.getOtherReserveAndSurplus(), (CommonUtils.addNumbers(curFinYearDouble.getShareCapital(),curFinYearDouble.getShareWarrantOutstandings())))));
			prevFinYearString.setFreeReserveEquity(CommonUtils.convertValue(CommonUtils.divideNumbers(prevFinYearDouble.getOtherReserveAndSurplus(), (CommonUtils.addNumbers(prevFinYearDouble.getShareCapital(),prevFinYearDouble.getShareWarrantOutstandings())))));
			yrBeforePrevFinYearString.setFreeReserveEquity(CommonUtils.convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getOtherReserveAndSurplus(), (CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getShareCapital(),yrBeforePrevFinYearDouble.getShareWarrantOutstandings())))));
			
			curFinYearDouble.setCfoMargine(CommonUtils.divideNumbers(curFinYearDouble.getCashFromOperating(),curFinYearDouble.getNetSale())*100);
			prevFinYearDouble.setCfoMargine(CommonUtils.divideNumbers(prevFinYearDouble.getCashFromOperating(),prevFinYearDouble.getNetSale())*100);
			curFinYearString.setCfoMargine(CommonUtils.convertValue(curFinYearDouble.getCfoMargine()));
			prevFinYearString.setCfoMargine(CommonUtils.convertValue(prevFinYearDouble.getCfoMargine()));
			yrBeforePrevFinYearString.setCfoMargine("-");
			
			curFinYearString.setGrowthCfoMargine(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(curFinYearDouble.getCfoMargine(),prevFinYearDouble.getCfoMargine())), prevFinYearDouble.getCfoMargine()) *100));
			prevFinYearString.setGrowthCfoMargine("-");
			yrBeforePrevFinYearString.setGrowthCfoMargine("-");
			
			
			curFinYear[curFinYear.length - 2] = curFinYearString;
			prevFinYear[prevFinYear.length - 2] = prevFinYearString;
			yrBeforePrevFinYear[yrBeforePrevFinYear.length - 2] = yrBeforePrevFinYearString;
			
			financials.put((currentYear - 1), curFinYear);
			financials.put((currentYear - 2), prevFinYear);
			financials.put((currentYear - 3), yrBeforePrevFinYear);
			logger.info("financials========="+financials.toString());
		}
	
	

}
