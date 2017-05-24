package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryPersonalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.AddressResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.PersonalLoanResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryPersonalLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.service.teaser.primaryview.PersonalLoansViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.CityByCityListIdClient;
import com.capitaworld.service.oneform.client.CountryByCountryListIdClient;
import com.capitaworld.service.oneform.client.StateListByStateListIdClient;
import com.capitaworld.service.oneform.enums.*;
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

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class PersonalLoansViewServiceImpl implements PersonalLoansViewService {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonalLoansViewServiceImpl.class);

	@Autowired
	private RetailApplicantDetailRepository applicantRepository;

	@Autowired
	RetailApplicantService retailApplicantService;
	
	@Autowired
	CoApplicantService coApplicantService;

	@Autowired
	GuarantorService guarantorService;

	@Autowired
	private PrimaryPersonalLoanDetailRepository personalLoanDetailRepository;

	@Autowired
	Environment environment;

	protected static final String DMS_URL = "dmsURL";

	@Override
	public RetailPrimaryViewResponse getPersonalLoansPrimaryViewDetails(Long applicantId, Long userId) throws Exception {
		RetailPrimaryViewResponse retailPrimaryViewResponse = new RetailPrimaryViewResponse();
		PersonalLoanResponse personalLoanResponse = new PersonalLoanResponse();
		//applicant
		try {
			RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(userId, applicantId);
			if (applicantDetail != null) {
				RetailProfileViewResponse profileViewPLResponse = new RetailProfileViewResponse();
				profileViewPLResponse.setCompanyName(applicantDetail.getCompanyName());
				personalLoanResponse.setDateOfProposal(CommonUtils.getStringDateFromDate(applicantDetail.getModifiedDate()));
				try {
					if (applicantDetail.getEmployedWithId() != 8) {
						profileViewPLResponse.setEmployeeWith(EmployeeWith.getById(applicantDetail.getEmployedWithId()).getValue());
					} else {
						profileViewPLResponse.setEmployeeWith(applicantDetail.getEmployedWithOther());
					}
				} catch (Exception e) {

				}
				profileViewPLResponse.setFirstName(applicantDetail.getFirstName());
				try {
					profileViewPLResponse.setGender(Gender.getById(applicantDetail.getGenderId()).getValue());
				} catch (Exception e) {
				}
				profileViewPLResponse.setLastName(applicantDetail.getLastName());
				profileViewPLResponse.setMaritalStatus(applicantDetail.getStatusId() != null ? MaritalStatus.getById(applicantDetail.getStatusId()).getValue() : null);
				profileViewPLResponse.setMiddleName(applicantDetail.getMiddleName());
				profileViewPLResponse.setMonthlyIncome(String.valueOf(applicantDetail.getMonthlyIncome() != null ? applicantDetail.getMonthlyIncome() : 0));
				profileViewPLResponse.setNatureOfOccupation(OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
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
				personalLoanResponse.setOfficeAddress(officeAddress);

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
					if (applicantDetail.getOfficeCountryId() != null) {
						permanentCountryLong = Long.valueOf(applicantDetail.getPermanentCountryId().toString());

						permanentCountry.add(permanentCountryLong);
						OneFormResponse countryPermanent = countryByCountryListIdClient.send(permanentCountry);
						MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) countryPermanent.getListData().get(0), MasterResponse.class);
						officeAddress.setCountry(dataCountry.getValue());
					}
				} catch (Exception e) {

				}
				try {
					List<Long> permanentState = new ArrayList<Long>(1);

					Long permanentStateLong = null;
					if (applicantDetail.getOfficeCountryId() != null) {
						permanentStateLong = Long.valueOf(applicantDetail.getPermanentStateId().toString());

						permanentState.add(permanentStateLong);
						OneFormResponse statePermanent = stateListByStateListIdClient.send(permanentState);
						MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) statePermanent.getListData().get(0), MasterResponse.class);
						officeAddress.setState(dataState.getValue());

					}
				} catch (Exception e) {

				}
				permanentAddress.setLandMark(applicantDetail.getPermanentLandMark());
				permanentAddress.setPincode(applicantDetail.getPermanentPincode() != null ? applicantDetail.getPermanentPincode().toString() : null);
				permanentAddress.setPremiseNumber(applicantDetail.getPermanentPremiseNumberName());
				permanentAddress.setStreetName(applicantDetail.getPermanentStreetName());
				personalLoanResponse.setPermanentAddress(permanentAddress);


				profileViewPLResponse.setTitle(Title.getById(applicantDetail.getTitleId()).getValue());
				profileViewPLResponse.setAge(applicantDetail.getBirthDate() != null ? CommonUtils.getAgeFromBirthDate(applicantDetail.getBirthDate()).toString() : null);

				if (applicantDetail.getApplicationId() != null) {
					personalLoanResponse.setTenure(applicantDetail.getApplicationId().getTenure() != null ? applicantDetail.getApplicationId().getTenure().toString() : null);
					personalLoanResponse.setLoanType(applicantDetail.getApplicationId().getProductId() != null ? LoanType.getById(applicantDetail.getApplicationId().getProductId()).getValue() : null);
					personalLoanResponse.setLoanAmount(applicantDetail.getApplicationId().getAmount() != null ? applicantDetail.getApplicationId().getAmount().toString() : null);
					profileViewPLResponse.setCurrency(applicantDetail.getApplicationId().getCurrencyId() != null ? Currency.getById(applicantDetail.getApplicationId().getCurrencyId()).getValue() : null);
				}


				profileViewPLResponse.setEntityName(applicantDetail.getEntityName());
				if (applicantDetail.getIndustryTypeId() != null && applicantDetail.getIndustryTypeId() != 16) {
					profileViewPLResponse.setIndustryType(IndustryType.getById(applicantDetail.getIndustryTypeId()).getValue());
				} else {
					profileViewPLResponse.setIndustryType(applicantDetail.getIndustryTypeOther());
				}

				//set pan car
				profileViewPLResponse.setPan(applicantDetail.getPan());

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
				retailPrimaryViewResponse.setPersonalProfileRespoonse(profileViewPLResponse);
			} else {
				throw new Exception("No Data found");
			}
		} catch (Exception e) {
			throw new Exception("Problem Occured while Fetching Retail Details");
		}

		//set up loan specific details
		PrimaryPersonalLoanDetail loanDetail = personalLoanDetailRepository.getByApplicationAndUserId(applicantId, userId);
		if (loanDetail.getLoanPurpose() != 7 && loanDetail.getLoanPurpose() != null) {
			personalLoanResponse.setPurposeOfLoan(PersonalLoanPurpose.getById(Integer.valueOf(loanDetail.getLoanPurpose().toString())).getValue());
		} else {
			personalLoanResponse.setPurposeOfLoan(loanDetail.getLoanPurposeOther());
		}

		//setting co-application details
		List<RetailProfileViewResponse> coApplicantResponse = coApplicantService.getCoApplicantPLResponse(applicantId, userId);
		retailPrimaryViewResponse.setCoApplicantResponse(coApplicantResponse);

		//setting guarantor details
		List<RetailProfileViewResponse> garantorResponse = guarantorService.getGuarantorServiceResponse(applicantId, userId);
		retailPrimaryViewResponse.setGarantorResponse(garantorResponse);

		//setting Personal Loan Specific Data
		retailPrimaryViewResponse.setPersonalLoanResponse(personalLoanResponse);

		return retailPrimaryViewResponse;
	}

}
