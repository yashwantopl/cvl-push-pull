package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.FinalHomeLoanDetail;
import com.capitaworld.service.loans.model.retail.FinalHomeLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.FinalHomeLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.FinalHomeLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FinalHomeLoanServiceImpl implements FinalHomeLoanService {

	@Autowired
	private FinalHomeLoanDetailRepository finalHomeLoanDetailRepository;


	@Override
	public boolean saveOrUpdate(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest) {
		FinalHomeLoanDetail finalHomeLoanDetail= null;
		if (finalHomeLoanDetailRequest.getId() != null && finalHomeLoanDetailRequest.getApplicationId() != null) {
			finalHomeLoanDetail = finalHomeLoanDetailRepository.getByApplicationID(finalHomeLoanDetailRequest.getApplicationId(),finalHomeLoanDetailRequest.getId());
			if (finalHomeLoanDetail == null) {
				return false;
			}
			
		} else {
			finalHomeLoanDetail = new FinalHomeLoanDetail();
			finalHomeLoanDetail.setCreatedBy(finalHomeLoanDetailRequest.getUserId());
			finalHomeLoanDetail.setCreatedDate(new Date());
			finalHomeLoanDetail.setIsActive(true);
			finalHomeLoanDetail.setApplicationId(new LoanApplicationMaster(finalHomeLoanDetailRequest.getApplicationId()));
			
		}
		finalHomeLoanDetail.setModifiedBy(finalHomeLoanDetailRequest.getUserId());
		finalHomeLoanDetail.setModifiedDate(new Date());
		BeanUtils.copyProperties(finalHomeLoanDetailRequest, finalHomeLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		finalHomeLoanDetail = finalHomeLoanDetailRepository.save(finalHomeLoanDetail);

		
		return true;
	}

	@Override
	public FinalHomeLoanDetailRequest get(Long id) {
		FinalHomeLoanDetail loanDetail = finalHomeLoanDetailRepository.findOne(id);
		if (loanDetail == null) {
			return null;
		}
		FinalHomeLoanDetailRequest finalHomeLoanDetailRequest = new FinalHomeLoanDetailRequest();
		BeanUtils.copyProperties(loanDetail, finalHomeLoanDetailRequest);
		return finalHomeLoanDetailRequest;
	}

	
}
