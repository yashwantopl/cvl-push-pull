package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLapLoanDetail;
import com.capitaworld.service.loans.model.PrimaryLapLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryLapLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryLapLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryLapLoanServiceImpl implements PrimaryLapLoanService {

	@Autowired
	private PrimaryLapLoanDetailRepository primaryLapLoanDetailRepository;

	@Override
	public boolean saveOrUpdate(PrimaryLapLoanDetailRequest lapLoanDetailRequest) {
		// ID must not be null
		PrimaryLapLoanDetail primaryLapLoanDetail= primaryLapLoanDetailRepository.findOne(lapLoanDetailRequest.getId());
		if (primaryLapLoanDetail == null) {
			return false;
		}
		BeanUtils.copyProperties(lapLoanDetailRequest, primaryLapLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		primaryLapLoanDetail.setModifiedBy(primaryLapLoanDetail.getUserId());
		primaryLapLoanDetail.setModifiedDate(new Date());
		primaryLapLoanDetailRepository.save(primaryLapLoanDetail);
		return true;
	}

	@Override
	public PrimaryLapLoanDetailRequest get(Long id) {
		PrimaryLapLoanDetail loanDetail = primaryLapLoanDetailRepository.findOne(id);
		if (loanDetail == null) {
			return null;
		}
		PrimaryLapLoanDetailRequest lapLoanDetailRequest= new PrimaryLapLoanDetailRequest();
		BeanUtils.copyProperties(loanDetail, lapLoanDetailRequest);
		return lapLoanDetailRequest;
	}
}
