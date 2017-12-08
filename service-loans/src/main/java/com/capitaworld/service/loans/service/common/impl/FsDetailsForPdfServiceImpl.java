package com.capitaworld.service.loans.service.common.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.cibil.api.utility.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.model.teaser.finalview.HomeLoanFinalViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.common.FsDetailsForPdfService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.service.teaser.finalview.HomeLoanFinalViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Service
@Transactional
public class FsDetailsForPdfServiceImpl implements FsDetailsForPdfService {

	@Autowired
	private HomeLoanFinalViewService homeLoanFinalViewService;

	@Autowired
	private RetailApplicantService retailApplicantService; 
	
	@Autowired
	private UsersClient usersClient;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Override
	public Map getHomeLoanDetails(Long applicantId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		HomeLoanFinalViewResponse finalViewResponse = homeLoanFinalViewService.getHomeLoanFinalViewDetails(applicantId);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		map.put("loanAmount", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLoanAmount());
		map.put("tennure", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getTenure());
		//APPLICANT DETAILS
		map.put("name",finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getTitle()+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getFirstName()+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getLastName());
		map.put("fatherName", finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getFatherFullName());
		map.put("panNo", finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getPan());
		map.put("aadharNo",finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getAadharNumber());
		String birthDate = format1.format(format.parse(finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getBirthDate()));
		map.put("birthDate",birthDate);
		map.put("age", finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getAge());
		map.put("gender", finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getGender());
		map.put("maritalStatus",finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getMaritalStatus());
		map.put("dependents", finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getNoOfDependents());
		map.put("cast", finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCaste());
		map.put("city", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getCity());
		String presentAddress = "";
		presentAddress +=finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getPremiseNumber()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getStreetName()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getLandMark()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getCity()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getState()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getCountry()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getContactNo();
		map.put("presentAddress", presentAddress);
		map.put("presentAccommodation",finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getResidenceType());
		String permanentAddress = "";
		permanentAddress += finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getPremiseNumber()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getStreetName()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getLandMark()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getCity()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getState()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getCountry()
				+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getContactNo();
		map.put("permanentAddress", permanentAddress);
		Long userId=loanApplicationRepository.getUserIdByApplicationId(applicantId);
		UserResponse userResponse = usersClient.getEmailMobile(userId);
		LinkedHashMap<String, Object> lm = (LinkedHashMap<String, Object>)userResponse.getData();
		UsersRequest request = MultipleJSONObjectHelper.getObjectFromMap(lm,UsersRequest.class);
		map.put("mobile", request.getMobile());
		map.put("email", request.getEmail());
		map.put("occupation",finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupation());
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 2){
			map.put("orgName",finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getCompanyName());
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 3 || finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 4){
			map.put("orgName",finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getEntityName());
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 5){
			map.put("orgName",finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOccupation());
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 2){
			map.put("designation",finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCurrentDesignation());
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 2){
			map.put("yearsInPresentEmployment",finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getYearsInCurrentJob());
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 3 || finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 4 || finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 5 || finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 6){
			map.put("yearsInPresentEmployment",finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getBusinessExperience());
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 2){
			String grossAnnualIncome="";
			grossAnnualIncome += finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getMonthlyIncome()
								+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getBonusPerAnnum()
								+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOtherIncome();
		map.put("grossAnnualIncome",grossAnnualIncome);
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 3 || finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 4){
			String grossAnnualIncome="";
			grossAnnualIncome += finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getPatCurrentYear()
					+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getDepreciationCurrentYear()
					+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getRemunerationCurrentYear()
					+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOtherIncome();
			map.put("grossAnnualIncome",grossAnnualIncome);
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 7){
			String grossAnnualIncome="";
			grossAnnualIncome += finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getMonthlyIncome()
					+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOtherIncome();
			map.put("grossAnnualIncome",grossAnnualIncome);
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 2 || finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 7){
			map.put("taxPaid",finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getTaxPaid());
		}
		map.put("otherAnnualExpenditure",finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOtherInvestment());
		map.put("religion", finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getReligion());
		
