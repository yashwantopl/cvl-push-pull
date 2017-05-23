package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryPersonalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.AddressResponse;
import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.retail.GuarantorRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;
import com.capitaworld.service.loans.model.teaser.primaryview.ProfileViewPLResponse;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryPersonalLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryPersonalLoanService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.CityByCityListIdClient;
import com.capitaworld.service.oneform.client.CountryByCountryListIdClient;
import com.capitaworld.service.oneform.client.StateListByStateListIdClient;
import com.capitaworld.service.oneform.enums.EmployeeWith;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.MaritalStatus;
import com.capitaworld.service.oneform.enums.OccupationNature;
import com.capitaworld.service.oneform.enums.PersonalLoanPurpose;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
@Transactional
public class RetailApplicantServiceImpl implements RetailApplicantService {

	private static final Logger logger = LoggerFactory.getLogger(RetailApplicantServiceImpl.class.getName());
	
	protected static final String DMS_URL = "dmsURL";

	@Autowired
	private RetailApplicantDetailRepository applicantRepository;

	@Autowired
	private CoApplicantService coApplicantService;

	@Autowired
	private GuarantorService guarantorService;
	
	@Autowired
	private PrimaryPersonalLoanService primaryPersonalLoanService;
	
	@Autowired
	private PrimaryPersonalLoanDetailRepository personalLoanDetailRepository;
	
	@Autowired
	Environment environment; 

