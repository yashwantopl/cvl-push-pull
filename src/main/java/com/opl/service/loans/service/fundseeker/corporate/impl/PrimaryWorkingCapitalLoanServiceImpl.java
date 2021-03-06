package com.opl.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.corporate.PrimaryWorkingCapitalLoanRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.service.loans.domain.fundseeker.FsNegativeFpList;
import com.opl.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.opl.service.loans.repository.fundseeker.FsNegativeFpListRepository;
import com.opl.service.loans.repository.fundseeker.corporate.PrimaryWorkingCapitalLoanDetailRepository;
import com.opl.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.opl.service.loans.service.fundseeker.corporate.PrimaryWorkingCapitalLoanService;

@Service
@Transactional
public class PrimaryWorkingCapitalLoanServiceImpl implements PrimaryWorkingCapitalLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryWorkingCapitalLoanServiceImpl.class.getName());

	@Autowired
	private PrimaryWorkingCapitalLoanDetailRepository primaryWCRepository;
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private FsNegativeFpListRepository fsNegativeFpListRepository; 

	@Override
	public boolean saveOrUpdate(PrimaryWorkingCapitalLoanRequest capitalLoanRequest, Long userId) throws LoansException {
		try {
			// ID must not be null
			PrimaryWorkingCapitalLoanDetail capitalLoanDetail = primaryWCRepository
					.getByApplicationAndUserId(capitalLoanRequest.getId(), (CommonUtils.isObjectNullOrEmpty(capitalLoanRequest.getClientId()) ? userId : capitalLoanRequest.getClientId()));
			if (capitalLoanDetail == null) {
				throw new NullPointerException("PrimaryWorkingDetail not exist in DB with ID=>"
						+ capitalLoanRequest.getId() + " and userId==>" + userId);
			}
			BeanUtils.copyProperties(capitalLoanRequest, capitalLoanDetail, CommonUtils.IgnorableCopy.getCORPORATE());
			capitalLoanDetail.setTenure(CommonUtils.isObjectNullOrEmpty(capitalLoanRequest.getTenure()) ? null
					: (capitalLoanRequest.getTenure() * 12));
			capitalLoanDetail.setModifiedBy(capitalLoanRequest.getUserId());
			capitalLoanDetail.setModifiedDate(new Date());
			primaryWCRepository.save(capitalLoanDetail);
			//save negative list
			fsNegativeFpListRepository.inActiveMappingByApplicationId(capitalLoanDetail.getApplicationId().getId());
			saveNegativeList(capitalLoanDetail.getApplicationId().getId(), capitalLoanRequest.getNegativeList());
			return true;
		} catch (Exception e) {
			logger.error("Error while Primary Working Details Profile :- ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	private void saveNegativeList(Long id, List<Long> negativeList) {

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
	public PrimaryWorkingCapitalLoanRequest get(Long id, Long userId) throws LoansException {
		try {
			PrimaryWorkingCapitalLoanDetail loanDetail = null;
			if(userId != null) {
				loanDetail = primaryWCRepository.getByApplicationAndUserId(id, userId);				
			}else {
				loanDetail = primaryWCRepository.findByApplicationIdIdAndIsActive(id,true);
			}
			if (loanDetail == null) {
				throw new NullPointerException(
						"PrimaryWorkingCapitalLoanDetail not exist in DB with ID=>" + id + " with user Id==>" + userId);
			}
			PrimaryWorkingCapitalLoanRequest capitalLoanRequest = new PrimaryWorkingCapitalLoanRequest();
			//get fp negative list
			capitalLoanRequest.setNegativeList(fsNegativeFpListRepository.getListByApplicationId(id));
			BeanUtils.copyProperties(loanDetail, capitalLoanRequest);
			capitalLoanRequest.setTenure(
					CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? null : (loanDetail.getTenure() / 12));
			JSONObject result = loanApplicationService.getCurrencyAndDenomination(id,userId);
			String data = result.get("currency").toString();
			data = data.concat(" In "+ result.get("denomination").toString());
			capitalLoanRequest.setCurrencyValue(data);
			return capitalLoanRequest;
		} catch (Exception e) {
			logger.error("Error while Getting Working Details Profile :- ",e);
			throw new LoansException("Something went Wrong !");
		}
	}
}
