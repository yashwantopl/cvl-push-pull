package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.util.CommonUtil;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.AddressResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.HomeLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.HomeLoanResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryHomeLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.common.DocumentManagementService;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.teaser.primaryview.HomeLoanPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.AlliedActivity;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.EmployeeWith;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.IndustryType;
import com.capitaworld.service.oneform.enums.LandSize;
import com.capitaworld.service.oneform.enums.LoanType;
import com.capitaworld.service.oneform.enums.MaritalStatus;
import com.capitaworld.service.oneform.enums.Occupation;
import com.capitaworld.service.oneform.enums.OccupationNature;
import com.capitaworld.service.oneform.enums.Options;
import com.capitaworld.service.oneform.enums.PropertySubType;
import com.capitaworld.service.oneform.enums.PropertyUsedType;
import com.capitaworld.service.oneform.enums.RepairType;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
@Transactional
public class HomeLoanPrimaryViewServiceImpl implements HomeLoanPrimaryViewService{

	
	private static final Logger logger = LoggerFactory.getLogger(HomeLoanPrimaryViewServiceImpl.class);

	@Autowired
	private RetailApplicantDetailRepository applicantRepository;

	@Autowired
	private CoApplicantService coApplicantService;

	@Autowired
	private GuarantorService guarantorService;

	@Autowired
	private PrimaryHomeLoanDetailRepository primaryHomeLoanRepository;
	
	@Autowired
	private OneFormClient oneFormClient;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private DocumentManagementService documentManagementService;

	protected static final String DMS_URL = "dmsURL";

