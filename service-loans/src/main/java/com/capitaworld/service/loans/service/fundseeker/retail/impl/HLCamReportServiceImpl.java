package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.capitaworld.service.oneform.enums.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.eligibility.model.EligibililityRequest;
import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.api.eligibility.model.RetailEligibilityRequest;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.connect.api.ConnectStage;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.FinancialArrangementDetailResponseString;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.retail.BankAccountHeldDetailsRequest;
import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import com.capitaworld.service.loans.model.retail.FixedDepositsDetailsRequest;
import com.capitaworld.service.loans.model.retail.HLOneformPrimaryRes;
import com.capitaworld.service.loans.model.retail.ObligationDetailRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailRequest;
import com.capitaworld.service.loans.model.retail.PLRetailApplicantRequest;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantIncomeRequest;
import com.capitaworld.service.loans.model.retail.RetailFinalInfoRequest;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.sanction.LoanDisbursementRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.common.PincodeDateService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.impl.CamReportPdfDetailsServiceImpl;
import com.capitaworld.service.loans.service.fundseeker.retail.BankAccountHeldDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.FixedDepositsDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.HLCamReportService;
import com.capitaworld.service.loans.service.fundseeker.retail.ObligationDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.OtherCurrentAssetDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.PlRetailApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryHomeLoanService;
import com.capitaworld.service.loans.service.fundseeker.retail.ReferenceRetailDetailsService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantIncomeService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingRequestString;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.oneform.model.SectorIndustryModel;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.model.ProposalScoreDetailResponse;
import com.capitaworld.service.scoring.model.ProposalScoreResponse;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.scoring.utils.ScoreParameter.Retail;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class HLCamReportServiceImpl implements HLCamReportService{

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private PlRetailApplicantService plRetailApplicantService;
	
	@Autowired
	private OneFormClient oneFormClient;
	
	@Autowired
	private ApplicationProposalMappingRepository applicationMappingRepository;
	
	@Autowired 
	private PincodeDateService pincodeDateService;
	
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
	private AnalyzerClient analyzerClient;

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
	
	@Autowired
	private CoApplicantService coApplicantService;
	
	@Autowired
	private PrimaryHomeLoanService primaryHomeLoanService;
	
	@Autowired
	private ProductMasterRepository productMasterRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CamReportPdfDetailsServiceImpl.class);
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public Map<String, Object> getCamReportDetailsByProposalId(Long applicationId, Long productId, Long proposalId, boolean isFinalView) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Long userId = loanApplicationRepository.getUserIdByApplicationId(applicationId);
		ApplicationProposalMapping applicationProposalMapping = applicationMappingRepository.getByApplicationIdAndProposalId(applicationId, proposalId);
     	LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
     	
     	if(loanApplicationMaster != null) {
     		map.put("applicationType", (loanApplicationMaster.getWcRenewalStatus() != null ? WcRenewalType.getById(loanApplicationMaster.getWcRenewalStatus()).getValue() : "New" ));
     		map.put("dateOfProposal", !CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getCreatedDate())? simpleDateFormat.format(loanApplicationMaster.getCreatedDate()):"-");
     	}
     	if(applicationProposalMapping != null) {
     		map.put("applicationCode", applicationProposalMapping.getApplicationCode() != null ? applicationProposalMapping.getApplicationCode() : "-");
     		map.put("loanType", !CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getProductId()) ? CommonUtils.LoanType.getType(applicationProposalMapping.getProductId()).getName() : " ");
     	}
     	
		try {
			PLRetailApplicantRequest plRetailApplicantRequest = plRetailApplicantService.getPrimaryByProposalId(userId, applicationId, proposalId);
			map.put("salutation", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getTitleId()) ? StringEscapeUtils.escapeXml(Title.getById(plRetailApplicantRequest.getTitleId()).getValue()):"");
			if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest)) {
				map.put("registeredAddPremise", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getAddressPremiseName()) ? CommonUtils.printFields(plRetailApplicantRequest.getAddressPremiseName(),null) + "," : "");
				map.put("registeredAddStreetName", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getAddressStreetName()) ? CommonUtils.printFields(plRetailApplicantRequest.getAddressStreetName(),null) + "," : "");
				map.put("registeredAddLandmark", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getAddressLandmark()) ? CommonUtils.printFields(plRetailApplicantRequest.getAddressLandmark(),null) + "," : "");
				map.put("registeredAddCountry", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getAddressCountry()) ? StringEscapeUtils.escapeXml(getCountryName(plRetailApplicantRequest.getAddressCountry().intValue())) : "");
				map.put("registeredAddState", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getAddressState()) ? StringEscapeUtils.escapeXml(getStateName(plRetailApplicantRequest.getAddressState().intValue())) : "");
				map.put("registeredAddCity", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getAddressCity()) ? StringEscapeUtils.escapeXml(getCityName(plRetailApplicantRequest.getAddressCity())) : "");
				map.put("registeredAddPincode", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getAddressPincode())?plRetailApplicantRequest.getAddressPincode() : "");
				try {
					if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getAddressDistrictMappingId())) {
						map.put("registeredAddressData",CommonUtils.printFields(pincodeDateService.getById(plRetailApplicantRequest.getAddressDistrictMappingId()),null));				
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			
			if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getOfficeAddress())) {
				map.put("officeAddPremise", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getOfficeAddress().getPremiseNumber()) ? CommonUtils.printFields(plRetailApplicantRequest.getOfficeAddress().getPremiseNumber(),null) + "," : "");
				map.put("officeAddStreetName", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getOfficeAddress().getStreetName()) ? CommonUtils.printFields(plRetailApplicantRequest.getOfficeAddress().getStreetName(),null) + "," : "");
				map.put("officeAddLandmark", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getOfficeAddress().getLandMark()) ? CommonUtils.printFields(plRetailApplicantRequest.getOfficeAddress().getLandMark(),null) + "," : "");
				map.put("officeAddCountry", StringEscapeUtils.escapeXml(getCountryName(plRetailApplicantRequest.getOfficeAddress().getCountryId())));
				map.put("officeAddState", StringEscapeUtils.escapeXml(getStateName(plRetailApplicantRequest.getOfficeAddress().getStateId())));
				map.put("officeAddCity", StringEscapeUtils.escapeXml(getCityName(plRetailApplicantRequest.getOfficeAddress().getCityId())));
				map.put("officeAddPincode", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getOfficeAddress().getPincode())?plRetailApplicantRequest.getOfficeAddress().getPincode() : "");
				try {
					if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getOfficeAddress().getDistrictMappingId())) {
						map.put("officeAddressData",CommonUtils.printFields(pincodeDateService.getById(plRetailApplicantRequest.getOfficeAddress().getDistrictMappingId()),null));				
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			
			LocalDate today = LocalDate.now();
			if(plRetailApplicantRequest.getBirthDate() != null ) {
				LocalDate birthday = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(plRetailApplicantRequest.getBirthDate()));
				map.put("ageOfApplicant",(today.getYear() - birthday.getYear()) + " years");
			}
			
			if(plRetailApplicantRequest.getSalaryBankYear() != null && plRetailApplicantRequest.getSalaryBankMonth() != null) {
				LocalDate since = LocalDate.of(plRetailApplicantRequest.getSalaryBankYear(), plRetailApplicantRequest.getSalaryBankMonth(), 1);
				LocalDate now = LocalDate.now();
				Period sinceWhen = Period.between(since, now);
				int years = sinceWhen.getYears();
				int months = sinceWhen.getMonths();
				plRetailApplicantRequest.setSalaryBankYear(years);
				plRetailApplicantRequest.setSalaryBankMonth(months);
			}
			
			if(plRetailApplicantRequest.getResidenceSinceYear() != null && plRetailApplicantRequest.getResidenceSinceMonth() != null) {
				LocalDate since = LocalDate.of(plRetailApplicantRequest.getResidenceSinceYear(), plRetailApplicantRequest.getResidenceSinceMonth(), 1);
				LocalDate now = LocalDate.now();
				Period sinceWhen = Period.between(since, now);
				int years = sinceWhen.getYears();
				plRetailApplicantRequest.setResidenceSinceYear(years);
			}
			
			map.put("gender", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getGenderId()) ? Gender.getById(plRetailApplicantRequest.getGenderId()).getValue(): "-");
			map.put("birthDate",!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getBirthDate())? simpleDateFormat.format(plRetailApplicantRequest.getBirthDate()):"-");
			
			/*employment type*/
			if(plRetailApplicantRequest.getEmploymentType()!= null && plRetailApplicantRequest.getEmploymentType() == 2) {
				map.put("employmentWith" , plRetailApplicantRequest.getEmploymentWith() != null ? EmploymentWithPL.getById(plRetailApplicantRequest.getEmploymentWith()).getValue() : "-");
			}else if (plRetailApplicantRequest.getEmploymentType()!= null && plRetailApplicantRequest.getEmploymentType() == 5) {
				map.put("employmentWith" , plRetailApplicantRequest.getEmploymentWith() != null ? OccupationHL.getById(plRetailApplicantRequest.getEmploymentWith()).getValue() : "-");
			}else if (plRetailApplicantRequest.getEmploymentType()!= null && plRetailApplicantRequest.getEmploymentType() == 4) {
				map.put("employmentWith" , plRetailApplicantRequest.getEmploymentWith() != null ? EmploymentWithRetail.getById(plRetailApplicantRequest.getEmploymentWith()).getValue() : "-");
			}
			
			map.put("employmentType", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getEmploymentType()) ? EmploymentWithPL.getById(plRetailApplicantRequest.getEmploymentType()).getValue() : "-");
			//map.put("employmentWith", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getEmploymentWith()) ? OccupationHL.getById(plRetailApplicantRequest.getEmploymentWith()).getValue() : "-");
			map.put("employmentStatus", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getEmploymentStatus()) ? EmploymentCategory.getById(plRetailApplicantRequest.getEmploymentStatus()).getValue() : "-");
			map.put("sinceSalaryWhen", (!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getSalaryBankYear()) ? plRetailApplicantRequest.getSalaryBankYear() + " years" : "")+" "+(plRetailApplicantRequest.getSalaryBankMonth() != null ? plRetailApplicantRequest.getSalaryBankMonth() +" months" : ""));
			map.put("retailApplicantProfile", CommonUtils.printFields(plRetailApplicantRequest, null));
			map.put("educationQualification", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getEducationQualification()) ? EducationStatusRetailMst.getById(plRetailApplicantRequest.getEducationQualification()).getValue() : "-");
			map.put("maritalStatus", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getStatusId()) ? MaritalStatusMst.getById(plRetailApplicantRequest.getStatusId()).getValue() : "-");
			map.put("residenceType", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getResidenceType()) ? ResidenceTypeHomeLoan.getById(plRetailApplicantRequest.getResidenceType()).getValue() : "-");
			map.put("spouseEmployment", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getSpouseEmployment()) ? SpouseEmploymentList.getById(plRetailApplicantRequest.getSpouseEmployment()).getValue() : "-");
			map.put("designation", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getDesignation())? DesignationList.getById(plRetailApplicantRequest.getDesignation()).getValue() : "-");
			map.put("noOfDependent", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getNoOfDependent()) ? plRetailApplicantRequest.getNoOfDependent() : "-");
			map.put("nationality", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getResidentialStatus()) ? ResidentStatusMst.getById(plRetailApplicantRequest.getResidentialStatus()).getValue() : "-");
			map.put("annualIncomeOfSpouse", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getAnnualIncomeOfSpouse()) ? CommonUtils.convertValueWithoutDecimal(plRetailApplicantRequest.getAnnualIncomeOfSpouse()) : "-");
			map.put("applicantNetWorth", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getNetworth()) ? CommonUtils.convertValueWithoutDecimal(plRetailApplicantRequest.getNetworth()) : "-");
			map.put("grossMonthlyIncome", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getGrossMonthlyIncome()) ? CommonUtils.convertValueWithoutDecimal(plRetailApplicantRequest.getGrossMonthlyIncome()) : null);
			map.put("netMonthlyIncome", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getMonthlyIncome()) ? CommonUtils.convertValueWithoutDecimal(plRetailApplicantRequest.getMonthlyIncome()) : null);
			map.put("residenceSinceYearMonths", (!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getResidenceSinceYear()) ? plRetailApplicantRequest.getResidenceSinceYear() + " years" : "")+ " " +(plRetailApplicantRequest.getResidenceSinceMonth() != null ? plRetailApplicantRequest.getResidenceSinceMonth()+" months":""));
			map.put("eligibleLoanAmount", !CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getLoanAmount()) ? CommonUtils.convertValueWithoutDecimal(applicationProposalMapping.getLoanAmount()): "-");
			map.put("eligibleTenure", !CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getTenure()) ? applicationProposalMapping.getTenure().longValue():"-");
			map.put("operatingBusinessSince", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getBusinessStartDate()) ? simpleDateFormat.format(plRetailApplicantRequest.getBusinessStartDate()) :"-");
			map.put("applicantCategory", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getCategory()) ? CastCategory.getById(plRetailApplicantRequest.getCategory()).getValue() : "-");
			map.put("experienceInPresentJob", (!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getCurrentJobYear()) ? plRetailApplicantRequest.getCurrentJobYear() + " years" :"")+" "+(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getCurrentJobMonth()) ? plRetailApplicantRequest.getCurrentJobMonth() +" months" : ""));
			map.put("totalExperience", (!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getTotalExperienceYear()) ? plRetailApplicantRequest.getTotalExperienceYear() + " years" :"")+" "+(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getTotalExperienceMonth()) ? plRetailApplicantRequest.getTotalExperienceMonth() +" months" : ""));
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
		
		// Product Name
		if(productId != null) {
			String productName = productMasterRepository.getFpProductName(productId);
			if(productName != null) {
				map.put("fpProductName", productName);
			}else {
				logger.info("product name is null..of productId==>{}", productId);
			}
		}else {
			logger.info("fpProductMapping id is null..");
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
			Date inPrincipleDate = loanApplicationRepository.getModifiedDate(applicationId, ConnectStage.RETAIL_COMPLETE.getId());
			if(!CommonUtils.isObjectNullOrEmpty(inPrincipleDate)) {
				map.put("dateOfInPrincipalApproval",!CommonUtils.isObjectNullOrEmpty(inPrincipleDate)? simpleDateFormat.format(inPrincipleDate):"-");
			}
		} catch (Exception e2) {
			logger.error(CommonUtils.EXCEPTION,e2);
		}
		// ENDS HERE===================>

		try {
			//Fetching CoApplicantDetails
			List<CoApplicantDetail> coApplicantDetails = coApplicantService.getCoApplicantList(applicationId);
			List<Map<String , Object>> listMap = new ArrayList<Map<String,Object>>();
			for(CoApplicantDetail coApplicantDetail : coApplicantDetails) {
				CoApplicantRequest coApplicantRequest = new CoApplicantRequest();
				Map<String, Object> coApp=new HashMap<>();
				coApp.put("salutation", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTitleId()) ? StringEscapeUtils.escapeXml(Title.getById(coApplicantDetail.getTitleId()).getValue()):"");
				copyAddressFromDomainToRequestOfCoApplicant(coApplicantDetail, coApplicantRequest);
				if(!CommonUtils.isObjectNullOrEmpty(coApplicantRequest.getFirstAddress())) {
					coApp.put("registeredAddPremise", !CommonUtils.isObjectNullOrEmpty(coApplicantRequest.getFirstAddress().getPremiseNumber()) ? CommonUtils.printFields(coApplicantRequest.getFirstAddress().getPremiseNumber(),null) + "," : "");
					coApp.put("registeredAddStreetName", !CommonUtils.isObjectNullOrEmpty(coApplicantRequest.getFirstAddress().getStreetName()) ? CommonUtils.printFields(coApplicantRequest.getFirstAddress().getStreetName(),null) + "," : "");
					coApp.put("registeredAddLandmark", !CommonUtils.isObjectNullOrEmpty(coApplicantRequest.getFirstAddress().getLandMark()) ? CommonUtils.printFields(coApplicantRequest.getFirstAddress().getLandMark(),null) + "," : "");
					coApp.put("registeredAddCountry", StringEscapeUtils.escapeXml(getCountryName(coApplicantRequest.getFirstAddress().getCountryId())));
					coApp.put("registeredAddState", StringEscapeUtils.escapeXml(getStateName(coApplicantRequest.getFirstAddress().getStateId())));
					coApp.put("registeredAddCity", StringEscapeUtils.escapeXml(getCityName(coApplicantRequest.getFirstAddress().getCityId())));
					coApp.put("registeredAddPincode", !CommonUtils.isObjectNullOrEmpty(coApplicantRequest.getFirstAddress().getPincode())?coApplicantRequest.getFirstAddress().getPincode() : "");
					try {
						if(!CommonUtils.isObjectNullOrEmpty(coApplicantRequest.getFirstAddress().getDistrictMappingId())) {
							coApp.put("registeredAddressData",CommonUtils.printFields(pincodeDateService.getById(coApplicantRequest.getFirstAddress().getDistrictMappingId()),null));				
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}
				
				if(!CommonUtils.isObjectNullOrEmpty(coApplicantRequest.getSecondAddress())) {
					coApp.put("officeAddPremise", !CommonUtils.isObjectNullOrEmpty(coApplicantRequest.getSecondAddress().getPremiseNumber()) ? CommonUtils.printFields(coApplicantRequest.getSecondAddress().getPremiseNumber(),null) + "," : "");
					coApp.put("officeAddStreetName", !CommonUtils.isObjectNullOrEmpty(coApplicantRequest.getSecondAddress().getStreetName()) ? CommonUtils.printFields(coApplicantRequest.getSecondAddress().getStreetName(),null) + "," : "");
					coApp.put("officeAddLandmark", !CommonUtils.isObjectNullOrEmpty(coApplicantRequest.getSecondAddress().getLandMark()) ? CommonUtils.printFields(coApplicantRequest.getSecondAddress().getLandMark(),null) + "," : "");
					coApp.put("officeAddCountry", StringEscapeUtils.escapeXml(getCountryName(coApplicantRequest.getSecondAddress().getCountryId())));
					coApp.put("officeAddState", StringEscapeUtils.escapeXml(getStateName(coApplicantRequest.getSecondAddress().getStateId())));
					coApp.put("officeAddCity", StringEscapeUtils.escapeXml(getCityName(coApplicantRequest.getSecondAddress().getCityId())));
					coApp.put("officeAddPincode", !CommonUtils.isObjectNullOrEmpty(coApplicantRequest.getSecondAddress().getPincode())?coApplicantRequest.getSecondAddress().getPincode() : "");
					try {
						if(!CommonUtils.isObjectNullOrEmpty(coApplicantRequest.getSecondAddress().getDistrictMappingId())) {
							coApp.put("officeAddressData",CommonUtils.printFields(pincodeDateService.getById(coApplicantRequest.getSecondAddress().getDistrictMappingId()),null));				
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}
				
				BeanUtils.copyProperties(coApplicantDetail, coApplicantRequest);
				LocalDate today = LocalDate.now();
				if(coApplicantDetail.getBirthDate() != null ) {
					LocalDate birthday = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(coApplicantDetail.getBirthDate()));
					coApp.put("ageOfApplicant",(today.getYear() - birthday.getYear()) + " years");
				}
				
				if(coApplicantDetail.getResidenceSinceYear() != null && coApplicantDetail.getResidenceSinceMonth() != null) {
					LocalDate since = LocalDate.of(coApplicantDetail.getResidenceSinceYear(), coApplicantDetail.getResidenceSinceMonth(), 1);
					LocalDate now = LocalDate.now();
					Period sinceWhen = Period.between(since, now);
					Integer years = sinceWhen.getYears();
					Integer months = sinceWhen.getMonths();
					
					coApp.put("residenceSinceYearMonths", (years!= null ?  years+ " years" : "")+ " " +(months != null ? months+" months":""));
				}else {
					coApp.put("residenceSinceYearMonths", coApplicantDetail.getResidenceSinceMonth() != null ? coApplicantDetail.getResidenceSinceMonth()+" months":"");
				}

				coApp.put("gender", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getGenderId()) ? Gender.getById(coApplicantDetail.getGenderId()).getValue(): "-");
				coApp.put("birthDate",!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthDate())? simpleDateFormat.format(coApplicantDetail.getBirthDate()):"-");
				coApp.put("employmentType", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getEmploymentType()) ? EmploymentWithPL.getById(coApplicantDetail.getEmploymentType()).getValue() : "-");
				
				/*employment type*/
				if(coApplicantDetail.getEmploymentType()!= null && coApplicantDetail.getEmploymentType() == 2) {
					coApp.put("employmentWith" , coApplicantDetail.getEmployedWithId() != null ? EmploymentWithPL.getById(coApplicantDetail.getEmployedWithId()).getValue() : "-");
				}else if (coApplicantDetail.getEmploymentType()!= null && coApplicantDetail.getEmploymentType() == 5) {
					coApp.put("employmentWith" , coApplicantDetail.getEmployedWithId() != null ? OccupationHL.getById(coApplicantDetail.getEmployedWithId()).getValue() : "-");
				}else if (coApplicantDetail.getEmploymentType()!= null && coApplicantDetail.getEmploymentType() == 4) {
					coApp.put("employmentWith" , coApplicantDetail.getEmployedWithId() != null ? EmploymentWithRetail.getById(coApplicantDetail.getEmployedWithId()).getValue() : "-");
				}
				
				//coApp.put("employmentWith", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getEmployedWithId()) ? OccupationHL.getById(coApplicantDetail.getEmployedWithId()).getValue() : "-");
				coApp.put("employmentStatus", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getEmploymentStatus()) ? EmploymentCategory.getById(coApplicantDetail.getEmploymentStatus()).getValue() : "-");
				coApp.put("relationshipWithApp", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getRelationshipWithApplicant()) ? RelationshipTypeHL.getById(coApplicantDetail.getRelationshipWithApplicant()).getValue() : "-");
				coApp.put("maritalStatus", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getStatusId()) ? MaritalStatusMst.getById(coApplicantDetail.getStatusId()).getValue() : "-");
				coApp.put("nameOfEmployer", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getNameOfEmployer()) ? coApplicantDetail.getNameOfEmployer() : "-");
				coApp.put("spouseEmployment", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getSpouseEmployment()) ? SpouseEmploymentList.getById(coApplicantDetail.getSpouseEmployment()).getValue() : "-");
				coApp.put("annualIncomeOfSpouse", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAnnualIncomeOfSpouse()) ? CommonUtils.convertValueWithoutDecimal(coApplicantDetail.getAnnualIncomeOfSpouse()) : "-");
				coApp.put("residenceType", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getResidenceType()) ? ResidenceTypeHomeLoan.getById(coApplicantDetail.getResidenceType()).getValue() : "-");
				coApp.put("noOfDependent", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getNoDependent()) ? coApplicantDetail.getNoDependent() : "-");
				coApp.put("designation", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getDesignation()) ? DesignationList.getById(coApplicantDetail.getDesignation()).getValue() : "-");
				coApp.put("educationQualification", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getEducationQualification()) ? EducationStatusRetailMst.getById(coApplicantDetail.getEducationQualification()).getValue() : "-");
				coApp.put("coApplicantNetWorth", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getNetworth()) ? CommonUtils.convertValueWithoutDecimal(coApplicantDetail.getNetworth()) : null);
				coApp.put("eligibleLoanAmount", !CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getLoanAmount()) ? CommonUtils.convertValueWithoutDecimal(applicationProposalMapping.getLoanAmount()): "-");
				coApp.put("eligibleTenure", !CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getTenure()) ? applicationProposalMapping.getTenure().longValue():"-");
				coApp.put("nationality", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getNationality()) ? ResidentStatusMst.getById(coApplicantDetail.getNationality()).getValue() : "-");
				coApp.put("grossMonthlyIncome", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getGrossMonthlyIncome()) ? CommonUtils.convertValueWithoutDecimal(coApplicantDetail.getGrossMonthlyIncome()) : null);
				coApp.put("netMonthlyIncome", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMonthlyIncome()) ? CommonUtils.convertValueWithoutDecimal(coApplicantDetail.getMonthlyIncome()) : null);
				coApp.put("operatingBusinessSince", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBusinessStartDate()) ? simpleDateFormat.format(coApplicantDetail.getBusinessStartDate()) : "-");
				coApp.put("coApplicantCategory", !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCategory()) ? CastCategory.getById(coApplicantDetail.getCategory()).getValue() : "-");
				coApp.put("experienceInPresentJob", (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentJobYear()) ? coApplicantDetail.getCurrentJobYear() + " years" :"")+" "+(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentJobMonth()) ? coApplicantDetail.getCurrentJobMonth() +" months" : ""));
				coApp.put("totalExperience", (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceYear()) ? coApplicantDetail.getTotalExperienceYear() + " years" :"")+" "+(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceMonth()) ? coApplicantDetail.getTotalExperienceMonth() +" months" : ""));
				coApp.put("retailCoApplicantProfile", CommonUtils.printFields(coApplicantRequest, null));
				listMap.add(coApp);		
			}
			map.put("retailCoApplicantDetails", CommonUtils.printFields(listMap, null));
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
		
		//MATCHES RESPONSE
		try {
			MatchRequest matchRequest = new MatchRequest();
			matchRequest.setApplicationId(applicationId);
			matchRequest.setProductId(productId);
			matchRequest.setBusinessTypeId(applicationProposalMapping.getBusinessTypeId());
			MatchDisplayResponse matchResponse= matchEngineClient.displayMatchesOfRetail(matchRequest);
			logger.info("matchesResponse"+matchResponse);
			map.put("matchesResponse", !CommonUtils.isListNullOrEmpty(matchResponse.getMatchDisplayObjectList()) ? CommonUtils.printFields(matchResponse.getMatchDisplayObjectList(),null) : " ");
		}
		catch (Exception e) {
			logger.error("Error while getting matches data : ",e);
		}
		//PROPOSAL RESPONSE
		try {
			ObjectMapper mapper = new ObjectMapper();
			ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
			proposalMappingRequest.setApplicationId(applicationId);
			proposalMappingRequest.setFpProductId(productId);
			ProposalMappingResponse proposalMappingResponse= proposalDetailsClient.getActiveProposalDetails(proposalMappingRequest);
			
			ProposalMappingRequestString proposalMappingRequestString = mapper.convertValue(proposalMappingResponse.getData(), ProposalMappingRequestString.class);
//			BeanUtils.copyProperties(proposalMappingResponse.getData(), proposalMappingRequestString);
			
			map.put("proposalDate", simpleDateFormat.format(proposalMappingRequestString.getModifiedDate()));
			map.put("proposalResponseEmi", !CommonUtils.isObjectNullOrEmpty(proposalMappingResponse.getData()) ? CommonUtils.convertValueWithoutDecimal((Double)((LinkedHashMap<String, Object>)proposalMappingResponse.getData()).get("emi")) : "");
			map.put("proposalResponse", !CommonUtils.isObjectNullOrEmpty(proposalMappingResponse.getData()) ? proposalMappingResponse.getData() : " ");
		}
		catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		//PRIMARY DATA (LOAN DETAILS)
		try {
			PLRetailApplicantRequest plRetailApplicantRequest = plRetailApplicantService.getPrimaryByProposalId(userId, applicationId, proposalId);
			map.put("loanPurpose", !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getLoanPurpose()) ? StringEscapeUtils.escapeXml(HomeLoanPurpose.getById(plRetailApplicantRequest.getLoanPurpose()).getValue()): "");
			map.put("retailApplicantPrimaryDetails", plRetailApplicantRequest);
		} catch (Exception e) {
			logger.error("Error while getting primary Details : ",e);
		}
		
		//Property Details
		try {
			Map<String ,Object> propertyDetails = new HashMap<String, Object>(); 
			HLOneformPrimaryRes response = primaryHomeLoanService.getOneformPrimaryDetails(applicationId);
			propertyDetails.put("propertyValue",response.getMarketValProp() != null ? CommonUtils.convertValueWithoutDecimal(response.getMarketValProp()) : "");
			propertyDetails.put("propertyAge", response.getOldPropYear() != null ? response.getOldPropYear() : "");
			propertyDetails.put("propertyPremise", !CommonUtils.isObjectNullOrEmpty(response.getPropPremiseName()) ? CommonUtils.printFields(response.getPropPremiseName(),null) + "," : "");
			propertyDetails.put("propertyStreetName", !CommonUtils.isObjectNullOrEmpty(response.getPropStreetName()) ? CommonUtils.printFields(response.getPropStreetName(),null) + "," : "");
			propertyDetails.put("propertyLandmark", !CommonUtils.isObjectNullOrEmpty(response.getPropLandmark()) ? CommonUtils.printFields(response.getPropLandmark(),null) + "," : "");
			propertyDetails.put("propertyCountry", !CommonUtils.isObjectNullOrEmpty(response.getPropCountry()) ? StringEscapeUtils.escapeXml(getCountryName(response.getPropCountry().intValue())) : "");
			propertyDetails.put("propertyState", !CommonUtils.isObjectNullOrEmpty(response.getPropState()) ? StringEscapeUtils.escapeXml(getStateName(response.getPropState().intValue())) : "");
			propertyDetails.put("propertyCity", !CommonUtils.isObjectNullOrEmpty(response.getPropCity()) ? StringEscapeUtils.escapeXml(getCityName(response.getPropCity())) : "");
			propertyDetails.put("propertyPincode", !CommonUtils.isObjectNullOrEmpty(response.getPropPincode()) ? response.getPropPincode() : "");
			try {
				if(!CommonUtils.isObjectNullOrEmpty(response.getPropdistrictMappingId())) {
					propertyDetails.put("propertyAddress",CommonUtils.printFields(pincodeDateService.getById(response.getPropdistrictMappingId()),null));				
				}
			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			map.put("propertyDetails", propertyDetails);
		} catch (Exception e) {
			logger.error("Error while getting property Details : ",e);
		}

		//INCOME DETAILS - NET INCOME
		try {
			List<RetailApplicantIncomeRequest> retailApplicantIncomeDetail = retailApplicantIncomeService.getAllByProposalId(applicationId, proposalId);
			
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
                financialArrangementsDetailResponse.setOutstandingAmount(CommonUtils.convertValueWithoutDecimal(financialArrangementsDetailRequest.getOutstandingAmount()));
                financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
                financialArrangementsDetailResponse.setAmount(CommonUtils.convertValueWithoutDecimal(financialArrangementsDetailRequest.getAmount()));
                financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
                financialArrangementsDetailResponse.setLoanType(financialArrangementsDetailRequest.getLoanType());
                financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
                financialArrangementsDetailResponse.setEmi(CommonUtils.convertValueWithoutDecimal(financialArrangementsDetailRequest.getEmi()));
                //financialArrangementsDetailResponse.setLcbgStatus(!CommonUtils.isObjectNullOrEmpty(financialArrangementsDetailRequest.getLcBgStatus()) ? LCBG_Status_SBI.getById(financialArrangementsDetailRequest.getLcBgStatus()).getValue().toString() : "-");
                financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
            }
            	map.put("financialArrangments",!CommonUtils.isListNullOrEmpty(financialArrangementsDetailResponseList) ? CommonUtils.printFields(financialArrangementsDetailResponseList,null) : " ");
        } catch (Exception e) {
            logger.error("Problem to get Data of Financial Arrangements Details {}", e);
        }	
		
		//Co-Applicant FINANCIAL ARRANGEMENTS
		try {	
			List<CoApplicantDetail> coApplicantDetails = coApplicantService.getCoApplicantList(applicationId);
			List<Map<String , Object>> listMap = new ArrayList<Map<String,Object>>();
			for(CoApplicantDetail coApplicantDetail : coApplicantDetails) {
				CoApplicantRequest coApplicantRequest = new CoApplicantRequest();
				copyAddressFromDomainToRequestOfCoApplicant(coApplicantDetail, coApplicantRequest);
				BeanUtils.copyProperties(coApplicantDetail, coApplicantRequest);
				Map<String, Object> map1 = new HashMap<String, Object>();
				List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService.getFinancialArrangementDetailsListDirId(coApplicantDetail.getId() , applicationId);	
				List<FinancialArrangementDetailResponseString> financialArrangementsDetailResponseList = new ArrayList<>();	
				for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
					FinancialArrangementDetailResponseString financialArrangementsDetailResponse = new FinancialArrangementDetailResponseString();
					financialArrangementsDetailResponse.setOutstandingAmount(CommonUtils.convertValueWithoutDecimal(financialArrangementsDetailRequest.getOutstandingAmount()));	
					financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());	
					financialArrangementsDetailResponse.setAmount(CommonUtils.convertValueWithoutDecimal(financialArrangementsDetailRequest.getAmount()));	
					financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());	
					financialArrangementsDetailResponse.setLoanType(financialArrangementsDetailRequest.getLoanType());	
					financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());	
					financialArrangementsDetailResponse.setEmi(CommonUtils.convertValueWithoutDecimal(financialArrangementsDetailRequest.getEmi()));	
					//financialArrangementsDetailResponse.setLcbgStatus(!CommonUtils.isObjectNullOrEmpty(financialArrangementsDetailRequest.getLcBgStatus()) ? LCBG_Status_SBI.getById(financialArrangementsDetailRequest.getLcBgStatus()).getValue().toString() : "-");	
					financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);	
				}
				map1.put("financialDetails", !CommonUtils.isListNullOrEmpty(financialArrangementsDetailResponseList) ? CommonUtils.printFields(financialArrangementsDetailResponseList,null) : " ");
				map1.put("coAppDetail", CommonUtils.printFields(coApplicantRequest, null));
				listMap.add(map1);		
			}
			map.put("financialArrangmentsofCoApplicant",!CommonUtils.isListNullOrEmpty(listMap) ? CommonUtils.printFields(listMap,null) : " ");
         } catch (Exception e) {	
            logger.error("Problem to get Data of Financial Arrangements Details {}", e);	
        }
		
		//SCORING for Applicant
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
				map.put("weightConsider", proposalScoreResponse.getWeightConsider() != null ? proposalScoreResponse.getWeightConsider() : false);
				map.put("isProposnate", proposalScoreResponse.getIsProportionateScoreConsider() != null ? proposalScoreResponse.getIsProportionateScoreConsider() : false);
				map.put("proposnateScoreFs", proposalScoreResponse.getProportionateScoreFS() != null ? proposalScoreResponse.getProportionateScoreFS() : false);
				map.put("proposnateScore", proposalScoreResponse.getProportionateScore() != null ? proposalScoreResponse.getProportionateScore() : false);
			}
			//Filter Parameters
			List<LinkedHashMap<String, Object>> mapList = (List<LinkedHashMap<String, Object>>)scoringResponse.getDataList();
			List<ProposalScoreDetailResponse> newMapList = new ArrayList<>(mapList.size());
			for(LinkedHashMap<String, Object> mp : mapList) {
				newMapList.add(MultipleJSONObjectHelper.getObjectFromMap(mp,ProposalScoreDetailResponse.class));
			}
			List<ProposalScoreDetailResponse> collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.AGE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.AGE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.CURRENT_JOB_EXP)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.CURRENT_JOB_EXP, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.TOTAL_WORK_EXP)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.TOTAL_WORK_EXP, CommonUtils.printFields(collect.get(0),null));
					}
