package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryTermLoanDetail;
import com.capitaworld.service.loans.model.PrimaryTermLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryTermLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.PrimaryTermLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryTermLoanServiceImpl implements PrimaryTermLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryTermLoanServiceImpl.class.getName());

	@Autowired
	private PrimaryTermLoanDetailRepository primaryTLRepository;

	@Override
	public boolean saveOrUpdate(PrimaryTermLoanRequest termLoanRequest) throws Exception {
		try {
			PrimaryTermLoanDetail termLoanDetail = primaryTLRepository.findOne(termLoanRequest.getId());
			if (termLoanDetail == null) {
				throw new NullPointerException(
						"PrimaryTermLoanDetail not exist in DB with ID=>" + termLoanRequest.getId());
			}
			BeanUtils.copyProperties(termLoanRequest, termLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
			termLoanDetail.setModifiedBy(termLoanRequest.getUserId());
			termLoanDetail.setModifiedDate(new Date());
			primaryTLRepository.save(termLoanDetail);
			return true;
		} catch (Exception e) {
			logger.error("Error while Primary Term Loan Details:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	@Override
	public PrimaryTermLoanRequest get(Long id) throws Exception {
		try {
			PrimaryTermLoanDetail loanDetail = primaryTLRepository.findOne(id);
			if (loanDetail == null) {
				throw new NullPointerException(
						"PrimaryTermLoanDetail not exist in DB with ID=>" + id);
			}
			PrimaryTermLoanRequest capitalLoanRequest = new PrimaryTermLoanRequest();
			BeanUtils.copyProperties(loanDetail, capitalLoanRequest);
			return capitalLoanRequest;
		} catch (Exception e) {
			logger.error("Error while Primary Term Loan Details:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}

	}
}
