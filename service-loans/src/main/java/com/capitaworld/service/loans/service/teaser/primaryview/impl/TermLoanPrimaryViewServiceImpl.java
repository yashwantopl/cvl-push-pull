package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryTermLoanDetail;
import com.capitaworld.service.loans.model.CreditRatingOrganizationDetailRequest;
import com.capitaworld.service.loans.model.CreditRatingOrganizationDetailResponse;
import com.capitaworld.service.loans.model.FinanceMeansDetailRequest;
import com.capitaworld.service.loans.model.FinanceMeansDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.OwnershipDetailRequest;
import com.capitaworld.service.loans.model.OwnershipDetailResponse;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.TotalCostOfProjectResponse;
import com.capitaworld.service.loans.model.corporate.TotalCostOfProjectRequest;
import com.capitaworld.service.loans.model.teaser.primaryview.TermLoanPrimaryViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryTermLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AchievmentDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CreditRatingOrganizationDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExistingProductDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinanceMeansDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FutureFinancialEstimatesDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OwnershipDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PastFinancialEstiamateDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PromotorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProposedProductDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.SecurityCorporateDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.TotalCostOfProjectService;
import com.capitaworld.service.loans.service.teaser.primaryview.TermLoanPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.CityByCityListIdClient;
import com.capitaworld.service.oneform.client.CountryByCountryListIdClient;
import com.capitaworld.service.oneform.client.IndustryClient;
import com.capitaworld.service.oneform.client.IndustrySectorSubSectorTeaser;
import com.capitaworld.service.oneform.client.RatingByRatingIdClient;
import com.capitaworld.service.oneform.client.StateListByStateListIdClient;
import com.capitaworld.service.oneform.enums.Constitution;
import com.capitaworld.service.oneform.enums.CreditRatingFund;
import com.capitaworld.service.oneform.enums.CreditRatingTerm;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.EstablishmentMonths;
import com.capitaworld.service.oneform.enums.FinanceCategory;
import com.capitaworld.service.oneform.enums.LoanType;
import com.capitaworld.service.oneform.enums.NatureFacility;
import com.capitaworld.service.oneform.enums.Particular;
import com.capitaworld.service.oneform.enums.RatingAgency;
import com.capitaworld.service.oneform.enums.ShareHoldingCategory;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.model.IndustrySectorSubSectorTeaserRequest;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
@Transactional
public class TermLoanPrimaryViewServiceImpl implements TermLoanPrimaryViewService {

	private static final Logger logger = LoggerFactory.getLogger(TermLoanPrimaryViewServiceImpl.class);

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private PrimaryTermLoanDetailRepository primaryTermLoanLoanDetailRepository;

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
	private TotalCostOfProjectService costOfProjectService;

	@Autowired
	private FinanceMeansDetailsService financeMeansDetailsService;

	protected static final String DMS_URL = "dmsURL";
	protected static final String ONE_FORM_URL = "oneForm";

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/mm/yyyy");

