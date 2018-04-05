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

import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.model.corporate.AssetsDetailsRequest;
import com.capitaworld.service.loans.model.corporate.CMARequest;
import com.capitaworld.service.loans.model.corporate.LiabilitiesDetailsRequest;
import com.capitaworld.service.loans.model.corporate.OperatingStatementDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CMAService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class CMAServiceImpl implements CMAService {

	private static final Logger logger = LoggerFactory.getLogger(CMAServiceImpl.class);
	
	@Autowired
	private LiabilitiesDetailsRepository liabilitiesDetailsRepository;
	
	@Autowired
	private AssetsDetailsRepository assetsDetailsRepository;
	
	@Autowired 
	private OperatingStatementDetailsRepository operatingStatementDetailsRepository;
	
	@Autowired 
	private LoanApplicationRepository loanApplicationRepository;
	
	public void saveCMA(CMARequest cmaRequest) {
		logger.info("ENTER IN CMA SAVE SERVICE IMPLEMENTATION");
		//SAVE LIABILITY DETAILS BY APPLICATION ID
		try {
			logger.info("TOTAL LIABILITY OBJECT --------------------------------->"+cmaRequest.getLiabilitiesRequestList().size());
			for(LiabilitiesDetailsRequest liabilitiesDetailsRequest : cmaRequest.getLiabilitiesRequestList()) {
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetailsRequest)) {
					logger.info("Current Object is null or Empty in FOR LOOP");
					continue;
				}
				LiabilitiesDetails liabilitiesDetails = null;
				if(!CommonUtils.isObjectNullOrEmpty(liabilitiesDetailsRequest.getId())) {
					liabilitiesDetails = liabilitiesDetailsRepository.findByIdAndIsActive(liabilitiesDetailsRequest.getId(),true);	
				} else {
					liabilitiesDetails = liabilitiesDetailsRepository.getLiabilitiesDetails(liabilitiesDetailsRequest.getApplicationId(), liabilitiesDetailsRequest.getYear());
				}
				if(!CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)) {
					BeanUtils.copyProperties(liabilitiesDetailsRequest, liabilitiesDetails,"id","applicationId","createdBy","createdDate","isActive");
					liabilitiesDetails.setModifiedBy(cmaRequest.getUserId());
					liabilitiesDetails.setModifiedDate(new Date());
				} else {
					liabilitiesDetails = new LiabilitiesDetails();
					BeanUtils.copyProperties(liabilitiesDetailsRequest, liabilitiesDetails);
					liabilitiesDetails.setFsLoanApplicationMaster(loanApplicationRepository.findOne(liabilitiesDetailsRequest.getApplicationId()));
					liabilitiesDetails.setCreatedBy(cmaRequest.getUserId());
					liabilitiesDetails.setCreatedDate(new Date());
					liabilitiesDetails.setIsActive(true);
				}
				liabilitiesDetailsRepository.save(liabilitiesDetails);
				
			}	
		} catch (Exception e) {
			logger.info("THROW EXCEPTION WHILE SAVE LIABILITY DETAILS");
			e.printStackTrace();
		}
		
		
		
		//SAVE ASSET DETAILS BY APPLICATION ID
		try {
			logger.info("TOTAL ASSET OBJECT --------------------------------->"+cmaRequest.getAssetsRequestList().size());
			for(AssetsDetailsRequest assetsDetailsRequest : cmaRequest.getAssetsRequestList()) {
				AssetsDetails assetsDetails = null;
				if(!CommonUtils.isObjectNullOrEmpty(assetsDetailsRequest.getId())) {
					assetsDetails = assetsDetailsRepository.findByIdAndIsActive(assetsDetailsRequest.getId(),true);	
				} else {
					assetsDetails = assetsDetailsRepository.getAssetsDetails(assetsDetailsRequest.getApplicationId(), assetsDetailsRequest.getYear());
				}
				if(!CommonUtils.isObjectNullOrEmpty(assetsDetails)) {
					BeanUtils.copyProperties(assetsDetailsRequest, assetsDetails,"id","applicationId","createdBy","createdDate","isActive");
					assetsDetails.setModifiedBy(cmaRequest.getUserId());
					assetsDetails.setModifiedDate(new Date());
				} else {
					assetsDetails = new AssetsDetails();
					BeanUtils.copyProperties(assetsDetailsRequest, assetsDetails);
					assetsDetails.setLoanApplicationMaster(loanApplicationRepository.findOne(assetsDetailsRequest.getApplicationId()));
					assetsDetails.setCreatedBy(cmaRequest.getUserId());
					assetsDetails.setCreatedDate(new Date());
					assetsDetails.setIsActive(true);
				}
				assetsDetailsRepository.save(assetsDetails);
			}	
		} catch (Exception e) {
			logger.info("THROW EXCEPTION WHILE SAVE ASSET DETAILS DETAILS");
			e.printStackTrace();
		}
		
		
		//SAVE OPERATING STATEMENT DETAILS BY APPLICATION ID
		try {
			logger.info("TOTAL OPEATING STATEMENT OBJECT --------------------------------->"+cmaRequest.getOperatingStatementRequestList().size());
			for(OperatingStatementDetailsRequest operatingStatementDetailsRequest : cmaRequest.getOperatingStatementRequestList()) {
				OperatingStatementDetails operatingStatementDetails = null;
				if(!CommonUtils.isObjectNullOrEmpty(operatingStatementDetailsRequest.getId())) {
					operatingStatementDetails = operatingStatementDetailsRepository.findByIdAndIsActive(operatingStatementDetailsRequest.getId(),true);	
				} else {
					operatingStatementDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(operatingStatementDetailsRequest.getApplicationId(), operatingStatementDetailsRequest.getYear());
				}
				if(!CommonUtils.isObjectNullOrEmpty(operatingStatementDetails)) {
					BeanUtils.copyProperties(operatingStatementDetailsRequest, operatingStatementDetails,"id","applicationId","createdBy","createdDate","isActive");
					operatingStatementDetails.setModifiedBy(cmaRequest.getUserId());
					operatingStatementDetails.setModifiedDate(new Date());
				} else {
					operatingStatementDetails = new OperatingStatementDetails();
					BeanUtils.copyProperties(operatingStatementDetailsRequest, operatingStatementDetails);
					operatingStatementDetails.setLoanApplicationMaster(loanApplicationRepository.findOne(operatingStatementDetailsRequest.getApplicationId()));
					operatingStatementDetails.setCreatedBy(cmaRequest.getUserId());
					operatingStatementDetails.setCreatedDate(new Date());
					operatingStatementDetails.setIsActive(true);
				}
				operatingStatementDetailsRepository.save(operatingStatementDetails);
			}
		} catch (Exception e) {
			logger.info("THROW EXCEPTION WHILE SAVE OPERATING STATEMENT DETAILS DETAILS");
			e.printStackTrace();
		}
		logger.info("CMA DETAILS SAVED SUCCESSFULLY");
		
	}
	
	public CMARequest getCMA(Long applicationId) {
		
		CMARequest cmaRequest = new CMARequest();
		
		//GET LIABILITY DETAILS BY APPLICATION ID
		List<LiabilitiesDetails> liabilitiesDetailList = liabilitiesDetailsRepository.getByApplicationId(applicationId);
		List<LiabilitiesDetailsRequest> liabilitiesRequestsList = new ArrayList<>(liabilitiesDetailList.size());
		LiabilitiesDetailsRequest liabilitiesRequest = null;
		for(LiabilitiesDetails liabilitiesDetails : liabilitiesDetailList) {
			liabilitiesRequest = new LiabilitiesDetailsRequest();
			BeanUtils.copyProperties(liabilitiesDetails, liabilitiesRequest);
			liabilitiesRequest.setApplicationId(liabilitiesDetails.getFsLoanApplicationMaster().getId());
			liabilitiesRequestsList.add(liabilitiesRequest);
		}
		cmaRequest.setLiabilitiesRequestList(liabilitiesRequestsList);
		
		//GET ASSET DETAILS BY APPLICATION ID
		List<AssetsDetails> assetsDetailsList = assetsDetailsRepository.getByApplicationId(applicationId);
		List<AssetsDetailsRequest> assetsRequestsList = new ArrayList<>(assetsDetailsList.size());
		AssetsDetailsRequest assetsDetailsRequest = null;
		for(AssetsDetails assetsDetails : assetsDetailsList) {
			assetsDetailsRequest = new AssetsDetailsRequest();
			BeanUtils.copyProperties(assetsDetails, assetsDetailsRequest);
			assetsDetailsRequest.setApplicationId(assetsDetails.getLoanApplicationMaster().getId());
			assetsRequestsList.add(assetsDetailsRequest);
		}
		cmaRequest.setAssetsRequestList(assetsRequestsList);
		
		//GET OPERATING STATEMENT DETAILS BY APPLICATION ID
		List<OperatingStatementDetails> operatingStatementList = operatingStatementDetailsRepository.getByApplicationId(applicationId);
		List<OperatingStatementDetailsRequest> operatingStatementRequestList = new ArrayList<>(operatingStatementList.size());
		OperatingStatementDetailsRequest operatingStatementRequest = null;
		for(OperatingStatementDetails operatingStatement : operatingStatementList) {
			operatingStatementRequest = new OperatingStatementDetailsRequest();
			BeanUtils.copyProperties(operatingStatement, operatingStatementRequest);
			operatingStatementRequest.setApplicationId(operatingStatement.getLoanApplicationMaster().getId());
			operatingStatementRequestList.add(operatingStatementRequest);
		}
		cmaRequest.setOperatingStatementRequestList(operatingStatementRequestList);
		
		return cmaRequest;
		
	}
	
}
