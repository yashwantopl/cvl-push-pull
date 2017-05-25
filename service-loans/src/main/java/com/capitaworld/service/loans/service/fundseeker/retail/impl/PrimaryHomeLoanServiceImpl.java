package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.model.retail.PrimaryHomeLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryHomeLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryHomeLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryHomeLoanServiceImpl implements PrimaryHomeLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryHomeLoanServiceImpl.class.getName());
	
	@Autowired
	private PrimaryHomeLoanDetailRepository primaryHomeLoanDetailRepository;

	@Override
	public boolean saveOrUpdate(PrimaryHomeLoanDetailRequest homeLoanDetailRequest,Long userId) throws Exception {
		// ID must not be null
		try{
		PrimaryHomeLoanDetail primaryHomeLoanDetail= primaryHomeLoanDetailRepository.getByApplicationAndUserId(homeLoanDetailRequest.getId(),(CommonUtils.isObjectNullOrEmpty(homeLoanDetailRequest.getClientId()) ? userId : homeLoanDetailRequest.getClientId()));
		if (primaryHomeLoanDetail == null) {
			throw new NullPointerException(
					"PrimaryHomeLoanDetail not exist in DB with Application Id=>" + homeLoanDetailRequest.getId() + " and user Id ==>" + userId); 
		}
		BeanUtils.copyProperties(homeLoanDetailRequest, primaryHomeLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		primaryHomeLoanDetail.setTenure(CommonUtils.isObjectNullOrEmpty(homeLoanDetailRequest.getTenure()) ? null : (homeLoanDetailRequest.getTenure() * 12));
		primaryHomeLoanDetail.setModifiedBy(userId);
		primaryHomeLoanDetail.setModifiedDate(new Date());
		primaryHomeLoanDetail.setIsActive(true);
		primaryHomeLoanDetailRepository.save(primaryHomeLoanDetail);
		return true;
		} catch (Exception e) {
			logger.error("Error while saving Primary Working Details Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public PrimaryHomeLoanDetailRequest get(Long applicationId,Long userId) throws Exception {
		try{
		PrimaryHomeLoanDetail loanDetail = primaryHomeLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
		if (loanDetail == null) {
			throw new NullPointerException("PrimaryHomeLoanDetail not exist in DB with applicationId=>" + applicationId + " and UserId==>"+ userId);
		}
		PrimaryHomeLoanDetailRequest primaryHomeLoanDetailRequest= new PrimaryHomeLoanDetailRequest();
		BeanUtils.copyProperties(loanDetail, primaryHomeLoanDetailRequest);
		primaryHomeLoanDetailRequest.setTenure(CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? null : (loanDetail.getTenure() / 12));
		return primaryHomeLoanDetailRequest;
		} catch (Exception e) {
			logger.error("Error while getting Primary Home Loan Details Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
}
