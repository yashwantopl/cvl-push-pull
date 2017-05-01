package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;

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

	@Autowired
	private PrimaryTermLoanDetailRepository primaryTLRepository;

	@Override
	public boolean saveOrUpdate(PrimaryTermLoanRequest termLoanRequest) {
		PrimaryTermLoanDetail termLoanDetail = primaryTLRepository.findOne(termLoanRequest.getId());
		if (termLoanDetail == null) {
			return false;
		}
		BeanUtils.copyProperties(termLoanRequest, termLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		termLoanDetail.setModifiedBy(termLoanRequest.getUserId());
		termLoanDetail.setModifiedDate(new Date());
		primaryTLRepository.save(termLoanDetail);
		return true;
	}

	@Override
	public PrimaryTermLoanRequest get(Long id) {
		PrimaryTermLoanDetail loanDetail = primaryTLRepository.findOne(id);
		if (loanDetail == null) {
			return null;
		}
		PrimaryTermLoanRequest capitalLoanRequest = new PrimaryTermLoanRequest();
		BeanUtils.copyProperties(loanDetail, capitalLoanRequest);
		return capitalLoanRequest;

	}
}
