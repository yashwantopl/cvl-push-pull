package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.dms.util.CommonUtil;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.AddressResponse;
import com.capitaworld.service.loans.model.retail.BankAccountHeldDetailsRequest;
import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailResponse;
import com.capitaworld.service.loans.model.retail.ExistingLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.retail.FixedDepositsDetailsRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailResponse;
import com.capitaworld.service.loans.model.retail.OtherIncomeDetailRequest;
import com.capitaworld.service.loans.model.retail.OtherIncomeDetailResponse;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewCommonResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.common.DocumentManagementService;
import com.capitaworld.service.loans.service.fundseeker.retail.BankAccountHeldDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.CreditCardsDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.ExistingLoanDetailsService;
import com.capitaworld.service.loans.service.fundseeker.retail.FixedDepositsDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.OtherCurrentAssetDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.OtherIncomeDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.ReferenceRetailDetailsService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.AlliedActivity;
import com.capitaworld.service.oneform.enums.Assets;
import com.capitaworld.service.oneform.enums.CastCategory;
import com.capitaworld.service.oneform.enums.CreditCardTypesRetail;
import com.capitaworld.service.oneform.enums.EducationStatusRetailMst;
import com.capitaworld.service.oneform.enums.EmployeeWith;
import com.capitaworld.service.oneform.enums.EmploymentStatusRetailMst;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.IncomeDetails;
import com.capitaworld.service.oneform.enums.IndustryType;
import com.capitaworld.service.oneform.enums.LandSize;
import com.capitaworld.service.oneform.enums.MaritalStatus;
import com.capitaworld.service.oneform.enums.Occupation;
import com.capitaworld.service.oneform.enums.OccupationNature;
import com.capitaworld.service.oneform.enums.OfficeTypeRetailMst;
import com.capitaworld.service.oneform.enums.OwnershipTypeRetailMst;
import com.capitaworld.service.oneform.enums.RelationshipType;
import com.capitaworld.service.oneform.enums.ReligionRetailMst;
import com.capitaworld.service.oneform.enums.ResidenceStatusRetailMst;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
@Transactional
public class CoApplicantServiceImpl implements CoApplicantService {

	private static final Logger logger = LoggerFactory.getLogger(CoApplicantServiceImpl.class.getName());

	protected static final String DMS_URL = "dmsURL";

	@Autowired
	private CoApplicantDetailRepository coApplicantDetailRepository;

	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;

	@Autowired
	Environment environment;

	@Autowired
	private CreditCardsDetailService creditCardDetailsService;

	@Autowired
	private ExistingLoanDetailsService existingLoanService;

	@Autowired
	private BankAccountHeldDetailService bankAccountsHeldService;

	@Autowired
	private FixedDepositsDetailService fixedDepositService;

	@Autowired
	private OtherCurrentAssetDetailService otherCurrentAssetService;

	@Autowired
	private OtherIncomeDetailService otherIncomeService;

	@Autowired
	private ReferenceRetailDetailsService referenceService;

	@Autowired
	private DocumentManagementService documentManagementService;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private OneFormClient oneFormClient;

