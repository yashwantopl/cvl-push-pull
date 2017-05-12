package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.FinalCarLoanDetail;
import com.capitaworld.service.loans.model.retail.FinalCarLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.FinalCarLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.FinalCarLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FinalCarLoanServiceImpl implements FinalCarLoanService {

	@Autowired
	private FinalCarLoanDetailRepository finalCarLoanDetailRepository;


	@Override
	public boolean saveOrUpdate(FinalCarLoanDetailRequest finalCarLoanDetailRequest) {
		FinalCarLoanDetail finalCarLoanDetail= null;
		if (finalCarLoanDetailRequest.getId() != null && finalCarLoanDetailRequest.getApplicationId() != null) {
			finalCarLoanDetail = finalCarLoanDetailRepository.getByApplicationID(finalCarLoanDetailRequest.getApplicationId(),finalCarLoanDetailRequest.getId());
			if (finalCarLoanDetail == null) {
				return false;
			}
			
		} else {
			finalCarLoanDetail = new FinalCarLoanDetail();
			finalCarLoanDetail.setCreatedBy(finalCarLoanDetailRequest.getUserId());
			finalCarLoanDetail.setCreatedDate(new Date());
			finalCarLoanDetail.setIsActive(true);
			finalCarLoanDetail.setApplicationId(new LoanApplicationMaster(finalCarLoanDetailRequest.getApplicationId()));
			
		}
		finalCarLoanDetail.setModifiedBy(finalCarLoanDetailRequest.getUserId());
		finalCarLoanDetail.setModifiedDate(new Date());
		BeanUtils.copyProperties(finalCarLoanDetailRequest, finalCarLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		finalCarLoanDetail = finalCarLoanDetailRepository.save(finalCarLoanDetail);

		
		return true;
	}

	@Override
	public FinalCarLoanDetailRequest get(Long id) {
		FinalCarLoanDetail loanDetail = finalCarLoanDetailRepository.findOne(id);
		if (loanDetail == null) {
			return null;
		}
		FinalCarLoanDetailRequest finalCarLoanDetailRequest = new FinalCarLoanDetailRequest();
		BeanUtils.copyProperties(loanDetail, finalCarLoanDetailRequest);
		return finalCarLoanDetailRequest;
	}

	
}