//					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.TOTAL_BUSI_PROFE_EXP)).collect(Collectors.toList());
//					if(!CommonUtils.isListNullOrEmpty(collect)) {
//						companyMap.put(Retail.HomeLoan.TOTAL_BUSI_PROFE_EXP, CommonUtils.printFields(collect.get(0),null));
//					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.RESIDENCE_TYPE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.RESIDENCE_TYPE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.NO_YEARS_STAY_CURR_LOC)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.NO_YEARS_STAY_CURR_LOC, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.BUREAU_SCORE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.BUREAU_SCORE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.MARITAL_STATUS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.MARITAL_STATUS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.EMPLOYMENT_TYPE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.EMPLOYMENT_TYPE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.EMPLOYMENT_CATEG_JOB)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.EMPLOYMENT_CATEG_JOB, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.CURRENT_EMPLOYMENT_STATUS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.CURRENT_EMPLOYMENT_STATUS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.MIN_BANKING_RELATIONSHIP)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.MIN_BANKING_RELATIONSHIP, CommonUtils.printFields(collect.get(0),null));
					} // new parameter Pl
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.SPOUSE_EMPLOYEMENT)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.SPOUSE_EMPLOYEMENT, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.NO_OF_DEPENDANTS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.NO_OF_DEPENDANTS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.DESIGNATION)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.DESIGNATION, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.EDUCATION_QUALIFICATION)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.EDUCATION_QUALIFICATION, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.NO_OF_APPLICANTS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.NO_OF_APPLICANTS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.ANNUAL_INCOME)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.ANNUAL_INCOME, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.AVAILABLE_INCOME)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.AVAILABLE_INCOME, CommonUtils.printFields(collect.get(0),null));
					}
