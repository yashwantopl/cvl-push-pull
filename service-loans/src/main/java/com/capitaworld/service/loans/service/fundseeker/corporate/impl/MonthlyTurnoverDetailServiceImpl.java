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

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.MonthlyTurnoverDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.MonthlyTurnoverDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.MonthlyTurnoverDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.MonthlyTurnoverDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;
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

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				MonthlyTurnoverDetailRequest monthlyTurnoverDetailRequest = (MonthlyTurnoverDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, MonthlyTurnoverDetailRequest.class);
				MonthlyTurnoverDetail monthlyTurnoverDetail = null;

				if (monthlyTurnoverDetailRequest.getId() != null) {
					monthlyTurnoverDetail = monthlyTurnoverDetailsRepository
							.findOne(monthlyTurnoverDetailRequest.getId());
				} else {
					monthlyTurnoverDetail = new MonthlyTurnoverDetail();
					monthlyTurnoverDetail.setCreatedBy(frameRequest.getUserId());
					monthlyTurnoverDetail.setCreatedDate(new Date());
				}
				BeanUtils.copyProperties(monthlyTurnoverDetailRequest, monthlyTurnoverDetail);
				monthlyTurnoverDetail.setApplicationId(new LoanApplicationMaster(frameRequest.getApplicationId()));
				monthlyTurnoverDetail.setModifiedBy(frameRequest.getUserId());
				monthlyTurnoverDetail.setModifiedDate(new Date());
				monthlyTurnoverDetailsRepository.save(monthlyTurnoverDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save monthlyTurnoverDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<MonthlyTurnoverDetailRequest> getMonthlyTurnoverDetailList(Long id,Long userId) throws Exception {
		try {
			List<MonthlyTurnoverDetail> monthlyTurnoverDetails = monthlyTurnoverDetailsRepository
					.listMonthlyTurnoverFromAppId(id,userId);
			List<MonthlyTurnoverDetailRequest> monthlyTurnoverDetailRequests = new ArrayList<MonthlyTurnoverDetailRequest>();

			for (MonthlyTurnoverDetail detail : monthlyTurnoverDetails) {
				MonthlyTurnoverDetailRequest monthlyTurnoverDetailRequest = new MonthlyTurnoverDetailRequest();
				BeanUtils.copyProperties(detail, monthlyTurnoverDetailRequest);
				monthlyTurnoverDetailRequests.add(monthlyTurnoverDetailRequest);
			}
			return monthlyTurnoverDetailRequests;
		}

		catch (Exception e) {
			logger.info("Exception  in get List monthlyTurnoverDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
