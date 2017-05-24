package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.GuarantorDetails;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.retail.GuarantorRequest;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryPersonalLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private PrimaryPersonalLoanDetailRepository personalLoanDetailRepository;
	
	@Autowired
	Environment environment; 

	@Override
	public boolean save(GuarantorRequest guarantorRequest, Long applicationId, Long userId) throws Exception {
		try {
			GuarantorDetails guarantorDetails = guarantorDetailsRepository.get(applicationId, (CommonUtils.isObjectNullOrEmpty(guarantorRequest.getClientId()) ? userId : guarantorRequest.getClientId()),
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
			return true;

		} catch (Exception e) {
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
			GuarantorDetails guaDetails = guarantorDetailsRepository.get(applicantRequest.getApplicationId(), (CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId : applicantRequest.getClientId()),
					applicantRequest.getId());
			if (guaDetails == null) {
				throw new NullPointerException("Guarantor Id Record not exists in DB : Application Id==>"
						+ applicantRequest.getApplicationId());
			}
			guaDetails.setModifiedBy(userId);
			guaDetails.setModifiedDate(new Date());
			BeanUtils.copyProperties(applicantRequest, guaDetails, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
			guarantorDetailsRepository.save(guaDetails);
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
	public List<RetailProfileViewResponse> getGuarantorServiceResponse(Long applicantId, Long userId) throws Exception {
		try {
			List<GuarantorDetails> guarantorDetails = guarantorDetailsRepository.getList(applicantId, userId);
			if (guarantorDetails != null && !guarantorDetails.isEmpty()) {
				List<RetailProfileViewResponse> plResponses = new ArrayList<RetailProfileViewResponse>();

				for (GuarantorDetails guarantorDetail : guarantorDetails) {

					RetailProfileViewResponse profileViewPLResponse = new RetailProfileViewResponse();
					profileViewPLResponse.setCompanyName(guarantorDetail.getCompanyName());
					try {
						if (guarantorDetail.getEmployedWithId() != 8) {
							profileViewPLResponse.setEmployeeWith(EmployeeWith.getById(guarantorDetail.getEmployedWithId()).getValue());
						} else {
							profileViewPLResponse.setEmployeeWith(guarantorDetail.getEmployedWithOther());
						}
					} catch (Exception e) {
					}
					profileViewPLResponse.setFirstName(guarantorDetail.getFirstName());
					try {
						profileViewPLResponse.setGender(Gender.getById(guarantorDetail.getGenderId()).getValue());
					} catch (Exception e) {
					}
					profileViewPLResponse.setLastName(guarantorDetail.getLastName());
					profileViewPLResponse.setMaritalStatus(guarantorDetail.getStatusId() != null ? MaritalStatus.getById(guarantorDetail.getStatusId()).getValue() : null);
					profileViewPLResponse.setMiddleName(guarantorDetail.getMiddleName());
					profileViewPLResponse.setMonthlyIncome(String.valueOf(guarantorDetail.getMonthlyIncome() != null ? guarantorDetail.getMonthlyIncome() : 0));
					profileViewPLResponse.setNatureOfOccupation(guarantorDetail.getOccupationId() != null ? OccupationNature.getById(guarantorDetail.getOccupationId()).getValue() : null);
					profileViewPLResponse.setTitle(guarantorDetail.getTitleId() != null ? Title.getById(guarantorDetail.getTitleId()).getValue() : null);
					profileViewPLResponse.setAge(guarantorDetail.getBirthDate() != null ? CommonUtils.getAgeFromBirthDate(guarantorDetail.getBirthDate()).toString() : null);

					if (guarantorDetail.getApplicationId() != null) {
						profileViewPLResponse.setCurrency(guarantorDetail.getApplicationId().getCurrencyId() != null ? Currency.getById(guarantorDetail.getApplicationId().getCurrencyId()).getValue() : null);
					}
					profileViewPLResponse.setEntityName(guarantorDetail.getEntityName());
					if (guarantorDetail.getIndustryTypeId() != null && guarantorDetail.getIndustryTypeId() != 16) {
						profileViewPLResponse.setIndustryType(IndustryType.getById(guarantorDetail.getIndustryTypeId()).getValue());
					} else {
						profileViewPLResponse.setIndustryType(guarantorDetail.getIndustryTypeOther());
					}

					//set pan car
					profileViewPLResponse.setPan(guarantorDetail.getPan());

					//get list of Pan Card
					DMSClient dmsClient = new DMSClient(environment.getProperty(DMS_URL));
					DocumentRequest documentRequestPanCard = new DocumentRequest();
					documentRequestPanCard.setGuarantorId(guarantorDetail.getId());
					documentRequestPanCard.setUserType(DocumentAlias.UERT_TYPE_GUARANTOR);
					documentRequestPanCard.setProductDocumentMappingId(DocumentAlias.GUARANTOR_SCANNED_COPY_OF_PAN_CARD);
					try {
						DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequestPanCard);
						profileViewPLResponse.setPanCardList(documentResponse.getDataList());
					} catch (DocumentException e) {
						e.printStackTrace();
					}

					//get list of Aadhar Card
					DocumentRequest documentRequestAadharCard = new DocumentRequest();
					documentRequestAadharCard.setGuarantorId(guarantorDetail.getId());
					documentRequestAadharCard.setUserType(DocumentAlias.UERT_TYPE_GUARANTOR);
					documentRequestAadharCard.setProductDocumentMappingId(DocumentAlias.GUARANTOR_SCANNED_COPY_OF_AADHAR_CARD);
					try {
						DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequestAadharCard);
						profileViewPLResponse.setAadharCardList(documentResponse.getDataList());
					} catch (DocumentException e) {
						e.printStackTrace();
					}

					plResponses.add(profileViewPLResponse);
				}

				return plResponses;
			} else {
				throw new Exception("No Data found");
			}
		} catch (Exception e) {
			throw new Exception("Error Fetching Guarantor Details");
		}
	}
}
