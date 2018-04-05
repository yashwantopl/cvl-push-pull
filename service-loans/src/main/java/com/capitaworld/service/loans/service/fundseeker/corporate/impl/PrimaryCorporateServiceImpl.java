package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.model.corporate.PrimaryCorporateRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryTermLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PrimaryCorporateService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class PrimaryCorporateServiceImpl implements PrimaryCorporateService{

    private static final Logger logger = LoggerFactory.getLogger(PrimaryCorporateServiceImpl.class.getName());

    @Autowired
    private PrimaryCorporateDetailRepository primaryCorporateRepository;

    @Autowired
    private LoanApplicationService loanApplicationService;

    @Override
    public boolean saveOrUpdate(PrimaryCorporateRequest primaryCorporateRequest, Long userId) throws Exception {

        try {
            PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository.getByApplicationAndUserId(primaryCorporateRequest.getId(),  (CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getClientId()) ? userId : primaryCorporateRequest.getClientId()));
            if (primaryCorporateDetail == null) {
                throw new NullPointerException("PrimaryCorporateDetail not exist in DB with ID=>"
                        + primaryCorporateRequest.getId() + " and UserId==>" + userId);
            }
            BeanUtils.copyProperties(primaryCorporateRequest, primaryCorporateDetail, CommonUtils.IgnorableCopy.CORPORATE);
            primaryCorporateDetail.setModifiedBy(userId);
            primaryCorporateDetail.setModifiedDate(new Date());
            primaryCorporateRepository.save(primaryCorporateDetail);
            return true;
        }catch (Exception e){
            logger.error("Error while Primary Corporate Details:-");
            e.printStackTrace();
            throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public PrimaryCorporateRequest get(Long applicationId, Long userId) throws Exception {
        try {
            PrimaryCorporateDetail loanDetail = primaryCorporateRepository.getByApplicationAndUserId(applicationId, userId);
            if (loanDetail == null) {
                throw new NullPointerException(
                        "PrimaryCorporateDetail not exist in DB with ID=>" + applicationId + " and UserId==>" + userId);
            }
            PrimaryCorporateRequest primaryCorporateRequest = new PrimaryCorporateRequest();
            BeanUtils.copyProperties(loanDetail, primaryCorporateRequest);
            JSONObject result = loanApplicationService.getCurrencyAndDenomination(applicationId,userId);
            String data = result.get("currency").toString();
            data = data.concat(" In "+ result.get("denomination").toString());
            primaryCorporateRequest.setCurrencyValue(data);
            return primaryCorporateRequest;
        } catch (Exception e) {
            logger.error("Error while Primary Corporate Details:-");
            e.printStackTrace();
            throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
        }

    }
    }

