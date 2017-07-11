package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.text.SimpleDateFormat;
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

import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.GuarantorDetails;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.retail.BankAccountHeldDetailsRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailResponse;
import com.capitaworld.service.loans.model.retail.ExistingLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.retail.FixedDepositsDetailsRequest;
import com.capitaworld.service.loans.model.retail.GuarantorRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailResponse;
import com.capitaworld.service.loans.model.retail.OtherIncomeDetailRequest;
import com.capitaworld.service.loans.model.retail.OtherIncomeDetailResponse;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewCommonResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.common.DocumentManagementService;
import com.capitaworld.service.loans.service.fundseeker.retail.BankAccountHeldDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.CreditCardsDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.ExistingLoanDetailsService;
import com.capitaworld.service.loans.service.fundseeker.retail.FixedDepositsDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
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
import com.capitaworld.service.oneform.enums.OwnershipTypeRetailMst;
import com.capitaworld.service.oneform.enums.ReligionRetailMst;
import com.capitaworld.service.oneform.enums.ResidenceStatusRetailMst;
import com.capitaworld.service.oneform.enums.Title;

@Service
@Transactional
public class GuarantorServiceImpl implements GuarantorService {

	private static final Logger logger = LoggerFactory.getLogger(GuarantorServiceImpl.class.getName());

	protected static final String DMS_URL = "dmsURL";

