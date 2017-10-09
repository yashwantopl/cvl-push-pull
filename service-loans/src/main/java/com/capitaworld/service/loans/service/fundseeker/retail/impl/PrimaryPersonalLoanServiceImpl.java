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
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryPersonalLoanDetail;
import com.capitaworld.service.loans.model.retail.PrimaryPersonalLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.FsNegativeFpListRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryPersonalLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryPersonalLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryPersonalLoanServiceImpl implements PrimaryPersonalLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryPersonalLoanServiceImpl.class.getName());

	@Autowired
	private PrimaryPersonalLoanDetailRepository personalLoanDetailRepository;
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;

	@Autowired
	private FsNegativeFpListRepository fsNegativeFpListRepository;
	
	@Override
	public boolean saveOrUpdate(PrimaryPersonalLoanRequest personalLoanRequest, Long userId) throws Exception {
		// ID must not be null
		try {
			PrimaryPersonalLoanDetail primaryPersonalLoanDetail = personalLoanDetailRepository
					.getByApplicationAndUserId(personalLoanRequest.getId(), (CommonUtils.isObjectNullOrEmpty(personalLoanRequest.getClientId()) ? userId : personalLoanRequest.getClientId()));
			if (primaryPersonalLoanDetail == null) {
				throw new NullPointerException("PrimaryPersonalLoanDetail not exist in DB with ID=>"
						+ personalLoanRequest.getId() + " and User Id ==>" + userId);
			}
			BeanUtils.copyProperties(personalLoanRequest, primaryPersonalLoanDetail,
					CommonUtils.IgnorableCopy.CORPORATE);
			primaryPersonalLoanDetail.setTenure(CommonUtils.isObjectNullOrEmpty(personalLoanRequest.getTenure()) ? null : (personalLoanRequest.getTenure() * 12));
			primaryPersonalLoanDetail.setIsActive(true);
			primaryPersonalLoanDetail.setModifiedBy(userId);
			primaryPersonalLoanDetail.setModifiedDate(new Date());
			personalLoanDetailRepository.save(primaryPersonalLoanDetail);
			//save negative list
			fsNegativeFpListRepository.inActiveMappingByApplicationId(primaryPersonalLoanDetail.getApplicationId().getId());
			saveNegativeList(primaryPersonalLoanDetail.getApplicationId().getId(), personalLoanRequest.getNegativeList());
			return true;
		} catch (Exception e) {
			logger.error("Error while saving PrimaryCarLoan Details");
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
	public PrimaryPersonalLoanRequest get(Long applicationId, Long userId) throws Exception {
		try {
			PrimaryPersonalLoanDetail loanDetail = personalLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
			if (loanDetail == null) {
				throw new NullPointerException(
						"PrimaryPersonalLoanDetail not exist in DB with ID=>" + applicationId + " and User Id ==>" + userId);
			}
			PrimaryPersonalLoanRequest personalLoanRequest = new PrimaryPersonalLoanRequest();
			//get fp negative list
			personalLoanRequest.setNegativeList(fsNegativeFpListRepository.getListByApplicationId(applicationId));
			BeanUtils.copyProperties(loanDetail, personalLoanRequest);
			personalLoanRequest.setTenure(CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? null : (loanDetail.getTenure() / 12));
			Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
			personalLoanRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
			return personalLoanRequest;
		} catch (Exception e) {
			logger.error("Error while saving PrimaryCarLoan Details");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
}
