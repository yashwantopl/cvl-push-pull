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

import com.capitaworld.service.loans.domain.fundseeker.retail.ReferencesRetailDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.ReferenceRetailDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.ReferenceRetailDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class ReferenceRetailDetailsServiceImpl implements ReferenceRetailDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(ReferenceRetailDetailsServiceImpl.class);
	
	@Autowired
	private ReferenceRetailDetailsRepository referenceRetailDetailsRepository; 
	
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
				ReferenceRetailDetailsRequest referencesRetailDetailRequest = (ReferenceRetailDetailsRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, ReferenceRetailDetailsRequest.class);
				ReferencesRetailDetail referencesRetailDetail = new ReferencesRetailDetail();
				BeanUtils.copyProperties(referencesRetailDetailRequest, referencesRetailDetail);
				if (referencesRetailDetailRequest.getId() == null) {
					referencesRetailDetail.setCreatedBy(frameRequest.getUserId());
					referencesRetailDetail.setCreatedDate(new Date());
				}
				switch(frameRequest.getApplicantType()) {
				case CommonUtils.ApplicantType.APPLICANT:
					referencesRetailDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.COAPPLICANT:
					referencesRetailDetail.setCoApplicantDetailId(coApplicantDetailRepository.findOne(frameRequest.getApplicationId()));
					break;
				case CommonUtils.ApplicantType.GARRANTOR:
					referencesRetailDetail.setGuarantorDetailId(guarantorDetailsRepository.findOne(frameRequest.getApplicationId()));
					break;
				default :
					throw new Exception();
				}
				
				referencesRetailDetail.setModifiedBy(frameRequest.getUserId());
				referencesRetailDetail.setModifiedDate(new Date());
				referenceRetailDetailsRepository.save(referencesRetailDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save referencesRetailDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailList(Long id, int applicationType) throws Exception {

		List<ReferencesRetailDetail> referencesRetailDetails = new ArrayList<ReferencesRetailDetail>() ;
		switch (applicationType) {
		case CommonUtils.ApplicantType.APPLICANT:
			referencesRetailDetails = referenceRetailDetailsRepository.listReferencesRetailFromAppId(id);
			break;
		case CommonUtils.ApplicantType.COAPPLICANT:
			referencesRetailDetails = referenceRetailDetailsRepository.listReferencesRetailFromCoAppId(id);
			break;
		case CommonUtils.ApplicantType.GARRANTOR:
			referencesRetailDetails = referenceRetailDetailsRepository.listReferencesRetailFromGarrId(id);
			break;
		default:
			throw new Exception();
		}
		
		List<ReferenceRetailDetailsRequest> referencesRetailRequests = new ArrayList<ReferenceRetailDetailsRequest>();

		for (ReferencesRetailDetail detail : referencesRetailDetails) {
			ReferenceRetailDetailsRequest referencesRetailRequest = new ReferenceRetailDetailsRequest();
			BeanUtils.copyProperties(detail, referencesRetailRequest);
			referencesRetailRequests.add(referencesRetailRequest);
		}
		return referencesRetailRequests;
	}

}