		//COAPPLICANT DETAILS
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().size(); i++) {
				int j=i+1;
				map.put("coappName" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getTitle()+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstName()+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getLastName());
				map.put("coappFatherName" + j, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getFatherFullName());
				map.put("coappRelationshipWithApplicant" + j, finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getRelationshipWithApplicant());
				map.put("coappPanNo" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getPan());
				map.put("coappAadharNo" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getAadharNumber());
				String coappBirthDate = format1.format(format.parse(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getBirthDate()));
				map.put("coappBirthDate" + j,coappBirthDate);
				map.put("coappAge" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getAge());
				map.put("coappGender" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getGender());
				map.put("coappMaritalStatus" + j, finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getMaritalStatus());
				map.put("coappDependents" + j, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getNoOfDependents());
				map.put("coappCast" +j,finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getCaste());
				map.put("coappCity" +j,finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getCity());
				String coAppPresentAddress = "";
				coAppPresentAddress += finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getPremiseNumber()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getStreetName()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getLandMark()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getCity()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getState()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getCountry()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getContactNo();
				map.put("coAppPresentAddress", coAppPresentAddress);
				String coAppPermanantAddress = "";
				coAppPermanantAddress += finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getPremiseNumber()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getStreetName()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getLandMark()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getCity()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getState()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getCountry()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getContactNo();
				map.put("coAppPermanantAddress", coAppPermanantAddress);
				map.put("coappPresentAccommodation" + j, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getResidenceType());
				map.put("coappMobile", finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getContactNo());
				map.put("coappOccupation" + j, finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupation());
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 2){
					map.put("coappOrgName",finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getCompanyName());
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 3 || finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 4){
					map.put("coappOrgName",finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getEntityName());
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 5){
					map.put("coappOrgName",finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOccupation());
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 2){
					map.put("coappDesignation",finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getCurrentDesignation());
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 2){
					map.put("coappYearsInPresentEmployment",finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getYearsInCurrentJob());
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 3 || finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 4 || finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 5 || finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 6){
					map.put("coappYearsInPresentEmployment",finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getBusinessExperience());
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 2){
					String coappGrossAnnualIncome="";
					coappGrossAnnualIncome += finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getMonthlyIncome()
										+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getBonusPerAnnum()
										+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOtherIncome();
				map.put("coappGrossAnnualIncome",coappGrossAnnualIncome);
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 3 || finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 4){
					String coappGrossAnnualIncome="";
					coappGrossAnnualIncome += finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getPatCurrentYear()
							+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getDepreciationCurrentYear()
							+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getRemunerationCurrentYear()
							+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOtherIncome();
					map.put("coappGrossAnnualIncome",coappGrossAnnualIncome);
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 7){
					String coappGrossAnnualIncome="";
					coappGrossAnnualIncome += finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getMonthlyIncome()
							+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOtherIncome();
					map.put("coappGrossAnnualIncome",coappGrossAnnualIncome);
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 2 || finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 7){
					map.put("coappTaxPaid",finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getTaxPaid());
				}
				map.put("coappOtherAnnualExpenditure",finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOtherInvestment());
				map.put("coappReligion" +j,finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getReligion());
				i++;
			}
		}
		//GUARANTOR DETAILS
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().size(); i++) {
				int j=i+1;
				map.put("guaName" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getTitle()+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstName()+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getLastName());
				map.put("guaFatherName" + j, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getFatherFullName());
				map.put("guaPanNo" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getPan());
				map.put("guaAadhaarNo" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getAadharNumber());
				String guaResidentialAddress = "";
				guaResidentialAddress += finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getPremiseNumber()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getStreetName()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getLandMark()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getCity()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getState()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getCountry()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getContactNo();
				map.put("guaResidentialAddress", guaResidentialAddress);
				map.put("guaOccupation" + j, finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupation());
				map.put("guaMobile", finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getContactNo());
				String guaBirthDate = format1.format(format.parse(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getBirthDate()));
				map.put("guaBirthDate" + j,guaBirthDate);
				map.put("guaAge" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getAge());
				map.put("guaGender" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getGender());
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupationId() == 2){
					String guaGrossAnnualIncome="";
					guaGrossAnnualIncome += finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getMonthlyIncome()
										+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getBonusPerAnnum()
										+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getOtherIncome();
				map.put("guaGrossAnnualIncome",guaGrossAnnualIncome);
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupationId() == 3 || finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupationId() == 4){
					String guaGrossAnnualIncome="";
					guaGrossAnnualIncome += finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getPatCurrentYear()
							+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getDepreciationCurrentYear()
							+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getRemunerationCurrentYear()
							+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getOtherIncome();
					map.put("guaGrossAnnualIncome",guaGrossAnnualIncome);
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupationId() == 7){
					String guaGrossAnnualIncome="";
					guaGrossAnnualIncome += finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getMonthlyIncome()
										+" "+finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getOtherIncome();
					map.put("guaGrossAnnualIncome",guaGrossAnnualIncome);
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupationId() == 2 || finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupationId() == 7){
					map.put("guaTaxPaid",finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getTaxPaid());
				}
				map.put("guaOtherAnnualExpenditure",finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getOtherInvestment());
				map.put("guaMaritalStatus" + j, finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getMaritalStatus());
				map.put("guaDependents" + j, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getNoOfDependents());
				map.put("guaCast" + j,finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getCaste());
				String guaPermanantAddress = "";
				guaPermanantAddress += finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getPremiseNumber()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getStreetName()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getLandMark()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getCity()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getState()
						+" "+ finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getCountry();
				map.put("guaPermanantAddress", guaPermanantAddress);
				map.put("guaPresentAccommodation" + j,finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getResidenceType());
				String guaOfficeAddress = "";
				guaOfficeAddress += finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getCompanyName();
				map.put("guaOfficeAddress" + j, guaOfficeAddress);
				map.put("guaDesignation" + j, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getCurrentDesignation());
				map.put("guaYearsInPresentEmployment" + j, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getYearsInCurrentJob());
				map.put("guaGrossAnnualIncome" + j, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getAnnualTurnover());
				map.put("guaCity", finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getCity());
				map.put("guaReligion" +j,finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getReligion());
				i++;

			}
		}
		//PRIMARY HOME LOAN DETAILS
		map.put("nameOfSeller", finalViewResponse.getSellerName());
		String propertyAddress = "";
		propertyAddress += finalViewResponse.getPropPremiseNo() +" "+ finalViewResponse.getPropStreetName()
				+" "+ finalViewResponse.getPropLandmark() + finalViewResponse.getPropCity()
				+" "+ finalViewResponse.getPropState() + finalViewResponse.getPropCountry()
				+" "+ finalViewResponse.getPropPinCode();
		map.put("propertyAddress", propertyAddress);
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyUsedType() == "Purchase of Plot"){
			map.put("areaOfLand", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getArea());
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyUsedType() == "Purchase of ready flat/tenament/row house"){
			map.put("builtUpArea", finalViewResponse.getBuiltUpArea());
			map.put("carpetUpArea", finalViewResponse.getCarpetArea());
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyUsedType() == "Construction of bunglow/tenament"){
			map.put("costOfLand", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLandPlotCost());
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyUsedType() == "Purchase of Plot"){
			map.put("costOfLand", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLandPlotCost());
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyUsedType() == "Purchase of ready flat/tenament/row house"){
			map.put("purchasePrice", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyPrice());
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyUsedType() == "Construction of bunglow/tenament"){
			map.put("purchasePrice", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getConstructionCost());
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyUsedType() == "Repairing/Renovation of flat/tenament"){
			map.put("purchasePrice", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getRenovationCost());
		}
		map.put("propertyUse", finalViewResponse.getPropertyUse());
		map.put("downPayment", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getDownPayment());
		map.put("loanAmountRequested", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLoanAmount());
		
		//REFERENCE DETAILS FRAME APPLICANT
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

		//BANK ACCOUNT HELD DETAILS APPLICANT
		Map<String, Object> bankAccountHeldMap = new HashMap<>();
		for (int i = 0; i < finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getBankAccountHeldDetailsRequest().size(); i++) {
			int j=i+1;
			bankAccountHeldMap.put("acno" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getBankAccountHeldDetailsRequest().get(i).getAccountNumber());
			bankAccountHeldMap.put("acBankName" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getBankAccountHeldDetailsRequest().get(i).getBankNameAndBranch());
			bankAccountHeldMap.put("acType" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getBankAccountHeldDetailsRequest().get(i).getAccountType());
			bankAccountHeldMap.put("acHeldFor" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getBankAccountHeldDetailsRequest().get(i).getAccountHeldFor());
		}
		map.put("accountDetails", bankAccountHeldMap);

		//EXISISTING LOAN DETAILS APPLICANT
		Map<String, Object> existingLoanMap = new HashMap<>();
		for (int i = 1; i < finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getExistingLoanDetailRequest().size(); i++) {
			int j=i+1;
			existingLoanMap.put("loanBankName" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getExistingLoanDetailRequest().get(i).getBankOrFinancerName());
			existingLoanMap.put("loanEmi" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getExistingLoanDetailRequest().get(i).getEmiAmount());
			existingLoanMap.put("loanType" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getExistingLoanDetailRequest().get(i).getLoanType());
			existingLoanMap.put("loanOutstanding" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getExistingLoanDetailRequest().get(i).getOutstandingBalance());
		}
		map.put("existingBorrowing", existingLoanMap);

		//CREDIT INFORMATION APPLICANT
		Map<String, Object> creditCardsMap = new HashMap<>();
		for (int i = 1; i < finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCreditCardsDetailResponse().size(); i++) {
			int j=i+1;
			creditCardsMap.put("cardNo" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCreditCardsDetailResponse().get(i).getCardNumber());
			creditCardsMap.put("cardBank" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCreditCardsDetailResponse().get(i).getIssuingBank());
			creditCardsMap.put("cardLimit" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCreditCardsDetailResponse().get(i).getCardLimit());
			creditCardsMap.put("cardExpiry" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCreditCardsDetailResponse().get(i).getYearOfExpiry());
			creditCardsMap.put("cardOutstanding" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCreditCardsDetailResponse().get(i).getOutstandingBalance());
		}
		map.put("creditcards", creditCardsMap);
		
		//BANK ACCOUNT HELD DETAILS COAPPLICANT
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().size(); i++) {
				int j=i+1;
				Map<String, Object> coappBankAccountHeldMap = new HashMap<>();
				for (int k = 0; k < finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getBankAccountHeldDetailsRequest().size(); k++) {
					int l=k+1;
					coappBankAccountHeldMap.put("coppAcno" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getBankAccountHeldDetailsRequest().get(k).getAccountNumber());
					coappBankAccountHeldMap.put("coappAcBankName" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getBankAccountHeldDetailsRequest().get(k).getBankNameAndBranch());
					coappBankAccountHeldMap.put("coappAcType" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getBankAccountHeldDetailsRequest().get(k).getAccountType());
					coappBankAccountHeldMap.put("coappAcHeldFor" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getBankAccountHeldDetailsRequest().get(k).getAccountHeldFor());
				}
				map.put("coappAccountDetails"+ j, coappBankAccountHeldMap);
			}
		}
		//EXISISTING LOAN DETAILS COAPPLICANT
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().size(); i++) {
				int j=i+1;
				Map<String, Object> coappexistingLoanMap = new HashMap<>();
					for (int k = 0; k < finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getExistingLoanDetailRequest().size(); k++) {
						int l=k+1;
						coappexistingLoanMap.put("coppLoanBankName" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getExistingLoanDetailRequest().get(k).getBankOrFinancerName());
						coappexistingLoanMap.put("coappLoanEmi" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getExistingLoanDetailRequest().get(k).getEmiAmount());
						coappexistingLoanMap.put("coappLoanType" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getExistingLoanDetailRequest().get(k).getLoanType());
						coappexistingLoanMap.put("coappLoanOutstanding" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getExistingLoanDetailRequest().get(k).getOutstandingBalance());
					}
					map.put("coappexistingLoanMap"+ j, coappexistingLoanMap);
				}
			}
		//CREDIT INFORMATION COAPPLICANT
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().size(); i++) {
				int j=i+1;
				Map<String, Object> coappCreditCardsMap = new HashMap<>();
				for (int k = 0; k < finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getCreditCardsDetailResponse().size(); k++) {
					int l=k+1;
					coappCreditCardsMap.put("coppCardNo" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getCreditCardsDetailResponse().get(k).getCardNumber());
					coappCreditCardsMap.put("coappCardBank" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getCreditCardsDetailResponse().get(k).getIssuingBank());
					coappCreditCardsMap.put("coappCardLimit" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getCreditCardsDetailResponse().get(k).getCardLimit());
					coappCreditCardsMap.put("coappCardExpiry" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getCreditCardsDetailResponse().get(k).getYearOfExpiry());
					coappCreditCardsMap.put("coappCardOutstanding" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getCreditCardsDetailResponse().get(k).getOutstandingBalance());
				}
				map.put("coappCreditCardsMap"+ j, coappCreditCardsMap);
			}
		}
		
		//BANK ACCOUNT HELD DETAILS GUARANTOR
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().size(); i++) {
				int j=i+1;
				Map<String, Object> guaBankAccountHeldMap = new HashMap<>();
				for (int k = 0; k < finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getBankAccountHeldDetailsRequest().size(); k++) {
					int l=k+1;
					guaBankAccountHeldMap.put("guaAcno" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getBankAccountHeldDetailsRequest().get(k).getAccountNumber());
					guaBankAccountHeldMap.put("guaAcBankName" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getBankAccountHeldDetailsRequest().get(k).getBankNameAndBranch());
					guaBankAccountHeldMap.put("guaAcType" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getBankAccountHeldDetailsRequest().get(k).getAccountType());
					guaBankAccountHeldMap.put("guaAcHeldFor" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getBankAccountHeldDetailsRequest().get(k).getAccountHeldFor());
				}
				map.put("guaBankAccountHeldMap"+ j, guaBankAccountHeldMap);
			}
		}
		//EXISISTING LOAN DETAILS GUARANTOR
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().size(); i++) {
				int j=i+1;
				Map<String, Object> guaExistingLoanMap = new HashMap<>();
				for (int k = 0; k < finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getExistingLoanDetailRequest().size(); k++) {
					int l=k+1;
					guaExistingLoanMap.put("guaLoanBankName" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getExistingLoanDetailRequest().get(k).getBankOrFinancerName());
					guaExistingLoanMap.put("guaLoanEmi" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getExistingLoanDetailRequest().get(k).getEmiAmount());
					guaExistingLoanMap.put("guaLoanType" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getExistingLoanDetailRequest().get(k).getLoanType());
					guaExistingLoanMap.put("guaLoanOutstanding" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getExistingLoanDetailRequest().get(k).getOutstandingBalance());
				}
				map.put("guaExistingLoanMap"+ j, guaExistingLoanMap);
			}
		}
		//CREDIT INFORMATION GUARANTOR
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().size(); i++) {
				int j=i+1;
				Map<String, Object> guaCreditCardsMap = new HashMap<>();
				for (int k = 0; k < finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getCreditCardsDetailResponse().size(); k++) {
					int l=k+1;
					guaCreditCardsMap.put("guaCardNo" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getCreditCardsDetailResponse().get(k).getCardNumber());
					guaCreditCardsMap.put("guaCardBank" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getCreditCardsDetailResponse().get(k).getIssuingBank());
					guaCreditCardsMap.put("guaCardLimit" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getCreditCardsDetailResponse().get(k).getCardLimit());
					guaCreditCardsMap.put("guaCardExpiry" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getCreditCardsDetailResponse().get(k).getYearOfExpiry());
					guaCreditCardsMap.put("guaCardOutstanding" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getCreditCardsDetailResponse().get(k).getOutstandingBalance());
				}
					map.put("guaCreditCardsMap"+ j, guaCreditCardsMap);
		}
				}
		
		return map;
	}

}
