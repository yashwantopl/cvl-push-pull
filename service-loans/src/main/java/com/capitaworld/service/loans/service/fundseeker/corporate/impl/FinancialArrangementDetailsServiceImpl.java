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
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinancialArrangementDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class FinancialArrangementDetailsServiceImpl implements FinancialArrangementDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(SecurityCorporateDetailsServiceImpl.class);

	@Autowired
	private FinancialArrangementDetailsRepository financialArrangementDetailsRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				FinancialArrangementsDetailRequest financialArrangementsDetailRequest = (FinancialArrangementsDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, FinancialArrangementsDetailRequest.class);
				FinancialArrangementsDetail financialArrangementsDetail = null;
				if (financialArrangementsDetailRequest.getId() != null) {
					financialArrangementsDetail = financialArrangementDetailsRepository
							.findOne(financialArrangementsDetailRequest.getId());
				} else {
					financialArrangementsDetail = new FinancialArrangementsDetail();
					financialArrangementsDetail.setCreatedBy(frameRequest.getUserId());
					financialArrangementsDetail.setCreatedDate(new Date());
				}
				BeanUtils.copyProperties(financialArrangementsDetailRequest, financialArrangementsDetail);
				financialArrangementsDetail
						.setApplicationId(new LoanApplicationMaster(frameRequest.getApplicationId()));
				financialArrangementsDetail.setModifiedBy(frameRequest.getUserId());
				financialArrangementsDetail.setModifiedDate(new Date());
				financialArrangementDetailsRepository.save(financialArrangementsDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save financialArrangementsDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<FinancialArrangementsDetailRequest> getFinancialArrangementDetailsList(Long id,Long userId)throws Exception {
		try {
			List<FinancialArrangementsDetail> financialArrangementDetails = financialArrangementDetailsRepository
					.listSecurityCorporateDetailFromAppId(id,userId);
			List<FinancialArrangementsDetailRequest> financialArrangementDetailRequests = new ArrayList<FinancialArrangementsDetailRequest>();

			for (FinancialArrangementsDetail detail : financialArrangementDetails) {
				FinancialArrangementsDetailRequest financialArrangementDetailsRequest = new FinancialArrangementsDetailRequest();
				BeanUtils.copyProperties(detail, financialArrangementDetailsRequest);
				financialArrangementDetailRequests.add(financialArrangementDetailsRequest);
			}
			return financialArrangementDetailRequests;
		}

		catch (Exception e) {
			logger.info("Exception  in save financialArrangementsDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
