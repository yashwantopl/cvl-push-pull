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

import com.capitaworld.service.loans.domain.fundseeker.corporate.GuarantorsCorporateDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.GuarantorsCorporateDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.GuarantorsCorporateDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.GuarantorsCorporateDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class GuarantorsCorporateDetailServiceImpl implements GuarantorsCorporateDetailService {

	private static final Logger logger = LoggerFactory.getLogger(AchievementDetailServiceImpl.class.getName());
	@Autowired
	private GuarantorsCorporateDetailRepository guarantorsCorporateDetailRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				GuarantorsCorporateDetailRequest guarantorsCorporateDetailRequest = (GuarantorsCorporateDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, GuarantorsCorporateDetailRequest.class);
				GuarantorsCorporateDetail guarantorsCorporateDetail = new GuarantorsCorporateDetail();
				BeanUtils.copyProperties(guarantorsCorporateDetailRequest, guarantorsCorporateDetail);
				if (guarantorsCorporateDetailRequest.getId() == null) {
					guarantorsCorporateDetail.setCreatedBy(frameRequest.getUserId());
					guarantorsCorporateDetail.setCreatedDate(new Date());
				}
				guarantorsCorporateDetail
						.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				guarantorsCorporateDetail.setModifiedBy(frameRequest.getUserId());
				guarantorsCorporateDetail.setModifiedDate(new Date());
				guarantorsCorporateDetailRepository.save(guarantorsCorporateDetail);
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
	public List<GuarantorsCorporateDetailRequest> getGuarantorsCorporateDetailList(Long id) {

		List<GuarantorsCorporateDetail> guarantorsCorporateDetail = guarantorsCorporateDetailRepository
				.listGuarantorsCorporateFromAppId(id);
		List<GuarantorsCorporateDetailRequest> guarantorsCorporateDetailRequests = new ArrayList<GuarantorsCorporateDetailRequest>();

		for (GuarantorsCorporateDetail detail : guarantorsCorporateDetail) {
			GuarantorsCorporateDetailRequest guarantorsCorporateDetailRequest = new GuarantorsCorporateDetailRequest();
			BeanUtils.copyProperties(detail, guarantorsCorporateDetailRequest);
			guarantorsCorporateDetailRequests.add(guarantorsCorporateDetailRequest);
		}
		return guarantorsCorporateDetailRequests;
	}

}
