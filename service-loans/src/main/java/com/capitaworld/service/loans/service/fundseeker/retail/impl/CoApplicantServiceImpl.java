package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class CoApplicantServiceImpl implements CoApplicantService {

	private static final Logger logger = LoggerFactory.getLogger(CoApplicantServiceImpl.class.getName());

	@Autowired
	private CoApplicantDetailRepository coApplicantDetailRepository;

	@Override
	public boolean save(CoApplicantRequest applicantRequest, Long applicationId) {
		try {
			CoApplicantDetail coDetails = null;
			if (applicantRequest.getId() != null && applicationId != null) {
				coDetails = coApplicantDetailRepository.get(applicationId, applicantRequest.getId());
				if (coDetails == null) {
					throw new NullPointerException(
							"CoApplicant Id Record not exists in DB : " + applicantRequest.getId());
				}
				if (applicantRequest.getIsActive() != null && !applicantRequest.getIsActive().booleanValue()) {
					coApplicantDetailRepository.inactiveCoApplicant(applicationId, applicantRequest.getId());
					return true;
				}
				coDetails.setModifiedBy(applicantRequest.getUserId());
				coDetails.setModifiedDate(new Date());
			} else {
				coDetails = new CoApplicantDetail();
				coDetails.setRelationshipWithApplicant(applicantRequest.getRelationshipWithApplicant());
				coDetails.setCreatedBy(applicantRequest.getUserId());
				coDetails.setCreatedDate(new Date());
				coDetails.setApplicationId(new LoanApplicationMaster(applicationId));
			}
			BeanUtils.copyProperties(applicantRequest, coDetails, CommonUtils.IgnorableCopy.RETAIL_FINAL);
			copyAddressFromRequestToDomain(applicantRequest, coDetails);
			coApplicantDetailRepository.save(coDetails);
			return true;

		} catch (Exception e) {
			logger.info("Exception Throw while Saving CoApplicant Profile:-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CoApplicantRequest get(Long id, Long applicationId) {
		CoApplicantDetail applicantDetail = coApplicantDetailRepository.get(applicationId, id);
		if (applicantDetail == null) {
			throw new NullPointerException("CoApplicantDetail Record not exists in DB of ID : " + id
					+ " and ApplicationId==>" + applicationId);
		}
		CoApplicantRequest applicantRequest = new CoApplicantRequest();
		BeanUtils.copyProperties(applicantDetail, applicantRequest, CommonUtils.IgnorableCopy.RETAIL_FINAL);
		copyAddressFromDomainToRequest(applicantDetail, applicantRequest);
		return applicantRequest;
	}

	@Override
	public List<CoApplicantRequest> getList(Long applicationId) {
		List<CoApplicantDetail> details = coApplicantDetailRepository.getList(applicationId);
		List<CoApplicantRequest> requests = new ArrayList<>(details.size());
		for (CoApplicantDetail detail : details) {
			CoApplicantRequest request = new CoApplicantRequest();
			BeanUtils.copyProperties(detail, request, CommonUtils.IgnorableCopy.RETAIL_FINAL);
			requests.add(request);
		}
		return requests;
	}

	@Override
	public boolean saveFinal(FinalCommonRetailRequest applicantRequest) {
		try {
			if (applicantRequest.getApplicationId() == null || applicantRequest.getId() == null) {
				return false;
			}
			CoApplicantDetail coDetails = coApplicantDetailRepository.get(applicantRequest.getApplicationId(),
					applicantRequest.getId());
			if (coDetails == null) {
				throw new NullPointerException("CoApplicant Id Record not exists in DB ID: " + applicantRequest.getId()
						+ " and Application Id==>" + applicantRequest.getApplicationId());
			}
			coDetails.setModifiedBy(applicantRequest.getUserId());
			coDetails.setModifiedDate(new Date());
			BeanUtils.copyProperties(applicantRequest, coDetails, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
			coApplicantDetailRepository.save(coDetails);
			return true;

		} catch (Exception e) {
			logger.info("Exception Throw while Saving CoApplicant Profile:-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public FinalCommonRetailRequest getFinal(Long id, Long applicationId) {
		CoApplicantDetail applicantDetail = coApplicantDetailRepository.get(applicationId, id);
		if (applicantDetail == null) {
			throw new NullPointerException("CoApplicantDetail Record of Final Portion not exists in DB of ID : " + id
					+ " and Application Id ==>" + applicationId);
		}
		FinalCommonRetailRequest applicantRequest = new FinalCommonRetailRequest();
		BeanUtils.copyProperties(applicantDetail, applicantRequest, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
		return applicantRequest;
	}

	public static void copyAddressFromRequestToDomain(CoApplicantRequest from, CoApplicantDetail to) {
		if (from.getFirstAddress() != null) {
			to.setPermanentPremiseNumberName(from.getFirstAddress().getPremiseNumber());
			to.setPermanentStreetName(from.getFirstAddress().getStreetName());
			to.setPermanentLandMark(from.getFirstAddress().getLandMark());
			to.setPermanentCityId(from.getFirstAddress().getCityId().intValue());
			to.setPermanentStateId(from.getFirstAddress().getStateId());
			to.setPermanentCountryId(from.getFirstAddress().getCountryId());
			to.setPermanentPincode(from.getFirstAddress().getPincode().intValue());
		}

		if (from.isAddressSameAs()) {
			if (from.getFirstAddress() != null) {
				to.setOfficePremiseNumberName(from.getFirstAddress().getPremiseNumber());
				to.setOfficeStreetName(from.getFirstAddress().getStreetName());
				to.setOfficeLandMark(from.getFirstAddress().getLandMark());
				to.setOfficeCityId(from.getFirstAddress().getCityId().intValue());
				to.setOfficeStateId(from.getFirstAddress().getStateId());
				to.setOfficeCountryId(from.getFirstAddress().getCountryId());
				to.setOfficePincode(from.getFirstAddress().getPincode().intValue());
			}
		} else {
			if (from.getSecondAddress() != null) {
				to.setOfficePremiseNumberName(from.getSecondAddress().getPremiseNumber());
				to.setOfficeStreetName(from.getSecondAddress().getStreetName());
				to.setOfficeLandMark(from.getSecondAddress().getLandMark());
				to.setOfficeCityId(from.getSecondAddress().getCityId().intValue());
				to.setOfficeStateId(from.getSecondAddress().getStateId());
				to.setOfficeCountryId(from.getSecondAddress().getCountryId());
				to.setOfficePincode(from.getSecondAddress().getPincode().intValue());
			}
		}

	}

	public static void copyAddressFromDomainToRequest(CoApplicantDetail from, CoApplicantRequest to) {
		Address address = new Address();
		address.setPremiseNumber(from.getPermanentPremiseNumberName());
		address.setLandMark(from.getPermanentLandMark());
		address.setStreetName(from.getAddressStreetName());
		address.setCityId(from.getPermanentCityId().longValue());
		address.setStateId(from.getPermanentStateId());
		address.setCountryId(from.getPermanentCountryId());
		address.setPincode(from.getPermanentPincode().longValue());
		to.setFirstAddress(address);
		if (from.getAddressSameAs()) {
			to.setSecondAddress(address);
		} else {
			address = new Address();
			address.setPremiseNumber(from.getOfficePremiseNumberName());
			address.setLandMark(from.getOfficeLandMark());
			address.setStreetName(from.getPermanentStreetName());
			address.setCityId(from.getOfficeCityId().longValue());
			address.setStateId(from.getOfficeStateId());
			address.setCountryId(from.getOfficeCountryId());
			address.setPincode(from.getOfficePincode().longValue());
		}
	}
}