//					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.TOIR)).collect(Collectors.toList());
//					if(!CommonUtils.isListNullOrEmpty(collect)) {
//						companyMap.put(Retail.HomeLoan.TOIR, CommonUtils.printFields(collect.get(0),null));
//					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.ADDI_INCOME_SPOUSE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.ADDI_INCOME_SPOUSE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.MON_INCOME_DEPENDANT)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.MON_INCOME_DEPENDANT, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.AVG_INCREASE_INCOME_REPORT_3_YEARS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.AVG_INCREASE_INCOME_REPORT_3_YEARS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.APPLICANT_NW_TO_LOAN_AMOUNT)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.APPLICANT_NW_TO_LOAN_AMOUNT, CommonUtils.printFields(collect.get(0),null));
					}
//					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.REPAYMENT_PERIOD)).collect(Collectors.toList());
//					if(!CommonUtils.isListNullOrEmpty(collect)) {
//						companyMap.put(Retail.HomeLoan.REPAYMENT_PERIOD, CommonUtils.printFields(collect.get(0),null));
//					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.TENURE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.TENURE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.AGE_PROPERTY)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.AGE_PROPERTY, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.AVG_DEPOS_LAST_6_MONTH)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.AVG_DEPOS_LAST_6_MONTH, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.CHECQUE_BOUNSE_LAST_1_MONTH)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.CHECQUE_BOUNSE_LAST_1_MONTH, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.CHECQUE_BOUNSE_LAST_6_MONTH)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.CHECQUE_BOUNSE_LAST_6_MONTH, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.DPD)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.DPD, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.LTV)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.LTV, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.EMI_NMI_RATIO)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.EMI_NMI_RATIO, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.LOAN_PURPOSE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.LOAN_PURPOSE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.INCOME_PROOF)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.INCOME_PROOF, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.EMI_NMI)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.EMI_NMI, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.AVG_EOD_BALANCE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.AVG_EOD_BALANCE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.LOAN_TO_INCOME_RATIO)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.LOAN_TO_INCOME_RATIO, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.INCOME_TO_INSTALLMENT_RATIO)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.INCOME_TO_INSTALLMENT_RATIO, CommonUtils.printFields(collect.get(0),null));
					}
					scoreResponse.add(companyMap);
					map.put("scoringResp", scoreResponse);
			}catch (Exception e) {
				logger.error("Error while getting scoring data : ",e);
			}
		
		//SCORING for Co-Applicant
		try {
			ScoringRequest scoringRequest = new ScoringRequest();
			scoringRequest.setApplicationId(applicationId);
			scoringRequest.setFpProductId(productId);
			List<CoApplicantDetail> coApplicantDetails = coApplicantService.getCoApplicantList(applicationId);
			List<List<Map<String,Object>>> coAppScoringData = new ArrayList<List<Map<String,Object>>>();
			
			if(coApplicantDetails != null) {
			for(CoApplicantDetail coApplicantDetail : coApplicantDetails) {
				scoringRequest.setCoAppId(coApplicantDetail.getId());
				ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
				DecimalFormat df = new DecimalFormat(".##");
				List<Map<String,Object>> scoreResponse = new ArrayList<>(scoringResponse.getDataList().size());
				Map<String,Object> companyMap =new HashMap<>();
				ProposalScoreResponse proposalScoreResponse =  (ProposalScoreResponse)MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)scoringResponse.getDataObject(),ProposalScoreResponse.class);
				companyMap.put("scoringDataObject",CommonUtils.printFields(proposalScoreResponse,null));
			
				//Filter Parameters
				List<LinkedHashMap<String, Object>> mapList = (List<LinkedHashMap<String, Object>>)scoringResponse.getDataList();
				List<ProposalScoreDetailResponse> newMapList = new ArrayList<>(mapList.size());
				for(LinkedHashMap<String, Object> mp : mapList) {
					newMapList.add(MultipleJSONObjectHelper.getObjectFromMap(mp,ProposalScoreDetailResponse.class));
				}
				
				List<ProposalScoreDetailResponse> collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.AGE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.AGE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.CURRENT_JOB_EXP)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.CURRENT_JOB_EXP, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.TOTAL_WORK_EXP)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.TOTAL_WORK_EXP, CommonUtils.printFields(collect.get(0),null));
					}
