package com.capitaworld.service.loans.service.fundprovider.impl;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.workflow.model.WorkflowJobsTrackerRequest;
import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.MultipleJSONObjectHelper;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.loans.domain.fundprovider.ProductMasterTemp;
import com.capitaworld.service.loans.domain.fundprovider.UniformProductParamter;
import com.capitaworld.service.loans.domain.fundprovider.UniformProductParamterTemp;
import com.capitaworld.service.loans.model.WorkflowData;
import com.capitaworld.service.loans.model.corporate.UniformProductParamterRequest;
import com.capitaworld.service.loans.repository.fundprovider.UniformProductParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.UniformProductParameterTempRepository;
import com.capitaworld.service.loans.service.fundprovider.UniformProductParameterService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;

@Service
public class UniformProductParameterServiceImpl implements UniformProductParameterService {
	private static final Logger logger = LoggerFactory.getLogger(UniformProductParameterServiceImpl.class);

	@Autowired
	private UniformProductParameterRepository uniformProductParameterRepository;

	@Autowired
	private UniformProductParameterTempRepository uniformProductParameterTempRepository;

	@Autowired
	private WorkflowClient workflowClient;

	@Override
	@Transactional
	public Boolean saveOrUpdate(UniformProductParamterRequest productParamterRequest) {
		UniformProductParamter uniformProductParamter = uniformProductParameterRepository
				.findFirstByUserOrgIdOrderByIdDesc(productParamterRequest.getUserOrgId());
		if (CommonUtils.isObjectNullOrEmpty(uniformProductParamter)) {
			uniformProductParamter = new UniformProductParamter();
			uniformProductParamter.setCreatedBy(productParamterRequest.getUserId());
			uniformProductParamter.setCreatedDate(new Date());
		} else {
			uniformProductParamter.setModifiedBy(productParamterRequest.getUserId());
			uniformProductParamter.setModifiedDate(new Date());
		}
		BeanUtils.copyProperties(productParamterRequest, uniformProductParamter, "id", "createdBy", "createdDate",
				"modifiedDate", "modifiedBy");
		uniformProductParamter.setUserOrgId(productParamterRequest.getUserOrgId());
		uniformProductParamter.setBusinessTypeId(
				CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId().longValue());
		uniformProductParamter.setUserId(productParamterRequest.getUserId());
		uniformProductParamter.setName("Uniform Product");
		uniformProductParamter.setProductId(LoanType.WORKING_CAPITAL.getValue());
		uniformProductParamter.setActionFor(productParamterRequest.getActionFor());
		if (!CommonUtils.isObjectNullOrEmpty(uniformProductParamter.getVersion())) {
			uniformProductParamter.setVersion(uniformProductParamter.getVersion() + 1);
		} else {
			uniformProductParamter.setVersion(1);
		}
		uniformProductParamter.setIsActive(true);
		uniformProductParameterRepository.save(uniformProductParamter);
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public UniformProductParamterRequest get(Long userId, Long orgId) {
		UniformProductParamter uniformProductParamter = uniformProductParameterRepository
				.findFirstByUserOrgIdOrderByIdDesc(orgId);
		if (CommonUtils.isObjectNullOrEmpty(uniformProductParamter)) {
			return null;
		}
		UniformProductParamterRequest paramterRequest = new UniformProductParamterRequest();
		BeanUtils.copyProperties(uniformProductParamter, paramterRequest);
		return paramterRequest;
	}

	@Override
	@Transactional
	public Boolean saveOrUpdateTemp(UniformProductParamterRequest productParamterRequest) {
		UniformProductParamterTemp uniformProductParamter = uniformProductParameterTempRepository
				.findFirstByUserOrgIdOrderByIdDesc(productParamterRequest.getUserOrgId());
		if (CommonUtils.isObjectNullOrEmpty(uniformProductParamter)) {
			uniformProductParamter = new UniformProductParamterTemp();
			uniformProductParamter.setCreatedBy(productParamterRequest.getUserId());
			uniformProductParamter.setCreatedDate(new Date());
		} else {
			uniformProductParamter.setModifiedBy(productParamterRequest.getUserId());
			uniformProductParamter.setModifiedDate(new Date());
		}
		BeanUtils.copyProperties(productParamterRequest, uniformProductParamter, "id", "createdBy", "createdDate",
				"modifiedDate", "modifiedBy", "jobId");
		uniformProductParamter.setUserOrgId(productParamterRequest.getUserOrgId());
		uniformProductParamter.setBusinessTypeId(
				CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId().longValue());
		uniformProductParamter.setUserId(productParamterRequest.getUserId());
		uniformProductParamter.setName("Uniform Product");
		uniformProductParamter.setProductId(LoanType.WORKING_CAPITAL.getValue());
		WorkflowResponse workflowResponse = null;
		if (CommonUtils.isObjectNullOrEmpty(productParamterRequest.getJobId())) {
			workflowResponse = workflowClient.createJobForMasters(WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS,
					WorkflowUtils.Action.SEND_FOR_APPROVAL, productParamterRequest.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
				Long jobId = ((Integer) workflowResponse.getData()).longValue();
				productParamterRequest.setJobId(jobId);
			}
		} else {
			if (!CommonUtils.isObjectNullOrEmpty(productParamterRequest.getWorkflowRequest())) {
				updateJob(productParamterRequest);
			}
		}
		uniformProductParamter.setJobId(productParamterRequest.getJobId());
		uniformProductParamter.setIsActive(true);
		uniformProductParameterTempRepository.save(uniformProductParamter);
		return true;
	}
	
	public void copyObjectProperties(){
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public UniformProductParamterRequest getTempObj(Long userId, Long orgId, List<Long> roleIds) {
		UniformProductParamterTemp uniformProductParamter = uniformProductParameterTempRepository
				.findFirstByUserOrgIdOrderByIdDesc(orgId);
		if (CommonUtils.isObjectNullOrEmpty(uniformProductParamter)) {
			return null;
		}
		UniformProductParamterRequest paramterRequest = new UniformProductParamterRequest();
		BeanUtils.copyProperties(uniformProductParamter, paramterRequest);
		if (!CommonUtils.isObjectNullOrEmpty(uniformProductParamter.getJobId())
				&& !CommonUtils.isListNullOrEmpty(roleIds)) {
			WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(uniformProductParamter.getJobId(),
					roleIds, userId);
			if (!CommonUtils.isObjectNullOrEmpty(workflowResponse)
					&& !CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
				try {
					WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) workflowResponse.getData(),
							WorkflowJobsTrackerRequest.class);
					if (!CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !CommonUtils
							.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
						paramterRequest.setWorkflowData(workflowJobsTrackerRequest.getStep().getStepActions());
					} else {
						logger.info("response from workflow NULL jobId = {} and roleId = {}",
								uniformProductParamter.getJobId(), roleIds);
					}
				} catch (IOException e) {
					logger.error("Error While getting data from workflow {}", e);
				}
			}
		} else {
			logger.info("you set jobId or list of roleId NULL for calling workflow");
		}
		return paramterRequest;
	}

