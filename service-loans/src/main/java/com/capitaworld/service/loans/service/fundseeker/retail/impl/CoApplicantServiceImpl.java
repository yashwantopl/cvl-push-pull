package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryPersonalLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
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
public class CoApplicantServiceImpl implements CoApplicantService {

	private static final Logger logger = LoggerFactory.getLogger(CoApplicantServiceImpl.class.getName());
	
	protected static final String DMS_URL = "dmsURL";

	@Autowired
	private CoApplicantDetailRepository coApplicantDetailRepository;
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository; 
	
	@Autowired
	private PrimaryPersonalLoanDetailRepository personalLoanDetailRepository;
	
	
	@Autowired
	Environment environment; 

	@Override
	public boolean save(CoApplicantRequest applicantRequest, Long applicationId, Long userId) throws Exception {
		try {
			CoApplicantDetail coDetails = coApplicantDetailRepository.get(applicationId, (CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId : applicantRequest.getClientId()),
					applicantRequest.getId());
			if (coDetails != null) {
				// throw new NullPointerException("CoApplicant Id Record not
				// exists in DB : " + applicantRequest.getId()
				// + " Applicant Id ==>" + applicationId);
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
			return true;

		} catch (Exception e) {
			logger.error("Error while Saving Retail Profile:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
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
			throw new Exception("Something went Wrong !");
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
			throw new Exception("Something went Wrong !");
		}
	}

	@Override
	public boolean saveFinal(FinalCommonRetailRequest applicantRequest, Long userId) throws Exception {
		try {
			CoApplicantDetail coDetails = coApplicantDetailRepository.get(applicantRequest.getApplicationId(), (CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId : applicantRequest.getClientId()),
					applicantRequest.getId());
			if (coDetails == null) {
				throw new NullPointerException("CoApplicant Id Record not exists in DB ID: " + applicantRequest.getId()
						+ " and Application Id==>" + applicantRequest.getApplicationId());
			}
			coDetails.setModifiedBy(userId);
			coDetails.setModifiedDate(new Date());
			BeanUtils.copyProperties(applicantRequest, coDetails, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
			coApplicantDetailRepository.save(coDetails);
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
			return applicantRequest;
		} catch (Exception e) {
			logger.error("Error while getting Final CoApplicant Retail Profile:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
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
	public List<RetailProfileViewResponse> getCoApplicantPLResponse(Long applicantId, Long userId) throws Exception {
		try {
			List<CoApplicantDetail> coApplicantDetails = coApplicantDetailRepository.getList(applicantId, userId);
			if (coApplicantDetails != null && !coApplicantDetails.isEmpty()) {
				List<RetailProfileViewResponse> plResponses = new ArrayList<RetailProfileViewResponse>();

				for (CoApplicantDetail coApplicantDetail : coApplicantDetails) {
					RetailProfileViewResponse profileViewPLResponse = new RetailProfileViewResponse();
					profileViewPLResponse.setCompanyName(coApplicantDetail.getCompanyName());
					try {
						if (coApplicantDetail.getEmployedWithId() != 8) {
							profileViewPLResponse.setEmployeeWith(EmployeeWith.getById(coApplicantDetail.getEmployedWithId()).getValue());
						} else {
							profileViewPLResponse.setEmployeeWith(coApplicantDetail.getEmployedWithOther());
						}
					} catch (Exception e) {
					}
					profileViewPLResponse.setFirstName(coApplicantDetail.getFirstName());
					try {
						profileViewPLResponse.setGender(Gender.getById(coApplicantDetail.getGenderId()).getValue());
					} catch (Exception e) {
					}
					profileViewPLResponse.setLastName(coApplicantDetail.getLastName());
					profileViewPLResponse.setMaritalStatus(coApplicantDetail.getStatusId() != null ? MaritalStatus.getById(coApplicantDetail.getStatusId()).getValue() : null);
					profileViewPLResponse.setMiddleName(coApplicantDetail.getMiddleName());
					profileViewPLResponse.setMonthlyIncome(String.valueOf(coApplicantDetail.getMonthlyIncome() != null ? coApplicantDetail.getMonthlyIncome() : 0));
					profileViewPLResponse.setNatureOfOccupation(coApplicantDetail.getOccupationId() != null ? OccupationNature.getById(coApplicantDetail.getOccupationId()).getValue() : null);
					profileViewPLResponse.setTitle(coApplicantDetail.getTitleId() != null ? Title.getById(coApplicantDetail.getTitleId()).getValue() : null);
					profileViewPLResponse.setAge(coApplicantDetail.getBirthDate() != null ? CommonUtils.getAgeFromBirthDate(coApplicantDetail.getBirthDate()).toString() : null);

					if (coApplicantDetail.getApplicationId() != null) {
						profileViewPLResponse.setCurrency(coApplicantDetail.getApplicationId().getCurrencyId() != null ? Currency.getById(coApplicantDetail.getApplicationId().getCurrencyId()).getValue() : null);
					}

					profileViewPLResponse.setRelationshipWithApplicant(coApplicantDetail.getRelationshipWithApplicant() != null ? RelationshipType.getById(coApplicantDetail.getRelationshipWithApplicant()).getValue() : null);
					profileViewPLResponse.setEntityName(coApplicantDetail.getEntityName());
					if (coApplicantDetail.getIndustryTypeId() != null && coApplicantDetail.getIndustryTypeId() != 16) {
						profileViewPLResponse.setIndustryType(IndustryType.getById(coApplicantDetail.getIndustryTypeId()).getValue());
					} else {
						profileViewPLResponse.setIndustryType(coApplicantDetail.getIndustryTypeOther());
					}
					//set pan car
					profileViewPLResponse.setPan(coApplicantDetail.getPan());

					//get list of Pan Card
					DMSClient dmsClient = new DMSClient(environment.getProperty(DMS_URL));
					DocumentRequest documentRequestPanCard = new DocumentRequest();
					documentRequestPanCard.setCoApplicantId(coApplicantDetail.getId());
					documentRequestPanCard.setUserType(DocumentAlias.UERT_TYPE_CO_APPLICANT);
					documentRequestPanCard.setProductDocumentMappingId(DocumentAlias.CO_APPLICANT_SCANNED_COPY_OF_PAN_CARD);
					try {
						DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequestPanCard);
						profileViewPLResponse.setPanCardList(documentResponse.getDataList());
					} catch (DocumentException e) {
						e.printStackTrace();
					}

					//get list of Aadhar Card
					DocumentRequest documentRequestAadharCard = new DocumentRequest();
					documentRequestAadharCard.setCoApplicantId(coApplicantDetail.getId());
					documentRequestAadharCard.setUserType(DocumentAlias.UERT_TYPE_CO_APPLICANT);
					documentRequestAadharCard.setProductDocumentMappingId(DocumentAlias.CO_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD);
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
				throw new Exception("No CoApplicant Found");
			}
		} catch (Exception e) {
			throw new Exception("Error Occured while fetching CoApplicant Details");
		}
	}
}
