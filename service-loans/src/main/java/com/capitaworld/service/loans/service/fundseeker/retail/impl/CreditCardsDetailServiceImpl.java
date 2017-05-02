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
	public Boolean saveOrUpdate(FrameRequest frameRequest) {
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
				switch(frameRequest.getApplicantType()) {
				case CommonUtils.ApplicantType.APPLICANT:
					crediCardsDetail.setApplicantionId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.COAPPLICANT:
					crediCardsDetail.setCoApplicantDetailId(coApplicantDetailRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.GARRANTOR:
					crediCardsDetail.setGuarantorDetailId(guarantorDetailsRepository.findOne(frameRequest.getApplicationId()));
					break;
				default :
					throw new Exception();
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
			return false;
		}

	}

	@Override
	public List<CreditCardsDetailRequest> getExistingLoanDetailList(Long id, int applicationType) throws Exception {

		List<CreditCardsDetail> creditCardsDetails = new ArrayList<CreditCardsDetail>() ;
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

		for (int i = 0; i < creditCardsDetails.size(); i++) {
			CreditCardsDetailRequest creditCardsRequest = new CreditCardsDetailRequest();
			BeanUtils.copyProperties(creditCardsDetails.get(i), creditCardsRequest);
			creditCardsRequests.add(creditCardsRequest);
		}
		return creditCardsRequests;
	}
	
	

}
