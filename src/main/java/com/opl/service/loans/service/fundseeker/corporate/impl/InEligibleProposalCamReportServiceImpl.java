/**
 * 
 */
package com.opl.service.loans.service.fundseeker.corporate.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.capitaworld.service.pennydrop.client.PennydropClient;
import com.opl.api.pennydrop.model.CommonResponse;
import com.opl.mudra.api.analyzer.model.common.AnalyzerResponse;
import com.opl.mudra.api.analyzer.model.common.Data;
import com.opl.mudra.api.analyzer.model.common.ReportRequest;
import com.opl.mudra.api.cibil.model.CibilRequest;
import com.opl.mudra.api.cibil.model.CibilScoreLogRequest;
import com.opl.mudra.api.connect.ConnectStage;
import com.opl.mudra.api.gst.model.GstCalculation;
import com.opl.mudra.api.gst.model.GstResponse;
import com.opl.mudra.api.gst.model.yuva.request.GSTR1Request;
import com.opl.mudra.api.itr.model.ITRConnectionResponse;
import com.opl.mudra.api.loans.model.AssociatedConcernDetailRequest;
import com.opl.mudra.api.loans.model.DirectorBackgroundDetailRequest;
import com.opl.mudra.api.loans.model.DirectorBackgroundDetailResponseString;
import com.opl.mudra.api.loans.model.DirectorPersonalDetailResponse;
import com.opl.mudra.api.loans.model.FinancialArrangementDetailResponseString;
import com.opl.mudra.api.loans.model.FinancialArrangementsDetailRequest;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.model.CAM.AssetDetailsString;
import com.opl.mudra.api.loans.model.CAM.FinancialInputRequestDbl;
import com.opl.mudra.api.loans.model.CAM.FinancialInputRequestString;
import com.opl.mudra.api.loans.model.CAM.LiabilitiesDetailsString;
import com.opl.mudra.api.loans.model.CAM.OperatingStatementDetailsString;
import com.opl.mudra.api.loans.model.corporate.CorporateApplicantRequest;
import com.opl.mudra.api.loans.model.corporate.CorporateFinalInfoRequest;
import com.opl.mudra.api.loans.model.corporate.MachineDetailMudraLoanRequestResponse;
import com.opl.mudra.api.loans.model.corporate.PrimaryCorporateDetailMudraLoanReqRes;
import com.opl.mudra.api.loans.model.corporate.PrimaryCorporateRequest;
import com.opl.mudra.api.loans.model.retail.BankRelationshipRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper;
import com.opl.mudra.api.matchengine.model.MatchDisplayResponse;
import com.opl.mudra.api.matchengine.model.MatchRequest;
import com.opl.mudra.api.mca.McaResponse;
import com.opl.mudra.api.mca.verifyApi.VerifyAPIRequest;
import com.opl.mudra.api.oneform.enums.AssessedForITMst;
import com.opl.mudra.api.oneform.enums.AssessmentOptionForFS;
import com.opl.mudra.api.oneform.enums.CertificationCourseMst;
import com.opl.mudra.api.oneform.enums.CompetitionMst_SBI;
import com.opl.mudra.api.oneform.enums.Constitution;
import com.opl.mudra.api.oneform.enums.Denomination;
import com.opl.mudra.api.oneform.enums.DirectorRelationshipType;
import com.opl.mudra.api.oneform.enums.EducationalStatusMst;
import com.opl.mudra.api.oneform.enums.EstablishmentMonths;
import com.opl.mudra.api.oneform.enums.FSParameterMst;
import com.opl.mudra.api.oneform.enums.FactoryPremiseMst;
import com.opl.mudra.api.oneform.enums.Gender;
import com.opl.mudra.api.oneform.enums.GovSchemesMst;
import com.opl.mudra.api.oneform.enums.HaveLIMst;
import com.opl.mudra.api.oneform.enums.IdProofMst;
import com.opl.mudra.api.oneform.enums.Industry;
import com.opl.mudra.api.oneform.enums.KnowHowMst;
import com.opl.mudra.api.oneform.enums.LCBG_Status_SBI;
import com.opl.mudra.api.oneform.enums.MaritalStatusMst;
import com.opl.mudra.api.oneform.enums.MrktArrFinishedGoodsList;
import com.opl.mudra.api.oneform.enums.MudraOwningHouseMst;
import com.opl.mudra.api.oneform.enums.OngoingMudraLoan;
import com.opl.mudra.api.oneform.enums.PurposeOfLoan;
import com.opl.mudra.api.oneform.enums.RegistrationWithGovernmentAuthoritiesList;
import com.opl.mudra.api.oneform.enums.ResidentStatusMst;
import com.opl.mudra.api.oneform.enums.SpouseDetailMst;
import com.opl.mudra.api.oneform.enums.Title;
import com.opl.mudra.api.oneform.enums.VisuallyImpairedMst;
import com.opl.mudra.api.oneform.enums.WcRenewalType;
import com.opl.mudra.api.oneform.model.MasterResponse;
import com.opl.mudra.api.oneform.model.OneFormResponse;
import com.opl.mudra.api.rating.model.FinancialInputRequest;
import com.opl.mudra.api.user.model.UserResponse;
import com.opl.mudra.api.user.model.UsersRequest;
import com.opl.mudra.api.workflow.model.WorkflowRequest;
import com.opl.mudra.api.workflow.model.WorkflowResponse;
import com.opl.mudra.api.workflow.utils.WorkflowUtils;
import com.opl.mudra.client.analyzer.AnalyzerClient;
import com.opl.mudra.client.cibil.CIBILClient;
import com.opl.mudra.client.gst.GstClient;
import com.opl.mudra.client.itr.ITRClient;
import com.opl.mudra.client.matchengine.MatchEngineClient;
import com.opl.mudra.client.mca.McaClient;
import com.opl.mudra.client.oneform.OneFormClient;
import com.opl.mudra.client.users.UsersClient;
import com.opl.mudra.client.workflow.WorkflowClient;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.opl.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.opl.service.loans.domain.fundseeker.corporate.AssociatedConcernDetail;
import com.opl.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.opl.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.opl.service.loans.domain.fundseeker.corporate.MachineDetailMudraLoan;
import com.opl.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.opl.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.opl.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetailMudraLoan;
import com.opl.service.loans.domain.fundseeker.retail.BankingRelation;
import com.opl.service.loans.repository.common.CommonRepository;
import com.opl.service.loans.repository.common.LoanRepository;
import com.opl.service.loans.repository.common.impl.LoanRepositoryImpl;
import com.opl.service.loans.repository.fundprovider.FSParameterMappingRepository;
import com.opl.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.opl.service.loans.repository.fundseeker.IneligibleProposalDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.AssociatedConcernDetailRepository;
import com.opl.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.repository.fundseeker.corporate.MachineDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailMudraLoanRepository;
import com.opl.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.opl.service.loans.repository.fundseeker.retail.BankingRelationlRepository;
import com.opl.service.loans.service.common.CommonService;
import com.opl.service.loans.service.common.PincodeDateService;
import com.opl.service.loans.service.fundseeker.corporate.CamReportPdfDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.opl.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.opl.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.InEligibleProposalCamReportService;
import com.opl.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.opl.service.loans.service.fundseeker.corporate.PrimaryCorporateService;
import com.opl.service.loans.service.scoring.ScoringService;
import com.opl.service.loans.service.teaser.primaryview.CorporatePrimaryViewService;

/**
 * @author nilay.darji
 *
 */
@SuppressWarnings("unchecked")
@Service
@Transactional
public class InEligibleProposalCamReportServiceImpl implements InEligibleProposalCamReportService {

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private LoanApplicationService loanApplicationService;

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
	private ITRClient itrClient;
	
	@Autowired
	private McaClient mcaClient;

	@Autowired
	private ScoringService scoringService;

	@Autowired
	private PincodeDateService pincodeDateService;

	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateRepository;
	
	@Autowired
	private CorporatePrimaryViewService corporatePrimaryViewService;
	
	@Autowired
	private CamReportPdfDetailsService camReportPdfDetailsService;
	
	@Autowired
	private PrimaryCorporateDetailMudraLoanRepository mudraLoanRepo ;

    @Autowired
	MachineDetailsRepository machineDetailsRepo;
    
    @Autowired
	ProposalDetailsRepository proposalDetailsRepository;
    
    @Autowired
	IneligibleProposalDetailsRepository ineligibleProposalDetailsRepository;
    
    @Autowired
	private CommonRepository commonRepository;
    
    @Autowired
    private FSParameterMappingRepository fsParameterMappingRepository;
    
    @Autowired
	BankingRelationlRepository bankingRelationlRepository;
    
    @Autowired
	private CommonService commonService;
    
    @Autowired
  	AssociatedConcernDetailRepository associatedConcernDetailRepository;
    
    @Autowired
   	private CorporateApplicantService applicantService;
    
    @Autowired
    private PrimaryCorporateDetailMudraLoanRepository primaryCorporateDetailsMudra;
    
    @Autowired
    private LoanRepositoryImpl loanRepoImpl;
    
    @Autowired
    private PennydropClient pennyDropClient;
    
    @Autowired
    private LoanRepository loanRepository;

