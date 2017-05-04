package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.model.PrimaryHomeLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryHomeLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryHomeLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryHomeLoanServiceImpl implements PrimaryHomeLoanService {

	@Autowired
	private PrimaryHomeLoanDetailRepository primaryHomeLoanDetailRepository;

	@Override
	public boolean saveOrUpdate(PrimaryHomeLoanDetailRequest homeLoanDetailRequest) {
		// ID must not be null
		PrimaryHomeLoanDetail primaryHomeLoanDetail= primaryHomeLoanDetailRepository.findOne(homeLoanDetailRequest.getId());
		if (primaryHomeLoanDetail == null) {
			return false;
		}
		BeanUtils.copyProperties(homeLoanDetailRequest, primaryHomeLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		primaryHomeLoanDetail.setModifiedBy(homeLoanDetailRequest.getUserId());
		primaryHomeLoanDetail.setModifiedDate(new Date());
		primaryHomeLoanDetail.setIsActive(true);
		primaryHomeLoanDetailRepository.save(primaryHomeLoanDetail);
		return true;
	}

	@Override
	public PrimaryHomeLoanDetailRequest get(Long id) {
		PrimaryHomeLoanDetail loanDetail = primaryHomeLoanDetailRepository.findOne(id);
		if (loanDetail == null) {
			return null;
		}
		PrimaryHomeLoanDetailRequest primaryHomeLoanDetailRequest= new PrimaryHomeLoanDetailRequest();
		BeanUtils.copyProperties(loanDetail, primaryHomeLoanDetailRequest);
		return primaryHomeLoanDetailRequest;
	}
}
