package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalTermLoanDetail;
import com.capitaworld.service.loans.model.FinalTermLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinalTermLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinalTermLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FinalTermLoanServiceImpl implements FinalTermLoanService {

	@Autowired
	private FinalTermLoanDetailRepository termLoanDetailRepository;

	@Override
	public boolean saveOrUpdate(FinalTermLoanRequest termLoanRequest) {
		FinalTermLoanDetail termLoanDetail = termLoanDetailRepository.findOne(termLoanRequest.getId());
		termLoanDetail.setModifiedBy(termLoanRequest.getUserId());
		termLoanDetail.setModifiedDate(new Date());
		BeanUtils.copyProperties(termLoanRequest, termLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		termLoanDetail = termLoanDetailRepository.save(termLoanDetail);
		return true;
	}

	@Override
	public FinalTermLoanRequest get(Long id) {
		FinalTermLoanRequest termLoanRequest = new FinalTermLoanRequest();
		BeanUtils.copyProperties(termLoanDetailRepository.findOne(id), termLoanRequest);
		return termLoanRequest;
	}
}
