package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.model.corporate.PrimaryWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryWorkingCapitalLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.PrimaryWorkingCapitalLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryWorkingCapitalLoanServiceImpl implements PrimaryWorkingCapitalLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryWorkingCapitalLoanServiceImpl.class.getName());

	@Autowired
	private PrimaryWorkingCapitalLoanDetailRepository primaryWCRepository;

	@Override
	public boolean saveOrUpdate(PrimaryWorkingCapitalLoanRequest capitalLoanRequest, Long userId) throws Exception {
		try {
			// ID must not be null
			PrimaryWorkingCapitalLoanDetail capitalLoanDetail = primaryWCRepository
					.getByApplicationAndUserId(capitalLoanRequest.getId(), (CommonUtils.isObjectNullOrEmpty(capitalLoanRequest.getClientId()) ? userId : capitalLoanRequest.getClientId()));
			if (capitalLoanDetail == null) {
				throw new NullPointerException("PrimaryWorkingDetail not exist in DB with ID=>"
						+ capitalLoanRequest.getId() + " and userId==>" + userId);
			}
			BeanUtils.copyProperties(capitalLoanRequest, capitalLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
			capitalLoanDetail.setTenure(CommonUtils.isObjectNullOrEmpty(capitalLoanRequest.getTenure()) ? null
					: (capitalLoanRequest.getTenure() * 12));
			capitalLoanDetail.setModifiedBy(capitalLoanRequest.getUserId());
			capitalLoanDetail.setModifiedDate(new Date());
			primaryWCRepository.save(capitalLoanDetail);
			return true;
		} catch (Exception e) {
			logger.error("Error while Primary Working Details Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public PrimaryWorkingCapitalLoanRequest get(Long id, Long userId) throws Exception {
		try {
			PrimaryWorkingCapitalLoanDetail loanDetail = primaryWCRepository.getByApplicationAndUserId(id, userId);
			if (loanDetail == null) {
				throw new NullPointerException(
						"PrimaryWorkingCapitalLoanDetail not exist in DB with ID=>" + id + " with user Id==>" + userId);
			}
			PrimaryWorkingCapitalLoanRequest capitalLoanRequest = new PrimaryWorkingCapitalLoanRequest();
			BeanUtils.copyProperties(loanDetail, capitalLoanRequest);
			capitalLoanRequest.setTenure(
					CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? null : (loanDetail.getTenure() / 12));
			return capitalLoanRequest;
		} catch (Exception e) {
			logger.error("Error while Getting Working Details Profile:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}
}
