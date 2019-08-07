package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import com.capitaworld.cibil.api.model.CibilScoreLogRequest;
import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.connect.api.ConnectRequest;
import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.itr.api.model.ITRConnectionResponse;
import com.capitaworld.itr.client.ITRClient;
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
import com.capitaworld.service.gst.GstCalculation;
import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.gst.MomSales;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.gst.model.CAMGSTData;
import com.capitaworld.service.gst.yuva.request.GSTR1Request;
import com.capitaworld.service.loans.domain.fundprovider.ProposalDetails;
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.WcTlParameter;
import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameter;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalDetails;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponseString;
import com.capitaworld.service.loans.model.DirectorPersonalDetailResponse;
import com.capitaworld.service.loans.model.FinanceMeansDetailRequest;
import com.capitaworld.service.loans.model.FinanceMeansDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementDetailResponseString;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.GstRelatedPartyRequest;
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
import com.capitaworld.service.loans.model.common.CGTMSECalcDataResponse;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.corporate.PrimaryCorporateRequest;
import com.capitaworld.service.loans.model.corporate.TotalCostOfProjectRequest;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.WcTlLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.WorkingCapitalParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.IneligibleProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.service.common.PincodeDateService;
import com.capitaworld.service.loans.service.fundseeker.corporate.AchievmentDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssociatedConcernDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CamReportPdfDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CollateralSecurityDetailService;
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
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.service.teaser.primaryview.CorporatePrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingRequestString;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.mca.client.McaClient;
import com.capitaworld.service.mca.model.McaResponse;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.AssessedForITMst;
import com.capitaworld.service.oneform.enums.CompetitionMst_SBI;
import com.capitaworld.service.oneform.enums.Constitution;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.DirectorRelationshipType;
import com.capitaworld.service.oneform.enums.EducationalStatusMst;
import com.capitaworld.service.oneform.enums.EstablishmentMonths;
import com.capitaworld.service.oneform.enums.FactoryPremiseMst;
import com.capitaworld.service.oneform.enums.FinanceCategory;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.HaveLIMst;
import com.capitaworld.service.oneform.enums.Industry;
import com.capitaworld.service.oneform.enums.KnowHowMst;
import com.capitaworld.service.oneform.enums.LCBG_Status_SBI;
import com.capitaworld.service.oneform.enums.MaritalStatusMst;
import com.capitaworld.service.oneform.enums.OwningHouseMst;
import com.capitaworld.service.oneform.enums.Particular;
import com.capitaworld.service.oneform.enums.PurposeOfLoan;
import com.capitaworld.service.oneform.enums.ResidentStatusMst;
import com.capitaworld.service.oneform.enums.ShareHoldingCategory;
import com.capitaworld.service.oneform.enums.SpouseDetailMst;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.enums.VisuallyImpairedMst;
import com.capitaworld.service.oneform.enums.WcRenewalType;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Service
@Transactional
public class CamReportPdfDetailsServiceImpl implements CamReportPdfDetailsService{

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;

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
	private OperatingStatementDetailsRepository operatingStatementDetailsRepository;
	
	@Autowired
	private LiabilitiesDetailsRepository liabilitiesDetailsRepository;
	
	@Autowired
	private AssetsDetailsRepository assetsDetailsRepository;
	
	@Autowired
	private EligibilityClient eligibilityClient;
	
	@Autowired
	private WorkflowClient workflowClient;
	
	@Autowired
	private ConnectClient connectClient;
	
	@Autowired
	private McaClient mcaClient;
	
	@Autowired
	private FraudAnalyticsClient fraudAnalyticsClient;
	
	@Autowired
	private TermLoanParameterRepository termLoanParameterRepository;
	
	@Autowired
	private WorkingCapitalParameterRepository workingCapitalParameterRepository;
	
	@Autowired
	private WcTlLoanParameterRepository wcTlLoanParameterRepository;
	
	@Autowired
	private ITRClient itrClient;
	
	@Autowired
	private ScoringService scoringService;
	
	@Autowired
	private PincodeDateService pincodeDateService;
	
	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateRepository;
	
	@Autowired
	private ProductMasterRepository productMasterRepository;
	
	@Autowired
	private CollateralSecurityDetailService collateralSecurityDetailService;

	@Autowired
	ProposalDetailsRepository proposalDetailsRepository;

	@Autowired
	IneligibleProposalDetailsRepository ineligibleProposalDetailsRepository;
	
	@Autowired
	CorporatePrimaryViewService corporatePrimaryViewService;

	private static final Logger logger = LoggerFactory.getLogger(CamReportPdfDetailsServiceImpl.class);
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private static final String ASSESSMENT_ID = "assessmentId";
	private static final String FITCH_RESPONSE = "fitchResponse";
	private static final String FITCH_TITLE = "fitchTitle";

	@Override
	public Map<String, Object> getCamReportPrimaryDetails(Long applicationId, Long productId,Long proposalId, boolean isFinalView) {

		ProposalMappingRequestString proposalMappingRequestString = null;
		Map<String, Object> map = new HashMap<String, Object>();

		Long userOrgId = proposalDetailsRepository.getOrgIdByProposalId(proposalId);
		ProposalDetails proposalDetails = proposalDetailsRepository.getSanctionProposalByApplicationIdAndUserOrgId(applicationId, userOrgId);
		IneligibleProposalDetails ineligibleProposalDetails = ineligibleProposalDetailsRepository.getSanctionedByApplicationIdAndOrgId(applicationId, userOrgId);

		if (!CommonUtils.isObjectNullOrEmpty(proposalDetails) || !CommonUtils.isObjectNullOrEmpty(ineligibleProposalDetails)) {
			map.put("isSanctioned", "true");
		}

		DecimalFormat decim = new DecimalFormat("####");

        // CHANGES FOR NEW MULTIPLE BANKS----->
        ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.getByApplicationIdAndProposalId(proposalId);
        Long toApplicationId = applicationProposalMapping.getApplicationId();
        Long userId     =  applicationProposalMapping.getUserId();

        //Long userId = loanApplicationRepository.getUserIdByApplicationId(toApplicationId);  // PREVIOUS
        // ENDS HERE MULTIPLE BANK----->

        //new loan type based on proposal mapping
        map.put("loanType", !CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getProductId()) ? CommonUtils.LoanType.getType(applicationProposalMapping.getProductId()).getName() : " ");
        
