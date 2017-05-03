package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.HomeLoanParameter;
import com.capitaworld.service.loans.model.HomeLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.HomeLoanParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.HomeLoanParameterService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
public class HomeLoanParameterServiceImpl implements HomeLoanParameterService {
	@Autowired
	private HomeLoanParameterRepository homeLoanParameterRepository;
	
	@Override
	public boolean saveOrUpdate(HomeLoanParameterRequest homeLoanParameterRequest) {
		// TODO Auto-generated method stub
		HomeLoanParameter homeLoanParameter= null;

		homeLoanParameter = homeLoanParameterRepository.findOne(homeLoanParameterRequest.getId());
		if (homeLoanParameter == null) {
			return false;
		}
		BeanUtils.copyProperties(homeLoanParameterRequest, homeLoanParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		homeLoanParameter.setModifiedBy(homeLoanParameterRequest.getId());
		homeLoanParameter.setModifiedDate(new Date());
		homeLoanParameterRepository.save(homeLoanParameter);
		return true;
	}

	@Override
	public HomeLoanParameterRequest getHomeLoanParameterRequest(Long id) {
		// TODO Auto-generated method stub
		HomeLoanParameterRequest homeLoanParameterRequest= new HomeLoanParameterRequest();
		HomeLoanParameter homeLoanParameter = homeLoanParameterRepository.getByID(id);
		if(homeLoanParameter==null)
			return null;
		BeanUtils.copyProperties(homeLoanParameter, homeLoanParameterRequest);
		return homeLoanParameterRequest;
	}
	
	

}
