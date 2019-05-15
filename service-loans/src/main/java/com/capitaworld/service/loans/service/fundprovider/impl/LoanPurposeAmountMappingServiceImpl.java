
package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundprovider.LoanPurposeAmountMapping;
import com.capitaworld.service.loans.model.LoanPurposeAmountMappingRequest;
import com.capitaworld.service.loans.repository.fundprovider.LoanPurposeAmountMappingRepository;
import com.capitaworld.service.loans.service.fundprovider.LoanPurposeAmountMappingService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class LoanPurposeAmountMappingServiceImpl implements LoanPurposeAmountMappingService {
	private static final Logger logger = LoggerFactory.getLogger(LoanPurposeAmountMappingServiceImpl.class);

	@Autowired
	private LoanPurposeAmountMappingRepository loanPurposeAmountMappingRepository;

	@Override
	public Boolean save(Long fpProductId, Integer type, Double min, Double max) {
		LoanPurposeAmountMapping amountMapping = new LoanPurposeAmountMapping();
		amountMapping.setFpProductId(fpProductId);
		amountMapping.setMin(min);
		amountMapping.setMax(max);
		amountMapping.setIsActive(true);
		amountMapping.setType(type);
		loanPurposeAmountMappingRepository.save(amountMapping);
		return true;
	}

	@Override
	public int deleteByFpProductId(Long fpProductId) {
		return loanPurposeAmountMappingRepository.deleteExistingMapping(fpProductId);
	}

	@Override
	public Boolean save(List<LoanPurposeAmountMappingRequest> loanPurposeAmountMappingRequest, Long fpProductId) {
		for (LoanPurposeAmountMappingRequest amountMappingRequest : loanPurposeAmountMappingRequest) {
			save(fpProductId, amountMappingRequest.getType(), amountMappingRequest.getMin(),
					amountMappingRequest.getMax());
		}
		return true;
	}

	@Override
	public Boolean deleteAndSave(List<LoanPurposeAmountMappingRequest> loanPurposeAmountMappingRequest,
			Long fpProductId) {
		loanPurposeAmountMappingRepository.deleteExistingMapping(fpProductId);
		return save(loanPurposeAmountMappingRequest, fpProductId);
	}

	@Override
	public Boolean save(LoanPurposeAmountMappingRequest loanPurposeAmountMappingRequest) {
		return save(loanPurposeAmountMappingRequest.getFpProductId(), loanPurposeAmountMappingRequest.getType(), loanPurposeAmountMappingRequest.getMin(), loanPurposeAmountMappingRequest.getMax());
	}

	@Override
	public List<LoanPurposeAmountMappingRequest> getByFpProductId(Long fpProductId) {
		List<LoanPurposeAmountMapping> list = loanPurposeAmountMappingRepository.findByFpProductId(fpProductId);
		if(CommonUtils.isListNullOrEmpty(list)) {
			return Collections.emptyList();
		}
		LoanPurposeAmountMappingRequest amountMappingRequest = null;
		List<LoanPurposeAmountMappingRequest> amountMappingResponses = new ArrayList<>(list.size());
		for(LoanPurposeAmountMapping amountMapping :  list) {
			amountMappingRequest = new LoanPurposeAmountMappingRequest();
			BeanUtils.copyProperties(amountMapping, amountMappingRequest);
			amountMappingResponses.add(amountMappingRequest);
		}
		return amountMappingResponses;
	}
}
