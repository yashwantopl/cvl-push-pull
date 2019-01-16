package com.capitaworld.service.loans.service.fundseeker.corporate.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.api.eligibility.model.CLEligibilityRequest;
import com.capitaworld.api.eligibility.model.EligibililityRequest;
import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.fraudanalytics.client.FraudAnalyticsClient;
import com.capitaworld.service.fraudanalytics.model.AnalyticsResponse;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.model.FinanceMeansDetailRequest;
import com.capitaworld.service.loans.model.FinanceMeansDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.TotalCostOfProjectResponse;
import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.model.corporate.TotalCostOfProjectRequest;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AchievmentDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssociatedConcernDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateDirectorIncomeService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExistingProductDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinanceMeansDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.GuarantorsCorporateDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.MonthlyTurnoverDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.NTBService;
import com.capitaworld.service.loans.service.fundseeker.corporate.NtbCamReportService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProposedProductDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.SecurityCorporateDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.TotalCostOfProjectService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.FinanceCategory;
import com.capitaworld.service.oneform.enums.Particular;
import com.capitaworld.service.oneform.enums.ProposedConstitutionOfUnitNTB;
import com.capitaworld.service.oneform.enums.ProposedDetailOfUnitNTB;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.oneform.model.SectorIndustryModel;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.model.ProposalScoreDetailResponse;
import com.capitaworld.service.scoring.model.ProposalScoreResponse;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.scoring.utils.ScoreParameter.NTB;
import com.capitaworld.service.thirdparty.model.CGTMSEDataResponse;
import com.capitaworld.service.thirdpaty.client.ThirdPartyClient;


@Service
@Transactional
public class NtbCamReportServiceImpl implements NtbCamReportService{

	@Autowired
    private CorporateDirectorIncomeService corporateDirectorIncomeService;
	
	@Autowired
    private NTBService ntbService;
	
	@Autowired
	private EligibilityClient eligibilityClient;
	
	@Autowired
	private MatchEngineClient matchEngineClient;
	
	@Autowired
	private AnalyzerClient analyzerClient;
	
	@Autowired
	private TermLoanParameterRepository termLoanParameterRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private ScoringClient scoringClient;
	
	@Autowired
	private OneFormClient oneFormClient;
	
	@Autowired 
	private DirectorBackgroundDetailsRepository dirBackgroundDetailsRepository;
	
	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;
	
	@Autowired
	private ThirdPartyClient thirdPartyClient; 
	
	@Autowired
	private FraudAnalyticsClient fraudAnalyticsClient;
	
	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateRepository;
	
	@Autowired
	private GuarantorsCorporateDetailService guarantorsCorporateDetailService;

	@Autowired
	private MonthlyTurnoverDetailService monthlyTurnoverDetailService;
	
	@Autowired
	private TotalCostOfProjectService costOfProjectService; 
	
	@Autowired
	private FinanceMeansDetailsService financeMeansDetailsService;
	
	@Autowired
	private AssociatedConcernDetailService associatedConcernDetailService;
	
	@Autowired
	private ProposedProductDetailsService proposedProductDetailsService; 
	
	@Autowired
	private ExistingProductDetailsService existingProductDetailsService;
	
	@Autowired
	private AchievmentDetailsService achievmentDetailsService;
	
	@Autowired
	private SecurityCorporateDetailsService securityCorporateDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(NtbCamReportServiceImpl.class);
	