	@Override
	public HomeLoanPrimaryViewResponse getHomeLoanPrimaryViewDetails(Long applicantId) throws Exception {
		HomeLoanPrimaryViewResponse homeLoanPrimaryViewResponse = new HomeLoanPrimaryViewResponse();
		HomeLoanResponse homeLoanResponse = new HomeLoanResponse();
		//applicant
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicantId);
		try {
			RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(applicationMaster.getUserId(), applicantId);
			if (applicantDetail != null) {
				RetailProfileViewResponse profileViewHLResponse = new RetailProfileViewResponse();
				homeLoanResponse.setDateOfProposal(CommonUtils.getStringDateFromDate(applicantDetail.getModifiedDate()));
				if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getOccupationId())){
					profileViewHLResponse.setNatureOfOccupationId(applicantDetail.getOccupationId());
					if (applicantDetail.getOccupationId() == 2){
                    	profileViewHLResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getCompanyName())){
                        	profileViewHLResponse.setCompanyName(applicantDetail.getCompanyName());
                        }else{
                        	profileViewHLResponse.setCompanyName("NA");
                        }
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getEmployedWithId())){
                            if (applicantDetail.getEmployedWithId() == 8){
                            	profileViewHLResponse.setEmployeeWith(applicantDetail.getEmployedWithOther());
                            }else{
                            	profileViewHLResponse.setEmployeeWith(EmployeeWith.getById(applicantDetail.getEmployedWithId()).getValue());
                            }
                        }else{
                        	profileViewHLResponse.setEmployeeWith("NA");
                        }
                    }
                    else if (applicantDetail.getOccupationId() == 3 || applicantDetail.getOccupationId() == 4) {
                    	profileViewHLResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getEntityName())){
                        	profileViewHLResponse.setEntityName(applicantDetail.getEntityName());
                        }else{
                        	profileViewHLResponse.setEntityName("NA");
                        }
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getIndustryTypeId())){
                            if (applicantDetail.getIndustryTypeId()==16){
                            	profileViewHLResponse.setIndustryType(applicantDetail.getIndustryTypeOther());
                            }else{
                            	profileViewHLResponse.setIndustryType(IndustryType.getById(applicantDetail.getIndustryTypeId()).getValue());
                            }
                        }else{
                        	profileViewHLResponse.setIndustryType("NA");
                        }
                    }
                    else if(applicantDetail.getOccupationId()==5){
                    	profileViewHLResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                        if(!CommonUtil.isObjectNullOrEmpty(applicantDetail.getSelfEmployedOccupationId())){
                        	if (applicantDetail.getSelfEmployedOccupationId()==10){
                            	profileViewHLResponse.setOccupation(applicantDetail.getSelfEmployedOccupationOther());
                            }else{
                            	profileViewHLResponse.setOccupation(Occupation.getById(applicantDetail.getSelfEmployedOccupationId()).getValue());
                            }	
                        }else{
                        	profileViewHLResponse.setOccupation("NA");
                        }
                    }else if(applicantDetail.getOccupationId()==6){
                    	profileViewHLResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getLandSize())){                          
                        	profileViewHLResponse.setLandSize(LandSize.getById(applicantDetail.getLandSize().intValue()).getValue());
                        }else{
                        	profileViewHLResponse.setLandSize("NA");
                        }
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getAlliedActivityId())){
                        	profileViewHLResponse.setAlliedActivity(AlliedActivity.getById(applicantDetail.getAlliedActivityId()).getValue());
                        }else{
                        	profileViewHLResponse.setAlliedActivity("NA");
                        }
                    }else if(applicantDetail.getOccupationId()==7){
                    	profileViewHLResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                    }                   
                }else{
                	profileViewHLResponse.setNatureOfOccupation("NA");
                }
				profileViewHLResponse.setFirstName((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getFirstName()) ? applicantDetail.getFirstName() : null));
				profileViewHLResponse.setMiddleName((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getMiddleName()) ? applicantDetail.getMiddleName() : null));
				profileViewHLResponse.setLastName((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getLastName()) ? applicantDetail.getLastName() : null));
				profileViewHLResponse.setGender((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getGenderId()) ? Gender.getById(applicantDetail.getGenderId()).getValue() : "NA"));
				profileViewHLResponse.setMaritalStatus((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getStatusId()) ? MaritalStatus.getById(applicantDetail.getStatusId()).getValue() : "NA"));
				profileViewHLResponse.setMonthlyIncome((!CommonUtils.isObjectNullOrEmpty(String.valueOf(applicantDetail.getMonthlyIncome())) ? String.format("%.2f",applicantDetail.getMonthlyIncome()) : "0"));
				
				
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
                        OneFormResponse country = oneFormClient.getCountryByCountryListId(officeCountry);
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
                    if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getOfficeStateId())) {
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
                homeLoanResponse.setOfficeAddress(officeAddress);

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
                homeLoanResponse.setPermanentAddress(permanentAddress);


				profileViewHLResponse.setTitle(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTitleId()) ? Title.getById(applicantDetail.getTitleId()).getValue() : null);
				profileViewHLResponse.setAge(applicantDetail.getBirthDate() != null ? CommonUtils.getAgeFromBirthDate(applicantDetail.getBirthDate()).toString() : null);

				homeLoanResponse.setCurrency(applicantDetail.getCurrencyId() != null ? Currency.getById(applicantDetail.getCurrencyId()).getValue() : "NA");

				profileViewHLResponse.setEntityName(applicantDetail.getEntityName());

				//set pan 
				profileViewHLResponse.setPan(applicantDetail.getPan().toUpperCase());

				//applicant profile image
				try {
					homeLoanResponse.setProfileImage(documentManagementService.getDocumentDetails(applicantId,DocumentAlias.UERT_TYPE_APPLICANT,DocumentAlias.HOME_LOAN_PROFIEL_PICTURE));
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				
				//get list of Pan Card
				try {
					profileViewHLResponse.setPanCardList(documentManagementService.getDocumentDetails(applicantId,DocumentAlias.UERT_TYPE_APPLICANT,DocumentAlias.HOME_LOAN_APPLICANT_SCANNED_COPY_OF_PAN_CARD));
				} catch (DocumentException e) {
					e.printStackTrace();
				}

				//get list of Aadhar Card
				try {
					profileViewHLResponse.setAadharCardList(documentManagementService.getDocumentDetails(applicantId,DocumentAlias.UERT_TYPE_APPLICANT,DocumentAlias.HOME_LOAN_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD));
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				homeLoanPrimaryViewResponse.setPersonalProfileRespoonse(profileViewHLResponse);
			} else {
				throw new Exception("No Data found");
			}
		} catch (Exception e) {
			throw new Exception("Problem Occured while Fetching Retail Details");
		}

		//set up loan specific details
		PrimaryHomeLoanDetail loanDetail = primaryHomeLoanRepository.getByApplicationAndUserId(applicantId,applicationMaster.getUserId());
		
		if(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyType())){
			homeLoanResponse.setPropertyType(PropertySubType.getById(loanDetail.getPropertyType()).getValue());
			homeLoanResponse.setPropertyUsedTypeId(loanDetail.getPropertyType().toString());
			if(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyType()) && loanDetail.getPropertyType() == 3){
				homeLoanResponse.setPropertyUsedType(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyUsedType()) ? PropertyUsedType.getById(loanDetail.getPropertyUsedType()).getValue() : "NA");
				if(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyUsedType()) && loanDetail.getPropertyUsedType() == 3){
					homeLoanResponse.setConstructionCompleted(!CommonUtils.isObjectNullOrEmpty(loanDetail.getIsConstructionCompleted()) ? Options.getById((loanDetail.getIsConstructionCompleted() ? 1 : 0)).getValue() : "NA");
					if(!loanDetail.getIsConstructionCompleted()){
						homeLoanResponse.setConstructionCompletionTimeInMonth(!CommonUtils.isObjectNullOrEmpty(loanDetail.getConstructionCompletionTimeInMonth()) ? loanDetail.getConstructionCompletionTimeInMonth().toString() : "NA");
						homeLoanResponse.setConstructionCompletionTimeInYear(!CommonUtils.isObjectNullOrEmpty(loanDetail.getConstructionCompletionTimeInYear()) ? loanDetail.getConstructionCompletionTimeInYear().toString() : "NA");
					}
				}
				homeLoanResponse.setProjectName(loanDetail.getProjectName());
				homeLoanResponse.setProjectCity(loanDetail.getProjectCity());
				homeLoanResponse.setArea(!CommonUtils.isObjectNullOrEmpty(loanDetail.getArea()) ? loanDetail.getArea().toString() : "NA");
				homeLoanResponse.setPropertyPrice(loanDetail.getPropertyPrice().toString());
			}else if(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyType()) && loanDetail.getPropertyType() == 4){
				homeLoanResponse.setBunglowCost(!CommonUtils.isObjectNullOrEmpty(loanDetail.getBunglowCost()) ? loanDetail.getBunglowCost().toString() : "NA");
				homeLoanResponse.setConstructionCost(!CommonUtils.isObjectNullOrEmpty(loanDetail.getConstructionCost()) ? loanDetail.getConstructionCost().toString() : "NA");
				homeLoanResponse.setCompletionTimeInMonth(!CommonUtils.isObjectNullOrEmpty(loanDetail.getCompletionTimeInMonth()) ? loanDetail.getCompletionTimeInMonth().toString() : "NA");
				homeLoanResponse.setCompletionTimeInYear(!CommonUtils.isObjectNullOrEmpty(loanDetail.getCompletionTimeInYear()) ? loanDetail.getCompletionTimeInYear().toString() : "NA");
			}else if(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyType()) && loanDetail.getPropertyType() == 5){
				homeLoanResponse.setRenovationType(!CommonUtils.isObjectNullOrEmpty(loanDetail.getRenovationType()) ? RepairType.getById(loanDetail.getRenovationType()).getValue() : "NA");
				if(!CommonUtils.isObjectNullOrEmpty(loanDetail.getRenovationType()) && loanDetail.getRenovationType() == 8){
					homeLoanResponse.setRenovationTypeOther(loanDetail.getOtherRenovationType());
				}
				homeLoanResponse.setRenovationCost(loanDetail.getRenovationCost().toString());
				homeLoanResponse.setRenovationCompletionTimeInMonth(!CommonUtils.isObjectNullOrEmpty(loanDetail.getRenovationCompletionTimeInMonth()) ? loanDetail.getRenovationCompletionTimeInMonth().toString() : "NA");
				homeLoanResponse.setRenovationCompletionTimeInYear(!CommonUtils.isObjectNullOrEmpty(loanDetail.getRenovationCompletionTimeInYear()) ? loanDetail.getRenovationCompletionTimeInYear().toString() : "NA");	
				if(!CommonUtils.isObjectNullOrEmpty(loanDetail.getIsLoanTaken())){
					if(loanDetail.getIsLoanTaken()){
						homeLoanResponse.setLoanTaken(!CommonUtils.isObjectNullOrEmpty(loanDetail.getIsLoanTaken()) ? Options.getById(loanDetail.getIsLoanTaken() ? 1 : 0).getValue() : "NA");
						homeLoanResponse.setDateOfLoanTaken(!CommonUtils.isObjectNullOrEmpty(loanDetail.getDateOfLoanTaken()) ? CommonUtils.getStringDateFromDate(loanDetail.getDateOfLoanTaken()) : "NA");	
					}else{
						homeLoanResponse.setLoanTaken(!CommonUtils.isObjectNullOrEmpty(loanDetail.getIsLoanTaken()) ? Options.getById(loanDetail.getIsLoanTaken() ? 1 : 0).getValue() : "NA");
					}	
				}	
						
			}else if(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyType()) && loanDetail.getPropertyType() == 6){
				homeLoanResponse.setLandPlotCost(!CommonUtils.isObjectNullOrEmpty(loanDetail.getLandPlotCost()) ? loanDetail.getLandPlotCost().toString() : "NA");
				homeLoanResponse.setLandArea(!CommonUtils.isObjectNullOrEmpty(loanDetail.getLandArea()) ? loanDetail.getLandArea().toString() : "NA");
			}
		}
		
		homeLoanResponse.setDownPayment(!CommonUtils.isObjectNullOrEmpty(loanDetail.getDownPayment()) ? loanDetail.getDownPayment().toString() : "NA");
		homeLoanResponse.setLoanAmount(!CommonUtils.isObjectNullOrEmpty(loanDetail.getAmount()) ? loanDetail.getAmount().toString() : "NA");
		homeLoanResponse.setTenure(!CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? String.valueOf(loanDetail.getTenure() / 12) : "NA");
		homeLoanResponse.setLoanType(LoanType.getById(applicationMaster.getProductId()).getValue());
		homeLoanPrimaryViewResponse.setHomeLoanResponse(homeLoanResponse);
		
		//setting co-application details
		List<RetailProfileViewResponse> coApplicantResponse = null;
		try {
			coApplicantResponse = coApplicantService.getCoApplicantPLResponse(applicantId, applicationMaster.getUserId(),applicationMaster.getProductId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		homeLoanPrimaryViewResponse.setCoApplicantResponse(coApplicantResponse);

		//setting guarantor details
		List<RetailProfileViewResponse> garantorResponse = null;
		try {
			garantorResponse = guarantorService.getGuarantorServiceResponse(applicantId, applicationMaster.getUserId(),applicationMaster.getProductId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		homeLoanPrimaryViewResponse.setGarantorResponse(garantorResponse);

		return homeLoanPrimaryViewResponse;
	}

}
