package com.capitaworld.service.loans.service.sidbi.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.sidbi.SidbiBasicDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.sidbi.SidbiBasicDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.sidbi.BasicDetailRepository;
import com.capitaworld.service.loans.service.sidbi.SidbiSpecificService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UserTypeRequest;
import com.capitaworld.service.users.model.UsersRequest;

@Service
@Transactional
public class SidbiSpecificServiceImpl implements SidbiSpecificService{

	private static final Logger logger = LoggerFactory.getLogger(SidbiSpecificServiceImpl.class);
	
	@Autowired
	BasicDetailRepository basicDetailRepository;
	
	@Autowired
	CorporateApplicantDetailRepository corporateApplicantDetailRepository;
	
	@Autowired
	UsersClient usersClient;
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
			if(sidbiBasicDetail != null) {
				BeanUtils.copyProperties(sidbiBasicDetail, sidbiBasicDetailRequest);
			}else {
				sidbiBasicDetailRequest = setAutoFilledValue(applicationId,userId);
			}
			
		} catch (Exception e) {
			logger.error("Exception : ", e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
		
		return sidbiBasicDetailRequest;
	}
	
	public SidbiBasicDetailRequest setAutoFilledValue(Long applicationId, Long userId) {
		SidbiBasicDetailRequest sidbiBasicDetailRequest = null;
		try {
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.getByApplicationIdAndIsAtive(applicationId);
			if(corporateApplicantDetail != null) {
				sidbiBasicDetailRequest = new SidbiBasicDetailRequest();
				BeanUtils.copyProperties(corporateApplicantDetail, sidbiBasicDetailRequest);
				
				sidbiBasicDetailRequest.setIndustryId(corporateApplicantDetail.getKeyVericalFunding());
				sidbiBasicDetailRequest.setPremiseNumber(corporateApplicantDetail.getRegisteredPremiseNumber());
				sidbiBasicDetailRequest.setStreetName(corporateApplicantDetail.getRegisteredStreetName());
				sidbiBasicDetailRequest.setLandMark(corporateApplicantDetail.getRegisteredLandMark());
				sidbiBasicDetailRequest.setPincode(corporateApplicantDetail.getRegisteredPincode());
				sidbiBasicDetailRequest.setMsmeRegistrationNumber(corporateApplicantDetail.getMsmeRegistrationNumber());
				sidbiBasicDetailRequest.setAadhar(corporateApplicantDetail.getAadhar());
				
				
				if(corporateApplicantDetail.getEstablishmentMonth()!=null && corporateApplicantDetail.getEstablishmentYear()!=null) {
					String str="01-"+corporateApplicantDetail.getEstablishmentMonth()+"-"+corporateApplicantDetail.getEstablishmentYear();
					Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(str);  
					sidbiBasicDetailRequest.setEstablishmentDate(date1);
				}
				
			}
			UserResponse userResponse=usersClient.getUserBasicDetails(userId);
			if(!CommonUtils.isObjectNullOrEmpty(userResponse)) {
				UsersRequest usersRequest =MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
				if(!CommonUtils.isObjectNullOrEmpty(usersRequest)) {
					if(sidbiBasicDetailRequest==null) {
						sidbiBasicDetailRequest = new SidbiBasicDetailRequest();
					}
					sidbiBasicDetailRequest.setMobile(usersRequest.getMobile());
					sidbiBasicDetailRequest.setEmail(usersRequest.getEmail());
				}
			}
			
			
		} catch (Exception e) {
			logger.error("Exception while calling setAutoFilledValue :: ",e);
		}
		return sidbiBasicDetailRequest;
	}
}
