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
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.loans.domain.fundprovider.AgriLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.AgriLoanParameterTemp;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.AgriLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.AgriLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.AgriLoanParameterTempRepository;
import com.capitaworld.service.loans.service.fundprovider.AgriLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.FPParameterMappingService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Transactional
@Service
public class AgriLoanParameterServiceImpl implements AgriLoanParameterService {

	private static final Logger logger = LoggerFactory.getLogger(AgriLoanParameterServiceImpl.class);

	private static final String ERROR_WHILE_GET_HOME_LOAN_PARAMETER_REQUEST_MSG = "error while getHomeLoanParameterRequest : ";

	@Autowired
	private AgriLoanParameterRepository agriLoanParameterRepository;
	
	@Autowired
	private AgriLoanParameterTempRepository agriLoanParameterTempRepository;

	@Autowired
	private FPParameterMappingService fPParameterMappingService;

	@Autowired
	private WorkflowClient workflowClient;

	@Override
	public boolean saveOrUpdate(AgriLoanParameterRequest agriLoanParameterRequest) {
		AgriLoanParameterTemp loanParameter = agriLoanParameterTempRepository.findById(agriLoanParameterRequest.getId());

		AgriLoanParameter agriLoanParameter = null;
		if (loanParameter.getFpProductMappingId() != null) {
			agriLoanParameter = agriLoanParameterRepository.findOne(loanParameter.getFpProductMappingId());
		}
		if (agriLoanParameter == null) {
			agriLoanParameter = new AgriLoanParameter();
		}
		loanParameter.setStatusId(CommonUtils.Status.APPROVED);
		loanParameter.setIsDeleted(false);
		loanParameter.setIsEdit(false);
		loanParameter.setIsCopied(true);
		loanParameter.setIsApproved(true);
		loanParameter.setApprovalDate(new Date());
		loanParameter.setFpProductMappingId(agriLoanParameter.getId());
		agriLoanParameterTempRepository.save(loanParameter);
		
		
		BeanUtils.copyProperties(agriLoanParameterRequest, agriLoanParameter, CommonUtils.IgnorableCopy.getFpProduct());
		agriLoanParameter.setIsParameterFilled(true);
		agriLoanParameter.setProductId(agriLoanParameterRequest.getProductId());
		agriLoanParameter.setUserId(agriLoanParameterRequest.getUserId());
		agriLoanParameter.setModifiedBy(agriLoanParameterRequest.getUserId());
		agriLoanParameter.setIsParameterFilled(true);
		agriLoanParameter.setModifiedDate(new Date());
		agriLoanParameter = agriLoanParameterRepository.save(agriLoanParameter);
		agriLoanParameterRequest.setId(agriLoanParameter.getId());
		
		// Saving Mapping Current DISTRICT
		fPParameterMappingService.inactiveAndSave(agriLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.DISTRICT,
				agriLoanParameterRequest.getDistrictIds());

		// Saving Mapping Current CROP
		fPParameterMappingService.inactiveAndSave(agriLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.CROP, agriLoanParameterRequest.getCropIds());

		// Saving Mapping Current IRRIGATED_UNIRRIGATED
		fPParameterMappingService.inactiveAndSave(agriLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.IRRIGATED_UNIRRIGATED, agriLoanParameterRequest.getIrriUnirriIds() );
		return true;
	}

