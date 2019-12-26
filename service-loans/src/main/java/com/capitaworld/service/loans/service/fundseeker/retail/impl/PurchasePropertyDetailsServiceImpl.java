package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.retail.*;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.*;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.*;
import com.capitaworld.service.loans.service.fundseeker.retail.EmpFinancialDetailsService;
import com.capitaworld.service.loans.service.fundseeker.retail.PurchasePropertyDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class PurchasePropertyDetailsServiceImpl implements PurchasePropertyDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(PurchasePropertyDetailsServiceImpl.class);

	@Autowired
	private PurchasePropertyDetailsRepository purchasePropertyDetailsRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;



	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				PurchasePropertyDetailsRequest purchasePropertyDetailsRequest = (PurchasePropertyDetailsRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, PurchasePropertyDetailsRequest.class);

				PurchasePropertyDetails propertyDetails = new PurchasePropertyDetails();
				BeanUtils.copyProperties(purchasePropertyDetailsRequest, propertyDetails);
				if (purchasePropertyDetailsRequest.getId() == null) {
					propertyDetails.setCreatedDate(new Date());
					propertyDetails.setIsActive(true);
				}
				propertyDetails.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
/*
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
						throw new LoansException();
				}
*/

				if(frameRequest.getProposalMappingId() != null) {
					propertyDetails.setProposalId(applicationProposalMappingRepository.findByProposalIdAndIsActive(frameRequest.getProposalMappingId(), true));
				}

				propertyDetails.setModifiedDate(new Date());
				purchasePropertyDetailsRepository.save(propertyDetails);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save referencesRetailDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<PurchasePropertyDetailsRequest> getPropertyDetailListByProposalId(Long proposalId, int applicationType) throws LoansException {
		List<PurchasePropertyDetails> purchasePropertyDetails = null;
//		switch (applicationType) {
//		case CommonUtils.ApplicantType.APPLICANT:
//			referencesRetailDetails = referenceRetailDetailsRepository.listReferencesRetailFromAppId(id);
//			break;
//		case CommonUtils.ApplicantType.COAPPLICANT:
//			referencesRetailDetails = referenceRetailDetailsRepository.listReferencesRetailFromCoAppId(id);
//			break;
//		case CommonUtils.ApplicantType.GARRANTOR:
//			referencesRetailDetails = referenceRetailDetailsRepository.listReferencesRetailFromGarrId(id);
//			break;
//		default:
//			throw new LoansException();
//		}

		purchasePropertyDetails = purchasePropertyDetailsRepository.listPropertyFromPropsalId(proposalId);

		List<PurchasePropertyDetailsRequest> purchasePropertyDetailsRequests = new ArrayList<>();

		for (PurchasePropertyDetails detail : purchasePropertyDetails) {
			PurchasePropertyDetailsRequest purchasePropertyDetailsRequest = new PurchasePropertyDetailsRequest();
			//referencesRetailRequest.setReferncesList(!CommonUtils.isObjectNullOrEmpty(detail.getReferencesListId()) ? StringEscapeUtils.escapeXml(ReferencesList.getById(detail.getReferencesListId()).getValue()) :"");
			BeanUtils.copyProperties(detail, purchasePropertyDetailsRequest);
			purchasePropertyDetailsRequests.add(purchasePropertyDetailsRequest);
		}
		return purchasePropertyDetailsRequests;
	}
}
