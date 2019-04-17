
package com.capitaworld.service.loans.service.fundprovider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.service.fundprovider.HomeLoanModelService;

@Service
@Transactional
public class HomeLoanModelServiceImpl implements HomeLoanModelService {
	private static final Logger logger = LoggerFactory.getLogger(HomeLoanModelServiceImpl.class);
}
