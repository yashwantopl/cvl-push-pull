package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.utils.CommonUtils.APIFlags;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class DirectorBackgroundDetailsServiceImpl implements DirectorBackgroundDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(DirectorBackgroundDetailsServiceImpl.class);

	@Autowired
	private DirectorBackgroundDetailsRepository directorBackgroundDetailsRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception {
		
		if(!CommonUtils.isObjectNullOrEmpty(frameRequest)) {
			if(!CommonUtils.isObjectNullOrEmpty(frameRequest.getIsFromClient())) {
				if(frameRequest.getIsFromClient()) {
					directorBackgroundDetailsRepository.inActive(frameRequest.getUserId(), frameRequest.getApplicationId());	
				}
			}
		}
		
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				DirectorBackgroundDetailRequest directorBackgroundDetailRequest= (DirectorBackgroundDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, DirectorBackgroundDetailRequest.class);
				DirectorBackgroundDetail  directorBackgroundDetail= null;
				if (directorBackgroundDetailRequest.getId() != null) {
					directorBackgroundDetail = directorBackgroundDetailsRepository
							.findOne(directorBackgroundDetailRequest.getId());
				} else {
					directorBackgroundDetail = new DirectorBackgroundDetail();
					directorBackgroundDetail.setCreatedBy(frameRequest.getUserId());
					directorBackgroundDetail.setCreatedDate(new Date());
				}
				BeanUtils.copyProperties(directorBackgroundDetailRequest, directorBackgroundDetail, "applicationId");
				directorBackgroundDetail.setApplicationId(new LoanApplicationMaster(frameRequest.getApplicationId()));
				directorBackgroundDetail.setModifiedBy(frameRequest.getUserId());
				directorBackgroundDetail.setModifiedDate(new Date());
				directorBackgroundDetailsRepository.save(directorBackgroundDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save directorBackgroundDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<DirectorBackgroundDetailRequest> getDirectorBackgroundDetailList(Long applicationId,Long userId) throws Exception {
		try {
			List<DirectorBackgroundDetail> directorBackgroundDetails = null;
			if(userId != null) {
				directorBackgroundDetails = directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(applicationId,userId);	
			}else {
				directorBackgroundDetails = directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(applicationId);
			}
			List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequests = new ArrayList<DirectorBackgroundDetailRequest>();

			for (DirectorBackgroundDetail detail : directorBackgroundDetails) {
				DirectorBackgroundDetailRequest directorBackgroundDetailRequest = new DirectorBackgroundDetailRequest();
				BeanUtils.copyProperties(detail, directorBackgroundDetailRequest);
				DirectorBackgroundDetailRequest.printFields(directorBackgroundDetailRequest);
				directorBackgroundDetailRequests.add(directorBackgroundDetailRequest);
			}
			
			return directorBackgroundDetailRequests;
		} catch (Exception e) {
			logger.info("Exception  in getdirectorBackgroundDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean updateFlag(Long directorId, Integer apiId, Boolean apiFlag,Long userId) {
		// TODO Auto-generated method stub
		logger.info("Enter in updateFlag()");
		APIFlags apiFlagObj = CommonUtils.APIFlags.fromId(apiId);
		if(apiFlag == null) {
			logger.warn("Invalid Flag===>{}",apiId);
			return false;
		}
		int updatedRows = 0;
		switch (apiFlagObj) {
		case ITR:
			updatedRows = directorBackgroundDetailsRepository.updateITRFlag(userId, directorId, apiFlag);
			break;
		case CIBIL:
			updatedRows = directorBackgroundDetailsRepository.updateCIBILFlag(userId, directorId, apiFlag);
			break;
		case BANK_STATEMENT:
			updatedRows = directorBackgroundDetailsRepository.updateBankStatementFlag(userId, directorId, apiFlag);
			break;
		case ONE_FORM:
			updatedRows = directorBackgroundDetailsRepository.updateOneFormFlag(userId, directorId, apiFlag);
			break;

		default:
			break;
		}
		logger.info("updatedRows====>{}",updatedRows);
		return updatedRows > 0;
	}

}