//					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.TOTAL_BUSI_PROFE_EXP)).collect(Collectors.toList());
//					if(!CommonUtils.isListNullOrEmpty(collect)) {
//						companyMap.put(Retail.HomeLoan.TOTAL_BUSI_PROFE_EXP, CommonUtils.printFields(collect.get(0),null));
//					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.RESIDENCE_TYPE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.RESIDENCE_TYPE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.NO_YEARS_STAY_CURR_LOC)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.NO_YEARS_STAY_CURR_LOC, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.BUREAU_SCORE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.BUREAU_SCORE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.MARITAL_STATUS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.MARITAL_STATUS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.EMPLOYMENT_TYPE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.EMPLOYMENT_TYPE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.EMPLOYMENT_CATEG_JOB)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.EMPLOYMENT_CATEG_JOB, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.CURRENT_EMPLOYMENT_STATUS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.CURRENT_EMPLOYMENT_STATUS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.MIN_BANKING_RELATIONSHIP)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.MIN_BANKING_RELATIONSHIP, CommonUtils.printFields(collect.get(0),null));
					} // new parameter Pl
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.SPOUSE_EMPLOYEMENT)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.SPOUSE_EMPLOYEMENT, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.NO_OF_DEPENDANTS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.NO_OF_DEPENDANTS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.DESIGNATION)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.DESIGNATION, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.EDUCATION_QUALIFICATION)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.EDUCATION_QUALIFICATION, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.NO_OF_APPLICANTS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.NO_OF_APPLICANTS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.ANNUAL_INCOME)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.ANNUAL_INCOME, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.AVAILABLE_INCOME)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.AVAILABLE_INCOME, CommonUtils.printFields(collect.get(0),null));
					}
