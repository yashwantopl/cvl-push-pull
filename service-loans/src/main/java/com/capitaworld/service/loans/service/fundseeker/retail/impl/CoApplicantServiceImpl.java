package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.capitaworld.service.oneform.enums.AlliedActivity;
import com.capitaworld.service.oneform.enums.Assets;
import com.capitaworld.service.oneform.enums.CastCategory;
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
import com.capitaworld.service.oneform.enums.Options;
import com.capitaworld.service.oneform.enums.OwnershipTypeRetailMst;
import com.capitaworld.service.oneform.enums.RelationshipType;
import com.capitaworld.service.oneform.enums.ReligionRetailMst;
import com.capitaworld.service.oneform.enums.ResidenceStatusRetailMst;
import com.capitaworld.service.oneform.enums.Title;

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
			return true;

		} catch (Exception e) {
			logger.error("Error while Saving Retail Profile:-");
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
					if (coApplicantDetail.getOccupationId() != null) {
						profileViewPLResponse.setNatureOfOccupationId(coApplicantDetail.getOccupationId());
						if (coApplicantDetail.getOccupationId() == 2) {
							profileViewPLResponse.setNatureOfOccupation(
									OccupationNature.getById(coApplicantDetail.getOccupationId()).getValue());
							if (!CommonUtil.isObjectNullOrEmpty(coApplicantDetail.getCompanyName())) {
								profileViewPLResponse.setCompanyName(coApplicantDetail.getCompanyName());
							}
							if (!CommonUtil.isObjectNullOrEmpty(coApplicantDetail.getEmployedWithId())) {
								if (coApplicantDetail.getEmployedWithId() == 8) {
									profileViewPLResponse.setEmployeeWith(coApplicantDetail.getEmployedWithOther());
								} else {
									profileViewPLResponse.setEmployeeWith(
											EmployeeWith.getById(coApplicantDetail.getEmployedWithId()).getValue());
								}
							}
						} else if (coApplicantDetail.getOccupationId() == 3
								|| coApplicantDetail.getOccupationId() == 4) {
							profileViewPLResponse.setNatureOfOccupation(
									OccupationNature.getById(coApplicantDetail.getOccupationId()).getValue());
							if (!CommonUtil.isObjectNullOrEmpty(coApplicantDetail.getEntityName())) {
								profileViewPLResponse.setEntityName(coApplicantDetail.getEntityName());
							}
							if (!CommonUtil.isObjectNullOrEmpty(coApplicantDetail.getIndustryTypeId())) {
								if (coApplicantDetail.getIndustryTypeId() == 16) {
									profileViewPLResponse.setIndustryType(coApplicantDetail.getIndustryTypeOther());
								} else {
									profileViewPLResponse.setIndustryType(
											IndustryType.getById(coApplicantDetail.getIndustryTypeId()).getValue());
								}
							}
						} else if (coApplicantDetail.getOccupationId() == 5) {
							profileViewPLResponse.setNatureOfOccupation(
									OccupationNature.getById(coApplicantDetail.getOccupationId()).getValue());
							if (coApplicantDetail.getSelfEmployedOccupationId() == 10) {
								profileViewPLResponse.setOccupation(coApplicantDetail.getSelfEmployedOccupationOther());
							} else {
								profileViewPLResponse.setOccupation(
										Occupation.getById(coApplicantDetail.getSelfEmployedOccupationId()).getValue());
							}
						} else if (coApplicantDetail.getOccupationId() == 6) {
							profileViewPLResponse.setNatureOfOccupation(
									OccupationNature.getById(coApplicantDetail.getOccupationId()).getValue());
							if (!CommonUtil.isObjectNullOrEmpty(coApplicantDetail.getLandSize())) {
								profileViewPLResponse.setLandSize(
										LandSize.getById(coApplicantDetail.getLandSize().intValue()).getValue());
							}
							if (!CommonUtil.isObjectNullOrEmpty(coApplicantDetail.getAlliedActivityId())) {
								profileViewPLResponse.setAlliedActivity(
										AlliedActivity.getById(coApplicantDetail.getAlliedActivityId()).getValue());
							}
						} else if (coApplicantDetail.getOccupationId() == 7) {
							profileViewPLResponse.setNatureOfOccupation(
									OccupationNature.getById(coApplicantDetail.getOccupationId()).getValue());
						}
					}

					// set pan car
					profileViewPLResponse.setRelationshipWithApplicant(
							coApplicantDetail.getRelationshipWithApplicant() != null ? RelationshipType
									.getById(coApplicantDetail.getRelationshipWithApplicant()).getValue() : null);
					profileViewPLResponse
							.setPan(coApplicantDetail.getPan() != null ? coApplicantDetail.getPan() : null);
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
					profileViewPLResponse.setMonthlyIncome(String.valueOf(
							coApplicantDetail.getMonthlyIncome() != null ? coApplicantDetail.getMonthlyIncome() : 0));
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
	public List<RetailFinalViewCommonResponse> getCoApplicantFinalResponse(Long applicantId, Long userId)
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
						finalViewResponse.setCaste("NA");
					}
					if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getReligion())) {
						finalViewResponse
								.setReligion(ReligionRetailMst.getById(coApplicantDetail.getReligion()).getValue());
						if (coApplicantDetail.getReligion() == 8) {
							finalViewResponse.setReligionOther(coApplicantDetail.getReligionOther());
						}
					} else {
						finalViewResponse.setReligion("NA");
					}
					finalViewResponse.setBirthPlace(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthPlace())
							? coApplicantDetail.getBirthPlace() : "NA");
					finalViewResponse
							.setFatherFullName(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getFatherName())
									? coApplicantDetail.getFatherName() : "NA");
					finalViewResponse.setMotherName(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMotherName())
							? coApplicantDetail.getMotherName() : "NA");
					if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getStatusId())) {
						if (coApplicantDetail.getStatusId() == 2) {
							finalViewResponse
									.setSpouseName(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getSpouseName())
											? coApplicantDetail.getSpouseName() : "NA");
							finalViewResponse.setSpouseEmployed(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getIsSpouseEmployed()) ? Options
											.getById((coApplicantDetail.getIsSpouseEmployed() ? 1 : 0)).getValue()
											: "NA");
							finalViewResponse
									.setNoOfChildren(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getNoChildren())
											? coApplicantDetail.getNoChildren().toString() : "NA");
						}
					}
					finalViewResponse
							.setNoOfDependents(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getNoDependent())
									? coApplicantDetail.getNoDependent().toString() : "NA");
					if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getHighestQualification())) {
						finalViewResponse.setHighestQualification(EducationStatusRetailMst
								.getById(coApplicantDetail.getHighestQualification()).getValue());
						if (coApplicantDetail.getHighestQualification() == 6) {
							finalViewResponse.setHighestQualificationOther(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getHighestQualificationOther())
											? coApplicantDetail.getHighestQualificationOther() : "NA");
						}
					} else {
						finalViewResponse.setHighestQualification("NA");
					}
					finalViewResponse
							.setQualifyingYear(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getQualifyingYear())
									? coApplicantDetail.getQualifyingYear().getMonth() + "/"
											+ coApplicantDetail.getQualifyingYear().getYear()
									: "NA");
					finalViewResponse
							.setInstituteName(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getInstitute())
									? coApplicantDetail.getInstitute() : "NA");
					if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getResidenceType())) {
						finalViewResponse.setResidenceType(
								ResidenceStatusRetailMst.getById(coApplicantDetail.getResidenceType()).getValue());
						if (coApplicantDetail.getResidenceType() == 2) {
							finalViewResponse
									.setAnnualRent(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAnnualRent())
											? coApplicantDetail.getAnnualRent().toString() : "NA");
						}
					} else {
						finalViewResponse.setResidenceType("NA");
					}
					finalViewResponse.setYearAtCurrentResident(
							!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getResidingYear())
									? coApplicantDetail.getResidingYear().toString() : "NA");
					finalViewResponse.setMonthsAtCurrentResident(
							!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getResidingMonth())
									? coApplicantDetail.getResidingMonth().toString() : "NA");
					finalViewResponse.setWebsite(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getWebsiteAddress())
							? coApplicantDetail.getWebsiteAddress() : "NA");
					if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getOccupationId())) {
						if (coApplicantDetail.getOccupationId() == 2) {// salaried
							finalViewResponse
									.setEmploymentStatus(
											!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getEmploymentStatus())
													? EmploymentStatusRetailMst
															.getById(coApplicantDetail.getEmploymentStatus()).getValue()
													: "NA");
							finalViewResponse.setCurrentIndustry(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentIndustry())
											? coApplicantDetail.getCurrentIndustry() : "NA");
							finalViewResponse.setCurrentDepartment(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentDepartment())
											? coApplicantDetail.getCurrentDepartment() : "NA");
							finalViewResponse.setCurrentDesignation(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentDesignation())
											? coApplicantDetail.getCurrentDesignation() : "NA");
							finalViewResponse.setYearsInCurrentJob(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentJobYear())
											? coApplicantDetail.getCurrentJobYear().toString() : "NA");
							finalViewResponse.setMonthsInCurrentJob(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentJobMonth())
											? coApplicantDetail.getCurrentJobMonth().toString() : "NA");
							finalViewResponse.setTotalExperienceInMonths(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceMonth())
											? coApplicantDetail.getTotalExperienceMonth().toString() : "NA");
							finalViewResponse.setTotalExperienceInYears(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceYear())
											? coApplicantDetail.getTotalExperienceYear().toString() : "NA");
							finalViewResponse.setPreviousExperienceInMonths(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPreviousJobMonth())
											? coApplicantDetail.getPreviousJobMonth().toString() : "NA");
							finalViewResponse.setPreviousExperienceInYears(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPreviousJobYear())
											? coApplicantDetail.getPreviousJobYear().toString() : "NA");
							finalViewResponse.setPreviousEmployerName(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPreviousEmployersName())
											? coApplicantDetail.getPreviousEmployersName() : "NA");
							finalViewResponse.setPreviousEmployerAddress(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPreviousEmployersAddress())
											? coApplicantDetail.getPreviousEmployersAddress() : "NA");
						} else if (coApplicantDetail.getOccupationId() == 6) {// agriculturist
							finalViewResponse.setTotalLandOwned(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalLandOwned())
											? coApplicantDetail.getTotalLandOwned().toString() : "NA");
							finalViewResponse.setPresentlyIrrigated(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPresentlyIrrigated())
											? coApplicantDetail.getPresentlyIrrigated() : "NA");
							finalViewResponse.setSeasonalIrrigated(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getSeasonalIrrigated())
											? coApplicantDetail.getSeasonalIrrigated() : "NA");
							finalViewResponse
									.setRainFed(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getRainFed())
											? coApplicantDetail.getRainFed() : "NA");
							finalViewResponse
									.setUnAttended(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getUnattended())
											? coApplicantDetail.getUnattended() : "NA");
						} else if (coApplicantDetail.getOccupationId() == 3 || coApplicantDetail.getOccupationId() == 4
								|| coApplicantDetail.getOccupationId() == 5) {// business/self
																				// employed
																				// prof/self
																				// employed
							finalViewResponse
									.setEntityName(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getNameOfEntity())
											? coApplicantDetail.getNameOfEntity() : "NA");
							finalViewResponse
									.setOwnershipType(
											!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getOwnershipType())
													? OwnershipTypeRetailMst
															.getById(coApplicantDetail.getOwnershipType()).getValue()
													: "NA");
							finalViewResponse
									.setOfficeType(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getOfficeType())
											? OfficeTypeRetailMst.getById(coApplicantDetail.getOfficeType()).getValue()
											: "NA");
							finalViewResponse
									.setNoOfPartners(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getNoPartners())
											? coApplicantDetail.getNoPartners().toString() : "NA");
							finalViewResponse.setNameOfPartners(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPartnersName())
											? coApplicantDetail.getPartnersName() : "NA");
							finalViewResponse
									.setBusinessEstablishmentYear(
											!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBusinessStartDate())
													? coApplicantDetail.getBusinessStartDate().getMonth() + "/"
															+ coApplicantDetail.getBusinessStartDate().getYear()
													: "NA");
							finalViewResponse.setShareHolding(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getShareHolding())
											? coApplicantDetail.getShareHolding() : "NA");
							finalViewResponse.setAnnualTurnover(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAnnualTurnover())
											? coApplicantDetail.getAnnualTurnover().toString() : "NA");
							finalViewResponse.setTradeLicenseNo(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTradeLicenseNumber())
											? coApplicantDetail.getTradeLicenseNumber() : "NA");
							finalViewResponse.setTradeExpiryDate(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTradeLicenseExpiryDate())
											? coApplicantDetail.getTradeLicenseExpiryDate().getMonth() + "/"
													+ coApplicantDetail.getTradeLicenseExpiryDate().getYear()
											: "NA");
							finalViewResponse.setNameOfPoaHolder(
									!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getPoaHolderName())
											? coApplicantDetail.getPoaHolderName() : "NA");
						}
					}
					List<ExistingLoanDetailRequest> existingLoanDetailRequestList = existingLoanService
							.getExistingLoanDetailList(applicantId, CommonUtils.ApplicantType.COAPPLICANT);
					finalViewResponse.setExistingLoanDetailRequest(existingLoanDetailRequestList);

					List<BankAccountHeldDetailsRequest> accountHeldDetailsRequestList = bankAccountsHeldService
							.getExistingLoanDetailList(applicantId, CommonUtils.ApplicantType.COAPPLICANT);
					finalViewResponse.setBankAccountHeldDetailsRequest(accountHeldDetailsRequestList);

					List<CreditCardsDetailRequest> creditCardsDetailRequestList = creditCardDetailsService
							.getExistingLoanDetailList(applicantId, CommonUtils.ApplicantType.COAPPLICANT);
					List<CreditCardsDetailResponse> creditCardsDetailResponseList = new ArrayList<CreditCardsDetailResponse>();
					for (CreditCardsDetailRequest cardsDetailRequest : creditCardsDetailRequestList) {
						CreditCardsDetailResponse cardsDetailResponse = new CreditCardsDetailResponse();
						cardsDetailResponse
								.setCardNumber(!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getCardNumber())
										? cardsDetailRequest.getCardNumber() : "NA");
						cardsDetailResponse
								.setIssuerName(!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getIssuerName())
										? cardsDetailRequest.getIssuerName() : "NA");
						/*
						 * cardsDetailResponse.setCreditCardTypes(!CommonUtils.
						 * isObjectNullOrEmpty(cardsDetailRequest.
						 * getCreditCardTypesId()) ? C
						 * cardsDetailRequest.getIssuerName() : "NA");
						 */
						cardsDetailResponse.setOutstandingBalance(
								!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getOutstandingBalance())
										? cardsDetailRequest.getOutstandingBalance().toString() : "NA");
						creditCardsDetailResponseList.add(cardsDetailResponse);
					}
					finalViewResponse.setCreditCardsDetailResponse(creditCardsDetailResponseList);

					List<FixedDepositsDetailsRequest> depositsDetailsRequestList = fixedDepositService
							.getFixedDepositsDetailList(applicantId, CommonUtils.ApplicantType.COAPPLICANT);
					finalViewResponse.setFixedDepositsDetailsRequest(depositsDetailsRequestList);

					List<OtherCurrentAssetDetailRequest> otherCurrentAssetDetailRequestList = otherCurrentAssetService
							.getOtherCurrentAssetDetailList(applicantId, CommonUtils.ApplicantType.COAPPLICANT);
					List<OtherCurrentAssetDetailResponse> assetDetailResponseList = new ArrayList<OtherCurrentAssetDetailResponse>();
					for (OtherCurrentAssetDetailRequest assetDetailRequest : otherCurrentAssetDetailRequestList) {
						OtherCurrentAssetDetailResponse assetDetailResponse = new OtherCurrentAssetDetailResponse();
						assetDetailResponse
								.setAssetType(!CommonUtils.isObjectNullOrEmpty(assetDetailRequest.getAssetTypesId())
										? Assets.getById(assetDetailRequest.getAssetTypesId()).getValue() : "NA");
						assetDetailResponse.setAssetDescription(
								!CommonUtils.isObjectNullOrEmpty(assetDetailRequest.getAssetDescription())
										? assetDetailRequest.getAssetDescription() : "NA");
						assetDetailResponse
								.setAssetValue(!CommonUtils.isObjectNullOrEmpty(assetDetailRequest.getAssetValue())
										? assetDetailRequest.getAssetValue().toString() : "NA");
						assetDetailResponseList.add(assetDetailResponse);
					}
					finalViewResponse.setAssetDetailResponseList(assetDetailResponseList);

					List<OtherIncomeDetailRequest> otherIncomeDetailRequestsList = otherIncomeService
							.getOtherIncomeDetailList(applicantId, CommonUtils.ApplicantType.COAPPLICANT);
					List<OtherIncomeDetailResponse> incomeDetailResponseList = new ArrayList<OtherIncomeDetailResponse>();
					for (OtherIncomeDetailRequest detailRequest : otherIncomeDetailRequestsList) {
						OtherIncomeDetailResponse detailResponse = new OtherIncomeDetailResponse();
						detailResponse
								.setIncomeDetails(!CommonUtils.isObjectNullOrEmpty(detailRequest.getIncomeDetailsId())
										? IncomeDetails.getById(detailRequest.getIncomeDetailsId()).getValue() : "NA");
						detailResponse.setIncomeHead(!CommonUtils.isObjectNullOrEmpty(detailRequest.getIncomeHead())
								? detailRequest.getIncomeHead() : "NA");
						detailResponse.setGrossIncome(!CommonUtils.isObjectNullOrEmpty(detailRequest.getGrossIncome())
								? detailRequest.getGrossIncome().toString() : "NA");
						detailResponse.setNetIncome(!CommonUtils.isObjectNullOrEmpty(detailRequest.getNetIncome())
								? detailRequest.getNetIncome().toString() : "NA");
						incomeDetailResponseList.add(detailResponse);
					}
					finalViewResponse.setIncomeDetailResponseList(incomeDetailResponseList);

					List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequestList = referenceService
							.getReferenceRetailDetailList(applicantId, CommonUtils.ApplicantType.COAPPLICANT);
					finalViewResponse.setReferenceRetailDetailsRequest(referenceRetailDetailsRequestList);

					// for uploaded documents
					finalViewResponse.setCoApplicant_BankACStatments(documentManagementService.getDocumentDetails(
							applicantId, DocumentAlias.UERT_TYPE_CO_APPLICANT,
							DocumentAlias.HOME_LOAN_APPLICANT_STATEMENT_OF_BANK_ACCOUNT_FOR_LAST_6_MONTHS));
					finalViewResponse.setCoApplicant_SalaraySlip(documentManagementService.getDocumentDetails(
							applicantId, DocumentAlias.UERT_TYPE_CO_APPLICANT,
							DocumentAlias.HOME_LOAN_APPLICANT_INCOME_PROOF_LATEST_SALARY_SLIP));
					finalViewResponse.setCoApplicant_ItReturn(documentManagementService.getDocumentDetails(applicantId,
							DocumentAlias.UERT_TYPE_CO_APPLICANT,
							DocumentAlias.HOME_LOAN_APPLICANT_INCOME_TAX_RETURNS_OR_FORM_16_FOR_THE_LAST_2_YEARS));
					finalViewResponse.setCoApplicant_BalanceSheet(documentManagementService.getDocumentDetails(
							applicantId, DocumentAlias.UERT_TYPE_CO_APPLICANT,
							DocumentAlias.HOME_LOAN_APPLICANT_AUDITED_UNAUDITED_BALANCE_SHEET_PROFIT_LOSS_STATEMENT_FOR_3_YEARS));
					finalViewResponse.setCoApplicant_AddressProof(documentManagementService.getDocumentDetails(
							applicantId, DocumentAlias.UERT_TYPE_CO_APPLICANT,
							DocumentAlias.HOME_LOAN_APPLICANT_ADDRESS_PROOF));
					finalViewResponse.setCoApplicant_IncomeProof(documentManagementService.getDocumentDetails(
							applicantId, DocumentAlias.UERT_TYPE_CO_APPLICANT,
							DocumentAlias.HOME_LOAN_APPLICANT_INCOME_PROOF_OF_ENTITY___INCOME_TAX_RETURN_FOR_LAST_2_YEARS));
					finalViewResponse.setCoApplicant_CropCultivation(documentManagementService.getDocumentDetails(
							applicantId, DocumentAlias.UERT_TYPE_CO_APPLICANT,
							DocumentAlias.HOME_LOAN_APPLICANT_CROP_CULTIVATION_SHOWING_CROPPING_PATTERN_LAND_HOLDING_WITH_PHOTOGRAPH));
					finalViewResponse.setCoApplicant_AlliedActivities(documentManagementService.getDocumentDetails(
							applicantId, DocumentAlias.UERT_TYPE_CO_APPLICANT,
							DocumentAlias.HOME_LOAN_CO_APPLICANT_DOCUMENTARY_PROOF_OF_ALLIED_AGRICULTURAL_ACTIVITIES));

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
}