	@Override
	public boolean save(RetailApplicantRequest applicantRequest, Long userId) throws Exception {

		try {
			RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId((CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId : applicantRequest.getClientId()),
					applicantRequest.getApplicationId());
			if (applicantDetail != null) {
				// throw new NullPointerException(
				// "Applicant ID and ID(Primary Key) does not match with the
				// database==> Applicant ID==>"
				// + applicantRequest.getApplicationId() + "ID==>" +
				// applicantRequest.getId());

				applicantDetail.setModifiedBy(userId);
				applicantDetail.setModifiedDate(new Date());
			} else {
				applicantDetail = new RetailApplicantDetail();
				applicantDetail.setCreatedBy(userId);
				applicantDetail.setCreatedDate(new Date());
				applicantDetail.setActive(true);
				applicantDetail.setApplicationId(new LoanApplicationMaster(applicantRequest.getApplicationId()));
			}

			BeanUtils.copyProperties(applicantRequest, applicantDetail, CommonUtils.IgnorableCopy.RETAIL_FINAL);
			copyAddressFromRequestToDomain(applicantRequest, applicantDetail);
			if (applicantRequest.getDate() != null && applicantRequest.getMonth() != null
					&& applicantRequest.getYear() != null) {
				Date birthDate = CommonUtils.getDateByDateMonthYear(applicantRequest.getDate(),
						applicantRequest.getMonth(), applicantRequest.getYear());
				applicantDetail.setBirthDate(birthDate);
			}
			applicantDetail = applicantRepository.save(applicantDetail);
			for (CoApplicantRequest request : applicantRequest.getCoApplicants()) {
				coApplicantService.save(request, applicantRequest.getApplicationId(), userId);
			}
			for (GuarantorRequest request : applicantRequest.getGuarantors()) {
				guarantorService.save(request, applicantRequest.getApplicationId(), userId);
			}
			return true;

		} catch (Exception e) {
			logger.error("Error while Saving Retail Profile:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	@Override
	public RetailApplicantRequest get(Long userId, Long applicationId) throws Exception {
		try {
			RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(userId,
					applicationId);
			if (applicantDetail == null) {
				// throw new NullPointerException("RetailApplicantDetail Record
				// not exists in DB of Application ID : "
				// + applicationId + " and User Id==>" + userId);
				return null;
			}
			RetailApplicantRequest applicantRequest = new RetailApplicantRequest();
			BeanUtils.copyProperties(applicantDetail, applicantRequest);
			copyAddressFromDomainToRequest(applicantDetail, applicantRequest);
			applicantRequest.setCoApplicants(coApplicantService.getList(applicationId, userId));
			applicantRequest.setGuarantors(guarantorService.getList(applicationId, userId));
			Integer[] saperatedTime = CommonUtils.saperateDayMonthYearFromDate(applicantDetail.getBirthDate());
			applicantRequest.setDate(saperatedTime[0]);
			applicantRequest.setMonth(saperatedTime[1]);
			applicantRequest.setYear(saperatedTime[2]);
			return applicantRequest;
		} catch (Exception e) {
			logger.error("Error while Saving Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public FinalCommonRetailRequest getFinal(Long id, Long applicationId) throws Exception {
		try {
			RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(id, applicationId);
			if (applicantDetail == null) {
				throw new NullPointerException("RetailApplicantDetail Record of Final Portion not exists in DB of ID : "
						+ id + "  ApplicationId==>" + applicationId);
			}
			FinalCommonRetailRequest applicantRequest = new FinalCommonRetailRequest();
			BeanUtils.copyProperties(applicantDetail, applicantRequest, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
			return applicantRequest;
		} catch (Exception e) {
			logger.error("Error while Saving Retail Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public boolean saveFinal(FinalCommonRetailRequest applicantRequest, Long userId) throws Exception {
		try {
			if (applicantRequest.getApplicationId() == null) {
				throw new NullPointerException("Application Id and ID(Primary Key) must not be null=>Application ID==>"
						+ applicantRequest.getApplicationId() + " User Id (Primary Key)==>" + userId);
			}

			RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(userId,
					applicantRequest.getApplicationId());
			if (applicantDetail == null) {
				throw new NullPointerException(
						"Applicant ID and ID(Primary Key) does not match with the database==> Applicant ID==>"
								+ applicantRequest.getApplicationId() + "User ID==>" + userId);
			}
			applicantDetail.setModifiedBy(userId);
			applicantDetail.setModifiedDate(new Date());
			BeanUtils.copyProperties(applicantRequest, applicantDetail, CommonUtils.IgnorableCopy.RETAIL_PROFILE);
			applicantRepository.save(applicantDetail);
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Retail Profile:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	@Override
	public List<CoApplicantRequest> getCoApplicants(Long userId, Long applicationId) throws Exception {
		// TODO Auto-generated method stub
		return coApplicantService.getList(applicationId, userId);
	}

	@Override
	public List<GuarantorRequest> getGuarantors(Long userId, Long applicationId) throws Exception {
		return guarantorService.getList(applicationId, userId);
	}

	public static void copyAddressFromRequestToDomain(RetailApplicantRequest from, RetailApplicantDetail to) {
		if (from.getFirstAddress() != null) {
			to.setPermanentPremiseNumberName(from.getFirstAddress().getPremiseNumber());
			to.setPermanentStreetName(from.getFirstAddress().getStreetName());
			to.setPermanentLandMark(from.getFirstAddress().getLandMark());
			to.setPermanentCityId(from.getFirstAddress().getCityId());
			to.setPermanentStateId(from.getFirstAddress().getStateId());
			to.setPermanentCountryId(from.getFirstAddress().getCountryId());
			to.setPermanentPincode(from.getFirstAddress().getPincode());
		}

		if (from.isAddressSameAs()) {
			if (from.getFirstAddress() != null) {
				to.setOfficePremiseNumberName(from.getFirstAddress().getPremiseNumber());
				to.setOfficeStreetName(from.getFirstAddress().getStreetName());
				to.setOfficeLandMark(from.getFirstAddress().getLandMark());
				to.setOfficeCityId(from.getFirstAddress().getCityId());
				to.setOfficeStateId(from.getFirstAddress().getStateId());
				to.setOfficeCountryId(from.getFirstAddress().getCountryId());
				to.setOfficePincode(from.getFirstAddress().getPincode());
			}
		} else {
			if (from.getSecondAddress() != null) {
				to.setOfficePremiseNumberName(from.getSecondAddress().getPremiseNumber());
				to.setOfficeStreetName(from.getSecondAddress().getStreetName());
				to.setOfficeLandMark(from.getSecondAddress().getLandMark());
				to.setOfficeCityId(from.getSecondAddress().getCityId());
				to.setOfficeStateId(from.getSecondAddress().getStateId());
				to.setOfficeCountryId(from.getSecondAddress().getCountryId());
				to.setOfficePincode(from.getSecondAddress().getPincode());
			}
		}

	}

	public static void copyAddressFromDomainToRequest(RetailApplicantDetail from, RetailApplicantRequest to) {
		Address address = new Address();
		address.setPremiseNumber(from.getPermanentPremiseNumberName());
		address.setLandMark(from.getPermanentLandMark());
		address.setStreetName(from.getPermanentStreetName());
		address.setCityId(from.getPermanentCityId());
		address.setStateId(from.getPermanentStateId());
		address.setCountryId(from.getPermanentCountryId());
		address.setPincode(from.getPermanentPincode());
		to.setFirstAddress(address);
		if (from.getAddressSameAs()) {
			to.setSecondAddress(address);
		} else {
			address = new Address();
			address.setPremiseNumber(from.getOfficePremiseNumberName());
			address.setLandMark(from.getOfficeLandMark());
			address.setStreetName(from.getOfficeStreetName());
			address.setCityId(from.getOfficeCityId());
			address.setStateId(from.getOfficeStateId());
			address.setCountryId(from.getOfficeCountryId());
			address.setPincode(from.getOfficePincode());
			to.setSecondAddress(address);
		}
	}

	@Override
	public ProfileViewPLResponse getProfileViewPLResponse(Long applicantId, Long userId) throws Exception {
		try{
		RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(userId, applicantId);
		if(applicantDetail!=null){
			ProfileViewPLResponse profileViewPLResponse = new ProfileViewPLResponse();
			
			profileViewPLResponse.setCompanyName(applicantDetail.getCompanyName());
			profileViewPLResponse.setDateOfProposal(CommonUtils.getStringDateFromDate(applicantDetail.getModifiedDate()));
			try{
			if(applicantDetail.getEmployedWithId()!=8){
			profileViewPLResponse.setEmployeeWith(EmployeeWith.getById(applicantDetail.getEmployedWithId()).getValue());
			}
			else{
				profileViewPLResponse.setEmployeeWith(applicantDetail.getEmployedWithOther());
			}
			}
			catch (Exception e) {
				
			}
			profileViewPLResponse.setFirstName(applicantDetail.getFirstName());
			try{
			profileViewPLResponse.setGender(Gender.getById(applicantDetail.getGenderId()).getValue());
			}
			catch (Exception e) {
			}
			profileViewPLResponse.setLastName(applicantDetail.getLastName());
			profileViewPLResponse.setMaritalStatus(applicantDetail.getStatusId()!=null?MaritalStatus.getById(applicantDetail.getStatusId()).getValue():null);
			profileViewPLResponse.setMiddleName(applicantDetail.getMiddleName());
			profileViewPLResponse.setMonthlyIncome(String.valueOf(applicantDetail.getMonthlyIncome()!=null?applicantDetail.getMonthlyIncome(): 0 ));
			profileViewPLResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
			AddressResponse officeAddress = new AddressResponse();
			
			CityByCityListIdClient cityByCityListIdClient = new CityByCityListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
			try{
			List<Long> officeCity = new ArrayList<Long>(1);
			officeCity.add(applicantDetail.getOfficeCityId());
			OneFormResponse formResponse = cityByCityListIdClient.send(officeCity);
			
			MasterResponse data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)formResponse.getListData().get(0), MasterResponse.class);
			officeAddress.setCity(data.getValue());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			CountryByCountryListIdClient countryByCountryListIdClient = new CountryByCountryListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
			try{
			List<Long> officeCountry = new ArrayList<Long>(1);
			Long officeCountryLong = null;
			if(applicantDetail.getOfficeCountryId()!=null){
				officeCountryLong = Long.valueOf(applicantDetail.getOfficeCountryId().toString());
			
			officeCountry.add(officeCountryLong);
			OneFormResponse country = countryByCountryListIdClient.send(officeCountry);
			MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)country.getListData().get(0), MasterResponse.class);
			officeAddress.setCountry(dataCountry.getValue());
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
			StateListByStateListIdClient stateListByStateListIdClient = new StateListByStateListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
			try{
			List<Long> officeState = new ArrayList<Long>(1);
			Long officeStateLong = null;
			if(applicantDetail.getOfficeCountryId()!=null){
				officeStateLong = Long.valueOf(applicantDetail.getOfficeStateId().toString());
			
			officeState.add(officeStateLong);
			OneFormResponse state = stateListByStateListIdClient.send(officeState);
			MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)state.getListData().get(0), MasterResponse.class);
			officeAddress.setState(dataState.getValue());
			}
			}
			catch (Exception e) {
				
			}
			officeAddress.setLandMark(applicantDetail.getOfficeLandMark());
			officeAddress.setPincode(applicantDetail.getOfficePincode().toString());
			officeAddress.setPremiseNumber(applicantDetail.getOfficePremiseNumberName());
			officeAddress.setStreetName(applicantDetail.getOfficeStreetName());
			profileViewPLResponse.setOfficeAddress(officeAddress);
			
			AddressResponse permanentAddress = new AddressResponse();
			try{
			List<Long> permanentCity = new ArrayList<Long>(1);
			permanentCity.add(applicantDetail.getPermanentCityId());
			OneFormResponse formResponsePermanentCity = cityByCityListIdClient.send(permanentCity);
			MasterResponse dataCity = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)formResponsePermanentCity.getListData().get(0), MasterResponse.class);
			permanentAddress.setCity(dataCity.getValue());
			}
			catch (Exception e) {
				
			}
			try{
			List<Long> permanentCountry = new ArrayList<Long>(1);
			Long permanentCountryLong = null;
			if(applicantDetail.getOfficeCountryId()!=null){
				permanentCountryLong = Long.valueOf(applicantDetail.getPermanentCountryId().toString());
			
			permanentCountry.add(permanentCountryLong);
			OneFormResponse countryPermanent = countryByCountryListIdClient.send(permanentCountry);
			MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)countryPermanent.getListData().get(0), MasterResponse.class);
			officeAddress.setCountry(dataCountry.getValue());
			}
			}
			catch (Exception e) {
				
			}
			try{
			List<Long> permanentState = new ArrayList<Long>(1);
			
			Long permanentStateLong = null;
			if(applicantDetail.getOfficeCountryId()!=null){
				permanentStateLong = Long.valueOf(applicantDetail.getPermanentStateId().toString());
			
			permanentState.add(permanentStateLong);
			OneFormResponse statePermanent = stateListByStateListIdClient.send(permanentState);
			MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)statePermanent.getListData().get(0), MasterResponse.class);
			officeAddress.setState(dataState.getValue());
			
			}
			}
			catch (Exception e) {
				
			}
			permanentAddress.setLandMark(applicantDetail.getPermanentLandMark());
			permanentAddress.setPincode(applicantDetail.getPermanentPincode().toString());
			permanentAddress.setPremiseNumber(applicantDetail.getPermanentPremiseNumberName());
			permanentAddress.setStreetName(applicantDetail.getPermanentStreetName());
			profileViewPLResponse.setPermanentAddress(permanentAddress);
			try{
			PrimaryPersonalLoanDetail loanDetail = personalLoanDetailRepository.getByApplicationAndUserId(applicantId, userId);
			
			if(loanDetail.getLoanPurpose()!=7 && loanDetail.getLoanPurpose()!=null){
				profileViewPLResponse.setPurposeOfLoan(PersonalLoanPurpose.getById(Integer.valueOf(loanDetail.getLoanPurpose().toString())).getValue());
			}
			else{
				profileViewPLResponse.setPurposeOfLoan(loanDetail.getLoanPurposeOther());
			}
			}
			catch (Exception e) {
			}
			
			
//			profileViewPLResponse.setPurposeOfLoan(.applicantDetail.getp);
			profileViewPLResponse.setTitle(Title.getById(applicantDetail.getTitleId()).getValue());
			profileViewPLResponse.setAge(applicantDetail.getBirthDate()!=null?CommonUtils.getAgeFromBirthDate(applicantDetail.getBirthDate()).toString():null);
			
			
			//get list of Pan Card
	        DMSClient dmsClient = new DMSClient(environment.getProperty(DMS_URL));
	        DocumentRequest documentRequestPanCard = new DocumentRequest();
	        documentRequestPanCard.setApplicationId(applicantId);
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
	        documentRequestAadharCard.setApplicationId(applicantId);
	        documentRequestAadharCard.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
	        documentRequestAadharCard.setProductDocumentMappingId(DocumentAlias.APPLICANT_SCANNED_COPY_OF_AADHAR_CARD);
	        try {
	            DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequestAadharCard);
	            profileViewPLResponse.setAadharCardList(documentResponse.getDataList());
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
			
			
			
			
		return profileViewPLResponse;
		}
		else{
			throw new Exception("No Data found");
		}
		}
		catch (Exception e) {
			throw new Exception("Problem Occured while Fetching Retail Details");
		}
	}
}
