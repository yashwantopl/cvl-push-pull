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

import com.capitaworld.service.loans.domain.fundseeker.retail.CreditCardsDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CreditCardsDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.CreditCardsDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class CreditCardsDetailServiceImpl implements CreditCardsDetailService {

	private static final Logger logger = LoggerFactory.getLogger(CreditCardsDetailServiceImpl.class);

	@Autowired
	private CreditCardsDetailRepository creditCardsDetailRepository;

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
				CreditCardsDetailRequest creditCardsDetailRequest = (CreditCardsDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, CreditCardsDetailRequest.class);
				CreditCardsDetail crediCardsDetail = new CreditCardsDetail();
				BeanUtils.copyProperties(creditCardsDetailRequest, crediCardsDetail);
				if (creditCardsDetailRequest.getId() == null) {
					crediCardsDetail.setCreatedBy(frameRequest.getUserId());
					crediCardsDetail.setCreatedDate(new Date());
				}
				switch (frameRequest.getApplicantType()) {
				case CommonUtils.ApplicantType.APPLICANT:
					crediCardsDetail
							.setApplicantionId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.COAPPLICANT:
					crediCardsDetail.setCoApplicantDetailId(
							coApplicantDetailRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.GARRANTOR:
					crediCardsDetail
							.setGuarantorDetailId(guarantorDetailsRepository.findOne(frameRequest.getApplicationId()));
					break;
				default:
					throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
				}

				crediCardsDetail.setModifiedBy(frameRequest.getUserId());
				crediCardsDetail.setModifiedDate(new Date());
				creditCardsDetailRepository.save(crediCardsDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save crediCardsDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<CreditCardsDetailRequest> getCreditCardDetailList(Long id, int applicationType) throws Exception {

		List<CreditCardsDetail> creditCardsDetails = new ArrayList<CreditCardsDetail>();
		switch (applicationType) {
		case CommonUtils.ApplicantType.APPLICANT:
			creditCardsDetails = creditCardsDetailRepository.listCreditCardsFromAppId(id);
			break;
		case CommonUtils.ApplicantType.COAPPLICANT:
			creditCardsDetails = creditCardsDetailRepository.listCreditCardsFromCoAppId(id);
			break;
		case CommonUtils.ApplicantType.GARRANTOR:
			creditCardsDetails = creditCardsDetailRepository.listCreditCardsFromGarrId(id);
			break;
		default:
			throw new Exception();
		}

		List<CreditCardsDetailRequest> creditCardsRequests = new ArrayList<CreditCardsDetailRequest>();

		for (CreditCardsDetail detail : creditCardsDetails) {
			CreditCardsDetailRequest creditCardsRequest = new CreditCardsDetailRequest();
			BeanUtils.copyProperties(detail, creditCardsRequest);
			creditCardsRequests.add(creditCardsRequest);
		}
		return creditCardsRequests;
	}

	@Override
	public Boolean saveOrUpdateFromCibil(List<CreditCardsDetailRequest> creditCardDetail, Long applicationId,
			Long userId, int applicantType) throws Exception {
		try {
			// Inactive Previous Loans Before Adding new
			creditCardsDetailRepository.inactive(applicationId);

			for (CreditCardsDetailRequest request : creditCardDetail) {
				CreditCardsDetail crediCardsDetail = new CreditCardsDetail();
				BeanUtils.copyProperties(request, crediCardsDetail);
				if (request.getId() == null) {
					crediCardsDetail.setCreatedBy(userId);
					crediCardsDetail.setCreatedDate(new Date());
				}
				switch (applicantType) {
				case CommonUtils.ApplicantType.APPLICANT:
					crediCardsDetail.setApplicantionId(loanApplicationRepository.findOne(applicationId));
					break;
				case CommonUtils.ApplicantType.COAPPLICANT:
					crediCardsDetail.setCoApplicantDetailId(coApplicantDetailRepository.findOne(applicationId));
					break;
				case CommonUtils.ApplicantType.GARRANTOR:
					crediCardsDetail.setGuarantorDetailId(guarantorDetailsRepository.findOne(applicationId));
					break;
				default:
					throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
				}

				crediCardsDetail.setModifiedBy(userId);
				crediCardsDetail.setModifiedDate(new Date());
				creditCardsDetailRepository.save(crediCardsDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save CreditCard from CIBIL :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

}
