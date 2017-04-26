package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.model.WorkingCapitalLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.WorkingCapitalLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.WorkingCapitalLoanService;

@Service
@Transactional
public class WorkingCapitalLoanServiceImpl implements WorkingCapitalLoanService {

	private static final Logger logger = LoggerFactory.getLogger(WorkingCapitalLoanServiceImpl.class);

	@Autowired
	private WorkingCapitalLoanDetailRepository workingCapitalLoanDetailRepository;

	@Override
	public boolean saveOrUpdate(WorkingCapitalLoanRequest capitalLoanRequest) {
		try {
			if (capitalLoanRequest.getApplicationId() != null) {
				workingCapitalLoanDetailRepository.updatePrimaryWorkingCapital(capitalLoanRequest.getAmount(),capitalLoanRequest.getName(), 1l,
						capitalLoanRequest.getCategoryCode(), capitalLoanRequest.getHaveExistingLimit(),
						capitalLoanRequest.getProjectBrief(), capitalLoanRequest.getCollateralSecurityAmtTotal(),
						capitalLoanRequest.getCurrencyId(), capitalLoanRequest.getDenominationId(),
						capitalLoanRequest.getApplicationId(),capitalLoanRequest.getCreditRatingId());
				return true;
			}
			
			FinalWorkingCapitalLoanDetail capitalLoanDetail = new FinalWorkingCapitalLoanDetail();
			BeanUtils.copyProperties(capitalLoanRequest, capitalLoanDetail);
			capitalLoanDetail.setAmount(capitalLoanRequest.getAmount());
			capitalLoanDetail.setProductId(capitalLoanRequest.getProductId());
			capitalLoanDetail.setTenure(capitalLoanRequest.getTenure());
			capitalLoanDetail.setUserId(capitalLoanRequest.getUserId());
			capitalLoanDetail.setCreatedBy(1l);
			capitalLoanDetail.setIsActive(true);
			capitalLoanDetail.setCreatedDate(new Date());
			capitalLoanDetail.setModifiedBy(1l);
			capitalLoanDetail.setModifiedDate(new Date());
			capitalLoanDetail.setCategoryCode(capitalLoanRequest.getCategoryCode());
			capitalLoanDetail.setName(capitalLoanRequest.getName());
			workingCapitalLoanDetailRepository.save(capitalLoanDetail);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public WorkingCapitalLoanRequest getWorkingCapitalLoan(Long id) {
		WorkingCapitalLoanRequest capitalLoanRequest = new WorkingCapitalLoanRequest();
		BeanUtils.copyProperties(workingCapitalLoanDetailRepository.findOne(id), capitalLoanRequest);
		return capitalLoanRequest;

	}
}
