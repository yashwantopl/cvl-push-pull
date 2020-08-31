package com.opl.service.loans.service.sidbi.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.common.CommonUtils;
import com.opl.mudra.api.common.MultipleJSONObjectHelper;
import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.sidbi.FacilityDetailsRequest;
import com.opl.mudra.api.sidbi.enums.SidbiCurrencyRate;
import com.opl.service.loans.domain.sidbi.FacilityDetails;
import com.opl.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.opl.service.loans.repository.sidbi.FacilityDetailsRepository;
import com.opl.service.loans.service.sidbi.FacilityDetailsService;
import com.opl.service.loans.service.sidbi.SidbiSpecificService;

/**
 * Created by pooja.patel on 19-06-2019.
 */

@Service
@Transactional
public class FacilityDetailsServiceImpl implements FacilityDetailsService{

    private static final Logger logger = LoggerFactory.getLogger(FacilityDetailsServiceImpl.class.getName());

    @Autowired
    FacilityDetailsRepository facilityDetailsRepository;

    @Autowired
    PrimaryCorporateDetailRepository primaryCorporateDetailRepository;

    @Autowired
    SidbiSpecificService sidbiSpecificService;

    @Override
    public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
        try {
            for (Map<String, Object> obj : frameRequest.getDataList()) {
                FacilityDetailsRequest facilityDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(obj, FacilityDetailsRequest.class);
                FacilityDetails facilityDetails= null;
                if (facilityDetailsRequest.getId() != null) {
                    facilityDetails = facilityDetailsRepository.findOne(facilityDetailsRequest.getId());
                } else {
                    facilityDetails = new FacilityDetails();
                    facilityDetails.setCreatedBy(frameRequest.getUserId());
                    facilityDetails.setCreatedDate(new Date());
                }
                this.convertAbsoluteValues(facilityDetailsRequest, frameRequest.getApplicationId());
                BeanUtils.copyProperties(facilityDetailsRequest, facilityDetails);
                facilityDetails.setApplicationId(frameRequest.getApplicationId());
                facilityDetails.setModifiedBy(frameRequest.getUserId());
                facilityDetails.setModifiedDate(new Date());
                facilityDetailsRepository.save(facilityDetails);
            }
            return true;
        }

        catch (Exception e) {
            logger.error("Exception in save facilityDetails :-",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }

    }

	@Override
	public List<FacilityDetailsRequest> getFacilityDetailsListAppId(Long applicationId,Long userId) throws LoansException {
		List<FacilityDetailsRequest> facilityDetailsRequestList = null;
		try {
			List<FacilityDetails> facilityDetailsList = facilityDetailsRepository.getFacilityDetailsListAppId(applicationId);
			facilityDetailsRequestList = new ArrayList<>(facilityDetailsList.size());
			SidbiCurrencyRate sidbiCurrencyRateObj = sidbiSpecificService.getValuesIn(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(facilityDetailsList) && facilityDetailsList.size() == 0){
			    Integer purposeLoanId = primaryCorporateDetailRepository.getPurposeLoanId(applicationId);
                FacilityDetailsRequest facilityDetailsRequest = new FacilityDetailsRequest();

                Double loanAmt = sidbiSpecificService.getLoanAmountByApplicationId(applicationId);

			    if (purposeLoanId == 1){
                    facilityDetailsRequest.setRupeeTermLoan(CommonUtils.convertTwoDecimalValuesIn(loanAmt, sidbiCurrencyRateObj.getRate()));
                }
                else {
                    facilityDetailsRequest.setWorkingCapitalFund(CommonUtils.convertTwoDecimalValuesIn(loanAmt, sidbiCurrencyRateObj.getRate()));
                }
//			    this.convertValuesIn(facilityDetailsRequest, applicationId);
                facilityDetailsRequestList.add(facilityDetailsRequest);
            }
            else {
                for (FacilityDetails detail : facilityDetailsList) {
                    FacilityDetailsRequest facilityDetailsRequest = new FacilityDetailsRequest();
                    BeanUtils.copyProperties(detail, facilityDetailsRequest);
//                    this.convertValuesIn(facilityDetailsRequest, applicationId);
                    facilityDetailsRequestList.add(facilityDetailsRequest);
                }
            }
			return facilityDetailsRequestList;
		}

		catch (Exception e) { 
			logger.error("Exception in get facilityDetailsRequestList :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	private void convertAbsoluteValues(FacilityDetailsRequest facilityDetailsRequest,Long applicationId) throws LoansException {
		SidbiCurrencyRate sidbiCurrencyRateObj = sidbiSpecificService.getValuesIn(applicationId);
		
		facilityDetailsRequest.setRupeeTermLoan(CommonUtils.convertTwoDecimalAbsoluteValues(facilityDetailsRequest.getRupeeTermLoan(), sidbiCurrencyRateObj.getRate()));
		facilityDetailsRequest.setInrCurrency(CommonUtils.convertTwoDecimalAbsoluteValues(facilityDetailsRequest.getInrCurrency(), sidbiCurrencyRateObj.getRate()));
		facilityDetailsRequest.setWorkingCapitalFund(CommonUtils.convertTwoDecimalAbsoluteValues(facilityDetailsRequest.getWorkingCapitalFund(), sidbiCurrencyRateObj.getRate()));
		facilityDetailsRequest.setWorkingCapitalNonFund(CommonUtils.convertTwoDecimalAbsoluteValues(facilityDetailsRequest.getWorkingCapitalNonFund(), sidbiCurrencyRateObj.getRate()));
		facilityDetailsRequest.setTotal(CommonUtils.convertTwoDecimalAbsoluteValues(facilityDetailsRequest.getTotal(), sidbiCurrencyRateObj.getRate()));
		
	}
	
	private void convertValuesIn(FacilityDetailsRequest facilityDetailsRequest,Long applicationId) throws LoansException {
		SidbiCurrencyRate sidbiCurrencyRateObj = sidbiSpecificService.getValuesIn(applicationId);
		
		facilityDetailsRequest.setRupeeTermLoan(CommonUtils.convertTwoDecimalValuesIn(facilityDetailsRequest.getRupeeTermLoan(), sidbiCurrencyRateObj.getRate()));
		facilityDetailsRequest.setInrCurrency(CommonUtils.convertTwoDecimalValuesIn(facilityDetailsRequest.getInrCurrency(), sidbiCurrencyRateObj.getRate()));
		facilityDetailsRequest.setWorkingCapitalFund(CommonUtils.convertTwoDecimalValuesIn(facilityDetailsRequest.getWorkingCapitalFund(), sidbiCurrencyRateObj.getRate()));
		facilityDetailsRequest.setWorkingCapitalNonFund(CommonUtils.convertTwoDecimalValuesIn(facilityDetailsRequest.getWorkingCapitalNonFund(), sidbiCurrencyRateObj.getRate()));
		facilityDetailsRequest.setTotal(CommonUtils.convertTwoDecimalValuesIn(facilityDetailsRequest.getTotal(), sidbiCurrencyRateObj.getRate()));
		
		
	}
}
