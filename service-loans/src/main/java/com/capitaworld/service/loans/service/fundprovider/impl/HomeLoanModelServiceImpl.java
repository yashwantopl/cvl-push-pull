
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
import com.capitaworld.service.loans.domain.fundprovider.HomeLoanModel;
import com.capitaworld.service.loans.domain.fundprovider.HomeLoanModelTemp;
import com.capitaworld.service.loans.model.HomeLoanModelRequest;
import com.capitaworld.service.loans.repository.fundprovider.HomeLoanModelRepository;
import com.capitaworld.service.loans.repository.fundprovider.HomeLoanModelTempRepository;
import com.capitaworld.service.loans.service.fundprovider.HomeLoanModelService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class HomeLoanModelServiceImpl implements HomeLoanModelService {
	private static final Logger logger = LoggerFactory.getLogger(HomeLoanModelServiceImpl.class);

	@Autowired
	private HomeLoanModelRepository homeLoanModelRepository; 
	
	@Autowired
	private HomeLoanModelTempRepository homeLoanModelTempRepository; 
	
	@Autowired
	private WorkflowClient workflowClient; 
	
	@Override
	public Boolean save(HomeLoanModelRequest homeLoanModelRequest,Long homeLoanModelTempRefId) {
		HomeLoanModel homeLoanModel = homeLoanModelRepository.findByRetailModelTempRefId(homeLoanModelTempRefId);
		if(homeLoanModel == null) {
			homeLoanModel = new HomeLoanModel();
			homeLoanModel.setCreatedBy(homeLoanModelRequest.getUserId());
			homeLoanModel.setCreatedDate(new Date());
			homeLoanModel.setUserId(homeLoanModelRequest.getUserId());
		}else {
			homeLoanModel.setModifiedBy(homeLoanModelRequest.getUserId());
			homeLoanModel.setModifiedDate(new Date());
		}
		//Purchase
		homeLoanModel.setBusinessTypeId(homeLoanModelRequest.getBusinessTypeId());
		homeLoanModel.setOrgId(homeLoanModelRequest.getOrgId());
		homeLoanModel.setJobId(homeLoanModelRequest.getJobId());
		homeLoanModel.setName(homeLoanModelRequest.getName());
		homeLoanModel.setRetailModelTempRefId(homeLoanModelTempRefId);
		homeLoanModel.setIsPurReadyBuiltHouse(homeLoanModelRequest.getIsPurReadyBuiltHouse());
		homeLoanModel.setIsPurReadyBuiltIndependentHouse(homeLoanModelRequest.getIsPurReadyBuiltIndependentHouse());
		homeLoanModel.setIsPurResidetialFlat(homeLoanModelRequest.getIsPurResidetialFlat());
		homeLoanModel.setIsPurResidetialFlatAllotee(homeLoanModelRequest.getIsPurResidetialFlatAllotee());
		homeLoanModel.setIsPurResidetialSite(homeLoanModelRequest.getIsPurResidetialSite());
		
		//Construction
		homeLoanModel.setIsConstruResidetialBuid(homeLoanModelRequest.getIsConstruResidetialBuid());
		homeLoanModel.setIsConstruExpaResBuild(homeLoanModelRequest.getIsConstruExpaResBuild());
		homeLoanModel.setIsConstruPurResSite(homeLoanModelRequest.getIsConstruPurResSite());
		homeLoanModel.setIsRepPurReadyBuiltIndependant(homeLoanModelRequest.getIsRepPurReadyBuiltIndependant());
		homeLoanModel.setIsRepRenImpFlatHouse(homeLoanModelRequest.getIsRepRenImpFlatHouse());
		
		//Others
		homeLoanModel.setIsOthRefExcessMarginPaid(homeLoanModelRequest.getIsOthRefExcessMarginPaid());
		homeLoanModel.setIsOthLoanReimbursementFlat(homeLoanModelRequest.getIsOthLoanReimbursementFlat());
		homeLoanModelRepository.save(homeLoanModel);
		return true;
	}

	@Override
	public Boolean saveToTemp(HomeLoanModelRequest homeLoanModelRequest) {
		HomeLoanModelTemp homeLoanModelTemp = homeLoanModelTempRepository.findByIdAndIsCopiedAndIsApproved(homeLoanModelRequest.getId(),false,false);
		if(homeLoanModelTemp == null) {
			throw new NullPointerException("HomeLoanTemp Object Must not be null at this stage");
		}
		homeLoanModelTemp.setModifiedDate(new Date());
		homeLoanModelTemp.setModifiedBy(homeLoanModelRequest.getUserId());
		homeLoanModelTemp.setStatusId(CommonUtils.Status.MODIFIED);
		
		//Purchase
		homeLoanModelTemp.setIsPurReadyBuiltHouse(homeLoanModelRequest.getIsPurReadyBuiltHouse());
		homeLoanModelTemp.setIsPurReadyBuiltIndependentHouse(homeLoanModelRequest.getIsPurReadyBuiltIndependentHouse());
		homeLoanModelTemp.setIsPurResidetialFlat(homeLoanModelRequest.getIsPurResidetialFlat());
		homeLoanModelTemp.setIsPurResidetialFlatAllotee(homeLoanModelRequest.getIsPurResidetialFlatAllotee());
		homeLoanModelTemp.setIsPurResidetialSite(homeLoanModelRequest.getIsPurResidetialSite());
		
		//Construction
		homeLoanModelTemp.setIsConstruResidetialBuid(homeLoanModelRequest.getIsConstruResidetialBuid());
		homeLoanModelTemp.setIsConstruExpaResBuild(homeLoanModelRequest.getIsConstruExpaResBuild());
		homeLoanModelTemp.setIsConstruPurResSite(homeLoanModelRequest.getIsConstruPurResSite());
		homeLoanModelTemp.setIsRepPurReadyBuiltIndependant(homeLoanModelRequest.getIsRepPurReadyBuiltIndependant());
		homeLoanModelTemp.setIsRepRenImpFlatHouse(homeLoanModelRequest.getIsRepRenImpFlatHouse());
		
		//Others
		homeLoanModelTemp.setIsOthRefExcessMarginPaid(homeLoanModelRequest.getIsOthRefExcessMarginPaid());
		homeLoanModelTemp.setIsOthLoanReimbursementFlat(homeLoanModelRequest.getIsOthLoanReimbursementFlat());
		homeLoanModelTempRepository.save(homeLoanModelTemp);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HomeLoanModelRequest getTemp(Long modelId, Long role, Long userId) {
		HomeLoanModelTemp homeLoanModelTemp = homeLoanModelTempRepository.findByIdAndIsCopiedAndIsApproved(modelId,false,false);
		if(homeLoanModelTemp == null) {
			return null;
		}
		HomeLoanModelRequest homeLoanModelRequest = new HomeLoanModelRequest();
		BeanUtils.copyProperties(homeLoanModelTemp, homeLoanModelRequest);
		if(homeLoanModelTemp.getJobId() != null && role != null) {
			 WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(homeLoanModelTemp.getJobId(),Arrays.asList(role), userId);
	            if (!CommonUtils.isObjectNullOrEmpty(workflowResponse) && !CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
	                try {
	                    WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) workflowResponse.getData(), WorkflowJobsTrackerRequest.class);
	                    if (!CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
	                    	homeLoanModelRequest.setWorkflowData(workflowJobsTrackerRequest.getStep().getStepActions());
	                    } else {
	                        logger.info("response from workflow NULL jobId = {} and roleId = {}", homeLoanModelTemp.getJobId(), role);
	                    }
	                } catch (IOException e) {
	                    logger.error("Error While getting data from workflow {}", e);
	                }
	            }
		}
		return homeLoanModelRequest;
	}

	@Override
	public HomeLoanModelRequest get(Long modelId, Long role, Long userId) {
		HomeLoanModel homeLoanModel = homeLoanModelRepository.findById(modelId);
		if(homeLoanModel == null) {
			return null;
		}
		HomeLoanModelRequest homeLoanModelRequest = new HomeLoanModelRequest();
		BeanUtils.copyProperties(homeLoanModel, homeLoanModelRequest);
		return homeLoanModelRequest;
	}

	@Override
	public Boolean copyTempToMaster(Long modelId) {
		HomeLoanModelRequest temp = getTemp(modelId, null, null);
		return save(temp, temp.getId());
	}
	
}
