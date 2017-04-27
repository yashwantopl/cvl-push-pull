package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.model.FinalWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.WorkingCapitalLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.WorkingCapitalLoanService;

@Service
@Transactional
public class WorkingCapitalLoanServiceImpl implements WorkingCapitalLoanService {

	private static final Logger logger = LoggerFactory.getLogger(WorkingCapitalLoanServiceImpl.class);

	@Autowired
	private WorkingCapitalLoanDetailRepository workingCapitalLoanDetailRepository;

	@Override
	public boolean saveOrUpdateFinalDetails(FinalWorkingCapitalLoanRequest capitalLoanRequest) {
		FinalWorkingCapitalLoanDetail capitalLoanDetail = null;
		if (capitalLoanRequest.getApplicationId() != null) {
			capitalLoanDetail = workingCapitalLoanDetailRepository.findOne(capitalLoanRequest.getApplicationId());
		} else {
			capitalLoanDetail = new FinalWorkingCapitalLoanDetail();
			capitalLoanDetail.setUserId(capitalLoanRequest.getUserId());
			capitalLoanDetail.setCreatedBy(1l);
			capitalLoanDetail.setIsActive(true);
			capitalLoanDetail.setCreatedDate(new Date());
		}

		capitalLoanDetail.setModifiedDate(new Date());
		capitalLoanDetail.setModifiedBy(1l);
		capitalLoanDetail.setCategoryCode(capitalLoanRequest.getCategoryCode());
		capitalLoanDetail.setName(capitalLoanRequest.getName());
		BeanUtils.copyProperties(capitalLoanRequest, capitalLoanDetail);
		workingCapitalLoanDetailRepository.save(capitalLoanDetail);
		return true;
	}

	@Override
	public FinalWorkingCapitalLoanRequest getFinalWorkingCapitalLoan(Long id) {
		FinalWorkingCapitalLoanRequest capitalLoanRequest = new FinalWorkingCapitalLoanRequest();
		BeanUtils.copyProperties(workingCapitalLoanDetailRepository.findOne(id), capitalLoanRequest);
		return capitalLoanRequest;

	}
}
