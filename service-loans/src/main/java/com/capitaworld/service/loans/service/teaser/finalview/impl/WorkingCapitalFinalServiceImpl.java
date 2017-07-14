package com.capitaworld.service.loans.service.teaser.finalview.impl;

import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.model.*;
import com.capitaworld.service.loans.model.corporate.FinalWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.model.teaser.finalview.*;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.common.DocumentManagementService;
import com.capitaworld.service.loans.service.fundseeker.corporate.*;
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

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	private static final Logger logger = LoggerFactory.getLogger(WorkingCapitalFinalServiceImpl.class);

	@Override
	public WorkingCapitalFinalViewResponse getWorkingCapitalFinalViewDetails(Long toApplicationId) {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(toApplicationId);
		Long userId = applicationMaster.getUserId();

		// create response object
		WorkingCapitalFinalViewResponse response = new WorkingCapitalFinalViewResponse();

		List<Object> dprList = new ArrayList<Object>();
		// get list of uploads of final and profile picture
		try {
			response.setProfilePic(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
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
			response.setNetWorthStatementOfdirectorsList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_NET_WORTH_STATEMENT_OF_DIRECTORS));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try{
			response.setProvisionalFinancialsList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_PROVISIONAL_FINANCIALS));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try{
			response.setPanOfDirectorsList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT,DocumentAlias.WORKING_CAPITAL_PAN_OF_DIRECTORS_CERTIFICATE_OF_INCORPORATION));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try{
			response.setDetailedListOfShareholdersList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_DETAILED_LIST_OF_SHAREHOLDERS));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try{
			response.setPhotoOfDirectorsList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_PHOTO_OF_DIRECTORS));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try{
			dprList = documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT,Long.valueOf(DocumentAlias.WC_DPR_OUR_FORMAT));
			response.setDprList(dprList);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
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
		try{
			response.setDprYourFormatList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_DPR_YOUR_FORMAT)));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		

		// if DPR our format not upload no need get data of DPR
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
		}
		// final information
		try {
			FinalWorkingCapitalLoanRequest finalWorkingCapitalLoanRequest = finalWorkingCapitalLoanService.get(userId,
					toApplicationId);

			if (!CommonUtils.isObjectNullOrEmpty(finalWorkingCapitalLoanRequest)) {
				response.setTechnologyType(finalWorkingCapitalLoanRequest.getTechnologyTypeId() != null ? TypeTechnology.getById(finalWorkingCapitalLoanRequest.getTechnologyTypeId()).getValue() : null);
				response.setTechnologyPatented(finalWorkingCapitalLoanRequest.getTechnologyPatentedId() != null ? TechnologyPatented.getById(finalWorkingCapitalLoanRequest.getTechnologyPatentedId()).getValue() : null);
				response.setTechnologyRequiresUpgradation(finalWorkingCapitalLoanRequest.getTechnologyRequiresUpgradationId() != null ? TechnologyRequiresUpgradation.getById(finalWorkingCapitalLoanRequest.getTechnologyRequiresUpgradationId()).getValue() : null);
				response.setMarketPosition(finalWorkingCapitalLoanRequest.getMarketingPositioningId() != null ? MarketPosition.getById(finalWorkingCapitalLoanRequest.getMarketingPositioningId()).getValue() : null);
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
			response.setPromotorBackgroundDetailResponseList(promotorBackgroundDetailResponseList);
		} catch (Exception e) {
			logger.error("Problem to get Data of Promotor Background {}", e);
		}


		//get value of Past Financial and set in response
		try {
			response.setPastFinancialEstimatesDetailRequestList(pastFinancialEstiamateDetailsService.getFinancialListData(userId, toApplicationId));
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
