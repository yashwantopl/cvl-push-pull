package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.api.eligibility.model.CLEligibilityRequest;
import com.capitaworld.api.eligibility.model.EligibililityRequest;
import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.cibil.api.model.CibilRequest;
import com.capitaworld.cibil.api.model.CibilResponse;
import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.api.ConnectStage;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.fitchengine.model.manufacturing.FitchOutputManu;
import com.capitaworld.service.fitchengine.model.service.FitchOutputServ;
import com.capitaworld.service.fitchengine.model.trading.FitchOutputTrad;
import com.capitaworld.service.fitchengine.utils.CommonUtils.BusinessType;
import com.capitaworld.service.fraudanalytics.client.FraudAnalyticsClient;
import com.capitaworld.service.fraudanalytics.model.AnalyticsResponse;
import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.gst.yuva.request.GSTR1Request;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponseString;
import com.capitaworld.service.loans.model.FinanceMeansDetailRequest;
import com.capitaworld.service.loans.model.FinanceMeansDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementDetailResponseString;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.OwnershipDetailRequest;
import com.capitaworld.service.loans.model.OwnershipDetailResponse;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.TotalCostOfProjectResponse;
import com.capitaworld.service.loans.model.CAM.AssetDetailsString;
import com.capitaworld.service.loans.model.CAM.FinancialInputRequestDbl;
import com.capitaworld.service.loans.model.CAM.FinancialInputRequestString;
import com.capitaworld.service.loans.model.CAM.LiabilitiesDetailsString;
import com.capitaworld.service.loans.model.CAM.OperatingStatementDetailsString;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.corporate.PrimaryCorporateRequest;
import com.capitaworld.service.loans.model.corporate.TotalCostOfProjectRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AchievmentDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssociatedConcernDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CamReportPdfDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExistingProductDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinanceMeansDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.GuarantorsCorporateDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.corporate.MonthlyTurnoverDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OwnershipDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PrimaryCorporateService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PromotorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProposedProductDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.SecurityCorporateDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.TotalCostOfProjectService;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.mca.client.McaClient;
import com.capitaworld.service.mca.model.McaResponse;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.Constitution;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.DirectorRelationshipType;
import com.capitaworld.service.oneform.enums.EstablishmentMonths;
import com.capitaworld.service.oneform.enums.FinanceCategory;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.Industry;
import com.capitaworld.service.oneform.enums.Particular;
import com.capitaworld.service.oneform.enums.PurposeOfLoan;
import com.capitaworld.service.oneform.enums.ShareHoldingCategory;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.rating.model.RatingResponse;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.model.ProposalScoreDetailResponse;
import com.capitaworld.service.scoring.model.ProposalScoreResponse;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.scoring.utils.ScoreParameter;
import com.capitaworld.service.thirdparty.model.CGTMSEDataResponse;
import com.capitaworld.service.thirdparty.model.CGTMSEResponseDetails;
import com.capitaworld.service.thirdpaty.client.ThirdPartyClient;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;
import com.ibm.icu.util.Calendar;

@Service
@Transactional
public class CamReportPdfDetailsServiceImpl implements CamReportPdfDetailsService{

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private IrrService irrService; 
	
	@Autowired
	private MatchEngineClient matchEngineClient; 
	
	@Autowired
	private ScoringClient scoringClient;
	
	@Autowired
	private AnalyzerClient analyzerClient;
	
	@Autowired
	private CorporateApplicantService corporateApplicantService;
	
	@Autowired
	private PrimaryCorporateService primaryCorporateService;
	
	@Autowired
	private CorporateFinalInfoService corporateFinalInfoService;
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private OneFormClient oneFormClient;
	
	@Autowired 
	private PromotorBackgroundDetailsService promotorBackgroundDetailsService;
	
	@Autowired
	private OwnershipDetailsService ownershipDetailsService;
	
	@Autowired
	private ProposedProductDetailsService proposedProductDetailsService; 
	
	@Autowired
	private ExistingProductDetailsService existingProductDetailsService;
	
	@Autowired
	private AssociatedConcernDetailService associatedConcernDetailService;
	
	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;
	
	@Autowired
	private AchievmentDetailsService achievmentDetailsService;
	
	@Autowired
	private DirectorBackgroundDetailsService backgroundDetailsService;
	
	@Autowired
	private ProposalDetailsClient proposalDetailsClient;
	
	@Autowired
	private UsersClient usersClient;
	
	@Autowired
	private CIBILClient cibilClient;
	
	@Autowired
	private GstClient gstClient;
	
	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;
	
	@Autowired
	private GuarantorsCorporateDetailService guarantorsCorporateDetailService;

	@Autowired
	private MonthlyTurnoverDetailService monthlyTurnoverDetailService;
	
	@Autowired
	private TotalCostOfProjectService costOfProjectService; 
	
	@Autowired
	private FinanceMeansDetailsService financeMeansDetailsService;
	
	@Autowired
	private SecurityCorporateDetailsService securityCorporateDetailsService;
	
	@Autowired
	private ThirdPartyClient thirdPartyClient;
	
	@Autowired
	OperatingStatementDetailsRepository operatingStatementDetailsRepository;
	
	@Autowired
	LiabilitiesDetailsRepository liabilitiesDetailsRepository;
	
	@Autowired
	AssetsDetailsRepository assetsDetailsRepository;
	
	@Autowired
	EligibilityClient eligibilityClient;
	
	@Autowired
	WorkflowClient workflowClient;
	
	@Autowired
	ConnectClient connectClient;
	
	@Autowired
	McaClient mcaClient;
	
	@Autowired
	FraudAnalyticsClient fraudAnalyticsClient;
	
