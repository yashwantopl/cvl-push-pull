package com.capitaworld.service.loans.service.fundprovider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.model.TermLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.TermLoanParameterService;

@Service
public class TermLoanParameterServiceImpl implements TermLoanParameterService {
	private static final Logger logger = LoggerFactory.getLogger(TermLoanParameterServiceImpl.class.getName());
	@Autowired
	private TermLoanParameterRepository termLoanParameterRepository;
	@Override
	public boolean saveOrUpdate(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub
		try { 
			TermLoanParameter termLoanParameter= new TermLoanParameter();
			BeanUtils.copyProperties(termLoanParameterRequest, termLoanParameter);

			termLoanParameter = termLoanParameterRepository.save(termLoanParameter);
			return true;
			}

		 catch (Exception e) {
			logger.info("Exception Throw while json parse in save TermLoanParameter :-");
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public TermLoanParameterRequest getTermLoanParameterRequest(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
