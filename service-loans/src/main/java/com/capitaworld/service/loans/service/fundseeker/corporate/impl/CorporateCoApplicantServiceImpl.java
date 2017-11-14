package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

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
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateCoApplicantDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.CorporateCoApplicantRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateCoApplicantRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateCoApplicantService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class CorporateCoApplicantServiceImpl implements CorporateCoApplicantService{

	private static final Logger logger = LoggerFactory.getLogger(CorporateCoApplicantServiceImpl.class.getName());
	
	@Autowired
	private CorporateCoApplicantRepository coApplicantDetailRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Override
	public boolean save(CorporateCoApplicantRequest applicantRequest, Long applicationId, Long userId)
			throws Exception {
		try {
			Long finalUserId = CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId
					: applicantRequest.getClientId();
			CorporateCoApplicantDetail coDetails = coApplicantDetailRepository.get(applicationId, finalUserId,
					applicantRequest.getId());
			if (coDetails != null) {
				if (applicantRequest.getIsActive() != null && !applicantRequest.getIsActive().booleanValue()) {
					coApplicantDetailRepository.inactiveCoApplicant(applicationId, applicantRequest.getId());
					return true;
				}
				coDetails.setModifiedBy(userId);
				coDetails.setModifiedDate(new Date());
			} else {
				coDetails = new CorporateCoApplicantDetail();
				coDetails.setRelationshipWithApplicant(applicantRequest.getRelationshipWithApplicant());
				coDetails.setCreatedBy(userId);
				coDetails.setCreatedDate(new Date());
				coDetails.setApplicationId(new LoanApplicationMaster(applicationId));
			}
			BeanUtils.copyProperties(applicantRequest, coDetails);
			copyAddressFromRequestToDomain(applicantRequest, coDetails);
			
			coApplicantDetailRepository.save(coDetails);

			List<Long> coAppIds = coApplicantDetailRepository.getCoAppIds(applicationId);
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

			// Updating Bowl Count
			loanApplicationRepository.setProfileFilledCount(applicantRequest.getApplicationId(), finalUserId,
					applicantRequest.getDetailsFilledCount());
			return true;

		} catch (Exception e) {
			logger.error("Error while Saving Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public CorporateCoApplicantRequest get(Long userId, Long applicationId, Long id) throws Exception {
		// TODO Auto-generated method stub
		try {
			CorporateCoApplicantDetail applicantDetail = coApplicantDetailRepository.get(applicationId, userId, id);
			if (applicantDetail == null) {
				throw new NullPointerException("CoApplicantDetail Record not exists in DB of ID : " + id
						+ " and ApplicationId==>" + applicationId + " userId ==>" + userId);
			}
			CorporateCoApplicantRequest applicantRequest = new CorporateCoApplicantRequest();
			BeanUtils.copyProperties(applicantDetail, applicantRequest);
			copyAddressFromDomainToRequest(applicantDetail, applicantRequest);
			//copyAddressFromDomainToRequest(applicantDetail, applicantRequest);
			
			//applicantRequest.setCurrencyId(retailApplicantDetailRepository.getCurrency(userId, applicationId));
			applicantRequest.setDetailsFilledCount(applicantDetail.getApplicationId().getDetailsFilledCount());
			return applicantRequest;
		} catch (Exception e) {
			logger.error("Error while getting CoApplicant Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<CorporateCoApplicantRequest> getList(Long applicationId, Long userId) throws Exception {
		// TODO Auto-generated method stub
		try {
			List<CorporateCoApplicantDetail> details = coApplicantDetailRepository.getList(applicationId, userId);
			List<CorporateCoApplicantRequest> requests = new ArrayList<>(details.size());
			for (CorporateCoApplicantDetail detail : details) {
				CorporateCoApplicantRequest request = new CorporateCoApplicantRequest();
				BeanUtils.copyProperties(detail, request);
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
	public List<Long> getCoAppIds(Long applicationId,Long userId) throws Exception {
		// TODO Auto-generated method stub
		try {
			
			//List<CorporateCoApplicantDetail> temp =coApplicantDetailRepository.getCoAppIdstmp(applicationId);
			return coApplicantDetailRepository.getCoAppIds(applicationId);
		} catch (Exception e) {
			logger.error("Error while getCoAppIds:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Long getApplicantIdById(Long id) throws Exception {
		// TODO Auto-generated method stub
		try {
			return coApplicantDetailRepository.getApplicantIdById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error While getting Applicant Id by CoApplicant ID");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	private static void copyAddressFromRequestToDomain(CorporateCoApplicantRequest from, CorporateCoApplicantDetail to) {
		// Setting Regsiterd Address
		if (from.getFirstAddress() != null) {
			to.setRegisteredPremiseNumber(from.getFirstAddress().getPremiseNumber());
			to.setRegisteredLandMark(from.getFirstAddress().getLandMark());
			to.setRegisteredStreetName(from.getFirstAddress().getStreetName());
			to.setRegisteredPincode(from.getFirstAddress().getPincode());
			to.setRegisteredCityId(from.getFirstAddress().getCityId());
			to.setRegisteredStateId(from.getFirstAddress().getStateId());
			to.setRegisteredCountryId(from.getFirstAddress().getCountryId());
		}

		// Setting Administrative Address
		if (from.getSameAs() != null && from.getSameAs().booleanValue()) {
			if (from.getFirstAddress() != null) {
				to.setAdministrativePremiseNumber(from.getFirstAddress().getPremiseNumber());
				to.setAdministrativeLandMark(from.getFirstAddress().getLandMark());
				to.setAdministrativeStreetName(from.getFirstAddress().getStreetName());
				to.setAdministrativePincode(from.getFirstAddress().getPincode());
				to.setAdministrativeCityId(from.getFirstAddress().getCityId());
				to.setAdministrativeStateId(from.getFirstAddress().getStateId());
				to.setAdministrativeCountryId(from.getFirstAddress().getCountryId());
			}
		} else {
			if (from.getSecondAddress() != null) {
				to.setAdministrativePremiseNumber(from.getSecondAddress().getPremiseNumber());
				to.setAdministrativeLandMark(from.getSecondAddress().getLandMark());
				to.setAdministrativeStreetName(from.getSecondAddress().getStreetName());
				to.setAdministrativePincode(from.getSecondAddress().getPincode());
				to.setAdministrativeCityId(from.getSecondAddress().getCityId());
				to.setAdministrativeStateId(from.getSecondAddress().getStateId());
				to.setAdministrativeCountryId(from.getSecondAddress().getCountryId());
			}
		}
	}
	
	private static void copyAddressFromDomainToRequest(CorporateCoApplicantDetail from, CorporateCoApplicantRequest to) {
		// Setting Regsiterd Address
		Address address = new Address();

		address.setPremiseNumber(from.getRegisteredPremiseNumber());
		address.setLandMark(from.getRegisteredLandMark());
		address.setStreetName(from.getRegisteredStreetName());
		address.setPincode(from.getRegisteredPincode());
		address.setCityId(from.getRegisteredCityId());
		address.setStateId(from.getRegisteredStateId());
		address.setCountryId(from.getRegisteredCountryId());
		to.setFirstAddress(address);
		if (from.getSameAs() != null && from.getSameAs()) {
			to.setSecondAddress(address);
		} else {
			address = new Address();
			address.setPremiseNumber(from.getAdministrativePremiseNumber());
			address.setLandMark(from.getAdministrativeLandMark());
			address.setStreetName(from.getAdministrativeStreetName());
			address.setPincode(from.getAdministrativePincode());
			address.setCityId(from.getAdministrativeCityId());
			address.setStateId(from.getAdministrativeStateId());
			address.setCountryId(from.getAdministrativeCountryId());
			to.setSecondAddress(address);

		}

		// Setting Administrative Address
	}

}
