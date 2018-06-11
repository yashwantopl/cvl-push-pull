package com.capitaworld.service.loans.service.common.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.common.UserLoanAmountMapping;
import com.capitaworld.service.loans.model.common.UserLoanAmountMappingRequest;
import com.capitaworld.service.loans.repository.common.UserLoanAmountMappingRepository;
import com.capitaworld.service.loans.service.common.UserLoanAmountMappingService;
import com.capitaworld.service.loans.utils.CommonUtils;

/**
 * @author harshit
 * Date : 11-Jun-2018
 */
@Service
@Transactional
public class UserLoanAmountMappingServiceImpl implements UserLoanAmountMappingService {

	private static final Logger logger = LoggerFactory.getLogger(UserLoanAmountMappingServiceImpl.class);
	
	@Autowired
	private UserLoanAmountMappingRepository amountMappingRepository;
	
	/**
	 * ENTRY IN USER PRODUCT AMOUNT MAPPIUNG TABLE
	 * @param amountMappingRequest
	 * @return
	 */
	@Override
	public Long save(UserLoanAmountMappingRequest amountMappingRequest) {
		logger.info("ENTER IN SAVE USER LOAN AMOUNT MAPPING SERVICE CLASS------------>" + amountMappingRequest.toString());
		UserLoanAmountMapping amountMapping = null;
		
		if(!CommonUtils.isObjectNullOrEmpty(amountMappingRequest.getUserId()) &&
				!CommonUtils.isObjectNullOrEmpty(amountMappingRequest.getProductId())) {
			logger.info("UPDATE DETAILS BY THIS ID _---------->" + amountMappingRequest.getUserId());
			amountMapping = amountMappingRepository.findByUserIdAndProductIdAndIsActive(amountMappingRequest.getUserId(), amountMappingRequest.getProductId(), true);
			amountMapping.setModifiedBy(amountMappingRequest.getUserId());
			amountMapping.setModifiedDate(new Date());
			amountMapping.setIsActive(amountMappingRequest.getIsActive());
		} else {
			logger.info("CREATE NEW ROW FOR THIS USER ID --------------------->" + amountMappingRequest.getUserId());
			amountMapping = new UserLoanAmountMapping();
			amountMapping.setCreatedBy(amountMappingRequest.getUserId());
			amountMapping.setCreatedDate(new Date());
			amountMapping.setIsActive(true);
		}
		amountMapping.setMaxAmount(amountMappingRequest.getMaxAmount());
		amountMapping.setMinAmount(amountMappingRequest.getMinAmount());
		amountMapping.setProductId(amountMappingRequest.getProductId());
		amountMapping.setUserId(amountMappingRequest.getUserId());
		
		amountMapping = amountMappingRepository.save(amountMapping);
		
		return amountMapping.getId();
	}
	
	/**
	 * CHEKC AMOUNT IS FOR PERTICULAR USER OR NOT
	 * @param userId
	 * @param amount
	 * @return TRUE :- AMOUNT IS RIGHT FOR USER , FALSE :- AMOUNT IS NOT RIGHT FOR USER  
	 */
	@Override
	public Boolean checkAmountByUserIdAndProductId(Long userId, Double amount,Long productId) {
		Long count = amountMappingRepository.checkAmountByUserId(amount, userId,productId);
		return count > 0;
	}
	
	@Override
	public UserLoanAmountMappingRequest getByUserIdAndProductId(Long userId, Long productId) {
		UserLoanAmountMapping userLoanAmountMapping = amountMappingRepository.findByUserIdAndProductIdAndIsActive(userId, productId, true);
		UserLoanAmountMappingRequest amountMappingRequest = null;
		if(!CommonUtils.isObjectNullOrEmpty(userLoanAmountMapping)) {
			amountMappingRequest = new UserLoanAmountMappingRequest();
			BeanUtils.copyProperties(userLoanAmountMapping, amountMappingRequest);
			return amountMappingRequest;
		}
		return null;
	}
	
}