	private void updateJob(UniformProductParamterRequest paramterRequest) {
		try {
			WorkflowData workflowData = paramterRequest.getWorkflowRequest();
			WorkflowRequest request = new WorkflowRequest();
			request.setActionId(workflowData.getActionId());
			request.setCurrentStep(workflowData.getWorkflowStep());
			request.setToStep(workflowData.getNextworkflowStep());
			request.setJobId(paramterRequest.getJobId());
			request.setUserId(paramterRequest.getUserId());
			request.setRemarks(workflowData.getActionFor());
			if (workflowData.getActionId() == WorkflowUtils.Action.SEND_FOR_APPROVAL) {
				WorkflowResponse workflowResponse = workflowClient.updateJob(request);
				if (workflowResponse.getStatus() == 200) {
					logger.info("Successfully Updated Status to Send for Approval on Job Id ===> {}", workflowData.getJobId());
					return;
				} else {
					logger.warn("could not updated in Uniform Product Paramters temp for Send for Approval ===> {}", workflowData.getJobId());
					return;
				}
			} else if (workflowData.getActionId() == WorkflowUtils.Action.APPROVED) {
				Boolean saveOrUpdate = saveOrUpdate(paramterRequest);
				if(saveOrUpdate){
					WorkflowResponse workflowResponse = workflowClient.updateJob(request);
					if (workflowResponse.getStatus() == 200) {
						logger.info("Successfully Updated Status to Approved on Job Id ===> {}", workflowData.getJobId());
						return;
					} else {
						logger.warn("could not updated in Uniform Product Paramters temp for Approval ===> {}", workflowData.getJobId());
						return;
					}					
				}else{
					logger.warn("Something Went wrong while updating Master Information on Approval of uniform Product ===> {}", workflowData.getJobId());
				}				
			} else if (workflowData.getActionId() == WorkflowUtils.Action.SEND_BACK) {
				WorkflowResponse workflowResponse = workflowClient.updateJob(request);
				if (workflowResponse.getStatus() == 200) {
					logger.info("Successfully Updated Status to Send back on Job Id ===> {}", workflowData.getJobId());
					return;
				} else {
					logger.warn("could not updated in Uniform Product Paramters temp for Sending Back ===> {}", workflowData.getJobId());
					return;
				}
			}
			return;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
			return;
		}
	}
}
