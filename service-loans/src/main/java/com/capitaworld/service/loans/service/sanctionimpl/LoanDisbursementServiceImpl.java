package com.capitaworld.service.loans.service.sanctionimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalDetails;
import com.capitaworld.service.loans.domain.sanction.LoanDisbursementDomain;
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoanDisbursementRequest;
import com.capitaworld.service.loans.repository.OfflineProcessedAppRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.sanction.LoanDisbursementRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.sanction.LoanDisbursementService;
import com.capitaworld.service.loans.utils.CommonUtility;
import com.capitaworld.service.loans.utils.CommonUtils;

/**
 * @author Ankit
 *
 */

@Service
@Transactional
public class LoanDisbursementServiceImpl implements LoanDisbursementService {

	private static final Logger logger = LoggerFactory.getLogger(LoanDisbursementServiceImpl.class);

	private static final String EXIT_SAVE_LOAN_DISBURSEMENT_DETAIL_MSG = "Exit saveLoanDisbursementDetail() --> msg==>";
	private static final String SUCCESS_LITERAL = "SUCCESS";
	private static final String FIRST_DISBURSEMENT = "First Disbursement";

	@Autowired
	private LoanDisbursementRepository loanDisbursementRepository;

	@Autowired
	private ProposalDetailsRepository proposalDetailsRepository;

	@Autowired
	private LoanSanctionRepository loanSanctionRepository;
	
	@Autowired
	private OfflineProcessedAppRepository offlineProcessedAppRepository;
	
	@Override
	public Boolean saveLoanDisbursementDetail(LoanDisbursementRequest loanDisbursementRequest) throws IOException {
		logger.info("Enter in saveLoanDisbursementDetail() ----------------------->  LoanDisbursementRequest "+ loanDisbursementRequest);
		try {
			LoanDisbursementDomain loanDisbursementDomain = new LoanDisbursementDomain();
			BeanUtils.copyProperties(loanDisbursementRequest, loanDisbursementDomain);
			loanDisbursementDomain.setIsActive(true);
			loanDisbursementDomain.setCreatedBy(loanDisbursementRequest.getActionBy());
			loanDisbursementDomain.setCreatedDate(new Date());
			loanDisbursementDomain.setModifiedBy(loanDisbursementRequest.getActionBy());
			loanDisbursementDomain.setModifiedDate(new Date());
			if(loanDisbursementRequest.getIsIneligibleProposal()!= null &&  loanDisbursementRequest.getIsIneligibleProposal()) {
				loanDisbursementDomain.setIsDisbursedFrom(2L);
				loanDisbursementDomain.setOrgId(loanDisbursementRequest.getOrgId());
			}
			
			
			logger.info("Exit saveLoanDisbursementDetail() -----------------------> ");
			return loanDisbursementRepository.save(loanDisbursementDomain) != null;
		} catch (Exception e) {
			logger.error("Error/Exception in saveLoanDisbursementDetail() -----------------------> Message ",e);
			throw e;
		}
	}

