package com.capitaworld.service.loans.service.fundseeker.retail.impl;

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

import com.capitaworld.service.loans.domain.fundseeker.retail.FixedDepositsDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.FixedDepositsDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.FixedDepositsDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.FixedDepositsDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class FixedDepositsDetailServiceImpl implements FixedDepositsDetailService {

	private static final Logger logger = LoggerFactory.getLogger(FixedDepositsDetailServiceImpl.class);

	@Autowired
	private FixedDepositsDetailRepository fixedDepositsDetailRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private CoApplicantDetailRepository coApplicantDetailRepository;

	@Autowired
	private GuarantorDetailsRepository guarantorDetailsRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				FixedDepositsDetailsRequest fixedDepositsDetailRequest = (FixedDepositsDetailsRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, FixedDepositsDetailsRequest.class);
				FixedDepositsDetail fixedDepositsDetail = new FixedDepositsDetail();
				BeanUtils.copyProperties(fixedDepositsDetailRequest, fixedDepositsDetail);
				if (fixedDepositsDetailRequest.getId() == null) {
					fixedDepositsDetail.setCreatedBy(frameRequest.getUserId());
					fixedDepositsDetail.setCreatedDate(new Date());
				}
				switch (frameRequest.getApplicantType()) {
				case CommonUtils.ApplicantType.APPLICANT:
					fixedDepositsDetail
							.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.COAPPLICANT:
					fixedDepositsDetail.setCoApplicantDetailId(
							coApplicantDetailRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.GARRANTOR:
					fixedDepositsDetail
							.setGuarantorDetailId(guarantorDetailsRepository.findOne(frameRequest.getApplicationId()));
					break;
				default:
					throw new Exception();
				}

				fixedDepositsDetail.setModifiedBy(frameRequest.getUserId());
				fixedDepositsDetail.setModifiedDate(new Date());
				fixedDepositsDetailRepository.save(fixedDepositsDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save fixedDepositsDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<FixedDepositsDetailsRequest> getFixedDepositsDetailList(Long id, int applicationType) throws Exception {
		try {
			List<FixedDepositsDetail> fixedDepositsDetails = null;
			switch (applicationType) {
			case CommonUtils.ApplicantType.APPLICANT:
				fixedDepositsDetails = fixedDepositsDetailRepository.listFixedDepositsFromAppId(id);
				break;
			case CommonUtils.ApplicantType.COAPPLICANT:
				fixedDepositsDetails = fixedDepositsDetailRepository.listFixedDepositsFromCoAppId(id);
				break;
			case CommonUtils.ApplicantType.GARRANTOR:
				fixedDepositsDetails = fixedDepositsDetailRepository.listFixedDepositsFromGarrId(id);
				break;
			default:
				throw new Exception();
			}

			List<FixedDepositsDetailsRequest> fixedDepositsDetailRequests = new ArrayList<FixedDepositsDetailsRequest>(fixedDepositsDetails.size());

			for (FixedDepositsDetail detail : fixedDepositsDetails) {
				FixedDepositsDetailsRequest fixedDepositsDetailRequest = new FixedDepositsDetailsRequest();
				BeanUtils.copyProperties(detail, fixedDepositsDetailRequest);
				fixedDepositsDetailRequests.add(fixedDepositsDetailRequest);
			}
			return fixedDepositsDetailRequests;
		}

		catch (Exception e) {
			logger.info("Exception in get fixedDepositsDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

}
