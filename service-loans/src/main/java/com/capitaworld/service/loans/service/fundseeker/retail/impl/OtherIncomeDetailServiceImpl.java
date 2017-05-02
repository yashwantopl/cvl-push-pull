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

import com.capitaworld.service.loans.domain.fundseeker.retail.OtherIncomeDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.OtherIncomeDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.OtherIncomeDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.OtherIncomeDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */

@Service
@Transactional
public class OtherIncomeDetailServiceImpl implements OtherIncomeDetailService{
	
	private static final Logger logger = LoggerFactory.getLogger(OtherIncomeDetailServiceImpl.class);
	
	@Autowired
	private OtherIncomeDetailRepository otherIncomeDetailRepository;
	
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
				OtherIncomeDetailRequest otherIncomeDetailRequest = (OtherIncomeDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, OtherIncomeDetailRequest.class);
				OtherIncomeDetail otherIncomeDetail = new OtherIncomeDetail();
				BeanUtils.copyProperties(otherIncomeDetailRequest, otherIncomeDetail);
				if (otherIncomeDetailRequest.getId() == null) {
					otherIncomeDetail.setCreatedBy(frameRequest.getUserId());
					otherIncomeDetail.setCreatedDate(new Date());
				}
				switch(frameRequest.getApplicantType()) {
				case CommonUtils.ApplicantType.APPLICANT:
					otherIncomeDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.COAPPLICANT:
					otherIncomeDetail.setCoApplicantDetailId(coApplicantDetailRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.GARRANTOR:
					otherIncomeDetail.setGuarantorDetailId(guarantorDetailsRepository.findOne(frameRequest.getApplicationId()));
					break;
				default :
					throw new Exception();
				}
				
				otherIncomeDetail.setModifiedBy(frameRequest.getUserId());
				otherIncomeDetail.setModifiedDate(new Date());
				otherIncomeDetailRepository.save(otherIncomeDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save otherIncomeDetail  :-");
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<OtherIncomeDetailRequest> getOtherIncomeDetailList(Long id, int applicationType) throws Exception {

		List<OtherIncomeDetail> otherIncomeDetails = new ArrayList<OtherIncomeDetail>() ;
		switch (applicationType) {
		case CommonUtils.ApplicantType.APPLICANT:
			otherIncomeDetails = otherIncomeDetailRepository.listOtherIncomeFromAppId(id);
			break;
		case CommonUtils.ApplicantType.COAPPLICANT:
			otherIncomeDetails = otherIncomeDetailRepository.listOtherIncomeFromCoAppId(id);
			break;
		case CommonUtils.ApplicantType.GARRANTOR:
			otherIncomeDetails = otherIncomeDetailRepository.listOtherIncomeFromGarrId(id);
			break;
		default:
			throw new Exception();
		}
		
		List<OtherIncomeDetailRequest> otherIncomeRequests = new ArrayList<OtherIncomeDetailRequest>();

		for (int i = 0; i < otherIncomeDetails.size(); i++) {
			OtherIncomeDetailRequest otherIncomeRequest = new OtherIncomeDetailRequest();
			BeanUtils.copyProperties(otherIncomeDetails.get(i), otherIncomeRequest);
			otherIncomeRequests.add(otherIncomeRequest);
		}
		return otherIncomeRequests;
	}

}
