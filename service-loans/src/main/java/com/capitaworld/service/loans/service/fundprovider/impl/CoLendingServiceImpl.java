package com.capitaworld.service.loans.service.fundprovider.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.capitaworld.service.loans.domain.fundprovider.CoLendingRatio;
import com.capitaworld.service.loans.domain.fundprovider.FpCoLendingBanks;
import com.capitaworld.service.loans.domain.fundprovider.ProductMasterTemp;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.WorkflowData;
import com.capitaworld.service.loans.model.corporate.CoLendingRequest;
import com.capitaworld.service.loans.repository.fundprovider.CoLendingRatioRepository;
import com.capitaworld.service.loans.repository.fundprovider.FpCoLendingBanksRepository;
import com.capitaworld.service.loans.service.fundprovider.CoLendingService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class CoLendingServiceImpl implements CoLendingService {

	
	private static final Logger logger = LoggerFactory.getLogger(CoLendingServiceImpl.class);
	@Autowired
	private FpCoLendingBanksRepository fpCoLendingBanksRepository;
	
	@Autowired
	private WorkflowClient workflowClient;
	
	@Autowired
	private CoLendingRatioRepository coLendingRatioRepository;
	
	

	@Override
	public List<FpCoLendingBanks> getBankList() {
		// TODO Auto-generated method stub
		return fpCoLendingBanksRepository.getAllActive();
	}
	
	@Override
	public Boolean 	saveOrUpdate(CoLendingRequest coLendingRequest, Long userOrgId) {
		// TODO Auto-generated method stub

		
		CommonDocumentUtils.startHook(logger, "saveOrUpdate");
		try {
			
			
			CoLendingRatio coLendingRatio;
			if(CommonUtils.isObjectNullOrEmpty(coLendingRequest.getId()))
			{
				coLendingRatio=new CoLendingRatio();
				BeanUtils.copyProperties(coLendingRequest, coLendingRatio,"createdDate");
				coLendingRatio.setCreatedDate(new Date());
				coLendingRatio.setActive(true);
				coLendingRatio.setUserOrgId(userOrgId);
				
				//set workflow status
				WorkflowResponse workflowResponse = workflowClient.createJobForMasters(
						WorkflowUtils.Workflow.NBFC_LOAN_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL,
						(CommonUtils.isObjectNullOrEmpty(coLendingRequest.getClientId())
								? coLendingRequest.getUserId() : coLendingRequest.getClientId()));
				
				Long jobId = null;
				if(workflowResponse != null){
					jobId = workflowResponse.getData() != null ? Long.valueOf(workflowResponse.getData().toString()) : null;
					coLendingRatio.setJobId(jobId);
				}
				
			}
			else
			{
				coLendingRatio=coLendingRatioRepository.findOne(coLendingRequest.getId());
				BeanUtils.copyProperties(coLendingRequest, coLendingRatio,"createdDate");
			}
			
			coLendingRatioRepository.save(coLendingRatio);
			return true;
		}

		catch(Exception e)
		{
			logger.error("Error while saving ratio");
			return false;
		}
	}
	
	@Override
	public List<CoLendingRequest> 	listAll(Long userId, Long userOrgId,Long role) {
		// TODO Auto-generated method stub

		
		CommonDocumentUtils.startHook(logger, "listAll");
		try {
			
			List<CoLendingRatio> coLendingRatiosList=new ArrayList<>();
			if(role==WorkflowUtils.Role.ADMIN_CHECKER)
			{
				coLendingRatiosList=coLendingRatioRepository.listAllActiveByBankId(userOrgId);
			}
			else
			{
				coLendingRatiosList=coLendingRatioRepository.listAllActiveByUserId(userOrgId);	
			}
			
			
			List<CoLendingRequest> coLendingRequestList=new ArrayList<>(coLendingRatiosList.size());
			
			for(CoLendingRatio coLendingRatio:coLendingRatiosList)
			{
				CoLendingRequest coLendingRequest2=new CoLendingRequest();
				BeanUtils.copyProperties(coLendingRatio, coLendingRequest2);
				
				//set workflow buttons
				
				 if (!CommonUtils.isObjectNullOrEmpty(coLendingRequest2.getJobId()) && !CommonUtils.isObjectNullOrEmpty(role)) {
		             WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(coLendingRequest2.getJobId(),Arrays.asList(role), userId);
		             if (!CommonUtils.isObjectNullOrEmpty(workflowResponse) && !CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
		                 try {
		                     WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) workflowResponse.getData(), WorkflowJobsTrackerRequest.class);
		                     if (!CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
		                    	 coLendingRequest2.setWorkflowData(workflowJobsTrackerRequest.getStep().getStepActions());
		                     } else {
		                         logger.info("response from workflow NULL jobId = {} and roleId = {}", coLendingRequest2.getJobId(), role);
		                     }
		                 } catch (IOException e) {
		                     logger.error("Error While getting data from workflow {}", e);
		                 }
		             }
		         } else {
		             logger.info("you set jobId or list of roleId NULL for calling workflow");
		         }
				
				coLendingRequestList.add(coLendingRequest2);
			}
			
			
			
			
			
			return coLendingRequestList;
		}

		catch(Exception e)
		{
			logger.error("Error while listing ratio",e);
			return null;
		}
	}

	@Override
	public Boolean clickOnWorkFlowButton(WorkflowData workflowData) {
		// TODO Auto-generated method stub
		try {

			WorkflowRequest request = new WorkflowRequest();
			request.setActionId(workflowData.getActionId());
			request.setCurrentStep(workflowData.getWorkflowStep());
			request.setToStep(workflowData.getNextworkflowStep());
			request.setJobId(workflowData.getJobId());
			request.setUserId(workflowData.getUserId());
			
			
			
		
			if (workflowData.getActionId() == WorkflowUtils.Action.SEND_FOR_APPROVAL) {
				
				//WorkflowResponse workflowResponse = workflowClient.updateJob(request);
				
			} else if (workflowData.getActionId() == WorkflowUtils.Action.APPROVED) {
				//change status for activated
				coLendingRatioRepository.updateActivatedRatio(workflowData.getJobId(), true);
				
				
				
				
			} else if (workflowData.getActionId() == WorkflowUtils.Action.SEND_BACK) {
				
				

			}
			WorkflowResponse workflowResponse = workflowClient.updateJob(request);
			return true;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return false;
		}
	}

	@Override
	public Boolean removeCoLendingProposal(Long id) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "removeCoLendingProposal");
		try {
			
			coLendingRatioRepository.inActiveRatio(id);
			return true;
		}

		catch(Exception e)
		{
			logger.error("Error while removeCoLendingProposal",e);
			return false;
		}
	}

	@Override
	public Boolean addReasonByJobId(DataRequest dataRequest) {
		// TODO Auto-generated method stub
		
		CommonDocumentUtils.startHook(logger, "addReasonByJobId");
		try {
			
			coLendingRatioRepository.addReasonByJobId(dataRequest.getValue(),dataRequest.getId());
			return true;
		}

		catch(Exception e)
		{
			logger.error("Error while addReasonByJobId",e);
			return false;
		}
		
	}
	
	
	

	@Override
	public List<CoLendingRequest> listByOrgId(Long userOrgId) {
		return coLendingRatioRepository.listByOrgId(userOrgId);
	}
}
