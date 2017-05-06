package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.LasParameter;
import com.capitaworld.service.loans.model.retail.LasParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.LasParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.LasLoanParameterService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
public class LasLoanParameterServiceImpl implements LasLoanParameterService {
	@Autowired
	private LasParameterRepository lasParameterRepository;
	
	@Override
	public boolean saveOrUpdate(LasParameterRequest lasParameterRequest) {
		// TODO Auto-generated method stub
		LasParameter lasParameter= null;

		lasParameter = lasParameterRepository.findOne(lasParameterRequest.getId());
		if (lasParameter == null) {
			return false;
		}
		BeanUtils.copyProperties(lasParameterRequest, lasParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		lasParameter.setModifiedBy(lasParameterRequest.getId());
		lasParameter.setModifiedDate(new Date());
		lasParameterRepository.save(lasParameter);
		return true;
	}

	@Override
	public LasParameterRequest getLasParameterRequest(Long id) {
		// TODO Auto-generated method stub
		LasParameterRequest lasParameterRequest= new LasParameterRequest();
		LasParameter lasParameter = lasParameterRepository.getByID(id);
		if(lasParameter==null)
			return null;
		BeanUtils.copyProperties(lasParameter, lasParameterRequest);
		return lasParameterRequest;
	}
	
	

}
