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

import com.capitaworld.service.loans.domain.fundseeker.retail.BankAccountHeldDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.BankAccountHeldDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.BankAccountHeldDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.BankAccountHeldDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class BankAccountHeldDetailServiceImpl implements BankAccountHeldDetailService {

	private static final Logger logger = LoggerFactory.getLogger(BankAccountHeldDetailServiceImpl.class);

	@Autowired
	private BankAccountHeldDetailRepository bankAccountHeldDetailRepository;

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
				BankAccountHeldDetailsRequest bankAccountHeldDetailRequest = (BankAccountHeldDetailsRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, BankAccountHeldDetailsRequest.class);
				BankAccountHeldDetail bankAccountHeldDetail = new BankAccountHeldDetail();
				BeanUtils.copyProperties(bankAccountHeldDetailRequest, bankAccountHeldDetail);
				if (bankAccountHeldDetailRequest.getId() == null) {
					bankAccountHeldDetail.setCreatedBy(frameRequest.getUserId());
					bankAccountHeldDetail.setCreatedDate(new Date());
				}
				switch (frameRequest.getApplicantType()) {
				case CommonUtils.ApplicantType.APPLICANT:
					bankAccountHeldDetail
							.setApplicantId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.COAPPLICANT:
					bankAccountHeldDetail.setCoApplicantDetailId(
							coApplicantDetailRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.GARRANTOR:
					bankAccountHeldDetail
							.setGuarantorDetailId(guarantorDetailsRepository.findOne(frameRequest.getApplicationId()));
					break;
				default:
					throw new Exception();
				}

				bankAccountHeldDetail.setModifiedBy(frameRequest.getUserId());
				bankAccountHeldDetail.setModifiedDate(new Date());
				bankAccountHeldDetailRepository.save(bankAccountHeldDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save bankAccountHeldDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<BankAccountHeldDetailsRequest> getExistingLoanDetailList(Long id, int applicationType)
			throws Exception {
		try {
			List<BankAccountHeldDetail> existingLoanDetails = new ArrayList<BankAccountHeldDetail>();
			switch (applicationType) {
			case CommonUtils.ApplicantType.APPLICANT:
				existingLoanDetails = bankAccountHeldDetailRepository.listBankAccountHeldFromAppId(id);
				break;
			case CommonUtils.ApplicantType.COAPPLICANT:
				existingLoanDetails = bankAccountHeldDetailRepository.listBankAccountHeldFromCoAppId(id);
				break;
			case CommonUtils.ApplicantType.GARRANTOR:
				existingLoanDetails = bankAccountHeldDetailRepository.listBankAccountHeldFromGarrId(id);
				break;
			default:
				throw new Exception();
			}

			List<BankAccountHeldDetailsRequest> existingLoanDetailRequests = new ArrayList<BankAccountHeldDetailsRequest>();

			for (BankAccountHeldDetail detail : existingLoanDetails) {
				BankAccountHeldDetailsRequest existingLoanDetailRequest = new BankAccountHeldDetailsRequest();
				BeanUtils.copyProperties(detail, existingLoanDetailRequest);
				existingLoanDetailRequests.add(existingLoanDetailRequest);
			}
			return existingLoanDetailRequests;
		}

		catch (Exception e) {
			logger.info("Exception in getting bankAccountHeldDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
