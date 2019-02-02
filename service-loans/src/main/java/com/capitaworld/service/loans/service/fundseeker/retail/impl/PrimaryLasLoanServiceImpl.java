package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import com.capitaworld.service.loans.exceptions.LoansException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLasLoanDetail;
import com.capitaworld.service.loans.model.retail.PrimaryLasLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryLasLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryLasLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryLasLoanServiceImpl implements PrimaryLasLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryLasLoanServiceImpl.class.getName());

	@Autowired
	private PrimaryLasLoanDetailRepository primaryLasLoanDetailRepository;
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;

	@Override
	public boolean saveOrUpdate(PrimaryLasLoanDetailRequest lasLoanDetailRequest, Long userId) throws LoansException {
		// ID must not be null
		try {
			PrimaryLasLoanDetail primaryLasLoanDetail = primaryLasLoanDetailRepository.getByApplicationAndUserId(
					lasLoanDetailRequest.getId(), (CommonUtils.isObjectNullOrEmpty(lasLoanDetailRequest.getClientId())
							? userId : lasLoanDetailRequest.getClientId()));
			if (primaryLasLoanDetail == null) {
				throw new NullPointerException("PrimaryLasLoanDetail not exist in DB with Application Id=>"
						+ lasLoanDetailRequest.getId() + " and user Id ==>" + userId);
			}
			BeanUtils.copyProperties(lasLoanDetailRequest, primaryLasLoanDetail, CommonUtils.IgnorableCopy.getCORPORATE());
			primaryLasLoanDetail.setTenure(CommonUtils.isObjectNullOrEmpty(lasLoanDetailRequest.getTenure()) ? null
					: (lasLoanDetailRequest.getTenure() * 12));
			primaryLasLoanDetail.setIsActive(true);
			primaryLasLoanDetail.setModifiedBy(lasLoanDetailRequest.getUserId());
			primaryLasLoanDetail.setModifiedDate(new Date());
			primaryLasLoanDetailRepository.save(primaryLasLoanDetail);
			return true;
		} catch (Exception e) {
			logger.error("Error while saving Primary LAS Loan Details Profile:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public PrimaryLasLoanDetailRequest get(Long applicationId, Long userId) throws LoansException {
		try {
			PrimaryLasLoanDetail loanDetail = primaryLasLoanDetailRepository.getByApplicationAndUserId(applicationId,
					userId);
			if (loanDetail == null) {
				throw new NullPointerException("PrimaryLasLoanDetail not exist in DB with applicationId=>"
						+ applicationId + " and UserId==>" + userId);
			}
			PrimaryLasLoanDetailRequest lasLoanDetailRequest = new PrimaryLasLoanDetailRequest();
			BeanUtils.copyProperties(loanDetail, lasLoanDetailRequest);
			lasLoanDetailRequest.setTenure(
					CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? null : (loanDetail.getTenure() / 12));
			Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
			lasLoanDetailRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
			return lasLoanDetailRequest;
		} catch (Exception e) {
			logger.error("Error while getting Primary LAS Loan Details Profile:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
}
