/**
 * 
 */
package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.eligibility.model.EligibililityRequest;
import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.cibil.api.model.CibilRequest;
import com.capitaworld.cibil.api.model.CibilScoreLogRequest;
import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.connect.api.ConnectStage;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.itr.api.model.ITRConnectionResponse;
import com.capitaworld.itr.client.ITRClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.config.AsyncComponent;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.PincodeDataResponse;
import com.capitaworld.service.loans.model.retail.BankAccountHeldDetailsRequest;
import com.capitaworld.service.loans.model.retail.FixedDepositsDetailsRequest;
import com.capitaworld.service.loans.model.retail.HLOneformPrimaryRes;
import com.capitaworld.service.loans.model.retail.ObligationDetailRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailRequest;
import com.capitaworld.service.loans.model.retail.PLRetailApplicantRequest;
import com.capitaworld.service.loans.model.retail.PLRetailApplicantResponse;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantIncomeRequest;
import com.capitaworld.service.loans.model.retail.RetailFinalInfoRequest;
import com.capitaworld.service.loans.model.teaser.primaryview.HlTeaserViewResponse;
import com.capitaworld.service.loans.repository.common.CommonRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryHomeLoanDetailRepository;
import com.capitaworld.service.loans.service.common.CommonService;
import com.capitaworld.service.loans.service.common.PincodeDateService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.retail.BankAccountHeldDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.FixedDepositsDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.ObligationDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.OtherCurrentAssetDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.PlRetailApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryHomeLoanService;
import com.capitaworld.service.loans.service.fundseeker.retail.ReferenceRetailDetailsService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantIncomeService;
import com.capitaworld.service.loans.service.teaser.primaryview.HlTeaserViewService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
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
import com.capitaworld.service.oneform.enums.DesignationList;
import com.capitaworld.service.oneform.enums.DisabilityType;
import com.capitaworld.service.oneform.enums.EducationStatusRetailMst;
import com.capitaworld.service.oneform.enums.EmploymentCategory;
import com.capitaworld.service.oneform.enums.EmploymentStatusRetailMst;
import com.capitaworld.service.oneform.enums.EmploymentWithPL;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.HomeLoanPurpose;
import com.capitaworld.service.oneform.enums.LoanType;
import com.capitaworld.service.oneform.enums.MaritalStatusMst;
import com.capitaworld.service.oneform.enums.OccupationHL;
import com.capitaworld.service.oneform.enums.OccupationNature;
import com.capitaworld.service.oneform.enums.ReligionRetailMst;
import com.capitaworld.service.oneform.enums.ResidenceTypeHomeLoan;
import com.capitaworld.service.oneform.enums.ResidentialStatus;
import com.capitaworld.service.oneform.enums.SalaryModeMst;
import com.capitaworld.service.oneform.enums.SpouseEmploymentList;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.oneform.model.SectorIndustryModel;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.exception.ScoringException;
import com.capitaworld.service.scoring.model.ProposalScoreResponse;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;

/**
 * @author nilay.darji
 *
 */

@Service
@Transactional
public class HlTeaserViewServiceImpl implements HlTeaserViewService {

	private static final Logger logger = LoggerFactory.getLogger(HlTeaserViewServiceImpl.class);

	private static final String DISTRICT_ID_IS_NULL_MSG = "District id is null";

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateRepository;

	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private DMSClient dmsClient;

	@Autowired
	private MatchEngineClient matchEngineClient;

	@Autowired
	private CoApplicantService coAppService; 

	@Autowired
	private ScoringClient scoringClient;

	@Autowired
	private AnalyzerClient analyzerClient;

	@Autowired
	private EligibilityClient eligibilityClient;
	
	@Autowired
	private PlRetailApplicantService plRetailApplicantService;
	
	@Autowired
	private RetailApplicantIncomeService retailApplicantIncomeService;
	
	@Autowired
	private CorporateFinalInfoService corporateFinalInfoService;
	
	@Autowired
	private PincodeDateService pincodeDateService;
	
	@Autowired
	private CIBILClient cibilClient;
	
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
	private ConnectClient connectClient;
	
	@Autowired
	private ITRClient itrClient;
	
	@Autowired
	private ProposalDetailsClient proposalDetailsClient;
	
	@Autowired
	ApplicationProposalMappingRepository applicationProposalMappingRepository;
	
	@Autowired
	ProductMasterRepository productMasterRepository;
	
	@Autowired
	PrimaryHomeLoanDetailRepository primaryHomeloanDetailsRepo;
	
	@Autowired
	PrimaryHomeLoanService primaryHomeloanService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private AsyncComponent asyncComponent;

