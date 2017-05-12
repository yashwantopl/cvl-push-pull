package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryCarLoanDetail;
import com.capitaworld.service.loans.model.retail.PrimaryCarLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryCarLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryCarLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryCarLoanServiceImpl implements PrimaryCarLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryCarLoanServiceImpl.class.getName());
	
	@Autowired
	private PrimaryCarLoanDetailRepository primaryCarLoanDetailRepository;

	@Override
	public boolean saveOrUpdate(PrimaryCarLoanDetailRequest carLoanDetailRequest, Long userId) throws Exception {
		// ID must not be null
		try {
			PrimaryCarLoanDetail primaryCarLoanDetail = primaryCarLoanDetailRepository
					.getByApplicationAndUserId(carLoanDetailRequest.getId(), userId);
			if (primaryCarLoanDetail == null) {
				throw new NullPointerException("PrimaryCarLoanDetail not exist in DB with ID=>"
						+ carLoanDetailRequest.getId() + " and User Id ==>" + userId);
			}
			BeanUtils.copyProperties(carLoanDetailRequest, primaryCarLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
			primaryCarLoanDetail.setModifiedBy(userId);
			primaryCarLoanDetail.setModifiedDate(new Date());
			primaryCarLoanDetailRepository.save(primaryCarLoanDetail);
			return true;
		} catch (Exception e) {
			logger.error("Error while saving PrimaryCarLoan Details");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public PrimaryCarLoanDetailRequest get(Long id, Long userId) throws Exception {
		try {
			PrimaryCarLoanDetail loanDetail = primaryCarLoanDetailRepository.getByApplicationAndUserId(id, userId);
			if (loanDetail == null) {
				throw new NullPointerException(
						"PrimaryCarLoanDetail not exist in DB with ID=>" + id + " and User Id ==>" + userId);
			}
			PrimaryCarLoanDetailRequest carLoanDetailRequest = new PrimaryCarLoanDetailRequest();
			BeanUtils.copyProperties(loanDetail, carLoanDetailRequest);
			return carLoanDetailRequest;
		} catch (Exception e) {
			logger.error("Error while getting Primary CarLoan Details");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
}
