package com.capitaworld.service.loans.service.teaser.primaryview.impl;

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
import com.capitaworld.service.oneform.enums.*;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Transactional
public class HomeLoanPrimaryViewServiceImpl implements HomeLoanPrimaryViewService {

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
		// applicant
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicantId);
		try {
			RetailApplicantDetail applicantDetail = applicantRepository
					.getByApplicationAndUserId(applicationMaster.getUserId(), applicantId);
			if (applicantDetail != null) {
				RetailProfileViewResponse profileViewHLResponse = new RetailProfileViewResponse();
				homeLoanResponse
						.setDateOfProposal(CommonUtils.getStringDateFromDate(applicantDetail.getCreatedDate()));
				if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getOccupationId())) {
					profileViewHLResponse.setNatureOfOccupationId(applicantDetail.getOccupationId());
					if (applicantDetail.getOccupationId() == 2) {
						profileViewHLResponse.setNatureOfOccupation(
								OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
						if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getCompanyName())) {
							profileViewHLResponse.setCompanyName(applicantDetail.getCompanyName());
						} else {
							profileViewHLResponse.setCompanyName("-");
						}
						if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getEmployedWithId())) {
							if (applicantDetail.getEmployedWithId() == 8) {
								profileViewHLResponse.setEmployeeWith(applicantDetail.getEmployedWithOther());
							} else {
								profileViewHLResponse.setEmployeeWith(
										EmployeeWith.getById(applicantDetail.getEmployedWithId()).getValue());
							}
						} else {
							profileViewHLResponse.setEmployeeWith("-");
						}
						profileViewHLResponse.setYearsInCurrentJob(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCurrentJobYear()) ?  applicantDetail.getCurrentJobYear().toString() : "-");
						profileViewHLResponse.setMonthsInCurrentJob(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCurrentJobMonth()) ?  applicantDetail.getCurrentJobMonth().toString() : "-");
						profileViewHLResponse.setTotalExperienceInMonths(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTotalExperienceMonth()) ?  applicantDetail.getTotalExperienceMonth().toString() : "-");
						profileViewHLResponse.setTotalExperienceInYears(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTotalExperienceYear()) ?  applicantDetail.getTotalExperienceYear().toString() : "-");
						profileViewHLResponse.setPreviousExperienceInMonths(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPreviousJobMonth()) ?  applicantDetail.getPreviousJobMonth().toString() : "-");
						profileViewHLResponse.setPreviousExperienceInYears(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPreviousJobYear()) ?  applicantDetail.getPreviousJobYear().toString() : "-");
						profileViewHLResponse.setPreviousEmployerName(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPreviousEmployersName()) ?  applicantDetail.getPreviousEmployersName() : "-");
						profileViewHLResponse.setPreviousEmployerAddress(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPreviousEmployersAddress()) ?  applicantDetail.getPreviousEmployersAddress() : "-");
					} else if (applicantDetail.getOccupationId() == 3 || applicantDetail.getOccupationId() == 4) {
						profileViewHLResponse.setNatureOfOccupation(
								OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
						if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getEntityName())) {
							profileViewHLResponse.setEntityName(applicantDetail.getEntityName());
						} else {
							profileViewHLResponse.setEntityName("-");
						}
						if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getIndustryTypeId())) {
							if (applicantDetail.getIndustryTypeId() == 16) {
								profileViewHLResponse.setIndustryType(applicantDetail.getIndustryTypeOther());
							} else {
								profileViewHLResponse.setIndustryType(
										IndustryType.getById(applicantDetail.getIndustryTypeId()).getValue());
							}
						} else {
							profileViewHLResponse.setIndustryType("-");
						}
						 	profileViewHLResponse.setAnnualTurnover(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getAnnualTurnover()) ? CommonUtils.CurrencyFormat( applicantDetail.getAnnualTurnover().toString()) : "-");
							profileViewHLResponse.setMonthlyLoanObligation(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getMonthlyLoanObligation()) ? CommonUtils.CurrencyFormat( applicantDetail.getMonthlyLoanObligation().toString()): "-");
							profileViewHLResponse.setPatPreviousYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPatPreviousYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getPatPreviousYear().toString()): "-");
							profileViewHLResponse.setPatCurrentYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPatCurrentYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getPatCurrentYear().toString()): "-");
							profileViewHLResponse.setDepreciationPreviousYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getDepreciationPreviousYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getDepreciationPreviousYear().toString()): "-");
							profileViewHLResponse.setDepreciationCurrentYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getDepreciationCurrentYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getDepreciationCurrentYear().toString()): "-");
							profileViewHLResponse.setRemunerationPreviousYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRemunerationPreviousYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getRemunerationPreviousYear().toString()): "-");
							profileViewHLResponse.setRemunerationCurrentYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRemunerationCurrentYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getRemunerationCurrentYear().toString()): "-");
							profileViewHLResponse.setBusinessExperience(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getBusinessStartDate()) ? CommonUtils.calculateBusinessExperience(applicantDetail.getBusinessStartDate()) : "-");
							
					} else if (applicantDetail.getOccupationId() == 5) {
						profileViewHLResponse.setNatureOfOccupation(
								OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
						if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getSelfEmployedOccupationId())) {
							if (applicantDetail.getSelfEmployedOccupationId() == 10) {
								profileViewHLResponse.setOccupation(applicantDetail.getSelfEmployedOccupationOther());
							} else {
								profileViewHLResponse.setOccupation(
										Occupation.getById(applicantDetail.getSelfEmployedOccupationId()).getValue());
							}
						} else {
							profileViewHLResponse.setOccupation("-");
						}
						 profileViewHLResponse.setAnnualTurnover(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getAnnualTurnover()) ? CommonUtils.CurrencyFormat( applicantDetail.getAnnualTurnover().toString()) : "-");
							profileViewHLResponse.setMonthlyLoanObligation(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getMonthlyLoanObligation()) ? CommonUtils.CurrencyFormat( applicantDetail.getMonthlyLoanObligation().toString()): "-");
							profileViewHLResponse.setPatPreviousYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPatPreviousYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getPatPreviousYear().toString()): "-");
							profileViewHLResponse.setPatCurrentYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPatCurrentYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getPatCurrentYear().toString()): "-");
							profileViewHLResponse.setDepreciationPreviousYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getDepreciationPreviousYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getDepreciationPreviousYear().toString()): "-");
							profileViewHLResponse.setDepreciationCurrentYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getDepreciationCurrentYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getDepreciationCurrentYear().toString()): "-");
							profileViewHLResponse.setRemunerationPreviousYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRemunerationPreviousYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getRemunerationPreviousYear().toString()): "-");
							profileViewHLResponse.setRemunerationCurrentYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRemunerationCurrentYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getRemunerationCurrentYear().toString()): "-");
							profileViewHLResponse.setBusinessExperience(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getBusinessStartDate()) ? CommonUtils.calculateBusinessExperience(applicantDetail.getBusinessStartDate()) : "-");
							
					} else if (applicantDetail.getOccupationId() == 6) {
						profileViewHLResponse.setNatureOfOccupation(
								OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
						if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getLandSize())) {
							profileViewHLResponse
									.setLandSize(LandSize.getById(applicantDetail.getLandSize().intValue()).getValue());
						} else {
							profileViewHLResponse.setLandSize("-");
						}
						if (!CommonUtil.isObjectNullOrEmpty(applicantDetail.getAlliedActivityId())) {
							profileViewHLResponse.setAlliedActivity(
									AlliedActivity.getById(applicantDetail.getAlliedActivityId()).getValue());
						} else {
							profileViewHLResponse.setAlliedActivity("-");
						}
						 profileViewHLResponse.setAnnualTurnover(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getAnnualTurnover()) ? CommonUtils.CurrencyFormat( applicantDetail.getAnnualTurnover().toString()) : "-");
							profileViewHLResponse.setMonthlyLoanObligation(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getMonthlyLoanObligation()) ? CommonUtils.CurrencyFormat( applicantDetail.getMonthlyLoanObligation().toString()): "-");
							profileViewHLResponse.setPatPreviousYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPatPreviousYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getPatPreviousYear().toString()): "-");
							profileViewHLResponse.setPatCurrentYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPatCurrentYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getPatCurrentYear().toString()): "-");
							profileViewHLResponse.setDepreciationPreviousYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getDepreciationPreviousYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getDepreciationPreviousYear().toString()): "-");
							profileViewHLResponse.setDepreciationCurrentYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getDepreciationCurrentYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getDepreciationCurrentYear().toString()): "-");
							profileViewHLResponse.setRemunerationPreviousYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRemunerationPreviousYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getRemunerationPreviousYear().toString()): "-");
							profileViewHLResponse.setRemunerationCurrentYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRemunerationCurrentYear()) ? CommonUtils.CurrencyFormat( applicantDetail.getRemunerationCurrentYear().toString()): "-");
							profileViewHLResponse.setBusinessExperience(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getBusinessStartDate()) ? CommonUtils.calculateBusinessExperience(applicantDetail.getBusinessStartDate()) : "-");
							
					} else if (applicantDetail.getOccupationId() == 7) {
						profileViewHLResponse.setNatureOfOccupation(
								OccupationNature.getById(applicantDetail.getOccupationId()).getValue());
					}
				} else {
					profileViewHLResponse.setNatureOfOccupation("-");
				}
				profileViewHLResponse.setFirstName((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getFirstName())
						? applicantDetail.getFirstName() : null));
				profileViewHLResponse.setMiddleName((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getMiddleName())
						? applicantDetail.getMiddleName() : null));
				profileViewHLResponse.setLastName((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getLastName())
						? applicantDetail.getLastName() : null));
				profileViewHLResponse.setGender((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getGenderId())
						? Gender.getById(applicantDetail.getGenderId()).getValue() : "-"));
				profileViewHLResponse.setMaritalStatus((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getStatusId())
						? MaritalStatus.getById(applicantDetail.getStatusId()).getValue() : "-"));
				profileViewHLResponse.setMonthlyIncome(
						(!CommonUtils.isObjectNullOrEmpty(String.valueOf(applicantDetail.getMonthlyIncome()))
								? String.format("%.2f", applicantDetail.getMonthlyIncome()) : "0"));
				profileViewHLResponse.setOtherIncome((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getOtherIncome()) ? applicantDetail.getOtherIncome().toString() : ""));
				profileViewHLResponse.setOtherInvestment((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getOtherInvestment()) ? applicantDetail.getOtherInvestment().toString() : ""));
				profileViewHLResponse.setTaxPaid((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTaxPaidLastYear()) ? applicantDetail.getTaxPaidLastYear().toString() : ""));
				profileViewHLResponse.setBonusPerAnnum((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getBonusPerAnnum()) ? applicantDetail.getBonusPerAnnum().toString() : ""));
				profileViewHLResponse.setIncentivePerAnnum((!CommonUtils.isObjectNullOrEmpty(applicantDetail.getIncentivePerAnnum()) ? applicantDetail.getIncentivePerAnnum().toString() : ""));
				
				// set office address
				AddressResponse officeAddress = new AddressResponse();
				try {
					List<Long> officeCity = new ArrayList<Long>(1);
					if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getOfficeCityId())) {
						officeCity.add(applicantDetail.getOfficeCityId());
						OneFormResponse formResponse = oneFormClient.getCityByCityListId(officeCity);
						MasterResponse data = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) formResponse.getListData().get(0),
								MasterResponse.class);
						if (!CommonUtils.isObjectNullOrEmpty(data)) {
							officeAddress.setCity(data.getValue());
						} else {
							officeAddress.setCity("-");
						}
					} else {
						officeAddress.setCity("-");
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
						MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) country.getListData().get(0), MasterResponse.class);
						if (!CommonUtils.isObjectNullOrEmpty(dataCountry.getValue())) {
							officeAddress.setCountry(dataCountry.getValue());
						} else {
							officeAddress.setCountry("-");
						}
					} else {
						officeAddress.setCountry("-");
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
						MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) state.getListData().get(0), MasterResponse.class);
						if (!CommonUtil.isObjectNullOrEmpty(dataState)) {
							officeAddress.setState(dataState.getValue());
						} else {
							officeAddress.setState("-");
						}
					} else {
						officeAddress.setState("-");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				officeAddress.setLandMark(applicantDetail.getOfficeLandMark());
				officeAddress.setPincode(applicantDetail.getOfficePincode() != null
						? applicantDetail.getOfficePincode().toString() : null);
				officeAddress.setPremiseNumber(applicantDetail.getOfficePremiseNumberName());
				officeAddress.setStreetName(applicantDetail.getOfficeStreetName());
				homeLoanResponse.setOfficeAddress(officeAddress);
				profileViewHLResponse.setFirstAddress(officeAddress);

				// set permanent address
				AddressResponse permanentAddress = new AddressResponse();
				try {
					List<Long> permanentCity = new ArrayList<Long>(1);
					if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPermanentCityId())) {
						permanentCity.add(applicantDetail.getPermanentCityId());
						OneFormResponse formResponsePermanentCity = oneFormClient.getCityByCityListId(permanentCity);
						MasterResponse dataCity = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) formResponsePermanentCity.getListData().get(0),
								MasterResponse.class);
						if (!CommonUtils.isObjectNullOrEmpty(dataCity)) {
							permanentAddress.setCity(dataCity.getValue());
						} else {
							permanentAddress.setCity("-");
						}
					} else {
						permanentAddress.setCity("-");
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
						MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) countryPermanent.getListData().get(0),
								MasterResponse.class);
						if (!CommonUtils.isObjectNullOrEmpty(dataCountry)) {
							permanentAddress.setCountry(dataCountry.getValue());
						} else {
							permanentAddress.setCountry("-");
						}
					} else {
						permanentAddress.setCountry("-");
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
						MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) statePermanent.getListData().get(0),
								MasterResponse.class);
						if (!CommonUtils.isObjectNullOrEmpty(dataState)) {
							permanentAddress.setState(dataState.getValue());
						} else {
							permanentAddress.setCountry("-");
						}
					} else {
						permanentAddress.setCountry("-");
					}
				} catch (Exception e) {

				}
				permanentAddress.setLandMark(applicantDetail.getPermanentLandMark());
				permanentAddress.setPincode(applicantDetail.getPermanentPincode() != null
						? applicantDetail.getPermanentPincode().toString() : null);
				permanentAddress.setPremiseNumber(applicantDetail.getPermanentPremiseNumberName());
				permanentAddress.setStreetName(applicantDetail.getPermanentStreetName());
				homeLoanResponse.setPermanentAddress(permanentAddress);
				
				profileViewHLResponse.setContactNo(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getContactNo()) ?  applicantDetail.getContactNo() : "-");
				profileViewHLResponse.setTitle(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTitleId()) ? Title.getById(applicantDetail.getTitleId()).getValue() : null);
				profileViewHLResponse.setAge(applicantDetail.getBirthDate() != null ? CommonUtils.getAgeFromBirthDate(applicantDetail.getBirthDate()).toString() : null);

				homeLoanResponse.setCurrency(applicantDetail.getCurrencyId() != null
						? Currency.getById(applicantDetail.getCurrencyId()).getValue() : "-");

				profileViewHLResponse.setEntityName(applicantDetail.getEntityName());

				// set pan
				profileViewHLResponse.setPan(applicantDetail.getPan().toUpperCase());

				// applicant profile image
				try {
					homeLoanResponse.setProfileImage(documentManagementService.getDocumentDetails(applicantId,
							DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.HOME_LOAN_PROFIEL_PICTURE));
				} catch (DocumentException e) {
					e.printStackTrace();
				}

				// get list of Pan Card
				try {
					profileViewHLResponse.setPanCardList(
							documentManagementService.getDocumentDetails(applicantId, DocumentAlias.UERT_TYPE_APPLICANT,
									DocumentAlias.HOME_LOAN_APPLICANT_SCANNED_COPY_OF_PAN_CARD));
				} catch (DocumentException e) {
					e.printStackTrace();
				}

				// get list of Aadhar Card
				try {
					profileViewHLResponse.setAadharCardList(
							documentManagementService.getDocumentDetails(applicantId, DocumentAlias.UERT_TYPE_APPLICANT,
									DocumentAlias.HOME_LOAN_APPLICANT_SCANNED_COPY_OF_AADHAR_CARD));
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

		// set up loan specific details
		PrimaryHomeLoanDetail loanDetail = primaryHomeLoanRepository.getByApplicationAndUserId(applicantId, applicationMaster.getUserId());
		if (!CommonUtils.isObjectNullOrEmpty(loanDetail)) {

			if (!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyType())) {
				homeLoanResponse.setPropertyType(PropertySubType.getById(loanDetail.getPropertyType()).getValue());
				homeLoanResponse.setPropertyUsedTypeId(loanDetail.getPropertyType().toString());
				if (!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyType()) && loanDetail.getPropertyType() == 3) {
					homeLoanResponse.setPropertyUsedType(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyUsedType()) ? PropertyUsedType.getById(loanDetail.getPropertyUsedType()).getValue() : "-");
					if (!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyUsedType()) && loanDetail.getPropertyUsedType() == 3) {
						homeLoanResponse.setConstructionCompleted(!CommonUtils.isObjectNullOrEmpty(loanDetail.getIsConstructionCompleted()) ? Options.getById((loanDetail.getIsConstructionCompleted() ? 1 : 0)).getValue() : "-");
						if (!CommonUtils.isObjectNullOrEmpty(loanDetail.getIsConstructionCompleted()) && !loanDetail.getIsConstructionCompleted()) {
							homeLoanResponse.setCompletionTimeInMonth(!CommonUtils.isObjectNullOrEmpty(loanDetail.getCompletionTimeInMonth()) ? loanDetail.getCompletionTimeInMonth().toString() : "-");
							homeLoanResponse.setCompletionTimeInYear(!CommonUtils.isObjectNullOrEmpty(loanDetail.getCompletionTimeInYear()) ? loanDetail.getCompletionTimeInYear().toString() : "-");
						}
					}
					homeLoanResponse.setProjectName(loanDetail.getProjectName());
					homeLoanResponse.setProjectCity(loanDetail.getProjectCity());
					homeLoanResponse.setArea(!CommonUtils.isObjectNullOrEmpty(loanDetail.getArea()) ? loanDetail.getArea().toString() : "-");
					homeLoanResponse.setPropertyPrice(loanDetail.getPropertyPrice().toString());
				} else if (!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyType()) && loanDetail.getPropertyType() == 4) {
					homeLoanResponse.setBunglowCost(!CommonUtils.isObjectNullOrEmpty(loanDetail.getBunglowCost()) ? loanDetail.getBunglowCost().toString() : "-");
					homeLoanResponse.setConstructionCost(!CommonUtils.isObjectNullOrEmpty(loanDetail.getConstructionCost()) ? loanDetail.getConstructionCost().toString() : "-");
					homeLoanResponse.setConstructionCompletionTimeInMonth(!CommonUtils.isObjectNullOrEmpty(loanDetail.getConstructionCompletionTimeInMonth()) ? loanDetail.getConstructionCompletionTimeInMonth().toString() : "-");
					homeLoanResponse.setConstructionCompletionTimeInYear(!CommonUtils.isObjectNullOrEmpty(loanDetail.getConstructionCompletionTimeInYear()) ? loanDetail.getConstructionCompletionTimeInYear().toString() : "-");
				} else if (!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyType()) && loanDetail.getPropertyType() == 5) {
					homeLoanResponse.setRenovationType(!CommonUtils.isObjectNullOrEmpty(loanDetail.getRenovationType()) ? RepairType.getById(loanDetail.getRenovationType()).getValue() : "-");
					if (!CommonUtils.isObjectNullOrEmpty(loanDetail.getRenovationType()) && loanDetail.getRenovationType() == 8) {
						homeLoanResponse.setRenovationTypeOther(loanDetail.getOtherRenovationType());
					}
					homeLoanResponse.setRenovationCost(loanDetail.getRenovationCost().toString());
					homeLoanResponse.setRenovationCompletionTimeInMonth(!CommonUtils.isObjectNullOrEmpty(loanDetail.getRenovationCompletionTimeInMonth()) ? loanDetail.getRenovationCompletionTimeInMonth().toString() : "-");
					homeLoanResponse.setRenovationCompletionTimeInYear(!CommonUtils.isObjectNullOrEmpty(loanDetail.getRenovationCompletionTimeInYear()) ? loanDetail.getRenovationCompletionTimeInYear().toString() : "-");
					if (!CommonUtils.isObjectNullOrEmpty(loanDetail.getIsLoanTaken())) {
						if (loanDetail.getIsLoanTaken()) {
							homeLoanResponse.setLoanTaken(!CommonUtils.isObjectNullOrEmpty(loanDetail.getIsLoanTaken()) ? Options.getById(loanDetail.getIsLoanTaken() ? 1 : 0).getValue() : "-");
							homeLoanResponse.setDateOfLoanTaken(!CommonUtils.isObjectNullOrEmpty(loanDetail.getDateOfLoanTaken()) ? CommonUtils.getStringDateFromDate(loanDetail.getDateOfLoanTaken()) : "-");
						} else {
							homeLoanResponse.setLoanTaken(!CommonUtils.isObjectNullOrEmpty(loanDetail.getIsLoanTaken()) ? Options.getById(loanDetail.getIsLoanTaken() ? 1 : 0).getValue() : "-");
						}
					}

				} else if (!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyType()) && loanDetail.getPropertyType() == 6) {
					homeLoanResponse.setLandPlotCost(!CommonUtils.isObjectNullOrEmpty(loanDetail.getLandPlotCost()) ? loanDetail.getLandPlotCost().toString() : "-");
					homeLoanResponse.setLandArea(!CommonUtils.isObjectNullOrEmpty(loanDetail.getLandArea()) ? loanDetail.getLandArea().toString() : "-");
				}
			}

			homeLoanResponse.setDownPayment(!CommonUtils.isObjectNullOrEmpty(loanDetail.getDownPayment()) ? loanDetail.getDownPayment().toString() : "-");
			homeLoanResponse.setLoanAmount(!CommonUtils.isObjectNullOrEmpty(loanDetail.getAmount()) ? loanDetail.getAmount().toString() : "-");
			homeLoanResponse.setTenure(!CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? String.valueOf(loanDetail.getTenure() / 12) : "-");
		}
		homeLoanResponse.setLoanType(LoanType.getById(applicationMaster.getProductId()).getValue());
		
		homeLoanResponse.setPropertyUse(!CommonUtils.isObjectNullOrEmpty(loanDetail.getPropertyUsed()) ? PropertyUsedSubType.getById(loanDetail.getPropertyUsed()).getValue() : null);
		homeLoanResponse.setRentalIncome(!CommonUtils.isObjectNullOrEmpty(loanDetail.getEstimatedRentalIncome()) ? loanDetail.getEstimatedRentalIncome().toString() : null);
		homeLoanPrimaryViewResponse.setHomeLoanResponse(homeLoanResponse);

		// setting co-application details
		List<RetailProfileViewResponse> coApplicantResponse = null;
		try {
			coApplicantResponse = coApplicantService.getCoApplicantPLResponse(applicantId,
					applicationMaster.getUserId(), applicationMaster.getProductId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		homeLoanPrimaryViewResponse.setCoApplicantResponse(coApplicantResponse);

		// setting guarantor details
		List<RetailProfileViewResponse> garantorResponse = null;
		try {
			garantorResponse = guarantorService.getGuarantorServiceResponse(applicantId, applicationMaster.getUserId(),
					applicationMaster.getProductId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		homeLoanPrimaryViewResponse.setGarantorResponse(garantorResponse);

		return homeLoanPrimaryViewResponse;
	}

}