	public LoanDisbursementRequest disbursementRequestValidation(Long sanctionPrimaryId , LoanDisbursementRequest loanDisbursementRequest, Long orgId ,Integer apiType) throws IOException {
		logger.info("Enter in requestValidation() ----------------------->  LoanDisbursementRequest ==> " + loanDisbursementRequest);  
		try {
			LoanSanctionDomain loanSanctionDomain =null ;
			if(CommonUtility.ApiType.DISBURSEMENT == apiType || (  sanctionPrimaryId == null && apiType == CommonUtility.ApiType.REVERSE_DISBURSEMENT ) ) {
				loanSanctionDomain  = loanSanctionRepository.findByAppliationId(loanDisbursementRequest.getApplicationId());
			}else {
				loanSanctionDomain  = loanSanctionRepository.findByBankSanctionPrimaryKeyAndIsActiveAndApplicationId(sanctionPrimaryId , true,loanDisbursementRequest.getApplicationId());
			}
			if(loanSanctionDomain == null || loanSanctionDomain.getSanctionAmount() == null) {
				logger.info(EXIT_SAVE_LOAN_DISBURSEMENT_DETAIL_MSG +"Please Sanction Before Disbursement for this applicationId==>" +loanDisbursementRequest.getApplicationId() );
				 loanDisbursementRequest.setReason("Please Sanction Before Disbursement for this applicationId ==>" +loanDisbursementRequest.getApplicationId());
				 loanDisbursementRequest.setIsSaved(false);
				 loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.DISBURSEMENT_WITHOUT_SANCTION);
				return loanDisbursementRequest;
			}
			Long recCount = 0L;
			if(CommonUtils.isObjectNullOrEmpty(loanDisbursementRequest.getIsIneligibleProposal()) || !loanDisbursementRequest.getIsIneligibleProposal()) {
				recCount = proposalDetailsRepository.getApplicationIdCountByOrgId(loanDisbursementRequest.getApplicationId(), orgId);
			}
			if (( recCount != null && recCount > 0 ) || (loanDisbursementRequest.getIsIneligibleProposal()!= null &&  loanDisbursementRequest.getIsIneligibleProposal())) {
				Double oldDisbursedAmount = loanDisbursementRepository.getTotalDisbursedAmount(loanDisbursementRequest.getApplicationId());
				if (oldDisbursedAmount != null) {
					if (loanSanctionDomain.getSanctionAmount() == oldDisbursedAmount) {
						logger.info(EXIT_SAVE_LOAN_DISBURSEMENT_DETAIL_MSG+"Alread Your Disbursement is Complete");
						loanDisbursementRequest.setReason("Alread Your Disbursement is Complete");
						loanDisbursementRequest.setIsSaved(true);
						loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.ALREADY_DONE_DISBURSEMENT);
						if(loanDisbursementRequest.getIsIneligibleProposal()!= null &&  loanDisbursementRequest.getIsIneligibleProposal()) {
						loanDisbursementRequest.setIsDisbursedFrom(2L);
						loanDisbursementRequest.setOrgId(orgId);
						}
						return loanDisbursementRequest;
					}
					Double totalDisbursedAmount = oldDisbursedAmount + loanDisbursementRequest.getDisbursedAmount();
					if (loanSanctionDomain.getSanctionAmount() == totalDisbursedAmount) {
						logger.info(EXIT_SAVE_LOAN_DISBURSEMENT_DETAIL_MSG+SUCCESS_LITERAL);
						loanDisbursementRequest.setReason(SUCCESS_LITERAL);
						loanDisbursementRequest.setIsSaved(false);
						loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.SUCCESS);
						if(loanDisbursementRequest.getIsIneligibleProposal()!= null &&  loanDisbursementRequest.getIsIneligibleProposal()) {
						loanDisbursementRequest.setIsDisbursedFrom(2L);
						loanDisbursementRequest.setOrgId(orgId);
						}
						return loanDisbursementRequest;
					}else if (loanSanctionDomain.getSanctionAmount() > totalDisbursedAmount) {
						logger.info(EXIT_SAVE_LOAN_DISBURSEMENT_DETAIL_MSG+SUCCESS_LITERAL);
						loanDisbursementRequest.setReason("Remaining");
						loanDisbursementRequest.setIsSaved(false);
						loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.SUCCESS);
						if(loanDisbursementRequest.getIsIneligibleProposal()!= null &&  loanDisbursementRequest.getIsIneligibleProposal()) {
						loanDisbursementRequest.setIsDisbursedFrom(2L);
						loanDisbursementRequest.setOrgId(orgId);
						}
						return loanDisbursementRequest;
					} else {
						logger.info(EXIT_SAVE_LOAN_DISBURSEMENT_DETAIL_MSG+ "Total Disbursement Amount EXCEED Sanction Amount");
						loanDisbursementRequest.setReason("Total Disbursement Amount EXCEED Sanction Amount{} sanctionAmount ==>"+loanSanctionDomain.getSanctionAmount()+" ,  ( oldDisbursedAmount ==> "+oldDisbursedAmount+" +  newDisbursedAmount==>" +loanDisbursementRequest.getDisbursedAmount()+" ) = totalDisbursedAmount==>"+totalDisbursedAmount);
						loanDisbursementRequest.setIsSaved(false);
						loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.DISBURSEMENT_AMOUNT_EXCEED_SANCTION_AMOUNT);
						if(loanDisbursementRequest.getIsIneligibleProposal()!= null &&  loanDisbursementRequest.getIsIneligibleProposal()) {
							loanDisbursementRequest.setIsDisbursedFrom(2L);
							loanDisbursementRequest.setOrgId(orgId);
						}
						return loanDisbursementRequest;
					}
				} else {
					logger.info(EXIT_SAVE_LOAN_DISBURSEMENT_DETAIL_MSG +FIRST_DISBURSEMENT);
					loanDisbursementRequest.setReason(FIRST_DISBURSEMENT);
					loanDisbursementRequest.setIsSaved(false);
					loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.FIRST_DISBURSEMENT);
					if(loanDisbursementRequest.getIsIneligibleProposal() != null &&  loanDisbursementRequest.getIsIneligibleProposal()) {

						loanDisbursementRequest.setIsDisbursedFrom(2L);
						loanDisbursementRequest.setOrgId(orgId);
						IneligibleProposalDetails ineligibleProposalDetails = (IneligibleProposalDetails) offlineProcessedAppRepository.findByAppliationId(loanDisbursementRequest.getApplicationId(),orgId);
						ineligibleProposalDetails.setIsDisbursed(true);
						ineligibleProposalDetails.setModifiedDate(new Date());
						if(!CommonUtils.isObjectNullOrEmpty(loanDisbursementRequest.getActionBy())) {
							ineligibleProposalDetails.setModifiedBy(Long.valueOf(loanDisbursementRequest.getActionBy()));
						}
						ineligibleProposalDetails.setStatus(CommonUtils.InEligibleProposalStatus.DISBURED);
						LoanSanctionDomain loanSanctionDomainOld =loanSanctionRepository.findByAppliationId(loanDisbursementRequest.getApplicationId());
						loanSanctionDomainOld.setIsPartiallyDisbursedOffline(true);
					}
					return loanDisbursementRequest;
				}
			} else {
				logger.info(EXIT_SAVE_LOAN_DISBURSEMENT_DETAIL_MSG +"Invalid ApplicationId ");
				loanDisbursementRequest.setReason( "Invalid ApplicationId");
				loanDisbursementRequest.setIsSaved(false);
				loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.DISBURSEMENT_WITHOUT_SANCTION);
				return loanDisbursementRequest;
			}
			
		} catch (Exception e) {
			logger.error("Error/Exception in requestValidation() -----------------------> Message : ",e);
			throw e;
		}
	}

	@Override
	public List<LoanDisbursementRequest> getDisbursedList(Long applicationId) throws LoansException {
		try{
			List<LoanDisbursementDomain> loanDisbursementDomainList = loanDisbursementRepository.getDisbursedListByApplicationId(applicationId);
			if(!CommonUtils.isListNullOrEmpty(loanDisbursementDomainList)){
				List<LoanDisbursementRequest> loanDisbursementRequestList = new ArrayList<LoanDisbursementRequest>();
				LoanDisbursementRequest loanDisbursementRequest = null;
				for(LoanDisbursementDomain loanDomainObject : loanDisbursementDomainList){
					loanDisbursementRequest = new LoanDisbursementRequest();
					BeanUtils.copyProperties(loanDomainObject, loanDisbursementRequest);
					loanDisbursementRequestList.add(loanDisbursementRequest);
				}
				logger.info("Fetched DisbursedList successfully for applicationId=>" + applicationId);
				return loanDisbursementRequestList;
			}
			logger.warn("No DisbursedList found for applicationId =>" + applicationId);
			return Collections.emptyList();
		}catch (Exception e){
			logger.error("Error/Exception in getDisbursedList() -----------------------> Message : ",e);
			throw new LoansException(e);
		}
	}

	//its save , disbursement request by there primary key 'id' and isSavedFromBank
	@Override
	public Boolean saveLoanDisbursementDetailbyId(Long orgId , LoanDisbursementRequest loanDisbursementRequest ) throws IOException {
		logger.info("Enter in saveLoanDisbursementDetail() ----------------------->  LoanDisbursementRequest "+ loanDisbursementRequest);
		try {
			LoanDisbursementDomain loanDisbursementDomain =  loanDisbursementRepository.findByBankDisbursementPrimaryKeyAndApplicationIdAndIsActive(loanDisbursementRequest.getId() , loanDisbursementRequest.getApplicationId() ,    true);
			if(CommonUtils.isObjectNullOrEmpty(loanDisbursementDomain)) {
				loanDisbursementDomain = new LoanDisbursementDomain();
				loanDisbursementDomain.setIsActive(true);
				loanDisbursementDomain.setCreatedBy(loanDisbursementRequest.getActionBy());
				loanDisbursementDomain.setCreatedDate(new Date());
				BeanUtils.copyProperties(loanDisbursementRequest, loanDisbursementDomain , "id","createdBy" , "createdDate" , "isActive" , "modifiedBy" , "modifiedDate");
				loanDisbursementDomain.setBankDisbursementPrimaryKey(loanDisbursementRequest.getId());
				loanDisbursementDomain.setApplicationId(loanDisbursementRequest.getApplicationId());
				loanDisbursementDomain.setOrgId(orgId);
				return loanDisbursementRepository.save(loanDisbursementDomain) != null;
			}/*else {
				loanDisbursementDomain.setModifiedBy(loanDisbursementRequest.getActionBy());
				loanDisbursementDomain.setModifiedDate(new Date());
				logger.info("Exit saveLoanDisbursementDetail() -----------------------> ");
			}*/
			/*BeanUtils.copyProperties(loanDisbursementRequest, loanDisbursementDomain , "id","createdBy" , "createdDate" , "isActive" , "modifiedBy" , "modifiedDate");
			loanDisbursementDomain.setBankDisbursementPrimaryKey(loanDisbursementRequest.getId());

			return loanDisbursementRepository.save(loanDisbursementDomain) != null;*/
			return true;
		} catch (Exception e) {
			logger.error("Error/Exception in saveLoanDisbursementDetail() -----------------------> Message : ",e);
			throw e;
		}
	}

	
}
