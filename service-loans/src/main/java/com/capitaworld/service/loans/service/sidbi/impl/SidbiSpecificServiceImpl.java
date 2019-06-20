package com.capitaworld.service.loans.service.sidbi.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.domain.sidbi.SidbiBasicDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.sidbi.SidbiBasicDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.sidbi.BasicDetailRepository;
import com.capitaworld.service.loans.service.sidbi.SidbiSpecificService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class SidbiSpecificServiceImpl implements SidbiSpecificService{

	private static final Logger logger = LoggerFactory.getLogger(SidbiSpecificServiceImpl.class);
	
	@Autowired
	BasicDetailRepository basicDetailRepository;
	
	@Autowired
	CorporateApplicantDetailRepository corporateApplicantDetailRepository;
	
	@Override
	public boolean saveOrUpdateAdditionalData(SidbiBasicDetailRequest sidbiBasicDetailRequest, Long userId) throws LoansException {

		try {
			SidbiBasicDetail sidbiBasicDetail = basicDetailRepository.getByApplicationAndUserId(userId, sidbiBasicDetailRequest.getApplicationId());
			
			if (sidbiBasicDetail == null) {
				sidbiBasicDetail = new SidbiBasicDetail();
				sidbiBasicDetail.setCreatedBy(userId);
				sidbiBasicDetail.setCreatedDate(new Date());
				sidbiBasicDetail.setIsActive(true);
			}
			
			BeanUtils.copyProperties(sidbiBasicDetailRequest, sidbiBasicDetail);

			sidbiBasicDetail.setModifiedBy(userId);
			sidbiBasicDetail.setModifiedDate(new Date());
			basicDetailRepository.save(sidbiBasicDetail);
			return true;
		} catch (Exception e) {
			logger.error("Exception : ", e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public SidbiBasicDetailRequest getAdditionalData(Long applicationId, Long userId) throws LoansException {
		
		SidbiBasicDetailRequest sidbiBasicDetailRequest = null;
		try {
			sidbiBasicDetailRequest = new SidbiBasicDetailRequest();
			SidbiBasicDetail sidbiBasicDetail = basicDetailRepository.getByApplicationAndUserId(userId, applicationId);
			System.out.println("sidbiBasicDetail : "+sidbiBasicDetail);
			if(sidbiBasicDetail != null) {
				BeanUtils.copyProperties(sidbiBasicDetail, sidbiBasicDetailRequest);
			}else {
				sidbiBasicDetailRequest = setAutoFilledValue(applicationId);
			}
			
		} catch (Exception e) {
			logger.error("Exception : ", e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
		
		return sidbiBasicDetailRequest;
	}
	
	public SidbiBasicDetailRequest setAutoFilledValue(Long applicationId) {
		SidbiBasicDetailRequest sidbiBasicDetailRequest = null;
		try {
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.getByApplicationIdAndIsAtive(applicationId);
			if(corporateApplicantDetail != null) {
				sidbiBasicDetailRequest = new SidbiBasicDetailRequest();
				BeanUtils.copyProperties(corporateApplicantDetail, sidbiBasicDetailRequest);
				
				sidbiBasicDetailRequest.setIndustryId(corporateApplicantDetail.getKeyVericalFunding());
				sidbiBasicDetailRequest.setPremiseNumber(corporateApplicantDetail.getAdministrativePremiseNumber());
				sidbiBasicDetailRequest.setStreetName(corporateApplicantDetail.getAdministrativeStreetName());
				sidbiBasicDetailRequest.setLandMark(corporateApplicantDetail.getAdministrativeLandMark());
				sidbiBasicDetailRequest.setPincode(corporateApplicantDetail.getAdministrativePincode());
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sidbiBasicDetailRequest;
	}
}
