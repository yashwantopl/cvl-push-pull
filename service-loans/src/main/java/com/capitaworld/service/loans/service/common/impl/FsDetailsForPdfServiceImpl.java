package com.capitaworld.service.loans.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.model.teaser.finalview.HomeLoanFinalViewResponse;
import com.capitaworld.service.loans.service.common.FsDetailsForPdfService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.service.teaser.finalview.HomeLoanFinalViewService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FsDetailsForPdfServiceImpl implements FsDetailsForPdfService {

	@Autowired
	private HomeLoanFinalViewService homeLoanFinalViewService;

	@Autowired
	private RetailApplicantService retailApplicantService; 
	
	
	@Override
	public Map getHomeLoanDetails(Long applicantId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		HomeLoanFinalViewResponse finalViewResponse = homeLoanFinalViewService.getHomeLoanFinalViewDetails(applicantId);
		map.put("purpose", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyType());
		map.put("loanAmount", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLoanAmount());
		map.put("tennure", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getTenure());
		map.put("name", finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getFirstName()
				+ finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getLastName());
		map.put("fatherName", finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getFatherFullName());
		map.put("panNo", finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getPan());
		 map.put("aadharNo",
		 finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getAadharNumber());
		 map.put("birthDate",
		 finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getBirthDate());
		map.put("age", finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getAge());
		map.put("gender", finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getGender());
		map.put("maritalStatus",
				finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getMaritalStatus());
		map.put("dependents", finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getNoOfDependents());
		map.put("cast", finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCaste());
		String presentAddress = "";
		presentAddress += finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress()
				.getPremiseNumber()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress()
						.getStreetName()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress()
						.getLandMark()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress()
						.getCity()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress()
						.getState()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress()
						.getCountry();
		map.put("presentAddress", presentAddress);
		map.put("presentAccommodation",
				finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getResidenceType());
		String permanentAddress = "";
		permanentAddress += finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress()
				.getPremiseNumber()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress()
						.getStreetName()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress()
						.getLandMark()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getCity()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getState()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress()
						.getCountry();
		map.put("permanentAddress", permanentAddress);
		//mobile and email
		//map.put("mobile", presentAddress);
		//map.put("email", presentAddress);
		//end mobile and email
		
		map.put("companyName",
				finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getCompanyName());
		
		map.put("occupation",
				finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOccupation());

		String officeAddress = "";
		officeAddress += finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse()
				.getCompanyName();
		map.put("officeAddress", officeAddress);

		map.put("designation",
				finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCurrentDesignation());
		map.put("yearsInPresentEmployment",
				finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getYearsInCurrentJob());
		map.put("grossAnnualIncome",
				finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getAnnualTurnover());

		// start co-applicant details
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().size(); i++) {
				int j=i+1;
				map.put("coappName" + j,
						finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i)
								.getFirstName()
								+ finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i)
										.getLastName());
				map.put("coappFatherName" + j, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails()
						.get(i).getFatherFullName());
				map.put("coappRelationshipWithApplicant" + j, finalViewResponse.getHomeLoanPrimaryViewResponse()
						.getCoApplicantResponse().get(i).getRelationshipWithApplicant());
				map.put("coappPanNo" + j,
						finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getPan());
				 map.put("aadharNo",
						 finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getAadharNumber());
				// map.put("birthDate",
				// finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getAge());
				map.put("coappAge" + j,
						finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getAge());
				map.put("coappGender" + j,
						finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getGender());
				map.put("coappMaritalStatus" + j, finalViewResponse.getHomeLoanPrimaryViewResponse()
						.getCoApplicantResponse().get(i).getMaritalStatus());
				map.put("coappDependents" + j, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails()
						.get(i).getNoOfDependents());
				map.put("coappCast" +j,
						finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getCaste());
				/*
				 * String coappPresentAddress="";
				 * coappPresentAddress+=finalViewResponse.
				 * getHomeLoanPrimaryViewResponse().getHomeLoanResponse().
				 * getPermanentAddress().getPremiseNumber()+finalViewResponse.
				 * getHomeLoanPrimaryViewResponse().getHomeLoanResponse().
				 * getPermanentAddress().getStreetName()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getPermanentAddress().getLandMark()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getPermanentAddress().getCity()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getPermanentAddress().getState()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getPermanentAddress().getCountry();
				 * map.put("coappPresentAddress", coappPresentAddress);
				 */
				map.put("coappPresentAccommodation" + j, finalViewResponse.getFinalViewResponse()
						.getCoApplicantCommonDetails().get(i).getResidenceType());
				/*
				 * String coappPermanentAddress="";
				 * coappPermanentAddress+=finalViewResponse.
				 * getHomeLoanPrimaryViewResponse().getHomeLoanResponse().
				 * getOfficeAddress().getPremiseNumber()+finalViewResponse.
				 * getHomeLoanPrimaryViewResponse().getHomeLoanResponse().
				 * getOfficeAddress().getStreetName()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getOfficeAddress().getLandMark()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getOfficeAddress().getCity()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getOfficeAddress().getState()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getOfficeAddress().getCountry();
				 * map.put("presentAddress", permanentAddress);
				 */
				map.put("coappOccupation" + j, finalViewResponse.getHomeLoanPrimaryViewResponse()
						.getCoApplicantResponse().get(i).getOccupation());

				String coappOfficeAddress = "";
				officeAddress += finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i)
						.getCompanyName();
				map.put("coappOfficeAddress" + j, coappOfficeAddress);

				map.put("coappDesignation" + j, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails()
						.get(i).getCurrentDesignation());
				map.put("coappYearsInPresentEmployment" + j, finalViewResponse.getFinalViewResponse()
						.getCoApplicantCommonDetails().get(i).getYearsInCurrentJob());
				map.put("coappGrossAnnualIncome" + j, finalViewResponse.getFinalViewResponse()
						.getCoApplicantCommonDetails().get(i).getAnnualTurnover());
				i++;
			}
		}
		// end co-applicant details

		// start guarantor details
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().size(); i++) {
				int j=i+1;
				map.put("guaName" + j,
						finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstName()
								+ finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i)
										.getLastName());
				map.put("guaFatherName" + j, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i)
						.getFatherFullName());
				map.put("guaRelationshipWithApplicant" + j, finalViewResponse.getHomeLoanPrimaryViewResponse()
						.getGarantorResponse().get(i).getRelationshipWithApplicant());
				map.put("guaPanNo" + j,
						finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getPan());
				// map.put("aadharNo",
				// finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().get);
				// map.put("birthDate",
				// finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getAge());
				map.put("guaAge" + j,
						finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getAge());
				map.put("guaGender" + j,
						finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getGender());
				map.put("guaMaritalStatus" + j, finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse()
						.get(i).getMaritalStatus());
				map.put("guaDependents" + j, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i)
						.getNoOfDependents());
				map.put("guaCast" + j,
						finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getCaste());
				/*
				 * String guaPresentAddress="";
				 * guaPresentAddress+=finalViewResponse.
				 * getHomeLoanPrimaryViewResponse().getHomeLoanResponse().
				 * getPermanentAddress().getPremiseNumber()+finalViewResponse.
				 * getHomeLoanPrimaryViewResponse().getHomeLoanResponse().
				 * getPermanentAddress().getStreetName()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getPermanentAddress().getLandMark()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getPermanentAddress().getCity()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getPermanentAddress().getState()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getPermanentAddress().getCountry();
				 * map.put("guaPresentAddress", guaPresentAddress);
				 */
				map.put("guaPresentAccommodation" + j,
						finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getResidenceType());
				/*
				 * String guaPermanentAddress="";
				 * guaPermanentAddress+=finalViewResponse.
				 * getHomeLoanPrimaryViewResponse().getHomeLoanResponse().
				 * getOfficeAddress().getPremiseNumber()+finalViewResponse.
				 * getHomeLoanPrimaryViewResponse().getHomeLoanResponse().
				 * getOfficeAddress().getStreetName()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getOfficeAddress().getLandMark()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getOfficeAddress().getCity()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getOfficeAddress().getState()+
				 * finalViewResponse.getHomeLoanPrimaryViewResponse().
				 * getHomeLoanResponse().getOfficeAddress().getCountry();
				 * map.put("presentAddress", permanentAddress);
				 */
				map.put("guaOccupation" + j, finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse()
						.get(i).getOccupation());

				String guaOfficeAddress = "";
				officeAddress += finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i)
						.getCompanyName();
				map.put("guaOfficeAddress" + j, guaOfficeAddress);

				map.put("guaDesignation" + j, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails()
						.get(i).getCurrentDesignation());
				map.put("guaYearsInPresentEmployment" + j, finalViewResponse.getFinalViewResponse()
						.getGuarantorCommonDetails().get(i).getYearsInCurrentJob());
				map.put("guaGrossAnnualIncome" + j, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails()
						.get(i).getAnnualTurnover());
				i++;

			}
		}
		// end guarantor details

		map.put("nameOfSeller", finalViewResponse.getSellerName());

		String propertyAddress = "";
		propertyAddress += finalViewResponse.getPropPremiseNo() + finalViewResponse.getPropStreetName()
				+ finalViewResponse.getPropLandmark() + finalViewResponse.getPropCity()
				+ finalViewResponse.getPropState() + finalViewResponse.getPropCountry()
				+ finalViewResponse.getPropPinCode();
		map.put("propertyAddress", propertyAddress);

		map.put("builtUpArea", finalViewResponse.getBuiltUpArea());
		map.put("carpetUpArea", finalViewResponse.getCarpetArea());
		map.put("propertyUseType", finalViewResponse.getPropertyUse());

		map.put("loanAmountRequested",
				finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLoanAmount());
		// map.put("mobile", value)
		// map.put("email", value)

		// references details
		Map<String, Object> referenceMap = new HashMap<>();
		for (int i = 0; i < finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
				.getReferenceRetailDetailsRequest().size(); i++) {
			int j=i+1;
			referenceMap.put("refname" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getReferenceRetailDetailsRequest().get(i).getName());
			referenceMap.put("refrelation" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getReferenceRetailDetailsRequest().get(i).getRelationshipWithApplicant());
			referenceMap.put("refaddress" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getReferenceRetailDetailsRequest().get(i).getAddress());
			referenceMap.put("refmobile" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getReferenceRetailDetailsRequest().get(i).getMobile());
			referenceMap.put("refemail" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getReferenceRetailDetailsRequest().get(i).getEmail());
		}
		map.put("reference", referenceMap);

		// bank account held
		Map<String, Object> bankAccountHeldMap = new HashMap<>();
		for (int i = 0; i < finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
				.getBankAccountHeldDetailsRequest().size(); i++) {
			int j=i+1;
			bankAccountHeldMap.put("acno" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getBankAccountHeldDetailsRequest().get(i).getAccountNumber());
			bankAccountHeldMap.put("acBankName" + j, finalViewResponse.getFinalViewResponse()
					.getApplicantCommonDetails().getBankAccountHeldDetailsRequest().get(i).getBankNameAndBranch());
			bankAccountHeldMap.put("acType" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getBankAccountHeldDetailsRequest().get(i).getAccountType());
			bankAccountHeldMap.put("acHeldFor" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getBankAccountHeldDetailsRequest().get(i).getAccountHeldFor());
		}
		map.put("accountDetails", bankAccountHeldMap);

		// existing loan details
		Map<String, Object> existingLoanMap = new HashMap<>();
		for (int i = 1; i < finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
				.getExistingLoanDetailRequest().size(); i++) {
			int j=i+1;
			existingLoanMap.put("loanBankName" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getExistingLoanDetailRequest().get(i).getBankOrFinancerName());
			existingLoanMap.put("loanEmi" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getExistingLoanDetailRequest().get(i).getEmiAmount());
			existingLoanMap.put("loanType" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getExistingLoanDetailRequest().get(i).getLoanType());
			existingLoanMap.put("loanOutstanding" + j, finalViewResponse.getFinalViewResponse()
					.getApplicantCommonDetails().getExistingLoanDetailRequest().get(i).getOutstandingBalance());
		}
		map.put("existingBorrowing", bankAccountHeldMap);

		// existing loan details
		Map<String, Object> creditCardsMap = new HashMap<>();
		for (int i = 1; i < finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
				.getCreditCardsDetailResponse().size(); i++) {
			int j=i+1;
			creditCardsMap.put("cardNo" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getCreditCardsDetailResponse().get(i).getCardNumber());
			creditCardsMap.put("cardBank" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getCreditCardsDetailResponse().get(i).getIssuingBank());
			creditCardsMap.put("cardLimit" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getCreditCardsDetailResponse().get(i).getCardLimit());
			creditCardsMap.put("cardExpiry" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails()
					.getCreditCardsDetailResponse().get(i).getYearOfExpiry());
			creditCardsMap.put("cardOutstanding" + j, finalViewResponse.getFinalViewResponse()
					.getApplicantCommonDetails().getCreditCardsDetailResponse().get(i).getOutstandingBalance());
		}
		map.put("creditcards", creditCardsMap);
		return map;
	}

}
