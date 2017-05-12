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

import com.capitaworld.service.loans.domain.fundseeker.corporate.OwnershipDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.OwnershipDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OwnershipDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.OwnershipDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class OwnershipDetailsServiceImpl implements OwnershipDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(ExistingProductDetailsServiceImpl.class.getName());
	@Autowired
	public OwnershipDetailsRepository ownershipDetailsRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception {

		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				OwnershipDetailRequest ownershipDetailRequest = (OwnershipDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, OwnershipDetailRequest.class);
				OwnershipDetail ownershipDetail = new OwnershipDetail();
				BeanUtils.copyProperties(ownershipDetailRequest, ownershipDetail);
				if (ownershipDetailRequest.getId() == null) {
					ownershipDetail.setCreatedBy(frameRequest.getUserId());
					ownershipDetail.setCreatedDate(new Date());
				}
				ownershipDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				ownershipDetail.setModifiedBy(frameRequest.getUserId());
				ownershipDetail.setModifiedDate(new Date());
				ownershipDetailsRepository.save(ownershipDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save ownershipDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<OwnershipDetailRequest> getOwnershipDetailList(Long applicationId) {
		List<OwnershipDetail> ownershipDetails = ownershipDetailsRepository.listOwnershipFromAppId(applicationId);
		List<OwnershipDetailRequest> ownershipDetailRequests = new ArrayList<OwnershipDetailRequest>();

		for (OwnershipDetail detail : ownershipDetails) {
			OwnershipDetailRequest ownershipDetailRequest = new OwnershipDetailRequest();
			BeanUtils.copyProperties(detail, ownershipDetailRequest);
			ownershipDetailRequests.add(ownershipDetailRequest);
		}
		return ownershipDetailRequests;
	}
}
