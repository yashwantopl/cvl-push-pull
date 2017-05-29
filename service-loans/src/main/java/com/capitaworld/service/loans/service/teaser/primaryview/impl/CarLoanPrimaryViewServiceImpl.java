package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.CommonUtil;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.AddressResponse;
import com.capitaworld.service.loans.model.retail.PrimaryCarLoanDetailRequest;
import com.capitaworld.service.loans.model.teaser.primaryview.CarLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.CarLoanResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryCarLoanService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.service.teaser.primaryview.CarLoanPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.CityByCityListIdClient;
import com.capitaworld.service.oneform.client.CountryByCountryListIdClient;
import com.capitaworld.service.oneform.client.StateListByStateListIdClient;
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
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
@Transactional
/**
 * Created by dhaval on 23-May-17.
 */
public class CarLoanPrimaryViewServiceImpl implements CarLoanPrimaryViewService{

    private static final Logger logger = LoggerFactory.getLogger(CarLoanPrimaryViewServiceImpl.class);

    @Autowired
    private RetailApplicantDetailRepository applicantRepository;

    @Autowired
    private RetailApplicantService retailApplicantService;

    @Autowired
    private CoApplicantService coApplicantService;

    @Autowired
    private GuarantorService guarantorService;

    @Autowired
    private PrimaryCarLoanService primaryCarLoanService;

