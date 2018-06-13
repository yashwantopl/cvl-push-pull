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
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.repository.banktocw.BankToCWAuditTrailRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.sanction.LoanSanctionService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.users.client.UsersClient;

/**
 * @author Ankit
 *
 */

@Service
@Transactional
public class LoanSanctionServiceImpl implements LoanSanctionService {
	private static final Logger logger = LoggerFactory.getLogger(LoanSanctionServiceImpl.class);

	@Autowired
	private LoanSanctionRepository loanSanctionRepository;

	@Autowired 
	private UsersClient userClient;
	
	@Autowired 
	private ProposalDetailsRepository proposalDetailsRepository; 
	
	@Autowired
	private BankToCWAuditTrailRepository bankToCWAuditTrailRepository; 
	@Override
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) {
		
		logger.info("Enter in saveLoanSanctionDetail() ----------------------->  LoanSanctionRequest "+ loanSanctionRequest);
		
		LoanSanctionDomain loanSanctionDomain =loanSanctionRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
		if(CommonUtils.isObjectNullOrEmpty(loanSanctionDomain) ) {
			loanSanctionDomain = new LoanSanctionDomain();
		}
		BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomain);
		loanSanctionDomain.setIsActive(true);
		loanSanctionDomain.setCreatedDate(new Date());
		loanSanctionDomain.setModifiedDate(new Date());
		logger.info("Exit saveLoanSanctionDetail() -----------------------> LoanSanctionDomain "+ loanSanctionDomain);
		return loanSanctionRepository.save(loanSanctionDomain) != null;

	}
	
	@Override
	public String requestValidation(String userName, String password, Long applicationId) {
	                	
		 Long orgId =getOrgIdByCredential(userName, password);
		 if(orgId != null) {
			 Long recCount = proposalDetailsRepository.getApplicationIdCountByOrgId(applicationId ,orgId);
			if(recCount != null && recCount  > 0) {
					return  "SUCCESS";
			}else {
				return "InvalId ApplicationId ";
			}
		 }else {
			 return "Invalid Credential";
		 }
	}
	@Override
	public void saveBankReqRes(LoanSanctionRequest loanSanctionRequest, LoansResponse loansResponse, String msg,Long orgId) throws IOException {
		
		 BankCWAuditTrailDomain bankCWAuditTrailDomain = new BankCWAuditTrailDomain();
		 bankCWAuditTrailDomain.setApplicationId(loanSanctionRequest.getApplicationId());
		 bankCWAuditTrailDomain.setOrgId(getOrgIdByCredential(loanSanctionRequest.getUserName(), loanSanctionRequest.getPassword()));
		 bankCWAuditTrailDomain.setBankRequest(MultipleJSONObjectHelper.getStringfromObject(loanSanctionRequest));
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
	
	@Override
	public Long getOrgIdByCredential(String userName, String pwd) {
		 return userClient.getOrganisationDetailIdByCredential(userName, pwd);
		
	}

}
