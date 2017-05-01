package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryPersonalLoanDetail;
import com.capitaworld.service.loans.model.PrimaryPersonalLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryPersonalLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryPersonalLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryPersonalLoanServiceImpl implements PrimaryPersonalLoanService {

	@Autowired
	private PrimaryPersonalLoanDetailRepository personalLoanDetailRepository;

	@Override
	public boolean saveOrUpdate(PrimaryPersonalLoanRequest personalLoanRequest) {
		// ID must not be null
		PrimaryPersonalLoanDetail primaryPersonalLoanDetail= personalLoanDetailRepository.findOne(personalLoanRequest.getId());
		if (primaryPersonalLoanDetail == null) {
			return false;
		}
		BeanUtils.copyProperties(personalLoanRequest, primaryPersonalLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		primaryPersonalLoanDetail.setModifiedBy(personalLoanRequest.getUserId());
		primaryPersonalLoanDetail.setModifiedDate(new Date());
		personalLoanDetailRepository.save(primaryPersonalLoanDetail);
		return true;
	}

	@Override
	public PrimaryPersonalLoanRequest get(Long id) {
		PrimaryPersonalLoanDetail loanDetail = personalLoanDetailRepository.findOne(id);
		if (loanDetail == null) {
			return null;
		}
		PrimaryPersonalLoanRequest personalLoanRequest= new PrimaryPersonalLoanRequest();
		BeanUtils.copyProperties(loanDetail, personalLoanRequest);
		return personalLoanRequest;
	}
}