	private static final Logger logger = LoggerFactory.getLogger(InEligibleProposalCamReportServiceImpl.class);
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Map<String, Object> getInEligibleCamReport(Long applicationId) {

		Object[] profileVersionDetails = loanRepository.getProfileVersionDetailsByApplicationId(applicationId);
		if(CommonUtils.isObjectNullOrEmpty(profileVersionDetails)) {
			logger.error("Profile not found for applicationId =======>"+applicationId);
			return null;
		}
		Long itrId = profileVersionDetails[0] != null ? Long.valueOf(profileVersionDetails[0].toString()) : null;
		Long gstId = profileVersionDetails[1] != null ? Long.valueOf(profileVersionDetails[1].toString()) : null;
		Long bsId =  profileVersionDetails[2] != null ? Long.valueOf(profileVersionDetails[2].toString()) : null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		DecimalFormat decim = new DecimalFormat("####");
		Long userId = loanApplicationRepository.getUserIdByApplicationId(applicationId);

		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getByIdAndUserIdForInEligibleCam(applicationId, userId);
		
		CorporateApplicantRequest corporateApplicantRequest = corporateApplicantService.getCorporateApplicant(applicationId);
		UserResponse userResponse = usersClient.getEmailMobile(userId);
		
		LinkedHashMap<String, Object> lm = (LinkedHashMap<String, Object>) userResponse.getData();
		try {
			UsersRequest request = MultipleJSONObjectHelper.getObjectFromMap(lm, UsersRequest.class);
			map.put("mobile", request.getMobile());
			map.put("email", StringEscapeUtils.escapeXml(request.getEmail()));
		} catch (IOException e1) {
			logger.error("Error while getting registration details : ", e1);
		}
		
		CorporateFinalInfoRequest corporateFinalInfoRequest;
		try {
			corporateFinalInfoRequest = corporateFinalInfoService.get(userId, applicationId);
			// ADMIN OFFICE ADDRESS
			if (corporateFinalInfoRequest != null
					&& !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress())) {
				map.put("adminAddPremise",!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getPremiseNumber())? CommonUtils.printFields(corporateFinalInfoRequest.getSecondAddress().getPremiseNumber(), null)+ ",": "");
				map.put("adminAddStreetName",!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getStreetName())? CommonUtils.printFields(corporateFinalInfoRequest.getSecondAddress().getStreetName(),null) + " ": "");
				map.put("adminAddLandmark",!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getLandMark())? CommonUtils.printFields(corporateFinalInfoRequest.getSecondAddress().getLandMark(),null) + " ": "");
				map.put("adminAddCountry", StringEscapeUtils.escapeXml(getCountryName(corporateFinalInfoRequest.getSecondAddress().getCountryId())));
				map.put("adminAddState", StringEscapeUtils.escapeXml(getStateName(corporateFinalInfoRequest.getSecondAddress().getStateId())));
				map.put("adminAddCity", StringEscapeUtils.escapeXml(getCityName(corporateFinalInfoRequest.getSecondAddress().getCityId())));
				map.put("adminAddPincode",!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getPincode())? corporateFinalInfoRequest.getSecondAddress().getPincode(): "");
				try {
					if (!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId())) {
						map.put("adminAddressData",CommonUtils.printFields(pincodeDateService.getById(corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId()),null));
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION, e);
				}
			}
			// REGISTERED OFFICE ADDRESS
			if (corporateFinalInfoRequest != null
					&& !CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress())) {map.put("registeredAddPremise",!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getPremiseNumber())? CommonUtils.printFields(corporateFinalInfoRequest.getFirstAddress().getPremiseNumber(), null) + ",": "");
				map.put("registeredAddStreetName",!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getStreetName())? CommonUtils.printFields(corporateFinalInfoRequest.getFirstAddress().getStreetName(),null) + " ": "");
				map.put("registeredAddLandmark",!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getLandMark())? CommonUtils.printFields(corporateFinalInfoRequest.getFirstAddress().getLandMark(),null) + " ": "");
				map.put("registeredAddCountry", StringEscapeUtils.escapeXml(getCountryName(corporateFinalInfoRequest.getFirstAddress().getCountryId())));
				map.put("registeredAddState", StringEscapeUtils.escapeXml(getStateName(corporateFinalInfoRequest.getFirstAddress().getStateId())));
				map.put("registeredAddCity", StringEscapeUtils.escapeXml(getCityName(corporateFinalInfoRequest.getFirstAddress().getCityId())));
				map.put("registeredAddPincode",!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getPincode())? corporateFinalInfoRequest.getFirstAddress().getPincode(): "");
				try {
					if (!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress().getDistrictMappingId())) {
						map.put("registeredAddressData",CommonUtils.printFields(pincodeDateService.getById(corporateFinalInfoRequest.getFirstAddress().getDistrictMappingId()),null));
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION, e);
				}
			}
			map.put("corporateApplicantFinal", corporateFinalInfoRequest);
			map.put("aboutUs",corporateFinalInfoRequest!= null && corporateFinalInfoRequest.getAboutUs() != null ? StringEscapeUtils.escapeXml(corporateFinalInfoRequest.getAboutUs()) : "");
		} catch (Exception e1) {
			logger.error(CommonUtils.EXCEPTION, e1);
		}

		// TIMELINE DATES
		// date of is now change again it is consider at the time of mcq page selection
		// time ----
		if (loanApplicationMaster != null && loanApplicationMaster.getCreatedDate() != null) {
			map.put("dateOfProposal",!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getCreatedDate())? CommonUtils.DATE_FORMAT.format(loanApplicationMaster.getCreatedDate()): "-");
		}

		try {
			WorkflowRequest workflowRequest = new WorkflowRequest();
			workflowRequest.setApplicationId(applicationId);
			workflowRequest.setWorkflowId(WorkflowUtils.Workflow.DDR);
			workflowRequest.setUserId(userId);
			WorkflowResponse auditTrail = workflowClient.getAuditTrail(workflowRequest);
			if (!CommonUtils.isObjectNullOrEmpty(auditTrail)) {
				map.put("trailDates", auditTrail.getData());
			}
		} catch (Exception e2) {
			logger.error(CommonUtils.EXCEPTION, e2);
		}
		
		/* Arun */
		// Currently Commented dateOfInPrincipalApproval from
		try {
			// ConnectResponse connectResponse =
			// connectClient.getByAppStageBusinessTypeId(applicationId,
			// ConnectStage.COMPLETE.getId(),
			// com.capitaworld.service.loans.utils.CommonUtils.BusinessType.EXISTING_BUSINESS.getId());
			Date InPrincipleDate = loanApplicationRepository.getInEligibleModifiedDate(applicationId,ConnectStage.MUDRA_ONE_FORM.getId(), 6);
			if (!CommonUtils.isObjectNullOrEmpty(InPrincipleDate)) {
				map.put("dateOfInEligible", !CommonUtils.isObjectNullOrEmpty(InPrincipleDate)? CommonUtils.DATE_FORMAT.format(InPrincipleDate): "-");
			}
		} catch (Exception e2) {
			logger.error(CommonUtils.EXCEPTION, e2);
		}
		
		 //Bank Details for MSME added.
        map.put("bankDetails", getBranchDetails(applicationId, userId, null));
        
        try {
	        PrimaryCorporateDetailMudraLoanReqRes primaryCorporateDetailMudraLoanReqRes = new PrimaryCorporateDetailMudraLoanReqRes(); 
			PrimaryCorporateDetailMudraLoan primaryCorporateDetailMudraLoan = primaryCorporateDetailsMudra.findFirstByApplicationIdAndApplicationProposalMappingProposalIdOrderByIdDesc(applicationId, null);
			if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetailMudraLoan)) {
				BeanUtils.copyProperties(primaryCorporateDetailMudraLoan, primaryCorporateDetailMudraLoanReqRes);
				if (primaryCorporateDetailMudraLoanReqRes != null) {
					map.put("statutoryObligation",  CommonUtils.printFields(primaryCorporateDetailMudraLoanReqRes , null));
				}
			}
        }catch (Exception e) {
			logger.error("Error/Exception statutoryObligation==>{}",e);
		}
		
		//ITR (CHECK IF UPLOADED USING XML OR ONLINE)
		try {
			ITRConnectionResponse itrConnectionResponse= itrClient.getIsUploadAndYearDetails(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(itrConnectionResponse)) {
				map.put("checkItr", itrConnectionResponse.getData());
			}
		}catch(Exception e) {
			logger.error("Error while getting ITR data : ",e);
		}
		
		//FOR NO-ITR DATA (MUDRA - CAM)
		CorporateApplicantRequest corpApp = applicantService.getCorporateApplicantDetails(applicationId);
		LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
		corpApp.getIncomeDetails().get("creditors");
		DateFormat dfYear = new SimpleDateFormat("yyyy");
		loansResponse.setData(corpApp.getIncomeDetails());
		Date dateNoItr = new Date();
		dateNoItr = corpApp.getDob();
		if (!CommonUtils.isObjectNullOrEmpty(dateNoItr)) {
//			int ii = (int) (dateNoItr.getTime()/1000);
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(corpApp.getDob().getTime());
			map.put("noItrYear", dfYear.format(dateNoItr));
			
			String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
//			map.put("noItrMonth",dfMonth.format(response.getDob()));			
			map.put("noItrMonth",month);
		}else {
			map.put("noItrYear", "-");
			map.put("noItrMonth","-");			
		}
		
		try {
			//Map<String, Object> incomeDetails = response.getIncomeDetails();
			LinkedHashMap<String, Object> incomeDetails = corpApp.getIncomeDetails();
			
			Map<String, Object> sales = MultipleJSONObjectHelper.getObjectFromString(incomeDetails.get("sales").toString(), Map.class) ;
			  sales.put("label", "Sales"); 
			  incomeDetails.put("sales", sales);
			
			  Map<String, Object> profitAfterTax = MultipleJSONObjectHelper.getObjectFromString(incomeDetails.get("profitAfterTax").toString(), Map.class) ;
			  profitAfterTax.put("label","Net Profit / Loss"); 
			  incomeDetails.put("profitAfterTax", profitAfterTax);	  
			  
			  Map<String, Object> inventory =  MultipleJSONObjectHelper.getObjectFromString(incomeDetails.get("inventory").toString(), Map.class) ;
			  inventory.put("label", "Inventory");
			  incomeDetails.put("inventory", inventory); 
			  
			  Map<String, Object> debtors = MultipleJSONObjectHelper.getObjectFromString(incomeDetails.get("debtors").toString(), Map.class) ;
			  debtors.put("label", "Debtors");
			  incomeDetails.put("debtors", debtors);
			
			Map<String, Object> creditor = MultipleJSONObjectHelper.getObjectFromString(incomeDetails.get("creditors").toString(), Map.class) ; 
			creditor.put("label", "Creditors");
			incomeDetails.put("creditors", creditor);
			
			 
			 Map<String, Object> investmentInPlantMachinery = MultipleJSONObjectHelper.getObjectFromString(incomeDetails.get("investmentInPlantMachinery").toString(), Map.class) ;
			  investmentInPlantMachinery.put("label","Investment in Plant and Machinery / Equipments");
			  incomeDetails.put("investmentInPlantMachinery", investmentInPlantMachinery);
			  
			  Map<String, Object> networth = MultipleJSONObjectHelper.getObjectFromString(incomeDetails.get("networth").toString(), Map.class) ;
			  networth.put("label", "Networth");
			  incomeDetails.put("networth", networth);
			  
			  Map<String, Object> totalAssets = MultipleJSONObjectHelper.getObjectFromString(incomeDetails.get("totalAssets").toString(), Map.class) ;
			  totalAssets.put("label", "Total Assets");
			  incomeDetails.put("totalAssets", totalAssets);
			 
			  
			  Map<String, Object> totalLiabilities = MultipleJSONObjectHelper.getObjectFromString(incomeDetails.get("totalLiabilities").toString(), Map.class) ;
			  totalLiabilities.put("label", "Total Liabilities");
			  incomeDetails.put("totalLiabilities", totalLiabilities);

			
			  
			map.put("noItrIncomeMudra", incomeDetails);

	} catch (Exception e) {
		logger.error("Error while Getting ITR Income Details= >{}",e);
	}
		
		// GET ASSOCIATE CONCERN DETAILS
		List<AssociatedConcernDetailRequest> associatedConcernResList = new ArrayList<>(); 
		List<AssociatedConcernDetail> associatedConcernDetailList =  associatedConcernDetailRepository.listAssociatedConcernFromAppId(applicationId);
		
		if (!CommonUtils.isListNullOrEmpty(associatedConcernDetailList)) {
			for (AssociatedConcernDetail associatedConcern : associatedConcernDetailList) {
				AssociatedConcernDetailRequest assoConcernDetailRes = new AssociatedConcernDetailRequest(); 
				BeanUtils.copyProperties(associatedConcern, assoConcernDetailRes);
				// SET ADDRESS
				setAssociateAddress(assoConcernDetailRes);
				associatedConcernResList.add(assoConcernDetailRes);
			}
		}	
		try {
			map.put("associateConcern", CommonUtils.printFields(associatedConcernResList, null));
		} catch (Exception e) {
			logger.error("Error while Associate convern Details= >{}",e);
		}
		
		//GST Comparision by Maaz
		try{
			FinancialInputRequest finaForCam = camReportPdfDetailsService.finaForCam(applicationId,null,itrId);
			map.put("gstComparision", corporatePrimaryViewService.gstVsItrVsBsComparision(applicationId, finaForCam , gstId ,itrId ,bsId));
		}catch (Exception e) {
			logger.error("error in getting gst comparision data in Ineligible Cam : {}",e);
		}
		
		//gstRelatedParty Data Fetch
		try {
			Map<String , Object> gstRelatedPartyRequests = loanApplicationService.getGstRelatedPartyDetails(applicationId);
			map.put("gstPartyRelatedData", gstRelatedPartyRequests != null && !gstRelatedPartyRequests.isEmpty() ? gstRelatedPartyRequests : null);
		}catch (Exception e) {
			logger.error("Error/Exception while fetching list of gst Related Party List Data in Ineligible Cam of APplicationId==>{}  ... Error==>{}",applicationId ,e);
		}

		// GST DATA
		try {
			GSTR1Request gstr1Request = new GSTR1Request();
			gstr1Request.setApplicationId(gstId);
			gstr1Request.setUserId(userId);
			gstr1Request.setGstin(corporateApplicantRequest.getGstIn());
			GstResponse response = gstClient.getCalculations(gstr1Request);
			GstCalculation gstData = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) response.getData(), GstCalculation.class);
			int noOfCustomer = gstData.getNoOfCustomer().intValue();
			map.put("noOfCustomer", noOfCustomer);
			map.put("projectedSales", CommonUtils.convertValueRound(gstData.getProjectedSales()));
			map.put("customerConcentration", CommonUtils.convertValue(gstData.getConcentration()));
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}

		map.put("gstDetailedResp", camReportPdfDetailsService.getGstDetails(applicationId, userId));
		
		
		String categoryType = "";
		PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository.getByApplicationAndUserId(applicationId, userId);
		if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)) {
				if (primaryCorporateDetail.getLoanAmount() != null) {
					if (primaryCorporateDetail.getLoanAmount() <= 50000) {
						categoryType = "Shishu"; 
//						map.put("categoryType", "Shishu");
					}
					if(primaryCorporateDetail.getLoanAmount() >= 50001 && primaryCorporateDetail.getLoanAmount() <= 500000){
						categoryType = "Kishor"; 
//						map.put("categoryType", "Kishor");
					}
					if(primaryCorporateDetail.getLoanAmount() >= 500001 && primaryCorporateDetail.getLoanAmount() <= 1000000){
						categoryType = "Tarun"; 
//						map.put("categoryType", "Tarun");
					}
				}			
			map.put("categoryType", categoryType);
			map.put("comercialOpDate",!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCommercialOperationDate())? simpleDateFormat.format(primaryCorporateDetail.getCommercialOperationDate()): "-");
			map.put("factoryPremise", !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getFactoryPremise())? StringEscapeUtils.escapeXml(FactoryPremiseMst.getById(primaryCorporateDetail.getFactoryPremise()).getValue().toString()): "-");
			map.put("knowHow",!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getKnowHow())? StringEscapeUtils.escapeXml(KnowHowMst.getById(primaryCorporateDetail.getKnowHow()).getValue().toString()): "-");
			map.put("competition", !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCompetition())? StringEscapeUtils.escapeXml(CompetitionMst_SBI.getById(primaryCorporateDetail.getCompetition()).getValue().toString()): "-");
		}
		
		//get NO BS+ DATA
				CommonResponse verificationrequestResponse = null;
				//VerificationRequestResponse verificationResponse = null;
				try {
					if(!CommonUtils.isObjectNullOrEmpty(applicationId)) {
						verificationrequestResponse =  pennyDropClient.getAccountDetails(applicationId);
						LinkedHashMap<String, Object> noBs = (LinkedHashMap<String, Object>) verificationrequestResponse.getData();	
						String year = (String) noBs.get("sinceYear");
						String months = (String) noBs.get("sinceMonth");
						if (!CommonUtils.isObjectNullOrEmpty(year) || !CommonUtils.isObjectNullOrEmpty(months)) {
							LocalDate today = LocalDate.now();
							LocalDate since = LocalDate.of(Integer.parseInt(year), Integer.parseInt(months),1);
							Period age = Period.between(since, today);
							map.put("noBsSinceYear", age.getYears());
							map.put("noBsSinceMonths", age.getMonths());
							map.put("noBsSinceWhen", (!CommonUtils.isObjectNullOrEmpty(age.getYears()) ? age.getYears() +" year " : "") + " " +(!CommonUtils.isObjectNullOrEmpty(age.getMonths()) ? age.getMonths()+" months" :  "" ));

						}

						map.put("noBsData", verificationrequestResponse);
					}
					
				} catch (Exception e) {
					logger.error("Error while fetching Account data : ",e);			
				}
				
		


		// ONE-FORM DATA
		try {
			// ONE-FORM DATA
			map.put("corporateApplicant", corporateApplicantRequest);
			map.put("orgName", CommonUtils.printFields(corporateApplicantRequest.getOrganisationName(), null));
			map.put("constitution",!CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getConstitutionId())? StringEscapeUtils.escapeXml(Constitution.getById(corporateApplicantRequest.getConstitutionId()).getValue()): " ");
			Integer industry = corporateApplicantRequest.getKeyVericalFunding().intValue();
			map.put("keyVerticalFunding",!CommonUtils.isObjectNullOrEmpty(industry)? CommonUtils.printFields(Industry.getById(industry).getValue(), null): " ");
			
			String establishMentYear = !CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getEstablishmentMonth())? EstablishmentMonths.getById(corporateApplicantRequest.getEstablishmentMonth()).getValue(): "";
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getEstablishmentYear())) {
				try {
					OneFormResponse establishmentYearResponse = oneFormClient.getYearByYearId(CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest.getEstablishmentYear()) ? null: corporateApplicantRequest.getEstablishmentYear().longValue());
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) establishmentYearResponse.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						establishMentYear += " " + masterResponse.getValue();
					}
				} catch (Exception e) {
					logger.error("Error in getting establishment year : ", e);
				}
			}
			map.put("establishmentYr",!CommonUtils.isObjectNullOrEmpty(establishMentYear)? CommonUtils.printFields(establishMentYear, null): " ");
			// INDUSTRY DATA
