package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.IndustrySectorDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.SubsectorDetail;
import com.capitaworld.service.loans.model.CorporateApplicantRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;

@Service
public class CorporateApplicantServiceImpl implements CorporateApplicantService {
	private static final Logger logger = LoggerFactory.getLogger(CorporateApplicantService.class.getName());
	@Autowired
	private CorporateApplicantDetailRepository applicantRepository;

	@Autowired
	private IndustrySectorRepository industrySectorRepository;
	
	@Autowired
	private SubSectorRepository subSectorRepository;

	@Override
	public boolean saveOrUpdate(CorporateApplicantRequest applicantRequest) {

		try { 
			CorporateApplicantDetail applicantDetail = new CorporateApplicantDetail();
			BeanUtils.copyProperties(applicantRequest, applicantDetail);
			// address set
			applicantDetail.setRegisteredPremiseNumber(applicantRequest.getRegisteredAddress().getPremiseNumber());
			applicantDetail.setRegisteredLandMark(applicantRequest.getRegisteredAddress().getLandMark());
			applicantDetail.setRegisteredStreetName(applicantRequest.getRegisteredAddress().getStreetName());
			applicantDetail.setRegisteredPincode(applicantRequest.getRegisteredAddress().getPincode());
			applicantDetail.setRegisteredCityId(applicantRequest.getRegisteredAddress().getCityId());
			applicantDetail.setRegisteredStateId(applicantRequest.getRegisteredAddress().getStateId());
			applicantDetail.setRegisteredCountryId(applicantRequest.getRegisteredAddress().getCountryId());

			applicantDetail
					.setAdministrativePremiseNumber(applicantRequest.getAdministrativeAddress().getPremiseNumber());
			applicantDetail.setAdministrativeLandMark(applicantRequest.getAdministrativeAddress().getLandMark());
			applicantDetail.setAdministrativeStreetName(applicantRequest.getAdministrativeAddress().getStreetName());
			applicantDetail.setAdministrativePincode(applicantRequest.getAdministrativeAddress().getPincode());
			applicantDetail.setAdministrativeCityId(applicantRequest.getAdministrativeAddress().getCityId());
			applicantDetail.setAdministrativeStateId(applicantRequest.getAdministrativeAddress().getStateId());
			applicantDetail.setAdministrativeCountryId(applicantRequest.getAdministrativeAddress().getCountryId());
			applicantDetail.setCategoryCode("cat");
			applicantDetail.setCreatedBy(applicantDetail.getId());
			applicantDetail.setModifiedBy(applicantDetail.getId());
			applicantDetail.setCreatedDate(new Date());
			applicantDetail.setModifiedDate(new Date());
			// end address set
			

			applicantDetail = applicantRepository.save(applicantDetail);

			
			//industry data save
			IndustrySectorDetail industrySectorDetail = null;
			for (Long id : applicantRequest.getIndustrylist()) {
				industrySectorDetail = new IndustrySectorDetail();
				industrySectorDetail.setApplicationId(applicantDetail.getId());
				industrySectorDetail.setIndustryId(id);
				industrySectorDetail.setCreatedBy(applicantDetail.getId());
				industrySectorDetail.setModifiedBy(applicantDetail.getId());
				industrySectorDetail.setCreatedDate(new Date());
				industrySectorDetail.setModifiedDate(new Date());
				industrySectorDetail.setIsActive(true);
				// create by and update
				industrySectorRepository.save(industrySectorDetail);
			}
			
			//sector data save
			for (Long id : applicantRequest.getIndustrylist()) {
				industrySectorDetail = new IndustrySectorDetail();
				industrySectorDetail.setApplicationId(applicantDetail.getId());
				industrySectorDetail.setSectorId(id);
				industrySectorDetail.setCreatedBy(applicantDetail.getId());
				industrySectorDetail.setModifiedBy(applicantDetail.getId());
				industrySectorDetail.setCreatedDate(new Date());
				industrySectorDetail.setModifiedDate(new Date());
				industrySectorDetail.setIsActive(true);
				// create by and update
				industrySectorRepository.save(industrySectorDetail);
			}

			//sub sector save
			SubsectorDetail subsectorDetail=null;
			for (Long id: applicantRequest.getSubsectors()) {
				subsectorDetail = new SubsectorDetail();
				subsectorDetail.setApplicationId(applicantDetail.getId());
				subsectorDetail.setSectorSubsectorTransactionId(id);
				subsectorDetail.setCreatedBy(applicantDetail.getId());
				subsectorDetail.setModifiedBy(applicantDetail.getId());
				subsectorDetail.setCreatedDate(new Date());
				subsectorDetail.setModifiedDate(new Date());
				subsectorDetail.setIsActive(true);
				// create by and update
				subSectorRepository.save(subsectorDetail);
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

}
