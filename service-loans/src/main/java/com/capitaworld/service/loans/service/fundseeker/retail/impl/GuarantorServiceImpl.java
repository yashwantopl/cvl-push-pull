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
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.retail.GuarantorRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.utils.CommonUtils;

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
			copyAddressFromRequestToDomain(guarantorRequest, guarantorDetails);
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
		copyAddressFromDomainToRequest(guarantorDetail, guaRequest);
		return guaRequest;
	}

	@Override
	public List<GuarantorRequest> getList(Long applicationId) {
		List<GuarantorDetails> details = guarantorDetailsRepository.getList(applicationId);
		List<GuarantorRequest> requests = new ArrayList<>(details.size());
		for (GuarantorDetails detail : details) {
			GuarantorRequest request = new GuarantorRequest();
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
			GuarantorDetails guaDetails = guarantorDetailsRepository.get(applicantRequest.getApplicationId(),
					applicantRequest.getId());
			if (guaDetails == null) {
				throw new NullPointerException("Guarantor Id Record not exists in DB ID: " + applicantRequest.getId()
						+ " and Application Id==>" + applicantRequest.getApplicationId());
			}
			guaDetails.setModifiedBy(applicantRequest.getUserId());
			guaDetails.setModifiedDate(new Date());
			BeanUtils.copyProperties(applicantRequest, guaDetails, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
			guarantorDetailsRepository.save(guaDetails);
			return true;

		} catch (Exception e) {
			logger.info("Exception Throw while Saving CoApplicant Profile:-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public FinalCommonRetailRequest getFinal(Long id, Long applicationId) {
		GuarantorDetails guaDetail = guarantorDetailsRepository.get(applicationId, id);
		if (guaDetail == null) {
			throw new NullPointerException("GuarantorDetails Record of Final Portion not exists in DB of ID : " + id
					+ " and Application Id ==>" + applicationId);
		}
		FinalCommonRetailRequest applicantRequest = new FinalCommonRetailRequest();
		BeanUtils.copyProperties(guaDetail, applicantRequest, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
		return applicantRequest;
	}

	public static void copyAddressFromRequestToDomain(GuarantorRequest from, GuarantorDetails to) {
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

	public static void copyAddressFromDomainToRequest(GuarantorDetails from, GuarantorRequest to) {
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
