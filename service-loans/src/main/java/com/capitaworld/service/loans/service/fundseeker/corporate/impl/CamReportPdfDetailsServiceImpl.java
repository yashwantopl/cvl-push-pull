package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.api.eligibility.model.EligibililityRequest;
import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.cibil.api.model.CibilRequest;
import com.capitaworld.cibil.api.model.CibilResponse;
import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.fitchengine.model.manufacturing.FitchOutputManu;
import com.capitaworld.service.fitchengine.model.service.FitchOutputServ;
import com.capitaworld.service.fitchengine.model.trading.FitchOutputTrad;
import com.capitaworld.service.fitchengine.utils.CommonUtils.BusinessType;
import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.gst.yuva.request.GSTR1Request;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.model.AssetDetailsString;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponseString;
import com.capitaworld.service.loans.model.FinanceMeansDetailRequest;
import com.capitaworld.service.loans.model.FinanceMeansDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementDetailResponseString;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FinancialInputRequestString;
import com.capitaworld.service.loans.model.LiabilitiesDetailsString;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.OperatingStatementDetailsString;
import com.capitaworld.service.loans.model.OwnershipDetailRequest;
import com.capitaworld.service.loans.model.OwnershipDetailResponse;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.TotalCostOfProjectResponse;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.corporate.PrimaryCorporateRequest;
import com.capitaworld.service.loans.model.corporate.TotalCostOfProjectRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorRepository;
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
import com.capitaworld.service.rating.model.FinancialInputRequest;
import com.capitaworld.service.rating.model.RatingResponse;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.model.ProposalScoreDetailResponse;
import com.capitaworld.service.scoring.model.ProposalScoreResponse;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.scoring.utils.ScoreParameter;
import com.capitaworld.service.thirdparty.model.CGTMSEDataResponse;
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
			map.put("email", request.getEmail());
		} catch (IOException e1) {
			logger.info("Error while getting registration details");
			e1.printStackTrace();
		}
		CorporateFinalInfoRequest corporateFinalInfoRequest;
		try {
			corporateFinalInfoRequest = corporateFinalInfoService.get(userId, applicationId);
			//ADMIN OFFICE ADDRESS
			if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress())){
				map.put("adminAddPremise", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getPremiseNumber()) ? printFields(corporateFinalInfoRequest.getSecondAddress().getPremiseNumber()) + ", " : "");
				map.put("adminAddStreetName", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getStreetName()) ? printFields(corporateFinalInfoRequest.getSecondAddress().getStreetName()) + ", " : "");
				map.put("adminAddLandmark", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getLandMark()) ? printFields(corporateFinalInfoRequest.getSecondAddress().getLandMark()) + ", " : "");
				map.put("adminAddCountry", StringEscapeUtils.escapeXml(getCountryName(corporateFinalInfoRequest.getSecondAddress().getCountryId())));
				map.put("adminAddState", StringEscapeUtils.escapeXml(getStateName(corporateFinalInfoRequest.getSecondAddress().getStateId())));
				map.put("adminAddCity", StringEscapeUtils.escapeXml(getCityName(corporateFinalInfoRequest.getSecondAddress().getCityId())));
				map.put("adminAddPincode", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getPincode())?corporateFinalInfoRequest.getSecondAddress().getPincode() : "");
			}
			//REGISTERED OFFICE ADDRESS
			if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress())) {
				map.put("registeredAddPremise", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getPremiseNumber()) ? escapeXml(corporateFinalInfoRequest.getFirstAddress().getPremiseNumber()) + ", " : "");
				map.put("registeredAddStreetName", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getStreetName()) ? escapeXml(corporateFinalInfoRequest.getFirstAddress().getStreetName()) + ", " : "");
				map.put("registeredAddLandmark", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getLandMark()) ? escapeXml(corporateFinalInfoRequest.getFirstAddress().getLandMark()) + ", " : "");
				map.put("registeredAddCountry", StringEscapeUtils.escapeXml(getCountryName(corporateFinalInfoRequest.getFirstAddress().getCountryId())));
				map.put("registeredAddState", StringEscapeUtils.escapeXml(getStateName(corporateFinalInfoRequest.getFirstAddress().getStateId())));
				map.put("registeredAddCity", StringEscapeUtils.escapeXml(getCityName(corporateFinalInfoRequest.getFirstAddress().getCityId())));
				map.put("registeredAddPincode", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getPincode())?corporateFinalInfoRequest.getFirstAddress().getPincode() : "");
			}
			map.put("corporateApplicantFinal", corporateFinalInfoRequest);
			map.put("aboutUs", StringEscapeUtils.escapeXml(corporateFinalInfoRequest.getAboutUs()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//TIMELINE DATES
		map.put("dateOfProposal", !CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getCreatedDate())? DATE_FORMAT.format(loanApplicationMaster.getCreatedDate()):"-");
		map.put("dateOfInPrincipalApproval", !CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getApprovedDate())? DATE_FORMAT.format(loanApplicationMaster.getApprovedDate()):"-");
		
		//GST DATA
		try {
			GSTR1Request gstr1Request = new GSTR1Request();
			gstr1Request.setGstin(corporateApplicantRequest.getGstIn());
			GstResponse response = gstClient.getCalculations(gstr1Request);
			map.put("gstResponse", !CommonUtils.isObjectNullOrEmpty(response.getData()) ? convertToDoubleForXml(response.getData()) : " ");
			
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
//				financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
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
		//IRR AND RATIO ANALYSIS DATA
		try {
			LoanApplicationRequest loanApplicationRequest = loanApplicationService.get(applicationId, userId);
			Long denominationValue = Denomination.getById(loanApplicationRequest.getDenominationId()).getDigit();
			Object financialInputRequest= calculateIRRScore(userId, applicationId, null, denominationValue);
			map.put("financialInputRequest", !CommonUtils.isObjectNullOrEmpty(financialInputRequest) ? financialInputRequest : " ");
			logger.info("Financial Input",financialInputRequest);
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
					map.put("proposalResponse", !CommonUtils.isObjectNullOrEmpty(proposalMappingResponse.getData()) ? proposalMappingResponse.getData() : " ");
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
			map.put("proposalScoreResponse",convertToDoubleForXml(proposalScoreResponse));
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
		
		//PERFIOS API DATA
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(applicationId);
		reportRequest.setUserId(userId);
		try {
			AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReport(reportRequest);
			Data data = MultipleJSONObjectHelper.getObjectFromMap((HashMap<String, Object>) analyzerResponse.getData(), Data.class);
			map.put("bankStatement", printFields(data.getXns().getXn()));
			map.put("monthlyDetails", printFields(data.getMonthlyDetailList().getMonthlyDetails()));
			map.put("top5FundReceived", printFields(data.getTop5FundReceivedList().getItem()));
			map.put("top5FundTransfered", printFields(data.getTop5FundTransferedList().getItem()));
			map.put("bouncedChequeList", printFields(data.getBouncedOrPenalXnList().getBouncedOrPenalXns()));
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Error while getting perfios data");
		}
		
		//CGTMSE DATA
		try {
			CGTMSEDataResponse cgtmseDataResponse = thirdPartyClient.getCalulation(applicationId);
			map.put("cgtmseData", printFields(cgtmseDataResponse));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//NOTES TO ACCOUNTS
		try {
			LoanApplicationRequest loanApplicationRequest = loanApplicationService.get(applicationId, userId);
			Long denominationValue = Denomination.getById(loanApplicationRequest.getDenominationId()).getDigit();
			OperatingStatementDetailsString operatingStatementDetailsString =getOperatingStatementMappings(userId, applicationId, null, denominationValue);
			map.put("operatingStatement", operatingStatementDetailsString);
			LiabilitiesDetailsString liabilitiesDetailsString = getLiabilitiesDetails(userId, applicationId, null, denominationValue);
			map.put("liabilitiesDetails", liabilitiesDetailsString);
			AssetDetailsString assetDetailsString =getAssetDetails(userId, applicationId, null, denominationValue);
			map.put("assetDetails", assetDetailsString);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//ELIGIBILITY DATA (ASSESSMENT TO LIMITS)
		try {
			EligibililityRequest eligibilityReq=new EligibililityRequest();
			eligibilityReq.setApplicationId(applicationId);
			PrimaryCorporateRequest primaryCorporateRequest = primaryCorporateService.get(applicationId, userId);
			eligibilityReq.setProductId(primaryCorporateRequest.getProductId().longValue());
			EligibilityResponse eligibilityResp= eligibilityClient.corporateLoanData(eligibilityReq);
			map.put("assLimits", eligibilityResp.getData());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error while getting Eligibility data");
		}
	
		/**********************************************FINAL DETAILS*****************************************************/
		
		if(isFinalView) {
			
			//FITCH DATA
			try {
			RatingResponse ratingResponse = (RatingResponse) irrService.calculateIrrRating(applicationId, userId).getBody().getData();
			if(!CommonUtils.isObjectNullOrEmpty(ratingResponse.getBusinessTypeId())) {
				if(BusinessType.MANUFACTURING == ratingResponse.getBusinessTypeId())
				{
					FitchOutputManu fitchOutputManu= MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)ratingResponse.getData(),FitchOutputManu.class);
					map.put("fitchResponse",convertToDoubleForXml(fitchOutputManu));
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
					map.put("fitchResponse",convertToDoubleForXml(fitchOutputTrad));
					map.put("fitchTitle","Trading");
				}
				if(BusinessType.SERVICE == ratingResponse.getBusinessTypeId())
				{
					FitchOutputServ fitchOutputServ = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)ratingResponse.getData(),FitchOutputServ.class);
					map.put("fitchResponse",convertToDoubleForXml(fitchOutputServ));
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
		
		return map;
	}
	public Object calculateIRRScore(Long userId, Long applicationId, String industry, Long denomination) throws Exception {
		FinancialInputRequest financialInputRequest= irrService.cmaIrrMappingService(userId, applicationId, null, denomination);
		logger.info("Financial Input=================================>"+financialInputRequest.toString());
		
		FinancialInputRequestString financialInputRequestString = new FinancialInputRequestString();
		
		financialInputRequestString.setGrossSalesFy(convertValue(financialInputRequest.getGrossSalesFy()));
		financialInputRequestString.setGrossSalesSy(convertValue(financialInputRequest.getGrossSalesSy()));
		financialInputRequestString.setGrossSalesTy(convertValue(financialInputRequest.getGrossSalesTy()));
		
		financialInputRequestString.setLessExciseDuityFy(convertValue(financialInputRequest.getLessExciseDuityFy()));
        financialInputRequestString.setLessExciseDuitySy(convertValue(financialInputRequest.getLessExciseDuitySy()));
        financialInputRequestString.setLessExciseDuityTy(convertValue(financialInputRequest.getLessExciseDuityTy()));
        
        financialInputRequestString.setIncreaseDecreaseStockFy(convertValue(financialInputRequest.getIncreaseDecreaseStockFy()));
        financialInputRequestString.setIncreaseDecreaseStockSy(convertValue(financialInputRequest.getIncreaseDecreaseStockSy()));
        financialInputRequestString.setIncreaseDecreaseStockTy(convertValue(financialInputRequest.getIncreaseDecreaseStockTy()));
        
        financialInputRequestString.setRawMaterialConsumedFy(convertValue(financialInputRequest.getRawMaterialConsumedFy()));
        financialInputRequestString.setRawMaterialConsumedSy(convertValue(financialInputRequest.getRawMaterialConsumedSy()));
        financialInputRequestString.setRawMaterialConsumedTy(convertValue(financialInputRequest.getRawMaterialConsumedTy()));
        
        financialInputRequestString.setPowerAndFuelCostFy(convertValue(financialInputRequest.getPowerAndFuelCostFy()));
        financialInputRequestString.setPowerAndFuelCostSy(convertValue(financialInputRequest.getPowerAndFuelCostSy()));
        financialInputRequestString.setPowerAndFuelCostTy(convertValue(financialInputRequest.getPowerAndFuelCostTy()));
        
        financialInputRequestString.setEmployeeCostFy(convertValue(financialInputRequest.getEmployeeCostFy()));
        financialInputRequestString.setEmployeeCostSy(convertValue(financialInputRequest.getEmployeeCostSy()));
        financialInputRequestString.setEmployeeCostTy(convertValue(financialInputRequest.getEmployeeCostTy()));
        
        financialInputRequestString.setGeneralAndAdminExpeFy(convertValue(financialInputRequest.getGeneralAndAdminExpeFy()));
        financialInputRequestString.setGeneralAndAdminExpeSy(convertValue(financialInputRequest.getGeneralAndAdminExpeSy()));
        financialInputRequestString.setGeneralAndAdminExpeTy(convertValue(financialInputRequest.getGeneralAndAdminExpeTy()));
        
        financialInputRequestString.setSellingAndDistriExpeFy(convertValue(financialInputRequest.getSellingAndDistriExpeFy()));
        financialInputRequestString.setSellingAndDistriExpeSy(convertValue(financialInputRequest.getSellingAndDistriExpeSy()));             
        financialInputRequestString.setSellingAndDistriExpeTy(convertValue(financialInputRequest.getSellingAndDistriExpeTy()));
		
        financialInputRequestString.setMiscelExpeFy(convertValue(financialInputRequest.getMiscelExpeFy()));
        financialInputRequestString.setMiscelExpeSy(convertValue(financialInputRequest.getMiscelExpeSy()));           
        financialInputRequestString.setMiscelExpeTy(convertValue(financialInputRequest.getMiscelExpeTy()));
        
        financialInputRequestString.setLessExpeCapitaFy(convertValue(financialInputRequest.getLessExpeCapitaFy()));
        financialInputRequestString.setLessExpeCapitaSy(convertValue(financialInputRequest.getLessExpeCapitaSy()));               
        financialInputRequestString.setLessExpeCapitaTy(convertValue(financialInputRequest.getLessExpeCapitaTy()));
        
        financialInputRequestString.setOtherIncomeFy(convertValue(financialInputRequest.getOtherIncomeFy()));
        financialInputRequestString.setOtherIncomeSy(convertValue(financialInputRequest.getOtherIncomeSy()));
        financialInputRequestString.setOtherIncomeTy(convertValue(financialInputRequest.getOtherIncomeTy()));
        
        financialInputRequestString.setInterestFy(convertValue(financialInputRequest.getInterestFy()));
        financialInputRequestString.setInterestSy(convertValue(financialInputRequest.getInterestSy()));
        financialInputRequestString.setInterestTy(convertValue(financialInputRequest.getInterestTy()));
        
        financialInputRequestString.setDepriciationFy(convertValue(financialInputRequest.getDepriciationFy()));
        financialInputRequestString.setDepriciationSy(convertValue(financialInputRequest.getDepriciationSy()));
        financialInputRequestString.setDepriciationTy(convertValue(financialInputRequest.getDepriciationTy()));
        
        financialInputRequestString.setExceptionalIncomeFy(convertValue(financialInputRequest.getExceptionalIncomeFy()));
        financialInputRequestString.setExceptionalIncomeSy(convertValue(financialInputRequest.getExceptionalIncomeSy()));
        financialInputRequestString.setExceptionalIncomeTy(convertValue(financialInputRequest.getExceptionalIncomeTy()));
        
        financialInputRequestString.setProvisionForTaxFy(convertValue(financialInputRequest.getProvisionForTaxFy()));
        financialInputRequestString.setProvisionForTaxSy(convertValue(financialInputRequest.getProvisionForTaxSy()));
        financialInputRequestString.setProvisionForTaxTy(convertValue(financialInputRequest.getProvisionForTaxTy()));
        
        financialInputRequestString.setDividendPayOutFy(convertValue(financialInputRequest.getDividendPayOutFy()));
        financialInputRequestString.setDividendPayOutSy(convertValue(financialInputRequest.getDividendPayOutSy()));
        financialInputRequestString.setDividendPayOutTy(convertValue(financialInputRequest.getDividendPayOutTy()));
		
        financialInputRequest.setShareCapitalFy(financialInputRequest.getShareCapitalFy());
        financialInputRequest.setShareCapitalSy(financialInputRequest.getShareCapitalSy());
        financialInputRequest.setShareCapitalTy(financialInputRequest.getShareCapitalTy());
        
        financialInputRequestString.setShareCapitalFy(convertValue(financialInputRequest.getShareCapitalFy()));
        financialInputRequestString.setShareCapitalSy(convertValue(financialInputRequest.getShareCapitalSy()));
        financialInputRequestString.setShareCapitalTy(convertValue(financialInputRequest.getShareCapitalTy()));
        
        financialInputRequestString.setShareWarrantOutstandingsFy(convertValue(financialInputRequest.getShareWarrantOutstandingsFy()));
        financialInputRequestString.setShareWarrantOutstandingsSy(convertValue(financialInputRequest.getShareWarrantOutstandingsSy()));
        financialInputRequestString.setShareWarrantOutstandingsTy(convertValue(financialInputRequest.getShareWarrantOutstandingsTy()));
        
        financialInputRequestString.setRevalationReserveFy(convertValue(financialInputRequest.getRevalationReserveFy()));
        financialInputRequestString.setRevalationReserveSy(convertValue(financialInputRequest.getRevalationReserveSy()));
        financialInputRequestString.setRevalationReserveTy(convertValue(financialInputRequest.getRevalationReserveTy()));
        
        financialInputRequestString.setOtherReserveAndSurplusFy(convertValue(financialInputRequest.getOtherReserveAndSurplusFy()));
        financialInputRequestString.setOtherReserveAndSurplusSy(convertValue(financialInputRequest.getOtherReserveAndSurplusSy()));
        financialInputRequestString.setOtherReserveAndSurplusTy(convertValue(financialInputRequest.getOtherReserveAndSurplusTy()));
        
        financialInputRequestString.setMinorityInterestFy(convertValue(financialInputRequest.getMinorityInterestFy()));
        financialInputRequestString.setMinorityInterestSy(convertValue(financialInputRequest.getMinorityInterestSy()));
        financialInputRequestString.setMinorityInterestTy(convertValue(financialInputRequest.getMinorityInterestTy()));
        
        financialInputRequestString.setSecuredLoansFy(convertValue(financialInputRequest.getSecuredLoansFy()));
        financialInputRequestString.setSecuredLoansSy(convertValue(financialInputRequest.getSecuredLoansSy()));
        financialInputRequestString.setSecuredLoansTy(convertValue(financialInputRequest.getSecuredLoansTy()));
        
        financialInputRequestString.setUnsecuredLoansPromotersFy(convertValue(financialInputRequest.getUnsecuredLoansPromotersFy()));
        financialInputRequestString.setUnsecuredLoansPromotersSy(convertValue(financialInputRequest.getUnsecuredLoansPromotersSy()));
        financialInputRequestString.setUnsecuredLoansPromotersTy(convertValue(financialInputRequest.getUnsecuredLoansPromotersTy()));
        
        financialInputRequestString.setUnsecuredLoansOthersFy(convertValue(financialInputRequest.getUnsecuredLoansOthersFy()));
        financialInputRequestString.setUnsecuredLoansOthersSy(convertValue(financialInputRequest.getUnsecuredLoansOthersSy()));
        financialInputRequestString.setUnsecuredLoansOthersTy(convertValue(financialInputRequest.getUnsecuredLoansOthersTy()));
        
        financialInputRequestString.setDeferredTaxLiablitiesFy(convertValue(financialInputRequest.getDeferredTaxLiablitiesFy()));
        financialInputRequestString.setDeferredTaxLiablitiesSy(convertValue(financialInputRequest.getDeferredTaxLiablitiesSy()));
        financialInputRequestString.setDeferredTaxLiablitiesTy(convertValue(financialInputRequest.getDeferredTaxLiablitiesTy()));
        
        financialInputRequestString.setOtherLongTermLiablitiesFy(convertValue(financialInputRequest.getOtherLongTermLiablitiesFy()));
        financialInputRequestString.setOtherLongTermLiablitiesSy(convertValue(financialInputRequest.getOtherLongTermLiablitiesSy()));
        financialInputRequestString.setOtherLongTermLiablitiesTy(convertValue(financialInputRequest.getOtherLongTermLiablitiesTy()));
        
        financialInputRequestString.setOtherBorrowingFy(convertValue(financialInputRequest.getOtherBorrowingFy()));
        financialInputRequestString.setOtherBorrowingSy(convertValue(financialInputRequest.getOtherBorrowingSy()));
        financialInputRequestString.setOtherBorrowingTy(convertValue(financialInputRequest.getOtherBorrowingTy()));
        
        financialInputRequestString.setLongTermProvisionFy(convertValue(financialInputRequest.getLongTermProvisionFy()));
        financialInputRequestString.setLongTermProvisionSy(convertValue(financialInputRequest.getLongTermProvisionSy()));
        financialInputRequestString.setLongTermProvisionTy(convertValue(financialInputRequest.getLongTermProvisionTy()));
		
        financialInputRequestString.setTradePayablesFy(convertValue(financialInputRequest.getTradePayablesFy()));
        financialInputRequestString.setTradePayablesSy(convertValue(financialInputRequest.getTradePayablesSy()));
        financialInputRequestString.setTradePayablesTy(convertValue(financialInputRequest.getTradePayablesTy()));
        
        financialInputRequestString.setOtherCurruntLiablitiesFy(convertValue(financialInputRequest.getOtherCurruntLiablitiesFy()));
        financialInputRequestString.setOtherCurruntLiablitiesSy(convertValue(financialInputRequest.getOtherCurruntLiablitiesSy()));
        financialInputRequestString.setOtherCurruntLiablitiesTy(convertValue(financialInputRequest.getOtherCurruntLiablitiesTy()));
        
        financialInputRequestString.setShortTermProvisionFy(convertValue(financialInputRequest.getShortTermProvisionFy()));
        financialInputRequestString.setShortTermProvisionSy(convertValue(financialInputRequest.getShortTermProvisionSy()));
        financialInputRequestString.setShortTermProvisionTy(convertValue(financialInputRequest.getShortTermProvisionTy()));
        
        financialInputRequestString.setGrossBlockFy(convertValue(financialInputRequest.getGrossBlockFy()));
        financialInputRequestString.setGrossBlockSy(convertValue(financialInputRequest.getGrossBlockSy()));
        financialInputRequestString.setGrossBlockTy(convertValue(financialInputRequest.getGrossBlockTy()));
        
        financialInputRequestString.setLessAccumulatedDepreFy(convertValue(financialInputRequest.getLessAccumulatedDepreFy()));
        financialInputRequestString.setLessAccumulatedDepreSy(convertValue(financialInputRequest.getLessAccumulatedDepreSy()));
        financialInputRequestString.setLessAccumulatedDepreTy(convertValue(financialInputRequest.getLessAccumulatedDepreTy()));
        
        financialInputRequestString.setImpairmentofAssetFy(convertValue(financialInputRequest.getImpairmentofAssetFy()));
        financialInputRequestString.setImpairmentofAssetSy(convertValue(financialInputRequest.getImpairmentofAssetSy()));
        financialInputRequestString.setImpairmentofAssetTy(convertValue(financialInputRequest.getImpairmentofAssetTy()));
        
        financialInputRequestString.setCapitalWorkInProgressFy(convertValue(financialInputRequest.getCapitalWorkInProgressFy()));
        financialInputRequestString.setCapitalWorkInProgressSy(convertValue(financialInputRequest.getCapitalWorkInProgressSy()));
        financialInputRequestString.setCapitalWorkInProgressTy(convertValue(financialInputRequest.getCapitalWorkInProgressTy()));
        
        financialInputRequestString.setIntengibleAssetsFy(convertValue(financialInputRequest.getIntengibleAssetsFy()));
        financialInputRequestString.setIntengibleAssetsSy(convertValue(financialInputRequest.getIntengibleAssetsSy()));
        financialInputRequestString.setIntengibleAssetsTy(convertValue(financialInputRequest.getIntengibleAssetsTy()));
        
        financialInputRequestString.setPreOperativeExpeFy(convertValue(financialInputRequest.getPreOperativeExpeFy()));
        financialInputRequestString.setPreOperativeExpeSy(convertValue(financialInputRequest.getPreOperativeExpeSy()));
        financialInputRequestString.setPreOperativeExpeTy(convertValue(financialInputRequest.getPreOperativeExpeTy()));
        
        financialInputRequestString.setAssetInTransitFy(convertValue(financialInputRequest.getAssetInTransitFy()));
        financialInputRequestString.setAssetInTransitSy(convertValue(financialInputRequest.getAssetInTransitSy()));
        financialInputRequestString.setAssetInTransitTy(convertValue(financialInputRequest.getAssetInTransitTy()));
        
        financialInputRequestString.setInvestmentInSubsidiariesFy(convertValue(financialInputRequest.getInvestmentInSubsidiariesFy()));
        financialInputRequestString.setInvestmentInSubsidiariesSy(convertValue(financialInputRequest.getInvestmentInSubsidiariesSy()));
        financialInputRequestString.setInvestmentInSubsidiariesTy(convertValue(financialInputRequest.getInvestmentInSubsidiariesTy()));
        
        financialInputRequestString.setOtherInvestmentFy(convertValue(financialInputRequest.getOtherInvestmentFy()));
        financialInputRequestString.setOtherInvestmentSy(convertValue(financialInputRequest.getOtherInvestmentSy()));
        financialInputRequestString.setOtherInvestmentTy(convertValue(financialInputRequest.getOtherInvestmentTy()));
        
        financialInputRequestString.setLongTermLoansAndAdvaFy(convertValue(financialInputRequest.getLongTermLoansAndAdvaFy()));
        financialInputRequestString.setLongTermLoansAndAdvaSy(convertValue(financialInputRequest.getLongTermLoansAndAdvaSy()));
        financialInputRequestString.setLongTermLoansAndAdvaTy(convertValue(financialInputRequest.getLongTermLoansAndAdvaTy()));
        
        financialInputRequestString.setOtheNonCurruntAssetFy(convertValue(financialInputRequest.getOtheNonCurruntAssetFy()));
        financialInputRequestString.setOtheNonCurruntAssetSy(convertValue(financialInputRequest.getOtheNonCurruntAssetSy()));
        financialInputRequestString.setOtheNonCurruntAssetTy(convertValue(financialInputRequest.getOtheNonCurruntAssetTy()));
        
        financialInputRequestString.setInventoriesFy(convertValue(financialInputRequest.getInventoriesFy()));
        financialInputRequestString.setInventoriesSy(convertValue(financialInputRequest.getInventoriesSy()));
        financialInputRequestString.setInventoriesTy(convertValue(financialInputRequest.getInventoriesTy()));
        
        financialInputRequestString.setSundryDebtorsFy(convertValue(financialInputRequest.getSundryDebtorsFy()));
        financialInputRequestString.setSundryDebtorsSy(convertValue(financialInputRequest.getSundryDebtorsSy()));
        financialInputRequestString.setSundryDebtorsTy(convertValue(financialInputRequest.getSundryDebtorsTy()));
        
        financialInputRequestString.setCashAndBankFy(convertValue(financialInputRequest.getCashAndBankFy()));
        financialInputRequestString.setCashAndBankSy(convertValue(financialInputRequest.getCashAndBankSy()));
        financialInputRequestString.setCashAndBankTy(convertValue(financialInputRequest.getCashAndBankTy()));
        
        financialInputRequestString.setOtherCurruntAssetFy(convertValue(financialInputRequest.getOtherCurruntAssetFy()));
        financialInputRequestString.setOtherCurruntAssetSy(convertValue(financialInputRequest.getOtherCurruntAssetSy()));
        financialInputRequestString.setOtherCurruntAssetTy(convertValue(financialInputRequest.getOtherCurruntAssetTy()));
        
        financialInputRequestString.setShortTermLoansAdvancesFy(convertValue(financialInputRequest.getShortTermLoansAdvancesFy()));
        financialInputRequestString.setShortTermLoansAdvancesSy(convertValue(financialInputRequest.getShortTermLoansAdvancesSy()));
        financialInputRequestString.setShortTermLoansAdvancesTy(convertValue(financialInputRequest.getShortTermLoansAdvancesTy()));
        
        financialInputRequestString.setContingentLiablitiesFy(convertValue(financialInputRequest.getContingentLiablitiesFy()));
        financialInputRequestString.setContingentLiablitiesSy(convertValue(financialInputRequest.getContingentLiablitiesSy()));
        financialInputRequestString.setContingentLiablitiestTy(convertValue(financialInputRequest.getContingentLiablitiestTy()));
		
		
		
		//Profit & Loss Statement
        
        
        //FOR CALCULATION OF NET SALE
        financialInputRequest.setNetSaleFy(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesFy(), financialInputRequest.getLessExciseDuityFy()));
      	financialInputRequest.setNetSaleSy(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesSy(), financialInputRequest.getLessExciseDuitySy()));
      	financialInputRequest.setNetSaleTy(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesTy(), financialInputRequest.getLessExciseDuityTy()));
      	//FOR DISPLAY OF NET SALE
      	financialInputRequestString.setNetSaleFy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesFy(), financialInputRequest.getLessExciseDuityFy())));
      	financialInputRequestString.setNetSaleSy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesSy(), financialInputRequest.getLessExciseDuitySy())));
      	financialInputRequestString.setNetSaleTy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesTy(), financialInputRequest.getLessExciseDuityTy())));
		
      	//FOR CALCULATION OF TOTAL EXPENDITUR
		financialInputRequest.setTotalExpenditureFy(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockFy(),financialInputRequest.getRawMaterialConsumedFy(),financialInputRequest.getPowerAndFuelCostFy(),financialInputRequest.getEmployeeCostFy(), financialInputRequest.getGeneralAndAdminExpeFy(),financialInputRequest.getSellingAndDistriExpeFy(),financialInputRequest.getMiscelExpeFy()), financialInputRequest.getLessExpeCapitaFy()));
		financialInputRequest.setTotalExpenditureSy(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockSy(),financialInputRequest.getRawMaterialConsumedSy(),financialInputRequest.getPowerAndFuelCostSy(),financialInputRequest.getEmployeeCostSy(), financialInputRequest.getGeneralAndAdminExpeSy(),financialInputRequest.getSellingAndDistriExpeSy(),financialInputRequest.getMiscelExpeSy()), financialInputRequest.getLessExpeCapitaSy()));
		financialInputRequest.setTotalExpenditureTy(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockTy(),financialInputRequest.getRawMaterialConsumedTy(),financialInputRequest.getPowerAndFuelCostTy(),financialInputRequest.getEmployeeCostTy(), financialInputRequest.getGeneralAndAdminExpeTy(),financialInputRequest.getSellingAndDistriExpeTy(),financialInputRequest.getMiscelExpeTy()), financialInputRequest.getLessExpeCapitaTy()));
		//FOR DISPLAY OF TOTAL EXPENDITURE
		financialInputRequestString.setTotalExpenditureFy(convertValue(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockFy(),financialInputRequest.getRawMaterialConsumedFy(),financialInputRequest.getPowerAndFuelCostFy(),financialInputRequest.getEmployeeCostFy(), financialInputRequest.getGeneralAndAdminExpeFy(),financialInputRequest.getSellingAndDistriExpeFy(),financialInputRequest.getMiscelExpeFy()), financialInputRequest.getLessExpeCapitaFy())));
		financialInputRequestString.setTotalExpenditureSy(convertValue(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockSy(),financialInputRequest.getRawMaterialConsumedSy(),financialInputRequest.getPowerAndFuelCostSy(),financialInputRequest.getEmployeeCostSy(), financialInputRequest.getGeneralAndAdminExpeSy(),financialInputRequest.getSellingAndDistriExpeSy(),financialInputRequest.getMiscelExpeSy()), financialInputRequest.getLessExpeCapitaSy())));
		financialInputRequestString.setTotalExpenditureTy(convertValue(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockTy(),financialInputRequest.getRawMaterialConsumedTy(),financialInputRequest.getPowerAndFuelCostTy(),financialInputRequest.getEmployeeCostTy(), financialInputRequest.getGeneralAndAdminExpeTy(),financialInputRequest.getSellingAndDistriExpeTy(),financialInputRequest.getMiscelExpeTy()), financialInputRequest.getLessExpeCapitaTy())));
		
		//FOR CALCULATION OF OPERATING PROFIT EXCL
		financialInputRequest.setOperatingProfitExclOiFy(CommonUtils.substractNumbers(financialInputRequest.getNetSaleFy(),financialInputRequest.getTotalExpenditureFy()));
		financialInputRequest.setOperatingProfitExclOiSy(CommonUtils.substractNumbers(financialInputRequest.getNetSaleSy(),financialInputRequest.getTotalExpenditureSy()));
		financialInputRequest.setOperatingProfitExclOiTy(CommonUtils.substractNumbers(financialInputRequest.getNetSaleTy(),financialInputRequest.getTotalExpenditureTy()));
		//FOR DISPLAY OF OPERATING PROFIT EXCL
		financialInputRequestString.setOperatingProfitExclOiFy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getNetSaleFy(),financialInputRequest.getTotalExpenditureFy())));
		financialInputRequestString.setOperatingProfitExclOiSy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getNetSaleSy(),financialInputRequest.getTotalExpenditureSy())));
		financialInputRequestString.setOperatingProfitExclOiTy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getNetSaleTy(),financialInputRequest.getTotalExpenditureTy())));
		
		//FOR CALCULATION OF OPERATING PROFIT EBITAD
		financialInputRequest.setOperatingProfitEbitadOiFy(CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiFy(),financialInputRequest.getOtherIncomeFy()));
		financialInputRequest.setOperatingProfitEbitadOiSy(CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiSy(),financialInputRequest.getOtherIncomeSy()));
		financialInputRequest.setOperatingProfitEbitadOiTy(CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiTy(),financialInputRequest.getOtherIncomeTy()));
		//FOR DISPLAY OF OPERATING PROFIT EBITAD
		financialInputRequestString.setOperatingProfitEbitadOiFy(convertValue(CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiFy(),financialInputRequest.getOtherIncomeFy())));
		financialInputRequestString.setOperatingProfitEbitadOiSy(convertValue(CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiSy(),financialInputRequest.getOtherIncomeSy())));
		financialInputRequestString.setOperatingProfitEbitadOiTy(convertValue(CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiTy(),financialInputRequest.getOtherIncomeTy())));
		
		//FOR CALCULATION OF PBDT
		financialInputRequest.setPbdtFy(CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiFy(), financialInputRequest.getInterestFy()));
		financialInputRequest.setPbdtSy(CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiSy(), financialInputRequest.getInterestSy()));
		financialInputRequest.setPbdtTy(CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiTy(), financialInputRequest.getInterestTy()));
		//FOR DISPLAY OF PBDT
		financialInputRequestString.setPbdtFy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiFy(), financialInputRequest.getInterestFy())));
		financialInputRequestString.setPbdtSy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiSy(), financialInputRequest.getInterestSy())));
		financialInputRequestString.setPbdtTy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiTy(), financialInputRequest.getInterestTy())));
		
		//FOR CALCULATION OF PROFIT BEFORE TAXATION
		financialInputRequest.setProfitBeforeTaxationFy(CommonUtils.substractNumbers(financialInputRequest.getPbdtFy(), financialInputRequest.getDepriciationFy()));
		financialInputRequest.setProfitBeforeTaxationSy(CommonUtils.substractNumbers(financialInputRequest.getPbdtSy(), financialInputRequest.getDepriciationSy()));
		financialInputRequest.setProfitBeforeTaxationTy(CommonUtils.substractNumbers(financialInputRequest.getPbdtTy(), financialInputRequest.getDepriciationTy()));
		//FOR DISPLAY OF PROFIT BEFORE TAXATION
		financialInputRequestString.setProfitBeforeTaxationFy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getPbdtFy(), financialInputRequest.getDepriciationFy())));
		financialInputRequestString.setProfitBeforeTaxationSy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getPbdtSy(), financialInputRequest.getDepriciationSy())));
		financialInputRequestString.setProfitBeforeTaxationTy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getPbdtTy(), financialInputRequest.getDepriciationTy())));
		//FOR CALCULATION OF PROFIT BEFORE TAX
		financialInputRequest.setProfitBeforeTaxFy(CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationFy(), financialInputRequest.getExceptionalIncomeFy()));
		financialInputRequest.setProfitBeforeTaxSy(CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationSy(), financialInputRequest.getExceptionalIncomeSy()));
		financialInputRequest.setProfitBeforeTaxTy(CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationTy(), financialInputRequest.getExceptionalIncomeTy()));
		//FOR DISPLAY OF PROFIT BEFORE TAX
		financialInputRequestString.setProfitBeforeTaxFy(convertValue(CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationFy(), financialInputRequest.getExceptionalIncomeFy())));
		financialInputRequestString.setProfitBeforeTaxSy(convertValue(CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationSy(), financialInputRequest.getExceptionalIncomeSy())));
		financialInputRequestString.setProfitBeforeTaxTy(convertValue(CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationTy(), financialInputRequest.getExceptionalIncomeTy())));
		//FOR CALCULATION OF PROFIT AFTER TAX
		financialInputRequest.setProfitAfterTaxFy(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxFy(), financialInputRequest.getProvisionForTaxFy()));
		financialInputRequest.setProfitAfterTaxSy(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxSy(), financialInputRequest.getProvisionForTaxSy()));
		financialInputRequest.setProfitAfterTaxTy(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxTy(), financialInputRequest.getProvisionForTaxTy()));
		//FOR DISPLAY OF PROFIT AFTER TAX
		financialInputRequestString.setProfitAfterTaxFy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxFy(), financialInputRequest.getProvisionForTaxFy())));
		financialInputRequestString.setProfitAfterTaxSy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxSy(), financialInputRequest.getProvisionForTaxSy())));
		financialInputRequestString.setProfitAfterTaxTy(convertValue(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxTy(), financialInputRequest.getProvisionForTaxTy())));
		
		
		if(financialInputRequest.getDividendPayOutFy() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequest.getDividendPayOutFy()) || financialInputRequest.getShareFaceValue() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareFaceValue()) || financialInputRequest.getShareCapitalFy() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareCapitalFy()))
			financialInputRequestString.setEquityDividendFy("0.0");
		else
			financialInputRequestString.setEquityDividendFy(convertValue((financialInputRequest.getDividendPayOutFy()*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalFy())));
		
		if(financialInputRequest.getDividendPayOutSy() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequest.getDividendPayOutSy()) || financialInputRequest.getShareFaceValue() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareFaceValue()) || financialInputRequest.getShareCapitalSy() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareCapitalSy()))
			financialInputRequestString.setEquityDividendSy("0.0");
		else
			financialInputRequestString.setEquityDividendSy(convertValue(financialInputRequest.getDividendPayOutSy()*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalSy()));
		
		if(financialInputRequest.getDividendPayOutTy() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequest.getDividendPayOutTy()) || financialInputRequest.getShareFaceValue() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareFaceValue()) || financialInputRequest.getShareCapitalTy() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareCapitalTy()))
			financialInputRequestString.setEquityDividendTy("0.0");
		else
			financialInputRequestString.setEquityDividendTy(convertValue(financialInputRequest.getDividendPayOutTy()*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalTy()));
		
		
		if(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareFaceValue()) && !CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareCapitalFy())) {
			if(financialInputRequest.getShareFaceValue() !=0 && financialInputRequest.getShareCapitalFy() !=0) {
				double total = financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalFy();
				if(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getProfitAfterTaxFy()) && financialInputRequest.getProfitAfterTaxFy() !=0) {
					financialInputRequestString.setEarningPerShareFy(convertValue(financialInputRequest.getProfitAfterTaxFy() * total));
				}else {
					financialInputRequestString.setEarningPerShareFy("0.0");
				}
			}else {
				financialInputRequestString.setEarningPerShareFy("0.0");
			}
		}else {
			financialInputRequestString.setEarningPerShareFy("0.0");
		}
		
		
		if(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareFaceValue()) && !CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareCapitalSy())) {
			if(financialInputRequest.getShareFaceValue() !=0 && financialInputRequest.getShareCapitalSy() !=0) {
				double total = financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalSy();
				if(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getProfitAfterTaxSy()) && financialInputRequest.getProfitAfterTaxSy() !=0) {
					financialInputRequestString.setEarningPerShareSy(convertValue(financialInputRequest.getProfitAfterTaxSy() * total));
				}else {
					financialInputRequestString.setEarningPerShareSy("0.0");
				}
			}else {
				financialInputRequestString.setEarningPerShareSy("0.0");
			}
		}else {
			financialInputRequestString.setEarningPerShareSy("0.0");
		}
		
		
		if(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareFaceValue()) && !CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareCapitalTy())) {
			if(financialInputRequest.getShareFaceValue() !=0 && financialInputRequest.getShareCapitalTy() !=0) {
				double total = financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalTy();
				if(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getProfitAfterTaxTy()) && financialInputRequest.getProfitAfterTaxTy() !=0) {
					financialInputRequestString.setEarningPerShareTy(convertValue(financialInputRequest.getProfitAfterTaxTy() * total));
				}else {
					financialInputRequestString.setEarningPerShareTy("0.0");
				}
			}else {
				financialInputRequestString.setEarningPerShareTy("0.0");
			}
		}else {
			financialInputRequestString.setEarningPerShareTy("0.0");
		}
		
		
		//Balance Sheet -Equities and Liabilities
		
		//FOR CALCULATION OF SHARE HOLDING
		financialInputRequest.setShareHolderFundsFy(CommonUtils.addNumbers(financialInputRequest.getShareCapitalFy(),financialInputRequest.getShareWarrantOutstandingsFy(),financialInputRequest.getRevalationReserveFy(),financialInputRequest.getOtherReserveAndSurplusFy()));
		financialInputRequest.setShareHolderFundsSy(CommonUtils.addNumbers(financialInputRequest.getShareCapitalSy(),financialInputRequest.getShareWarrantOutstandingsSy(),financialInputRequest.getRevalationReserveSy(),financialInputRequest.getOtherReserveAndSurplusSy()));
		financialInputRequest.setShareHolderFundsTy(CommonUtils.addNumbers(financialInputRequest.getShareCapitalTy(),financialInputRequest.getShareWarrantOutstandingsTy(),financialInputRequest.getRevalationReserveTy(),financialInputRequest.getOtherReserveAndSurplusTy()));
		//FOR DISPLAY OF SHARE HOLDING
		financialInputRequestString.setShareHolderFundsFy(convertValue(CommonUtils.addNumbers(financialInputRequest.getShareCapitalFy(),financialInputRequest.getShareWarrantOutstandingsFy(),financialInputRequest.getRevalationReserveFy(),financialInputRequest.getOtherReserveAndSurplusFy())));
		financialInputRequestString.setShareHolderFundsSy(convertValue(CommonUtils.addNumbers(financialInputRequest.getShareCapitalSy(),financialInputRequest.getShareWarrantOutstandingsSy(),financialInputRequest.getRevalationReserveSy(),financialInputRequest.getOtherReserveAndSurplusSy())));
		financialInputRequestString.setShareHolderFundsTy(convertValue(CommonUtils.addNumbers(financialInputRequest.getShareCapitalTy(),financialInputRequest.getShareWarrantOutstandingsTy(),financialInputRequest.getRevalationReserveTy(),financialInputRequest.getOtherReserveAndSurplusTy())));
		
		//FOR CALCULATION OF TOTAL NON CURRUNT LIABILITIES
		financialInputRequest.setTotalNonCurruntLiablitiesFy(CommonUtils.addNumbers(financialInputRequest.getMinorityInterestFy(), financialInputRequest.getSecuredLoansFy(), financialInputRequest.getUnsecuredLoansOthersFy(),financialInputRequest.getUnsecuredLoansPromotersFy(),financialInputRequest.getDeferredTaxLiablitiesFy(),financialInputRequest.getOtherLongTermLiablitiesFy(),financialInputRequest.getOtherBorrowingFy(),financialInputRequest.getLongTermProvisionFy()));
		financialInputRequest.setTotalNonCurruntLiablitiesSy(CommonUtils.addNumbers(financialInputRequest.getMinorityInterestSy(), financialInputRequest.getSecuredLoansSy(), financialInputRequest.getUnsecuredLoansOthersSy(),financialInputRequest.getUnsecuredLoansPromotersSy(),financialInputRequest.getDeferredTaxLiablitiesSy(),financialInputRequest.getOtherLongTermLiablitiesSy(),financialInputRequest.getOtherBorrowingSy(),financialInputRequest.getLongTermProvisionSy()));
		financialInputRequest.setTotalNonCurruntLiablitiesTy(CommonUtils.addNumbers(financialInputRequest.getMinorityInterestTy(), financialInputRequest.getSecuredLoansTy(), financialInputRequest.getUnsecuredLoansOthersTy(),financialInputRequest.getUnsecuredLoansPromotersTy(),financialInputRequest.getDeferredTaxLiablitiesTy(),financialInputRequest.getOtherLongTermLiablitiesTy(),financialInputRequest.getOtherBorrowingTy(),financialInputRequest.getLongTermProvisionTy()));
		//FOR DISPLAY OF TOTAL NON CURRUNT LIABILITIES
		financialInputRequestString.setTotalNonCurruntLiablitiesFy(convertValue(CommonUtils.addNumbers(financialInputRequest.getMinorityInterestFy(), financialInputRequest.getSecuredLoansFy(), financialInputRequest.getUnsecuredLoansOthersFy(),financialInputRequest.getUnsecuredLoansPromotersFy(),financialInputRequest.getDeferredTaxLiablitiesFy(),financialInputRequest.getOtherLongTermLiablitiesFy(),financialInputRequest.getOtherBorrowingFy(),financialInputRequest.getLongTermProvisionFy())));
		financialInputRequestString.setTotalNonCurruntLiablitiesSy(convertValue(CommonUtils.addNumbers(financialInputRequest.getMinorityInterestSy(), financialInputRequest.getSecuredLoansSy(), financialInputRequest.getUnsecuredLoansOthersSy(),financialInputRequest.getUnsecuredLoansPromotersSy(),financialInputRequest.getDeferredTaxLiablitiesSy(),financialInputRequest.getOtherLongTermLiablitiesSy(),financialInputRequest.getOtherBorrowingSy(),financialInputRequest.getLongTermProvisionSy())));
		financialInputRequestString.setTotalNonCurruntLiablitiesTy(convertValue(CommonUtils.addNumbers(financialInputRequest.getMinorityInterestTy(), financialInputRequest.getSecuredLoansTy(), financialInputRequest.getUnsecuredLoansOthersTy(),financialInputRequest.getUnsecuredLoansPromotersTy(),financialInputRequest.getDeferredTaxLiablitiesTy(),financialInputRequest.getOtherLongTermLiablitiesTy(),financialInputRequest.getOtherBorrowingTy(),financialInputRequest.getLongTermProvisionTy())));
		//FOR CALCULATION OF TOTAL CURRUNT LIABLITIES
		financialInputRequest.setTotalCurruntLiablitiesFy(CommonUtils.addNumbers(financialInputRequest.getTradePayablesFy(), financialInputRequest.getOtherCurruntLiablitiesFy(), financialInputRequest.getShortTermProvisionFy()));
		financialInputRequest.setTotalCurruntLiablitiesSy(CommonUtils.addNumbers(financialInputRequest.getTradePayablesSy(), financialInputRequest.getOtherCurruntLiablitiesSy(), financialInputRequest.getShortTermProvisionSy()));
		financialInputRequest.setTotalCurruntLiablitiesTy(CommonUtils.addNumbers(financialInputRequest.getTradePayablesTy(), financialInputRequest.getOtherCurruntLiablitiesTy(), financialInputRequest.getShortTermProvisionTy()));
		//FOR DISPLAY OF TOTAL CURRUNT LIABLITIES
		financialInputRequestString.setTotalCurruntLiablitiesFy(convertValue(CommonUtils.addNumbers(financialInputRequest.getTradePayablesFy(), financialInputRequest.getOtherCurruntLiablitiesFy(), financialInputRequest.getShortTermProvisionFy())));
		financialInputRequestString.setTotalCurruntLiablitiesSy(convertValue(CommonUtils.addNumbers(financialInputRequest.getTradePayablesSy(), financialInputRequest.getOtherCurruntLiablitiesSy(), financialInputRequest.getShortTermProvisionSy())));
		financialInputRequestString.setTotalCurruntLiablitiesTy(convertValue(CommonUtils.addNumbers(financialInputRequest.getTradePayablesTy(), financialInputRequest.getOtherCurruntLiablitiesTy(), financialInputRequest.getShortTermProvisionTy())));
		//FOR CALCULATION OF TOTAL LIABILITIES
		financialInputRequest.setTotalLiablitiesFy(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsFy(), financialInputRequest.getTotalNonCurruntLiablitiesFy(), financialInputRequest.getTotalCurruntLiablitiesFy()));
		financialInputRequest.setTotalLiablitiesSy(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsSy(), financialInputRequest.getTotalNonCurruntLiablitiesSy(), financialInputRequest.getTotalCurruntLiablitiesSy()));
		financialInputRequest.setTotalLiablitiesTy(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsTy(), financialInputRequest.getTotalNonCurruntLiablitiesTy(), financialInputRequest.getTotalCurruntLiablitiesTy()));
		//FOR DISPLAY OF TOTAL LIABLITIES
		financialInputRequestString.setTotalLiablitiesFy(convertValue(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsFy(), financialInputRequest.getTotalNonCurruntLiablitiesFy(), financialInputRequest.getTotalCurruntLiablitiesFy())));
		financialInputRequestString.setTotalLiablitiesSy(convertValue(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsSy(), financialInputRequest.getTotalNonCurruntLiablitiesSy(), financialInputRequest.getTotalCurruntLiablitiesSy())));
		financialInputRequestString.setTotalLiablitiesTy(convertValue(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsTy(), financialInputRequest.getTotalNonCurruntLiablitiesTy(), financialInputRequest.getTotalCurruntLiablitiesTy())));
		
		//Balance Sheet -ASSETS
		//FOR CALCULATION OF NET BLOCK
		financialInputRequest.setNetBlockFy(CommonUtils.substractThreeNumbers(financialInputRequest.getGrossBlockFy(), financialInputRequest.getLessAccumulatedDepreFy(),financialInputRequest.getImpairmentofAssetFy()));
		financialInputRequest.setNetBlockSy(CommonUtils.substractThreeNumbers(financialInputRequest.getGrossBlockSy(), financialInputRequest.getLessAccumulatedDepreSy(),financialInputRequest.getImpairmentofAssetSy()));
		financialInputRequest.setNetBlockTy(CommonUtils.substractThreeNumbers(financialInputRequest.getGrossBlockTy(), financialInputRequest.getLessAccumulatedDepreTy(),financialInputRequest.getImpairmentofAssetTy()));
		//FOR DISPLAY OF NET BLOCK
		financialInputRequestString.setNetBlockFy(convertValue(CommonUtils.substractThreeNumbers(financialInputRequest.getGrossBlockFy(), financialInputRequest.getLessAccumulatedDepreFy(),financialInputRequest.getImpairmentofAssetFy())));
		financialInputRequestString.setNetBlockSy(convertValue(CommonUtils.substractThreeNumbers(financialInputRequest.getGrossBlockSy(), financialInputRequest.getLessAccumulatedDepreSy(),financialInputRequest.getImpairmentofAssetSy())));
		financialInputRequestString.setNetBlockTy(convertValue(CommonUtils.substractThreeNumbers(financialInputRequest.getGrossBlockTy(), financialInputRequest.getLessAccumulatedDepreTy(),financialInputRequest.getImpairmentofAssetTy())));
		//FOR CALCULATION OF TOTAL NON CURRUNT ASSET
		financialInputRequest.setTotalNonCurruntAssetFy(CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressFy(), financialInputRequest.getIntengibleAssetsFy(), financialInputRequest.getPreOperativeExpeFy(), financialInputRequest.getAssetInTransitFy(), financialInputRequest.getInvestmentInSubsidiariesFy(), financialInputRequest.getOtherInvestmentFy(), financialInputRequest.getLongTermLoansAndAdvaFy(), financialInputRequest.getOtheNonCurruntAssetFy()));
		financialInputRequest.setTotalNonCurruntAssetSy(CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressSy(), financialInputRequest.getIntengibleAssetsSy(), financialInputRequest.getPreOperativeExpeSy(), financialInputRequest.getAssetInTransitSy(), financialInputRequest.getInvestmentInSubsidiariesSy(), financialInputRequest.getOtherInvestmentSy(), financialInputRequest.getLongTermLoansAndAdvaSy(), financialInputRequest.getOtheNonCurruntAssetSy()));
		financialInputRequest.setTotalNonCurruntAssetTy(CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressTy(), financialInputRequest.getIntengibleAssetsTy(), financialInputRequest.getPreOperativeExpeTy(), financialInputRequest.getAssetInTransitTy(), financialInputRequest.getInvestmentInSubsidiariesTy(), financialInputRequest.getOtherInvestmentTy(), financialInputRequest.getLongTermLoansAndAdvaTy(), financialInputRequest.getOtheNonCurruntAssetTy()));
		//FOR DISPLAY OF TOTAL NON CURRUNT ASSET
		financialInputRequestString.setTotalNonCurruntAssetFy(convertValue(CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressFy(), financialInputRequest.getIntengibleAssetsFy(), financialInputRequest.getPreOperativeExpeFy(), financialInputRequest.getAssetInTransitFy(), financialInputRequest.getInvestmentInSubsidiariesFy(), financialInputRequest.getOtherInvestmentFy(), financialInputRequest.getLongTermLoansAndAdvaFy(), financialInputRequest.getOtheNonCurruntAssetFy())));
		financialInputRequestString.setTotalNonCurruntAssetSy(convertValue(CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressSy(), financialInputRequest.getIntengibleAssetsSy(), financialInputRequest.getPreOperativeExpeSy(), financialInputRequest.getAssetInTransitSy(), financialInputRequest.getInvestmentInSubsidiariesSy(), financialInputRequest.getOtherInvestmentSy(), financialInputRequest.getLongTermLoansAndAdvaSy(), financialInputRequest.getOtheNonCurruntAssetSy())));
		financialInputRequestString.setTotalNonCurruntAssetTy(convertValue(CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressTy(), financialInputRequest.getIntengibleAssetsTy(), financialInputRequest.getPreOperativeExpeTy(), financialInputRequest.getAssetInTransitTy(), financialInputRequest.getInvestmentInSubsidiariesTy(), financialInputRequest.getOtherInvestmentTy(), financialInputRequest.getLongTermLoansAndAdvaTy(), financialInputRequest.getOtheNonCurruntAssetTy())));
		//FOR CALCULATION OF TOTAL CURRENT ASSET
		financialInputRequest.setTotalCurruntAssetFy(CommonUtils.addNumbers(financialInputRequest.getInventoriesFy(), financialInputRequest.getSundryDebtorsFy(), financialInputRequest.getCashAndBankFy(), financialInputRequest.getOtherCurruntAssetFy(), financialInputRequest.getShortTermLoansAdvancesFy()));
		financialInputRequest.setTotalCurruntAssetSy(CommonUtils.addNumbers(financialInputRequest.getInventoriesSy(), financialInputRequest.getSundryDebtorsSy(), financialInputRequest.getCashAndBankSy(), financialInputRequest.getOtherCurruntAssetSy(), financialInputRequest.getShortTermLoansAdvancesSy()));
		financialInputRequest.setTotalCurruntAssetTy(CommonUtils.addNumbers(financialInputRequest.getInventoriesTy(), financialInputRequest.getSundryDebtorsTy(), financialInputRequest.getCashAndBankTy(), financialInputRequest.getOtherCurruntAssetTy(), financialInputRequest.getShortTermLoansAdvancesTy()));
		//FOR DISPLAY OF TOTAL CURRENT ASSET
		financialInputRequestString.setTotalCurruntAssetFy(convertValue(CommonUtils.addNumbers(financialInputRequest.getInventoriesFy(), financialInputRequest.getSundryDebtorsFy(), financialInputRequest.getCashAndBankFy(), financialInputRequest.getOtherCurruntAssetFy(), financialInputRequest.getShortTermLoansAdvancesFy())));
		financialInputRequestString.setTotalCurruntAssetSy(convertValue(CommonUtils.addNumbers(financialInputRequest.getInventoriesSy(), financialInputRequest.getSundryDebtorsSy(), financialInputRequest.getCashAndBankSy(), financialInputRequest.getOtherCurruntAssetSy(), financialInputRequest.getShortTermLoansAdvancesSy())));
		financialInputRequestString.setTotalCurruntAssetTy(convertValue(CommonUtils.addNumbers(financialInputRequest.getInventoriesTy(), financialInputRequest.getSundryDebtorsTy(), financialInputRequest.getCashAndBankTy(), financialInputRequest.getOtherCurruntAssetTy(), financialInputRequest.getShortTermLoansAdvancesTy())));
		//FOR CALCULATION OF TOTAL ASSET
		financialInputRequest.setTotalAssetFy(CommonUtils.addNumbers(financialInputRequest.getNetBlockFy(), financialInputRequest.getTotalCurruntAssetFy(), financialInputRequest.getTotalNonCurruntAssetFy()));
		financialInputRequest.setTotalAssetSy(CommonUtils.addNumbers(financialInputRequest.getNetBlockSy(), financialInputRequest.getTotalCurruntAssetSy(), financialInputRequest.getTotalNonCurruntAssetSy()));
		financialInputRequest.setTotalAssetTy(CommonUtils.addNumbers(financialInputRequest.getNetBlockTy(), financialInputRequest.getTotalCurruntAssetTy(), financialInputRequest.getTotalNonCurruntAssetTy()));
		//FOR DISPLAY OF TOTAL ASSET
		financialInputRequestString.setTotalAssetFy(convertValue(CommonUtils.addNumbers(financialInputRequest.getNetBlockFy(), financialInputRequest.getTotalCurruntAssetFy(), financialInputRequest.getTotalNonCurruntAssetFy())));
		financialInputRequestString.setTotalAssetSy(convertValue(CommonUtils.addNumbers(financialInputRequest.getNetBlockSy(), financialInputRequest.getTotalCurruntAssetSy(), financialInputRequest.getTotalNonCurruntAssetSy())));
		financialInputRequestString.setTotalAssetTy(convertValue(CommonUtils.addNumbers(financialInputRequest.getNetBlockTy(), financialInputRequest.getTotalCurruntAssetTy(), financialInputRequest.getTotalNonCurruntAssetTy())));
		
		
		if(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareFaceValue()) && !CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareCapitalFy())) {
			if(financialInputRequest.getShareFaceValue() !=0 && financialInputRequest.getShareCapitalFy() !=0) {
				double total = financialInputRequest.getShareCapitalFy()/financialInputRequest.getShareFaceValue();
				if(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareHolderFundsFy()) && financialInputRequest.getShareHolderFundsFy() !=0) {
					financialInputRequestString.setBookValueFy(convertValue(financialInputRequest.getShareHolderFundsFy() / total));
				}else {
					financialInputRequestString.setBookValueFy("0.0");
				}
			}else {
				financialInputRequestString.setBookValueFy("0.0");
			}
		}else {
			financialInputRequestString.setBookValueFy("0.0");
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareFaceValue()) && !CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareCapitalSy())) {
			if(financialInputRequest.getShareFaceValue() !=0 && financialInputRequest.getShareCapitalSy() !=0) {
				double total = financialInputRequest.getShareCapitalSy()/financialInputRequest.getShareFaceValue();
				if(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareHolderFundsSy()) && financialInputRequest.getShareHolderFundsSy() !=0) {
					financialInputRequestString.setBookValueSy(convertValue(financialInputRequest.getShareHolderFundsSy() / total));
				}else {
					financialInputRequestString.setBookValueSy("0.0");
				}
			}else {
				financialInputRequestString.setBookValueSy("0.0");
			}
		}else {
			financialInputRequestString.setBookValueSy("0.0");
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareFaceValue()) && !CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareCapitalTy())) {
			if(financialInputRequest.getShareFaceValue() !=0 && financialInputRequest.getShareCapitalTy() !=0) {
				double total = financialInputRequest.getShareCapitalTy()/financialInputRequest.getShareFaceValue();
				if(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareHolderFundsTy()) && financialInputRequest.getShareHolderFundsTy() !=0) {
					financialInputRequestString.setBookValueTy(convertValue(financialInputRequest.getShareHolderFundsTy() / total));
				}else {
					financialInputRequestString.setBookValueTy("0.0");
				}
			}else {
				financialInputRequestString.setBookValueTy("0.0");
			}
		}else {
			financialInputRequestString.setBookValueTy("0.0");
		}
		
		/*================================================BEGIN CODE FOR RATIO ANALYSIS===========================================================*/
		
		 //CASH FLOW 
		financialInputRequestString.setEbitdaFy(convertValue(financialInputRequest.getOperatingProfitEbitadOiFy()));
		financialInputRequestString.setEbitdaSy(convertValue(financialInputRequest.getOperatingProfitEbitadOiSy()));
		financialInputRequest.setEbitdaFy(financialInputRequest.getOperatingProfitEbitadOiFy());
		financialInputRequest.setEbitdaSy(financialInputRequest.getOperatingProfitEbitadOiSy());
		
		financialInputRequestString.setInterestPaidFy(convertValue(financialInputRequest.getInterestFy()));
		financialInputRequestString.setInterestPaidSy(convertValue(financialInputRequest.getInterestSy()));
		financialInputRequest.setInterestPaidFy(financialInputRequest.getInterestFy());
		financialInputRequest.setInterestPaidSy(financialInputRequest.getInterestSy());
		
		financialInputRequestString.setIncreaseWorkingCapitalFy(convertValue(financialInputRequest.getInventoriesFy()+financialInputRequest.getSundryDebtorsFy()+financialInputRequest.getOtherCurruntAssetFy()-financialInputRequest.getInventoriesSy()-financialInputRequest.getSundryDebtorsSy()-financialInputRequest.getOtherCurruntAssetSy()+financialInputRequest.getTradePayablesSy()+financialInputRequest.getOtherCurruntLiablitiesSy()+financialInputRequest.getShortTermProvisionSy()-financialInputRequest.getTradePayablesFy()-financialInputRequest.getOtherCurruntLiablitiesFy()-financialInputRequest.getShortTermProvisionFy()));
		financialInputRequestString.setIncreaseWorkingCapitalSy(convertValue(financialInputRequest.getInventoriesSy()+financialInputRequest.getSundryDebtorsSy()+financialInputRequest.getOtherCurruntAssetSy()-financialInputRequest.getInventoriesTy()-financialInputRequest.getSundryDebtorsTy()-financialInputRequest.getOtherCurruntAssetTy()+financialInputRequest.getTradePayablesTy()+financialInputRequest.getOtherCurruntLiablitiesTy()+financialInputRequest.getShortTermProvisionTy()-financialInputRequest.getTradePayablesSy()-financialInputRequest.getOtherCurruntLiablitiesSy()-financialInputRequest.getShortTermProvisionSy()));
		financialInputRequest.setIncreaseWorkingCapitalFy(financialInputRequest.getInventoriesFy()+financialInputRequest.getSundryDebtorsFy()+financialInputRequest.getOtherCurruntAssetFy()-financialInputRequest.getInventoriesSy()-financialInputRequest.getSundryDebtorsSy()-financialInputRequest.getOtherCurruntAssetSy()+financialInputRequest.getTradePayablesSy()+financialInputRequest.getOtherCurruntLiablitiesSy()+financialInputRequest.getShortTermProvisionSy()-financialInputRequest.getTradePayablesFy()-financialInputRequest.getOtherCurruntLiablitiesFy()-financialInputRequest.getShortTermProvisionFy());
		financialInputRequest.setIncreaseWorkingCapitalSy(financialInputRequest.getInventoriesSy()+financialInputRequest.getSundryDebtorsSy()+financialInputRequest.getOtherCurruntAssetSy()-financialInputRequest.getInventoriesTy()-financialInputRequest.getSundryDebtorsTy()-financialInputRequest.getOtherCurruntAssetTy()+financialInputRequest.getTradePayablesTy()+financialInputRequest.getOtherCurruntLiablitiesTy()+financialInputRequest.getShortTermProvisionTy()-financialInputRequest.getTradePayablesSy()-financialInputRequest.getOtherCurruntLiablitiesSy()-financialInputRequest.getShortTermProvisionSy());
		
		financialInputRequestString.setTaxPaidFy(convertValue(financialInputRequest.getProvisionForTaxFy()));
		financialInputRequestString.setTaxPaidSy(convertValue(financialInputRequest.getProvisionForTaxSy()));
		financialInputRequest.setTaxPaidFy(financialInputRequest.getProvisionForTaxFy());
		financialInputRequest.setTaxPaidSy(financialInputRequest.getProvisionForTaxSy());
		
		financialInputRequestString.setCashFromOperatingFy(convertValue(financialInputRequest.getEbitdaFy()-financialInputRequest.getInterestPaidFy()-financialInputRequest.getIncreaseWorkingCapitalFy()-financialInputRequest.getTaxPaidFy()));
		financialInputRequestString.setCashFromOperatingSy(convertValue(financialInputRequest.getEbitdaSy()-financialInputRequest.getInterestPaidSy()-financialInputRequest.getIncreaseWorkingCapitalSy()-financialInputRequest.getTaxPaidSy()));
		financialInputRequest.setCashFromOperatingFy(financialInputRequest.getEbitdaFy()-financialInputRequest.getInterestPaidFy()-financialInputRequest.getIncreaseWorkingCapitalFy()-financialInputRequest.getTaxPaidFy());
		financialInputRequest.setCashFromOperatingSy(financialInputRequest.getEbitdaSy()-financialInputRequest.getInterestPaidSy()-financialInputRequest.getIncreaseWorkingCapitalSy()-financialInputRequest.getTaxPaidSy());
		
		//RATIO ANALYSIS
		financialInputRequestString.setEbitadPercentageFy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getOperatingProfitEbitadOiFy(), financialInputRequest.getNetSaleFy())));
		financialInputRequestString.setEbitadPercentageSy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getOperatingProfitEbitadOiSy(), financialInputRequest.getNetSaleSy())));
		financialInputRequestString.setEbitadPercentageTy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getOperatingProfitEbitadOiTy(), financialInputRequest.getNetSaleTy())));
		
		financialInputRequestString.setPatmFy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getProfitAfterTaxFy(), financialInputRequest.getNetSaleFy())));
		financialInputRequestString.setPatmSy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getProfitAfterTaxSy(), financialInputRequest.getNetSaleSy())));
		financialInputRequestString.setPatmTy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getProfitAfterTaxTy(), financialInputRequest.getNetSaleTy())));
		
		financialInputRequestString.setRoceFy(convertValue((financialInputRequest.getOperatingProfitEbitadOiFy()*2/(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsFy(), financialInputRequest.getShareHolderFundsSy(), financialInputRequest.getTotalNonCurruntLiablitiesFy(), financialInputRequest.getTotalNonCurruntLiablitiesSy())))*12/financialInputRequest.getNoOfMonthFy()));
		financialInputRequestString.setRoceSy(convertValue((financialInputRequest.getOperatingProfitEbitadOiSy()*2/(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsSy(), financialInputRequest.getShareHolderFundsTy(), financialInputRequest.getTotalNonCurruntLiablitiesSy(), financialInputRequest.getTotalNonCurruntLiablitiesTy())))*12/financialInputRequest.getNoOfMonthSy()));
		
		financialInputRequestString.setAssetTurnOverFy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getNetSaleFy() * 12, (CommonUtils.multiplyNumbers(financialInputRequest.getTotalAssetFy(), financialInputRequest.getNoOfMonthFy())))));
		financialInputRequestString.setAssetTurnOverSy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getNetSaleSy() * 12, (CommonUtils.multiplyNumbers(financialInputRequest.getTotalAssetSy(), financialInputRequest.getNoOfMonthSy())))));
		financialInputRequestString.setAssetTurnOverTy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getNetSaleTy() * 12, (CommonUtils.multiplyNumbers(financialInputRequest.getTotalAssetTy(), financialInputRequest.getNoOfMonthTy())))));
		
		financialInputRequestString.setInventoryTurnOverFy(convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(financialInputRequest.getTotalExpenditureFy()*12, (financialInputRequest.getInventoriesFy()*financialInputRequest.getNoOfMonthFy()))))));
		financialInputRequestString.setInventoryTurnOverSy(convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(financialInputRequest.getTotalExpenditureSy()*12, (financialInputRequest.getInventoriesSy()*financialInputRequest.getNoOfMonthSy()))))));
		financialInputRequestString.setInventoryTurnOverTy(convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(financialInputRequest.getTotalExpenditureTy()*12, (financialInputRequest.getInventoriesTy()*financialInputRequest.getNoOfMonthTy()))))));
		
		financialInputRequestString.setDebtorsTurnoverFy(convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(financialInputRequest.getNetSaleFy()*12, (financialInputRequest.getSundryDebtorsFy()*financialInputRequest.getNoOfMonthFy()))))));
		financialInputRequestString.setDebtorsTurnoverSy(convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(financialInputRequest.getNetSaleSy()*12, (financialInputRequest.getSundryDebtorsSy()*financialInputRequest.getNoOfMonthSy()))))));
		financialInputRequestString.setDebtorsTurnoverTy(convertValue(CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(financialInputRequest.getNetSaleTy()*12, (financialInputRequest.getSundryDebtorsTy()*financialInputRequest.getNoOfMonthTy()))))));
		
		financialInputRequestString.setCreditorsTurnoverFy(convertValue((CommonUtils.divideNumbers(365.0, CommonUtils.divideNumbers((CommonUtils.addNumbers(financialInputRequest.getRawMaterialConsumedFy(), financialInputRequest.getPowerAndFuelCostFy())), financialInputRequest.getTradePayablesFy())))*12/financialInputRequest.getNoOfMonthFy()));
		financialInputRequestString.setCreditorsTurnoverSy(convertValue((CommonUtils.divideNumbers(365.0, CommonUtils.divideNumbers((CommonUtils.addNumbers(financialInputRequest.getRawMaterialConsumedSy(), financialInputRequest.getPowerAndFuelCostSy())), financialInputRequest.getTradePayablesSy())))*12/financialInputRequest.getNoOfMonthSy()));
		financialInputRequestString.setCreditorsTurnoverTy(convertValue((CommonUtils.divideNumbers(365.0, CommonUtils.divideNumbers((CommonUtils.addNumbers(financialInputRequest.getRawMaterialConsumedTy(), financialInputRequest.getPowerAndFuelCostTy())), financialInputRequest.getTradePayablesTy())))*12/financialInputRequest.getNoOfMonthTy()));
		
		financialInputRequestString.setSalesWorkingCapitalFy(convertValue((CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(financialInputRequest.getNetSaleFy(), (CommonUtils.addNumbers(financialInputRequest.getInventoriesFy(),financialInputRequest.getSundryDebtorsFy())-financialInputRequest.getTradePayablesFy())))))*12 /financialInputRequest.getNoOfMonthFy()));
		financialInputRequestString.setSalesWorkingCapitalSy(convertValue((CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(financialInputRequest.getNetSaleSy(), (CommonUtils.addNumbers(financialInputRequest.getInventoriesSy(),financialInputRequest.getSundryDebtorsSy())-financialInputRequest.getTradePayablesSy())))))*12 /financialInputRequest.getNoOfMonthSy()));
		financialInputRequestString.setSalesWorkingCapitalTy(convertValue((CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(financialInputRequest.getNetSaleTy(), (CommonUtils.addNumbers(financialInputRequest.getInventoriesTy(),financialInputRequest.getSundryDebtorsTy())-financialInputRequest.getTradePayablesTy())))))*12 /financialInputRequest.getNoOfMonthTy()));
		
		financialInputRequestString.setNetSalesGrowthCapitalFy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getNetSaleFy(),financialInputRequest.getNetSaleSy())-1));
		financialInputRequestString.setNetSalesGrowthCapitalSy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getNetSaleSy(),financialInputRequest.getNetSaleTy())-1));
		
		financialInputRequestString.setPatGrowthCapitalFy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getProfitAfterTaxFy(),financialInputRequest.getProfitAfterTaxSy())-1));
		financialInputRequestString.setPatGrowthCapitalSy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getProfitAfterTaxSy(),financialInputRequest.getProfitAfterTaxTy())-1));
		
		financialInputRequestString.setAdjustedTotalDebtEquityFy(convertValue(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(financialInputRequest.getTotalNonCurruntLiablitiesFy(),financialInputRequest.getLongTermProvisionFy(),financialInputRequest.getUnsecuredLoansPromotersFy())), (CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsFy(),financialInputRequest.getUnsecuredLoansPromotersFy())))));
		financialInputRequestString.setAdjustedTotalDebtEquitySy(convertValue(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(financialInputRequest.getTotalNonCurruntLiablitiesSy(),financialInputRequest.getLongTermProvisionSy(),financialInputRequest.getUnsecuredLoansPromotersSy())), (CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsSy(),financialInputRequest.getUnsecuredLoansPromotersSy())))));
		financialInputRequestString.setAdjustedTotalDebtEquityTy(convertValue(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(financialInputRequest.getTotalNonCurruntLiablitiesTy(),financialInputRequest.getLongTermProvisionTy(),financialInputRequest.getUnsecuredLoansPromotersTy())), (CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsTy(),financialInputRequest.getUnsecuredLoansPromotersTy())))));
		financialInputRequest.setAdjustedTotalDebtEquityFy(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(financialInputRequest.getTotalNonCurruntLiablitiesFy(),financialInputRequest.getLongTermProvisionFy(),financialInputRequest.getUnsecuredLoansPromotersFy())), (CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsFy(),financialInputRequest.getUnsecuredLoansPromotersFy()))));
		financialInputRequest.setAdjustedTotalDebtEquitySy(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(financialInputRequest.getTotalNonCurruntLiablitiesSy(),financialInputRequest.getLongTermProvisionSy(),financialInputRequest.getUnsecuredLoansPromotersSy())), (CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsSy(),financialInputRequest.getUnsecuredLoansPromotersSy()))));
		financialInputRequest.setAdjustedTotalDebtEquityTy(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(financialInputRequest.getTotalNonCurruntLiablitiesTy(),financialInputRequest.getLongTermProvisionTy(),financialInputRequest.getUnsecuredLoansPromotersTy())), (CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsTy(),financialInputRequest.getUnsecuredLoansPromotersTy()))));
		
		financialInputRequestString.setGrowthDebtEquityFy(convertValue((CommonUtils.substractNumbers(financialInputRequest.getAdjustedTotalDebtEquityFy(),financialInputRequest.getAdjustedTotalDebtEquitySy()))/financialInputRequest.getAdjustedTotalDebtEquitySy()));
		financialInputRequestString.setGrowthDebtEquitySy(convertValue((CommonUtils.substractNumbers(financialInputRequest.getAdjustedTotalDebtEquitySy(),financialInputRequest.getAdjustedTotalDebtEquityTy()))/financialInputRequest.getAdjustedTotalDebtEquityTy()));
		
		financialInputRequestString.setCurruntRatioXFy(convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(financialInputRequest.getInventoriesFy(),financialInputRequest.getSundryDebtorsFy())), financialInputRequest.getTradePayablesFy())));
		financialInputRequestString.setCurruntRatioXSy(convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(financialInputRequest.getInventoriesSy(),financialInputRequest.getSundryDebtorsSy())), financialInputRequest.getTradePayablesSy())));
		financialInputRequestString.setCurruntRatioXTy(convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(financialInputRequest.getInventoriesTy(),financialInputRequest.getSundryDebtorsTy())), financialInputRequest.getTradePayablesTy())));
		
		financialInputRequestString.setQuickRatioXFy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getSundryDebtorsFy(),financialInputRequest.getTradePayablesFy())));
		financialInputRequestString.setQuickRatioXSy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getSundryDebtorsSy(),financialInputRequest.getTradePayablesSy())));
		financialInputRequestString.setQuickRatioXTy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getSundryDebtorsTy(),financialInputRequest.getTradePayablesTy())));
		
		financialInputRequestString.setCashInterestCoverFy(convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(financialInputRequest.getCashFromOperatingFy(),financialInputRequest.getInterestFy())), financialInputRequest.getInterestFy())));
		financialInputRequestString.setCashInterestCoverSy(convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(financialInputRequest.getCashFromOperatingSy(),financialInputRequest.getInterestSy())), financialInputRequest.getInterestSy())));
		
		financialInputRequestString.setDebtEbitadFy(convertValue(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(financialInputRequest.getTotalNonCurruntLiablitiesFy(),financialInputRequest.getUnsecuredLoansPromotersFy(),financialInputRequest.getLongTermProvisionFy())), (12*CommonUtils.divideNumbers(financialInputRequest.getOperatingProfitEbitadOiFy(),financialInputRequest.getNoOfMonthFy())))));
		financialInputRequestString.setDebtEbitadSy(convertValue(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(financialInputRequest.getTotalNonCurruntLiablitiesSy(),financialInputRequest.getUnsecuredLoansPromotersSy(),financialInputRequest.getLongTermProvisionSy())), (12*CommonUtils.divideNumbers(financialInputRequest.getOperatingProfitEbitadOiSy(),financialInputRequest.getNoOfMonthSy())))));
		financialInputRequestString.setDebtEbitadTy(convertValue(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(financialInputRequest.getTotalNonCurruntLiablitiesTy(),financialInputRequest.getUnsecuredLoansPromotersTy(),financialInputRequest.getLongTermProvisionTy())), (12*CommonUtils.divideNumbers(financialInputRequest.getOperatingProfitEbitadOiTy(),financialInputRequest.getNoOfMonthTy())))));
		
		financialInputRequestString.setFreeReserveEquityFy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getOtherReserveAndSurplusFy(), (CommonUtils.addNumbers(financialInputRequest.getShareCapitalFy(),financialInputRequest.getShareWarrantOutstandingsFy())))));
		financialInputRequestString.setFreeReserveEquitySy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getOtherReserveAndSurplusSy(), (CommonUtils.addNumbers(financialInputRequest.getShareCapitalSy(),financialInputRequest.getShareWarrantOutstandingsSy())))));
		financialInputRequestString.setFreeReserveEquityTy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getOtherReserveAndSurplusTy(), (CommonUtils.addNumbers(financialInputRequest.getShareCapitalTy(),financialInputRequest.getShareWarrantOutstandingsTy())))));
		
		financialInputRequestString.setCfoMargineFy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getCashFromOperatingFy(),financialInputRequest.getNetSaleFy())));
		financialInputRequestString.setCfoMargineSy(convertValue(CommonUtils.divideNumbers(financialInputRequest.getCashFromOperatingSy(),financialInputRequest.getNetSaleSy())));
		financialInputRequest.setCfoMargineFy(CommonUtils.divideNumbers(financialInputRequest.getCashFromOperatingFy(),financialInputRequest.getNetSaleFy()));
		financialInputRequest.setCfoMargineSy(CommonUtils.divideNumbers(financialInputRequest.getCashFromOperatingSy(),financialInputRequest.getNetSaleSy()));
		
		financialInputRequestString.setGrowthCfoMargineFy(convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(financialInputRequest.getCfoMargineFy(),financialInputRequest.getCfoMargineSy())), financialInputRequest.getCfoMargineSy())));
		financialInputRequestString.setGrowthCfoMargineSy("0.0");
		
		return financialInputRequestString;
	}
	
	
	
	
	//NOTES TO ACCOUNTS
	public OperatingStatementDetailsString getOperatingStatementMappings(Long userId, Long applicationId, String industry, Long denomination) {
		OperatingStatementDetails osDetails = null;
		OperatingStatementDetailsString osDetailsString = new OperatingStatementDetailsString();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		/*==============================================THIRD YEAR===========================================================*/
		osDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear-1+"");
		if(CommonUtils.isObjectNullOrEmpty(osDetails)) {
			return osDetailsString;
		}
		
		osDetailsString.setDomesticSalesFy(convertValue(osDetails.getDomesticSales()));
		osDetailsString.setExportSalesFy(convertValue(osDetails.getExportSales()));
		osDetailsString.setGrossSalesTotalFY(convertValue(CommonUtils.addNumbers(osDetails.getDomesticSales(),osDetails.getExportSales())));
		
		osDetailsString.setLessExciseDutyFy(convertValue(osDetails.getLessExciseDuty()));
		osDetailsString.setDeductOtherItemsFy(convertValue(osDetails.getDeductOtherItems()));
		osDetailsString.setExciseDutyTotalFY(convertValue(CommonUtils.addNumbers(osDetails.getLessExciseDuty(),osDetails.getDeductOtherItems())));
		
		osDetailsString.setAddOperatingStockFy(convertValue(osDetails.getAddOperatingStock()));
		osDetailsString.setDeductStockInProcessFy(convertValue(osDetails.getDeductStockInProcess()));
		osDetailsString.setAddOperatingStockFgFy(convertValue(osDetails.getAddOperatingStockFg()));
		osDetailsString.setDeductClStockFgFy(convertValue(osDetails.getDeductClStockFg()));
		double subFy = CommonUtils.substractNumbers(osDetails.getAddOperatingStockFg(), osDetails.getDeductClStockFg());
		double sumFy = CommonUtils.addNumbers(subFy,osDetails.getAddOperatingStockFg());
		osDetailsString.setIncreaseDecreaseTotalFy(convertValue(CommonUtils.substractNumbers(sumFy, osDetails.getDeductClStockFg())));
		
		osDetailsString.setRawMaterialsFy(convertValue(osDetails.getRawMaterials()));
		osDetailsString.setOtherSparesFy(convertValue(osDetails.getOtherSpares()));
		osDetailsString.setRawMaterialsConsumedTotalFy(convertValue(CommonUtils.addNumbers(osDetails.getRawMaterials(), osDetails.getOtherSpares())));
		
		osDetailsString.setProvisionForTaxesFy(convertValue(osDetails.getProvisionForTaxes()));
		osDetailsString.setProvisionForDeferredTaxFy(convertValue(osDetails.getProvisionForDeferredTax()));
		osDetailsString.setProvisionForTaxTotalFy(convertValue(CommonUtils.addNumbers(osDetails.getProvisionForDeferredTax(), osDetails.getProvisionForTaxes())));
		
		/*==============================================SECOND YEAR===========================================================*/
		osDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear-2+"");
		if(CommonUtils.isObjectNullOrEmpty(osDetails)) {
			return osDetailsString;
		}
		osDetailsString.setDomesticSalesSy(convertValue(osDetails.getDomesticSales()));
		osDetailsString.setExportSalesSy(convertValue(osDetails.getExportSales()));
		osDetailsString.setGrossSalesTotalSY(convertValue(CommonUtils.addNumbers(osDetails.getDomesticSales(),osDetails.getExportSales())));
		
		osDetailsString.setLessExciseDutySy(convertValue(osDetails.getLessExciseDuty()));
		osDetailsString.setDeductOtherItemsSy(convertValue(osDetails.getDeductOtherItems()));
		osDetailsString.setExciseDutyTotalSY(convertValue(CommonUtils.addNumbers(osDetails.getLessExciseDuty(),osDetails.getDeductOtherItems())));
		
		osDetailsString.setAddOperatingStockSy(convertValue(osDetails.getAddOperatingStock()));
		osDetailsString.setDeductStockInProcessSy(convertValue(osDetails.getDeductStockInProcess()));
		osDetailsString.setAddOperatingStockFgSy(convertValue(osDetails.getAddOperatingStockFg()));
		osDetailsString.setDeductClStockFgSy(convertValue(osDetails.getDeductClStockFg()));
		double subSy = CommonUtils.substractNumbers(osDetails.getAddOperatingStockFg(), osDetails.getDeductClStockFg());
		double sumSy = CommonUtils.addNumbers(subSy,osDetails.getAddOperatingStockFg());
		osDetailsString.setIncreaseDecreaseTotalSy(convertValue(CommonUtils.substractNumbers(sumSy, osDetails.getDeductClStockFg())));
		
		osDetailsString.setRawMaterialsSy(convertValue(osDetails.getRawMaterials()));
		osDetailsString.setOtherSparesSy(convertValue(osDetails.getOtherSpares()));
		osDetailsString.setRawMaterialsConsumedTotalSy(convertValue(CommonUtils.addNumbers(osDetails.getRawMaterials(), osDetails.getOtherSpares())));
		
		osDetailsString.setProvisionForTaxesSy(convertValue(osDetails.getProvisionForTaxes()));
		osDetailsString.setProvisionForDeferredTaxSy(convertValue(osDetails.getProvisionForDeferredTax()));
		osDetailsString.setProvisionForTaxTotalSy(convertValue(CommonUtils.addNumbers(osDetails.getProvisionForDeferredTax(), osDetails.getProvisionForTaxes())));

		/*==============================================FIRST YEAR===========================================================*/
		osDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear-3+"");
		if(CommonUtils.isObjectNullOrEmpty(osDetails)) {
			return osDetailsString;
		}
		osDetailsString.setDomesticSalesTy(convertValue(osDetails.getDomesticSales()));
		osDetailsString.setExportSalesTy(convertValue(osDetails.getExportSales()));
		osDetailsString.setGrossSalesTotalTY(convertValue(CommonUtils.addNumbers(osDetails.getDomesticSales(),osDetails.getExportSales())));
		
		osDetailsString.setLessExciseDutyTy(convertValue(osDetails.getLessExciseDuty()));
		osDetailsString.setDeductOtherItemsTy(convertValue(osDetails.getDeductOtherItems()));
		osDetailsString.setExciseDutyTotalTY(convertValue(CommonUtils.addNumbers(osDetails.getLessExciseDuty(),osDetails.getDeductOtherItems())));
		
		osDetailsString.setAddOperatingStockTy(convertValue(osDetails.getAddOperatingStock()));
		osDetailsString.setDeductStockInProcessTy(convertValue(osDetails.getDeductStockInProcess()));
		osDetailsString.setAddOperatingStockFgTy(convertValue(osDetails.getAddOperatingStockFg()));
		osDetailsString.setDeductClStockFgTy(convertValue(osDetails.getDeductClStockFg()));
		double subTy = CommonUtils.substractNumbers(osDetails.getAddOperatingStockFg(), osDetails.getDeductClStockFg());
		double sumTy = CommonUtils.addNumbers(subTy,osDetails.getAddOperatingStockFg());
		osDetailsString.setIncreaseDecreaseTotalTy(convertValue(CommonUtils.substractNumbers(sumTy, osDetails.getDeductClStockFg())));
		
		osDetailsString.setRawMaterialsTy(convertValue(osDetails.getRawMaterials()));
		osDetailsString.setOtherSparesTy(convertValue(osDetails.getOtherSpares()));
		osDetailsString.setRawMaterialsConsumedTotalTy(convertValue(CommonUtils.addNumbers(osDetails.getRawMaterials(), osDetails.getOtherSpares())));
		
		osDetailsString.setProvisionForTaxesTy(convertValue(osDetails.getProvisionForTaxes()));
		osDetailsString.setProvisionForDeferredTaxTy(convertValue(osDetails.getProvisionForDeferredTax()));
		osDetailsString.setProvisionForTaxTotalTy(convertValue(CommonUtils.addNumbers(osDetails.getProvisionForDeferredTax(), osDetails.getProvisionForTaxes())));
		
		return osDetailsString;
	}
	
	public LiabilitiesDetailsString getLiabilitiesDetails(Long userId, Long applicationId, String industry, Long denomination) {
		LiabilitiesDetails liabilitiesDetails = null;
		LiabilitiesDetailsString liabilitiesDetailsString = new LiabilitiesDetailsString();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		/*==============================================THIRD YEAR===========================================================*/
		liabilitiesDetails = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear-1+"");
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)) {
			return liabilitiesDetailsString;
		}
		liabilitiesDetailsString.setOrdinarySharesCapitalFy(convertValue(liabilitiesDetails.getOrdinarySharesCapital()));
		liabilitiesDetailsString.setPreferencesSharesFy(convertValue(liabilitiesDetails.getPreferencesShares()));
		liabilitiesDetailsString.setShareCapitalTotalFy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getOrdinarySharesCapital(), liabilitiesDetails.getPreferencesShares())));
		
		liabilitiesDetailsString.setGeneralReserveFy(convertValue(liabilitiesDetails.getGeneralReserve()));
		liabilitiesDetailsString.setOtherReservseFy(convertValue(liabilitiesDetails.getOtherReservse()));
		liabilitiesDetailsString.setSurplusOrDeficitFy(convertValue(liabilitiesDetails.getSurplusOrDeficit()));
		liabilitiesDetailsString.setOthersFy(convertValue(liabilitiesDetails.getOthers()));
		liabilitiesDetailsString.setOtherReservesTotalFy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getGeneralReserve(),liabilitiesDetails.getOtherReservse(), liabilitiesDetails.getSurplusOrDeficit(),liabilitiesDetails.getOthers())));
		
		liabilitiesDetailsString.setTermLoansFy(convertValue(liabilitiesDetails.getTermLoans()));
		liabilitiesDetailsString.setOtherNclUnsecuredLoansFromOtherFy(convertValue(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther()));
		liabilitiesDetailsString.setUnsecuredLoansOthersTotalFy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getTermLoans(), liabilitiesDetails.getOtherNclUnsecuredLoansFromOther())));
		
		liabilitiesDetailsString.setSubTotalAFy(convertValue(liabilitiesDetails.getSubTotalA()));
		liabilitiesDetailsString.setShortTermBorrowingFromOthersFy(convertValue(liabilitiesDetails.getShortTermBorrowingFromOthers()));
		liabilitiesDetailsString.setOtherBorrowingsTotalFy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getSubTotalA(), liabilitiesDetails.getShortTermBorrowingFromOthers())));
		
		liabilitiesDetailsString.setOtherNclFy(convertValue(liabilitiesDetails.getOtherNcl()));
		liabilitiesDetailsString.setDeferredPaymentsCreditsFy(convertValue(liabilitiesDetails.getDeferredPaymentsCredits()));
		liabilitiesDetailsString.setTermDepositsFy(convertValue(liabilitiesDetails.getTermDeposits()));
		liabilitiesDetailsString.setDebenturesFy(convertValue(liabilitiesDetails.getDebentures()));
		liabilitiesDetailsString.setOtherTermLiabiliesFy(convertValue(liabilitiesDetails.getOtherTermLiabilies()));
		liabilitiesDetailsString.setOtherLongTermLiabilitiesTotalFy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getOtherNcl(),liabilitiesDetails.getDeferredPaymentsCredits(),liabilitiesDetails.getTermDeposits(), liabilitiesDetails.getDebentures(), liabilitiesDetails.getOtherTermLiabilies())));
		
		liabilitiesDetailsString.setAdvancePaymentsFromCustomersFy(convertValue(liabilitiesDetails.getAdvancePaymentsFromCustomers()));
		liabilitiesDetailsString.setDividendPayableFy(convertValue(liabilitiesDetails.getDividendPayable()));
		liabilitiesDetailsString.setOtherStatutoryLiabilityFy(convertValue(liabilitiesDetails.getOtherStatutoryLiability()));
		liabilitiesDetailsString.setDepositsOrInstalmentsOfTermLoansFy(convertValue(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()));
		liabilitiesDetailsString.setOtherCurrentLiabilityFy(convertValue(liabilitiesDetails.getOtherCurrentLiability()));
		liabilitiesDetailsString.setOtherCurrentLiabilitiesTotalFy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getAdvancePaymentsFromCustomers(),liabilitiesDetails.getDividendPayable(),liabilitiesDetails.getOtherStatutoryLiability(),liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans(),liabilitiesDetails.getOtherCurrentLiability())));
		
		/*==============================================SECOND YEAR===========================================================*/
		liabilitiesDetails = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear-2+"");
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)) {
			return liabilitiesDetailsString;
		}
		liabilitiesDetailsString.setOrdinarySharesCapitalSy(convertValue(liabilitiesDetails.getOrdinarySharesCapital()));
		liabilitiesDetailsString.setPreferencesSharesSy(convertValue(liabilitiesDetails.getPreferencesShares()));
		liabilitiesDetailsString.setShareCapitalTotalSy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getOrdinarySharesCapital(), liabilitiesDetails.getPreferencesShares())));
		
		liabilitiesDetailsString.setGeneralReserveSy(convertValue(liabilitiesDetails.getGeneralReserve()));
		liabilitiesDetailsString.setOtherReservseSy(convertValue(liabilitiesDetails.getOtherReservse()));
		liabilitiesDetailsString.setSurplusOrDeficitSy(convertValue(liabilitiesDetails.getSurplusOrDeficit()));
		liabilitiesDetailsString.setOthersSy(convertValue(liabilitiesDetails.getOthers()));
		liabilitiesDetailsString.setOtherReservesTotalSy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getGeneralReserve(),liabilitiesDetails.getOtherReservse(), liabilitiesDetails.getSurplusOrDeficit(),liabilitiesDetails.getOthers())));
		
		liabilitiesDetailsString.setTermLoansSy(convertValue(liabilitiesDetails.getTermLoans()));
		liabilitiesDetailsString.setOtherNclUnsecuredLoansFromOtherSy(convertValue(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther()));
		liabilitiesDetailsString.setUnsecuredLoansOthersTotalSy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getTermLoans(), liabilitiesDetails.getOtherNclUnsecuredLoansFromOther())));
		
		liabilitiesDetailsString.setSubTotalASy(convertValue(liabilitiesDetails.getSubTotalA()));
		liabilitiesDetailsString.setShortTermBorrowingFromOthersSy(convertValue(liabilitiesDetails.getShortTermBorrowingFromOthers()));
		liabilitiesDetailsString.setOtherBorrowingsTotalSy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getSubTotalA(), liabilitiesDetails.getShortTermBorrowingFromOthers())));
		
		liabilitiesDetailsString.setOtherNclSy(convertValue(liabilitiesDetails.getOtherNcl()));
		liabilitiesDetailsString.setDeferredPaymentsCreditsSy(convertValue(liabilitiesDetails.getDeferredPaymentsCredits()));
		liabilitiesDetailsString.setTermDepositsSy(convertValue(liabilitiesDetails.getTermDeposits()));
		liabilitiesDetailsString.setDebenturesSy(convertValue(liabilitiesDetails.getDebentures()));
		liabilitiesDetailsString.setOtherTermLiabiliesSy(convertValue(liabilitiesDetails.getOtherTermLiabilies()));
		liabilitiesDetailsString.setOtherLongTermLiabilitiesTotalSy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getOtherNcl(),liabilitiesDetails.getDeferredPaymentsCredits(),liabilitiesDetails.getTermDeposits(), liabilitiesDetails.getDebentures(), liabilitiesDetails.getOtherTermLiabilies())));
		
		liabilitiesDetailsString.setAdvancePaymentsFromCustomersSy(convertValue(liabilitiesDetails.getAdvancePaymentsFromCustomers()));
		liabilitiesDetailsString.setDividendPayableSy(convertValue(liabilitiesDetails.getDividendPayable()));
		liabilitiesDetailsString.setOtherStatutoryLiabilitySy(convertValue(liabilitiesDetails.getOtherStatutoryLiability()));
		liabilitiesDetailsString.setDepositsOrInstalmentsOfTermLoansSy(convertValue(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()));
		liabilitiesDetailsString.setOtherCurrentLiabilitySy(convertValue(liabilitiesDetails.getOtherCurrentLiability()));
		liabilitiesDetailsString.setOtherCurrentLiabilitiesTotalSy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getAdvancePaymentsFromCustomers(),liabilitiesDetails.getDividendPayable(),liabilitiesDetails.getOtherStatutoryLiability(),liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans(),liabilitiesDetails.getOtherCurrentLiability())));
		
		/*==============================================FIRST YEAR===========================================================*/
		liabilitiesDetails = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear-3+"");
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)) {
			return liabilitiesDetailsString;
		}
		liabilitiesDetailsString.setOrdinarySharesCapitalTy(convertValue(liabilitiesDetails.getOrdinarySharesCapital()));
		liabilitiesDetailsString.setPreferencesSharesTy(convertValue(liabilitiesDetails.getPreferencesShares()));
		liabilitiesDetailsString.setShareCapitalTotalTy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getOrdinarySharesCapital(), liabilitiesDetails.getPreferencesShares())));
		
		liabilitiesDetailsString.setGeneralReserveTy(convertValue(liabilitiesDetails.getGeneralReserve()));
		liabilitiesDetailsString.setOtherReservseTy(convertValue(liabilitiesDetails.getOtherReservse()));
		liabilitiesDetailsString.setSurplusOrDeficitTy(convertValue(liabilitiesDetails.getSurplusOrDeficit()));
		liabilitiesDetailsString.setOthersTy(convertValue(liabilitiesDetails.getOthers()));
		liabilitiesDetailsString.setOtherReservesTotalTy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getGeneralReserve(),liabilitiesDetails.getOtherReservse(), liabilitiesDetails.getSurplusOrDeficit(),liabilitiesDetails.getOthers())));
		
		liabilitiesDetailsString.setTermLoansTy(convertValue(liabilitiesDetails.getTermLoans()));
		liabilitiesDetailsString.setOtherNclUnsecuredLoansFromOtherTy(convertValue(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther()));
		liabilitiesDetailsString.setUnsecuredLoansOthersTotalTy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getTermLoans(), liabilitiesDetails.getOtherNclUnsecuredLoansFromOther())));
		
		liabilitiesDetailsString.setSubTotalATy(convertValue(liabilitiesDetails.getSubTotalA()));
		liabilitiesDetailsString.setShortTermBorrowingFromOthersTy(convertValue(liabilitiesDetails.getShortTermBorrowingFromOthers()));
		liabilitiesDetailsString.setOtherBorrowingsTotalTy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getSubTotalA(), liabilitiesDetails.getShortTermBorrowingFromOthers())));
		
		liabilitiesDetailsString.setOtherNclTy(convertValue(liabilitiesDetails.getOtherNcl()));
		liabilitiesDetailsString.setDeferredPaymentsCreditsTy(convertValue(liabilitiesDetails.getDeferredPaymentsCredits()));
		liabilitiesDetailsString.setTermDepositsTy(convertValue(liabilitiesDetails.getTermDeposits()));
		liabilitiesDetailsString.setDebenturesTy(convertValue(liabilitiesDetails.getDebentures()));
		liabilitiesDetailsString.setOtherTermLiabiliesTy(convertValue(liabilitiesDetails.getOtherTermLiabilies()));
		liabilitiesDetailsString.setOtherLongTermLiabilitiesTotalTy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getOtherNcl(),liabilitiesDetails.getDeferredPaymentsCredits(),liabilitiesDetails.getTermDeposits(), liabilitiesDetails.getDebentures(), liabilitiesDetails.getOtherTermLiabilies())));
		
		liabilitiesDetailsString.setAdvancePaymentsFromCustomersTy(convertValue(liabilitiesDetails.getAdvancePaymentsFromCustomers()));
		liabilitiesDetailsString.setDividendPayableTy(convertValue(liabilitiesDetails.getDividendPayable()));
		liabilitiesDetailsString.setOtherStatutoryLiabilityTy(convertValue(liabilitiesDetails.getOtherStatutoryLiability()));
		liabilitiesDetailsString.setDepositsOrInstalmentsOfTermLoansTy(convertValue(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()));
		liabilitiesDetailsString.setOtherCurrentLiabilityTy(convertValue(liabilitiesDetails.getOtherCurrentLiability()));
		liabilitiesDetailsString.setOtherCurrentLiabilitiesTotalTy(convertValue(CommonUtils.addNumbers(liabilitiesDetails.getAdvancePaymentsFromCustomers(),liabilitiesDetails.getDividendPayable(),liabilitiesDetails.getOtherStatutoryLiability(),liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans(),liabilitiesDetails.getOtherCurrentLiability())));
		return liabilitiesDetailsString;
	}
	
	public AssetDetailsString getAssetDetails(Long userId, Long applicationId, String industry, Long denomination) {
		AssetsDetails assetsDetails = null;
		AssetDetailsString assetDetailsString = new AssetDetailsString();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		/*==============================================THIRD YEAR===========================================================*/
		assetsDetails = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear-1+"");
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails)) {
			return assetDetailsString;
		}
		assetDetailsString.setInvestmentsOrBookDebtsStringFy(convertValue(assetsDetails.getInvestmentsOrBookDebts()));
		assetDetailsString.setDeferredReceviablesFy(convertValue(assetsDetails.getDeferredReceviables()));
		assetDetailsString.setOthersFy(convertValue(assetsDetails.getOthers()));
		assetDetailsString.setOtherInvestmentsTotalFy(convertValue(CommonUtils.addNumbers(assetsDetails.getInvestmentsOrBookDebts(), assetsDetails.getDeferredReceviables(),assetsDetails.getOthers())));
		
		assetDetailsString.setNonConsumableStoreAndSparesFy(convertValue(assetsDetails.getNonConsumableStoreAndSpares()));
		assetDetailsString.setOtherNonCurrentAssetsFy(convertValue(assetsDetails.getOtherNonCurrentAssets()));
		assetDetailsString.setOtherNonCurrentAssestsTotalFy(convertValue(CommonUtils.addNumbers(assetsDetails.getNonConsumableStoreAndSpares(),assetsDetails.getOtherNonCurrentAssets())));
		
		assetDetailsString.setReceivableOtherThanDefferredFy(convertValue(assetsDetails.getReceivableOtherThanDefferred()));
		assetDetailsString.setExportReceivablesFy(convertValue(assetsDetails.getExportReceivables()));
		assetDetailsString.setSundryDebtorsTotalFy(convertValue(CommonUtils.addNumbers(assetsDetails.getReceivableOtherThanDefferred(),assetsDetails.getExportReceivables())));
		
		assetDetailsString.setInvestmentsFy(convertValue(assetsDetails.getInvestments()));
		assetDetailsString.setInstalmentsDeferredFy(convertValue(assetsDetails.getInstalmentsDeferred()));
		assetDetailsString.setOtherCurrentAssetsFy(convertValue(assetsDetails.getOtherCurrentAssets()));
		assetDetailsString.setOtherCurrentAssetsTotalFy(convertValue(CommonUtils.addNumbers(assetsDetails.getInvestments(),assetsDetails.getInstalmentsDeferred(), assetsDetails.getOtherCurrentAssets())));
		
		assetDetailsString.setAdvanceToSupplierRawMaterialsFy(convertValue(assetsDetails.getAdvanceToSupplierRawMaterials()));
		assetDetailsString.setAdvancePaymentTaxesFy(convertValue(assetsDetails.getAdvancePaymentTaxes()));
		assetDetailsString.setShortTermLoansAndAdvancesTotalFy(convertValue(CommonUtils.addNumbers(assetsDetails.getAdvanceToSupplierRawMaterials(),assetsDetails.getAdvancePaymentTaxes() )));
		/*==============================================SECOND YEAR===========================================================*/
		assetsDetails = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear-2+"");
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails)) {
			return assetDetailsString;
		}
		assetDetailsString.setInvestmentsOrBookDebtsStringSy(convertValue(assetsDetails.getInvestmentsOrBookDebts()));
		assetDetailsString.setDeferredReceviablesSy(convertValue(assetsDetails.getDeferredReceviables()));
		assetDetailsString.setOthersSy(convertValue(assetsDetails.getOthers()));
		assetDetailsString.setOtherInvestmentsTotalSy(convertValue(CommonUtils.addNumbers(assetsDetails.getInvestmentsOrBookDebts(), assetsDetails.getDeferredReceviables(),assetsDetails.getOthers())));
		
		assetDetailsString.setNonConsumableStoreAndSparesSy(convertValue(assetsDetails.getNonConsumableStoreAndSpares()));
		assetDetailsString.setOtherNonCurrentAssetsSy(convertValue(assetsDetails.getOtherNonCurrentAssets()));
		assetDetailsString.setOtherNonCurrentAssestsTotalSy(convertValue(CommonUtils.addNumbers(assetsDetails.getNonConsumableStoreAndSpares(),assetsDetails.getOtherNonCurrentAssets())));
		
		assetDetailsString.setReceivableOtherThanDefferredSy(convertValue(assetsDetails.getReceivableOtherThanDefferred()));
		assetDetailsString.setExportReceivablesSy(convertValue(assetsDetails.getExportReceivables()));
		assetDetailsString.setSundryDebtorsTotalSy(convertValue(CommonUtils.addNumbers(assetsDetails.getReceivableOtherThanDefferred(),assetsDetails.getExportReceivables())));
		
		assetDetailsString.setInvestmentsSy(convertValue(assetsDetails.getInvestments()));
		assetDetailsString.setInstalmentsDeferredSy(convertValue(assetsDetails.getInstalmentsDeferred()));
		assetDetailsString.setOtherCurrentAssetsSy(convertValue(assetsDetails.getOtherCurrentAssets()));
		assetDetailsString.setOtherCurrentAssetsTotalSy(convertValue(CommonUtils.addNumbers(assetsDetails.getInvestments(),assetsDetails.getInstalmentsDeferred(), assetsDetails.getOtherCurrentAssets())));
		
		assetDetailsString.setAdvanceToSupplierRawMaterialsSy(convertValue(assetsDetails.getAdvanceToSupplierRawMaterials()));
		assetDetailsString.setAdvancePaymentTaxesSy(convertValue(assetsDetails.getAdvancePaymentTaxes()));
		assetDetailsString.setShortTermLoansAndAdvancesTotalSy(convertValue(CommonUtils.addNumbers(assetsDetails.getAdvanceToSupplierRawMaterials(),assetsDetails.getAdvancePaymentTaxes() )));
		/*==============================================SECOND YEAR===========================================================*/
		assetsDetails = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear-3+"");
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails)) {
			return assetDetailsString;
		}
		assetDetailsString.setInvestmentsOrBookDebtsStringTy(convertValue(assetsDetails.getInvestmentsOrBookDebts()));
		assetDetailsString.setDeferredReceviablesTy(convertValue(assetsDetails.getDeferredReceviables()));
		assetDetailsString.setOthersTy(convertValue(assetsDetails.getOthers()));
		assetDetailsString.setOtherInvestmentsTotalTy(convertValue(CommonUtils.addNumbers(assetsDetails.getInvestmentsOrBookDebts(), assetsDetails.getDeferredReceviables(),assetsDetails.getOthers())));
		
		assetDetailsString.setNonConsumableStoreAndSparesTy(convertValue(assetsDetails.getNonConsumableStoreAndSpares()));
		assetDetailsString.setOtherNonCurrentAssetsTy(convertValue(assetsDetails.getOtherNonCurrentAssets()));
		assetDetailsString.setOtherNonCurrentAssestsTotalTy(convertValue(CommonUtils.addNumbers(assetsDetails.getNonConsumableStoreAndSpares(),assetsDetails.getOtherNonCurrentAssets())));
		
		assetDetailsString.setReceivableOtherThanDefferredTy(convertValue(assetsDetails.getReceivableOtherThanDefferred()));
		assetDetailsString.setExportReceivablesTy(convertValue(assetsDetails.getExportReceivables()));
		assetDetailsString.setSundryDebtorsTotalTy(convertValue(CommonUtils.addNumbers(assetsDetails.getReceivableOtherThanDefferred(),assetsDetails.getExportReceivables())));
		
		assetDetailsString.setInvestmentsTy(convertValue(assetsDetails.getInvestments()));
		assetDetailsString.setInstalmentsDeferredTy(convertValue(assetsDetails.getInstalmentsDeferred()));
		assetDetailsString.setOtherCurrentAssetsTy(convertValue(assetsDetails.getOtherCurrentAssets()));
		assetDetailsString.setOtherCurrentAssetsTotalTy(convertValue(CommonUtils.addNumbers(assetsDetails.getInvestments(),assetsDetails.getInstalmentsDeferred(), assetsDetails.getOtherCurrentAssets())));
		
		assetDetailsString.setAdvanceToSupplierRawMaterialsTy(convertValue(assetsDetails.getAdvanceToSupplierRawMaterials()));
		assetDetailsString.setAdvancePaymentTaxesTy(convertValue(assetsDetails.getAdvancePaymentTaxes()));
		assetDetailsString.setShortTermLoansAndAdvancesTotalTy(convertValue(CommonUtils.addNumbers(assetsDetails.getAdvanceToSupplierRawMaterials(),assetsDetails.getAdvancePaymentTaxes() )));
		return assetDetailsString;
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
	public static Object convertToDoubleForXml (Object obj) throws Exception {
		Field[] fields = obj.getClass().getDeclaredFields();
		 for(Field field : fields) {
			 field.setAccessible(true);
             Object value = field.get(obj);
             if(!CommonUtils.isObjectNullOrEmpty(value)) {
            	 if(value instanceof Double){
                	 if(!Double.isNaN((Double)value)) {
                		 DecimalFormat decim = new DecimalFormat("0.00");
                    	 value = Double.parseDouble(decim.format(value));
                    	 field.set(obj,value);        
                	 }
                 }
             }
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
			} 
			else {
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