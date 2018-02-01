package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRAuthorizedSignDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRCreditCardDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRCreditorsDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRFormDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDROfficeDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDROtherBankLoanDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRRelWithDbsDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRVehiclesOwnedDetails;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.OwnershipDetailRequest;
import com.capitaworld.service.loans.model.OwnershipDetailResponse;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.ddr.DDRAuthorizedSignDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRCreditCardDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRCreditorsDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRFormDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDROfficeDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDROneFormResponse;
import com.capitaworld.service.loans.model.ddr.DDROtherBankLoanDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRRelWithDbsDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRVehiclesOwnedDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRAuthorizedSignDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRCreditCardDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRCreditorsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRFormDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDROfficeDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDROtherBankLoanDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRRelWithDbsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRVehiclesOwnedDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.DDRFormService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExistingProductDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OwnershipDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PromotorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProposedProductDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.utils.CommonUtils.DDRFrames;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.Constitution;
import com.capitaworld.service.oneform.enums.EstablishmentMonths;
import com.capitaworld.service.oneform.enums.NatureFacility;
import com.capitaworld.service.oneform.enums.ShareHoldingCategory;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Service
@Transactional
public class DDRFormServiceImpl implements DDRFormService{

	private static final Logger logger = LoggerFactory.getLogger(DDRFormServiceImpl.class);
	
	@Autowired
	private DDRFormDetailsRepository ddrFormDetailsRepository;
	
	@Autowired
	private DDRAuthorizedSignDetailsRepository authorizedSignDetailsRepository;
	
	@Autowired 
	private DDRCreditCardDetailsRepository cardDetailsRepository;
	
	@Autowired
	private DDRCreditorsDetailsRepository creditorsDetailsRepository;
	
	@Autowired
	private DDROtherBankLoanDetailsRepository bankLoanDetailsRepository;
	
	@Autowired
	private DDRRelWithDbsDetailsRepository dbsDetailsRepository;
	
	@Autowired
	private DDRVehiclesOwnedDetailsRepository vehiclesOwnedDetailsRepository;
	
	@Autowired
	private DDROfficeDetailsRepository ddrOfficeDetailsRepository;
	
	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;
	
	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private UsersClient usersClient;

	@Autowired
	private PromotorBackgroundDetailsService promotorBackgroundDetailsService;
	
	@Autowired
	private OwnershipDetailsService ownershipDetailsService;
	
	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;
	
	@Autowired
	private ProposedProductDetailsService proposedProductDetailsService; 
	
	@Autowired
	private ExistingProductDetailsService existingProductDetailsService;
	