	@Override
	public boolean save(CoApplicantRequest applicantRequest, Long applicationId, Long userId) throws Exception {
		try {
			Long finalUserId = CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId
					: applicantRequest.getClientId();
			CoApplicantDetail coDetails = coApplicantDetailRepository.get(applicationId, finalUserId,
					applicantRequest.getId());
			if (coDetails != null) {
				if (applicantRequest.getIsActive() != null && !applicantRequest.getIsActive().booleanValue()) {
					coApplicantDetailRepository.inactiveCoApplicant(applicationId, applicantRequest.getId());
					return true;
				}
				coDetails.setModifiedBy(userId);
				coDetails.setModifiedDate(new Date());
			} else {
				coDetails = new CoApplicantDetail();
				coDetails.setRelationshipWithApplicant(applicantRequest.getRelationshipWithApplicant());
				coDetails.setCreatedBy(userId);
				coDetails.setCreatedDate(new Date());
				coDetails.setApplicationId(new LoanApplicationMaster(applicationId));
			}
			BeanUtils.copyProperties(applicantRequest, coDetails, CommonUtils.IgnorableCopy.RETAIL_FINAL);
			copyAddressFromRequestToDomain(applicantRequest, coDetails);
			if (applicantRequest.getDate() != null && applicantRequest.getMonth() != null
					&& applicantRequest.getYear() != null) {
				Date birthDate = CommonUtils.getDateByDateMonthYear(applicantRequest.getDate(),
						applicantRequest.getMonth(), applicantRequest.getYear());
				coDetails.setBirthDate(birthDate);
			}
			coApplicantDetailRepository.save(coDetails);

			List<Long> coAppIds = coApplicantDetailRepository.getCoAppIds(applicationId, finalUserId);
			int index = coAppIds.indexOf(coDetails.getId());
			if (index == 0) {
				if (!CommonUtils.isObjectNullOrEmpty(applicantRequest.getIsCoApp1DetailsFilled())) {
					loanApplicationRepository.setIsCoAppOneProfileMandatoryFilled(applicationId, finalUserId,
							applicantRequest.getIsCoApp1DetailsFilled());
				}
			} else if (index == 1) {
				if (!CommonUtils.isObjectNullOrEmpty(applicantRequest.getIsCoApp2DetailsFilled())) {
					loanApplicationRepository.setIsCoAppTwoProfileMandatoryFilled(applicationId, finalUserId,
							applicantRequest.getIsCoApp2DetailsFilled());
				}
			}

			// Updating Bowl Count
			loanApplicationRepository.setProfileFilledCount(applicantRequest.getApplicationId(), finalUserId,
					applicantRequest.getDetailsFilledCount());
			return true;

		} catch (Exception e) {
			logger.error("Error while Saving Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<Long> getCoAppIds(Long userId, Long applicationId) throws Exception {
		try {
			return coApplicantDetailRepository.getCoAppIds(applicationId, userId);
		} catch (Exception e) {
			logger.error("Error while getCoAppIds:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public CoApplicantRequest get(Long userId, Long applicationId, Long id) throws Exception {
		try {
			CoApplicantDetail applicantDetail = coApplicantDetailRepository.get(applicationId, userId, id);
			if (applicantDetail == null) {
				throw new NullPointerException("CoApplicantDetail Record not exists in DB of ID : " + id
						+ " and ApplicationId==>" + applicationId + " userId ==>" + userId);
			}
			CoApplicantRequest applicantRequest = new CoApplicantRequest();
			BeanUtils.copyProperties(applicantDetail, applicantRequest, CommonUtils.IgnorableCopy.RETAIL_FINAL);
			copyAddressFromDomainToRequest(applicantDetail, applicantRequest);
			Integer[] saperatedTime = CommonUtils.saperateDayMonthYearFromDate(applicantDetail.getBirthDate());
			applicantRequest.setDate(saperatedTime[0]);
			applicantRequest.setMonth(saperatedTime[1]);
			applicantRequest.setYear(saperatedTime[2]);
			applicantRequest.setCurrencyId(retailApplicantDetailRepository.getCurrency(userId, applicationId));
			applicantRequest.setDetailsFilledCount(applicantDetail.getApplicationId().getDetailsFilledCount());
			return applicantRequest;
		} catch (Exception e) {
			logger.error("Error while getting CoApplicant Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<CoApplicantRequest> getList(Long applicationId, Long userId) throws Exception {
		try {
			List<CoApplicantDetail> details = coApplicantDetailRepository.getList(applicationId, userId);
			List<CoApplicantRequest> requests = new ArrayList<>(details.size());
			for (CoApplicantDetail detail : details) {
				CoApplicantRequest request = new CoApplicantRequest();
				BeanUtils.copyProperties(detail, request, CommonUtils.IgnorableCopy.RETAIL_FINAL);
				if(!CommonUtils.isObjectNullOrEmpty(detail.getBirthDate())) {
					logger.info("Birthdate==>" + detail.getBirthDate().toString());					
				}
				Integer[] saperatedTime = CommonUtils.saperateDayMonthYearFromDate(detail.getBirthDate());
				logger.info("Date in Loan==>" + saperatedTime[0]);
				logger.info("Month in Loan==>" + saperatedTime[1]);
				logger.info("Year in Loan==>" + saperatedTime[2]);
				request.setDate(saperatedTime[0]);
				request.setMonth(saperatedTime[1]);
				request.setYear(saperatedTime[2]);
				requests.add(request);
			}
			return requests;
		} catch (Exception e) {
			logger.error("Error while getting List of CoApplicant Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public boolean saveFinal(FinalCommonRetailRequest applicantRequest, Long userId) throws Exception {
		try {
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId
					: applicantRequest.getClientId());
			CoApplicantDetail coDetails = coApplicantDetailRepository.get(applicantRequest.getApplicationId(),
					finalUserId, applicantRequest.getId());
			if (coDetails == null) {
				throw new NullPointerException("CoApplicant Id Record not exists in DB ID: " + applicantRequest.getId()
						+ " and Application Id==>" + applicantRequest.getApplicationId());
			}
			coDetails.setModifiedBy(userId);
			coDetails.setModifiedDate(new Date());
			BeanUtils.copyProperties(applicantRequest, coDetails, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
			coApplicantDetailRepository.save(coDetails);

			List<Long> coAppIds = coApplicantDetailRepository.getCoAppIds(applicantRequest.getApplicationId(),
					finalUserId);
			int index = coAppIds.indexOf(coDetails.getId());
			if (index == 0) {
				if (!CommonUtils.isObjectNullOrEmpty(applicantRequest.getIsCoApp1FinalFilled())) {
					loanApplicationRepository.setIsCoAppOneFinalMandatoryFilled(applicantRequest.getApplicationId(),
							finalUserId, applicantRequest.getIsCoApp1FinalFilled());
				}
			} else if (index == 1) {
				if (!CommonUtils.isObjectNullOrEmpty(applicantRequest.getIsCoApp2FinalFilled())) {
					loanApplicationRepository.setIsCoAppTwoFinalMandatoryFilled(applicantRequest.getApplicationId(),
							finalUserId, applicantRequest.getIsCoApp2FinalFilled());
				}
			}
			// Updating Final Count
			loanApplicationRepository.setFinalFilledCount(applicantRequest.getApplicationId(), finalUserId,
					applicantRequest.getFinalFilledCount());

			return true;

		} catch (Exception e) {
			logger.error("Error while Saving Final CoApplicant Retail Profile:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	@Override
	public FinalCommonRetailRequest getFinal(Long userId, Long applicationId, Long id) throws Exception {
		try {
			CoApplicantDetail applicantDetail = coApplicantDetailRepository.get(applicationId, userId, id);
			if (applicantDetail == null) {
				throw new NullPointerException("CoApplicantDetail Record of Final Portion not exists in DB of ID : "
						+ userId + " and Application Id ==>" + applicationId);
			}
			FinalCommonRetailRequest applicantRequest = new FinalCommonRetailRequest();
			BeanUtils.copyProperties(applicantDetail, applicantRequest, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
			Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
			applicantRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
			applicantRequest.setFinalFilledCount(applicantDetail.getApplicationId().getFinalFilledCount());
			return applicantRequest;
		} catch (Exception e) {
			logger.error("Error while getting Final CoApplicant Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	public static void copyAddressFromRequestToDomain(CoApplicantRequest from, CoApplicantDetail to) {
		if (from.getFirstAddress() != null) {
			to.setPermanentPremiseNumberName(from.getFirstAddress().getPremiseNumber());
			to.setPermanentStreetName(from.getFirstAddress().getStreetName());
			to.setPermanentLandMark(from.getFirstAddress().getLandMark());
			if (from.getFirstAddress().getCityId() != null) {
				to.setPermanentCityId(from.getFirstAddress().getCityId().intValue());
			}
			to.setPermanentStateId(from.getFirstAddress().getStateId());
			to.setPermanentCountryId(from.getFirstAddress().getCountryId());
			if (from.getFirstAddress().getPincode() != null) {
				to.setPermanentPincode(from.getFirstAddress().getPincode().intValue());
			}
		}

		if (from.getAddressSameAs() != null && from.getAddressSameAs().booleanValue()) {
			if (from.getFirstAddress() != null) {
				to.setOfficePremiseNumberName(from.getFirstAddress().getPremiseNumber());
				to.setOfficeStreetName(from.getFirstAddress().getStreetName());
				to.setOfficeLandMark(from.getFirstAddress().getLandMark());
				if (from.getFirstAddress().getCityId() != null) {
					to.setOfficeCityId(from.getFirstAddress().getCityId().intValue());
				}
				to.setOfficeStateId(from.getFirstAddress().getStateId());
				to.setOfficeCountryId(from.getFirstAddress().getCountryId());
				if (from.getFirstAddress().getPincode() != null) {
					to.setOfficePincode(from.getFirstAddress().getPincode().intValue());
				}

			}
		} else {
			if (from.getSecondAddress() != null) {
				to.setOfficePremiseNumberName(from.getSecondAddress().getPremiseNumber());
				to.setOfficeStreetName(from.getSecondAddress().getStreetName());
				to.setOfficeLandMark(from.getSecondAddress().getLandMark());
				if (from.getSecondAddress().getCityId() != null) {
					to.setOfficeCityId(from.getSecondAddress().getCityId().intValue());
				}

				to.setOfficeStateId(from.getSecondAddress().getStateId());
				to.setOfficeCountryId(from.getSecondAddress().getCountryId());
				if (from.getSecondAddress().getPincode() != null) {
					to.setOfficePincode(from.getSecondAddress().getPincode().intValue());
				}

			}
		}

	}

	public static void copyAddressFromDomainToRequest(CoApplicantDetail from, CoApplicantRequest to) {
		Address address = new Address();
		address.setPremiseNumber(from.getPermanentPremiseNumberName());
		address.setLandMark(from.getPermanentLandMark());
		address.setStreetName(from.getPermanentStreetName());
		if (from.getPermanentCityId() != null) {
			address.setCityId(from.getPermanentCityId().longValue());
		}
		address.setStateId(from.getPermanentStateId());
		address.setCountryId(from.getPermanentCountryId());
		if (from.getPermanentPincode() != null) {
			address.setPincode(from.getPermanentPincode().longValue());
		}
		to.setFirstAddress(address);
		if (from.getAddressSameAs() != null && from.getAddressSameAs()) {
			to.setSecondAddress(address);
		} else {
			address = new Address();
			address.setPremiseNumber(from.getOfficePremiseNumberName());
			address.setLandMark(from.getOfficeLandMark());
			address.setStreetName(from.getOfficeStreetName());
			if (from.getOfficeCityId() != null) {
				address.setCityId(from.getOfficeCityId().longValue());
			}
			address.setStateId(from.getOfficeStateId());
			address.setCountryId(from.getOfficeCountryId());
			if (from.getOfficePincode() != null) {
				address.setPincode(from.getOfficePincode().longValue());
			}
			to.setSecondAddress(address);
		}
	}

	@Override
	public List<RetailProfileViewResponse> getCoApplicantPLResponse(Long applicantId, Long userId, int productId)
			throws Exception {
		try {
			List<CoApplicantDetail> coApplicantDetails = coApplicantDetailRepository.getList(applicantId, userId);
			if (coApplicantDetails != null && !coApplicantDetails.isEmpty()) {
				List<RetailProfileViewResponse> plResponses = new ArrayList<RetailProfileViewResponse>();

				for (CoApplicantDetail coApplicantDetail : coApplicantDetails) {
					RetailProfileViewResponse profileViewPLResponse = new RetailProfileViewResponse();
					profileViewPLResponse.setNatureOfOccupation(
							OccupationNature.getById(coApplicantDetail.getOccupationId()).getValue());
					profileViewPLResponse.setNatureOfOccupationId(coApplicantDetail.getOccupationId());
					if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getOccupationId())) {
						switch (coApplicantDetail.getOccupationId().intValue()) {
						case 2: // Salaried
							profileViewPLResponse.setCompanyName(coApplicantDetail.getCompanyName());
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getEmployedWithId())) {
								if (coApplicantDetail.getEmployedWithId() != 8) {
									profileViewPLResponse.setEmployeeWith(
											EmployeeWith.getById(coApplicantDetail.getEmployedWithId()).getValue());
								} else {
									profileViewPLResponse.setEmployeeWith(coApplicantDetail.getEmployedWithOther());
								}
							}
							profileViewPLResponse.setYearsInCurrentJob(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentJobYear()) ?  coApplicantDetail.getCurrentJobYear().toString() : "-");
							profileViewPLResponse.setMonthsInCurrentJob(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentJobMonth()) ?  coApplicantDetail.getCurrentJobMonth().toString() : "-");
							profileViewPLResponse.setTotalExperienceInMonths(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceMonth()) ?  coApplicantDetail.getTotalExperienceMonth().toString() : "-");
							profileViewPLResponse.setTotalExperienceInYears(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceYear()) ?  coApplicantDetail.getTotalExperienceYear().toString() : "-");
							profileViewPLResponse.setPreviousExperienceInMonths(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPreviousJobMonth()) ?  coApplicantDetail.getPreviousJobMonth().toString() : "-");
							profileViewPLResponse.setPreviousExperienceInYears(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPreviousJobYear()) ?  coApplicantDetail.getPreviousJobYear().toString() : "-");
							profileViewPLResponse.setPreviousEmployerName(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPreviousEmployersName()) ?  coApplicantDetail.getPreviousEmployersName() : "-");
							profileViewPLResponse.setPreviousEmployerAddress(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPreviousEmployersAddress()) ?  coApplicantDetail.getPreviousEmployersAddress() : "-");
							break;
						case 3: // Business
						case 4: // Self Employed
							profileViewPLResponse.setEntityName(coApplicantDetail.getEntityName());
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getIndustryTypeId())) {
								if (coApplicantDetail.getIndustryTypeId() != 16) {
									profileViewPLResponse.setIndustryType(
											IndustryType.getById(coApplicantDetail.getIndustryTypeId()).getValue());
								} else {
									profileViewPLResponse.setIndustryType(coApplicantDetail.getIndustryTypeOther());
								}
							}
							profileViewPLResponse.setAnnualTurnover(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAnnualTurnover()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getAnnualTurnover().toString()) : "-");
							profileViewPLResponse.setMonthlyLoanObligation(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMonthlyLoanObligation()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getMonthlyLoanObligation().toString()): "-");
							profileViewPLResponse.setPatPreviousYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPatPreviousYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getPatPreviousYear().toString()): "-");
							profileViewPLResponse.setPatCurrentYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPatCurrentYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getPatCurrentYear().toString()): "-");
							profileViewPLResponse.setDepreciationPreviousYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getDepreciationPreviousYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getDepreciationPreviousYear().toString()): "-");
							profileViewPLResponse.setDepreciationCurrentYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getDepreciationCurrentYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getDepreciationCurrentYear().toString()): "-");
							profileViewPLResponse.setRemunerationPreviousYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getRemunerationPreviousYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getRemunerationPreviousYear().toString()): "-");
							profileViewPLResponse.setRemunerationCurrentYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getRemunerationCurrentYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getRemunerationCurrentYear().toString()): "-");
							profileViewPLResponse.setBusinessExperience(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBusinessStartDate()) ? CommonUtils.calculateBusinessExperience(coApplicantDetail.getBusinessStartDate()) : "-");
							break;
						case 5:// Self Employed Professional
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getSelfEmployedOccupationId())) {
								if (coApplicantDetail.getSelfEmployedOccupationId().intValue() != 10) {
									profileViewPLResponse.setOccupation(Occupation
											.getById(coApplicantDetail.getSelfEmployedOccupationId()).getValue());
								} else {
									profileViewPLResponse
											.setOccupation(coApplicantDetail.getSelfEmployedOccupationOther());
								}
							}
							profileViewPLResponse.setAnnualTurnover(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAnnualTurnover()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getAnnualTurnover().toString()) : "-");
							profileViewPLResponse.setMonthlyLoanObligation(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMonthlyLoanObligation()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getMonthlyLoanObligation().toString()): "-");
							profileViewPLResponse.setPatPreviousYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPatPreviousYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getPatPreviousYear().toString()): "-");
							profileViewPLResponse.setPatCurrentYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPatCurrentYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getPatCurrentYear().toString()): "-");
							profileViewPLResponse.setDepreciationPreviousYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getDepreciationPreviousYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getDepreciationPreviousYear().toString()): "-");
							profileViewPLResponse.setDepreciationCurrentYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getDepreciationCurrentYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getDepreciationCurrentYear().toString()): "-");
							profileViewPLResponse.setRemunerationPreviousYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getRemunerationPreviousYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getRemunerationPreviousYear().toString()): "-");
							profileViewPLResponse.setRemunerationCurrentYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getRemunerationCurrentYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getRemunerationCurrentYear().toString()): "-");
							profileViewPLResponse.setBusinessExperience(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBusinessStartDate()) ? CommonUtils.calculateBusinessExperience(coApplicantDetail.getBusinessStartDate()) : "-");
							break;
						case 6:// Agriculturist
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getLandSize())) {
								profileViewPLResponse.setLandSize(
										LandSize.getById(coApplicantDetail.getLandSize().intValue()).getValue());
							}
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAlliedActivityId())) {
								profileViewPLResponse.setAlliedActivity(
										AlliedActivity.getById(coApplicantDetail.getAlliedActivityId()).getValue());
							}
							profileViewPLResponse.setAnnualTurnover(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAnnualTurnover()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getAnnualTurnover().toString()) : "-");
							profileViewPLResponse.setMonthlyLoanObligation(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMonthlyLoanObligation()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getMonthlyLoanObligation().toString()): "-");
							profileViewPLResponse.setPatPreviousYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPatPreviousYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getPatPreviousYear().toString()): "-");
							profileViewPLResponse.setPatCurrentYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPatCurrentYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getPatCurrentYear().toString()): "-");
							profileViewPLResponse.setDepreciationPreviousYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getDepreciationPreviousYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getDepreciationPreviousYear().toString()): "-");
							profileViewPLResponse.setDepreciationCurrentYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getDepreciationCurrentYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getDepreciationCurrentYear().toString()): "-");
							profileViewPLResponse.setRemunerationPreviousYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getRemunerationPreviousYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getRemunerationPreviousYear().toString()): "-");
							profileViewPLResponse.setRemunerationCurrentYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getRemunerationCurrentYear()) ? CommonUtils.CurrencyFormat( coApplicantDetail.getRemunerationCurrentYear().toString()): "-");
							profileViewPLResponse.setBusinessExperience(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBusinessStartDate()) ? CommonUtils.calculateBusinessExperience(coApplicantDetail.getBusinessStartDate()) : "-");
							break;
						default:
							break;
						}
					}

					//start of set address
					// set office address
					AddressResponse officeAddress = new AddressResponse();
					try {
						List<Long> officeCity = new ArrayList<Long>(1);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getOfficeCityId())) {
							officeCity.add((long)coApplicantDetail.getOfficeCityId());
							OneFormResponse formResponse = oneFormClient.getCityByCityListId(officeCity);
							MasterResponse data = MultipleJSONObjectHelper.getObjectFromMap(
									(LinkedHashMap<String, Object>) formResponse.getListData().get(0),
									MasterResponse.class);
							if (!CommonUtils.isObjectNullOrEmpty(data)) {
								officeAddress.setCity(data.getValue());
							} else {
								officeAddress.setCity("-");
							}
						} else {
							officeAddress.setCity("-");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						List<Long> officeCountry = new ArrayList<Long>(1);
						Long officeCountryLong = null;
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getOfficeCountryId())) {
							officeCountryLong = Long.valueOf(coApplicantDetail.getOfficeCountryId().toString());

							officeCountry.add(officeCountryLong);
							OneFormResponse country = oneFormClient.getCountryByCountryListId(officeCountry);
							MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap(
									(LinkedHashMap<String, Object>) country.getListData().get(0), MasterResponse.class);
							if (!CommonUtils.isObjectNullOrEmpty(dataCountry.getValue())) {
								officeAddress.setCountry(dataCountry.getValue());
							} else {
								officeAddress.setCountry("-");
							}
						} else {
							officeAddress.setCountry("-");
						}
					} catch (Exception e) {
						e.printStackTrace();

					}
					try {
						List<Long> officeState = new ArrayList<Long>(1);
						Long officeStateLong = null;
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getOfficeStateId())) {
							officeStateLong = Long.valueOf(coApplicantDetail.getOfficeStateId().toString());

							officeState.add(officeStateLong);
							OneFormResponse state = oneFormClient.getStateByStateListId(officeState);
							MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap(
									(LinkedHashMap<String, Object>) state.getListData().get(0), MasterResponse.class);
							if (!CommonUtil.isObjectNullOrEmpty(dataState)) {
								officeAddress.setState(dataState.getValue());
							} else {
								officeAddress.setState("-");
							}
						} else {
							officeAddress.setState("-");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					officeAddress.setLandMark(coApplicantDetail.getOfficeLandMark() !=null ? coApplicantDetail.getOfficeLandMark() : "");
					officeAddress.setPincode(coApplicantDetail.getOfficePincode() != null? coApplicantDetail.getOfficePincode().toString() : "");
					officeAddress.setPremiseNumber(coApplicantDetail.getOfficePremiseNumberName() != null ? coApplicantDetail.getOfficePremiseNumberName() : "");
					officeAddress.setStreetName(coApplicantDetail.getOfficeStreetName() != null ? coApplicantDetail.getOfficeStreetName() : "");
					profileViewPLResponse.setFirstAddress(officeAddress);
					profileViewPLResponse.setOtherIncome((!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getOtherIncome()) ? coApplicantDetail.getOtherIncome().toString() : ""));
					profileViewPLResponse.setOtherInvestment((!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getOtherInvestment()) ? coApplicantDetail.getOtherInvestment().toString() : ""));
					profileViewPLResponse.setTaxPaid((!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTaxPaidLastYear()) ? coApplicantDetail.getTaxPaidLastYear().toString() : ""));
					profileViewPLResponse.setBonusPerAnnum((!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBonusPerAnnum()) ? coApplicantDetail.getBonusPerAnnum().toString() : ""));
					profileViewPLResponse.setIncentivePerAnnum((!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getIncentivePerAnnum()) ? coApplicantDetail.getIncentivePerAnnum().toString() : ""));
					// set permanent address
					AddressResponse permanentAddress = new AddressResponse();
					try {
						List<Long> permanentCity = new ArrayList<Long>(1);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPermanentCityId())) {
							permanentCity.add((long)coApplicantDetail.getPermanentCityId());
							OneFormResponse formResponsePermanentCity = oneFormClient.getCityByCityListId(permanentCity);
							MasterResponse dataCity = MultipleJSONObjectHelper.getObjectFromMap(
									(LinkedHashMap<String, Object>) formResponsePermanentCity.getListData().get(0),
									MasterResponse.class);
							if (!CommonUtils.isObjectNullOrEmpty(dataCity)) {
								permanentAddress.setCity(dataCity.getValue());
							} else {
								permanentAddress.setCity("-");
							}
						} else {
							permanentAddress.setCity("-");
						}
					} catch (Exception e) {

					}
					try {
						List<Long> permanentCountry = new ArrayList<Long>(1);
						Long permanentCountryLong = null;
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPermanentCountryId())) {
							permanentCountryLong = Long.valueOf(coApplicantDetail.getPermanentCountryId().toString());
							permanentCountry.add(permanentCountryLong);
							OneFormResponse countryPermanent = oneFormClient.getCountryByCountryListId(permanentCountry);
							MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap(
									(LinkedHashMap<String, Object>) countryPermanent.getListData().get(0),
									MasterResponse.class);
							if (!CommonUtils.isObjectNullOrEmpty(dataCountry)) {
								permanentAddress.setCountry(dataCountry.getValue());
							} else {
								permanentAddress.setCountry("-");
							}
						} else {
							permanentAddress.setCountry("-");
						}
					} catch (Exception e) {

					}
					try {
						List<Long> permanentState = new ArrayList<Long>(1);
						Long permanentStateLong = null;
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPermanentStateId())) {
							permanentStateLong = Long.valueOf(coApplicantDetail.getPermanentStateId().toString());
							permanentState.add(permanentStateLong);
							OneFormResponse statePermanent = oneFormClient.getStateByStateListId(permanentState);
							MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap(
									(LinkedHashMap<String, Object>) statePermanent.getListData().get(0),
									MasterResponse.class);
							if (!CommonUtils.isObjectNullOrEmpty(dataState)) {
								permanentAddress.setState(dataState.getValue());
							} else {
								permanentAddress.setCountry("-");
							}
						} else {
							permanentAddress.setCountry("-");
						}
					} catch (Exception e) {

					}
					permanentAddress.setLandMark(coApplicantDetail.getPermanentLandMark() != null ? coApplicantDetail.getPermanentLandMark() : "");
					permanentAddress.setPincode(coApplicantDetail.getPermanentPincode() != null ? coApplicantDetail.getPermanentPincode().toString() : "");
					permanentAddress.setPremiseNumber(coApplicantDetail.getPermanentPremiseNumberName() !=null ? coApplicantDetail.getPermanentPremiseNumberName() : "");
					permanentAddress.setStreetName(coApplicantDetail.getPermanentStreetName() !=null ? coApplicantDetail.getPermanentStreetName() : "");
					profileViewPLResponse.setSecondAddress(permanentAddress);
					
					//end of set address
					
					profileViewPLResponse.setRelationshipWithApplicant(
							coApplicantDetail.getRelationshipWithApplicant() != null ? RelationshipType
									.getById(coApplicantDetail.getRelationshipWithApplicant()).getValue() : null);
					// set pan car
					profileViewPLResponse.setPan(
							coApplicantDetail.getPan() != null ? coApplicantDetail.getPan().toUpperCase() : null);
					profileViewPLResponse.setTitle(coApplicantDetail.getTitleId() != null
							? Title.getById(coApplicantDetail.getTitleId()).getValue() : null);
					profileViewPLResponse.setAge(coApplicantDetail.getBirthDate() != null
							? CommonUtils.getAgeFromBirthDate(coApplicantDetail.getBirthDate()).toString() : null);
					profileViewPLResponse.setFirstName(
							coApplicantDetail.getFirstName() != null ? coApplicantDetail.getFirstName() : null);
					profileViewPLResponse.setGender(coApplicantDetail.getGenderId() != null
							? Gender.getById(coApplicantDetail.getGenderId()).getValue() : null);
					profileViewPLResponse.setLastName(
							coApplicantDetail.getLastName() != null ? coApplicantDetail.getLastName() : null);
					profileViewPLResponse.setMaritalStatus(coApplicantDetail.getStatusId() != null
							? MaritalStatus.getById(coApplicantDetail.getStatusId()).getValue() : null);
					profileViewPLResponse.setMiddleName(
							coApplicantDetail.getMiddleName() != null ? coApplicantDetail.getMiddleName() : null);
					profileViewPLResponse.setMonthlyIncome(String.valueOf(coApplicantDetail.getMonthlyIncome() != null
							? String.format("%.2f", coApplicantDetail.getMonthlyIncome()) : 0));
					profileViewPLResponse.setBirthDate(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthDate()) ? coApplicantDetail.getBirthDate().toString() : "-");
					// set uploads
					switch (productId) {
					case 3:// HOME LOAN
						profileViewPLResponse.setPanCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.HOME_LOAN_CO_APPLICANT_SCANNED_COPY_OF_PAN_CARD));
						profileViewPLResponse.setAadharCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.HOME_LOAN_CO_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD));
						break;
					case 7:// PERSONAL LOAN
						profileViewPLResponse.setPanCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.PERSONAL_LOAN_CO_APPLICANT_SCANNED_COPY_OF_PAN_CARD));
						profileViewPLResponse.setAadharCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.PERSONAL_LOAN_CO_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD));
						break;
					case 12:// CAR_LOAN
						profileViewPLResponse.setPanCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.CAR_LOAN_CO_APPLICANT_SCANNED_COPY_OF_PAN_CARD));
						profileViewPLResponse.setAadharCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.CAR_LOAN_CO_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD));
						break;
					case 13:// LOAN_AGAINST_PROPERTY
						profileViewPLResponse.setPanCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAP_LOAN_CO_APPLICANT_SCANNED_COPY_OF_PAN_CARD));
						profileViewPLResponse.setAadharCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAP_LOAN_CO_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD));
						break;
					case 14:// LOAN_AGAINST_SHARES_AND_SECUIRITIES
						profileViewPLResponse.setPanCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAS_LOAN_CO_APPLICANT_SCANNED_COPY_OF_PAN_CARD));
						profileViewPLResponse.setAadharCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAS_LOAN_CO_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD));
						break;
					}
					plResponses.add(profileViewPLResponse);
				}

				return plResponses;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception("Error Occured while fetching CoApplicant Details");
		}
	}

	@Override
	public List<RetailFinalViewCommonResponse> getCoApplicantFinalResponse(Long applicantId, Long userId, int productId)
			throws Exception {
		try {
			List<CoApplicantDetail> coApplicantDetails = coApplicantDetailRepository.getList(applicantId, userId);
			if (coApplicantDetails != null && !coApplicantDetails.isEmpty()) {
				List<RetailFinalViewCommonResponse> finalCommonresponseList = new ArrayList<RetailFinalViewCommonResponse>();

				for (CoApplicantDetail coApplicantDetail : coApplicantDetails) {
					RetailFinalViewCommonResponse finalViewResponse = new RetailFinalViewCommonResponse();

					if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCastId())) {
						finalViewResponse.setCaste(CastCategory.getById(coApplicantDetail.getCastId()).getValue());
						if (coApplicantDetail.getCastId() == 6) {
							finalViewResponse.setCasteOther(coApplicantDetail.getCastOther());
						}
					} else {
						finalViewResponse.setCaste("-");
					}
					if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getReligion())) {
						finalViewResponse
								.setReligion(ReligionRetailMst.getById(coApplicantDetail.getReligion()).getValue());
						if (coApplicantDetail.getReligion() == 8) {
							finalViewResponse.setReligionOther(coApplicantDetail.getReligionOther());
						}
					} else {
						finalViewResponse.setReligion("-");
					}
					finalViewResponse.setBirthPlace(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthPlace())
							? coApplicantDetail.getBirthPlace() : null);
					finalViewResponse.setBirthDate(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthDate()) ? coApplicantDetail.getBirthDate().toString() : "-");
					finalViewResponse
							.setFatherFullName(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getFatherName())
									? coApplicantDetail.getFatherName() : null);
					finalViewResponse.setMotherName(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMotherName())
							? coApplicantDetail.getMotherName() : null);
					if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getStatusId())) {
						if (coApplicantDetail.getStatusId() == 2) {
							finalViewResponse
									.setSpouseName(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getSpouseName())
											? coApplicantDetail.getSpouseName() : null);
							finalViewResponse.setSpouseEmployed(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getIsSpouseEmployed())
											? (coApplicantDetail.getIsSpouseEmployed() == true ? "Yes" : "No") : "-");
							finalViewResponse
									.setNoOfChildren(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getNoChildren())
											? coApplicantDetail.getNoChildren().toString() : null);
						}
					}
					finalViewResponse
							.setNoOfDependents(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getNoDependent())
									? coApplicantDetail.getNoDependent().toString() : null);
					if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getHighestQualification())) {
						finalViewResponse.setHighestQualification(EducationStatusRetailMst
								.getById(coApplicantDetail.getHighestQualification()).getValue());
						if (coApplicantDetail.getHighestQualification() == 6) {
							finalViewResponse.setHighestQualificationOther(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getHighestQualificationOther())
											? coApplicantDetail.getHighestQualificationOther() : null);
						}
					} else {
						finalViewResponse.setHighestQualification(null);
					}
					SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
					SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
					finalViewResponse
							.setQualifyingYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getQualifyingYear())
									? monthFormat.format(coApplicantDetail.getQualifyingYear()) + "/"
											+ yearFormat.format(coApplicantDetail.getQualifyingYear())
									: null);
					finalViewResponse
							.setInstituteName(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getInstitute())
									? coApplicantDetail.getInstitute() : null);
					if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getResidenceType())) {
						finalViewResponse.setResidenceType(
								ResidenceStatusRetailMst.getById(coApplicantDetail.getResidenceType()).getValue());
						if (coApplicantDetail.getResidenceType() == 2) {
							finalViewResponse
									.setAnnualRent(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAnnualRent())
											? coApplicantDetail.getAnnualRent().toString() : null);
						}
					} else {
						finalViewResponse.setResidenceType(null);
					}
					finalViewResponse.setAnnualRent(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAnnualRent())
							? coApplicantDetail.getAnnualRent().toString() : "-");
					finalViewResponse.setYearAtCurrentResident(
							!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getResidingYear())
									? coApplicantDetail.getResidingYear().toString() : null);
					finalViewResponse.setMonthsAtCurrentResident(
							!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getResidingMonth())
									? coApplicantDetail.getResidingMonth().toString() : null);
					finalViewResponse.setWebsite(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getWebsiteAddress())
							? coApplicantDetail.getWebsiteAddress() : null);
					if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getOccupationId())) {
						if (coApplicantDetail.getOccupationId() == 2) {// salaried
							finalViewResponse
									.setEmploymentStatus(
											!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getEmploymentStatus())
													? EmploymentStatusRetailMst
															.getById(coApplicantDetail.getEmploymentStatus()).getValue()
													: null);
							finalViewResponse.setCurrentIndustry(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentIndustry())
											? coApplicantDetail.getCurrentIndustry() : null);
							finalViewResponse.setCurrentDepartment(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentDepartment())
											? coApplicantDetail.getCurrentDepartment() : null);
							finalViewResponse.setCurrentDesignation(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentDesignation())
											? coApplicantDetail.getCurrentDesignation() : null);
							finalViewResponse.setYearsInCurrentJob(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentJobYear())
											? coApplicantDetail.getCurrentJobYear().toString() : null);
							finalViewResponse.setMonthsInCurrentJob(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentJobMonth())
											? coApplicantDetail.getCurrentJobMonth().toString() : null);
							finalViewResponse.setTotalExperienceInMonths(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceMonth())
											? coApplicantDetail.getTotalExperienceMonth().toString() : null);
							finalViewResponse.setTotalExperienceInYears(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceYear())
											? coApplicantDetail.getTotalExperienceYear().toString() : null);
							finalViewResponse.setPreviousExperienceInMonths(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPreviousJobMonth())
											? coApplicantDetail.getPreviousJobMonth().toString() : null);
							finalViewResponse.setPreviousExperienceInYears(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPreviousJobYear())
											? coApplicantDetail.getPreviousJobYear().toString() : null);
							finalViewResponse.setPreviousEmployerName(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPreviousEmployersName())
											? coApplicantDetail.getPreviousEmployersName() : null);
							finalViewResponse.setPreviousEmployerAddress(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPreviousEmployersAddress())
											? coApplicantDetail.getPreviousEmployersAddress() : null);
						} else if (coApplicantDetail.getOccupationId() == 6) {// agriculturist
							finalViewResponse.setTotalLandOwned(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalLandOwned())
											? coApplicantDetail.getTotalLandOwned().toString() : null);
							finalViewResponse.setPresentlyIrrigated(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPresentlyIrrigated())
											? coApplicantDetail.getPresentlyIrrigated() : null);
							finalViewResponse.setSeasonalIrrigated(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getSeasonalIrrigated())
											? coApplicantDetail.getSeasonalIrrigated() : null);
							finalViewResponse
									.setRainFed(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getRainFed())
											? coApplicantDetail.getRainFed() : null);
							finalViewResponse
									.setUnAttended(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getUnattended())
											? coApplicantDetail.getUnattended() : null);
						} else if (coApplicantDetail.getOccupationId() == 3 || coApplicantDetail.getOccupationId() == 4
								|| coApplicantDetail.getOccupationId() == 5) {// business/self
							// employed
							// prof/self
							// employed
							finalViewResponse
									.setEntityName(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getNameOfEntity())
											? coApplicantDetail.getNameOfEntity() : null);
							finalViewResponse
									.setOwnershipType(
											!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getOwnershipType())
													? OwnershipTypeRetailMst
															.getById(coApplicantDetail.getOwnershipType()).getValue()
													: null);
							finalViewResponse
									.setOfficeType(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getOfficeType())
											? OfficeTypeRetailMst.getById(coApplicantDetail.getOfficeType()).getValue()
											: null);
							finalViewResponse
									.setNoOfPartners(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getNoPartners())
											? coApplicantDetail.getNoPartners().toString() : null);
							finalViewResponse.setNameOfPartners(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPartnersName())
											? coApplicantDetail.getPartnersName() : null);
							SimpleDateFormat format = new SimpleDateFormat("yyyy");
							finalViewResponse.setBusinessEstablishmentYear(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBusinessStartDate())
											? format.format(coApplicantDetail.getBusinessStartDate()) : null);
							finalViewResponse.setShareHolding(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getShareHolding())
											? coApplicantDetail.getShareHolding() : null);
							finalViewResponse.setAnnualTurnover(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAnnualTurnover())
											? coApplicantDetail.getAnnualTurnover().toString() : null);
							finalViewResponse.setTradeLicenseNo(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTradeLicenseNumber())
											? coApplicantDetail.getTradeLicenseNumber() : null);
							SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
							finalViewResponse.setTradeExpiryDate(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTradeLicenseExpiryDate())
											? format1.format(coApplicantDetail.getTradeLicenseExpiryDate()) : null);
							finalViewResponse.setNameOfPoaHolder(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPoaHolderName())
											? coApplicantDetail.getPoaHolderName() : null);
						}
					}
					List<ExistingLoanDetailRequest> existingLoanDetailRequestList = existingLoanService
							.getExistingLoanDetailList(coApplicantDetail.getId(),
									CommonUtils.ApplicantType.COAPPLICANT);
					finalViewResponse.setExistingLoanDetailRequest(existingLoanDetailRequestList);

					List<BankAccountHeldDetailsRequest> accountHeldDetailsRequestList = bankAccountsHeldService
							.getExistingLoanDetailList(coApplicantDetail.getId(),
									CommonUtils.ApplicantType.COAPPLICANT);
					finalViewResponse.setBankAccountHeldDetailsRequest(accountHeldDetailsRequestList);

					List<CreditCardsDetailRequest> creditCardsDetailRequestList = creditCardDetailsService
							.getCreditCardDetailList(coApplicantDetail.getId(),
									CommonUtils.ApplicantType.COAPPLICANT);
					List<CreditCardsDetailResponse> creditCardsDetailResponseList = new ArrayList<CreditCardsDetailResponse>();
					for (CreditCardsDetailRequest cardsDetailRequest : creditCardsDetailRequestList) {
						CreditCardsDetailResponse cardsDetailResponse = new CreditCardsDetailResponse();
						cardsDetailResponse
								.setCardNumber(!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getCardNumber())
										? cardsDetailRequest.getCardNumber() : null);
						cardsDetailResponse
								.setIssuerName(!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getIssuerName())
										? cardsDetailRequest.getIssuerName() : null);
						cardsDetailResponse
								.setCreditCardTypes(
										!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getCreditCardTypesId())
												? CreditCardTypesRetail
														.getById(cardsDetailRequest.getCreditCardTypesId()).getValue()
												: null);
						cardsDetailResponse.setOutstandingBalance(
								!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getOutstandingBalance())
										? cardsDetailRequest.getOutstandingBalance().toString() : null);
						creditCardsDetailResponseList.add(cardsDetailResponse);
					}
					finalViewResponse.setCreditCardsDetailResponse(creditCardsDetailResponseList);

					List<FixedDepositsDetailsRequest> depositsDetailsRequestList = fixedDepositService
							.getFixedDepositsDetailList(coApplicantDetail.getId(),
									CommonUtils.ApplicantType.COAPPLICANT);
					finalViewResponse.setFixedDepositsDetailsRequest(depositsDetailsRequestList);

					List<OtherCurrentAssetDetailRequest> otherCurrentAssetDetailRequestList = otherCurrentAssetService
							.getOtherCurrentAssetDetailList(coApplicantDetail.getId(),
									CommonUtils.ApplicantType.COAPPLICANT);
					List<OtherCurrentAssetDetailResponse> assetDetailResponseList = new ArrayList<OtherCurrentAssetDetailResponse>();
					for (OtherCurrentAssetDetailRequest assetDetailRequest : otherCurrentAssetDetailRequestList) {
						OtherCurrentAssetDetailResponse assetDetailResponse = new OtherCurrentAssetDetailResponse();
						assetDetailResponse
								.setAssetType(!CommonUtils.isObjectNullOrEmpty(assetDetailRequest.getAssetTypesId())
										? Assets.getById(assetDetailRequest.getAssetTypesId()).getValue() : null);
						assetDetailResponse.setAssetDescription(
								!CommonUtils.isObjectNullOrEmpty(assetDetailRequest.getAssetDescription())
										? assetDetailRequest.getAssetDescription() : null);
						assetDetailResponse
								.setAssetValue(!CommonUtils.isObjectNullOrEmpty(assetDetailRequest.getAssetValue())
										? assetDetailRequest.getAssetValue().toString() : null);
						assetDetailResponseList.add(assetDetailResponse);
					}
					finalViewResponse.setAssetDetailResponseList(assetDetailResponseList);

					List<OtherIncomeDetailRequest> otherIncomeDetailRequestsList = otherIncomeService
							.getOtherIncomeDetailList(coApplicantDetail.getId(), CommonUtils.ApplicantType.COAPPLICANT);
					List<OtherIncomeDetailResponse> incomeDetailResponseList = new ArrayList<OtherIncomeDetailResponse>();
					for (OtherIncomeDetailRequest detailRequest : otherIncomeDetailRequestsList) {
						OtherIncomeDetailResponse detailResponse = new OtherIncomeDetailResponse();
						detailResponse
								.setIncomeDetails(!CommonUtils.isObjectNullOrEmpty(detailRequest.getIncomeDetailsId())
										? IncomeDetails.getById(detailRequest.getIncomeDetailsId()).getValue() : null);
						detailResponse.setIncomeHead(!CommonUtils.isObjectNullOrEmpty(detailRequest.getIncomeHead())
								? detailRequest.getIncomeHead() : null);
						detailResponse.setGrossIncome(!CommonUtils.isObjectNullOrEmpty(detailRequest.getGrossIncome())
								? detailRequest.getGrossIncome().toString() : null);
						detailResponse.setNetIncome(!CommonUtils.isObjectNullOrEmpty(detailRequest.getNetIncome())
								? detailRequest.getNetIncome().toString() : null);
						incomeDetailResponseList.add(detailResponse);
					}
					finalViewResponse.setIncomeDetailResponseList(incomeDetailResponseList);

					List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequestList = referenceService
							.getReferenceRetailDetailList(coApplicantDetail.getId(),
									CommonUtils.ApplicantType.COAPPLICANT);
					finalViewResponse.setReferenceRetailDetailsRequest(referenceRetailDetailsRequestList);

					// for uploaded documents

					// set uploads
					switch (productId) {
					case 3:// HOME LOAN
						finalViewResponse.setCoApplicant_panCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.HOME_LOAN_CO_APPLICANT_SCANNED_COPY_OF_PAN_CARD));
						finalViewResponse.setCoApplicant_aadharCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.HOME_LOAN_CO_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD));
						finalViewResponse.setCoApplicant_BankACStatments(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.HOME_LOAN_CO_APPLICANT_STATEMENT_OF_BANK_ACCOUNT_FOR_LAST_6_MONTHS));
						finalViewResponse.setCoApplicant_SalaraySlip(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.HOME_LOAN_CO_APPLICANT_INCOME_PROOF_LATEST_SALARY_SLIP));
						finalViewResponse.setCoApplicant_ItReturn(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.HOME_LOAN_CO_APPLICANT_INCOME_TAX_RETURNS_OR_FORM_16_FOR_THE_LAST_2_YEARS));
						finalViewResponse.setCoApplicant_BalanceSheet(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.HOME_LOAN_CO_APPLICANT_AUDITED_UNAUDITED_BALANCE_SHEET_PROFIT_LOSS_STATEMENT_FOR_3_YEARS));
						finalViewResponse.setCoApplicant_AddressProof(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.HOME_LOAN_CO_APPLICANT_ADDRESS_PROOF));
						finalViewResponse.setCoApplicant_IncomeProof(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.HOME_LOAN_CO_APPLICANT_INCOME_PROOF_OF_ENTITY___INCOME_TAX_RETURN_FOR_LAST_2_YEARS));
						finalViewResponse.setCoApplicant_CropCultivation(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.HOME_LOAN_CO_APPLICANT_CROP_CULTIVATION_SHOWING_CROPPING_PATTERN_LAND_HOLDING_WITH_PHOTOGRAPH));
						finalViewResponse.setCoApplicant_AlliedActivities(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.HOME_LOAN_CO_APPLICANT_DOCUMENTARY_PROOF_OF_ALLIED_AGRICULTURAL_ACTIVITIES));
						break;
					case 7:// PERSONAL LOAN
						finalViewResponse.setCoApplicant_panCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.PERSONAL_LOAN_CO_APPLICANT_SCANNED_COPY_OF_PAN_CARD));
						finalViewResponse.setCoApplicant_aadharCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.PERSONAL_LOAN_CO_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD));
						finalViewResponse.setCoApplicant_BankACStatments(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.PERSONAL_LOAN_CO_APPLICANT_STATEMENT_OF_BANK_ACCOUNT_FOR_LAST_6_MONTHS));
						finalViewResponse.setCoApplicant_SalaraySlip(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.PERSONAL_LOAN_CO_APPLICANT_INCOME_PROOF_LATEST_SALARY_SLIP));
						finalViewResponse.setCoApplicant_ItReturn(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.PERSONAL_LOAN_CO_APPLICANT_INCOME_TAX_RETURNS_OR_FORM_16_FOR_THE_LAST_2_YEARS));
						finalViewResponse.setCoApplicant_BalanceSheet(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.PERSONAL_LOAN_CO_APPLICANT_AUDITED_UNAUDITED_BALANCE_SHEET_PROFIT__LOSS_STATEMENT_FOR_3_YEARS));
						finalViewResponse.setCoApplicant_AddressProof(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.PERSONAL_LOAN_CO_APPLICANT_ADDRESS_PROOF_ELECTRICITY_BILL_ADHAR_CARD_VOTER_ID_CARDANY_1));
						finalViewResponse.setCoApplicant_IncomeProof(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.PERSONAL_LOAN_CO_APPLICANT_INCOME_PROOF_OF_ENTITY_INCOME_TAX_RETURN_FOR_LAST_2_YEARS));
						finalViewResponse.setCoApplicant_CropCultivation(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.PERSONAL_LOAN_CO_APPLICANT_CROP_CULTIVATION_SHOWING_CROPPING_PATTERN__LAND_HOLDING_WITH_PHOTOGRAPH));
						finalViewResponse.setCoApplicant_AlliedActivities(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.PERSONAL_LOAN_CO_APPLICANT_DOCUMENTARY_PROOF_OF_ALLIED_AGRICULTURAL_ACTIVITIES_DAIRY__POULTRY__PLANTATION__HORTICULTURE));
						break;
					case 12:// CAR_LOAN
						finalViewResponse.setCoApplicant_panCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.CAR_LOAN_CO_APPLICANT_SCANNED_COPY_OF_PAN_CARD));
						finalViewResponse.setCoApplicant_aadharCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.CAR_LOAN_CO_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD));
						finalViewResponse.setCoApplicant_BankACStatments(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.CAR_LOAN_CO_APPLICANT_STATEMENT_OF_BANK_ACCOUNT_FOR_LAST_6_MONTHS));
						finalViewResponse.setCoApplicant_SalaraySlip(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.CAR_LOAN_CO_APPLICANT_INCOME_PROOF_LATEST_SALARY_SLIP));
						finalViewResponse.setCoApplicant_ItReturn(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.CAR_LOAN_CO_APPLICANT_INCOME_TAX_RETURNS_OR_FORM_16_FOR_THE_LAST_2_YEARS));
						finalViewResponse.setCoApplicant_BalanceSheet(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.CAR_LOAN_CO_APPLICANT_AUDITED_UNAUDITED_BALANCE_SHEET_PROFIT_LOSS_STATEMENT_FOR_3_YEARS));
						finalViewResponse.setCoApplicant_AddressProof(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.CAR_LOAN_CO_APPLICANT_ADDRESS_PROOF));
						finalViewResponse.setCoApplicant_IncomeProof(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.CAR_LOAN_CO_APPLICANT_INCOME_PROOF_OF_ENTITY___INCOME_TAX_RETURN_FOR_LAST_2_YEARS));
						finalViewResponse.setCoApplicant_CropCultivation(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.CAR_LOAN_CO_APPLICANT_CROP_CULTIVATION_SHOWING_CROPPING_PATTERN_LAND_HOLDING_WITH_PHOTOGRAPH));
						finalViewResponse.setCoApplicant_AlliedActivities(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.CAR_LOAN_CO_APPLICANT_DOCUMENTARY_PROOF_OF_ALLIED_AGRICULTURAL_ACTIVITIES));
						break;
					case 13:// LOAN_AGAINST_PROPERTY
						finalViewResponse.setCoApplicant_panCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAP_LOAN_CO_APPLICANT_SCANNED_COPY_OF_PAN_CARD));
						finalViewResponse.setCoApplicant_aadharCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAP_LOAN_CO_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD));
						finalViewResponse.setCoApplicant_BankACStatments(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAP_LOAN_CO_APPLICANT_STATEMENT_OF_BANK_ACCOUNT_FOR_LAST_6_MONTHS));
						finalViewResponse.setCoApplicant_SalaraySlip(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAP_LOAN_CO_APPLICANT_INCOME_PROOF_LATEST_SALARY_SLIP));
						finalViewResponse.setCoApplicant_ItReturn(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAP_LOAN_CO_APPLICANT_INCOME_TAX_RETURNS_OR_FORM_16_FOR_THE_LAST_2_YEARS));
						finalViewResponse.setCoApplicant_BalanceSheet(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAP_LOAN_CO_APPLICANT_AUDITED_UNAUDITED_BALANCE_SHEET_PROFIT_LOSS_STATEMENT_FOR_3_YEARS));
						finalViewResponse.setCoApplicant_AddressProof(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAP_LOAN_CO_APPLICANT_ADDRESS_PROOF_ELECTRICITY_BILL_ADHAR_CARD_VOTER_ID_CARD_ANY_1));
						finalViewResponse.setCoApplicant_IncomeProof(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAP_LOAN_CO_APPLICANT_INCOME_PROOF_OF_ENTITY_INCOME_TAX_RETURN_FOR_LAST_2_YEARS));
						finalViewResponse.setCoApplicant_CropCultivation(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAP_LOAN_CO_APPLICANT_CROP_CULTIVATION_SHOWING_CROPPING_PATTERN_LAND_HOLDING_WITH_PHOTOGRAPH));
						finalViewResponse.setCoApplicant_AlliedActivities(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAP_LOAN_CO_APPLICANT_DOCUMENTARY_PROOF_OF_ALLIED_AGRICULTURAL_ACTIVITIES_DAIRY__POULTRY__PLANTATION__HORTICULTURE));
						break;
					case 14:// LOAN_AGAINST_SHARES_AND_SECUIRITIES
						finalViewResponse.setCoApplicant_panCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAS_LOAN_CO_APPLICANT_SCANNED_COPY_OF_PAN_CARD));
						finalViewResponse.setCoApplicant_aadharCardList(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAS_LOAN_CO_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD));
						finalViewResponse.setCoApplicant_BankACStatments(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAS_LOAN_CO_APPLICANT_STATEMENT_OF_BANK_ACCOUNT_FOR_LAST_6_MONTHS));
						finalViewResponse.setCoApplicant_SalaraySlip(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAS_LOAN_CO_APPLICANT_INCOME_PROOF_LATEST_SALARY_SLIP));
						finalViewResponse.setCoApplicant_ItReturn(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAS_LOAN_CO_APPLICANT_INCOME_TAX_RETURNS_OR_FORM_16_FOR_THE_LAST_2_YEARS));
						finalViewResponse.setCoApplicant_BalanceSheet(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAS_LOAN_CO_APPLICANT_AUDITEDUNAUDITED_BALANCE_SHEET_PROFIT_LOSS_STATEMENT_FOR_3_YEARS));
						finalViewResponse.setCoApplicant_AddressProof(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAS_LOAN_CO_APPLICANT_ADDRESS_PROOF_ELECTRICITY_BILL_ADHAR_CARD_VOTER_ID_CARD_ANY_1));
						finalViewResponse.setCoApplicant_IncomeProof(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAS_LOAN_CO_APPLICANT_INCOME_PROOF_OF_ENTITY_INCOME_TAX_RETURN_FOR_LAST_2_YEARS));
						finalViewResponse.setCoApplicant_CropCultivation(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAS_LOAN_CO_APPLICANT_CROP_CULTIVATION_SHOWING_CROPPING_PATTERN_LAND_HOLDING_WITH_PHOTOGRAPH));
						finalViewResponse.setCoApplicant_AlliedActivities(documentManagementService.getDocumentDetails(
								coApplicantDetail.getId(), DocumentAlias.UERT_TYPE_CO_APPLICANT,
								DocumentAlias.LAS_LOAN_CO_APPLICANT_DOCUMENTARY_PROOF_OF_ALLIED_AGRICULTURAL_ACTIVITIES_DAIRY__POULTRY__PLANTATION_HORTICULTURE));
						break;
					}

					finalCommonresponseList.add(finalViewResponse);
				}

				return finalCommonresponseList;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception("Error Occured while fetching CoApplicant Final Details");
		}
	}

	@Override
	public Long getApplicantIdById(Long id) throws Exception {
		try {
			return coApplicantDetailRepository.getApplicantIdById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error While getting Applicant Id by CoApplicant ID");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
