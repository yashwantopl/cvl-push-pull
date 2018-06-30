package com.capitaworld.service.loans.service.sanctionimpl;

import java.util.Date;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.repository.banktocw.BankToCWAuditTrailRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.sanction.LoanSanctionService;
import com.capitaworld.service.loans.utils.CommonUtils;

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
	private ProposalDetailsRepository proposalDetailsRepository; 
	
	@Override
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) throws Exception {
		try {
		logger.info("Enter in saveLoanSanctionDetail() ----------------------->  LoanSanctionRequest==> "+ loanSanctionRequest);
		
		LoanSanctionDomain loanSanctionDomainOld =loanSanctionRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
		LoanSanctionDomain loanSanctionDomainNew=null;
		if(CommonUtils.isObjectNullOrEmpty(loanSanctionDomainOld) ) {
			loanSanctionDomainNew = new LoanSanctionDomain();
			loanSanctionDomainNew.setCreatedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainNew.setCreatedDate(new Date()); 
		}
		BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainNew);
		loanSanctionDomainNew.setIsActive(true);
		loanSanctionDomainNew.setModifiedBy(loanSanctionRequest.getActionBy());
		loanSanctionDomainNew.setModifiedDate(new Date());
		logger.info("Exit saveLoanSanctionDetail() -----------------------> LoanSanctionDomain "+ loanSanctionDomainNew);
		
		return loanSanctionRepository.save(loanSanctionDomainNew) != null;
		}catch (Exception e) {
			logger.info("Error/Exception in saveLoanSanctionDetail() -----------------------> Message "+e.getMessage());
			e.printStackTrace();
			throw e;
		}

	}
	
	@Override
	public String requestValidation( Long applicationId,Long orgId) throws Exception {
		logger.info("Enter in requestValidation() ----------------------->  applicationId==> "+ applicationId);
	        try {        	
		 
		 if(orgId != null) {
			 Long recCount = proposalDetailsRepository.getApplicationIdCountByOrgId(applicationId ,orgId);
			if(recCount != null && recCount  > 0) {
					return  "SUCCESS";
			}else {
				return "Invalid ApplicationId ";
			}
		 }else {
			 return "Invalid Credential";
		 }
	        }catch (Exception e) {
	        	logger.info("Error/Exception in requestValidation() ----------------------->  Message "+ e.getMessage());
	        	throw e;
			}
	}

}
