package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.model.*;
import com.capitaworld.service.loans.model.teaser.primaryview.WorkingCapitalPrimaryViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.service.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.teaser.primaryview.WorkingCapitalPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.*;
import com.capitaworld.service.oneform.model.IndustrySectorSubSectorTeaserRequest;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dhaval on 19-May-17.
 */
@Service
@Transactional
public class WorkingCapitalPrimaryViewServiceImpl implements WorkingCapitalPrimaryViewService {

	private static final Logger logger = LoggerFactory.getLogger(WorkingCapitalPrimaryViewServiceImpl.class);

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
	private IndustrySectorRepository industrySectorRepository;

	@Autowired
	private SubSectorRepository subSectorRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private ProductMasterService productMasterService;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private DMSClient dmsClient;

	@Autowired
	private MatchEngineClient matchEngineClient;

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public WorkingCapitalPrimaryViewResponse getWorkingCapitalPrimaryViewDetails(Long toApplicationId, Integer userType,
			Long fundProviderUserId) {
		WorkingCapitalPrimaryViewResponse workingCapitalPrimaryViewResponse = new WorkingCapitalPrimaryViewResponse();
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(toApplicationId);
		Long userId = loanApplicationMaster.getUserId();

		if (userType != null) {
			if (!(CommonUtils.UserType.FUND_SEEKER == userType)) { // teaser
																	// view
																	// viwed by
																	// fund
																	// provider
				Long fpProductMappingId = null;
				try {
					fpProductMappingId = productMasterService.getList(fundProviderUserId).get(0).getId();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					MatchRequest matchRequest = new MatchRequest();
					matchRequest.setApplicationId(toApplicationId);
					matchRequest.setProductId(fpProductMappingId);
					MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfCorporate(matchRequest);
					workingCapitalPrimaryViewResponse.setMatchesList(matchResponse.getMatchDisplayObjectList());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// get details of CorporateApplicantDetail
		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
				.getByApplicationAndUserId(userId, toApplicationId);
		// set value to response
		if (corporateApplicantDetail != null) {
			BeanUtils.copyProperties(corporateApplicantDetail, workingCapitalPrimaryViewResponse);
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getConstitutionId()))
				workingCapitalPrimaryViewResponse
						.setConstitution(Constitution.getById(corporateApplicantDetail.getConstitutionId()).getValue());
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getEstablishmentMonth()))
				workingCapitalPrimaryViewResponse.setEstablishmentMonth(
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
						workingCapitalPrimaryViewResponse.setEstablishmentYear(masterResponse.getValue());
					} else {
						workingCapitalPrimaryViewResponse.setEstablishmentYear("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// set city
			List<Long> cityList = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId()))
				cityList.add(corporateApplicantDetail.getRegisteredCityId());
			if (!CommonUtils.isListNullOrEmpty(cityList)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						workingCapitalPrimaryViewResponse.setCity(masterResponse.getValue());
					} else {
						workingCapitalPrimaryViewResponse.setCity("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// set state
			List<Long> stateList = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId()))
				stateList.add(Long.valueOf(corporateApplicantDetail.getRegisteredStateId()));
			if (!CommonUtils.isListNullOrEmpty(stateList)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						workingCapitalPrimaryViewResponse.setState(masterResponse.getValue());
					} else {
						workingCapitalPrimaryViewResponse.setState("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// set country
			List<Long> countryList = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId()))
				countryList.add(Long.valueOf(corporateApplicantDetail.getRegisteredCountryId()));
			if (!CommonUtils.isListNullOrEmpty(countryList)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						workingCapitalPrimaryViewResponse.setCountry(masterResponse.getValue());
					} else {
						workingCapitalPrimaryViewResponse.setCountry("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			List<Long> keyVerticalFundingId = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVericalFunding()))
				keyVerticalFundingId.add(corporateApplicantDetail.getKeyVericalFunding());
			if (!CommonUtils.isListNullOrEmpty(keyVerticalFundingId)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getIndustryById(keyVerticalFundingId);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						workingCapitalPrimaryViewResponse.setKeyVericalFunding(masterResponse.getValue());
					} else {
						workingCapitalPrimaryViewResponse.setKeyVericalFunding("NA");
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
			workingCapitalPrimaryViewResponse.setIndustrySector(oneFormResponse.getListData());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// get value of working capital data
		PrimaryWorkingCapitalLoanDetail primaryWorkingCapitalLoanDetail = primaryWorkingCapitalLoanDetailRepository
				.getByApplicationAndUserId(toApplicationId, userId);
		if (primaryWorkingCapitalLoanDetail != null) {
			// set value to response
			BeanUtils.copyProperties(primaryWorkingCapitalLoanDetail, workingCapitalPrimaryViewResponse);
			if(!CommonUtils.isObjectNullOrEmpty(primaryWorkingCapitalLoanDetail.getCurrencyId())&&!CommonUtils.isObjectNullOrEmpty(primaryWorkingCapitalLoanDetail.getDenominationId())) {
				workingCapitalPrimaryViewResponse.setCurrencyDenomination(Currency.getById(primaryWorkingCapitalLoanDetail.getCurrencyId()).getValue()
						+ " in "
						+ Denomination.getById(primaryWorkingCapitalLoanDetail.getDenominationId()).getValue());
			}
				workingCapitalPrimaryViewResponse.setLoanType(primaryWorkingCapitalLoanDetail.getProductId() != null ? LoanType.getById(primaryWorkingCapitalLoanDetail.getProductId()).getValue() : null);
				workingCapitalPrimaryViewResponse.setLoanAmount(primaryWorkingCapitalLoanDetail.getAmount() != null ? String.valueOf(primaryWorkingCapitalLoanDetail.getAmount()) : null);
				if (!CommonUtils.isObjectNullOrEmpty(primaryWorkingCapitalLoanDetail.getModifiedDate()))
					workingCapitalPrimaryViewResponse.setDateOfProposal(primaryWorkingCapitalLoanDetail.getModifiedDate() != null ? DATE_FORMAT.format(primaryWorkingCapitalLoanDetail.getModifiedDate()) : null);
				workingCapitalPrimaryViewResponse.setIsCreditRatingAvailable(primaryWorkingCapitalLoanDetail.getCreditRatingId() != null ? CreditRatingAvailable.getById(primaryWorkingCapitalLoanDetail.getCreditRatingId()).getValue() : null);
		}
		// get value of proposed product and set in response
		try {
			workingCapitalPrimaryViewResponse.setProposedProductDetailRequestList(
					proposedProductDetailsService.getProposedProductDetailList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Proposed Product {}", e);
		}

		// get value of Existing product and set in response
		try {
			workingCapitalPrimaryViewResponse.setExistingProductDetailRequestList(
					existingProductDetailsService.getExistingProductDetailList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Existing Product {}", e);
		}

		// get value of achievement details and set in response
		try {
			workingCapitalPrimaryViewResponse.setAchievementDetailList(
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
				OneFormResponse oneFormResponse = oneFormClient.getRatingById(
						CommonUtils.isObjectNullOrEmpty(creditRatingOrganizationDetailRequest.getCreditRatingOptionId())
								? null : creditRatingOrganizationDetailRequest.getCreditRatingOptionId().longValue());
				MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) oneFormResponse.getData(), MasterResponse.class);
				if (masterResponse != null) {
					creditRatingOrganizationDetailResponse.setCreditRatingOption(masterResponse.getValue());
				} else {
					workingCapitalPrimaryViewResponse.setKeyVericalFunding("NA");
				}
				creditRatingOrganizationDetailResponse.setCreditRatingTerm(CreditRatingTerm
						.getById(creditRatingOrganizationDetailRequest.getCreditRatingTermId()).getValue());
				creditRatingOrganizationDetailResponse.setRatingAgency(
						RatingAgency.getById(creditRatingOrganizationDetailRequest.getRatingAgencyId()).getValue());
				creditRatingOrganizationDetailResponse
						.setFacilityName(creditRatingOrganizationDetailRequest.getFacilityName());
				creditRatingOrganizationDetailResponseList.add(creditRatingOrganizationDetailResponse);
			}
			workingCapitalPrimaryViewResponse
					.setCreditRatingOrganizationDetailResponse(creditRatingOrganizationDetailResponseList);
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
				workingCapitalPrimaryViewResponse.setShortTermRating(shortTermValueList);
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
			workingCapitalPrimaryViewResponse.setLongTermRating(longTermValueList);
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
			workingCapitalPrimaryViewResponse.setOwnershipDetailResponseList(ownershipDetailResponseList);
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
			workingCapitalPrimaryViewResponse
					.setPromotorBackgroundDetailResponseList(promotorBackgroundDetailResponseList);
		} catch (Exception e) {
			logger.error("Problem to get Data of Promotor Background {}", e);
		}

		// get value of Past Financial and set in response
		try {
			workingCapitalPrimaryViewResponse.setPastFinancialEstimatesDetailRequestList(
					pastFinancialEstiamateDetailsService.getFinancialListData(userId, toApplicationId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Past Financial {}", e);
		}

		// get value of Future Projection and set in response
		try {
			workingCapitalPrimaryViewResponse
					.setFutureFinancialEstimatesDetailRequestList(futureFinancialEstimatesDetailsService
							.getFutureFinancialEstimateDetailsList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Future Projection {}", e);
		}

		// get value of Security and set in response
		try {
			workingCapitalPrimaryViewResponse.setSecurityCorporateDetailRequestList(
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
			workingCapitalPrimaryViewResponse
					.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);

		} catch (Exception e) {
			logger.error("Problem to get Data of Financial Arrangements Details {}", e);
		}

		// get list of Brochure
		DocumentRequest documentRequest = new DocumentRequest();
		documentRequest.setApplicationId(toApplicationId);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_BROCHURE_OF_PROPOSED_ACTIVITIES);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			workingCapitalPrimaryViewResponse.setBrochureList(documentResponse.getDataList());
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// get list fo certificate
		documentRequest.setApplicationId(toApplicationId);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_CERTIFICATE_OF_INCORPORATION);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			workingCapitalPrimaryViewResponse.setCertificateList(documentResponse.getDataList());
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// get list of pan card
		documentRequest.setApplicationId(toApplicationId);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_COPY_OF_PAN_CARD);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			workingCapitalPrimaryViewResponse.setPanCardList(documentResponse.getDataList());
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// get profile pic
		documentRequest.setApplicationId(toApplicationId);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			workingCapitalPrimaryViewResponse.setProfilePic(documentResponse.getDataList());
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return workingCapitalPrimaryViewResponse;
	}
}
