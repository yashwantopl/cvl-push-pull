package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.LapParameter;
import com.capitaworld.service.loans.model.retail.LapParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.LapParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.LapLoanParameterService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
public class LapLoanParameterServiceImpl implements LapLoanParameterService {
	@Autowired
	private LapParameterRepository lapParameterRepository;
	@Override
	public boolean saveOrUpdate(LapParameterRequest lapParameterRequest) {
		// TODO Auto-generated method stub
		LapParameter lapParameter= null;

		lapParameter = lapParameterRepository.findOne(lapParameterRequest.getId());
		if (lapParameter == null) {
			return false;
		}
		BeanUtils.copyProperties(lapParameterRequest, lapParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		lapParameter.setModifiedBy(lapParameterRequest.getId());
		lapParameter.setModifiedDate(new Date());
		lapParameterRepository.save(lapParameter);
		return true;
	}

	@Override
	public LapParameterRequest getLapParameterRequest(Long id) {
		// TODO Auto-generated method stub
		LapParameterRequest lapParameterRequest= new LapParameterRequest();
		LapParameter lapParameter = lapParameterRepository.getByID(id);
		if(lapParameter==null)
			return null;
		BeanUtils.copyProperties(lapParameter, lapParameterRequest);
		return lapParameterRequest;
	}
	
	

}
