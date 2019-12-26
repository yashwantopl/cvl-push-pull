package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpSalariedType;
import com.capitaworld.service.loans.domain.fundseeker.retail.OtherPropertyDetails;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.EmpSalariedTypeRequest;
import com.capitaworld.service.loans.model.retail.OtherPropertyDetailsRequest;
import com.capitaworld.service.loans.model.retail.PurchasePropertyDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.OtherPropertyDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.OtherPropertyDetailsService;
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
public class OtherPropertyDetailsServiceImpl implements OtherPropertyDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(OtherPropertyDetailsServiceImpl.class);

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;

	@Autowired
	private OtherPropertyDetailsRepository otherPropertyDetailsRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest,int type) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				OtherPropertyDetailsRequest otherPropertyDetailsRequest = (OtherPropertyDetailsRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, OtherPropertyDetailsRequest.class);

				OtherPropertyDetails otherPropertyDetails = new OtherPropertyDetails();
				BeanUtils.copyProperties(otherPropertyDetailsRequest, otherPropertyDetails);
				if (otherPropertyDetailsRequest.getId() == null) {
					otherPropertyDetails.setCreatedDate(new Date());
					otherPropertyDetails.setIsActive(true);
				}
				otherPropertyDetails.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
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
					otherPropertyDetails.setProposalId(applicationProposalMappingRepository.findByProposalIdAndIsActive(frameRequest.getProposalMappingId(), true));
				}

				otherPropertyDetails.setModifiedDate(new Date());
				otherPropertyDetailsRepository.save(otherPropertyDetails);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save referencesRetailDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<OtherPropertyDetailsRequest> getPropertyDetailListByProposalId(Long proposalId, int applicationType) throws LoansException {
		List<OtherPropertyDetails> otherPropertyDetails = null;
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

		otherPropertyDetails = otherPropertyDetailsRepository.listPropertyFromPropsalIdAndType(proposalId);

		List<OtherPropertyDetailsRequest> otherPropertyDetailsRequests = new ArrayList<>();

		for (OtherPropertyDetails detail : otherPropertyDetails) {
			OtherPropertyDetailsRequest otherPropertyDetailsRequest = new OtherPropertyDetailsRequest();
			//referencesRetailRequest.setReferncesList(!CommonUtils.isObjectNullOrEmpty(detail.getReferencesListId()) ? StringEscapeUtils.escapeXml(ReferencesList.getById(detail.getReferencesListId()).getValue()) :"");
			BeanUtils.copyProperties(detail, otherPropertyDetailsRequest);
			otherPropertyDetailsRequests.add(otherPropertyDetailsRequest);
		}
		return otherPropertyDetailsRequests;
	}
}
