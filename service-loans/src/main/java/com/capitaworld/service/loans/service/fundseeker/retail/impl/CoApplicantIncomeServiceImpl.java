package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantIncomeDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantIncomeRequest;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantIncomeRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantIncomeService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;


@Service
@Transactional
public class CoApplicantIncomeServiceImpl implements CoApplicantIncomeService{

	private static final Logger logger = LoggerFactory.getLogger(CoApplicantIncomeServiceImpl.class);
	
	@Autowired
	private CoApplicantIncomeRepository appIncomeRepository;
	

	@Override
	public boolean save(RetailApplicantIncomeRequest appIncomeReq) throws LoansException {
		if(CommonUtils.isObjectNullOrEmpty(appIncomeReq.getApplicationId()) || CommonUtils.isObjectNullOrEmpty(appIncomeReq.getYear())) {
			logger.info("ApplicationId or Year Null Or Empty !! ");
			throw new LoansException("ApplicationId or Year Null Or Empty");
		}
		try {
			CoApplicantIncomeDetail appIncomeDetail = null;
			if(!CommonUtils.isObjectNullOrEmpty(appIncomeReq.getId())) {
				appIncomeDetail = appIncomeRepository.findByIdAndIsActive(appIncomeReq.getId(), true);
			}
			if(CommonUtils.isObjectNullOrEmpty(appIncomeDetail)) {
				appIncomeDetail = appIncomeRepository.findByApplicationIdAndYearAndIsActive(appIncomeReq.getApplicationId(),
						appIncomeReq.getYear(), true);
			}
			
			if(appIncomeDetail == null || CommonUtils.isObjectNullOrEmpty(appIncomeDetail)) {
				appIncomeDetail = new CoApplicantIncomeDetail();
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
			logger.error("THROW EXCEPTION WHILE SAVE RETAIL CO APPLICANT INCOME DETAILS : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	
	@Override
	public boolean saveAll(List<RetailApplicantIncomeRequest> appIncomeReqList) throws LoansException{
		for(RetailApplicantIncomeRequest appIncomeReq : appIncomeReqList) {
			if(!CommonUtils.isObjectNullOrEmpty(appIncomeReq)) {
				save(appIncomeReq);
			}
		}
		return true;
	}
	
	@Override
	public List<RetailApplicantIncomeRequest> getAll(Long applicationId) {
		List<CoApplicantIncomeDetail> appIncomeDetailList = appIncomeRepository.findByApplicationIdAndIsActive(applicationId,true);
		List<RetailApplicantIncomeRequest> appIncomeReqList = new ArrayList<>(appIncomeDetailList.size());
		for(CoApplicantIncomeDetail appIncomeDetail : appIncomeDetailList) {
			appIncomeReqList.add(prepareObj(appIncomeDetail));
		}
		return appIncomeReqList;
	}



	@Override
	public Boolean saveOrUpdateIncomeDetailForGrossIncome(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				RetailApplicantIncomeRequest retailApplicantIncomeRequest = (RetailApplicantIncomeRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, RetailApplicantIncomeRequest.class);
				CoApplicantIncomeDetail incomeDetail = new CoApplicantIncomeDetail();
				BeanUtils.copyProperties(retailApplicantIncomeRequest, incomeDetail);
				if (retailApplicantIncomeRequest.getId() == null) {
					incomeDetail.setCreatedBy(frameRequest.getUserId());
					incomeDetail.setCreatedDate(new Date());
				}
				incomeDetail.setApplicationId(frameRequest.getApplicationId());

				incomeDetail.setModifiedBy(frameRequest.getUserId());
				incomeDetail.setModifiedDate(new Date());
				appIncomeRepository.save(incomeDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save Gross Income Detail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	


	@Override
	public List<RetailApplicantIncomeRequest> get(Long coApplicantId) {
		List<CoApplicantIncomeDetail> coAppObj = appIncomeRepository.findByIsActiveAndProposalIdIsNullAndCoAppId(true,coApplicantId);
		List<RetailApplicantIncomeRequest> appIncomeReqList = new ArrayList<>(coAppObj.size());
		for(CoApplicantIncomeDetail appIncomeDetail : coAppObj) {
			appIncomeReqList.add(prepareObj(appIncomeDetail));
		}
		return appIncomeReqList;
	}
	
	private RetailApplicantIncomeRequest prepareObj(CoApplicantIncomeDetail appIncomeDetail){
		RetailApplicantIncomeRequest appIncomeReq = new RetailApplicantIncomeRequest();
		//FOR PL CAM
		appIncomeReq.setSalaryIncomeString(CommonUtils.convertValue(appIncomeDetail.getSalaryIncome()));
		appIncomeReq.setIncomeRatioString(CommonUtils.convertValue(appIncomeDetail.getIncomeRatio()));
		appIncomeReq.setHousePropertyString(CommonUtils.convertValue(appIncomeDetail.getHouseProperty()));
		appIncomeReq.setCapitalGainString(CommonUtils.convertValue(appIncomeDetail.getCapitalGain()));
		appIncomeReq.setPgbpString(CommonUtils.convertValue(appIncomeDetail.getPgbp()));
		appIncomeReq.setOtherSourceString(CommonUtils.convertValue(appIncomeDetail.getOtherSource()));
		
		appIncomeReq.setSalaryIncomeGrossString(CommonUtils.convertValue(appIncomeDetail.getSalaryIncomeGross()));
		appIncomeReq.setCapitalGainGrossString(CommonUtils.convertValue(appIncomeDetail.getCapitalGainGross()));
		appIncomeReq.setHousePropertyGrossString(CommonUtils.convertValue(appIncomeDetail.getHousePropertyGross()));
		appIncomeReq.setOtherSourceGrossString(CommonUtils.convertValue(appIncomeDetail.getOtherSourceGross()));
		appIncomeReq.setPgbpGrossString(CommonUtils.convertValue(appIncomeDetail.getPgbpGross()));
		BeanUtils.copyProperties(appIncomeDetail, appIncomeReq);
		return appIncomeReq;
	}
	


	@Override
	public List<RetailApplicantIncomeRequest> getAllByProposalId(Long applicationId, Long proposalId) {
		
		List<CoApplicantIncomeDetail> appIncomeDetailList = appIncomeRepository.findByPropsoalIdAndIsActive(applicationId,proposalId);
		List<RetailApplicantIncomeRequest> appIncomeReqList = new ArrayList<>(appIncomeDetailList.size());
		RetailApplicantIncomeRequest appIncomeReq = null;
		for(CoApplicantIncomeDetail appIncomeDetail : appIncomeDetailList) {
			appIncomeReq = new RetailApplicantIncomeRequest();
			//FOR PL CAM
			appIncomeReq.setSalaryIncomeString(CommonUtils.convertValue(appIncomeDetail.getSalaryIncome()));
			appIncomeReq.setIncomeRatioString(CommonUtils.convertValue(appIncomeDetail.getIncomeRatio()));
			appIncomeReq.setHousePropertyString(CommonUtils.convertValue(appIncomeDetail.getHouseProperty()));
			appIncomeReq.setCapitalGainString(CommonUtils.convertValue(appIncomeDetail.getCapitalGain()));
			appIncomeReq.setPgbpString(CommonUtils.convertValue(appIncomeDetail.getPgbp()));
			appIncomeReq.setOtherSourceString(CommonUtils.convertValue(appIncomeDetail.getOtherSource()));
			
			appIncomeReq.setSalaryIncomeGrossString(CommonUtils.convertValue(appIncomeDetail.getSalaryIncomeGross()));
			appIncomeReq.setCapitalGainGrossString(CommonUtils.convertValue(appIncomeDetail.getCapitalGainGross()));
			appIncomeReq.setHousePropertyGrossString(CommonUtils.convertValue(appIncomeDetail.getHousePropertyGross()));
			appIncomeReq.setOtherSourceGrossString(CommonUtils.convertValue(appIncomeDetail.getOtherSourceGross()));
			appIncomeReq.setPgbpGrossString(CommonUtils.convertValue(appIncomeDetail.getPgbpGross()));
			BeanUtils.copyProperties(appIncomeDetail, appIncomeReq);
			appIncomeReqList.add(appIncomeReq);
		}
		return appIncomeReqList;
	}
}