//					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.TOIR)).collect(Collectors.toList());
//					if(!CommonUtils.isListNullOrEmpty(collect)) {
//						companyMap.put(Retail.HomeLoan.TOIR, CommonUtils.printFields(collect.get(0),null));
//					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.ADDI_INCOME_SPOUSE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.ADDI_INCOME_SPOUSE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.MON_INCOME_DEPENDANT)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.MON_INCOME_DEPENDANT, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.AVG_INCREASE_INCOME_REPORT_3_YEARS)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.AVG_INCREASE_INCOME_REPORT_3_YEARS, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.APPLICANT_NW_TO_LOAN_AMOUNT)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.APPLICANT_NW_TO_LOAN_AMOUNT, CommonUtils.printFields(collect.get(0),null));
					}
//					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.REPAYMENT_PERIOD)).collect(Collectors.toList());
//					if(!CommonUtils.isListNullOrEmpty(collect)) {
//						companyMap.put(Retail.HomeLoan.REPAYMENT_PERIOD, CommonUtils.printFields(collect.get(0),null));
//					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.TENURE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.TENURE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.AGE_PROPERTY)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.AGE_PROPERTY, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.AVG_DEPOS_LAST_6_MONTH)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.AVG_DEPOS_LAST_6_MONTH, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.CHECQUE_BOUNSE_LAST_1_MONTH)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.CHECQUE_BOUNSE_LAST_1_MONTH, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.CHECQUE_BOUNSE_LAST_6_MONTH)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.CHECQUE_BOUNSE_LAST_6_MONTH, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.DPD)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.DPD, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.LTV)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.LTV, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.EMI_NMI_RATIO)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.EMI_NMI_RATIO, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.LOAN_PURPOSE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.LOAN_PURPOSE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.INCOME_PROOF)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.INCOME_PROOF, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.EMI_NMI)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.EMI_NMI, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.AVG_EOD_BALANCE)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.AVG_EOD_BALANCE, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.LOAN_TO_INCOME_RATIO)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.LOAN_TO_INCOME_RATIO, CommonUtils.printFields(collect.get(0),null));
					}
					collect = newMapList.stream().filter(m -> m.getParameterName().equalsIgnoreCase(Retail.HomeLoan.INCOME_TO_INSTALLMENT_RATIO)).collect(Collectors.toList());
					if(!CommonUtils.isListNullOrEmpty(collect)) {
						companyMap.put(Retail.HomeLoan.INCOME_TO_INSTALLMENT_RATIO, CommonUtils.printFields(collect.get(0),null));
					}
					scoreResponse.add(companyMap);
					coAppScoringData.add(scoreResponse);
			}
			map.put("scoringRespOfCoApp", coAppScoringData);
			}
				
		}catch (Exception e) {
			logger.error("Error while getting scoring data : ",e);
		}
		
		//PERFIOS API DATA (BANK STATEMENT ANALYSIS)
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(applicationId);
		reportRequest.setUserId(userId);
		List<Data> datas = new ArrayList<>();
