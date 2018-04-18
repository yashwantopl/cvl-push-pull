package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.fitchengine.model.manufacturing.FitchOutputManu;
import com.capitaworld.service.fitchengine.model.service.FitchOutputServ;
import com.capitaworld.service.fitchengine.model.trading.FitchOutputTrad;
import com.capitaworld.service.fitchengine.utils.CommonUtils.BusinessType;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.FinancialInputRequestString;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.OwnershipDetailRequest;
import com.capitaworld.service.loans.model.OwnershipDetailResponse;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.corporate.PrimaryCorporateRequest;
import com.capitaworld.service.loans.model.ddr.DDRFormDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AchievmentDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssociatedConcernDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CamReportPdfDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExistingProductDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OwnershipDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PrimaryCorporateService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PromotorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProposedProductDetailsService;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
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
import com.capitaworld.service.oneform.enums.EstablishmentMonths;
import com.capitaworld.service.oneform.enums.Industry;
import com.capitaworld.service.oneform.enums.LoanTypeNatureFacility;
import com.capitaworld.service.oneform.enums.ShareHoldingCategory;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.enums.PurposeOfLoan;
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
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

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
	private IndustrySectorRepository industrySectorRepository;

	@Autowired
	private SubSectorRepository subSectorRepository;
	
	@Autowired
	private DirectorBackgroundDetailsService backgroundDetailsService;
	
	@Autowired
	private ProposalDetailsClient proposalDetailsClient;
	
	@Autowired
	private UsersClient usersClient;

	private static final Logger logger = LoggerFactory.getLogger(CamReportPdfDetailsServiceImpl.class);
	
	@Override
	public Map<String, Object> getCamReportFinalDetails(Long applicationId, Long productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long userId = loanApplicationRepository.getUserIdByApplicationId(applicationId);
		//ONE-FORM DATA
		/*try {
			CorporateApplicantRequest corporateApplicantRequest =corporateApplicantService.getCorporateApplicant(applicationId);
			map.put("corporateApplicant", printFields(corporateApplicantRequest));
			map.put("orgName", escapeXml(corporateApplicantRequest.getOrganisationName()));
			//REGISTERED OFFICE ADDRESS
			try {
				if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getFirstAddress())) {
					map.put("registeredAddPremise", !CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getFirstAddress().getPremiseNumber()) ? escapeXml(corporateApplicantRequest.getFirstAddress().getPremiseNumber()) + ", " : "");
					map.put("registeredAddStreetName", !CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getFirstAddress().getStreetName()) ? escapeXml(corporateApplicantRequest.getFirstAddress().getStreetName()) + ", " : "");
					map.put("registeredAddLandmark", !CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getFirstAddress().getLandMark()) ? escapeXml(corporateApplicantRequest.getFirstAddress().getLandMark()) + ", " : "");
					map.put("registeredAddCountry", escapeXml(getCountryName(corporateApplicantRequest.getFirstAddress().getCountryId())));
					map.put("registeredAddState", escapeXml(getStateName(corporateApplicantRequest.getFirstAddress().getStateId())));
					map.put("registeredAddCity", escapeXml(getCityName(corporateApplicantRequest.getFirstAddress().getCityId())));
					map.put("registeredAddPincode", !CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getFirstAddress().getPincode())?corporateApplicantRequest.getFirstAddress().getPincode() : "");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			map.put("constitution", !CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getConstitutionId()) ? escapeXml(Constitution.getById(corporateApplicantRequest.getConstitutionId()).getValue()) : "NA");
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
				}
			}
			map.put("establishmentYr",!CommonUtils.isObjectNullOrEmpty(establishMentYear) ? printFields(establishMentYear) : "NA");
			
			//PROMOTOR BACKGROUND DETAILS
			try {
				List<PromotorBackgroundDetailRequest> promotorBackgroundDetailRequestList = promotorBackgroundDetailsService.getPromotorBackgroundDetailList(applicationId, userId);
				List<PromotorBackgroundDetailResponse> promotorBackgroundDetailResponseList = new ArrayList<>();
				for (PromotorBackgroundDetailRequest promotorBackgroundDetailRequest : promotorBackgroundDetailRequestList) {
					PromotorBackgroundDetailResponse promotorBackgroundDetailResponse = new PromotorBackgroundDetailResponse();
					promotorBackgroundDetailResponse.setAchievements(promotorBackgroundDetailRequest.getAchivements());
					promotorBackgroundDetailResponse.setAddress(promotorBackgroundDetailRequest.getAddress());
					promotorBackgroundDetailResponse.setAge(promotorBackgroundDetailRequest.getAge());
	                promotorBackgroundDetailResponse.setPanNo(promotorBackgroundDetailRequest.getPanNo().toUpperCase());
					String promotorName = "";
					if (promotorBackgroundDetailRequest.getSalutationId() != null){
						promotorName = Title.getById(promotorBackgroundDetailRequest.getSalutationId()).getValue();
					}
					promotorName += promotorBackgroundDetailRequest.getPromotorsName();
					promotorBackgroundDetailResponse.setPromotorsName(promotorName);
					promotorBackgroundDetailResponse.setQualification(promotorBackgroundDetailRequest.getQualification());
					promotorBackgroundDetailResponse.setTotalExperience(promotorBackgroundDetailRequest.getTotalExperience());
					promotorBackgroundDetailResponseList.add(promotorBackgroundDetailResponse);
				}
				map.put("promotorsbckgrnd", printFields(promotorBackgroundDetailResponseList));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//DIRECTOR'S BACKGROUND
			try {
				List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = backgroundDetailsService.getDirectorBackgroundDetailList(applicationId, userId);
				List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponseList = new ArrayList<>();
				for (DirectorBackgroundDetailRequest directorBackgroundDetailRequest : directorBackgroundDetailRequestList) {
					DirectorBackgroundDetailResponse directorBackgroundDetailResponse = new DirectorBackgroundDetailResponse();
					//directorBackgroundDetailResponse.setAchivements(directorBackgroundDetailRequest.getAchivements());
					directorBackgroundDetailResponse.setAddress(directorBackgroundDetailRequest.getAddress());
					//directorBackgroundDetailResponse.setAge(directorBackgroundDetailRequest.getAge());
					directorBackgroundDetailResponse.setPanNo(directorBackgroundDetailRequest.getPanNo());
					directorBackgroundDetailResponse.setDirectorsName((directorBackgroundDetailRequest.getSalutationId() != null ? Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue() : " " )+ " " + directorBackgroundDetailRequest.getDirectorsName());
					directorBackgroundDetailResponse.setPanNo(directorBackgroundDetailRequest.getPanNo().toUpperCase());
					String directorName = "";
					if (directorBackgroundDetailRequest.getSalutationId() != null){
						directorName = Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue();
					}
					directorName += " "+directorBackgroundDetailRequest.getDirectorsName();
					directorBackgroundDetailResponse.setDirectorsName(directorName);
					//.setQualification(directorBackgroundDetailRequest.getQualification());
					directorBackgroundDetailResponse.setTotalExperience(directorBackgroundDetailRequest.getTotalExperience());
					directorBackgroundDetailResponse.setNetworth(directorBackgroundDetailRequest.getNetworth());
					directorBackgroundDetailResponse.setDesignation(directorBackgroundDetailRequest.getDesignation());
					directorBackgroundDetailResponse.setAppointmentDate(directorBackgroundDetailRequest.getAppointmentDate());
					directorBackgroundDetailResponse.setDin(directorBackgroundDetailRequest.getDin());
					directorBackgroundDetailResponse.setMobile(directorBackgroundDetailRequest.getMobile());
					directorBackgroundDetailResponse.setDob(directorBackgroundDetailRequest.getDob());
					directorBackgroundDetailResponseList.add(directorBackgroundDetailResponse);
				}
				map.put("dirBackground", printFields(directorBackgroundDetailResponseList));
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
					ownershipDetailResponse.setShareHoldingCategory(
							ShareHoldingCategory.getById(ownershipDetailRequest.getShareHoldingCategoryId()).getValue());
					ownershipDetailResponseList.add(ownershipDetailResponse);
				}
				map.put("ownership", printFields(ownershipDetailResponseList));

			} catch (Exception e) {
				e.printStackTrace();
		}
			
		//FINANCIAL ARRANGEMENTS
			try {
				List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService.getFinancialArrangementDetailsList(applicationId, userId);
				List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList = new ArrayList<>();
				for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
					FinancialArrangementsDetailResponse financialArrangementsDetailResponse = new FinancialArrangementsDetailResponse();
					financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
					financialArrangementsDetailResponse.setOutstandingAmount(financialArrangementsDetailRequest.getOutstandingAmount());
					financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
					financialArrangementsDetailResponse.setAmount(financialArrangementsDetailRequest.getAmount());
					financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
					financialArrangementsDetailResponse.setLoanType(LoanTypeNatureFacility.getById(financialArrangementsDetailRequest.getLoanType()).getValue());
					financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
					financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
					financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
				}
				map.put("financialArrangments",printFields(financialArrangementsDetailResponseList));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			//INDUSTRY SECTOR SUBSECTOR
			List<Long> industryList = industrySectorRepository.getIndustryByApplicationId(applicationId);
			List<Long> sectorList = industrySectorRepository.getSectorByApplicationId(applicationId);
			List<Long> subSectorList = subSectorRepository.getSubSectorByApplicationId(applicationId);
			IndustrySectorSubSectorTeaserRequest industrySectorSubSectorTeaserRequest = new IndustrySectorSubSectorTeaserRequest();
			industrySectorSubSectorTeaserRequest.setIndustryList(industryList);
			industrySectorSubSectorTeaserRequest.setSectorList(sectorList);
			industrySectorSubSectorTeaserRequest.setSubSectorList(subSectorList);
			try {
				OneFormResponse oneFormResponse = oneFormClient.getIndustrySectorSubSector(industrySectorSubSectorTeaserRequest);
			map.put("industry", oneFormResponse.getListData());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Integer industry =  corporateApplicantRequest.getKeyVericalFunding().intValue();
			//Integer sector = (int)(long) corporateApplicantRequest.getKeyVerticalSector();
			//Integer subsector = (int)(long) corporateApplicantRequest.getKeyVerticalSubsector();
			map.put("keyVerticalFunding", !CommonUtils.isObjectNullOrEmpty(industry) ? printFields(Industry.getById(industry).getValue()) : " ");
			
			
			CorporateFinalInfoRequest corporateFinalInfoRequest = corporateFinalInfoService.get(userId, applicationId);
			//ADMIN OFFICE ADDRESS
			if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress())){
				map.put("adminAddPremise", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getPremiseNumber()) ? printFields(corporateFinalInfoRequest.getSecondAddress().getPremiseNumber()) + ", " : "");
				map.put("adminAddStreetName", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getStreetName()) ? printFields(corporateFinalInfoRequest.getSecondAddress().getStreetName()) + ", " : "");
				map.put("adminAddLandmark", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getLandMark()) ? printFields(corporateFinalInfoRequest.getSecondAddress().getLandMark()) + ", " : "");
				map.put("adminAddCountry", getCountryName(corporateFinalInfoRequest.getSecondAddress().getCountryId()));
				map.put("adminAddState", getStateName(corporateFinalInfoRequest.getSecondAddress().getStateId()));
				map.put("adminAddCity", getCityName(corporateFinalInfoRequest.getSecondAddress().getCityId()));
				map.put("adminAddPincode", !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getPincode())?corporateFinalInfoRequest.getSecondAddress().getPincode() : "");
			}
			map.put("corporateApplicantFinal", corporateFinalInfoRequest);
			map.put("aboutUs", printFields(corporateFinalInfoRequest.getAboutUs()));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		*/
		return map;
	}
	@Override
	public Map<String, Object> getCamReportPrimaryDetails(Long applicationId, Long productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long userId = loanApplicationRepository.getUserIdByApplicationId(applicationId);
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
				List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponseList = new ArrayList<>();
				for (DirectorBackgroundDetailRequest directorBackgroundDetailRequest : directorBackgroundDetailRequestList) {
					DirectorBackgroundDetailResponse directorBackgroundDetailResponse = new DirectorBackgroundDetailResponse();
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
					directorBackgroundDetailResponse.setTotalExperience(directorBackgroundDetailRequest.getTotalExperience());
					directorBackgroundDetailResponse.setNetworth(directorBackgroundDetailRequest.getNetworth());
					directorBackgroundDetailResponse.setDesignation(directorBackgroundDetailRequest.getDesignation());
					directorBackgroundDetailResponse.setAppointmentDate(directorBackgroundDetailRequest.getAppointmentDate());
					directorBackgroundDetailResponse.setDin(directorBackgroundDetailRequest.getDin());
					directorBackgroundDetailResponse.setMobile(directorBackgroundDetailRequest.getMobile());
					directorBackgroundDetailResponse.setDob(directorBackgroundDetailRequest.getDob());
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
                List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService
                        .getFinancialArrangementDetailsList(applicationId, userId);
                List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList = new ArrayList<>();
                for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
                    FinancialArrangementsDetailResponse financialArrangementsDetailResponse = new FinancialArrangementsDetailResponse();
//				financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
                    financialArrangementsDetailResponse.setOutstandingAmount(financialArrangementsDetailRequest.getOutstandingAmount());
                    financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
                    financialArrangementsDetailResponse.setAmount(financialArrangementsDetailRequest.getAmount());
                    //			financialArrangementsDetailResponse.setLenderType(LenderType.getById(financialArrangementsDetailRequest.getLenderType()).getValue());
                    financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
                    financialArrangementsDetailResponse.setLoanType(LoanTypeNatureFacility.getById(financialArrangementsDetailRequest.getLoanType()).getValue());
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
			map.put("loanAmt", !CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getLoanAmount()) ? String.valueOf(primaryCorporateRequest.getLoanAmount()) : " ");
			map.put("loanType", !CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getProductId()) ? LoanType.getType(primaryCorporateRequest.getProductId()) : " ");
			
			if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getPurposeOfLoanId())) {
				map.put("purpose", StringEscapeUtils.escapeXml(PurposeOfLoan.getById(primaryCorporateRequest.getPurposeOfLoanId()).getValue()));
			}else {
				map.put("purpose", "");
			}
			
			
			if(primaryCorporateRequest.getHaveCollateralSecurity()) {
				map.put("amtOfSecurity",!CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getCollateralSecurityAmount()) ? primaryCorporateRequest.getCollateralSecurityAmount() : " ");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		//IRR DATA
		try {
			LoanApplicationRequest loanApplicationRequest = loanApplicationService.get(applicationId, userId);
			Long denominationValue = Denomination.getById(loanApplicationRequest.getDenominationId()).getDigit();
			FinancialInputRequestString financialInputRequestString= calculateIRRScore(userId, applicationId, null, denominationValue);
			map.put("financialInputRequest", !CommonUtils.isObjectNullOrEmpty(financialInputRequestString) ? financialInputRequestString : " ");
			logger.info("Financial Input",financialInputRequestString);
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
		//FITCH DATA
		try {
		RatingResponse ratingResponse = (RatingResponse) irrService.calculateIrrRating(applicationId, userId).getBody().getData();
		if(BusinessType.MANUFACTURING == ratingResponse.getBusinessTypeId())
		{
			FitchOutputManu fitchOutputManu= MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)ratingResponse.getData(),FitchOutputManu.class);
			map.put("fitchResponse",fitchOutputManu);
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
			map.put("fitchResponse",fitchOutputTrad);
			map.put("fitchTitle","Trading");
		}
		if(BusinessType.SERVICE == ratingResponse.getBusinessTypeId())
		{
			FitchOutputServ fitchOutputServ = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)ratingResponse.getData(),FitchOutputTrad.class);
			map.put("fitchResponse",fitchOutputServ);
			map.put("fitchTitle","Service");
		}
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
			map.put("proposalScoreResponse",convertToString(proposalScoreResponse));
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
		
		/**********************************************FINAL DETAILS*****************************************************/
		/*//OWNERSHIP DETAILS :- 
		try {
			List<OwnershipDetailRequest> ownershipDetailRequestsList = ownershipDetailsService.getOwnershipDetailList(applicationId, userId);
			List<OwnershipDetailResponse> ownershipDetailResponseList = new ArrayList<>();
			for (OwnershipDetailRequest ownershipDetailRequest : ownershipDetailRequestsList) {
				OwnershipDetailResponse ownershipDetailResponse = new OwnershipDetailResponse();
				ownershipDetailResponse.setRemarks(ownershipDetailRequest.getRemarks());
				ownershipDetailResponse.setStackPercentage(ownershipDetailRequest.getStackPercentage());
				ownershipDetailResponse.setShareHoldingCategory(
						ShareHoldingCategory.getById(ownershipDetailRequest.getShareHoldingCategoryId()).getValue());
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
				promotorBackgroundDetailResponse.setAchievements(promotorBackgroundDetailRequest.getAchivements());
				promotorBackgroundDetailResponse.setAddress(promotorBackgroundDetailRequest.getAddress());
				promotorBackgroundDetailResponse.setAge(promotorBackgroundDetailRequest.getAge());
                promotorBackgroundDetailResponse.setPanNo(promotorBackgroundDetailRequest.getPanNo().toUpperCase());
				String promotorName = "";
				if (promotorBackgroundDetailRequest.getSalutationId() != null){
					promotorName = Title.getById(promotorBackgroundDetailRequest.getSalutationId()).getValue();
				}
				promotorName += promotorBackgroundDetailRequest.getPromotorsName();
				promotorBackgroundDetailResponse.setPromotorsName(promotorName);
				promotorBackgroundDetailResponse.setQualification(promotorBackgroundDetailRequest.getQualification());
				promotorBackgroundDetailResponse.setTotalExperience(promotorBackgroundDetailRequest.getTotalExperience());
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
		}*/
		return map;
	}
	public FinancialInputRequestString calculateIRRScore(Long userId, Long applicationId, String industry, Long denomination) throws Exception {
		
		FinancialInputRequest financialInputRequest= irrService.cmaIrrMappingService(userId, applicationId, null, denomination);
		logger.info("Financial Input=================================>"+financialInputRequest.toString());
		
		FinancialInputRequestString financialInputRequestString = new FinancialInputRequestString();
		
		financialInputRequestString.setGrossSalesFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getGrossBlockFy())? String.format ("%.2f", financialInputRequest.getGrossBlockFy()): "0");
		financialInputRequestString.setGrossSalesSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getGrossBlockSy())? String.format ("%.2f", financialInputRequest.getGrossBlockSy()): "0");
		financialInputRequestString.setGrossSalesTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getGrossBlockTy())? String.format ("%.2f", financialInputRequest.getGrossBlockTy()): "0");
		
		financialInputRequestString.setLessExciseDuityFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLessExciseDuityFy())? String.format ("%.2f", financialInputRequest.getLessExciseDuityFy()): "0");
		financialInputRequestString.setLessExciseDuitySy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLessExciseDuitySy())? String.format ("%.2f", financialInputRequest.getLessExciseDuitySy()): "0");
		financialInputRequestString.setLessExciseDuityTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLessExciseDuityTy())? String.format ("%.2f", financialInputRequest.getLessExciseDuityTy()): "0");
		
		financialInputRequestString.setIncreaseDecreaseStockFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getIncreaseDecreaseStockFy())? String.format ("%.2f", financialInputRequest.getIncreaseDecreaseStockFy()): "0");
		financialInputRequestString.setIncreaseDecreaseStockSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getIncreaseDecreaseStockSy())? String.format ("%.2f", financialInputRequest.getIncreaseDecreaseStockSy()): "0");
		financialInputRequestString.setIncreaseDecreaseStockTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getIncreaseDecreaseStockTy())? String.format ("%.2f", financialInputRequest.getIncreaseDecreaseStockTy()): "0");
		
		financialInputRequestString.setRawMaterialConsumedFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getRawMaterialConsumedFy())? String.format ("%.2f", financialInputRequest.getRawMaterialConsumedFy()): "0");
		financialInputRequestString.setRawMaterialConsumedSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getRawMaterialConsumedSy())? String.format ("%.2f", financialInputRequest.getRawMaterialConsumedSy()): "0");
		financialInputRequestString.setRawMaterialConsumedTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getRawMaterialConsumedTy())? String.format ("%.2f", financialInputRequest.getRawMaterialConsumedTy()): "0");
		
		financialInputRequestString.setPowerAndFuelCostFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getPowerAndFuelCostFy())? String.format ("%.2f", financialInputRequest.getPowerAndFuelCostFy()): "0");
		financialInputRequestString.setPowerAndFuelCostSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getPowerAndFuelCostSy())? String.format ("%.2f", financialInputRequest.getPowerAndFuelCostSy()): "0");
		financialInputRequestString.setPowerAndFuelCostTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getPowerAndFuelCostTy())? String.format ("%.2f", financialInputRequest.getPowerAndFuelCostTy()): "0");
		
		financialInputRequestString.setEmployeeCostFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getEmployeeCostFy())? String.format ("%.2f", financialInputRequest.getEmployeeCostFy()): "0");
		financialInputRequestString.setEmployeeCostSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getEmployeeCostSy())? String.format ("%.2f", financialInputRequest.getEmployeeCostSy()): "0");
		financialInputRequestString.setEmployeeCostTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getEmployeeCostTy())? String.format ("%.2f", financialInputRequest.getEmployeeCostTy()): "0");
		
		financialInputRequestString.setGeneralAndAdminExpeFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getGeneralAndAdminExpeFy())? String.format ("%.2f", financialInputRequest.getGeneralAndAdminExpeFy()): "0");
		financialInputRequestString.setGeneralAndAdminExpeSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getGeneralAndAdminExpeSy())? String.format ("%.2f", financialInputRequest.getGeneralAndAdminExpeSy()): "0");
		financialInputRequestString.setGeneralAndAdminExpeTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getGeneralAndAdminExpeTy())? String.format ("%.2f", financialInputRequest.getGeneralAndAdminExpeTy()): "0");
		
		financialInputRequestString.setSellingAndDistriExpeFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getSellingAndDistriExpeFy())? String.format ("%.2f", financialInputRequest.getSellingAndDistriExpeFy()): "0");
		financialInputRequestString.setSellingAndDistriExpeSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getSellingAndDistriExpeSy())? String.format ("%.2f", financialInputRequest.getSellingAndDistriExpeSy()): "0");		
		financialInputRequestString.setSellingAndDistriExpeTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getSellingAndDistriExpeTy())? String.format ("%.2f", financialInputRequest.getSellingAndDistriExpeTy()): "0");
		
		financialInputRequestString.setMiscelExpeFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getMiscelExpeFy())? String.format ("%.2f", financialInputRequest.getMiscelExpeFy()): "0");
		financialInputRequestString.setMiscelExpeSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getMiscelExpeSy())? String.format ("%.2f", financialInputRequest.getMiscelExpeSy()): "0");		
		financialInputRequestString.setMiscelExpeTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getMiscelExpeTy())? String.format ("%.2f", financialInputRequest.getMiscelExpeTy()): "0");
		
		financialInputRequestString.setLessExpeCapitaFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLessExpeCapitaFy())? String.format ("%.2f", financialInputRequest.getLessExpeCapitaFy()): "0");
		financialInputRequestString.setLessExpeCapitaSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLessExpeCapitaSy())? String.format ("%.2f", financialInputRequest.getLessExpeCapitaSy()): "0");		
		financialInputRequestString.setLessExpeCapitaTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLessExpeCapitaTy())? String.format ("%.2f", financialInputRequest.getLessExpeCapitaTy()): "0");
		
		financialInputRequestString.setOtherIncomeFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherIncomeFy())? String.format ("%.2f", financialInputRequest.getOtherIncomeFy()): "0");
		financialInputRequestString.setOtherIncomeSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherIncomeSy())? String.format ("%.2f", financialInputRequest.getOtherIncomeSy()): "0");
		financialInputRequestString.setOtherIncomeTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherIncomeTy())? String.format ("%.2f", financialInputRequest.getOtherIncomeTy()): "0");
		
		financialInputRequestString.setInterestFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getInterestFy())? String.format ("%.2f", financialInputRequest.getInterestFy()): "0");
		financialInputRequestString.setInterestSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getInterestSy())? String.format ("%.2f", financialInputRequest.getInterestSy()): "0");
		financialInputRequestString.setInterestTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getInterestTy())? String.format ("%.2f", financialInputRequest.getInterestTy()): "0");
		
		financialInputRequestString.setDepriciationFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getDepriciationFy())? String.format ("%.2f", financialInputRequest.getDepriciationFy()): "0");
		financialInputRequestString.setDepriciationSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getDepriciationSy())? String.format ("%.2f", financialInputRequest.getDepriciationSy()): "0");
		financialInputRequestString.setDepriciationTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getDepriciationTy())? String.format ("%.2f", financialInputRequest.getDepriciationTy()): "0");
		
		financialInputRequestString.setExceptionalIncomeFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getExceptionalIncomeFy())? String.format ("%.2f", financialInputRequest.getExceptionalIncomeFy()): "0");
		financialInputRequestString.setExceptionalIncomeSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getExceptionalIncomeSy())? String.format ("%.2f", financialInputRequest.getExceptionalIncomeSy()): "0");
		financialInputRequestString.setExceptionalIncomeTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getExceptionalIncomeTy())? String.format ("%.2f", financialInputRequest.getExceptionalIncomeTy()): "0");
		
		financialInputRequestString.setProvisionForTaxFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getProvisionForTaxFy())? String.format ("%.2f", financialInputRequest.getProvisionForTaxFy()): "0");
		financialInputRequestString.setProvisionForTaxSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getProvisionForTaxSy())? String.format ("%.2f", financialInputRequest.getProvisionForTaxSy()): "0");
		financialInputRequestString.setProvisionForTaxTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getProvisionForTaxTy())? String.format ("%.2f", financialInputRequest.getProvisionForTaxTy()): "0");
		
		financialInputRequestString.setDividendPayOutFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getDividendPayOutFy())? String.format ("%.2f", financialInputRequest.getDividendPayOutFy()): "0");
		financialInputRequestString.setDividendPayOutSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getDividendPayOutSy())? String.format ("%.2f", financialInputRequest.getDividendPayOutSy()): "0");
		financialInputRequestString.setDividendPayOutTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getDividendPayOutTy())? String.format ("%.2f", financialInputRequest.getDividendPayOutTy()): "0");
		
		financialInputRequestString.setShareCapitalFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareCapitalFy())? String.format ("%.2f", financialInputRequest.getShareCapitalFy()): "0");
		financialInputRequestString.setShareCapitalSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareCapitalSy())? String.format ("%.2f", financialInputRequest.getShareCapitalSy()): "0");
		financialInputRequestString.setShareCapitalTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareCapitalTy())? String.format ("%.2f", financialInputRequest.getShareCapitalTy()): "0");
		
		financialInputRequestString.setShareWarrantOutstandingsFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareWarrantOutstandingsFy())? String.format ("%.2f", financialInputRequest.getShareWarrantOutstandingsFy()): "0");
		financialInputRequestString.setShareWarrantOutstandingsSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareWarrantOutstandingsSy())? String.format ("%.2f", financialInputRequest.getShareWarrantOutstandingsSy()): "0");
		financialInputRequestString.setShareWarrantOutstandingsTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShareWarrantOutstandingsTy())? String.format ("%.2f", financialInputRequest.getShareWarrantOutstandingsTy()): "0");
		
		financialInputRequestString.setRevalationReserveFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getRevalationReserveFy())? String.format ("%.2f", financialInputRequest.getRevalationReserveFy()): "0");
		financialInputRequestString.setRevalationReserveSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getRevalationReserveSy())? String.format ("%.2f", financialInputRequest.getRevalationReserveSy()): "0");
		financialInputRequestString.setRevalationReserveTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getRevalationReserveTy())? String.format ("%.2f", financialInputRequest.getRevalationReserveTy()): "0");
		
		financialInputRequestString.setOtherReserveAndSurplusFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherReserveAndSurplusFy())? String.format ("%.2f", financialInputRequest.getOtherReserveAndSurplusFy()): "0");
		financialInputRequestString.setOtherReserveAndSurplusSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherReserveAndSurplusSy())? String.format ("%.2f", financialInputRequest.getOtherReserveAndSurplusSy()): "0");
		financialInputRequestString.setOtherReserveAndSurplusTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherReserveAndSurplusTy())? String.format ("%.2f", financialInputRequest.getOtherReserveAndSurplusTy()): "0");
		
		financialInputRequestString.setMinorityInterestFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getMinorityInterestFy())? String.format ("%.2f", financialInputRequest.getMinorityInterestFy()): "0");
		financialInputRequestString.setMinorityInterestSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getMinorityInterestSy())? String.format ("%.2f", financialInputRequest.getMinorityInterestSy()): "0");
		financialInputRequestString.setMinorityInterestTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getMinorityInterestTy())? String.format ("%.2f", financialInputRequest.getMinorityInterestTy()): "0");
		
		financialInputRequestString.setSecuredLoansFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getSecuredLoansFy())? String.format ("%.2f", financialInputRequest.getSecuredLoansFy()): "0");
		financialInputRequestString.setSecuredLoansSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getSecuredLoansSy())? String.format ("%.2f", financialInputRequest.getSecuredLoansSy()): "0");
		financialInputRequestString.setSecuredLoansTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getSecuredLoansTy())? String.format ("%.2f", financialInputRequest.getSecuredLoansTy()): "0");
		
		financialInputRequestString.setUnsecuredLoansPromotersFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getUnsecuredLoansPromotersFy())? String.format ("%.2f", financialInputRequest.getUnsecuredLoansPromotersFy()): "0");
		financialInputRequestString.setUnsecuredLoansPromotersSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getUnsecuredLoansPromotersSy())? String.format ("%.2f", financialInputRequest.getUnsecuredLoansPromotersSy()): "0");
		financialInputRequestString.setUnsecuredLoansPromotersTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getUnsecuredLoansPromotersTy())? String.format ("%.2f", financialInputRequest.getUnsecuredLoansPromotersTy()): "0");
		
		financialInputRequestString.setUnsecuredLoansOthersFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getUnsecuredLoansOthersFy())? String.format ("%.2f", financialInputRequest.getUnsecuredLoansOthersFy()): "0");
		financialInputRequestString.setUnsecuredLoansOthersSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getUnsecuredLoansOthersSy())? String.format ("%.2f", financialInputRequest.getUnsecuredLoansOthersSy()): "0");
		financialInputRequestString.setUnsecuredLoansOthersTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getUnsecuredLoansOthersTy())? String.format ("%.2f", financialInputRequest.getUnsecuredLoansOthersTy()): "0");
		
		financialInputRequestString.setDeferredTaxLiablitiesFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getDeferredTaxLiablitiesFy())? String.format ("%.2f", financialInputRequest.getDeferredTaxLiablitiesFy()): "0");
		financialInputRequestString.setDeferredTaxLiablitiesSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getDeferredTaxLiablitiesSy())? String.format ("%.2f", financialInputRequest.getDeferredTaxLiablitiesSy()): "0");
		financialInputRequestString.setDeferredTaxLiablitiesTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getDeferredTaxLiablitiesTy())? String.format ("%.2f", financialInputRequest.getDeferredTaxLiablitiesTy()): "0");
		
		financialInputRequestString.setOtherLongTermLiablitiesFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherLongTermLiablitiesFy())? String.format ("%.2f", financialInputRequest.getOtherLongTermLiablitiesFy()): "0");
		financialInputRequestString.setOtherLongTermLiablitiesSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherLongTermLiablitiesSy())? String.format ("%.2f", financialInputRequest.getOtherLongTermLiablitiesSy()): "0");
		financialInputRequestString.setOtherLongTermLiablitiesTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherLongTermLiablitiesTy())? String.format ("%.2f", financialInputRequest.getOtherLongTermLiablitiesTy()): "0");
		
		financialInputRequestString.setOtherBorrowingFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherBorrowingFy())? String.format ("%.2f", financialInputRequest.getOtherBorrowingFy()): "0");
		financialInputRequestString.setOtherBorrowingSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherBorrowingSy())? String.format ("%.2f", financialInputRequest.getOtherBorrowingSy()): "0");
		financialInputRequestString.setOtherBorrowingTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherBorrowingTy())? String.format ("%.2f", financialInputRequest.getOtherBorrowingTy()): "0");
		
		financialInputRequestString.setLongTermProvisionFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLongTermProvisionFy())? String.format ("%.2f", financialInputRequest.getLongTermProvisionFy()): "0");
		financialInputRequestString.setLongTermProvisionSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLongTermProvisionSy())? String.format ("%.2f", financialInputRequest.getLongTermProvisionSy()): "0");
		financialInputRequestString.setLongTermProvisionTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLongTermProvisionTy())? String.format ("%.2f", financialInputRequest.getLongTermProvisionTy()): "0");

		financialInputRequestString.setTradePayablesFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getTradePayablesFy())? String.format ("%.2f", financialInputRequest.getTradePayablesFy()): "0");
		financialInputRequestString.setTradePayablesSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getTradePayablesSy())? String.format ("%.2f", financialInputRequest.getTradePayablesSy()): "0");
		financialInputRequestString.setTradePayablesTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getTradePayablesTy())? String.format ("%.2f", financialInputRequest.getTradePayablesTy()): "0");
		
		financialInputRequestString.setOtherCurruntLiablitiesFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherCurruntLiablitiesFy())? String.format ("%.2f", financialInputRequest.getOtherCurruntLiablitiesFy()): "0");
		financialInputRequestString.setOtherCurruntLiablitiesSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherCurruntLiablitiesSy())? String.format ("%.2f", financialInputRequest.getOtherCurruntLiablitiesSy()): "0");
		financialInputRequestString.setOtherCurruntLiablitiesTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherCurruntLiablitiesTy())? String.format ("%.2f", financialInputRequest.getOtherCurruntLiablitiesTy()): "0");
		
		financialInputRequestString.setShortTermProvisionFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShortTermProvisionFy())? String.format ("%.2f", financialInputRequest.getShortTermProvisionFy()): "0");
		financialInputRequestString.setShortTermProvisionSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShortTermProvisionSy())? String.format ("%.2f", financialInputRequest.getShortTermProvisionSy()): "0");
		financialInputRequestString.setShortTermProvisionTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShortTermProvisionTy())? String.format ("%.2f", financialInputRequest.getShortTermProvisionTy()): "0");
		
		financialInputRequestString.setGrossBlockFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getGrossBlockFy())? String.format ("%.2f", financialInputRequest.getGrossBlockFy()): "0");
		financialInputRequestString.setGrossBlockSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getGrossBlockSy())? String.format ("%.2f", financialInputRequest.getGrossBlockSy()): "0");
		financialInputRequestString.setGrossBlockTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getGrossBlockTy())? String.format ("%.2f", financialInputRequest.getGrossBlockTy()): "0");
		
		financialInputRequestString.setLessAccumulatedDepreFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLessAccumulatedDepreFy())? String.format ("%.2f", financialInputRequest.getLessAccumulatedDepreFy()): "0");
		financialInputRequestString.setLessAccumulatedDepreSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLessAccumulatedDepreSy())? String.format ("%.2f", financialInputRequest.getLessAccumulatedDepreSy()): "0");
		financialInputRequestString.setLessAccumulatedDepreTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLessAccumulatedDepreTy())? String.format ("%.2f", financialInputRequest.getLessAccumulatedDepreTy()): "0");
		
		financialInputRequestString.setImpairmentofAssetFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getImpairmentofAssetFy())? String.format ("%.2f", financialInputRequest.getImpairmentofAssetFy()): "0");
		financialInputRequestString.setImpairmentofAssetSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getImpairmentofAssetSy())? String.format ("%.2f", financialInputRequest.getImpairmentofAssetSy()): "0");
		financialInputRequestString.setImpairmentofAssetTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getImpairmentofAssetTy())? String.format ("%.2f", financialInputRequest.getImpairmentofAssetTy()): "0");
		
		financialInputRequestString.setCapitalWorkInProgressFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getCapitalWorkInProgressFy())? String.format ("%.2f", financialInputRequest.getCapitalWorkInProgressFy()): "0");
		financialInputRequestString.setCapitalWorkInProgressSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getCapitalWorkInProgressSy())? String.format ("%.2f", financialInputRequest.getCapitalWorkInProgressSy()): "0");
		financialInputRequestString.setCapitalWorkInProgressTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getCapitalWorkInProgressTy())? String.format ("%.2f", financialInputRequest.getCapitalWorkInProgressTy()): "0");
		
		financialInputRequestString.setIntengibleAssetsFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getIntengibleAssetsFy())? String.format ("%.2f", financialInputRequest.getIntengibleAssetsFy()): "0");
		financialInputRequestString.setIntengibleAssetsSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getIntengibleAssetsSy())? String.format ("%.2f", financialInputRequest.getIntengibleAssetsSy()): "0");
		financialInputRequestString.setIntengibleAssetsTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getIntengibleAssetsTy())? String.format ("%.2f", financialInputRequest.getIntengibleAssetsTy()): "0");
		
		financialInputRequestString.setPreOperativeExpeFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getPreOperativeExpeFy())? String.format ("%.2f", financialInputRequest.getPreOperativeExpeFy()): "0");
		financialInputRequestString.setPreOperativeExpeSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getPreOperativeExpeSy())? String.format ("%.2f", financialInputRequest.getPreOperativeExpeSy()): "0");
		financialInputRequestString.setPreOperativeExpeTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getPreOperativeExpeTy())? String.format ("%.2f", financialInputRequest.getPreOperativeExpeTy()): "0");
		
		financialInputRequestString.setAssetInTransitFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getAssetInTransitFy())? String.format ("%.2f", financialInputRequest.getAssetInTransitFy()): "0");
		financialInputRequestString.setAssetInTransitSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getAssetInTransitSy())? String.format ("%.2f", financialInputRequest.getAssetInTransitSy()): "0");
		financialInputRequestString.setAssetInTransitTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getAssetInTransitTy())? String.format ("%.2f", financialInputRequest.getAssetInTransitTy()): "0");
		
		financialInputRequestString.setInvestmentInSubsidiariesFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getInvestmentInSubsidiariesFy())? String.format ("%.2f", financialInputRequest.getInvestmentInSubsidiariesFy()): "0");
		financialInputRequestString.setInvestmentInSubsidiariesSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getInvestmentInSubsidiariesSy())? String.format ("%.2f", financialInputRequest.getInvestmentInSubsidiariesSy()): "0");
		financialInputRequestString.setInvestmentInSubsidiariesTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getInvestmentInSubsidiariesTy())? String.format ("%.2f", financialInputRequest.getInvestmentInSubsidiariesTy()): "0");
		
		financialInputRequestString.setOtherInvestmentFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherInvestmentFy())? String.format ("%.2f", financialInputRequest.getOtherInvestmentFy()): "0");
		financialInputRequestString.setOtherInvestmentSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherInvestmentSy())? String.format ("%.2f", financialInputRequest.getOtherInvestmentSy()): "0");
		financialInputRequestString.setOtherInvestmentTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherInvestmentTy())? String.format ("%.2f", financialInputRequest.getOtherInvestmentTy()): "0");

		financialInputRequestString.setLongTermLoansAndAdvaFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLongTermLoansAndAdvaFy())? String.format ("%.2f", financialInputRequest.getLongTermLoansAndAdvaFy()): "0");
		financialInputRequestString.setLongTermLoansAndAdvaSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLongTermLoansAndAdvaSy())? String.format ("%.2f", financialInputRequest.getLongTermLoansAndAdvaSy()): "0");
		financialInputRequestString.setLongTermLoansAndAdvaTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getLongTermLoansAndAdvaTy())? String.format ("%.2f", financialInputRequest.getLongTermLoansAndAdvaTy()): "0");
		
		financialInputRequestString.setOtheNonCurruntAssetFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtheNonCurruntAssetFy())? String.format ("%.2f", financialInputRequest.getOtheNonCurruntAssetFy()): "0");
		financialInputRequestString.setOtheNonCurruntAssetSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtheNonCurruntAssetSy())? String.format ("%.2f", financialInputRequest.getOtheNonCurruntAssetSy()): "0");
		financialInputRequestString.setOtheNonCurruntAssetTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtheNonCurruntAssetTy())? String.format ("%.2f", financialInputRequest.getOtheNonCurruntAssetTy()): "0");
		
		financialInputRequestString.setInventoriesFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getInventoriesFy())? String.format ("%.2f", financialInputRequest.getInventoriesFy()): "0");
		financialInputRequestString.setInventoriesSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getInventoriesSy())? String.format ("%.2f", financialInputRequest.getInventoriesSy()): "0");
		financialInputRequestString.setInventoriesTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getInventoriesTy())? String.format ("%.2f", financialInputRequest.getInventoriesTy()): "0");
		
		financialInputRequestString.setSundryDebtorsFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getSundryDebtorsFy())? String.format ("%.2f", financialInputRequest.getSundryDebtorsFy()): "0");
		financialInputRequestString.setSundryDebtorsSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getSundryDebtorsSy())? String.format ("%.2f", financialInputRequest.getSundryDebtorsSy()): "0");
		financialInputRequestString.setSundryDebtorsTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getSundryDebtorsTy())? String.format ("%.2f", financialInputRequest.getSundryDebtorsTy()): "0");
		
		financialInputRequestString.setCashAndBankFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getCashAndBankFy())? String.format ("%.2f", financialInputRequest.getCashAndBankFy()): "0");
		financialInputRequestString.setCashAndBankSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getCashAndBankSy())? String.format ("%.2f", financialInputRequest.getCashAndBankSy()): "0");
		financialInputRequestString.setCashAndBankTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getCashAndBankTy())? String.format ("%.2f", financialInputRequest.getCashAndBankTy()): "0");
		
		financialInputRequestString.setOtherCurruntAssetFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherCurruntAssetFy())? String.format ("%.2f", financialInputRequest.getOtherCurruntAssetFy()): "0");
		financialInputRequestString.setOtherCurruntAssetSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherCurruntAssetSy())? String.format ("%.2f", financialInputRequest.getOtherCurruntAssetSy()): "0");
		financialInputRequestString.setOtherCurruntAssetTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getOtherCurruntAssetTy())? String.format ("%.2f", financialInputRequest.getOtherCurruntAssetTy()): "0");
		
		financialInputRequestString.setShortTermLoansAdvancesFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShortTermLoansAdvancesFy())? String.format ("%.2f", financialInputRequest.getShortTermLoansAdvancesFy()): "0");
		financialInputRequestString.setShortTermLoansAdvancesSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShortTermLoansAdvancesSy())? String.format ("%.2f", financialInputRequest.getShortTermLoansAdvancesSy()): "0");
		financialInputRequestString.setShortTermLoansAdvancesTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getShortTermLoansAdvancesTy())? String.format ("%.2f", financialInputRequest.getShortTermLoansAdvancesTy()): "0");
		
		financialInputRequestString.setContingentLiablitiesFy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getContingentLiablitiesFy())? String.format ("%.2f", financialInputRequest.getContingentLiablitiesFy()): "0");
		financialInputRequestString.setContingentLiablitiesSy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getContingentLiablitiesSy())? String.format ("%.2f", financialInputRequest.getContingentLiablitiesSy()): "0");
		financialInputRequestString.setContingentLiablitiestTy(!CommonUtils.isObjectNullOrEmpty(financialInputRequest.getContingentLiablitiestTy())? String.format ("%.2f", financialInputRequest.getContingentLiablitiestTy()): "0");
		
		//Profit & Loss Statement
		financialInputRequestString.setNetSaleFy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getGrossSalesFy(), financialInputRequest.getLessExciseDuityFy())));
		financialInputRequestString.setNetSaleSy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getGrossSalesSy(), financialInputRequest.getLessExciseDuitySy())));
		financialInputRequestString.setNetSaleTy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getGrossSalesTy(), financialInputRequest.getLessExciseDuityTy())));
		financialInputRequestString.setTotalExpenditureFy(String.format ("%.2f", CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockFy(),financialInputRequest.getRawMaterialConsumedFy(),financialInputRequest.getPowerAndFuelCostFy(),financialInputRequest.getEmployeeCostFy(), financialInputRequest.getGeneralAndAdminExpeFy(),financialInputRequest.getSellingAndDistriExpeFy(),financialInputRequest.getMiscelExpeFy()), financialInputRequest.getLessExpeCapitaFy())));
		financialInputRequestString.setTotalExpenditureSy(String.format ("%.2f", CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockSy(),financialInputRequest.getRawMaterialConsumedSy(),financialInputRequest.getPowerAndFuelCostSy(),financialInputRequest.getEmployeeCostSy(), financialInputRequest.getGeneralAndAdminExpeSy(),financialInputRequest.getSellingAndDistriExpeSy(),financialInputRequest.getMiscelExpeSy()), financialInputRequest.getLessExpeCapitaSy())));
		financialInputRequestString.setTotalExpenditureTy(String.format ("%.2f", CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockTy(),financialInputRequest.getRawMaterialConsumedTy(),financialInputRequest.getPowerAndFuelCostTy(),financialInputRequest.getEmployeeCostTy(), financialInputRequest.getGeneralAndAdminExpeTy(),financialInputRequest.getSellingAndDistriExpeTy(),financialInputRequest.getMiscelExpeTy()), financialInputRequest.getLessExpeCapitaTy())));
		financialInputRequestString.setOperatingProfitExclOiFy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getNetSaleFy(),financialInputRequest.getTotalExpenditureFy())));
		financialInputRequestString.setOperatingProfitExclOiSy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getNetSaleSy(),financialInputRequest.getTotalExpenditureSy())));
		financialInputRequestString.setOperatingProfitExclOiTy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getNetSaleTy(),financialInputRequest.getTotalExpenditureTy())));
		financialInputRequestString.setOperatingProfitEbitadOiFy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiFy(),financialInputRequest.getOtherIncomeFy())));
		financialInputRequestString.setOperatingProfitEbitadOiSy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiSy(),financialInputRequest.getOtherIncomeSy())));
		financialInputRequestString.setOperatingProfitEbitadOiTy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiTy(),financialInputRequest.getOtherIncomeTy())));
		financialInputRequestString.setPbdtFy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiFy(), financialInputRequest.getInterestFy())));
		financialInputRequestString.setPbdtSy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiSy(), financialInputRequest.getInterestSy())));
		financialInputRequestString.setPbdtTy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiTy(), financialInputRequest.getInterestTy())));
		financialInputRequestString.setProfitBeforeTaxationFy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getPbdtFy(), financialInputRequest.getDepriciationFy())));
		financialInputRequestString.setProfitBeforeTaxationSy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getPbdtSy(), financialInputRequest.getDepriciationSy())));
		financialInputRequestString.setProfitBeforeTaxationTy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getPbdtTy(), financialInputRequest.getDepriciationTy())));
		financialInputRequestString.setProfitBeforeTaxFy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationFy(), financialInputRequest.getExceptionalIncomeFy())));
		financialInputRequestString.setProfitBeforeTaxSy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationSy(), financialInputRequest.getExceptionalIncomeSy())));
		financialInputRequestString.setProfitBeforeTaxTy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationTy(), financialInputRequest.getExceptionalIncomeTy())));
		financialInputRequestString.setProfitAfterTaxFy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxFy(), financialInputRequest.getProvisionForTaxFy())));
		financialInputRequestString.setProfitAfterTaxSy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxSy(), financialInputRequest.getProvisionForTaxSy())));
		financialInputRequestString.setProfitAfterTaxTy(String.format ("%.2f", CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxTy(), financialInputRequest.getProvisionForTaxTy())));
		
		
		if(financialInputRequest.getDividendPayOutFy() == 0)
			financialInputRequestString.setEquityDividendFy("0.0");
		else
			financialInputRequestString.setEquityDividendFy(String.format ("%.2f", (financialInputRequest.getDividendPayOutFy()*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalFy())));
		
		if(financialInputRequest.getDividendPayOutSy() == 0)
			financialInputRequestString.setEquityDividendSy("0.0");
		else
			financialInputRequestString.setEquityDividendSy(String.format ("%.2f", financialInputRequest.getDividendPayOutSy()*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalSy()));
		
		if(financialInputRequest.getDividendPayOutTy() == 0)
			financialInputRequestString.setEquityDividendTy("0.0");
		else
			financialInputRequest.setEquityDividendTy(financialInputRequest.getDividendPayOutTy()*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalTy());
		
		if(financialInputRequest.getShareCapitalFy() == 0.0 ) {
			financialInputRequestString.setEarningPerShareFy("0.0");
		}else {
			financialInputRequestString.setEarningPerShareFy(String.format ("%.2f", ((financialInputRequest.getProfitAfterTaxFy())*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalFy())));
		}
		if(financialInputRequest.getShareCapitalSy() == 0.0 ) {
			financialInputRequestString.setEarningPerShareSy("0.0");
		}else {
			financialInputRequestString.setEarningPerShareSy(String.format ("%.2f", ((financialInputRequest.getProfitAfterTaxSy())*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalSy())));
		}
		if(financialInputRequest.getShareCapitalTy() == 0.0 ) {
			financialInputRequestString.setEarningPerShareTy("0.0");
		}else {
			financialInputRequestString.setEarningPerShareTy(String.format ("%.2f", ((financialInputRequest.getProfitAfterTaxTy())*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalTy())));
		}
		
		
		//Balance Sheet -Equities and Liabilities
		financialInputRequestString.setShareHolderFundsFy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getShareCapitalFy(),financialInputRequest.getShareWarrantOutstandingsFy(),financialInputRequest.getRevalationReserveFy(),financialInputRequest.getOtherReserveAndSurplusFy())));
		financialInputRequestString.setShareHolderFundsSy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getShareCapitalSy(),financialInputRequest.getShareWarrantOutstandingsSy(),financialInputRequest.getRevalationReserveSy(),financialInputRequest.getOtherReserveAndSurplusSy())));
		financialInputRequestString.setShareHolderFundsTy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getShareCapitalTy(),financialInputRequest.getShareWarrantOutstandingsTy(),financialInputRequest.getRevalationReserveTy(),financialInputRequest.getOtherReserveAndSurplusTy())));
		financialInputRequestString.setTotalNonCurruntLiablitiesFy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getMinorityInterestFy(), financialInputRequest.getSecuredLoansFy(), financialInputRequest.getUnsecuredLoansOthersFy(),financialInputRequest.getUnsecuredLoansPromotersFy(),financialInputRequest.getDeferredTaxLiablitiesFy(),financialInputRequest.getOtherLongTermLiablitiesFy(),financialInputRequest.getOtherBorrowingFy(),financialInputRequest.getLongTermProvisionFy())));
		financialInputRequestString.setTotalNonCurruntLiablitiesSy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getMinorityInterestSy(), financialInputRequest.getSecuredLoansSy(), financialInputRequest.getUnsecuredLoansOthersSy(),financialInputRequest.getUnsecuredLoansPromotersSy(),financialInputRequest.getDeferredTaxLiablitiesSy(),financialInputRequest.getOtherLongTermLiablitiesSy(),financialInputRequest.getOtherBorrowingSy(),financialInputRequest.getLongTermProvisionSy())));
		financialInputRequestString.setTotalNonCurruntLiablitiesTy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getMinorityInterestTy(), financialInputRequest.getSecuredLoansTy(), financialInputRequest.getUnsecuredLoansOthersTy(),financialInputRequest.getUnsecuredLoansPromotersTy(),financialInputRequest.getDeferredTaxLiablitiesTy(),financialInputRequest.getOtherLongTermLiablitiesTy(),financialInputRequest.getOtherBorrowingTy(),financialInputRequest.getLongTermProvisionTy())));
		financialInputRequestString.setTotalCurruntLiablitiesFy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getTradePayablesFy(), financialInputRequest.getOtherCurruntLiablitiesFy(), financialInputRequest.getShortTermProvisionFy())));
		financialInputRequestString.setTotalCurruntLiablitiesSy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getTradePayablesSy(), financialInputRequest.getOtherCurruntLiablitiesSy(), financialInputRequest.getShortTermProvisionSy())));
		financialInputRequestString.setTotalCurruntLiablitiesTy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getTradePayablesTy(), financialInputRequest.getOtherCurruntLiablitiesTy(), financialInputRequest.getShortTermProvisionTy())));
		financialInputRequestString.setTotalLiablitiesFy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsFy(), financialInputRequest.getTotalNonCurruntLiablitiesFy(), financialInputRequest.getTotalCurruntLiablitiesFy())));
		financialInputRequestString.setTotalLiablitiesSy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsSy(), financialInputRequest.getTotalNonCurruntLiablitiesSy(), financialInputRequest.getTotalCurruntLiablitiesSy())));
		financialInputRequestString.setTotalLiablitiesTy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsTy(), financialInputRequest.getTotalNonCurruntLiablitiesTy(), financialInputRequest.getTotalCurruntLiablitiesTy())));
		
		//Balance Sheet -ASSETS
		financialInputRequestString.setNetBlockFy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getGrossBlockFy(), financialInputRequest.getLessAccumulatedDepreFy(),financialInputRequest.getImpairmentofAssetFy())));
		financialInputRequestString.setNetBlockSy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getGrossBlockSy(), financialInputRequest.getLessAccumulatedDepreSy(),financialInputRequest.getImpairmentofAssetSy())));
		financialInputRequestString.setNetBlockTy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getGrossBlockTy(), financialInputRequest.getLessAccumulatedDepreTy(),financialInputRequest.getImpairmentofAssetTy())));
		financialInputRequestString.setTotalNonCurruntAssetFy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressFy(), financialInputRequest.getIntengibleAssetsFy(), financialInputRequest.getPreOperativeExpeFy(), financialInputRequest.getAssetInTransitFy(), financialInputRequest.getInvestmentInSubsidiariesFy(), financialInputRequest.getOtherInvestmentFy(), financialInputRequest.getLongTermLoansAndAdvaFy(), financialInputRequest.getOtheNonCurruntAssetFy())));
		financialInputRequestString.setTotalNonCurruntAssetSy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressSy(), financialInputRequest.getIntengibleAssetsSy(), financialInputRequest.getPreOperativeExpeSy(), financialInputRequest.getAssetInTransitSy(), financialInputRequest.getInvestmentInSubsidiariesSy(), financialInputRequest.getOtherInvestmentSy(), financialInputRequest.getLongTermLoansAndAdvaSy(), financialInputRequest.getOtheNonCurruntAssetSy())));
		financialInputRequestString.setTotalNonCurruntAssetTy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressTy(), financialInputRequest.getIntengibleAssetsTy(), financialInputRequest.getPreOperativeExpeTy(), financialInputRequest.getAssetInTransitTy(), financialInputRequest.getInvestmentInSubsidiariesTy(), financialInputRequest.getOtherInvestmentTy(), financialInputRequest.getLongTermLoansAndAdvaTy(), financialInputRequest.getOtheNonCurruntAssetTy())));
		financialInputRequestString.setTotalCurruntAssetFy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getInventoriesFy(), financialInputRequest.getSundryDebtorsFy(), financialInputRequest.getCashAndBankFy(), financialInputRequest.getOtherCurruntAssetFy(), financialInputRequest.getShortTermLoansAdvancesFy())));
		financialInputRequestString.setTotalCurruntAssetSy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getInventoriesSy(), financialInputRequest.getSundryDebtorsSy(), financialInputRequest.getCashAndBankSy(), financialInputRequest.getOtherCurruntAssetSy(), financialInputRequest.getShortTermLoansAdvancesSy())));
		financialInputRequestString.setTotalCurruntAssetTy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getInventoriesTy(), financialInputRequest.getSundryDebtorsTy(), financialInputRequest.getCashAndBankTy(), financialInputRequest.getOtherCurruntAssetTy(), financialInputRequest.getShortTermLoansAdvancesTy())));
		financialInputRequestString.setTotalAssetFy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getNetBlockFy(), financialInputRequest.getTotalCurruntAssetFy(), financialInputRequest.getTotalNonCurruntAssetFy())));
		financialInputRequestString.setTotalAssetSy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getNetBlockSy(), financialInputRequest.getTotalCurruntAssetSy(), financialInputRequest.getTotalNonCurruntAssetSy())));
		financialInputRequestString.setTotalAssetTy(String.format ("%.2f", CommonUtils.addNumbers(financialInputRequest.getNetBlockTy(), financialInputRequest.getTotalCurruntAssetTy(), financialInputRequest.getTotalNonCurruntAssetTy())));
		financialInputRequestString.setBookValueFy(String.format ("%.2f", financialInputRequest.getShareHolderFundsFy()/(financialInputRequest.getShareCapitalFy()/financialInputRequest.getShareFaceValue())));
		financialInputRequestString.setBookValueSy(String.format ("%.2f", financialInputRequest.getShareHolderFundsSy()/(financialInputRequest.getShareCapitalSy()/financialInputRequest.getShareFaceValue())));
		financialInputRequestString.setBookValueTy(String.format ("%.2f", financialInputRequest.getShareHolderFundsTy()/(financialInputRequest.getShareCapitalTy()/financialInputRequest.getShareFaceValue())));
		return financialInputRequestString;
	}
	
	public String convertValue(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value)? String.format ("%.2f", value): "0";
	}
	
	public static Object convertToString (Object obj) throws Exception {
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
