package com.capitaworld.service.loans.service.teaser.finalview.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.teaser.finalview.PersonalLoanFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.teaser.finalview.PersonalLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.RetailFinalCommonApplicantService;
import com.capitaworld.service.loans.service.teaser.primaryview.PersonalLoansViewService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PersonalLoanFinalViewServiceImpl implements PersonalLoanFinalViewService{
	private static final Logger logger = LoggerFactory.getLogger(PersonalLoanFinalViewServiceImpl.class);
	@Autowired
	private RetailApplicantDetailRepository applicantRepository;
    
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private CoApplicantService coApplicantService;
	
	@Autowired
	private GuarantorService guarantorService;
	
	@Autowired
	private RetailFinalCommonApplicantService finalCommonService;
	
	@Autowired
	private PersonalLoansViewService primaryViewPLService;
	
	@Override
	public PersonalLoanFinalViewResponse getPersonalLoanFinalViewDetails(Long applicantId) throws Exception {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.getOne(applicantId);
		PersonalLoanFinalViewResponse plFinalViewResponse = new PersonalLoanFinalViewResponse();
		RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(applicationMaster.getUserId(), applicantId);
		if (!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
			RetailFinalViewResponse finalViewResponse = new RetailFinalViewResponse();
			//applicant final common details
			finalViewResponse.setApplicantCommonDetails(finalCommonService.getApplicantCommonInfo(applicantId, applicantDetail));
			
			//co-applicant final common details
			try {
				finalViewResponse.setCoApplicantCommonDetails(coApplicantService.getCoApplicantFinalResponse(applicantId, applicationMaster.getUserId()));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting CoApplicant final details");
			}
			
			//guarantor final common details
			try {
				finalViewResponse.setGuarantorCommonDetails(guarantorService.getGuarantorFinalViewResponse(applicantId, applicationMaster.getUserId()));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting Guarantor final details");
			}
			plFinalViewResponse.setFinalViewResponse(finalViewResponse);
			
			//Personal Loan primary details
			try { 
				plFinalViewResponse.setPersonalLoansPrimaryViewResponse(primaryViewPLService.getPersonalLoansPrimaryViewDetails(applicantId, applicationMaster.getUserId()));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting PL primary details");
			}
			
		}
		return plFinalViewResponse;
	}

	
}