	@Override
	public TermLoanPrimaryViewResponse getTermLoanPrimaryViewDetails(Long toApplicationId, Long userId) {
		TermLoanPrimaryViewResponse termLoanPrimaryViewResponse = new TermLoanPrimaryViewResponse();
		// get details of CorporateApplicantDetail
		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
				.getByApplicationAndUserId(userId, toApplicationId);
		// set value to response
		if (corporateApplicantDetail != null)
			BeanUtils.copyProperties(corporateApplicantDetail, termLoanPrimaryViewResponse);
		if (corporateApplicantDetail.getConstitutionId() != null)
			termLoanPrimaryViewResponse
					.setConstitution(Constitution.getById(corporateApplicantDetail.getConstitutionId()).getValue());
		if (corporateApplicantDetail.getEstablishmentMonth() != null)
			termLoanPrimaryViewResponse.setEstablishmentMonth(
					EstablishmentMonths.getById(corporateApplicantDetail.getEstablishmentMonth()).getValue());

		// set city
		List<Long> cityList = new ArrayList<>();
		cityList.add(corporateApplicantDetail.getRegisteredCityId());
		CityByCityListIdClient cityByCityListIdClient = new CityByCityListIdClient(
				environment.getProperty(ONE_FORM_URL));
		if (cityList != null && !cityList.isEmpty()) {
			try {
				OneFormResponse oneFormResponse = cityByCityListIdClient.send(cityList);
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					termLoanPrimaryViewResponse.setCity(masterResponse.getValue());
				} else {
					termLoanPrimaryViewResponse.setCity(CommonUtils.NOT_APPLICABLE);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// set state
		List<Long> stateList = new ArrayList<>();

		stateList.add(Long.valueOf(corporateApplicantDetail.getAdministrativeStateId()));
		StateListByStateListIdClient stateListByStateListIdClient = new StateListByStateListIdClient(
				environment.getProperty(ONE_FORM_URL));
		if (stateList != null && !stateList.isEmpty()) {
			try {
				OneFormResponse oneFormResponse = stateListByStateListIdClient.send(stateList);
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					termLoanPrimaryViewResponse.setState(masterResponse.getValue());
				} else {
					termLoanPrimaryViewResponse.setState(CommonUtils.NOT_APPLICABLE);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// set country
		List<Long> countryList = new ArrayList<>();

		countryList.add(Long.valueOf(corporateApplicantDetail.getAdministrativeStateId()));
		CountryByCountryListIdClient countryByCountryListIdClient = new CountryByCountryListIdClient(
				environment.getProperty(ONE_FORM_URL));
		if (countryList != null && !countryList.isEmpty()) {
			try {
				OneFormResponse oneFormResponse = countryByCountryListIdClient.send(countryList);
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					termLoanPrimaryViewResponse.setCountry(masterResponse.getValue());
				} else {
					termLoanPrimaryViewResponse.setCountry(CommonUtils.NOT_APPLICABLE);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		IndustryClient industryClient = new IndustryClient(environment.getProperty(ONE_FORM_URL));
		List<Long> keyVerticalFundingId = new ArrayList<>();
		keyVerticalFundingId.add(corporateApplicantDetail.getKeyVericalFunding());
		if (keyVerticalFundingId != null && !keyVerticalFundingId.isEmpty()) {
			try {
				OneFormResponse oneFormResponse = industryClient.send(keyVerticalFundingId);
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					termLoanPrimaryViewResponse.setKeyVericalFunding(masterResponse.getValue());
				} else {
					termLoanPrimaryViewResponse.setKeyVericalFunding(CommonUtils.NOT_APPLICABLE);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		List<Long> industryList = industrySectorRepository.getIndustryByApplicationId(toApplicationId);
		List<Long> sectorList = industrySectorRepository.getSectorByApplicationId(toApplicationId);
		List<Long> subSectorList = subSectorRepository.getSubSectorByApplicationId(toApplicationId);

		IndustrySectorSubSectorTeaser industrySectorSubSectorTeaser = new IndustrySectorSubSectorTeaser(
				environment.getProperty(ONE_FORM_URL));
		IndustrySectorSubSectorTeaserRequest industrySectorSubSectorTeaserRequest = new IndustrySectorSubSectorTeaserRequest();
		industrySectorSubSectorTeaserRequest.setIndustryList(industryList);
		industrySectorSubSectorTeaserRequest.setSectorList(sectorList);
		industrySectorSubSectorTeaserRequest.setSubSectorList(subSectorList);
		if (industryList != null && !industryList.isEmpty()) {
			try {
				OneFormResponse oneFormResponse = industrySectorSubSectorTeaser
						.send(industrySectorSubSectorTeaserRequest);
				termLoanPrimaryViewResponse.setIndustrySector(oneFormResponse.getListData());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// get value of Term Loan data
		PrimaryTermLoanDetail primaryTermLoanDetail = primaryTermLoanLoanDetailRepository
				.getByApplicationAndUserId(toApplicationId, userId);
		// set value to response
		if (primaryTermLoanDetail != null)
			BeanUtils.copyProperties(primaryTermLoanDetail, termLoanPrimaryViewResponse);
		if (primaryTermLoanDetail.getCurrencyId() != null && primaryTermLoanDetail.getDenominationId() != null)
			termLoanPrimaryViewResponse
					.setCurrencyDenomination(Currency.getById(primaryTermLoanDetail.getCurrencyId()).getValue() + " in "
							+ Denomination.getById(primaryTermLoanDetail.getDenominationId()).getValue());
		if (primaryTermLoanDetail.getProductId() != null)
			termLoanPrimaryViewResponse.setLoanType(LoanType.getById(primaryTermLoanDetail.getProductId()).getValue());

		if (primaryTermLoanDetail.getModifiedDate() != null)
			termLoanPrimaryViewResponse.setDateOfProposal(DATE_FORMAT.format(primaryTermLoanDetail.getModifiedDate()));
		// get value of proposed product and set in response
		try {
			termLoanPrimaryViewResponse.setProposedProductDetailRequestList(
					proposedProductDetailsService.getProposedProductDetailList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Proposed Product {}", e);
		}

		// get value of Existing product and set in response
		try {
			termLoanPrimaryViewResponse.setExistingProductDetailRequestList(
					existingProductDetailsService.getExistingProductDetailList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Existing Product {}", e);
		}

		// get value of achievement details and set in response
		try {
			termLoanPrimaryViewResponse.setAchievementDetailList(
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
				if(creditRatingOrganizationDetailRequest.getCreditRatingFundId()!=null)
				creditRatingOrganizationDetailResponse.setCreditRatingFund(CreditRatingFund
						.getById(creditRatingOrganizationDetailRequest.getCreditRatingFundId()).getValue());

				RatingByRatingIdClient ratingOptionClient = new RatingByRatingIdClient(
						environment.getProperty(ONE_FORM_URL));
				OneFormResponse oneFormResponse = ratingOptionClient
						.send(Long.valueOf(creditRatingOrganizationDetailRequest.getCreditRatingOptionId()));
				MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) oneFormResponse.getData(), MasterResponse.class);
				if (masterResponse != null) {
					creditRatingOrganizationDetailResponse.setCreditRatingOption(masterResponse.getValue());
				} else {
					termLoanPrimaryViewResponse.setKeyVericalFunding(CommonUtils.NOT_APPLICABLE);
				}

				if(creditRatingOrganizationDetailRequest.getCreditRatingTermId()!=null)
				creditRatingOrganizationDetailResponse.setCreditRatingTerm(CreditRatingTerm
						.getById(creditRatingOrganizationDetailRequest.getCreditRatingTermId()).getValue());
				if(creditRatingOrganizationDetailRequest.getRatingAgencyId()!=null)
				creditRatingOrganizationDetailResponse.setRatingAgency(
						RatingAgency.getById(creditRatingOrganizationDetailRequest.getRatingAgencyId()).getValue());
				creditRatingOrganizationDetailResponse
						.setFacilityName(creditRatingOrganizationDetailRequest.getFacilityName());
				creditRatingOrganizationDetailResponseList.add(creditRatingOrganizationDetailResponse);
			}
			termLoanPrimaryViewResponse
					.setCreditRatingOrganizationDetailResponse(creditRatingOrganizationDetailResponseList);
		} catch (Exception e) {
			logger.error("Problem to get Data of Credit Rating {}", e);
		}

		// get value of Ownership Details and set in response
		try {
			List<OwnershipDetailRequest> ownershipDetailRequestsList = ownershipDetailsService
					.getOwnershipDetailList(toApplicationId, userId);
			List<OwnershipDetailResponse> ownershipDetailResponseList = new ArrayList<>();

			for (OwnershipDetailRequest ownershipDetailRequest : ownershipDetailRequestsList) {
				OwnershipDetailResponse ownershipDetailResponse = new OwnershipDetailResponse();
				BeanUtils.copyProperties(ownershipDetailRequest, ownershipDetailResponse);
				if(ownershipDetailRequest.getShareHoldingCategoryId()!=null)
				ownershipDetailResponse.setShareHoldingCategory(
						ShareHoldingCategory.getById(ownershipDetailRequest.getShareHoldingCategoryId()).getValue());
				ownershipDetailResponseList.add(ownershipDetailResponse);
			}
			termLoanPrimaryViewResponse.setOwnershipDetailResponseList(ownershipDetailResponseList);

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
				BeanUtils.copyProperties(promotorBackgroundDetailRequest, promotorBackgroundDetailResponse);
				if(promotorBackgroundDetailRequest.getSalutationId()!=null)
				promotorBackgroundDetailResponse
						.setPromotorsName(Title.getById(promotorBackgroundDetailRequest.getSalutationId()).getValue()
								+ " " + promotorBackgroundDetailRequest.getPromotorsName());
				promotorBackgroundDetailResponseList.add(promotorBackgroundDetailResponse);
			}
			termLoanPrimaryViewResponse.setPromotorBackgroundDetailResponseList(promotorBackgroundDetailResponseList);
		} catch (Exception e) {
			logger.error("Problem to get Data of Promotor Background {}", e);
		}

		// get value of Past Financial and set in response
		try {
			termLoanPrimaryViewResponse.setPastFinancialEstimatesDetailRequestList(
					pastFinancialEstiamateDetailsService.getFinancialListData(userId, toApplicationId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Past Financial {}", e);
		}

		// get value of Future Projection and set in response
		try {
			termLoanPrimaryViewResponse
					.setFutureFinancialEstimatesDetailRequestList(futureFinancialEstimatesDetailsService
							.getFutureFinancialEstimateDetailsList(toApplicationId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Future Projection {}", e);
		}

		// get value of Security and set in response
		try {
			termLoanPrimaryViewResponse.setSecurityCorporateDetailRequestList(
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
				BeanUtils.copyProperties(financialArrangementsDetailRequest, financialArrangementsDetailResponse);
				if(financialArrangementsDetailRequest.getFacilityNatureId()!=null)
				financialArrangementsDetailResponse.setFacilityNature(
						NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
				financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
			}
			termLoanPrimaryViewResponse
					.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);

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
				
				if(financeMeansDetailRequest.getFinanceMeansCategoryId()!=null)
				detailResponse.setFinanceMeansCategory(FinanceCategory
						.getById(Integer.parseInt(financeMeansDetailRequest.getFinanceMeansCategoryId().toString()))
						.getValue());
				financeMeansDetailResponsesList.add(detailResponse);
			}
			termLoanPrimaryViewResponse.setFinanceMeansDetailResponseList(financeMeansDetailResponsesList);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error("Problem to get Data of Finance Means Details {}", e1);
		}

		// get Total cost of project and set in response
		try {
			List<TotalCostOfProjectRequest> costOfProjectsList = costOfProjectService
					.getCostOfProjectDetailList(toApplicationId, userId);
			List<TotalCostOfProjectResponse> costOfProjectResponses = new ArrayList<TotalCostOfProjectResponse>();
			for (TotalCostOfProjectRequest costOfProjectRequest : costOfProjectsList) {
				TotalCostOfProjectResponse costOfProjectResponse = new TotalCostOfProjectResponse();
				BeanUtils.copyProperties(costOfProjectRequest, costOfProjectResponse);
				if(costOfProjectRequest.getParticularsId()!=null)
				costOfProjectResponse.setParticulars(Particular
						.getById(Integer.parseInt(costOfProjectRequest.getParticularsId().toString())).getValue());
				costOfProjectResponses.add(costOfProjectResponse);
			}
			termLoanPrimaryViewResponse.setTotalCostOfProjectResponseList(costOfProjectResponses);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error("Problem to get Data of Total cost of project{}", e1);
		}

		// get list of Brochure
		DMSClient dmsClient = new DMSClient(environment.getProperty(DMS_URL));
		DocumentRequest documentRequest = new DocumentRequest();
		documentRequest.setApplicationId(toApplicationId);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_BROCHURE_OF_PROPOSED_ACTIVITIES);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			termLoanPrimaryViewResponse.setBrochureList(documentResponse.getDataList());
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// get list fo certificate
		documentRequest.setApplicationId(toApplicationId);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_CERTIFICATE_OF_INCORPORATION);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			termLoanPrimaryViewResponse.setCertificateList(documentResponse.getDataList());
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// get list of pan card
		documentRequest.setApplicationId(toApplicationId);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_COPY_OF_PAN_CARD);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			termLoanPrimaryViewResponse.setPanCardList(documentResponse.getDataList());
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// get profile pic
		documentRequest.setApplicationId(toApplicationId);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_PROFIEL_PICTURE);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			termLoanPrimaryViewResponse.setProfilePic(documentResponse.getDataList());
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return termLoanPrimaryViewResponse;
	}

}
