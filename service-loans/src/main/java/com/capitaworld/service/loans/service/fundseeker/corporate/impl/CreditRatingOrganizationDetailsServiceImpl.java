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

import com.capitaworld.service.loans.domain.fundseeker.corporate.CreditRatingOrganizationDetail;
import com.capitaworld.service.loans.model.CreditRatingOrganizationDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CreditRatingOrganizationDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CreditRatingOrganizationDetailsService;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class CreditRatingOrganizationDetailsServiceImpl implements CreditRatingOrganizationDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CreditRatingOrganizationDetailsServiceImpl.class);
	
	@Autowired
	private CreditRatingOrganizationDetailsRepository creditRatingOrganizationDetailsRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				CreditRatingOrganizationDetailRequest creditRatingOrganizationsDetailRequest = (CreditRatingOrganizationDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, CreditRatingOrganizationDetailRequest.class);
				CreditRatingOrganizationDetail creditRatingOrganizationDetail = new CreditRatingOrganizationDetail();
				BeanUtils.copyProperties(creditRatingOrganizationsDetailRequest, creditRatingOrganizationDetail);
				if (creditRatingOrganizationsDetailRequest.getId() == null) {
					creditRatingOrganizationDetail.setCreatedBy(frameRequest.getUserId());
					creditRatingOrganizationDetail.setCreatedDate(new Date());
				}
				creditRatingOrganizationDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				creditRatingOrganizationDetail.setModifiedBy(frameRequest.getUserId());
				creditRatingOrganizationDetail.setModifiedDate(new Date());
				creditRatingOrganizationDetailsRepository.save(creditRatingOrganizationDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save creditRatingOrganizationDetail  :-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CreditRatingOrganizationDetailRequest> getcreditRatingOrganizationDetailsList(Long id) {
		List<CreditRatingOrganizationDetail> creditRatingOrganizationDetails = creditRatingOrganizationDetailsRepository
				.listCreditRatingOrganizationDetailsFromAppId(id);
		List<CreditRatingOrganizationDetailRequest> creditRatingOrganizationDetailRequests = new ArrayList<CreditRatingOrganizationDetailRequest>();

		for (int i = 0; i < creditRatingOrganizationDetails.size(); i++) {
			CreditRatingOrganizationDetailRequest creditRatingOrganizationDetailsRequest = new CreditRatingOrganizationDetailRequest();
			BeanUtils.copyProperties(creditRatingOrganizationDetails.get(i), creditRatingOrganizationDetailsRequest);
			creditRatingOrganizationDetailRequests.add(creditRatingOrganizationDetailsRequest);
		}
		return creditRatingOrganizationDetailRequests;
	}
}
