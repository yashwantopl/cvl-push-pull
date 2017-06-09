package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundprovider.PersonalLoanParameterService;
import com.capitaworld.service.loans.service.teaser.primaryview.CommonTeaserViewService;
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
	private TermLoanPrimaryViewService termLoanPrimaryViewService; 
	
	@Autowired
	private PersonalLoanParameterService personalLoanParameterService;
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
			loansResponse.setData(personalLoanParameterService.getPersonalLoanParameterRequest(applicantId));
			loansResponse.setMessage(LoanType.PERSONAL_LOAN.getId().toString());
			break;
		/*case HOME_LOAN:
			productMaster = new HomeLoanParameter();
			if (productMasterRequest.getId() != null)
				homeLoanParameter = homeLoanParameterRepository.findOne(productMasterRequest.getId());
			if (homeLoanParameter != null)
				BeanUtils.copyProperties(homeLoanParameter, productMaster);
			break;
		case CAR_LOAN:
			productMaster = new CarLoanParameter();
			if (productMasterRequest.getId() != null)
				carLoanParameter = carLoanParameterRepository.findOne(productMasterRequest.getId());
			if (carLoanParameter != null)
				BeanUtils.copyProperties(carLoanParameter, productMaster);
			break;
		
		case LOAN_AGAINST_PROPERTY:
			productMaster = new LapParameter();
			if (productMasterRequest.getId() != null)
				lapParameter = lapParameterRepository.findOne(productMasterRequest.getId());
			if (lapParameter != null)
				BeanUtils.copyProperties(lapParameter, productMaster);
			break;
		case LOAN_AGAINST_SHARES_AND_SECUIRITIES:
			productMaster = new LasParameter();
			if (productMasterRequest.getId() != null)
				lasParameter = lasParameterRepository.findOne(productMasterRequest.getId());
			if (lasParameter != null)
				BeanUtils.copyProperties(lasParameter, productMaster);
			break;
*/
		default:
			break;
		}
		return null;
		
	}

}
