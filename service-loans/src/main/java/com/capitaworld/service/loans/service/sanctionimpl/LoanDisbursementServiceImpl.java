package com.capitaworld.service.loans.service.sanctionimpl;

import java.io.IOException;
import java.util.Date;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capitaworld.service.loans.domain.BankCWAuditTrailDomain;
import com.capitaworld.service.loans.domain.sanction.LoanDisbursementDomain;
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.model.LoanDisbursementRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.repository.banktocw.BankToCWAuditTrailRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.sanction.LoanDisbursementRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.sanction.LoanDisbursementService;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.users.client.UsersClient;

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
	private UsersClient usersClient;

	@Autowired
	private ProposalDetailsRepository proposalDetailsRepository;

	@Autowired
	private LoanSanctionRepository loanSanctionRepository;

	@Autowired
	private BankToCWAuditTrailRepository bankToCWAuditTrailRepository;

	@Override
	public Boolean saveLoanDisbursementDetail(LoanDisbursementRequest loanDisbursementRequest) throws IOException {
		logger.info("Enter in saveLoanDisbursementDetail() ----------------------->  LoanDisbursementRequest "+ loanDisbursementRequest);
		try {
			LoanDisbursementDomain loanDisbursementDomain = new LoanDisbursementDomain();
			BeanUtils.copyProperties(loanDisbursementRequest, loanDisbursementDomain);
			loanDisbursementDomain.setIsActive(true);
			loanDisbursementDomain.setCreatedDate(new Date());
			loanDisbursementDomain.setModifiedDate(new Date());
			logger.info("Exit saveLoanDisbursementDetail() -----------------------> ");
			return loanDisbursementRepository.save(loanDisbursementDomain) != null;
		} catch (Exception e) {
			logger.info("Error/Exception in saveLoanDisbursementDetail() -----------------------> Message "+e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public String requestValidation(LoanDisbursementRequest loanDisbursementRequest, Long orgId) throws IOException {
		logger.info("Enter in requestValidation() ----------------------->  LoanDisbursementRequest ==> " + loanDisbursementRequest);  
		try {
			
			if (orgId != null) {
				Long recCount = proposalDetailsRepository
						.getApplicationIdCountByOrgId(loanDisbursementRequest.getApplicationId(), orgId);
				if (recCount != null && recCount > 0) {
					Double amount = loanDisbursementRepository
							.getTotalDisbursedAmount(loanDisbursementRequest.getApplicationId());
					if (amount != null) {
						Double totalAmount = amount + loanDisbursementRequest.getDisbursedAmount();
						LoanSanctionDomain loanSanctionDomain  =loanSanctionRepository.findByAppliationId(loanDisbursementRequest.getApplicationId());
						
						if(loanSanctionDomain == null || loanSanctionDomain.getSanctionAmount()==null) {
							logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>" +"Please Sanction Before Disbursement for this applicationId" +loanDisbursementRequest.getApplicationId() );
							return "Please Sanction Before Disbursement for this applicationId" +loanDisbursementRequest.getApplicationId();
						}
						if (loanSanctionDomain.getSanctionAmount() <= totalAmount) {
							logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>"+"SUCCESS");
							return "SUCCESS";
						} else {
							logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>"+ "Total Disbursement Amount EXCEED Sanction Amount");
							return "Total Disbursement Amount EXCEED Sanction Amount";
						}
					} else {
						logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>" +"First Disbursement");
						return "First Disbursement";
					}
				} else {
					logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>" +"Invalid ApplicationId ");
					return "Invalid ApplicationId ";
				}
			} else {
				logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>" +"Invalid Credential");
				return "Invalid Credential";
			}
		} catch (Exception e) {
			logger.info("Error/Exception in requestValidation() -----------------------> Message "+e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void saveBankReqRes(LoanDisbursementRequest loanDisbursementRequest, LoansResponse loansResponse, String msg,
			Long orgId) throws IOException {
		logger.info("Enter in saveBankReqRes() ----------------------->  LoanDisbursementRequest ==> " + loanDisbursementRequest);
		try {
		BankCWAuditTrailDomain bankCWAuditTrailDomain = new BankCWAuditTrailDomain();
		bankCWAuditTrailDomain.setApplicationId(loanDisbursementRequest!=null?loanDisbursementRequest.getApplicationId():null);
		bankCWAuditTrailDomain.setOrgId(orgId);
		bankCWAuditTrailDomain.setBankRequest(MultipleJSONObjectHelper.getStringfromObject(loanDisbursementRequest));
		bankCWAuditTrailDomain.setCwResponse(MultipleJSONObjectHelper.getStringfromObject(loansResponse.toString()));
		bankCWAuditTrailDomain.setMsg(msg);
		bankCWAuditTrailDomain.setIsActive(true);
		bankCWAuditTrailDomain.setCreatedDate(new Date());
		if (loansResponse.getStatus() == 200) {
			bankCWAuditTrailDomain.setStatus("SUCCESS");
		} else {
			bankCWAuditTrailDomain.setStatus("FAILURE");
		}
		bankCWAuditTrailDomain =bankToCWAuditTrailRepository.save(bankCWAuditTrailDomain);
		logger.info("Exit saveLoanDisbursementDetail() -----------------------> BankCWAuditTrailDomain ==>" +bankCWAuditTrailDomain);
		}catch (Exception e) {
			logger.info("Error/Exception in saveBankReqRes() -----------------------> Message "+e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Long getOrgIdByCredential(String userName, String pwd) {
		 return usersClient.getOrganisationDetailIdByCredential(userName, pwd);
		
	}
}
