package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.model.PrimaryWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryWorkingCapitalLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.PrimaryWorkingCapitalLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryWorkingCapitalLoanServiceImpl implements PrimaryWorkingCapitalLoanService {

	@Autowired
	private PrimaryWorkingCapitalLoanDetailRepository primaryWCRepository;

	@Override
	public boolean saveOrUpdate(PrimaryWorkingCapitalLoanRequest capitalLoanRequest) {
		// ID must not be null
		PrimaryWorkingCapitalLoanDetail capitalLoanDetail = primaryWCRepository.findOne(capitalLoanRequest.getId());
		if (capitalLoanDetail == null) {
			return false;
		}
		BeanUtils.copyProperties(capitalLoanRequest, capitalLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		capitalLoanDetail.setModifiedBy(capitalLoanRequest.getUserId());
		capitalLoanDetail.setModifiedDate(new Date());
		primaryWCRepository.save(capitalLoanDetail);
		return true;
	}

	@Override
	public PrimaryWorkingCapitalLoanRequest get(Long id) {
		PrimaryWorkingCapitalLoanDetail loanDetail = primaryWCRepository.findOne(id);
		if (loanDetail == null) {
			return null;
		}
		PrimaryWorkingCapitalLoanRequest capitalLoanRequest = new PrimaryWorkingCapitalLoanRequest();
		BeanUtils.copyProperties(loanDetail, capitalLoanRequest);
		return capitalLoanRequest;
	}
}
