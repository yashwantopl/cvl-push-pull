package com.capitaworld.service.loans.service.common.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.cibil.api.utility.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.model.teaser.finalview.HomeLoanFinalViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.common.FsDetailsForPdfService;
import com.capitaworld.service.loans.service.common.LogService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.service.teaser.finalview.HomeLoanFinalViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.enums.LogDateTypeMaster;
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
	
	@Autowired
	private LogService logService; 
	
	@Override
	public Map getHomeLoanDetails(Long applicantId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		HomeLoanFinalViewResponse finalViewResponse = homeLoanFinalViewService.getHomeLoanFinalViewDetails(applicantId);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		map.put("loanPurpose", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyType());
		map.put("loanAmount", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLoanAmount());
		//amount in words
		try {
			if (!CommonUtils.isObjectNullOrEmptyOrDash(
					finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLoanAmount())) {
				map.put("loanAmountInWords", CommonUtils.amountInWords((long)Double.parseDouble(
						finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLoanAmount())));
			} else {
				map.put("loanAmountInWords",
						finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLoanAmount());
			}
		} catch (Exception e) {
			map.put("loanAmountInWords",
					finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLoanAmount());
		}
		map.put("tennure", CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getTenure())?"":Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getTenure())*12);
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
		presentAddress +=(CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getPremiseNumber())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getPremiseNumber())
                +" "+(CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getStreetName())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getStreetName())
                +" "+(CommonUtils.isObjectNullOrEmpty(  finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getLandMark())?"": finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getLandMark())
                +" "+ (CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getCity())?"": finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getCity())
                +" "+ (CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getState())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getState())
                +" "+ (CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getCountry())?"": finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getCountry())
                +" "+ (CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getPincode())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getOfficeAddress().getPincode())
                +" "+ (CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getContactNo())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getContactNo());
		map.put("presentAddress", presentAddress);
		map.put("presentAccommodation",finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getResidenceType());
		String permanentAddress = "";
		permanentAddress += (CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getPremiseNumber())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getPremiseNumber())
                +" "+ (CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getStreetName())?"": finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getStreetName())
                +" "+ (CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getLandMark())?"": finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getLandMark())
                +" "+ (CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getCity())?"": finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getCity())
                +" "+ (CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getState())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getState())
                +" "+ (CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getCountry())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getCountry())
                +" "+ (CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getPincode())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPermanentAddress().getPincode())
                +" "+ (CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getContactNo())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getContactNo());
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

		Double grossAnnualIncome=(double) 0;
		
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 2){
			grossAnnualIncome += CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getMonthlyIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getMonthlyIncome().replaceAll(",", ""));
								grossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getBonusPerAnnum())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getBonusPerAnnum().replaceAll(",", ""));
								grossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOtherIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOtherIncome().replaceAll(",", ""));
		map.put("grossAnnualIncome",grossAnnualIncome*12);
		}
		
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 3 || finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 4){
			
			grossAnnualIncome = CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getPatCurrentYear())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getPatCurrentYear().replaceAll(",", ""));
			grossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getDepreciationCurrentYear())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getDepreciationCurrentYear().replaceAll(",", ""));
			grossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getRemunerationCurrentYear())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getRemunerationCurrentYear().replaceAll(",", ""));
			grossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOtherIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOtherIncome().replaceAll(",", ""));
			map.put("grossAnnualIncome",grossAnnualIncome*12);
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 7){
			
			grossAnnualIncome += CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getMonthlyIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getMonthlyIncome().replaceAll(",", ""));
			grossAnnualIncome +=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOtherIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOtherIncome().replaceAll(",", ""));
			map.put("grossAnnualIncome",grossAnnualIncome*12);
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 2 || finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getNatureOfOccupationId() == 7){
			map.put("taxPaid",finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getTaxPaid());
		}
		map.put("otherAnnualExpenditure",finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOtherInvestment());
		map.put("religion", finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getReligion());
		//put primary lock date
		try
		{
			Date logDate=logService.getDateByLogType(applicantId, LogDateTypeMaster.PRIMARY_NEXT.getId());
			if(!CommonUtils.isObjectNullOrEmpty(logDate))
			{
				String lockDate = format1.format((logDate));
				map.put("dateOfPrimaryLock", lockDate);
			}
			else
			{
				map.put("dateOfPrimaryLock", "-");
			}
				
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		//surplus
		Double surplus=grossAnnualIncome*12;
		surplus-=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getTaxPaid())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getTaxPaid().replaceAll(",", ""));
		surplus-=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOtherInvestment())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getPersonalProfileRespoonse().getOtherInvestment().replaceAll(",", ""));
		map.put("surplus", surplus);
		
		//COAPPLICANT DETAILS
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().size(); i++) {
				int j=i+1;
				 map.put("coappName" + j,(CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getTitle())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getTitle())+" "+(CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstName())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstName())+" "+(CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getLastName())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getLastName()));
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
				coAppPresentAddress += (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getPremiseNumber())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getPremiseNumber())
                        +" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getStreetName())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getStreetName())
                        +" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getLandMark())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getLandMark())
                        +" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getCity())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getCity())
                        +" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getState())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getState())
                        +" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getCountry())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getSecondAddress().getCountry())
                        +" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getContactNo())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getContactNo())
                        ;
				map.put("coAppPresentAddress" + j, coAppPresentAddress);
				String coAppPermanantAddress = "";
				coAppPermanantAddress += (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getPremiseNumber())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getPremiseNumber())
                        +" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getStreetName())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getStreetName())
                        +" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getLandMark())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getLandMark())
                        +" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getCity())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getCity())
                        +" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getState())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getState())
                        +" "+(CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getCountry())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getFirstAddress().getCountry())
                        +" "+(CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getContactNo())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getContactNo());
				map.put("coAppPermanantAddress" + j, coAppPermanantAddress);
				map.put("coappPresentAccommodation" + j, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getResidenceType());
				map.put("coappMobile"+ j, finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getContactNo());
				map.put("coappOccupation" + j, finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupation());
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 2){
					map.put("coappOrgName" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getCompanyName());
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 3 || finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 4){
					map.put("coappOrgName" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getEntityName());
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 5){
					map.put("coappOrgName" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOccupation());
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 2){
					map.put("coappDesignation" + j,finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getCurrentDesignation());
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 2){
					map.put("coappYearsInPresentEmployment" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getYearsInCurrentJob());
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 3 || finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 4 || finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 5 || finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 6){
					map.put("coappYearsInPresentEmployment" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getBusinessExperience());
				}
				
				Double coappGrossAnnualIncome=(double) 0;
				
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 2){
					coappGrossAnnualIncome += CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getMonthlyIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getMonthlyIncome().replaceAll(",", ""));
					coappGrossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getBonusPerAnnum())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getBonusPerAnnum().replaceAll(",", ""));
					coappGrossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOtherIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOtherIncome().replaceAll(",", ""));
				map.put("coappGrossAnnualIncome" + j,coappGrossAnnualIncome*12);
				}
				
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 3 || finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 4){
					
					coappGrossAnnualIncome = CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getPatCurrentYear())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getPatCurrentYear().replaceAll(",", ""));
					coappGrossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getDepreciationCurrentYear())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getDepreciationCurrentYear().replaceAll(",", ""));
					coappGrossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getRemunerationCurrentYear())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getRemunerationCurrentYear().replaceAll(",", ""));
					coappGrossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOtherIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOtherIncome());
					map.put("coappGrossAnnualIncome" + j,coappGrossAnnualIncome*12);
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 7){
					coappGrossAnnualIncome += CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getMonthlyIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getMonthlyIncome().replaceAll(",", ""));
					coappGrossAnnualIncome +=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOtherIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOtherIncome().replaceAll(",", ""));
					map.put("coappGrossAnnualIncome" + j,coappGrossAnnualIncome*12);
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 2 || finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getNatureOfOccupationId() == 7){
					map.put("coappTaxPaid",finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getTaxPaid());
				}
				map.put("coappOtherAnnualExpenditure" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOtherInvestment());
				
				
				//surplus
				Double coappsurplus=coappGrossAnnualIncome*12;
				coappsurplus-=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getTaxPaid())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getTaxPaid().replaceAll(",", ""));
				coappsurplus-=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOtherInvestment())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getCoApplicantResponse().get(i).getOtherInvestment().replaceAll(",", ""));
				map.put("coappsurplus" + j, coappsurplus);
				map.put("coappReligion" +j,finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getReligion());
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
				guaResidentialAddress += (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getPremiseNumber())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getPremiseNumber())
						+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getStreetName())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getStreetName())
						+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getLandMark())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getLandMark())
						+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getCity())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getCity())
						+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getState())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getState())
						+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getCountry())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getCountry())
						+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getPincode())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getPincode())
						+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getContactNo())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getContactNo());
				map.put("guaResidentialAddress" + j, guaResidentialAddress);
				map.put("guaOccupation" + j, finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupation());
				map.put("guaMobile"+ j, finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getContactNo());
				String guaBirthDate = format1.format(format.parse(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getBirthDate()));
				map.put("guaBirthDate" + j,guaBirthDate);
				map.put("guaAge" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getAge());
				map.put("guaGender" + j,finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getGender());
				
				Double guaGrossAnnualIncome=(double) 0;
				
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupationId() == 2){
					
					guaGrossAnnualIncome += CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getMonthlyIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getMonthlyIncome().replaceAll(",", ""));
					guaGrossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getBonusPerAnnum())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getBonusPerAnnum().replaceAll(",", ""));
					guaGrossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getOtherIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getOtherIncome().replaceAll(",", ""));

				map.put("guaGrossAnnualIncome"+ j,guaGrossAnnualIncome*12);
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupationId() == 3 || finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupationId() == 4){
					
					guaGrossAnnualIncome += CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getPatCurrentYear())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getPatCurrentYear().replaceAll(",", ""));
					guaGrossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getDepreciationCurrentYear())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getDepreciationCurrentYear().replaceAll(",", ""));
					guaGrossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getRemunerationCurrentYear())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getRemunerationCurrentYear().replaceAll(",", ""));
					guaGrossAnnualIncome+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getOtherIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getOtherIncome().replaceAll(",", ""));

					map.put("guaGrossAnnualIncome"+ j,guaGrossAnnualIncome*12);
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupationId() == 7){
					
					guaGrossAnnualIncome += CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getMonthlyIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getMonthlyIncome().replaceAll(",", ""));
					guaGrossAnnualIncome +=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getOtherIncome())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getOtherIncome().replaceAll(",", ""));
					map.put("guaGrossAnnualIncome"+ j,guaGrossAnnualIncome*12);
				}
				if(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupationId() == 2 || finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getNatureOfOccupationId() == 7){
					map.put("guaTaxPaid"+ j,finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getTaxPaid());
				}
				map.put("guaOtherAnnualExpenditure"+ j,finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getOtherInvestment());
				
				
				//surplus
				Double guasurplus=guaGrossAnnualIncome*12;
				surplus-=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getTaxPaid())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getTaxPaid().replaceAll(",", ""));
				surplus-=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getOtherInvestment())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getOtherInvestment().replaceAll(",", ""));
				map.put("guasurplus"+ j, guasurplus);
				map.put("guaMaritalStatus" + j, finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getMaritalStatus());
				map.put("guaDependents" + j, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getNoOfDependents());
				map.put("guaCast" + j,finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getCaste());
				String guaPermanantAddress = "";
				guaPermanantAddress += (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getPremiseNumber())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getPremiseNumber())
						+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getStreetName())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getStreetName())
						+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getLandMark())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getLandMark())
						+" "+(CommonUtils.isObjectNullOrEmpty( finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getCity())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getCity())
						+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getState())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getState())
						+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getCountry())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getCountry())
						+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getPincode())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getFirstAddress().getPincode())
						+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getContactNo())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getContactNo());
				map.put("guaPermanantAddress", guaPermanantAddress);
				map.put("guaPresentAccommodation" + j,finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getResidenceType());
				String guaOfficeAddress = "";
				guaOfficeAddress += CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getCompanyName())?"":finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getCompanyName();
				map.put("guaOfficeAddress" + j, guaOfficeAddress);
				map.put("guaDesignation" + j, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getCurrentDesignation());
				map.put("guaYearsInPresentEmployment" + j, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getYearsInCurrentJob());
				map.put("guaCity" + j, finalViewResponse.getHomeLoanPrimaryViewResponse().getGarantorResponse().get(i).getSecondAddress().getCity());
				map.put("guaReligion" +j,finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getReligion());

			}
		}
		//PRIMARY HOME LOAN DETAILS
		map.put("nameOfSeller", finalViewResponse.getSellerName());
		String propertyAddress = "";
		propertyAddress += (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getPropPremiseNo())?"":finalViewResponse.getPropPremiseNo()) 
				+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getPropStreetName())?"":finalViewResponse.getPropStreetName())
				+" "+(CommonUtils.isObjectNullOrEmpty( finalViewResponse.getPropLandmark() )?"":finalViewResponse.getPropLandmark() )
				+" "+(CommonUtils.isObjectNullOrEmpty(finalViewResponse.getPropCity())?"":finalViewResponse.getPropCity())
				+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getPropState())?"":finalViewResponse.getPropState()) 
				+" "+(CommonUtils.isObjectNullOrEmpty(finalViewResponse.getPropCountry())?"":finalViewResponse.getPropCountry())
				+" "+ (CommonUtils.isObjectNullOrEmpty(finalViewResponse.getPropPinCode())?"":finalViewResponse.getPropPinCode());
		map.put("propertyAddress", propertyAddress);
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyType() == "Purchase of Plot"){
			map.put("areaOfLand", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getArea());
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyType() == "Purchase of ready flat/tenament/row house" || finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyType() == "Repairing/Renovation of flat/tenament" || finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyType() == "Purchase of Plot"){
			map.put("builtUpArea", finalViewResponse.getBuiltUpArea());
			map.put("carpetUpArea", finalViewResponse.getCarpetArea());
		}
		Double requirementTotal = (double) 0,sourceTotal=(double) 0;
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyType() == "Construction of bunglow/tenament"){
			map.put("costOfLand", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLandPlotCost());
			requirementTotal+= CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLandPlotCost())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLandPlotCost().replaceAll(",", ""));
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyType() == "Purchase of Plot"){
			map.put("costOfLand", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLandPlotCost());
			requirementTotal+= CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLandPlotCost())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLandPlotCost().replaceAll(",", ""));
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyType() == "Purchase of ready flat/tenament/row house"){
			map.put("purchasePrice", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyPrice());
			requirementTotal+= CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyPrice())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyPrice().replaceAll(",", ""));
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyType() == "Construction of bunglow/tenament"){
			map.put("purchasePrice", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getConstructionCost());
			requirementTotal+= CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getConstructionCost())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getConstructionCost().replaceAll(",", ""));
		}
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyType() == "Repairing/Renovation of flat/tenament"){
			map.put("purchasePrice", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getRenovationCost());
			requirementTotal+= CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getRenovationCost())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getRenovationCost().replaceAll(",", ""));
		}
		
		if(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getPropertyUse() == "Renting"){
			map.put("rentedout", "Yes");
		}
		else
		{
			map.put("rentedout", "No");
		}
		
		map.put("requirementTotal", requirementTotal);
		map.put("propertyUse", finalViewResponse.getPropertyUse());
		map.put("downPayment", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getDownPayment());
		map.put("loanAmountRequested", finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLoanAmount());
		
		
		sourceTotal+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getDownPayment())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getDownPayment().replaceAll(",", ""));
		sourceTotal+=CommonUtils.isObjectNullOrEmptyOrDash(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLoanAmount())?0:Double.parseDouble(finalViewResponse.getHomeLoanPrimaryViewResponse().getHomeLoanResponse().getLoanAmount().replaceAll(",", ""));
		map.put("sourceTotal", sourceTotal);
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
		for (int i = 0; i < finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getExistingLoanDetailRequest().size(); i++) {
			int j=i+1;
			existingLoanMap.put("loanBankName" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getExistingLoanDetailRequest().get(i).getBankOrFinancerName());
			existingLoanMap.put("loanEmi" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getExistingLoanDetailRequest().get(i).getEmiAmount());
			existingLoanMap.put("loanType" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getExistingLoanDetailRequest().get(i).getLoanType());
			existingLoanMap.put("loanOutstanding" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getExistingLoanDetailRequest().get(i).getOutstandingBalance());
		}
		map.put("existingBorrowing", existingLoanMap);

		//CREDIT INFORMATION APPLICANT
		Map<String, Object> creditCardsMap = new HashMap<>();
		for (int i = 0; i < finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCreditCardsDetailResponse().size(); i++) {
			int j=i+1;
			creditCardsMap.put("cardNo" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCreditCardsDetailResponse().get(i).getCardNumber());
			creditCardsMap.put("cardBank" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCreditCardsDetailResponse().get(i).getIssuingBank());
			creditCardsMap.put("cardLimit" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCreditCardsDetailResponse().get(i).getCardLimit());
			creditCardsMap.put("cardExpiry" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCreditCardsDetailResponse().get(i).getYearOfExpiry());
			creditCardsMap.put("cardOutstanding" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getCreditCardsDetailResponse().get(i).getOutstandingBalance());
		}
		map.put("creditcards", creditCardsMap);
		
		//FIXED DEPOSIT BANK NAME APPLICANT
		Map<String, Object> fixedDepositeMap = new HashMap<>();
		for (int i = 0; i < finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getFixedDepositsDetailsRequest().size(); i++) {
			int j=i+1;
			fixedDepositeMap.put("fixedDepositeBankName" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getFixedDepositsDetailsRequest().get(i).getBankName());
		}
		map.put("fixedDepositeMap", fixedDepositeMap);
		
		//OTHER CURRENT ASSET TYPE APPLICANT
		Map<String, Object> otherCurrentAssetTypeMap = new HashMap<>();
		for (int i = 0; i < finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getAssetDetailResponseList().size(); i++) {
			int j=i+1;
			otherCurrentAssetTypeMap.put("assetType" + j, finalViewResponse.getFinalViewResponse().getApplicantCommonDetails().getAssetDetailResponseList().get(i).getAssetType());
		}
		map.put("otherCurrentAssetTypeMap", otherCurrentAssetTypeMap);
		
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
		//FIXED ASSET BANK NAME COAPPLICANT
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().size(); i++) {
				int j=i+1;
				Map<String, Object> coappFixedAssetBankName = new HashMap<>();
				for (int k = 0; k < finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getCreditCardsDetailResponse().size(); k++) {
					int l=k+1;
					coappFixedAssetBankName.put("coppBankName" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getFixedDepositsDetailsRequest().get(k).getBankName());
				}
				map.put("coappFixedAssetBankName"+ j, coappFixedAssetBankName);
			}
		}
		//OTHER CURRENT ASSET TYPE COAPPLICANT
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().size(); i++) {
				 int j=i+1;
				 Map<String, Object> coappOtherCurrentAsset = new HashMap<>();
				 for (int k = 0; k < finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getAssetDetailResponseList().size(); k++) {
				 int l=k+1;
				 coappOtherCurrentAsset.put("coppOtherCurrentAsset" + j + l, finalViewResponse.getFinalViewResponse().getCoApplicantCommonDetails().get(i).getAssetDetailResponseList().get(k).getAssetType());
			}
			map.put("coappOtherCurrentAsset"+ j, coappOtherCurrentAsset);
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
				for (int k = 0; k < finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getExistingLoanDetailRequest().size(); k++) {
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
		//FIXED ASSET BANK NAME COAPPLICANT
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().size(); i++) {
				int j=i+1;
				Map<String, Object> guaFixedAssetBankName = new HashMap<>();
				for (int k = 0; k < finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getFixedDepositsDetailsRequest().size(); k++) {
					int l=k+1;
					guaFixedAssetBankName.put("guaBankName" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getFixedDepositsDetailsRequest().get(k).getBankName());
				}
				map.put("guaFixedAssetBankName"+ j, guaFixedAssetBankName);
				}
		}
				//OTHER CURRENT ASSET TYPE COAPPLICANT
		if (!CommonUtils.isObjectListNull(finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails())) {
			for (int i = 0; i < finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().size(); i++) {
				int j=i+1;
				 Map<String, Object> guaOtherCurrentAsset = new HashMap<>();
				 for (int k = 0; k < finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getAssetDetailResponseList().size(); k++) {
					 int l=k+1;
					 guaOtherCurrentAsset.put("guaCurrentAssetType" + j + l, finalViewResponse.getFinalViewResponse().getGuarantorCommonDetails().get(i).getAssetDetailResponseList().get(k).getAssetType());
				}
				map.put("guaOtherCurrentAsset"+ j, guaOtherCurrentAsset);
				}
	  }
		
		return map;
	}

}
