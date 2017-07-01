package com.capitaworld.service.loans.service.teaser.finalview.impl;

import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryTermLoanDetail;
import com.capitaworld.service.loans.model.*;
import com.capitaworld.service.loans.model.corporate.FinalTermLoanRequest;
import com.capitaworld.service.loans.model.corporate.TotalCostOfProjectRequest;
import com.capitaworld.service.loans.model.teaser.finalview.*;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.common.DocumentManagementService;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.service.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.teaser.finalview.TermLoanFinalViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.*;
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
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dhaval on 27-May-17.
 */
@Service
@Transactional
public class TermLoanFinalViewServiceImpl implements TermLoanFinalViewService {

	@Autowired
	private TotalCostOfProjectService costOfProjectService;

	@Autowired
	private FinanceMeansDetailsService financeMeansDetailsService;

	@Autowired
	private PrimaryTermLoanDetailRepository primaryTermLoanLoanDetailRepository;

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
	private PastFinancialEstiamateDetailsService pastFinancialEstiamateDetailsService;

	@Autowired
	private FutureFinancialEstimatesDetailsService futureFinancialEstimatesDetailsService;

	@Autowired
	private ExistingProductDetailsService existingProductDetailsService;

	@Autowired
	private SecurityCorporateDetailsService securityCorporateDetailsService;

	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;

	@Autowired
	private Environment environment;

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
	private FinalTermLoanService finalTermLoanService;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private UsersClient usersClient;

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	protected static final String ONE_FORM_URL = "oneForm";
	protected static final String USERS_URL = "userURL";
	protected static final String MATCHES_URL = "matchesURL";

	private static final Logger logger = LoggerFactory.getLogger(WorkingCapitalFinalServiceImpl.class);

