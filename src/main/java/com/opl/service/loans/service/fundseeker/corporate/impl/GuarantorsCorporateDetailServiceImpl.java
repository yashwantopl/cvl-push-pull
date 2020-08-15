package com.opl.service.loans.service.fundseeker.corporate.impl;

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

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.GuarantorsCorporateDetailRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper;
import com.opl.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.opl.service.loans.domain.fundseeker.corporate.GuarantorsCorporateDetail;
import com.opl.service.loans.repository.fundseeker.corporate.GuarantorsCorporateDetailRepository;
import com.opl.service.loans.service.fundseeker.corporate.GuarantorsCorporateDetailService;

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

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				GuarantorsCorporateDetailRequest guarantorsCorporateDetailRequest = (GuarantorsCorporateDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, GuarantorsCorporateDetailRequest.class);
				GuarantorsCorporateDetail guarantorsCorporateDetail = null;

				if (guarantorsCorporateDetailRequest.getId() != null) {
					guarantorsCorporateDetail = guarantorsCorporateDetailRepository
							.findOne(guarantorsCorporateDetailRequest.getId());
				} else {
					guarantorsCorporateDetail = new GuarantorsCorporateDetail();
					guarantorsCorporateDetail.setCreatedBy(frameRequest.getUserId());
					guarantorsCorporateDetail.setCreatedDate(new Date());
				}
				BeanUtils.copyProperties(guarantorsCorporateDetailRequest, guarantorsCorporateDetail);
				guarantorsCorporateDetail.setApplicationId(new LoanApplicationMaster(frameRequest.getApplicationId()));
				guarantorsCorporateDetail.setApplicationProposalMapping(new ApplicationProposalMapping(frameRequest.getProposalMappingId()));
				guarantorsCorporateDetail.setModifiedBy(frameRequest.getUserId());
				guarantorsCorporateDetail.setModifiedDate(new Date());
				guarantorsCorporateDetailRepository.save(guarantorsCorporateDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save GuarantorDetails  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<GuarantorsCorporateDetailRequest> getGuarantorsCorporateDetailListByProposalId(Long proposalId,Long userId) throws Exception {
		try {
			List<GuarantorsCorporateDetail> guarantorsCorporateDetail = guarantorsCorporateDetailRepository
					.listGuarantorsCorporateFromProposalId(proposalId);
			List<GuarantorsCorporateDetailRequest> guarantorsCorporateDetailRequests = new ArrayList<GuarantorsCorporateDetailRequest>();

			for (GuarantorsCorporateDetail detail : guarantorsCorporateDetail) {
				GuarantorsCorporateDetailRequest guarantorsCorporateDetailRequest = new GuarantorsCorporateDetailRequest();
				BeanUtils.copyProperties(detail, guarantorsCorporateDetailRequest);
				guarantorsCorporateDetailRequests.add(guarantorsCorporateDetailRequest);
			}
			return guarantorsCorporateDetailRequests;
		}

		catch (Exception e) {
			logger.error("Exception  in get Guarantor Details :-{}",e);
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<GuarantorsCorporateDetailRequest> getGuarantorsCorporateDetailList(Long id,Long userId) throws LoansException {
		try {
			List<GuarantorsCorporateDetail> guarantorsCorporateDetail = guarantorsCorporateDetailRepository
					.listGuarantorsCorporateFromAppId(id,userId);
			List<GuarantorsCorporateDetailRequest> guarantorsCorporateDetailRequests = new ArrayList<GuarantorsCorporateDetailRequest>();

			for (GuarantorsCorporateDetail detail : guarantorsCorporateDetail) {
				GuarantorsCorporateDetailRequest guarantorsCorporateDetailRequest = new GuarantorsCorporateDetailRequest();
				BeanUtils.copyProperties(detail, guarantorsCorporateDetailRequest);
				guarantorsCorporateDetailRequests.add(guarantorsCorporateDetailRequest);
			}
			return guarantorsCorporateDetailRequests;
		}

		catch (Exception e) {
			logger.info("Exception  in get Guarantor Details :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

}
