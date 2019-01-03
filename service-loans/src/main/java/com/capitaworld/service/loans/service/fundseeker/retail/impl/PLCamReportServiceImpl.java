package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.eligibility.model.EligibililityRequest;
import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.api.eligibility.model.PersonalEligibilityRequest;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.api.ConnectStage;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.model.FinancialArrangementDetailResponseString;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.retail.BankAccountHeldDetailsRequest;
import com.capitaworld.service.loans.model.retail.FixedDepositsDetailsRequest;
import com.capitaworld.service.loans.model.retail.ObligationDetailRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailRequest;
import com.capitaworld.service.loans.model.retail.PLRetailApplicantRequest;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantIncomeRequest;
import com.capitaworld.service.loans.model.retail.RetailFinalInfoRequest;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.sanction.LoanDisbursementRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.common.PincodeDateService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.impl.CamReportPdfDetailsServiceImpl;
import com.capitaworld.service.loans.service.fundseeker.retail.BankAccountHeldDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.FixedDepositsDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.ObligationDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.OtherCurrentAssetDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.PLCamReportService;
import com.capitaworld.service.loans.service.fundseeker.retail.PlRetailApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.ReferenceRetailDetailsService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantIncomeService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.CastCategory;
import com.capitaworld.service.oneform.enums.DisabilityType;
import com.capitaworld.service.oneform.enums.EducationStatusRetailMst;
import com.capitaworld.service.oneform.enums.EmploymentStatusRetailMst;
import com.capitaworld.service.oneform.enums.EmploymentWithPL;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.MaritalStatusMst;
import com.capitaworld.service.oneform.enums.OccupationNatureNTB;
import com.capitaworld.service.oneform.enums.PersonalLoanPurpose;
import com.capitaworld.service.oneform.enums.ReligionRetailMst;
import com.capitaworld.service.oneform.enums.ResidenceStatusRetailMst;
import com.capitaworld.service.oneform.enums.ResidentialStatus;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.oneform.model.SectorIndustryModel;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.model.ProposalScoreDetailResponse;
import com.capitaworld.service.scoring.model.ProposalScoreResponse;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.scoring.utils.ScoreParameter.Retail;

@Service
@Transactional
public class PLCamReportServiceImpl implements PLCamReportService{

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private PlRetailApplicantService plRetailApplicantService;
	
	@Autowired
	private OneFormClient oneFormClient;
	
	@Autowired 
	private PincodeDateService pincodeDateService;
	
	@Autowired
	private ConnectClient connectClient; 
	
	@Autowired
	private MatchEngineClient matchEngineClient;
	
	@Autowired
	private ProposalDetailsClient proposalDetailsClient;
	
	@Autowired 
	private RetailApplicantIncomeService retailApplicantIncomeService;
	
	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;
	
	@Autowired
	private ScoringClient scoringClient;
	
	@Autowired
	private LoanDisbursementRepository loanDisbursementRepository;
	
	@Autowired
	private LoanSanctionRepository loanSanctionRepository;
	
	@Autowired
	private ProposalDetailsRepository proposalDetailsRepository;
	
	@Autowired
	private EligibilityClient eligibilityClient;
	
	@Autowired
	private BankAccountHeldDetailService bankAccountHeldDetailsService;
	
	@Autowired
	private FixedDepositsDetailService fixedDepositsDetailService;
	
	@Autowired
	private OtherCurrentAssetDetailService otherCurrentAssetDetailsService;
	
	@Autowired
	private ObligationDetailService obligationDetailService;
	
	@Autowired
	private ReferenceRetailDetailsService referenceRetailDetailService;
	
	private static final Logger logger = LoggerFactory.getLogger(CamReportPdfDetailsServiceImpl.class);
	
