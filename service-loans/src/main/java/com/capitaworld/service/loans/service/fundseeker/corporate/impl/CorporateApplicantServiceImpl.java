package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.IndustrySectorDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.SubsectorDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.CorporateApplicantRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;

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

	@Override
	public boolean save(CorporateApplicantRequest applicantRequest) {
		CorporateApplicantDetail applicantDetail = null;
		try {
			if (applicantRequest.getApplicationId() != null) {
				applicantDetail = applicantRepository.findOne(applicantRequest.getApplicationId());
				applicantDetail.setId(applicantRequest.getApplicationId());
				updateApplicant(applicantRequest, applicantDetail);
			} else {
				applicantDetail = new CorporateApplicantDetail();
				saveApplicant(applicantRequest, applicantDetail);
			}
			return true;

		} catch (Exception e) {
			logger.info("Exception Throw while json parse in save profile :-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CorporateApplicantDetail getCorporateApplicant(Long corporateApplicantDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	private void updateApplicant(CorporateApplicantRequest applicantRequest, CorporateApplicantDetail applicantDetail) {
		// TODO Auto-generated method stub
		BeanUtils.copyProperties(applicantRequest, applicantDetail);
		// address set
		copyAddressFromRequestToDomain(applicantRequest, applicantDetail);
		applicantDetail.setSameAs(applicantRequest.isSameAs());
		applicantDetail.setCategoryCode(applicantRequest.getCategoryCode());
		applicantDetail.setModifiedBy(applicantDetail.getId());
		applicantDetail.setModifiedDate(new Date());
		applicantDetail = applicantRepository.save(applicantDetail);

		// inactive previous before adding new Data
		int updatedRecords = industrySectorRepository.inActiveMappingByApplicationId(applicantDetail.getId());
		logger.info("updated industrySector ==>" + updatedRecords);

		// industry data save
		saveIndustry(applicantDetail.getId(), applicantRequest.getIndustrylist());
		// Sector data save
		saveSector(applicantDetail.getId(), applicantRequest.getSectorlist());

		// inactive previous before adding new Data
		int subSecors = subSectorRepository.inActiveMappingByApplicationId(applicantDetail.getId());
		logger.info("updated subSector==>" + subSecors);
		
		// sub sector save
		saveSubSector(applicantDetail.getId(), applicantRequest.getSubsectors());
	}

	private void saveApplicant(CorporateApplicantRequest applicantRequest, CorporateApplicantDetail applicantDetail) {
		BeanUtils.copyProperties(applicantRequest, applicantDetail);
		// address set
		copyAddressFromRequestToDomain(applicantRequest, applicantDetail);
		applicantDetail.setSameAs(applicantRequest.isSameAs());
		applicantDetail.setCategoryCode(applicantRequest.getCategoryCode());
		applicantDetail.setCreatedBy(1l);
		applicantDetail.setModifiedBy(1l);
		applicantDetail.setCreatedDate(new Date());
		applicantDetail.setModifiedDate(new Date());
		applicantDetail.setIsActive(true);
		applicantDetail = applicantRepository.save(applicantDetail);

		// industry data save
		saveIndustry(applicantDetail.getId(), applicantRequest.getIndustrylist());

		// Sector data save
		saveSector(applicantDetail.getId(), applicantRequest.getSectorlist());

		// sub sector save
		saveSubSector(applicantDetail.getId(), applicantRequest.getSubsectors());
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

	private void copyAddressFromRequestToDomain(CorporateApplicantRequest from, CorporateApplicantDetail to) {
		// Setting Regsiterd Address
		to.setRegisteredPremiseNumber(from.getRegisteredAddress().getPremiseNumber());
		to.setRegisteredLandMark(from.getRegisteredAddress().getLandMark());
		to.setRegisteredStreetName(from.getRegisteredAddress().getStreetName());
		to.setRegisteredPincode(from.getRegisteredAddress().getPincode());
		to.setRegisteredCityId(from.getRegisteredAddress().getCityId());
		to.setRegisteredStateId(from.getRegisteredAddress().getStateId());
		to.setRegisteredCountryId(from.getRegisteredAddress().getCountryId());

		// Setting Administrative Address
		if (from.isSameAs()) {
			to.setAdministrativePremiseNumber(from.getRegisteredAddress().getPremiseNumber());
			to.setAdministrativeLandMark(from.getRegisteredAddress().getLandMark());
			to.setAdministrativeStreetName(from.getRegisteredAddress().getStreetName());
			to.setAdministrativePincode(from.getRegisteredAddress().getPincode());
			to.setAdministrativeCityId(from.getRegisteredAddress().getCityId());
			to.setAdministrativeStateId(from.getRegisteredAddress().getStateId());
			to.setAdministrativeCountryId(from.getRegisteredAddress().getCountryId());
		} else {
			to.setAdministrativePremiseNumber(from.getAdministrativeAddress().getPremiseNumber());
			to.setAdministrativeLandMark(from.getAdministrativeAddress().getLandMark());
			to.setAdministrativeStreetName(from.getAdministrativeAddress().getStreetName());
			to.setAdministrativePincode(from.getAdministrativeAddress().getPincode());
			to.setAdministrativeCityId(from.getAdministrativeAddress().getCityId());
			to.setAdministrativeStateId(from.getAdministrativeAddress().getStateId());
			to.setAdministrativeCountryId(from.getAdministrativeAddress().getCountryId());
		}
	}

	private void copyAddressFromDomainToRequest(CorporateApplicantDetail from, CorporateApplicantRequest to) {
		// Setting Registered Address
		Address address = new Address();

		address.setPremiseNumber(from.getRegisteredPremiseNumber());
		address.setLandMark(from.getRegisteredLandMark());
		address.setStreetName(from.getRegisteredStreetName());
		address.setPincode(from.getRegisteredPincode());
		address.setCityId(from.getRegisteredCityId());
		address.setStateId(from.getRegisteredStateId());
		address.setCountryId(from.getRegisteredCountryId());
		to.setRegisteredAddress(address);

		// Setting Administrative Address
		if (from.getSameAs() != null && from.getSameAs().booleanValue()) {
			to.setAdministrativeAddress(to.getRegisteredAddress());
		} else {
			address = new Address();
			address.setPremiseNumber(from.getAdministrativePremiseNumber());
			address.setLandMark(from.getAdministrativeLandMark());
			address.setStreetName(from.getAdministrativeStreetName());
			address.setPincode(from.getAdministrativePincode());
			address.setCityId(from.getAdministrativeCityId());
			address.setStateId(from.getAdministrativeStateId());
			address.setCountryId(from.getAdministrativeCountryId());
			to.setAdministrativeAddress(address);
		}

	}
}
