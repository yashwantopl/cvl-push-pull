package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.dms.util.CommonUtil;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.service.teaser.primaryview.PersonalLoansViewService;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class PersonalLoansViewServiceImpl implements PersonalLoansViewService {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonalLoansViewServiceImpl.class);
	
	
	@Autowired
	RetailApplicantService retailApplicantService;
	
	@Autowired
	CoApplicantService coApplicantService;

	@Autowired
	GuarantorService guarantorService; 

	@Override
	public Boolean validatePersonalLoansPrimaryViewRequest(String toApplicationId) {
        if(!CommonUtil.isObjectNullOrEmpty(toApplicationId)){
            try {
                Long.parseLong(toApplicationId);
                return false;
            } catch (NumberFormatException e) {
                return true;
            }
        }else{
            logger.warn("Invalid Request {}", toApplicationId);
            return true;
        }
    }

	@Override
	public RetailPrimaryViewResponse getPersonalLoansPrimaryViewDetails(Long applicantId, Long userId) throws Exception {

		try{
		RetailPrimaryViewResponse response = new RetailPrimaryViewResponse();
		RetailProfileViewResponse profileViewPLResponse = retailApplicantService.getProfileViewPLResponse( applicantId,  userId);
		response.setPersonalProfileRespoonse(profileViewPLResponse);
		
		List<RetailProfileViewResponse> coApplicantResponse = coApplicantService.getCoApplicantPLResponse(applicantId,userId);	
		response.setCoApplicantResponse(coApplicantResponse);
		
		List<RetailProfileViewResponse> garantorResponse = guarantorService.getGuarantorServiceResponse(applicantId,userId);
		response.setGarantorResponse(garantorResponse);
		return response;
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
