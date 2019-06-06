
package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.loans.config.FPAsyncComponent;
import com.capitaworld.service.loans.domain.fundprovider.HomeLoanModel;
import com.capitaworld.service.loans.domain.fundprovider.HomeLoanModelTemp;
import com.capitaworld.service.loans.domain.fundprovider.RetailModel;
import com.capitaworld.service.loans.domain.fundprovider.RetailModelTemp;
import com.capitaworld.service.loans.model.RetailModelRequest;
import com.capitaworld.service.loans.model.WorkflowData;
import com.capitaworld.service.loans.repository.fundprovider.RetailModelRepository;
import com.capitaworld.service.loans.repository.fundprovider.RetailModelTempRepository;
import com.capitaworld.service.loans.service.fundprovider.HomeLoanModelService;
import com.capitaworld.service.loans.service.fundprovider.RetailModelService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class RetailModelServiceImpl implements RetailModelService {
	private static final Logger logger = LoggerFactory.getLogger(RetailModelServiceImpl.class);

	private static final String SAVE_TO_TEMP_METHOD = "saveToTemp";
	private static final String SAVE = "save";
	
	@Autowired
	private RetailModelTempRepository retailModelTempRepository;

	@Autowired
	private RetailModelRepository retailModelRepository;
	
	@Autowired
	private HomeLoanModelService homeLoanModelService;
	
	@Autowired
	private WorkflowClient workflowClient;
	
	@Autowired
	private FPAsyncComponent fpAsyncComp;

	@Override
	public Boolean save(RetailModelRequest modelRequest) {
		logger.info(CommonUtils.ENTRY_IN + SAVE);
		RetailModel retailModel = retailModelRepository.findById(modelRequest.getId());
		if (retailModel == null) {
			if (CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId().equals(modelRequest.getBusinessTypeId())) {
				retailModel = new HomeLoanModel();
				retailModel.setCreatedBy(modelRequest.getUserId());
				retailModel.setCreatedDate(new Date());
			} else {
				logger.error("Invalid BusinessType Id");
				logger.info(CommonUtils.EXIT_FROM + SAVE + " With Error");
				return false;
			}
		}else {
			retailModel.setModifiedBy(modelRequest.getUserId());
			retailModel.setModifiedDate(new Date());
		}
		BeanUtils.copyProperties(modelRequest, retailModel,"createdBy","createdDate","modifiedBy","modifiedDate","isActive","id","jobId");
		retailModel.setIsActive(true);
		retailModelRepository.save(retailModel);
		fpAsyncComp.sendEmailToAdminMakerWhenPurposeOfLoanApprovedOrRevertBeck(modelRequest.getUserId(), retailModel.getId(), WorkflowUtils.Action.PENDING,false);
		logger.info(CommonUtils.EXIT_FROM + SAVE);
		return true;
	}

	@Override
	public Boolean saveToTemp(RetailModelRequest modelRequest) {
		logger.info(CommonUtils.ENTRY_IN + SAVE_TO_TEMP_METHOD);
		RetailModelTemp retailModelTemp = retailModelTempRepository.findById(modelRequest.getId());
		if (retailModelTemp == null) {
			if (CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId().equals(modelRequest.getBusinessTypeId())) {
				retailModelTemp = new HomeLoanModelTemp();
				retailModelTemp.setCreatedBy(modelRequest.getUserId());
				retailModelTemp.setCreatedDate(new Date());
				retailModelTemp.setIsApproved(false);
				retailModelTemp.setIsCopied(false);
				retailModelTemp.setIsEdit(false);
				retailModelTemp.setStatusId(CommonUtils.Status.OPEN);
			} else {
				logger.error("Invalid BusinessType Id");
				logger.info(CommonUtils.EXIT_FROM + SAVE_TO_TEMP_METHOD + " With Error");
				return false;
			}
		}else {
			retailModelTemp.setStatusId(CommonUtils.Status.MODIFIED);
			retailModelTemp.setModifiedBy(modelRequest.getUserId());
			retailModelTemp.setModifiedDate(new Date());
			retailModelTemp.setIsEdit(true);
		}
		retailModelTemp.setName(modelRequest.getName());
		retailModelTemp.setOrgId(modelRequest.getOrgId());
		retailModelTemp.setBusinessTypeId(modelRequest.getBusinessTypeId());
		retailModelTemp.setUserId(modelRequest.getUserId());
		retailModelTemp.setIsActive(true);
//		BeanUtils.copyProperties(modelRequest, retailModelTemp,"createdBy","createdDate","modifiedBy","modifiedDate","isActive","id","jobId");
		if(retailModelTemp.getJobId() == null) {
			WorkflowResponse workflowResponse = workflowClient.createJobForMasters(WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL, (CommonUtils.isObjectNullOrEmpty(modelRequest.getClientId()) ? modelRequest.getUserId() : modelRequest.getClientId()));
			if(workflowResponse != null){
				retailModelTemp.setJobId(workflowResponse.getData() != null ? Long.valueOf(workflowResponse.getData().toString()) : null);
			}
		}
		retailModelTempRepository.save(retailModelTemp);
		logger.info(CommonUtils.EXIT_FROM + SAVE_TO_TEMP_METHOD);
		return true;
	}

	@Override
	public List<RetailModelRequest> getTemp(Long orgId) {
		List<RetailModelTemp> responses = retailModelTempRepository.findByOrgIdAndIsCopiedAndIsApproved(orgId,false,false);
		if(CommonUtils.isListNullOrEmpty(responses)) {
			return Collections.emptyList();
		}
		List<RetailModelRequest> list = new ArrayList<>(responses.size());
		for(RetailModelTemp modelTemp : responses) {
			RetailModelRequest  modelRequest = new RetailModelRequest();
			BeanUtils.copyProperties(modelTemp, modelRequest);
			list.add(modelRequest);
		}
		return list;
	}

	@Override
	public List<RetailModelRequest> get(Long orgId) {
		List<RetailModel> responses = retailModelRepository.findByOrgId(orgId);
		if(CommonUtils.isListNullOrEmpty(responses)) {
			return Collections.emptyList();
		}
		List<RetailModelRequest> list = new ArrayList<>(responses.size());
		for(RetailModel modelTemp : responses) {
			RetailModelRequest  modelRequest = new RetailModelRequest();
			BeanUtils.copyProperties(modelTemp, modelRequest);
			list.add(modelRequest);
		}
		return list;
	}

	@Override
	public Boolean processWorkflow(WorkflowData workflowData,Integer businessTypeId) {
		WorkflowRequest request = new WorkflowRequest();
		request.setActionId(workflowData.getActionId());
		request.setCurrentStep(workflowData.getWorkflowStep());
		request.setToStep(workflowData.getNextworkflowStep());
		request.setJobId(workflowData.getJobId());
		request.setUserId(workflowData.getUserId());
		WorkflowResponse workflowResponse = null;
		if (WorkflowUtils.Action.SEND_FOR_APPROVAL.equals(workflowData.getActionId())) {
			int updateStatus = retailModelTempRepository.changeStatus(workflowData.getId(), CommonUtils.Status.IN_PROGRESS, false, false, false, null);
			workflowResponse = workflowClient.updateJob(request);
			logger.info("UpdateStatus Count in SEND_FOR_APPROVAL == >{}",updateStatus);
			fpAsyncComp.sendEmailToAdminMakerWhenPurposeOfLoanApprovedOrRevertBeck(workflowData.getUserId(), workflowData.getId(), workflowData.getActionId(),false);
		}else if (WorkflowUtils.Action.APPROVED.equals(workflowData.getActionId())) {
			Boolean result = false;
			if(CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId().equals(businessTypeId)) {
				result = homeLoanModelService.copyTempToMaster(workflowData.getId());
				fpAsyncComp.sendEmailToAdminMakerWhenPurposeOfLoanApprovedOrRevertBeck(workflowData.getUserId(), workflowData.getId(), workflowData.getActionId(),true);
			}
			int updateStatus = retailModelTempRepository.changeStatus(workflowData.getId(), CommonUtils.Status.APPROVED, true, false, true, new Date());
			logger.info("UpdateStatus Count in APPROVED == >{}===Copy Master Result==>{}",updateStatus,result);
			workflowResponse = workflowClient.updateJob(request);
		}else if (WorkflowUtils.Action.SEND_BACK.equals(workflowData.getActionId())) {
			int updateStatus = retailModelTempRepository.changeStatus(workflowData.getId(), CommonUtils.Status.REVERTED, false, false, false, null);
			logger.info("UpdateStatus Count in SEND_BACK== >{}",updateStatus);
			workflowResponse = workflowClient.updateJob(request);
			fpAsyncComp.sendEmailToAdminMakerWhenPurposeOfLoanApprovedOrRevertBeck(workflowData.getUserId(), workflowData.getId(), workflowData.getActionId(),true);
		}
		if (workflowResponse != null && workflowResponse.getStatus() == 200) {
			logger.info("Workflow Job updated Successfully with Job Id = >{}",workflowData.getJobId());
		}
		return true;
	}
}
