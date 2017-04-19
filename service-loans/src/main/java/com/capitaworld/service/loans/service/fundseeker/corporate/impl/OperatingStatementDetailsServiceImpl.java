package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.OperatingStatementDetailsService;

@Service
public class OperatingStatementDetailsServiceImpl implements OperatingStatementDetailsService{

	@Autowired
	OperatingStatementDetailsRepository operatingStatementDetailsRepository;
	
	@Override
	public void saveOrUpdate(OperatingStatementDetails operatingStatementDetails) {
		// TODO Auto-generated method stub
		operatingStatementDetailsRepository.save(operatingStatementDetails);
		
	}

}
