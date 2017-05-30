package com.capitaworld.service.loans.service.teaser.finalview.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.teaser.finalview.LapFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.teaser.finalview.LapFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.RetailFinalCommonApplicantService;
import com.capitaworld.service.loans.service.teaser.primaryview.LapPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class LapFinalViewServiceImpl implements LapFinalViewService{

	private static final Logger logger = LoggerFactory.getLogger(HomeLoanFinalViewServiceImpl.class);

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
	private LapPrimaryViewService lapPrimaryService;
	
	@Override
	public LapFinalViewResponse getLapFinalViewDetails(Long applicantId) throws Exception {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicantId);
		LapFinalViewResponse lapFinalViewResponse = new LapFinalViewResponse();
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
			
			//LAP Loan primary details
			try { 
				lapFinalViewResponse.setLapPrimaryViewResponse(lapPrimaryService.getLapPrimaryViewDetails(applicantId));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting LAP primary details");
			}
			
			
		}
		return lapFinalViewResponse;
	}

}
