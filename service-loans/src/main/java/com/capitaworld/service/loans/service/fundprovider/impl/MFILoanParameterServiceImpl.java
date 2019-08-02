package com.capitaworld.service.loans.service.fundprovider.impl;

import com.capitaworld.api.workflow.model.WorkflowJobsTrackerRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.MultipleJSONObjectHelper;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.loans.domain.fundprovider.MFILoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.MFILoanParameterTemp;
import com.capitaworld.service.loans.domain.fundprovider.MFIMappingMaster;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.mfi.MFILoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.*;
import com.capitaworld.service.loans.service.fundprovider.MFILoanParameterService;
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

    @Autowired
    private MFIMappingTempRepository mfiMappingTempRepository;

    @Autowired
    private UsersClient usersClient;

    @Autowired
    private  MFIMappingMasterRepository mfiMappingMasterRepository;


    @Override
    public MFILoanParameterRequest getMFILoanParameterRequest(Long id) {
        CommonDocumentUtils.startHook(logger, "getMFILoanParameterRequest");
        MFILoanParameterRequest mfiLoanParameterRequest = new MFILoanParameterRequest();
        MFILoanParameter mfiLoanParameter = mfiLoanParameterRepository.getByID(id);
        if (mfiLoanParameter == null)
            return null;
        BeanUtils.copyProperties(mfiLoanParameter, mfiLoanParameterRequest);


        // get list of fp mfi mapping

        List<Long> mfiList = mfiMappingMasterRepository.getMfiByProductId(mfiLoanParameterRequest.getId());
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
                logger.error("Error while get mfi mapping detail", e);
            }
        }

        // get list of  mfi mapping

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
            logger.error("Error while get mfi master detail", e);
        }

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

        mfiLoanParameterRequest.getIndustrylist();

        // start mfi mapping saving
        mfiMappingMasterRepository.inActiveMappingByFpProductId(mfiLoanParameterRequest.getId());
        saveMFIMappingMaster(mfiLoanParameterRequest);
        // end mfi mapping saving


        logger.info("saveOrUpdate Ends");
        return true;
    }

    private void saveMFIMappingMaster(MFILoanParameterRequest mfiLoanParameterRequest) {
        logger.info("start saveMFIMappingMaster");
        MFIMappingMaster mfiMappingMaster = null;
        logger.info("" + mfiLoanParameterRequest.getIndustrylist());
        for (DataRequest dataRequest : mfiLoanParameterRequest.getIndustrylist()) {
            mfiMappingMaster = new MFIMappingMaster();
            mfiMappingMaster.setFpProductId(mfiLoanParameterRequest.getId());
            mfiMappingMaster.setOrgId(dataRequest.getId());
            mfiMappingMaster.setCreatedBy(mfiLoanParameterRequest.getUserId());
            mfiMappingMaster.setModifiedBy(mfiLoanParameterRequest.getUserId());
            mfiMappingMaster.setCreatedDate(new Date());
            mfiMappingMaster.setModifiedDate(new Date());
            mfiMappingMaster.setIsActive(true);
            // create by and update
            mfiMappingMasterRepository.save(mfiMappingMaster);
        }
        logger.info("end saveMFIMappingMaster");
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
                logger.error("Error while get mfi detail", e);
            }
        }

        // get list of master mfi


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
