package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.FinalHomeLoanDetail;
import com.capitaworld.service.loans.model.retail.FinalHomeLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.FinalHomeLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.FinalHomeLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FinalHomeLoanServiceImpl implements FinalHomeLoanService {

	private static final Logger logger = LoggerFactory.getLogger(FinalHomeLoanServiceImpl.class);

	@Autowired
	private FinalHomeLoanDetailRepository finalHomeLoanDetailRepository;

	@Override
	public boolean saveOrUpdate(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest, Long userId) throws Exception {
		try {
			FinalHomeLoanDetail finalHomeLoanDetail = finalHomeLoanDetailRepository
					.getByApplicationAndUserId(finalHomeLoanDetailRequest.getApplicationId(), (CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailRequest.getClientId()) ? userId : finalHomeLoanDetailRequest.getClientId()));
			if (finalHomeLoanDetail == null) {
				finalHomeLoanDetail = new FinalHomeLoanDetail();
				finalHomeLoanDetail.setCreatedBy(userId);
				finalHomeLoanDetail.setCreatedDate(new Date());
				finalHomeLoanDetail.setIsActive(true);
				finalHomeLoanDetail
						.setApplicationId(new LoanApplicationMaster(finalHomeLoanDetailRequest.getApplicationId()));
			} else {
				finalHomeLoanDetail.setModifiedBy(userId);
				finalHomeLoanDetail.setModifiedDate(new Date());
			}
			String[] corporate = new String[CommonUtils.IgnorableCopy.CORPORATE.length + 1];
			corporate[CommonUtils.IgnorableCopy.CORPORATE.length] = CommonUtils.IgnorableCopy.ID;
			BeanUtils.copyProperties(finalHomeLoanDetailRequest, finalHomeLoanDetail,corporate);
			finalHomeLoanDetail = finalHomeLoanDetailRepository.save(finalHomeLoanDetail);
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Final Home Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public FinalHomeLoanDetailRequest get(Long applicationId, Long userId) throws Exception {
		try {
			FinalHomeLoanDetail loanDetail = finalHomeLoanDetailRepository.getByApplicationAndUserId(applicationId,
					userId);
			if (loanDetail == null) {
				return null;
			}
			FinalHomeLoanDetailRequest finalHomeLoanDetailRequest = new FinalHomeLoanDetailRequest();
			BeanUtils.copyProperties(loanDetail, finalHomeLoanDetailRequest);
			return finalHomeLoanDetailRequest;
		} catch (Exception e) {
			logger.error("Error while getting Final Home Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
