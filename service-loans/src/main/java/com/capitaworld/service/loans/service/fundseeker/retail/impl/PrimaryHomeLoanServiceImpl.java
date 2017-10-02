package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.FsNegativeFpList;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.model.retail.PrimaryHomeLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.FsNegativeFpListRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryHomeLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryHomeLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryHomeLoanServiceImpl implements PrimaryHomeLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryHomeLoanServiceImpl.class.getName());
	
	@Autowired
	private PrimaryHomeLoanDetailRepository primaryHomeLoanDetailRepository;
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository; 

	@Autowired
	private FsNegativeFpListRepository fsNegativeFpListRepository;
	
	@Override
	public boolean saveOrUpdate(PrimaryHomeLoanDetailRequest homeLoanDetailRequest,Long userId) throws Exception {
		// ID must not be null
		try{
		Long finalUserId = (CommonUtils.isObjectNullOrEmpty(homeLoanDetailRequest.getClientId()) ? userId : homeLoanDetailRequest.getClientId());
		PrimaryHomeLoanDetail primaryHomeLoanDetail= primaryHomeLoanDetailRepository.getByApplicationAndUserId(homeLoanDetailRequest.getId(),finalUserId);
		if (primaryHomeLoanDetail == null) {
			throw new NullPointerException(
					"PrimaryHomeLoanDetail not exist in DB with Application Id=>" + homeLoanDetailRequest.getId() + " and user Id ==>" + userId); 
		}
		BeanUtils.copyProperties(homeLoanDetailRequest, primaryHomeLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
		primaryHomeLoanDetail.setTenure(CommonUtils.isObjectNullOrEmpty(homeLoanDetailRequest.getTenure()) ? null : (homeLoanDetailRequest.getTenure() * 12));
		primaryHomeLoanDetail.setModifiedBy(userId);
		primaryHomeLoanDetail.setModifiedDate(new Date());
		primaryHomeLoanDetail.setIsActive(true);
		primaryHomeLoanDetail = primaryHomeLoanDetailRepository.save(primaryHomeLoanDetail);
		
		//Updating Primary Flag
		loanApplicationRepository.setPrimaryFilledCount(primaryHomeLoanDetail.getId(), finalUserId, homeLoanDetailRequest.getPrimaryFilledCount());
		//save negative list
		fsNegativeFpListRepository.inActiveMappingByApplicationId(primaryHomeLoanDetail.getApplicationId().getId());
		saveNegativeList(primaryHomeLoanDetail.getApplicationId().getId(), homeLoanDetailRequest.getNegativeList());
		return true;
		} catch (Exception e) {
			logger.error("Error while saving Primary Working Details Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	private void saveNegativeList(Long id, List<Long> negativeList) {
		// TODO Auto-generated method stub
		FsNegativeFpList fsNegativeFpList= null;
		for (Long fpId : negativeList) {
			fsNegativeFpList = new FsNegativeFpList();
			fsNegativeFpList.setApplicationId(id);
			fsNegativeFpList.setFpId(fpId);
			fsNegativeFpList.setCreatedBy(id);
			fsNegativeFpList.setModifiedBy(id);
			fsNegativeFpList.setCreatedDate(new Date());
			fsNegativeFpList.setModifiedDate(new Date());
			fsNegativeFpList.setIsActive(true);
			// create by and update
			fsNegativeFpListRepository.save(fsNegativeFpList);
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
		//get fp negative list
		primaryHomeLoanDetailRequest.setNegativeList(fsNegativeFpListRepository.getListByApplicationId(applicationId));
		BeanUtils.copyProperties(loanDetail, primaryHomeLoanDetailRequest);
		primaryHomeLoanDetailRequest.setTenure(CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? null : (loanDetail.getTenure() / 12));
		Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
		primaryHomeLoanDetailRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
		return primaryHomeLoanDetailRequest;
		} catch (Exception e) {
			logger.error("Error while getting Primary Home Loan Details Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
}
