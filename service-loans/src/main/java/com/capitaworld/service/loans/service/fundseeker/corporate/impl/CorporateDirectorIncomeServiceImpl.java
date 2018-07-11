package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.gst.util.CommonUtils;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateDirectorIncomeDetails;
import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateDirectorIncomeDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateDirectorIncomeService;

@Service
@Transactional
public class CorporateDirectorIncomeServiceImpl implements CorporateDirectorIncomeService {

	
	@Autowired
	private CorporateDirectorIncomeDetailsRepository incomeDetailsRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CorporateDirectorIncomeServiceImpl.class.getName());
	
	@Override
	public Boolean saveOrUpdateIncomeDetails(List<CorporateDirectorIncomeRequest> corporateRequest) throws Exception {

		try {
			logger.info("Entering into saveOrUpdateDirectorIncomeDetails=======================>");
			for(CorporateDirectorIncomeRequest corpoObj : corporateRequest) {
				if(CommonUtils.isObjectNullOrEmpty(corpoObj)) {
					continue;
				}
				if(CommonUtils.isObjectNullOrEmpty(corpoObj.getApplicationId()) || CommonUtils.isObjectNullOrEmpty(corpoObj.getDirectorId())
						|| CommonUtils.isObjectNullOrEmpty(corpoObj.getYear())) {
					continue;
				}
				CorporateDirectorIncomeDetails corpoDirReq = incomeDetailsRepository.findByApplicationIdAndDirectorIdAndYear(corpoObj.getApplicationId(), corpoObj.getDirectorId(), corpoObj.getYear());
				if(CommonUtils.isObjectNullOrEmpty(corpoDirReq)) {
					corpoDirReq = new CorporateDirectorIncomeDetails();
					BeanUtils.copyProperties(corpoObj, corpoDirReq);
					corpoDirReq.setCreatedDate(new Date());
					corpoDirReq.setCreatedBy(corpoObj.getUserId());
					corpoDirReq.setIsActive(true);
					incomeDetailsRepository.save(corpoDirReq);
				} else {
					BeanUtils.copyProperties(corpoObj, corpoDirReq,"id","isActive","createdDate","createdBy","modifiedBy","modifiedDate","applicationId","directorId","year");
					corpoDirReq.setModifiedDate(new Date());
					corpoDirReq.setModifiedBy(corpoObj.getUserId());
					incomeDetailsRepository.save(corpoDirReq);
				}
			}	
			return true;
		} catch (Exception e) {
			logger.info("Exception Occured in saveOrUpdateDirectorIncomeDetails=======================>");
			e.printStackTrace();
		}
		return false;
		
	}

}
