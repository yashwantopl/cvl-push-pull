package com.capitaworld.service.loans.service.teaser.finalview.impl;

import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.model.*;
import com.capitaworld.service.loans.model.corporate.FinalWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.model.retail.PastFinancialEstimatesDetailRequest;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;
import com.capitaworld.service.loans.model.teaser.finalview.*;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.common.DocumentManagementService;
import com.capitaworld.service.loans.service.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.fundseeker.retail.ReferenceRetailDetailsService;
import com.capitaworld.service.loans.service.teaser.finalview.WorkingCapitalFinalService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.*;
import com.capitaworld.service.oneform.model.IndustrySectorSubSectorTeaserRequest;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dhaval on 25-May-17.
 */
@Service
@Transactional
public class WorkingCapitalFinalServiceImpl implements WorkingCapitalFinalService {

	@Autowired
	private BoardOfDirectorsDetailRepository boardOfDirectorsDetailRepository;

	@Autowired
	private StrategicAlliancesDetailRepository strategicAlliancesDetailRepository;

	@Autowired
	private KeyManagementDetailRepository keyManagementDetailRepository;

	@Autowired
	private EmployeesCategoryBreaksDetailRepository employeesCategoryBreaksDetailRepository;

	@Autowired
	private TechnologyPositioningDetailRepository technologyPositioningDetailRepository;

	@Autowired
	private RevenueAndOrderBookDetailRepository revenueAndOrderBookDetailRepository;

	@Autowired
	private CapacityDetailRepository capacityDetailRepository;

	@Autowired
	private AvailabilityProposedPlantDetailRepository availabilityProposedPlantDetailRepository;

	@Autowired
	private RequirementsAndAvailabilityRawMaterialsDetailRepository requirementsAndAvailabilityRawMaterialsDetailRepository;

	@Autowired
	private ScotAnalysisDetailRepository scotAnalysisDetailRepository;

	@Autowired
	private DprUserDataDetailRepository dprUserDataDetailRepository;

	@Autowired
	private DocumentManagementService documentManagementService;

	@Autowired
	private DriverForFutureGrowthDetailRepository driverForFutureGrowthDetailRepository;

	@Autowired
	private ProjectImplementationScheduleDetailRepository projectImplementationScheduleDetailRepository;

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private PrimaryWorkingCapitalLoanDetailRepository primaryWorkingCapitalLoanDetailRepository;

	@Autowired
	private ProposedProductDetailsService proposedProductDetailsService;

	@Autowired
	private AchievmentDetailsService achievmentDetailsService;

	@Autowired
	private CreditRatingOrganizationDetailsService creditRatingOrganizationDetailsService;

	@Autowired
	private OwnershipDetailsService ownershipDetailsService;

	@Autowired
	private PromotorBackgroundDetailsService promotorBackgroundDetailsService;

	@Autowired
	private FutureFinancialEstimatesDetailsService futureFinancialEstimatesDetailsService;

	@Autowired
	private PastFinancialEstiamateDetailsService pastFinancialEstiamateDetailsService;

	@Autowired
	private ExistingProductDetailsService existingProductDetailsService;

	@Autowired
	private SecurityCorporateDetailsService securityCorporateDetailsService;

	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;

	@Autowired
	private IndustrySectorRepository industrySectorRepository;

	@Autowired
	private SubSectorRepository subSectorRepository;

	@Autowired
	private AssociatedConcernDetailService associatedConcernDetailService;

	@Autowired
	private GuarantorsCorporateDetailService guarantorsCorporateDetailService;

	@Autowired
	private MonthlyTurnoverDetailService monthlyTurnoverDetailService;

	@Autowired
	private FinalWorkingCapitalLoanService finalWorkingCapitalLoanService;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private UsersClient usersClient;

	@Autowired
	private PastFinancialEstimateDetailsRepository pastFinancialEstimateDetailsRepository;
	
	@Autowired
	private DirectorBackgroundDetailsService directorBackgroundDetailsService;
	
	@Autowired
	private ReferenceRetailDetailsService referenceRetailDetailsService;

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	private static final Logger logger = LoggerFactory.getLogger(WorkingCapitalFinalServiceImpl.class);

