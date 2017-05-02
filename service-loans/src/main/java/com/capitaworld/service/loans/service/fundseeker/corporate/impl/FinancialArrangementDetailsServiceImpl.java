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

import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinancialArrangementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
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
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				FinancialArrangementsDetailRequest financialArrangementsDetailRequest = (FinancialArrangementsDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, FinancialArrangementsDetailRequest.class);
				FinancialArrangementsDetail financialArrangementsDetail = new FinancialArrangementsDetail();
				BeanUtils.copyProperties(financialArrangementsDetailRequest, financialArrangementsDetail);
				if (financialArrangementsDetailRequest.getId() == null) {
					financialArrangementsDetail.setCreatedBy(frameRequest.getUserId());
					financialArrangementsDetail.setCreatedDate(new Date());
				}
				financialArrangementsDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				financialArrangementsDetail.setModifiedBy(frameRequest.getUserId());
				financialArrangementsDetail.setModifiedDate(new Date());
				financialArrangementDetailsRepository.save(financialArrangementsDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save financialArrangementsDetail  :-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<FinancialArrangementsDetailRequest> getFinancialArrangementDetailsList(Long id) {
		List<FinancialArrangementsDetail> financialArrangementDetails = financialArrangementDetailsRepository
				.listSecurityCorporateDetailFromAppId(id);
		List<FinancialArrangementsDetailRequest> financialArrangementDetailRequests = new ArrayList<FinancialArrangementsDetailRequest>();

		for (int i = 0; i < financialArrangementDetails.size(); i++) {
			FinancialArrangementsDetailRequest financialArrangementDetailsRequest = new FinancialArrangementsDetailRequest();
			BeanUtils.copyProperties(financialArrangementDetails.get(i), financialArrangementDetailsRequest);
			financialArrangementDetailRequests.add(financialArrangementDetailsRequest);
		}
		return financialArrangementDetailRequests;
	}

}