	@Autowired
	private CommonRepository commonRepo;
	
	
	@Override
	public HlTeaserViewResponse getHlTeaserView(Long toApplicationId, Integer userType, Long userId,Long productMappingId, Boolean isFinal, Long proposalId) {


		HlTeaserViewResponse hlTeaserViewResponse = new HlTeaserViewResponse();
		/*PLRetailApplicantRequest profile = new PLRetailApplicantRequest();
		try {
			profile = plRetailApplicantService.getProfile(userId, toApplicationId);
			logger.info("Profile Details :{}",profile);
			BeanUtils.copyProperties(profile, hlTeaserViewResponse);
		} catch (LoansException e2) {
			logger.error("Exveption in getting profile value from retail profile for application {}",toApplicationId);
		}*/
		
//		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(toApplicationId);
		ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findOne(proposalId);
		logger.info("applicationProposalMapping  ==>{}",applicationProposalMapping );
		Long userid=applicationProposalMapping.getUserId();
		hlTeaserViewResponse.setLoanType(applicationProposalMapping.getProductId() != null ? LoanType.getById(applicationProposalMapping.getProductId()).getValue().toString() : "");
		hlTeaserViewResponse.setLoanAmount(applicationProposalMapping.getLoanAmount().longValue());
		hlTeaserViewResponse.setTenure(applicationProposalMapping.getTenure()!=null ? ((applicationProposalMapping.getTenure()).toString()) + " Years":" - ");
		hlTeaserViewResponse.setAppId(toApplicationId);
		

		 // CHANGES FOR DATE OF PROPOSAL(TEASER VIEW)	NEW CODE
			try {
				Object obj = "-";
				Date dateOfProposal = loanApplicationRepository.getModifiedDate(toApplicationId, ConnectStage.HL_COMPLETE.getId(), com.capitaworld.service.loans.utils.CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId());
				if(!CommonUtils.isObjectNullOrEmpty(dateOfProposal)) {
			     hlTeaserViewResponse.setDateOfProposal(dateOfProposal);
				}else{
				hlTeaserViewResponse.setDateOfProposal(obj);
				}
			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			// ENDS HERE===================>


		/* ========= Matches Data ========== */
		if (userType != null && !(CommonUtils.UserType.FUND_SEEKER == userType) ) {
			// TEASER VIEW FROM FP SIDE
				try {
					MatchRequest matchRequest = new MatchRequest();
					matchRequest.setApplicationId(toApplicationId);
					matchRequest.setProductId(productMappingId);
					matchRequest.setProposalId(proposalId);
					matchRequest.setBusinessTypeId(applicationProposalMapping.getBusinessTypeId());
					MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfRetail(matchRequest);
					hlTeaserViewResponse.setMatchesList(matchResponse.getMatchDisplayObjectList());
				} catch (Exception e) {
					logger.error("Error while getting matches data : " + e);
				}
		}
		
		// Product Name
		
		if(productMappingId != null) {
			String productName = productMasterRepository.getFpProductName(productMappingId);
			if(productName != null) {
				hlTeaserViewResponse.setFpProductName(productName);
			}
		}
		
		// basic Details
		PLRetailApplicantResponse plRetailApplicantResponse = null;
		try {
			PLRetailApplicantRequest plRetailApplicantRequest = plRetailApplicantService.getPrimaryByProposalId(userid, toApplicationId, proposalId);
			plRetailApplicantResponse = new PLRetailApplicantResponse();
			if(plRetailApplicantRequest != null) {
				
				/*-----  all primary details fetch from db (fs_retail_applicat_detail) and using enum ------*/

				plRetailApplicantResponse.setFullName((plRetailApplicantRequest.getFirstName() != null ? plRetailApplicantRequest.getFirstName() : "") +" "+ (plRetailApplicantRequest.getMiddleName() != null ? plRetailApplicantRequest.getMiddleName() : "") +" "+ (plRetailApplicantRequest.getLastName() != null ?  plRetailApplicantRequest.getLastName() : ""));
				plRetailApplicantResponse.setGender(plRetailApplicantRequest.getGenderId() != null ? Gender.getById(plRetailApplicantRequest.getGenderId()).getValue().toString() : "-");
				plRetailApplicantResponse.setBirthDate(plRetailApplicantRequest.getBirthDate());
				plRetailApplicantResponse.setPan(plRetailApplicantRequest.getPan());
				plRetailApplicantResponse.setAadharNumber(plRetailApplicantRequest.getAadharNumber());
				plRetailApplicantResponse.setMobile(plRetailApplicantRequest.getMobile());
				plRetailApplicantResponse.setEmploymentType(plRetailApplicantRequest.getEmploymentType() != null ? OccupationNature.getById(plRetailApplicantRequest.getEmploymentType()).getValue().toString() : "-");
				plRetailApplicantResponse.setNameOfEmployer(plRetailApplicantRequest.getNameOfEmployer());
				plRetailApplicantResponse.setEmploymentWith(plRetailApplicantRequest.getEmploymentType() != null ? EmploymentCategory.getById(plRetailApplicantRequest.getEmploymentType()).getValue().toString() : "-");
				plRetailApplicantResponse.setCurrentEmploymentStatus(plRetailApplicantRequest.getCurrentEmploymentStatus()!= null ? 	EmploymentStatusRetailMst.getById(plRetailApplicantRequest.getCurrentEmploymentStatus()).getValue() : "-");
				plRetailApplicantResponse.setEmploymentStatus(plRetailApplicantRequest.getEmploymentStatus()!= null ? 	OccupationHL.getById(plRetailApplicantRequest.getEmploymentStatus()).getValue() : "-");
				plRetailApplicantResponse.setCurrentJobYear((plRetailApplicantRequest.getCurrentJobYear() !=null ? (plRetailApplicantRequest.getCurrentJobYear() +" year") : "") + "" +(plRetailApplicantRequest.getCurrentJobMonth() != null ? (plRetailApplicantRequest.getCurrentJobMonth() +" months") :  "" )); 
				plRetailApplicantResponse.setTotalExperienceYear((plRetailApplicantRequest.getTotalExperienceYear() !=null ? (plRetailApplicantRequest.getTotalExperienceYear() +" year") : "") + "" + (plRetailApplicantRequest.getTotalExperienceMonth() != null ? (plRetailApplicantRequest.getTotalExperienceMonth() +" months") :  "" ));
				plRetailApplicantResponse.setResidenceType(plRetailApplicantRequest.getResidenceType() != null ? ResidenceTypeHomeLoan.getById(plRetailApplicantRequest.getResidenceType()).getValue().toString() : "-");
				plRetailApplicantResponse.setMaritalStatus(plRetailApplicantRequest.getStatusId() != null ? MaritalStatusMst.getById(plRetailApplicantRequest.getStatusId()).getValue().toString() : "-");
				plRetailApplicantResponse.setEducationQualificationString(plRetailApplicantRequest.getEducationQualification() != null ? EducationStatusRetailMst.getById(plRetailApplicantRequest.getEducationQualification()).getValue().toString() : "-");
				plRetailApplicantResponse.setSpouseEmployment(plRetailApplicantRequest.getSpouseEmployment() != null ? SpouseEmploymentList.getById(plRetailApplicantRequest.getSpouseEmployment()).getValue().toString() : "-");
				plRetailApplicantResponse.setDesignation(plRetailApplicantRequest.getDesignation()!= null ? DesignationList.getById(plRetailApplicantRequest.getDesignation()).getValue().toString() : "-");
				plRetailApplicantResponse.setNoOfDependent(plRetailApplicantRequest.getNoOfDependent());
				plRetailApplicantResponse.setCategory(plRetailApplicantRequest.getCategory()!=null?String.valueOf(CastCategory.getById(plRetailApplicantRequest.getCategory())):" - ");
				plRetailApplicantResponse.setResidenceSinceYear(plRetailApplicantRequest.getResidenceSinceYear());
				plRetailApplicantResponse.setSalaryMode(plRetailApplicantRequest.getSalaryMode()!=null ? SalaryModeMst.getById(plRetailApplicantRequest.getSalaryMode()).getValue().toString() : "-");
				plRetailApplicantResponse.setFatherName(plRetailApplicantRequest.getFatherName()!=null ? plRetailApplicantRequest.getFatherName(): "-");
				plRetailApplicantResponse.setNationality(plRetailApplicantRequest.getNationality()!=null ? plRetailApplicantRequest.getNationality(): "-");
				plRetailApplicantResponse.setAnnualIncomeOfSpouse(plRetailApplicantRequest.getAnnualIncomeOfSpouse());
				plRetailApplicantResponse.setRetailApplicantIncomeRequestList(plRetailApplicantRequest.getRetailApplicantIncomeRequestList());
				plRetailApplicantResponse.setBusinessStartDate(plRetailApplicantRequest.getBusinessStartDate());
				plRetailApplicantResponse.setNetworth(plRetailApplicantRequest.getNetworth());
				plRetailApplicantResponse.setNationality(plRetailApplicantRequest.getNationality());
				plRetailApplicantResponse.setAnnualIncomeOfSpouse(plRetailApplicantRequest.getAnnualIncomeOfSpouse());

				/*salary account details*/
				plRetailApplicantResponse.setSalaryAccountBankName(plRetailApplicantRequest.getSalaryBankName());
				plRetailApplicantResponse.setIsOtherSalaryAccBank(plRetailApplicantRequest.getIsOtherSalaryBank()!=null ? plRetailApplicantRequest.getIsOtherSalaryBank() : false);

				
				/*Co Applicant Details*/
				List<PLRetailApplicantResponse> coApplicationDetails = getCoApplicationDetails(toApplicationId);
				hlTeaserViewResponse.setRetailCoApplicantDetail(coApplicationDetails);
				
				LocalDate today = LocalDate.now();
				if(plRetailApplicantRequest.getSalaryBankYear() !=null && plRetailApplicantRequest.getSalaryBankMonth()!= null) {

//					LocalDate since = LocalDate.of(plRetailApplicantRequest.getSalaryBankYear(), plRetailApplicantRequest.getSalaryBankMonth(), 1);
//
//			        Period age = Period.between(since, today);
//			        int years = age.getYears();
//			        int months = age.getMonths();

					plRetailApplicantResponse.setSalaryAccountBankSince(plRetailApplicantRequest.getSalaryBankYear()+" year "+plRetailApplicantRequest.getSalaryBankMonth()+" months");
				}
				
				if(plRetailApplicantRequest.getResidenceSinceYear() !=null && plRetailApplicantRequest.getResidenceSinceMonth() != null) {
					LocalDate since = LocalDate.of(plRetailApplicantRequest.getResidenceSinceYear(), plRetailApplicantRequest.getResidenceSinceMonth(), 1);

			        Period age = Period.between(since, today);
			        int years = age.getYears();
			        int months = age.getMonths();
			        
			        plRetailApplicantResponse.setResidenceSinceMonthYear(years+" year "+months+" months");
				}

				
				//citetailApplicantResponse.setry,State,country
				hlTeaserViewResponse.setCity(CommonDocumentUtils.getCity(plRetailApplicantRequest.getAddressCity(), oneFormClient));
				hlTeaserViewResponse.setState(CommonDocumentUtils.getState(plRetailApplicantRequest.getAddressState(), oneFormClient));
				hlTeaserViewResponse.setCountry(CommonDocumentUtils.getState(plRetailApplicantRequest.getAddressCountry(), oneFormClient));
				
				// address
				try {
					if(plRetailApplicantRequest.getContactAddress() != null) {
						PincodeDataResponse pindata=pincodeDateService.getById(plRetailApplicantRequest.getContactAddress().getDistrictMappingId());
						hlTeaserViewResponse.setPresentAddDist(pindata.getDistrictName());
						hlTeaserViewResponse.setPresentAddTaluko(pindata.getTaluka());
						pindata.getTaluka();
					}else {
						logger.warn(DISTRICT_ID_IS_NULL_MSG);
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
				
				if(plRetailApplicantRequest.getContactAddress() != null){
					hlTeaserViewResponse.setPresentAdd( (plRetailApplicantRequest.getContactAddress().getPremiseNumber()!=null ? (CommonUtils.commaReplace(plRetailApplicantRequest.getContactAddress().getPremiseNumber())) :"") + (plRetailApplicantRequest.getContactAddress().getStreetName() != null ? (CommonUtils.commaReplace(plRetailApplicantRequest.getContactAddress().getStreetName())) : "") + (plRetailApplicantRequest.getContactAddress().getLandMark() != null ? (CommonUtils.commaReplace(plRetailApplicantRequest.getContactAddress().getLandMark())) : "")+ (hlTeaserViewResponse.getPresentAddDist() != null ?(CommonUtils.commaReplace(hlTeaserViewResponse.getPresentAddDist())) :"")+ (hlTeaserViewResponse.getPresentAddTaluko() != null ? (CommonUtils.commaReplace(hlTeaserViewResponse.getPresentAddTaluko())) : "") + (plRetailApplicantRequest.getContactAddress().getPincode() != null ? (plRetailApplicantRequest.getContactAddress().getPincode()) : ""));
				}
				
				/*banking relationship details*/

				
				// loan Details 
				plRetailApplicantResponse.setLoanAmountRequired(plRetailApplicantRequest.getLoanAmountRequired());
				hlTeaserViewResponse.setPurposeOfLoan(plRetailApplicantRequest.getLoanPurpose() != null ? HomeLoanPurpose.getById(plRetailApplicantRequest.getLoanPurpose()).getValue().toString() : "NA");
				plRetailApplicantResponse.setTenureRequired(plRetailApplicantRequest.getTenureRequired());
				plRetailApplicantResponse.setRepayment(plRetailApplicantRequest.getRepayment());
				plRetailApplicantResponse.setMonthlyIncome(plRetailApplicantRequest.getMonthlyIncome());
				plRetailApplicantResponse.setTenureReq(plRetailApplicantRequest.getTenureRequired()!= null ? plRetailApplicantRequest.getTenureRequired() > 1 ? plRetailApplicantRequest.getTenureRequired() + " Years" : plRetailApplicantRequest.getTenureRequired() + " Year " : "" );
				
				//set existing financial data if not null 
				
				if(plRetailApplicantRequest.getFinancialArrangementsDetailRequestsList() != null) {
					plRetailApplicantResponse.setFinancialArrangementsDetailRequestsList(plRetailApplicantRequest.getFinancialArrangementsDetailRequestsList());	
				}else {
					logger.warn("FinancialArrangementsDetail is null...");
				}
				
				//set credit card data if not null
				if(plRetailApplicantRequest.getCreditCardsDetailRequestList() != null) {
					
					plRetailApplicantResponse.setCreditCardsDetailRequestList(plRetailApplicantRequest.getCreditCardsDetailRequestList());	
				}else {
					logger.warn("CreditCardDetails is null...");
				}
				
				if(plRetailApplicantRequest.getBankingRelationshipList() != null) {
					plRetailApplicantResponse.setBankRelationShipList(plRetailApplicantRequest.getBankingRelationshipList());
				}else {
					logger.warn("bankRelationship  is null...");
				}

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
						    hlTeaserViewResponse.setKeyVericalFunding(masterResponse.getValue());
						} else {
							logger.warn("key vertical funding is null");
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
						hlTeaserViewResponse.setKeyVericalSector(masterResponse.getValue());
					} else {
						logger.warn("key vertical sector is null");
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
				
				//KEY VERTICAL SUBSECTOR
				try {
					if (!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getKeyVerticalSubSector())) {
						OneFormResponse oneFormResponse = oneFormClient.getSubSecNameByMappingId(plRetailApplicantRequest.getKeyVerticalSubSector());
						hlTeaserViewResponse.setKeyVericalSubsector(oneFormResponse.getData().toString());
					}
				} catch (Exception e) {
					logger.warn("key vertical subSector is null ");
				}
				
				
				hlTeaserViewResponse.setRetailApplicantDetail(plRetailApplicantResponse);
				
			}else {
				logger.warn("retailApplicantDetail is null");
			}
			
		} catch (Exception e) {
			logger.error("error while fetching retailApplicantDetails : ",e);
		}
		
		//property details
		
		PrimaryHomeLoanDetail primaryHlDetail= primaryHomeloanDetailsRepo.getByApplication(toApplicationId);
		HLOneformPrimaryRes res=primaryHomeloanService.getOneformPrimaryDetails(toApplicationId);
		if(primaryHlDetail != null) {
			hlTeaserViewResponse.setPropertyValue(primaryHlDetail.getMarketValProp() != null ? primaryHlDetail.getMarketValProp() : 0);
			hlTeaserViewResponse.setPropertyAge((primaryHlDetail.getOldPropYear()!= null ? primaryHlDetail.getOldPropYear() + (primaryHlDetail.getOldPropYear() >1 ? " years"  : " year") :"") + " " +
												( primaryHlDetail.getOldPropMonth()!= null? primaryHlDetail.getOldPropMonth() + (primaryHlDetail.getOldPropMonth() > 1 ? " months" : " month") :""));
		}
		
		if(res!= null) {
			if(res.getPropCity() != null && res.getPropState() != null && res.getPropCountry() != null) {
				Map<String ,Object> mapData = commonService.getCityStateCountryNameFromOneForm(res.getPropCity().longValue(), res.getPropState().intValue(), res.getPropCountry().intValue());
				if(mapData != null) {
					hlTeaserViewResponse.setPropertyAdd( (res.getPropPremiseName()!= null ? res.getPropPremiseName()+"," :"") + 
														 (res.getPropStreetName()!= null ? res.getPropStreetName()+"," : "") + 
														 (res.getPropLandmark() != null ? res.getPropLandmark()+"," : "") + 
														 (mapData.get(CommonUtils.CITY_NAME).toString() != null ? mapData.get(CommonUtils.CITY_NAME).toString() +"," : "") + 
														 (mapData.get(CommonUtils.STATE_NAME).toString() != null ? mapData.get(CommonUtils.STATE_NAME).toString() +"," : "") + 
														 ((mapData.get(CommonUtils.COUNTRY_NAME).toString() != null ? mapData.get(CommonUtils.COUNTRY_NAME).toString() +"," : "")) + 
														 (res.getPropPincode() != null ? res.getPropPincode() : "")
														);
				}
			}
		}
		
		
		//PROPOSAL RESPONSE
		try {
			ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
			proposalMappingRequest.setApplicationId(toApplicationId);
			proposalMappingRequest.setFpProductId(productMappingId);
			ProposalMappingResponse proposalMappingResponse= proposalDetailsClient.getActiveProposalDetails(proposalMappingRequest);
			if(proposalMappingResponse.getData() != null) {	
				hlTeaserViewResponse.setProposalData(proposalMappingResponse.getData());
			}else {
				logger.info("proposal data is null");
			}
		}catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
				
		//cibil score
		try {
			CibilRequest cibilReq=new CibilRequest();
			cibilReq.setPan(plRetailApplicantResponse.getPan());
			cibilReq.setApplicationId(toApplicationId);
			CibilScoreLogRequest cibilScoreByPanCard = cibilClient.getCibilScoreByPanCard(cibilReq);
			hlTeaserViewResponse.setCibilScore(cibilScoreByPanCard);
		} catch (Exception e) {
			logger.error("Error While calling Cibil Score By PanCard : ",e);
		}
		
		// Income Details
		try {
			List<RetailApplicantIncomeRequest> retailApplicantIncomeDetail = retailApplicantIncomeService.getAllByProposalId(toApplicationId, proposalId);
			
			if(retailApplicantIncomeDetail != null) {
				hlTeaserViewResponse.setRetailApplicantIncomeDetails(retailApplicantIncomeDetail);	
			}else {
				logger.warn("..........::::::::----->>retailApplicantIncomeDetail is null<<-----:::::::::.....");	
			}
		} catch (Exception e) {
			logger.error("..........::::::::----->> Error while calling PL Income Details <<-----:::::::::.....",e);
		}
		
		// bank statement data
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(toApplicationId);
		reportRequest.setUserId(userId);
		List<Data> datas = new ArrayList<>();
		try {
			AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
			List<HashMap<String, Object>> hashMaps = (List<HashMap<String, Object>>) analyzerResponse.getData();
			if (!CommonUtils.isListNullOrEmpty(hashMaps)) {
				for (HashMap<String, Object> hashMap : hashMaps) {
					Data data = MultipleJSONObjectHelper.getObjectFromMap(hashMap, Data.class);
					datas.add(data);
				}
			}
			hlTeaserViewResponse.setBankData(datas);
		
		} catch (Exception e) {
			logger.error("Error while getting perfios data : ",e);
		}

		// SCORING DATA
		ScoringRequest scoringRequest = new ScoringRequest();
		scoringRequest.setApplicationId(toApplicationId);
		scoringRequest.setFpProductId(productMappingId);

		try {
			ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
			ProposalScoreResponse proposalScoreResponse = MultipleJSONObjectHelper.getObjectFromMap(
					(LinkedHashMap<String, Object>) scoringResponse.getDataObject(), ProposalScoreResponse.class);

			if (proposalScoreResponse != null){
				logger.info("getObjectFromMap called successfully");
				hlTeaserViewResponse.setScoringModelName(proposalScoreResponse.getScoringModelName()!=null?proposalScoreResponse.getScoringModelName():" - ");
				hlTeaserViewResponse.setDataList(scoringResponse.getDataList()!=null?scoringResponse.getDataList():" - ");
				hlTeaserViewResponse.setDataObject(scoringResponse.getDataObject()!=null?scoringResponse.getDataObject():" - ");
				hlTeaserViewResponse.setScoringResponseList(scoringResponse.getScoringResponseList()!=null?scoringResponse.getScoringResponseList():" - ");
			}
		} catch (ScoringException | IOException e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
		}

		// Eligibility Data
		EligibililityRequest eligibilityReq = new EligibililityRequest();
		eligibilityReq.setApplicationId(toApplicationId);
		eligibilityReq.setFpProductMappingId(productMappingId);
		logger.info(" for eligibility appid============>>{}" , toApplicationId);

		try {
			EligibilityResponse eligibilityResp = eligibilityClient.getHLLoanData(eligibilityReq);
			hlTeaserViewResponse.setEligibilityDataObject(eligibilityResp.getData()!=null?eligibilityResp.getData():null);
		} catch (Exception e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
		}
		
		try {
			ITRConnectionResponse resNameAsPerITR = itrClient.getIsUploadAndYearDetails(toApplicationId);
			if (resNameAsPerITR != null) {
				hlTeaserViewResponse.setNameAsPerItr(resNameAsPerITR.getData() != null ? resNameAsPerITR.getData() : "NA");
			} else {
				logger.warn("-----------:::::::::::::: ItrResponse is null ::::::::::::---------");
			}
		} catch (Exception e) {
			logger.error(":::::::::::---------Error while fetching name as per itr----------:::::::::::",e);
		}
		
		
		// GET DOCUMENTS
		DocumentRequest documentRequest = new DocumentRequest();
		documentRequest.setApplicationId(toApplicationId);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			hlTeaserViewResponse.setProfilePic(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_BANK_STATEMENT);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			hlTeaserViewResponse.setBankStatement(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.RETAIL_ITR_PDF);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			hlTeaserViewResponse.setIrtPdfReport(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.RETAIL_ITR_XML);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			hlTeaserViewResponse.setIrtXMLReport(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		// pl final view details filled from here
		if (isFinal) {	
			try {
				RetailFinalInfoRequest retailFinalInfo = plRetailApplicantService.getFinalByProposalId(userId, toApplicationId, proposalId);
				if(retailFinalInfo != null) {
					hlTeaserViewResponse.setReligion(retailFinalInfo.getReligion() != null ? ReligionRetailMst.getById(retailFinalInfo.getReligion()).getValue().toString() : "-");
					hlTeaserViewResponse.setResidentialStatus(retailFinalInfo.getResidentialStatus() != null ? ResidentialStatus.getById(retailFinalInfo.getResidentialStatus()).getValue().toString() : "-");
					hlTeaserViewResponse.setCastCategory(retailFinalInfo.getCastId() != null ? CastCategory.getById(retailFinalInfo.getCastId()).getValue().toString() : "-");
					hlTeaserViewResponse.setDiasablityType(retailFinalInfo.getDisabilityType() != null ? DisabilityType.getById(retailFinalInfo.getDisabilityType()).getValue().toString() : "-");
					hlTeaserViewResponse.setDdoOrganizationType(retailFinalInfo.getDdoOrganizationType() != null ? EmploymentWithPL.getById(retailFinalInfo.getDdoOrganizationType()).getValue().toString() : "-");
					if(retailFinalInfo.getDdoRemainingSerYrs() != null && retailFinalInfo.getDdoRemainingSerMonths() != null) {
						LocalDate today = LocalDate.now();
						LocalDate remainingYears = LocalDate.of(retailFinalInfo.getDdoRemainingSerYrs(), retailFinalInfo.getDdoRemainingSerMonths(), 1);
						Period p = Period.between(today, remainingYears);
						retailFinalInfo.setDdoRemainingSerYrs(p.getYears());
						retailFinalInfo.setDdoRemainingSerMonths(p.getMonths());
					}
					
					//permanent address
					try {
						if(retailFinalInfo != null && retailFinalInfo.getPermanentAddress().getDistrictMappingId() != null) {	
							PincodeDataResponse pindata=pincodeDateService.getById(retailFinalInfo.getPermanentAddress().getDistrictMappingId());
							hlTeaserViewResponse.setPermAddDist(pindata.getDistrictName());
							hlTeaserViewResponse.setPermAddTaluko(pindata.getTaluka());
							pindata.getTaluka();
						}else {
							logger.warn(DISTRICT_ID_IS_NULL_MSG);
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
					
					if(retailFinalInfo.getPermanentAddress() != null){	
						hlTeaserViewResponse.setPermAdd( (retailFinalInfo.getPermanentAddress().getPremiseNumber()!=null ? (CommonUtils.commaReplace(retailFinalInfo.getPermanentAddress().getPremiseNumber())) :"") + (retailFinalInfo.getPermanentAddress().getStreetName() != null ? (CommonUtils.commaReplace(retailFinalInfo.getPermanentAddress().getStreetName())) : "") + (retailFinalInfo.getPermanentAddress().getLandMark() != null ? (CommonUtils.commaReplace(retailFinalInfo.getPermanentAddress().getLandMark())) : "")+ (hlTeaserViewResponse.getPermAddDist() != null ?(CommonUtils.commaReplace(hlTeaserViewResponse.getPermAddDist())) :"")+ (hlTeaserViewResponse.getPermAddTaluko() != null ? (CommonUtils.commaReplace(hlTeaserViewResponse.getPermAddTaluko())) : "") + (retailFinalInfo.getPermanentAddress().getPincode() != null ? (retailFinalInfo.getPermanentAddress().getPincode()) : ""));
					}
					
					
					//Office address
					try {
						if(retailFinalInfo != null && retailFinalInfo.getOfficeAddress().getDistrictMappingId() !=null) {	
							PincodeDataResponse pindata=pincodeDateService.getById(retailFinalInfo.getOfficeAddress().getDistrictMappingId());
							hlTeaserViewResponse.setOffAddDist(pindata.getDistrictName());
							hlTeaserViewResponse.setOffAddTaluko(pindata.getTaluka());
							pindata.getTaluka();
						}else {
							logger.warn(DISTRICT_ID_IS_NULL_MSG);
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
					
					if(retailFinalInfo.getOfficeAddress() != null){
						hlTeaserViewResponse.setOffAdd( (retailFinalInfo.getOfficeAddress().getPremiseNumber()!=null ? (CommonUtils.commaReplace(retailFinalInfo.getOfficeAddress().getPremiseNumber())) :"") + (retailFinalInfo.getOfficeAddress().getStreetName() != null ? (CommonUtils.commaReplace(retailFinalInfo.getOfficeAddress().getStreetName())) : "") + (retailFinalInfo.getOfficeAddress().getLandMark() != null ? (CommonUtils.commaReplace(retailFinalInfo.getOfficeAddress().getLandMark())) : "")+ (hlTeaserViewResponse.getOffAddDist() != null ?(CommonUtils.commaReplace(hlTeaserViewResponse.getOffAddDist())) :"")+ (hlTeaserViewResponse.getOffAddTaluko() != null ? (CommonUtils.commaReplace(hlTeaserViewResponse.getOffAddTaluko())) : "") + (retailFinalInfo.getOfficeAddress().getPincode() != null ? (retailFinalInfo.getOfficeAddress().getPincode()) : ""));
					}
				
					hlTeaserViewResponse.setFinalDetails(retailFinalInfo);
				}else {
					logger.warn("Retail Final Info is Null....");
				}	
			} catch (Exception e) {
				logger.error("Error while fetching RetailFinalData : ",e);
			}

			//BANK ACCOUNT HELD DETAILS
			try {
				List<BankAccountHeldDetailsRequest> bankAccountHeldDetails = bankAccountHeldDetailsService.getExistingLoanDetailListByProposalId(proposalId,1);
				if(bankAccountHeldDetails != null) {
					hlTeaserViewResponse.setBankAccountDetails(bankAccountHeldDetails);
				}else {
					logger.warn("Bank Held Details is Null....");
				}	
			} catch (Exception e) {
				logger.error("Error while getting bank account held details : ",e);
			}
			
			//FIXED DEPOSITS DETAILS
			try {
				List<FixedDepositsDetailsRequest> fixedDepositeDetails = fixedDepositsDetailService.getFixedDepositsDetailByProposalId(proposalId, 1);
				if(fixedDepositeDetails != null) {
					hlTeaserViewResponse.setFixDepositDetails(fixedDepositeDetails);
				}else {
					logger.warn("Fix Deposit Details is Null....");
				}
			} catch (Exception e) {
				logger.error("Error while getting fixed deposite details : ",e);
			}
			
			//OTHER CURRENT ASSEST DETAILS
			try {
				List<OtherCurrentAssetDetailRequest> otherCurrentAssetDetails = otherCurrentAssetDetailsService.getOtherCurrentAssetDetailListByProposalId(proposalId,1);	
				if(otherCurrentAssetDetails != null) {
					hlTeaserViewResponse.setOtherCurruntAssetDetail(otherCurrentAssetDetails);	
				}else {
					logger.warn("Other Currnt Asset Details is Null....");
				}
			} catch (Exception e) {
				logger.error("Error while getting other current asset details : ",e);
			}
			
			//OBLIGATION DETAILS
			try {
				List<ObligationDetailRequest> obligationRequest = obligationDetailService.getObligationDetailsFromProposalId(proposalId, 1);
				if(obligationRequest != null) {	
					hlTeaserViewResponse.setObligationDetails(obligationRequest);	
				}else {
					logger.warn("Obligation Details is Null....");
				}	
			} catch (Exception e) {
				logger.error("Error while getting obligation details : ",e);
			}
			
			//REFERENCES DETAILS
			try {
				List<ReferenceRetailDetailsRequest> referenceDetails = referenceRetailDetailService.getReferenceRetailDetailListByPropsalId(proposalId,1);
				if(referenceDetails !=null) {	
					hlTeaserViewResponse.setReferenceDetails(referenceDetails);
				}else {
					logger.warn("Reference Details is Null...");
				}
			} catch (Exception e) {
				logger.error("Error while getting reference details : ",e);
			}
			
			DocumentRequest finalDocumentRequest = new DocumentRequest();
			documentRequest.setApplicationId(toApplicationId);
			documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
			documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(finalDocumentRequest);
				hlTeaserViewResponse.setProfilePic(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
		}
		return hlTeaserViewResponse;
	
	}



	private List<PLRetailApplicantResponse> getCoApplicationDetails(Long applicationId) throws LoansException {
		List<PLRetailApplicantResponse> request=new ArrayList<>(); 
		try {
			List<CoApplicantDetail> coApplicantList = coAppService.getCoApplicantList(applicationId);
			for (CoApplicantDetail coApplicantDetail : coApplicantList) {
				PLRetailApplicantResponse plRetailApplicantResponse=new PLRetailApplicantResponse();
				
				plRetailApplicantResponse.setFullName((coApplicantDetail.getFirstName() != null ? coApplicantDetail.getFirstName() : "") +" "+ (coApplicantDetail.getMiddleName() != null ? coApplicantDetail.getMiddleName() : "") +" "+ (coApplicantDetail.getLastName() != null ?  coApplicantDetail.getLastName() : ""));
				plRetailApplicantResponse.setGender(coApplicantDetail.getGenderId() != null ? Gender.getById(coApplicantDetail.getGenderId()).getValue().toString() : "-");
				plRetailApplicantResponse.setBirthDate(coApplicantDetail.getBirthDate());
				plRetailApplicantResponse.setPan(coApplicantDetail.getPan());
				plRetailApplicantResponse.setAadharNumber(coApplicantDetail.getAadharNumber());
				plRetailApplicantResponse.setMobile(coApplicantDetail.getMobile());
				plRetailApplicantResponse.setEmploymentType(coApplicantDetail.getEmploymentType() != null ? OccupationNature.getById(coApplicantDetail.getEmploymentType()).getValue().toString() : "-");
				plRetailApplicantResponse.setEmploymentStatus(coApplicantDetail.getEmploymentStatus() != null ? 	EmploymentCategory.getById(coApplicantDetail.getEmploymentStatus()).getValue() : "-");
				plRetailApplicantResponse.setCurrentJobYear((coApplicantDetail.getCurrentJobYear() !=null ? (coApplicantDetail.getCurrentJobYear() +" year") : "") + "" +(coApplicantDetail.getCurrentJobMonth() != null ? (coApplicantDetail.getCurrentJobMonth() +" months") :  "" )); 
				plRetailApplicantResponse.setTotalExperienceYear((coApplicantDetail.getTotalExperienceYear() !=null ? (coApplicantDetail.getTotalExperienceYear() +" year") : "") + "" + (coApplicantDetail.getTotalExperienceMonth() != null ? (coApplicantDetail.getTotalExperienceMonth() +" months") :  "" ));
				plRetailApplicantResponse.setResidenceType(coApplicantDetail.getResidenceType() != null ? ResidenceTypeHomeLoan.getById(coApplicantDetail.getResidenceType()).getValue().toString() : "-");
				plRetailApplicantResponse.setMaritalStatus(coApplicantDetail.getStatusId() != null ? MaritalStatusMst.getById(coApplicantDetail.getStatusId()).getValue().toString() : "-");
				plRetailApplicantResponse.setCategory(coApplicantDetail.getCategory()!=null?String.valueOf(CastCategory.getById(coApplicantDetail.getCategory())):" - ");
				plRetailApplicantResponse.setFatherName(coApplicantDetail.getFatherName()!=null ? coApplicantDetail.getFatherName(): "-");
				plRetailApplicantResponse.setNationality(coApplicantDetail.getNationality()!=null ?ResidentialStatus.getById(coApplicantDetail.getNationality()).getValue(): "-");
				plRetailApplicantResponse.setResidenceSinceMonthYear(coApplicantDetail.getResidenceSinceMonth()!=null?coApplicantDetail.getResidenceSinceYear()!=null?coApplicantDetail.getResidenceSinceMonth()+"-"+coApplicantDetail.getResidenceSinceYear():"":"");
				plRetailApplicantResponse.setResidenceSinceYear(coApplicantDetail.getResidenceSinceYear());
				plRetailApplicantResponse.setNameOfEmployer(coApplicantDetail.getNameOfEntity());
				plRetailApplicantResponse.setEmploymentWith(EmploymentCategory.getById(coApplicantDetail.getEmploymentStatus()).getValue());
				plRetailApplicantResponse.setBusinessStartDate(coApplicantDetail.getBusinessStartDate());
				plRetailApplicantResponse.setNetworth(coApplicantDetail.getNetworth());
				plRetailApplicantResponse.setGrossMonthlyIncome(coApplicantDetail.getGrossMonthlyIncome());
				plRetailApplicantResponse.setCurrentEmploymentStatus(EmploymentStatusRetailMst.getById(coApplicantDetail.getCurrentEmploymentStatus()).getValue());
				plRetailApplicantResponse.setMonthlyIncome(coApplicantDetail.getMonthlyIncome());
				
				try {
					ITRConnectionResponse resNameAsPerITR = itrClient.getIsUploadAndYearDetails(applicationId);
					if (resNameAsPerITR != null) {
						String coAppName = commonRepo.getCoApplicatantNameFromITR(coApplicantDetail.getId());
						plRetailApplicantResponse.setCoApplicantNameAsPerITR(coAppName);
					} else {
						logger.warn("-----------:::::::::::::: ItrResponse is null ::::::::::::---------");
					}
				} catch (Exception e) {
					logger.error(":::::::::::---------Error while fetching name as per itr----------:::::::::::",e);
				}
				
				try {
					plRetailApplicantResponse.setAddress(asyncComponent.murgedAddress(coApplicantDetail.getAddressPremiseName(), coApplicantDetail.getAddressLandmark(), coApplicantDetail.getAddressStreetName(), Long.valueOf(coApplicantDetail.getAddressCity()), Long.valueOf(coApplicantDetail.getAddressPincode().toString()), Long.valueOf(coApplicantDetail.getAddressState())));
				}catch (Exception e) {
					logger.error("Error in getting address for coapplicant");
				}
				
				List<FinancialArrangementsDetailRequest> financeData = financialArrangementDetailsService.getFinancialArrangementDetailsListDirId(coApplicantDetail.getId(), applicationId);
				plRetailApplicantResponse.setFinancialArrangementsDetailRequestsList(financeData);
				
				

				/*get itrXml and ItrPdf as per coApplicant Id*/
				
				DocumentRequest coAppDocReq = new DocumentRequest();
				coAppDocReq.setCoApplicantId(coApplicantDetail.getId());
				coAppDocReq.setUserType(DocumentAlias.UERT_TYPE_CO_APPLICANT);
				coAppDocReq.setProductDocumentMappingId(DocumentAlias.RETAIL_ITR_XML);
				try {
					DocumentResponse documentResponse = dmsClient.listProductDocument(coAppDocReq);
					plRetailApplicantResponse.setCoAppItrXml(documentResponse.getDataList());
				} catch (DocumentException e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
				coAppDocReq.setProductDocumentMappingId(DocumentAlias.RETAIL_ITR_PDF);
				try {
					DocumentResponse documentResponse = dmsClient.listProductDocument(coAppDocReq);
					plRetailApplicantResponse.setCoAppItrPdf(documentResponse.getDataList());
				} catch (DocumentException e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
				request.add(plRetailApplicantResponse);
			}
		}catch (Exception e) {
			logger.error("",e);
		}
		return request;
	}
	
}
