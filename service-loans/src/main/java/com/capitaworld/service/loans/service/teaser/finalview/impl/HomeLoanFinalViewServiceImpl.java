package com.capitaworld.service.loans.service.teaser.finalview.impl;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.FinalHomeLoanDetailTmp;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.teaser.finalview.HomeLoanFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewCommonResponse;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.FinalHomeLoanDetailOldRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.common.CommonService;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.teaser.finalview.HomeLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.RetailFinalCommonApplicantService;
import com.capitaworld.service.loans.service.teaser.primaryview.HomeLoanPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
	private FinalHomeLoanDetailOldRepository finalHomeLoanRepository;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private OneFormClient oneFormClient;
	
	@Autowired
	private RetailFinalCommonApplicantService finalCommonService;
	
	@Autowired
	private CommonService commonService;
	
	@Override
	public HomeLoanFinalViewResponse getHomeLoanFinalViewDetails(Long applicantId) throws LoansException {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicantId);
		HomeLoanFinalViewResponse homeLoanFinalViewResponse = new HomeLoanFinalViewResponse();
		RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(applicationMaster.getUserId(), applicantId);
		if (!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
			RetailFinalViewResponse finalViewResponse = new RetailFinalViewResponse();
			//applicant final common details
			finalViewResponse.setApplicantCommonDetails(finalCommonService.getApplicantCommonInfo(applicantId, applicantDetail,applicationMaster.getProductId()));
			
			//co-applicant final common details
			try {
				List<RetailFinalViewCommonResponse> retailFinalViewCommonResponses = coApplicantService.getCoApplicantFinalResponse(applicantId, applicationMaster.getUserId(),applicationMaster.getProductId());
				if (retailFinalViewCommonResponses != null && !retailFinalViewCommonResponses.isEmpty()) {
					finalViewResponse.setCoApplicantCommonDetails(retailFinalViewCommonResponses);
				}
			} catch (Exception e) {
				logger.error("error while getting CoApplicant final details : ",e);
			}
			
			//guarantor final common details
			try {
				finalViewResponse.setGuarantorCommonDetails(guarantorService.getGuarantorFinalViewResponse(applicantId, applicationMaster.getUserId(),applicationMaster.getProductId()));
			} catch (Exception e) {
				logger.error("error while getting Guarantor final details : ",e);
			}
			
			homeLoanFinalViewResponse.setFinalViewResponse(finalViewResponse);
			
			
			//Home Loan primary details
			try { 
				homeLoanFinalViewResponse.setHomeLoanPrimaryViewResponse(homeLoanPrimaryViewService.getHomeLoanPrimaryViewDetails(applicantId));
			} catch (Exception e) {
				logger.error("error while getting HL primary details : ",e);
			}
			
			//Home Loan final details
			FinalHomeLoanDetailTmp finalHomeLoanDetailsOld = finalHomeLoanRepository.getByApplicationAndUserId(applicantId, applicationMaster.getUserId());
			try {
				homeLoanFinalViewResponse.setPropPremiseNo(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getPropertyAddressPremise()) ? finalHomeLoanDetailsOld.getPropertyAddressPremise() : "-");
				homeLoanFinalViewResponse.setPropLandmark(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getPropertyAddressLandmark()) ? finalHomeLoanDetailsOld.getPropertyAddressLandmark() : "-");
				homeLoanFinalViewResponse.setPropStreetName(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getPropertyAddressStreet()) ? finalHomeLoanDetailsOld.getPropertyAddressStreet() : "-");
				homeLoanFinalViewResponse.setPropPinCode(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getPropertyAddressPincode()) ? finalHomeLoanDetailsOld.getPropertyAddressPincode().toString() : "-");
				
				//Set Property CityStateCountryName
				Long cityId = null ;
				Integer stateId = null;
				Integer countryId = null;
				String cityName = null;
				String stateName = null;
				String countryName = null;
				if(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getPropertyAddressCity()))
					cityId = finalHomeLoanDetailsOld.getPropertyAddressCity().longValue();
				if(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getPropertyAddressState()))
					stateId = finalHomeLoanDetailsOld.getPropertyAddressState();
				if(CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getPropertyAddressCountry()))
					countryId = finalHomeLoanDetailsOld.getPropertyAddressCountry();
					
				if(cityId != null || stateId != null || countryId != null) {
					Map<String ,Object> mapData = commonService.getCityStateCountryNameFromOneForm(cityId, stateId, countryId);
					if(mapData != null) {
						cityName = mapData.get(CommonUtils.CITY_NAME).toString();
						stateName = mapData.get(CommonUtils.STATE_NAME).toString();
						countryName = mapData.get(CommonUtils.COUNTRY_NAME).toString();
						
						//set City
						homeLoanFinalViewResponse.setPropCity(cityName != null ? cityName : "-");
						
						//set State
						homeLoanFinalViewResponse.setPropState(stateName != null ? stateName : "-");
						
						//set Country
						homeLoanFinalViewResponse.setPropCountry(countryName != null ? countryName : "-");
					}
				}
				
               /** try {
                    List<Long> permanentCity = new ArrayList<Long>(1);
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getPropertyAddressCity())) {
                    	permanentCity.add(Long.valueOf(finalHomeLoanDetailsOld.getPropertyAddressCity()));
                        OneFormResponse formResponsePermanentCity = oneFormClient.getCityByCityListId(permanentCity);
                        MasterResponse dataCity = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) formResponsePermanentCity.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCity)){
                        	homeLoanFinalViewResponse.setPropCity(dataCity.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setPropCity("-");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setPropCity("-");
                    }
                } catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
                }
                try {
                    List<Long> permanentCountry = new ArrayList<Long>(1);
                    Long permanentCountryLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getPropertyAddressCountry())) {
                        permanentCountryLong = Long.valueOf(finalHomeLoanDetailsOld.getPropertyAddressCountry());
                        permanentCountry.add(permanentCountryLong);
                        OneFormResponse countryPermanent = oneFormClient.getCountryByCountryListId(permanentCountry);
                        MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) countryPermanent.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCountry)){
                        	homeLoanFinalViewResponse.setPropCountry(dataCountry.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setPropCountry("-");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setPropCountry("-");
                    }
                } catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
                }
                try {
                    List<Long> permanentState = new ArrayList<Long>(1);
                    Long permanentStateLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getPropertyAddressState())) {
                        permanentStateLong = Long.valueOf(finalHomeLoanDetailsOld.getPropertyAddressState());
                        permanentState.add(permanentStateLong);
                        OneFormResponse statePermanent = oneFormClient.getStateByStateListId(permanentState);
                        MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) statePermanent.getListData().get(0), MasterResponse.class);
                        if (!CommonUtils.isObjectNullOrEmpty(dataState)){
                        	homeLoanFinalViewResponse.setPropState(dataState.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setPropState("-");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setPropState("-");
                    }
                } catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
                }*/
				
				homeLoanFinalViewResponse.setBuiltUpArea(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getBuiltUpArea()) ? finalHomeLoanDetailsOld.getBuiltUpArea().toString() : null);
				homeLoanFinalViewResponse.setCarpetArea(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getCarpetArea()) ? finalHomeLoanDetailsOld.getCarpetArea().toString() : null);
				homeLoanFinalViewResponse.setSuperBuiltuparea(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getSuperBuiltUpArea()) ? finalHomeLoanDetailsOld.getSuperBuiltUpArea().toString() : null);
				homeLoanFinalViewResponse.setSellerName(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getSellerName()) ? finalHomeLoanDetailsOld.getSellerName() :  null);
				homeLoanFinalViewResponse.setSellerPremiseNo(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getSellersAddressPremise()) ? finalHomeLoanDetailsOld.getSellersAddressPremise() : null);
				homeLoanFinalViewResponse.setSellerStreetName(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getSellersAddressStreet()) ? finalHomeLoanDetailsOld.getSellersAddressStreet() : null);
				homeLoanFinalViewResponse.setSellerLandmark(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getSellersAddressLandmark()) ? finalHomeLoanDetailsOld.getSellersAddressLandmark() : null);
				homeLoanFinalViewResponse.setSellerPinCode(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getSellersAddressPincode()) ? finalHomeLoanDetailsOld.getSellersAddressPincode() : null);

				//Set Seller CityStateCountryName
				cityId = null;
				stateId = null;
				countryId = null;
				if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getSellersAddressCity()))
					cityId = finalHomeLoanDetailsOld.getSellersAddressCity().longValue();
				if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getSellersAddressState()))
					stateId = finalHomeLoanDetailsOld.getSellersAddressState();
				if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getSellersAddressCountry()))
					countryId = finalHomeLoanDetailsOld.getSellersAddressCountry();
				
				if(cityId != null || stateId != null || countryId != null) {
					Map<String ,Object> mapData = commonService.getCityStateCountryNameFromOneForm(cityId, stateId, countryId);
					if(mapData != null) {
						cityName = mapData.get(CommonUtils.CITY_NAME).toString();
						stateName = mapData.get(CommonUtils.STATE_NAME).toString();
						countryName = mapData.get(CommonUtils.COUNTRY_NAME).toString();
						
						//set City
						homeLoanFinalViewResponse.setSellerCity(cityName != null ? cityName : "NA");
						
						//set State
						homeLoanFinalViewResponse.setSellerState(stateName != null ? stateName : "NA");
						
						//set Country
						homeLoanFinalViewResponse.setSellerCountry(countryName != null ? countryName : "NA");
					}
				}
				
				/**
				try {
                    List<Long> permanentCity = new ArrayList<Long>(1);
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getSellersAddressCity())) {
                    	permanentCity.add(Long.valueOf(finalHomeLoanDetailsOld.getSellersAddressCity()));
                        OneFormResponse formResponsePermanentCity = oneFormClient.getCityByCityListId(permanentCity);
                        MasterResponse dataCity = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) formResponsePermanentCity.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCity)){
                        	homeLoanFinalViewResponse.setSellerCity(dataCity.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setSellerCity("-");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setSellerCity("-");
                    }
                } catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
                }
				try {
                    List<Long> permanentState = new ArrayList<Long>(1);
                    Long permanentStateLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getSellersAddressState())) {
                        permanentStateLong = Long.valueOf(finalHomeLoanDetailsOld.getSellersAddressState());
                        permanentState.add(permanentStateLong);
                        OneFormResponse statePermanent = oneFormClient.getStateByStateListId(permanentState);
                        MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) statePermanent.getListData().get(0), MasterResponse.class);
                        if (!CommonUtils.isObjectNullOrEmpty(dataState)){
                        	homeLoanFinalViewResponse.setSellerState(dataState.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setSellerState("-");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setSellerState("-");
                    }
                } catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
                }
				
				try {
                    List<Long> permanentCountry = new ArrayList<Long>(1);
                    Long permanentCountryLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailsOld.getSellersAddressCountry())) {
                        permanentCountryLong = Long.valueOf(finalHomeLoanDetailsOld.getSellersAddressCountry());
                        permanentCountry.add(permanentCountryLong);
                        OneFormResponse countryPermanent = oneFormClient.getCountryByCountryListId(permanentCountry);
                        MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) countryPermanent.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCountry)){
                        	homeLoanFinalViewResponse.setSellerCountry(dataCountry.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setSellerCountry("-");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setSellerCountry("-");
                    }
                } catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
                }
				*/
			} catch (Exception e) {
				logger.error("error while getting HL final details : ",e);
			}
		}
		return homeLoanFinalViewResponse;
	}
	
}
