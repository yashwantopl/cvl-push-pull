package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.lang.reflect.Field;
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
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.OwnershipDetailRequest;
import com.capitaworld.service.loans.model.OwnershipDetailResponse;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.corporate.PrimaryCorporateRequest;
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
import com.capitaworld.service.oneform.enums.EstablishmentMonths;
import com.capitaworld.service.oneform.enums.Industry;
import com.capitaworld.service.oneform.enums.LoanTypeNatureFacility;
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
		try {
			PrimaryCorporateRequest primaryCorporateRequest = primaryCorporateService.get(applicationId, userId);
			map.put("loanAmt", primaryCorporateRequest.getAmount());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//IRR DATA
		try {
			LoanApplicationRequest loanApplicationRequest = loanApplicationService.get(applicationId, userId);
			Integer denomination =loanApplicationRequest.getDenominationId();
			Long denominationValue = denomination.longValue();
			FinancialInputRequest financialInputRequest= calculateIRRScore(userId, applicationId,null, denominationValue);
			map.put("financialInputRequest", financialInputRequest);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//MATCHES RESPONSE
		try {
			MatchRequest matchRequest = new MatchRequest();
			matchRequest.setApplicationId(applicationId);
			matchRequest.setProductId(productId);
			MatchDisplayResponse matchResponse= matchEngineClient.displayMatchesOfCorporate(matchRequest);
			map.put("matchesResponse", printFields(matchResponse.getMatchDisplayObjectList()));
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
			map.put("proposalScoreResponse",proposalScoreResponse);
			
			List<ProposalScoreDetailResponse> proposalScoreDetailResponseList=scoringResponse.getDataList();
			for(ProposalScoreDetailResponse proposalScoreDetailResponse:proposalScoreDetailResponseList)
			{
				switch (proposalScoreDetailResponse.getParameterOption()) {
				case ScoreParameter.COMBINED_NETWORTH:
					map.put("combinedNetworthActual", proposalScoreDetailResponse.getParameterOption());
					map.put("combinedNetworthScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("combinedNetworthScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.CUSTOMER_ASSOCIATE_CONCERN:
					map.put("customerAssociateConcernActual", proposalScoreDetailResponse.getParameterOption());
					map.put("customerAssociateConcernScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("customerAssociateConcernScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.CIBIL_TRANSUNION_SCORE:
					map.put("cibilTransunionScoreActual", proposalScoreDetailResponse.getParameterOption());
					map.put("cibilTransunionScoreScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("cibilTransunionScoreScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.EXPERIENCE_IN_THE_BUSINESS:
					map.put("experienceInBusinessActual", proposalScoreDetailResponse.getParameterOption());
					map.put("experienceInBusinessScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("experienceInBusinessScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.DEBT_EQUITY_RATIO:
					map.put("debtEquityRatioActual", proposalScoreDetailResponse.getParameterOption());
					map.put("debtEquityRatioScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("debtEquityRatioScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.TOL_TNW:
					map.put("tolTnwActual", proposalScoreDetailResponse.getParameterOption());
					map.put("tolTnwScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("tolTnwScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.AVERAGE_CURRENT_RATIO:
					map.put("avgCurrentRatioActual", proposalScoreDetailResponse.getParameterOption());
					map.put("avgCurrentRatioScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("avgCurrentRatioScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.WORKING_CAPITAL_CYCLE:
					map.put("wcCycleActual", proposalScoreDetailResponse.getParameterOption());
					map.put("wcCycleScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("wcCycleScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.AVERAGE_ANNUAL_GROWTH_GROSS_CASH:
					map.put("avgAnnualgrowthGrossActual", proposalScoreDetailResponse.getParameterOption());
					map.put("avgAnnualgrowthGrossScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("avgAnnualgrowthGrossScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.AVERAGE_ANNUAL_GROWTH_NET_SALE:
					map.put("avgAnnualgrowthNetSaleActual", proposalScoreDetailResponse.getParameterOption());
					map.put("avgAnnualgrowthNetSaleScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("avgAnnualgrowthNetSaleScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.AVERAGE_EBIDTA:
					map.put("avgEbidtaActual", proposalScoreDetailResponse.getParameterOption());
					map.put("avgEbidtaScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("avgEbidtaScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS:
					map.put("avgAnnualGrossCashActual", proposalScoreDetailResponse.getParameterOption());
					map.put("avgAnnualGrossCashScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("avgAnnualGrossCashScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.AVERAGE_INTEREST_COV_RATIO:
					map.put("avgIntCovRatioActual", proposalScoreDetailResponse.getParameterOption());
					map.put("avgIntCovRatioScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("avgIntCovRatioScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.NO_OF_CUSTOMER:
					map.put("noOfCustomerActual", proposalScoreDetailResponse.getParameterOption());
					map.put("noOfCustomerScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("noOfCustomerScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.CONCENTRATION_CUSTOMER:
					map.put("concentrationCustomerActual", proposalScoreDetailResponse.getParameterOption());
					map.put("concentrationCustomerScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("concentrationCustomerScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				case ScoreParameter.CREDIT_SUMMATION:
					map.put("creditSummationActual", proposalScoreDetailResponse.getParameterOption());
					map.put("creditSummationScoreActual", proposalScoreDetailResponse.getObtainedScore());
					map.put("creditSummationScoreOutOf", proposalScoreDetailResponse.getMaxScore());
					continue;
				default:
					break;
				}
			}
		}
			catch (Exception e) {
				// TODO: handle exception
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
		}*/
		return map;
	}
	@Override
	public Map<String, Object> getCamReportPrimaryDetails(Long applicationId, Long productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long userId = loanApplicationRepository.getUserIdByApplicationId(applicationId);
		CorporateApplicantRequest corporateApplicantRequest =corporateApplicantService.getCorporateApplicant(applicationId);
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
				map.put("financialArrangments",!CommonUtils.isListNullOrEmpty(financialArrangementsDetailResponseList) ? printFields(financialArrangementsDetailResponseList) : " ");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("Error in getting Financial arrangements data");
			}
		try {
			PrimaryCorporateRequest primaryCorporateRequest = primaryCorporateService.get(applicationId, userId);
			map.put("loanAmt", !CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getLoanAmount()) ? primaryCorporateRequest.getLoanAmount() : " ");
			map.put("loanType", !CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getProductId()) ? LoanType.getType(primaryCorporateRequest.getProductId()) : " ");
			if(primaryCorporateRequest.getBusinessAssetChecked()) {
				map.put("purpose", "Purchase of Business Asset");
			}else if(primaryCorporateRequest.getWorkingCapitalChecked()) {
				map.put("purpose", "For Working Capital");
			}else if(primaryCorporateRequest.getOtherGeneralChecked()) {
				map.put("purpose", "Other General");
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
			Integer denomination =loanApplicationRequest.getDenominationId();
			Long denominationValue = denomination.longValue();
			FinancialInputRequest financialInputRequest= calculateIRRScore(userId, applicationId, null, denominationValue);
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
			map.put("proposalScoreResponse",proposalScoreResponse);
			
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
		
		return map;
	}
	public FinancialInputRequest calculateIRRScore(Long userId, Long applicationId, String industry, Long denomination) throws Exception {
		
		FinancialInputRequest financialInputRequest= irrService.cmaIrrMappingService(userId, applicationId, null, denomination);
		logger.info("Financial Input=================================>"+financialInputRequest.toString());
		//Profit & Loss Statement
		financialInputRequest.setNetSaleFy(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesFy(), financialInputRequest.getLessExciseDuityFy()));
		financialInputRequest.setNetSaleSy(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesSy(), financialInputRequest.getLessExciseDuitySy()));
		financialInputRequest.setNetSaleTy(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesTy(), financialInputRequest.getLessExciseDuityTy()));
		financialInputRequest.setTotalExpenditureFy(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockFy(),financialInputRequest.getRawMaterialConsumedFy(),financialInputRequest.getPowerAndFuelCostFy(),financialInputRequest.getEmployeeCostFy(), financialInputRequest.getGeneralAndAdminExpeFy(),financialInputRequest.getSellingAndDistriExpeFy(),financialInputRequest.getMiscelExpeFy()), financialInputRequest.getLessExpeCapitaFy()));
		financialInputRequest.setTotalExpenditureSy(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockSy(),financialInputRequest.getRawMaterialConsumedSy(),financialInputRequest.getPowerAndFuelCostSy(),financialInputRequest.getEmployeeCostSy(), financialInputRequest.getGeneralAndAdminExpeSy(),financialInputRequest.getSellingAndDistriExpeSy(),financialInputRequest.getMiscelExpeSy()), financialInputRequest.getLessExpeCapitaSy()));
		financialInputRequest.setTotalExpenditureTy(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockTy(),financialInputRequest.getRawMaterialConsumedTy(),financialInputRequest.getPowerAndFuelCostTy(),financialInputRequest.getEmployeeCostTy(), financialInputRequest.getGeneralAndAdminExpeTy(),financialInputRequest.getSellingAndDistriExpeTy(),financialInputRequest.getMiscelExpeTy()), financialInputRequest.getLessExpeCapitaTy()));
		financialInputRequest.setOperatingProfitExclOiFy(CommonUtils.substractNumbers(financialInputRequest.getNetSaleFy(),financialInputRequest.getTotalExpenditureFy()));
		financialInputRequest.setOperatingProfitExclOiSy(CommonUtils.substractNumbers(financialInputRequest.getNetSaleSy(),financialInputRequest.getTotalExpenditureSy()));
		financialInputRequest.setOperatingProfitExclOiTy(CommonUtils.substractNumbers(financialInputRequest.getNetSaleTy(),financialInputRequest.getTotalExpenditureTy()));
		financialInputRequest.setOperatingProfitEbitadOiFy(CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiFy(),financialInputRequest.getOtherIncomeFy()));
		financialInputRequest.setOperatingProfitEbitadOiSy(CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiSy(),financialInputRequest.getOtherIncomeSy()));
		financialInputRequest.setOperatingProfitEbitadOiTy(CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiTy(),financialInputRequest.getOtherIncomeTy()));
		financialInputRequest.setPbdtFy(CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiFy(), financialInputRequest.getInterestFy()));
		financialInputRequest.setPbdtSy(CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiSy(), financialInputRequest.getInterestSy()));
		financialInputRequest.setPbdtTy(CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiTy(), financialInputRequest.getInterestTy()));
		financialInputRequest.setProfitBeforeTaxationFy(CommonUtils.substractNumbers(financialInputRequest.getPbdtFy(), financialInputRequest.getDepriciationFy()));
		financialInputRequest.setProfitBeforeTaxationSy(CommonUtils.substractNumbers(financialInputRequest.getPbdtSy(), financialInputRequest.getDepriciationSy()));
		financialInputRequest.setProfitBeforeTaxationTy(CommonUtils.substractNumbers(financialInputRequest.getPbdtTy(), financialInputRequest.getDepriciationTy()));
		financialInputRequest.setProfitBeforeTaxFy(CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationFy(), financialInputRequest.getExceptionalIncomeFy()));
		financialInputRequest.setProfitBeforeTaxSy(CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationSy(), financialInputRequest.getExceptionalIncomeSy()));
		financialInputRequest.setProfitBeforeTaxTy(CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationTy(), financialInputRequest.getExceptionalIncomeTy()));
		financialInputRequest.setProfitAfterTaxFy(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxFy(), financialInputRequest.getProvisionForTaxFy()));
		financialInputRequest.setProfitAfterTaxSy(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxSy(), financialInputRequest.getProvisionForTaxSy()));
		financialInputRequest.setProfitAfterTaxTy(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxTy(), financialInputRequest.getProvisionForTaxTy()));
		if(financialInputRequest.getDividendPayOutFy() == 0)
			financialInputRequest.setEquityDividendFy(0.0);
		else
			financialInputRequest.setEquityDividendFy(financialInputRequest.getDividendPayOutFy()*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalFy());
		
		if(financialInputRequest.getDividendPayOutSy() == 0)
			financialInputRequest.setEquityDividendSy(0.0);
		else
			financialInputRequest.setEquityDividendSy(financialInputRequest.getDividendPayOutSy()*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalSy());
		
		if(financialInputRequest.getDividendPayOutTy() == 0)
			financialInputRequest.setEquityDividendTy(0.0);
		else
			financialInputRequest.setEquityDividendTy(financialInputRequest.getDividendPayOutTy()*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalTy());
		
		financialInputRequest.setEarningPerShareFy((financialInputRequest.getProfitAfterTaxFy())*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalFy());
		financialInputRequest.setEarningPerShareSy((financialInputRequest.getProfitAfterTaxSy())*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalSy());
		financialInputRequest.setEarningPerShareTy((financialInputRequest.getProfitAfterTaxTy())*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalTy());
		
		//Balance Sheet -Equities and Liabilities
		financialInputRequest.setShareHolderFundsFy(CommonUtils.addNumbers(financialInputRequest.getShareCapitalFy(),financialInputRequest.getShareWarrantOutstandingsFy(),financialInputRequest.getRevalationReserveFy(),financialInputRequest.getOtherReserveAndSurplusFy()));
		financialInputRequest.setShareHolderFundsSy(CommonUtils.addNumbers(financialInputRequest.getShareCapitalSy(),financialInputRequest.getShareWarrantOutstandingsSy(),financialInputRequest.getRevalationReserveSy(),financialInputRequest.getOtherReserveAndSurplusSy()));
		financialInputRequest.setShareHolderFundsTy(CommonUtils.addNumbers(financialInputRequest.getShareCapitalTy(),financialInputRequest.getShareWarrantOutstandingsTy(),financialInputRequest.getRevalationReserveTy(),financialInputRequest.getOtherReserveAndSurplusTy()));
		financialInputRequest.setTotalNonCurruntLiablitiesFy(CommonUtils.addNumbers(financialInputRequest.getMinorityInterestFy(), financialInputRequest.getSecuredLoansFy(), financialInputRequest.getUnsecuredLoansOthersFy(),financialInputRequest.getUnsecuredLoansPromotersFy(),financialInputRequest.getDeferredTaxLiablitiesFy(),financialInputRequest.getOtherLongTermLiablitiesFy(),financialInputRequest.getOtherBorrowingFy(),financialInputRequest.getLongTermProvisionFy()));
		financialInputRequest.setTotalNonCurruntLiablitiesSy(CommonUtils.addNumbers(financialInputRequest.getMinorityInterestSy(), financialInputRequest.getSecuredLoansSy(), financialInputRequest.getUnsecuredLoansOthersSy(),financialInputRequest.getUnsecuredLoansPromotersSy(),financialInputRequest.getDeferredTaxLiablitiesSy(),financialInputRequest.getOtherLongTermLiablitiesSy(),financialInputRequest.getOtherBorrowingSy(),financialInputRequest.getLongTermProvisionSy()));
		financialInputRequest.setTotalNonCurruntLiablitiesTy(CommonUtils.addNumbers(financialInputRequest.getMinorityInterestTy(), financialInputRequest.getSecuredLoansTy(), financialInputRequest.getUnsecuredLoansOthersTy(),financialInputRequest.getUnsecuredLoansPromotersTy(),financialInputRequest.getDeferredTaxLiablitiesTy(),financialInputRequest.getOtherLongTermLiablitiesTy(),financialInputRequest.getOtherBorrowingTy(),financialInputRequest.getLongTermProvisionTy()));
		financialInputRequest.setTotalCurruntLiablitiesFy(CommonUtils.addNumbers(financialInputRequest.getTradePayablesFy(), financialInputRequest.getOtherCurruntLiablitiesFy(), financialInputRequest.getShortTermProvisionFy()));
		financialInputRequest.setTotalCurruntLiablitiesSy(CommonUtils.addNumbers(financialInputRequest.getTradePayablesSy(), financialInputRequest.getOtherCurruntLiablitiesSy(), financialInputRequest.getShortTermProvisionSy()));
		financialInputRequest.setTotalCurruntLiablitiesTy(CommonUtils.addNumbers(financialInputRequest.getTradePayablesTy(), financialInputRequest.getOtherCurruntLiablitiesTy(), financialInputRequest.getShortTermProvisionTy()));
		financialInputRequest.setTotalLiablitiesFy(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsFy(), financialInputRequest.getTotalNonCurruntLiablitiesFy(), financialInputRequest.getTotalCurruntLiablitiesFy()));
		financialInputRequest.setTotalLiablitiesSy(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsSy(), financialInputRequest.getTotalNonCurruntLiablitiesSy(), financialInputRequest.getTotalCurruntLiablitiesSy()));
		financialInputRequest.setTotalLiablitiesTy(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsTy(), financialInputRequest.getTotalNonCurruntLiablitiesTy(), financialInputRequest.getTotalCurruntLiablitiesTy()));
		
		//Balance Sheet -ASSETS
		financialInputRequest.setNetBlockFy(CommonUtils.addNumbers(financialInputRequest.getGrossBlockFy(), financialInputRequest.getLessAccumulatedDepreFy(),financialInputRequest.getImpairmentofAssetFy()));
		financialInputRequest.setNetBlockSy(CommonUtils.addNumbers(financialInputRequest.getGrossBlockSy(), financialInputRequest.getLessAccumulatedDepreSy(),financialInputRequest.getImpairmentofAssetSy()));
		financialInputRequest.setNetBlockTy(CommonUtils.addNumbers(financialInputRequest.getGrossBlockTy(), financialInputRequest.getLessAccumulatedDepreTy(),financialInputRequest.getImpairmentofAssetTy()));
		financialInputRequest.setTotalNonCurruntAssetFy(CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressFy(), financialInputRequest.getIntengibleAssetsFy(), financialInputRequest.getPreOperativeExpeFy(), financialInputRequest.getAssetInTransitFy(), financialInputRequest.getInvestmentInSubsidiariesFy(), financialInputRequest.getOtherInvestmentFy(), financialInputRequest.getLongTermLoansAndAdvaFy(), financialInputRequest.getOtheNonCurruntAssetFy()));
		financialInputRequest.setTotalNonCurruntAssetSy(CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressSy(), financialInputRequest.getIntengibleAssetsSy(), financialInputRequest.getPreOperativeExpeSy(), financialInputRequest.getAssetInTransitSy(), financialInputRequest.getInvestmentInSubsidiariesSy(), financialInputRequest.getOtherInvestmentSy(), financialInputRequest.getLongTermLoansAndAdvaSy(), financialInputRequest.getOtheNonCurruntAssetSy()));
		financialInputRequest.setTotalNonCurruntAssetTy(CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressTy(), financialInputRequest.getIntengibleAssetsTy(), financialInputRequest.getPreOperativeExpeTy(), financialInputRequest.getAssetInTransitTy(), financialInputRequest.getInvestmentInSubsidiariesTy(), financialInputRequest.getOtherInvestmentTy(), financialInputRequest.getLongTermLoansAndAdvaTy(), financialInputRequest.getOtheNonCurruntAssetTy()));
		financialInputRequest.setTotalCurruntAssetFy(CommonUtils.addNumbers(financialInputRequest.getInventoriesFy(), financialInputRequest.getSundryDebtorsFy(), financialInputRequest.getCashAndBankFy(), financialInputRequest.getOtherCurruntAssetFy(), financialInputRequest.getShortTermLoansAdvancesFy()));
		financialInputRequest.setTotalCurruntAssetSy(CommonUtils.addNumbers(financialInputRequest.getInventoriesSy(), financialInputRequest.getSundryDebtorsSy(), financialInputRequest.getCashAndBankSy(), financialInputRequest.getOtherCurruntAssetSy(), financialInputRequest.getShortTermLoansAdvancesSy()));
		financialInputRequest.setTotalCurruntAssetTy(CommonUtils.addNumbers(financialInputRequest.getInventoriesTy(), financialInputRequest.getSundryDebtorsTy(), financialInputRequest.getCashAndBankTy(), financialInputRequest.getOtherCurruntAssetTy(), financialInputRequest.getShortTermLoansAdvancesTy()));
		financialInputRequest.setTotalAssetFy(CommonUtils.addNumbers(financialInputRequest.getNetBlockFy(), financialInputRequest.getTotalCurruntAssetFy(), financialInputRequest.getTotalNonCurruntAssetFy()));
		financialInputRequest.setTotalAssetSy(CommonUtils.addNumbers(financialInputRequest.getNetBlockSy(), financialInputRequest.getTotalCurruntAssetSy(), financialInputRequest.getTotalNonCurruntAssetSy()));
		financialInputRequest.setTotalAssetTy(CommonUtils.addNumbers(financialInputRequest.getNetBlockTy(), financialInputRequest.getTotalCurruntAssetTy(), financialInputRequest.getTotalNonCurruntAssetTy()));
		financialInputRequest.setBookValueFy(financialInputRequest.getShareHolderFundsFy()/(financialInputRequest.getShareCapitalFy()/financialInputRequest.getShareFaceValue()));
		financialInputRequest.setBookValueSy(financialInputRequest.getShareHolderFundsSy()/(financialInputRequest.getShareCapitalSy()/financialInputRequest.getShareFaceValue()));
		financialInputRequest.setBookValueTy(financialInputRequest.getShareHolderFundsTy()/(financialInputRequest.getShareCapitalTy()/financialInputRequest.getShareFaceValue()));
		
		return financialInputRequest;
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
			System.out.println(field);
			Object value = field.get(obj);
			System.out.println("value"+value);
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
