package com.capitaworld.service.loans.service.fundseeker.agri.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.repository.common.LoanRepository;
import com.capitaworld.service.loans.service.fundseeker.agri.AgriLoanService;

@Service
public class AgriLoanServiceImpl implements AgriLoanService {

	
	@Autowired
	private LoanRepository loanRepository; 
	
	@Override
	@Transactional(readOnly = true)
	public String getApplications(Integer orgId, Integer status) {
		return loanRepository.getAgriLoanApplicationsByOrgIdAndStatus(orgId, status);
	}

}