//		List<Object> bankStatement = new ArrayList<Object>();
/**	List<Object> monthlyDetails = new ArrayList<Object>();
		List<Object> top5FundReceived = new ArrayList<Object>();
		List<Object> top5FundTransfered = new ArrayList<Object>();
		List<Object> bouncedChequeList = new ArrayList<Object>();
		List<Object> customerInfo = new ArrayList<Object>();
		List<Object> summaryInfo = new ArrayList<Object>();*/


		try {
			AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
			List<HashMap<String, Object>> listhashMap = (List<HashMap<String, Object>>) analyzerResponse.getData();
			//List<HashMap<String, Object>> bankDataDetails = new ArrayList<HashMap<String,Object>>(); 

			if (!CommonUtils.isListNullOrEmpty(listhashMap)) {	
				for (HashMap<String, Object> rec : listhashMap) {
					Data data = MultipleJSONObjectHelper.getObjectFromMap(rec, Data.class);
					datas.add(data);
							
					//bankStatement.add(!CommonUtils.isObjectNullOrEmpty(data.getXns()) ? CommonUtils.printFields(data.getXns().getXn(),null) : " ");
					/**monthlyDetails.add(!CommonUtils.isObjectNullOrEmpty(data.getMonthlyDetailList()) ? CommonUtils.printFields(data.getMonthlyDetailList(),null) : "");
					top5FundReceived.add(!CommonUtils.isObjectNullOrEmpty(data.getTop5FundReceivedList()) ? CommonUtils.printFields(data.getTop5FundReceivedList().getItem(),null) : "");
					top5FundTransfered.add(!CommonUtils.isObjectNullOrEmpty(data.getTop5FundTransferedList()) ? CommonUtils.printFields(data.getTop5FundTransferedList().getItem(),null) : "");
					bouncedChequeList.add(!CommonUtils.isObjectNullOrEmpty(data.getBouncedOrPenalXnList()) ? CommonUtils.printFields(data.getBouncedOrPenalXnList().getBouncedOrPenalXns(),null) : " ");
					customerInfo.add(!CommonUtils.isObjectNullOrEmpty(data.getCustomerInfo()) ? CommonUtils.printFields(data.getCustomerInfo(),null) : " ");
					summaryInfo.add(!CommonUtils.isObjectNullOrEmpty(data.getSummaryInfo()) ?CommonUtils.printFields(data.getSummaryInfo(),null) : " ");*/
						
/**					HashMap<String, Object>  bankData = new HashMap<>();
					bankData.put("monthlyDetails", monthlyDetails);
					bankData.put("top5FundReceived", top5FundReceived);
					bankData.put("top5FundTransfered", top5FundTransfered);
					bankData.put("bouncedChequeList", bouncedChequeList);
					bankData.put("customerInfo", customerInfo);
					bankData.put("summaryInfo", summaryInfo);
					bankData.put("bankStatementAnalysis", CommonUtils.printFields(datas, null));
					bankDataDetails.add(bankData);*/
				}
						
				map.put("bankRelatedData" , CommonUtils.printFields(datas, null));
				//map.put("bankStatement", bankStatement);
				/**map.put("monthlyDetails", monthlyDetails);
				map.put("top5FundReceived", top5FundReceived);
				map.put("top5FundTransfered", top5FundTransfered);
				map.put("bouncedChequeList", bouncedChequeList);
				map.put("customerInfo", customerInfo);
				map.put("summaryInfo", summaryInfo);
				map.put("bankStatementAnalysis", CommonUtils.printFields(datas, null));*/
			}
		} catch (Exception e) {
			logger.error("Error while getting perfios data : ",e);
		}

		//ELIGIBILITY DATA (ASSESSMENT TO LIMITS)
		try{
			EligibililityRequest eligibilityReq=new EligibililityRequest();
			eligibilityReq.setApplicationId(applicationId);
			eligibilityReq.setFpProductMappingId(productId);
			EligibilityResponse eligibilityResp= eligibilityClient.getHLLoanData(eligibilityReq);
			if(!CommonUtils.isObjectListNull(eligibilityResp,eligibilityResp.getData())){
				map.put("assLimits",CommonUtils.convertToValueForXml(MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)eligibilityResp.getData(), RetailEligibilityRequest.class), new HashMap<>()));
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
					map.put("disbursmentDate", simpleDateFormat.format(data1.get(0)));
				}
				data1 = loanSanctionRepository.findSanctionDateByApplicationId(applicationId);
				if(data1 != null && !data1.isEmpty()) {
					map.put("sanctionDate", simpleDateFormat.format(data1.get(0)));
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
			
			Map<String , Object> retailMap = new HashMap<String, Object>();
			
			//RETAIL FINAL DETAILS
			try {
				RetailFinalInfoRequest retailFinalInfo = plRetailApplicantService.getFinalByProposalId(userId, applicationId, proposalId);
				if(!CommonUtils.isObjectNullOrEmpty(retailFinalInfo)) {
					retailMap.put("educationalQualificationYear", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getQualifyingYear()) ? simpleDateFormat.format(retailFinalInfo.getQualifyingYear()) : "-");
					retailMap.put("birthPlace", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getBirthPlace()) ? retailFinalInfo.getBirthPlace() : "-");
					retailMap.put("religion", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getReligion()) ? ReligionRetailMst.getById(retailFinalInfo.getReligion()).getValue() : "-");
					retailMap.put("caste", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getCastId()) ? CastCategory.getById(retailFinalInfo.getCastId()).getValue() : "-");
					retailMap.put("noOfChildren", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getNoChildren()) ? retailFinalInfo.getNoChildren() : "-");
					retailMap.put("diasablityType", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getDisabilityType()) ? DisabilityType.getById(retailFinalInfo.getDisabilityType()) : "");
					retailMap.put("permanantAddCountry", StringEscapeUtils.escapeXml(getCountryName(retailFinalInfo.getPermanentAddress().getCountryId())));
					retailMap.put("permanantAddState", StringEscapeUtils.escapeXml(getStateName(retailFinalInfo.getPermanentAddress().getStateId())));
					retailMap.put("permanantAddCity", StringEscapeUtils.escapeXml(getCityName(retailFinalInfo.getPermanentAddress().getCityId())));
					retailMap.put("permanantAddPincode", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getPermanentAddress().getPincode())?retailFinalInfo.getPermanentAddress().getPincode() : "");
					
					try {
						if(!CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getPermanentAddress().getDistrictMappingId())) {
							retailMap.put("permanantAddressData",CommonUtils.printFields(pincodeDateService.getById(retailFinalInfo.getPermanentAddress().getDistrictMappingId()),null));				
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
					
					retailMap.put("officeAddPremise", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getOfficeAddress().getPremiseNumber()) ? CommonUtils.printFields(retailFinalInfo.getOfficeAddress().getPremiseNumber(),null) + "," : "");
					retailMap.put("officeAddStreetName", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getOfficeAddress().getStreetName()) ? CommonUtils.printFields(retailFinalInfo.getOfficeAddress().getStreetName(),null) + "," : "");
					retailMap.put("officeAddLandmark", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getOfficeAddress().getLandMark()) ? CommonUtils.printFields(retailFinalInfo.getOfficeAddress().getLandMark(),null) + "," : "");
					retailMap.put("officeAddCountry", StringEscapeUtils.escapeXml(getCountryName(retailFinalInfo.getOfficeAddress().getCountryId())));
					retailMap.put("officeAddState", StringEscapeUtils.escapeXml(getStateName(retailFinalInfo.getOfficeAddress().getStateId())));
					retailMap.put("officeAddCity", StringEscapeUtils.escapeXml(getCityName(retailFinalInfo.getOfficeAddress().getCityId())));
					retailMap.put("officeAddPincode", !CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getOfficeAddress().getPincode())?retailFinalInfo.getPermanentAddress().getPincode() : "");
					try {
						if(!CommonUtils.isObjectNullOrEmpty(retailFinalInfo.getOfficeAddress().getDistrictMappingId())) {
							retailMap.put("officeAddressData",CommonUtils.printFields(pincodeDateService.getById(retailFinalInfo.getOfficeAddress().getDistrictMappingId()),null));				
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
					
					retailMap.put("retailFinalDetails", retailFinalInfo);
				}
			} catch (Exception e) {
				logger.error("Error while getting Final Information : ",e);
			}		
			
			//INCOME DETAILS - GROSS INCOME
			try {
				List<RetailApplicantIncomeRequest> retailApplicantIncomeDetail = retailApplicantIncomeService.getAllByProposalId(applicationId, proposalId);
				
				if(!CommonUtils.isObjectNullOrEmpty(retailApplicantIncomeDetail)) {
					retailMap.put("grossIncomeDetails", retailApplicantIncomeDetail);
				}
			} catch (Exception e) {
				logger.error("Error while getting income details : ",e);
			}
			
			//BANK ACCOUNT HELD DETAILS
			try {
				List<BankAccountHeldDetailsRequest> bankAccountHeldDetails = bankAccountHeldDetailsService.getExistingLoanDetailListByProposalId(proposalId, 1);
				if(!CommonUtils.isObjectNullOrEmpty(bankAccountHeldDetails)) {
					retailMap.put("bankAccountHeld", bankAccountHeldDetails);
				}
			} catch (Exception e) {
				logger.error("Error while getting bank account held details : ",e);
			}
			
			//FIXED DEPOSITS DETAILS
			try {
				List<FixedDepositsDetailsRequest> fixedDepositeDetails = fixedDepositsDetailService.getFixedDepositsDetailByProposalId(proposalId, 1);
				if(!CommonUtils.isObjectNullOrEmpty(fixedDepositeDetails)) {
					retailMap.put("fixedDepositeDetails", fixedDepositeDetails);
				}
			} catch (Exception e) {
				logger.error("Error while getting fixed deposite details : ",e);
			}
			
			//OTHER CURRENT ASSEST DETAILS
			try {
				List<OtherCurrentAssetDetailRequest> otherCurrentAssetDetails = otherCurrentAssetDetailsService.getOtherCurrentAssetDetailListByProposalId(proposalId,1);
				if(!CommonUtils.isObjectNullOrEmpty(otherCurrentAssetDetails)) {
					retailMap.put("otherCurrentAssetDetails", otherCurrentAssetDetails);
				}
			} catch (Exception e) {
				logger.error("Error while getting other current asset details : ",e);
			}
			
			//OBLIGATION DETAILS
			try {
				List<ObligationDetailRequest> obligationRequest = obligationDetailService.getObligationDetailsFromProposalId(proposalId,1);
				if(!CommonUtils.isObjectNullOrEmpty(obligationRequest)) {
					retailMap.put("obligationDetails", obligationRequest);
				}
			} catch (Exception e) {
				logger.error("Error while getting obligation details : ",e);
			}
			
			//REFERENCES DETAILS
			try {
				List<ReferenceRetailDetailsRequest> referenceDetails = referenceRetailDetailService.getReferenceRetailDetailListByPropsalId(proposalId,1);
				if(!CommonUtils.isObjectNullOrEmpty(referenceDetails)) {
					retailMap.put("referenceDetails", referenceDetails);
				}
			} catch (Exception e) {
				logger.error("Error while getting reference details : ",e);
			}
			
			map.put("retailData", retailMap);
			
		}
		
		return map;
	}
	
	 public static void copyAddressFromDomainToRequestOfCoApplicant(CoApplicantDetail from, CoApplicantRequest to) {
	        Address address = new Address();
	        address.setPremiseNumber(from.getAddressPremiseName());
	        address.setLandMark(from.getAddressLandmark());
	        address.setStreetName(from.getAddressStreetName());
	        address.setCityId(from.getAddressCity() != null ? from.getAddressCity().longValue() : null);
	        address.setStateId(CommonUtils.isObjectNullOrEmpty(from.getAddressState()) ? null : from.getAddressState().intValue());
	        address.setCountryId(from.getAddressCountry());
	        address.setPincode(from.getAddressPincode() != null ? from.getAddressPincode().longValue() : null);
	        address.setDistrictMappingId(from.getAddressDistrictMappingId() != null ? from.getAddressDistrictMappingId() : null);
	        to.setFirstAddress(address);
	        
	        Address officeAddress = new Address();
	        officeAddress.setPremiseNumber(from.getOfficePremiseNumberName());
	        officeAddress.setLandMark(from.getOfficeLandMark());
	        officeAddress.setStreetName(from.getOfficeStreetName());
	        officeAddress.setCityId(from.getOfficeCityId() != null ? from.getOfficeCityId().longValue() : null);
	        officeAddress.setStateId(from.getOfficeStateId());
	        officeAddress.setCountryId(from.getOfficeCountryId());
	        officeAddress.setPincode(from.getOfficePincode() != null ? from.getOfficePincode().longValue() : null);
	        to.setSecondAddress(officeAddress);

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
