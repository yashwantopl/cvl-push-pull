package com.capitaworld.service.loans.service.common.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.common.BankBureauResponse;
import com.capitaworld.service.loans.repository.common.BankBureauResponseRepository;
import com.capitaworld.service.loans.service.common.BankBureauResponseService;
import com.capitaworld.service.matchengine.model.BankBureauRequest;
import com.capitaworld.service.matchengine.utils.CommonUtils;

@Service
@Transactional
public class BankBureauResponseServiceImpl implements BankBureauResponseService {

	private static Logger logger = LoggerFactory.getLogger(BankBureauResponseServiceImpl.class);

	@Autowired
	private BankBureauResponseRepository bankBureauResponseRepository;
	
	@Override
	public boolean save(List<BankBureauRequest> bankBureauRequests) {
		if(CommonUtils.isListNullOrEmpty(bankBureauRequests)) {
			logger.warn("No Response available for Save");
			return true;
		}
		for(BankBureauRequest bankBureauRequest :  bankBureauRequests) {
			save(bankBureauRequest);
		}
		return true;
	}

	@Override
	public boolean inActiveAndsave(List<BankBureauRequest> bankBureauRequests, Integer type) {
		int inactiveByType = bankBureauResponseRepository.inactiveByType(type);
		logger.info("inactive Count for Type = >{}",inactiveByType);
		return save(bankBureauRequests);
	}
	@Override
	public boolean inActiveAndsave(BankBureauRequest bankBureauRequest) {
		int inactiveByType = bankBureauResponseRepository.inactiveByAppAndFpProductIdAndType(bankBureauRequest.getApplicationId(), bankBureauRequest.getFpProductId(), bankBureauRequest.getType());
		logger.info("inactive Count for Type Single Row = >{}",inactiveByType);
		return save(bankBureauRequest);
	}

	@Override
	public boolean save(BankBureauRequest bankBureauRequest) {
		BankBureauResponse bankBureauResponse = new BankBureauResponse();
		BeanUtils.copyProperties(bankBureauRequest, bankBureauResponse);
		bankBureauResponse.setIsActive(true);
		bankBureauResponse.setCreatedDate(new Date());
		bankBureauResponseRepository.save(bankBureauResponse);
		return true;
	}
	

	@Override
	public boolean inActiveAndsaveScoring(BankBureauRequest bankBureauRequest) {
		Integer inactiveByType = bankBureauResponseRepository.inactiveByAppAndScoringModelAndFieldMasterIdAndType(bankBureauRequest.getApplicationId(), bankBureauRequest.getScoringModelId(), bankBureauRequest.getFieldMasterId(), com.capitaworld.service.matchengine.utils.CommonUtils.BankBureauResponseType.SCORING.getId());
		logger.info("inactiveByAppAndScoringModelAndFieldMasterIdAndType Count Single Row = >{}",inactiveByType);
		return save(bankBureauRequest);
	}

	@Override
	public BankBureauRequest get(Long applicationId, Long fpProductId, Integer type) {
		BankBureauResponse bankBureauResponse = bankBureauResponseRepository.findFirstByApplicationIdAndFpProductIdAndTypeOrderByIdDesc(applicationId, fpProductId, type);
		if(CommonUtils.isObjectNullOrEmpty(bankBureauResponse)) {
			logger.warn("No Response Found for ApplicationId===>{}==>FpProductId==>{}==and Type ==>{}==>",applicationId,fpProductId,type);
			return null;
		}
		BankBureauRequest bankBureauRequest = new BankBureauRequest();
		BeanUtils.copyProperties(bankBureauResponse, bankBureauRequest);
		return bankBureauRequest;
	}
	
}
