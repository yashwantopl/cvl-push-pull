package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.PersonalLoanParameter;
import com.capitaworld.service.loans.model.PersonalLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.PersonalLoanParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.PersonalLoanParameterService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
public class  PersonalLoanParameterServiceImpl implements PersonalLoanParameterService {
	@Autowired
	private PersonalLoanParameterRepository personalLoanParameterRepository;
	
	@Override
	public boolean saveOrUpdate(PersonalLoanParameterRequest personalLoanParameterRequest) {
		// TODO Auto-generated method stub
		PersonalLoanParameter personalLoanParameter= null;

		personalLoanParameter = personalLoanParameterRepository.findOne(personalLoanParameterRequest.getId());
		if (personalLoanParameter == null) {
			return false;
		}
		BeanUtils.copyProperties(personalLoanParameterRequest, personalLoanParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		personalLoanParameter.setModifiedBy(personalLoanParameterRequest.getId());
		personalLoanParameter.setModifiedDate(new Date());
		personalLoanParameterRepository.save(personalLoanParameter);
		return true;
	}

	@Override
	public PersonalLoanParameterRequest getPersonalLoanParameterRequest(Long id) {
		// TODO Auto-generated method stub
		PersonalLoanParameterRequest personalLoanParameterRequest= new PersonalLoanParameterRequest();
		PersonalLoanParameter personalLoanParameter = personalLoanParameterRepository.getByID(id);
		if(personalLoanParameter==null)
			return null;
		BeanUtils.copyProperties(personalLoanParameter, personalLoanParameterRequest);
		return personalLoanParameterRequest;
	}
	
	

}