    @Autowired
    private Environment environment;
    
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    protected static final String DMS_URL = "dmsURL";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public CarLoanPrimaryViewResponse getCarLoanPrimaryViewDetails(Long toApplicationId) {
        CarLoanPrimaryViewResponse carLoanPrimaryViewResponse = new CarLoanPrimaryViewResponse();
        CarLoanResponse carLoanResponse = new CarLoanResponse();
        LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(toApplicationId);
        Long userId = applicationMaster.getUserId();
        //applicant
        try {
            RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(userId, toApplicationId);
            if (applicantDetail != null) {
                RetailProfileViewResponse profileViewPLResponse = new RetailProfileViewResponse();
                carLoanResponse.setDateOfProposal(CommonUtils.getStringDateFromDate(applicantDetail.getModifiedDate()));
                if (applicantDetail.getApplicationId() != null) {
                    carLoanResponse.setTenure(applicantDetail.getApplicationId().getTenure() != null ? applicantDetail.getApplicationId().getTenure().toString() : null);
                    carLoanResponse.setLoanType(applicantDetail.getApplicationId().getProductId() != null ? LoanType.getById(applicantDetail.getApplicationId().getProductId()).getValue() : null);
                    carLoanResponse.setLoanAmount(applicantDetail.getApplicationId().getAmount() != null ? applicantDetail.getApplicationId().getAmount().toString() : null);
                    carLoanResponse.setCurrency(applicantDetail.getCurrencyId() != null ? Currency.getById(applicantDetail.getCurrencyId()).getValue() : null);
                }
                if (applicantDetail.getOccupationId()!=null){
                    if (applicantDetail.getOccupationId()==2){
                        profileViewPLResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getCompanyName())){
                            profileViewPLResponse.setCompanyName(applicantDetail.getCompanyName());
                        }
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getEmployedWithId())){
                            if (applicantDetail.getEmployedWithId()==8){
                                profileViewPLResponse.setEmployeeWith(applicantDetail.getEmployedWithOther());
                            }else{
                                profileViewPLResponse.setEmployeeWith(EmployeeWith.getById(applicantDetail.getEmployedWithId()).getValue());
                            }
                        }
                    }
                    else if (applicantDetail.getOccupationId() == 3 || applicantDetail.getOccupationId() == 4) {
                        profileViewPLResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getEntityName())){
                            profileViewPLResponse.setEntityName(applicantDetail.getEntityName());
                        }
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getIndustryTypeId())){
                            if (applicantDetail.getIndustryTypeId()==16){
                                profileViewPLResponse.setIndustryType(applicantDetail.getIndustryTypeOther());
                            }else{
                                profileViewPLResponse.setIndustryType(IndustryType.getById(applicantDetail.getIndustryTypeId()).getValue());
                            }
                        }
                    }
                    else if(applicantDetail.getOccupationId()==5){
                        profileViewPLResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                        if (applicantDetail.getSelfEmployedOccupationId()==10){
                            profileViewPLResponse.setOccupation(applicantDetail.getSelfEmployedOccupationOther());
                        }else{
                            profileViewPLResponse.setOccupation(Occupation.getById(applicantDetail.getSelfEmployedOccupationId()).getValue());
                        }
                    }else if(applicantDetail.getOccupationId()==6){
                    	profileViewPLResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getLandSize())){                          
                            profileViewPLResponse.setLandSize(LandSize.getById(applicantDetail.getLandSize().intValue()).getValue());
                        }
                        if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getAlliedActivityId())){
                        	profileViewPLResponse.setAlliedActivity(AlliedActivity.getById(applicantDetail.getAlliedActivityId()).getValue());
                        }
                    }else if(applicantDetail.getOccupationId()==7){
                    	profileViewPLResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
                    }                   
                }

                //set pan car
                profileViewPLResponse.setPan(applicantDetail.getPan() != null ? applicantDetail.getPan() : null);
                profileViewPLResponse.setTitle(applicantDetail.getTitleId() != null ? Title.getById(applicantDetail.getTitleId()).getValue() : null);
                profileViewPLResponse.setAge(applicantDetail.getBirthDate() != null ? CommonUtils.getAgeFromBirthDate(applicantDetail.getBirthDate()).toString() : null);
                profileViewPLResponse.setFirstName(applicantDetail.getFirstName() != null ? applicantDetail.getFirstName() : null);
                profileViewPLResponse.setGender(applicantDetail.getGenderId() != null ? Gender.getById(applicantDetail.getGenderId()).getValue() : null);
                profileViewPLResponse.setLastName(applicantDetail.getLastName() != null ? applicantDetail.getLastName() : null);
                profileViewPLResponse.setMaritalStatus(applicantDetail.getStatusId() != null ? MaritalStatus.getById(applicantDetail.getStatusId()).getValue() : null);
                profileViewPLResponse.setMiddleName(applicantDetail.getMiddleName() != null ? applicantDetail.getMiddleName() : null);
                profileViewPLResponse.setMonthlyIncome(String.valueOf(applicantDetail.getMonthlyIncome() != null ? applicantDetail.getMonthlyIncome() : 0));

                //set office address
                AddressResponse officeAddress = new AddressResponse();
                CityByCityListIdClient cityByCityListIdClient = new CityByCityListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
                try {
                    List<Long> officeCity = new ArrayList<Long>(1);
                    officeCity.add(applicantDetail.getOfficeCityId());
                    OneFormResponse formResponse = cityByCityListIdClient.send(officeCity);

                    MasterResponse data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) formResponse.getListData().get(0), MasterResponse.class);
                    officeAddress.setCity(data.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                CountryByCountryListIdClient countryByCountryListIdClient = new CountryByCountryListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
                try {
                    List<Long> officeCountry = new ArrayList<Long>(1);
                    Long officeCountryLong = null;
                    if (applicantDetail.getOfficeCountryId() != null) {
                        officeCountryLong = Long.valueOf(applicantDetail.getOfficeCountryId().toString());

                        officeCountry.add(officeCountryLong);
                        OneFormResponse country = countryByCountryListIdClient.send(officeCountry);
                        MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) country.getListData().get(0), MasterResponse.class);
                        officeAddress.setCountry(dataCountry.getValue());
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
                StateListByStateListIdClient stateListByStateListIdClient = new StateListByStateListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
                try {
                    List<Long> officeState = new ArrayList<Long>(1);
                    Long officeStateLong = null;
                    if (applicantDetail.getOfficeCountryId() != null) {
                        officeStateLong = Long.valueOf(applicantDetail.getOfficeStateId().toString());

                        officeState.add(officeStateLong);
                        OneFormResponse state = stateListByStateListIdClient.send(officeState);
                        MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) state.getListData().get(0), MasterResponse.class);
                        officeAddress.setState(dataState.getValue());
                    }
                } catch (Exception e) {

                }
                officeAddress.setLandMark(applicantDetail.getOfficeLandMark());
                officeAddress.setPincode(applicantDetail.getOfficePincode() != null ? applicantDetail.getOfficePincode().toString() : null);
                officeAddress.setPremiseNumber(applicantDetail.getOfficePremiseNumberName());
                officeAddress.setStreetName(applicantDetail.getOfficeStreetName());
                carLoanResponse.setOfficeAddress(officeAddress);

                //set permanent address
                AddressResponse permanentAddress = new AddressResponse();
                try {
                    List<Long> permanentCity = new ArrayList<Long>(1);
                    permanentCity.add(applicantDetail.getPermanentCityId());
                    OneFormResponse formResponsePermanentCity = cityByCityListIdClient.send(permanentCity);
                    MasterResponse dataCity = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) formResponsePermanentCity.getListData().get(0), MasterResponse.class);
                    permanentAddress.setCity(dataCity.getValue());
                } catch (Exception e) {

                }
                try {
                    List<Long> permanentCountry = new ArrayList<Long>(1);
                    Long permanentCountryLong = null;
                    if (applicantDetail.getPermanentCountryId() != null) {
                        permanentCountryLong = Long.valueOf(applicantDetail.getPermanentCountryId().toString());
                        permanentCountry.add(permanentCountryLong);
                        OneFormResponse countryPermanent = countryByCountryListIdClient.send(permanentCountry);
                        MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) countryPermanent.getListData().get(0), MasterResponse.class);
                        permanentAddress.setCountry(dataCountry.getValue());
                    }
                } catch (Exception e) {

                }
                try {
                    List<Long> permanentState = new ArrayList<Long>(1);
                    Long permanentStateLong = null;
                    if (applicantDetail.getPermanentStateId() != null) {
                        permanentStateLong = Long.valueOf(applicantDetail.getPermanentStateId().toString());
                        permanentState.add(permanentStateLong);
                        OneFormResponse statePermanent = stateListByStateListIdClient.send(permanentState);
                        MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) statePermanent.getListData().get(0), MasterResponse.class);
                        permanentAddress.setState(dataState.getValue());
                    }
                } catch (Exception e) {

                }
                permanentAddress.setLandMark(applicantDetail.getPermanentLandMark());
                permanentAddress.setPincode(applicantDetail.getPermanentPincode() != null ? applicantDetail.getPermanentPincode().toString() : null);
                permanentAddress.setPremiseNumber(applicantDetail.getPermanentPremiseNumberName());
                permanentAddress.setStreetName(applicantDetail.getPermanentStreetName());
                carLoanResponse.setPermanentAddress(permanentAddress);

                //get list of Pan Card
                DMSClient dmsClient = new DMSClient(environment.getProperty(DMS_URL));
                DocumentRequest documentRequestPanCard = new DocumentRequest();
                documentRequestPanCard.setApplicationId(toApplicationId);
                documentRequestPanCard.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
                documentRequestPanCard.setProductDocumentMappingId(DocumentAlias.APPLICANT_SCANNED_COPY_OF_PAN_CARD);
                try {
                    DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequestPanCard);
                    profileViewPLResponse.setPanCardList(documentResponse.getDataList());
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

                //get list of Aadhar Card
                DocumentRequest documentRequestAadharCard = new DocumentRequest();
                documentRequestAadharCard.setApplicationId(toApplicationId);
                documentRequestAadharCard.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
                documentRequestAadharCard.setProductDocumentMappingId(DocumentAlias.APPLICANT_SCANNED_COPY_OF_AADHAR_CARD);
                try {
                    DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequestAadharCard);
                    profileViewPLResponse.setAadharCardList(documentResponse.getDataList());
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

                //get profile picture
                DocumentRequest applicantProfilePicture = new DocumentRequest();
                applicantProfilePicture.setApplicationId(toApplicationId);
                applicantProfilePicture.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
                applicantProfilePicture.setProductDocumentMappingId(DocumentAlias.CAR_LOAN_PROFIEL_PICTURE);
                try {
                    DocumentResponse documentResponse = dmsClient.listProductDocument(applicantProfilePicture);
                    carLoanResponse.setApplicantProfilePicture(documentResponse.getDataList());
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

                carLoanPrimaryViewResponse.setApplicant(profileViewPLResponse);
            } else {
                throw new Exception("No Data found");
            }
        } catch (Exception e) {
           // throw new Exception("Problem Occured while Fetching Retail Details");
        }

        //setting co-application details
        List<RetailProfileViewResponse> coApplicantResponse = null;
        try {
            coApplicantResponse = coApplicantService.getCoApplicantPLResponse(toApplicationId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        carLoanPrimaryViewResponse.setCoApplicantList(coApplicantResponse);

        //setting guarantor details
        List<RetailProfileViewResponse> guarantorResponse = null;
        try {
            guarantorResponse = guarantorService.getGuarantorServiceResponse(toApplicationId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        carLoanPrimaryViewResponse.setGuarantorList(guarantorResponse);

        //setting Personal Loan Specific Data
        try {
            PrimaryCarLoanDetailRequest primaryCarLoanDetailRequest = primaryCarLoanService.get(toApplicationId, userId);
            BeanUtils.copyProperties(primaryCarLoanDetailRequest, carLoanResponse);
            carLoanResponse.setDeliveryDate(primaryCarLoanDetailRequest.getDeliveryDate() != null ? DATE_FORMAT.format(primaryCarLoanDetailRequest.getDeliveryDate()) : null);
            carLoanResponse.setPurchasePreownedDate(primaryCarLoanDetailRequest.getPurchasePreownedDate()!=null?DATE_FORMAT.format(primaryCarLoanDetailRequest.getPurchasePreownedDate()):null);
            carLoanResponse.setPurchaseReimbursmentDate(primaryCarLoanDetailRequest.getPurchaseReimbursmentDate()!=null?DATE_FORMAT.format(primaryCarLoanDetailRequest.getPurchaseReimbursmentDate()):null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        carLoanPrimaryViewResponse.setCarLoanResponse(carLoanResponse);
        return carLoanPrimaryViewResponse;
    }
}