	private static final Logger logger = LoggerFactory.getLogger(CamReportPdfDetailsServiceImpl.class);
	 public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	DecimalFormat decim = new DecimalFormat("#,##0.00");
	DecimalFormat decim2 = new DecimalFormat("#,###");
	@Override
	public Map<String, Object> getCamReportPrimaryDetails(Long applicationId, Long productId, boolean isFinalView) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long userId = loanApplicationRepository.getUserIdByApplicationId(applicationId);
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
		map.put("date",!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getApprovedDate())? DATE_FORMAT.format(loanApplicationMaster.getApprovedDate()):"-");
		CorporateApplicantRequest corporateApplicantRequest =corporateApplicantService.getCorporateApplicant(applicationId);
		UserResponse userResponse = usersClient.getEmailMobile(userId);
		LinkedHashMap<String, Object> lm = (LinkedHashMap<String, Object>)userResponse.getData();
		try {
			UsersRequest request = MultipleJSONObjectHelper.getObjectFromMap(lm,UsersRequest.class);
			map.put("mobile", request.getMobile());
			map.put("email", StringEscapeUtils.escapeXml(request.getEmail()));
		} catch (IOException e1) {
			logger.info("Error while getting registration details");
			e1.printStackTrace();
		}
		CorporateFinalInfoRequest corporateFinalInfoRequest;
		try {
			corporateFinalInfoRequest = corporateFinalInfoService.get(userId, applicationId);
			//ADMIN OFFICE ADDRESS
			if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress())){
				map.put("adminAddPremise", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getPremiseNumber()) ? printFields(corporateFinalInfoRequest.getSecondAddress().getPremiseNumber()) + "," : "");
				map.put("adminAddStreetName", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getStreetName()) ? printFields(corporateFinalInfoRequest.getSecondAddress().getStreetName()) + " " : "");
				map.put("adminAddLandmark", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getLandMark()) ? printFields(corporateFinalInfoRequest.getSecondAddress().getLandMark()) + " " : "");
				map.put("adminAddCountry", StringEscapeUtils.escapeXml(getCountryName(corporateFinalInfoRequest.getSecondAddress().getCountryId())));
				map.put("adminAddState", StringEscapeUtils.escapeXml(getStateName(corporateFinalInfoRequest.getSecondAddress().getStateId())));
				map.put("adminAddCity", StringEscapeUtils.escapeXml(getCityName(corporateFinalInfoRequest.getSecondAddress().getCityId())));
				map.put("adminAddPincode", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getPincode())?corporateFinalInfoRequest.getSecondAddress().getPincode() : "");
			}
			//REGISTERED OFFICE ADDRESS
			if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress())) {
				map.put("registeredAddPremise", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getPremiseNumber()) ? escapeXml(corporateFinalInfoRequest.getFirstAddress().getPremiseNumber()) + "," : "");
				map.put("registeredAddStreetName", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getStreetName()) ? escapeXml(corporateFinalInfoRequest.getFirstAddress().getStreetName()) + " " : "");
				map.put("registeredAddLandmark", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getLandMark()) ? escapeXml(corporateFinalInfoRequest.getFirstAddress().getLandMark()) + " " : "");
				map.put("registeredAddCountry", StringEscapeUtils.escapeXml(getCountryName(corporateFinalInfoRequest.getFirstAddress().getCountryId())));
				map.put("registeredAddState", StringEscapeUtils.escapeXml(getStateName(corporateFinalInfoRequest.getFirstAddress().getStateId())));
				map.put("registeredAddCity", StringEscapeUtils.escapeXml(getCityName(corporateFinalInfoRequest.getFirstAddress().getCityId())));
				map.put("registeredAddPincode", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getPincode())?corporateFinalInfoRequest.getFirstAddress().getPincode() : "");
			}
			map.put("corporateApplicantFinal", printFields(corporateFinalInfoRequest));
			map.put("aboutUs", StringEscapeUtils.escapeXml(corporateFinalInfoRequest.getAboutUs()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//TIMELINE DATES
		map.put("dateOfProposal", !CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getCreatedDate())? DATE_FORMAT.format(loanApplicationMaster.getCreatedDate()):"-");
		try {
			WorkflowRequest workflowRequest = new WorkflowRequest();
			workflowRequest.setApplicationId(applicationId);
			workflowRequest.setWorkflowId(WorkflowUtils.Workflow.DDR);
			workflowRequest.setUserId(userId);
			WorkflowResponse auditTrail = workflowClient.getAuditTrail(workflowRequest);
			map.put("trailDates", auditTrail.getData());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			ConnectResponse connectResponse = connectClient.getByAppStageBusinessTypeId(applicationId, ConnectStage.COMPLETE.getId(), com.capitaworld.service.loans.utils.CommonUtils.BusinessType.EXISTING_BUSINESS.getId());
			map.put("dateOfInPrincipalApproval",!CommonUtils.isObjectNullOrEmpty(connectResponse.getData())? DATE_FORMAT.format(connectResponse.getData()):"-");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		//GST DATA
		try {
			GSTR1Request gstr1Request = new GSTR1Request();
			gstr1Request.setGstin(corporateApplicantRequest.getGstIn());
			GstResponse response = gstClient.getCalculations(gstr1Request);
			map.put("gstResponse", !CommonUtils.isObjectNullOrEmpty(response.getData()) ? convertToDoubleForXml(response.getData(),null) : " ");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//ONE-FORM DATA
		try {
			//ONE-FORM DATA
			map.put("corporateApplicant", printFields(corporateApplicantRequest));
			map.put("orgName", escapeXml(corporateApplicantRequest.getOrganisationName()));
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
					e.printStackTrace();
					logger.info("Error in getting establishment year");
				}
			}
			map.put("establishmentYr",!CommonUtils.isObjectNullOrEmpty(establishMentYear) ? printFields(establishMentYear) : " ");
			//INDUSTRY DATA
			Integer industry = corporateApplicantRequest.getKeyVericalFunding().intValue();
			map.put("keyVerticalFunding", !CommonUtils.isObjectNullOrEmpty(industry) ? printFields(Industry.getById(industry).getValue()) : " ");
		}catch (Exception e) {
			e.printStackTrace();
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
					directorBackgroundDetailResponse.setTotalExperience(directorBackgroundDetailRequest.getTotalExperience().toString());
					directorBackgroundDetailResponse.setNetworth(convertValue(directorBackgroundDetailRequest.getNetworth()));
					directorBackgroundDetailResponse.setDesignation(directorBackgroundDetailRequest.getDesignation());
					directorBackgroundDetailResponse.setAppointmentDate(directorBackgroundDetailRequest.getAppointmentDate());
					directorBackgroundDetailResponse.setDin(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDin())?convertValue(directorBackgroundDetailRequest.getDin()) : "");
					directorBackgroundDetailResponse.setMobile(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getMobile())?directorBackgroundDetailRequest.getMobile(): " ");
					directorBackgroundDetailResponse.setDob(directorBackgroundDetailRequest.getDob());
					try {
						CibilRequest cibilRequest = new CibilRequest();
						cibilRequest.setPan(directorBackgroundDetailRequest.getPanNo());
						cibilRequest.setApplicationId(applicationId);
						CibilResponse cibilResponse = cibilClient.getCibilScoreByPanCard(cibilRequest);
						directorBackgroundDetailResponse.setCibilScore(!CommonUtils.isObjectNullOrEmpty(cibilResponse.getData())? Double.parseDouble(cibilResponse.getData().toString()) : 0);
					}catch(Exception e) {
						e.printStackTrace();
						logger.info("Error while getting cibil details",e);
					}
					directorBackgroundDetailResponseList.add(directorBackgroundDetailResponse);
				}
				map.put("dirBackground", !CommonUtils.isListNullOrEmpty(directorBackgroundDetailResponseList) ? printFields(directorBackgroundDetailResponseList) : " ");
	        }
				catch (Exception e) {
					e.printStackTrace();
					logger.info("Error in getting directors background details");
		    }
			
		    //FINANCIAL ARRANGEMENTS
			try {
                List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService.getFinancialArrangementDetailsList(applicationId, userId);
                List<FinancialArrangementDetailResponseString> financialArrangementsDetailResponseList = new ArrayList<>();
                for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
                	FinancialArrangementDetailResponseString financialArrangementsDetailResponse = new FinancialArrangementDetailResponseString();
     				//financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
                    financialArrangementsDetailResponse.setOutstandingAmount(convertValue(financialArrangementsDetailRequest.getOutstandingAmount()));
                    financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
                    financialArrangementsDetailResponse.setAmount(convertValue(financialArrangementsDetailRequest.getAmount()));
                    //			financialArrangementsDetailResponse.setLenderType(LenderType.getById(financialArrangementsDetailRequest.getLenderType()).getValue());
                    financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
                    financialArrangementsDetailResponse.setLoanType(financialArrangementsDetailRequest.getLoanType());
                    financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
                    //			financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
                    //financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
                    financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
                }
                	map.put("financialArrangments",!CommonUtils.isListNullOrEmpty(financialArrangementsDetailResponseList) ? printFields(financialArrangementsDetailResponseList) : " ");

            } catch (Exception e) {
                logger.error("Problem to get Data of Financial Arrangements Details {}", e);
            }
			
		try {
			PrimaryCorporateRequest primaryCorporateRequest = primaryCorporateService.get(applicationId, userId);
			map.put("loanAmt", !CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getLoanAmount()) ? convertValueWithoutDecimal(primaryCorporateRequest.getAmount()) : " ");
			map.put("loanType", !CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getProductId()) ? CommonUtils.LoanType.getType(primaryCorporateRequest.getProductId()).getName() : " ");
			
			if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getPurposeOfLoanId())) {
				map.put("purpose", StringEscapeUtils.escapeXml(PurposeOfLoan.getById(primaryCorporateRequest.getPurposeOfLoanId()).getValue()));
			}else {
				map.put("purpose", "");
			}
			
			
			if(primaryCorporateRequest.getHaveCollateralSecurity()) {
				map.put("amtOfSecurity",!CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getCollateralSecurityAmount()) ? convertValue(primaryCorporateRequest.getCollateralSecurityAmount()) : " ");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		//FINANCIALS AND NOTES TO ACCOUNTS
		try {
			PrimaryCorporateRequest primaryCorporateRequest = primaryCorporateService.get(applicationId, userId);
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			Long denominationValue = Denomination.getById(loanApplicationMaster.getDenominationId()).getDigit();
			Integer years[] = {currentYear-3, currentYear-2, currentYear-1};
			Map<Integer, Object[]> financials = new TreeMap<Integer, Object[]>(Collections.reverseOrder());
			for(Integer year : years) {
				Object[] data = calculateFinancials(userId, applicationId, null, denominationValue, year);
				financials.put(year, data);
			}
			calculateRatioAnalysis(financials);
			map.put("financials", financials);
			Map<Integer, Object[]> projectedFin = new HashMap<Integer, Object[]>(loanApplicationMaster.getTenure().intValue());
			if(primaryCorporateRequest.getProductId() == 1) {
				projectedFin.put(currentYear , calculateFinancials(userId, applicationId, null, denominationValue, currentYear));
				map.put("tenure", 1);
			}else {
				for(int i=0; i<=loanApplicationMaster.getTenure().intValue();i++) {
					projectedFin.put(currentYear + i, calculateFinancials(userId, applicationId, null, denominationValue, currentYear + i));
				}
				map.put("tenure", loanApplicationMaster.getTenure().intValue() +1 );
			}
			map.put("projectedFinancials", projectedFin);
		}catch (Exception e) {
			e.printStackTrace();
		}
		//MATCHES RESPONSE
		try {
			MatchRequest matchRequest = new MatchRequest();
			matchRequest.setApplicationId(applicationId);
			matchRequest.setProductId(productId);
			MatchDisplayResponse matchResponse= matchEngineClient.displayMatchesOfCorporate(matchRequest);
			map.put("matchesResponse", !CommonUtils.isListNullOrEmpty(matchResponse.getMatchDisplayObjectList()) ? printFields(matchResponse.getMatchDisplayObjectList()) : " ");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//PROPOSAL RESPONSE
		try {
				ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
				proposalMappingRequest.setApplicationId(applicationId);
				proposalMappingRequest.setFpProductId(productId);
				ProposalMappingResponse proposalMappingResponse= proposalDetailsClient.getActiveProposalDetails(proposalMappingRequest);
				map.put("proposalResponse", !CommonUtils.isObjectNullOrEmpty(proposalMappingResponse.getData()) ? printFields(proposalMappingResponse.getData()) : " ");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//SCORING DATA 
		try {
			ScoringRequest scoringRequest = new ScoringRequest();
			scoringRequest.setApplicationId(applicationId);
			scoringRequest.setFpProductId(productId);
			ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
			ProposalScoreResponse proposalScoreResponse = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)scoringResponse.getDataObject(),ProposalScoreResponse.class);
			map.put("proposalScoreResponse",convertToDoubleForXml(proposalScoreResponse,null));
			map.put("totalActualScore", CommonUtils.addNumbers(proposalScoreResponse.getManagementRiskScore(), proposalScoreResponse.getFinancialRiskScore(), proposalScoreResponse.getBusinessRiskScore()));
			List<Map<String, Object>> proposalScoreDetailResponseList = (List<Map<String, Object>>) scoringResponse.getDataList();
			logger.info("proposalScoreDetailResponseList Size ::::"+proposalScoreDetailResponseList.size());
			for(int i=0;i<proposalScoreDetailResponseList.size();i++)
			{
				ProposalScoreDetailResponse proposalScoreDetailResponse = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)proposalScoreDetailResponseList.get(i),ProposalScoreDetailResponse.class);
				logger.info(proposalScoreDetailResponse.getParameterName());
				switch (proposalScoreDetailResponse.getParameterName()) {
				case ScoreParameter.COMBINED_NETWORTH:
					map.put("combinedNetworthActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("combinedNetworthScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("combinedNetworthScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.CUSTOMER_ASSOCIATE_CONCERN:
					map.put("customerAssociateConcernActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("customerAssociateConcernScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("customerAssociateConcernScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.CIBIL_TRANSUNION_SCORE:
					map.put("cibilTransunionScoreActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("cibilTransunionScoreScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("cibilTransunionScoreScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.EXPERIENCE_IN_THE_BUSINESS:
					map.put("experienceInBusinessActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("experienceInBusinessScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("experienceInBusinessScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.DEBT_EQUITY_RATIO:
					map.put("debtEquityRatioActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("debtEquityRatioScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("debtEquityRatioScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.TOL_TNW:
					map.put("tolTnwActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("tolTnwScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("tolTnwScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.AVERAGE_CURRENT_RATIO:
					map.put("avgCurrentRatioActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("avgCurrentRatioScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("avgCurrentRatioScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.WORKING_CAPITAL_CYCLE:
					map.put("wcCycleActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("wcCycleScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("wcCycleScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.AVERAGE_ANNUAL_GROWTH_GROSS_CASH:
					map.put("avgAnnualgrowthGrossActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("avgAnnualgrowthGrossScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("avgAnnualgrowthGrossScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.AVERAGE_ANNUAL_GROWTH_NET_SALE:
					map.put("avgAnnualgrowthNetSaleActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("avgAnnualgrowthNetSaleScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("avgAnnualgrowthNetSaleScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.AVERAGE_EBIDTA:
					map.put("avgEbidtaActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("avgEbidtaScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("avgEbidtaScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS:
					map.put("avgAnnualGrossCashActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("avgAnnualGrossCashScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("avgAnnualGrossCashScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.AVERAGE_INTEREST_COV_RATIO:
					map.put("avgIntCovRatioActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("avgIntCovRatioScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("avgIntCovRatioScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.NO_OF_CUSTOMER:
					map.put("noOfCustomerActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("noOfCustomerScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("noOfCustomerScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.CONCENTRATION_CUSTOMER:
					map.put("concentrationCustomerActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("concentrationCustomerScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("concentrationCustomerScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.CREDIT_SUMMATION:
					map.put("creditSummationActual", StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()));
					map.put("creditSummationScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("creditSummationScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				default:
					break;
				}
			}
		}
			catch (Exception e) {
				e.printStackTrace();
				logger.info("Error while getting scoring data");
			}
		
		//PERFIOS API DATA (BANK STATEMENT ANALYSIS)
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(applicationId);
		reportRequest.setUserId(userId);
		List<Data> datas=new ArrayList<>();
		try {
			AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
			List<HashMap<String, Object>> hashMap=(List<HashMap<String, Object>>) analyzerResponse.getData();
			if(!CommonUtils.isListNullOrEmpty(hashMap))
			{
				for(HashMap<String,Object> rec:hashMap)
				{
					Data data = MultipleJSONObjectHelper.getObjectFromMap(rec, Data.class);
					datas.add(data);
					map.put("bankStatementAnalysis", datas);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Error while getting perfios data");
		}
		
		//CGTMSE DATA
		try {
			CGTMSEDataResponse cgtmseDataResponse = thirdPartyClient.getCalulation(applicationId);
			map.put("cgtmseData", cgtmseDataResponse);
			map.put("maxCgtmseCoverageAmount", convertValue(cgtmseDataResponse.getMaxCgtmseCoverageAmount()));
			if(!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getCgtmseResponse()) && !CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getCgtmseResponse().getDetails())) {
				//map.put("cgtmseBankWise", cgtmseDataResponse.getCgtmseResponse().getDetails());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//ELIGIBILITY DATA (ASSESSMENT TO LIMITS)
		try {
			EligibililityRequest eligibilityReq=new EligibililityRequest();
			eligibilityReq.setApplicationId(applicationId);
			PrimaryCorporateRequest primaryCorporateRequest = primaryCorporateService.get(applicationId, userId);
			eligibilityReq.setProductId(primaryCorporateRequest.getProductId().longValue());
			EligibilityResponse eligibilityResp= eligibilityClient.corporateLoanData(eligibilityReq);
			logger.info("********************Eligibility data**********************"+eligibilityResp.getData().toString());
			map.put("assLimits",convertToDoubleForXml(MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)eligibilityResp.getData(), CLEligibilityRequest.class), new HashMap<>()));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error while getting Eligibility data");
		}
		//MCA DATA
		/*try {
			String companyId = loanApplicationMaster.getMcaCompanyId();
			McaResponse mcaResponse = mcaClient.getCompanyDetailedData(companyId);
			map.put("mcaData", mcaResponse.getData());
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		
		//HUNTER API ANALYSIS
		/*try {
			AnalyticsResponse hunterResp =fraudAnalyticsClient.getRuleAnalysisData(applicationId);
			map.put("hunterResponse", hunterResp.getData());
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/
		
	
		/**********************************************FINAL DETAILS*****************************************************/
		
		if(isFinalView) {
			//FITCH DATA
			try {
			RatingResponse ratingResponse = (RatingResponse) irrService.calculateIrrRating(applicationId, userId).getBody().getData();
			if(!CommonUtils.isObjectNullOrEmpty(ratingResponse.getBusinessTypeId())) {
				if(BusinessType.MANUFACTURING == ratingResponse.getBusinessTypeId())
				{
					FitchOutputManu fitchOutputManu= MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)ratingResponse.getData(),FitchOutputManu.class);
					map.put("fitchResponse",convertToDoubleForXml(fitchOutputManu,null));
					map.put("financialClosure",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getFinancialClosureScore()) ? fitchOutputManu.getFinancialClosureScore() : "NA");
					map.put("intraCompany",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getIntraCompanyScore()) ? fitchOutputManu.getIntraCompanyScore() : "NA");
					map.put("statusProjectClearance",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getStatusProjectClearanceScore()) ? fitchOutputManu.getStatusProjectClearanceScore() : "NA");
					map.put("financialStrength",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getFinancialStrengthScore()) ? fitchOutputManu.getFinancialStrengthScore() : "NA");
					map.put("infrastructureAvailability",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getInfrastructureAvailabilityScore()) ? fitchOutputManu.getInfrastructureAvailabilityScore() : "NA");
					map.put("constructionContract",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getConstructionContractScore()) ? fitchOutputManu.getConstructionContractScore() : "NA");
					map.put("forexRisk",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getForexRiskScore()) ? fitchOutputManu.getForexRiskScore() : "NA");
					map.put("designTechnology",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getDesignTechnologyRiskScore()) ? fitchOutputManu.getDesignTechnologyRiskScore() : "NA");
					map.put("fitchTitle","Manufacturing");
				}
				if(BusinessType.TRADING == ratingResponse.getBusinessTypeId())
				{
					FitchOutputTrad fitchOutputTrad = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)ratingResponse.getData(),FitchOutputTrad.class);
					map.put("fitchResponse",convertToDoubleForXml(fitchOutputTrad,null));
					map.put("fitchTitle","Trading");
				}
				if(BusinessType.SERVICE == ratingResponse.getBusinessTypeId())
				{
					FitchOutputServ fitchOutputServ = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)ratingResponse.getData(),FitchOutputServ.class);
					map.put("fitchResponse",convertToDoubleForXml(fitchOutputServ,null));
					map.put("fitchTitle","Service");
				}
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			//OWNERSHIP DETAILS :- 
			try {
				List<OwnershipDetailRequest> ownershipDetailRequestsList = ownershipDetailsService.getOwnershipDetailList(applicationId, userId);
				List<OwnershipDetailResponse> ownershipDetailResponseList = new ArrayList<>();
				for (OwnershipDetailRequest ownershipDetailRequest : ownershipDetailRequestsList) {
					OwnershipDetailResponse ownershipDetailResponse = new OwnershipDetailResponse();
					ownershipDetailResponse.setRemarks(ownershipDetailRequest.getRemarks());
					ownershipDetailResponse.setStackPercentage(ownershipDetailRequest.getStackPercentage());
					ownershipDetailResponse.setShareHoldingCategory(ShareHoldingCategory.getById(ownershipDetailRequest.getShareHoldingCategoryId()).getValue());
					ownershipDetailResponseList.add(ownershipDetailResponse);
				}
				map.put("ownership", printFields(ownershipDetailResponseList));

			} catch (Exception e) {
				e.printStackTrace();
		    }
			//PROMOTOR BACKGROUND DETAILS
			try {
				List<PromotorBackgroundDetailRequest> promotorBackgroundDetailRequestList = promotorBackgroundDetailsService.getPromotorBackgroundDetailList(applicationId, userId);
				List<PromotorBackgroundDetailResponse> promotorBackgroundDetailResponseList = new ArrayList<>();
				for (PromotorBackgroundDetailRequest promotorBackgroundDetailRequest : promotorBackgroundDetailRequestList) {
					PromotorBackgroundDetailResponse promotorBackgroundDetailResponse = new PromotorBackgroundDetailResponse();
					//promotorBackgroundDetailResponse.setAchievements(promotorBackgroundDetailRequest.getAchivements());
					String promotorName = "";
					if (promotorBackgroundDetailRequest.getSalutationId() != null){
						promotorName = Title.getById(promotorBackgroundDetailRequest.getSalutationId()).getValue();
					}
					promotorName += promotorBackgroundDetailRequest.getPromotorsName();
					promotorBackgroundDetailResponse.setPromotorsName(promotorName);
					promotorBackgroundDetailResponse.setPanNo(promotorBackgroundDetailRequest.getPanNo().toUpperCase());
					promotorBackgroundDetailResponse.setAddress(promotorBackgroundDetailRequest.getAddress());
					promotorBackgroundDetailResponse.setGender((promotorBackgroundDetailRequest.getGender() != null ? Gender.getById(promotorBackgroundDetailRequest.getGender()).getValue() : " " ));
					promotorBackgroundDetailResponse.setDin(!CommonUtils.isObjectNullOrEmpty(promotorBackgroundDetailRequest.getDin()) ? convertValue(promotorBackgroundDetailRequest.getDin()) : " ");
					promotorBackgroundDetailResponse.setTotalExperience(convertValue(promotorBackgroundDetailRequest.getTotalExperience()));
					promotorBackgroundDetailResponse.setNetworth(convertValue(promotorBackgroundDetailRequest.getNetworth()));
					promotorBackgroundDetailResponse.setAppointmentDate(promotorBackgroundDetailRequest.getAppointmentDate() != null ? DATE_FORMAT.format(promotorBackgroundDetailRequest.getAppointmentDate()) : null);
					promotorBackgroundDetailResponse.setRelationshipType((promotorBackgroundDetailRequest.getRelationshipType() != null ? DirectorRelationshipType.getById(promotorBackgroundDetailRequest.getRelationshipType()).getValue() : " " ));
					promotorBackgroundDetailResponse.setDesignation(promotorBackgroundDetailRequest.getDesignation());
					promotorBackgroundDetailResponse.setMobile(promotorBackgroundDetailRequest.getMobile());
					promotorBackgroundDetailResponseList.add(promotorBackgroundDetailResponse);
				}
				map.put("promotorsbckgrnd", printFields(promotorBackgroundDetailResponseList));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//ASSOCIATE ENTITY
			try {
				map.put("associatedConcerns",printFields(associatedConcernDetailService.getAssociatedConcernsDetailList(applicationId, userId)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				map.put("proposedProduct",printFields(proposedProductDetailsService.getProposedProductDetailList(applicationId, userId)));
				map.put("existingProduct",printFields(existingProductDetailsService.getExistingProductDetailList(applicationId, userId)));
				map.put("achievementDetails",printFields(achievmentDetailsService.getAchievementDetailList(applicationId, userId)));
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			//SHARE PRICE
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.getByApplicationAndUserId(userId, applicationId);
			map.put("sharePriceFace", convertValueWithoutDecimal(corporateApplicantDetail.getSharePriceFace()));
			map.put("sharePriceMarket", convertValueWithoutDecimal(corporateApplicantDetail.getSharePriceMarket()));
			
			//DETAILS OF GUARANTER
			try {
					map.put("guaDetails", printFields(guarantorsCorporateDetailService.getGuarantorsCorporateDetailList(applicationId, userId)));
			} catch (Exception e) {
					logger.error("Problem to get Data of Details of Guarantor {}", e);
			}

		    //MONTHLY TURNOVER
			try {
				map.put("monthlyTurnOver", printFields(monthlyTurnoverDetailService.getMonthlyTurnoverDetailList(applicationId, userId)));
			} catch (Exception e) {
				logger.error("Problem to get Data of Monthly Turnover {}", e);
			}
			
			//COST ESTIMATES
			try {
				List<TotalCostOfProjectRequest> costOfProjectsList = costOfProjectService.getCostOfProjectDetailList(applicationId, userId);
				List<TotalCostOfProjectResponse> costOfProjectResponses = new ArrayList<TotalCostOfProjectResponse>();
				for (TotalCostOfProjectRequest costOfProjectRequest : costOfProjectsList) {
				TotalCostOfProjectResponse costOfProjectResponse = new TotalCostOfProjectResponse();
				BeanUtils.copyProperties(costOfProjectRequest, costOfProjectResponse);
					if (costOfProjectRequest.getParticularsId() != null)
						costOfProjectResponse.setParticulars(Particular.getById(Integer.parseInt(costOfProjectRequest.getParticularsId().toString())).getValue());
					    costOfProjectResponses.add(costOfProjectResponse);
				}
				map.put("costEstimate", printFields(costOfProjectResponses));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logger.error("Problem to get Data of Total cost of project{}", e1);
			}
			
			//MEANS OF FINANCE
			try {
				List<FinanceMeansDetailRequest> financeMeansDetailRequestsList = financeMeansDetailsService.getMeansOfFinanceList(applicationId, userId);
				List<FinanceMeansDetailResponse> financeMeansDetailResponsesList = new ArrayList<FinanceMeansDetailResponse>();
				for (FinanceMeansDetailRequest financeMeansDetailRequest : financeMeansDetailRequestsList) {
					FinanceMeansDetailResponse detailResponse = new FinanceMeansDetailResponse();
					BeanUtils.copyProperties(financeMeansDetailRequest, detailResponse);
					if (financeMeansDetailRequest.getFinanceMeansCategoryId() != null)
						detailResponse.setFinanceMeansCategory(FinanceCategory.getById(Integer.parseInt(financeMeansDetailRequest.getFinanceMeansCategoryId().toString())).getValue());
					financeMeansDetailResponsesList.add(detailResponse);
				}
				map.put("meansOfFinance", printFields(financeMeansDetailResponsesList));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logger.error("Problem to get Data of Finance Means Details {}", e1);
			}
			
			//COLLATERAL SECURITY			
			try {
				map.put("collateralSecurity", printFields(securityCorporateDetailsService.getsecurityCorporateDetailsList(applicationId, userId)));
			} catch (Exception e) {
				logger.error("Problem to get Data of Security Details {}", e);
			}
			
		}
		logger.info("============CAM data=========================="+map.toString());
		return map;
	}
	
	//FINANCIALS & NOTES TO ACCOUNTS
	public Object[] calculateFinancials(Long userId, Long applicationId, String industry, Long denomination, Integer year) throws Exception {
		FinancialInputRequestDbl financialInputRequestDbl = new FinancialInputRequestDbl();
		FinancialInputRequestString financialInputRequestString = new FinancialInputRequestString();
		OperatingStatementDetailsString osDetailsString = new OperatingStatementDetailsString();
		LiabilitiesDetailsString liabilitiesDetailsString = new LiabilitiesDetailsString();
		AssetDetailsString assetDetailsString = new AssetDetailsString();
		CorporateFinalInfoRequest  corporateFinalInfoRequest = corporateFinalInfoService.get(userId ,applicationId);
        //SET SHARE PHASE VALUE
		Double shareFaceVal=1.00;
		CorporateApplicantDetail corporateApplicantDetail=corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
		if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
			if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getSharePriceFace())) {
				shareFaceVal=corporateApplicantDetail.getSharePriceFace();
				financialInputRequestDbl.setShareFaceValue(shareFaceVal);
			}else{
				financialInputRequestDbl.setShareFaceValue(1.00);
			}
		}else{
			financialInputRequestDbl.setShareFaceValue(1.00);
		}

		financialInputRequestDbl.setNoOfMonth(12.0);
		
		/************************************************** OPERATING STATEMENT ***************************************************/
		OperatingStatementDetails osDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, year+"");
		if(CommonUtils.isObjectNullOrEmpty(osDetails)) {
			osDetails = new OperatingStatementDetails();
		}
		
		osDetailsString.setDomesticSales(convertValue(osDetails.getDomesticSales()));
		osDetailsString.setExportSales(convertValue(osDetails.getExportSales()));
		osDetailsString.setGrossSalesTotal(convertValue(CommonUtils.addNumbers(osDetails.getDomesticSales(),osDetails.getExportSales())));
		financialInputRequestDbl.setGrossSales((osDetails.getDomesticSales()+osDetails.getExportSales()) * denomination);
		financialInputRequestString.setGrossSales(convertValue(financialInputRequestDbl.getGrossSales()));
		
		osDetailsString.setLessExciseDuty(convertValue(osDetails.getLessExciseDuty()));
		osDetailsString.setDeductOtherItems(convertValue(osDetails.getDeductOtherItems()));
		osDetailsString.setExciseDutyTotal(convertValue(CommonUtils.addNumbers(osDetails.getLessExciseDuty(),osDetails.getDeductOtherItems())));
		financialInputRequestDbl.setLessExciseDuity((osDetails.getLessExciseDuty()+osDetails.getDeductOtherItems()) * denomination);
		financialInputRequestString.setLessExciseDuity(convertValue(financialInputRequestDbl.getLessExciseDuity()));
		
		osDetailsString.setAddOperatingStock(convertValue(osDetails.getAddOperatingStock()));
		osDetailsString.setDeductStockInProcess(convertValue(osDetails.getDeductStockInProcess()));
		osDetailsString.setAddOperatingStockFg(convertValue(osDetails.getAddOperatingStockFg()));
		osDetailsString.setDeductClStockFg(convertValue(osDetails.getDeductClStockFg()));
		double subFy = CommonUtils.substractNumbers(osDetails.getAddOperatingStockFg(), osDetails.getDeductClStockFg());
		double sumFy = CommonUtils.addNumbers(subFy,osDetails.getAddOperatingStockFg());
		osDetailsString.setIncreaseDecreaseTotal(convertValue(CommonUtils.substractNumbers(sumFy, osDetails.getDeductClStockFg())));
		financialInputRequestDbl.setIncreaseDecreaseStock(((osDetails.getAddOperatingStock()-osDetails.getDeductStockInProcess()) + (osDetails.getAddOperatingStockFg()-osDetails.getDeductClStockFg())) * denomination);
		financialInputRequestString.setIncreaseDecreaseStock(convertValue(financialInputRequestDbl.getIncreaseDecreaseStock()));
		
		osDetailsString.setRawMaterials(convertValue(osDetails.getRawMaterials()));
		osDetailsString.setOtherSpares(convertValue(osDetails.getOtherSpares()));
		osDetailsString.setRawMaterialsConsumedTotal(convertValue(CommonUtils.addNumbers(osDetails.getRawMaterials(), osDetails.getOtherSpares())));
		financialInputRequestDbl.setRawMaterialConsumed((osDetails.getRawMaterials()+osDetails.getOtherSpares()) * denomination);
		financialInputRequestString.setRawMaterialConsumed(convertValue(financialInputRequestDbl.getRawMaterialConsumed()));
		financialInputRequestDbl.setPowerAndFuelCost(osDetails.getPowerAndFuel()  * denomination);
		financialInputRequestString.setPowerAndFuelCost(convertValue(financialInputRequestDbl.getPowerAndFuelCost()));
		financialInputRequestDbl.setEmployeeCost(osDetails.getDirectLabour() * denomination);
		financialInputRequestString.setEmployeeCost(convertValue(financialInputRequestDbl.getEmployeeCost()));
		financialInputRequestDbl.setGeneralAndAdminExpe(osDetails.getSellingGenlAdmnExpenses() * denomination);
		financialInputRequestString.setGeneralAndAdminExpe(convertValue(financialInputRequestDbl.getGeneralAndAdminExpe()));
		financialInputRequestDbl.setSellingAndDistriExpe(osDetails.getSellingAndDistributionExpenses() * denomination);
		financialInputRequestString.setSellingAndDistriExpe(convertValue(financialInputRequestDbl.getSellingAndDistriExpe()));
		financialInputRequestDbl.setLessExpeCapita(osDetails.getExpensesAmortised() * denomination);
		financialInputRequestString.setLessExpeCapita(convertValue(financialInputRequestDbl.getLessExpeCapita()));
		financialInputRequestDbl.setMiscelExpe(osDetails.getOtherMfgExpenses() * denomination);
		financialInputRequestString.setMiscelExpe(convertValue(financialInputRequestDbl.getMiscelExpe()));
		financialInputRequestDbl.setOtherIncome(osDetails.getAddOtherRevenueIncome() * denomination);
		financialInputRequestString.setOtherIncome(convertValue(financialInputRequestDbl.getOtherIncome()));
		financialInputRequestDbl.setInterest(osDetails.getInterest() * denomination);
		financialInputRequestString.setInterest(convertValue(financialInputRequestDbl.getInterest()));
		financialInputRequestDbl.setDepriciation(osDetails.getDepreciation() * denomination);
		financialInputRequestString.setDepriciation(convertValue(financialInputRequestDbl.getDepriciation()));
		financialInputRequestDbl.setExceptionalIncome(osDetails.getNetofNonOpIncomeOrExpenses() * denomination);
		financialInputRequestString.setExceptionalIncome(convertValue(financialInputRequestDbl.getExceptionalIncome()));
		
		osDetailsString.setOtherIncomeNeedTocCheckOp(convertValue(osDetails.getOtherIncomeNeedTocCheckOp()));
		financialInputRequestDbl.setOtherIncomeNeedTocCheckOp(osDetails.getOtherIncomeNeedTocCheckOp() * denomination);
		financialInputRequestString.setOtherIncomeNeedTocCheckOp(convertValue(financialInputRequestDbl.getOtherIncomeNeedTocCheckOp()));
		
		osDetailsString.setProvisionForTaxes(convertValue(osDetails.getProvisionForTaxes()));
		osDetailsString.setProvisionForDeferredTax(convertValue(osDetails.getProvisionForDeferredTax()));
		osDetailsString.setProvisionForTaxTotal(convertValue(CommonUtils.addNumbers(osDetails.getProvisionForDeferredTax(), osDetails.getProvisionForTaxes())));
		financialInputRequestDbl.setProvisionForTax((osDetails.getProvisionForTaxes() + osDetails.getProvisionForDeferredTax()) * denomination);
		financialInputRequestString.setProvisionForTax(convertValue(financialInputRequestDbl.getProvisionForTax()));
		financialInputRequestDbl.setDividendPayOut(osDetails.getEquityDeividendPaidAmt() * denomination);
		financialInputRequestString.setDividendPayOut(convertValue(financialInputRequestDbl.getDividendPayOut()));

		/************************************************ LIABILITIES DETAIL ***************************************************/
		LiabilitiesDetails liabilitiesDetails = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, year+"");
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)) {
			liabilitiesDetails = new LiabilitiesDetails();
		}
		liabilitiesDetailsString.setOrdinarySharesCapital(convertValue(liabilitiesDetails.getOrdinarySharesCapital()));
		liabilitiesDetailsString.setPreferencesShares(convertValue(liabilitiesDetails.getPreferencesShares()));
		liabilitiesDetailsString.setShareCapitalTotal(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getOrdinarySharesCapital(), liabilitiesDetails.getPreferencesShares())));
		financialInputRequestDbl.setShareCapital((liabilitiesDetails.getPreferencesShares() + liabilitiesDetails.getOrdinarySharesCapital()) * denomination);
		financialInputRequestString.setShareCapital(convertValue(financialInputRequestDbl.getShareCapital()));
		financialInputRequestDbl.setShareWarrantOutstandings((liabilitiesDetails.getShareWarrentsOutstanding()) * denomination);
		financialInputRequestString.setShareWarrantOutstandings(convertValue(financialInputRequestDbl.getShareWarrantOutstandings()));
		financialInputRequestDbl.setRevalationReserve((liabilitiesDetails.getRevaluationReservse()) * denomination);
		financialInputRequestString.setRevalationReserve(convertValue(financialInputRequestDbl.getRevalationReserve()));
		
		liabilitiesDetailsString.setGeneralReserve(convertValue(liabilitiesDetails.getGeneralReserve()));
		liabilitiesDetailsString.setOtherReservse(convertValue(liabilitiesDetails.getOtherReservse()));
		liabilitiesDetailsString.setSurplusOrDeficit(convertValue(liabilitiesDetails.getSurplusOrDeficit()));
		liabilitiesDetailsString.setOthers(convertValue(liabilitiesDetails.getOthers()));
		liabilitiesDetailsString.setOtherReservesTotal(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getGeneralReserve(),liabilitiesDetails.getOtherReservse(), liabilitiesDetails.getSurplusOrDeficit(),liabilitiesDetails.getOthers())));
		financialInputRequestDbl.setOtherReserveAndSurplus((liabilitiesDetails.getGeneralReserve() + liabilitiesDetails.getOtherReservse() + liabilitiesDetails.getSurplusOrDeficit() + liabilitiesDetails.getOthers()) * denomination);
		financialInputRequestString.setOtherReserveAndSurplus(convertValue(financialInputRequestDbl.getOtherReserveAndSurplus()));
		financialInputRequestDbl.setMinorityInterest(liabilitiesDetails.getMinorityInterest() * denomination);
		financialInputRequestString.setMinorityInterest(convertValue(financialInputRequestDbl.getMinorityInterest()));
		financialInputRequestDbl.setSecuredLoans(liabilitiesDetails.getTermLiabilitiesSecured() * denomination);
		financialInputRequestString.setSecuredLoans(convertValue(financialInputRequestDbl.getSecuredLoans()));
		financialInputRequestDbl.setUnsecuredLoansPromoters(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters() * denomination);
		financialInputRequestString.setUnsecuredLoansPromoters(convertValue(financialInputRequestDbl.getUnsecuredLoansPromoters()));
		
		liabilitiesDetailsString.setTermLiabilitiesUnsecured(convertValue(liabilitiesDetails.getTermLiabilitiesUnsecured()));
		liabilitiesDetailsString.setOtherNclUnsecuredLoansFromOther(convertValue(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther()));
		liabilitiesDetailsString.setUnsecuredLoansOthersTotal(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getTermLiabilitiesUnsecured(), liabilitiesDetails.getOtherNclUnsecuredLoansFromOther())));
		financialInputRequestDbl.setUnsecuredLoansOthers((liabilitiesDetails.getOtherNclUnsecuredLoansFromOther() + liabilitiesDetails.getTermLiabilitiesUnsecured()) * denomination);
		financialInputRequestString.setUnsecuredLoansOthers(convertValue(financialInputRequestDbl.getUnsecuredLoansOthers()));

		liabilitiesDetailsString.setSubTotalA(convertValue(liabilitiesDetails.getSubTotalA()));
		liabilitiesDetailsString.setShortTermBorrowingFromOthers(convertValue(liabilitiesDetails.getShortTermBorrowingFromOthers()));
		liabilitiesDetailsString.setOtherBorrowingsTotal(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getSubTotalA(), liabilitiesDetails.getShortTermBorrowingFromOthers())));
		financialInputRequestDbl.setOtherBorrowing((liabilitiesDetails.getSubTotalA() + liabilitiesDetails.getShortTermBorrowingFromOthers()) * denomination);
		financialInputRequestString.setOtherBorrowing(convertValue(financialInputRequestDbl.getOtherBorrowing()));
		financialInputRequestDbl.setDeferredTaxLiablities(liabilitiesDetails.getDeferredTaxLiability() * denomination);
		financialInputRequestString.setDeferredTaxLiablities(convertValue(financialInputRequestDbl.getDeferredTaxLiablities()));

		liabilitiesDetailsString.setOtherNcl(convertValue(liabilitiesDetails.getOtherNcl()));
		liabilitiesDetailsString.setDeferredPaymentsCredits(convertValue(liabilitiesDetails.getDeferredPaymentsCredits()));
		liabilitiesDetailsString.setTermDeposits(convertValue(liabilitiesDetails.getTermDeposits()));
		liabilitiesDetailsString.setDebentures(convertValue(liabilitiesDetails.getDebentures()));
		liabilitiesDetailsString.setOtherTermLiabilies(convertValue(liabilitiesDetails.getOtherTermLiabilies()));
		liabilitiesDetailsString.setOtherLongTermLiabilitiesTotal(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getOtherNcl(),liabilitiesDetails.getDeferredPaymentsCredits(),liabilitiesDetails.getTermDeposits(), liabilitiesDetails.getDebentures(), liabilitiesDetails.getOtherTermLiabilies())));
		financialInputRequestDbl.setOtherLongTermLiablities((liabilitiesDetails.getOtherNclOthers() + liabilitiesDetails.getDeferredPaymentsCredits() + liabilitiesDetails.getTermDeposits() + liabilitiesDetails.getDebentures() + liabilitiesDetails.getOtherTermLiabilies()) * denomination);
		financialInputRequestString.setOtherLongTermLiablities(convertValue(financialInputRequestDbl.getOtherLongTermLiablities()));
		financialInputRequestDbl.setLongTermProvision(liabilitiesDetails.getOtherNclLongTermProvisions() * denomination);
		financialInputRequestString.setLongTermProvision(convertValue(financialInputRequestDbl.getLongTermProvision()));
		financialInputRequestDbl.setTradePayables(liabilitiesDetails.getSundryCreditors() * denomination);
		financialInputRequestString.setTradePayables(convertValue(financialInputRequestDbl.getTradePayables()));
		
		liabilitiesDetailsString.setAdvancePaymentsFromCustomers(convertValue(liabilitiesDetails.getAdvancePaymentsFromCustomers()));
		liabilitiesDetailsString.setDividendPayable(convertValue(liabilitiesDetails.getDividendPayable()));
		liabilitiesDetailsString.setOtherStatutoryLiability(convertValue(liabilitiesDetails.getOtherStatutoryLiability()));
		liabilitiesDetailsString.setDepositsOrInstalmentsOfTermLoans(convertValue(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()));
		liabilitiesDetailsString.setOtherCurrentLiability(convertValue(liabilitiesDetails.getOtherCurrentLiability()));
		liabilitiesDetailsString.setOtherCurrentLiabilitiesTotal(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getAdvancePaymentsFromCustomers(),liabilitiesDetails.getDividendPayable(),liabilitiesDetails.getOtherStatutoryLiability(),liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans(),liabilitiesDetails.getOtherCurrentLiability())));
		financialInputRequestDbl.setOtherCurruntLiablities((liabilitiesDetails.getAdvancePaymentsFromCustomers() + liabilitiesDetails.getDividendPayable() + liabilitiesDetails.getOtherStatutoryLiability() + liabilitiesDetails.getOtherCurrentLiability() + liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()) * denomination);
		financialInputRequestString.setOtherCurruntLiablities(convertValue(financialInputRequestDbl.getOtherCurruntLiablities()));
		financialInputRequestDbl.setShortTermProvision(liabilitiesDetails.getProvisionalForTaxation() * denomination);
		financialInputRequestString.setShortTermProvision(convertValue(financialInputRequestDbl.getShortTermProvision()));
		
		liabilitiesDetailsString.setOtherIncomeNeedTocCheckLia(convertValue(liabilitiesDetails.getOtherIncomeNeedTocCheckLia()));
		financialInputRequestDbl.setOtherIncomeNeedTocCheckLia(liabilitiesDetails.getOtherIncomeNeedTocCheckLia() * denomination);
		financialInputRequestString.setOtherIncomeNeedTocCheckLia(convertValue(financialInputRequestDbl.getOtherIncomeNeedTocCheckLia()));
		
		/************************************************ ASSETS DETAIL ***************************************************/
		AssetsDetails assetsDetails = assetsDetailsRepository.getAssetsDetails(applicationId, year+"");
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails)) {
			assetsDetails = new AssetsDetails();
		}
		
		financialInputRequestDbl.setGrossBlock(assetsDetails.getGrossBlock() * denomination);
		financialInputRequestString.setGrossBlock(convertValue(financialInputRequestDbl.getGrossBlock()));
		financialInputRequestDbl.setLessAccumulatedDepre(assetsDetails.getDepreciationToDate() * denomination);
		financialInputRequestString.setLessAccumulatedDepre(convertValue(financialInputRequestDbl.getLessAccumulatedDepre()));
		financialInputRequestDbl.setImpairmentofAsset(assetsDetails.getImpairmentAsset() * denomination);
		financialInputRequestString.setImpairmentofAsset(convertValue(financialInputRequestDbl.getImpairmentofAsset()));
		financialInputRequestDbl.setCapitalWorkInProgress(assetsDetails.getOtherNcaOtherCapitalWorkInprogress() * denomination);
		financialInputRequestString.setCapitalWorkInProgress(convertValue(financialInputRequestDbl.getCapitalWorkInProgress()));
		financialInputRequestDbl.setIntengibleAssets(assetsDetails.getIntangibleAssets() * denomination);
		financialInputRequestString.setIntengibleAssets(convertValue(financialInputRequestDbl.getIntengibleAssets()));
		financialInputRequestDbl.setPreOperativeExpe(assetsDetails.getOthersPreOperativeExpensesPending() * denomination);
		financialInputRequestString.setPreOperativeExpe(convertValue(financialInputRequestDbl.getPreOperativeExpe()));
		financialInputRequestDbl.setAssetInTransit(assetsDetails.getOthersAssetsInTransit() * denomination);
		financialInputRequestString.setAssetInTransit(convertValue(financialInputRequestDbl.getAssetInTransit()));
		financialInputRequestDbl.setInvestmentInSubsidiaries(assetsDetails.getInvestmentsInSubsidiary() * denomination);
		financialInputRequestString.setInvestmentInSubsidiaries(convertValue(financialInputRequestDbl.getInvestmentInSubsidiaries()));
		
		assetDetailsString.setInvestmentsOrBookDebtsString(convertValue(assetsDetails.getInvestmentsOrBookDebts()));
		assetDetailsString.setDeferredReceviables(convertValue(assetsDetails.getDeferredReceviables()));
		assetDetailsString.setOthers(convertValue(assetsDetails.getOthers()));
		assetDetailsString.setOtherInvestmentsTotal(convertValue(CommonUtils.addNumbers(assetsDetails.getInvestmentsOrBookDebts(), assetsDetails.getDeferredReceviables(),assetsDetails.getOthers())));
		financialInputRequestDbl.setOtherInvestment((assetsDetails.getOthersOther() + assetsDetails.getDeferredReceviables() + assetsDetails.getOthers()) * denomination);
		financialInputRequestString.setOtherInvestment(convertValue(financialInputRequestDbl.getOtherInvestment()));
		
		financialInputRequestDbl.setLongTermLoansAndAdva(assetsDetails.getAdvanceToSuppliersCapitalGoods() * denomination);
		financialInputRequestString.setLongTermLoansAndAdva(convertValue(financialInputRequestDbl.getLongTermLoansAndAdva()));
		
		assetDetailsString.setNonConsumableStoreAndSpares(convertValue(assetsDetails.getNonConsumableStoreAndSpares()));
		assetDetailsString.setOtherNonCurrentAssets(convertValue(assetsDetails.getOtherNonCurrentAssets()));
		assetDetailsString.setOtherNonCurrentAssestsTotal(convertValue(CommonUtils.addNumbers(assetsDetails.getNonConsumableStoreAndSpares(),assetsDetails.getOtherNonCurrentAssets())));
		financialInputRequestDbl.setOtheNonCurruntAsset((assetsDetails.getNonConsumableStoreAndSpares() + assetsDetails.getOtherNonCurrentAssets()) * denomination);
		financialInputRequestString.setOtheNonCurruntAsset(convertValue(financialInputRequestDbl.getOtheNonCurruntAsset()));
		financialInputRequestDbl.setInventories(assetsDetails.getInventory() * denomination);
		financialInputRequestString.setInventories(convertValue(financialInputRequestDbl.getInventories()));
		
		assetDetailsString.setReceivableOtherThanDefferred(convertValue(assetsDetails.getReceivableOtherThanDefferred()));
		assetDetailsString.setExportReceivables(convertValue(assetsDetails.getExportReceivables()));
		assetDetailsString.setSundryDebtorsTotal(convertValue(CommonUtils.addNumbers(assetsDetails.getReceivableOtherThanDefferred(),assetsDetails.getExportReceivables())));
		financialInputRequestDbl.setSundryDebtors((assetsDetails.getReceivableOtherThanDefferred() + assetsDetails.getExportReceivables()) * denomination);
		financialInputRequestString.setSundryDebtors(convertValue(financialInputRequestDbl.getSundryDebtors()));
		financialInputRequestDbl.setCashAndBank(assetsDetails.getCashAndBankBalance() * denomination);
		financialInputRequestString.setCashAndBank(convertValue(financialInputRequestDbl.getCashAndBank()));
		
		assetDetailsString.setInvestments(convertValue(assetsDetails.getInvestments()));
		assetDetailsString.setInstalmentsDeferred(convertValue(assetsDetails.getInstalmentsDeferred()));
		assetDetailsString.setOtherCurrentAssets(convertValue(assetsDetails.getOtherCurrentAssets()));
		assetDetailsString.setOtherCurrentAssetsTotal(convertValue(CommonUtils.addNumbers(assetsDetails.getInvestments(),assetsDetails.getInstalmentsDeferred(), assetsDetails.getOtherCurrentAssets())));
		financialInputRequestDbl.setOtherCurruntAsset((assetsDetails.getInvestments() + assetsDetails.getInstalmentsDeferred() + assetsDetails.getOtherCurrentAssets()) * denomination);
		financialInputRequestString.setOtherCurruntAsset(convertValue(financialInputRequestDbl.getOtherCurruntAsset()));
		
		assetDetailsString.setAdvanceToSupplierRawMaterials(convertValue(assetsDetails.getAdvanceToSupplierRawMaterials()));
		assetDetailsString.setAdvancePaymentTaxes(convertValue(assetsDetails.getAdvancePaymentTaxes()));
		assetDetailsString.setShortTermLoansAndAdvancesTotal(convertValue(CommonUtils.addNumbers(assetsDetails.getAdvanceToSupplierRawMaterials(),assetsDetails.getAdvancePaymentTaxes() )));
		financialInputRequestDbl.setShortTermLoansAdvances((assetsDetails.getAdvanceToSupplierRawMaterials() + assetsDetails.getAdvancePaymentTaxes()) * denomination);
		financialInputRequestString.setShortTermLoansAdvances(convertValue(financialInputRequestDbl.getShortTermLoansAdvances()));
		if(corporateFinalInfoRequest == null) {
			financialInputRequestDbl.setContingentLiablities(null);
			financialInputRequestString.setContingentLiablities(null);
		}
		else {
			financialInputRequestDbl.setContingentLiablities(CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getContLiabilityFyAmt()) ? 0.0 : (corporateFinalInfoRequest.getContLiabilityFyAmt()* denomination));
			financialInputRequestString.setContingentLiablities(convertValue(financialInputRequestDbl.getContingentLiablities()));
		}
		
		assetDetailsString.setOtherIncomeNeedTocCheckAsset(convertValue(assetsDetails.getOtherIncomeNeedTocCheckAsset()));
		financialInputRequestDbl.setOtherIncomeNeedTocCheckAsset(assetsDetails.getOtherIncomeNeedTocCheckAsset() * denomination);
		financialInputRequestString.setOtherIncomeNeedTocCheckAsset(convertValue(financialInputRequestDbl.getOtherIncomeNeedTocCheckAsset()));
		
		/************************************************** OTHER CALCULATIONS *******************************************************/ 
		//Profit & Loss Statement
        financialInputRequestDbl.setNetSale(CommonUtils.substractNumbers(financialInputRequestDbl.getGrossSales(), financialInputRequestDbl.getLessExciseDuity()));
      	financialInputRequestString.setNetSale(convertValue(financialInputRequestDbl.getNetSale()));
		financialInputRequestDbl.setTotalExpenditure(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequestDbl.getIncreaseDecreaseStock(),financialInputRequestDbl.getRawMaterialConsumed(),financialInputRequestDbl.getPowerAndFuelCost(),financialInputRequestDbl.getEmployeeCost(), financialInputRequestDbl.getGeneralAndAdminExpe(),financialInputRequestDbl.getSellingAndDistriExpe(),financialInputRequestDbl.getMiscelExpe()), financialInputRequestDbl.getLessExpeCapita()));
		financialInputRequestString.setTotalExpenditure(convertValue(financialInputRequestDbl.getTotalExpenditure()));
		financialInputRequestDbl.setOperatingProfitExclOi(CommonUtils.substractNumbers(financialInputRequestDbl.getNetSale(),financialInputRequestDbl.getTotalExpenditure()));
		financialInputRequestString.setOperatingProfitExclOi(convertValue(financialInputRequestDbl.getOperatingProfitExclOi()));
		financialInputRequestDbl.setOperatingProfitEbitadOi(CommonUtils.addNumbers(financialInputRequestDbl.getOperatingProfitExclOi(),financialInputRequestDbl.getOtherIncome()));
		financialInputRequestString.setOperatingProfitEbitadOi(convertValue(financialInputRequestDbl.getOperatingProfitEbitadOi()));
		financialInputRequestDbl.setPbdt(CommonUtils.substractNumbers(financialInputRequestDbl.getOperatingProfitEbitadOi(), financialInputRequestDbl.getInterest()));
		financialInputRequestString.setPbdt(convertValue(financialInputRequestDbl.getPbdt()));
		financialInputRequestDbl.setProfitBeforeTaxation(CommonUtils.substractNumbers(financialInputRequestDbl.getPbdt(), financialInputRequestDbl.getDepriciation()));
		financialInputRequestString.setProfitBeforeTaxation(convertValue(financialInputRequestDbl.getProfitBeforeTaxation()));
		financialInputRequestDbl.setProfitBeforeTax(CommonUtils.addNumbers(financialInputRequestDbl.getProfitBeforeTaxation(), financialInputRequestDbl.getExceptionalIncome()));
		financialInputRequestString.setProfitBeforeTax(convertValue(financialInputRequestDbl.getProfitBeforeTax()));
		financialInputRequestDbl.setProfitAfterTax(CommonUtils.substractNumbers(financialInputRequestDbl.getProfitBeforeTax(), financialInputRequestDbl.getProvisionForTax()) + financialInputRequestDbl.getOtherIncomeNeedTocCheckOp());
		financialInputRequestString.setProfitAfterTax(convertValue(financialInputRequestDbl.getProfitAfterTax()));
		if(financialInputRequestDbl.getDividendPayOut() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getDividendPayOut()) || financialInputRequestDbl.getShareFaceValue() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getShareFaceValue()) || financialInputRequestDbl.getShareCapital() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getShareCapital()))
			financialInputRequestString.setEquityDividend("0.0");
		else
			financialInputRequestString.setEquityDividend(convertValue((financialInputRequestDbl.getDividendPayOut()*financialInputRequestDbl.getShareFaceValue()/financialInputRequestDbl.getShareCapital())));
		
		if(financialInputRequestDbl.getShareFaceValue() !=0 && financialInputRequestDbl.getShareCapital() !=0) {
			double total = financialInputRequestDbl.getShareFaceValue()/financialInputRequestDbl.getShareCapital();
			if(!CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getProfitAfterTax()) && financialInputRequestDbl.getProfitAfterTax() !=0) {
				financialInputRequestString.setEarningPerShare(convertValue(financialInputRequestDbl.getProfitAfterTax() * total));
			}
		}
		
		//Balance Sheet -Equities and Liabilities
		
		financialInputRequestDbl.setShareHolderFunds(CommonUtils.addNumbers(financialInputRequestDbl.getShareCapital(),financialInputRequestDbl.getShareWarrantOutstandings(),financialInputRequestDbl.getRevalationReserve(),financialInputRequestDbl.getOtherReserveAndSurplus()));
		financialInputRequestString.setShareHolderFunds(convertValue(financialInputRequestDbl.getShareHolderFunds()));
		financialInputRequestDbl.setTotalNonCurruntLiablities(CommonUtils.addNumbers(financialInputRequestDbl.getMinorityInterest(), financialInputRequestDbl.getSecuredLoans(), financialInputRequestDbl.getUnsecuredLoansOthers(),financialInputRequestDbl.getUnsecuredLoansPromoters(),financialInputRequestDbl.getDeferredTaxLiablities(),financialInputRequestDbl.getOtherLongTermLiablities(),financialInputRequestDbl.getOtherBorrowing(),financialInputRequestDbl.getLongTermProvision()));
		financialInputRequestString.setTotalNonCurruntLiablities(convertValue(financialInputRequestDbl.getTotalNonCurruntLiablities()));
		financialInputRequestDbl.setTotalCurruntLiablities(CommonUtils.addNumbers(financialInputRequestDbl.getTradePayables(), financialInputRequestDbl.getOtherCurruntLiablities(), financialInputRequestDbl.getShortTermProvision()));
		financialInputRequestString.setTotalCurruntLiablities(convertValue(financialInputRequestDbl.getTotalCurruntLiablities()));
		financialInputRequestDbl.setTotalLiablities(CommonUtils.addNumbers(financialInputRequestDbl.getShareHolderFunds(), financialInputRequestDbl.getTotalNonCurruntLiablities(), financialInputRequestDbl.getTotalCurruntLiablities(), financialInputRequestDbl.getOtherIncomeNeedTocCheckLia()));
		financialInputRequestString.setTotalLiablities(convertValue(financialInputRequestDbl.getTotalLiablities()));

		//Balance Sheet -ASSETS
		financialInputRequestDbl.setNetBlock(CommonUtils.substractThreeNumbers(financialInputRequestDbl.getGrossBlock(), financialInputRequestDbl.getLessAccumulatedDepre(),financialInputRequestDbl.getImpairmentofAsset()));
		financialInputRequestString.setNetBlock(convertValue(financialInputRequestDbl.getNetBlock()));
		financialInputRequestDbl.setTotalNonCurruntAsset(CommonUtils.addNumbers(financialInputRequestDbl.getCapitalWorkInProgress(), financialInputRequestDbl.getIntengibleAssets(), financialInputRequestDbl.getPreOperativeExpe(), financialInputRequestDbl.getAssetInTransit(), financialInputRequestDbl.getInvestmentInSubsidiaries(), financialInputRequestDbl.getOtherInvestment(), financialInputRequestDbl.getLongTermLoansAndAdva(), financialInputRequestDbl.getOtheNonCurruntAsset()));
		financialInputRequestString.setTotalNonCurruntAsset(convertValue(financialInputRequestDbl.getTotalNonCurruntAsset()));
		financialInputRequestDbl.setTotalCurruntAsset(CommonUtils.addNumbers(financialInputRequestDbl.getInventories(), financialInputRequestDbl.getSundryDebtors(), financialInputRequestDbl.getCashAndBank(), financialInputRequestDbl.getOtherCurruntAsset(), financialInputRequestDbl.getShortTermLoansAdvances()));
		financialInputRequestString.setTotalCurruntAsset(convertValue(financialInputRequestDbl.getTotalCurruntAsset()));
		financialInputRequestDbl.setTotalAsset(CommonUtils.addNumbers(financialInputRequestDbl.getNetBlock(), financialInputRequestDbl.getTotalCurruntAsset(), financialInputRequestDbl.getTotalNonCurruntAsset(), financialInputRequestDbl.getOtherIncomeNeedTocCheckAsset()));
		financialInputRequestString.setTotalAsset(convertValue(financialInputRequestDbl.getTotalAsset()));
		if(financialInputRequestDbl.getShareFaceValue() !=0 && financialInputRequestDbl.getShareCapital() !=0) {
			double total = financialInputRequestDbl.getShareCapital()/financialInputRequestDbl.getShareFaceValue();
			if(!CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getShareHolderFunds()) && financialInputRequestDbl.getShareHolderFunds() !=0) {
				financialInputRequestString.setBookValue(convertValue(financialInputRequestDbl.getShareHolderFunds() / total));
			}
		}
		
		return new Object[] {osDetailsString, liabilitiesDetailsString, assetDetailsString , financialInputRequestString , financialInputRequestDbl };
	}
	
	public void calculateRatioAnalysis(Map<Integer, Object[]>financials) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
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
		curFinYearString.setEbitda(convertValue(curFinYearDouble.getEbitda()));
		prevFinYearString.setEbitda(convertValue(prevFinYearDouble.getEbitda()));
		
		curFinYearDouble.setInterestPaid(curFinYearDouble.getInterest());
		prevFinYearDouble.setInterestPaid(prevFinYearDouble.getInterest());
		curFinYearString.setInterestPaid(convertValue(curFinYearDouble.getInterestPaid()));
		prevFinYearString.setInterestPaid(convertValue(prevFinYearDouble.getInterestPaid()));
		
		curFinYearDouble.setIncreaseWorkingCapital(curFinYearDouble.getInventories()+curFinYearDouble.getSundryDebtors()+curFinYearDouble.getOtherCurruntAsset()-prevFinYearDouble.getInventories()-prevFinYearDouble.getSundryDebtors()-prevFinYearDouble.getOtherCurruntAsset()+prevFinYearDouble.getTradePayables()+prevFinYearDouble.getOtherCurruntLiablities()+prevFinYearDouble.getShortTermProvision()-curFinYearDouble.getTradePayables()-curFinYearDouble.getOtherCurruntLiablities()-curFinYearDouble.getShortTermProvision());
		prevFinYearDouble.setIncreaseWorkingCapital(prevFinYearDouble.getInventories()+prevFinYearDouble.getSundryDebtors()+prevFinYearDouble.getOtherCurruntAsset()-yrBeforePrevFinYearDouble.getInventories()-yrBeforePrevFinYearDouble.getSundryDebtors()-yrBeforePrevFinYearDouble.getOtherCurruntAsset()+yrBeforePrevFinYearDouble.getTradePayables()+yrBeforePrevFinYearDouble.getOtherCurruntLiablities()+yrBeforePrevFinYearDouble.getShortTermProvision()-prevFinYearDouble.getTradePayables()-prevFinYearDouble.getOtherCurruntLiablities()-prevFinYearDouble.getShortTermProvision());
		curFinYearString.setIncreaseWorkingCapital(convertValue(curFinYearDouble.getIncreaseWorkingCapital()));
		prevFinYearString.setIncreaseWorkingCapital(convertValue(prevFinYearDouble.getIncreaseWorkingCapital()));
		
		curFinYearDouble.setTaxPaid(curFinYearDouble.getProvisionForTax());
		prevFinYearDouble.setTaxPaid(prevFinYearDouble.getProvisionForTax());
		curFinYearString.setTaxPaid(convertValue(curFinYearDouble.getTaxPaid()));
		prevFinYearString.setTaxPaid(convertValue(prevFinYearDouble.getTaxPaid()));
		
		curFinYearDouble.setCashFromOperating(curFinYearDouble.getEbitda()-curFinYearDouble.getInterestPaid()-curFinYearDouble.getIncreaseWorkingCapital()-curFinYearDouble.getTaxPaid());
		prevFinYearDouble.setCashFromOperating(prevFinYearDouble.getEbitda()-prevFinYearDouble.getInterestPaid()-prevFinYearDouble.getIncreaseWorkingCapital()-prevFinYearDouble.getTaxPaid());
		curFinYearString.setCashFromOperating(convertValue(curFinYearDouble.getCashFromOperating()));
		prevFinYearString.setCashFromOperating(convertValue(prevFinYearDouble.getCashFromOperating()));
		
		//RATIO ANALYSIS
		curFinYearString.setEbitadPercentage(convertValue(CommonUtils.divideNumbers(curFinYearDouble.getOperatingProfitEbitadOi(), curFinYearDouble.getNetSale())* 100));
		prevFinYearString.setEbitadPercentage(convertValue(CommonUtils.divideNumbers(prevFinYearDouble.getOperatingProfitEbitadOi(), prevFinYearDouble.getNetSale())* 100));
		yrBeforePrevFinYearString.setEbitadPercentage(convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getOperatingProfitEbitadOi(), yrBeforePrevFinYearDouble.getNetSale())* 100));
		
		curFinYearString.setPatm(convertValue(CommonUtils.divideNumbers(curFinYearDouble.getProfitAfterTax(), curFinYearDouble.getNetSale())* 100));
		prevFinYearString.setPatm(convertValue(CommonUtils.divideNumbers(prevFinYearDouble.getProfitAfterTax(), prevFinYearDouble.getNetSale())* 100));
		yrBeforePrevFinYearString.setPatm(convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getProfitAfterTax(), yrBeforePrevFinYearDouble.getNetSale())* 100));
		
		curFinYearString.setRoce(convertValue(((curFinYearDouble.getOperatingProfitEbitadOi()*2/(CommonUtils.addNumbers(curFinYearDouble.getShareHolderFunds(), prevFinYearDouble.getShareHolderFunds(), curFinYearDouble.getTotalNonCurruntLiablities(), prevFinYearDouble.getTotalNonCurruntLiablities())))*12/curFinYearDouble.getNoOfMonth())* 100));
		prevFinYearString.setRoce(convertValue(((prevFinYearDouble.getOperatingProfitEbitadOi()*2/(CommonUtils.addNumbers(prevFinYearDouble.getShareHolderFunds(), yrBeforePrevFinYearDouble.getShareHolderFunds(), prevFinYearDouble.getTotalNonCurruntLiablities(), yrBeforePrevFinYearDouble.getTotalNonCurruntLiablities())))*12/prevFinYearDouble.getNoOfMonth())* 100));
		
		curFinYearString.setAssetTurnOver(convertValue(CommonUtils.divideNumbers(curFinYearDouble.getNetSale() * 12, (CommonUtils.multiplyNumbers(curFinYearDouble.getTotalAsset(), curFinYearDouble.getNoOfMonth())))));
		prevFinYearString.setAssetTurnOver(convertValue(CommonUtils.divideNumbers(prevFinYearDouble.getNetSale() * 12, (CommonUtils.multiplyNumbers(prevFinYearDouble.getTotalAsset(), prevFinYearDouble.getNoOfMonth())))));
		yrBeforePrevFinYearString.setAssetTurnOver(convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getNetSale() * 12, (CommonUtils.multiplyNumbers(yrBeforePrevFinYearDouble.getTotalAsset(), yrBeforePrevFinYearDouble.getNoOfMonth())))));
		
		curFinYearString.setInventoryTurnOver(convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(curFinYearDouble.getTotalExpenditure()*12, (curFinYearDouble.getInventories()*curFinYearDouble.getNoOfMonth()))))));
		prevFinYearString.setInventoryTurnOver(convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(prevFinYearDouble.getTotalExpenditure()*12, (prevFinYearDouble.getInventories()*prevFinYearDouble.getNoOfMonth()))))));
		yrBeforePrevFinYearString.setInventoryTurnOver(convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getTotalExpenditure()*12, (yrBeforePrevFinYearDouble.getInventories()*yrBeforePrevFinYearDouble.getNoOfMonth()))))));
		
		curFinYearString.setDebtorsTurnover(convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(curFinYearDouble.getNetSale()*12, (curFinYearDouble.getSundryDebtors()*curFinYearDouble.getNoOfMonth()))))));
		prevFinYearString.setDebtorsTurnover(convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(prevFinYearDouble.getNetSale()*12, (prevFinYearDouble.getSundryDebtors()*prevFinYearDouble.getNoOfMonth()))))));
		yrBeforePrevFinYearString.setDebtorsTurnover(convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getNetSale()*12, (yrBeforePrevFinYearDouble.getSundryDebtors()*yrBeforePrevFinYearDouble.getNoOfMonth()))))));
		
		curFinYearString.setCreditorsTurnover(convertValue((CommonUtils.divideNumbers(365.0, CommonUtils.divideNumbers((CommonUtils.addNumbers(curFinYearDouble.getRawMaterialConsumed(), curFinYearDouble.getPowerAndFuelCost())), curFinYearDouble.getTradePayables())))*12/curFinYearDouble.getNoOfMonth()));
		prevFinYearString.setCreditorsTurnover(convertValue((CommonUtils.divideNumbers(365.0, CommonUtils.divideNumbers((CommonUtils.addNumbers(prevFinYearDouble.getRawMaterialConsumed(), prevFinYearDouble.getPowerAndFuelCost())), prevFinYearDouble.getTradePayables())))*12/prevFinYearDouble.getNoOfMonth()));
		yrBeforePrevFinYearString.setCreditorsTurnover(convertValue((CommonUtils.divideNumbers(365.0, CommonUtils.divideNumbers((CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getRawMaterialConsumed(), yrBeforePrevFinYearDouble.getPowerAndFuelCost())), yrBeforePrevFinYearDouble.getTradePayables())))*12/yrBeforePrevFinYearDouble.getNoOfMonth()));
		
		curFinYearString.setSalesWorkingCapital(convertValue((CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(curFinYearDouble.getNetSale(), (CommonUtils.addNumbers(curFinYearDouble.getInventories(),curFinYearDouble.getSundryDebtors())-curFinYearDouble.getTradePayables())))))*12 /curFinYearDouble.getNoOfMonth()));
		prevFinYearString.setSalesWorkingCapital(convertValue((CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(prevFinYearDouble.getNetSale(), (CommonUtils.addNumbers(prevFinYearDouble.getInventories(),prevFinYearDouble.getSundryDebtors())-prevFinYearDouble.getTradePayables())))))*12 /prevFinYearDouble.getNoOfMonth()));
		yrBeforePrevFinYearString.setSalesWorkingCapital(convertValue((CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getNetSale(), (CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getInventories(),yrBeforePrevFinYearDouble.getSundryDebtors())-yrBeforePrevFinYearDouble.getTradePayables())))))*12 /yrBeforePrevFinYearDouble.getNoOfMonth()));
		
		curFinYearString.setNetSalesGrowthCapital(convertValue((CommonUtils.divideNumbers(curFinYearDouble.getNetSale(),prevFinYearDouble.getNetSale())-1)* 100));
		prevFinYearString.setNetSalesGrowthCapital(convertValue((CommonUtils.divideNumbers(prevFinYearDouble.getNetSale(),yrBeforePrevFinYearDouble.getNetSale())-1)* 100));
		
		curFinYearString.setPatGrowthCapital(convertValue((CommonUtils.divideNumbers(curFinYearDouble.getProfitAfterTax(),prevFinYearDouble.getProfitAfterTax())-1)* 100));
		prevFinYearString.setPatGrowthCapital(convertValue((CommonUtils.divideNumbers(prevFinYearDouble.getProfitAfterTax(),yrBeforePrevFinYearDouble.getProfitAfterTax())-1)* 100));

		curFinYearDouble.setAdjustedTotalDebtEquity(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(curFinYearDouble.getTotalNonCurruntLiablities(),curFinYearDouble.getLongTermProvision(),curFinYearDouble.getUnsecuredLoansPromoters())), (CommonUtils.addNumbers(curFinYearDouble.getShareHolderFunds(),curFinYearDouble.getUnsecuredLoansPromoters()))));
		prevFinYearDouble.setAdjustedTotalDebtEquity(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(prevFinYearDouble.getTotalNonCurruntLiablities(),prevFinYearDouble.getLongTermProvision(),prevFinYearDouble.getUnsecuredLoansPromoters())), (CommonUtils.addNumbers(prevFinYearDouble.getShareHolderFunds(),prevFinYearDouble.getUnsecuredLoansPromoters()))));
		yrBeforePrevFinYearDouble.setAdjustedTotalDebtEquity(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(yrBeforePrevFinYearDouble.getTotalNonCurruntLiablities(),yrBeforePrevFinYearDouble.getLongTermProvision(),yrBeforePrevFinYearDouble.getUnsecuredLoansPromoters())), (CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getShareHolderFunds(),yrBeforePrevFinYearDouble.getUnsecuredLoansPromoters()))));
		curFinYearString.setAdjustedTotalDebtEquity(convertValue(curFinYearDouble.getAdjustedTotalDebtEquity()));
		prevFinYearString.setAdjustedTotalDebtEquity(convertValue(prevFinYearDouble.getAdjustedTotalDebtEquity()));
		yrBeforePrevFinYearString.setAdjustedTotalDebtEquity(convertValue(yrBeforePrevFinYearDouble.getAdjustedTotalDebtEquity()));
		
		curFinYearString.setGrowthDebtEquity(convertValue(CommonUtils.substractNumbers(curFinYearDouble.getAdjustedTotalDebtEquity(),prevFinYearDouble.getAdjustedTotalDebtEquity())/prevFinYearDouble.getAdjustedTotalDebtEquity()));
		prevFinYearString.setGrowthDebtEquity(convertValue(CommonUtils.substractNumbers(prevFinYearDouble.getAdjustedTotalDebtEquity(),yrBeforePrevFinYearDouble.getAdjustedTotalDebtEquity())/yrBeforePrevFinYearDouble.getAdjustedTotalDebtEquity()));
		
		curFinYearString.setCurruntRatioX(convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(curFinYearDouble.getInventories(),curFinYearDouble.getSundryDebtors())), curFinYearDouble.getTradePayables())));
		prevFinYearString.setCurruntRatioX(convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(prevFinYearDouble.getInventories(),prevFinYearDouble.getSundryDebtors())), prevFinYearDouble.getTradePayables())));
		yrBeforePrevFinYearString.setCurruntRatioX(convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getInventories(),yrBeforePrevFinYearDouble.getSundryDebtors())), yrBeforePrevFinYearDouble.getTradePayables())));
		
		curFinYearString.setQuickRatioX(convertValue(CommonUtils.divideNumbers(curFinYearDouble.getSundryDebtors(),curFinYearDouble.getTradePayables())));
		prevFinYearString.setQuickRatioX(convertValue(CommonUtils.divideNumbers(prevFinYearDouble.getSundryDebtors(),prevFinYearDouble.getTradePayables())));
		yrBeforePrevFinYearString.setQuickRatioX(convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getSundryDebtors(),yrBeforePrevFinYearDouble.getTradePayables())));
		
		curFinYearString.setCashInterestCover(convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(curFinYearDouble.getCashFromOperating(),curFinYearDouble.getInterest())), curFinYearDouble.getInterest())));
		prevFinYearString.setCashInterestCover(convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(prevFinYearDouble.getCashFromOperating(),prevFinYearDouble.getInterest())), prevFinYearDouble.getInterest())));
		
		curFinYearString.setDebtEbitad(convertValue(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(curFinYearDouble.getTotalNonCurruntLiablities(),curFinYearDouble.getUnsecuredLoansPromoters(),curFinYearDouble.getLongTermProvision())), (12*CommonUtils.divideNumbers(curFinYearDouble.getOperatingProfitEbitadOi(),curFinYearDouble.getNoOfMonth())))));
		prevFinYearString.setDebtEbitad(convertValue(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(prevFinYearDouble.getTotalNonCurruntLiablities(),prevFinYearDouble.getUnsecuredLoansPromoters(),prevFinYearDouble.getLongTermProvision())), (12*CommonUtils.divideNumbers(prevFinYearDouble.getOperatingProfitEbitadOi(),prevFinYearDouble.getNoOfMonth())))));
		yrBeforePrevFinYearString.setDebtEbitad(convertValue(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(yrBeforePrevFinYearDouble.getTotalNonCurruntLiablities(),yrBeforePrevFinYearDouble.getUnsecuredLoansPromoters(),yrBeforePrevFinYearDouble.getLongTermProvision())), (12*CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getOperatingProfitEbitadOi(),yrBeforePrevFinYearDouble.getNoOfMonth())))));
		
		curFinYearString.setFreeReserveEquity(convertValue(CommonUtils.divideNumbers(curFinYearDouble.getOtherReserveAndSurplus(), (CommonUtils.addNumbers(curFinYearDouble.getShareCapital(),curFinYearDouble.getShareWarrantOutstandings())))));
		prevFinYearString.setFreeReserveEquity(convertValue(CommonUtils.divideNumbers(prevFinYearDouble.getOtherReserveAndSurplus(), (CommonUtils.addNumbers(prevFinYearDouble.getShareCapital(),prevFinYearDouble.getShareWarrantOutstandings())))));
		yrBeforePrevFinYearString.setFreeReserveEquity(convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getOtherReserveAndSurplus(), (CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getShareCapital(),yrBeforePrevFinYearDouble.getShareWarrantOutstandings())))));
		
		curFinYearDouble.setCfoMargine(CommonUtils.divideNumbers(curFinYearDouble.getCashFromOperating(),curFinYearDouble.getNetSale()));
		prevFinYearDouble.setCfoMargine(CommonUtils.divideNumbers(prevFinYearDouble.getCashFromOperating(),prevFinYearDouble.getNetSale()));
		curFinYearString.setCfoMargine(convertValue(curFinYearDouble.getCfoMargine()));
		prevFinYearString.setCfoMargine(convertValue(prevFinYearDouble.getCfoMargine()));
		
		curFinYearString.setGrowthCfoMargine(convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(curFinYearDouble.getCfoMargine(),prevFinYearDouble.getCfoMargine())), prevFinYearDouble.getCfoMargine())));
		prevFinYearString.setGrowthCfoMargine("0.0");
		
		
		curFinYear[curFinYear.length - 2] = curFinYearString;
		prevFinYear[prevFinYear.length - 2] = prevFinYearString;
		yrBeforePrevFinYear[yrBeforePrevFinYear.length - 2] = yrBeforePrevFinYearString;
		
		financials.put((currentYear - 1), curFinYear);
		financials.put((currentYear - 2), prevFinYear);
		financials.put((currentYear - 3), yrBeforePrevFinYear);
		logger.info("financials========="+financials.toString());
	}
	
	
	/*********************************************************CAM UTILS****************************************************************/
	
	public String convertValue(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value)? decim.format(value).toString(): "0";
	}
	public String convertValueWithoutDecimal(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value)? decim2.format(value).toString(): "0";
	}
	public Double checkDoubleNUll(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value) ? value : 0.0;
	}
	public static Object convertToDoubleForXml (Object obj, Map<String, Object>data) throws Exception {
		Field[] fields = obj.getClass().getDeclaredFields();
		 for(Field field : fields) {
			 field.setAccessible(true);
             Object value = field.get(obj);
             if(data != null) {
            	 data.put(field.getName(), value);
             }
             if(!CommonUtils.isObjectNullOrEmpty(value)) {
            	 if(value instanceof Double){
                	 if(!Double.isNaN((Double)value)) {
                		 DecimalFormat decim = new DecimalFormat("0.00");
                    	 value = Double.parseDouble(decim.format(value));
                    	 if(data != null) {
                    		 logger.info("eligibility data================>"+field.getName());
                    		 value = decim.format(value);
                    		 data.put(field.getName(), value);
                    	 }else {
                    		 field.set(obj,value);                    		 
                    	 }
                	 }
                 }
             }
		 }
		 if(data != null) {
			 return data;
		 }
		return obj;
	}
	public static Object printFields(Object obj) throws Exception {
		if(obj instanceof List) {
			List<?> lst = (List)obj;
			for(Object o : lst) {
				escapeXml(o);
			}
		}else if(obj instanceof Map) {
			Map<Object, Object> map = (Map)obj;
			for(Map.Entry<Object, Object> setEntry : map.entrySet()) {
				escapeXml(setEntry.getValue());
			}
		}
		else {
			escapeXml(obj);
		}
		 return obj;
	}
	public static Object escapeXml(Object obj) throws Exception{
		if(obj instanceof List) {
			List<?> lst = (List)obj;
			for(Object o : lst) {
				escapeXml(o);
			}
		}else if(obj instanceof Map) {
			Map<Object, Object> map = (Map)obj;
			for(Map.Entry<Object, Object> setEntry : map.entrySet()) {
				escapeXml(setEntry.getValue());
			}
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object value = field.get(obj);
			if (value instanceof String) {
				String value1 = (String) field.get(obj);
				String a = StringEscapeUtils.escapeXml(value1.toString());
				value = a;
				field.set(obj, value);
			}else {
				continue;
			}
		}
		return obj;
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return null;
	}
}
