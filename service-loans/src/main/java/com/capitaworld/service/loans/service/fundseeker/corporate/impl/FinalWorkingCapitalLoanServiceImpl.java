package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.model.FinalWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinalWorkingCapitalLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinalWorkingCapitalLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FinalWorkingCapitalLoanServiceImpl implements FinalWorkingCapitalLoanService {

	@Autowired
	private FinalWorkingCapitalLoanDetailRepository finalWCRepository;

	@Override
	public boolean saveOrUpdate(FinalWorkingCapitalLoanRequest capitalLoanRequest) {
		FinalWorkingCapitalLoanDetail capitalLoanDetail = null;
		if (capitalLoanRequest.getId() != null && capitalLoanRequest.getApplicationId() != null) {
			capitalLoanDetail = finalWCRepository.getByApplicationIDAndID(capitalLoanRequest.getApplicationId(),
					capitalLoanRequest.getId());
			capitalLoanDetail.setModifiedBy(capitalLoanRequest.getUserId());
			capitalLoanDetail.setModifiedDate(new Date());
		} else {
			capitalLoanDetail = new FinalWorkingCapitalLoanDetail();
			capitalLoanDetail.setCreatedBy(capitalLoanRequest.getUserId());
			capitalLoanDetail.setCreatedDate(new Date());
			capitalLoanDetail.setIsActive(true);
			capitalLoanDetail.setApplicationId(new LoanApplicationMaster(capitalLoanRequest.getApplicationId()));
		}
		BeanUtils.copyProperties(capitalLoanRequest, capitalLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		finalWCRepository.save(capitalLoanDetail);
		return true;
	}

	@Override
	public FinalWorkingCapitalLoanRequest get(Long id) {
		FinalWorkingCapitalLoanRequest capitalLoanRequest = new FinalWorkingCapitalLoanRequest();
		BeanUtils.copyProperties(finalWCRepository.findOne(id), capitalLoanRequest);
		return capitalLoanRequest;

	}
}
