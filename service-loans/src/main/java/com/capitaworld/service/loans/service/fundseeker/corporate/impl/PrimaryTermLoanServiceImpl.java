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
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryTermLoanDetail;
import com.capitaworld.service.loans.model.corporate.PrimaryTermLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.FsNegativeFpListRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryTermLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PrimaryTermLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryTermLoanServiceImpl implements PrimaryTermLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryTermLoanServiceImpl.class.getName());

	@Autowired
	private PrimaryTermLoanDetailRepository primaryTLRepository;
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private FsNegativeFpListRepository fsNegativeFpListRepository; 

	@Override
	public boolean saveOrUpdate(PrimaryTermLoanRequest termLoanRequest, Long userId) throws Exception {
		try {
			PrimaryTermLoanDetail termLoanDetail = primaryTLRepository
					.getByApplicationAndUserId(termLoanRequest.getId(),  (CommonUtils.isObjectNullOrEmpty(termLoanRequest.getClientId()) ? userId : termLoanRequest.getClientId()));
			if (termLoanDetail == null) {
				throw new NullPointerException("PrimaryTermLoanDetail not exist in DB with ID=>"
						+ termLoanRequest.getId() + " and UserId==>" + userId);
			}
			BeanUtils.copyProperties(termLoanRequest, termLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
			/*termLoanDetail.setTenure(CommonUtils.isObjectNullOrEmpty(termLoanRequest.getTenure()) ? null
					: (termLoanRequest.getTenure() * 12));*/
			termLoanDetail.setModifiedBy(userId);
			termLoanDetail.setModifiedDate(new Date());
			primaryTLRepository.save(termLoanDetail);
			/*//save negative list
			fsNegativeFpListRepository.inActiveMappingByApplicationId(termLoanDetail.getApplicationId().getId());
			saveNegativeList(termLoanDetail.getApplicationId().getId(), termLoanRequest.getNegativeList());*/
			return true;
		} catch (Exception e) {
			logger.error("Error while Primary Term Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	/*private void saveNegativeList(Long id, List<Long> negativeList) {
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
		
	}*/
	
	@Override
	public PrimaryTermLoanRequest get(Long applicationId, Long userId) throws Exception {
		try {
			PrimaryTermLoanDetail loanDetail = primaryTLRepository.getByApplicationAndUserId(applicationId, userId);
			if (loanDetail == null) {
				throw new NullPointerException(
						"PrimaryTermLoanDetail not exist in DB with ID=>" + applicationId + " and UserId==>" + userId);
			}
			PrimaryTermLoanRequest termLoanRequest = new PrimaryTermLoanRequest();
			//get fp negative list
			//termLoanRequest.setNegativeList(fsNegativeFpListRepository.getListByApplicationId(applicationId));
			BeanUtils.copyProperties(loanDetail, termLoanRequest);
			/*termLoanRequest.setTenure(
					CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? null : (loanDetail.getTenure() / 12));*/
			JSONObject result = loanApplicationService.getCurrencyAndDenomination(applicationId,userId);
			String data = result.get("currency").toString();
			data = data.concat(" In "+ result.get("denomination").toString());
			termLoanRequest.setCurrencyValue(data);
			return termLoanRequest;
		} catch (Exception e) {
			logger.error("Error while Primary Term Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}
}
