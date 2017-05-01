package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalTermLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OverseasNetworkMappingDetail;
import com.capitaworld.service.loans.model.FinalTermLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinalTermLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OverseasNetworkRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinalTermLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FinalTermLoanServiceImpl implements FinalTermLoanService {

	@Autowired
	private FinalTermLoanDetailRepository termLoanDetailRepository;

	@Autowired
	private OverseasNetworkRepository networkRepository;

	@Override
	public boolean saveOrUpdate(FinalTermLoanRequest termLoanRequest) {
		FinalTermLoanDetail termLoanDetail = null;
		if (termLoanRequest.getId() != null && termLoanRequest.getApplicationId() != null) {
			termLoanDetail = termLoanDetailRepository.getByApplicationIDAndID(termLoanRequest.getApplicationId(),
					termLoanRequest.getId());
			if (termLoanDetail == null) {
				return false;
			}
			// Inactive Previous Mapping
			networkRepository.inActiveMappingByApplicationId(termLoanRequest.getApplicationId());
			termLoanDetail.setModifiedBy(termLoanRequest.getUserId());
			termLoanDetail.setModifiedDate(new Date());
		} else {
			termLoanDetail = new FinalTermLoanDetail();
			termLoanDetail.setCreatedBy(termLoanRequest.getUserId());
			termLoanDetail.setCreatedDate(new Date());
			termLoanDetail.setIsActive(true);
			termLoanDetail.setApplicationId(new LoanApplicationMaster(termLoanRequest.getApplicationId()));
		}
		BeanUtils.copyProperties(termLoanRequest, termLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		termLoanDetail = termLoanDetailRepository.save(termLoanDetail);

		// saving Data
		saveOverseasNetworkMapping(termLoanRequest.getApplicationId(), termLoanRequest.getUserId(),
				termLoanRequest.getOverseasNetworkIds());
		return true;
	}

	@Override
	public FinalTermLoanRequest get(Long id) {
		FinalTermLoanDetail loanDetail = termLoanDetailRepository.findOne(id);
		if (loanDetail == null) {
			return null;
		}
		FinalTermLoanRequest termLoanRequest = new FinalTermLoanRequest();
		BeanUtils.copyProperties(loanDetail, termLoanRequest);
		return termLoanRequest;
	}

	private void saveOverseasNetworkMapping(Long applicationId, Long userId, List<Integer> overseasNetworkIds) {
		for (Integer networkId : overseasNetworkIds) {
			OverseasNetworkMappingDetail mappingDetail = new OverseasNetworkMappingDetail();
			mappingDetail.setApplicationId(applicationId);
			mappingDetail.setOverseasNetworkId(networkId);
			mappingDetail.setActive(true);
			mappingDetail.setCreatedDate(new Date());
			mappingDetail.setCreatedBy(userId);
			networkRepository.save(mappingDetail);
		}
	}
}
