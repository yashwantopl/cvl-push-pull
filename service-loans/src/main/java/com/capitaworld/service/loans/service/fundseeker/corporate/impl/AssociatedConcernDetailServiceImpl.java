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

import com.capitaworld.service.loans.domain.fundseeker.corporate.AssociatedConcernDetail;
import com.capitaworld.service.loans.model.AssociatedConcernDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssociatedConcernDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssociatedConcernDetailService;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class AssociatedConcernDetailServiceImpl implements AssociatedConcernDetailService {
	
	private static final Logger logger = LoggerFactory.getLogger(AssociatedConcernDetailServiceImpl.class.getName());
	@Autowired
	private  AssociatedConcernDetailRepository  associatedConcernDetailRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				AssociatedConcernDetailRequest associatedConcernDetailRequest = (AssociatedConcernDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, AssociatedConcernDetailRequest.class);
				AssociatedConcernDetail associatedConcernDetail = new AssociatedConcernDetail();
				BeanUtils.copyProperties(associatedConcernDetailRequest, associatedConcernDetail);
				if (associatedConcernDetailRequest.getId() == null) {
					associatedConcernDetail.setCreatedBy(frameRequest.getUserId());
					associatedConcernDetail.setCreatedDate(new Date());
				}
				associatedConcernDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				associatedConcernDetail.setModifiedBy(frameRequest.getUserId());
				associatedConcernDetail.setModifiedDate(new Date());
				associatedConcernDetailRepository.save(associatedConcernDetail);
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
	public List<AssociatedConcernDetailRequest> getAssociatedConcernsDetailList(Long id) {

		List<AssociatedConcernDetail> associatedConcernDetail = associatedConcernDetailRepository
				.listAssociatedConcernFromAppId(id);
		List<AssociatedConcernDetailRequest> associatedConcernDetailRequests = new ArrayList<AssociatedConcernDetailRequest>();

		for (int i = 0; i < associatedConcernDetail.size(); i++) {
			AssociatedConcernDetailRequest associatedConcernDetailRequest = new AssociatedConcernDetailRequest();
			BeanUtils.copyProperties(associatedConcernDetail.get(i), associatedConcernDetailRequest);
			associatedConcernDetailRequests.add(associatedConcernDetailRequest);
		}
		return associatedConcernDetailRequests;
	}

}