        //CHANGES====>
        LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getByIdAndUserId(toApplicationId, userId);
        if(applicationProposalMapping != null) {
            map.put("applicationCode", applicationProposalMapping.getApplicationCode());
            map.put("date",!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getApprovedDate())? simpleDateFormat.format(applicationProposalMapping.getApprovedDate()):"-");
            map.put("isMcqSkipped", applicationProposalMapping.getIsMcqSkipped() != null ? applicationProposalMapping.getIsMcqSkipped() : false);
        }
        
        CorporateApplicantRequest corporateApplicantRequest =corporateApplicantService.getCorporateApplicant(toApplicationId);
        UserResponse userResponse = usersClient.getEmailMobile(userId);
        if(userResponse != null) {
            LinkedHashMap<String, Object> lm = (LinkedHashMap<String, Object>)userResponse.getData();
            try {
                UsersRequest request = MultipleJSONObjectHelper.getObjectFromMap(lm,UsersRequest.class);
                map.put("mobile", request.getMobile());
                map.put("email", StringEscapeUtils.escapeXml(request.getEmail()));
            } catch (IOException e1) {
                logger.error("Error while getting registration details : ",e1);
            }
        }

		CorporateFinalInfoRequest corporateFinalInfoRequest;
		try {
			//corporateFinalInfoRequest = corporateFinalInfoService.get(userId, applicationId);  PREVIOIS
			corporateFinalInfoRequest = corporateFinalInfoService.getByProposalId(userId, proposalId);//NEW

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
		
		// Product Name
		if(productId != null) {
			String productName = productMasterRepository.getFpProductName(productId);
			if(productName != null) {
				try {
					map.put("fpProductName",CommonUtils.printFields(productName, null));
				} catch (Exception e) {
					logger.info("error"+e);
				}
			}else {
				logger.info("product name is null..of productId==>{}", productId);
			}
		}else {
			logger.info("fpProductMapping id is null..");
		}
		
		// application type
		map.put("applicationType", (loanApplicationMaster.getWcRenewalStatus() != null ? WcRenewalType.getById(loanApplicationMaster.getWcRenewalStatus()).getValue() : "New" ));

		//TIMELINE DATES  date of is now change again it is consider at the time of mcq page selection time ----
		map.put("dateOfProposal", !CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getCreatedDate())? CommonUtils.DATE_FORMAT.format(loanApplicationMaster.getCreatedDate()):"-");
		
		try {
			WorkflowRequest workflowRequest = new WorkflowRequest();
			workflowRequest.setApplicationId(toApplicationId);
			workflowRequest.setWorkflowId(WorkflowUtils.Workflow.DDR);
			workflowRequest.setUserId(userId);
			WorkflowResponse auditTrail = workflowClient.getAuditTrail(workflowRequest);
			if(!CommonUtils.isObjectNullOrEmpty(auditTrail)) {
				map.put("trailDates", auditTrail.getData());
			}
		} catch (Exception e2) {
			logger.error(CommonUtils.EXCEPTION,e2);
		}
		
		// FOR dateOfInPrincipalApproval  NEW FOR MULTIPLE BANK CONNECT MODIFIED DATE
		try {
			ConnectRequest response = null;
			ConnectResponse connectResponse = connectClient.getApplicationList(toApplicationId);
			if (!CommonUtils.isObjectNullOrEmpty(connectResponse) && !CommonUtils.isListNullOrEmpty(connectResponse.getDataList())) {
				List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) connectResponse.getDataList();
				for (LinkedHashMap<String, Object> mp : list) {
					response = MultipleJSONObjectHelper.getObjectFromMap(mp, ConnectRequest.class);
					if (response.getProposalId().equals(proposalId)) {
						Date inPrincipleDate = response.getModifiedDate();
						map.put("dateOfInPrincipalApproval", !CommonUtils.isObjectNullOrEmpty(inPrincipleDate)? CommonUtils.DATE_FORMAT.format(inPrincipleDate) : "-");
					}
				}
			}
		} catch (Exception e2) {
			logger.error(CommonUtils.EXCEPTION,e2);
		}
			
		// Currently Commented  dateOfInPrincipalApproval from
		/**try { 
		//ConnectResponse connectResponse = connectClient.getByAppStageBusinessTypeId(applicationId, ConnectStage.COMPLETE.getId(), com.capitaworld.service.loans.utils.CommonUtils.BusinessType.EXISTING_BUSINESS.getId());
		Date InPrincipleDate=loanApplicationRepository.getModifiedDate(toApplicationId, ConnectStage.COMPLETE.getId(), com.capitaworld.service.loans.utils.CommonUtils.BusinessType.EXISTING_BUSINESS.getId());
		if(!CommonUtils.isObjectNullOrEmpty(InPrincipleDate)) {
			map.put("dateOfInPrincipalApproval",!CommonUtils.isObjectNullOrEmpty(InPrincipleDate)? CommonUtils.DATE_FORMAT.format(InPrincipleDate):"-");
		}
		} catch (Exception e2) {
			logger.error(CommonUtils.EXCEPTION,e2);
		}*/
		
		//GST DATA
		try {
			GSTR1Request gstr1Request = new GSTR1Request();
			gstr1Request.setApplicationId(toApplicationId);
			gstr1Request.setUserId(userId);
			gstr1Request.setGstin(corporateApplicantRequest.getGstIn());
			GstResponse response = gstClient.getCalculations(gstr1Request);
			GstCalculation gstData = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)response.getData(),GstCalculation.class);
			int noOfCustomer = gstData.getNoOfCustomer().intValue();
			map.put("noOfCustomer", noOfCustomer);
			map.put("projectedSales", CommonUtils.convertValueIndianCurrency(gstData.getProjectedSales()));
			map.put("customerConcentration", CommonUtils.convertValue(gstData.getConcentration()));
		}catch(Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		
		try {
			GSTR1Request req= new GSTR1Request();
			req.setApplicationId(toApplicationId);
			req.setUserId(userId);
			req.setGstin(corporateApplicantRequest.getGstIn());	
			CAMGSTData resp =null;
			GstResponse response = gstClient.detailCalculation(req);
			
			DecimalFormat df = new DecimalFormat(".##");
			if (!CommonUtils.isObjectNullOrEmpty(response) && response.getData() != null) {
				for (LinkedHashMap<String, Object> data : (List<LinkedHashMap<String, Object>>) response.getData()) {
					resp = MultipleJSONObjectHelper.getObjectFromMap(data,CAMGSTData.class);
					if(resp.getMomSales() != null) {
                        List<MomSales> momSalesResp1 = resp.getMomSales();
                        List<MomSales> responseMom= new ArrayList<>();
                        
                        for (MomSales sales1 : momSalesResp1) {
                        	StringBuilder str = new StringBuilder(sales1.getMonth());
                        	sales1.setMonth(str.insert(2, '-').toString());
                        	sales1.setValue((String)CommonUtils.convertValueIndianCurrency(Double.valueOf(sales1.getValue())));
                        	sales1.setIsManualEntry(sales1.getIsManualEntry());
                            responseMom.add(sales1);
                        }
                        data.put("monthWiseMomSales", responseMom);
                    }
					Double totalSales =0.0d;
					if(resp.getMomSales() != null) {
						List<MomSales> momSalesResp = resp.getMomSales();
						for (MomSales sales : momSalesResp) {
							
							totalSales += Double.valueOf(CommonUtils.convertStringCurrencyToDouble(sales.getValue()));
						}
						map.put("totalMomSales", df.format(totalSales));
//					resp.setTotalMomSales(totalSales);
					}
				}
				
				
				map.put("gstDetailedResp", (List<LinkedHashMap<String, Object>>) response.getData());
			}else {
				logger.info("Gst Data Null for==>"+applicationId);
			}
		
		}catch(Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		
		PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository.getByApplicationAndUserId(toApplicationId, userId);
		if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)) {
			map.put("loanAmtApplied", primaryCorporateDetail.getLoanAmount()!= null ? CommonUtils.convertValueIndianCurrency(primaryCorporateDetail.getLoanAmount()) : 0);
			map.put("comercialOpDate",!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCommercialOperationDate())? CommonUtils.DATE_FORMAT.format(primaryCorporateDetail.getCommercialOperationDate()):"-");
			map.put("factoryPremise", !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getFactoryPremise())? StringEscapeUtils.escapeXml(FactoryPremiseMst.getById(primaryCorporateDetail.getFactoryPremise()).getValue()) : "-");
			map.put("knowHow", !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getKnowHow())? StringEscapeUtils.escapeXml(KnowHowMst.getById(primaryCorporateDetail.getKnowHow()).getValue()) : "-");
			map.put("competition", !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCompetition())? StringEscapeUtils.escapeXml(CompetitionMst_SBI.getById(primaryCorporateDetail.getCompetition()).getValue()) : "-");
			map.put("productDesc", !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getProductServiceDescription()) ? primaryCorporateDetail.getProductServiceDescription() : null);
		}
		
		//ONE-FORM DATA
		try {
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
			List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = backgroundDetailsService.getDirectorBackgroundDetailList(toApplicationId, userId);
			List<DirectorBackgroundDetailResponseString> directorBackgroundDetailResponseList = new ArrayList<>();
			for (DirectorBackgroundDetailRequest directorBackgroundDetailRequest : directorBackgroundDetailRequestList) {
				DirectorBackgroundDetailResponseString directorBackgroundDetailResponse = new DirectorBackgroundDetailResponseString();
				//directorBackgroundDetailResponse.setAchivements(directorBackgroundDetailRequest.getAchivements());
				directorBackgroundDetailResponse.setAddress(directorBackgroundDetailRequest.getAddress());
				//directorBackgroundDetailResponse.setAge(directorBackgroundDetailRequest.getAge());
				directorBackgroundDetailResponse.setDirectorsName((directorBackgroundDetailRequest.getSalutationId() != null ? Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue() : null )+ " " + directorBackgroundDetailRequest.getDirectorsName());
				if(directorBackgroundDetailRequest.getPanNo() != null) {
					directorBackgroundDetailResponse.setPanNo(directorBackgroundDetailRequest.getPanNo().toUpperCase());
				}
				
				String directorName = "";
				if (directorBackgroundDetailRequest.getSalutationId() != null){
					directorName = Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue();
				}
				directorName += " "+directorBackgroundDetailRequest.getDirectorsName();
				directorBackgroundDetailResponse.setDirectorsName(directorName);
				//directorBackgroundDetailResponse.setQualification(directorBackgroundDetailRequest.getQualification());
				directorBackgroundDetailResponse.setTotalExperience(CommonUtils.convertValueWithoutDecimal(directorBackgroundDetailRequest.getTotalExperience()));
				directorBackgroundDetailResponse.setNetworth(CommonUtils.convertValueIndianCurrency(directorBackgroundDetailRequest.getNetworth()).toString());
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
						cibilRequest.setApplicationId(toApplicationId);

						List<CibilScoreLogRequest> response = cibilClient.getMultipleProviderScoreByApplicationIdAndPan(cibilRequest);
						if(!CommonUtils.isListNullOrEmpty(response)) {
							for(CibilScoreLogRequest cibilScoreLogRequest : response) {
								String score = cibilScoreLogRequest.getActualScore();
								if("000-1".equalsIgnoreCase(cibilScoreLogRequest.getActualScore())) {
									score = "-1";
								}else {
									score = Integer.valueOf(cibilScoreLogRequest.getActualScore()).toString();								
								}
								if(cibilScoreLogRequest.getScoreName() != null && cibilScoreLogRequest.getScoreName().contains("CIBIL")) {
									directorBackgroundDetailResponse.setCibilScore(score);								
								}else if(cibilScoreLogRequest.getScoreName().contains("Experian")) {
									directorBackgroundDetailResponse.setExperianScore(score);								
								}else if(cibilScoreLogRequest.getScoreName().contains("HIGHMARK")) {
									directorBackgroundDetailResponse.setHighmarkScore(score);								
								}
								
							}
						}
					}catch(Exception e) {
						logger.error("Error while getting cibil details : ",e);
					}
				}
				
				Double loanObligation = financialArrangementDetailsService.getTotalOfEmiByApplicationIdAndDirectorId(applicationId , directorBackgroundDetailRequest.getId());
				directorBackgroundDetailResponse.setLoanObligation(!CommonUtils.isObjectNullOrEmpty(loanObligation) ? loanObligation : 0);
				
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
							directorPersonalDetailResponse.setNoOfChildren(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getNoOfChildren()) ? directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getNoOfChildren() : 0 );
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
		
		/* cmr details cibil */
		
		try {
			String cmrScore= cibilClient.getCMRScore(applicationId);
			
			if (cmrScore != null && cmrScore.contains("EXP")) {
				map.put("msmeRankingTitle", "Experian");
			}else if (cmrScore != null && cmrScore.contains("CIBIL")) {
				map.put("msmeRankingTitle", "Cibil");
			}else {
				map.put("msmeRankingTitle", "MSME Ranking");
			}
			map.put("cibilCmrScore", cmrScore != null ? cmrScore : "Not Found");
		} catch (Exception e) {
			
			logger.error("error while getting cmr score : ",e);
		}
			
		//FINANCIAL ARRANGEMENTS
		try {
			List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService.getFinancialArrangementDetailsList(toApplicationId, userId);
            List<FinancialArrangementDetailResponseString> financialArrangementsDetailResponseList = new ArrayList<>();
            for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
            	FinancialArrangementDetailResponseString financialArrangementsDetailResponse = new FinancialArrangementDetailResponseString();
     			//financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
                financialArrangementsDetailResponse.setOutstandingAmount(CommonUtils.convertValueIndianCurrency(financialArrangementsDetailRequest.getOutstandingAmount()).toString());
                financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
                financialArrangementsDetailResponse.setAmount(financialArrangementsDetailRequest.getAmount() != null ? CommonUtils.convertValueIndianCurrency(financialArrangementsDetailRequest.getAmount()).toString() : "-");
                //financialArrangementsDetailResponse.setLenderType(LenderType.getById(financialArrangementsDetailRequest.getLenderType()).getValue());
                financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
                financialArrangementsDetailResponse.setLoanType(financialArrangementsDetailRequest.getLoanType());
                financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
                //financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
                //financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
                financialArrangementsDetailResponse.setLcbgStatus(!CommonUtils.isObjectNullOrEmpty(financialArrangementsDetailRequest.getLcBgStatus()) ? LCBG_Status_SBI.getById(financialArrangementsDetailRequest.getLcBgStatus()).getValue().toString() : "-");
                financialArrangementsDetailResponse.setEmi(financialArrangementsDetailRequest.getEmi() != null ? CommonUtils.convertValueIndianCurrency(financialArrangementsDetailRequest.getEmi()).toString() : "-");
				financialArrangementsDetailResponse.setBuerauOutStandingStr(financialArrangementsDetailRequest.getBureauOutstandingAmount() != null ? CommonUtils.convertValueIndianCurrency(financialArrangementsDetailRequest.getBureauOutstandingAmount()).toString() : "-");
				financialArrangementsDetailResponse.setCollateralAmtStr(financialArrangementsDetailRequest.getCollateralSecurityAmount() != null ? CommonUtils.convertValueIndianCurrency(financialArrangementsDetailRequest.getCollateralSecurityAmount()).toString() : "-");
                financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
             }
             map.put("financialArrangments",!CommonUtils.isListNullOrEmpty(financialArrangementsDetailResponseList) ? financialArrangementsDetailResponseList : " ");
		} catch (Exception e) {
             logger.error("Problem to get Data of Financial Arrangements Details {}", e);
        }
			
		/*get loan obligation of dir*/
		Double loanObligation=financialArrangementDetailsService.getTotalEmiOfAllDirByApplicationId(applicationId);
		map.put("loanObligation", loanObligation != null ? CommonUtils.CurrencyFormat(loanObligation.toString()) : 0);

		try {
			PrimaryCorporateRequest primaryCorporateRequest = primaryCorporateService.get(toApplicationId, userId);
			map.put("loanAmt", applicationProposalMapping.getLoanAmount() != null ? CommonUtils.convertValueIndianCurrency(applicationProposalMapping.getLoanAmount()) : "-");
			map.put("enhancementAmount", !CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getEnhancementAmount()) ? CommonUtils.convertValueIndianCurrency(primaryCorporateRequest.getEnhancementAmount()) : " ");
			//map.put("loanType", !CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getProductId()) ? CommonUtils.LoanType.getType(primaryCorporateRequest.getProductId()).getName() : " ");
			map.put("promotorsContribution", CommonUtils.convertValueIndianCurrency(primaryCorporateRequest.getPromoterContribution()));
			map.put("totalAmtPer", !CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getTotalAmtPercentage()) ? " ("+CommonUtils.convertValue(primaryCorporateRequest.getTotalAmtPercentage())+"%)" : null);
			if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getPurposeOfLoanId())) {
				map.put("purpose", StringEscapeUtils.escapeXml(PurposeOfLoan.getById(primaryCorporateRequest.getPurposeOfLoanId()).getValue()));
			}else {
				map.put("purpose", "");
			}

			if(primaryCorporateRequest.getHaveCollateralSecurity()) {
				map.put("collateralSecurityList", collateralSecurityDetailService.getData(applicationId));
				map.put("amtOfSecurity",!CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getCollateralSecurityAmount()) ? CommonUtils.convertValueIndianCurrency(primaryCorporateRequest.getCollateralSecurityAmount()) : " ");
			}
		}catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		
		//FINANCIALS AND NOTES TO ACCOUNTS
		try {
			//PrimaryCorporateRequest primaryCorporateRequest = primaryCorporateService.get(toApplicationId, userId);
			int currentYear = scoringService.getFinYear(toApplicationId);
			map.put("currentYr",currentYear-1);
			// PENDING
			Long denominationValue = 1l;
			if(applicationProposalMapping.getDenominationId() != null) {
				denominationValue = Denomination.getById(applicationProposalMapping.getDenominationId()).getDigit();
			}
			
			Integer[] years = {currentYear-3, currentYear-2, currentYear-1};
			Map<Integer, Object[]> financials = new TreeMap<Integer, Object[]>(Collections.reverseOrder());
			for(Integer year : years) {
				//Object[] data = calculateFinancials(userId, applicationId, null, denominationValue, year); // PREVIOUS
				Object[] data = calculateFinancials(userId, toApplicationId, null, denominationValue,proposalId, year);//NEW BASED ON PROPOSAL ID
				financials.put(year, data);
			}
			
			calculateRatioAnalysis(financials, toApplicationId);
			map.put("financials", financials);
			Map<Integer, Object[]> projectedFin = new HashMap<Integer, Object[]>(applicationProposalMapping.getTenure().intValue());
			//if(primaryCorporateRequest.getProductId() == 1) { previous
			 if(applicationProposalMapping.getProductId() == 1){  // new
				//projectedFin.put(currentYear , calculateFinancials(userId, applicationId, null, denominationValue, currentYear)); // PREVIOUS
				projectedFin.put(currentYear , calculateFinancials(userId, toApplicationId, null, denominationValue,proposalId, currentYear));// NEW BASED ON PROPOSAL ID
				map.put("tenure", 1);
			}else {
				for(int i=0; i<=applicationProposalMapping.getTenure().intValue();i++) {
					//projectedFin.put(currentYear + i, calculateFinancials(userId, applicationId, null, denominationValue, currentYear + i));// PREVIOUS
					projectedFin.put(currentYear + i, calculateFinancials(userId, toApplicationId, null, denominationValue, proposalId,currentYear + i));// NEW BASED ON PROPOSAL ID
				}
				map.put("tenure", applicationProposalMapping.getTenure().intValue() +1);
			}
			map.put("projectedFinancials", projectedFin);
		}catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		
		//MATCHES RESPONSE
		try {
			MatchRequest matchRequest = new MatchRequest();
			matchRequest.setApplicationId(toApplicationId);
			matchRequest.setProductId(productId);
			MatchDisplayResponse matchResponse= matchEngineClient.displayMatchesOfCorporate(matchRequest);
			map.put("matchesResponse", !CommonUtils.isListNullOrEmpty(matchResponse.getMatchDisplayObjectList()) ? CommonUtils.printFields(matchResponse.getMatchDisplayObjectList(),null) : " ");
		}
		catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		
		//PROPOSAL RESPONSE
		try {
				ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
				proposalMappingRequest.setApplicationId(toApplicationId);
				proposalMappingRequest.setFpProductId(productId);
				ProposalMappingResponse proposalMappingResponse= proposalDetailsClient.getActiveProposalDetails(proposalMappingRequest);
				proposalMappingRequestString = new ProposalMappingRequestString();
				logger.info("============proposalMappingRequestId==>{}",proposalMappingRequestString.getId());
				BeanUtils.copyProperties(proposalMappingResponse.getData(), proposalMappingRequestString);
				map.put("proposalResponse", !CommonUtils.isObjectNullOrEmpty(proposalMappingResponse.getData()) ? proposalMappingResponse.getData() : " ");
				
		}
		catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		
		//SCORING DATA 
		try {
			ScoringRequest scoringRequest = new ScoringRequest();
			scoringRequest.setApplicationId(toApplicationId);
			scoringRequest.setFpProductId(productId);
			ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
			DecimalFormat df = new DecimalFormat(".##");
			ProposalScoreResponse proposalScoreResponse = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)scoringResponse.getDataObject(),ProposalScoreResponse.class);
			map.put("scoringModelName", proposalScoreResponse.getScoringModelName() !=null ? CommonUtils.printFields(proposalScoreResponse.getScoringModelName(), null) : "-");
			if(!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse)) {
				map.put("managementRiskScore",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getManagementRiskScore()) ? proposalScoreResponse.getManagementRiskScore().intValue(): "-");
				map.put("managementRiskMaxTotalScore",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getManagementRiskMaxTotalScore()) ?  proposalScoreResponse.getManagementRiskMaxTotalScore().intValue():"-");
				map.put("managementRiskWeightOfScoring",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getManagementRiskWeightOfScoring()) ? df.format(proposalScoreResponse.getManagementRiskWeightOfScoring()) :"-");
				map.put("managementRiskWeight", !CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getManagementRiskWeight()) ? Math.round(proposalScoreResponse.getManagementRiskWeight()): "-");
				map.put("managementRiskMaxTotalWeight", !CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getManagementRiskMaxTotalWeight()) ? Math.round(proposalScoreResponse.getManagementRiskMaxTotalWeight()): "-");
				
				map.put("financialRiskScore",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getFinancialRiskScore()) ? proposalScoreResponse.getFinancialRiskScore().intValue() : "-");
				map.put("financialRiskMaxTotalScore",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getFinancialRiskMaxTotalScore()) ? proposalScoreResponse.getFinancialRiskMaxTotalScore().intValue():"-");
				map.put("financialRiskWeightOfScoring",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getFinancialRiskWeightOfScoring()) ? df.format(proposalScoreResponse.getFinancialRiskWeightOfScoring()): "-");
				map.put("financialRiskWeight",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getFinancialRiskWeight()) ? Math.round(proposalScoreResponse.getFinancialRiskWeight()): "-");
				map.put("financialRiskMaxTotalWeight",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getFinancialRiskMaxTotalWeight()) ? CommonUtils.convertValueRound(proposalScoreResponse.getFinancialRiskMaxTotalWeight()) : "-");
				
				map.put("businessRiskScore", !CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getBusinessRiskScore()) ? proposalScoreResponse.getBusinessRiskScore().intValue():"-");
				map.put("businessRiskMaxTotalScore",!CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getBusinessRiskMaxTotalScore()) ? proposalScoreResponse.getBusinessRiskMaxTotalScore().intValue():"-");
				map.put("businessRiskWeightOfScoring", !CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getBusinessRiskWeightOfScoring()) ? df.format(proposalScoreResponse.getBusinessRiskWeightOfScoring()):"-");
				map.put("businessRiskWeight", !CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getBusinessRiskWeight()) ? Math.round(proposalScoreResponse.getBusinessRiskWeight()):"-");
				map.put("businessRiskMaxTotalWeight", !CommonUtils.isObjectNullOrEmpty(proposalScoreResponse.getBusinessRiskMaxTotalWeight()) ? CommonUtils.convertValueRound(proposalScoreResponse.getBusinessRiskMaxTotalWeight()):"-");
				
				map.put("totalActualScore", CommonUtils.addNumbers(proposalScoreResponse.getManagementRiskScore(), proposalScoreResponse.getFinancialRiskScore(), proposalScoreResponse.getBusinessRiskScore()).intValue());
				map.put("totalOutOfScore", CommonUtils.addNumbers(proposalScoreResponse.getManagementRiskMaxTotalScore(), proposalScoreResponse.getFinancialRiskMaxTotalScore(), proposalScoreResponse.getBusinessRiskMaxTotalScore()).intValue());
				map.put("totalWeight", df.format(CommonUtils.addNumbers(proposalScoreResponse.getManagementRiskWeightOfScoring(), proposalScoreResponse.getFinancialRiskWeightOfScoring(), proposalScoreResponse.getBusinessRiskWeightOfScoring()).doubleValue()));
				
				if(proposalScoreResponse.getManagementRiskWeight()!=null && proposalScoreResponse.getFinancialRiskWeight() != null && proposalScoreResponse.getBusinessRiskWeight()!= null){
				map.put("totalRiskWeight", Math.addExact(Math.addExact(Math.round(proposalScoreResponse.getManagementRiskWeight()), Math.round(proposalScoreResponse.getFinancialRiskWeight())), Math.round(proposalScoreResponse.getBusinessRiskWeight())));
				}
				map.put("totalRiskMaxWeight", Math.round(CommonUtils.addNumbers(proposalScoreResponse.getManagementRiskMaxTotalWeight(), proposalScoreResponse.getFinancialRiskMaxTotalWeight(), proposalScoreResponse.getBusinessRiskMaxTotalWeight()).intValue()));
				
				map.put("interpretation", StringEscapeUtils.escapeXml(proposalScoreResponse.getInterpretation()));
				map.put("proposalScoreResp", proposalScoreResponse);
			}
			List<Map<String, Object>> proposalScoreDetailResponseList = (List<Map<String, Object>>) scoringResponse.getDataList();
			int manufacturing =0;
			int business = 0;
			int financial = 0;
			for(int i=0;i<proposalScoreDetailResponseList.size();i++)
			{
				ProposalScoreDetailResponse proposalScoreDetailResponse = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)proposalScoreDetailResponseList.get(i),ProposalScoreDetailResponse.class);
				switch (proposalScoreDetailResponse.getParameterName()) {
				case ScoreParameter.COMBINED_NETWORTH:
					map.put("combinedNetworthActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("combinedNetworthScoreActual",!CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("combinedNetworthScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue() : "-");
					continue;
				case ScoreParameter.CUSTOMER_ASSOCIATE_CONCERN:
					map.put("customerAssociateConcernActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("customerAssociateConcernScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("customerAssociateConcernScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue() : "-");
					manufacturing++;
					continue;
				case ScoreParameter.CIBIL_TRANSUNION_SCORE:
					map.put("cibilTransunionScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("cibilTransunionScoreScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("cibilTransunionScoreScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue() : "-");
					manufacturing++;
					continue;
				case ScoreParameter.EXPERIENCE_IN_THE_BUSINESS:
					map.put("experienceInBusinessActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("experienceInBusinessScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("experienceInBusinessScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue() : "-");
					manufacturing++;
					continue;
				case ScoreParameter.DEBT_EQUITY_RATIO:
					map.put("debtEquityRatioActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("debtEquityRatioScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("debtEquityRatioScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue() : "-");
					continue;
				case ScoreParameter.TOL_TNW:
					map.put("tolTnwActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("tolTnwScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("tolTnwScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue() : "-");
					financial++;
					continue;
				case ScoreParameter.AVERAGE_CURRENT_RATIO:
					map.put("avgCurrentRatioActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("avgCurrentRatioScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("avgCurrentRatioScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue() : "-");
					financial++;
					continue;
				case ScoreParameter.WORKING_CAPITAL_CYCLE:
					map.put("wcCycleActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("wcCycleScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("wcCycleScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue() : "-");
					financial++;
					continue;
				case ScoreParameter.AVERAGE_ANNUAL_GROWTH_GROSS_CASH:
					map.put("avgAnnualgrowthGrossActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("avgAnnualgrowthGrossScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("avgAnnualgrowthGrossScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					financial++;
					continue;
				case ScoreParameter.AVERAGE_ANNUAL_GROWTH_NET_SALE:
					map.put("avgAnnualgrowthNetSaleActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("avgAnnualgrowthNetSaleScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("avgAnnualgrowthNetSaleScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					financial++;
					continue;
				case ScoreParameter.AVERAGE_EBIDTA:
					map.put("avgEbidtaActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("avgEbidtaScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("avgEbidtaScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					financial++;
					continue;
				case ScoreParameter.AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS:
					map.put("avgAnnualGrossCashActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("avgAnnualGrossCashScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("avgAnnualGrossCashScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					financial++;
					continue;
				case ScoreParameter.AVERAGE_INTEREST_COV_RATIO:
					map.put("avgIntCovRatioActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("avgIntCovRatioScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("avgIntCovRatioScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					financial++;
					continue;
				case ScoreParameter.NO_OF_CUSTOMER:
					map.put("noOfCustomerActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("noOfCustomerScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("noOfCustomerScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					continue;
				case ScoreParameter.CONCENTRATION_CUSTOMER:
					map.put("concentrationCustomerActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("concentrationCustomerScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("concentrationCustomerScoreOutOf",!CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;
				case ScoreParameter.CREDIT_SUMMATION:
					map.put("creditSummationActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("creditSummationScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("creditSummationScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;
				case ScoreParameter.AGE:
					map.put("ageActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("ageScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("ageScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					manufacturing++;
					continue;
				case ScoreParameter.NO_OF_CHILDREN:
					map.put("noOfChildrenActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("noOfChildrenScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("noOfChildrenScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					manufacturing++;
					continue;
				case ScoreParameter.OWNING_HOUSE:
					map.put("owningHouseActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("owningHouseScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("owningHouseScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					manufacturing++;
					continue;
				case ScoreParameter.ACADEMIC_QUALIFICATION:
					map.put("acadamicQualificationActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("acadamicQualificationScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("acadamicQualificationScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					manufacturing++;
					continue;
				case ScoreParameter.EXPERIENCE_IN_THE_LINE_OF_TRADE:
					map.put("experienceInTradeActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("experienceInTradeScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("experienceInTradeScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					manufacturing++;
					continue;
				case ScoreParameter.SPOUSE_DETAILS:
					map.put("spouseDetailsActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("spouseDetailsScoreActual",!CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("spouseDetailsScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					manufacturing++;
					continue;
				case ScoreParameter.ASSESSED_FOR_INCOME_TAX:
					map.put("assessedITActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("assessedITScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("assessedITScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					manufacturing++;
					continue;
				case ScoreParameter.HAVE_LIFE_INSURANCE_POLICY:
					map.put("lifeInsurancePolicyActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("lifeInsurancePolicyScoreActual",!CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("lifeInsurancePolicyScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					manufacturing++;
					continue;
				case ScoreParameter.REPAYMENT_PERIOD:
					map.put("repaymentPeriodActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("repaymentPeriodScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("repaymentPeriodScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					financial++;
					continue;
				case ScoreParameter.CONTINUOUS_NET_PROFIT:
					map.put("continuousNetProfitActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("continuousNetProfitScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("continuousNetProfitScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					financial++;
					continue;
				/* kotak changes */
				case ScoreParameter.COLLATERAL_COVERAGE:
					map.put("collateraltActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("collateralScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("collateralScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					financial++;
					continue;
				case ScoreParameter.DEBT_SERVICE_COVERAGE_RATIO:
					map.put("dscrActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("dscrScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("dscrScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					financial++;
					continue;
				case ScoreParameter.PAST_YEAR_TURNOVER:
					map.put("pastYearActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("pastYearScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("pastYearScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					financial++;
					continue;
				case ScoreParameter.DEBT_EBITDA:
					map.put("debtEbitdaActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("debtEbitdaScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("debtEbitdaScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					financial++;
					continue;	
				case ScoreParameter.TURNOVER_ATNW:
					map.put("turnActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("turnOverScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("turnOverScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					financial++;
					continue;	
				case ScoreParameter.QUALITY_OF_RECEIVABLES:
					map.put("qualityReceivablesActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("qualityReceivablesScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("qualityReceivablesScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;
				case ScoreParameter.QUALITY_OF_FINISHED_GOODS_INVENTORY:
					map.put("qualityFinishedGoodsActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("qualityFinishedGoodsScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("qualityFinishedGoodsScoreOutOf",!CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;
				case ScoreParameter.KNOW_HOW:
					map.put("knowHowActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("knowHowScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("knowHowScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;
				case ScoreParameter.LINE_OF_ACTIVITY:
					map.put("lineOfActivityActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("lineOfActivityScoreActual",!CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("lineOfActivityScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;
				case ScoreParameter.COMPETITION:
					map.put("competitionActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("competitionScoreActual",!CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("competitionScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;
				case ScoreParameter.FACTORY_PREMISES:
					map.put("factoryPremisesActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("factoryPremisesScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("factoryPremisesScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;
				case ScoreParameter.SALES_SHOW_A_RISING_TREND:
					map.put("salesShowActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("salesShowScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("salesShowScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;
				case ScoreParameter.YEARS_IN_BUSINESS:
					map.put("yearsInBusinessActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("yearsInBusinessScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("yearsInBusinessScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;
					/* kotak changes */
				case ScoreParameter.UTILISATION_PERCENTAGE:
					map.put("utilisationPerActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("utilisationPerScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("utilisationPerScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;
				case ScoreParameter.TURN_OVER_TO_LIMIT_RATIO:
					map.put("turnOverPerActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("turnOverScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("turnOverScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;	
				case ScoreParameter.NO_OF_CHEQUES_BOUNCED:
					map.put("nocbActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("nocbScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("nocbScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;
				case ScoreParameter.NO_OF_CHEQUES_BOUNCED_LAST_SIX_MONTH:
					map.put("nocblsmActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getParameterOption()) ? StringEscapeUtils.escapeXml(proposalScoreDetailResponse.getParameterOption()):"-");
					map.put("nocblsmScoreActual", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getObtainedScore()) ? proposalScoreDetailResponse.getObtainedScore().intValue():"-");
					map.put("nocbScoreOutOf", !CommonUtils.isObjectNullOrEmpty(proposalScoreDetailResponse.getMaxScore()) ? proposalScoreDetailResponse.getMaxScore().intValue():"-");
					business++;
					continue;	
				default:
					break;
				}
			}
			map.put("manufacturing", manufacturing+1);
			map.put("financial", financial+1);
			map.put("business", business+1);
		}
			catch (Exception e) {
				logger.error("Error while getting scoring data : ",e);
			}
		
		//NAME AS PER ITR
		try{
			ITRConnectionResponse itrResponse = itrClient.getITRBasicDetails(toApplicationId);
			logger.info("ITR RESPONSE===========>{}",itrResponse);
			map.put("nameAsPerItr", CommonUtils.printFields(itrResponse.getData(),null));
		}catch(Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		
		//CGTMSE DATA
		try {
			CGTMSEDataResponse cgtmseDataResponse = thirdPartyClient.getCalulation(toApplicationId,productId);
			if(!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse)) {
				map.put("cgtmseData", CommonUtils.printFields(cgtmseDataResponse,null));
				if(cgtmseDataResponse.getNatureOfEntity().equals("Manufacturer")) {
					map.put("industryType", "MANUFACTURING");
				}else {
					map.put("industryType", " ");
				}
				map.put("maxCgtmseCoverageAmount", CommonUtils.convertValueIndianCurrency(cgtmseDataResponse.getMaxCgtmseCoverageAmount()));
				map.put("identityAmount", CommonUtils.convertValueIndianCurrency(cgtmseDataResponse.getIdentityAmount()));
				map.put("gutAmt", CommonUtils.convertValueIndianCurrency(cgtmseDataResponse.getGutAmt()));
				map.put("loanAmount", CommonUtils.convertValueIndianCurrency(cgtmseDataResponse.getLoanAmount()));
				map.put("cgtmseCoverageAmount", CommonUtils.convertValueIndianCurrency(cgtmseDataResponse.getCgtmseCoverageAmount()));
				map.put("amountOfColleteral", CommonUtils.convertValueIndianCurrency(cgtmseDataResponse.getAmountOfColleteral()));
				map.put("totalColleteralAmount", CommonUtils.convertValueIndianCurrency(cgtmseDataResponse.getTotalColleteralAmount()));
				map.put("gurAmtCarld", CommonUtils.convertValueIndianCurrency(cgtmseDataResponse.getGurAmtCarld()));
				map.put("colleteralCoverage", CommonUtils.convertValue(cgtmseDataResponse.getColleteralCoverage()));
				map.put("percTerms", CommonUtils.convertValue(cgtmseDataResponse.getPercTerms()));
				map.put("assetAqu", CommonUtils.convertValueIndianCurrency(cgtmseDataResponse.getAssetAqusition()));
				if(!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getCgtmseResponse()) && !CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getCgtmseResponse().getDetails())) {
					map.put("cgtmseBankWise", CommonUtils.printFields(cgtmseDataResponse.getCgtmseResponse().getDetails(),null));
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		
		//ELIGIBILITY DATA (ASSESSMENT TO LIMITS)
		try {
			//PrimaryCorporateRequest primaryCorporateRequest = primaryCorporateService.get(applicationProposalMapping.getApplicationId(), userId); // previous
			//primaryCorporateRequest.getProductId(); // previous
			int loanId = applicationProposalMapping.getProductId(); // new
			switch (loanId) {
				case 1:
				WorkingCapitalParameter workingCapitalPara = workingCapitalParameterRepository.getByID(productId);
					if (workingCapitalPara.getAssessmentMethodId() != null) {
						Long assessmentId = workingCapitalPara.getAssessmentMethodId().longValue();
						map.put(ASSESSMENT_ID, assessmentId);
					}else {
						map.put(ASSESSMENT_ID, " ");
					}
					break;
				case 2:
				TermLoanParameter termLoanParameter = termLoanParameterRepository.getById(productId);
					if (termLoanParameter.getAssessmentMethodId() != null) {
						Long assessmentId = termLoanParameter.getAssessmentMethodId().longValue();
						map.put(ASSESSMENT_ID, assessmentId);
					} else {
						map.put(ASSESSMENT_ID, " ");
					}
					break;
				case 16:
				WcTlParameter wctlPara = wcTlLoanParameterRepository.getById(productId);
					if (wctlPara.getAssessmentMethodId() != null) {
						Long assessmentId = wctlPara.getAssessmentMethodId().longValue();
						map.put(ASSESSMENT_ID, assessmentId);
					}else {
						map.put(ASSESSMENT_ID, " ");
					}
					break;
				default:
					logger.info("Invalid Loan Id");
					break;
				}
		} catch (Exception e2) {
			logger.error(CommonUtils.EXCEPTION,e2);
		}
		
		try{
			EligibililityRequest eligibilityReq=new EligibililityRequest();
			eligibilityReq.setApplicationId(toApplicationId);
			eligibilityReq.setFpProductMappingId(productId);
			EligibilityResponse eligibilityResp= eligibilityClient.corporateLoanData(eligibilityReq);
			
			if(!CommonUtils.isObjectListNull(eligibilityResp.getData())){
				CLEligibilityRequest req= MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)eligibilityResp.getData(), CLEligibilityRequest.class);
				
				map.put("elProSales", req.getProjectedSales() != null ? CommonUtils.convertValueIndianCurrency(req.getProjectedSales())  : "-");
				map.put("defaultHisSales", req.getDefaultHistoricSales() != null ? CommonUtils.convertValueIndianCurrency(req.getDefaultHistoricSales())  : "-");
				map.put("assLimits",CommonUtils.convertToDoubleForXmlIndianCurr(req, new HashMap<>()));
			}
		}catch (Exception e) {
			logger.error("Error while getting Eligibility data : ",e);
		}

		/* eligibility financialCalculation year */
		map.put("eligibilityFinancialYear",CommonUtils.getFinancialYear());

		//MCA DATA
		try {
			// CURRENTLY PENDING DATA-ID NOT EXISTS IN application_proposal_mapping-->applicationProposalMapping
			String companyId = loanApplicationMaster.getMcaCompanyId();
			
			if(companyId != null) {
				McaResponse mcaStatusResponse = mcaClient.mcaStatusCheck(String.valueOf(toApplicationId), companyId);
				if (mcaStatusResponse!= null && mcaStatusResponse.getData()!= null && mcaStatusResponse.getData().equals(true)) {
					McaResponse mcaResponse = mcaClient.getCompanyDetailedData(companyId);
					if(!CommonUtils.isObjectNullOrEmpty(mcaResponse.getData())) {
						map.put("mcaData", CommonUtils.printFields(mcaResponse.getData(),null));
					}
					McaResponse mcaFinancialAndDetailsRes=mcaClient.getCompanyFinancialCalcAndDetails(toApplicationId, companyId);
					if(mcaFinancialAndDetailsRes.getData()!=null){
						map.put("financialDetailResp", mcaFinancialAndDetailsRes.getData());
					}
				}
				map.put("mcaCheckStatus",mcaStatusResponse.getData()!= null ?  mcaStatusResponse.getData() : null);
				
			}else {
				map.put("mcaNotApplicable",true);
			}
		}catch(Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		
		//HUNTER API ANALYSIS
		
		
		try {
			UserResponse campaignUser=usersClient.isExists(userId,null);
			map.put("isCapaignUser", campaignUser != null && campaignUser.getData() != null ? campaignUser.getData() : "");
			if(campaignUser!= null && campaignUser.getData().equals(false)) {
				AnalyticsResponse hunterResp =fraudAnalyticsClient.getRuleAnalysisData(applicationId);
				if(!CommonUtils.isObjectListNull(hunterResp,hunterResp.getData())) {
					map.put("hunterResponse",  CommonUtils.printFields(hunterResp.getData(),null));
				}	
			}else {
				logger.info("user is campaign so hunter is skipped");
			}
			
		} catch (Exception e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
		}
		
		//ITR (CHECK IF UPLOADED USING XML OR ONLINE)
		try {
			ITRConnectionResponse itrConnectionResponse= itrClient.getIsUploadAndYearDetails(toApplicationId);
			if(!CommonUtils.isObjectNullOrEmpty(itrConnectionResponse)) {
				map.put("checkItr", itrConnectionResponse.getData());
			}
		}catch(Exception e) {
			logger.error("Error while getting ITR data : ",e);
		}
		
		//gstRelatedParty Data Fetch
		try {
			List<GstRelatedPartyRequest> gstRelatedPartyRequests = loanApplicationService.getGstRelatedPartyDetails(applicationId);
			map.put("gstPartyRelatedData", !CommonUtils.isObjectListNull(gstRelatedPartyRequests) ? gstRelatedPartyRequests : null);
		}catch (Exception e) {
			logger.error("Error/Exception while fetching list of gst Related Party List Data of APplicationId==>{}  ... Error==>{}",applicationId ,e);
		}


		//PERFIOS API DATA (BANK STATEMENT ANALYSIS)
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(toApplicationId);
		reportRequest.setUserId(userId);

		List<Data> datas = new ArrayList<>();
		//List<Object> bankStatement = new ArrayList<Object>();
		List<Object> monthlyDetails = new ArrayList<Object>();
		List<Object> top5FundReceived = new ArrayList<Object>();
		List<Object> top5FundTransfered = new ArrayList<Object>();
		List<Object> bouncedChequeList = new ArrayList<Object>();
		List<Object> customerInfo = new ArrayList<Object>();
		List<Object> summaryInfo = new ArrayList<Object>();

		try {
			AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
			if(analyzerResponse.getData()!=null){
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

				//map.put("bankStatement", bankStatement);
				map.put("monthlyDetails", monthlyDetails);
				map.put("top5FundReceived", top5FundReceived);
				map.put("top5FundTransfered", top5FundTransfered);
				map.put("bouncedChequeList", bouncedChequeList);
				map.put("customerInfo", !CommonUtils.isObjectListNull(customerInfo) ? customerInfo : null);
				map.put("summaryInfo", summaryInfo);
				map.put("bankStatementAnalysis", CommonUtils.printFields(datas, null));

			}
		 }
		} catch (Exception e) {
			logger.error("Error while getting perfios data : ",e);
		}
		
		//		GST Comparision by Maaz
		try{
			FinancialInputRequest finaForCam = finaForCam(applicationId,proposalId);
			map.put("gstComparision", corporatePrimaryViewService.gstVsItrVsBsComparision(applicationId, finaForCam));
		}catch (Exception e) {
			logger.error("error in getting gst comparision data : {}",e);
		}

		/**ReportRequest reportRequest = new ReportRequest();
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
					List<Object> customerInfo = new ArrayList<Object>();
					for(int i =0; i<hashMap.size(); i++) {
						customerInfo.add(!CommonUtils.isObjectNullOrEmpty(data.getCustomerInfo()) ? CommonUtils.printFields(data.getCustomerInfo(),null) : " ");
					}
					map.put("customerInfo" , customerInfo);
				}
			}
		}catch (Exception e) {
			logger.error("Error while getting perfios data : ",e);
		}*/



/**********************************************FINAL DETAILS*****************************************************/
		
		if(isFinalView && (applicationProposalMapping.getIsMcqSkipped() == null || applicationProposalMapping.getIsMcqSkipped() == false)) {
			
			//FITCH DATA
			try {
				//RatingResponse ratingResponse = (RatingResponse) irrService.calculateIrrRating(applicationId, userId).getBody().getData();

				//RatingResponse ratingResponse = (RatingResponse) irrService.calculateIrrRating(proposalMappingRequestString.getId(), userId, proposalMappingRequestString.getId()).getBody().getData();// PREVIOUS
				RatingResponse ratingResponse = (RatingResponse) irrService.calculateIrrRating(toApplicationId, userId, applicationProposalMapping.getProposalId()).getBody().getData(); //NEW BASED ON PROPOSAL MAPPING ID
				if(!CommonUtils.isObjectNullOrEmpty(ratingResponse) && !CommonUtils.isObjectNullOrEmpty(ratingResponse.getBusinessTypeId())) {
					if(BusinessType.MANUFACTURING == ratingResponse.getBusinessTypeId()){
						FitchOutputManu fitchOutputManu= MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)ratingResponse.getData(),FitchOutputManu.class);
						if(!CommonUtils.isObjectNullOrEmpty(fitchOutputManu)) {
							map.put(FITCH_RESPONSE,CommonUtils.convertToDoubleForXml(fitchOutputManu,null));
							map.put("financialClosure",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getFinancialClosureScore()) ? fitchOutputManu.getFinancialClosureScore() : "NA");
							map.put("intraCompany",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getIntraCompanyScore()) ? fitchOutputManu.getIntraCompanyScore() : "NA");
							map.put("statusProjectClearance",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getStatusProjectClearanceScore()) ? fitchOutputManu.getStatusProjectClearanceScore() : "NA");
							map.put("financialStrength",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getFinancialStrengthScore()) ? fitchOutputManu.getFinancialStrengthScore() : "NA");
							map.put("infrastructureAvailability",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getInfrastructureAvailabilityScore()) ? fitchOutputManu.getInfrastructureAvailabilityScore() : "NA");
							map.put("constructionContract",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getConstructionContractScore()) ? fitchOutputManu.getConstructionContractScore() : "NA");
							map.put("forexRisk",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getForexRiskScore()) ? fitchOutputManu.getForexRiskScore() : "NA");
							map.put("designTechnology",!CommonUtils.isObjectNullOrEmpty(fitchOutputManu.getDesignTechnologyRiskScore()) ? fitchOutputManu.getDesignTechnologyRiskScore() : "NA");
							map.put(FITCH_TITLE,"Manufacturing");
						}
					}
					
					if(BusinessType.TRADING == ratingResponse.getBusinessTypeId()){
						FitchOutputTrad fitchOutputTrad = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)ratingResponse.getData(),FitchOutputTrad.class);
						if(!CommonUtils.isObjectNullOrEmpty(fitchOutputTrad)) {
							map.put(FITCH_RESPONSE,CommonUtils.convertToDoubleForXml(fitchOutputTrad,null));
							map.put(FITCH_TITLE,"Trading");
						}
					}
					
					if(BusinessType.SERVICE == ratingResponse.getBusinessTypeId()){
						FitchOutputServ fitchOutputServ = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)ratingResponse.getData(),FitchOutputServ.class);
						if(!CommonUtils.isObjectNullOrEmpty(fitchOutputServ)) {
							map.put(FITCH_RESPONSE,CommonUtils.convertToDoubleForXml(fitchOutputServ,null));
							map.put(FITCH_TITLE,"Service");
						}
					}
				}
			}
			catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			
			//OWNERSHIP DETAILS :- 
			try {
				List<OwnershipDetailRequest> ownershipDetailRequestsList = ownershipDetailsService.getOwnershipDetailList(toApplicationId, userId,applicationProposalMapping.getProposalId());
				List<OwnershipDetailResponse> ownershipDetailResponseList = new ArrayList<>();
				for (OwnershipDetailRequest ownershipDetailRequest : ownershipDetailRequestsList) {
					OwnershipDetailResponse ownershipDetailResponse = new OwnershipDetailResponse();
					ownershipDetailResponse.setRemarks(ownershipDetailRequest.getRemarks());
					ownershipDetailResponse.setStackPercentage(ownershipDetailRequest.getStackPercentage());
					ownershipDetailResponse.setShareHoldingCategory(ShareHoldingCategory.getById(ownershipDetailRequest.getShareHoldingCategoryId()).getValue());
					ownershipDetailResponseList.add(ownershipDetailResponse);
				}
				map.put("ownership", CommonUtils.printFields(ownershipDetailResponseList,null));

			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
		    }
			
			//PROMOTOR BACKGROUND DETAILS
			try {
			/*	List<PromotorBackgroundDetailRequest> promotorBackgroundDetailRequestList = promotorBackgroundDetailsService.getPromotorBackgroundDetailList(toApplicationId, userId);*/
				List<PromotorBackgroundDetailRequest> promotorBackgroundDetailRequestList = promotorBackgroundDetailsService.getPromotorBackgroundDetailListByProposalId(toApplicationId, applicationProposalMapping.getProposalId(),userId);
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
					promotorBackgroundDetailResponse.setDin(!CommonUtils.isObjectNullOrEmpty(promotorBackgroundDetailRequest.getDin())?decim.format(promotorBackgroundDetailRequest.getDin()).toString() : "");
					promotorBackgroundDetailResponse.setTotalExperience(CommonUtils.convertValueWithoutDecimal(promotorBackgroundDetailRequest.getTotalExperience()));
					promotorBackgroundDetailResponse.setNetworth(CommonUtils.convertValue(promotorBackgroundDetailRequest.getNetworth()));
					promotorBackgroundDetailResponse.setAppointmentDate(promotorBackgroundDetailRequest.getAppointmentDate() != null ? CommonUtils.DATE_FORMAT.format(promotorBackgroundDetailRequest.getAppointmentDate()) : null);
					promotorBackgroundDetailResponse.setRelationshipType((promotorBackgroundDetailRequest.getRelationshipType() != null ? DirectorRelationshipType.getById(promotorBackgroundDetailRequest.getRelationshipType()).getValue() : " " ));
					promotorBackgroundDetailResponse.setDesignation(promotorBackgroundDetailRequest.getDesignation());
					promotorBackgroundDetailResponse.setMobile(promotorBackgroundDetailRequest.getMobile());
					promotorBackgroundDetailResponseList.add(promotorBackgroundDetailResponse);
				}
				map.put("promotorsbckgrnd", CommonUtils.printFields(promotorBackgroundDetailResponseList,null));
			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			
			//ASSOCIATE ENTITY
			try {
				/*map.put("associatedConcerns",CommonUtils.printFields(associatedConcernDetailService.getAssociatedConcernsDetailList(toApplicationId, userId),null));*/
				map.put("associatedConcerns",CommonUtils.printFields(associatedConcernDetailService.getAssociatedConcernsDetailListByProposalId(applicationProposalMapping.getProposalId(), userId),null));
			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			
			//DETAILS OF GUARANTER
			try {
				/*map.put("guaDetails", CommonUtils.printFields(guarantorsCorporateDetailService.getGuarantorsCorporateDetailList(toApplicationId, userId),null));*/
				map.put("guaDetails", CommonUtils.printFields(guarantorsCorporateDetailService.getGuarantorsCorporateDetailListByProposalId(applicationProposalMapping.getProposalId(), userId),null));
			} catch (Exception e) {
					logger.error("Problem to get Data of Details of Guarantor {}", e);
			}
			
		    //MONTHLY TURNOVER
			try {
				map.put("monthlyTurnOver", CommonUtils.printFields(monthlyTurnoverDetailService.getMonthlyTurnoverDetailListByProposalId(toApplicationId, userId,applicationProposalMapping.getProposalId()),null));
			} catch (Exception e) {
				logger.error("Problem to get Data of Monthly Turnover {}", e);
			}
			
			//COST ESTIMATES
			try {
/*				List<TotalCostOfProjectRequest> costOfProjectsList = costOfProjectService.getCostOfProjectDetailList(toApplicationId, userId);*/
				List<TotalCostOfProjectRequest> costOfProjectsList = costOfProjectService.getCostOfProjectDetailListByProposalId(applicationProposalMapping.getProposalId(), userId);
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
				/*List<FinanceMeansDetailRequest> financeMeansDetailRequestsList = financeMeansDetailsService.getMeansOfFinanceList(toApplicationId, userId);*/
				List<FinanceMeansDetailRequest> financeMeansDetailRequestsList = financeMeansDetailsService.getMeansOfFinanceListByProposalId(applicationProposalMapping.getProposalId(), userId);
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
				/*map.put("collateralSecurity", CommonUtils.printFields(securityCorporateDetailsService.getsecurityCorporateDetailsList(toApplicationId, userId),null));*/
				map.put("collateralSecurity", CommonUtils.convertToDoubleForXmlIndianCurr(securityCorporateDetailsService.getSecurityCorporateDetailsListFromProposalId(applicationProposalMapping.getProposalId(), userId),null));
			} catch (Exception e) {
				logger.error("Problem to get Data of Security Details {}", e);
			}
		}
		
		try {
			/*map.put("proposedProduct",CommonUtils.printFields(proposedProductDetailsService.getProposedProductDetailList(applicationProposalMapping.getApplicationId(), userId),null));
			map.put("existingProduct",CommonUtils.printFields(existingProductDetailsService.getExistingProductDetailList(applicationProposalMapping.getApplicationId(), userId),null));
			map.put("achievementDetails",CommonUtils.printFields(achievmentDetailsService.getAchievementDetailList(applicationProposalMapping.getApplicationId(), userId),null));*/
						
			map.put("proposedProduct",CommonUtils.printFields(proposedProductDetailsService.getProposedProductDetailListFromProposalId(applicationProposalMapping.getProposalId(), userId),null));
			map.put("existingProduct",CommonUtils.printFields(existingProductDetailsService.getExistingProductDetailListByProposalId(applicationProposalMapping.getProposalId(), userId),null));
			map.put("achievementDetails",CommonUtils.printFields(achievmentDetailsService.getAchievementDetailListForMultipleBank(applicationProposalMapping.getProposalId()),null));
		}catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		
		//SHARE PRICE
		try {
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.getByApplicationAndProposalIdAndUserId(userId, toApplicationId,applicationProposalMapping.getProposalId());
			if(corporateApplicantDetail != null) {
				map.put("sharePriceFace", CommonUtils.convertValue(corporateApplicantDetail.getSharePriceFace()));
				map.put("sharePriceMarket", CommonUtils.convertValue(corporateApplicantDetail.getSharePriceMarket()));
				map.put("castCategory", corporateApplicantDetail.getCastCategory());
			}
		}catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return map;
	}
	
	/*@Override
	public Map<String, Object> getBankStatementAnalysisReport(Long applicationId, Long productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long userId = loanApplicationRepository.getUserIdByApplicationId(applicationId);
		CorporateApplicantRequest corporateApplicantRequest =corporateApplicantService.getCorporateApplicant(applicationId);
		try {
			map.put("orgName", CommonUtils.printFields(corporateApplicantRequest.getOrganisationName(),null));
		} catch (Exception e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
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
							List<Object> bankStatement = new ArrayList<Object>();
							List<Object> monthlyDetails = new ArrayList<Object>();
							List<Object> top5FundReceived = new ArrayList<Object>();
							List<Object> top5FundTransfered = new ArrayList<Object>();
							List<Object> bouncedChequeList = new ArrayList<Object>();
							List<Object> customerInfo = new ArrayList<Object>();
							List<Object> summaryInfo = new ArrayList<Object>();
							for(int i =0; i<hashMap.size(); i++) {
								bankStatement.add(!CommonUtils.isObjectNullOrEmpty(data.getXns()) ? CommonUtils.printFields(data.getXns().getXn(),null) : " ");
								monthlyDetails.add(!CommonUtils.isObjectNullOrEmpty(data.getMonthlyDetailList()) ? CommonUtils.printFields(data.getMonthlyDetailList(),null) : "");
								top5FundReceived.add(!CommonUtils.isObjectNullOrEmpty(data.getTop5FundReceivedList().getItem()) ? CommonUtils.printFields(data.getTop5FundReceivedList().getItem(),null) : "");
								top5FundTransfered.add(!CommonUtils.isObjectNullOrEmpty(data.getTop5FundTransferedList().getItem()) ? CommonUtils.printFields(data.getTop5FundTransferedList().getItem(),null) : "");
								bouncedChequeList.add(!CommonUtils.isObjectNullOrEmpty(data.getBouncedOrPenalXnList()) ? CommonUtils.printFields(data.getBouncedOrPenalXnList().getBouncedOrPenalXns(),null) : " ");
								customerInfo.add(!CommonUtils.isObjectNullOrEmpty(data.getCustomerInfo()) ? CommonUtils.printFields(data.getCustomerInfo(),null) : " ");
								summaryInfo.add(!CommonUtils.isObjectNullOrEmpty(data.getSummaryInfo()) ?CommonUtils.printFields(data.getSummaryInfo(),null) : " ");
							}
							map.put("bankStatement" , bankStatement);
							map.put("monthlyDetails" , monthlyDetails);
							map.put("top5FundReceived" , top5FundReceived);
							map.put("top5FundTransfered" , top5FundTransfered);
							map.put("bouncedChequeList" , bouncedChequeList);
							map.put("customerInfo" , customerInfo);
							map.put("summaryInfo" , summaryInfo);
							map.put("bankStatementAnalysis", CommonUtils.printFields(datas,null));
						}
					}
				}catch (Exception e) {
					logger.error("Error while getting perfios data : ",e);
				}

		return map;
	}*/

	@Override
	public Map<String, Object> getBankStatementAnalysisReport(Long applicationId, Long productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long userId = loanApplicationRepository.getUserIdByApplicationId(applicationId);
		CorporateApplicantRequest corporateApplicantRequest = corporateApplicantService.getCorporateApplicant(applicationId);
		try {
			if(corporateApplicantRequest != null) {
				map.put("orgName", CommonUtils.printFields(corporateApplicantRequest.getOrganisationName(), null));
			}
		} catch (Exception e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
		}
		
		// PERFIOS API DATA (BANK STATEMENT ANALYSIS)
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
					bankStatement.add(!CommonUtils.isObjectNullOrEmpty(data.getXns()) ? CommonUtils.printFields(data.getXns().getXn(),null) : " ");
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
				map.put("bankStatement", bankStatement);
				map.put("monthlyDetails", monthlyDetails);
				map.put("top5FundReceived", top5FundReceived);
				map.put("top5FundTransfered", top5FundTransfered);
				map.put("bouncedChequeList", bouncedChequeList);
				map.put("customerInfo", customerInfo);
				map.put("summaryInfo", summaryInfo);
				map.put("bankStatementAnalysis", CommonUtils.printFields(datas, null));

				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				ow.writeValueAsString(monthlyDetails);
			}
		} catch (Exception e) {
			logger.error("Error while getting perfios data : ",e);
		}
		return map;
	}
	
	//FINANCIALS & NOTES TO ACCOUNTS
	public Object[] calculateFinancials(Long userId, Long applicationId, String industry, Long denomination, Long proposalId,Integer year) throws Exception {
		logger.info("USER ID==>{}  APPLICATION_ID==>{} AND PROPOSAL_ID==>{}  AND YEAR_DEMONITAION==>{}--------",userId , applicationId, proposalId , year);
		FinancialInputRequestDbl financialInputRequestDbl = new FinancialInputRequestDbl();
		FinancialInputRequestString financialInputRequestString = new FinancialInputRequestString();
		OperatingStatementDetailsString osDetailsString = new OperatingStatementDetailsString();
		LiabilitiesDetailsString liabilitiesDetailsString = new LiabilitiesDetailsString();
		AssetDetailsString assetDetailsString = new AssetDetailsString();
		//CorporateFinalInfoRequest  corporateFinalInfoRequest = corporateFinalInfoService.get(userId ,applicationId);// PREVIOUS
		CorporateFinalInfoRequest  corporateFinalInfoRequest = corporateFinalInfoService.getByProposalId(userId,proposalId);//NEW BASED ON PROPOSAL ID
		//logger.info("user id and application ID  fs corporate Applicatiion details======>"+corporateFinalInfoRequest.getSharePriceFace());
        //SET SHARE FACE VALUE
		Double shareFaceVal=1.00;
		// changes remaining---->
		//CorporateApplicantDetail corporateApplicantDetail=corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId); // PREVIOUS
		CorporateApplicantDetail corporateApplicantDetail=corporateApplicantDetailRepository.getByApplicationIdAndProposalIdAndIsActive(applicationId,proposalId); // NEW BASED ON PROPOSAL ID
		logger.info("CMA DETAILS===========================>{}",corporateApplicantDetail);
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
		//OperatingStatementDetails osDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, year+""); // PREVIOUS
		OperatingStatementDetails osDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId,proposalId, year+""); // NEW BASED ON PROPOSAL ID
		if(CommonUtils.isObjectNullOrEmpty(osDetails)) {
			osDetails = new OperatingStatementDetails();
		}
		
		osDetailsString.setDomesticSales(CommonUtils.convertValueIndianCurrency(osDetails.getDomesticSales()).toString());
		osDetailsString.setExportSales(CommonUtils.convertValueIndianCurrency(osDetails.getExportSales()).toString());
		osDetailsString.setGrossSalesTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(osDetails.getDomesticSales(),osDetails.getExportSales())).toString());
		financialInputRequestDbl.setGrossSales((osDetails.getDomesticSales()+osDetails.getExportSales()) * denomination);
		financialInputRequestString.setGrossSales(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getGrossSales()).toString());
		
		osDetailsString.setLessExciseDuty(CommonUtils.convertValueIndianCurrency(osDetails.getLessExciseDuty()).toString());
		osDetailsString.setDeductOtherItems(CommonUtils.convertValueIndianCurrency(osDetails.getDeductOtherItems()).toString());
		osDetailsString.setExciseDutyTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(osDetails.getLessExciseDuty(),osDetails.getDeductOtherItems())).toString());
		financialInputRequestDbl.setLessExciseDuity((osDetails.getLessExciseDuty()+osDetails.getDeductOtherItems()) * denomination);
		financialInputRequestString.setLessExciseDuity(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getLessExciseDuity()).toString());
		
		osDetailsString.setAddOperatingStock(CommonUtils.convertValueIndianCurrency(osDetails.getAddOperatingStock()).toString());
		osDetailsString.setDeductStockInProcess(CommonUtils.convertValueIndianCurrency(osDetails.getDeductStockInProcess()).toString());
		osDetailsString.setAddOperatingStockFg(CommonUtils.convertValueIndianCurrency(osDetails.getAddOperatingStockFg()).toString());
		osDetailsString.setDeductClStockFg(CommonUtils.convertValueIndianCurrency(osDetails.getDeductClStockFg()).toString());
		osDetailsString.setIncreaseDecreaseTotal(CommonUtils.convertValueIndianCurrency((osDetails.getAddOperatingStock()-osDetails.getDeductStockInProcess()) + (osDetails.getAddOperatingStockFg()-osDetails.getDeductClStockFg()) * denomination).toString());
		financialInputRequestDbl.setIncreaseDecreaseStock(((osDetails.getAddOperatingStock()-osDetails.getDeductStockInProcess()) + (osDetails.getAddOperatingStockFg()-osDetails.getDeductClStockFg())) * denomination);
		financialInputRequestString.setIncreaseDecreaseStock(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getIncreaseDecreaseStock()).toString());
		
		osDetailsString.setRawMaterials(CommonUtils.convertValueIndianCurrency(osDetails.getRawMaterials()).toString());
		osDetailsString.setOtherSpares(CommonUtils.convertValueIndianCurrency(osDetails.getOtherSpares()).toString());
		osDetailsString.setRawMaterialsConsumedTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(osDetails.getRawMaterials(), osDetails.getOtherSpares())).toString());
		financialInputRequestDbl.setRawMaterialConsumed((osDetails.getRawMaterials()+osDetails.getOtherSpares()) * denomination);
		financialInputRequestString.setRawMaterialConsumed(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getRawMaterialConsumed()).toString());
		financialInputRequestDbl.setPowerAndFuelCost(osDetails.getPowerAndFuel()  * denomination);
		financialInputRequestString.setPowerAndFuelCost(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getPowerAndFuelCost()).toString());
		financialInputRequestDbl.setEmployeeCost(osDetails.getDirectLabour() * denomination);
		financialInputRequestString.setEmployeeCost(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getEmployeeCost()).toString());
		financialInputRequestDbl.setGeneralAndAdminExpe(osDetails.getSellingGenlAdmnExpenses() * denomination);
		financialInputRequestString.setGeneralAndAdminExpe(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getGeneralAndAdminExpe()).toString());
		financialInputRequestDbl.setSellingAndDistriExpe(osDetails.getSellingAndDistributionExpenses() * denomination);
		financialInputRequestString.setSellingAndDistriExpe(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getSellingAndDistriExpe()).toString());
		financialInputRequestDbl.setLessExpeCapita(osDetails.getExpensesAmortised() * denomination);
		financialInputRequestString.setLessExpeCapita(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getLessExpeCapita()).toString());
		financialInputRequestDbl.setMiscelExpe(osDetails.getOtherMfgExpenses() * denomination);
		financialInputRequestString.setMiscelExpe(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getMiscelExpe()).toString());
		financialInputRequestDbl.setOtherIncome(osDetails.getAddOtherRevenueIncome() * denomination);
		financialInputRequestString.setOtherIncome(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherIncome()).toString());
		financialInputRequestDbl.setInterest(osDetails.getInterest() * denomination);
		financialInputRequestString.setInterest(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getInterest()).toString());
		financialInputRequestDbl.setDepriciation(osDetails.getDepreciation() * denomination);
		financialInputRequestString.setDepriciation(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getDepriciation()).toString());
		financialInputRequestDbl.setExceptionalIncome(osDetails.getNetofNonOpIncomeOrExpenses() * denomination);
		financialInputRequestString.setExceptionalIncome(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getExceptionalIncome()).toString());
		
		osDetailsString.setOtherIncomeNeedTocCheckOp(CommonUtils.convertValueIndianCurrency(osDetails.getOtherIncomeNeedTocCheckOp()).toString());
		financialInputRequestDbl.setOtherIncomeNeedTocCheckOp(osDetails.getOtherIncomeNeedTocCheckOp() * denomination);
		financialInputRequestString.setOtherIncomeNeedTocCheckOp(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherIncomeNeedTocCheckOp()).toString());
		
		osDetailsString.setProvisionForTaxes(CommonUtils.convertValueIndianCurrency(osDetails.getProvisionForTaxes()).toString());
		osDetailsString.setProvisionForDeferredTax(CommonUtils.convertValueIndianCurrency(osDetails.getProvisionForDeferredTax()).toString());
		osDetailsString.setProvisionForTaxTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(osDetails.getProvisionForDeferredTax(), osDetails.getProvisionForTaxes())).toString());
		financialInputRequestDbl.setProvisionForTax((osDetails.getProvisionForTaxes() + osDetails.getProvisionForDeferredTax()) * denomination);
		financialInputRequestString.setProvisionForTax(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getProvisionForTax()).toString());
		financialInputRequestDbl.setDividendPayOut(osDetails.getEquityDeividendPaidAmt() * denomination);
		financialInputRequestString.setDividendPayOut(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getDividendPayOut()).toString());

		/************************************************ LIABILITIES DETAIL ***************************************************/
		//LiabilitiesDetails liabilitiesDetails = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, year+""); // PREVIOUS
		LiabilitiesDetails liabilitiesDetails = liabilitiesDetailsRepository.getLiabilitiesDetailByProposal(proposalId,year+"");// NEW BASED ON PROPOSAL ID
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)) {
			liabilitiesDetails = new LiabilitiesDetails();
		}
		liabilitiesDetailsString.setOrdinarySharesCapital(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOrdinarySharesCapital()).toString());
		liabilitiesDetailsString.setPreferencesShares(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getPreferencesShares()).toString());
		liabilitiesDetailsString.setShareCapitalTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(liabilitiesDetails.getOrdinarySharesCapital(), liabilitiesDetails.getPreferencesShares())).toString());
		financialInputRequestDbl.setShareCapital((liabilitiesDetails.getPreferencesShares() + liabilitiesDetails.getOrdinarySharesCapital()) * denomination);
		financialInputRequestString.setShareCapital(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getShareCapital()).toString());
		financialInputRequestDbl.setShareWarrantOutstandings((liabilitiesDetails.getShareWarrentsOutstanding()) * denomination);
		financialInputRequestString.setShareWarrantOutstandings(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getShareWarrantOutstandings()).toString());
		financialInputRequestDbl.setRevalationReserve((liabilitiesDetails.getRevaluationReservse()) * denomination);
		financialInputRequestString.setRevalationReserve(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getRevalationReserve()).toString());
		
		liabilitiesDetailsString.setGeneralReserve(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getGeneralReserve()).toString());
		liabilitiesDetailsString.setOtherReservse(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOtherReservse()).toString());
		liabilitiesDetailsString.setSurplusOrDeficit(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getSurplusOrDeficit()).toString());
		liabilitiesDetailsString.setOthers(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOthers()).toString());
		liabilitiesDetailsString.setOtherReservesTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(liabilitiesDetails.getGeneralReserve(),liabilitiesDetails.getOtherReservse(), liabilitiesDetails.getSurplusOrDeficit(),liabilitiesDetails.getOthers())).toString());
		financialInputRequestDbl.setOtherReserveAndSurplus((liabilitiesDetails.getGeneralReserve() + liabilitiesDetails.getOtherReservse() + liabilitiesDetails.getSurplusOrDeficit() + liabilitiesDetails.getOthers()) * denomination);
		financialInputRequestString.setOtherReserveAndSurplus(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherReserveAndSurplus()).toString());
		financialInputRequestDbl.setMinorityInterest(liabilitiesDetails.getMinorityInterest() * denomination);
		financialInputRequestString.setMinorityInterest(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getMinorityInterest()).toString());
		financialInputRequestDbl.setSecuredLoans(liabilitiesDetails.getTermLiabilitiesSecured() * denomination);
		financialInputRequestString.setSecuredLoans(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getSecuredLoans()).toString());
		financialInputRequestDbl.setUnsecuredLoansPromoters(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters() * denomination);
		financialInputRequestString.setUnsecuredLoansPromoters(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getUnsecuredLoansPromoters()).toString());
		
		liabilitiesDetailsString.setTermLiabilitiesUnsecured(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getTermLiabilitiesUnsecured()).toString());
		liabilitiesDetailsString.setOtherNclUnsecuredLoansFromOther(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther()).toString());
		liabilitiesDetailsString.setUnsecuredLoansOthersTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(liabilitiesDetails.getTermLiabilitiesUnsecured(), liabilitiesDetails.getOtherNclUnsecuredLoansFromOther())).toString());
		financialInputRequestDbl.setUnsecuredLoansOthers((liabilitiesDetails.getOtherNclUnsecuredLoansFromOther() + liabilitiesDetails.getTermLiabilitiesUnsecured()) * denomination);
		financialInputRequestString.setUnsecuredLoansOthers(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getUnsecuredLoansOthers()).toString());

		liabilitiesDetailsString.setSubTotalA(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getSubTotalA()).toString());
		liabilitiesDetailsString.setShortTermBorrowingFromOthers(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getShortTermBorrowingFromOthers()).toString());
		liabilitiesDetailsString.setOtherBorrowingsTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(liabilitiesDetails.getSubTotalA(), liabilitiesDetails.getShortTermBorrowingFromOthers())).toString());
		financialInputRequestDbl.setOtherBorrowing((liabilitiesDetails.getSubTotalA() + liabilitiesDetails.getShortTermBorrowingFromOthers()) * denomination);
		financialInputRequestString.setOtherBorrowing(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherBorrowing()).toString());
		financialInputRequestDbl.setDeferredTaxLiablities(liabilitiesDetails.getDeferredTaxLiability() * denomination);
		financialInputRequestString.setDeferredTaxLiablities(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getDeferredTaxLiablities()).toString());

		liabilitiesDetailsString.setOtherNcl(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOtherNcl()).toString());
		liabilitiesDetailsString.setDeferredPaymentsCredits(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getDeferredPaymentsCredits()).toString());
		liabilitiesDetailsString.setTermDeposits(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getTermDeposits()).toString());
		liabilitiesDetailsString.setDebentures(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getDebentures()).toString());
		liabilitiesDetailsString.setOtherTermLiabilies(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOtherTermLiabilies()).toString());
		liabilitiesDetailsString.setOtherLongTermLiabilitiesTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(liabilitiesDetails.getOtherNcl(),liabilitiesDetails.getDeferredPaymentsCredits(),liabilitiesDetails.getTermDeposits(), liabilitiesDetails.getDebentures(), liabilitiesDetails.getOtherTermLiabilies())).toString());
		financialInputRequestDbl.setOtherLongTermLiablities((liabilitiesDetails.getOtherNclOthers() + liabilitiesDetails.getDeferredPaymentsCredits() + liabilitiesDetails.getTermDeposits() + liabilitiesDetails.getDebentures() + liabilitiesDetails.getOtherTermLiabilies()) * denomination);
		financialInputRequestString.setOtherLongTermLiablities(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherLongTermLiablities()).toString());
		financialInputRequestDbl.setLongTermProvision(liabilitiesDetails.getOtherNclLongTermProvisions() * denomination);
		financialInputRequestString.setLongTermProvision(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getLongTermProvision()).toString());
		financialInputRequestDbl.setTradePayables(liabilitiesDetails.getSundryCreditors() * denomination);
		financialInputRequestString.setTradePayables(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTradePayables()).toString());
		
		liabilitiesDetailsString.setAdvancePaymentsFromCustomers(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getAdvancePaymentsFromCustomers()).toString());
		liabilitiesDetailsString.setDividendPayable(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getDividendPayable()).toString());
		liabilitiesDetailsString.setOtherStatutoryLiability(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOtherStatutoryLiability()).toString());
		liabilitiesDetailsString.setDepositsOrInstalmentsOfTermLoans(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()).toString());
		liabilitiesDetailsString.setOtherCurrentLiability(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOtherCurrentLiability()).toString());
		liabilitiesDetailsString.setOtherCurrentLiabilitiesTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(liabilitiesDetails.getAdvancePaymentsFromCustomers(),liabilitiesDetails.getDividendPayable(),liabilitiesDetails.getOtherStatutoryLiability(),liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans(),liabilitiesDetails.getOtherCurrentLiability())).toString());
		financialInputRequestDbl.setOtherCurruntLiablities((liabilitiesDetails.getAdvancePaymentsFromCustomers() + liabilitiesDetails.getDividendPayable() + liabilitiesDetails.getOtherStatutoryLiability() + liabilitiesDetails.getOtherCurrentLiability() + liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()) * denomination);
		financialInputRequestString.setOtherCurruntLiablities(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherCurruntLiablities()).toString());
		financialInputRequestDbl.setShortTermProvision(liabilitiesDetails.getProvisionalForTaxation() * denomination);
		financialInputRequestString.setShortTermProvision(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getShortTermProvision()).toString());
		
		liabilitiesDetailsString.setOtherIncomeNeedTocCheckLia(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOtherIncomeNeedTocCheckLia()).toString());
		financialInputRequestDbl.setOtherIncomeNeedTocCheckLia(liabilitiesDetails.getOtherIncomeNeedTocCheckLia() * denomination);
		financialInputRequestString.setOtherIncomeNeedTocCheckLia(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherIncomeNeedTocCheckLia()).toString());
		
		/************************************************ ASSETS DETAIL ***************************************************/
		//AssetsDetails assetsDetails = assetsDetailsRepository.getAssetsDetails(applicationId, year+""); // PREVIOUS
		AssetsDetails assetsDetails = assetsDetailsRepository.getAssetsDetailByProposal(proposalId, year+"");// NEW BASED ON PROPOSAL ID
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails)) {
			assetsDetails = new AssetsDetails();
		}
		
		financialInputRequestDbl.setGrossBlock(assetsDetails.getGrossBlock() * denomination);
		financialInputRequestString.setGrossBlock(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getGrossBlock()).toString());
		financialInputRequestDbl.setLessAccumulatedDepre(assetsDetails.getDepreciationToDate() * denomination);
		financialInputRequestString.setLessAccumulatedDepre(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getLessAccumulatedDepre()).toString());
		financialInputRequestDbl.setImpairmentofAsset(assetsDetails.getImpairmentAsset() * denomination);
		financialInputRequestString.setImpairmentofAsset(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getImpairmentofAsset()).toString());
		financialInputRequestDbl.setCapitalWorkInProgress(assetsDetails.getOtherNcaOtherCapitalWorkInprogress() * denomination);
		financialInputRequestString.setCapitalWorkInProgress(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getCapitalWorkInProgress()).toString());
		financialInputRequestDbl.setIntengibleAssets(assetsDetails.getIntangibleAssets() * denomination);
		financialInputRequestString.setIntengibleAssets(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getIntengibleAssets()).toString());
		financialInputRequestDbl.setPreOperativeExpe(assetsDetails.getOthersPreOperativeExpensesPending() * denomination);
		financialInputRequestString.setPreOperativeExpe(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getPreOperativeExpe()).toString());
		financialInputRequestDbl.setAssetInTransit(assetsDetails.getOthersAssetsInTransit() * denomination);
		financialInputRequestString.setAssetInTransit(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getAssetInTransit()).toString());
		financialInputRequestDbl.setInvestmentInSubsidiaries(assetsDetails.getInvestmentsInSubsidiary() * denomination);
		financialInputRequestString.setInvestmentInSubsidiaries(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getInvestmentInSubsidiaries()).toString());
		
		assetDetailsString.setInvestmentsOrBookDebtsString(CommonUtils.convertValueIndianCurrency(assetsDetails.getInvestmentsOrBookDebts()).toString());
		assetDetailsString.setDeferredReceviables(CommonUtils.convertValueIndianCurrency(assetsDetails.getDeferredReceviables()).toString());
		assetDetailsString.setOthers(CommonUtils.convertValueIndianCurrency(assetsDetails.getOthers()).toString());
		assetDetailsString.setOtherInvestmentsTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(assetsDetails.getInvestmentsOrBookDebts(), assetsDetails.getDeferredReceviables(),assetsDetails.getOthersOther())).toString());
		assetDetailsString.setOthersOther(CommonUtils.convertValueIndianCurrency(assetsDetails.getOthersOther()).toString());
		financialInputRequestDbl.setOtherInvestment((assetsDetails.getOthersOther() + assetsDetails.getDeferredReceviables() + assetsDetails.getOthers()) * denomination);
		financialInputRequestString.setOtherInvestment(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherInvestment()).toString());
		
		financialInputRequestDbl.setLongTermLoansAndAdva(assetsDetails.getAdvanceToSuppliersCapitalGoods() * denomination);
		financialInputRequestString.setLongTermLoansAndAdva(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getLongTermLoansAndAdva()).toString());
		
		assetDetailsString.setNonConsumableStoreAndSpares(CommonUtils.convertValueIndianCurrency(assetsDetails.getNonConsumableStoreAndSpares()).toString());
		assetDetailsString.setOtherNonCurrentAssets(CommonUtils.convertValueIndianCurrency(assetsDetails.getOtherNonCurrentAssets()).toString());
		assetDetailsString.setOtherNonCurrentAssestsTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(assetsDetails.getNonConsumableStoreAndSpares(),assetsDetails.getOtherNonCurrentAssets())).toString());
		financialInputRequestDbl.setOtheNonCurruntAsset((assetsDetails.getNonConsumableStoreAndSpares() + assetsDetails.getOtherNonCurrentAssets()) * denomination);
		financialInputRequestString.setOtheNonCurruntAsset(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtheNonCurruntAsset()).toString());
		financialInputRequestDbl.setInventories(assetsDetails.getInventory() * denomination);
		financialInputRequestString.setInventories(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getInventories()).toString());
		
		assetDetailsString.setReceivableOtherThanDefferred(CommonUtils.convertValueIndianCurrency(assetsDetails.getReceivableOtherThanDefferred()).toString());
		assetDetailsString.setExportReceivables(CommonUtils.convertValueIndianCurrency(assetsDetails.getExportReceivables()).toString());
		assetDetailsString.setSundryDebtorsTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(assetsDetails.getReceivableOtherThanDefferred(),assetsDetails.getExportReceivables())).toString());
		financialInputRequestDbl.setSundryDebtors((assetsDetails.getReceivableOtherThanDefferred() + assetsDetails.getExportReceivables()) * denomination);
		financialInputRequestString.setSundryDebtors(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getSundryDebtors()).toString());
		financialInputRequestDbl.setCashAndBank(assetsDetails.getCashAndBankBalance() * denomination);
		financialInputRequestString.setCashAndBank(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getCashAndBank()).toString());
		
		assetDetailsString.setInvestments(CommonUtils.convertValueIndianCurrency(assetsDetails.getInvestments()).toString());
		assetDetailsString.setInstalmentsDeferred(CommonUtils.convertValueIndianCurrency(assetsDetails.getInstalmentsDeferred()).toString());
		assetDetailsString.setOtherCurrentAssets(CommonUtils.convertValueIndianCurrency(assetsDetails.getOtherCurrentAssets()).toString());
		assetDetailsString.setOtherCurrentAssetsTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(assetsDetails.getInvestments(),assetsDetails.getInstalmentsDeferred(), assetsDetails.getOtherCurrentAssets())).toString());
		financialInputRequestDbl.setOtherCurruntAsset((assetsDetails.getInvestments() + assetsDetails.getInstalmentsDeferred() + assetsDetails.getOtherCurrentAssets()) * denomination);
		financialInputRequestString.setOtherCurruntAsset(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherCurruntAsset()).toString());
		
		assetDetailsString.setAdvanceToSupplierRawMaterials(CommonUtils.convertValueIndianCurrency(assetsDetails.getAdvanceToSupplierRawMaterials()).toString());
		assetDetailsString.setAdvancePaymentTaxes(CommonUtils.convertValueIndianCurrency(assetsDetails.getAdvancePaymentTaxes()).toString());
		assetDetailsString.setShortTermLoansAndAdvancesTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(assetsDetails.getAdvanceToSupplierRawMaterials(),assetsDetails.getAdvancePaymentTaxes() )).toString());
		financialInputRequestDbl.setShortTermLoansAdvances((assetsDetails.getAdvanceToSupplierRawMaterials() + assetsDetails.getAdvancePaymentTaxes()) * denomination);
		financialInputRequestString.setShortTermLoansAdvances(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getShortTermLoansAdvances()).toString());
		if(corporateFinalInfoRequest == null) {
			financialInputRequestDbl.setContingentLiablities(null);
			financialInputRequestString.setContingentLiablities(null);
		}
		else {
			financialInputRequestDbl.setContingentLiablities(CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getContLiabilityFyAmt()) ? 0.0 : (corporateFinalInfoRequest.getContLiabilityFyAmt()* denomination));
			financialInputRequestString.setContingentLiablities(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getContingentLiablities()).toString());
		}
		
		assetDetailsString.setOtherIncomeNeedTocCheckAsset(CommonUtils.convertValueIndianCurrency(assetsDetails.getOtherIncomeNeedTocCheckAsset()).toString());
		financialInputRequestDbl.setOtherIncomeNeedTocCheckAsset(assetsDetails.getOtherIncomeNeedTocCheckAsset() * denomination);
		financialInputRequestString.setOtherIncomeNeedTocCheckAsset(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherIncomeNeedTocCheckAsset()).toString());
		assetDetailsString.setCurrentRatio(CommonUtils.convertValue(assetsDetails.getCurrentRatio()).toString());
		
		/************************************************** OTHER CALCULATIONS *******************************************************/ 
		//Profit & Loss Statement
        financialInputRequestDbl.setNetSale(CommonUtils.substractNumbers(financialInputRequestDbl.getGrossSales(), financialInputRequestDbl.getLessExciseDuity()));
      	financialInputRequestString.setNetSale(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getNetSale()).toString());
		financialInputRequestDbl.setTotalExpenditure(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequestDbl.getIncreaseDecreaseStock(),financialInputRequestDbl.getRawMaterialConsumed(),financialInputRequestDbl.getPowerAndFuelCost(),financialInputRequestDbl.getEmployeeCost(), financialInputRequestDbl.getGeneralAndAdminExpe(),financialInputRequestDbl.getSellingAndDistriExpe(),financialInputRequestDbl.getMiscelExpe()), financialInputRequestDbl.getLessExpeCapita()));
		financialInputRequestString.setTotalExpenditure(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTotalExpenditure()).toString());
		financialInputRequestDbl.setOperatingProfitExclOi(CommonUtils.substractNumbers(financialInputRequestDbl.getNetSale(),financialInputRequestDbl.getTotalExpenditure()));
		financialInputRequestString.setOperatingProfitExclOi(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOperatingProfitExclOi()).toString());
		financialInputRequestDbl.setOperatingProfitEbitadOi(CommonUtils.addNumbers(financialInputRequestDbl.getOperatingProfitExclOi(),financialInputRequestDbl.getOtherIncome()));
		financialInputRequestString.setOperatingProfitEbitadOi(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOperatingProfitEbitadOi()).toString());
		financialInputRequestDbl.setPbdt(CommonUtils.substractNumbers(financialInputRequestDbl.getOperatingProfitEbitadOi(), financialInputRequestDbl.getInterest()));
		financialInputRequestString.setPbdt(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getPbdt()).toString());
		financialInputRequestDbl.setProfitBeforeTaxation(CommonUtils.substractNumbers(financialInputRequestDbl.getPbdt(), financialInputRequestDbl.getDepriciation()));
		financialInputRequestString.setProfitBeforeTaxation(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getProfitBeforeTaxation()).toString());
		financialInputRequestDbl.setProfitBeforeTax(CommonUtils.addNumbers(financialInputRequestDbl.getProfitBeforeTaxation(), financialInputRequestDbl.getExceptionalIncome()));
		financialInputRequestString.setProfitBeforeTax(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getProfitBeforeTax()).toString());
		financialInputRequestDbl.setProfitAfterTax(CommonUtils.substractNumbers(financialInputRequestDbl.getProfitBeforeTax(), financialInputRequestDbl.getProvisionForTax()) + financialInputRequestDbl.getOtherIncomeNeedTocCheckOp());
		financialInputRequestString.setProfitAfterTax(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getProfitAfterTax()).toString());
		if(financialInputRequestDbl.getDividendPayOut() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getDividendPayOut()) || financialInputRequestDbl.getShareFaceValue() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getShareFaceValue()) || financialInputRequestDbl.getShareCapital() == 0 || CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getShareCapital()))
			financialInputRequestString.setEquityDividend("0");
		else
			financialInputRequestString.setEquityDividend(CommonUtils.convertValueIndianCurrency((financialInputRequestDbl.getDividendPayOut()*financialInputRequestDbl.getShareFaceValue()/financialInputRequestDbl.getShareCapital())).toString());
		
		if(financialInputRequestDbl.getShareFaceValue() !=0 && financialInputRequestDbl.getShareCapital() !=0) {
			double total = financialInputRequestDbl.getShareFaceValue()/financialInputRequestDbl.getShareCapital();
			if(!CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getProfitAfterTax()) && financialInputRequestDbl.getProfitAfterTax() !=0) {
				financialInputRequestString.setEarningPerShare(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getProfitAfterTax() * total).toString());
			}
		}
		
		//Balance Sheet -Equities and Liabilities
		
		financialInputRequestDbl.setShareHolderFunds(CommonUtils.addNumbers(financialInputRequestDbl.getShareCapital(),financialInputRequestDbl.getShareWarrantOutstandings(),financialInputRequestDbl.getRevalationReserve(),financialInputRequestDbl.getOtherReserveAndSurplus()));
		financialInputRequestString.setShareHolderFunds(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getShareHolderFunds()).toString());
		financialInputRequestDbl.setTotalNonCurruntLiablities(CommonUtils.addNumbers(financialInputRequestDbl.getMinorityInterest(), financialInputRequestDbl.getSecuredLoans(), financialInputRequestDbl.getUnsecuredLoansOthers(),financialInputRequestDbl.getUnsecuredLoansPromoters(),financialInputRequestDbl.getDeferredTaxLiablities(),financialInputRequestDbl.getOtherLongTermLiablities(),financialInputRequestDbl.getOtherBorrowing(),financialInputRequestDbl.getLongTermProvision()));
		financialInputRequestString.setTotalNonCurruntLiablities(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTotalNonCurruntLiablities()).toString());
		financialInputRequestDbl.setTotalCurruntLiablities(CommonUtils.addNumbers(financialInputRequestDbl.getTradePayables(), financialInputRequestDbl.getOtherCurruntLiablities(), financialInputRequestDbl.getShortTermProvision()));
		financialInputRequestString.setTotalCurruntLiablities(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTotalCurruntLiablities()).toString());
		financialInputRequestDbl.setTotalLiablities(CommonUtils.addNumbers(financialInputRequestDbl.getShareHolderFunds(), financialInputRequestDbl.getTotalNonCurruntLiablities(), financialInputRequestDbl.getTotalCurruntLiablities(), financialInputRequestDbl.getOtherIncomeNeedTocCheckLia()));
		financialInputRequestString.setTotalLiablities(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTotalLiablities()).toString());

		//Balance Sheet -ASSETS
		financialInputRequestDbl.setNetBlock(CommonUtils.substractThreeNumbers(financialInputRequestDbl.getGrossBlock(), financialInputRequestDbl.getLessAccumulatedDepre(),financialInputRequestDbl.getImpairmentofAsset()));
		financialInputRequestString.setNetBlock(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getNetBlock()).toString());
		financialInputRequestDbl.setTotalNonCurruntAsset(CommonUtils.addNumbers(financialInputRequestDbl.getCapitalWorkInProgress(), financialInputRequestDbl.getIntengibleAssets(), financialInputRequestDbl.getPreOperativeExpe(), financialInputRequestDbl.getAssetInTransit(), financialInputRequestDbl.getInvestmentInSubsidiaries(), financialInputRequestDbl.getOtherInvestment(), financialInputRequestDbl.getLongTermLoansAndAdva(), financialInputRequestDbl.getOtheNonCurruntAsset()));
		financialInputRequestString.setTotalNonCurruntAsset(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTotalNonCurruntAsset()).toString());
		financialInputRequestDbl.setTotalCurruntAsset(CommonUtils.addNumbers(financialInputRequestDbl.getInventories(), financialInputRequestDbl.getSundryDebtors(), financialInputRequestDbl.getCashAndBank(), financialInputRequestDbl.getOtherCurruntAsset(), financialInputRequestDbl.getShortTermLoansAdvances()));
		financialInputRequestString.setTotalCurruntAsset(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTotalCurruntAsset()).toString());
		financialInputRequestDbl.setTotalAsset(CommonUtils.addNumbers(financialInputRequestDbl.getNetBlock(), financialInputRequestDbl.getTotalCurruntAsset(), financialInputRequestDbl.getTotalNonCurruntAsset(), financialInputRequestDbl.getOtherIncomeNeedTocCheckAsset()));
		financialInputRequestString.setTotalAsset(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTotalAsset()).toString());
		if(financialInputRequestDbl.getShareFaceValue() !=0 && financialInputRequestDbl.getShareCapital() !=0) {
			double total = financialInputRequestDbl.getShareCapital()/financialInputRequestDbl.getShareFaceValue();
			if(!CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getShareHolderFunds()) && financialInputRequestDbl.getShareHolderFunds() !=0) {
				financialInputRequestString.setBookValue(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getShareHolderFunds() / total).toString());
			}
		}
		
		return new Object[] {osDetailsString, liabilitiesDetailsString, assetDetailsString , financialInputRequestString , financialInputRequestDbl };
	}
	
	public void calculateRatioAnalysis(Map<Integer, Object[]>financials,Long toapplicationId) {
		int currentYear = scoringService.getFinYear(toapplicationId);
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
		yrBeforePrevFinYearString.setRoce("NA");
		
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
		yrBeforePrevFinYearString.setNetSalesGrowthCapital("NA");
		
		curFinYearString.setPatGrowthCapital(CommonUtils.convertValue((CommonUtils.divideNumbers(curFinYearDouble.getProfitAfterTax(),prevFinYearDouble.getProfitAfterTax())-1)* 100));
		prevFinYearString.setPatGrowthCapital(CommonUtils.convertValue((CommonUtils.divideNumbers(prevFinYearDouble.getProfitAfterTax(),yrBeforePrevFinYearDouble.getProfitAfterTax())-1)* 100));
		yrBeforePrevFinYearString.setPatGrowthCapital("NA");
		
		curFinYearDouble.setAdjustedTotalDebtEquity(Double.parseDouble(decim.format(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(curFinYearDouble.getTotalNonCurruntLiablities(),curFinYearDouble.getLongTermProvision(),curFinYearDouble.getUnsecuredLoansPromoters())), (CommonUtils.addNumbers(curFinYearDouble.getShareHolderFunds(),curFinYearDouble.getUnsecuredLoansPromoters()))))));
		prevFinYearDouble.setAdjustedTotalDebtEquity(Double.parseDouble(decim.format(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(prevFinYearDouble.getTotalNonCurruntLiablities(),prevFinYearDouble.getLongTermProvision(),prevFinYearDouble.getUnsecuredLoansPromoters())), (CommonUtils.addNumbers(prevFinYearDouble.getShareHolderFunds(),prevFinYearDouble.getUnsecuredLoansPromoters()))))));
		yrBeforePrevFinYearDouble.setAdjustedTotalDebtEquity(Double.parseDouble(decim.format(CommonUtils.divideNumbers((CommonUtils.substractThreeNumbers(yrBeforePrevFinYearDouble.getTotalNonCurruntLiablities(),yrBeforePrevFinYearDouble.getLongTermProvision(),yrBeforePrevFinYearDouble.getUnsecuredLoansPromoters())), (CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getShareHolderFunds(),yrBeforePrevFinYearDouble.getUnsecuredLoansPromoters()))))));
		curFinYearString.setAdjustedTotalDebtEquity(CommonUtils.convertValue(curFinYearDouble.getAdjustedTotalDebtEquity()));
		prevFinYearString.setAdjustedTotalDebtEquity(CommonUtils.convertValue(prevFinYearDouble.getAdjustedTotalDebtEquity()));
		yrBeforePrevFinYearString.setAdjustedTotalDebtEquity(CommonUtils.convertValue(yrBeforePrevFinYearDouble.getAdjustedTotalDebtEquity()));
		
		curFinYearString.setGrowthDebtEquity(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(curFinYearDouble.getAdjustedTotalDebtEquity(),prevFinYearDouble.getAdjustedTotalDebtEquity())), prevFinYearDouble.getAdjustedTotalDebtEquity())*100));
		prevFinYearString.setGrowthDebtEquity(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(prevFinYearDouble.getAdjustedTotalDebtEquity(),yrBeforePrevFinYearDouble.getAdjustedTotalDebtEquity())), yrBeforePrevFinYearDouble.getAdjustedTotalDebtEquity())*100));
		yrBeforePrevFinYearString.setGrowthDebtEquity("NA");
		
		curFinYearString.setCurruntRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(curFinYearDouble.getInventories(),curFinYearDouble.getSundryDebtors())), curFinYearDouble.getTradePayables())));
		prevFinYearString.setCurruntRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(prevFinYearDouble.getInventories(),prevFinYearDouble.getSundryDebtors())), prevFinYearDouble.getTradePayables())));
		yrBeforePrevFinYearString.setCurruntRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getInventories(),yrBeforePrevFinYearDouble.getSundryDebtors())), yrBeforePrevFinYearDouble.getTradePayables())));
		
		curFinYearString.setQuickRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers(curFinYearDouble.getSundryDebtors(),curFinYearDouble.getTradePayables())));
		prevFinYearString.setQuickRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers(prevFinYearDouble.getSundryDebtors(),prevFinYearDouble.getTradePayables())));
		yrBeforePrevFinYearString.setQuickRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getSundryDebtors(),yrBeforePrevFinYearDouble.getTradePayables())));
		
		try {
			CGTMSECalcDataResponse response = loanApplicationService.getDataForCGTMSE(toapplicationId);
			if(!CommonUtils.isObjectNullOrEmpty(response)) {
				if(response.getSubSector() != null && response.getSubSector().equals("Manufacturer")) {
					curFinYearString.setCashInterestCover(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(curFinYearDouble.getCashFromOperating(),curFinYearDouble.getInterestPaid())), curFinYearDouble.getInterestPaid())));
					prevFinYearString.setCashInterestCover(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.addNumbers(prevFinYearDouble.getCashFromOperating(),prevFinYearDouble.getInterestPaid())), prevFinYearDouble.getInterestPaid())));
					yrBeforePrevFinYearString.setCashInterestCover("NA");
				}else {
					curFinYearString.setCashInterestCover(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(curFinYearDouble.getOperatingProfitEbitadOi(),curFinYearDouble.getProvisionForTax())), curFinYearDouble.getInterest())));
					prevFinYearString.setCashInterestCover(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(prevFinYearDouble.getOperatingProfitEbitadOi(),prevFinYearDouble.getProvisionForTax())), prevFinYearDouble.getInterest())));
					yrBeforePrevFinYearString.setCashInterestCover(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(yrBeforePrevFinYearDouble.getOperatingProfitEbitadOi(),yrBeforePrevFinYearDouble.getProvisionForTax())), yrBeforePrevFinYearDouble.getInterest())));
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
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
		yrBeforePrevFinYearString.setCfoMargine("NA");
		
		curFinYearString.setGrowthCfoMargine(CommonUtils.convertValue(CommonUtils.divideNumbers((CommonUtils.substractNumbers(curFinYearDouble.getCfoMargine(),prevFinYearDouble.getCfoMargine())), prevFinYearDouble.getCfoMargine()) *100));
		prevFinYearString.setGrowthCfoMargine("NA");
		yrBeforePrevFinYearString.setGrowthCfoMargine("NA");
		
		
		curFinYear[curFinYear.length - 2] = curFinYearString;
		prevFinYear[prevFinYear.length - 2] = prevFinYearString;
		yrBeforePrevFinYear[yrBeforePrevFinYear.length - 2] = yrBeforePrevFinYearString;
		
		financials.put((currentYear - 1), curFinYear);
		financials.put((currentYear - 2), prevFinYear);
		financials.put((currentYear - 3), yrBeforePrevFinYear);
		logger.info("financials========={}",financials.toString());
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

	public FinancialInputRequest finaForCam(Long aplicationId,Long proposalId) {
		FinancialInputRequest financialInputRequest = new FinancialInputRequest();
		OperatingStatementDetails operatingStatementDetails;
		HashMap<String, Object> yearSalesPurchase = new HashMap<>();
		int currentYear = scoringService.getFinYear(aplicationId);
		List<Map<String, Object>> financialYearAndSalesAndPurchase = new ArrayList<>();
		try {
				operatingStatementDetails = operatingStatementDetailsRepository.getOperatingStatementDetailsByProposal(proposalId, currentYear-1+"");
				if(operatingStatementDetails != null) {
					yearSalesPurchase.put("year",currentYear-1);
					yearSalesPurchase.put("grossSale",financialInputRequest.getGrossSalesFy());
					yearSalesPurchase.put("totalCostSales",operatingStatementDetails.getTotalCostSales());
					financialYearAndSalesAndPurchase.add(yearSalesPurchase);
				}
		}catch (Exception e) {
			logger.info("Exception in getting financial fist year details {}",e);
		}
		try {
			operatingStatementDetails = operatingStatementDetailsRepository.getOperatingStatementDetailsByProposal(proposalId, currentYear-2+"");
			if(operatingStatementDetails != null) {
				yearSalesPurchase.put("year",currentYear-1);
				yearSalesPurchase.put("grossSale",financialInputRequest.getGrossSalesFy());
				yearSalesPurchase.put("totalCostSales",operatingStatementDetails.getTotalCostSales());
				financialYearAndSalesAndPurchase.add(yearSalesPurchase);
			}
		}catch (Exception e) {
			logger.info("Exception in getting financial fist year details {}",e);
		}
		
		try {
			operatingStatementDetails = operatingStatementDetailsRepository.getOperatingStatementDetailsByProposal(proposalId, currentYear-3+"");
			if(operatingStatementDetails != null) {
				yearSalesPurchase.put("year",currentYear-1);
				yearSalesPurchase.put("grossSale",financialInputRequest.getGrossSalesFy());
				yearSalesPurchase.put("totalCostSales",operatingStatementDetails.getTotalCostSales());
				financialYearAndSalesAndPurchase.add(yearSalesPurchase);
			}
		}catch (Exception e) {
			logger.info("Exception in getting financial fist year details {}",e);
		}
		financialInputRequest.setYearSalesPurchasList(financialYearAndSalesAndPurchase);
		return financialInputRequest;
	}
	
}