	@Override
	public AgriLoanParameterRequest get(Long id) {
		AgriLoanParameter agriLoanParameter = agriLoanParameterRepository.findById(id);
		if (agriLoanParameter == null) {
			return null;
		}
		AgriLoanParameterRequest agriLoanParameterRequest = new AgriLoanParameterRequest();
		BeanUtils.copyProperties(agriLoanParameter, agriLoanParameterRequest);

		agriLoanParameterRequest.setDistrictIds(fPParameterMappingService
				.getParameters(agriLoanParameterRequest.getId(), CommonUtils.ParameterTypes.DISTRICT));
		
		agriLoanParameterRequest.setCropIds(fPParameterMappingService
				.getParameters(agriLoanParameterRequest.getId(), CommonUtils.ParameterTypes.CROP));
		
		agriLoanParameterRequest.setIrriUnirriIds(fPParameterMappingService
				.getParameters(agriLoanParameterRequest.getId(), CommonUtils.ParameterTypes.IRRIGATED_UNIRRIGATED));
		return agriLoanParameterRequest;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AgriLoanParameterRequest getTemp(Long id, Long role, Long userId) {
		logger.info("Start getTemp==>");
		AgriLoanParameterTemp agriLoanParameter = agriLoanParameterTempRepository.findById(id);
		if (agriLoanParameter == null) {
			return null;
		}
		AgriLoanParameterRequest agriLoanParameterRequest = new AgriLoanParameterRequest();
		BeanUtils.copyProperties(agriLoanParameter, agriLoanParameterRequest);
		agriLoanParameterRequest.setDistrictIds(fPParameterMappingService.getParametersTemp(
				agriLoanParameterRequest.getId(), CommonUtils.ParameterTypes.DISTRICT));
		
		agriLoanParameterRequest.setCropIds(fPParameterMappingService.getParametersTemp(
				agriLoanParameterRequest.getId(), CommonUtils.ParameterTypes.CROP));
		
		agriLoanParameterRequest.setIrriUnirriIds(fPParameterMappingService.getParametersTemp(
				agriLoanParameterRequest.getId(), CommonUtils.ParameterTypes.IRRIGATED_UNIRRIGATED));

		//set workflow buttons
		 if (!CommonUtils.isObjectNullOrEmpty(agriLoanParameter.getJobId()) && !CommonUtils.isObjectNullOrEmpty(role)) {
            WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(agriLoanParameter.getJobId(),Arrays.asList(role), userId);
            if (!CommonUtils.isObjectNullOrEmpty(workflowResponse) && !CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
                try {
                    WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) workflowResponse.getData(), WorkflowJobsTrackerRequest.class);
                    if (!CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
                    	agriLoanParameterRequest.setWorkflowData(workflowJobsTrackerRequest.getStep().getStepActions());
                    } else {
                        logger.info("response from workflow NULL jobId = {} and roleId = {}", agriLoanParameter.getJobId(), role);
                    }
                } catch (IOException e) {
                    logger.error("Error While getting data from workflow {}", e);
                }
            }
        } else {
            logger.info("you set jobId or list of roleId NULL for calling workflow");
        }
		
		logger.info("End getTemp==>");
		return agriLoanParameterRequest;
	}

	@Override
	public Boolean saveOrUpdateTemp(AgriLoanParameterRequest agriLoanParameterRequest) {
		logger.info("saveOrUpdateTemp Start");
		AgriLoanParameterTemp agriLoanParameter = agriLoanParameterTempRepository.findOne(agriLoanParameterRequest.getId());
		if (agriLoanParameter == null) {
			return false;
		}
		BeanUtils.copyProperties(agriLoanParameterRequest, agriLoanParameter, CommonUtils.IgnorableCopy.getFpProduct());
		agriLoanParameter.setModifiedBy(agriLoanParameterRequest.getUserId());
		agriLoanParameter.setIsParameterFilled(true);
		agriLoanParameter.setModifiedDate(new Date());
		if (CommonUtils.isObjectNullOrEmpty(agriLoanParameter.getJobId())) {
			WorkflowResponse workflowResponse = workflowClient.createJobForMasters(WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL,agriLoanParameter.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
				agriLoanParameter.setJobId(Long.valueOf(workflowResponse.getData().toString()));
			}
		}
		agriLoanParameterTempRepository.save(agriLoanParameter);

		// Saving Mapping Current BORROWER_SALARY_ACCOUNT
		fPParameterMappingService.inactiveAndSaveTemp(agriLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.DISTRICT, agriLoanParameterRequest.getDistrictIds() );
		
		fPParameterMappingService.inactiveAndSaveTemp(agriLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.CROP, agriLoanParameterRequest.getCropIds());
		
		fPParameterMappingService.inactiveAndSaveTemp(agriLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.IRRIGATED_UNIRRIGATED, agriLoanParameterRequest.getIrriUnirriIds());
		logger.info("saveOrUpdateTemp End");
		return true;
	}

	@Override
	public Boolean saveMasterFromTemp(Long mappingId) throws LoansException {
		AgriLoanParameterRequest agriLoanParameterRequest = getTemp(mappingId, null, null);
		return saveOrUpdate(agriLoanParameterRequest);
	}
}
