package com.capitaworld.service.loans.service.fundseeker.microfinance.impl;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIApplicationDetail;
import com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiApplicationDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.impl.LoanApplicationServiceImpl;
import com.capitaworld.service.loans.service.fundseeker.microfinance.MfiApplicationService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MfiApplicationServiceImpl implements MfiApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(MfiApplicationServiceImpl.class.getName());

    @Autowired
    private MfiApplicationDetailsRepository detailsRepository;

    @Autowired
    private LoanApplicationServiceImpl applicationService;

    @Override
    public boolean saveOrUpdateAadharDetails(AadharDetailsReq aadharDetailsReq) {
        if(aadharDetailsReq.getId() == null || aadharDetailsReq.getApplicationId() == null) {
            Long applicationId = applicationService.createLoan(aadharDetailsReq.getUserId(), true, aadharDetailsReq.getBusinessTypeId());
            if (applicationId != null) {
                MFIApplicationDetail mfiApplicationDetail = new MFIApplicationDetail();
                BeanUtils.copyProperties(aadharDetailsReq, mfiApplicationDetail);
                mfiApplicationDetail.setApplicationId(new LoanApplicationMaster(applicationId));
                mfiApplicationDetail.setStatus(CommonUtils.PENDING);
                detailsRepository.save(mfiApplicationDetail);
            }
        }
        return false;
    }
}