	@Autowired
	private GuarantorDetailsRepository guarantorDetailsRepository;

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
	public boolean save(GuarantorRequest guarantorRequest, Long applicationId, Long userId) throws Exception {
		try {
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(guarantorRequest.getClientId()) ? userId
					: guarantorRequest.getClientId());
			GuarantorDetails guarantorDetails = guarantorDetailsRepository.get(applicationId, finalUserId,
					guarantorRequest.getId());
			if (guarantorDetails != null) {
				// throw new NullPointerException(
				// "CoApplicant Id Record not exists in DB : " +
				// guarantorRequest.getId());

				if (guarantorRequest.getIsActive() != null && !guarantorRequest.getIsActive().booleanValue()) {
					guarantorDetailsRepository.inactiveGuarantor(applicationId, guarantorRequest.getId());
					return true;
				}
				guarantorDetails.setModifiedBy(userId);
				guarantorDetails.setModifiedDate(new Date());
			} else {
				guarantorDetails = new GuarantorDetails();
				guarantorDetails.setFirstName(guarantorRequest.getFirstName());
				guarantorDetails.setCreatedBy(userId);
				guarantorDetails.setCreatedDate(new Date());
				guarantorDetails.setApplicationId(new LoanApplicationMaster(applicationId));
			}
			BeanUtils.copyProperties(guarantorRequest, guarantorDetails);
			copyAddressFromRequestToDomain(guarantorRequest, guarantorDetails);
			if (guarantorRequest.getDate() != null && guarantorRequest.getMonth() != null
					&& guarantorRequest.getYear() != null) {
				Date birthDate = CommonUtils.getDateByDateMonthYear(guarantorRequest.getDate(),
						guarantorRequest.getMonth(), guarantorRequest.getYear());
				guarantorDetails.setBirthDate(birthDate);
			}
			guarantorDetailsRepository.save(guarantorDetails);

			// setting Guarantor Details filled flag
			List<Long> coAppIds = guarantorDetailsRepository.getGuarantorIds(guarantorRequest.getApplicationId(),
					finalUserId);
			int index = coAppIds.indexOf(guarantorDetails.getId());
			if (index == 0) {
				if (!CommonUtils.isObjectNullOrEmpty(guarantorRequest.getIsGuarantor1DetailsFilled())) {
					loanApplicationRepository.setIsGuarantorOneProfileMandatoryFilled(
							guarantorRequest.getApplicationId(), finalUserId,
							guarantorRequest.getIsGuarantor1DetailsFilled());
				}
			} else if (index == 1) {
				if (!CommonUtils.isObjectNullOrEmpty(guarantorRequest.getIsGuarantor2DetailsFilled())) {
					loanApplicationRepository.setIsGuarantorTwoProfileMandatoryFilled(
							guarantorRequest.getApplicationId(), finalUserId,
							guarantorRequest.getIsGuarantor2DetailsFilled());
				}
			}
			return true;

		} catch (Exception e) {
			logger.error("Error while Saving Guarantor Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	@Override
	public List<Long> getGuarantorIds(Long userId, Long applicationId) throws Exception {
		try {
			return guarantorDetailsRepository.getGuarantorIds(applicationId,userId);
		} catch(Exception e){
			logger.error("Error while Saving Guarantor Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	@Override
	public GuarantorRequest get(Long userId, Long applicationId, Long id) throws Exception {
		try {
			GuarantorDetails guarantorDetail = guarantorDetailsRepository.get(applicationId, userId, id);
			if (guarantorDetail == null) {
				throw new NullPointerException("GuarantorDetails Record not exists in DB of ID : " + id
						+ " and Application ID==>" + applicationId + " User Id ==>" + userId);
			}
			GuarantorRequest guaRequest = new GuarantorRequest();
			BeanUtils.copyProperties(guarantorDetail, guaRequest);
			copyAddressFromDomainToRequest(guarantorDetail, guaRequest);
			guaRequest.setCurrencyId(retailApplicantDetailRepository.getCurrency(userId, applicationId));
			Integer[] saperatedTime = CommonUtils.saperateDayMonthYearFromDate(guarantorDetail.getBirthDate());
			guaRequest.setDate(saperatedTime[0]);
			guaRequest.setMonth(saperatedTime[1]);
			guaRequest.setYear(saperatedTime[2]);
			return guaRequest;
		} catch (Exception e) {
			logger.error("Error while getting Guarantor Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<GuarantorRequest> getList(Long applicationId, Long userId) throws Exception {
		try {
			List<GuarantorDetails> details = guarantorDetailsRepository.getList(applicationId, userId);
			List<GuarantorRequest> requests = new ArrayList<>(details.size());
			for (GuarantorDetails detail : details) {
				GuarantorRequest request = new GuarantorRequest();
				BeanUtils.copyProperties(detail, request, CommonUtils.IgnorableCopy.RETAIL_FINAL);
				requests.add(request);
			}
			return requests;
		} catch (Exception e) {
			logger.error("Error while getting list of Guarantor Retail Profile:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	@Override
	public boolean saveFinal(FinalCommonRetailRequest applicantRequest, Long userId) throws Exception {
		try {
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId
					: applicantRequest.getClientId());
			GuarantorDetails guaDetails = guarantorDetailsRepository.get(applicantRequest.getApplicationId(),
					finalUserId, applicantRequest.getId());
			if (guaDetails == null) {
				throw new NullPointerException("Guarantor Id Record not exists in DB : Application Id==>"
						+ applicantRequest.getApplicationId());
			}
			guaDetails.setModifiedBy(userId);
			guaDetails.setModifiedDate(new Date());
			BeanUtils.copyProperties(applicantRequest, guaDetails, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
			guarantorDetailsRepository.save(guaDetails);

			// setting Guarantor Details filled flag
			List<Long> guarantorIds = guarantorDetailsRepository.getGuarantorIds(applicantRequest.getApplicationId(),
					finalUserId);
			int index = guarantorIds.indexOf(guaDetails.getId());
			if (index == 0) {
				if (!CommonUtils.isObjectNullOrEmpty(applicantRequest.getIsGuarantor1FinalFilled())) {
					loanApplicationRepository.setIsGuarantorOneFinalMandatoryFilled(applicantRequest.getApplicationId(),
							finalUserId, applicantRequest.getIsGuarantor1FinalFilled());
				}
			} else if (index == 1) {
				if (!CommonUtils.isObjectNullOrEmpty(applicantRequest.getIsGuarantor2FinalFilled())) {
					loanApplicationRepository.setIsGuarantorTwoFinalMandatoryFilled(applicantRequest.getApplicationId(),
							finalUserId, applicantRequest.getIsGuarantor2FinalFilled());
				}
			}
			return true;

		} catch (Exception e) {
			logger.error("Error while Saving final Guarantor Retail Profile:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	@Override
	public FinalCommonRetailRequest getFinal(Long userId, Long applicationId, Long id) throws Exception {
		try {
			GuarantorDetails guaDetail = guarantorDetailsRepository.get(applicationId, userId, id);
			if (guaDetail == null) {
				throw new NullPointerException("GuarantorDetails Record of Final Portion not exists in DB of User ID : "
						+ userId + " and Application Id ==>" + applicationId);
			}
			FinalCommonRetailRequest applicantRequest = new FinalCommonRetailRequest();
			BeanUtils.copyProperties(guaDetail, applicantRequest, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
			Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
			applicantRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
			return applicantRequest;
		} catch (Exception e) {
			logger.error("Error while getting final Guarantor Retail Profile:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	public static void copyAddressFromRequestToDomain(GuarantorRequest from, GuarantorDetails to) {
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

	public static void copyAddressFromDomainToRequest(GuarantorDetails from, GuarantorRequest to) {
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
		if (from.getAddressSameAs() != null && from.getAddressSameAs().booleanValue()) {
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
	public List<RetailProfileViewResponse> getGuarantorServiceResponse(Long applicantId, Long userId, int productId)
			throws Exception {
		try {
			List<GuarantorDetails> guarantorDetails = guarantorDetailsRepository.getList(applicantId, userId);
			if (guarantorDetails != null && !guarantorDetails.isEmpty()) {
				List<RetailProfileViewResponse> plResponses = new ArrayList<RetailProfileViewResponse>();
				for (GuarantorDetails guarantorDetail : guarantorDetails) {
					RetailProfileViewResponse profileViewPLResponse = new RetailProfileViewResponse();
					profileViewPLResponse.setNatureOfOccupation(OccupationNature.getById(guarantorDetail.getOccupationId()).getValue());
					profileViewPLResponse.setNatureOfOccupationId(guarantorDetail.getOccupationId());
					if(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getOccupationId())){
						switch (guarantorDetail.getOccupationId().intValue()) {
						case 2 : //Salaried
							profileViewPLResponse.setCompanyName(guarantorDetail.getCompanyName());
							if(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getEmployedWithId())){
								if(guarantorDetail.getEmployedWithId() != 8){
									profileViewPLResponse.setEmployeeWith(EmployeeWith.getById(guarantorDetail.getEmployedWithId()).getValue());
								}else{
									profileViewPLResponse.setEmployeeWith(guarantorDetail.getEmployedWithOther());
								}
							}
							break;
						case 3 : //Business
						case 4 : //Self Employed
							profileViewPLResponse.setEntityName(guarantorDetail.getEntityName());
							if(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getIndustryTypeId())){
								if(guarantorDetail.getIndustryTypeId() != 16){
									profileViewPLResponse.setIndustryType(IndustryType.getById(guarantorDetail.getIndustryTypeId()).getValue());
								}else{
									profileViewPLResponse.setIndustryType(guarantorDetail.getIndustryTypeOther());
								}
							}
							break;
						case 5 ://Self Employed Professional
							if(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getSelfEmployedOccupationId())){
								if(guarantorDetail.getSelfEmployedOccupationId().intValue() != 10){
									profileViewPLResponse.setOccupation(Occupation.getById(guarantorDetail.getSelfEmployedOccupationId()).getValue());
								}else{
									profileViewPLResponse.setOccupation(guarantorDetail.getSelfEmployedOccupationOther());
								}
							}
							break;
						case 6://Agriculturist
							if(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getLandSize())){
								profileViewPLResponse.setLandSize(LandSize.getById(guarantorDetail.getLandSize().intValue()).getValue());
							}
							if(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getAlliedActivityId())){
								profileViewPLResponse.setAlliedActivity(AlliedActivity.getById(guarantorDetail.getAlliedActivityId()).getValue());
							}
							break;
						default:
							break;
						}
					}

					// set pan car
					profileViewPLResponse.setPan(guarantorDetail.getPan() != null ? guarantorDetail.getPan().toUpperCase() : null);
					profileViewPLResponse.setTitle(guarantorDetail.getTitleId() != null
							? Title.getById(guarantorDetail.getTitleId()).getValue() : null);
					profileViewPLResponse.setAge(guarantorDetail.getBirthDate() != null
							? CommonUtils.getAgeFromBirthDate(guarantorDetail.getBirthDate()).toString() : null);
					profileViewPLResponse.setFirstName(
							guarantorDetail.getFirstName() != null ? guarantorDetail.getFirstName() : null);
					profileViewPLResponse.setGender(guarantorDetail.getGenderId() != null
							? Gender.getById(guarantorDetail.getGenderId()).getValue() : null);
					profileViewPLResponse
							.setLastName(guarantorDetail.getLastName() != null ? guarantorDetail.getLastName() : null);
					profileViewPLResponse.setMaritalStatus(guarantorDetail.getStatusId() != null
							? MaritalStatus.getById(guarantorDetail.getStatusId()).getValue() : null);
					profileViewPLResponse.setMiddleName(
							guarantorDetail.getMiddleName() != null ? guarantorDetail.getMiddleName() : null);
					profileViewPLResponse.setMonthlyIncome(String.valueOf(
							guarantorDetail.getMonthlyIncome() != null ? String.format("%.2f",guarantorDetail.getMonthlyIncome()) : 0));

					// set uploads
					switch (productId) {
					case 3:// HOME LOAN
						profileViewPLResponse.setPanCardList(documentManagementService.getDocumentDetails(
								guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR,
								DocumentAlias.HOME_LOAN_GUARANTOR_SCANNED_COPY_OF_PAN_CARD));
						profileViewPLResponse.setAadharCardList(documentManagementService.getDocumentDetails(
								guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR,
								DocumentAlias.HOME_LOAN_GUARANTOR_SCANNED_COPY_OF_AADHAR_CARD));
						break;
					case 7:// PERSONAL LOAN
						profileViewPLResponse.setPanCardList(documentManagementService.getDocumentDetails(
								guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR,
								DocumentAlias.PERSONAL_LOAN_GUARANTOR_SCANNED_COPY_OF_PAN_CARD));
						profileViewPLResponse.setAadharCardList(documentManagementService.getDocumentDetails(
								guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR,
								DocumentAlias.PERSONAL_LOAN_GUARANTOR_SCANNED_COPY_OF_AADHAR_CARD));
						break;
					case 12:// CAR_LOAN
						profileViewPLResponse.setPanCardList(documentManagementService.getDocumentDetails(
								guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR,
								DocumentAlias.CAR_LOAN_GUARANTOR_SCANNED_COPY_OF_PAN_CARD));
						profileViewPLResponse.setAadharCardList(documentManagementService.getDocumentDetails(
								guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR,
								DocumentAlias.CAR_LOAN_GUARANTOR_SCANNED_COPY_OF_AADHAR_CARD));
						break;
					case 13:// LOAN_AGAINST_PROPERTY
						profileViewPLResponse.setPanCardList(documentManagementService.getDocumentDetails(
								guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR,
								DocumentAlias.LAP_LOAN_GUARANTOR_SCANNED_COPY_OF_PAN_CARD));
						profileViewPLResponse.setAadharCardList(documentManagementService.getDocumentDetails(
								guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR,
								DocumentAlias.LAP_LOAN_GUARANTOR_SCANNED_COPY_OF_AADHAR_CARD));
						break;
					case 14:// LOAN_AGAINST_SHARES_AND_SECUIRITIES
						profileViewPLResponse.setPanCardList(documentManagementService.getDocumentDetails(
								guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR,
								DocumentAlias.LAS_LOAN_GUARANTOR_SCANNED_COPY_OF_PAN_CARD));
						profileViewPLResponse.setAadharCardList(documentManagementService.getDocumentDetails(
								guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR,
								DocumentAlias.LAS_LOAN_GUARANTOR_SCANNED_COPY_OF_AADHAR_CARD));
						break;
					}

					plResponses.add(profileViewPLResponse);
				}
				return plResponses;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<RetailFinalViewCommonResponse> getGuarantorFinalViewResponse(Long applicantId, Long userId, int productId)
			throws Exception {
		try {
			List<GuarantorDetails> guarantorDetails = guarantorDetailsRepository.getList(applicantId, userId);
			if (guarantorDetails != null && !guarantorDetails.isEmpty()) {
				List<RetailFinalViewCommonResponse> finalCommonresponseList = new ArrayList<RetailFinalViewCommonResponse>();

				for (GuarantorDetails guarantorDetail : guarantorDetails) {
					RetailFinalViewCommonResponse finalViewResponse = new RetailFinalViewCommonResponse();

					if (!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getCastId())) {
						finalViewResponse.setCaste(CastCategory.getById(guarantorDetail.getCastId()).getValue());
						if (guarantorDetail.getCastId() == 6) {
							finalViewResponse.setCasteOther(guarantorDetail.getCastOther());
						}
					} else {
						finalViewResponse.setCaste(null);
					}
					if (!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getReligion())) {
						finalViewResponse
								.setReligion(ReligionRetailMst.getById(guarantorDetail.getReligion()).getValue());
						if (guarantorDetail.getReligion() == 8) {
							finalViewResponse.setReligionOther(guarantorDetail.getReligionOther());
						}
					} else {
						finalViewResponse.setReligion(null);
					}
					finalViewResponse.setBirthPlace(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getBirthPlace())
							? guarantorDetail.getBirthPlace() : null);
					finalViewResponse
							.setFatherFullName(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getFatherName())
									? guarantorDetail.getFatherName() : null);
					finalViewResponse.setMotherName(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getMotherName())
							? guarantorDetail.getMotherName() : null);
					if (!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getStatusId())) {
						if (guarantorDetail.getStatusId() == 2) {
							finalViewResponse
									.setSpouseName(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getSpouseName())
											? guarantorDetail.getSpouseName() : null);
							finalViewResponse.setSpouseEmployed(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getIsSpouseEmployed()) ?(guarantorDetail.getIsSpouseEmployed()==true ? "Yes" : "No") :"-");
							finalViewResponse
									.setNoOfChildren(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getNoChildren())
											? guarantorDetail.getNoChildren().toString() : null);
						}
					}
					finalViewResponse
							.setNoOfDependents(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getNoDependent())
									? guarantorDetail.getNoDependent().toString() : null);
					if (!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getHighestQualification())) {
						finalViewResponse.setHighestQualification(
								EducationStatusRetailMst.getById(guarantorDetail.getHighestQualification()).getValue());
						if (guarantorDetail.getHighestQualification() == 6) {
							finalViewResponse.setHighestQualificationOther(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getHighestQualificationOther())
											? guarantorDetail.getHighestQualificationOther() : null);
						}
					} else {
						finalViewResponse.setHighestQualification(null);
					}

					SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
					SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
					finalViewResponse
							.setQualifyingYear(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getQualifyingYear())
									? monthFormat.format(guarantorDetail.getQualifyingYear()) + "/" + yearFormat.format(guarantorDetail.getQualifyingYear())
							: null);
					finalViewResponse.setInstituteName(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getInstitute())
							? guarantorDetail.getInstitute() : null);
					if (!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getResidenceType())) {
						finalViewResponse.setResidenceType(
								ResidenceStatusRetailMst.getById(guarantorDetail.getResidenceType()).getValue());
						if (guarantorDetail.getResidenceType() == 2) {
							finalViewResponse
									.setAnnualRent(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getAnnualRent())
											? guarantorDetail.getAnnualRent().toString() : null);
						}
					} else {
						finalViewResponse.setResidenceType(null);
					}
					finalViewResponse.setYearAtCurrentResident(
							!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getResidingYear())
									? guarantorDetail.getResidingYear().toString() : null);
					finalViewResponse.setMonthsAtCurrentResident(
							!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getResidingMonth())
									? guarantorDetail.getResidingMonth().toString() : null);
					finalViewResponse.setWebsite(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getWebsiteAddress())
							? guarantorDetail.getWebsiteAddress() : null);
					if (!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getOccupationId())) {
						if (guarantorDetail.getOccupationId() == 2) {// salaried
							finalViewResponse
									.setEmploymentStatus(
											!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getEmploymentStatus())
													? EmploymentStatusRetailMst
															.getById(guarantorDetail.getEmploymentStatus()).getValue()
													: null);
							finalViewResponse.setCurrentIndustry(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getCurrentIndustry())
											? guarantorDetail.getCurrentIndustry() : null);
							finalViewResponse.setCurrentDepartment(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getCurrentDepartment())
											? guarantorDetail.getCurrentDepartment() : null);
							finalViewResponse.setCurrentDesignation(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getCurrentDesignation())
											? guarantorDetail.getCurrentDesignation() : null);
							finalViewResponse.setYearsInCurrentJob(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getCurrentJobYear())
											? guarantorDetail.getCurrentJobYear().toString() : null);
							finalViewResponse.setMonthsInCurrentJob(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getCurrentJobMonth())
											? guarantorDetail.getCurrentJobMonth().toString() : null);
							finalViewResponse.setTotalExperienceInMonths(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getTotalExperienceMonth())
											? guarantorDetail.getTotalExperienceMonth().toString() : null);
							finalViewResponse.setTotalExperienceInYears(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getTotalExperienceYear())
											? guarantorDetail.getTotalExperienceYear().toString() : null);
							finalViewResponse.setPreviousExperienceInMonths(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getPreviousJobMonth())
											? guarantorDetail.getPreviousJobMonth().toString() : null);
							finalViewResponse.setPreviousExperienceInYears(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getPreviousJobYear())
											? guarantorDetail.getPreviousJobYear().toString() : null);
							finalViewResponse.setPreviousEmployerName(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getPreviousEmployersName())
											? guarantorDetail.getPreviousEmployersName() : null);
							finalViewResponse.setPreviousEmployerAddress(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getPreviousEmployersAddress())
											? guarantorDetail.getPreviousEmployersAddress() : null);
						} else if (guarantorDetail.getOccupationId() == 6) {// agriculturist
							finalViewResponse.setTotalLandOwned(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getTotalLandOwned())
											? guarantorDetail.getTotalLandOwned().toString() : null);
							finalViewResponse.setPresentlyIrrigated(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getPresentlyIrrigated())
											? guarantorDetail.getPresentlyIrrigated() : null);
							finalViewResponse.setSeasonalIrrigated(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getSeasonalIrrigated())
											? guarantorDetail.getSeasonalIrrigated() : null);
							finalViewResponse.setRainFed(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getRainFed())
									? guarantorDetail.getRainFed() : null);
							finalViewResponse
									.setUnAttended(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getUnattended())
											? guarantorDetail.getUnattended() : null);
						} else if (guarantorDetail.getOccupationId() == 3 || guarantorDetail.getOccupationId() == 4
								|| guarantorDetail.getOccupationId() == 5) {// business/self
																			// employed
																			// prof/self
																			// employed
							finalViewResponse
									.setEntityName(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getNameOfEntity())
											? guarantorDetail.getNameOfEntity() : null);
							finalViewResponse.setOwnershipType(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getOwnershipType())
											? OwnershipTypeRetailMst.getById(guarantorDetail.getOwnershipType())
													.getValue()
											: null);
							finalViewResponse
									.setOfficeType(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getOfficeType())
											? OfficeTypeRetailMst.getById(guarantorDetail.getOfficeType()).getValue()
											: null);
							finalViewResponse
									.setNoOfPartners(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getNoPartners())
											? guarantorDetail.getNoPartners().toString() : null);
							finalViewResponse.setNameOfPartners(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getPartnersName())
											? guarantorDetail.getPartnersName() : null);
							finalViewResponse.setBusinessEstablishmentYear(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getBusinessStartDate())
											? guarantorDetail.getBusinessStartDate().getMonth() + "/"
													+ guarantorDetail.getBusinessStartDate().getYear()
											: null);
							finalViewResponse
									.setShareHolding(!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getShareHolding())
											? guarantorDetail.getShareHolding() : null);
							finalViewResponse.setAnnualTurnover(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getAnnualTurnover())
											? guarantorDetail.getAnnualTurnover().toString() : null);
							finalViewResponse.setTradeLicenseNo(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getTradeLicenseNumber())
											? guarantorDetail.getTradeLicenseNumber() : null);
							finalViewResponse.setTradeExpiryDate(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getTradeLicenseExpiryDate())
											? guarantorDetail.getTradeLicenseExpiryDate().getMonth() + "/"
													+ guarantorDetail.getTradeLicenseExpiryDate().getYear()
											: null);
							finalViewResponse.setNameOfPoaHolder(
									!CommonUtils.isObjectNullOrEmpty(guarantorDetail.getPoaHolderName())
											? guarantorDetail.getPoaHolderName() : null);
						}
					}
					List<ExistingLoanDetailRequest> existingLoanDetailRequestList = existingLoanService
							.getExistingLoanDetailList(guarantorDetail.getId(), CommonUtils.ApplicantType.GARRANTOR);
					finalViewResponse.setExistingLoanDetailRequest(existingLoanDetailRequestList);

					List<BankAccountHeldDetailsRequest> accountHeldDetailsRequestList = bankAccountsHeldService
							.getExistingLoanDetailList(guarantorDetail.getId(), CommonUtils.ApplicantType.GARRANTOR);
					finalViewResponse.setBankAccountHeldDetailsRequest(accountHeldDetailsRequestList);

					List<CreditCardsDetailRequest> creditCardsDetailRequestList = creditCardDetailsService
							.getExistingLoanDetailList(guarantorDetail.getId(), CommonUtils.ApplicantType.GARRANTOR);
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
							.getFixedDepositsDetailList(guarantorDetail.getId(), CommonUtils.ApplicantType.GARRANTOR);
					finalViewResponse.setFixedDepositsDetailsRequest(depositsDetailsRequestList);

					List<OtherCurrentAssetDetailRequest> otherCurrentAssetDetailRequestList = otherCurrentAssetService
							.getOtherCurrentAssetDetailList(guarantorDetail.getId(), CommonUtils.ApplicantType.GARRANTOR);
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
							.getOtherIncomeDetailList(guarantorDetail.getId(), CommonUtils.ApplicantType.GARRANTOR);
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
							.getReferenceRetailDetailList(guarantorDetail.getId(), CommonUtils.ApplicantType.GARRANTOR);
					finalViewResponse.setReferenceRetailDetailsRequest(referenceRetailDetailsRequestList);

					// set uploads
					switch (productId) {
						case 3:// HOME LOAN
							finalViewResponse.setGuarantor_BankACStatments(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.HOME_LOAN_GUARANTOR_STATEMENT_OF_BANK_ACCOUNT_FOR_LAST_6_MONTHS));
							finalViewResponse.setGuarantor_SalaraySlip(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.HOME_LOAN_GUARANTOR_INCOME_PROOF_LATEST_SALARY_SLIP));
							finalViewResponse.setGuarantor_ItReturn(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.HOME_LOAN_GUARANTOR_INCOME_TAX_RETURNS_OR_FORM_16_FOR_THE_LAST_2_YEARS));
							finalViewResponse.setGuarantor_BalanceSheet(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.HOME_LOAN_GUARANTOR_AUDITED_UNAUDITED_BALANCE_SHEET_PROFIT_LOSS_STATEMENT_FOR_3_YEARS));
							finalViewResponse.setGuarantor_AddressProof(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.HOME_LOAN_GUARANTOR_ADDRESS_PROOF));
							finalViewResponse.setGuarantor_IncomeProof(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.HOME_LOAN_GUARANTOR_INCOME_PROOF_OF_ENTITY___INCOME_TAX_RETURN_FOR_LAST_2_YEARS));
							finalViewResponse.setGuarantor_CropCultivation(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.HOME_LOAN_GUARANTOR_CROP_CULTIVATION_SHOWING_CROPPING_PATTERN_LAND_HOLDING_WITH_PHOTOGRAPH));
							finalViewResponse.setGuarantor_AlliedActivities(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.HOME_LOAN_GUARANTOR_DOCUMENTARY_PROOF_OF_ALLIED_AGRICULTURAL_ACTIVITIES));
							break;
						case 7:// PERSONAL LOAN
							finalViewResponse.setGuarantor_BankACStatments(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.PERSONAL_LOAN_GUARANTOR_STATEMENT_OF_BANK_ACCOUNT_FOR_LAST_6_MONTHS));
							finalViewResponse.setGuarantor_SalaraySlip(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.PERSONAL_LOAN_GUARANTOR_INCOME_PROOF_LATEST_SALARY_SLIP));
							finalViewResponse.setGuarantor_ItReturn(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.PERSONAL_LOAN_GUARANTOR_INCOME_TAX_RETURNS_OR_FORM_16_FOR_THE_LAST_2_YEARS));
							finalViewResponse.setGuarantor_BalanceSheet(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.PERSONAL_LOAN_GUARANTOR_AUDITED_UNAUDITED_BALANCE_SHEET_PROFIT__LOSS_STATEMENT_FOR_3_YEARS));
							finalViewResponse.setGuarantor_AddressProof(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.PERSONAL_LOAN_GUARANTOR_ADDRESS_PROOF_ELECTRICITY_BILL_ADHAR_CARD_VOTER_ID_CARD_ANY_1));
							finalViewResponse.setGuarantor_IncomeProof(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.PERSONAL_LOAN_GUARANTOR_INCOME_PROOF_OF_ENTITY_INCOME_TAX_RETURN_FOR_LAST_2_YEARS));
							finalViewResponse.setGuarantor_CropCultivation(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.PERSONAL_LOAN_GUARANTOR_CROP_CULTIVATION_SHOWING_CROPPING_PATTERN__LAND_HOLDING_WITH_PHOTOGRAPH));
							finalViewResponse.setGuarantor_AlliedActivities(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.PERSONAL_LOAN_GUARANTOR_DOCUMENTARY_PROOF_OF_ALLIED_AGRICULTURAL_ACTIVITIES_DAIRY__POULTRY__PLANTATION__HORTICULTURE));
							break;
						case 12:// CAR_LOAN
							finalViewResponse.setGuarantor_BankACStatments(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.CAR_LOAN_GUARANTOR_STATEMENT_OF_BANK_ACCOUNT_FOR_LAST_6_MONTHS));
							finalViewResponse.setGuarantor_SalaraySlip(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.CAR_LOAN_GUARANTOR_INCOME_PROOF_LATEST_SALARY_SLIP));
							finalViewResponse.setGuarantor_ItReturn(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.CAR_LOAN_GUARANTOR_INCOME_TAX_RETURNS_OR_FORM_16_FOR_THE_LAST_2_YEARS));
							finalViewResponse.setGuarantor_BalanceSheet(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.CAR_LOAN_GUARANTOR_AUDITED_UNAUDITED_BALANCE_SHEET_PROFIT_LOSS_STATEMENT_FOR_3_YEARS));
							finalViewResponse.setGuarantor_AddressProof(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.CAR_LOAN_GUARANTOR_ADDRESS_PROOF));
							finalViewResponse.setGuarantor_IncomeProof(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.CAR_LOAN_GUARANTOR_INCOME_PROOF_OF_ENTITY___INCOME_TAX_RETURN_FOR_LAST_2_YEARS));
							finalViewResponse.setGuarantor_CropCultivation(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.CAR_LOAN_GUARANTOR_CROP_CULTIVATION_SHOWING_CROPPING_PATTERN_LAND_HOLDING_WITH_PHOTOGRAPH));
							finalViewResponse.setGuarantor_AlliedActivities(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.CAR_LOAN_GUARANTOR_DOCUMENTARY_PROOF_OF_ALLIED_AGRICULTURAL_ACTIVITIES));
							break;
						case 13:// LOAN_AGAINST_PROPERTY
							finalViewResponse.setGuarantor_BankACStatments(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAP_LOAN_GUARANTOR_STATEMENT_OF_BANK_ACCOUNT_FOR_LAST_6_MONTHS));
							finalViewResponse.setGuarantor_SalaraySlip(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAP_LOAN_GUARANTOR_INCOME_PROOF_LATEST_SALARY_SLIP));
							finalViewResponse.setGuarantor_ItReturn(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAP_LOAN_GUARANTOR_INCOME_TAX_RETURNS_OR_FORM_16_FOR_THE_LAST_2_YEARS));
							finalViewResponse.setGuarantor_BalanceSheet(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAP_LOAN_GUARANTOR_AUDITED_UNAUDITED_BALANCE_SHEET_PROFIT_LOSS_STATEMENT_FOR_3_YEARS));
							finalViewResponse.setGuarantor_AddressProof(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAP_LOAN_GUARANTOR_ADDRESS_PROOF_ELECTRICITY_BILL_ADHAR_CARD_VOTER_ID_CARD_ANY_1));
							finalViewResponse.setGuarantor_IncomeProof(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAP_LOAN_GUARANTOR_INCOME_PROOF_OF_ENTITY_INCOME_TAX_RETURN_FOR_LAST_2_YEARS));
							finalViewResponse.setGuarantor_CropCultivation(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAP_LOAN_GUARANTOR_CROP_CULTIVATION_SHOWING_CROPPING_PATTERN_LAND_HOLDING_WITH_PHOTOGRAPH));
							finalViewResponse.setGuarantor_AlliedActivities(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAP_LOAN_GUARANTOR_DOCUMENTARY_PROOF_OF_ALLIED_AGRICULTURAL_ACTIVITIES_DAIRY__POULTRY__PLANTATION__HORTICULTURE));
							break;
						case 14:// LOAN_AGAINST_SHARES_AND_SECUIRITIES
							finalViewResponse.setGuarantor_BankACStatments(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAS_LOAN_GUARANTOR_STATEMENT_OF_BANK_ACCOUNT_FOR_LAST_6_MONTHS));
							finalViewResponse.setGuarantor_SalaraySlip(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAS_LOAN_GUARANTOR_INCOME_PROOF_LATEST_SALARY_SLIP));
							finalViewResponse.setGuarantor_ItReturn(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAS_LOAN_GUARANTOR_INCOME_TAX_RETURNS_OR_FORM_16_FOR_THE_LAST_2_YEARS));
							finalViewResponse.setGuarantor_BalanceSheet(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAS_LOAN_GUARANTOR_AUDITEDUNAUDITED_BALANCE_SHEET_PROFIT_LOSS_STATEMENT_FOR_3_YEARS));
							finalViewResponse.setGuarantor_AddressProof(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAS_LOAN_GUARANTOR_ADDRESS_PROOF_ELECTRICITY_BILL_ADHAR_CARD_VOTER_ID_CARD_ANY_1));
							finalViewResponse.setGuarantor_IncomeProof(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAS_LOAN_GUARANTOR_INCOME_PROOF_OF_ENTITY_INCOME_TAX_RETURN_FOR_LAST_2_YEARS));
							finalViewResponse.setGuarantor_CropCultivation(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAS_LOAN_GUARANTOR_CROP_CULTIVATION_SHOWING_CROPPING_PATTERN_LAND_HOLDING_WITH_PHOTOGRAPH));
							finalViewResponse.setGuarantor_AlliedActivities(documentManagementService.getDocumentDetails(guarantorDetail.getId(), DocumentAlias.UERT_TYPE_GUARANTOR, DocumentAlias.LAS_LOAN_GUARANTOR_DOCUMENTARY_PROOF_OF_ALLIED_AGRICULTURAL_ACTIVITIES_DAIRY__POULTRY__PLANTATION_HORTICULTURE));
							break;
					}
					finalCommonresponseList.add(finalViewResponse);
				}

				return finalCommonresponseList;
			} else {
				throw new Exception("No Data found");
			}
		} catch (Exception e) {
			throw new Exception("Error Fetching Guarantor Details");
		}
	}
}