//			Integer industry = corporateApplicantRequest.getKeyVericalFunding().intValue();
//			map.put("keyVerticalFunding",!CommonUtils.isObjectNullOrEmpty(industry)? CommonUtils.printFields(Industry.getById(industry).getValue(), null): " ");
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}

		//DIRECTOR'S BACKGROUND
		try {
			List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = backgroundDetailsService.getDirectorBackgroundDetailList(applicationId, userId);
			List<DirectorBackgroundDetailResponseString> directorBackgroundDetailResponseList = new ArrayList<>();
			for (DirectorBackgroundDetailRequest directorBackgroundDetailRequest : directorBackgroundDetailRequestList) {
				DirectorBackgroundDetailResponseString directorBackgroundDetailResponse = new DirectorBackgroundDetailResponseString();
				//directorBackgroundDetailResponse.setAchivements(directorBackgroundDetailRequest.getAchivements());
				directorBackgroundDetailResponse.setAddress(directorBackgroundDetailRequest.getAddress());
				directorBackgroundDetailResponse.setPremiseNumber(directorBackgroundDetailRequest.getPremiseNumber());
				directorBackgroundDetailResponse.setStreetName(directorBackgroundDetailRequest.getStreetName());
				directorBackgroundDetailResponse.setLandmark(directorBackgroundDetailRequest.getLandmark());
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
						cibilRequest.setApplicationId(applicationId);

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
				
				Double loanObligation = financialArrangementDetailsService.getTotalOfEmiByApplicationIdAndDirectorId(applicationId,directorBackgroundDetailRequest.getId());
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
				directorBackgroundDetailResponse.setIsWorkPlaceResidenceSamePlace(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest()) && !CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getIsWorkAndResidenceSamePlace()) ? (directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getIsWorkAndResidenceSamePlace() != 0 ? "Yes" : "No") : "No" );
				directorBackgroundDetailResponse.setIsPhysicallyhandicapped(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getPhysicallyHandicapped()) ? VisuallyImpairedMst.getById(directorBackgroundDetailRequest.getPhysicallyHandicapped()).toString() : "-");
			
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
							directorPersonalDetailResponse.setIdProof(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getIdProof()) ? IdProofMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getIdProof()).getValue() : "-");
							directorPersonalDetailResponse.setAssessedForIt(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getAssessedForIt()) ? StringEscapeUtils.escapeXml(AssessedForITMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getAssessedForIt()).getValue().toString()) : "-");
							directorPersonalDetailResponse.setOwningHouse(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getOwningHouse()) ? StringEscapeUtils.escapeXml(MudraOwningHouseMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getOwningHouse()).getValue().toString()) : "-");
							directorPersonalDetailResponse.setNoOfChildren(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getNoOfChildren()) ? directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getNoOfChildren() : 0 );
							directorPersonalDetailResponse.setHaveLiPolicy(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getHaveLiPolicy()) ? StringEscapeUtils.escapeXml(HaveLIMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getHaveLiPolicy()).getValue().toString()) : "-");

							Boolean isSameIdProof = directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getIsSameAddIdProof(); 									
							directorPersonalDetailResponse.setIsSameAddIdProof(!CommonUtils.isObjectNullOrEmpty(isSameIdProof) ? (isSameIdProof ? "Yes" : "No") : "No");
							
							directorPersonalDetailResponse.setCertificationCourse(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getCertificationCourse()) ? CertificationCourseMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getCertificationCourse()).getValue() : "-" );
							directorPersonalDetailResponse.setOtherIncomeSource(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getOtherIncomeSource()) ? directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getOtherIncomeSource() : 0 );
							map.put("onGoingMudraLoan", directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getOngoingMudraLoan() != null ? OngoingMudraLoan.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getOngoingMudraLoan()).getValue() : "-"  );
							// COVERED IN GOV_SCHEMES
							List<Integer> govSchemes = fsParameterMappingRepository.getParametersByApplicationIdAndType(applicationId, FSParameterMst.GOV_SCHEMES.getId());
							if (!CommonUtils.isListNullOrEmpty(govSchemes)) {
								String govScheme  = ""; 
								for (int i = 0; i < govSchemes.size(); i++) {
									String authority = GovSchemesMst.getById(govSchemes.get(i)).getValue();
									govScheme = govScheme + ((i != 0) ? ", " : "" )+ authority;
								}
								directorPersonalDetailResponse.setGovScheme(govScheme);
								map.put("govtScheme", govScheme);
							}									
							
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
		
		// REGISTER WITH GOV AUTHORITIES
		List<Integer> govAuthorities = fsParameterMappingRepository.getParametersByApplicationIdAndType(applicationId, FSParameterMst.GOV_AUTHORITIES.getId());
		if (!CommonUtils.isListNullOrEmpty(govAuthorities)) {
			String govAuthValue  = ""; 
			for (int i = 0; i < govAuthorities.size(); i++) {
				String authority = 	RegistrationWithGovernmentAuthoritiesList.fromId(govAuthorities.get(i)).getValue();
				govAuthValue = govAuthValue + ((i != 0) ? ", " : "" )+ authority;
			}
			map.put("govtAuthority", govAuthValue);
		}
		
		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
		if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getCastCategory())) {
			map.put("castCategory", corporateApplicantDetail.getCastCategory());
			map.put("noOfWorker", corporateApplicantDetail.getEmploymentGeneration() != null ? corporateApplicantDetail.getEmploymentGeneration() : "-");
			map.put("nameAsperGST", corporateApplicantDetail.getOrganisationName() != null ? StringEscapeUtils.escapeXml(corporateApplicantDetail.getOrganisationName()) : "-");
		}
		
		
		// MUDRA LOAN DETAILS
		try {
			PrimaryCorporateDetailMudraLoan mlDetail = mudraLoanRepo.findFirstByApplicationIdAndApplicationProposalMappingProposalIdIsNullOrderByIdDesc(applicationId);
			if (!CommonUtils.isObjectNullOrEmpty(mlDetail)) {
				PrimaryCorporateDetailMudraLoanReqRes mlDetailsRes = new PrimaryCorporateDetailMudraLoanReqRes();
				BeanUtils.copyProperties(mlDetail, mlDetailsRes);
				if (!CommonUtils.isObjectNullOrEmpty(mlDetail.getMrktArragementFinishedGoods())) {
					mlDetailsRes.setMrktArragementFinishedGoodsValue(MrktArrFinishedGoodsList.fromId(mlDetail.getMrktArragementFinishedGoods()).getValue());
				}
				// corporatePrimaryViewResponse.setMlDetail(mlDetailsRes);
				map.put("mlDetail", !CommonUtils.isObjectNullOrEmpty(mlDetailsRes) ? CommonUtils.printFields(mlDetailsRes , null) : Collections.EMPTY_LIST);
			}
		}
		catch (Exception e) {
			logger.error("Error/Exception while fetching mlDetails Error==>{}",e);
		}
		
		// GET MACHINE DETAILS
		List<MachineDetailMudraLoan> machineDetails = machineDetailsRepo.findByApplicationIdAndIsActive(applicationId,true);
		PrimaryCorporateDetailMudraLoanReqRes mlDetailsRes = new PrimaryCorporateDetailMudraLoanReqRes();
		if (!CommonUtils.isListNullOrEmpty(machineDetails)) {
			List<MachineDetailMudraLoanRequestResponse> machineDetailsRes = new ArrayList<>(machineDetails.size());
			for (MachineDetailMudraLoan machineDetailMudraLoan : machineDetails) {
				MachineDetailMudraLoanRequestResponse machineDetail = new MachineDetailMudraLoanRequestResponse();
				BeanUtils.copyProperties(machineDetailMudraLoan, machineDetail);
				machineDetailsRes.add(machineDetail);
			}
			mlDetailsRes.setMachineDetails(machineDetailsRes);
		}
		map.put("machineDetailsMudra", mlDetailsRes);
		
		map.put("applicationType", (loanApplicationMaster.getWcRenewalStatus() != null ? WcRenewalType.getById(loanApplicationMaster.getWcRenewalStatus()).getValue() : "New" ));

		try {
			String cmrScore = cibilClient.getCMRScore(applicationId);

			if (cmrScore != null && cmrScore.contains("EXP")) {
				map.put("msmeRankingTitle", "Experian");
			} else if (cmrScore != null && cmrScore.contains("CIBIL")) {
				map.put("msmeRankingTitle", "Cibil");
			} else {
				map.put("msmeRankingTitle", "MSME Ranking");
			}
			map.put("cibilCmrScore", cmrScore != null ? cmrScore : "Not Found");
		} catch (Exception e) {

			logger.error("error while getting cmr score : ", e);
		}
		


		// MATCHES RESPONSE
		try {
			MatchRequest matchRequest = new MatchRequest();
			matchRequest.setApplicationId(applicationId);
			/* matchRequest.setProductId(productId); */
			MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfCorporate(matchRequest);
			map.put("matchesResponse",!CommonUtils.isObjectNullOrEmpty(matchResponse.getMatchDisplayObjectMap())? CommonUtils.printFields(matchResponse.getMatchDisplayObjectMap(), null): " ");
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}

		// FINANCIAL ARRANGEMENTS
		try {
			List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService.getFinancialArrangementDetailsList(applicationId, userId);
			List<FinancialArrangementDetailResponseString> financialArrangementsDetailResponseList = new ArrayList<>();
			for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
				FinancialArrangementDetailResponseString financialArrangementsDetailResponse = new FinancialArrangementDetailResponseString();
				// financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
				financialArrangementsDetailResponse.setOutstandingAmount(CommonUtils.convertValue(financialArrangementsDetailRequest.getOutstandingAmount()));
				financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
				financialArrangementsDetailResponse.setAmount(CommonUtils.convertValue(financialArrangementsDetailRequest.getAmount()));
				// financialArrangementsDetailResponse.setLenderType(LenderType.getById(financialArrangementsDetailRequest.getLenderType()).getValue());
				financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
				financialArrangementsDetailResponse.setLoanType(financialArrangementsDetailRequest.getLoanType());
				financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
				// financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
				// financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
				financialArrangementsDetailResponse.setLcbgStatus(!CommonUtils.isObjectNullOrEmpty(financialArrangementsDetailRequest.getLcBgStatus())? LCBG_Status_SBI.getById(financialArrangementsDetailRequest.getLcBgStatus()).getValue().toString(): "-");
				financialArrangementsDetailResponse.setEmi(financialArrangementsDetailRequest.getEmi().toString());
				financialArrangementsDetailResponse.setBuerauOutStanding(financialArrangementsDetailRequest.getBureauOutstandingAmount());
				financialArrangementsDetailResponse.setCollateralAmt(financialArrangementsDetailRequest.getCollateralSecurityAmount());
				financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
			}
			map.put("financialArrangments",!CommonUtils.isListNullOrEmpty(financialArrangementsDetailResponseList)? CommonUtils.printFields(financialArrangementsDetailResponseList, null): " ");

		} catch (Exception e) {
			logger.error("Problem to get Data of Financial Arrangements Details {}", e);
		}
		
		/*
		 * get loan obligation of dir Double loanObligation =
		 * financialArrangementDetailsService.getTotalEmiOfAllDirByApplicationId(
		 * applicationId); map.put("loanObligation1", loanObligation != null ?
		 * CommonUtils.CurrencyFormat(loanObligation.toString()) : 0);
		 */
		
		

		try {
			PrimaryCorporateRequest primaryCorporateRequest = primaryCorporateService.get(applicationId, userId);
			
			if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest)) {
				//map.put("loanAmtApplied", primaryCorporateDetail.getLoanAmount()!= null ? CommonUtils.convertValueIndianCurrency(primaryCorporateDetail.getLoanAmount()) : 0);
				map.put("loanAmtApplied", loanApplicationMaster != null && loanApplicationMaster.getWcRenewalStatus() != null && loanApplicationMaster.getWcRenewalStatus() == 2 ? (primaryCorporateDetail.getLoanAmount()!= null ? CommonUtils.convertValueIndianCurrency(primaryCorporateDetail.getLoanAmount()) : 0) : (primaryCorporateDetail.getAdditionalLoanAmount() != null ? CommonUtils.convertValueIndianCurrency(primaryCorporateDetail.getAdditionalLoanAmount()) : 0));
				map.put("loanType",!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getPurposeOfLoanId())? PurposeOfLoan.getById(primaryCorporateDetail.getPurposeOfLoanId()).getValue().toString(): " ");
				map.put("promotorsContribution",CommonUtils.convertValueIndianCurrency(primaryCorporateRequest.getPromoterContribution()));
				map.put("productDesc", !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getProductServiceDescription()) ? StringEscapeUtils.escapeXml(primaryCorporateDetail.getProductServiceDescription()) : null);
				map.put("totalAmtPer",!CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getTotalAmtPercentage())? " (" + CommonUtils.convertValue(primaryCorporateRequest.getTotalAmtPercentage()) + "%)": null);
				if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getPurposeOfLoanId())) {
					map.put("purpose", StringEscapeUtils.escapeXml(primaryCorporateDetail.getPurposeOfLoanId() != null && primaryCorporateDetail.getPurposeOfLoanId()==1 ? AssessmentOptionForFS.getById(primaryCorporateDetail.getAssessmentId()).getValue().toString() : PurposeOfLoan.getById(primaryCorporateDetail.getPurposeOfLoanId()).getValue().toString()));
				} else {
					map.put("purpose", "");
				}
	
				if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getHaveCollateralSecurity()) && primaryCorporateRequest.getHaveCollateralSecurity()) {
					map.put("amtOfSecurity",!CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getCollateralSecurityAmount())? CommonUtils.convertValue(primaryCorporateRequest.getCollateralSecurityAmount()): " ");
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}

		// FINANCIALS AND NOTES TO ACCOUNTS
		try {
			PrimaryCorporateRequest primaryCorporateRequest = primaryCorporateService.get(applicationId, userId);
			int currentYear = scoringService.getFinYear(applicationId);
			map.put("currentYr", currentYear - 1);
			if (loanApplicationMaster != null && loanApplicationMaster.getDenominationId() != null) {
				Long denominationValue = Denomination.getById(loanApplicationMaster.getDenominationId()).getDigit();
				Integer[] years = { currentYear - 3, currentYear - 2, currentYear - 1 };
				Map<Integer, Object[]> financials = new TreeMap<Integer, Object[]>(Collections.reverseOrder());
				for (Integer year : years) {
					Object[] data = calculateFinancials(userId, applicationId, null, denominationValue, year);
					financials.put(year, data);
				}
				calculateRatioAnalysis(financials, applicationId);
				map.put("financials", financials);
				if (loanApplicationMaster.getTenure() != null) {
					Map<Integer, Object[]> projectedFin = new HashMap<Integer, Object[]>(loanApplicationMaster.getTenure().intValue());
					if (primaryCorporateRequest != null && primaryCorporateRequest.getProductId() == 1) {
						projectedFin.put(currentYear,calculateFinancials(userId, applicationId, null, denominationValue, currentYear));
						map.put("tenure", 1);
					} else {
						for (int i = 0; i <= loanApplicationMaster.getTenure().intValue(); i++) {
							projectedFin.put(currentYear + i, calculateFinancials(userId, applicationId, null,denominationValue, currentYear + i));
						}
						map.put("tenure", loanApplicationMaster.getTenure().intValue() + 1);
					}
					map.put("projectedFinancials", projectedFin);

				}
			}

		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}

		// NAME AS PER ITR
		try {
			ITRConnectionResponse itrResponse = itrClient.getITRBasicDetails(itrId);
			logger.info("ITR RESPONSE===========>" + itrResponse);
			map.put("nameAsPerItr", CommonUtils.printFields(itrResponse.getData(), null));
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}
		
		try {
			VerifyAPIRequest verifyAPIRequest = new VerifyAPIRequest();
			verifyAPIRequest.setApplicationId(applicationId);
			McaResponse mcaResponse = mcaClient.getVerifyApiData(verifyAPIRequest);
			
			map.put("verifyApiData", !CommonUtils.isObjectNullOrEmpty(mcaResponse) && !CommonUtils.isObjectNullOrEmpty(mcaResponse.getData()) ? CommonUtils.printFields(mcaResponse.getData() ,null) : null);
		}catch (Exception e) {
			logger.error("Error/Exception while getting Verify API Data of ApplicationId==>{}",applicationId);
		}

		// PERFIOS API DATA (BANK STATEMENT ANALYSIS)
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(applicationId);
		reportRequest.setUserId(userId);
		reportRequest.setBsMasterId(bsId);

		List<Data> datas = new ArrayList<>();
