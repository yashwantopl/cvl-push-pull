package com.capitaworld.service.loans.service.fundprovider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.CarLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameter;
import com.capitaworld.service.loans.model.CarLoanParameterRequest;
import com.capitaworld.service.loans.model.WorkingCapitalParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.CarLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundprovider.WorkingCapitalParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.CarLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.WorkingCapitalParameterService;

@Service
public class CarLoanParameterServiceImpl implements CarLoanParameterService {
	private static final Logger logger = LoggerFactory.getLogger(CarLoanParameterServiceImpl.class.getName());
	@Autowired
	private CarLoanParameterRepository carLoanParameterRepository;
	
	@Autowired
	private ProductMasterRepository productMasterRepository;
	@Override
	public boolean saveOrUpdate(CarLoanParameterRequest carLoanParameterRequest) {
		// TODO Auto-generated method stub
		try { 
			CarLoanParameter carLoanParameter= new CarLoanParameter();
			BeanUtils.copyProperties(carLoanParameterRequest, carLoanParameter);
			if(null!=carLoanParameterRequest.getFpProductId())
				carLoanParameter.setFpProductId(productMasterRepository.findOne(carLoanParameterRequest.getFpProductId()));
				carLoanParameter = carLoanParameterRepository.save(carLoanParameter);
			return true;
			}

		 catch (Exception e) {
			logger.info("Exception  in save carLoanParameter  :-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CarLoanParameterRequest getCarLoanParameterRequest(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
