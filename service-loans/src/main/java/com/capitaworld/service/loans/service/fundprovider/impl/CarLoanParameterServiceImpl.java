package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.CarLoanParameter;
import com.capitaworld.service.loans.model.CarLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.CarLoanParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.CarLoanParameterService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
public class CarLoanParameterServiceImpl implements CarLoanParameterService {
	@Autowired
	private CarLoanParameterRepository carLoanParameterRepository;
	
	@Override
	public boolean saveOrUpdate(CarLoanParameterRequest carLoanParameterRequest) {
		// TODO Auto-generated method stub
		CarLoanParameter carLoanParameter= null;

		carLoanParameter = carLoanParameterRepository.findOne(carLoanParameterRequest.getId());
		if (carLoanParameter == null) {
			return false;
		}
		BeanUtils.copyProperties(carLoanParameterRequest, carLoanParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		carLoanParameter.setModifiedBy(carLoanParameterRequest.getId());
		carLoanParameter.setModifiedDate(new Date());
		carLoanParameterRepository.save(carLoanParameter);
		return true;
	}

	@Override
	public CarLoanParameterRequest getCarLoanParameterRequest(Long id) {
		CarLoanParameterRequest carLoanParameterRequest= new CarLoanParameterRequest();
		CarLoanParameter carLoanParameter = carLoanParameterRepository.getByID(id);
		if(carLoanParameter==null)
			return null;
		BeanUtils.copyProperties(carLoanParameter, carLoanParameterRequest);
		return carLoanParameterRequest;
	}
	
	

}
