package com.opl.service.loans.service.sidbi.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opl.mudra.api.common.CommonUtils;
import com.opl.mudra.api.common.MultipleJSONObjectHelper;
import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.corporate.TotalCostOfProjectRequest;
import com.opl.mudra.api.sidbi.enums.MeansOfFinanceParticulars;
import com.opl.mudra.api.sidbi.enums.SidbiCurrencyRate;
import com.opl.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.opl.service.loans.domain.sidbi.MeansOfFinanceDetail;
import com.opl.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.opl.service.loans.repository.sidbi.MeansOfFinanceDetailRepository;
import com.opl.service.loans.service.sidbi.MeansOfFinanceDetailService;
import com.opl.service.loans.service.sidbi.SidbiSpecificService;

@Service
@Transactional
public class MeansOfFinanceDetailServiceImpl implements MeansOfFinanceDetailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MeansOfFinanceDetailServiceImpl.class.getName());
	
	@Autowired
    MeansOfFinanceDetailRepository meansOfFinanceDetailRepository;
	
	@Autowired
    PrimaryCorporateDetailRepository primaryCorporateDetailRepository;
	
	@Autowired
    SidbiSpecificService sidbiSpecificService;
	

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				TotalCostOfProjectRequest financeMeansRequest = (TotalCostOfProjectRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, TotalCostOfProjectRequest.class);
				MeansOfFinanceDetail financeMeansDetail = null;
				if (financeMeansRequest.getId() != null) {
					financeMeansDetail = meansOfFinanceDetailRepository.findOne(financeMeansRequest.getId());
				} else {
					financeMeansDetail = new MeansOfFinanceDetail();
					financeMeansDetail.setCreatedBy(frameRequest.getUserId());
					financeMeansDetail.setCreatedDate(new Date());
				}
				this.convertAbsoluteValues(financeMeansRequest, frameRequest.getApplicationId(), frameRequest.getUserId());
				BeanUtils.copyProperties(financeMeansRequest, financeMeansDetail);
				financeMeansDetail.setApplicationId(frameRequest.getApplicationId());
				financeMeansDetail.setModifiedBy(frameRequest.getUserId());
				financeMeansDetail.setModifiedDate(new Date());
				meansOfFinanceDetailRepository.save(financeMeansDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception in save totalCostOfProject :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}


	@Override
	public List<TotalCostOfProjectRequest> getMeansOfFinanceList(Long applicationId, Long userId) throws LoansException {
		try {
			List<MeansOfFinanceDetail> financeMeansDetails = meansOfFinanceDetailRepository
					.listFinanceMeansFromAppId(applicationId, userId);
			List<TotalCostOfProjectRequest> financeMeansRequests = new ArrayList<>();
			SidbiCurrencyRate sidbiCurrencyRateObj = sidbiSpecificService.getValuesIn(applicationId);
			if(financeMeansDetails == null || financeMeansDetails.isEmpty()) {
				
				PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findByApplicationIdId(applicationId);
				
				for (MeansOfFinanceParticulars systems : MeansOfFinanceParticulars.getAll()) {
					TotalCostOfProjectRequest financeMeansDetailRequest = new TotalCostOfProjectRequest();
					financeMeansDetailRequest.setParticularName(systems.getDescription());
					financeMeansDetailRequest.setParticularsId(systems.getId());
					
					if(systems.getId().equals(1)) {
						Double promoterContribution = primaryCorporateDetail != null ? primaryCorporateDetail.getPromoterContribution() : 0;
//						financeMeansDetailRequest.setToBeIncurred(promoterContribution);
						financeMeansDetailRequest.setToBeIncurred(CommonUtils.convertTwoDecimalValuesIn(promoterContribution, sidbiCurrencyRateObj.getRate()));
					}else if(systems.getId().equals(4)) {
						Integer loanType = primaryCorporateDetail != null ? primaryCorporateDetail.getPurposeOfLoanId() : 0;
						if(loanType == 1) {
							Double loanAmt = sidbiSpecificService.getLoanAmountByApplicationId(applicationId);
//							financeMeansDetailRequest.setToBeIncurred(loanAmt);
							financeMeansDetailRequest.setToBeIncurred(CommonUtils.convertTwoDecimalValuesIn(loanAmt, sidbiCurrencyRateObj.getRate()));
						}
					}
					Double totalCost = (financeMeansDetailRequest.getAlreadyIncurred() != null ? financeMeansDetailRequest.getAlreadyIncurred() : 0)
							+ (financeMeansDetailRequest.getToBeIncurred() != null ? financeMeansDetailRequest.getToBeIncurred() : 0); 
					
//					financeMeansDetailRequest.setTotalCost(totalCost);
					financeMeansDetailRequest.setTotalCost(CommonUtils.convertTwoDecimalValuesIn(totalCost, sidbiCurrencyRateObj.getRate()));
					
//					this.convertValuesIn(financeMeansDetailRequest, applicationId, userId);
					financeMeansRequests.add(financeMeansDetailRequest);
                }
			}else {
				for (MeansOfFinanceDetail detail : financeMeansDetails) {
					TotalCostOfProjectRequest financeMeansDetailRequest = new TotalCostOfProjectRequest();
					BeanUtils.copyProperties(detail, financeMeansDetailRequest);
					
					Double totalCost = (financeMeansDetailRequest.getAlreadyIncurred() != null ? financeMeansDetailRequest.getAlreadyIncurred() : 0)
							+ (financeMeansDetailRequest.getToBeIncurred() != null ? financeMeansDetailRequest.getToBeIncurred() : 0); 
					financeMeansDetailRequest.setTotalCost(totalCost);
//					this.convertValuesIn(financeMeansDetailRequest, applicationId, userId);
					financeMeansRequests.add(financeMeansDetailRequest);
				}
			}
			
			return financeMeansRequests;
		} catch (Exception e) {
			logger.error("Exception getting financeMeansDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	private void convertAbsoluteValues(TotalCostOfProjectRequest totalCostOfProject,Long applicationId, Long userId) throws LoansException {
		SidbiCurrencyRate sidbiCurrencyRateObj = sidbiSpecificService.getValuesIn(applicationId);
		
		totalCostOfProject.setAlreadyIncurred(CommonUtils.convertTwoDecimalAbsoluteValues(totalCostOfProject.getAlreadyIncurred(), sidbiCurrencyRateObj.getRate()));
		totalCostOfProject.setToBeIncurred(CommonUtils.convertTwoDecimalAbsoluteValues(totalCostOfProject.getToBeIncurred(), sidbiCurrencyRateObj.getRate()));
		totalCostOfProject.setTotalCost(CommonUtils.convertTwoDecimalAbsoluteValues(totalCostOfProject.getTotalCost(), sidbiCurrencyRateObj.getRate()));
	}
	
	private void convertValuesIn(TotalCostOfProjectRequest totalCostOfProject,Long applicationId, Long userId) throws LoansException {
		SidbiCurrencyRate sidbiCurrencyRateObj = sidbiSpecificService.getValuesIn(applicationId);
		
		totalCostOfProject.setAlreadyIncurred(CommonUtils.convertTwoDecimalValuesIn(totalCostOfProject.getAlreadyIncurred(), sidbiCurrencyRateObj.getRate()));
		totalCostOfProject.setToBeIncurred(CommonUtils.convertTwoDecimalValuesIn(totalCostOfProject.getToBeIncurred(), sidbiCurrencyRateObj.getRate()));
		totalCostOfProject.setTotalCost(CommonUtils.convertTwoDecimalValuesIn(totalCostOfProject.getTotalCost(), sidbiCurrencyRateObj.getRate()));
		
	}

}
