package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.capitaworld.service.loans.model.EmploymentDetailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.APIFlags;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

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
	
	@Autowired
	private ConnectClient connectClient;
	
	private static final String SIDBI_AMOUNT = "com.capitaworld.sidbi.amount";
	
	@Autowired
	private Environment environment;

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
				if(!CommonUtils.isObjectNullOrEmpty(detail.getEmploymentDetail())){
					EmploymentDetailRequest employmentDetailRequest = new EmploymentDetailRequest();
					BeanUtils.copyProperties(detail.getEmploymentDetail(),employmentDetailRequest);
					directorBackgroundDetailRequest.setEmploymentDetailRequest(employmentDetailRequest);
				}
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
	public List<DirectorBackgroundDetailRequest> getDirectorBasicDetailsListForNTB(Long applicationId) throws Exception {
		try {
			List<DirectorBackgroundDetail> dirBackDetails = directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(applicationId);
			List<DirectorBackgroundDetailRequest> dirBackDetailReqList = new ArrayList<DirectorBackgroundDetailRequest>();

			DirectorBackgroundDetailRequest dirBackDetailReq = null;
			for (DirectorBackgroundDetail detail : dirBackDetails) {
				dirBackDetailReq = new DirectorBackgroundDetailRequest();
				dirBackDetailReq.setPanNo(detail.getPanNo());
				dirBackDetailReq.setDirectorsName(detail.getDirectorsName());
				dirBackDetailReq.setId(detail.getId());
				dirBackDetailReq.setAmount(environment.getProperty(SIDBI_AMOUNT));
				dirBackDetailReqList.add(dirBackDetailReq);
			}
			return dirBackDetailReqList;
		} catch (Exception e) {
			logger.info("Exception  in getDirectorBasicDetailsListForNTB  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	@Override
	public DirectorBackgroundDetailRequest getDirectorBackgroundDetail(Long id){
		try {
			DirectorBackgroundDetail directorBackgroundDetail = directorBackgroundDetailsRepository.findByIdAndIsActive(id, true);
			if(CommonUtils.isObjectNullOrEmpty(directorBackgroundDetail)) {
				logger.warn("Director Background Details not found For Id ==>{}",id);
				return null;
			}
			DirectorBackgroundDetailRequest directorBackgroundDetailRequest = new DirectorBackgroundDetailRequest();
			BeanUtils.copyProperties(directorBackgroundDetail, directorBackgroundDetailRequest);
			DirectorBackgroundDetailRequest.printFields(directorBackgroundDetailRequest);
			return directorBackgroundDetailRequest;
		} catch (Exception e) {
			logger.info("Exception  in getdirectorBackgroundDetail  :-");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean updateFlag(Long directorId, Integer apiId, Boolean apiFlag,Long userId) {
		// TODO Auto-generated method stub
		logger.info("Enter in updateFlag()");
		APIFlags apiFlagObj = CommonUtils.APIFlags.fromId(apiId);
		if(apiFlag == null) {
			logger.warn("Invalid Flag===>{}",apiId);
			logger.info("Exit in updateFlag()");
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
		logger.info("Exit in updateFlag()");
		return updatedRows > 0;
	}

	@Override
	public Boolean saveDirectors(Long applicationId, Long userId, Integer noOfDirector) {
		logger.info("Enter in saveDirectors()");
		directorBackgroundDetailsRepository.inActive(userId, applicationId);
		if(noOfDirector <= 0) {
			logger.warn("No Of Director Found Less than or Equal 0");
			return false;
		}
		LoanApplicationMaster loanMs = new LoanApplicationMaster(applicationId);
		for(int i = 0; i < noOfDirector; i++) {
			DirectorBackgroundDetail backgroundDetail = new DirectorBackgroundDetail();
			backgroundDetail.setApplicationId(loanMs);
			backgroundDetail.setIsActive(true);
			backgroundDetail.setCreatedBy(userId);
			backgroundDetail.setCreatedDate(new Date());
			backgroundDetail.setIsItrCompleted(false);
			backgroundDetail.setIsCibilCompleted(false);
			backgroundDetail.setIsBankStatementCompleted(false);
			backgroundDetail.setIsOneFormCompleted(false);
			directorBackgroundDetailsRepository.save(backgroundDetail);
		}
		try {
			ConnectResponse connResponse = connectClient.postMCQNTB(applicationId, userId, CommonUtils.BusinessType.NEW_TO_BUSINESS.getId());
			if(!CommonUtils.isObjectNullOrEmpty(connResponse) && !CommonUtils.isObjectNullOrEmpty(connResponse.getProceed()) && connResponse.getProceed()) {
				logger.info("Connect Response--------------------------------> " + connResponse.toString());
				return true;
			} else {
				logger.info("Connect Response--------------------------------> null");
			}
		} catch (Exception e) {
			logger.error("Throw Exception While Call Connect Client");
			e.printStackTrace();
		}
		logger.info("Exit in saveDirectors()");
		return false;
	}
	
	

}