	@Override
	public Map<String, Object> getCamReportDetails(Long applicationId, Long productId, boolean isFinalView) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Long userId = loanApplicationRepository.getUserIdByApplicationId(applicationId);
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
		map.put("dateOfProposal", !CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getCreatedDate())? CommonUtils.DATE_FORMAT.format(loanApplicationMaster.getCreatedDate()):"-");
		try {
			PLRetailApplicantRequest plRetailApplicantRequest = plRetailApplicantService.getProfile(userId, applicationId);
			map.put("salutation", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getTitleId()) ? StringEscapeUtils.escapeXml(Title.getById(plRetailApplicantRequest.getTitleId()).getValue()):"");
			if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getContactAddress())) {
				map.put("registeredAddPremise", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getContactAddress().getPremiseNumber()) ? CommonUtils.printFields(plRetailApplicantRequest.getContactAddress().getPremiseNumber(),null) + "," : "");
				map.put("registeredAddStreetName", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getContactAddress().getStreetName()) ? CommonUtils.printFields(plRetailApplicantRequest.getContactAddress().getStreetName(),null) + "," : "");
				map.put("registeredAddLandmark", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getContactAddress().getLandMark()) ? CommonUtils.printFields(plRetailApplicantRequest.getContactAddress().getLandMark(),null) + "," : "");
				map.put("registeredAddCountry", StringEscapeUtils.escapeXml(getCountryName(plRetailApplicantRequest.getContactAddress().getCountryId())));
				map.put("registeredAddState", StringEscapeUtils.escapeXml(getStateName(plRetailApplicantRequest.getContactAddress().getStateId())));
				map.put("registeredAddCity", StringEscapeUtils.escapeXml(getCityName(plRetailApplicantRequest.getContactAddress().getCityId())));
				map.put("registeredAddPincode", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getContactAddress().getPincode())?plRetailApplicantRequest.getContactAddress().getPincode() : "");
				try {
					if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getContactAddress().getDistrictMappingId())) {
						map.put("registeredAddressData",CommonUtils.printFields(pincodeDateService.getById(plRetailApplicantRequest.getContactAddress().getDistrictMappingId()),null));				
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			map.put("gender", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getGenderId()) ? Gender.getById(plRetailApplicantRequest.getGenderId()).getValue(): "");
			map.put("birthDate",!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getBirthDate())? CommonUtils.DATE_FORMAT.format(plRetailApplicantRequest.getBirthDate()):"-");
			map.put("employmentType", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getEmploymentType()) ? OccupationNatureNTB.getById(plRetailApplicantRequest.getEmploymentType()).getValue() : "");
			map.put("employmentWith", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getEmploymentWith()) ? EmploymentWithPL.getById(plRetailApplicantRequest.getEmploymentWith()).getValue() : "");
			map.put("employmentStatus", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getEmploymentStatus()) ? EmploymentStatusRetailMst.getById(plRetailApplicantRequest.getEmploymentStatus()).getValue() : "");
			map.put("retailApplicantProfile", CommonUtils.printFields(plRetailApplicantRequest, null));
			map.put("educationQualification", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getEducationQualification()) ? EducationStatusRetailMst.getById(plRetailApplicantRequest.getEducationQualification()).getValue() : "");
			map.put("maritalStatus", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getStatusId()) ? MaritalStatusMst.getById(plRetailApplicantRequest.getStatusId()).getValue() : "");
			map.put("residenceType", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getResidenceType()) ? ResidenceStatusRetailMst.getById(plRetailApplicantRequest.getResidenceType()).getValue() : "");
			//KEY VERTICAL FUNDING
			List<Long> keyVerticalFundingId = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getKeyVerticalFunding()))
				keyVerticalFundingId.add(plRetailApplicantRequest.getKeyVerticalFunding());
			if (!CommonUtils.isListNullOrEmpty(keyVerticalFundingId)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getIndustryById(keyVerticalFundingId);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						map.put("keyVerticalFunding", StringEscapeUtils.escapeXml(masterResponse.getValue()));
					} else {
						map.put("keyVerticalFunding", "-");
					}

				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			//KEY VERTICAL SECTOR
			List<Long> keyVerticalSectorId = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getKeyVerticalSector()))
				keyVerticalSectorId.add(plRetailApplicantRequest.getKeyVerticalSector());
			try {
				OneFormResponse formResponse = oneFormClient.getIndustrySecByMappingId(plRetailApplicantRequest.getKeyVerticalSector());
				SectorIndustryModel sectorIndustryModel = MultipleJSONObjectHelper.getObjectFromMap((Map) formResponse.getData(), SectorIndustryModel.class);
				OneFormResponse oneFormResponse = oneFormClient.getSectorById(Arrays.asList(sectorIndustryModel.getSectorId()));
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					map.put("keyVerticalSector", StringEscapeUtils.escapeXml(masterResponse.getValue()));
				} else {
					map.put("keyVerticalSector", "-");
				}
			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			//KEY VERTICAL SUBSECTOR
			try {
				if (!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getKeyVerticalSubSector())) {
					OneFormResponse oneFormResponse = oneFormClient.getSubSecNameByMappingId(plRetailApplicantRequest.getKeyVerticalSubSector());
					map.put("keyVerticalSubSector",StringEscapeUtils.escapeXml(oneFormResponse.getData().toString()));
				}
			} catch (Exception e) {
				logger.error("error while getting key vertical sub-sector : ",e);
			}
			
		} catch (Exception e) {
			logger.error("Error while getting profile Details : ",e);
		}
		//DATE OF IN-PRINCIPLE APPROVAL (CONNECT CLIENT) EXISTING CODE
		/*try {
			ConnectResponse connectResponse = connectClient.getByAppStageBusinessTypeId(applicationId, ConnectStage.COMPLETE.getId(), com.capitaworld.service.loans.utils.CommonUtils.BusinessType.EXISTING_BUSINESS.getId());
			if(!CommonUtils.isObjectNullOrEmpty(connectResponse)) {
				map.put("dateOfInPrincipalApproval",!CommonUtils.isObjectNullOrEmpty(connectResponse.getData())? CommonUtils.DATE_FORMAT.format(connectResponse.getData()):"-");
			}
		} catch (Exception e2) {
			logger.info("Error while getting date of in-principal approval from connect client : ",e2);
		}*/
		
		//  CHANGES FOR DATE OF PROPOSAL IN CAM REPORTS (NEW CODE)
		try {
			Date InPrincipleDate = loanApplicationRepository.getModifiedDate(applicationId, ConnectStage.RETAIL_COMPLETE.getId(), com.capitaworld.service.loans.utils.CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId());
			if(!CommonUtils.isObjectNullOrEmpty(InPrincipleDate)) {
				map.put("dateOfInPrincipalApproval",!CommonUtils.isObjectNullOrEmpty(InPrincipleDate)? CommonUtils.DATE_FORMAT.format(InPrincipleDate):"-");
			}
		} catch (Exception e2) {
			logger.error(CommonUtils.EXCEPTION,e2);
		}
		// ENDS HERE===================>
		
		//MATCHES RESPONSE
		try {
			MatchRequest matchRequest = new MatchRequest();
			matchRequest.setApplicationId(applicationId);
			matchRequest.setProductId(productId);
			matchRequest.setBusinessTypeId(loanApplicationMaster.getBusinessTypeId());
			MatchDisplayResponse matchResponse= matchEngineClient.displayMatchesOfRetail(matchRequest);
			logger.info("matchesResponse"+matchResponse);
			map.put("matchesResponse", !CommonUtils.isListNullOrEmpty(matchResponse.getMatchDisplayObjectList()) ? CommonUtils.printFields(matchResponse.getMatchDisplayObjectList(),null) : " ");
		}
		catch (Exception e) {
			logger.error("Error while getting matches data : ",e);
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
			logger.error(CommonUtils.EXCEPTION,e);
		}
		//PRIMARY DATA (LOAN DETAILS)
		try {
			PLRetailApplicantRequest plRetailApplicantRequest = plRetailApplicantService.getPrimary(userId, applicationId);
			map.put("loanPurpose", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getLoanPurpose()) ? PersonalLoanPurpose.getById(plRetailApplicantRequest.getLoanPurpose()).getValue(): "");
			map.put("retailApplicantPrimaryDetails", plRetailApplicantRequest);
		} catch (Exception e) {
			logger.error("Error while getting primary Details : ",e);
		}
		
		//INCOME DETAILS - NET INCOME
		try {
			List<RetailApplicantIncomeRequest> retailApplicantIncomeDetail = retailApplicantIncomeService.getAll(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(retailApplicantIncomeDetail)) {
				map.put("incomeDetails", retailApplicantIncomeDetail);
			}
		} catch (Exception e) {
			logger.error("Error while getting income details : ",e);
		}
		
		//FINANCIAL ARRANGEMENTS
		try {
            List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService.getFinancialArrangementDetailsList(applicationId, userId);
            List<FinancialArrangementDetailResponseString> financialArrangementsDetailResponseList = new ArrayList<>();
            for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
            	FinancialArrangementDetailResponseString financialArrangementsDetailResponse = new FinancialArrangementDetailResponseString();
                financialArrangementsDetailResponse.setOutstandingAmount(CommonUtils.convertValue(financialArrangementsDetailRequest.getOutstandingAmount()));
                financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
                financialArrangementsDetailResponse.setAmount(CommonUtils.convertValue(financialArrangementsDetailRequest.getAmount()));
                financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
                financialArrangementsDetailResponse.setLoanType(financialArrangementsDetailRequest.getLoanType());
                financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
                financialArrangementsDetailResponse.setEmi(CommonUtils.convertValue(financialArrangementsDetailRequest.getEmi()));
                //financialArrangementsDetailResponse.setLcbgStatus(!CommonUtils.isObjectNullOrEmpty(financialArrangementsDetailRequest.getLcBgStatus()) ? LCBG_Status_SBI.getById(financialArrangementsDetailRequest.getLcBgStatus()).getValue().toString() : "-");
                financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
            }
            	map.put("financialArrangments",!CommonUtils.isListNullOrEmpty(financialArrangementsDetailResponseList) ? CommonUtils.printFields(financialArrangementsDetailResponseList,null) : " ");
        } catch (Exception e) {
            logger.error("Problem to get Data of Financial Arrangements Details {}", e);
        }	
		//SCORING
		try {
			ScoringRequest scoringRequest = new ScoringRequest();
			scoringRequest.setApplicationId(applicationId);
			scoringRequest.setFpProductId(productId);
			ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
			DecimalFormat df = new DecimalFormat(".##");
			List<Map<String,Object>> scoreResponse = new ArrayList<>(scoringResponse.getDataList().size());
			Map<String,Object> companyMap =new HashMap<>();
			ProposalScoreResponse proposalScoreResponse =  (ProposalScoreResponse)MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)scoringResponse.getDataObject(),ProposalScoreResponse.class);
			companyMap.put("scoringDataObject",CommonUtils.printFields(proposalScoreResponse,null));
			if(!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse)) {
				map.put("managementRiskScore",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getManagementRiskScore()) ? proposalScoreResponse.getManagementRiskScore().intValue(): "-");
				map.put("managementRiskMaxTotalScore",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getManagementRiskMaxTotalScore()) ?  proposalScoreResponse.getManagementRiskMaxTotalScore().intValue():"-");
				map.put("managementRiskWeightOfScoring",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getManagementRiskWeightOfScoring()) ? proposalScoreResponse.getManagementRiskWeightOfScoring().intValue() :"-");
				map.put("managementRiskWeight", !CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getManagementRiskWeight()) ? df.format((proposalScoreResponse.getManagementRiskWeight())): "-");
				map.put("managementRiskMaxTotalWeight", !CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getManagementRiskMaxTotalWeight()) ? proposalScoreResponse.getManagementRiskMaxTotalWeight().intValue(): "-");
				
				map.put("financialRiskScore",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getFinancialRiskScore()) ? proposalScoreResponse.getFinancialRiskScore().intValue() : "-");
				map.put("financialRiskMaxTotalScore",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getFinancialRiskMaxTotalScore()) ? proposalScoreResponse.getFinancialRiskMaxTotalScore().intValue():"-");
				map.put("financialRiskWeightOfScoring",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getFinancialRiskWeightOfScoring()) ? proposalScoreResponse.getFinancialRiskWeightOfScoring().intValue(): "-");
				map.put("financialRiskWeight",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getFinancialRiskWeight()) ? df.format((proposalScoreResponse.getFinancialRiskWeight())) : "-");
				map.put("financialRiskMaxTotalWeight",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getFinancialRiskMaxTotalWeight()) ? proposalScoreResponse.getFinancialRiskMaxTotalWeight().intValue() : "-");
				
				map.put("businessRiskScore", !CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getBusinessRiskScore()) ? proposalScoreResponse.getBusinessRiskScore().intValue():"-");
				map.put("businessRiskMaxTotalScore",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getBusinessRiskMaxTotalScore()) ? proposalScoreResponse.getBusinessRiskMaxTotalScore().intValue():"-");
				map.put("businessRiskWeightOfScoring", !CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getBusinessRiskWeightOfScoring()) ? proposalScoreResponse.getBusinessRiskWeightOfScoring().intValue():"-");
				map.put("businessRiskWeight", !CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getBusinessRiskWeight()) ? df.format((proposalScoreResponse.getBusinessRiskWeight())):"-");
				map.put("businessRiskMaxTotalWeight", !CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getBusinessRiskMaxTotalWeight()) ? proposalScoreResponse.getBusinessRiskMaxTotalWeight().intValue():"-");
				
				map.put("totalActualScore", CommonUtils.addNumbers(proposalScoreResponse.getManagementRiskScore(), proposalScoreResponse.getFinancialRiskScore(), proposalScoreResponse.getBusinessRiskScore()).intValue());
				map.put("totalOutOfScore", CommonUtils.addNumbers(proposalScoreResponse.getManagementRiskMaxTotalScore(), proposalScoreResponse.getFinancialRiskMaxTotalScore(), proposalScoreResponse.getBusinessRiskMaxTotalScore()).intValue());
				map.put("totalWeight", CommonUtils.addNumbers(proposalScoreResponse.getManagementRiskWeightOfScoring(), proposalScoreResponse.getFinancialRiskWeightOfScoring(), proposalScoreResponse.getBusinessRiskWeightOfScoring()).intValue());
				map.put("totalRiskWeight", df.format(CommonUtils.addNumbers(proposalScoreResponse.getManagementRiskWeight(), proposalScoreResponse.getFinancialRiskWeight(), proposalScoreResponse.getBusinessRiskWeight())));
				map.put("totalRiskMaxWeight", CommonUtils.addNumbers(proposalScoreResponse.getManagementRiskMaxTotalWeight(), proposalScoreResponse.getFinancialRiskMaxTotalWeight(), proposalScoreResponse.getBusinessRiskMaxTotalWeight()).intValue());
				
				map.put("interpretation", StringEscapeUtils.escapeXml(proposalScoreResponse.getInterpretation()));
			}
			//Filter Parameters
			List<LinkedHashMap<String, Object>> mapList = (List<LinkedHashMap<String, Object>>)scoringResponse.getDataList();
			List<ProposalScoreDetailResponse> newMapList = new ArrayList<>(mapList.size());
			for(LinkedHashMap<String, Object> mp : mapList) {
				newMapList.add(MultipleJSONObjectHelper.getObjectFromMap(mp,ProposalScoreDetailResponse.class));
			}
			List<ProposalScoreDetailResponse> collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.WORKING_EXPERIENCE_PL)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.WORKING_EXPERIENCE_PL, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.CIBIL_SCORE_PL)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.CIBIL_SCORE_PL, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.AGE_PL)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.AGE_PL, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.EDUCATION_QUALI_PL)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.EDUCATION_QUALI_PL, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.EMPLOYEMENT_TYPE_PL)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.EMPLOYEMENT_TYPE_PL, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HOUSE_OWNERSHIP_PL)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HOUSE_OWNERSHIP_PL, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.MARITAL_STATUS_PL)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.MARITAL_STATUS_PL, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.CATEGORY_INFO_PL)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.CATEGORY_INFO_PL, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.FIXED_OBLI_INFO_RATIO_PL)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.FIXED_OBLI_INFO_RATIO_PL, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.CHEQUE_BOUNCE_PAST_SIX_MONTH_PL)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.CHEQUE_BOUNCE_PAST_SIX_MONTH_PL, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.DAY_PAST_DUE_PL)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.DAY_PAST_DUE_PL, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.NET_ANNUAL_INCOME_PL)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.NET_ANNUAL_INCOME_PL, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.EMI_NMI_PL)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.EMI_NMI_PL, CommonUtils.printFields(collect.get(0),null));
					}
					scoreResponse.add(companyMap);
					map.put("scoringResp", scoreResponse);
			}catch (Exception e) {
				logger.error("Error while getting scoring data : ",e);
			}
		
			//ELIGIBILITY DATA (ASSESSMENT TO LIMITS)
			try{
				EligibililityRequest eligibilityReq=new EligibililityRequest();
				eligibilityReq.setApplicationId(applicationId);
				eligibilityReq.setFpProductMappingId(productId);
				EligibilityResponse eligibilityResp= eligibilityClient.getRetailLoanData(eligibilityReq);
				if(!CommonUtils.isObjectListNull(eligibilityResp,eligibilityResp.getData())){
				map.put("assLimits",CommonUtils.convertToDoubleForXml(MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)eligibilityResp.getData(), PersonalEligibilityRequest.class), new HashMap<>()));
				}
			}catch (Exception e) {
				logger.error("Error while getting Eligibility data : ",e);
			}
		/*************************************************************FINAL DETAILS***********************************************************/
		
		if(isFinalView) {
			//PROPOSAL DATES
			try {
				List<Object[]> data = null;
				List<Date[]> data1 = null;
				data1 = loanDisbursementRepository.findDisbursementDateByApplicationId(applicationId);
				if(data1 != null && !data1.isEmpty()) {
					map.put("disbursmentDate", CommonUtils.DATE_FORMAT.format(data1.get(0)));
				}
				data1 = loanSanctionRepository.findSanctionDateByApplicationId(applicationId);
				if(data1 != null && !data1.isEmpty()) {
					map.put("sanctionDate", CommonUtils.DATE_FORMAT.format(data1.get(0)));
				}
				data = proposalDetailsRepository.findProposalDetailByApplicationId(applicationId);
				if(data != null && !data.isEmpty()) {
					String status = data.get(0) != null ? data.get(0)[1].toString() : "";
					if(status.equals("3")) {
						map.put("onHoldDate", data.get(0)[0]);
					}else if(status.equals("4")) {
						map.put("rejectedDate", data.get(0)[0]);
					}
				}
				} catch (Exception e) {
				logger.error("Error while getting PROPOSAL DATES data : ",e);
			}
			//RETAIL FINAL DETAILS
			try {
				RetailFinalInfoRequest retailFinalInfo = plRetailApplicantService.getFinal(userId, applicationId);
				if(!CommonUtils.isObjectNullOrEmpty(retailFinalInfo)) {
					map.put("religion", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getReligion()) ? ReligionRetailMst.getById(retailFinalInfo.getReligion()).getValue() : "");
					map.put("residentialStatus", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getResidentialStatus()) ? ResidentialStatus.getById(retailFinalInfo.getResidentialStatus()).getValue() : "");
					map.put("castCategory", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getCastId()) ? CastCategory.getById(retailFinalInfo.getCastId()).getValue() : "");
					map.put("diasablityType", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getDisabilityType()) ? DisabilityType.getById(retailFinalInfo.getDisabilityType()) : "");
					map.put("ddoOrganizationType", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getDdoOrganizationType()) ? EmploymentWithPL.getById(retailFinalInfo.getDdoOrganizationType()) : "");
					map.put("retailFinalDetails", retailFinalInfo);
					map.put("permanantAddCountry", StringEscapeUtils.escapeXml(getCountryName(retailFinalInfo.getPermanentAddress().getCountryId())));
					map.put("permanantAddState", StringEscapeUtils.escapeXml(getStateName(retailFinalInfo.getPermanentAddress().getStateId())));
					map.put("permanantAddCity", StringEscapeUtils.escapeXml(getCityName(retailFinalInfo.getPermanentAddress().getCityId())));
					map.put("permanantAddPincode", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getPermanentAddress().getPincode())?retailFinalInfo.getPermanentAddress().getPincode() : "");
					try {
						if(!CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getPermanentAddress().getDistrictMappingId())) {
							map.put("permanantAddressData",CommonUtils.printFields(pincodeDateService.getById(retailFinalInfo.getPermanentAddress().getDistrictMappingId()),null));				
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
					map.put("officeAddCountry", StringEscapeUtils.escapeXml(getCountryName(retailFinalInfo.getOfficeAddress().getCountryId())));
					map.put("officeAddState", StringEscapeUtils.escapeXml(getStateName(retailFinalInfo.getOfficeAddress().getStateId())));
					map.put("officeAddCity", StringEscapeUtils.escapeXml(getCityName(retailFinalInfo.getOfficeAddress().getCityId())));
					map.put("officeAddPincode", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getOfficeAddress().getPincode())?retailFinalInfo.getPermanentAddress().getPincode() : "");
					try {
						if(!CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getOfficeAddress().getDistrictMappingId())) {
							map.put("officeAddressData",CommonUtils.printFields(pincodeDateService.getById(retailFinalInfo.getOfficeAddress().getDistrictMappingId()),null));				
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}
			} catch (Exception e) {
				logger.error("Error while getting Final Information : ",e);
			}
			
			//INCOME DETAILS - GROSS INCOME
			try {
				List<RetailApplicantIncomeRequest> retailApplicantIncomeDetail = retailApplicantIncomeService.getAll(applicationId);
				if(!CommonUtils.isObjectNullOrEmpty(retailApplicantIncomeDetail)) {
					map.put("grossIncomeDetails", retailApplicantIncomeDetail);
				}
			} catch (Exception e) {
				logger.error("Error while getting income details : ",e);
			}
			
			//BANK ACCOUNT HELD DETAILS
			try {
				List<BankAccountHeldDetailsRequest> bankAccountHeldDetails = bankAccountHeldDetailsService.getExistingLoanDetailList(applicationId, 1);
				if(!CommonUtils.isObjectNullOrEmpty(bankAccountHeldDetails)) {
					map.put("bankAccountHeld", bankAccountHeldDetails);
				}
			} catch (Exception e) {
				logger.error("Error while getting bank account held details : ",e);
			}
			
			//FIXED DEPOSITS DETAILS
			try {
				List<FixedDepositsDetailsRequest> fixedDepositeDetails = fixedDepositsDetailService.getFixedDepositsDetailList(applicationId, 1);
				if(!CommonUtils.isObjectNullOrEmpty(fixedDepositeDetails)) {
					map.put("fixedDepositeDetails", fixedDepositeDetails);
				}
			} catch (Exception e) {
				logger.error("Error while getting fixed deposite details : ",e);
			}
			
			//OTHER CURRENT ASSEST DETAILS
			try {
				List<OtherCurrentAssetDetailRequest> otherCurrentAssetDetails = otherCurrentAssetDetailsService.getOtherCurrentAssetDetailList(applicationId,1);
				if(!CommonUtils.isObjectNullOrEmpty(otherCurrentAssetDetails)) {
					map.put("otherCurrentAssetDetails", otherCurrentAssetDetails);
				}
			} catch (Exception e) {
				logger.error("Error while getting other current asset details : ",e);
			}
			
			//OBLIGATION DETAILS
			try {
				List<ObligationDetailRequest> obligationRequest = obligationDetailService.getObligationDetailList(applicationId,1);
				if(!CommonUtils.isObjectNullOrEmpty(obligationRequest)) {
					map.put("obligationDetails", obligationRequest);
				}
			} catch (Exception e) {
				logger.error("Error while getting obligation details : ",e);
			}
			
			//REFERENCES DETAILS
			try {
				List<ReferenceRetailDetailsRequest> referenceDetails = referenceRetailDetailService.getReferenceRetailDetailList(applicationId,1);
				if(!CommonUtils.isObjectNullOrEmpty(referenceDetails)) {
					map.put("referenceDetails", referenceDetails);
				}
			} catch (Exception e) {
				logger.error("Error while getting reference details : ",e);
			}
			
			
		}
		
		return map;
	}
	
			/*********************************************************CAM UTILS****************************************************************/
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

}
