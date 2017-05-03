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

import com.capitaworld.service.loans.domain.fundseeker.retail.ExistingLoanDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.ExistingLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.ExistingLoanDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.ExistingLoanDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class ExistingLoanDetailsServiceImpl implements ExistingLoanDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(ExistingLoanDetailsServiceImpl.class);
	
	@Autowired
	private ExistingLoanDetailsRepository existingLoanDetailsRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private CoApplicantDetailRepository coApplicantDetailRepository;
	
	@Autowired
	private GuarantorDetailsRepository guarantorDetailsRepository;
	
	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				ExistingLoanDetailRequest existinfLoanDetailRequest = (ExistingLoanDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, ExistingLoanDetailRequest.class);
				ExistingLoanDetail existingLoanDetail = new ExistingLoanDetail();
				BeanUtils.copyProperties(existinfLoanDetailRequest, existingLoanDetail);
				if (existinfLoanDetailRequest.getId() == null) {
					existingLoanDetail.setCreatedBy(frameRequest.getUserId());
					existingLoanDetail.setCreatedDate(new Date());
				}
				switch(frameRequest.getApplicantType()) {
				case CommonUtils.ApplicantType.APPLICANT:
					existingLoanDetail.setApplicantId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.COAPPLICANT:
					existingLoanDetail.setCoApplicantDetailId(coApplicantDetailRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.GARRANTOR:
					existingLoanDetail.setGuarantorDetailId(guarantorDetailsRepository.findOne(frameRequest.getApplicationId()));
					break;
				default :
					throw new Exception();
				}
				
				existingLoanDetail.setModifiedBy(frameRequest.getUserId());
				existingLoanDetail.setModifiedDate(new Date());
				existingLoanDetailsRepository.save(existingLoanDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save existingLoanDetail  :-");
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<ExistingLoanDetailRequest> getExistingLoanDetailList(Long id, int applicationType) throws Exception {

		List<ExistingLoanDetail> existingLoanDetails = new ArrayList<ExistingLoanDetail>() ;
		switch (applicationType) {
		case CommonUtils.ApplicantType.APPLICANT:
			existingLoanDetails = existingLoanDetailsRepository.listExistingLoanFromAppId(id);
			break;
		case CommonUtils.ApplicantType.COAPPLICANT:
			existingLoanDetails = existingLoanDetailsRepository.listExistingLoanFromCoAppId(id);
			break;
		case CommonUtils.ApplicantType.GARRANTOR:
			existingLoanDetails = existingLoanDetailsRepository.listExistingLoanFromGarrId(id);
			break;
		default:
			throw new Exception();
		}
		
		List<ExistingLoanDetailRequest> existingLoanDetailRequests = new ArrayList<ExistingLoanDetailRequest>();

		for (ExistingLoanDetail detail : existingLoanDetails) {
			ExistingLoanDetailRequest existingLoanDetailRequest = new ExistingLoanDetailRequest();
			BeanUtils.copyProperties(detail, existingLoanDetailRequest);
			existingLoanDetailRequests.add(existingLoanDetailRequest);
		}
		return existingLoanDetailRequests;
	}

}