	@Override
	public TermLoanFinalViewResponse getTermLoanFinalViewDetails(Long toApplicationId) {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(toApplicationId);
		Long userId = applicationMaster.getUserId();

		// create response object
		TermLoanFinalViewResponse response = new TermLoanFinalViewResponse();

		List<Object> dprList = new ArrayList<Object>();
		// getting data of uploads documents and getting profile picture
		try {
			response.setProfilePic(documentManagementService.getDocumentDetails(toApplicationId,
					DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.TERM_LOAN_PROFIEL_PICTURE));
			response.setLastAuditedAnnualReportList(documentManagementService.getDocumentDetails(toApplicationId,
					DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.TERM_LOAN_LAST_AUDITED_ANNUAL_REPORT));
			response.setSanctionLetterCopyList(documentManagementService.getDocumentDetails(toApplicationId,
					DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.TERM_LOAN_SANCTION_LETTER_COPY));
			response.setLastITReturnList(documentManagementService.getDocumentDetails(toApplicationId,
					DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.TERM_LOAN_LAST_IT_RETURN));
			response.setNetWorthStatementOfdirectorsList(documentManagementService.getDocumentDetails(toApplicationId,
					DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.TERM_LOAN_NET_WORTH_STATEMENT_OF_DIRECTORS));
			response.setProvisionalFinancialsList(documentManagementService.getDocumentDetails(toApplicationId,
					DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.TERM_LOAN_PROVISIONAL_FINANCIALS));
			response.setPanOfDirectorsList(
					documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT,
							DocumentAlias.TERM_LOAN_PAN_OF_DIRECTORS_CERTIFICATE_OF_INCORPORATION));
			response.setDetailedListOfShareholdersList(documentManagementService.getDocumentDetails(toApplicationId,
					DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.TERM_LOAN_DETAILED_LIST_OF_SHAREHOLDERS));
			response.setPhotoOfDirectorsList(documentManagementService.getDocumentDetails(toApplicationId,
					DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.TERM_LOAN_PHOTO_OF_DIRECTORS));
			dprList = documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT,
					Long.valueOf(DocumentAlias.TL_DPR_OUR_FORMAT));
			response.setDprList(dprList);
			response.setCmaList(documentManagementService.getDocumentDetails(toApplicationId,
					DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_CMA)));
			response.setBsFormatList(documentManagementService.getDocumentDetails(toApplicationId,
					DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_COMPANY_ACT)));
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// if DPR our format not upload no need get data of DPR
		if (dprList.size() > 0) {
			// getting data of DPR
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

		}

		// set final working capital information
		try {
			FinalTermLoanRequest finalTermLoanRequest = finalTermLoanService.get(userId, toApplicationId);

			response.setTechnologyType(finalTermLoanRequest.getTechnologyTypeId() != null
					? TypeTechnology.getById(finalTermLoanRequest.getTechnologyTypeId()).getValue() : null);
			response.setTechnologyPatented(finalTermLoanRequest.getTechnologyPatentedId() != null
					? TechnologyPatented.getById(finalTermLoanRequest.getTechnologyPatentedId()).getValue() : null);
			response.setTechnologyRequiresUpgradation(
					finalTermLoanRequest.getTechnologyRequiresUpgradationId() != null
							? TechnologyRequiresUpgradation
									.getById(finalTermLoanRequest.getTechnologyRequiresUpgradationId()).getValue()
							: null);
			response.setMarketPosition(finalTermLoanRequest.getMarketingPositioningId() != null
					? MarketPosition.getById(finalTermLoanRequest.getMarketingPositioningId()).getValue() : null);
			response.setMarketingPositioning(finalTermLoanRequest.getMarketingPositioningId() != null
					? MarketingPositioningNew.getById(finalTermLoanRequest.getMarketingPositioningId()).getValue() : null);
			response.setMarketShareTurnover(finalTermLoanRequest.getMarketShareTurnoverId() != null
					? MarketShareTurnover.getById(finalTermLoanRequest.getMarketShareTurnoverId()).getValue() : null);
			response.setIndiaDistributionNetwork(finalTermLoanRequest.getIndiaDistributionNetworkId() != null
					? IndiaDistributionNetwork.getById(finalTermLoanRequest.getIndiaDistributionNetworkId()).getValue()
					: null);
			response.setDistributionAndTieUps(finalTermLoanRequest.getDistributionAndMarketingTieUpsId()!=null?DistributionMarketingTieUps.getById(finalTermLoanRequest.getDistributionAndMarketingTieUpsId()).getValue():null);
			response.setBrandAmbassador(finalTermLoanRequest.getBrandAmbassadorId()!=null?BrandAmbassador.getById(finalTermLoanRequest.getBrandAmbassadorId()).getValue():null);
			response.setMarketingPositioning(finalTermLoanRequest.getMarketingPositioningId()!=null?MarketingPositioningNew.getById(finalTermLoanRequest.getMarketingPositioningId()).getValue():null);
			response.setProductServicesPerse(finalTermLoanRequest.getProductServicesPerseId() != null
					? ProductServicesPerse.getById(finalTermLoanRequest.getProductServicesPerseId()).getValue() : null);
			response.setEnvironmentCertification(finalTermLoanRequest.getEnvironmentCertificationId() != null
					? EnvironmentCertification.getById(finalTermLoanRequest.getEnvironmentCertificationId()).getValue()
					: null);
			response.setAccountingSystems(finalTermLoanRequest.getAccountingSystemsId() != null
					? AccountingSystems.getById(finalTermLoanRequest.getAccountingSystemsId()).getValue() : null);
			response.setInternalAudit(finalTermLoanRequest.getInternalAuditId() != null
					? InternalAudit.getById(finalTermLoanRequest.getInternalAuditId()).getValue() : null);
			response.setCompetence(finalTermLoanRequest.getCompetenceId() != null
					? Competence.getById(finalTermLoanRequest.getCompetenceId()).getValue() : null);
			response.setExistingShareHolder(finalTermLoanRequest.getExistingShareHoldersId() != null
					? ExistingShareholders.getById(finalTermLoanRequest.getExistingShareHoldersId()).getValue() : null);

			if (finalTermLoanRequest.getIsDependsMajorlyOnGovernment()) {
				response.setMajorlyOnGovernment("Yes");
			} else {
				response.setMajorlyOnGovernment("No");
			}

			if (finalTermLoanRequest.getIsIsoCertified()) {
				response.setIsIsoCertified("Yes");
			} else {
				response.setIsIsoCertified("No");
			}
			if (finalTermLoanRequest.isWhetherTechnologyIsTied()) {
				response.setWhetherTechnologyIsTied("Yes");
			} else {
				response.setWhetherTechnologyIsTied("No");
			}
			// set overseas
			List<Integer> overseasIds = finalTermLoanRequest.getOverseasNetworkIds();
			String overseasString = "";
			for (int id : overseasIds) {
				overseasString += OverseasNetwork.getById(id).getValue() + ",";
			}
			response.setOverseasNetwork(overseasString);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// set registered email address and registered contact number
		UserResponse userResponse = usersClient.getEmailMobile(userId);
		if (!CommonUtils.isObjectNullOrEmpty(userResponse)) {
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
		}

		// get details of CorporateApplicantDetail
		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
				.getByApplicationAndUserId(userId, toApplicationId);
		// set value to response
		if (corporateApplicantDetail != null) {
			BeanUtils.copyProperties(corporateApplicantDetail, response);
			response.setConstitution(Constitution.getById(corporateApplicantDetail.getConstitutionId()).getValue());
			response.setEstablishmentMonth(
					EstablishmentMonths.getById(corporateApplicantDetail.getEstablishmentMonth()).getValue());

			// set city
			List<Long> cityList = new ArrayList<>();
			cityList.add(corporateApplicantDetail.getRegisteredCityId());
			try {
				OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					response.setCity(masterResponse.getValue());
				} else {
					response.setCity("NA");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// set state
			List<Long> stateList = new ArrayList<>();
			stateList.add(Long.valueOf(corporateApplicantDetail.getRegisteredStateId()));
			try {
				OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					response.setState(masterResponse.getValue());
				} else {
					response.setState("NA");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// set country
			List<Long> countryList = new ArrayList<>();
			countryList.add(Long.valueOf(corporateApplicantDetail.getRegisteredCountryId()));
			try {
				OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					response.setCountry(masterResponse.getValue());
				} else {
					response.setCountry("NA");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// set key vertical funding
			List<Long> keyVerticalFundingId = new ArrayList<>();
			keyVerticalFundingId.add(corporateApplicantDetail.getKeyVericalFunding());
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

		// set Establishment year
		try {
			OneFormResponse establishmentYearResponse = oneFormClient
					.getYearByYearId(Long.valueOf(corporateApplicantDetail.getEstablishmentYear()));
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) establishmentYearResponse
					.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
				MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0),
						MasterResponse.class);
				response.setEstablishmentYear(masterResponse.getValue());
			} else {
				response.setEstablishmentYear("NA");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// get industry sectors
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

		// get value of Term Loan data
		PrimaryTermLoanDetail primaryTermLoanDetail = primaryTermLoanLoanDetailRepository
				.getByApplicationAndUserId(toApplicationId, userId);
		// set value to response
		if (primaryTermLoanDetail != null) {
			BeanUtils.copyProperties(primaryTermLoanDetail, response);
		}
		if (primaryTermLoanDetail.getCurrencyId() != null && primaryTermLoanDetail.getDenominationId() != null) {
			response.setCurrencyDenomination(Currency.getById(primaryTermLoanDetail.getCurrencyId()).getValue() + " in "
					+ Denomination.getById(primaryTermLoanDetail.getDenominationId()).getValue());
		}
		if (primaryTermLoanDetail.getProductId() != null) {
			response.setLoanType(LoanType.getById(primaryTermLoanDetail.getProductId()).getValue());
		}
		if (primaryTermLoanDetail.getModifiedDate() != null) {
			response.setDateOfProposal(DATE_FORMAT.format(primaryTermLoanDetail.getModifiedDate()));
		}
		if (primaryTermLoanDetail.getAmount() != null) {
			response.setLoanAmount(String.valueOf(primaryTermLoanDetail.getAmount()));
		}

		/**
		 * getting frame data
		 */
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
				creditRatingOrganizationDetailResponse.setCreditRatingFund(CreditRatingFund
						.getById(creditRatingOrganizationDetailRequest.getCreditRatingFundId()).getValue());
				// calling client for credit rating options
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
				promotorBackgroundDetailResponse.setAchievements(promotorBackgroundDetailRequest.getAchivements());
				promotorBackgroundDetailResponse.setAddress(promotorBackgroundDetailRequest.getAddress());
				promotorBackgroundDetailResponse.setAge(promotorBackgroundDetailRequest.getAge());
				promotorBackgroundDetailResponse.setPanNo(promotorBackgroundDetailRequest.getPanNo());
				promotorBackgroundDetailResponse
						.setPromotorsName(Title.getById(promotorBackgroundDetailRequest.getSalutationId()).getValue()
								+ " " + promotorBackgroundDetailRequest.getPromotorsName());
				promotorBackgroundDetailResponse.setQualification(promotorBackgroundDetailRequest.getQualification());
				promotorBackgroundDetailResponse
						.setTotalExperience(promotorBackgroundDetailRequest.getTotalExperience());
				promotorBackgroundDetailResponseList.add(promotorBackgroundDetailResponse);
			}
			response.setPromotorBackgroundDetailResponseList(promotorBackgroundDetailResponseList);
		} catch (Exception e) {
			logger.error("Problem to get Data of Promotor Background {}", e);
		}

		// get value of Past Financial and set in response
		try {
			response.setPastFinancialEstimatesDetailRequestList(
					pastFinancialEstiamateDetailsService.getFinancialListData(userId, toApplicationId));
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
				financialArrangementsDetailResponse.setAmount(financialArrangementsDetailRequest.getAmount());
				financialArrangementsDetailResponse
						.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
				financialArrangementsDetailResponse.setFacilityNature(
						NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
				financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
			}
			response.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);
		} catch (Exception e) {
			logger.error("Problem to get Data of Financial Arrangements Details {}", e);
		}

		// get Finance Means Details and set in response
		try {
			List<FinanceMeansDetailRequest> financeMeansDetailRequestsList = financeMeansDetailsService
					.getMeansOfFinanceList(toApplicationId, userId);
			List<FinanceMeansDetailResponse> financeMeansDetailResponsesList = new ArrayList<FinanceMeansDetailResponse>();
			for (FinanceMeansDetailRequest financeMeansDetailRequest : financeMeansDetailRequestsList) {
				FinanceMeansDetailResponse detailResponse = new FinanceMeansDetailResponse();
				BeanUtils.copyProperties(financeMeansDetailRequest, detailResponse);
				if (financeMeansDetailRequest.getFinanceMeansCategoryId() != null) {
					detailResponse.setFinanceMeansCategory(FinanceCategory
							.getById(Integer.parseInt(financeMeansDetailRequest.getFinanceMeansCategoryId().toString()))
							.getValue());
				}
				financeMeansDetailResponsesList.add(detailResponse);
			}
			response.setFinanceMeansDetailResponseList(financeMeansDetailResponsesList);
		} catch (Exception e) {
			logger.error("Problem to get Data of Finance Means Details {}", e);
		}

		// get Total cost of project and set in response
		try {
			List<TotalCostOfProjectRequest> costOfProjectsList = costOfProjectService
					.getCostOfProjectDetailList(toApplicationId, userId);
			List<TotalCostOfProjectResponse> costOfProjectResponses = new ArrayList<TotalCostOfProjectResponse>();
			for (TotalCostOfProjectRequest costOfProjectRequest : costOfProjectsList) {
				TotalCostOfProjectResponse costOfProjectResponse = new TotalCostOfProjectResponse();
				BeanUtils.copyProperties(costOfProjectRequest, costOfProjectResponse);
				if (costOfProjectRequest.getParticularsId() != null) {
					costOfProjectResponse.setParticulars(Particular
							.getById(Integer.parseInt(costOfProjectRequest.getParticularsId().toString())).getValue());
				}
				costOfProjectResponses.add(costOfProjectResponse);
			}
			response.setTotalCostOfProjectResponseList(costOfProjectResponses);
		} catch (Exception e) {
			logger.error("Problem to get Data of Total cost of project{}", e);
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
