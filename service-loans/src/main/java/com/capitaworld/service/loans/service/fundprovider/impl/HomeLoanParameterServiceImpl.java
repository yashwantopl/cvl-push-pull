package com.capitaworld.service.loans.service.fundprovider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.HomeLoanParameter;
import com.capitaworld.service.loans.model.HomeLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.HomeLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.service.fundprovider.HomeLoanParameterService;

@Service
public class HomeLoanParameterServiceImpl implements HomeLoanParameterService {
	private static final Logger logger = LoggerFactory.getLogger(HomeLoanParameterServiceImpl.class.getName());
	@Autowired
	private HomeLoanParameterRepository homeLoanParameterRepository;
	
	@Autowired
	private ProductMasterRepository productMasterRepository;
	@Override
	public boolean saveOrUpdate(HomeLoanParameterRequest homeLoanParameterRequest) {
		// TODO Auto-generated method stub
		try { 
			HomeLoanParameter homeLoanParameter= new HomeLoanParameter();
			BeanUtils.copyProperties(homeLoanParameterRequest, homeLoanParameter);
			if(null!=homeLoanParameterRequest.getFpProductId())
				homeLoanParameter.setFpProductId(productMasterRepository.findOne(homeLoanParameterRequest.getFpProductId()));
			homeLoanParameter = homeLoanParameterRepository.save(homeLoanParameter);
			return true;
			}

		 catch (Exception e) {
			logger.info("Exception  in save homeLoanParameter  :-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public HomeLoanParameterRequest getHomeLoanParameterRequest(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
