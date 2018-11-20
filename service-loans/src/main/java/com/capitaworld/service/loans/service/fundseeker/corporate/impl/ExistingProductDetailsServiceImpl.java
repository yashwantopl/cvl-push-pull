package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.ExistingProductDetail;
import com.capitaworld.service.loans.model.ExistingProductDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ExistingProductDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExistingProductDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class ExistingProductDetailsServiceImpl implements ExistingProductDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(ExistingProductDetailsServiceImpl.class.getName());
	@Autowired
	private ExistingProductDetailsRepository existingProductDetailsRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				ExistingProductDetailRequest existingProductDetailRequest = MultipleJSONObjectHelper.getObjectFromMap(obj, ExistingProductDetailRequest.class);
				ExistingProductDetail existingProductDetail = new ExistingProductDetail();
				BeanUtils.copyProperties(existingProductDetailRequest, existingProductDetail);
				if (existingProductDetailRequest.getId() == null) {
					existingProductDetail.setCreatedBy(frameRequest.getUserId());
					existingProductDetail.setCreatedDate(new Date());
				}
				existingProductDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				existingProductDetail.setApplicationProposalMapping(new ApplicationProposalMapping(frameRequest.getProposalId()));
				existingProductDetail.setModifiedBy(frameRequest.getUserId());
				existingProductDetail.setModifiedDate(new Date());
				existingProductDetailsRepository.save(existingProductDetail);
			}
			return true;
		}
		catch (Exception e) {
			logger.info("Exception  in save existingProductDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<ExistingProductDetailRequest> getExistingProductDetailList(Long applicationId, Long userId) throws Exception {
		try {
			List<ExistingProductDetail> existingProductDetails = existingProductDetailsRepository.listExistingProductFromAppId(applicationId);
			List<ExistingProductDetailRequest> existingProductDetailRequests = new ArrayList<>();
			for (ExistingProductDetail detail : existingProductDetails) {
				ExistingProductDetailRequest existingProductDetailRequest = new ExistingProductDetailRequest();
				BeanUtils.copyProperties(detail, existingProductDetailRequest);
				ExistingProductDetailRequest.printFields(existingProductDetailRequest);
				existingProductDetailRequests.add(existingProductDetailRequest);
			}
			return existingProductDetailRequests;
		} catch (Exception e) {
			logger.info("Exception  in save existingProductDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}
}