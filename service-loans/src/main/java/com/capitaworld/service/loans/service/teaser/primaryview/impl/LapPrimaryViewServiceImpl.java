package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.util.CommonUtil;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLapLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.AddressResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.LapPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.LapResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryLapLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.common.DocumentManagementService;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.teaser.primaryview.LapPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.*;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Transactional
public class LapPrimaryViewServiceImpl implements LapPrimaryViewService{

	private static final Logger logger = LoggerFactory.getLogger(LapPrimaryViewServiceImpl.class);

	@Autowired
	private RetailApplicantDetailRepository applicantRepository;
	
	@Autowired
	private CoApplicantService coApplicantService;

	@Autowired
	private GuarantorService guarantorService;
	
	@Autowired
	private OneFormClient oneFormClient;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private PrimaryLapLoanDetailRepository primaryLapRepository;

    @Autowired
    private DocumentManagementService documentManagementService;

    protected static final String DMS_URL = "dmsURL";

	@Override
	public LapPrimaryViewResponse getLapPrimaryViewDetails(Long applicantId) throws Exception {
		
		LapPrimaryViewResponse lapPrimaryViewResponse = new LapPrimaryViewResponse();
		LapResponse lapResponse = new LapResponse();
		//applicant
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicantId);
        try {
			RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(applicationMaster.getUserId(), applicantId);
			if (applicantDetail != null) {
				RetailProfileViewResponse profileViewLAPResponse = new RetailProfileViewResponse();
				lapResponse.setDateOfProposal(CommonUtils.getStringDateFromDate(applicantDetail.getModifiedDate()));
				if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getOccupationId())){
					profileViewLAPResponse.setNatureOfOccupationId(applicantDetail.getOccupationId());
					if (applicantDetail.getOccupationId() == 2){
						profileViewLAPResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getCompanyName())){
                        	profileViewLAPResponse.setCompanyName(applicantDetail.getCompanyName());
                        }else{
                        	profileViewLAPResponse.setCompanyName("NA");
                        }
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getEmployedWithId())){
                            if (applicantDetail.getEmployedWithId() == 8){
                            	profileViewLAPResponse.setEmployeeWith(applicantDetail.getEmployedWithOther());
                            }else{
                            	profileViewLAPResponse.setEmployeeWith(EmployeeWith.getById(applicantDetail.getEmployedWithId()).getValue());
                            }
                        }else{
                        	profileViewLAPResponse.setEmployeeWith("NA");
                        }
                    }
                    else if (applicantDetail.getOccupationId() == 3 || applicantDetail.getOccupationId() == 4) {
                    	profileViewLAPResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getEntityName())){
                        	profileViewLAPResponse.setEntityName(applicantDetail.getEntityName());
                        }else{
                        	profileViewLAPResponse.setEntityName("NA");
                        }
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getIndustryTypeId())){
                            if (applicantDetail.getIndustryTypeId()==16){
                            	profileViewLAPResponse.setIndustryType(applicantDetail.getIndustryTypeOther());
                            }else{
                            	profileViewLAPResponse.setIndustryType(IndustryType.getById(applicantDetail.getIndustryTypeId()).getValue());
                            }
                        }else{
                        	profileViewLAPResponse.setIndustryType("NA");
                        }
                    }
                    else if(applicantDetail.getOccupationId()==5){
                    	profileViewLAPResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                        if(!CommonUtil.isObjectNullOrEmpty(applicantDetail.getSelfEmployedOccupationId())){
                        	if (applicantDetail.getSelfEmployedOccupationId()==10){
                        		profileViewLAPResponse.setOccupation(applicantDetail.getSelfEmployedOccupationOther());
                            }else{
                            	profileViewLAPResponse.setOccupation(Occupation.getById(applicantDetail.getSelfEmployedOccupationId()).getValue());
                            }	
                        }else{
                        	profileViewLAPResponse.setOccupation("NA");
                        }
                    }else if(applicantDetail.getOccupationId()==6){
                    	profileViewLAPResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getLandSize())){                          
                        	profileViewLAPResponse.setLandSize(LandSize.getById(applicantDetail.getLandSize().intValue()).getValue());
                        }else{
                        	profileViewLAPResponse.setLandSize("NA");
                        }
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getAlliedActivityId())){
                        	profileViewLAPResponse.setAlliedActivity(AlliedActivity.getById(applicantDetail.getAlliedActivityId()).getValue());
                        }else{
                        	profileViewLAPResponse.setAlliedActivity("NA");
                        }
                    }else if(applicantDetail.getOccupationId()==7){
                    	profileViewLAPResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                    }                   
                }else{
                	profileViewLAPResponse.setNatureOfOccupation("NA");
                }
				profileViewLAPResponse.setFirstName((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getFirstName()) ? applicantDetail.getFirstName() : "NA"));
				profileViewLAPResponse.setMiddleName((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getMiddleName()) ? applicantDetail.getMiddleName() : "NA"));
				profileViewLAPResponse.setLastName((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getLastName()) ? applicantDetail.getLastName() : "NA"));
				profileViewLAPResponse.setGender((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getGenderId()) ? Gender.getById(applicantDetail.getGenderId()).getValue() : "NA"));
				profileViewLAPResponse.setMaritalStatus((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getStatusId()) ? MaritalStatus.getById(applicantDetail.getStatusId()).getValue() : "NA"));
				profileViewLAPResponse.setMonthlyIncome((!CommonUtils.isObjectNullOrEmpty(String.valueOf(applicantDetail.getMonthlyIncome())) ? String.valueOf(applicantDetail.getMonthlyIncome()) : "0"));
				
				
				//set office address
                AddressResponse officeAddress = new AddressResponse();
                try {
                    List<Long> officeCity = new ArrayList<Long>(1);
                    if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getOfficeCityId())){
                    	officeCity.add(applicantDetail.getOfficeCityId());
                        OneFormResponse formResponse = oneFormClient.getCityByCityListId(officeCity);
                        MasterResponse data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) formResponse.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(data)){
                        	officeAddress.setCity(data.getValue());	
                        }else{
                        	officeAddress.setCity("NA");
                        }	
                    }else{
                       	officeAddress.setCity("NA");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    List<Long> officeCountry = new ArrayList<Long>(1);
                    Long officeCountryLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getOfficeCountryId())) {
                        officeCountryLong = Long.valueOf(applicantDetail.getOfficeCountryId().toString());

                        officeCountry.add(officeCountryLong);
                        OneFormResponse country = oneFormClient.getCityByCityListId(officeCountry);
                        MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) country.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCountry.getValue())){
                        	officeAddress.setCountry(dataCountry.getValue());
                        }else{
                        	officeAddress.setCountry("NA");
                        }
                    }else{
                    	officeAddress.setCountry("NA");
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
                try {
                    List<Long> officeState = new ArrayList<Long>(1);
                    Long officeStateLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getOfficeCountryId())) {
                        officeStateLong = Long.valueOf(applicantDetail.getOfficeStateId().toString());

                        officeState.add(officeStateLong);
                        OneFormResponse state = oneFormClient.getStateByStateListId(officeState);
                        MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) state.getListData().get(0), MasterResponse.class);
                        if(!CommonUtil.isObjectNullOrEmpty(dataState)){
                        	officeAddress.setState(dataState.getValue());	
                        }else{
                        	officeAddress.setState("NA");
                        }
                    }else{
                    	officeAddress.setState("NA");
                    }
                } catch (Exception e) {
                	e.printStackTrace();
                }
                officeAddress.setLandMark(applicantDetail.getOfficeLandMark());
                officeAddress.setPincode(applicantDetail.getOfficePincode() != null ? applicantDetail.getOfficePincode().toString() : null);
                officeAddress.setPremiseNumber(applicantDetail.getOfficePremiseNumberName());
                officeAddress.setStreetName(applicantDetail.getOfficeStreetName());
                lapResponse.setOfficeAddress(officeAddress);

                //set permanent address
                AddressResponse permanentAddress = new AddressResponse();
                try {
                    List<Long> permanentCity = new ArrayList<Long>(1);
                    if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPermanentCityId())) {
                    	permanentCity.add(applicantDetail.getPermanentCityId());
                        OneFormResponse formResponsePermanentCity = oneFormClient.getCityByCityListId(permanentCity);
                        MasterResponse dataCity = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) formResponsePermanentCity.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCity)){
                        	permanentAddress.setCity(dataCity.getValue());	
                        }else{
                        	permanentAddress.setCity("NA");
                        }
                    }else{
                    	permanentAddress.setCity("NA");
                    }
                } catch (Exception e) {

                }
                try {
                    List<Long> permanentCountry = new ArrayList<Long>(1);
                    Long permanentCountryLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPermanentCountryId())) {
                        permanentCountryLong = Long.valueOf(applicantDetail.getPermanentCountryId().toString());
                        permanentCountry.add(permanentCountryLong);
                        OneFormResponse countryPermanent = oneFormClient.getCountryByCountryListId(permanentCountry);
                        MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) countryPermanent.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCountry)){
                        	permanentAddress.setCountry(dataCountry.getValue());	
                        }else{
                        	permanentAddress.setCountry("NA");
                        }
                    }else{
                    	permanentAddress.setCountry("NA");
                    }
                } catch (Exception e) {

                }
                try {
                    List<Long> permanentState = new ArrayList<Long>(1);
                    Long permanentStateLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPermanentStateId())) {
                        permanentStateLong = Long.valueOf(applicantDetail.getPermanentStateId().toString());
                        permanentState.add(permanentStateLong);
                        OneFormResponse statePermanent = oneFormClient.getStateByStateListId(permanentState);
                        MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) statePermanent.getListData().get(0), MasterResponse.class);
                        if (!CommonUtils.isObjectNullOrEmpty(dataState)){
                        	permanentAddress.setState(dataState.getValue());	
                        }else{
                        	permanentAddress.setCountry("NA");	
                        }
                    }else{
                    	permanentAddress.setCountry("NA");
                    }
                } catch (Exception e) {

                }
                permanentAddress.setLandMark(applicantDetail.getPermanentLandMark());
                permanentAddress.setPincode(applicantDetail.getPermanentPincode() != null ? applicantDetail.getPermanentPincode().toString() : null);
                permanentAddress.setPremiseNumber(applicantDetail.getPermanentPremiseNumberName());
                permanentAddress.setStreetName(applicantDetail.getPermanentStreetName());
                lapResponse.setPermanentAddress(permanentAddress);


				profileViewLAPResponse.setTitle(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTitleId()) ? Title.getById(applicantDetail.getTitleId()).getValue() : null);
				profileViewLAPResponse.setAge(applicantDetail.getBirthDate() != null ? CommonUtils.getAgeFromBirthDate(applicantDetail.getBirthDate()).toString() : null);
                lapResponse.setLoanType(applicationMaster.getProductId()!=null?LoanType.getById(applicationMaster.getProductId()).getValue():null);
				lapResponse.setCurrency(applicantDetail.getCurrencyId() != null ? Currency.getById(applicantDetail.getCurrencyId()).getValue() : "NA");

				profileViewLAPResponse.setEntityName(applicantDetail.getEntityName());

				//set pan 
				profileViewLAPResponse.setPan(applicantDetail.getPan());

				//applicant profile image
				try {
					lapResponse.setProfileImage(documentManagementService.getDocumentDetails(applicantId,DocumentAlias.UERT_TYPE_APPLICANT,DocumentAlias.LAP_LOAN_PROFIEL_PICTURE));
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				
				//get list of Pan Card
				try {
					profileViewLAPResponse.setPanCardList(documentManagementService.getDocumentDetails(applicantId,DocumentAlias.UERT_TYPE_APPLICANT,DocumentAlias.LAP_LOAN_APPLICANT_SCANNED_COPY_OF_PAN_CARD));
				} catch (DocumentException e) {
					e.printStackTrace();
				}

				//get list of Aadhar Card
				try {
					profileViewLAPResponse.setAadharCardList(documentManagementService.getDocumentDetails(applicantId,DocumentAlias.UERT_TYPE_APPLICANT,DocumentAlias.LAP_LOAN_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD));
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				lapPrimaryViewResponse.setApplicant(profileViewLAPResponse);
			} else {
				throw new Exception("No Data found");
			}
		} catch (Exception e) {
            e.printStackTrace();
			throw new Exception("Problem Occured while Fetching Retail Details");
		}

		//set up loan specific details
		PrimaryLapLoanDetail loanDetail = primaryLapRepository.getByApplicationAndUserId(applicantId,applicationMaster.getUserId());
		
		if(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyType())){
			if(!CommonUtils.isObjectNullOrEmpty(loanDetail.getLoanPurpose())){
				lapResponse.setLoanPurpose(LoanPurpose.getById(loanDetail.getLoanPurpose()).getValue());
				if(loanDetail.getLoanPurpose() == 5){
					lapResponse.setLoanPurposeOther(!CommonUtils.isObjectNullOrEmpty(loanDetail.getLoanPurposeOther()) ? loanDetail.getLoanPurposeOther() : "NA");		
				}
			}
			
			lapResponse.setLoanAmount(!CommonUtils.isObjectNullOrEmpty(loanDetail.getAmount()) ? loanDetail.getAmount().toString() : "NA");
			lapResponse.setTenure(!CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? loanDetail.getTenure().toString() : "NA");
			if(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyType())){
				lapResponse.setPropertyType(PropertyType.getById(loanDetail.getPropertyType()).getValue());
				if(loanDetail.getPropertyType() == 4){
					lapResponse.setPropertyTypeOther(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyTypeOther()) ? loanDetail.getPropertyTypeOther() : "NA");		
				}
			}
			
			if(!CommonUtils.isObjectNullOrEmpty(loanDetail.getOccupationStatus())){
				lapResponse.setOccupationStatus(OccupationStatus.getById(loanDetail.getOccupationStatus()).getValue());
				if(loanDetail.getOccupationStatus() == 5){
					lapResponse.setOccupationStatusOther(!CommonUtils.isObjectNullOrEmpty(loanDetail.getOccupationStatusOther()) ? loanDetail.getOccupationStatusOther() : "NA");		
				}
			}
			
			lapResponse.setPropertyAgeInMonths(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyAgeInMonth()) ? loanDetail.getPropertyAgeInMonth().toString() : "NA");
			lapResponse.setPropertyAgeInYears(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyAgeInYear()) ? loanDetail.getPropertyAgeInYear().toString() : "NA");
			lapResponse.setTotalArea(!CommonUtils.isObjectNullOrEmpty(loanDetail.getLandArea()) ? loanDetail.getLandArea().toString() : "NA");
			lapResponse.setBuiltUpArea(!CommonUtils.isObjectNullOrEmpty(loanDetail.getBuiltUpArea()) ? loanDetail.getBuiltUpArea().toString() : "NA");
			lapResponse.setPropertyValue(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyValue()) ? loanDetail.getPropertyValue().toString() : "NA");
			lapResponse.setPropertyOwnerName(!CommonUtils.isObjectNullOrEmpty(loanDetail.getOwnerName()) ? loanDetail.getOwnerName() : "NA");
			lapResponse.setPropertyPremiseNumber(!CommonUtils.isObjectNullOrEmpty(loanDetail.getAddressPremise()) ? loanDetail.getAddressPremise() : "NA");
			lapResponse.setPropertyStreetName(!CommonUtils.isObjectNullOrEmpty(loanDetail.getAddressStreet()) ? loanDetail.getAddressStreet() : "NA");
			lapResponse.setPropertyLandmark(!CommonUtils.isObjectNullOrEmpty(loanDetail.getAddressLandmark()) ? loanDetail.getAddressLandmark() : "NA");
			
            try {
                List<Long> officeCity = new ArrayList<Long>(1);
                if(!CommonUtils.isObjectNullOrEmpty(loanDetail.getAddressCity())){
                	officeCity.add(Long.valueOf(loanDetail.getAddressCity().toString()));
                    OneFormResponse formResponse = oneFormClient.getCityByCityListId(officeCity);
                    MasterResponse data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) formResponse.getListData().get(0), MasterResponse.class);
                    if(!CommonUtils.isObjectNullOrEmpty(data)){
                    	lapResponse.setPropertyCity(data.getValue());
                    }else{
                    	lapResponse.setPropertyCity("NA");
                    }	
                }else{
                	lapResponse.setPropertyCity("NA");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                List<Long> officeCountry = new ArrayList<Long>(1);
                Long officeCountryLong = null;
                if (!CommonUtils.isObjectNullOrEmpty(loanDetail.getAddressCountry())) {
                    officeCountryLong = Long.valueOf(loanDetail.getAddressCountry().toString());

                    officeCountry.add(officeCountryLong);
                    OneFormResponse country = oneFormClient.getCountryByCountryListId(officeCountry);
                    MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) country.getListData().get(0), MasterResponse.class);
                    if(!CommonUtils.isObjectNullOrEmpty(dataCountry.getValue())){
                    	lapResponse.setPropertyCountry(dataCountry.getValue());
                    }else{
                    	lapResponse.setPropertyCountry("NA");
                    }
                }else{
                	lapResponse.setPropertyCountry("NA");
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
            try {
                List<Long> officeState = new ArrayList<Long>(1);
                Long officeStateLong = null;
                if (!CommonUtils.isObjectNullOrEmpty(loanDetail.getAddressState())) {
                    officeStateLong = Long.valueOf(loanDetail.getAddressState().toString());

                    officeState.add(officeStateLong);
                    OneFormResponse state = oneFormClient.getStateByStateListId(officeState);
                    MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) state.getListData().get(0), MasterResponse.class);
                    if(!CommonUtil.isObjectNullOrEmpty(dataState)){
                    	lapResponse.setPropertyState(dataState.getValue());
                    }else{
                    	lapResponse.setPropertyState("NA");
                    }
                }else{
                	lapResponse.setPropertyState("NA");
                }
            } catch (Exception e) {
            	e.printStackTrace();
            }
			
			lapResponse.setPropertyPincode(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPincode()) ? loanDetail.getPincode().toString() : "NA");
		}
		
		lapResponse.setLoanAmount(!CommonUtils.isObjectNullOrEmpty(loanDetail.getAmount()) ? loanDetail.getAmount().toString() : "NA");
		lapResponse.setTenure(!CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? loanDetail.getTenure().toString() : "NA");
		
		lapPrimaryViewResponse.setLapResponse(lapResponse);
		
		//setting co-application details
		List<RetailProfileViewResponse> coApplicantResponse = null;
		try {
			coApplicantResponse = coApplicantService.getCoApplicantPLResponse(applicantId, applicationMaster.getUserId(),applicationMaster.getProductId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		lapPrimaryViewResponse.setCoApplicantList(coApplicantResponse);

		//setting guarantor details
		List<RetailProfileViewResponse> garantorResponse = null;
		try {
			garantorResponse = guarantorService.getGuarantorServiceResponse(applicantId, applicationMaster.getUserId(),applicationMaster.getProductId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		lapPrimaryViewResponse.setGuarantorList(garantorResponse);
		
		return lapPrimaryViewResponse;
	}
	
	
}