//		List<Object> bankStatement = new ArrayList<Object>();
		List<Object> monthlyDetails = new ArrayList<Object>();
		List<Object> top5FundReceived = new ArrayList<Object>();
		List<Object> top5FundTransfered = new ArrayList<Object>();
		List<Object> bouncedChequeList = new ArrayList<Object>();
		List<Object> customerInfo = new ArrayList<Object>();
		List<Object> summaryInfo = new ArrayList<Object>();

		try {
			AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
			if (analyzerResponse.getData() != null) {
				List<HashMap<String, Object>> hashMap = (List<HashMap<String, Object>>) analyzerResponse.getData();

				if (!CommonUtils.isListNullOrEmpty(hashMap)) {
					for (HashMap<String, Object> rec : hashMap) {
						Data data = MultipleJSONObjectHelper.getObjectFromMap(rec, Data.class);
						datas.add(data);

						// bankStatement.add(!CommonUtils.isObjectNullOrEmpty(data.getXns()) ?
						// CommonUtils.printFields(data.getXns().getXn(),null) : " ");
						monthlyDetails.add(!CommonUtils.isObjectNullOrEmpty(data.getMonthlyDetailList())? CommonUtils.printFields(data.getMonthlyDetailList(), null): "");
						top5FundReceived.add(!CommonUtils.isObjectNullOrEmpty(data.getTop5FundReceivedList())? CommonUtils.printFields(data.getTop5FundReceivedList().getItem(), null): "");
						top5FundTransfered.add(!CommonUtils.isObjectNullOrEmpty(data.getTop5FundTransferedList())? CommonUtils.printFields(data.getTop5FundTransferedList().getItem(), null): "");
						bouncedChequeList.add(!CommonUtils.isObjectNullOrEmpty(data.getBouncedOrPenalXnList())? CommonUtils.printFields(data.getBouncedOrPenalXnList().getBouncedOrPenalXns(), null): " ");
						customerInfo.add(!CommonUtils.isObjectNullOrEmpty(data.getCustomerInfo())? CommonUtils.printFields(data.getCustomerInfo(), null): " ");
						summaryInfo.add(!CommonUtils.isObjectNullOrEmpty(data.getSummaryInfo())? CommonUtils.printFields(data.getSummaryInfo(), null): " ");

					}

					map.put("monthlyDetails", monthlyDetails);
					map.put("top5FundReceived", top5FundReceived);
					map.put("top5FundTransfered", top5FundTransfered);
					map.put("bouncedChequeList", bouncedChequeList);
					map.put("customerInfo", customerInfo);
					map.put("summaryInfo", summaryInfo);
					map.put("bankStatementAnalysis", CommonUtils.printFields(datas, null));

				}
			}
		} catch (Exception e) {
			logger.error("Error while getting perfios data : ", e);
		}
		
		
		
		
		

		return map;

	}

	private String getCityName(Long cityId) {
		try {
			if (CommonUtils.isObjectNullOrEmpty(cityId)) {
				return null;
			}
			List<Long> cityList = new ArrayList<>(1);
			cityList.add(cityId);
			OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
				MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0),MasterResponse.class);
				return masterResponse.getValue();
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}
		return null;
	}

	private String getStateName(Integer stateId) {
		try {
			if (CommonUtils.isObjectNullOrEmpty(stateId)) {
				return null;
			}
			List<Long> stateList = new ArrayList<>(1);
			stateList.add(stateId.longValue());
			OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
				MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0),MasterResponse.class);
				return masterResponse.getValue();
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}
		return null;
	}

	private String getCountryName(Integer country) {
		try {
			if (CommonUtils.isObjectNullOrEmpty(country)) {
				return null;
			}
			List<Long> countryList = new ArrayList<>(1);
			countryList.add(country.longValue());
			OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
				MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0),MasterResponse.class);
				return masterResponse.getValue();
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}
		return null;
	}

	// FINANCIALS & NOTES TO ACCOUNTS
	public Object[] calculateFinancials(Long userId, Long applicationId, String industry, Long denomination,Integer year) throws Exception {
		FinancialInputRequestDbl financialInputRequestDbl = new FinancialInputRequestDbl();
		FinancialInputRequestString financialInputRequestString = new FinancialInputRequestString();
		OperatingStatementDetailsString osDetailsString = new OperatingStatementDetailsString();
		LiabilitiesDetailsString liabilitiesDetailsString = new LiabilitiesDetailsString();
		AssetDetailsString assetDetailsString = new AssetDetailsString();
		CorporateFinalInfoRequest corporateFinalInfoRequest = corporateFinalInfoService.get(userId, applicationId);
		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
		if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getSharePriceFace())) {
				// SET SHARE FACE VALUE
				Double shareFaceVal = corporateApplicantDetail.getSharePriceFace();
				financialInputRequestDbl.setShareFaceValue(shareFaceVal);
			} else {
				financialInputRequestDbl.setShareFaceValue(1.00);
			}
		} else {
			financialInputRequestDbl.setShareFaceValue(1.00);
		}

		financialInputRequestDbl.setNoOfMonth(12.0);

		/**************************************************
		 * OPERATING STATEMENT
		 ***************************************************/
		OperatingStatementDetails osDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, year + "");
		if (CommonUtils.isObjectNullOrEmpty(osDetails)) {
			osDetails = new OperatingStatementDetails();
		}

		osDetailsString.setDomesticSales(CommonUtils.convertValueIndianCurrency(osDetails.getDomesticSales()).toString());
		osDetailsString.setExportSales(CommonUtils.convertValueIndianCurrency(osDetails.getExportSales()).toString());
		osDetailsString.setGrossSalesTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(osDetails.getDomesticSales(), osDetails.getExportSales())).toString());
		financialInputRequestDbl.setGrossSales((osDetails.getDomesticSales() + osDetails.getExportSales()) * denomination);
		financialInputRequestString.setGrossSales(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getGrossSales()).toString());

		osDetailsString.setLessExciseDuty(CommonUtils.convertValueIndianCurrency(osDetails.getLessExciseDuty()).toString());
		osDetailsString.setDeductOtherItems(CommonUtils.convertValueIndianCurrency(osDetails.getDeductOtherItems()).toString());
		osDetailsString.setExciseDutyTotal(CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(osDetails.getLessExciseDuty(), osDetails.getDeductOtherItems())).toString());
		financialInputRequestDbl.setLessExciseDuity((osDetails.getLessExciseDuty() + osDetails.getDeductOtherItems()) * denomination);
		financialInputRequestString.setLessExciseDuity(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getLessExciseDuity()).toString());

		osDetailsString.setAddOperatingStock(CommonUtils.convertValueIndianCurrency(osDetails.getAddOperatingStock()).toString());
		osDetailsString.setDeductStockInProcess(CommonUtils.convertValueIndianCurrency(osDetails.getDeductStockInProcess()).toString());
		osDetailsString.setAddOperatingStockFg(CommonUtils.convertValueIndianCurrency(osDetails.getAddOperatingStockFg()).toString());
		osDetailsString.setDeductClStockFg(CommonUtils.convertValueIndianCurrency(osDetails.getDeductClStockFg()).toString());
		osDetailsString.setIncreaseDecreaseTotal(CommonUtils.convertValueIndianCurrency((osDetails.getAddOperatingStock() - osDetails.getDeductStockInProcess())+ (osDetails.getAddOperatingStockFg() - osDetails.getDeductClStockFg()) * denomination).toString());
		financialInputRequestDbl
				.setIncreaseDecreaseStock(((osDetails.getAddOperatingStock() - osDetails.getDeductStockInProcess())
						+ (osDetails.getAddOperatingStockFg() - osDetails.getDeductClStockFg())) * denomination);
		financialInputRequestString.setIncreaseDecreaseStock(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getIncreaseDecreaseStock()).toString());

		osDetailsString.setRawMaterials(CommonUtils.convertValueIndianCurrency(osDetails.getRawMaterials()).toString());
		osDetailsString.setOtherSpares(CommonUtils.convertValueIndianCurrency(osDetails.getOtherSpares()).toString());
		osDetailsString
				.setRawMaterialsConsumedTotal(CommonUtils
						.convertValueIndianCurrency(
								CommonUtils.addNumbers(osDetails.getRawMaterials(), osDetails.getOtherSpares()))
						.toString());
		financialInputRequestDbl
				.setRawMaterialConsumed((osDetails.getRawMaterials() + osDetails.getOtherSpares()) * denomination);
		financialInputRequestString.setRawMaterialConsumed(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getRawMaterialConsumed()).toString());
		financialInputRequestDbl.setPowerAndFuelCost(osDetails.getPowerAndFuel() * denomination);
		financialInputRequestString.setPowerAndFuelCost(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getPowerAndFuelCost()).toString());
		financialInputRequestDbl.setEmployeeCost(osDetails.getDirectLabour() * denomination);
		financialInputRequestString.setEmployeeCost(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getEmployeeCost()).toString());
		financialInputRequestDbl.setGeneralAndAdminExpe(osDetails.getSellingGenlAdmnExpenses() * denomination);
		financialInputRequestString.setGeneralAndAdminExpe(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getGeneralAndAdminExpe()).toString());
		financialInputRequestDbl.setSellingAndDistriExpe(osDetails.getSellingAndDistributionExpenses() * denomination);
		financialInputRequestString.setSellingAndDistriExpe(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getSellingAndDistriExpe()).toString());
		financialInputRequestDbl.setLessExpeCapita(osDetails.getExpensesAmortised() * denomination);
		financialInputRequestString.setLessExpeCapita(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getLessExpeCapita()).toString());
		financialInputRequestDbl.setMiscelExpe(osDetails.getOtherMfgExpenses() * denomination);
		financialInputRequestString.setMiscelExpe(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getMiscelExpe()).toString());
		financialInputRequestDbl.setOtherIncome(osDetails.getAddOtherRevenueIncome() * denomination);
		financialInputRequestString.setOtherIncome(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherIncome()).toString());
		financialInputRequestDbl.setInterest(osDetails.getInterest() * denomination);
		financialInputRequestString
				.setInterest(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getInterest()).toString());
		financialInputRequestDbl.setDepriciation(osDetails.getDepreciation() * denomination);
		financialInputRequestString.setDepriciation(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getDepriciation()).toString());
		financialInputRequestDbl.setExceptionalIncome(osDetails.getNetofNonOpIncomeOrExpenses() * denomination);
		financialInputRequestString.setExceptionalIncome(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getExceptionalIncome()).toString());

		osDetailsString.setOtherIncomeNeedTocCheckOp(
				CommonUtils.convertValueIndianCurrency(osDetails.getOtherIncomeNeedTocCheckOp()).toString());
		financialInputRequestDbl.setOtherIncomeNeedTocCheckOp(osDetails.getOtherIncomeNeedTocCheckOp() * denomination);
		financialInputRequestString.setOtherIncomeNeedTocCheckOp(CommonUtils
				.convertValueIndianCurrency(financialInputRequestDbl.getOtherIncomeNeedTocCheckOp()).toString());

		osDetailsString.setProvisionForTaxes(
				CommonUtils.convertValueIndianCurrency(osDetails.getProvisionForTaxes()).toString());
		osDetailsString.setProvisionForDeferredTax(
				CommonUtils.convertValueIndianCurrency(osDetails.getProvisionForDeferredTax()).toString());
		osDetailsString
				.setProvisionForTaxTotal(
						CommonUtils
								.convertValueIndianCurrency(CommonUtils.addNumbers(
										osDetails.getProvisionForDeferredTax(), osDetails.getProvisionForTaxes()))
								.toString());
		financialInputRequestDbl.setProvisionForTax(
				(osDetails.getProvisionForTaxes() + osDetails.getProvisionForDeferredTax()) * denomination);
		financialInputRequestString.setProvisionForTax(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getProvisionForTax()).toString());
		financialInputRequestDbl.setDividendPayOut(osDetails.getEquityDeividendPaidAmt() * denomination);
		financialInputRequestString.setDividendPayOut(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getDividendPayOut()).toString());

		/************************************************
		 * LIABILITIES DETAIL
		 ***************************************************/
		LiabilitiesDetails liabilitiesDetails = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId,
				year + "");
		if (CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)) {
			liabilitiesDetails = new LiabilitiesDetails();
		}
		liabilitiesDetailsString.setOrdinarySharesCapital(
				CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOrdinarySharesCapital()).toString());
		liabilitiesDetailsString.setPreferencesShares(
				CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getPreferencesShares()).toString());
		liabilitiesDetailsString.setShareCapitalTotal(CommonUtils.convertValueIndianCurrency(CommonUtils
				.addNumbers(liabilitiesDetails.getOrdinarySharesCapital(), liabilitiesDetails.getPreferencesShares()))
				.toString());
		financialInputRequestDbl.setShareCapital(
				(liabilitiesDetails.getPreferencesShares() + liabilitiesDetails.getOrdinarySharesCapital())
						* denomination);
		financialInputRequestString.setShareCapital(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getShareCapital()).toString());
		financialInputRequestDbl
				.setShareWarrantOutstandings((liabilitiesDetails.getShareWarrentsOutstanding()) * denomination);
		financialInputRequestString.setShareWarrantOutstandings(CommonUtils
				.convertValueIndianCurrency(financialInputRequestDbl.getShareWarrantOutstandings()).toString());
		financialInputRequestDbl.setRevalationReserve((liabilitiesDetails.getRevaluationReservse()) * denomination);
		financialInputRequestString.setRevalationReserve(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getRevalationReserve()).toString());

		liabilitiesDetailsString.setGeneralReserve(
				CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getGeneralReserve()).toString());
		liabilitiesDetailsString.setOtherReservse(
				CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOtherReservse()).toString());
		liabilitiesDetailsString.setSurplusOrDeficit(
				CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getSurplusOrDeficit()).toString());
		liabilitiesDetailsString
				.setOthers(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOthers()).toString());
		liabilitiesDetailsString
				.setOtherReservesTotal(
						CommonUtils
								.convertValueIndianCurrency(CommonUtils.addNumbers(
										liabilitiesDetails.getGeneralReserve(), liabilitiesDetails.getOtherReservse(),
										liabilitiesDetails.getSurplusOrDeficit(), liabilitiesDetails.getOthers()))
								.toString());
		financialInputRequestDbl.setOtherReserveAndSurplus(
				(liabilitiesDetails.getGeneralReserve() + liabilitiesDetails.getOtherReservse()
						+ liabilitiesDetails.getSurplusOrDeficit() + liabilitiesDetails.getOthers()) * denomination);
		financialInputRequestString.setOtherReserveAndSurplus(CommonUtils
				.convertValueIndianCurrency(financialInputRequestDbl.getOtherReserveAndSurplus()).toString());
		financialInputRequestDbl.setMinorityInterest(liabilitiesDetails.getMinorityInterest() * denomination);
		financialInputRequestString.setMinorityInterest(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getMinorityInterest()).toString());
		financialInputRequestDbl.setSecuredLoans(liabilitiesDetails.getTermLiabilitiesSecured() * denomination);
		financialInputRequestString.setSecuredLoans(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getSecuredLoans()).toString());
		financialInputRequestDbl
				.setUnsecuredLoansPromoters(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters() * denomination);
		financialInputRequestString.setUnsecuredLoansPromoters(CommonUtils
				.convertValueIndianCurrency(financialInputRequestDbl.getUnsecuredLoansPromoters()).toString());

		liabilitiesDetailsString.setTermLiabilitiesUnsecured(
				CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getTermLiabilitiesUnsecured()).toString());
		liabilitiesDetailsString.setOtherNclUnsecuredLoansFromOther(CommonUtils
				.convertValueIndianCurrency(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther()).toString());
		liabilitiesDetailsString.setUnsecuredLoansOthersTotal(CommonUtils
				.convertValueIndianCurrency(CommonUtils.addNumbers(liabilitiesDetails.getTermLiabilitiesUnsecured(),
						liabilitiesDetails.getOtherNclUnsecuredLoansFromOther()))
				.toString());
		financialInputRequestDbl.setUnsecuredLoansOthers((liabilitiesDetails.getOtherNclUnsecuredLoansFromOther()
				+ liabilitiesDetails.getTermLiabilitiesUnsecured()) * denomination);
		financialInputRequestString.setUnsecuredLoansOthers(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getUnsecuredLoansOthers()).toString());

		liabilitiesDetailsString
				.setSubTotalA(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getSubTotalA()).toString());
		liabilitiesDetailsString.setShortTermBorrowingFromOthers(CommonUtils
				.convertValueIndianCurrency(liabilitiesDetails.getShortTermBorrowingFromOthers()).toString());
		liabilitiesDetailsString.setOtherBorrowingsTotal(
				CommonUtils.convertValueIndianCurrency(CommonUtils.addNumbers(liabilitiesDetails.getSubTotalA(),
						liabilitiesDetails.getShortTermBorrowingFromOthers())).toString());
		financialInputRequestDbl.setOtherBorrowing(
				(liabilitiesDetails.getSubTotalA() + liabilitiesDetails.getShortTermBorrowingFromOthers())
						* denomination);
		financialInputRequestString.setOtherBorrowing(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherBorrowing()).toString());
		financialInputRequestDbl.setDeferredTaxLiablities(liabilitiesDetails.getDeferredTaxLiability() * denomination);
		financialInputRequestString.setDeferredTaxLiablities(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getDeferredTaxLiablities()).toString());

		liabilitiesDetailsString
				.setOtherNcl(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOtherNcl()).toString());
		liabilitiesDetailsString.setDeferredPaymentsCredits(
				CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getDeferredPaymentsCredits()).toString());
		liabilitiesDetailsString.setTermDeposits(
				CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getTermDeposits()).toString());
		liabilitiesDetailsString
				.setDebentures(CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getDebentures()).toString());
		liabilitiesDetailsString.setOtherTermLiabilies(
				CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOtherTermLiabilies()).toString());
		liabilitiesDetailsString
				.setOtherLongTermLiabilitiesTotal(CommonUtils
						.convertValueIndianCurrency(CommonUtils.addNumbers(liabilitiesDetails.getOtherNcl(),
								liabilitiesDetails.getDeferredPaymentsCredits(), liabilitiesDetails.getTermDeposits(),
								liabilitiesDetails.getDebentures(), liabilitiesDetails.getOtherTermLiabilies()))
						.toString());
		financialInputRequestDbl.setOtherLongTermLiablities((liabilitiesDetails.getOtherNclOthers()
				+ liabilitiesDetails.getDeferredPaymentsCredits() + liabilitiesDetails.getTermDeposits()
				+ liabilitiesDetails.getDebentures() + liabilitiesDetails.getOtherTermLiabilies()) * denomination);
		financialInputRequestString.setOtherLongTermLiablities(CommonUtils
				.convertValueIndianCurrency(financialInputRequestDbl.getOtherLongTermLiablities()).toString());
		financialInputRequestDbl
				.setLongTermProvision(liabilitiesDetails.getOtherNclLongTermProvisions() * denomination);
		financialInputRequestString.setLongTermProvision(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getLongTermProvision()).toString());
		financialInputRequestDbl.setTradePayables(liabilitiesDetails.getSundryCreditors() * denomination);
		financialInputRequestString.setTradePayables(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTradePayables()).toString());

		liabilitiesDetailsString.setAdvancePaymentsFromCustomers(CommonUtils
				.convertValueIndianCurrency(liabilitiesDetails.getAdvancePaymentsFromCustomers()).toString());
		liabilitiesDetailsString.setDividendPayable(
				CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getDividendPayable()).toString());
		liabilitiesDetailsString.setOtherStatutoryLiability(
				CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOtherStatutoryLiability()).toString());
		liabilitiesDetailsString.setDepositsOrInstalmentsOfTermLoans(CommonUtils
				.convertValueIndianCurrency(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()).toString());
		liabilitiesDetailsString.setOtherCurrentLiability(
				CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOtherCurrentLiability()).toString());
		liabilitiesDetailsString.setOtherCurrentLiabilitiesTotal(CommonUtils
				.convertValueIndianCurrency(CommonUtils.addNumbers(liabilitiesDetails.getAdvancePaymentsFromCustomers(),
						liabilitiesDetails.getDividendPayable(), liabilitiesDetails.getOtherStatutoryLiability(),
						liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans(),
						liabilitiesDetails.getOtherCurrentLiability()))
				.toString());
		financialInputRequestDbl.setOtherCurruntLiablities((liabilitiesDetails.getAdvancePaymentsFromCustomers()
				+ liabilitiesDetails.getDividendPayable() + liabilitiesDetails.getOtherStatutoryLiability()
				+ liabilitiesDetails.getOtherCurrentLiability()
				+ liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()) * denomination);
		financialInputRequestString.setOtherCurruntLiablities(CommonUtils
				.convertValueIndianCurrency(financialInputRequestDbl.getOtherCurruntLiablities()).toString());
		financialInputRequestDbl.setShortTermProvision(liabilitiesDetails.getProvisionalForTaxation() * denomination);
		financialInputRequestString.setShortTermProvision(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getShortTermProvision()).toString());

		liabilitiesDetailsString.setOtherIncomeNeedTocCheckLia(
				CommonUtils.convertValueIndianCurrency(liabilitiesDetails.getOtherIncomeNeedTocCheckLia()).toString());
		financialInputRequestDbl
				.setOtherIncomeNeedTocCheckLia(liabilitiesDetails.getOtherIncomeNeedTocCheckLia() * denomination);
		financialInputRequestString.setOtherIncomeNeedTocCheckLia(CommonUtils
				.convertValueIndianCurrency(financialInputRequestDbl.getOtherIncomeNeedTocCheckLia()).toString());

		/************************************************
		 * ASSETS DETAIL
		 ***************************************************/
		AssetsDetails assetsDetails = assetsDetailsRepository.getAssestDetailsByApplicationId(applicationId, year + "");
		if (CommonUtils.isObjectNullOrEmpty(assetsDetails)) {
			assetsDetails = new AssetsDetails();
		}

		financialInputRequestDbl.setGrossBlock(assetsDetails.getGrossBlock() * denomination);
		financialInputRequestString.setGrossBlock(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getGrossBlock()).toString());
		financialInputRequestDbl.setLessAccumulatedDepre(assetsDetails.getDepreciationToDate() * denomination);
		financialInputRequestString.setLessAccumulatedDepre(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getLessAccumulatedDepre()).toString());
		financialInputRequestDbl.setImpairmentofAsset(assetsDetails.getImpairmentAsset() * denomination);
		financialInputRequestString.setImpairmentofAsset(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getImpairmentofAsset()).toString());
		financialInputRequestDbl
				.setCapitalWorkInProgress(assetsDetails.getOtherNcaOtherCapitalWorkInprogress() * denomination);
		financialInputRequestString.setCapitalWorkInProgress(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getCapitalWorkInProgress()).toString());
		financialInputRequestDbl.setIntengibleAssets(assetsDetails.getIntangibleAssets() * denomination);
		financialInputRequestString.setIntengibleAssets(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getIntengibleAssets()).toString());
		financialInputRequestDbl
				.setPreOperativeExpe(assetsDetails.getOthersPreOperativeExpensesPending() * denomination);
		financialInputRequestString.setPreOperativeExpe(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getPreOperativeExpe()).toString());
		financialInputRequestDbl.setAssetInTransit(assetsDetails.getOthersAssetsInTransit() * denomination);
		financialInputRequestString.setAssetInTransit(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getAssetInTransit()).toString());
		financialInputRequestDbl.setInvestmentInSubsidiaries(assetsDetails.getInvestmentsInSubsidiary() * denomination);
		financialInputRequestString.setInvestmentInSubsidiaries(CommonUtils
				.convertValueIndianCurrency(financialInputRequestDbl.getInvestmentInSubsidiaries()).toString());

		assetDetailsString.setInvestmentsOrBookDebtsString(
				CommonUtils.convertValueIndianCurrency(assetsDetails.getInvestmentsOrBookDebts()).toString());
		assetDetailsString.setDeferredReceviables(
				CommonUtils.convertValueIndianCurrency(assetsDetails.getDeferredReceviables()).toString());
		assetDetailsString.setOthers(CommonUtils.convertValueIndianCurrency(assetsDetails.getOthers()).toString());
		assetDetailsString
				.setOtherInvestmentsTotal(
						CommonUtils
								.convertValueIndianCurrency(
										CommonUtils.addNumbers(assetsDetails.getInvestmentsOrBookDebts(),
												assetsDetails.getDeferredReceviables(), assetsDetails.getOthersOther()))
								.toString());
		assetDetailsString
				.setOthersOther(CommonUtils.convertValueIndianCurrency(assetsDetails.getOthersOther()).toString());
		financialInputRequestDbl.setOtherInvestment(
				(assetsDetails.getOthersOther() + assetsDetails.getDeferredReceviables() + assetsDetails.getOthers())
						* denomination);
		financialInputRequestString.setOtherInvestment(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherInvestment()).toString());

		financialInputRequestDbl
				.setLongTermLoansAndAdva(assetsDetails.getAdvanceToSuppliersCapitalGoods() * denomination);
		financialInputRequestString.setLongTermLoansAndAdva(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getLongTermLoansAndAdva()).toString());

		assetDetailsString.setNonConsumableStoreAndSpares(
				CommonUtils.convertValueIndianCurrency(assetsDetails.getNonConsumableStoreAndSpares()).toString());
		assetDetailsString.setOtherNonCurrentAssets(
				CommonUtils.convertValueIndianCurrency(assetsDetails.getOtherNonCurrentAssets()).toString());
		assetDetailsString.setOtherNonCurrentAssestsTotal(CommonUtils.convertValueIndianCurrency(CommonUtils
				.addNumbers(assetsDetails.getNonConsumableStoreAndSpares(), assetsDetails.getOtherNonCurrentAssets()))
				.toString());
		financialInputRequestDbl.setOtheNonCurruntAsset(
				(assetsDetails.getNonConsumableStoreAndSpares() + assetsDetails.getOtherNonCurrentAssets())
						* denomination);
		financialInputRequestString.setOtheNonCurruntAsset(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtheNonCurruntAsset()).toString());
		financialInputRequestDbl.setInventories(assetsDetails.getInventory() * denomination);
		financialInputRequestString.setInventories(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getInventories()).toString());

		assetDetailsString.setReceivableOtherThanDefferred(
				CommonUtils.convertValueIndianCurrency(assetsDetails.getReceivableOtherThanDefferred()).toString());
		assetDetailsString.setExportReceivables(
				CommonUtils.convertValueIndianCurrency(assetsDetails.getExportReceivables()).toString());
		assetDetailsString
				.setSundryDebtorsTotal(CommonUtils
						.convertValueIndianCurrency(CommonUtils.addNumbers(
								assetsDetails.getReceivableOtherThanDefferred(), assetsDetails.getExportReceivables()))
						.toString());
		financialInputRequestDbl.setSundryDebtors(
				(assetsDetails.getReceivableOtherThanDefferred() + assetsDetails.getExportReceivables())
						* denomination);
		financialInputRequestString.setSundryDebtors(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getSundryDebtors()).toString());
		financialInputRequestDbl.setCashAndBank(assetsDetails.getCashAndBankBalance() * denomination);
		financialInputRequestString.setCashAndBank(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getCashAndBank()).toString());

		assetDetailsString
				.setInvestments(CommonUtils.convertValueIndianCurrency(assetsDetails.getInvestments()).toString());
		assetDetailsString.setInstalmentsDeferred(
				CommonUtils.convertValueIndianCurrency(assetsDetails.getInstalmentsDeferred()).toString());
		assetDetailsString.setOtherCurrentAssets(
				CommonUtils.convertValueIndianCurrency(assetsDetails.getOtherCurrentAssets()).toString());
		assetDetailsString
				.setOtherCurrentAssetsTotal(CommonUtils
						.convertValueIndianCurrency(CommonUtils.addNumbers(assetsDetails.getInvestments(),
								assetsDetails.getInstalmentsDeferred(), assetsDetails.getOtherCurrentAssets()))
						.toString());
		financialInputRequestDbl.setOtherCurruntAsset((assetsDetails.getInvestments()
				+ assetsDetails.getInstalmentsDeferred() + assetsDetails.getOtherCurrentAssets()) * denomination);
		financialInputRequestString.setOtherCurruntAsset(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOtherCurruntAsset()).toString());

		assetDetailsString.setAdvanceToSupplierRawMaterials(
				CommonUtils.convertValueIndianCurrency(assetsDetails.getAdvanceToSupplierRawMaterials()).toString());
		assetDetailsString.setAdvancePaymentTaxes(
				CommonUtils.convertValueIndianCurrency(assetsDetails.getAdvancePaymentTaxes()).toString());
		assetDetailsString.setShortTermLoansAndAdvancesTotal(CommonUtils.convertValueIndianCurrency(CommonUtils
				.addNumbers(assetsDetails.getAdvanceToSupplierRawMaterials(), assetsDetails.getAdvancePaymentTaxes()))
				.toString());
		financialInputRequestDbl.setShortTermLoansAdvances(
				(assetsDetails.getAdvanceToSupplierRawMaterials() + assetsDetails.getAdvancePaymentTaxes())
						* denomination);
		financialInputRequestString.setShortTermLoansAdvances(CommonUtils
				.convertValueIndianCurrency(financialInputRequestDbl.getShortTermLoansAdvances()).toString());
		if (corporateFinalInfoRequest == null) {
			financialInputRequestDbl.setContingentLiablities(null);
			financialInputRequestString.setContingentLiablities(null);
		} else {
			financialInputRequestDbl.setContingentLiablities(
					CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getContLiabilityFyAmt()) ? 0.0
							: (corporateFinalInfoRequest.getContLiabilityFyAmt() * denomination));
			financialInputRequestString.setContingentLiablities(CommonUtils
					.convertValueIndianCurrency(financialInputRequestDbl.getContingentLiablities()).toString());
		}

		assetDetailsString.setOtherIncomeNeedTocCheckAsset(
				CommonUtils.convertValueIndianCurrency(assetsDetails.getOtherIncomeNeedTocCheckAsset()).toString());
		financialInputRequestDbl
				.setOtherIncomeNeedTocCheckAsset(assetsDetails.getOtherIncomeNeedTocCheckAsset() * denomination);
		financialInputRequestString.setOtherIncomeNeedTocCheckAsset(CommonUtils
				.convertValueIndianCurrency(financialInputRequestDbl.getOtherIncomeNeedTocCheckAsset()).toString());
		assetDetailsString.setCurrentRatio(CommonUtils.convertValue(assetsDetails.getCurrentRatio()).toString());

		/**************************************************
		 * OTHER CALCULATIONS
		 *******************************************************/
		// Profit & Loss Statement
		financialInputRequestDbl.setNetSale(CommonUtils.substractNumbers(financialInputRequestDbl.getGrossSales(),
				financialInputRequestDbl.getLessExciseDuity()));
		financialInputRequestString
				.setNetSale(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getNetSale()).toString());
		financialInputRequestDbl.setTotalExpenditure(CommonUtils.substractNumbers(
				CommonUtils.addNumbers(financialInputRequestDbl.getIncreaseDecreaseStock(),
						financialInputRequestDbl.getRawMaterialConsumed(),
						financialInputRequestDbl.getPowerAndFuelCost(), financialInputRequestDbl.getEmployeeCost(),
						financialInputRequestDbl.getGeneralAndAdminExpe(),
						financialInputRequestDbl.getSellingAndDistriExpe(), financialInputRequestDbl.getMiscelExpe()),
				financialInputRequestDbl.getLessExpeCapita()));
		financialInputRequestString.setTotalExpenditure(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTotalExpenditure()).toString());
		financialInputRequestDbl.setOperatingProfitExclOi(CommonUtils.substractNumbers(
				financialInputRequestDbl.getNetSale(), financialInputRequestDbl.getTotalExpenditure()));
		financialInputRequestString.setOperatingProfitExclOi(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getOperatingProfitExclOi()).toString());
		financialInputRequestDbl.setOperatingProfitEbitadOi(CommonUtils.addNumbers(
				financialInputRequestDbl.getOperatingProfitExclOi(), financialInputRequestDbl.getOtherIncome()));
		financialInputRequestString.setOperatingProfitEbitadOi(CommonUtils
				.convertValueIndianCurrency(financialInputRequestDbl.getOperatingProfitEbitadOi()).toString());
		financialInputRequestDbl.setPbdt(CommonUtils.substractNumbers(
				financialInputRequestDbl.getOperatingProfitEbitadOi(), financialInputRequestDbl.getInterest()));
		financialInputRequestString
				.setPbdt(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getPbdt()).toString());
		financialInputRequestDbl.setProfitBeforeTaxation(CommonUtils
				.substractNumbers(financialInputRequestDbl.getPbdt(), financialInputRequestDbl.getDepriciation()));
		financialInputRequestString.setProfitBeforeTaxation(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getProfitBeforeTaxation()).toString());
		financialInputRequestDbl.setProfitBeforeTax(CommonUtils.addNumbers(
				financialInputRequestDbl.getProfitBeforeTaxation(), financialInputRequestDbl.getExceptionalIncome()));
		financialInputRequestString.setProfitBeforeTax(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getProfitBeforeTax()).toString());
		financialInputRequestDbl
				.setProfitAfterTax(CommonUtils.substractNumbers(financialInputRequestDbl.getProfitBeforeTax(),
						financialInputRequestDbl.getProvisionForTax())
						+ financialInputRequestDbl.getOtherIncomeNeedTocCheckOp());
		financialInputRequestString.setProfitAfterTax(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getProfitAfterTax()).toString());
		if (financialInputRequestDbl.getDividendPayOut() == 0
				|| CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getDividendPayOut())
				|| financialInputRequestDbl.getShareFaceValue() == 0
				|| CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getShareFaceValue())
				|| financialInputRequestDbl.getShareCapital() == 0
				|| CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getShareCapital()))
			financialInputRequestString.setEquityDividend("0");
		else
			financialInputRequestString.setEquityDividend(CommonUtils.convertValueIndianCurrency(
					(financialInputRequestDbl.getDividendPayOut() * financialInputRequestDbl.getShareFaceValue()
							/ financialInputRequestDbl.getShareCapital()))
					.toString());

		if (financialInputRequestDbl.getShareFaceValue() != 0 && financialInputRequestDbl.getShareCapital() != 0) {
			double total = financialInputRequestDbl.getShareFaceValue() / financialInputRequestDbl.getShareCapital();
			if (!CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getProfitAfterTax())
					&& financialInputRequestDbl.getProfitAfterTax() != 0) {
				financialInputRequestString.setEarningPerShare(CommonUtils
						.convertValueIndianCurrency(financialInputRequestDbl.getProfitAfterTax() * total).toString());
			}
		}

		// Balance Sheet -Equities and Liabilities

		financialInputRequestDbl.setShareHolderFunds(CommonUtils.addNumbers(financialInputRequestDbl.getShareCapital(),
				financialInputRequestDbl.getShareWarrantOutstandings(), financialInputRequestDbl.getRevalationReserve(),
				financialInputRequestDbl.getOtherReserveAndSurplus()));
		financialInputRequestString.setShareHolderFunds(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getShareHolderFunds()).toString());
		financialInputRequestDbl
				.setTotalNonCurruntLiablities(CommonUtils.addNumbers(financialInputRequestDbl.getMinorityInterest(),
						financialInputRequestDbl.getSecuredLoans(), financialInputRequestDbl.getUnsecuredLoansOthers(),
						financialInputRequestDbl.getUnsecuredLoansPromoters(),
						financialInputRequestDbl.getDeferredTaxLiablities(),
						financialInputRequestDbl.getOtherLongTermLiablities(),
						financialInputRequestDbl.getOtherBorrowing(), financialInputRequestDbl.getLongTermProvision()));
		financialInputRequestString.setTotalNonCurruntLiablities(CommonUtils
				.convertValueIndianCurrency(financialInputRequestDbl.getTotalNonCurruntLiablities()).toString());
		financialInputRequestDbl.setTotalCurruntLiablities(CommonUtils.addNumbers(
				financialInputRequestDbl.getTradePayables(), financialInputRequestDbl.getOtherCurruntLiablities(),
				financialInputRequestDbl.getShortTermProvision()));
		financialInputRequestString.setTotalCurruntLiablities(CommonUtils
				.convertValueIndianCurrency(financialInputRequestDbl.getTotalCurruntLiablities()).toString());
		financialInputRequestDbl.setTotalLiablities(CommonUtils.addNumbers(
				financialInputRequestDbl.getShareHolderFunds(), financialInputRequestDbl.getTotalNonCurruntLiablities(),
				financialInputRequestDbl.getTotalCurruntLiablities(),
				financialInputRequestDbl.getOtherIncomeNeedTocCheckLia()));
		financialInputRequestString.setTotalLiablities(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTotalLiablities()).toString());

		// Balance Sheet -ASSETS
		financialInputRequestDbl.setNetBlock(CommonUtils.substractThreeNumbers(financialInputRequestDbl.getGrossBlock(),
				financialInputRequestDbl.getLessAccumulatedDepre(), financialInputRequestDbl.getImpairmentofAsset()));
		financialInputRequestString
				.setNetBlock(CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getNetBlock()).toString());
		financialInputRequestDbl.setTotalNonCurruntAsset(CommonUtils.addNumbers(
				financialInputRequestDbl.getCapitalWorkInProgress(), financialInputRequestDbl.getIntengibleAssets(),
				financialInputRequestDbl.getPreOperativeExpe(), financialInputRequestDbl.getAssetInTransit(),
				financialInputRequestDbl.getInvestmentInSubsidiaries(), financialInputRequestDbl.getOtherInvestment(),
				financialInputRequestDbl.getLongTermLoansAndAdva(), financialInputRequestDbl.getOtheNonCurruntAsset()));
		financialInputRequestString.setTotalNonCurruntAsset(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTotalNonCurruntAsset()).toString());
		financialInputRequestDbl.setTotalCurruntAsset(CommonUtils.addNumbers(financialInputRequestDbl.getInventories(),
				financialInputRequestDbl.getSundryDebtors(), financialInputRequestDbl.getCashAndBank(),
				financialInputRequestDbl.getOtherCurruntAsset(), financialInputRequestDbl.getShortTermLoansAdvances()));
		financialInputRequestString.setTotalCurruntAsset(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTotalCurruntAsset()).toString());
		financialInputRequestDbl.setTotalAsset(CommonUtils.addNumbers(financialInputRequestDbl.getNetBlock(),
				financialInputRequestDbl.getTotalCurruntAsset(), financialInputRequestDbl.getTotalNonCurruntAsset(),
				financialInputRequestDbl.getOtherIncomeNeedTocCheckAsset()));
		financialInputRequestString.setTotalAsset(
				CommonUtils.convertValueIndianCurrency(financialInputRequestDbl.getTotalAsset()).toString());
		if (financialInputRequestDbl.getShareFaceValue() != 0 && financialInputRequestDbl.getShareCapital() != 0) {
			double total = financialInputRequestDbl.getShareCapital() / financialInputRequestDbl.getShareFaceValue();
			if (!CommonUtils.isObjectNullOrEmpty(financialInputRequestDbl.getShareHolderFunds())
					&& financialInputRequestDbl.getShareHolderFunds() != 0) {
				financialInputRequestString.setBookValue(CommonUtils
						.convertValueIndianCurrency(financialInputRequestDbl.getShareHolderFunds() / total).toString());
			}
		}

		return new Object[] { osDetailsString, liabilitiesDetailsString, assetDetailsString,
				financialInputRequestString, financialInputRequestDbl };
	}

	public void calculateRatioAnalysis(Map<Integer, Object[]> financials, Long applicationId) {
		int currentYear = scoringService.getFinYear(applicationId);
		DecimalFormat decim = new DecimalFormat("###.##");
		Object[] curFinYear = financials.get(currentYear - 1);
		Object[] prevFinYear = financials.get(currentYear - 2);
		Object[] yrBeforePrevFinYear = financials.get(currentYear - 3);

		FinancialInputRequestString curFinYearString = (FinancialInputRequestString) curFinYear[curFinYear.length - 2];
		FinancialInputRequestString prevFinYearString = (FinancialInputRequestString) prevFinYear[prevFinYear.length
				- 2];
		FinancialInputRequestString yrBeforePrevFinYearString = (FinancialInputRequestString) yrBeforePrevFinYear[yrBeforePrevFinYear.length
				- 2];

		FinancialInputRequestDbl curFinYearDouble = (FinancialInputRequestDbl) curFinYear[curFinYear.length - 1];
		FinancialInputRequestDbl prevFinYearDouble = (FinancialInputRequestDbl) prevFinYear[prevFinYear.length - 1];
		FinancialInputRequestDbl yrBeforePrevFinYearDouble = (FinancialInputRequestDbl) yrBeforePrevFinYear[yrBeforePrevFinYear.length
				- 1];

		// CASH FLOW
		curFinYearDouble.setEbitda(curFinYearDouble.getOperatingProfitEbitadOi());
		prevFinYearDouble.setEbitda(prevFinYearDouble.getOperatingProfitEbitadOi());
		curFinYearString.setEbitda(CommonUtils.convertValue(curFinYearDouble.getEbitda()));
		prevFinYearString.setEbitda(CommonUtils.convertValue(prevFinYearDouble.getEbitda()));

		curFinYearDouble.setInterestPaid(curFinYearDouble.getInterest());
		prevFinYearDouble.setInterestPaid(prevFinYearDouble.getInterest());
		curFinYearString.setInterestPaid(CommonUtils.convertValue(curFinYearDouble.getInterestPaid()));
		prevFinYearString.setInterestPaid(CommonUtils.convertValue(prevFinYearDouble.getInterestPaid()));

		curFinYearDouble
				.setIncreaseWorkingCapital(curFinYearDouble.getInventories() + curFinYearDouble.getSundryDebtors()
						+ curFinYearDouble.getOtherCurruntAsset() - prevFinYearDouble.getInventories()
						- prevFinYearDouble.getSundryDebtors() - prevFinYearDouble.getOtherCurruntAsset()
						+ prevFinYearDouble.getTradePayables() + prevFinYearDouble.getOtherCurruntLiablities()
						+ prevFinYearDouble.getShortTermProvision() - curFinYearDouble.getTradePayables()
						- curFinYearDouble.getOtherCurruntLiablities() - curFinYearDouble.getShortTermProvision());
		prevFinYearDouble.setIncreaseWorkingCapital(prevFinYearDouble.getInventories()
				+ prevFinYearDouble.getSundryDebtors() + prevFinYearDouble.getOtherCurruntAsset()
				- yrBeforePrevFinYearDouble.getInventories() - yrBeforePrevFinYearDouble.getSundryDebtors()
				- yrBeforePrevFinYearDouble.getOtherCurruntAsset() + yrBeforePrevFinYearDouble.getTradePayables()
				+ yrBeforePrevFinYearDouble.getOtherCurruntLiablities()
				+ yrBeforePrevFinYearDouble.getShortTermProvision() - prevFinYearDouble.getTradePayables()
				- prevFinYearDouble.getOtherCurruntLiablities() - prevFinYearDouble.getShortTermProvision());
		curFinYearString
				.setIncreaseWorkingCapital(CommonUtils.convertValue(curFinYearDouble.getIncreaseWorkingCapital()));
		prevFinYearString
				.setIncreaseWorkingCapital(CommonUtils.convertValue(prevFinYearDouble.getIncreaseWorkingCapital()));

		curFinYearDouble.setTaxPaid(curFinYearDouble.getProvisionForTax());
		prevFinYearDouble.setTaxPaid(prevFinYearDouble.getProvisionForTax());
		curFinYearString.setTaxPaid(CommonUtils.convertValue(curFinYearDouble.getTaxPaid()));
		prevFinYearString.setTaxPaid(CommonUtils.convertValue(prevFinYearDouble.getTaxPaid()));

		curFinYearDouble.setCashFromOperating(curFinYearDouble.getEbitda() - curFinYearDouble.getInterestPaid()
				- curFinYearDouble.getIncreaseWorkingCapital() - curFinYearDouble.getTaxPaid());
		prevFinYearDouble.setCashFromOperating(prevFinYearDouble.getEbitda() - prevFinYearDouble.getInterestPaid()
				- prevFinYearDouble.getIncreaseWorkingCapital() - prevFinYearDouble.getTaxPaid());
		curFinYearString.setCashFromOperating(CommonUtils.convertValue(curFinYearDouble.getCashFromOperating()));
		prevFinYearString.setCashFromOperating(CommonUtils.convertValue(prevFinYearDouble.getCashFromOperating()));

		// RATIO ANALYSIS
		curFinYearString.setEbitadPercentage(CommonUtils.convertValue(
				CommonUtils.divideNumbers(curFinYearDouble.getOperatingProfitEbitadOi(), curFinYearDouble.getNetSale())
						* 100));
		prevFinYearString.setEbitadPercentage(CommonUtils.convertValue(CommonUtils
				.divideNumbers(prevFinYearDouble.getOperatingProfitEbitadOi(), prevFinYearDouble.getNetSale()) * 100));
		yrBeforePrevFinYearString.setEbitadPercentage(CommonUtils
				.convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getOperatingProfitEbitadOi(),
						yrBeforePrevFinYearDouble.getNetSale()) * 100));

		curFinYearString.setPatm(CommonUtils.convertValue(
				CommonUtils.divideNumbers(curFinYearDouble.getProfitAfterTax(), curFinYearDouble.getNetSale()) * 100));
		prevFinYearString.setPatm(CommonUtils.convertValue(
				CommonUtils.divideNumbers(prevFinYearDouble.getProfitAfterTax(), prevFinYearDouble.getNetSale())
						* 100));
		yrBeforePrevFinYearString.setPatm(
				CommonUtils.convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getProfitAfterTax(),
						yrBeforePrevFinYearDouble.getNetSale()) * 100));

		curFinYearString.setRoce(CommonUtils.convertValue(((curFinYearDouble.getOperatingProfitEbitadOi() * 2
				/ (CommonUtils.addNumbers(curFinYearDouble.getShareHolderFunds(),
						prevFinYearDouble.getShareHolderFunds(), curFinYearDouble.getTotalNonCurruntLiablities(),
						prevFinYearDouble.getTotalNonCurruntLiablities())))
				* 12 / curFinYearDouble.getNoOfMonth()) * 100));
		prevFinYearString.setRoce(CommonUtils.convertValue(((prevFinYearDouble.getOperatingProfitEbitadOi() * 2
				/ (CommonUtils.addNumbers(prevFinYearDouble.getShareHolderFunds(),
						yrBeforePrevFinYearDouble.getShareHolderFunds(),
						prevFinYearDouble.getTotalNonCurruntLiablities(),
						yrBeforePrevFinYearDouble.getTotalNonCurruntLiablities())))
				* 12 / prevFinYearDouble.getNoOfMonth()) * 100));
		yrBeforePrevFinYearString.setRoce("NA");

		curFinYearString.setAssetTurnOver(CommonUtils.convertValue(CommonUtils.divideNumbers(
				curFinYearDouble.getNetSale() * 12,
				(CommonUtils.multiplyNumbers(curFinYearDouble.getTotalAsset(), curFinYearDouble.getNoOfMonth())))));
		prevFinYearString.setAssetTurnOver(CommonUtils.convertValue(CommonUtils.divideNumbers(
				prevFinYearDouble.getNetSale() * 12,
				(CommonUtils.multiplyNumbers(prevFinYearDouble.getTotalAsset(), prevFinYearDouble.getNoOfMonth())))));
		yrBeforePrevFinYearString.setAssetTurnOver(
				CommonUtils.convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getNetSale() * 12,
						(CommonUtils.multiplyNumbers(yrBeforePrevFinYearDouble.getTotalAsset(),
								yrBeforePrevFinYearDouble.getNoOfMonth())))));

		curFinYearString.setInventoryTurnOver(CommonUtils.convertValue(
				CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(curFinYearDouble.getTotalExpenditure() * 12,
						(curFinYearDouble.getInventories() * curFinYearDouble.getNoOfMonth()))))));
		prevFinYearString.setInventoryTurnOver(CommonUtils.convertValue(CommonUtils.divideNumbers(365.0,
				(CommonUtils.divideNumbers(prevFinYearDouble.getTotalExpenditure() * 12,
						(prevFinYearDouble.getInventories() * prevFinYearDouble.getNoOfMonth()))))));
		yrBeforePrevFinYearString.setInventoryTurnOver(CommonUtils.convertValue(CommonUtils.divideNumbers(365.0,
				(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getTotalExpenditure() * 12,
						(yrBeforePrevFinYearDouble.getInventories() * yrBeforePrevFinYearDouble.getNoOfMonth()))))));

		curFinYearString.setDebtorsTurnover(CommonUtils.convertValue(
				CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(curFinYearDouble.getNetSale() * 12,
						(curFinYearDouble.getSundryDebtors() * curFinYearDouble.getNoOfMonth()))))));
		prevFinYearString.setDebtorsTurnover(CommonUtils.convertValue(
				CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(prevFinYearDouble.getNetSale() * 12,
						(prevFinYearDouble.getSundryDebtors() * prevFinYearDouble.getNoOfMonth()))))));
		yrBeforePrevFinYearString.setDebtorsTurnover(CommonUtils.convertValue(
				CommonUtils.divideNumbers(365.0, (CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getNetSale() * 12,
						(yrBeforePrevFinYearDouble.getSundryDebtors() * yrBeforePrevFinYearDouble.getNoOfMonth()))))));

		curFinYearString.setCreditorsTurnover(CommonUtils.convertValue((CommonUtils.divideNumbers(365.0,
				CommonUtils.divideNumbers((CommonUtils.addNumbers(curFinYearDouble.getRawMaterialConsumed(),
						curFinYearDouble.getPowerAndFuelCost())), curFinYearDouble.getTradePayables())))
				* 12 / curFinYearDouble.getNoOfMonth()));
		prevFinYearString
				.setCreditorsTurnover(CommonUtils.convertValue((CommonUtils.divideNumbers(365.0,
						CommonUtils.divideNumbers(
								(CommonUtils.addNumbers(prevFinYearDouble.getRawMaterialConsumed(),
										prevFinYearDouble.getPowerAndFuelCost())),
								prevFinYearDouble.getTradePayables())))
						* 12 / prevFinYearDouble.getNoOfMonth()));
		yrBeforePrevFinYearString.setCreditorsTurnover(CommonUtils.convertValue((CommonUtils.divideNumbers(365.0,
				CommonUtils.divideNumbers(
						(CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getRawMaterialConsumed(),
								yrBeforePrevFinYearDouble.getPowerAndFuelCost())),
						yrBeforePrevFinYearDouble.getTradePayables())))
				* 12 / yrBeforePrevFinYearDouble.getNoOfMonth()));

		curFinYearString
				.setSalesWorkingCapital(CommonUtils.convertValue((CommonUtils.divideNumbers(365.0,
						(CommonUtils.divideNumbers(curFinYearDouble.getNetSale(),
								(CommonUtils.addNumbers(curFinYearDouble.getInventories(),
										curFinYearDouble.getSundryDebtors()) - curFinYearDouble.getTradePayables())))))
						* 12 / curFinYearDouble.getNoOfMonth()));
		prevFinYearString.setSalesWorkingCapital(CommonUtils.convertValue((CommonUtils.divideNumbers(365.0,
				(CommonUtils.divideNumbers(prevFinYearDouble.getNetSale(),
						(CommonUtils.addNumbers(prevFinYearDouble.getInventories(),
								prevFinYearDouble.getSundryDebtors()) - prevFinYearDouble.getTradePayables())))))
				* 12 / prevFinYearDouble.getNoOfMonth()));
		yrBeforePrevFinYearString.setSalesWorkingCapital(CommonUtils.convertValue((CommonUtils.divideNumbers(365.0,
				(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getNetSale(),
						(CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getInventories(),
								yrBeforePrevFinYearDouble.getSundryDebtors())
								- yrBeforePrevFinYearDouble.getTradePayables())))))
				* 12 / yrBeforePrevFinYearDouble.getNoOfMonth()));

		curFinYearString.setNetSalesGrowthCapital(CommonUtils.convertValue(
				(CommonUtils.divideNumbers(curFinYearDouble.getNetSale(), prevFinYearDouble.getNetSale()) - 1) * 100));
		prevFinYearString.setNetSalesGrowthCapital(CommonUtils.convertValue(
				(CommonUtils.divideNumbers(prevFinYearDouble.getNetSale(), yrBeforePrevFinYearDouble.getNetSale()) - 1)
						* 100));
		yrBeforePrevFinYearString.setNetSalesGrowthCapital("NA");

		curFinYearString.setPatGrowthCapital(CommonUtils.convertValue(
				(CommonUtils.divideNumbers(curFinYearDouble.getProfitAfterTax(), prevFinYearDouble.getProfitAfterTax())
						- 1) * 100));
		prevFinYearString.setPatGrowthCapital(
				CommonUtils.convertValue((CommonUtils.divideNumbers(prevFinYearDouble.getProfitAfterTax(),
						yrBeforePrevFinYearDouble.getProfitAfterTax()) - 1) * 100));
		yrBeforePrevFinYearString.setPatGrowthCapital("NA");

		curFinYearDouble.setAdjustedTotalDebtEquity(Double.parseDouble(decim.format(CommonUtils.divideNumbers(
				(CommonUtils.substractThreeNumbers(curFinYearDouble.getTotalNonCurruntLiablities(),
						curFinYearDouble.getLongTermProvision(), curFinYearDouble.getUnsecuredLoansPromoters())),
				(CommonUtils.addNumbers(curFinYearDouble.getShareHolderFunds(),
						curFinYearDouble.getUnsecuredLoansPromoters()))))));
		prevFinYearDouble.setAdjustedTotalDebtEquity(Double.parseDouble(decim.format(CommonUtils.divideNumbers(
				(CommonUtils.substractThreeNumbers(prevFinYearDouble.getTotalNonCurruntLiablities(),
						prevFinYearDouble.getLongTermProvision(), prevFinYearDouble.getUnsecuredLoansPromoters())),
				(CommonUtils.addNumbers(prevFinYearDouble.getShareHolderFunds(),
						prevFinYearDouble.getUnsecuredLoansPromoters()))))));
		yrBeforePrevFinYearDouble.setAdjustedTotalDebtEquity(Double.parseDouble(decim.format(CommonUtils.divideNumbers(
				(CommonUtils.substractThreeNumbers(yrBeforePrevFinYearDouble.getTotalNonCurruntLiablities(),
						yrBeforePrevFinYearDouble.getLongTermProvision(),
						yrBeforePrevFinYearDouble.getUnsecuredLoansPromoters())),
				(CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getShareHolderFunds(),
						yrBeforePrevFinYearDouble.getUnsecuredLoansPromoters()))))));
		curFinYearString
				.setAdjustedTotalDebtEquity(CommonUtils.convertValue(curFinYearDouble.getAdjustedTotalDebtEquity()));
		prevFinYearString
				.setAdjustedTotalDebtEquity(CommonUtils.convertValue(prevFinYearDouble.getAdjustedTotalDebtEquity()));
		yrBeforePrevFinYearString.setAdjustedTotalDebtEquity(
				CommonUtils.convertValue(yrBeforePrevFinYearDouble.getAdjustedTotalDebtEquity()));

		curFinYearString.setGrowthDebtEquity(CommonUtils.convertValue(CommonUtils.divideNumbers(
				(CommonUtils.substractNumbers(curFinYearDouble.getAdjustedTotalDebtEquity(),
						prevFinYearDouble.getAdjustedTotalDebtEquity())),
				prevFinYearDouble.getAdjustedTotalDebtEquity()) * 100));
		prevFinYearString.setGrowthDebtEquity(CommonUtils.convertValue(CommonUtils.divideNumbers(
				(CommonUtils.substractNumbers(prevFinYearDouble.getAdjustedTotalDebtEquity(),
						yrBeforePrevFinYearDouble.getAdjustedTotalDebtEquity())),
				yrBeforePrevFinYearDouble.getAdjustedTotalDebtEquity()) * 100));
		yrBeforePrevFinYearString.setGrowthDebtEquity("NA");

		curFinYearString.setCurruntRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers(
				(CommonUtils.addNumbers(curFinYearDouble.getInventories(), curFinYearDouble.getSundryDebtors())),
				curFinYearDouble.getTradePayables())));
		prevFinYearString.setCurruntRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers(
				(CommonUtils.addNumbers(prevFinYearDouble.getInventories(), prevFinYearDouble.getSundryDebtors())),
				prevFinYearDouble.getTradePayables())));
		yrBeforePrevFinYearString
				.setCurruntRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers(
						(CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getInventories(),
								yrBeforePrevFinYearDouble.getSundryDebtors())),
						yrBeforePrevFinYearDouble.getTradePayables())));

		curFinYearString.setQuickRatioX(CommonUtils.convertValue(
				CommonUtils.divideNumbers(curFinYearDouble.getSundryDebtors(), curFinYearDouble.getTradePayables())));
		prevFinYearString.setQuickRatioX(CommonUtils.convertValue(
				CommonUtils.divideNumbers(prevFinYearDouble.getSundryDebtors(), prevFinYearDouble.getTradePayables())));
		yrBeforePrevFinYearString.setQuickRatioX(CommonUtils.convertValue(CommonUtils.divideNumbers(
				yrBeforePrevFinYearDouble.getSundryDebtors(), yrBeforePrevFinYearDouble.getTradePayables())));

		curFinYearString
				.setCashInterestCover(
						CommonUtils
								.convertValue(CommonUtils.divideNumbers(
										(CommonUtils.substractNumbers(curFinYearDouble.getOperatingProfitEbitadOi(),
												curFinYearDouble.getProvisionForTax())),
										curFinYearDouble.getInterest())));
		prevFinYearString
				.setCashInterestCover(
						CommonUtils
								.convertValue(CommonUtils.divideNumbers(
										(CommonUtils.substractNumbers(prevFinYearDouble.getOperatingProfitEbitadOi(),
												prevFinYearDouble.getProvisionForTax())),
										prevFinYearDouble.getInterest())));
		yrBeforePrevFinYearString
				.setCashInterestCover(CommonUtils.convertValue(CommonUtils.divideNumbers(
						(CommonUtils.substractNumbers(yrBeforePrevFinYearDouble.getOperatingProfitEbitadOi(),
								yrBeforePrevFinYearDouble.getProvisionForTax())),
						yrBeforePrevFinYearDouble.getInterest())));

		curFinYearString.setDebtEbitad(CommonUtils.convertValue(CommonUtils.divideNumbers(
				(CommonUtils.substractThreeNumbers(curFinYearDouble.getTotalNonCurruntLiablities(),
						curFinYearDouble.getUnsecuredLoansPromoters(), curFinYearDouble.getLongTermProvision())),
				(12 * CommonUtils.divideNumbers(curFinYearDouble.getOperatingProfitEbitadOi(),
						curFinYearDouble.getNoOfMonth())))));
		prevFinYearString.setDebtEbitad(CommonUtils.convertValue(CommonUtils.divideNumbers(
				(CommonUtils.substractThreeNumbers(prevFinYearDouble.getTotalNonCurruntLiablities(),
						prevFinYearDouble.getUnsecuredLoansPromoters(), prevFinYearDouble.getLongTermProvision())),
				(12 * CommonUtils.divideNumbers(prevFinYearDouble.getOperatingProfitEbitadOi(),
						prevFinYearDouble.getNoOfMonth())))));
		yrBeforePrevFinYearString.setDebtEbitad(CommonUtils.convertValue(CommonUtils.divideNumbers(
				(CommonUtils.substractThreeNumbers(yrBeforePrevFinYearDouble.getTotalNonCurruntLiablities(),
						yrBeforePrevFinYearDouble.getUnsecuredLoansPromoters(),
						yrBeforePrevFinYearDouble.getLongTermProvision())),
				(12 * CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getOperatingProfitEbitadOi(),
						yrBeforePrevFinYearDouble.getNoOfMonth())))));

		curFinYearString.setFreeReserveEquity(
				CommonUtils.convertValue(CommonUtils.divideNumbers(curFinYearDouble.getOtherReserveAndSurplus(),
						(CommonUtils.addNumbers(curFinYearDouble.getShareCapital(),
								curFinYearDouble.getShareWarrantOutstandings())))));
		prevFinYearString.setFreeReserveEquity(
				CommonUtils.convertValue(CommonUtils.divideNumbers(prevFinYearDouble.getOtherReserveAndSurplus(),
						(CommonUtils.addNumbers(prevFinYearDouble.getShareCapital(),
								prevFinYearDouble.getShareWarrantOutstandings())))));
		yrBeforePrevFinYearString.setFreeReserveEquity(CommonUtils
				.convertValue(CommonUtils.divideNumbers(yrBeforePrevFinYearDouble.getOtherReserveAndSurplus(),
						(CommonUtils.addNumbers(yrBeforePrevFinYearDouble.getShareCapital(),
								yrBeforePrevFinYearDouble.getShareWarrantOutstandings())))));

		curFinYearDouble.setCfoMargine(
				CommonUtils.divideNumbers(curFinYearDouble.getCashFromOperating(), curFinYearDouble.getNetSale())
						* 100);
		prevFinYearDouble.setCfoMargine(
				CommonUtils.divideNumbers(prevFinYearDouble.getCashFromOperating(), prevFinYearDouble.getNetSale())
						* 100);
		curFinYearString.setCfoMargine(CommonUtils.convertValue(curFinYearDouble.getCfoMargine()));
		prevFinYearString.setCfoMargine(CommonUtils.convertValue(prevFinYearDouble.getCfoMargine()));
		yrBeforePrevFinYearString.setCfoMargine("NA");

		curFinYearString.setGrowthCfoMargine(CommonUtils.convertValue(CommonUtils.divideNumbers(
				(CommonUtils.substractNumbers(curFinYearDouble.getCfoMargine(), prevFinYearDouble.getCfoMargine())),
				prevFinYearDouble.getCfoMargine()) * 100));
		prevFinYearString.setGrowthCfoMargine("NA");
		yrBeforePrevFinYearString.setGrowthCfoMargine("NA");

		curFinYear[curFinYear.length - 2] = curFinYearString;
		prevFinYear[prevFinYear.length - 2] = prevFinYearString;
		yrBeforePrevFinYear[yrBeforePrevFinYear.length - 2] = yrBeforePrevFinYearString;

		financials.put((currentYear - 1), curFinYear);
		financials.put((currentYear - 2), prevFinYear);
		financials.put((currentYear - 3), yrBeforePrevFinYear);
		logger.info("financials=========" + financials.toString());
	}
	
	public Map<String ,Object> getBranchDetails(Long applicationId ,Long userId ,Long proposalId){
		//Fetch Bank Details
		Map<String, Object> bankData = new HashMap<String, Object>();
		try {
			Long orgId = null;
			if(proposalId != null) {
				orgId = proposalDetailsRepository.getOrgIdByProposalId(proposalId);
			}else {
				orgId = ineligibleProposalDetailsRepository.getOrgId(applicationId);
			}
			List<Object[]> listBankData = commonRepository.getBankDetails(applicationId, orgId);
			if(!CommonUtils.isListNullOrEmpty(listBankData) && !CommonUtils.isObjectNullOrEmpty(listBankData.get(0))) {
				
				String bankAddress = (listBankData.get(0)[5] != null ? listBankData.get(0)[5] : "") + (listBankData.get(0)[6] != null ? " ," + listBankData.get(0)[6] : "") 
						+ (listBankData.get(0)[7] != null ? " ," +listBankData.get(0)[7] : "") + (listBankData.get(0)[8] != null ? " - " + listBankData.get(0)[8] : "");
				bankData.put("currentBankAddress", !CommonUtils.isObjectNullOrEmpty(bankAddress) ? StringEscapeUtils.escapeXml(bankAddress) : "-");
				bankData.put("bankName", listBankData.get(0)[9] != null ? listBankData.get(0)[9] : "-");
				if(listBankData.size() > 1 && !CommonUtils.isObjectNullOrEmpty(listBankData.get(1))) {
					String prevBankAddress = (listBankData.get(1)[5] != null ? listBankData.get(1)[5] : "") + (listBankData.get(1)[6] != null ? " ," + listBankData.get(1)[6] : "") 
							+ (listBankData.get(1)[7] != null ? " ," +listBankData.get(1)[7] : "") + (listBankData.get(1)[8] != null ? " - " + listBankData.get(1)[8] : "");
					bankData.put("previousBankAddress", !CommonUtils.isObjectNullOrEmpty(bankAddress) ? StringEscapeUtils.escapeXml(prevBankAddress) : "-");
				}
			}
			
			LocalDate today = LocalDate.now();
            List<BankRelationshipRequest> bankRelationshipRequests = new ArrayList<>();
            List<BankingRelation> bankingRelations = bankingRelationlRepository.listBankRelationAppId(applicationId);
            for(BankingRelation bankingRelation : bankingRelations) {
            	BankRelationshipRequest bankRelationshipRequest	= new BankRelationshipRequest();
            	BeanUtils.copyProperties(bankingRelation, bankRelationshipRequest);
            	if (bankingRelation.getSinceYear() != null && bankingRelation.getSinceMonth() != null) {
					LocalDate since = LocalDate.of(bankingRelation.getSinceYear(), bankingRelation.getSinceMonth(),1);
					Period age = Period.between(since, today);
					bankRelationshipRequest.setSinceYear(age.getYears());
					bankRelationshipRequest.setSinceMonth(age.getMonths());
					bankRelationshipRequest.setSinceWhen((bankRelationshipRequest.getSinceYear() != null ? bankRelationshipRequest.getSinceYear() +" year " : "") + " " +(bankRelationshipRequest.getSinceMonth() != null ? bankRelationshipRequest.getSinceMonth()+" months" :  "" ));
				}
            	bankRelationshipRequests.add(bankRelationshipRequest);
            }
            bankData.put("bankingRelationShip", bankRelationshipRequests);
			
			try {
				Object[] campaignDetails = loanRepoImpl.getApplicationCampaignDetails(applicationId);
				if(campaignDetails != null && campaignDetails.length > 0) {
					if(campaignDetails.length > 2 && campaignDetails[2] != null) { // loanCampCode
						bankData.put("typeOfUser", "Bank Specific");
					}else if (campaignDetails[3] != null && CommonUtils.convertString(campaignDetails[3]).equalsIgnoreCase("cii")) {
						bankData.put("typeOfUser", "Market Place");
					}else {
						bankData.put("typeOfUser", "Market Place");
					}
				}
	        } catch (Exception e2) {
	            logger.info("error while campaign user check ==>" , e2);
	        }
			
			return !bankData.isEmpty() ? bankData : null;
		}catch (Exception e) {
			logger.error("Error/Exception while getting Bank Details Of ApplicationId==>{}  ..Error==>{}",applicationId ,e);
		}
		return null;
	}
	
	public void setAssociateAddress(AssociatedConcernDetailRequest asso) {
		String address = "";
		
		if (!CommonUtils.isObjectListNull(asso.getPremiseNumber())) {
			address = address + asso.getPremiseNumber();
		}
		
		if (!CommonUtils.isObjectListNull(asso.getLandMark())) {
			address = address + ", " + asso.getLandMark();
		}
		
		Long cityId = asso.getCityId() ;
		Integer stateId = asso.getStateId();
		Integer countryId = asso.getCountryId();
		
		if(cityId != null || stateId != null || countryId != null) {
			Map<String ,Object> mapData = commonService.getCityStateCountryNameFromOneForm(cityId, stateId, countryId);
			
			if(mapData != null) {	
				String cityName = mapData.get(CommonUtils.CITY_NAME).toString();
				String stateName = mapData.get(CommonUtils.STATE_NAME).toString();
				String countryName = mapData.get(CommonUtils.COUNTRY_NAME).toString();
				
				if (cityName != null) {					
					address = address + ", " + cityName; 
				}
				if (stateName != null) {					
					address = address + ", " + stateName; 
				}
				if (countryName != null) {					
					address = address + ", " + countryName; 
				}
			}
		}
		asso.setAddress(address);
	}	

}