	/**
	 * SAVE DDR FORM DETAILS EXCPET FRAMES AND ONEFORM DETAILS
	 * @throws Exception 
	 */
	@Override
	public void saveDDRForm(DDRFormDetailsRequest ddrFormDetailsRequest) throws Exception {
		
		Long userId = ddrFormDetailsRequest.getUserId();
		
		try {
			DDRFormDetails dDRFormDetails = ddrFormDetailsRepository.getByIdAndAppIdAndIsActive(ddrFormDetailsRequest.getId(),ddrFormDetailsRequest.getApplicationId());
			if(CommonUtils.isObjectNullOrEmpty(dDRFormDetails)) {
				logger.info("New DDR Form Saving ------------------------->");
				dDRFormDetails = new DDRFormDetails();
				BeanUtils.copyProperties(ddrFormDetailsRequest, dDRFormDetails,"id");
				dDRFormDetails.setIsActive(true);
				dDRFormDetails.setCreatedBy(userId);
				dDRFormDetails.setCreatedDate(new Date());
				dDRFormDetails.setModifyBy(userId);
				dDRFormDetails.setModifyDate(new Date());
			} else {
				logger.info("DDR Form Updating ------------------------->" +ddrFormDetailsRequest.getId());
				BeanUtils.copyProperties(ddrFormDetailsRequest, dDRFormDetails,"id","applicationId","userId","isActive");
				dDRFormDetails.setModifyBy(userId);
				dDRFormDetails.setModifyDate(new Date());
			}
			dDRFormDetails = ddrFormDetailsRepository.save(dDRFormDetails);
			
			
			//SAVE ALL LIST DATA
			saveAuthorizedSignDetails(ddrFormDetailsRequest.getdDRAuthSignDetailsList(), userId,dDRFormDetails.getId());
			saveCreaditorsDetails(ddrFormDetailsRequest.getdDRCreditorsDetailsList(), userId, dDRFormDetails.getId());
			saveCreditCardDetails(ddrFormDetailsRequest.getdDRCreditCardDetailsList(), userId, dDRFormDetails.getId());
			saveOfficeDetails(ddrFormDetailsRequest.getdDROperatingOfficeList(), userId,DDRFrames.OPERATING_OFFICE.getValue(),dDRFormDetails.getId());
			saveOfficeDetails(ddrFormDetailsRequest.getdDRRegisteredOfficeList(), userId,DDRFrames.REGISTERED_OFFICE.getValue(),dDRFormDetails.getId());
			saveOtherBankLoanDetails(ddrFormDetailsRequest.getdDROtherBankLoanDetailsList(), userId,dDRFormDetails.getId());
			saveRelWithDBSDetails(ddrFormDetailsRequest.getdDRRelWithDbsDetailsList(), userId,dDRFormDetails.getId());
			saveVehiclesOwnedDetails(ddrFormDetailsRequest.getdDRVehiclesOwnedDetailsList(), userId,dDRFormDetails.getId());
			logger.info("DDR Form Saved Successfully in Service-----------------> "+dDRFormDetails.getId());	
		} catch (Exception e) {
			logger.info("Throw Exception while saving ddr form");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
		
	}
	
	/**
	 * 
	 * GET DDR FORM DETAILS EXCPET FRAMES AND ONEFORM DETAILS
	 */
	@Override
	public DDRFormDetailsRequest get(Long appId) {
		DDRFormDetailsRequest dDRFormDetailsRequest = null;
		DDRFormDetails dDRFormDetails = ddrFormDetailsRepository.getByAppIdAndIsActive(appId);
		if(!CommonUtils.isObjectNullOrEmpty(dDRFormDetails)) {
			Long ddrFormId = dDRFormDetails.getId();
			dDRFormDetailsRequest = new DDRFormDetailsRequest();
			BeanUtils.copyProperties(dDRFormDetails, dDRFormDetailsRequest);
			dDRFormDetailsRequest.setdDRAuthSignDetailsList(getAuthorizedSignDetails(ddrFormId));
			dDRFormDetailsRequest.setdDRCreditCardDetailsList(getCreditCardDetails(ddrFormId));
			dDRFormDetailsRequest.setdDRCreditorsDetailsList(getCreaditorsDetails(ddrFormId));
			dDRFormDetailsRequest.setdDROperatingOfficeList(getOfficeDetails(ddrFormId, DDRFrames.OPERATING_OFFICE.getValue()));
			dDRFormDetailsRequest.setdDRRegisteredOfficeList(getOfficeDetails(ddrFormId, DDRFrames.REGISTERED_OFFICE.getValue()));
			dDRFormDetailsRequest.setdDROtherBankLoanDetailsList(getOtherBankLoanDetails(ddrFormId));
			dDRFormDetailsRequest.setdDRRelWithDbsDetailsList(getRelWithDBSDetails(ddrFormId));
			dDRFormDetailsRequest.setdDRVehiclesOwnedDetailsList(getVehiclesOwnedDetails(ddrFormId));
		}
		return dDRFormDetailsRequest;
	}
	
	/**
	 * GET AUTHORIZED SIGN DETAILS LIST BY DDR FORM ID
	 * @param ddrFormId
	 * @return
	 */
	public List<DDRAuthorizedSignDetailsRequest> getAuthorizedSignDetails(Long ddrFormId){
		List<DDRAuthorizedSignDetails> listByDDRFormId = authorizedSignDetailsRepository.getListByDDRFormId(ddrFormId);
		List<DDRAuthorizedSignDetailsRequest> responseList = new ArrayList<>(listByDDRFormId.size());
		if(!CommonUtils.isListNullOrEmpty(listByDDRFormId)) {
			for(DDRAuthorizedSignDetails authorizedSignDetails : listByDDRFormId) {
				DDRAuthorizedSignDetailsRequest response = new DDRAuthorizedSignDetailsRequest();
				BeanUtils.copyProperties(authorizedSignDetails, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveAuthorizedSignDetails(List<DDRAuthorizedSignDetailsRequest> dDRAuthSignDetailsList, Long userId, Long ddrFormId) {
		for(DDRAuthorizedSignDetailsRequest dDRAuthSignDetails : dDRAuthSignDetailsList) {
			DDRAuthorizedSignDetails ddrAuthorizedSignDetails = null;
			if(!CommonUtils.isObjectNullOrEmpty(dDRAuthSignDetails.getId())) {
				ddrAuthorizedSignDetails = authorizedSignDetailsRepository.getByIdAndIsActive(dDRAuthSignDetails.getId());
			}
			if(CommonUtils.isObjectNullOrEmpty(ddrAuthorizedSignDetails)) {
				ddrAuthorizedSignDetails = new DDRAuthorizedSignDetails();
				BeanUtils.copyProperties(dDRAuthSignDetails, ddrAuthorizedSignDetails,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId","isActive");
				ddrAuthorizedSignDetails.setCreatedBy(userId);
				ddrAuthorizedSignDetails.setCreatedDate(new Date());
				ddrAuthorizedSignDetails.setIsActive(true);
				ddrAuthorizedSignDetails.setDdrFormId(ddrFormId);
			} else {
				BeanUtils.copyProperties(dDRAuthSignDetails, ddrAuthorizedSignDetails,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId");
			}
			ddrAuthorizedSignDetails.setModifyBy(userId);
			ddrAuthorizedSignDetails.setModifyDate(new Date());
			authorizedSignDetailsRepository.save(ddrAuthorizedSignDetails);
		}
	}
	
	/**
	 * GET CREDIT CARD DETAILS BY DDR FORM ID
	 * @param ddrFormId
	 * @return
	 */
	public List<DDRCreditCardDetailsRequest> getCreditCardDetails(Long ddrFormId){
		List<DDRCreditCardDetails> objList = cardDetailsRepository.getListByDDRFormId(ddrFormId);
		List<DDRCreditCardDetailsRequest> responseList = new ArrayList<>(objList.size());
		if(!CommonUtils.isListNullOrEmpty(objList)) {
			for(DDRCreditCardDetails obj : objList) {
				DDRCreditCardDetailsRequest response = new DDRCreditCardDetailsRequest();
				BeanUtils.copyProperties(obj, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveCreditCardDetails(List<DDRCreditCardDetailsRequest> requestList, Long userId,Long ddrFormId) {
		for(DDRCreditCardDetailsRequest reqObj : requestList) {
			DDRCreditCardDetails saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = cardDetailsRepository.getByIdAndIsActive(reqObj.getId());
			}
			if(CommonUtils.isObjectNullOrEmpty(saveObj)){
				saveObj = new DDRCreditCardDetails();
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId","isActive");
				saveObj.setDdrFormId(ddrFormId);
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
			} else {
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId");				
			}
			saveObj.setModifyBy(userId);
			saveObj.setModifyDate(new Date());
			cardDetailsRepository.save(saveObj);
		}
	}
	
	
	/**
	 * GET CREADITORS DETAILS BY DDR FORM ID
	 * @param ddrFormId
	 * @return
	 */
	public List<DDRCreditorsDetailsRequest> getCreaditorsDetails(Long ddrFormId){
		List<DDRCreditorsDetails> objList = creditorsDetailsRepository.getListByDDRFormId(ddrFormId);
		List<DDRCreditorsDetailsRequest> responseList = new ArrayList<>(objList.size());
		if(!CommonUtils.isListNullOrEmpty(objList)) {
			for(DDRCreditorsDetails obj : objList) {
				DDRCreditorsDetailsRequest response = new DDRCreditorsDetailsRequest();
				BeanUtils.copyProperties(obj, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveCreaditorsDetails(List<DDRCreditorsDetailsRequest> requestList, Long userId, Long ddrFormId) {
		for(DDRCreditorsDetailsRequest reqObj : requestList) {
			DDRCreditorsDetails saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = creditorsDetailsRepository.getByIdAndIsActive(reqObj.getId());
			}
			if(CommonUtils.isObjectNullOrEmpty(saveObj)){
				saveObj = new DDRCreditorsDetails();
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId","isActive");
				saveObj.setDdrFormId(ddrFormId);
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
			} else {
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId");				
			}
			saveObj.setModifyBy(userId);
			saveObj.setModifyDate(new Date());
			creditorsDetailsRepository.save(saveObj);
		}
	}
	
	/**
	 * GET OFFICE DETAILS BASE ON OFFICE TYPE BY DDR FORM ID
	 * @param ddrFormId
	 * @param officeType :- Two Type First is REGISTERED OFFICE(ID: 4) and Second is OPERATING OFFICE(ID: 4)
	 * @return
	 */
	public List<DDROfficeDetailsRequest> getOfficeDetails(Long ddrFormId,Integer officeType){
		List<DDROfficeDetails> objList = ddrOfficeDetailsRepository.getListByDDRFormId(ddrFormId,officeType);
		List<DDROfficeDetailsRequest> responseList = new ArrayList<>(objList.size());
		if(!CommonUtils.isListNullOrEmpty(objList)) {
			for(DDROfficeDetails obj : objList) {
				DDROfficeDetailsRequest response = new DDROfficeDetailsRequest();
				BeanUtils.copyProperties(obj, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveOfficeDetails(List<DDROfficeDetailsRequest> requestList, Long userId,Integer officeType,Long ddrFormId) {
		for(DDROfficeDetailsRequest reqObj : requestList) {
			DDROfficeDetails saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = ddrOfficeDetailsRepository.getByIdAndIsActive(reqObj.getId());
			}
			
			if(CommonUtils.isObjectNullOrEmpty(saveObj)){
				saveObj = new DDROfficeDetails();
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId","isActive");
				saveObj.setDdrFormId(ddrFormId);
				saveObj.setOfficeType(officeType);
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
			} else {
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId");				
			}
			saveObj.setModifyBy(userId);
			saveObj.setModifyDate(new Date());
			ddrOfficeDetailsRepository.save(saveObj);
		}
	}
	
	
	/**
	 * GET OTHER BANK DETAILS BY DDR FORM ID
	 * @param ddrFormId
	 * @return
	 */
	public List<DDROtherBankLoanDetailsRequest> getOtherBankLoanDetails(Long ddrFormId){
		List<DDROtherBankLoanDetails> objList = bankLoanDetailsRepository.getListByDDRFormId(ddrFormId);
		List<DDROtherBankLoanDetailsRequest> responseList = new ArrayList<>(objList.size());
		if(!CommonUtils.isListNullOrEmpty(objList)) {
			for(DDROtherBankLoanDetails obj : objList) {
				DDROtherBankLoanDetailsRequest response = new DDROtherBankLoanDetailsRequest();
				BeanUtils.copyProperties(obj, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveOtherBankLoanDetails(List<DDROtherBankLoanDetailsRequest> requestList, Long userId, Long ddrFormId) {
		for(DDROtherBankLoanDetailsRequest reqObj : requestList) {
			DDROtherBankLoanDetails saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = bankLoanDetailsRepository.getByIdAndIsActive(reqObj.getId());
			}
			
			if(CommonUtils.isObjectNullOrEmpty(saveObj)){
				saveObj = new DDROtherBankLoanDetails();
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId","isActive");
				saveObj.setDdrFormId(ddrFormId);
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
			} else {
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId");				
			}
			saveObj.setModifyBy(userId);
			saveObj.setModifyDate(new Date());
			bankLoanDetailsRepository.save(saveObj);
		}
	}
	
	/**
	 * GET RELATION WITH DBS DETAILS BY DDR FORM ID
	 * @param ddrFormId
	 * @return
	 */
	public List<DDRRelWithDbsDetailsRequest> getRelWithDBSDetails(Long ddrFormId){
		List<DDRRelWithDbsDetails> objList = dbsDetailsRepository.getListByDDRFormId(ddrFormId);
		List<DDRRelWithDbsDetailsRequest> responseList = new ArrayList<>(objList.size());
		if(!CommonUtils.isListNullOrEmpty(objList)) {
			for(DDRRelWithDbsDetails obj : objList) {
				DDRRelWithDbsDetailsRequest response = new DDRRelWithDbsDetailsRequest();
				BeanUtils.copyProperties(obj, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveRelWithDBSDetails(List<DDRRelWithDbsDetailsRequest> requestList, Long userId, Long ddrFormId) {
		for(DDRRelWithDbsDetailsRequest reqObj : requestList) {
			DDRRelWithDbsDetails saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = dbsDetailsRepository.getByIdAndIsActive(reqObj.getId());
			}
			
			if(CommonUtils.isObjectNullOrEmpty(saveObj)){
				saveObj = new DDRRelWithDbsDetails();
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId","isActive");
				saveObj.setDdrFormId(ddrFormId);
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
			} else {
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId");				
			}
			saveObj.setModifyBy(userId);
			saveObj.setModifyDate(new Date());
			dbsDetailsRepository.save(saveObj);
		}
	}
	
	/**
	 * GET VEHICLES OWNED DETAILS BY DDR FORM ID
	 * @param ddrFormId
	 * @return
	 */
	public List<DDRVehiclesOwnedDetailsRequest> getVehiclesOwnedDetails(Long ddrFormId){
		List<DDRVehiclesOwnedDetails> objList = vehiclesOwnedDetailsRepository.getListByDDRFormId(ddrFormId);
		List<DDRVehiclesOwnedDetailsRequest> responseList = new ArrayList<>(objList.size());
		if(!CommonUtils.isListNullOrEmpty(objList)) {
			for(DDRVehiclesOwnedDetails obj : objList) {
				DDRVehiclesOwnedDetailsRequest response = new DDRVehiclesOwnedDetailsRequest();
				BeanUtils.copyProperties(obj, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveVehiclesOwnedDetails(List<DDRVehiclesOwnedDetailsRequest> requestList, Long userId, Long ddrFormId) {
		for(DDRVehiclesOwnedDetailsRequest reqObj : requestList) {
			DDRVehiclesOwnedDetails saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = vehiclesOwnedDetailsRepository.getByIdAndIsActive(reqObj.getId());
			}
			
			if(CommonUtils.isObjectNullOrEmpty(saveObj)){
				saveObj = new DDRVehiclesOwnedDetails();
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId","isActive");
				saveObj.setDdrFormId(ddrFormId);
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
			} else {
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId");				
			}
			saveObj.setModifyBy(userId);
			saveObj.setModifyDate(new Date());
			vehiclesOwnedDetailsRepository.save(saveObj);
		}
	}
	
	public void getOneFormDetails(Long userId, Long applicationId) {

		DDROneFormResponse response = new DDROneFormResponse();

		//---------------------------------------------------PROFILE ------------------------------------------------------------------------
		
		CorporateApplicantDetail applicantDetail = corporateApplicantDetailRepository.getByApplicationAndUserId(userId,applicationId);
		if(CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
			logger.info("Corporate Profile Details NUll or Empty!! ----------------->" + applicationId);
			return;
		}
		
		//ORGANIZATION NAME :- LINENO:6
		response.setNameOfBorrower(applicantDetail.getOrganisationName());
		
		//GET REGISTERED ADDRESS :- LINENO:7
		String regOfficeAdd = "";
		regOfficeAdd = !CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredPremiseNumber()) ? applicantDetail.getRegisteredPremiseNumber() + ", " : "";
		regOfficeAdd += !CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredStreetName()) ? applicantDetail.getRegisteredStreetName() + ", " : "";
		regOfficeAdd += !CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredLandMark()) ? applicantDetail.getRegisteredLandMark() + ", " : "";
		String countryName = getCountryName(applicantDetail.getRegisteredCountryId());
		regOfficeAdd += !CommonUtils.isObjectNullOrEmpty(countryName) ? countryName + ", " : "";
		String stateName = getStateName(applicantDetail.getRegisteredStateId());
		regOfficeAdd += !CommonUtils.isObjectNullOrEmpty(stateName) ? stateName+ ", " : "";
		String cityName = getCityName(applicantDetail.getRegisteredCityId());
		regOfficeAdd += !CommonUtils.isObjectNullOrEmpty(cityName) ? cityName : "";
		response.setRegOfficeAddress(!CommonUtils.isObjectNullOrEmpty(regOfficeAdd) ? regOfficeAdd : "NA");
		
		//Contact Details  :- LINENO:8
		response.setContactNo(applicantDetail.getLandlineNo());
		
		//GET ADMINISRATIVE (Corporate Office) ADDRESS  :- LINENO:9
		String admntOfficeAdd = "";
		admntOfficeAdd = !CommonUtils.isObjectNullOrEmpty(applicantDetail.getAdministrativePremiseNumber()) ? applicantDetail.getAdministrativePremiseNumber() + ", " : "";
		admntOfficeAdd += !CommonUtils.isObjectNullOrEmpty(applicantDetail.getAdministrativeStreetName()) ? applicantDetail.getAdministrativeStreetName() + ", " : "";
		admntOfficeAdd += !CommonUtils.isObjectNullOrEmpty(applicantDetail.getAdministrativeLandMark()) ? applicantDetail.getAdministrativeLandMark() + ", " : "";
		String admntCountryName = getCountryName(applicantDetail.getAdministrativeCountryId());
		admntOfficeAdd += !CommonUtils.isObjectNullOrEmpty(admntCountryName) ? admntCountryName + ", " : "";
		String admntStateName = getStateName(applicantDetail.getAdministrativeStateId());
		admntOfficeAdd += !CommonUtils.isObjectNullOrEmpty(admntStateName) ? admntStateName+ ", " : "";
		String admntCityName = getCityName(applicantDetail.getAdministrativeCityId());
		admntOfficeAdd += !CommonUtils.isObjectNullOrEmpty(admntCityName) ? admntCityName : "";
		response.setCorpOfficeAddress(!CommonUtils.isObjectNullOrEmpty(admntOfficeAdd) ? admntOfficeAdd : "NA");
		
		//GET RERGISTERED EMAIL ID  :- LINENO:11
		try {
			UserResponse userResponse = usersClient.getEmailAndNameByUserId(userId);
			if (!CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
				UsersRequest request = MultipleJSONObjectHelper
    					.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
    			if(!CommonUtils.isObjectNullOrEmpty(request)) {
    				response.setRegEmailId(request.getEmail());
    			}
    		}	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//GET PROFILE CONSTITUTION  :- LINENO:13
		response.setConstitution(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getConstitutionId()) ? Constitution.getById(applicantDetail.getConstitutionId()).getValue() : "NA");
		
		String establishMentYear = !CommonUtils.isObjectNullOrEmpty(applicantDetail.getEstablishmentMonth()) ? EstablishmentMonths.getById(applicantDetail.getEstablishmentMonth()).getValue() : "";
		if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getEstablishmentYear())) {
			try {
				OneFormResponse establishmentYearResponse = oneFormClient.getYearByYearId(
						CommonUtils.isObjectNullOrEmpty(applicantDetail.getEstablishmentYear()) ? null
								: applicantDetail.getEstablishmentYear().longValue());
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) establishmentYearResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					establishMentYear += " "+ masterResponse.getValue();
				} 
			} catch (Exception e) {
				logger.info("Throw Exception while get establishment year in DDR OneForm");
				e.printStackTrace();
			}
		}
		//GET PROFILE ESTABLISHMENT YEAR  :- LINENO:14
		response.setEstablishMentYear(!CommonUtils.isObjectNullOrEmpty(establishMentYear) ? establishMentYear : "NA");
		
		//ABOUT US :- LINENO:15
		response.setAboutMe(applicantDetail.getAboutUs());
		
		
		
		//---------------------------------------------------PRIMARY ------------------------------------------------------------------------
		
		//PROMOTOR BACKGROUND DETAILS :- LINENO:12
		try {
			List<PromotorBackgroundDetailRequest> promoBackReqList = promotorBackgroundDetailsService.getPromotorBackgroundDetailList(applicationId, userId);
			List<PromotorBackgroundDetailResponse> promoBackRespList = new ArrayList<>(promoBackReqList.size());
			PromotorBackgroundDetailResponse promoBackResp = null;
			for (PromotorBackgroundDetailRequest promBackReq : promoBackReqList) {
				promoBackResp = new PromotorBackgroundDetailResponse();
				BeanUtils.copyProperties(promBackReq, promoBackResp);
				promoBackResp.setPanNo(promBackReq.getPanNo().toUpperCase());
				promoBackResp.setPromotorsName((promBackReq.getSalutationId() != null ? Title.getById(promBackReq.getSalutationId()).getValue() : null )+ " " + promBackReq.getPromotorsName());
				promoBackRespList.add(promoBackResp);
			}
			response.setPromoBackRespList(promoBackRespList);
		} catch (Exception e) {
			logger.info("Throw Exception While Get Primary Promotor Background Details in DDR OneForm");
			e.printStackTrace();
		}
		
		//OWNERSHIP DETAILS :- LINENO:12
		try {
			List<OwnershipDetailRequest> ownershipReqList = ownershipDetailsService.getOwnershipDetailList(applicationId, userId);
			List<OwnershipDetailResponse> ownershipRespList = new ArrayList<>(ownershipReqList.size());
			OwnershipDetailResponse ownershipResp = null;
			for (OwnershipDetailRequest ownershipReq : ownershipReqList) {
				ownershipResp = new OwnershipDetailResponse();
				BeanUtils.copyProperties(ownershipReq, ownershipResp);
				ownershipResp.setShareHoldingCategory(ShareHoldingCategory.getById(ownershipReq.getShareHoldingCategoryId()).getValue());
				ownershipRespList.add(ownershipResp);
			}
			response.setOwnershipRespList(ownershipRespList);
		} catch (Exception e) {
			logger.info("Throw Exception While Get Primary Ownership Details in DDR OneForm");
			e.printStackTrace();
		}
		
		//CURRENT FINANCIAL ARRANGEMENT DETAILS (Existing Banker(s) Details) :- LINENO:21
		try {
			List<FinancialArrangementsDetailRequest> fincArrngDetailReqList = financialArrangementDetailsService.getFinancialArrangementDetailsList(applicationId, userId);
			List<FinancialArrangementsDetailResponse> fincArrngDetailResList = new ArrayList<>(fincArrngDetailReqList.size());
			FinancialArrangementsDetailResponse fincArragDetailResp = null;
			for (FinancialArrangementsDetailRequest fincArrngDetailReq : fincArrngDetailReqList) {
				fincArragDetailResp = new FinancialArrangementsDetailResponse();
				BeanUtils.copyProperties(fincArrngDetailReq, fincArragDetailResp);
				fincArragDetailResp.setFacilityNature(NatureFacility.getById(fincArrngDetailReq.getFacilityNatureId()).getValue());
				fincArrngDetailResList.add(fincArragDetailResp);
			}
			response.setFincArrngDetailResList(fincArrngDetailResList);
		} catch (Exception e) {
			logger.info("Throw Exception While Get Current Financial Arangement Details in DDR OneForm");
			e.printStackTrace();
		}
		
		
		
		//PRODUCT DETAILS PROPOSED AND EXISTING (Description of Products) :- LINENO:111
		try {
			response.setProposedProductDetailList(proposedProductDetailsService.getProposedProductDetailList(applicationId, userId));
			response.setExistingProductDetailList(existingProductDetailsService.getExistingProductDetailList(applicationId, userId));
		} catch (Exception e) {
			logger.info("Throw Exception While Get Product Proposed and Existing details in DDR OneForm");
			e.printStackTrace();
		}
		
		

		
	}
	
	@SuppressWarnings("unchecked")
	private String getCityName(Long cityId) {
		try {
			if(CommonUtils.isObjectNullOrEmpty(cityId)) {
				return null;
			}
			List<Long> cityList = new ArrayList<>(1);
			cityList.add(cityId);
			OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
					.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
				MasterResponse masterResponse = MultipleJSONObjectHelper
						.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
				return masterResponse.getValue();
			}
		} catch (Exception e) {
			logger.info("Throw Exception while get city name by city Id in DDR Onform");
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private String getStateName(Integer stateId) {
		try {
			if(CommonUtils.isObjectNullOrEmpty(stateId)) {
				return null;
			}
			List<Long> stateList = new ArrayList<>(1);
			stateList.add(stateId.longValue());
			OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
					.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
				MasterResponse masterResponse = MultipleJSONObjectHelper
						.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
				return masterResponse.getValue();
			}
		} catch (Exception e) {
			logger.info("Throw Exception while get city name by city Id in DDR Onform");
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private String getCountryName(Integer country) {
		try {
			if(CommonUtils.isObjectNullOrEmpty(country)) {
				return null;
			}
			List<Long> countryList = new ArrayList<>(1);
			countryList.add(country.longValue());
			OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
					.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
				MasterResponse masterResponse = MultipleJSONObjectHelper
						.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
				return masterResponse.getValue();
			}
		} catch (Exception e) {
			logger.info("Throw Exception while get country name by country Id in DDR Onform");
			e.printStackTrace();
		}
		return null;
	}
	
}