	@Override
	public Map<String, Object> getNtbCamReport(Long applicationId, Long productId, Long userId, boolean isFinalView) {
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
		logger.info("Entering into NTB CAM REPORT service=========> {}" +applicationId+" "+productId+" "+isFinalView + loanApplicationMaster.getBusinessTypeId());
		Map<String, Object> map = new HashMap<String, Object>();
		PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository.findOneByApplicationIdId(applicationId);
		if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getModifiedDate())) {
			map.put("dateOfProposal",!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getModifiedDate()) ? CommonUtils.DATE_FORMAT.format(primaryCorporateDetail.getModifiedDate()) : null);
		}
		//MATCHES RESPONSE
		try {
			MatchRequest matchRequest = new MatchRequest();
			matchRequest.setApplicationId(applicationId);
			matchRequest.setProductId(productId);
			matchRequest.setBusinessTypeId(loanApplicationMaster.getBusinessTypeId());
			MatchDisplayResponse matchResponse= matchEngineClient.displayMatchesOfCorporate(matchRequest);
			map.put("matchesResponse", !CommonUtils.isListNullOrEmpty(matchResponse.getMatchDisplayObjectList()) ? CommonUtils.printFields(matchResponse.getMatchDisplayObjectList(),null) : " ");
		}catch (Exception e) {
			logger.error("Error while getting Match Engine data : ",e);
		}
		
		// GET DIRECTOR BACKGROUND DETAILS
		try {
			List<Map<String, Object>> directorBackgroundDetails = corporateDirectorIncomeService.getDirectorBackGroundDetails(applicationId);
			if (directorBackgroundDetails != null && !directorBackgroundDetails.isEmpty()) {
				map.put("directorBackgroundDetails", directorBackgroundDetails);
			}
			if (directorBackgroundDetails.size() == 1) {
				map.put("isMultipleDirector", false);
			} else {
				map.put("isMultipleDirector", true);
			}
			int size = directorBackgroundDetails.size() * 2;
			List<Integer> columnWidth = new ArrayList<>(size);
			for(int i=0; i < size; i++) {
				columnWidth.add(i);
			}
			map.put("columnWidth", columnWidth);
			
		} catch (Exception e) {
			logger.error("Problem to get Data of Director's Background=========> {}", e);
		}
		
		//GET OTHER DETAILS
		try {

			FundSeekerInputRequestResponse fundSeekerInputRequestResponse = ntbService.getOthersDetail(applicationId);
			if (!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getProposedConstitutionOfUnit())) {
				map.put("proposedConsitutionOfUnit",!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getProposedConstitutionOfUnit()) ? ProposedConstitutionOfUnitNTB.getById(fundSeekerInputRequestResponse.getProposedDetailsOfUnit()).getValue().toString() : "-");
			}
			if (!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getProposedDetailsOfUnit())) {
				map.put("proposedDetailsOfUnit",!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getProposedDetailsOfUnit()) ? ProposedDetailOfUnitNTB.getById(fundSeekerInputRequestResponse.getProposedDetailsOfUnit()).getValue().toString() : "-");
			}
			//KEY VERTICAL FUNDING
			List<Long> keyVerticalFundingId = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getKeyVericalFunding()))
				keyVerticalFundingId.add(fundSeekerInputRequestResponse.getKeyVericalFunding());
			if (!CommonUtils.isListNullOrEmpty(keyVerticalFundingId)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getIndustryById(keyVerticalFundingId);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						map.put("keyVerticalFunding", masterResponse.getValue());
					} else {
						map.put("keyVerticalFunding", "-");
					}

				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			//KEY VERTICAL SECTOR
			List<Long> keyVerticalSectorId = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getKeyVerticalSector()))
				keyVerticalSectorId.add(fundSeekerInputRequestResponse.getKeyVerticalSector());
			try {
				OneFormResponse formResponse = oneFormClient.getIndustrySecByMappingId(fundSeekerInputRequestResponse.getKeyVerticalSector());
				SectorIndustryModel sectorIndustryModel = MultipleJSONObjectHelper.getObjectFromMap((Map) formResponse.getData(), SectorIndustryModel.class);
				OneFormResponse oneFormResponse = oneFormClient.getSectorById(Arrays.asList(sectorIndustryModel.getSectorId()));
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					map.put("keyVerticalSector", masterResponse.getValue());
				} else {
					map.put("keyVerticalSector", "-");
				}
			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			//KEY VERTICAL SUBSECTOR
			try {
				if (!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getKeyVerticalSubsector())) {
					OneFormResponse oneFormResponse = oneFormClient.getSubSecNameByMappingId(fundSeekerInputRequestResponse.getKeyVerticalSubsector());
					map.put("keyVerticalSubSector",(String) oneFormResponse.getData());
				}
			} catch (Exception e) {
				logger.error("error while getting key vertical sub-sector : ",e);
			}

			map.put("otherDetails",fundSeekerInputRequestResponse);
			map.put("proposedOperationDate",CommonUtils.DATE_FORMAT.format(fundSeekerInputRequestResponse.getProposedOperationDate()));

		} catch (Exception e) {
			logger.error("Problem to get Other Details=========> {}", e);
		}
			
		// GET DIRECTOR INCOME DETAILS
		try {
			List<CorporateDirectorIncomeRequest> directorIncomeDetails = corporateDirectorIncomeService.getDirectorIncomeDetails(applicationId);
			Map<String, List<CorporateDirectorIncomeRequest>> dirIncomeDetail = null;
			if (directorIncomeDetails != null && !directorIncomeDetails.isEmpty()) {
				dirIncomeDetail = directorIncomeDetails.stream().collect(Collectors.groupingBy(CorporateDirectorIncomeRequest::getDirectorName));
			}
			if(dirIncomeDetail != null && !dirIncomeDetail.isEmpty()) {
				map.put("directorIncomeDetails", dirIncomeDetail);
			}
		} catch (Exception e) {
			logger.error("Problem to get Director's Income Details===========> {}", e);
		}
		
		//GET EXISTING FINANCIAL DETAILS 
				List<Long> dirIdList = dirBackgroundDetailsRepository.getDirectorIdFromApplicationId(applicationId);
				List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList = new ArrayList<>(dirIdList.size());
				for(Long dirId : dirIdList) {
				try {
					List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService.getFinancialArrangementDetailsListDirId(dirId,applicationId);
					String directorName = dirBackgroundDetailsRepository.getDirectorNamefromDirectorId(dirId);
					FinancialArrangementsDetailResponse financialArrangementsDetailResponse = null;
					for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
						financialArrangementsDetailResponse = new FinancialArrangementsDetailResponse();
						// financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
						financialArrangementsDetailResponse.setOutstandingAmount(financialArrangementsDetailRequest.getOutstandingAmount());
						financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
						financialArrangementsDetailResponse.setAmount(financialArrangementsDetailRequest.getAmount());
						// financialArrangementsDetailResponse.setLenderType(LenderType.getById(financialArrangementsDetailRequest.getLenderType()).getValue());
						financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
						financialArrangementsDetailResponse.setLoanType(financialArrangementsDetailRequest.getLoanType());
						financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
						// financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
						// financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
						financialArrangementsDetailResponse.setDirectorName(directorName);
						financialArrangementsDetailResponse.setAmountStr(CommonUtils.convertValue(financialArrangementsDetailRequest.getAmount()));
						financialArrangementsDetailResponse.setOutstandingAmountStr(CommonUtils.convertValue(financialArrangementsDetailRequest.getOutstandingAmount()));
						financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
					}

				}catch (Exception e) {
					logger.error("Problem to get Data of Financial Arrangements Details {}", e);
				}
			}
			Map<String, List<FinancialArrangementsDetailResponse>> financialArrangementDetails = financialArrangementsDetailResponseList.stream().collect(Collectors.groupingBy(FinancialArrangementsDetailResponse:: getDirectorName));
			if(!CommonUtils.isObjectNullOrEmpty(financialArrangementDetails)) {
				map.put("existingFinancialArrangement",financialArrangementDetails);
			}
		
		//ELIGIBILITY DATA (ASSESSMENT TO LIMITS)
		EligibililityRequest eligibilityReq = new EligibililityRequest();
		eligibilityReq.setApplicationId(applicationId);
		eligibilityReq.setFpProductMappingId(productId);
		try {
			EligibilityResponse eligibilityResp = eligibilityClient.corporateLoanData(eligibilityReq);
			if(!CommonUtils.isObjectNullOrEmpty(eligibilityResp)) {
				map.put("assLimits",CommonUtils.convertToDoubleForXml(MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)eligibilityResp.getData(), CLEligibilityRequest.class), new HashMap<>()));
				CLEligibilityRequest clRequestList = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)eligibilityResp.getData(), CLEligibilityRequest.class);
				Map<Long, List<CLEligibilityRequest>> dirEl = new HashMap<>();
				for(CLEligibilityRequest clEligibilityRequest : clRequestList.getDirectorsCalculations()) {
					dirEl = clRequestList.getDirectorsCalculations().stream().collect(Collectors.groupingBy(CLEligibilityRequest:: getDirectorId));
				}
				map.put("dirEligibility", CommonUtils.printFields(dirEl, new HashMap<>()));
			}
		} catch (Exception e1) {
			logger.error("Error while getting Eligibility data : ",e1);
		}
		
		//SCORING DATA
		try {
			ScoringRequest scoringRequest = new ScoringRequest();
			scoringRequest.setApplicationId(applicationId);
			scoringRequest.setFpProductId(productId);
			ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
			if(!CommonUtils.isListNullOrEmpty(scoringResponse.getScoringResponseList())) {
				List<Map<String,Object>> scoreResponse = new ArrayList<>(scoringResponse.getScoringResponseList().size());
				Map<String,Object> map2 = null;
				for(ScoringResponse o : scoringResponse.getScoringResponseList()) {
					map2 = new HashMap<>();
					ProposalScoreResponse proposalScoreResponse =  (ProposalScoreResponse)MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)o.getDataObject(),ProposalScoreResponse.class);
					map2.put("dataObject", CommonUtils.printFields(proposalScoreResponse,null));
					//Filter Parameters
					List<LinkedHashMap<String, Object>> mapList = (List<LinkedHashMap<String, Object>>)o.getDataList();
					List<ProposalScoreDetailResponse> newMapList = new ArrayList<>(mapList.size());
					for(LinkedHashMap<String, Object> mp : mapList) {
						newMapList.add(MultipleJSONObjectHelper.getObjectFromMap(mp,ProposalScoreDetailResponse.class));
					}
					List<ProposalScoreDetailResponse> collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.WORKING_EXPERIENCE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						map2.put(NTB.WORKING_EXPERIENCE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						map2.put(NTB.IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.AGE_OF_PROMOTOR)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						map2.put(NTB.AGE_OF_PROMOTOR, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.EDUCATION_QUALIFICATION)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						map2.put(NTB.EDUCATION_QUALIFICATION, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.EMPLOYMENT_TYPE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						map2.put(NTB.EMPLOYMENT_TYPE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.HOUSE_OWNERSHIP)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						map2.put(NTB.HOUSE_OWNERSHIP, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.MARITIAL_STATUS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						map2.put(NTB.MARITIAL_STATUS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.ITR_SALARY_INCOME)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						map2.put(NTB.ITR_SALARY_INCOME, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.FIXED_OBLIGATION_RATIO)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						map2.put(NTB.FIXED_OBLIGATION_RATIO, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.CHEQUE_BOUNCES)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						map2.put(NTB.CHEQUE_BOUNCES, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.CIBIL_TRANSUNION_SCORE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						map2.put(NTB.CIBIL_TRANSUNION_SCORE, CommonUtils.printFields(collect.get(0),null));
					}
					scoreResponse.add(map2);
					}
					map.put("directorScoringData", scoreResponse);
				}
					
					List<Map<String,Object>> scoreResponse = new ArrayList<>(scoringResponse.getDataList().size());
					Map<String,Object> companyMap =new HashMap<>();
					ProposalScoreResponse proposalScoreResponse =  (ProposalScoreResponse)MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)scoringResponse.getDataObject(),ProposalScoreResponse.class);
					companyMap.put("companyDataObject",CommonUtils.printFields(proposalScoreResponse,null));
					map.put("totalWeight",CommonUtils.convertValue(CommonUtils.addNumbers(proposalScoreResponse.getBusinessRiskWeight(), proposalScoreResponse.getFinancialRiskWeight(), proposalScoreResponse.getManagementRiskWeight())));
					map.put("totalWeightActualScore",CommonUtils.convertValue(CommonUtils.addNumbers(proposalScoreResponse.getManagementRiskWeightOfScoring(), proposalScoreResponse.getFinancialRiskWeightOfScoring(), proposalScoreResponse.getBusinessRiskWeightOfScoring())));
					map.put("totalWeightOutOfScore",CommonUtils.convertValue(CommonUtils.addNumbers(proposalScoreResponse.getManagementRiskMaxTotalWeight(), proposalScoreResponse.getFinancialRiskMaxTotalWeight(), proposalScoreResponse.getBusinessRiskMaxTotalWeight())));
					//Filter Parameters
					List<LinkedHashMap<String, Object>> mapList = (List<LinkedHashMap<String, Object>>)scoringResponse.getDataList();
					List<ProposalScoreDetailResponse> newMapList = new ArrayList<>(mapList.size());
					for(LinkedHashMap<String, Object> mp : mapList) {
						newMapList.add(MultipleJSONObjectHelper.getObjectFromMap(mp,ProposalScoreDetailResponse.class));
					}
					List<ProposalScoreDetailResponse> collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.WORKING_EXPERIENCE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.WORKING_EXPERIENCE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.AGE_OF_PROMOTOR)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.AGE_OF_PROMOTOR, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.EDUCATION_QUALIFICATION)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.EDUCATION_QUALIFICATION, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.EMPLOYMENT_TYPE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.EMPLOYMENT_TYPE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.HOUSE_OWNERSHIP)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.HOUSE_OWNERSHIP, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.MARITIAL_STATUS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.MARITIAL_STATUS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.ITR_SALARY_INCOME)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.ITR_SALARY_INCOME, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.FIXED_OBLIGATION_RATIO)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.FIXED_OBLIGATION_RATIO, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.CHEQUE_BOUNCES)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.CHEQUE_BOUNCES, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.CIBIL_TRANSUNION_SCORE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.CIBIL_TRANSUNION_SCORE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.CNW)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.CNW, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.CONSTITUTION_OF_BORROWER)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.CONSTITUTION_OF_BORROWER, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.DPD)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.DPD, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.ASSET_COVERAGE_RATIO)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.ASSET_COVERAGE_RATIO, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.UNIT_FACTORY_PREMISES)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.UNIT_FACTORY_PREMISES, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.BALANCE_GESTATION_PERIOD)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.BALANCE_GESTATION_PERIOD, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(NTB.ENVIRONMENT_CATEGORY)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(NTB.ENVIRONMENT_CATEGORY, CommonUtils.printFields(collect.get(0),null));
					}
					scoreResponse.add(companyMap);
					map.put("companyScoreResponse", scoreResponse);
			
		}catch (Exception e) {
			logger.error("Error while getting scoring data : ",e);
		}
		
		//CGTMSE 
		try {
			CGTMSEDataResponse cgtmseDataResponse = thirdPartyClient.getCalulation(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse)) {
				map.put("cgtmseData", CommonUtils.printFields(cgtmseDataResponse,null));
				map.put("maxCgtmseCoverageAmount", !CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getMaxCgtmseCoverageAmount()) ? CommonUtils.convertValueWithoutDecimal(cgtmseDataResponse.getMaxCgtmseCoverageAmount()): "-");
				map.put("identityAmount",  !CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getIdentityAmount()) ? CommonUtils.convertValueWithoutDecimal(cgtmseDataResponse.getIdentityAmount()):"-");
				map.put("gutAmt",!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getGutAmt()) ? CommonUtils.convertValueWithoutDecimal(cgtmseDataResponse.getGutAmt()):"-");
				map.put("cgtmseLoanAmount",!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getLoanAmount()) ? CommonUtils.convertValueWithoutDecimal(cgtmseDataResponse.getLoanAmount()):"-");
				map.put("cgtmseCoverageAmount",!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getCgtmseCoverageAmount()) ? CommonUtils.convertValueWithoutDecimal(cgtmseDataResponse.getCgtmseCoverageAmount()):"-");
				map.put("amountOfColleteral",!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getAmountOfColleteral()) ? CommonUtils.convertValueWithoutDecimal(cgtmseDataResponse.getAmountOfColleteral()):"-");
				map.put("totalColleteralAmount",!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getTotalColleteralAmount()) ? CommonUtils.convertValueWithoutDecimal(cgtmseDataResponse.getTotalColleteralAmount()):"-");
				map.put("gurAmtCarld",!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getGurAmtCarld()) ? CommonUtils.convertValueWithoutDecimal(cgtmseDataResponse.getGurAmtCarld()):"-");
				map.put("colleteralCoverage",!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getColleteralCoverage()) ? CommonUtils.convertValue(cgtmseDataResponse.getColleteralCoverage()):"-");
				map.put("percTerms",!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getPercTerms()) ? CommonUtils.convertValue(cgtmseDataResponse.getPercTerms()):"-");
				map.put("assetAqu",!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getAssetAqusition()) ? CommonUtils.convertValueWithoutDecimal(cgtmseDataResponse.getAssetAqusition()):"-");
				if(!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getCgtmseResponse()) && !CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getCgtmseResponse().getDetails())) {
					map.put("cgtmseBankWise", CommonUtils.printFields(cgtmseDataResponse.getCgtmseResponse().getDetails(),null));
				}
			}
		} catch (Exception e) {
			logger.error("Error while getting CGTMSE data : ",e);
		}
		
		//HUNTER API ANALYSIS
				try {
					AnalyticsResponse hunterResp =fraudAnalyticsClient.getRuleAnalysisData(applicationId);
					if(!CommonUtils.isObjectListNull(hunterResp,hunterResp.getData())) {
						map.put("hunterResponse",  CommonUtils.printFields(hunterResp.getData(),null));
					}
				} catch (Exception e1) {
					logger.error(CommonUtils.EXCEPTION,e1);
		}
		
		/**********************************************FINAL DETAILS*****************************************************/
				if(isFinalView) {
					//ASSOCIATE ENTITY
					try {
						map.put("associatedConcerns",CommonUtils.printFields(associatedConcernDetailService.getAssociatedConcernsDetailList(applicationId, userId),null));
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
					
					//DETAILS OF GUARANTER
					try {
							map.put("guaDetails", CommonUtils.printFields(guarantorsCorporateDetailService.getGuarantorsCorporateDetailList(applicationId, userId),null));
					} catch (Exception e) {
							logger.error("Problem to get Data of Details of Guarantor {}", e);
					}
				    //MONTHLY TURNOVER
					try {
						map.put("monthlyTurnOver", CommonUtils.printFields(monthlyTurnoverDetailService.getMonthlyTurnoverDetailList(applicationId, userId),null));
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
						map.put("costEstimate", CommonUtils.printFields(costOfProjectResponses,null));
					} catch (Exception e1) {
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
						map.put("meansOfFinance", CommonUtils.printFields(financeMeansDetailResponsesList,null));
					} catch (Exception e1) {
						logger.error("Problem to get Data of Finance Means Details {}", e1);
					}
					
					//COLLATERAL SECURITY			
					try {
						map.put("collateralSecurity", CommonUtils.printFields(securityCorporateDetailsService.getsecurityCorporateDetailsList(applicationId, userId),null));
					} catch (Exception e) {
						logger.error("Problem to get Data of Security Details {}", e);
					}
					
				}
		return map;
	}
	
	

}
