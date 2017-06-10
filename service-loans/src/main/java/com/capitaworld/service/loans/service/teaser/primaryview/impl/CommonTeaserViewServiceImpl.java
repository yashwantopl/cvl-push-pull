package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.finalview.WorkingCapitalFinalViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundprovider.PersonalLoanParameterService;
import com.capitaworld.service.loans.service.teaser.finalview.CarLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.HomeLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.LapFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.PersonalLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.TermLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.WorkingCapitalFinalService;
import com.capitaworld.service.loans.service.teaser.finalview.impl.WorkingCapitalFinalServiceImpl;
import com.capitaworld.service.loans.service.teaser.primaryview.CarLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.CommonTeaserViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.HomeLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.LapPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.PersonalLoansViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.TermLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.WorkingCapitalPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.enums.LoanType;

@Service
@Transactional
public class CommonTeaserViewServiceImpl implements CommonTeaserViewService{

	@Autowired
	private LoanApplicationRepository loanApplicationMasterRepo;
	
	@Autowired
	private WorkingCapitalPrimaryViewService  workingCapitalPrimaryViewService;
	
	@Autowired
	private WorkingCapitalFinalService  workingCapitalFinalViewService;
	
	@Autowired
	private TermLoanPrimaryViewService termLoanPrimaryViewService;
	
	@Autowired
	private TermLoanFinalViewService termLoanFinalViewService;
	
	@Autowired
	private PersonalLoansViewService personalLoansViewService;
	
	@Autowired
	private PersonalLoanFinalViewService personalLoanFinalViewService;
	
	
	@Autowired
	private CarLoanPrimaryViewService carLoanPrimaryViewService; 
	
	@Autowired
	private CarLoanFinalViewService carLoanFinalViewService; 
	
	@Autowired
	private HomeLoanPrimaryViewService homeLoanPrimaryViewService;
	
	@Autowired
	private HomeLoanFinalViewService homeLoanFinalViewService;
	
	@Autowired
	private LapPrimaryViewService lapPrimaryViewService;
	
	@Autowired
	private LapFinalViewService lapFinalViewService;
	@Override
	public Boolean getPrimaryViewDetails(Long applicantId, LoansResponse loansResponse)
			throws Exception {
		// TODO Auto-generated method stub
		LoanApplicationMaster applicationMaster=loanApplicationMasterRepo.findOne(applicantId);
		if(CommonUtils.isObjectNullOrEmpty(applicationMaster))
		return false;
		
		LoanType loanType = LoanType.getById(Integer.parseInt(applicationMaster.getProductId().toString()));
		if (loanType == null)
			return false;
		switch (loanType) {
		case WORKING_CAPITAL:
				loansResponse.setData(workingCapitalPrimaryViewService.getWorkingCapitalPrimaryViewDetails(applicantId, null, null));
				loansResponse.setMessage(LoanType.WORKING_CAPITAL.getId().toString());
			break;
		case TERM_LOAN:
			loansResponse.setData(termLoanPrimaryViewService.getTermLoanPrimaryViewDetails(applicantId, null, null));
			loansResponse.setMessage(LoanType.TERM_LOAN.getId().toString());
			break;
		case PERSONAL_LOAN:
			loansResponse.setData(personalLoansViewService.getPersonalLoansPrimaryViewDetails(applicantId));
			loansResponse.setMessage(LoanType.PERSONAL_LOAN.getId().toString());
			break;
		case CAR_LOAN:
			loansResponse.setData(carLoanPrimaryViewService.getCarLoanPrimaryViewDetails(applicantId));
			loansResponse.setMessage(LoanType.CAR_LOAN.getId().toString());
			break;
		case HOME_LOAN:
			loansResponse.setData(homeLoanPrimaryViewService.getHomeLoanPrimaryViewDetails(applicantId));
			loansResponse.setMessage(LoanType.HOME_LOAN.getId().toString());
			break;
		case LOAN_AGAINST_PROPERTY:
			loansResponse.setData(lapPrimaryViewService.getLapPrimaryViewDetails(applicantId));
			loansResponse.setMessage(LoanType.LOAN_AGAINST_PROPERTY.getId().toString());
			break;
		

		default:
			break;
		}
		return null;
		
	}
	@Override
	public Boolean getFinalViewDetails(Long applicantId, LoansResponse loansResponse) throws Exception {
		// TODO Auto-generated method stub
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
		case PERSONAL_LOAN:
			loansResponse.setData(personalLoanFinalViewService.getPersonalLoanFinalViewDetails(applicantId));
			loansResponse.setMessage(LoanType.PERSONAL_LOAN.getId().toString());
			break;
		case CAR_LOAN:
			loansResponse.setData(carLoanFinalViewService.getCarLoanFinalViewDetails(applicantId));
			loansResponse.setMessage(LoanType.CAR_LOAN.getId().toString());
			break;
		case HOME_LOAN:
			loansResponse.setData(homeLoanFinalViewService.getHomeLoanFinalViewDetails(applicantId));
			loansResponse.setMessage(LoanType.HOME_LOAN.getId().toString());
			break;
		case LOAN_AGAINST_PROPERTY:
			loansResponse.setData(lapFinalViewService.getLapFinalViewDetails(applicantId));
			loansResponse.setMessage(LoanType.LOAN_AGAINST_PROPERTY.getId().toString());
			break;
		

		default:
			break;
		}
		return null;
	}

}
