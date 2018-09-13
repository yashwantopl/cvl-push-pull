package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantIncomeDetail;
import com.capitaworld.service.loans.model.retail.RetailApplicantIncomeRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantIncomeRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantIncomeService;
import com.capitaworld.service.loans.utils.CommonUtils;


@Service
@Transactional
public class RetailApplicantIncomeServiceImpl implements RetailApplicantIncomeService{

	private static final Logger logger = LoggerFactory.getLogger(RetailApplicantIncomeServiceImpl.class);
	
	@Autowired
	private RetailApplicantIncomeRepository appIncomeRepository;
	
	@Override
	public boolean save(RetailApplicantIncomeRequest appIncomeReq) throws Exception  {
		if(CommonUtils.isObjectNullOrEmpty(appIncomeReq.getApplicationId()) || CommonUtils.isObjectNullOrEmpty(appIncomeReq.getYear())) {
			logger.info("ApplicationId or Year Null Or Empty !! ");
			throw new Exception("ApplicationId or Year Null Or Empty");
		}
		try {
			RetailApplicantIncomeDetail appIncomeDetail = null;
			if(!CommonUtils.isObjectNullOrEmpty(appIncomeReq.getId())) {
				appIncomeDetail = appIncomeRepository.findByIdAndIsActive(appIncomeReq.getId(), true);
			}
			if(CommonUtils.isObjectNullOrEmpty(appIncomeDetail)) {
				appIncomeDetail = appIncomeRepository.findByApplicationIdAndYearAndIsActive(appIncomeReq.getApplicationId(),
						appIncomeReq.getYear(), true);
			}
			
			if(CommonUtils.isObjectNullOrEmpty(appIncomeDetail)) {
				appIncomeDetail = new RetailApplicantIncomeDetail();
				appIncomeDetail.setCreatedBy(appIncomeReq.getUserId());
				appIncomeDetail.setCreatedDate(new Date());
				appIncomeDetail.setIsActive(true);
				appIncomeDetail.setApplicationId(appIncomeReq.getApplicationId());
				appIncomeDetail.setYear(appIncomeReq.getYear());
			} else {
				appIncomeDetail.setModifiedBy(appIncomeReq.getUserId());
				appIncomeDetail.setModifiedDate(new Date());
				if(!CommonUtils.isObjectNullOrEmpty(appIncomeReq.getIsActive())) {
					appIncomeDetail.setIsActive(appIncomeReq.getIsActive());	
				}
			}
			appIncomeDetail.setSalaryIncome(appIncomeReq.getSalaryIncome());
			appIncomeDetail.setCapitalGain(appIncomeReq.getCapitalGain());
			appIncomeDetail.setIncomeRatio(appIncomeReq.getIncomeRatio());
			appIncomeDetail.setPgbp(appIncomeReq.getPgbp());
			appIncomeDetail.setHouseProperty(appIncomeReq.getHouseProperty());
			appIncomeRepository.save(appIncomeDetail);
			return true;
		} catch (Exception e) {
			logger.error("THROW EXCEPTION WHILE SAVE RETAIL APPLICANT INCOME DETAILS");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	
	@Override
	public boolean saveAll(List<RetailApplicantIncomeRequest> appIncomeReqList) throws Exception{
		for(RetailApplicantIncomeRequest appIncomeReq : appIncomeReqList) {
			if(!CommonUtils.isObjectNullOrEmpty(appIncomeReq)) {
				save(appIncomeReq);
			}
		}
		return true;
	}
	
	@Override
	public List<RetailApplicantIncomeRequest> getAll(Long applicationId) {
		List<RetailApplicantIncomeDetail> appIncomeDetailList = appIncomeRepository.findByApplicationIdAndIsActive(applicationId,true);
		List<RetailApplicantIncomeRequest> appIncomeReqList = new ArrayList<>(appIncomeDetailList.size());
		RetailApplicantIncomeRequest appIncomeReq = null;
		for(RetailApplicantIncomeDetail appIncomeDetail : appIncomeDetailList) {
			appIncomeReq = new RetailApplicantIncomeRequest();
			BeanUtils.copyProperties(appIncomeDetail, appIncomeReq);
			appIncomeReqList.add(appIncomeReq);
		}
		return appIncomeReqList;
	}
}
