package com.capitaworld.service.loans.service.fundprovider.impl;

import com.capitaworld.api.workflow.model.WorkflowJobsTrackerRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.MultipleJSONObjectHelper;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.loans.domain.fundprovider.MFILoanParameterTemp;
import com.capitaworld.service.loans.domain.fundprovider.MFIMappingTemp;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.mfi.MFILoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.MFILoanParameterTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.MFIMappingMasterRepository;
import com.capitaworld.service.loans.repository.fundprovider.MFIMappingTempRepository;
import com.capitaworld.service.loans.service.fundprovider.MFILoanParameterTempService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.OrgResponse;
import com.capitaworld.service.users.model.UsersRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class MFILoanParameterTempServiceImpl implements MFILoanParameterTempService {


    private static final Logger logger = LoggerFactory.getLogger(PersonalLoanParameterServiceImpl.class);

    private static final String ERROR_WHILE_GET_MFI_REQUEST_TEMP_MSG = "error while getMFIRequestTemp : ";
    private static final String ERROR_WHILE_GET_MFI_REQUEST_MASTER_MSG = "error while getMFIRequestMaster : ";

    @Autowired
    private MFILoanParameterTempRepository mfiLoanParameterTempRepository;

    @Autowired
    private MFIMappingMasterRepository mfiMappingMasterRepository;

    @Autowired
    private MFIMappingTempRepository mfiMappingTempRepository;

    @Autowired
    private OneFormClient oneFormClient;

    @Autowired
    private WorkflowClient workflowClient;

    @Autowired
    private UsersClient usersClient;

    @Override
    public MFILoanParameterRequest getMFILoanParameterRequest(Long id,Long role,Long userId) {
        CommonDocumentUtils.startHook(logger, "getMFILoanParameterRequest");
        MFILoanParameterRequest mfiLoanParameterRequest = new MFILoanParameterRequest();
        MFILoanParameterTemp mfiLoanParameterTemp = mfiLoanParameterTempRepository.getByID(id);
        if (mfiLoanParameterTemp == null)
            return null;
        BeanUtils.copyProperties(mfiLoanParameterTemp, mfiLoanParameterRequest);

        mfiLoanParameterRequest.setJobId(mfiLoanParameterTemp.getJobId());

        // get list of fp mfi

        List<Long> mfiList = mfiMappingTempRepository.getMfiByProductId(mfiLoanParameterRequest.getId());
        if (!mfiList.isEmpty()) {
            try {
                UsersRequest usersRequest=new UsersRequest();
                usersRequest.setOrgIdList(mfiList);
                usersRequest = usersClient.listMfiByOrgId(usersRequest);
                List<DataRequest> dataRequests = new ArrayList<>(usersRequest.getOrgResponseList().size());
                for (OrgResponse orgResponse : usersRequest.getOrgResponseList()) {
                    DataRequest dataRequest = new DataRequest();
                    BeanUtils.copyProperties(orgResponse,dataRequest);
                    dataRequests.add(dataRequest);
                }
                mfiLoanParameterRequest.setIndustrylist(dataRequests);
            } catch (Exception e) {
                logger.error(ERROR_WHILE_GET_MFI_REQUEST_TEMP_MSG, e);
            }
        }

        // get list of master mfi

        try {
            UsersRequest usersRequest=new UsersRequest();
            usersRequest.setOrgType(2);
            usersRequest = usersClient.listMfiByOrgType(usersRequest);
            List<DataRequest> dataRequests = new ArrayList<>(usersRequest.getOrgResponseList().size());
            for (OrgResponse orgResponse : usersRequest.getOrgResponseList()) {
                DataRequest dataRequest = new DataRequest();
                BeanUtils.copyProperties(orgResponse,dataRequest);
                dataRequests.add(dataRequest);
            }
            mfiLoanParameterRequest.setIndustryMasterList(dataRequests);
        } catch (Exception e) {
            logger.error(ERROR_WHILE_GET_MFI_REQUEST_MASTER_MSG, e);
        }
        //set workflow buttons

        if (!CommonUtils.isObjectNullOrEmpty(mfiLoanParameterTemp.getJobId()) && !CommonUtils.isObjectNullOrEmpty(role)) {
            WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(mfiLoanParameterTemp.getJobId(), Arrays.asList(role), userId);
            if (!CommonUtils.isObjectNullOrEmpty(workflowResponse) && !CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
                try {
                    WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) workflowResponse.getData(), WorkflowJobsTrackerRequest.class);
                    if (!CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
                        mfiLoanParameterRequest.setWorkflowData(workflowJobsTrackerRequest.getStep().getStepActions());
                    } else {
                        logger.info("response from workflow NULL jobId = {} and roleId = {}", mfiLoanParameterTemp.getJobId(), role);
                    }
                } catch (IOException e) {
                    logger.error("Error While getting data from workflow {}", e);
                }
            }
        } else {
            logger.info("you set jobId or list of roleId NULL for calling workflow");
        }


        return mfiLoanParameterRequest;
    }

    @Override
    public Boolean saveOrUpdateTemp(MFILoanParameterRequest mfiLoanParameterRequest) {
        CommonDocumentUtils.startHook(logger, "saveOrUpdateTemp");

        MFILoanParameterTemp mfiLoanParameterTemp = null;

        if (mfiLoanParameterRequest.getAppstage() == 1) {
            mfiLoanParameterTemp = mfiLoanParameterTempRepository.findOne(mfiLoanParameterRequest.getId());
        } else {

            mfiLoanParameterTemp = mfiLoanParameterTempRepository
                    .getMFIParameterTempByFpProductMappingId(mfiLoanParameterRequest.getId());

        }

        if (mfiLoanParameterTemp == null) {
            mfiLoanParameterTemp = new MFILoanParameterTemp();
            mfiLoanParameterTemp.setFpProductMappingId(mfiLoanParameterRequest.getId());
        }


        if (mfiLoanParameterRequest.getAppstage() != 1) {
            mfiLoanParameterTemp.setFpProductMappingId(mfiLoanParameterRequest.getId());
        }
        BeanUtils.copyProperties(mfiLoanParameterRequest, mfiLoanParameterTemp, "id");
        mfiLoanParameterTemp.setUserId(mfiLoanParameterRequest.getUserId() != null ? mfiLoanParameterRequest.getUserId() : null);
        mfiLoanParameterTemp.setProductId(mfiLoanParameterRequest.getProductId() != null ? mfiLoanParameterRequest.getProductId() : null);
        mfiLoanParameterTemp.setModifiedBy(mfiLoanParameterRequest.getUserId());
        mfiLoanParameterTemp.setModifiedDate(new Date());
        mfiLoanParameterTemp.setIsActive(true);
        mfiLoanParameterTemp.setIsParameterFilled(true);
        mfiLoanParameterTemp.setStatusId(CommonUtils.Status.OPEN);
        mfiLoanParameterTemp.setIsApproved(false);
        mfiLoanParameterTemp.setIsDeleted(false);
        mfiLoanParameterTemp.setIsCopied(false);
        mfiLoanParameterTemp.setApprovalDate(null);

        if (CommonUtils.isObjectNullOrEmpty(mfiLoanParameterTemp.getJobId())) {
            WorkflowResponse workflowResponse = workflowClient.createJobForMasters(
                    WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL,
                    mfiLoanParameterRequest.getUserId());
            Long jobId = null;
            if (!CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
                jobId = Long.valueOf(workflowResponse.getData().toString());
            }

            mfiLoanParameterTemp.setJobId(jobId);
        }

        mfiLoanParameterTemp = mfiLoanParameterTempRepository.save(mfiLoanParameterTemp);
        mfiLoanParameterRequest.setId(mfiLoanParameterTemp.getId());


        // start mfi mapping saving
        mfiMappingTempRepository.inActiveMappingByFpProductId(mfiLoanParameterTemp.getId());
        saveMFIMappingTemp(mfiLoanParameterRequest);
        // end mfi mapping saving

        CommonDocumentUtils.endHook(logger, "saveOrUpdateTemp");
        return true;

    }


    private void saveMFIMappingTemp(MFILoanParameterRequest mfiLoanParameterRequest) {
        logger.info("start saveMFIMappingTemp");
        MFIMappingTemp mfiMappingTemp = null;
        logger.info("" + mfiLoanParameterRequest.getIndustrylist());
        for (DataRequest dataRequest : mfiLoanParameterRequest.getIndustrylist()) {
            mfiMappingTemp = new MFIMappingTemp();
            mfiMappingTemp.setFpProductId(mfiLoanParameterRequest.getId());
            mfiMappingTemp.setOrgId(dataRequest.getId());
            mfiMappingTemp.setCreatedBy(mfiLoanParameterRequest.getUserId());
            mfiMappingTemp.setModifiedBy(mfiLoanParameterRequest.getUserId());
            mfiMappingTemp.setCreatedDate(new Date());
            mfiMappingTemp.setModifiedDate(new Date());
            mfiMappingTemp.setIsActive(true);
            // create by and update
            mfiMappingTempRepository.save(mfiMappingTemp);
        }
        logger.info("end saveMFIMappingTemp");
    }


}
