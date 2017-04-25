package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.TermLoanDetail;
import com.capitaworld.service.loans.model.TermLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.TermLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.TermLoanService;

@Service
@Transactional
public class TermLoanServiceImpl implements TermLoanService {

	private static final Logger logger = LoggerFactory.getLogger(TermLoanServiceImpl.class);

	@Autowired
	private TermLoanDetailRepository termLoanDetailRepository;

	@Override
	public boolean saveOrUpdate(TermLoanRequest termLoanRequest) {
		try {
			if (termLoanRequest.getApplicationId() != null) {
				termLoanDetailRepository.updateTermLoan(termLoanRequest.getName(), 1l,
						termLoanRequest.getCategoryCode(), termLoanRequest.getCurrencyId(),
						termLoanRequest.getDenominationId(), termLoanRequest.getAmount(), termLoanRequest.getTenure(),
						termLoanRequest.getProjectBrief(), termLoanRequest.getTotalCostOfEstimate(),
						termLoanRequest.getTotalMeansOfFinance(),termLoanRequest.getApplicationId(),termLoanRequest.getCreditRatingId());
				return true;
			}
			TermLoanDetail termLoanDetail = new TermLoanDetail();
			BeanUtils.copyProperties(termLoanRequest, termLoanDetail);
			termLoanDetail.setAmount(termLoanRequest.getAmount());
			termLoanDetail.setProductId(termLoanRequest.getProductId());
			termLoanDetail.setTenure(termLoanRequest.getTenure());
			termLoanDetail.setUserId(termLoanRequest.getUserId());
			termLoanDetail.setCreatedBy(1l);
			termLoanDetail.setIsActive(true);
			termLoanDetail.setCreatedDate(new Date());
			termLoanDetail.setModifiedBy(1l);
			termLoanDetail.setModifiedDate(new Date());
			termLoanDetail.setCategoryCode(termLoanRequest.getCategoryCode());
			termLoanDetail.setName(termLoanRequest.getName());
			termLoanDetail = termLoanDetailRepository.save(termLoanDetail);
			logger.info("Save Applicant ID == >" + termLoanDetail.getApplicationId().getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public TermLoanRequest getTermLoan(Long id) {
		TermLoanRequest termLoanRequest = new TermLoanRequest();
		BeanUtils.copyProperties(termLoanDetailRepository.findOne(id), termLoanRequest);
		return termLoanRequest;

	}
}
