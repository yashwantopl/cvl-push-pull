package com.capitaworld.service.loans.service.teaser.finalview.impl;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.FinalHomeLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.teaser.finalview.HomeLoanFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.FinalHomeLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.teaser.finalview.HomeLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.RetailFinalCommonApplicantService;
import com.capitaworld.service.loans.service.teaser.primaryview.HomeLoanPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.CityByCityListIdClient;
import com.capitaworld.service.oneform.client.CountryByCountryListIdClient;
import com.capitaworld.service.oneform.client.StateListByStateListIdClient;
import com.capitaworld.service.oneform.enums.PropertyUsedSubType;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Transactional
public class HomeLoanFinalViewServiceImpl implements HomeLoanFinalViewService{

	private static final Logger logger = LoggerFactory.getLogger(HomeLoanFinalViewServiceImpl.class);

	@Autowired
	private RetailApplicantDetailRepository applicantRepository;
    
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private HomeLoanPrimaryViewService homeLoanPrimaryViewService;
	
	@Autowired
	private CoApplicantService coApplicantService;
	
	@Autowired
	private GuarantorService guarantorService;
	
	@Autowired
	private FinalHomeLoanDetailRepository finalHomeLoanRepository;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private RetailFinalCommonApplicantService finalCommonService;
	
	@Override
	public HomeLoanFinalViewResponse getHomeLoanFinalViewDetails(Long applicantId) throws Exception {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicantId);
		HomeLoanFinalViewResponse homeLoanFinalViewResponse = new HomeLoanFinalViewResponse();
		RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(applicationMaster.getUserId(), applicantId);
		if (!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
			RetailFinalViewResponse finalViewResponse = new RetailFinalViewResponse();
			//applicant final common details
			finalViewResponse.setApplicantCommonDetails(finalCommonService.getApplicantCommonInfo(applicantId, applicantDetail));
			
			//co-applicant final common details
			try {
				finalViewResponse.setCoApplicantCommonDetails(coApplicantService.getCoApplicantFinalResponse(applicantId, applicationMaster.getUserId()));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting CoApplicant final details");
			}
			
			//guarantor final common details
			try {
				finalViewResponse.setGuarantorCommonDetails(guarantorService.getGuarantorFinalViewResponse(applicantId, applicationMaster.getUserId()));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting Guarantor final details");
			}
			
			//Home Loan primary details
			try { 
				homeLoanFinalViewResponse.setHomeLoanPrimaryViewResponse(homeLoanPrimaryViewService.getHomeLoanPrimaryViewDetails(applicantId));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting HL primary details");
			}
			
			//Home Loan final details
			FinalHomeLoanDetail finalHomeLoanDetails = finalHomeLoanRepository.getByApplicationAndUserId(applicantId, applicationMaster.getUserId());
			try {
				homeLoanFinalViewResponse.setPropPremiseNo(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressPremise()) ? finalHomeLoanDetails.getPropertyAddressPremise() : "NA" );
				homeLoanFinalViewResponse.setPropLandmark(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressLandmark()) ? finalHomeLoanDetails.getPropertyAddressLandmark() : "NA");
				homeLoanFinalViewResponse.setPropStreetName(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressStreet()) ? finalHomeLoanDetails.getPropertyAddressStreet() : "NA");
				homeLoanFinalViewResponse.setPropPinCode(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressPincode()) ? finalHomeLoanDetails.getPropertyAddressPincode().toString() : "NA");
				
                CityByCityListIdClient cityByCityListIdClient = new CityByCityListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
                try {
                    List<Long> permanentCity = new ArrayList<Long>(1);
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressCity())) {
                    	permanentCity.add(Long.valueOf(finalHomeLoanDetails.getPropertyAddressCity()));
                        OneFormResponse formResponsePermanentCity = cityByCityListIdClient.send(permanentCity);
                        MasterResponse dataCity = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) formResponsePermanentCity.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCity)){
                        	homeLoanFinalViewResponse.setPropCity(dataCity.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setPropCity("NA");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setPropCity("NA");
                    }
                } catch (Exception e) {

                }
                CountryByCountryListIdClient countryByCountryListIdClient = new CountryByCountryListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
                try {
                    List<Long> permanentCountry = new ArrayList<Long>(1);
                    Long permanentCountryLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressCountry())) {
                        permanentCountryLong = Long.valueOf(finalHomeLoanDetails.getPropertyAddressCountry());
                        permanentCountry.add(permanentCountryLong);
                        OneFormResponse countryPermanent = countryByCountryListIdClient.send(permanentCountry);
                        MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) countryPermanent.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCountry)){
                        	homeLoanFinalViewResponse.setPropCountry(dataCountry.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setPropCountry("NA");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setPropCountry("NA");
                    }
                } catch (Exception e) {

                }
                StateListByStateListIdClient stateListByStateListIdClient = new StateListByStateListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
                try {
                    List<Long> permanentState = new ArrayList<Long>(1);
                    Long permanentStateLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressState())) {
                        permanentStateLong = Long.valueOf(finalHomeLoanDetails.getPropertyAddressState());
                        permanentState.add(permanentStateLong);
                        OneFormResponse statePermanent = stateListByStateListIdClient.send(permanentState);
                        MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) statePermanent.getListData().get(0), MasterResponse.class);
                        if (!CommonUtils.isObjectNullOrEmpty(dataState)){
                        	homeLoanFinalViewResponse.setPropState(dataState.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setPropState("NA");	
                        }
                    }else{
                    	homeLoanFinalViewResponse.setPropState("NA");
                    }
                } catch (Exception e) {

                }
				
				homeLoanFinalViewResponse.setBuiltUpArea(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getBuiltUpArea()) ? finalHomeLoanDetails.getBuiltUpArea().toString() : "NA");
				homeLoanFinalViewResponse.setCarpetArea(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getCarpetArea()) ? finalHomeLoanDetails.getCarpetArea().toString() : "NA");
				homeLoanFinalViewResponse.setSuperBuiltuparea(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSuperBuiltUpArea()) ? finalHomeLoanDetails.getSuperBuiltUpArea().toString() : "NA");
				homeLoanFinalViewResponse.setSellerName(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellerName()) ? finalHomeLoanDetails.getSellerName() :  "NA");
				homeLoanFinalViewResponse.setSellerPremiseNo(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellersAddressPremise()) ? finalHomeLoanDetails.getSellersAddressPremise() : "NA");
				homeLoanFinalViewResponse.setSellerStreetName(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellersAddressStreet()) ? finalHomeLoanDetails.getSellersAddressStreet() : "NA");
				homeLoanFinalViewResponse.setSellerLandmark(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellersAddressLandmark()) ? finalHomeLoanDetails.getSellersAddressLandmark() : "NA");
				
				try {
                    List<Long> permanentCity = new ArrayList<Long>(1);
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellersAddressCity())) {
                    	permanentCity.add(Long.valueOf(finalHomeLoanDetails.getSellersAddressCity()));
                        OneFormResponse formResponsePermanentCity = cityByCityListIdClient.send(permanentCity);
                        MasterResponse dataCity = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) formResponsePermanentCity.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCity)){
                        	homeLoanFinalViewResponse.setSellerCity(dataCity.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setSellerCity("NA");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setSellerCity("NA");
                    }
                } catch (Exception e) {

                }
				try {
                    List<Long> permanentState = new ArrayList<Long>(1);
                    Long permanentStateLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellersAddressState())) {
                        permanentStateLong = Long.valueOf(finalHomeLoanDetails.getSellersAddressState());
                        permanentState.add(permanentStateLong);
                        OneFormResponse statePermanent = stateListByStateListIdClient.send(permanentState);
                        MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) statePermanent.getListData().get(0), MasterResponse.class);
                        if (!CommonUtils.isObjectNullOrEmpty(dataState)){
                        	homeLoanFinalViewResponse.setSellerState(dataState.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setSellerState("NA");	
                        }
                    }else{
                    	homeLoanFinalViewResponse.setSellerState("NA");
                    }
                } catch (Exception e) {

                }
				
				try {
                    List<Long> permanentCountry = new ArrayList<Long>(1);
                    Long permanentCountryLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellersAddressCountry())) {
                        permanentCountryLong = Long.valueOf(finalHomeLoanDetails.getSellersAddressCountry());
                        permanentCountry.add(permanentCountryLong);
                        OneFormResponse countryPermanent = countryByCountryListIdClient.send(permanentCountry);
                        MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) countryPermanent.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCountry)){
                        	homeLoanFinalViewResponse.setSellerCountry(dataCountry.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setSellerCountry("NA");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setSellerCountry("NA");
                    }
                } catch (Exception e) {

                }
				homeLoanFinalViewResponse.setPropertyUse(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyUsed()) ? PropertyUsedSubType.getById(finalHomeLoanDetails.getPropertyUsed()).getValue() : "NA");
				homeLoanFinalViewResponse.setRentalIncome(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getEstimatedRentalIncome()) ? finalHomeLoanDetails.getEstimatedRentalIncome().toString() : "NA");
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting HL final details");
			}
		}
		return homeLoanFinalViewResponse;
	}
	
}
