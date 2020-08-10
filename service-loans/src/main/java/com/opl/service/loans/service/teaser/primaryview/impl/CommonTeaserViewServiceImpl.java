package com.opl.service.loans.service.teaser.primaryview.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.oneform.enums.LoanType;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.service.teaser.finalview.TermLoanFinalViewService;
import com.opl.service.loans.service.teaser.finalview.UnsecuredLoanFinalViewService;
import com.opl.service.loans.service.teaser.finalview.WorkingCapitalFinalService;
import com.opl.service.loans.service.teaser.primaryview.CommonTeaserViewService;
import com.opl.service.loans.service.teaser.primaryview.CorporatePrimaryViewService;

@Service
@Transactional
public class CommonTeaserViewServiceImpl implements CommonTeaserViewService{
	private static final Logger logger = LoggerFactory.getLogger(CommonTeaserViewServiceImpl.class);

	@Autowired
	private LoanApplicationRepository loanApplicationMasterRepo;
	
	@Autowired
	private CorporatePrimaryViewService corporatePrimaryViewService;

	@Autowired
	private WorkingCapitalFinalService  workingCapitalFinalViewService;
	
	@Autowired
	private TermLoanFinalViewService termLoanFinalViewService;
	
	@Autowired
	private UnsecuredLoanFinalViewService unsecuredLoanFinalViewService;
	
	@Override
	public Boolean getPrimaryViewDetails(Long applicantId, LoansResponse loansResponse)
			throws LoansException {
		logger.info("start getPrimaryViewDetails ");
		LoanApplicationMaster applicationMaster=loanApplicationMasterRepo.findOne(applicantId);
		if(CommonUtils.isObjectNullOrEmpty(applicationMaster))
			return false;
		
		LoanType loanType = LoanType.getById(Integer.parseInt(applicationMaster.getProductId().toString()));
		if (loanType == null)
			return false;
		switch (loanType) {
		case WORKING_CAPITAL:
				/*loansResponse.setData(workingCapitalPrimaryViewService.getWorkingCapitalPrimaryViewDetails(applicantId, null, null));
				loansResponse.setMessage(LoanType.WORKING_CAPITAL.getId().toString());*/
				loansResponse.setData(corporatePrimaryViewService.getCorporatePrimaryViewDetails(applicantId,null, null, null));
				loansResponse.setMessage(LoanType.WORKING_CAPITAL.getId().toString());
			break;
		case TERM_LOAN:
			//loansResponse.setData(termLoanPrimaryViewService.getTermLoanPrimaryViewDetails(applicantId, null, null));
			loansResponse.setData(corporatePrimaryViewService.getCorporatePrimaryViewDetails(applicantId,null, null, null));
			loansResponse.setMessage(LoanType.TERM_LOAN.getId().toString());
			break;
		case UNSECURED_LOAN:
			//loansResponse.setData(unsecuredLoanPrimaryViewService.getUnsecuredLoanPrimaryViewDetails(applicantId, null, null));
			loansResponse.setData(corporatePrimaryViewService.getCorporatePrimaryViewDetails(applicantId,null, null, null));
			loansResponse.setMessage(LoanType.UNSECURED_LOAN.getId().toString());
			break;
		

		default:
			break;
		}
		logger.info("end getPrimaryViewDetails ");
		return null;
		
	}
	@Override
	public Boolean getFinalViewDetails(Long applicantId, LoansResponse loansResponse) throws LoansException {
		logger.info("start getFinalViewDetails ");
		LoanApplicationMaster applicationMaster=loanApplicationMasterRepo.findOne(applicantId);
		if(CommonUtils.isObjectNullOrEmpty(applicationMaster))
			return false;
		
		LoanType loanType = LoanType.getById(Integer.parseInt(applicationMaster.getProductId().toString()));
		if (loanType == null)
			return false;
		switch (loanType) {
		case WORKING_CAPITAL:
				loansResponse.setData(workingCapitalFinalViewService.getWorkingCapitalFinalViewDetails(applicantId));
				loansResponse.setMessage(LoanType.WORKING_CAPITAL.getId().toString());
			break;
		case TERM_LOAN:
			loansResponse.setData(termLoanFinalViewService.getTermLoanFinalViewDetails(applicantId));
			loansResponse.setMessage(LoanType.TERM_LOAN.getId().toString());
			break;
		case UNSECURED_LOAN:
			loansResponse.setData(unsecuredLoanFinalViewService.getUnsecuredLoanFinalViewDetails(applicantId, null, null));
			loansResponse.setMessage(LoanType.UNSECURED_LOAN.getId().toString());
			break;
		default:
			break;
		}
		logger.info("end getFinalViewDetails ");
		return null;
	}

}
