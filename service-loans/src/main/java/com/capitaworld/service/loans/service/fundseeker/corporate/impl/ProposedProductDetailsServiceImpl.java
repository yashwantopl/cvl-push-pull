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

import com.capitaworld.service.loans.domain.fundseeker.corporate.ProposedProductDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.ProposedProductDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ProposedProductDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProposedProductDetailsService;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class ProposedProductDetailsServiceImpl implements ProposedProductDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProposedProductDetailsServiceImpl.class);
	
	@Autowired
	private ProposedProductDetailsRepository proposedProductDetailsRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				ProposedProductDetailRequest proposedProductDetailRequest = (ProposedProductDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, ProposedProductDetailRequest.class);
				ProposedProductDetail proposedProductDetail = new ProposedProductDetail();
				BeanUtils.copyProperties(proposedProductDetailRequest, proposedProductDetail);
				if (proposedProductDetailRequest.getId() == null) {
					proposedProductDetail.setCreatedBy(frameRequest.getUserId());
					proposedProductDetail.setCreatedDate(new Date());
				}
				proposedProductDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				proposedProductDetail.setModifiedBy(frameRequest.getUserId());
				proposedProductDetail.setModifiedDate(new Date());
				proposedProductDetailsRepository.save(proposedProductDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save proposedProductDetail  :-");
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<ProposedProductDetailRequest> getProposedProductDetailList(Long id) {
			List<ProposedProductDetail> proposedProductDetails = proposedProductDetailsRepository
					.listProposedProductFromAppId(id);
			List<ProposedProductDetailRequest> proposedProductDetailRequests = new ArrayList<ProposedProductDetailRequest>();

			for (ProposedProductDetail detail : proposedProductDetails) {
				ProposedProductDetailRequest proposedProductDetailRequest = new ProposedProductDetailRequest();
				BeanUtils.copyProperties(detail, proposedProductDetailRequest);
				proposedProductDetailRequests.add(proposedProductDetailRequest);
			}
			return proposedProductDetailRequests;
	}

}
