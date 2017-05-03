package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.MonthlyTurnoverDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.MonthlyTurnoverDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.MonthlyTurnoverDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.MonthlyTurnoverDetailService;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class MonthlyTurnoverDetailServiceImpl implements MonthlyTurnoverDetailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MonthlyTurnoverDetailServiceImpl.class.getName());
	@Autowired
	private MonthlyTurnoverDetailRepository monthlyTurnoverDetailsRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	
	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				MonthlyTurnoverDetailRequest monthlyTurnoverDetailRequest = (MonthlyTurnoverDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, MonthlyTurnoverDetailRequest.class);
				MonthlyTurnoverDetail monthlyTurnoverDetail = new MonthlyTurnoverDetail();
				BeanUtils.copyProperties(monthlyTurnoverDetailRequest, monthlyTurnoverDetail);
				if (monthlyTurnoverDetailRequest.getId() == null) {
					monthlyTurnoverDetail.setCreatedBy(frameRequest.getUserId());
					monthlyTurnoverDetail.setCreatedDate(new Date());
				}
				monthlyTurnoverDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				monthlyTurnoverDetail.setModifiedBy(frameRequest.getUserId());
				monthlyTurnoverDetail.setModifiedDate(new Date());
				monthlyTurnoverDetailsRepository.save(monthlyTurnoverDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save monthlyTurnoverDetail  :-");
			e.printStackTrace();
			return false;
		}

	}


	@Override
	public List<MonthlyTurnoverDetailRequest> getMonthlyTurnoverDetailList(Long id) {

		List<MonthlyTurnoverDetail> monthlyTurnoverDetails = monthlyTurnoverDetailsRepository
				.listMonthlyTurnoverFromAppId(id);
		List<MonthlyTurnoverDetailRequest> monthlyTurnoverDetailRequests = new ArrayList<MonthlyTurnoverDetailRequest>();

		for (int i = 0; i < monthlyTurnoverDetails.size(); i++) {
			MonthlyTurnoverDetailRequest monthlyTurnoverDetailRequest = new MonthlyTurnoverDetailRequest();
			BeanUtils.copyProperties(monthlyTurnoverDetails.get(i), monthlyTurnoverDetailRequest);
			monthlyTurnoverDetailRequests.add(monthlyTurnoverDetailRequest);
		}
		return monthlyTurnoverDetailRequests;
	}

}
