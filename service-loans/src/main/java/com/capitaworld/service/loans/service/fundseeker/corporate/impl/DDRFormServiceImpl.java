package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRAuthorizedSignDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRCreditCardDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRCreditorsDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRFormDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDROfficeDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDROtherBankLoanDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRRelWithDbsDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRVehiclesOwnedDetails;
import com.capitaworld.service.loans.model.ddr.DDRAuthorizedSignDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRCreditCardDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRCreditorsDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRFormDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDROfficeDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDROtherBankLoanDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRRelWithDbsDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRVehiclesOwnedDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRAuthorizedSignDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRCreditCardDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRCreditorsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRFormDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDROfficeDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDROtherBankLoanDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRRelWithDbsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRVehiclesOwnedDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.DDRFormService;
import com.capitaworld.service.loans.utils.CommonUtils;

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
	
	
	/**
	 * SAVE DDR FORM DETAILS EXCPET FRAMES AND ONEFORM DETAILS
	 */
	@Override
	public void saveDDRForm(DDRFormDetailsRequest ddrFormDetailsRequest) {
		DDRFormDetails dDRFormDetails = ddrFormDetailsRepository.getByIdAndIsActive(ddrFormDetailsRequest.getId());
		if(CommonUtils.isObjectNullOrEmpty(dDRFormDetails)) {
			logger.info("New DDR Form Saving ------------------------->");
			dDRFormDetails = new DDRFormDetails();
			BeanUtils.copyProperties(ddrFormDetailsRequest, dDRFormDetails,"id");
			dDRFormDetails.setIsActive(true);
			dDRFormDetails.setCreatedBy(ddrFormDetailsRequest.getUserId());
			dDRFormDetails.setCreatedDate(new Date());
			dDRFormDetails.setModifyBy(ddrFormDetailsRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
		} else {
			logger.info("DDR Form Updating ------------------------->" +ddrFormDetailsRequest.getId());
			BeanUtils.copyProperties(ddrFormDetailsRequest, dDRFormDetails,"id","applicationId","userId","isActive");
			dDRFormDetails.setModifyBy(ddrFormDetailsRequest.getUserId());
			dDRFormDetails.setModifyDate(new Date());
		}
		dDRFormDetails = ddrFormDetailsRepository.save(dDRFormDetails);
		logger.info("DDR Form Saved Successfully in Service-----------------> "+dDRFormDetails.getId());
	}
	
	/**
	 * 
	 * GET DDR FORM DETAILS EXCPET FRAMES AND ONEFORM DETAILS
	 */
	@Override
	public DDRFormDetailsRequest get(Long id) {
		DDRFormDetailsRequest dDRFormDetailsRequest = null;
		DDRFormDetails dDRFormDetails = ddrFormDetailsRepository.getByIdAndIsActive(id);
		if(!CommonUtils.isObjectNullOrEmpty(dDRFormDetails)) {
			dDRFormDetailsRequest = new DDRFormDetailsRequest();
			BeanUtils.copyProperties(dDRFormDetails, dDRFormDetailsRequest);
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
		List<DDRAuthorizedSignDetailsRequest> responseList = null;
		if(!CommonUtils.isListNullOrEmpty(listByDDRFormId)) {
			responseList = new ArrayList<>(listByDDRFormId.size());
			for(DDRAuthorizedSignDetails authorizedSignDetails : listByDDRFormId) {
				DDRAuthorizedSignDetailsRequest response = new DDRAuthorizedSignDetailsRequest();
				BeanUtils.copyProperties(authorizedSignDetails, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveAuthorizedSignDetails(List<DDRAuthorizedSignDetailsRequest> dDRAuthSignDetailsList, Long userId) {
		for(DDRAuthorizedSignDetailsRequest dDRAuthSignDetails : dDRAuthSignDetailsList) {
			DDRAuthorizedSignDetails ddrAuthorizedSignDetails = null;
			if(!CommonUtils.isObjectNullOrEmpty(dDRAuthSignDetails.getId())) {
				ddrAuthorizedSignDetails = authorizedSignDetailsRepository.getByIdAndIsActive(dDRAuthSignDetails.getId());
			} else {
				ddrAuthorizedSignDetails = new DDRAuthorizedSignDetails();
				ddrAuthorizedSignDetails.setCreatedBy(userId);
				ddrAuthorizedSignDetails.setCreatedDate(new Date());
				ddrAuthorizedSignDetails.setIsActive(true);
			}
			ddrAuthorizedSignDetails.setModifyBy(userId);
			ddrAuthorizedSignDetails.setModifyDate(new Date());
			BeanUtils.copyProperties(dDRAuthSignDetails, ddrAuthorizedSignDetails,"id","createdBy","createdDate","modifyBy","modifyDate");
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
		List<DDRCreditCardDetailsRequest> responseList = null;
		if(!CommonUtils.isListNullOrEmpty(objList)) {
			responseList = new ArrayList<>(objList.size());
			for(DDRCreditCardDetails obj : objList) {
				DDRCreditCardDetailsRequest response = new DDRCreditCardDetailsRequest();
				BeanUtils.copyProperties(obj, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveCreditCardDetails(List<DDRCreditCardDetailsRequest> requestList, Long userId) {
		for(DDRCreditCardDetailsRequest reqObj : requestList) {
			DDRCreditCardDetails saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = cardDetailsRepository.getByIdAndIsActive(reqObj.getId());
			} else {
				saveObj = new DDRCreditCardDetails();
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
			}
			saveObj.setModifyBy(userId);
			saveObj.setModifyDate(new Date());
			BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate");
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
		List<DDRCreditorsDetailsRequest> responseList = null;
		if(!CommonUtils.isListNullOrEmpty(objList)) {
			responseList = new ArrayList<>(objList.size());
			for(DDRCreditorsDetails obj : objList) {
				DDRCreditorsDetailsRequest response = new DDRCreditorsDetailsRequest();
				BeanUtils.copyProperties(obj, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveCreaditorsDetails(List<DDRCreditorsDetailsRequest> requestList, Long userId) {
		for(DDRCreditorsDetailsRequest reqObj : requestList) {
			DDRCreditorsDetails saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = creditorsDetailsRepository.getByIdAndIsActive(reqObj.getId());
			} else {
				saveObj = new DDRCreditorsDetails();
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
			}
			saveObj.setModifyBy(userId);
			saveObj.setModifyDate(new Date());
			BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate");
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
		List<DDROfficeDetailsRequest> responseList = null;
		if(!CommonUtils.isListNullOrEmpty(objList)) {
			responseList = new ArrayList<>(objList.size());
			for(DDROfficeDetails obj : objList) {
				DDROfficeDetailsRequest response = new DDROfficeDetailsRequest();
				BeanUtils.copyProperties(obj, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveOfficeDetails(List<DDROfficeDetailsRequest> requestList, Long userId,Integer officeType) {
		for(DDROfficeDetailsRequest reqObj : requestList) {
			DDROfficeDetails saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = ddrOfficeDetailsRepository.getByIdAndIsActive(reqObj.getId());
			} else {
				saveObj = new DDROfficeDetails();
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
				saveObj.setOfficeType(officeType);
			}
			saveObj.setModifyBy(userId);
			saveObj.setModifyDate(new Date());
			BeanUtils.copyProperties(reqObj, saveObj,"id","officeType","createdBy","createdDate","modifyBy","modifyDate");
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
		List<DDROtherBankLoanDetailsRequest> responseList = null;
		if(!CommonUtils.isListNullOrEmpty(objList)) {
			responseList = new ArrayList<>(objList.size());
			for(DDROtherBankLoanDetails obj : objList) {
				DDROtherBankLoanDetailsRequest response = new DDROtherBankLoanDetailsRequest();
				BeanUtils.copyProperties(obj, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveOtherBankLoanDetails(List<DDROtherBankLoanDetailsRequest> requestList, Long userId) {
		for(DDROtherBankLoanDetailsRequest reqObj : requestList) {
			DDROtherBankLoanDetails saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = bankLoanDetailsRepository.getByIdAndIsActive(reqObj.getId());
			} else {
				saveObj = new DDROtherBankLoanDetails();
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
			}
			saveObj.setModifyBy(userId);
			saveObj.setModifyDate(new Date());
			BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate");
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
		List<DDRRelWithDbsDetailsRequest> responseList = null;
		if(!CommonUtils.isListNullOrEmpty(objList)) {
			responseList = new ArrayList<>(objList.size());
			for(DDRRelWithDbsDetails obj : objList) {
				DDRRelWithDbsDetailsRequest response = new DDRRelWithDbsDetailsRequest();
				BeanUtils.copyProperties(obj, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveRelWithDBSDetails(List<DDRRelWithDbsDetailsRequest> requestList, Long userId) {
		for(DDRRelWithDbsDetailsRequest reqObj : requestList) {
			DDRRelWithDbsDetails saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = dbsDetailsRepository.getByIdAndIsActive(reqObj.getId());
			} else {
				saveObj = new DDRRelWithDbsDetails();
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
			}
			saveObj.setModifyBy(userId);
			saveObj.setModifyDate(new Date());
			BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate");
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
		List<DDRVehiclesOwnedDetailsRequest> responseList = null;
		if(!CommonUtils.isListNullOrEmpty(objList)) {
			responseList = new ArrayList<>(objList.size());
			for(DDRVehiclesOwnedDetails obj : objList) {
				DDRVehiclesOwnedDetailsRequest response = new DDRVehiclesOwnedDetailsRequest();
				BeanUtils.copyProperties(obj, response);
				responseList.add(response);
			}
		}
		return responseList;
		
	}
	
	public void saveVehiclesOwnedDetails(List<DDRVehiclesOwnedDetailsRequest> requestList, Long userId) {
		for(DDRVehiclesOwnedDetailsRequest reqObj : requestList) {
			DDRVehiclesOwnedDetails saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = vehiclesOwnedDetailsRepository.getByIdAndIsActive(reqObj.getId());
			} else {
				saveObj = new DDRVehiclesOwnedDetails();
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
			}
			saveObj.setModifyBy(userId);
			saveObj.setModifyDate(new Date());
			BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate");
			vehiclesOwnedDetailsRepository.save(saveObj);
		}
	}
	
}
