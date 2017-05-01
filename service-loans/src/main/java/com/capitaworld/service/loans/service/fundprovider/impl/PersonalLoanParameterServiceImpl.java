package com.capitaworld.service.loans.service.fundprovider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.PersonalLoanParameter;
import com.capitaworld.service.loans.model.PersonalLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.PersonalLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.service.fundprovider.PersonalLoanParameterService;

@Service
public class PersonalLoanParameterServiceImpl implements PersonalLoanParameterService {
	private static final Logger logger = LoggerFactory.getLogger(PersonalLoanParameterServiceImpl.class.getName());
	@Autowired
	private PersonalLoanParameterRepository personalLoanParameterRepository;
	
	@Autowired
	private ProductMasterRepository productMasterRepository;
	@Override
	public boolean saveOrUpdate(PersonalLoanParameterRequest personalLoanParameterRequest) {
		// TODO Auto-generated method stub
		try { 
			PersonalLoanParameter personalLoanParameter= new PersonalLoanParameter();
			BeanUtils.copyProperties(personalLoanParameterRequest, personalLoanParameter);
			if(null!=personalLoanParameterRequest.getFpProductId())
				personalLoanParameter.setFpProductId(productMasterRepository.findOne(personalLoanParameterRequest.getFpProductId()));
			personalLoanParameter = personalLoanParameterRepository.save(personalLoanParameter);
			return true;
			}

		 catch (Exception e) {
			logger.info("Exception  in save carLoanParameter  :-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public PersonalLoanParameterRequest getPersonalLoanParameterRequest(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
