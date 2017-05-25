package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import com.capitaworld.service.dms.util.CommonUtil;
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
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.EmployeeWith;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.IndustryType;
import com.capitaworld.service.oneform.enums.MaritalStatus;
import com.capitaworld.service.oneform.enums.OccupationNature;
import com.capitaworld.service.oneform.enums.RelationshipType;
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
		try{
			List<CoApplicantDetail> coApplicantDetails =  coApplicantDetailRepository.getList(applicantId,userId);
			if(coApplicantDetails!=null && !coApplicantDetails.isEmpty()){
				List<RetailProfileViewResponse> plResponses = new ArrayList<RetailProfileViewResponse>();

				for (CoApplicantDetail coApplicantDetail : coApplicantDetails) {
					RetailProfileViewResponse profileViewPLResponse = new RetailProfileViewResponse();
					if (coApplicantDetail.getOccupationId() != null) {
						if (coApplicantDetail.getOccupationId() == 2) {
							profileViewPLResponse.setNatureOfOccupation(OccupationNature.getById(coApplicantDetail.getOccupationId()).getValue());
							if (!CommonUtil.isObjectNullOrEmpty(coApplicantDetail.getCompanyName())) {
								profileViewPLResponse.setCompanyName(coApplicantDetail.getCompanyName());
							}
							if (!CommonUtil.isObjectNullOrEmpty(coApplicantDetail.getEmployedWithId())) {
								if (coApplicantDetail.getEmployedWithId() == 8) {
									profileViewPLResponse.setEmployeeWith(coApplicantDetail.getEmployedWithOther());
								} else {
									profileViewPLResponse.setEmployeeWith(EmployeeWith.getById(coApplicantDetail.getEmployedWithId()).getValue());
								}
							}
						} else if (coApplicantDetail.getOccupationId() == 3 || coApplicantDetail.getOccupationId() == 4) {
							profileViewPLResponse.setNatureOfOccupation(OccupationNature.getById(coApplicantDetail.getOccupationId()).getValue());
							if (!CommonUtil.isObjectNullOrEmpty(coApplicantDetail.getEntityName())) {
								profileViewPLResponse.setEntityName(coApplicantDetail.getEntityName());
							}
							if (!CommonUtil.isObjectNullOrEmpty(coApplicantDetail.getIndustryTypeId())) {
								if (coApplicantDetail.getIndustryTypeId() == 16) {
									profileViewPLResponse.setIndustryType(coApplicantDetail.getIndustryTypeOther());
								} else {
									profileViewPLResponse.setIndustryType(IndustryType.getById(coApplicantDetail.getIndustryTypeId()).getValue());
								}
							}
						} else if (coApplicantDetail.getOccupationId() == 5) {
							profileViewPLResponse.setNatureOfOccupation(OccupationNature.getById(coApplicantDetail.getOccupationId()).getValue());
							if (coApplicantDetail.getSelfEmployedOccupationId() == 10) {
								profileViewPLResponse.setOccupation(coApplicantDetail.getSelfEmployedOccupationOther());
							} else {
								profileViewPLResponse.setOccupation(Occupation.getById(coApplicantDetail.getSelfEmployedOccupationId()).getValue());
							}
						} else if (coApplicantDetail.getOccupationId() == 6) {
							profileViewPLResponse.setNatureOfOccupation(OccupationNature.getById(coApplicantDetail.getOccupationId()).getValue());
							if (!CommonUtil.isObjectNullOrEmpty(coApplicantDetail.getLandSize())) {
								profileViewPLResponse.setLandSize(LandSize.getById(coApplicantDetail.getLandSize().intValue()).getValue());
							}
							if (!CommonUtil.isObjectNullOrEmpty(coApplicantDetail.getAlliedActivityId())) {
								profileViewPLResponse.setAlliedActivity(AlliedActivity.getById(coApplicantDetail.getAlliedActivityId()).getValue());
							}
						} else if (coApplicantDetail.getOccupationId() == 7) {
							profileViewPLResponse.setNatureOfOccupation(OccupationNature.getById(coApplicantDetail.getOccupationId()).getValue());
						}
					}

					//set pan car
					profileViewPLResponse.setRelationshipWithApplicant(coApplicantDetail.getRelationshipWithApplicant()!=null ? RelationshipType.getById(coApplicantDetail.getRelationshipWithApplicant()).getValue():null);
					profileViewPLResponse.setPan(coApplicantDetail.getPan() != null ? coApplicantDetail.getPan() : null);
					profileViewPLResponse.setTitle(coApplicantDetail.getTitleId() != null ? Title.getById(coApplicantDetail.getTitleId()).getValue() : null);
					profileViewPLResponse.setAge(coApplicantDetail.getBirthDate() != null ? CommonUtils.getAgeFromBirthDate(coApplicantDetail.getBirthDate()).toString() : null);
					profileViewPLResponse.setFirstName(coApplicantDetail.getFirstName() != null ? coApplicantDetail.getFirstName() : null);
					profileViewPLResponse.setGender(coApplicantDetail.getGenderId() != null ? Gender.getById(coApplicantDetail.getGenderId()).getValue() : null);
					profileViewPLResponse.setLastName(coApplicantDetail.getLastName() != null ? coApplicantDetail.getLastName() : null);
					profileViewPLResponse.setMaritalStatus(coApplicantDetail.getStatusId() != null ? MaritalStatus.getById(coApplicantDetail.getStatusId()).getValue() : null);
					profileViewPLResponse.setMiddleName(coApplicantDetail.getMiddleName() != null ? coApplicantDetail.getMiddleName() : null);
					profileViewPLResponse.setMonthlyIncome(String.valueOf(coApplicantDetail.getMonthlyIncome() != null ? coApplicantDetail.getMonthlyIncome() : 0));
					plResponses.add(profileViewPLResponse);
				}

				return plResponses;
			}
			else{
				throw new Exception("No CoApplicant Found");
			}
		}
		catch(Exception e){
			throw new Exception("Error Occured while fetching CoApplicant Details");
		}
	}
}
