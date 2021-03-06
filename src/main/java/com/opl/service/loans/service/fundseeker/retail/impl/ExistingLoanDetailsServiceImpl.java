package com.opl.service.loans.service.fundseeker.retail.impl;

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
import com.opl.mudra.api.loans.model.retail.ExistingLoanDetailRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper;
import com.opl.service.loans.domain.fundseeker.retail.ExistingLoanDetail;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.opl.service.loans.repository.fundseeker.retail.ExistingLoanDetailsRepository;
import com.opl.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.opl.service.loans.service.fundseeker.retail.ExistingLoanDetailsService;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class ExistingLoanDetailsServiceImpl implements ExistingLoanDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(ExistingLoanDetailsServiceImpl.class);

	private static final String INVALID_APPLICATION_TYPE_MSG =  "Invalid Application Type==>";

	@Autowired
	private ExistingLoanDetailsRepository existingLoanDetailsRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private CoApplicantDetailRepository coApplicantDetailRepository;

	@Autowired
	private GuarantorDetailsRepository guarantorDetailsRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
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
				switch (frameRequest.getApplicantType()) {
				case CommonUtils.ApplicantType.APPLICANT:
					existingLoanDetail
							.setApplicantId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.COAPPLICANT:
					existingLoanDetail.setCoApplicantDetailId(
							coApplicantDetailRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.GARRANTOR:
					existingLoanDetail
							.setGuarantorDetailId(guarantorDetailsRepository.findOne(frameRequest.getApplicationId()));
					break;
				default:
					throw new LoansException(INVALID_APPLICATION_TYPE_MSG + frameRequest.getApplicantType());
				}

				existingLoanDetail.setModifiedBy(frameRequest.getUserId());
				existingLoanDetail.setModifiedDate(new Date());
				existingLoanDetailsRepository.save(existingLoanDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save existingLoanDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<ExistingLoanDetailRequest> getExistingLoanDetailList(Long id, int applicationType) throws LoansException {

		List<ExistingLoanDetail> existingLoanDetails;
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
			throw new LoansException(INVALID_APPLICATION_TYPE_MSG + applicationType);
		}

		List<ExistingLoanDetailRequest> existingLoanDetailRequests = new ArrayList<ExistingLoanDetailRequest>();

		for (ExistingLoanDetail detail : existingLoanDetails) {
			ExistingLoanDetailRequest existingLoanDetailRequest = new ExistingLoanDetailRequest();
			BeanUtils.copyProperties(detail, existingLoanDetailRequest);
			existingLoanDetailRequests.add(existingLoanDetailRequest);
		}
		return existingLoanDetailRequests;
	}
	
	
	@Override
	public Boolean saveOrUpdateFromCibil(List<ExistingLoanDetailRequest> existingLoanDetailRequest,Long applicationId,Long userId,int applicantType) throws LoansException {
		try {

			// Inactive Previous Loans Before Adding new
			existingLoanDetailsRepository.inactive(applicationId);

			
			for (ExistingLoanDetailRequest request : existingLoanDetailRequest) {
				ExistingLoanDetail existingLoanDetail = new ExistingLoanDetail();
				BeanUtils.copyProperties(request, existingLoanDetail);
				if (request.getId() == null) {
					existingLoanDetail.setCreatedBy(userId);
					existingLoanDetail.setCreatedDate(new Date());
				}
				switch (applicantType) {
				case CommonUtils.ApplicantType.APPLICANT:
					existingLoanDetail
							.setApplicantId(loanApplicationRepository.findOne(applicationId));
					break;
				case CommonUtils.ApplicantType.COAPPLICANT:
					existingLoanDetail.setCoApplicantDetailId(
							coApplicantDetailRepository.findOne(applicationId));
					break;
				case CommonUtils.ApplicantType.GARRANTOR:
					existingLoanDetail
							.setGuarantorDetailId(guarantorDetailsRepository.findOne(applicationId));
					break;
				default:
					throw new LoansException(INVALID_APPLICATION_TYPE_MSG + applicantType);
				}

				existingLoanDetail.setModifiedBy(userId);
				existingLoanDetail.setModifiedDate(new Date());
				existingLoanDetailsRepository.save(existingLoanDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save existingLoanDetail from CIBIL :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

}
