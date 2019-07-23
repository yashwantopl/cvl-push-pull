package com.capitaworld.service.loans.service.fundprovider.impl;

import com.capitaworld.api.workflow.model.WorkflowJobsTrackerRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.MultipleJSONObjectHelper;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.loans.domain.fundprovider.MFILoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.MFILoanParameterTemp;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.mfi.MFILoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.MFILoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.MFILoanParameterTempRepository;
import com.capitaworld.service.loans.service.fundprovider.MFILoanParameterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;

@Service
@Transactional
public class MFILoanParameterServiceImpl implements MFILoanParameterService {

    private static final Logger logger = LoggerFactory.getLogger(PersonalLoanParameterServiceImpl.class);


    @Autowired
    private GeographicalCountryRepository geographicalCountryRepository;

    @Autowired
    private MFILoanParameterRepository mfiLoanParameterRepository;

    @Autowired
    private MFILoanParameterTempRepository mfiLoanParameterTempRepository;

    @Autowired
    private OneFormClient oneFormClient;

    @Autowired
    private WorkflowClient workflowClient;

    @Override
    public MFILoanParameterRequest getMFILoanParameterRequest(Long id) {
        CommonDocumentUtils.startHook(logger, "getMFILoanParameterRequest");
        MFILoanParameterRequest mfiLoanParameterRequest = new MFILoanParameterRequest();
        MFILoanParameter mfiLoanParameter = mfiLoanParameterRepository.getByID(id);
        if (mfiLoanParameter == null)
            return null;
        BeanUtils.copyProperties(mfiLoanParameter, mfiLoanParameterRequest);

        return mfiLoanParameterRequest;
    }

    @Override
    public Boolean saveMasterFromTemp(Long mappingId) throws LoansException {
        MFILoanParameterRequest mfiLoanParameterRequest = getTemp(mappingId, null, null);
        return saveOrUpdate(mfiLoanParameterRequest);
    }

    @Override
    public boolean saveOrUpdate(MFILoanParameterRequest mfiLoanParameterRequest) {

        logger.info("saveOrUpdate starts");
        MFILoanParameterTemp loanParameter = mfiLoanParameterTempRepository.findOne(mfiLoanParameterRequest.getId());

        MFILoanParameter mfiLoanParameter = null;
        if (loanParameter.getFpProductMappingId() != null) {
            mfiLoanParameter = mfiLoanParameterRepository.findOne(loanParameter.getFpProductMappingId());
        }
        if (mfiLoanParameter == null) {
            mfiLoanParameter = new MFILoanParameter();
        }

        loanParameter.setStatusId(CommonUtils.Status.APPROVED);
        loanParameter.setIsDeleted(false);
        loanParameter.setIsEdit(false);
        loanParameter.setIsCopied(true);
        loanParameter.setIsApproved(true);
        loanParameter.setApprovalDate(new Date());
        loanParameter.setFpProductMappingId(mfiLoanParameter.getId());
        mfiLoanParameterTempRepository.save(loanParameter);
        BeanUtils.copyProperties(mfiLoanParameterRequest, mfiLoanParameter, CommonUtils.IgnorableCopy.getFpProduct());



        mfiLoanParameter.setIsParameterFilled(true);
        mfiLoanParameter.setProductId(mfiLoanParameterRequest.getProductId());
        mfiLoanParameter.setUserId(mfiLoanParameterRequest.getUserId());
        mfiLoanParameter.setModifiedBy(mfiLoanParameterRequest.getUserId());
        mfiLoanParameter.setIsParameterFilled(true);
        mfiLoanParameter.setModifiedDate(new Date());
        mfiLoanParameter = mfiLoanParameterRepository.save(mfiLoanParameter);
        mfiLoanParameterRequest.setId(mfiLoanParameter.getId());

        logger.info("saveOrUpdate Ends");
        return true;
    }


    @SuppressWarnings("unchecked")
    @Override
    public MFILoanParameterRequest getTemp(Long id, Long role, Long userId) {

        logger.info("Start getTemp==>");
        MFILoanParameterTemp mfiLoanParameterTemp = mfiLoanParameterTempRepository.findOne(id);
        if (mfiLoanParameterTemp == null) {
            return null;
        }
        MFILoanParameterRequest mfiLoanParameterRequest = new MFILoanParameterRequest();
        BeanUtils.copyProperties(mfiLoanParameterTemp, mfiLoanParameterRequest);

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

        logger.info("End getTemp==>");
        return mfiLoanParameterRequest;
    }
}
