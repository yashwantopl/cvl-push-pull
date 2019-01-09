package com.capitaworld.service.loans.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capitaworld.service.loans.domain.BankCWAuditTrailDomain;
import com.capitaworld.service.loans.repository.banktocw.BankToCWAuditTrailRepository;
import com.capitaworld.service.users.client.UsersClient;

@Component
public class AuditComponentBankToCW {

	private static final Logger logger = LoggerFactory.getLogger(AuditComponentBankToCW.class);
	
	@Autowired
	private UsersClient userClient;
	
	@Autowired
	private BankToCWAuditTrailRepository bankToCWAuditTrailRepository;
	
	@Async
	public void saveBankToCWReqRes(String request, Long applicationId,Integer apiType , String status, String failureReason,
			Long orgId , Long  bankPrimaryKey) {
		logger.info("Enter in saveBankReqRes() ----------------------->{}===>{}===>{}===>{}===>{}===>{}",applicationId,apiType,status,failureReason,orgId,bankPrimaryKey);
		try {
		BankCWAuditTrailDomain bankCWAuditTrailDomain = new BankCWAuditTrailDomain();
		bankCWAuditTrailDomain.setApplicationId(applicationId);
		bankCWAuditTrailDomain.setOrgId(orgId);
		bankCWAuditTrailDomain.setBankRequest(request);
		//bankCWAuditTrailDomain.setCwResponse(MultipleJSONObjectHelper.getStringfromObject(loansResponse));
		bankCWAuditTrailDomain.setFailureReason(failureReason);
		bankCWAuditTrailDomain.setIsActive(true);
		bankCWAuditTrailDomain.setCreatedDate(new Date());
		bankCWAuditTrailDomain.setApiType(apiType);
		bankCWAuditTrailDomain.setBankPrimaryKey(bankPrimaryKey);
		bankCWAuditTrailDomain.setStatus(status);
		bankCWAuditTrailDomain =bankToCWAuditTrailRepository.save(bankCWAuditTrailDomain);
		logger.info("Exit saveLoanDisbursementDetail() -----------------------> BankCWAuditTrailDomain ==>" +bankCWAuditTrailDomain);
		}catch (Exception e) {
			logger.error("Error/Exception in saveBankReqRes() -----------------------> Message "+e);
			/*throw e;*/
		}
	}

	public   Long getOrgIdByCredential(String userName, String pwd) {
		 return userClient.getOrganisationDetailIdByCredential(userName, pwd);
		
	}
	public static void main(String[] args) {
		logger.info(""+new AuditComponentBankToCW().getOrgIdByCredential("sidbi@contactles", "contactless#$1SIDBI"));
	} 

	/*@Override
	public void saveBankReqRes(LoanDisbursementRequest loanDisbursementRequest,Integer apiType , LoansResponse loansResponse, String failureReason,
			Long orgId) {
		logger.info("Enter in saveBankReqRes() ----------------------->  LoanDisbursementRequest ==> " + loanDisbursementRequest);
		try {
		BankCWAuditTrailDomain bankCWAuditTrailDomain = new BankCWAuditTrailDomain();
		bankCWAuditTrailDomain.setApplicationId(loanDisbursementRequest!=null?loanDisbursementRequest.getApplicationId():null);
		bankCWAuditTrailDomain.setOrgId(orgId);
		bankCWAuditTrailDomain.setBankRequest(MultipleJSONObjectHelper.getStringfromObject(loanDisbursementRequest));
		bankCWAuditTrailDomain.setCwResponse(MultipleJSONObjectHelper.getStringfromObject(loansResponse.toString()));
		bankCWAuditTrailDomain.setFailureReason(failureReason);
		bankCWAuditTrailDomain.setIsActive(true);
		bankCWAuditTrailDomain.setCreatedDate(new Date());
		bankCWAuditTrailDomain.setApiType(apiType);;
		if (loansResponse.getStatus() == 200) {
			bankCWAuditTrailDomain.setStatus("SUCCESS");
		} else {
			bankCWAuditTrailDomain.setStatus("FAILURE");
		}
		bankCWAuditTrailDomain =bankToCWAuditTrailRepository.save(bankCWAuditTrailDomain);
		logger.info("Exit saveLoanDisbursementDetail() -----------------------> BankCWAuditTrailDomain ==>" +bankCWAuditTrailDomain);
		}catch (Exception e) {
			logger.error("Error/Exception in saveBankReqRes() -----------------------> Message "+e.getMessage());

			throw e;
		}
	}

	@Override
	public Long getOrgIdByCredential(String userName, String pwd) {
		 return usersClient.getOrganisationDetailIdByCredential(userName, pwd);

	}*/

	
}
