package com.capitaworld.service.loans.service.sanctionimpl;

import java.util.Date;


import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.sanction.LoanDisbursementDomain;
import com.capitaworld.service.loans.model.LoanDisbursementRequest;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.sanction.LoanDisbursementRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.sanction.LoanDisbursementService;
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
			if(proposalDetailsRepository.getApplicationIdByOrgId(loanDisbursementRequest.getApplicationId() ,orgId)) {
				Double amount=loanDisbursementRepository.getTotalDisbursedAmoount(loanDisbursementRequest.getApplicationId());
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

}
