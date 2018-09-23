package com.capitaworld.service.loans.service.sanctionimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.config.AuditComponentBankToCW;
import com.capitaworld.service.loans.domain.sanction.LoanDisbursementDomain;
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.model.LoanDisbursementRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.repository.banktocw.BankToCWAuditTrailRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.sanction.LoanDisbursementRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.sanction.LoanDisbursementService;
import com.capitaworld.service.loans.utils.CommonUtility;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Ankit
 *
 */

@Service
@Transactional
public class LoanDisbursementServiceImpl implements LoanDisbursementService {

	private static final Logger logger = LoggerFactory.getLogger(LoanDisbursementServiceImpl.class);

	@Autowired
	private LoanDisbursementRepository loanDisbursementRepository;

	@Autowired
	private ProposalDetailsRepository proposalDetailsRepository;

	@Autowired
	private LoanSanctionRepository loanSanctionRepository;

	@Autowired
	private AuditComponentBankToCW  auditComponentBankToCW;
	
	@Autowired
	private BankToCWAuditTrailRepository bankToCWAuditTrailRepository;
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
			logger.info("Exit saveLoanDisbursementDetail() -----------------------> ");
			return loanDisbursementRepository.save(loanDisbursementDomain) != null;
		} catch (Exception e) {
			logger.info("Error/Exception in saveLoanDisbursementDetail() -----------------------> Message "+e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	public LoanDisbursementRequest disbursementRequestValidation(Long sanctionPrimaryId , LoanDisbursementRequest loanDisbursementRequest, Long orgId ,Integer apiType) throws IOException {
		logger.info("Enter in requestValidation() ----------------------->  LoanDisbursementRequest ==> " + loanDisbursementRequest);  
		try {
			LoanSanctionDomain loanSanctionDomain =null ;
			if(CommonUtility.ApiType.DISBURSEMENT == apiType) {
				loanSanctionDomain  = loanSanctionRepository.findByAppliationId(loanDisbursementRequest.getApplicationId());
			}else {
				loanSanctionDomain  = loanSanctionRepository.findByBankSanctionPrimaryKeyAndIsActiveAndApplicationId(sanctionPrimaryId , true,loanDisbursementRequest.getApplicationId());
			}
			if(loanSanctionDomain == null || loanSanctionDomain.getSanctionAmount()==null) {
				logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>" +"Please Sanction Before Disbursement for this applicationId==>" +loanDisbursementRequest.getApplicationId() );
				 loanDisbursementRequest.setReason("Please Sanction Before Disbursement for this applicationId ==>" +loanDisbursementRequest.getApplicationId());
				 loanDisbursementRequest.setIsSaved(false);
				 loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.DISBURSEMENT_WITHOUT_SANCTION);
				return loanDisbursementRequest;
				
			}
			Long recCount = proposalDetailsRepository.getApplicationIdCountByOrgId(loanDisbursementRequest.getApplicationId(), orgId);
			if (recCount != null && recCount > 0) {
				Double oldDisbursedAmount = loanDisbursementRepository.getTotalDisbursedAmount(loanDisbursementRequest.getApplicationId());
				if (oldDisbursedAmount != null) {
					if (loanSanctionDomain.getSanctionAmount() == oldDisbursedAmount) {
						logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>"+"Alread Your Disbursement is Complete");
						loanDisbursementRequest.setReason("Alread Your Disbursement is Complete");
						loanDisbursementRequest.setIsSaved(true);
						 loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.ALREADY_DONE_DISBURSEMENT);
						return loanDisbursementRequest;
					}
					Double totalDisbursedAmount = oldDisbursedAmount + loanDisbursementRequest.getDisbursedAmount();
					if (loanSanctionDomain.getSanctionAmount() >= totalDisbursedAmount) {
						logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>"+"SUCCESS");
						loanDisbursementRequest.setReason("SUCCESS");
						loanDisbursementRequest.setIsSaved(false);
						 loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.SUCCESS);
						return loanDisbursementRequest;
					} else {
						logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>"+ "Total Disbursement Amount EXCEED Sanction Amount");
						loanDisbursementRequest.setReason("Total Disbursement Amount EXCEED Sanction Amount{} sanctionAmount ==>"+loanSanctionDomain.getSanctionAmount()+" ,  ( oldDisbursedAmount ==> "+oldDisbursedAmount+" +  newDisbursedAmount==>" +loanDisbursementRequest.getDisbursedAmount()+" ) = totalDisbursedAmount==>"+totalDisbursedAmount);
						loanDisbursementRequest.setIsSaved(false);
						 loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.DISBURSEMENT_AMOUNT_EXCEED_SANCTION_AMOUNT);
						return loanDisbursementRequest;
					}
				} else {
					logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>" +"First Disbursement");
					loanDisbursementRequest.setReason("First Disbursement");
					loanDisbursementRequest.setIsSaved(false);
					loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.FIRST_DISBURSEMENT);
					return loanDisbursementRequest;
				}
			} else {
				logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>" +"Invalid ApplicationId ");
				loanDisbursementRequest.setReason( "Invalid ApplicationId ");
				loanDisbursementRequest.setIsSaved(false);
				 loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.DISBURSEMENT_WITHOUT_SANCTION);
				return loanDisbursementRequest;
			}
			
		} catch (Exception e) {
			logger.info("Error/Exception in requestValidation() -----------------------> Message "+e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<LoanDisbursementRequest> getDisbursedList(Long applicationId) throws Exception {
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
			return null;
		}catch (Exception e){
			logger.info("Error/Exception in getDisbursedList() -----------------------> Message "+e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	//Its for list of disbursement per sanction
	@Override
	public List<LoanDisbursementRequest> bankRequestValidationAndSave(Long sanctionPrimaryId ,List<LoanDisbursementRequest> loanDisbursementRequestsList,Long orgId , Integer apiType) throws IOException {
		
		for(LoanDisbursementRequest  loanDisbursementRequest : loanDisbursementRequestsList) {		
			
			if(! CommonUtils.isObjectNullOrEmptyOrDash( bankToCWAuditTrailRepository.findByApplicationIdAndOrgIdAndApiTypeAndBankPrimaryKey(loanDisbursementRequest.getApplicationId() , orgId, CommonUtility.ApiType.REVERSE_DISBURSEMENT , loanDisbursementRequest.getId()))){
				logger.info("-------------------------already  saving disbursement detail of reverse api---------------");
				
				loanDisbursementRequest.setIsSaved(true);
				loanDisbursementRequest.setReason("already save disbursement detail of reverse api of applicaiton Id" + loanDisbursementRequest.getApplicationId() );
				loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.ALREADY_DONE_DISBURSEMENT);
				continue;
			}
			if(CommonUtils.isObjectListNull(loanDisbursementRequest.getDisbursedAmount(),loanDisbursementRequest.getDisbursementDate(),loanDisbursementRequest.getPaymentMode(),  loanDisbursementRequest.getAccountNo() , loanDisbursementRequest.getTransactionNo())){
				
				loanDisbursementRequest.setIsSaved(false);
				loanDisbursementRequest.setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.MANDAROTY_FIELD_MUST_NOT_BE_NULL);
				loanDisbursementRequest.setReason("Mandatory Fields Must Not be Null");
				String jsonString = MultipleJSONObjectHelper.getStringfromObject(loanDisbursementRequest);
				auditComponentBankToCW.saveBankToCWReqRes(jsonString , 	loanDisbursementRequest.getApplicationId() ,CommonUtility.ApiType.REVERSE_DISBURSEMENT, null , "Mandatory Fields Must Not be Null" , orgId ,loanDisbursementRequest.getId());
				continue;
			}
			

			loanDisbursementRequest = disbursementRequestValidation(sanctionPrimaryId , loanDisbursementRequest ,orgId , apiType);

			//saving req in bank to  cw-audit table
			String jsonString = null;
			
			if("SUCCESS".equalsIgnoreCase(loanDisbursementRequest.getReason()) || "First Disbursement".equalsIgnoreCase(loanDisbursementRequest.getReason())) {
				if(CommonUtility.ApiType.DISBURSEMENT == apiType) {
					if(saveLoanDisbursementDetail(loanDisbursementRequest)) {
						logger.info("Success msg while saveLoanDisbursementDetail() ----------------> msg " + loanDisbursementRequest.getReason()) ;
					}
				}else if(saveLoanDisbursementDetailbyId(loanDisbursementRequest)) {
					logger.info("Success msg while saveLoanDisbursementDetail() ----------------> msg " + loanDisbursementRequest.getReason()) ;
					loanDisbursementRequest.setIsSaved(true);
				}
				
				jsonString = MultipleJSONObjectHelper.getStringfromObject(loanDisbursementRequest);
				auditComponentBankToCW.saveBankToCWReqRes(jsonString , 	loanDisbursementRequest.getApplicationId() , apiType , new LoansResponse(loanDisbursementRequest.getReason() , HttpStatus.OK.value()) , null , orgId ,loanDisbursementRequest.getId());
			}else {
				jsonString =MultipleJSONObjectHelper.getStringfromObject(loanDisbursementRequest);
				auditComponentBankToCW.saveBankToCWReqRes(jsonString , 	loanDisbursementRequest.getApplicationId() ,CommonUtility.ApiType.REVERSE_DISBURSEMENT, new LoansResponse(loanDisbursementRequest.getReason() , HttpStatus.BAD_REQUEST.value()) , loanDisbursementRequest.getReason(), orgId ,loanDisbursementRequest.getId());
			}
		}
		return loanDisbursementRequestsList;
	}
	//its save , disbursement request by there primary key 'id' and isSavedFromBank
	@Override
	public Boolean saveLoanDisbursementDetailbyId(LoanDisbursementRequest loanDisbursementRequest ) throws IOException {
		logger.info("Enter in saveLoanDisbursementDetail() ----------------------->  LoanDisbursementRequest "+ loanDisbursementRequest);
		try { 
			LoanDisbursementDomain loanDisbursementDomain =  loanDisbursementRepository.findByBankDisbursementPrimaryKeyAndIsActive(loanDisbursementRequest.getId() ,   true);
			if(CommonUtils.isObjectNullOrEmpty(loanDisbursementDomain)) {
				loanDisbursementDomain = new LoanDisbursementDomain();
				loanDisbursementDomain.setIsActive(true);
				loanDisbursementDomain.setCreatedBy(loanDisbursementRequest.getActionBy());
				loanDisbursementDomain.setCreatedDate(new Date());
				BeanUtils.copyProperties(loanDisbursementRequest, loanDisbursementDomain , "id","createdBy" , "createdDate" , "isActive" , "modifiedBy" , "modifiedDate");
				loanDisbursementDomain.setBankDisbursementPrimaryKey(loanDisbursementRequest.getId());
				
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
			logger.info("Error/Exception in saveLoanDisbursementDetail() -----------------------> Message "+e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
}
