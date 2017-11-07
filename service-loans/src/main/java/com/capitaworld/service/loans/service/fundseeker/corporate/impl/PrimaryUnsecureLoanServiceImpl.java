package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.FsNegativeFpList;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryUnsecuredLoanDetail;
import com.capitaworld.service.loans.model.corporate.PrimaryUnsecureLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.FsNegativeFpListRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryUnsecuredLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PrimaryUnsecureLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryUnsecureLoanServiceImpl implements PrimaryUnsecureLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryUnsecureLoanServiceImpl.class.getName());

	@Autowired
	private PrimaryUnsecuredLoanDetailRepository primaryUSLRepository;
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private FsNegativeFpListRepository fsNegativeFpListRepository; 

	@Override
	public boolean saveOrUpdate(PrimaryUnsecureLoanRequest unsecureLoanRequest, Long userId) throws Exception {
		try {
			PrimaryUnsecuredLoanDetail unsecureLoanDetail = primaryUSLRepository
					.getByApplicationAndUserId(unsecureLoanRequest.getId(),  (CommonUtils.isObjectNullOrEmpty(unsecureLoanRequest.getClientId()) ? userId : unsecureLoanRequest.getClientId()));
			if (unsecureLoanDetail == null) {
				throw new NullPointerException("PrimaryUnsecureLoanDetail not exist in DB with ID=>"
						+ unsecureLoanRequest.getId() + " and UserId==>" + userId);
			}
			BeanUtils.copyProperties(unsecureLoanRequest, unsecureLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
			unsecureLoanDetail.setTenure(CommonUtils.isObjectNullOrEmpty(unsecureLoanRequest.getTenure()) ? null
					: (unsecureLoanRequest.getTenure() * 12));
			unsecureLoanDetail.setModifiedBy(userId);
			unsecureLoanDetail.setModifiedDate(new Date());
			primaryUSLRepository.save(unsecureLoanDetail);
			//save negative list
			fsNegativeFpListRepository.inActiveMappingByApplicationId(unsecureLoanDetail.getApplicationId().getId());
			saveNegativeList(unsecureLoanDetail.getApplicationId().getId(), unsecureLoanRequest.getNegativeList());
			return true;
		} catch (Exception e) {
			logger.error("Error while Primary Term Loan Details:-");
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
	public PrimaryUnsecureLoanRequest get(Long applicationId, Long userId) throws Exception {
		try {
			PrimaryUnsecuredLoanDetail loanDetail = primaryUSLRepository.getByApplicationAndUserId(applicationId, userId);
			if (loanDetail == null) {
				throw new NullPointerException(
						"PrimaryUnsecureLoanDetail not exist in DB with ID=>" + applicationId + " and UserId==>" + userId);
			}
			PrimaryUnsecureLoanRequest unsecureLoanRequest = new PrimaryUnsecureLoanRequest();
			//get fp negative list
			unsecureLoanRequest.setNegativeList(fsNegativeFpListRepository.getListByApplicationId(applicationId));
			BeanUtils.copyProperties(loanDetail, unsecureLoanRequest);
			unsecureLoanRequest.setTenure(
					CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? null : (loanDetail.getTenure() / 12));
			JSONObject result = loanApplicationService.getCurrencyAndDenomination(applicationId,userId);
			String data = result.get("currency").toString();
			data = data.concat(" In "+ result.get("denomination").toString());
			unsecureLoanRequest.setCurrencyValue(data);
			return unsecureLoanRequest;
		} catch (Exception e) {
			logger.error("Error while Primary Term Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}
}
