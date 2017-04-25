package com.capitaworld.service.loans.service.fundprovider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.HomeLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.LapParameter;
import com.capitaworld.service.loans.domain.fundprovider.LasParameter;
import com.capitaworld.service.loans.model.HomeLoanParameterRequest;
import com.capitaworld.service.loans.model.LapParameterRequest;
import com.capitaworld.service.loans.model.LasParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.HomeLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.LapParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.LasParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.service.fundprovider.HomeLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.LapLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.LasLoanParameterService;

@Service
public class LapLoanParameterServiceImpl implements LapLoanParameterService {
	private static final Logger logger = LoggerFactory.getLogger(LapLoanParameterServiceImpl.class.getName());
	@Autowired
	private LapParameterRepository lasParameterRepository;
	
	@Autowired
	private ProductMasterRepository productMasterRepository;
	@Override
	public boolean saveOrUpdate(LapParameterRequest lapParameterRequest) {
		// TODO Auto-generated method stub
		try { 
			LapParameter lapParameter= new LapParameter();
			BeanUtils.copyProperties(lapParameterRequest, lapParameter);
			if(null!=lapParameterRequest.getFpProductId())
				lapParameter.setFpProductId(productMasterRepository.findOne(lapParameterRequest.getFpProductId()));
			lapParameter = lasParameterRepository.save(lapParameter);
			return true;
			}

		 catch (Exception e) {
			logger.info("Exception  in save lapParameter  :-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public LapParameterRequest getLapParameterRequest(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
