package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CollateralSecurityDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.corporate.CollateralSecurityDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CollateralSecurityDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CollateralSecurityDetailService;
import com.capitaworld.service.loans.service.teaser.primaryview.impl.CorporatePrimaryViewServiceImpl;

@Service
public class CollateralSecurityDetailServiceImpl implements CollateralSecurityDetailService{

	@Autowired
	CollateralSecurityDetailRepository collateralSecurityDetailRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CollateralSecurityDetailServiceImpl.class);
	
	@Override
	@Transactional
	public void saveData(List<CollateralSecurityDetailRequest> collateralSecurityDetailRequests, Long applicationId, Long userId) throws Exception {
		
		collateralSecurityDetailRepository.inActive(userId, applicationId);
		
		List<CollateralSecurityDetail> collateralSecurityDetails = new ArrayList<>();
		for (CollateralSecurityDetailRequest req : collateralSecurityDetailRequests) {
			CollateralSecurityDetail collateralSecurityDetail = new CollateralSecurityDetail();
			BeanUtils.copyProperties(req, collateralSecurityDetail, "id");
			collateralSecurityDetail.setApplicationId(applicationId);
			collateralSecurityDetail.setCreatedBy(userId);
			collateralSecurityDetail.setCreatedDate(new Date());
			collateralSecurityDetail.setIsActive(true);
			
			collateralSecurityDetails.add(collateralSecurityDetail);
		}
		collateralSecurityDetailRepository.save(collateralSecurityDetails);
	}

	@Override
	public List<CollateralSecurityDetailRequest> getData(Long applicationId){
		List<CollateralSecurityDetailRequest> response = new ArrayList<>();
		
		try {
			List<CollateralSecurityDetail> list = collateralSecurityDetailRepository.findByApplicationIdAndIsActive(applicationId, true);
			for(CollateralSecurityDetail collateralSecurityDetail : list) {
				CollateralSecurityDetailRequest request = new CollateralSecurityDetailRequest();
				BeanUtils.copyProperties(collateralSecurityDetail, request);
				response.add(request);
			}
		} catch (Exception e) {
			logger.info("Exception in getData "+e.getMessage());
		}
		
		return response;
	}

}