	@Override
	public WorkingCapitalFinalViewResponse getWorkingCapitalFinalViewDetails(Long toApplicationId) {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(toApplicationId);
		Long userId = applicationMaster.getUserId();

		// create response object
		WorkingCapitalFinalViewResponse response = new WorkingCapitalFinalViewResponse();

		//List<Object> dprList = new ArrayList<Object>();
		// get list of uploads of final and profile picture
		try {
			response.setProfilePic(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		/*
		* FINAL UPLOADS
		* */
		/* FINANCIAL */
		try{
			response.setLastAuditedAnnualReportList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_LAST_AUDITED_ANNUAL_REPORT));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try{
			response.setSanctionLetterCopyList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_SANCTION_LETTER_COPY));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try{
			response.setLastITReturnList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_LAST_IT_RETURN));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try{
			response.setBankStatementList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_BANK_STATEMENT));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try{
			response.setNetWorthStatementOfdirectorsList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_NET_WORTH_STATEMENT_OF_DIRECTORS));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try{
			response.setProvisionalFinancialsList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_PROVISIONAL_FINANCIALS));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try {
			response.setBrochureList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WORKING_CAPITAL_BROCHURE_OF_PROPOSED_ACTIVITIES)));
		} catch (DocumentException e) {
			e.printStackTrace();
		}try {
			response.setItReturnForFYOfAllDirectorsList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WORKING_CAPITAL_IT_RETURN_DIRECTOR)));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try {
			response.setFinSubsidiariesEntitiesList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WORKING_CAPITAL_FINANCIALS_OF_SUBSIDIARIES)));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try {
			response.setAssessOrderForLastThreeYearsList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WORKING_CAPITAL_ASSESSMENT_ORDERS)));
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		/* KYC UPLOADS */
        try {
            response.setCertificateOfIncorpList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WORKING_CAPITAL_CERTIFICATE_OF_INCORPORATION)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try{
            response.setDetailedListOfShareholdersList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_DETAILED_LIST_OF_SHAREHOLDERS));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            response.setPanCardList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WORKING_CAPITAL_COPY_OF_PAN_CARD)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try{
            response.setPhotoOfDirectorsList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_PHOTO_OF_DIRECTORS));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
		try{
			response.setPanOfDirectorsList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT,DocumentAlias.WORKING_CAPITAL_PAN_OF_DIRECTORS_CERTIFICATE_OF_INCORPORATION));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        try{
            response.setResidenceAddProofList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT,DocumentAlias.WORKING_CAPITAL_DIRECTOR_ADDRESS));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            response.setResolutionForAdditionOfDirectorsList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WORKING_CAPITAL_DIRECTOR_RESOLUTION)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        /* OTHERS UPLOADS */
        try {
            response.setMomAndAOAList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_MOM_AOA)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            response.setDebtorsList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_DEBTORS_LIST)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            response.setGstVATExciseList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_GST_APPLIED)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            response.setLetterOfIntentFromFPList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_LETTER_OF_INTENT)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            response.setCopiesOfRelevantLicenseList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_RELEVANT_LICENSE)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            response.setSalesTaxReturnsList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_SALES_TAX)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            response.setLatestTaxPaidCoyList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_LATEST_TAX)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            response.setEncumbranceList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_ENCUMBRANCE)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            response.setCopiesOfTrustDeedList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_COPIES_TRUST_DEEDS)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            response.setMarketSurveyReportList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_MARKET_SURVEY_REPORT)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            response.setDetailsOfContLiabilitiesList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_CONTINGENT_LIABILITIES)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        /*
         * DPR, CMA, FINANCIAL MODELS
         * */
		try{
			response.setCmaList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_CMA)));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try{
			response.setBsFormatList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_COMPANY_ACT)));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try{
			response.setFinancialModelList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WC_FINANCIAL_MODEL));
		} catch (DocumentException e) {
			e.printStackTrace();
		}


		

		/*// if DPR our format not upload no need get data of DPR
		if (dprList.size() > 0) {
			response.setIsDprUploaded(true);
			List<BoardOfDirectorsResponse> boardOfDirectorsResponseList = boardOfDirectorsDetailRepository
					.listByApplicationId(toApplicationId);
			List<StrategicAlliancesResponse> strategicAlliancesResponseList = strategicAlliancesDetailRepository
					.listByApplicationId(toApplicationId);
			List<KeyManagementResponse> keyManagementResponseList = keyManagementDetailRepository
					.listByApplicationId(toApplicationId);
			List<EmployeesCategoryBreaksResponse> employeesCategoryBreaksResponseList = employeesCategoryBreaksDetailRepository
					.listByApplicationId(toApplicationId);
			List<TechnologyPositioningResponse> technologyPositioningResponseList = technologyPositioningDetailRepository
					.listByApplicationId(toApplicationId);
			List<RevenueAndOrderBookResponse> revenueAndOrderBookResponseList = revenueAndOrderBookDetailRepository
					.listByApplicationId(toApplicationId);
			DriverForFutureGrowthResponse driverForFutureGrowthResponse = driverForFutureGrowthDetailRepository
					.listByApplicationId(toApplicationId).size() > 0
							? driverForFutureGrowthDetailRepository.listByApplicationId(toApplicationId).get(0) : null;
			List<ProjectImplementationScheduleResponse> projectImplementationScheduleResponseList = projectImplementationScheduleDetailRepository
					.listByApplicationId(toApplicationId);
			List<CapacityDetailResponse> capacityDetailResponses = capacityDetailRepository
					.listByApplicationId(toApplicationId);
			List<AvailabilityProposedPlantDetailResponse> availabilityProposedPlantDetailResponses = availabilityProposedPlantDetailRepository
					.listByApplicationId(toApplicationId);
			List<RequirementsAndAvailabilityRawMaterialsDetailResponse> requirementsAndAvailabilityRawMaterialsDetailResponses = requirementsAndAvailabilityRawMaterialsDetailRepository
					.listByApplicationId(toApplicationId);
			ScotAnalysisDetailResponse scotAnalysisDetailResponses = scotAnalysisDetailRepository
					.listByApplicationId(toApplicationId).size() > 0
							? scotAnalysisDetailRepository.listByApplicationId(toApplicationId).get(0) : null;
			DprUserDataDetailResponse dprUserDataDetailResponses = dprUserDataDetailRepository
					.listByApplicationId(toApplicationId).size() > 0
							? dprUserDataDetailRepository.listByApplicationId(toApplicationId).get(0) : null;

			response.setAvailabilityProposedPlantDetailResponse(availabilityProposedPlantDetailResponses);
			response.setBoardOfDirectorsResponseList(boardOfDirectorsResponseList);
			response.setCapacityDetailResponses(capacityDetailResponses);
			response.setDprUserDataDetailResponses(dprUserDataDetailResponses);
			response.setEmployeesCategoryBreaksResponseList(employeesCategoryBreaksResponseList);
			response.setKeyManagementResponseList(keyManagementResponseList);
			response.setRequirementsAndAvailabilityRawMaterialsDetailResponse(
					requirementsAndAvailabilityRawMaterialsDetailResponses);
			response.setRevenueAndOrderBookResponseList(revenueAndOrderBookResponseList);
			response.setScotAnalysisDetailResponses(scotAnalysisDetailResponses);
			response.setStrategicAlliancesResponseList(strategicAlliancesResponseList);
			response.setTechnologyPositioningResponseList(technologyPositioningResponseList);
			response.setProjectImplementationScheduleResponseList(projectImplementationScheduleResponseList);
			response.setDriverForFutureGrowthResponse(driverForFutureGrowthResponse);

			// set final working capital information

		} else {
			response.setIsDprUploaded(false);
		}*/
		// final information
		try {
			FinalWorkingCapitalLoanRequest finalWorkingCapitalLoanRequest = finalWorkingCapitalLoanService.get(userId,
					toApplicationId);

			if (!CommonUtils.isObjectNullOrEmpty(finalWorkingCapitalLoanRequest)) {
				response.setTechnologyType(finalWorkingCapitalLoanRequest.getTechnologyTypeId() != null ? TypeTechnology.getById(finalWorkingCapitalLoanRequest.getTechnologyTypeId()).getValue() : null);
				response.setTechnologyPatented(finalWorkingCapitalLoanRequest.getTechnologyPatentedId() != null ? TechnologyPatented.getById(finalWorkingCapitalLoanRequest.getTechnologyPatentedId()).getValue() : null);
				response.setTechnologyRequiresUpgradation(finalWorkingCapitalLoanRequest.getTechnologyRequiresUpgradationId() != null ? TechnologyRequiresUpgradation.getById(finalWorkingCapitalLoanRequest.getTechnologyRequiresUpgradationId()).getValue() : null);
				response.setMarketPosition(finalWorkingCapitalLoanRequest.getMarketPositionId() != null ? MarketPosition.getById(finalWorkingCapitalLoanRequest.getMarketPositionId()).getValue() : null);
				response.setMarketingPositioning(finalWorkingCapitalLoanRequest.getMarketingPositioningId() != null ? MarketingPositioningNew.getById(finalWorkingCapitalLoanRequest.getMarketingPositioningId()).getValue() : null);
				response.setMarketPositioningTop(finalWorkingCapitalLoanRequest.getMarketPositioningTopId() != null ? MarketPositioningTop.getById(finalWorkingCapitalLoanRequest.getMarketPositioningTopId()).getValue() : null);
				response.setMarketShareTurnover(finalWorkingCapitalLoanRequest.getMarketShareTurnoverId() != null ? MarketShareTurnover.getById(finalWorkingCapitalLoanRequest.getMarketShareTurnoverId()).getValue() : null);
				response.setIndiaDistributionNetwork(finalWorkingCapitalLoanRequest.getIndiaDistributionNetworkId() != null ? IndiaDistributionNetwork.getById(finalWorkingCapitalLoanRequest.getIndiaDistributionNetworkId()).getValue() : null);
				response.setDistributionAndTieUps(finalWorkingCapitalLoanRequest.getDistributionAndMarketingTieUpsId() != null ? DistributionMarketingTieUps.getById(finalWorkingCapitalLoanRequest.getDistributionAndMarketingTieUpsId()).getValue() : null);
				response.setBrandAmbassador(finalWorkingCapitalLoanRequest.getBrandAmbassadorId() != null ? BrandAmbassador.getById(finalWorkingCapitalLoanRequest.getBrandAmbassadorId()).getValue() : null);
				response.setProductServicesPerse(finalWorkingCapitalLoanRequest.getProductServicesPerseId() != null ? ProductServicesPerse.getById(finalWorkingCapitalLoanRequest.getProductServicesPerseId()).getValue() : null);
				response.setEnvironmentCertification(finalWorkingCapitalLoanRequest.getEnvironmentCertificationId() != null ? EnvironmentCertification.getById(finalWorkingCapitalLoanRequest.getEnvironmentCertificationId()).getValue() : null);
				response.setAccountingSystems(finalWorkingCapitalLoanRequest.getAccountingSystemsId() != null ? AccountingSystems.getById(finalWorkingCapitalLoanRequest.getAccountingSystemsId()).getValue() : null);
				response.setInternalAudit(finalWorkingCapitalLoanRequest.getInternalAuditId() != null ? InternalAudit.getById(finalWorkingCapitalLoanRequest.getInternalAuditId()).getValue() : null);
				response.setCompetence(finalWorkingCapitalLoanRequest.getCompetenceId() != null ? Competence.getById(finalWorkingCapitalLoanRequest.getCompetenceId()).getValue() : null);
				response.setExistingShareHolder(finalWorkingCapitalLoanRequest.getExistingShareHoldersId() != null ? ExistingShareholders.getById(finalWorkingCapitalLoanRequest.getExistingShareHoldersId()).getValue() : null);
				//NEW MCQ FOR NHBS
				response.setTechnologyRiskId(finalWorkingCapitalLoanRequest.getTechnologyRiskId() != null ? TechnologyRisk.getById(finalWorkingCapitalLoanRequest.getTechnologyRiskId()).getValue() : null);
				response.setCustomerQuality(finalWorkingCapitalLoanRequest.getCustomerQuality() != null ? CustomerQuality.getById(finalWorkingCapitalLoanRequest.getCustomerQuality()).getValue() : null);
				response.setSupplierQuality(finalWorkingCapitalLoanRequest.getSupplierQuality() != null ? SupplierQuality.getById(finalWorkingCapitalLoanRequest.getSupplierQuality()).getValue() : null);
				response.setSustainabilityProduct(finalWorkingCapitalLoanRequest.getSustainabilityProduct() != null ? SustainabilityProduct.getById(finalWorkingCapitalLoanRequest.getSustainabilityProduct()).getValue() : null);
				response.setEmployeeRelations(finalWorkingCapitalLoanRequest.getEmployeeRelations() != null ? IndustrialRelations.getById(finalWorkingCapitalLoanRequest.getEmployeeRelations()).getValue() : null);
				response.setProductSeasonality(finalWorkingCapitalLoanRequest.getProductSeasonality() != null ? ProductSeasonality.getById(finalWorkingCapitalLoanRequest.getProductSeasonality()).getValue() : null);
				response.setImpactOnOperatingMargins(finalWorkingCapitalLoanRequest.getImpactOnOperatingMargins() != null ? OperatingMargins.getById(finalWorkingCapitalLoanRequest.getImpactOnOperatingMargins()).getValue() : null);
				response.setOrderBookPosition(finalWorkingCapitalLoanRequest.getOrderBookPosition() != null ? OrderBook.getById(finalWorkingCapitalLoanRequest.getOrderBookPosition()).getValue() : null);
				response.setEnvironmentalImpact(finalWorkingCapitalLoanRequest.getEnvironmentalImpact() != null ? EnvironmentalImpact.getById(finalWorkingCapitalLoanRequest.getEnvironmentalImpact()).getValue() : null);
				response.setAccountingQuality(finalWorkingCapitalLoanRequest.getAccountingQuality() != null ? AccountingQuality.getById(finalWorkingCapitalLoanRequest.getAccountingQuality()).getValue() : null);
				response.setFinancialRestructuringHistory(finalWorkingCapitalLoanRequest.getFinancialRestructuringHistory() != null ? FinancialRestructuring.getById(finalWorkingCapitalLoanRequest.getFinancialRestructuringHistory()).getValue() : null);
				response.setIntegrity(finalWorkingCapitalLoanRequest.getIntegrity() != null ? Integrity.getById(finalWorkingCapitalLoanRequest.getIntegrity()).getValue() : null);
				response.setBusinessCommitment(finalWorkingCapitalLoanRequest.getBusinessCommitment() != null ? BusinessCommitment.getById(finalWorkingCapitalLoanRequest.getBusinessCommitment()).getValue() : null);
				response.setManagementCompetence(finalWorkingCapitalLoanRequest.getManagementCompetence() != null ? ManagementCompetence.getById(finalWorkingCapitalLoanRequest.getManagementCompetence()).getValue() : null);
				response.setBusinessExperience(finalWorkingCapitalLoanRequest.getBusinessExperience() != null ? BusinessExperience.getById(finalWorkingCapitalLoanRequest.getBusinessExperience()).getValue() : null);
				response.setSuccessionPlanning(finalWorkingCapitalLoanRequest.getSuccessionPlanning() != null ? SuccessionPlanning.getById(finalWorkingCapitalLoanRequest.getSuccessionPlanning()).getValue() : null);
				response.setFinancialStrength(finalWorkingCapitalLoanRequest.getFinancialStrength() != null ? FinancialSupport.getById(finalWorkingCapitalLoanRequest.getFinancialStrength()).getValue() : null);
				response.setAbilityToRaiseFunds(finalWorkingCapitalLoanRequest.getAbilityToRaiseFunds() != null ? AbilityRaiseFunds.getById(finalWorkingCapitalLoanRequest.getAbilityToRaiseFunds()).getValue() : null);
				response.setIntraCompany(finalWorkingCapitalLoanRequest.getIntraCompany() != null ? CompanyConflicts.getById(finalWorkingCapitalLoanRequest.getIntraCompany()).getValue() : null);
				response.setInternalControl(finalWorkingCapitalLoanRequest.getInternalControl() != null ? InternalControl.getById(finalWorkingCapitalLoanRequest.getInternalControl()).getValue() : null);
				response.setCreditTrackRecord(finalWorkingCapitalLoanRequest.getCreditTrackRecord() != null ? CreditRecord.getById(finalWorkingCapitalLoanRequest.getCreditTrackRecord()).getValue() : null);
				response.setStatusOfProjectClearances(finalWorkingCapitalLoanRequest.getStatusOfProjectClearances() != null ? StatusClearances.getById(finalWorkingCapitalLoanRequest.getStatusOfProjectClearances()).getValue() : null);
				response.setStatusOfFinancialClosure(finalWorkingCapitalLoanRequest.getStatusOfFinancialClosure() != null ? StatusFinancialClosure.getById(finalWorkingCapitalLoanRequest.getStatusOfFinancialClosure()).getValue() : null);
				response.setInfrastructureAvailability(finalWorkingCapitalLoanRequest.getInfrastructureAvailability() != null ? InfrastructureAvailability.getById(finalWorkingCapitalLoanRequest.getInfrastructureAvailability()).getValue() : null);
				response.setConstructionContract(finalWorkingCapitalLoanRequest.getConstructionContract() != null ? ConstructionContract.getById(finalWorkingCapitalLoanRequest.getConstructionContract()).getValue() : null);
				response.setNumberOfCheques(finalWorkingCapitalLoanRequest.getNumberOfCheques() != null ? ChequesReturned.getById(finalWorkingCapitalLoanRequest.getNumberOfCheques()).getValue() : null);
				response.setNumberOfTimesDp(finalWorkingCapitalLoanRequest.getNumberOfTimesDp() != null ? LimitOverdrawn.getById(finalWorkingCapitalLoanRequest.getNumberOfTimesDp()).getValue() : null);
				response.setCumulativeNoOfDaysDp(finalWorkingCapitalLoanRequest.getCumulativeNoOfDaysDp() != null ? CumulativeOverdrawn.getById(finalWorkingCapitalLoanRequest.getCumulativeNoOfDaysDp()).getValue() : null);
				response.setComplianceWithSanctioned(finalWorkingCapitalLoanRequest.getComplianceWithSanctioned() != null ? ComplianceConditions.getById(finalWorkingCapitalLoanRequest.getComplianceWithSanctioned()).getValue() : null);
				response.setProgressReports(finalWorkingCapitalLoanRequest.getProgressReports() != null ? SubmissionReports.getById(finalWorkingCapitalLoanRequest.getProgressReports()).getValue() : null);
				response.setDelayInReceipt(finalWorkingCapitalLoanRequest.getDelayInReceipt() != null ? DelayInstalments.getById(finalWorkingCapitalLoanRequest.getDelayInReceipt()).getValue() : null);
				response.setDelayInSubmission(finalWorkingCapitalLoanRequest.getDelayInSubmission() != null ? DelaySubmission.getById(finalWorkingCapitalLoanRequest.getDelayInSubmission()).getValue() : null);
				response.setNumberOfLc(finalWorkingCapitalLoanRequest.getNumberOfLc() != null ? BorrowerInvoked.getById(finalWorkingCapitalLoanRequest.getNumberOfLc()).getValue() : null);
				response.setUnhedgedForeignCurrency(finalWorkingCapitalLoanRequest.getUnhedgedForeignCurrency() != null ? UnhedgedCurrency.getById(finalWorkingCapitalLoanRequest.getUnhedgedForeignCurrency()).getValue() : null);
				response.setProjectedDebtService(finalWorkingCapitalLoanRequest.getProjectedDebtService() != null ? ProjectedRatio.getById(finalWorkingCapitalLoanRequest.getProjectedDebtService()).getValue() : null);
				response.setInternalRateReturn(finalWorkingCapitalLoanRequest.getInternalRateReturn() != null ? InternalReturn.getById(finalWorkingCapitalLoanRequest.getInternalRateReturn()).getValue() : null);
				response.setSensititivityAnalysis(finalWorkingCapitalLoanRequest.getSensititivityAnalysis() != null ? SensititivityAnalysis.getById(finalWorkingCapitalLoanRequest.getSensititivityAnalysis()).getValue() : null);
				response.setVarianceInProjectedSales(finalWorkingCapitalLoanRequest.getVarianceInProjectedSales() != null ? VarianceSales.getById(finalWorkingCapitalLoanRequest.getVarianceInProjectedSales()).getValue() : null);
				if (finalWorkingCapitalLoanRequest.getIsIsoCertified()) {
					response.setIsIsoCertified("Yes");
				} else {
					response.setIsIsoCertified("No");
				}
				if (finalWorkingCapitalLoanRequest.getWhetherTechnologyIsTied()) {
					response.setWhetherTechnologyIsTied("Yes");
				} else {
					response.setWhetherTechnologyIsTied("No");
				}
				if (finalWorkingCapitalLoanRequest.getIsDependsMajorlyOnGovernment()) {
					response.setMajorlyOnGovernment("Yes");
				} else {
					response.setMajorlyOnGovernment("No");
				}
				// set overseas
				List<Integer> overseasIds = finalWorkingCapitalLoanRequest.getOverseasNetworkIds();
				String overseasString = "";
				for (int id : overseasIds) {
					overseasString += OverseasNetwork.getById(id).getValue() + ",";
				}
				response.setOverseasNetwork(overseasString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// set registered email address and registered contact number
		UserResponse userResponse = usersClient.getEmailMobile(userId);
		try {
			UsersRequest usersRequest = MultipleJSONObjectHelper
					.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
			if (usersRequest != null) {
				response.setRegisteredEmailAddress(usersRequest.getEmail());
				response.setRegisteredContactNumber(usersRequest.getMobile());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// get details of CorporateApplicantDetail
		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
				.getByApplicationAndUserId(userId, toApplicationId);
		// set value to response
		if (corporateApplicantDetail != null) {
			BeanUtils.copyProperties(corporateApplicantDetail, response);
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getConstitutionId()))
				response.setConstitution(Constitution.getById(corporateApplicantDetail.getConstitutionId()).getValue());
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getEstablishmentMonth()))
				response.setEstablishmentMonth(
						EstablishmentMonths.getById(corporateApplicantDetail.getEstablishmentMonth()).getValue());

			// set Establishment year
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getEstablishmentYear())) {
				try {
					OneFormResponse establishmentYearResponse = oneFormClient.getYearByYearId(
							CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getEstablishmentYear()) ? null
									: corporateApplicantDetail.getEstablishmentYear().longValue());
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) establishmentYearResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						response.setEstablishmentYear(masterResponse.getValue());
					} else {
						response.setEstablishmentYear("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// set city
			List<Long> cityList = new ArrayList<>();
			if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId()))
			cityList.add(corporateApplicantDetail.getRegisteredCityId());
			if(!CommonUtils.isListNullOrEmpty(cityList))
			{
			try {
				OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					response.setCity(masterResponse.getValue());
					response.setRegOfficeCity(masterResponse.getValue());
				} else {
					response.setCity("NA");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			
			cityList.clear();
			if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeCityId()))
			cityList.add(corporateApplicantDetail.getAdministrativeCityId());
			if(!CommonUtils.isListNullOrEmpty(cityList))
			{
			try {
				OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					response.setAddOfficeCity(masterResponse.getValue());
					
				} else {
					response.setCity("NA");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			

			// set state
			List<Long> stateList = new ArrayList<>();
			if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId()))
			stateList.add(Long.valueOf(corporateApplicantDetail.getRegisteredStateId()));
			if(!CommonUtils.isListNullOrEmpty(stateList))
			{
			try {
				OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					response.setState(masterResponse.getValue());
					response.setRegOfficestate(masterResponse.getValue());
				} else {
					response.setState("NA");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			
			
			stateList.clear();
			if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeStateId()))
				stateList.add(Long.valueOf(corporateApplicantDetail.getAdministrativeStateId()));
				if(!CommonUtils.isListNullOrEmpty(stateList))
				{
				try {
					OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						response.setAddOfficestate(masterResponse.getValue());
					} else {
						response.setState("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			// set country
			List<Long> countryList = new ArrayList<>();
			if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId()))
			countryList.add(Long.valueOf(corporateApplicantDetail.getRegisteredCountryId()));
			if(!CommonUtils.isListNullOrEmpty(countryList))
			{
			try {
				OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					response.setCountry(masterResponse.getValue());
					response.setRegOfficecountry(masterResponse.getValue());
				} else {
					response.setCountry("NA");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			
			countryList.clear();
			if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeCountryId()))
				countryList.add(Long.valueOf(corporateApplicantDetail.getAdministrativeCountryId()));
				if(!CommonUtils.isListNullOrEmpty(countryList))
				{
				try {
					OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						response.setAddOfficecountry(masterResponse.getValue());
					} else {
						response.setCountry("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
				
				
			List<Long> keyVerticalFundingId = new ArrayList<>();
			if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVericalFunding()))
			keyVerticalFundingId.add(corporateApplicantDetail.getKeyVericalFunding());
			if(!CommonUtils.isListNullOrEmpty(keyVerticalFundingId))
			{
			try {
				OneFormResponse oneFormResponse = oneFormClient.getIndustryById(keyVerticalFundingId);
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					response.setKeyVericalFunding(masterResponse.getValue());
				} else {
					response.setKeyVericalFunding("NA");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
		List<Long> industryList = industrySectorRepository.getIndustryByApplicationId(toApplicationId);
		List<Long> sectorList = industrySectorRepository.getSectorByApplicationId(toApplicationId);
		List<Long> subSectorList = subSectorRepository.getSubSectorByApplicationId(toApplicationId);

		IndustrySectorSubSectorTeaserRequest industrySectorSubSectorTeaserRequest = new IndustrySectorSubSectorTeaserRequest();
		industrySectorSubSectorTeaserRequest.setIndustryList(industryList);
		industrySectorSubSectorTeaserRequest.setSectorList(sectorList);
		industrySectorSubSectorTeaserRequest.setSubSectorList(subSectorList);
		try {
			OneFormResponse oneFormResponse = oneFormClient
					.getIndustrySectorSubSector(industrySectorSubSectorTeaserRequest);
			response.setIndustrySector(oneFormResponse.getListData());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// get value of working capital data
		PrimaryWorkingCapitalLoanDetail primaryWorkingCapitalLoanDetail = primaryWorkingCapitalLoanDetailRepository
				.getByApplicationAndUserId(toApplicationId, userId);
		if (primaryWorkingCapitalLoanDetail != null) {
			// set value to response
			BeanUtils.copyProperties(primaryWorkingCapitalLoanDetail, response);
			if(!CommonUtils.isObjectNullOrEmpty(primaryWorkingCapitalLoanDetail.getCurrencyId())&&!CommonUtils.isObjectNullOrEmpty(primaryWorkingCapitalLoanDetail.getDenominationId()))
			response.setCurrencyDenomination(Currency.getById(primaryWorkingCapitalLoanDetail.getCurrencyId()).getValue() + " in "
							+ Denomination.getById(primaryWorkingCapitalLoanDetail.getDenominationId()).getValue());
			response.setLoanType(primaryWorkingCapitalLoanDetail.getProductId() != null ? LoanType.getById(primaryWorkingCapitalLoanDetail.getProductId()).getValue() : null);
			response.setLoanAmount(String.valueOf(primaryWorkingCapitalLoanDetail.getAmount()));
			if(!CommonUtils.isObjectNullOrEmpty(primaryWorkingCapitalLoanDetail.getModifiedDate()))
			response.setDateOfProposal(DATE_FORMAT.format(primaryWorkingCapitalLoanDetail.getModifiedDate()));
			response.setProjectBrief(primaryWorkingCapitalLoanDetail.getProjectBrief());
            response.setIsCreditRatingAvailable(primaryWorkingCapitalLoanDetail.getCreditRatingId()!= null ? CreditRatingAvailable.getById(primaryWorkingCapitalLoanDetail.getCreditRatingId()).getValue() : null);
            response.setSharePriceFace(primaryWorkingCapitalLoanDetail.getSharePriceFace());
            response.setSharePriceMarket(primaryWorkingCapitalLoanDetail.getSharePriceMarket());
		}

		// get value of proposed product and set in response
		try {
			response.setProposedProductDetailRequestList(
					proposedProductDetailsService.getProposedProductDetailList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Proposed Product {}", e);
		}

		// get value of Existing product and set in response
		try {
			response.setExistingProductDetailRequestList(
					existingProductDetailsService.getExistingProductDetailList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Existing Product {}", e);
		}

		// get value of achievement details and set in response
		try {
			response.setAchievementDetailList(
					achievmentDetailsService.getAchievementDetailList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Achievement Details {}", e);
		}

		// get value of Credit Rating and set in response
        try {
            List<CreditRatingOrganizationDetailRequest> creditRatingOrganizationDetailRequestList = creditRatingOrganizationDetailsService
					.getcreditRatingOrganizationDetailsList(toApplicationId, userId);
			List<CreditRatingOrganizationDetailResponse> creditRatingOrganizationDetailResponseList = new ArrayList<>();
			for (CreditRatingOrganizationDetailRequest creditRatingOrganizationDetailRequest : creditRatingOrganizationDetailRequestList) {
				CreditRatingOrganizationDetailResponse creditRatingOrganizationDetailResponse = new CreditRatingOrganizationDetailResponse();
                creditRatingOrganizationDetailResponse.setAmount(creditRatingOrganizationDetailRequest.getAmount());
				creditRatingOrganizationDetailResponse.setCreditRatingFund(creditRatingOrganizationDetailRequest.getCreditRatingFundId() != null ? CreditRatingFund.getById(creditRatingOrganizationDetailRequest.getCreditRatingFundId()).getValue() : null);
				OneFormResponse oneFormResponse = oneFormClient.getRatingById(
						CommonUtils.isObjectNullOrEmpty(creditRatingOrganizationDetailRequest.getCreditRatingOptionId())
								? null : creditRatingOrganizationDetailRequest.getCreditRatingOptionId().longValue());
				MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) oneFormResponse.getData(), MasterResponse.class);
				if (masterResponse != null) {
					creditRatingOrganizationDetailResponse.setCreditRatingOption(masterResponse.getValue());
                } else {
                    response.setKeyVericalFunding("NA");
				}
				creditRatingOrganizationDetailResponse.setCreditRatingTerm(CreditRatingTerm
						.getById(creditRatingOrganizationDetailRequest.getCreditRatingTermId()).getValue());
				creditRatingOrganizationDetailResponse.setRatingAgency(
                        RatingAgency.getById(creditRatingOrganizationDetailRequest.getRatingAgencyId()).getValue());
				creditRatingOrganizationDetailResponse
						.setFacilityName(creditRatingOrganizationDetailRequest.getFacilityName());
				creditRatingOrganizationDetailResponseList.add(creditRatingOrganizationDetailResponse);
			}
			response.setCreditRatingOrganizationDetailResponse(creditRatingOrganizationDetailResponseList);
		} catch (Exception e) {
			logger.error("Problem to get Data of Credit Rating {}", e);
		}

		// set short term rating option
        try {
            List<String> shortTermValueList = new ArrayList<String>();
			List<Integer> shortTermIdList = creditRatingOrganizationDetailsService
                    .getShortTermCreditRatingForTeaser(toApplicationId, userId);
            for (Integer shortTermId : shortTermIdList) {
				OneFormResponse oneFormResponse = oneFormClient
						.getRatingById(CommonUtils.isObjectNullOrEmpty(shortTermId) ? null : shortTermId.longValue());
				MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) oneFormResponse.getData(), MasterResponse.class);
				if (masterResponse != null) {
					shortTermValueList.add(masterResponse.getValue());
				} else {
					shortTermValueList.add(CommonUtils.NOT_APPLICABLE);
				}
				response.setShortTermRating(shortTermValueList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// set long term rating option
        try {
			List<String> longTermValueList = new ArrayList<String>();
			List<Integer> longTermIdList = creditRatingOrganizationDetailsService
                    .getLongTermCreditRatingForTeaser(toApplicationId, userId);
            for (Integer shortTermId : longTermIdList) {
				OneFormResponse oneFormResponse = oneFormClient
						.getRatingById(CommonUtils.isObjectNullOrEmpty(shortTermId) ? null : shortTermId.longValue());
				MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) oneFormResponse.getData(), MasterResponse.class);
				if (masterResponse != null) {
					longTermValueList.add(masterResponse.getValue());
				} else {
					longTermValueList.add(CommonUtils.NOT_APPLICABLE);
				}
			}
			response.setLongTermRating(longTermValueList);
		} catch (Exception e) {
			e.printStackTrace();
        }
        
        try {
			List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = directorBackgroundDetailsService.getDirectorBackgroundDetailList(toApplicationId, userId);
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
				//.setQualification(directorBackgroundDetailRequest.getQualification());
				directorBackgroundDetailResponse.setTotalExperience(directorBackgroundDetailRequest.getTotalExperience().toString());
				directorBackgroundDetailResponse.setNetworth(directorBackgroundDetailRequest.getNetworth().toString());
				directorBackgroundDetailResponse.setDesignation(directorBackgroundDetailRequest.getDesignation());
				directorBackgroundDetailResponse.setAppointmentDate(directorBackgroundDetailRequest.getAppointmentDate());
				directorBackgroundDetailResponse.setDin(directorBackgroundDetailRequest.getDin());
				directorBackgroundDetailResponse.setMobile(directorBackgroundDetailRequest.getMobile());
				directorBackgroundDetailResponse.setDob(directorBackgroundDetailRequest.getDob());
				directorBackgroundDetailResponseList.add(directorBackgroundDetailResponse);
			}
			response.setDirectorBackgroundDetailResponses(directorBackgroundDetailResponseList);
        }
			catch (Exception e) {
				logger.error("Problem to get Data of Director's Background {}", e);
			}
        
      //references
        List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequestList = null;
		try {
			referenceRetailDetailsRequestList = referenceRetailDetailsService.getReferenceRetailDetailList(toApplicationId,  CommonUtils.ApplicantType.APPLICANT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setReferenceRetailDetailsRequests(referenceRetailDetailsRequestList);
        
        // get value of Ownership Details and set in response
		try {
			List<OwnershipDetailRequest> ownershipDetailRequestsList = ownershipDetailsService
					.getOwnershipDetailList(toApplicationId, userId);
			List<OwnershipDetailResponse> ownershipDetailResponseList = new ArrayList<>();
			for (OwnershipDetailRequest ownershipDetailRequest : ownershipDetailRequestsList) {
				OwnershipDetailResponse ownershipDetailResponse = new OwnershipDetailResponse();
				ownershipDetailResponse.setRemarks(ownershipDetailRequest.getRemarks());
				ownershipDetailResponse.setStackPercentage(ownershipDetailRequest.getStackPercentage());
				ownershipDetailResponse.setShareHoldingCategory(
						ShareHoldingCategory.getById(ownershipDetailRequest.getShareHoldingCategoryId()).getValue());
				ownershipDetailResponseList.add(ownershipDetailResponse);
			}
			response.setOwnershipDetailResponseList(ownershipDetailResponseList);

		} catch (Exception e) {
			logger.error("Problem to get Data of Ownership Details {}", e);
		}

		// get value of Promotor Background and set in response
		try {
			List<PromotorBackgroundDetailRequest> promotorBackgroundDetailRequestList = promotorBackgroundDetailsService
					.getPromotorBackgroundDetailList(toApplicationId, userId);
			List<PromotorBackgroundDetailResponse> promotorBackgroundDetailResponseList = new ArrayList<>();
			for (PromotorBackgroundDetailRequest promotorBackgroundDetailRequest : promotorBackgroundDetailRequestList) {
				PromotorBackgroundDetailResponse promotorBackgroundDetailResponse = new PromotorBackgroundDetailResponse();
			//	promotorBackgroundDetailResponse.setAchievements(promotorBackgroundDetailRequest.getAchivements());
				promotorBackgroundDetailResponse.setAddress(promotorBackgroundDetailRequest.getAddress());
			//	promotorBackgroundDetailResponse.setAge(promotorBackgroundDetailRequest.getAge());
                promotorBackgroundDetailResponse.setPanNo(promotorBackgroundDetailRequest.getPanNo().toUpperCase());
				String promotorName = "";
				if (promotorBackgroundDetailRequest.getSalutationId() != null){
					promotorName = Title.getById(promotorBackgroundDetailRequest.getSalutationId()).getValue();
				}
				promotorName += promotorBackgroundDetailRequest.getPromotorsName();
				promotorBackgroundDetailResponse.setPromotorsName(promotorName);
			// 	promotorBackgroundDetailResponse.setQualification(promotorBackgroundDetailRequest.getQualification());
			//	promotorBackgroundDetailResponse.setTotalExperience(promotorBackgroundDetailRequest.getTotalExperience());
				promotorBackgroundDetailResponseList.add(promotorBackgroundDetailResponse);
			}
			response.setPromotorBackgroundDetailResponseList(promotorBackgroundDetailResponseList);
		} catch (Exception e) {
			logger.error("Problem to get Data of Promotor Background {}", e);
		}


		//get value of Past Financial and set in response
		try {
			List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequestList = pastFinancialEstimateDetailsRepository.listPastFinancialEstimateDetailsRequestFromAppId(toApplicationId);
			if (pastFinancialEstimatesDetailRequestList.size()>4){
				pastFinancialEstimatesDetailRequestList = pastFinancialEstimatesDetailRequestList.subList((pastFinancialEstimatesDetailRequestList.size()-4),pastFinancialEstimatesDetailRequestList.size());
			}
			response.setPastFinancialEstimatesDetailRequestList(pastFinancialEstimatesDetailRequestList);
		} catch (Exception e) {
			logger.error("Problem to get Data of Past Financial {}", e);
		}


		// get value of Future Projection and set in response
		try {
			response.setFutureFinancialEstimatesDetailRequestList(futureFinancialEstimatesDetailsService
					.getFutureFinancialEstimateDetailsList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Future Projection {}", e);
		}

		// get value of Security and set in response
		try {
			response.setSecurityCorporateDetailRequestList(
					securityCorporateDetailsService.getsecurityCorporateDetailsList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Security Details {}", e);
		}

		// get value of Financial Arrangements and set in response
		try {
			List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService
					.getFinancialArrangementDetailsList(toApplicationId, userId);
			List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList = new ArrayList<>();
			for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
				FinancialArrangementsDetailResponse financialArrangementsDetailResponse = new FinancialArrangementsDetailResponse();
				financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
				financialArrangementsDetailResponse.setOutstandingAmount(financialArrangementsDetailRequest.getOutstandingAmount());
				financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
				financialArrangementsDetailResponse.setAmount(financialArrangementsDetailRequest.getAmount());
		//		financialArrangementsDetailResponse.setLenderType(LenderType.getById(financialArrangementsDetailRequest.getLenderType()).getValue());
				financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
				financialArrangementsDetailResponse.setLoanType(financialArrangementsDetailRequest.getLoanType());
				financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
		//		financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
				financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
				financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
			}
			response.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);
		} catch (Exception e) {
			logger.error("Problem to get Data of Financial Arrangements Details {}", e);
		}

		// get data of Associated Concern
		try {
			response.setAssociatedConcernDetailRequests(
					associatedConcernDetailService.getAssociatedConcernsDetailList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Associated Concerns {}", e);
		}

		// get data of Details of Guarantors
		try {
			response.setGuarantorsCorporateDetailRequestList(
					guarantorsCorporateDetailService.getGuarantorsCorporateDetailList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Details of Guarantor {}", e);
		}

		// get data of Monthly Turnover
		try {
			response.setMonthlyTurnoverDetailRequestList(
					monthlyTurnoverDetailService.getMonthlyTurnoverDetailList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Monthly Turnover {}", e);
		}
		return response;
	}
}
