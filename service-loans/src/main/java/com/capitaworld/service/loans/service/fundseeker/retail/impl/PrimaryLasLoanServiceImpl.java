package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLasLoanDetail;
import com.capitaworld.service.loans.model.PrimaryLasLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryLasLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryLasLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryLasLoanServiceImpl implements PrimaryLasLoanService {

	@Autowired
	private PrimaryLasLoanDetailRepository primaryLasLoanDetailRepository;

	@Override
	public boolean saveOrUpdate(PrimaryLasLoanDetailRequest lasLoanDetailRequest) {
		// ID must not be null
		PrimaryLasLoanDetail primaryLasLoanDetail= primaryLasLoanDetailRepository.findOne(lasLoanDetailRequest.getId());
		if (primaryLasLoanDetail == null) {
			return false;
		}
		BeanUtils.copyProperties(lasLoanDetailRequest, primaryLasLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		primaryLasLoanDetail.setModifiedBy(lasLoanDetailRequest.getUserId());
		primaryLasLoanDetail.setModifiedDate(new Date());
		primaryLasLoanDetailRepository.save(primaryLasLoanDetail);
		return true;
	}

	@Override
	public PrimaryLasLoanDetailRequest get(Long id) {
		PrimaryLasLoanDetail loanDetail = primaryLasLoanDetailRepository.findOne(id);
		if (loanDetail == null) {
			return null;
		}
		PrimaryLasLoanDetailRequest lasLoanDetailRequest= new PrimaryLasLoanDetailRequest();
		BeanUtils.copyProperties(loanDetail, lasLoanDetailRequest);
		return lasLoanDetailRequest;
	}
}
