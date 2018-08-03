package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.gst.util.CommonUtils;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateDirectorIncomeDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateDirectorIncomeDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateDirectorIncomeService;
import com.capitaworld.service.oneform.enums.DirectorRelationshipType;
import com.capitaworld.service.oneform.enums.EducationQualificationNTB;
import com.capitaworld.service.oneform.enums.EmploymentStatusNTB;
import com.capitaworld.service.oneform.enums.EmploymentWithNTB;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.MaritalStatus;
import com.capitaworld.service.oneform.enums.OccupationNatureNTB;
import com.capitaworld.service.oneform.enums.ResidenceStatusRetailMst;

@Service
@Transactional
public class CorporateDirectorIncomeServiceImpl implements CorporateDirectorIncomeService {

	
	@Autowired
	private CorporateDirectorIncomeDetailsRepository incomeDetailsRepository;
	
	@Autowired
	private DirectorBackgroundDetailsRepository backgroundDetailsRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CorporateDirectorIncomeServiceImpl.class.getName());
	
	@Override
	public Boolean saveOrUpdateIncomeDetails(List<CorporateDirectorIncomeRequest> corporateRequest) throws Exception {

		try {
			logger.info("Entering into saveOrUpdateDirectorIncomeDetails=======================>");
			for(CorporateDirectorIncomeRequest corpoObj : corporateRequest) {
				if(CommonUtils.isObjectNullOrEmpty(corpoObj)) {
					continue;
				}
				if(CommonUtils.isObjectNullOrEmpty(corpoObj.getApplicationId()) || CommonUtils.isObjectNullOrEmpty(corpoObj.getDirectorId())
						|| CommonUtils.isObjectNullOrEmpty(corpoObj.getYear())) {
					continue;
				}
				CorporateDirectorIncomeDetails corpoDirReq = incomeDetailsRepository.findByApplicationIdAndDirectorIdAndYear(corpoObj.getApplicationId(), corpoObj.getDirectorId(), corpoObj.getYear());
				if(CommonUtils.isObjectNullOrEmpty(corpoDirReq)) {
					corpoDirReq = new CorporateDirectorIncomeDetails();
					BeanUtils.copyProperties(corpoObj, corpoDirReq);
					corpoDirReq.setCreatedDate(new Date());
					corpoDirReq.setCreatedBy(corpoObj.getUserId());
					corpoDirReq.setIsActive(true);
					incomeDetailsRepository.save(corpoDirReq);
				} else {
					BeanUtils.copyProperties(corpoObj, corpoDirReq,"id","isActive","createdDate","createdBy","modifiedBy","modifiedDate","applicationId","directorId","year");
					corpoDirReq.setModifiedDate(new Date());
					corpoDirReq.setModifiedBy(corpoObj.getUserId());
					incomeDetailsRepository.save(corpoDirReq);
				}
			}	
			return true;
		} catch (Exception e) {
			logger.info("Exception Occured in saveOrUpdateDirectorIncomeDetails=======================>");
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public List<CorporateDirectorIncomeRequest> getDirectorIncomeDetails(Long applicationId)
			throws Exception {
		
		try {
			CorporateDirectorIncomeRequest incomeRequest = null;
			List<CorporateDirectorIncomeDetails> incomeDetails = null;
			List<CorporateDirectorIncomeRequest> incomeDetailsResponse = null;
			logger.info("Entering into getDirectorIncomeDetails=======================>");
			
			if(!(CommonUtils.isObjectNullOrEmpty(applicationId))){
				incomeDetails = incomeDetailsRepository.findByApplicationIdAndIsActive(applicationId, true);
				incomeDetailsResponse = new ArrayList<CorporateDirectorIncomeRequest>();
				if(!CommonUtils.isObjectNullOrEmpty(incomeDetails)){
					for(CorporateDirectorIncomeDetails corpObj:incomeDetails) {
						if(!CommonUtils.isObjectNullOrEmpty(corpObj)) {
							incomeRequest = new CorporateDirectorIncomeRequest();
							BeanUtils.copyProperties(corpObj, incomeRequest);
						}
						incomeDetailsResponse.add(incomeRequest);
				}
					logger.info("Successfully get DirectorIncomeDetails=======================>"+incomeDetailsResponse);
					return incomeDetailsResponse;	
			}
				
		}
		  return null;
		} catch (Exception e) {
			logger.info("Exception Occured in gettingDirectorIncomeDetails=======================>");
			e.printStackTrace();
		}
		  return null;
	}

	@Override
	public List<Map<String, Object>> getDirectorBackGroundDetails(Long applicationId) throws Exception {
		
		try {
			List<DirectorBackgroundDetail> backgroundDetailsList = null;			
			Map<String, Object> map = null;
			List<Map<String, Object>> directorBackgroundlist = null;
			logger.info("Entering into getDirectorBackGroundAndEmployeeDetails=======================>");
			
			if(!(CommonUtils.isObjectNullOrEmpty(applicationId))){
				backgroundDetailsList = backgroundDetailsRepository.listPromotorBackgroundFromAppId(applicationId);
			    System.out.println("Directors List==============>"+backgroundDetailsList);
				directorBackgroundlist = new ArrayList<Map<String, Object>>();
				if(!CommonUtils.isObjectNullOrEmpty(backgroundDetailsList)){
					for(DirectorBackgroundDetail corpObj:backgroundDetailsList) {
						if(!CommonUtils.isObjectNullOrEmpty(corpObj)) {
							map = new HashMap<String, Object>();					
							map.put("directorId", corpObj.getId());
							map.put("address", corpObj.getAddress());
							map.put("pincode", corpObj.getPincode());
							map.put("stateCode", corpObj.getStateCode());
							map.put("city", corpObj.getCity());
							map.put("din", corpObj.getDin());
							map.put("networth", corpObj.getNetworth());
							map.put("applicationId", applicationId);
							map.put("appointmentDate", corpObj.getAppointmentDate());
							map.put("salutationId", corpObj.getSalutationId());
							map.put("panNo", corpObj.getPanNo());
							map.put("designation", corpObj.getDesignation());
							map.put("directorsName", corpObj.getDirectorsName());
							map.put("totalExperience", corpObj.getTotalExperience());
							map.put("dob", corpObj.getDob());
							map.put("mobile", corpObj.getMobile());
							if(!CommonUtils.isObjectNullOrEmpty(corpObj.getGender())) {
								Gender byIdGndr = Gender.getById(corpObj.getGender());
								map.put("gender", !CommonUtils.isObjectNullOrEmpty(byIdGndr) ? byIdGndr.getValue() : "");	
							}
							if(!CommonUtils.isObjectNullOrEmpty(corpObj.getRelationshipType())) {
								DirectorRelationshipType byIdRelation = DirectorRelationshipType.getById(corpObj.getRelationshipType());
								map.put("relationshipType", !CommonUtils.isObjectNullOrEmpty(byIdRelation) ? byIdRelation.getValue() : "");	
							}
							map.put("firstName", corpObj.getFirstName());
							map.put("lastName", corpObj.getLastName());
							map.put("middleName", corpObj.getMiddleName());
							map.put("title", corpObj.getTitle());
							map.put("shareholding", corpObj.getShareholding());
							map.put("isItrCompleted", corpObj.getIsItrCompleted());
							map.put("isCibilCompleted", corpObj.getIsCibilCompleted());
							map.put("isBankStatementCompleted", corpObj.getIsBankStatementCompleted());
							map.put("isOneFormCompleted", corpObj.getIsOneFormCompleted());
							map.put("aadhar", corpObj.getAadhar());
							if(!CommonUtils.isObjectNullOrEmpty(corpObj.getMaritalStatus())) {
								MaritalStatus byIdMarital = MaritalStatus.getById(corpObj.getMaritalStatus());
								map.put("maritalStatus", !CommonUtils.isObjectNullOrEmpty(byIdMarital) ? byIdMarital.getValue() : "");	
							}
							
							map.put("noOfDependent", corpObj.getNoOfDependent());
							if(!CommonUtils.isObjectNullOrEmpty(corpObj.getResidenceType())) {
								ResidenceStatusRetailMst byIdResidentType = ResidenceStatusRetailMst.getById(corpObj.getResidenceType());
								map.put("residenceType", !CommonUtils.isObjectNullOrEmpty(byIdResidentType) ? byIdResidentType.getValue() : "");	
							}
							
							map.put("residenceSinceMonth", corpObj.getResidenceSinceMonth());
							map.put("residenceSinceYear", corpObj.getResidenceSinceYear());
							map.put("isFamilyMemberInBusiness", corpObj.getFamilyMemberInBusiness());
							map.put("stateId", corpObj.getStateId());
							map.put("cityId", corpObj.getCityId());
							map.put("premiseNumber", corpObj.getPremiseNumber());
							map.put("streetName", corpObj.getStreetName());
							map.put("landmark", corpObj.getLandmark());
							
							if(!CommonUtils.isObjectNullOrEmpty(corpObj.getQualificationId())) {
								EducationQualificationNTB byIdEduNtb = EducationQualificationNTB.getById(corpObj.getQualificationId());
								map.put("qualificationId", !CommonUtils.isObjectNullOrEmpty(byIdEduNtb) ? byIdEduNtb.getValue() : "");	
							}
							
							map.put("isMainDirector", corpObj.getMainDirector());
						
							// getting Employee Detail for each Director
							if(!CommonUtils.isObjectNullOrEmpty(corpObj.getEmploymentDetail())) {
							logger.info("Employment Detail======>"+corpObj.getEmploymentDetail());
							map.put("empId", corpObj.getEmploymentDetail().getId()); 
							if(!CommonUtils.isObjectNullOrEmpty(corpObj.getEmploymentDetail().getTypeOfEmployment())) {
								OccupationNatureNTB byIdOccupation = OccupationNatureNTB.getById(Integer.parseInt(corpObj.getEmploymentDetail().getTypeOfEmployment().toString()));
								map.put("typeOfEmployment", !CommonUtils.isObjectNullOrEmpty(byIdOccupation) ? byIdOccupation.getValue() : "");	
							}
							if(!CommonUtils.isObjectNullOrEmpty(corpObj.getEmploymentDetail().getEmploymentWith())) {
								EmploymentWithNTB byIdEmpWith = EmploymentWithNTB.getById(Integer.parseInt(corpObj.getEmploymentDetail().getEmploymentWith().toString()));
								map.put("employmentWith", !CommonUtils.isObjectNullOrEmpty(byIdEmpWith) ? byIdEmpWith.getValue() : "");	
							}
							if(!CommonUtils.isObjectNullOrEmpty(corpObj.getEmploymentDetail().getEmploymentStatus())) {
								EmploymentStatusNTB byIdEmpStatus = EmploymentStatusNTB.getById(Integer.parseInt(corpObj.getEmploymentDetail().getEmploymentStatus().toString()));
								map.put("employmentStatus", !CommonUtils.isObjectNullOrEmpty(byIdEmpStatus) ? byIdEmpStatus.getValue() : "");	
							}
							map.put("totalExperience", corpObj.getEmploymentDetail().getTotalExperience());
							}
							
							directorBackgroundlist.add(map);
						}
						
				}
					logger.info("Successfully get getDirectorBackGroundAndEmployeeDetails=======================>");
					return directorBackgroundlist;	
			}
				
		}
		  return null;
		} catch (Exception e) {
			logger.info("Exception Occured in getDirectorBackGroundAndEmployeeDetails=======================>");
			e.printStackTrace();
		}
		  return null;
	}

}
