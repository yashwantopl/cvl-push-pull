package com.capitaworld.service.loans.service.fundprovider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.LasParameter;
import com.capitaworld.service.loans.model.LasParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.LasParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.service.fundprovider.LasLoanParameterService;

@Service
public class LasLoanParameterServiceImpl implements LasLoanParameterService {
	private static final Logger logger = LoggerFactory.getLogger(LasLoanParameterServiceImpl.class.getName());
	@Autowired
	private LasParameterRepository lasParameterRepository;
	
	@Autowired
	private ProductMasterRepository productMasterRepository;
	@Override
	public boolean saveOrUpdate(LasParameterRequest lasParameterRequest) {
		// TODO Auto-generated method stub
		try { 
			LasParameter lasParameter= new LasParameter();
			BeanUtils.copyProperties(lasParameterRequest, lasParameter);
			if(null!=lasParameterRequest.getFpProductId())
				lasParameter.setFpProductId(productMasterRepository.findOne(lasParameterRequest.getFpProductId()));
			lasParameter = lasParameterRepository.save(lasParameter);
			return true;
			}

		 catch (Exception e) {
			logger.info("Exception  in save lasParameter  :-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public LasParameterRequest getLasParameterRequest(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
