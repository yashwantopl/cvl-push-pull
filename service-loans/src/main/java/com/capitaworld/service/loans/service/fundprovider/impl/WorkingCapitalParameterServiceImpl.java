package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameter;
import com.capitaworld.service.loans.model.corporate.WorkingCapitalParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.WorkingCapitalParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.WorkingCapitalParameterService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
public class WorkingCapitalParameterServiceImpl implements WorkingCapitalParameterService {
	@Autowired
	private WorkingCapitalParameterRepository workingCapitalParameterRepository;

	@Override
	public boolean saveOrUpdate(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		// TODO Auto-generated method stub
		WorkingCapitalParameter workingCapitalParameter = null;

		workingCapitalParameter = workingCapitalParameterRepository.findOne(workingCapitalParameterRequest.getId());
		if (workingCapitalParameter == null) {
			return false;
		}
		BeanUtils.copyProperties(workingCapitalParameterRequest, workingCapitalParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		workingCapitalParameter.setModifiedBy(workingCapitalParameterRequest.getId());
		workingCapitalParameter.setModifiedDate(new Date());
		workingCapitalParameterRepository.save(workingCapitalParameter);
		return true;
	}

	@Override
	public WorkingCapitalParameterRequest getWorkingCapitalParameter(Long id) {
		WorkingCapitalParameterRequest workingCapitalParameterRequest= new WorkingCapitalParameterRequest();
		WorkingCapitalParameter loanParameter = workingCapitalParameterRepository.getByID(id);
		if(loanParameter==null)
			return null;
		BeanUtils.copyProperties(loanParameter, workingCapitalParameterRequest);
		return workingCapitalParameterRequest;
	}
	
	

}
