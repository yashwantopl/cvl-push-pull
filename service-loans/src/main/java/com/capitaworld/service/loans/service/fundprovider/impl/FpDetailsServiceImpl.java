package com.capitaworld.service.loans.service.fundprovider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.model.FpDetailsRequest;
import com.capitaworld.service.loans.service.fundprovider.FpDetailsService;

@Service
public class FpDetailsServiceImpl implements FpDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(FpDetailsServiceImpl.class.getName());

	@Override
	public boolean saveOrUpdate(FpDetailsRequest applicantRequest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FpDetailsRequest getFpDetail(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
