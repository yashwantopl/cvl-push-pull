package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

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

	@Autowired
	private PrimaryCarLoanDetailRepository primaryCarLoanDetailRepository;

	@Override
	public boolean saveOrUpdate(PrimaryCarLoanDetailRequest carLoanDetailRequest) {
		// ID must not be null
		PrimaryCarLoanDetail primaryCarLoanDetail= primaryCarLoanDetailRepository.findOne(carLoanDetailRequest.getId());
		if (primaryCarLoanDetail == null) {
			return false;
		}
		BeanUtils.copyProperties(carLoanDetailRequest, primaryCarLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		primaryCarLoanDetail.setModifiedBy(carLoanDetailRequest.getUserId());
		primaryCarLoanDetail.setModifiedDate(new Date());
		primaryCarLoanDetailRepository.save(primaryCarLoanDetail);
		return true;
	}

	@Override
	public PrimaryCarLoanDetailRequest get(Long id) {
		PrimaryCarLoanDetail loanDetail = primaryCarLoanDetailRepository.findOne(id);
		if (loanDetail == null) {
			return null;
		}
		PrimaryCarLoanDetailRequest carLoanDetailRequest= new PrimaryCarLoanDetailRequest();
		BeanUtils.copyProperties(loanDetail, carLoanDetailRequest);
		return carLoanDetailRequest;
	}
}
