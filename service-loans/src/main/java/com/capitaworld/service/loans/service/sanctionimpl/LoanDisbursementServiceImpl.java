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
public class LoanDisbursementServiceImpl implements LoanDisbursementService{
		
	private static final Logger logger = LoggerFactory.getLogger(LoanDisbursementServiceImpl.class);
	
	@Autowired 
	private LoanDisbursementRepository loanDisbursementRepository;
	
	@Autowired 
	private UsersClient usersClient;
	
	@Autowired
	private ProposalDetailsRepository proposalDetailsRepository;  
	
	@Autowired
	private LoanSanctionRepository  loanSanctionRepository;
	
	@Autowired
	private BankToCWAuditTrailRepository bankToCWAuditTrailRepository; 
	
	@Override
	public Boolean saveLoanDisbursementDetail(LoanDisbursementRequest loanDisbursementRequest) {
		logger.info("Enter in saveLoanDisbursementDetail() ----------------------->  LoanDisbursementRequest "+ loanDisbursementRequest );
		LoanDisbursementDomain loanDisbursementDomain =new LoanDisbursementDomain();
		BeanUtils.copyProperties(loanDisbursementRequest, loanDisbursementDomain);
		loanDisbursementDomain.setIsActive(true);
		loanDisbursementDomain.setCreatedDate(new Date());
		loanDisbursementDomain.setModifiedDate(new Date());
		logger.info("Exit saveLoanDisbursementDetail() -----------------------> ");
		return loanDisbursementRepository.save(loanDisbursementDomain)!=null;
	}
	
	@Override
	public String requestValidation(LoanDisbursementRequest loanDisbursementRequest) {
		Long orgId =usersClient.getOrganisationDetailIdByCredential(loanDisbursementRequest.getUserName(), loanDisbursementRequest.getPassword());
		if(orgId!=null) {
			Long recCount  = proposalDetailsRepository.getApplicationIdCountByOrgId(loanDisbursementRequest.getApplicationId() ,orgId);
			if(recCount   !=null &&  recCount  > 0) {
				Double amount=loanDisbursementRepository.getTotalDisbursedAmount(loanDisbursementRequest.getApplicationId());
				if(amount!=null) {
					Double totalAmount = amount+loanDisbursementRequest.getDisbursedAmount();
					if(loanSanctionRepository.findByAppliationId(loanDisbursementRequest.getApplicationId()).getSanctionAmount() <= totalAmount) {
						return "SUCCESS";	
					}else {
						return "Total Disbursement Amount EXCEED Sanction Amount";
					}
				}else {
					return "First Disbursement";
					}
			}else {
				return  "Invalid ApplicationId ";
			}
		}else {
			 return "Invalid Credential";
		 }
	}

	@Override
	public void saveBankReqRes(LoanDisbursementRequest loanDisbursementRequest, LoansResponse loansResponse, String msg, Long orgId) throws IOException {
	
		 BankCWAuditTrailDomain bankCWAuditTrailDomain = new BankCWAuditTrailDomain();
		 bankCWAuditTrailDomain.setApplicationId(loanDisbursementRequest.getApplicationId());
		 bankCWAuditTrailDomain.setBankRequest(MultipleJSONObjectHelper.getStringfromObject(loanDisbursementRequest));
		 bankCWAuditTrailDomain.setCwResponse(MultipleJSONObjectHelper.getStringfromObject(loansResponse.toString()));
		 bankCWAuditTrailDomain.setMsg(msg);
		 bankCWAuditTrailDomain.setIsActive(true);
		 bankCWAuditTrailDomain.setCreatedDate(new Date());
		 if(loansResponse.getStatus()==200) {
			 bankCWAuditTrailDomain.setStatus("SUCCESS");
		 }else {
			 bankCWAuditTrailDomain.setStatus("FAILURE");
		 }
		 		bankToCWAuditTrailRepository.save(bankCWAuditTrailDomain);
	}
}
