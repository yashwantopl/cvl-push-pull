package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import com.capitaworld.service.loans.domain.fundseeker.retail.ObligationDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.ObligationDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.ObligationDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.ObligationDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ravina.panchal on 04-10-2018.
 */
@Service
@Transactional
public class ObligationDetailServiceimpl implements ObligationDetailService {


    private static final Logger logger = LoggerFactory.getLogger(OtherCurrentAssetDetailServiceImpl.class);

    @Autowired
    private ObligationDetailRepository obligationDetailRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    @Override
    public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
        try {
            for (Map<String, Object> obj : frameRequest.getDataList()) {
                ObligationDetailRequest obligationDetailRequest = (ObligationDetailRequest) MultipleJSONObjectHelper
                        .getObjectFromMap(obj, ObligationDetailRequest.class);
                ObligationDetail obligationDetail = new ObligationDetail();
                BeanUtils.copyProperties(obligationDetailRequest, obligationDetail);
                if (obligationDetailRequest.getId() == null) {
                    obligationDetail.setCreatedBy(frameRequest.getUserId());
                    obligationDetail.setCreatedDate(new Date());
                }
                switch(frameRequest.getApplicantType()) {
                    case CommonUtils.ApplicantType.APPLICANT:
                        obligationDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
                        break;

                    default :
                        throw new LoansException();
                }

                obligationDetail.setModifiedBy(frameRequest.getUserId());
                obligationDetail.setModifiedDate(new Date());
                obligationDetailRepository.save(obligationDetail);
            }
            return true;
        }

        catch (Exception e) {
            logger.error("Exception  in save obligationDetail  :-",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public List<ObligationDetailRequest> getObligationDetailList(Long id, int applicationType) throws LoansException {
        List<ObligationDetail> otherCurrentAssetDetails;
        switch (applicationType) {
            case CommonUtils.ApplicantType.APPLICANT:
                otherCurrentAssetDetails = obligationDetailRepository.listObligationDetailFromAppId(id);
                break;

            default:
                throw new LoansException();
        }

        List<ObligationDetailRequest> obligationDetailRequests = new ArrayList<ObligationDetailRequest>();

        for (ObligationDetail detail : otherCurrentAssetDetails) {
            ObligationDetailRequest obligationDetailRequest = new ObligationDetailRequest();
            obligationDetailRequest.setGrossAmountString(CommonUtils.convertValue(detail.getGrossAmount()));
            obligationDetailRequest.setNetAmountString(CommonUtils.convertValue(detail.getNetAmount()));
            obligationDetailRequest.setPeriodicityString(CommonUtils.convertValue(detail.getPeriodicity()));
            BeanUtils.copyProperties(detail, obligationDetailRequest);
            obligationDetailRequests.add(obligationDetailRequest);
        }
        return obligationDetailRequests;
    }

}
