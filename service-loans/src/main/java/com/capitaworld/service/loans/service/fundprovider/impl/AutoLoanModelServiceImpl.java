
package com.capitaworld.service.loans.service.fundprovider.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.workflow.model.WorkflowJobsTrackerRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.MultipleJSONObjectHelper;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.loans.domain.fundprovider.AutoLoanModel;
import com.capitaworld.service.loans.domain.fundprovider.AutoLoanModelTemp;
import com.capitaworld.service.loans.model.AutoLoanModelRequest;
import com.capitaworld.service.loans.repository.fundprovider.AutoLoanModelRepository;
import com.capitaworld.service.loans.repository.fundprovider.AutoLoanModelTempRepository;
import com.capitaworld.service.loans.service.fundprovider.AutoLoanModelService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class AutoLoanModelServiceImpl implements AutoLoanModelService {
	private static final Logger logger = LoggerFactory.getLogger(AutoLoanModelServiceImpl.class);

	@Autowired
	private AutoLoanModelRepository autoLoanModelRepository; 
	
	@Autowired
	private AutoLoanModelTempRepository autoLoanModelTempRepository; 
	
	@Autowired
	private WorkflowClient workflowClient; 
	
	@Override
	public Boolean save(AutoLoanModelRequest autoLoanModelRequest,Long autoLoanModelTempRefId) {
		AutoLoanModel autoLoanModel = autoLoanModelRepository.findByRetailModelTempRefId(autoLoanModelTempRefId);
		if(autoLoanModel == null) {
			autoLoanModel = new AutoLoanModel();
			autoLoanModel.setCreatedBy(autoLoanModelRequest.getUserId());
			autoLoanModel.setCreatedDate(new Date());
			autoLoanModel.setUserId(autoLoanModelRequest.getUserId());
		}else {
			autoLoanModel.setModifiedBy(autoLoanModelRequest.getUserId());
			autoLoanModel.setModifiedDate(new Date());
		}
		//Purchase
		autoLoanModel.setBusinessTypeId(autoLoanModelRequest.getBusinessTypeId());
		autoLoanModel.setOrgId(autoLoanModelRequest.getOrgId());
		autoLoanModel.setJobId(autoLoanModelRequest.getJobId());
		autoLoanModel.setName(autoLoanModelRequest.getName());
		autoLoanModel.setRetailModelTempRefId(autoLoanModelTempRefId);
		
		autoLoanModel.setIsPersonalUse(autoLoanModelRequest.getIsPersonalUse());
		autoLoanModel.setIsOfficialUse(autoLoanModelRequest.getIsOfficialUse());
		autoLoanModel.setIsSmallCar(autoLoanModelRequest.getIsSmallCar());
		autoLoanModel.setIsMidCar(autoLoanModelRequest.getIsMidCar());
		autoLoanModel.setIsLuxuryCar(autoLoanModelRequest.getIsLuxuryCar());
		autoLoanModel.setIsSuvOrMuv(autoLoanModelRequest.getIsSuvOrMuv());
		autoLoanModel.setIsElectricOrNonConventionalCar(autoLoanModelRequest.getIsElectricOrNonConventionalCar());
		autoLoanModel.setIsTwoWheelerLoan(autoLoanModelRequest.getIsTwoWheelerLoan());
		autoLoanModel.setIsElectricOrNonConventionalTwoWheeler(autoLoanModelRequest.getIsElectricOrNonConventionalTwoWheeler());
		autoLoanModel.setIsSecondHandFourWheelerLoan(autoLoanModelRequest.getIsSecondHandFourWheelerLoan());
		autoLoanModel.setIsSecondHandTwoWheelerLoan(autoLoanModelRequest.getIsSecondHandTwoWheelerLoan());
		
		autoLoanModelRepository.save(autoLoanModel);
		return true;
	}

	@Override
	public Boolean saveToTemp(AutoLoanModelRequest autoLoanModelRequest) {
		AutoLoanModelTemp autoLoanModelTemp = autoLoanModelTempRepository.findByIdAndIsCopiedAndIsApproved(autoLoanModelRequest.getId(),false,false);
		if(autoLoanModelTemp == null) {
			throw new NullPointerException("AutoLoanTemp Object Must not be null at this stage");
		}
		autoLoanModelTemp.setModifiedDate(new Date());
		autoLoanModelTemp.setModifiedBy(autoLoanModelRequest.getUserId());
		autoLoanModelTemp.setStatusId(CommonUtils.Status.MODIFIED);
		
		autoLoanModelTemp.setIsPersonalUse(autoLoanModelRequest.getIsPersonalUse());
		autoLoanModelTemp.setIsOfficialUse(autoLoanModelRequest.getIsOfficialUse());
		autoLoanModelTemp.setIsSmallCar(autoLoanModelRequest.getIsSmallCar());
		autoLoanModelTemp.setIsMidCar(autoLoanModelRequest.getIsMidCar());
		autoLoanModelTemp.setIsLuxuryCar(autoLoanModelRequest.getIsLuxuryCar());
		autoLoanModelTemp.setIsSuvOrMuv(autoLoanModelRequest.getIsSuvOrMuv());
		autoLoanModelTemp.setIsElectricOrNonConventionalCar(autoLoanModelRequest.getIsElectricOrNonConventionalCar());
		autoLoanModelTemp.setIsTwoWheelerLoan(autoLoanModelRequest.getIsTwoWheelerLoan());
		autoLoanModelTemp.setIsElectricOrNonConventionalTwoWheeler(autoLoanModelRequest.getIsElectricOrNonConventionalTwoWheeler());
		autoLoanModelTemp.setIsSecondHandFourWheelerLoan(autoLoanModelRequest.getIsSecondHandFourWheelerLoan());
		autoLoanModelTemp.setIsSecondHandTwoWheelerLoan(autoLoanModelRequest.getIsSecondHandTwoWheelerLoan());
		
		autoLoanModelTempRepository.save(autoLoanModelTemp);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AutoLoanModelRequest getTemp(Long modelId, Long role, Long userId) {
		AutoLoanModelTemp autoLoanModelTemp = autoLoanModelTempRepository.findByIdAndIsCopiedAndIsApproved(modelId,false,false);
		if(autoLoanModelTemp == null) {
			return null;
		}
		AutoLoanModelRequest autoLoanModelRequest = new AutoLoanModelRequest();
		BeanUtils.copyProperties(autoLoanModelTemp, autoLoanModelRequest);
		if(autoLoanModelTemp.getJobId() != null && role != null) {
			 WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(autoLoanModelTemp.getJobId(),Arrays.asList(role), userId);
	            if (!CommonUtils.isObjectNullOrEmpty(workflowResponse) && !CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
	                try {
	                    WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) workflowResponse.getData(), WorkflowJobsTrackerRequest.class);
	                    if (!CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
	                    	autoLoanModelRequest.setWorkflowData(workflowJobsTrackerRequest.getStep().getStepActions());
	                    } else {
	                        logger.info("response from workflow NULL jobId = {} and roleId = {}", autoLoanModelTemp.getJobId(), role);
	                    }
	                } catch (IOException e) {
	                    logger.error("Error While getting data from workflow {}", e);
	                }
	            }
		}
		return autoLoanModelRequest;
	}

	@Override
	public AutoLoanModelRequest get(Long modelId, Long role, Long userId) {
		AutoLoanModel autoLoanModel = autoLoanModelRepository.findById(modelId);
		if(autoLoanModel == null) {
			return null;
		}
		AutoLoanModelRequest autoLoanModelRequest = new AutoLoanModelRequest();
		BeanUtils.copyProperties(autoLoanModel, autoLoanModelRequest);
		return autoLoanModelRequest;
	}

	@Override
	public Boolean copyTempToMaster(Long modelId) {
		AutoLoanModelRequest temp = getTemp(modelId, null, null);
		return save(temp, temp.getId());
	}
	
}
