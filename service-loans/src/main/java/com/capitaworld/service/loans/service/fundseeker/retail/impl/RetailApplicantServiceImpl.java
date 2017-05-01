package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.RetailApplicantRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;

@Service
@Transactional
public class RetailApplicantServiceImpl implements RetailApplicantService {

	private static final Logger logger = LoggerFactory.getLogger(RetailApplicantServiceImpl.class.getName());

	@Autowired
	private RetailApplicantDetailRepository applicantRepository;

	@Override
	public boolean save(RetailApplicantRequest applicantRequest) {
		RetailApplicantDetail applicantDetail = null;
		try {
			// application id must not be null
			if (applicantRequest.getId() != null && applicantRequest.getApplicationId() != null) {
				applicantDetail = applicantRepository.getByApplicationAndID(applicantRequest.getId(),
						applicantRequest.getApplicationId());
				if (applicantDetail == null) {
					return false;
				}
				applicantDetail.setModifiedBy(applicantRequest.getUserId());
				applicantDetail.setModifiedDate(new Date());
			} else {
				applicantDetail = new RetailApplicantDetail();
				applicantDetail.setCreatedBy(applicantRequest.getUserId());
				applicantDetail.setCreatedDate(new Date());
				applicantDetail.setActive(true);
				applicantDetail.setApplicationId(new LoanApplicationMaster(applicantRequest.getApplicationId()));
			}

			BeanUtils.copyProperties(applicantRequest, applicantDetail);
			copyAddressFromRequestToDomain(applicantRequest, applicantDetail);
			applicantDetail = applicantRepository.save(applicantDetail);
			return true;

		} catch (Exception e) {
			logger.info("Exception Throw while Saving Profile:-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public RetailApplicantRequest get(Long id) {
		RetailApplicantDetail applicantDetail = applicantRepository.findOne(id);
		if (applicantDetail == null) {
			return null;
		}
		RetailApplicantRequest applicantRequest = new RetailApplicantRequest();
		BeanUtils.copyProperties(applicantDetail, applicantRequest);
		copyAddressFromDomainToRequest(applicantDetail, applicantRequest);
		return applicantRequest;
	}

	private static void copyAddressFromRequestToDomain(RetailApplicantRequest from, RetailApplicantDetail to) {
		if (from.getFirstAddress() != null) {
			to.setPermanentPremiseNumberName(from.getFirstAddress().getPremiseNumber());
			to.setPermanentStreetName(from.getFirstAddress().getStreetName());
			to.setPermanentLandMark(from.getFirstAddress().getLandMark());
			to.setPermanentCityId(from.getFirstAddress().getCityId());
			to.setPermanentStateId(from.getFirstAddress().getStateId());
			to.setPermanentCountryId(from.getFirstAddress().getCountryId());
			to.setPermanentPincode(from.getFirstAddress().getPincode());
		}

		if (from.isSameAs()) {
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

	private static void copyAddressFromDomainToRequest(RetailApplicantDetail from, RetailApplicantRequest to) {
		Address address = new Address();
		address.setPremiseNumber(from.getPermanentPremiseNumberName());
		address.setLandMark(from.getPermanentLandMark());
		address.setStreetName(from.getAddressStreetName());
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
			address.setStreetName(from.getPermanentStreetName());
			address.setCityId(from.getOfficeCityId());
			address.setStateId(from.getOfficeStateId());
			address.setCountryId(from.getOfficeCountryId());
			address.setPincode(from.getOfficePincode());
		}
	}
}
