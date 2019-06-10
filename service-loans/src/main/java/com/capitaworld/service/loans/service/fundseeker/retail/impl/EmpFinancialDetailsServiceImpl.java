package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import com.capitaworld.service.loans.domain.fundseeker.retail.EmpAgriculturistType;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpSalariedType;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpSelfEmployedType;
import com.capitaworld.service.loans.domain.fundseeker.retail.ReferencesRetailDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.EmpAgriculturistTypeRequest;
import com.capitaworld.service.loans.model.retail.EmpSalariedTypeRequest;
import com.capitaworld.service.loans.model.retail.EmpSelfEmployedTypeRequest;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.*;
import com.capitaworld.service.loans.service.fundseeker.retail.EmpFinancialDetailsService;
import com.capitaworld.service.loans.service.fundseeker.retail.ReferenceRetailDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.enums.ReferencesList;
import org.apache.commons.lang.StringEscapeUtils;
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
public class EmpFinancialDetailsServiceImpl implements EmpFinancialDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpFinancialDetailsServiceImpl.class);
	
	@Autowired
	private EmpAgriculturistTypeRepository empAgriculturistTypeRepository;

	@Autowired
	private EmpSalariedTypeRepository empSalariedTypeRepository;

	@Autowired
	private EmpSelfEmployedTypeRepository empSelfEmployedTypeRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private CoApplicantDetailRepository coApplicantDetailRepository;
	
	@Autowired
	private GuarantorDetailsRepository guarantorDetailsRepository;
	
	@Autowired
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;

	/*@Override
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

	}*/

	@Override
	public Boolean saveOrUpdateSalariedEmpDetails(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				EmpSalariedTypeRequest referencesRetailDetailRequest = (EmpSalariedTypeRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, EmpSalariedTypeRequest.class);

				EmpSalariedType empSalariedType = new EmpSalariedType();
				BeanUtils.copyProperties(referencesRetailDetailRequest, empSalariedType);
				if (referencesRetailDetailRequest.getId() == null) {
					empSalariedType.setCreatedDate(new Date());
					empSalariedType.setIsActive(true);
				}
				empSalariedType.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
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
					empSalariedType.setProposalId(applicationProposalMappingRepository.findByProposalIdAndIsActive(frameRequest.getProposalMappingId(), true));
				}

				empSalariedType.setModifiedDate(new Date());
				empSalariedTypeRepository.save(empSalariedType);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save referencesRetailDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public Boolean saveOrUpdateAgriculturistEmpDetails(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				EmpAgriculturistTypeRequest empAgriculturistTypeRequest = (EmpAgriculturistTypeRequest ) MultipleJSONObjectHelper
						.getObjectFromMap(obj, EmpAgriculturistTypeRequest .class);
				EmpAgriculturistType empAgriculturistType = new EmpAgriculturistType();
				BeanUtils.copyProperties(empAgriculturistTypeRequest, empAgriculturistType);
				if (empAgriculturistType.getId() == null) {
					//referencesRetailDetail.setCreatedBy(frameRequest.getUserId());
					empAgriculturistType.setCreatedDate(new Date());
					empAgriculturistType.setIsActive(true);
				}
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
					empAgriculturistType.setProposalId(applicationProposalMappingRepository.findByProposalIdAndIsActive(frameRequest.getProposalMappingId(), true));
				}

				//empAgriculturistType.setModifiedBy(frameRequest.getUserId());
				empAgriculturistType.setModifiedDate(new Date());
				empAgriculturistTypeRepository.save(empAgriculturistType);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save referencesRetailDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public Boolean saveOrUpdateSelfEmpDetails(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				EmpSelfEmployedTypeRequest empSelfEmployedTypeRequest = (EmpSelfEmployedTypeRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, EmpSelfEmployedTypeRequest.class);
				EmpSelfEmployedType empSelfEmployedType = new EmpSelfEmployedType();
				BeanUtils.copyProperties(empSelfEmployedTypeRequest, empSelfEmployedType);
				if (empSelfEmployedType.getId() == null) {
					//empSelfEmployedType.setCreatedBy(frameRequest.getUserId());
					empSelfEmployedType.setCreatedDate(new Date());
					empSelfEmployedType.setIsActive(true);
				}
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
					empSelfEmployedType.setProposalId(applicationProposalMappingRepository.findByProposalIdAndIsActive(frameRequest.getProposalMappingId(), true));
				}

				//empSelfEmployedType.setModifiedBy(frameRequest.getUserId());
				empSelfEmployedType.setModifiedDate(new Date());
				empSelfEmployedTypeRepository.save(empSelfEmployedType);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save referencesRetailDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<EmpSalariedTypeRequest> getSalariedEmpFinDetailListByProposalId(Long proposalId, int applicationType) throws LoansException {
		List<EmpSalariedType> empSalariedTypes = null;
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

		empSalariedTypes = empSalariedTypeRepository.listSalariedEmpRetailFromPropsalId(proposalId);

		List<EmpSalariedTypeRequest> empSalariedTypeRequests = new ArrayList<>();

		for (EmpSalariedType detail : empSalariedTypes) {
			EmpSalariedTypeRequest empSalariedTypeRequest = new EmpSalariedTypeRequest();
			//referencesRetailRequest.setReferncesList(!CommonUtils.isObjectNullOrEmpty(detail.getReferencesListId()) ? StringEscapeUtils.escapeXml(ReferencesList.getById(detail.getReferencesListId()).getValue()) :"");
			BeanUtils.copyProperties(detail, empSalariedTypeRequest);
			empSalariedTypeRequests.add(empSalariedTypeRequest);
		}
		return empSalariedTypeRequests;

	}

	@Override
	public List<EmpAgriculturistTypeRequest> getAgriculturistEmpFinDetailListByProposalId(Long proposalId, int applicationType) throws LoansException {
		List<EmpAgriculturistType> empAgriculturistTypes = null;
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

		empAgriculturistTypes = empAgriculturistTypeRepository.listSalariedEmpRetailFromPropsalId(proposalId);

		List<EmpAgriculturistTypeRequest> agriculturistTypes = new ArrayList<>();

		for (EmpAgriculturistType detail : empAgriculturistTypes) {
			EmpAgriculturistTypeRequest empAgriculturistTypeRequest = new EmpAgriculturistTypeRequest();
			//referencesRetailRequest.setReferncesList(!CommonUtils.isObjectNullOrEmpty(detail.getReferencesListId()) ? StringEscapeUtils.escapeXml(ReferencesList.getById(detail.getReferencesListId()).getValue()) :"");
			BeanUtils.copyProperties(detail, empAgriculturistTypeRequest);
			agriculturistTypes.add(empAgriculturistTypeRequest);
		}
		return agriculturistTypes;
	}

	@Override
	public List<EmpSelfEmployedTypeRequest> getSelfEmpFinDetailListByProposalId(Long proposalId, int applicationType) throws LoansException {
		List<EmpSelfEmployedType> empSelfEmployedTypes = null;
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

		empSelfEmployedTypes = empSelfEmployedTypeRepository.listSalariedEmpRetailFromPropsalId(proposalId);

		List<EmpSelfEmployedTypeRequest> empSelfEmployedTypeRequests = new ArrayList<>();

		for (EmpSelfEmployedType detail : empSelfEmployedTypes) {
			EmpSelfEmployedTypeRequest empSelfEmployedTypeRequest = new EmpSelfEmployedTypeRequest();
			//referencesRetailRequest.setReferncesList(!CommonUtils.isObjectNullOrEmpty(detail.getReferencesListId()) ? StringEscapeUtils.escapeXml(ReferencesList.getById(detail.getReferencesListId()).getValue()) :"");
			BeanUtils.copyProperties(detail, empSelfEmployedTypeRequest);
			empSelfEmployedTypeRequests.add(empSelfEmployedTypeRequest);
		}
		return empSelfEmployedTypeRequests;

	}

	/*@Override
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
	}*/

	/*@Override
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
	}*/

}
