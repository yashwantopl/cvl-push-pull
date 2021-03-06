package com.opl.service.loans.service.fundseeker.retail.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.retail.ReferenceRetailDetailsRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper;
import com.opl.mudra.api.oneform.enums.ReferencesList;
import com.opl.service.loans.domain.fundseeker.retail.ReferencesRetailDetail;
import com.opl.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.opl.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.opl.service.loans.repository.fundseeker.retail.ReferenceRetailDetailsRepository;
import com.opl.service.loans.service.fundseeker.retail.ReferenceRetailDetailsService;

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
	
	@Autowired
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
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
					throw new LoansException();
				}
				
				if(frameRequest.getProposalMappingId() != null) {
					referencesRetailDetail.setApplicationProposalMapping(applicationProposalMappingRepository.findByProposalIdAndIsActive(frameRequest.getProposalMappingId(), true));
				}
				
				referencesRetailDetail.setModifiedBy(frameRequest.getUserId());
				referencesRetailDetail.setModifiedDate(new Date());
				referenceRetailDetailsRepository.save(referencesRetailDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save referencesRetailDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailList(Long id, int applicationType) throws LoansException {

		List<ReferencesRetailDetail> referencesRetailDetails;
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
			throw new LoansException();
		}
		
		List<ReferenceRetailDetailsRequest> referencesRetailRequests = new ArrayList<ReferenceRetailDetailsRequest>();

		for (ReferencesRetailDetail detail : referencesRetailDetails) {
			ReferenceRetailDetailsRequest referencesRetailRequest = new ReferenceRetailDetailsRequest();
			referencesRetailRequest.setReferncesList(!CommonUtils.isObjectNullOrEmpty(detail.getReferencesListId()) ? StringEscapeUtils.escapeXml(ReferencesList.getById(detail.getReferencesListId()).getValue()) :"");
			BeanUtils.copyProperties(detail, referencesRetailRequest);
			referencesRetailRequests.add(referencesRetailRequest);
		}
		return referencesRetailRequests;
	}

	@Override
	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailListByPropsalId(Long proposalId,
			int applicationType) throws LoansException {
		
		List<ReferencesRetailDetail> referencesRetailDetails = null;
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
		
		referencesRetailDetails = referenceRetailDetailsRepository.listReferencesRetailFromPropsalId(proposalId);
		
		List<ReferenceRetailDetailsRequest> referencesRetailRequests = new ArrayList<ReferenceRetailDetailsRequest>();

		for (ReferencesRetailDetail detail : referencesRetailDetails) {
			ReferenceRetailDetailsRequest referencesRetailRequest = new ReferenceRetailDetailsRequest();
			referencesRetailRequest.setReferncesList(!CommonUtils.isObjectNullOrEmpty(detail.getReferencesListId()) ? StringEscapeUtils.escapeXml(ReferencesList.getById(detail.getReferencesListId()).getValue()) :"");
			BeanUtils.copyProperties(detail, referencesRetailRequest);
			referencesRetailRequests.add(referencesRetailRequest);
		}
		return referencesRetailRequests;
	}

	@Override
	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailListByPropsalIdAndCoAppId(Long proposalId,
																					   Long coAppId ) throws LoansException {

		List<ReferencesRetailDetail> referencesRetailDetails = null;
		referencesRetailDetails = referenceRetailDetailsRepository.listReferencesRetailFromPropsalIdAndCoAppId(proposalId,coAppId);

		List<ReferenceRetailDetailsRequest> referencesRetailRequests = new ArrayList<ReferenceRetailDetailsRequest>();

		for (ReferencesRetailDetail detail : referencesRetailDetails) {
			ReferenceRetailDetailsRequest referencesRetailRequest = new ReferenceRetailDetailsRequest();
			referencesRetailRequest.setReferncesList(!CommonUtils.isObjectNullOrEmpty(detail.getReferencesListId()) ? StringEscapeUtils.escapeXml(ReferencesList.getById(detail.getReferencesListId()).getValue()) :"");
			BeanUtils.copyProperties(detail, referencesRetailRequest);
			referencesRetailRequests.add(referencesRetailRequest);
		}
		return referencesRetailRequests;
	}

	@Override
	public Boolean saveOrUpdateCoApplicant(FrameRequest frameRequest) throws LoansException {
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
				referencesRetailDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				referencesRetailDetail.setCoApplicantDetailId(coApplicantDetailRepository.findOne(frameRequest.getCoApplicantId()));

				if(frameRequest.getProposalMappingId() != null) {
					referencesRetailDetail.setApplicationProposalMapping(applicationProposalMappingRepository.findByProposalIdAndIsActive(frameRequest.getProposalMappingId(), true));
				}

				referencesRetailDetail.setModifiedBy(frameRequest.getUserId());
				referencesRetailDetail.setModifiedDate(new Date());
				referenceRetailDetailsRepository.save(referencesRetailDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save referencesRetailDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

}
