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
import com.capitaworld.service.loans.domain.fundseeker.retail.GuarantorDetails;
import com.capitaworld.service.loans.model.GuarantorRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;

@Service
@Transactional
public class GuarantorServiceImpl implements GuarantorService {

	private static final Logger logger = LoggerFactory.getLogger(GuarantorServiceImpl.class.getName());

	@Autowired
	private GuarantorDetailsRepository guarantorDetailsRepository;

	@Override
	public boolean save(GuarantorRequest guarantorRequest, Long applicationId) {
		try {
			if (guarantorRequest == null || applicationId == null) {
				return false;
			}
			GuarantorDetails guarantorDetails = null;
			if (guarantorRequest.getId() != null && applicationId != null) {
				guarantorDetails = guarantorDetailsRepository.get(applicationId, guarantorRequest.getId());
				if (guarantorDetails == null) {
					throw new NullPointerException(
							"CoApplicant Id Record not exists in DB : " + guarantorRequest.getId());
				}
				if (guarantorRequest.getIsActive() != null && !guarantorRequest.getIsActive().booleanValue()) {
					guarantorDetailsRepository.inactiveGuarantor(applicationId, guarantorRequest.getId());
					return true;
				}
				guarantorDetails.setModifiedBy(guarantorRequest.getUserId());
				guarantorDetails.setModifiedDate(new Date());
			} else {
				guarantorDetails = new GuarantorDetails();
				guarantorDetails.setFirstName(guarantorRequest.getFirstName());
				guarantorDetails.setCreatedBy(guarantorRequest.getUserId());
				guarantorDetails.setCreatedDate(new Date());
				guarantorDetails.setApplicationId(new LoanApplicationMaster(applicationId));
			}
			BeanUtils.copyProperties(guarantorRequest, guarantorDetails);
			guarantorDetailsRepository.save(guarantorDetails);
			return true;

		} catch (Exception e) {
			logger.info("Exception Throw while Saving Profile:-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public GuarantorRequest get(Long id, Long applicationId) {
		GuarantorDetails guarantorDetail = guarantorDetailsRepository.get(applicationId, id);
		if (guarantorDetail == null) {
			throw new NullPointerException("GuarantorDetails Record not exists in DB of ID : " + id);
		}
		GuarantorRequest guaRequest = new GuarantorRequest();
		BeanUtils.copyProperties(guarantorDetail, guaRequest);
		return guaRequest;
	}

	@Override
	public List<GuarantorRequest> getList(Long applicationId) {
		List<GuarantorDetails> details = guarantorDetailsRepository.getList(applicationId);
		List<GuarantorRequest> requests = new ArrayList<>(details.size());
		for (GuarantorDetails detail : details) {
			GuarantorRequest request = new GuarantorRequest();
			BeanUtils.copyProperties(detail, request);
			requests.add(request);
		}
		return requests;
	}

	// private static void copyAddressFromRequestToDomain(RetailApplicantRequest
	// from, RetailApplicantDetail to) {
	// if (from.getFirstAddress() != null) {
	// to.setPermanentPremiseNumberName(from.getFirstAddress().getPremiseNumber());
	// to.setPermanentStreetName(from.getFirstAddress().getStreetName());
	// to.setPermanentLandMark(from.getFirstAddress().getLandMark());
	// to.setPermanentCityId(from.getFirstAddress().getCityId());
	// to.setPermanentStateId(from.getFirstAddress().getStateId());
	// to.setPermanentCountryId(from.getFirstAddress().getCountryId());
	// to.setPermanentPincode(from.getFirstAddress().getPincode());
	// }
	//
	// if (from.isAddressSameAs()) {
	// if (from.getFirstAddress() != null) {
	// to.setOfficePremiseNumberName(from.getFirstAddress().getPremiseNumber());
	// to.setOfficeStreetName(from.getFirstAddress().getStreetName());
	// to.setOfficeLandMark(from.getFirstAddress().getLandMark());
	// to.setOfficeCityId(from.getFirstAddress().getCityId());
	// to.setOfficeStateId(from.getFirstAddress().getStateId());
	// to.setOfficeCountryId(from.getFirstAddress().getCountryId());
	// to.setOfficePincode(from.getFirstAddress().getPincode());
	// }
	// } else {
	// if (from.getSecondAddress() != null) {
	// to.setOfficePremiseNumberName(from.getSecondAddress().getPremiseNumber());
	// to.setOfficeStreetName(from.getSecondAddress().getStreetName());
	// to.setOfficeLandMark(from.getSecondAddress().getLandMark());
	// to.setOfficeCityId(from.getSecondAddress().getCityId());
	// to.setOfficeStateId(from.getSecondAddress().getStateId());
	// to.setOfficeCountryId(from.getSecondAddress().getCountryId());
	// to.setOfficePincode(from.getSecondAddress().getPincode());
	// }
	// }
	//
	// }
	//
	// private static void copyAddressFromDomainToRequest(RetailApplicantDetail
	// from, RetailApplicantRequest to) {
	// Address address = new Address();
	// address.setPremiseNumber(from.getPermanentPremiseNumberName());
	// address.setLandMark(from.getPermanentLandMark());
	// address.setStreetName(from.getAddressStreetName());
	// address.setCityId(from.getPermanentCityId());
	// address.setStateId(from.getPermanentStateId());
	// address.setCountryId(from.getPermanentCountryId());
	// address.setPincode(from.getPermanentPincode());
	// to.setFirstAddress(address);
	// if (from.getAddressSameAs()) {
	// to.setSecondAddress(address);
	// } else {
	// address = new Address();
	// address.setPremiseNumber(from.getOfficePremiseNumberName());
	// address.setLandMark(from.getOfficeLandMark());
	// address.setStreetName(from.getPermanentStreetName());
	// address.setCityId(from.getOfficeCityId());
	// address.setStateId(from.getOfficeStateId());
	// address.setCountryId(from.getOfficeCountryId());
	// address.setPincode(from.getOfficePincode());
	// }
	// }
}
