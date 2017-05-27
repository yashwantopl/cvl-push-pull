package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryPersonalLoanDetail;
import com.capitaworld.service.loans.model.retail.PrimaryPersonalLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryPersonalLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryPersonalLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryPersonalLoanServiceImpl implements PrimaryPersonalLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryPersonalLoanServiceImpl.class.getName());

	@Autowired
	private PrimaryPersonalLoanDetailRepository personalLoanDetailRepository;

	@Override
	public boolean saveOrUpdate(PrimaryPersonalLoanRequest personalLoanRequest, Long userId) throws Exception {
		// ID must not be null
		try {
			PrimaryPersonalLoanDetail primaryPersonalLoanDetail = personalLoanDetailRepository
					.getByApplicationAndUserId(personalLoanRequest.getId(), (CommonUtils.isObjectNullOrEmpty(personalLoanRequest.getClientId()) ? userId : personalLoanRequest.getClientId()));
			if (primaryPersonalLoanDetail == null) {
				throw new NullPointerException("PrimaryPersonalLoanDetail not exist in DB with ID=>"
						+ personalLoanRequest.getId() + " and User Id ==>" + userId);
			}
			BeanUtils.copyProperties(personalLoanRequest, primaryPersonalLoanDetail,
					CommonUtils.IgnorableCopy.CORPORATE);
			primaryPersonalLoanDetail.setTenure(CommonUtils.isObjectNullOrEmpty(personalLoanRequest.getTenure()) ? null : (personalLoanRequest.getTenure() * 12));
			primaryPersonalLoanDetail.setIsActive(true);
			primaryPersonalLoanDetail.setModifiedBy(userId);
			primaryPersonalLoanDetail.setModifiedDate(new Date());
			personalLoanDetailRepository.save(primaryPersonalLoanDetail);
			return true;
		} catch (Exception e) {
			logger.error("Error while saving PrimaryCarLoan Details");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public PrimaryPersonalLoanRequest get(Long applicationId, Long userId) throws Exception {
		try {
			PrimaryPersonalLoanDetail loanDetail = personalLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
			if (loanDetail == null) {
				throw new NullPointerException(
						"PrimaryPersonalLoanDetail not exist in DB with ID=>" + applicationId + " and User Id ==>" + userId);
			}
			PrimaryPersonalLoanRequest personalLoanRequest = new PrimaryPersonalLoanRequest();
			BeanUtils.copyProperties(loanDetail, personalLoanRequest);
			personalLoanRequest.setTenure(CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? null : (loanDetail.getTenure() / 12));
			return personalLoanRequest;
		} catch (Exception e) {
			logger.error("Error while saving PrimaryCarLoan Details");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
}
