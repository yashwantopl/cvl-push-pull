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

import com.capitaworld.service.loans.domain.IndustrySectorDetail;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.SubsectorDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.SubSectorListRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SectorIndustryMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class CorporateApplicantServiceImpl implements CorporateApplicantService {
	private static final Logger logger = LoggerFactory.getLogger(CorporateApplicantService.class.getName());
	@Autowired
	private CorporateApplicantDetailRepository applicantRepository;

	@Autowired
	private IndustrySectorRepository industrySectorRepository;

	@Autowired
	private SubSectorRepository subSectorRepository;

	@Autowired
	private SectorIndustryMappingRepository sectorIndustryMappingRepository;

	@Autowired
	private SubSectorMappingRepository subSectorMappingRepository;

	@Override
	public boolean save(CorporateApplicantRequest applicantRequest, Long userId) throws Exception {
		try {
			// application id must not be null

			CorporateApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(userId,
					applicantRequest.getApplicationId());
			if (applicantDetail != null) {
				// throw new NullPointerException("Applicant ID does not match
				// with the database==> Applicant ID==>"
				// + applicantRequest.getApplicationId() + " and User Id==>" +
				// userId);
				applicantDetail.setModifiedBy(userId);
				applicantDetail.setModifiedDate(new Date());
				// inactive previous before adding new Data
				int updatedRecords = industrySectorRepository
						.inActiveMappingByApplicationId(applicantDetail.getApplicationId().getId());
				logger.info("updated industrySector ==>" + updatedRecords);
				// inactive previous before adding new Data
				int subSecors = subSectorRepository
						.inActiveMappingByApplicationId(applicantDetail.getApplicationId().getId());
				logger.info("updated subSector==>" + subSecors);
			} else {
				applicantDetail = new CorporateApplicantDetail();
				applicantDetail.setCreatedBy(userId);
				applicantDetail.setCreatedDate(new Date());
				applicantDetail.setIsActive(true);
				applicantDetail.setApplicationId(new LoanApplicationMaster(applicantRequest.getApplicationId()));
			}

			BeanUtils.copyProperties(applicantRequest, applicantDetail, CommonUtils.IgnorableCopy.ID);
			applicantDetail.setModifiedBy(userId);
			applicantDetail.setModifiedDate(new Date());
			copyAddressFromRequestToDomain(applicantRequest, applicantDetail);
			applicantDetail = applicantRepository.save(applicantDetail);
			// industry data save
			saveIndustry(applicantDetail.getApplicationId().getId(), applicantRequest.getIndustrylist());
			// Sector data save
			saveSector(applicantDetail.getApplicationId().getId(), applicantRequest.getSectorlist());
			// sub sector save
			saveSubSector(applicantDetail.getApplicationId().getId(), applicantRequest.getSubsectors());
			return true;

		} catch (Exception e) {
			logger.error("Error while Saving Corporate Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public CorporateApplicantRequest getCorporateApplicant(Long userId, Long applicationId) throws Exception {
		try {
			// TODO Auto-generated method stub
			CorporateApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(userId,
					applicationId);
			if (applicantDetail == null) {
				throw new NullPointerException(
						"Applicant ID and ID(Primary Key) does not match with the database==> Applicant ID==>"
								+ applicationId + "User ID==>" + userId);
			}
			CorporateApplicantRequest applicantRequest = new CorporateApplicantRequest();
			BeanUtils.copyProperties(applicantDetail, applicantRequest);
			copyAddressFromDomainToRequest(applicantDetail, applicantRequest);
			applicantRequest.setIndustrylist(industrySectorRepository.getIndustryByApplicationId(applicationId));
			applicantRequest.setSectorlist(industrySectorRepository.getSectorByApplicationId(applicationId));
			applicantRequest.setSubsectors(subSectorRepository.getSubSectorByApplicationId(applicationId));
			return applicantRequest;
		} catch (Exception e) {
			logger.error("Error while getting Corporate Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	private void saveIndustry(Long applicationId, List<Long> industrylist) {
		IndustrySectorDetail industrySectorDetail = null;
		for (Long id : industrylist) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setApplicationId(applicationId);
			industrySectorDetail.setIndustryId(id);
			industrySectorDetail.setCreatedBy(applicationId);
			industrySectorDetail.setModifiedBy(applicationId);
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
	}

	private void saveSector(Long applicationId, List<Long> sectorlist) {
		// sector data save
		for (Long id : sectorlist) {
			IndustrySectorDetail industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setApplicationId(applicationId);
			industrySectorDetail.setSectorId(id);
			industrySectorDetail.setCreatedBy(applicationId);
			industrySectorDetail.setModifiedBy(applicationId);
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
	}

	private void saveSubSector(Long applicationId, List<Long> subSectorlist) {
		// sector data save
		for (Long id : subSectorlist) {
			SubsectorDetail subsectorDetail = new SubsectorDetail();
			subsectorDetail.setApplicationId(applicationId);
			subsectorDetail.setSectorSubsectorTransactionId(id);
			subsectorDetail.setCreatedBy(applicationId);
			subsectorDetail.setModifiedBy(applicationId);
			subsectorDetail.setCreatedDate(new Date());
			subsectorDetail.setModifiedDate(new Date());
			subsectorDetail.setIsActive(true);
			// create by and update
			subSectorRepository.save(subsectorDetail);
		}
	}

	private static void copyAddressFromRequestToDomain(CorporateApplicantRequest from, CorporateApplicantDetail to) {
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

	private static void copyAddressFromDomainToRequest(CorporateApplicantDetail from, CorporateApplicantRequest to) {
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

	@Override
	public List<Long> getSectorListByIndustryId(List<Long> industryList) throws Exception {
		// TODO Auto-generated method stub
		return sectorIndustryMappingRepository.getSectorListByIndustryList(industryList);
	}

	@Override
	public List<SubSectorListRequest> getSubSectorList(List<Long> list) {
		// TODO Auto-generated method stub
		List<SubSectorListRequest> subSectorListRequests = new ArrayList<SubSectorListRequest>(list.size());
		for (Long id : list) {
			SubSectorListRequest subSectorListRequest = new SubSectorListRequest();
			if (industrySectorRepository.findOneBySectorId(id) != null)
				subSectorListRequest.setIndustryId(industrySectorRepository.findOneBySectorId(id));

			if (sectorIndustryMappingRepository.findIndustryBySectorId(id) != null)
				subSectorListRequest.setIndustryId(sectorIndustryMappingRepository.findIndustryBySectorId(id));

			subSectorListRequest.setSectorId(id);
			subSectorListRequest.setSubSectorIdList(subSectorMappingRepository.getSectorListByIndustryList(id));
			subSectorListRequests.add(subSectorListRequest);

		}
		return subSectorListRequests;
	}
}
