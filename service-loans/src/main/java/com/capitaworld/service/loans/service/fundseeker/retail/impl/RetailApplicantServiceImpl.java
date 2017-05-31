package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.retail.GuarantorRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class RetailApplicantServiceImpl implements RetailApplicantService {

	private static final Logger logger = LoggerFactory.getLogger(RetailApplicantServiceImpl.class.getName());

	@Autowired
	private RetailApplicantDetailRepository applicantRepository;

	@Autowired
	private CoApplicantService coApplicantService;

	@Autowired
	private GuarantorService guarantorService;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public boolean save(RetailApplicantRequest applicantRequest, Long userId) throws Exception {

		try {
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId
					: applicantRequest.getClientId());
			RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(finalUserId,
					applicantRequest.getApplicationId());
			if (applicantDetail != null) {
				applicantDetail.setModifiedBy(userId);
				applicantDetail.setModifiedDate(new Date());
			} else {
				applicantDetail = new RetailApplicantDetail();
				applicantDetail.setCreatedBy(userId);
				applicantDetail.setCreatedDate(new Date());
				applicantDetail.setIsActive(true);
				applicantDetail.setApplicationId(new LoanApplicationMaster(applicantRequest.getApplicationId()));
			}

			BeanUtils.copyProperties(applicantRequest, applicantDetail, CommonUtils.IgnorableCopy.RETAIL_FINAL);
			copyAddressFromRequestToDomain(applicantRequest, applicantDetail);
			if (applicantRequest.getDate() != null && applicantRequest.getMonth() != null
					&& applicantRequest.getYear() != null) {
				Date birthDate = CommonUtils.getDateByDateMonthYear(applicantRequest.getDate(),
						applicantRequest.getMonth(), applicantRequest.getYear());
				applicantDetail.setBirthDate(birthDate);
			}
			applicantDetail = applicantRepository.save(applicantDetail);
			for (CoApplicantRequest request : applicantRequest.getCoApplicants()) {
				coApplicantService.save(request, applicantRequest.getApplicationId(), (CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId : applicantRequest.getClientId()));
			}
			for (GuarantorRequest request : applicantRequest.getGuarantors()) {
				guarantorService.save(request, applicantRequest.getApplicationId(), (CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId : applicantRequest.getClientId()));
			}

			// Updating Flag
			loanApplicationRepository.setIsApplicantProfileMandatoryFilled(applicantRequest.getApplicationId(),
					finalUserId, applicantRequest.getIsApplicantDetailsFilled());

			return true;

		} catch (Exception e) {
			logger.error("Error while Saving Retail Profile:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	@Override
	public RetailApplicantRequest get(Long userId, Long applicationId) throws Exception {
		try {
			RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(userId,
					applicationId);
			if (applicantDetail == null) {
				// throw new NullPointerException("RetailApplicantDetail Record
				// not exists in DB of Application ID : "
				// + applicationId + " and User Id==>" + userId);
				return null;
			}
			RetailApplicantRequest applicantRequest = new RetailApplicantRequest();
			BeanUtils.copyProperties(applicantDetail, applicantRequest);
			copyAddressFromDomainToRequest(applicantDetail, applicantRequest);
			applicantRequest.setCoApplicants(coApplicantService.getList(applicationId, userId));
			applicantRequest.setGuarantors(guarantorService.getList(applicationId, userId));
			Integer[] saperatedTime = CommonUtils.saperateDayMonthYearFromDate(applicantDetail.getBirthDate());
			applicantRequest.setDate(saperatedTime[0]);
			applicantRequest.setMonth(saperatedTime[1]);
			applicantRequest.setYear(saperatedTime[2]);
			return applicantRequest;
		} catch (Exception e) {
			logger.error("Error while Saving Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public FinalCommonRetailRequest getFinal(Long id, Long applicationId) throws Exception {
		try {
			RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(id, applicationId);
			if (applicantDetail == null) {
				throw new NullPointerException("RetailApplicantDetail Record of Final Portion not exists in DB of ID : "
						+ id + "  ApplicationId==>" + applicationId);
			}
			FinalCommonRetailRequest applicantRequest = new FinalCommonRetailRequest();
			BeanUtils.copyProperties(applicantDetail, applicantRequest, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
			applicantRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(applicantDetail.getCurrencyId()));
			return applicantRequest;
		} catch (Exception e) {
			logger.error("Error while Saving Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public boolean saveFinal(FinalCommonRetailRequest applicantRequest, Long userId) throws Exception {
		try {
			if (applicantRequest.getApplicationId() == null) {
				throw new NullPointerException("Application Id and ID(Primary Key) must not be null=>Application ID==>"
						+ applicantRequest.getApplicationId() + " User Id (Primary Key)==>" + userId);
			}
			RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId((CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId : applicantRequest.getClientId()),
			Long finaluserId = (CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId : applicantRequest.getClientId());
			RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(finaluserId,
					applicantRequest.getApplicationId());
			if (applicantDetail == null) {
				throw new NullPointerException(
						"Applicant ID and ID(Primary Key) does not match with the database==> Applicant ID==>"
								+ applicantRequest.getApplicationId() + "User ID==>" + userId);
			}
			applicantDetail.setModifiedBy(userId);
			applicantDetail.setModifiedDate(new Date());
			BeanUtils.copyProperties(applicantRequest, applicantDetail, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
			applicantRepository.save(applicantDetail);
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<CoApplicantRequest> getCoApplicants(Long userId, Long applicationId) throws Exception {
		// TODO Auto-generated method stub
		return coApplicantService.getList(applicationId, userId);
	}

	@Override
	public Integer getCurrency(Long applicationId,Long userId) throws Exception{
		try{
		return loanApplicationRepository.getCurrencyId(applicationId, userId);
		} catch (Exception e) {
			logger.error("Error while Getting Currency:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<GuarantorRequest> getGuarantors(Long userId, Long applicationId) throws Exception {
		return guarantorService.getList(applicationId, userId);
	}

	public static void copyAddressFromRequestToDomain(RetailApplicantRequest from, RetailApplicantDetail to) {
		if (from.getFirstAddress() != null) {
			to.setPermanentPremiseNumberName(from.getFirstAddress().getPremiseNumber());
			to.setPermanentStreetName(from.getFirstAddress().getStreetName());
			to.setPermanentLandMark(from.getFirstAddress().getLandMark());
			to.setPermanentCityId(from.getFirstAddress().getCityId());
			to.setPermanentStateId(from.getFirstAddress().getStateId());
			to.setPermanentCountryId(from.getFirstAddress().getCountryId());
			to.setPermanentPincode(from.getFirstAddress().getPincode());
		}

		if (from.isAddressSameAs()) {
			if (from.getFirstAddress() != null) {
				to.setOfficePremiseNumberName(from.getFirstAddress().getPremiseNumber());
				to.setOfficeStreetName(from.getFirstAddress().getStreetName());
				to.setOfficeLandMark(from.getFirstAddress().getLandMark());
				to.setOfficeCityId(from.getFirstAddress().getCityId());
				to.setOfficeStateId(from.getFirstAddress().getStateId());
				to.setOfficeCountryId(from.getFirstAddress().getCountryId());
				to.setOfficePincode(from.getFirstAddress().getPincode());
			}
		} else {
			if (from.getSecondAddress() != null) {
				to.setOfficePremiseNumberName(from.getSecondAddress().getPremiseNumber());
				to.setOfficeStreetName(from.getSecondAddress().getStreetName());
				to.setOfficeLandMark(from.getSecondAddress().getLandMark());
				to.setOfficeCityId(from.getSecondAddress().getCityId());
				to.setOfficeStateId(from.getSecondAddress().getStateId());
				to.setOfficeCountryId(from.getSecondAddress().getCountryId());
				to.setOfficePincode(from.getSecondAddress().getPincode());
			}
		}

	}

	public static void copyAddressFromDomainToRequest(RetailApplicantDetail from, RetailApplicantRequest to) {
		Address address = new Address();
		address.setPremiseNumber(from.getPermanentPremiseNumberName());
		address.setLandMark(from.getPermanentLandMark());
		address.setStreetName(from.getPermanentStreetName());
		address.setCityId(from.getPermanentCityId());
		address.setStateId(from.getPermanentStateId());
		address.setCountryId(from.getPermanentCountryId());
		address.setPincode(from.getPermanentPincode());
		to.setFirstAddress(address);
		if (from.getAddressSameAs()) {
			to.setSecondAddress(address);
		} else {
			address = new Address();
			address.setPremiseNumber(from.getOfficePremiseNumberName());
			address.setLandMark(from.getOfficeLandMark());
			address.setStreetName(from.getOfficeStreetName());
			address.setCityId(from.getOfficeCityId());
			address.setStateId(from.getOfficeStateId());
			address.setCountryId(from.getOfficeCountryId());
			address.setPincode(from.getOfficePincode());
			to.setSecondAddress(address);
		}
	}

}
