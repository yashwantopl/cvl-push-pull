package com.capitaworld.service.loans.service.sanctionimpl;

import java.io.IOException;

import java.util.Date;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.sanction.LoanDisbursementDomain;
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.model.LoanDisbursementRequest;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.sanction.LoanDisbursementRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.sanction.LoanDisbursementService;

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

	@Override
	public String requestValidation(LoanDisbursementRequest loanDisbursementRequest, Long orgId) throws IOException {
		logger.info("Enter in requestValidation() ----------------------->  LoanDisbursementRequest ==> " + loanDisbursementRequest);  
		try {
			
			if (orgId != null) {
				LoanSanctionDomain loanSanctionDomain  =loanSanctionRepository.findByAppliationId(loanDisbursementRequest.getApplicationId());
				
				if(loanSanctionDomain == null || loanSanctionDomain.getSanctionAmount()==null) {
					logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>" +"Please Sanction Before Disbursement for this applicationId==>" +loanDisbursementRequest.getApplicationId() );
					return "Please Sanction Before Disbursement for this applicationId ==>" +loanDisbursementRequest.getApplicationId();
				}
				Long recCount = proposalDetailsRepository.getApplicationIdCountByOrgId(loanDisbursementRequest.getApplicationId(), orgId);
				if (recCount != null && recCount > 0) {
					Double oldDisbursedAmount = loanDisbursementRepository.getTotalDisbursedAmount(loanDisbursementRequest.getApplicationId());
					if (oldDisbursedAmount != null) {
						if (loanSanctionDomain.getSanctionAmount() == oldDisbursedAmount) {
							logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>"+"Alread Your Disbursement is Complete");
							return "Alread Your Disbursement is Complete";
						}
						Double totalDisbursedAmount = oldDisbursedAmount + loanDisbursementRequest.getDisbursedAmount();
						if (loanSanctionDomain.getSanctionAmount() >= totalDisbursedAmount) {
							logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>"+"SUCCESS");
							return "SUCCESS";
						} else {
							logger.info("Exit saveLoanDisbursementDetail() -----------------------> msg==>"+ "Total Disbursement Amount EXCEED Sanction Amount");
							return "Total Disbursement Amount EXCEED Sanction Amount{} sanctionAmount ==>"+loanSanctionDomain.getSanctionAmount()+" ,  ( oldDisbursedAmount ==> "+oldDisbursedAmount+" +  newDisbursedAmount==>" +loanDisbursementRequest.getDisbursedAmount()+" ) = totalDisbursedAmount==>"+totalDisbursedAmount;
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

	}
