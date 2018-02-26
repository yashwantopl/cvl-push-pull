package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.BalanceSheetDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.ProfitibilityStatementDetail;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRAuthorizedSignDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRCreditCardDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRCreditorsDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRFamilyDirectorsDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRFinancialSummary;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRFormDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDROfficeDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDROtherBankLoanDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRRelWithDbsDetails;
import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRVehiclesOwnedDetails;
import com.capitaworld.service.loans.domain.fundseeker.retail.ReferencesRetailDetail;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.OwnershipDetailRequest;
import com.capitaworld.service.loans.model.OwnershipDetailResponse;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.common.DocumentUploadFlagRequest;
import com.capitaworld.service.loans.model.ddr.DDRAuthorizedSignDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRCMACalculationResponse;
import com.capitaworld.service.loans.model.ddr.DDRCreditCardDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRCreditorsDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRFamilyDirectorsDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRFinancialSummaryRequest;
import com.capitaworld.service.loans.model.ddr.DDRFormDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDROfficeDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDROneFormResponse;
import com.capitaworld.service.loans.model.ddr.DDROtherBankLoanDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRRelWithDbsDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDRVehiclesOwnedDetailsRequest;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.BalanceSheetDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ProfitibilityStatementDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRAuthorizedSignDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRCreditCardDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRCreditorsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRFamilyDirectorsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRFinancialSummaryRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRFormDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDROfficeDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDROtherBankLoanDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRRelWithDbsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ddr.DDRVehiclesOwnedDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.ReferenceRetailDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssociatedConcernDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DDRFormService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExistingProductDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OwnershipDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PromotorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProposedProductDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.SecurityCorporateDetailsService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.DDRFinancialSummaryFields;
import com.capitaworld.service.loans.utils.CommonUtils.DDRFinancialSummaryToBeFields;
import com.capitaworld.service.loans.utils.CommonUtils.DDRFrames;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
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
	
	@Autowired
	private DDRFinancialSummaryRepository financialSummaryRepository;
	
	@Autowired
	private AssociatedConcernDetailService associatedConcernDetailService;
	
	@Autowired
	private DDRFamilyDirectorsDetailsRepository familyDirectorsDetailsRepository;
	
	@Autowired
	private OperatingStatementDetailsRepository operatingStatementDetailsRepository;
	
	@Autowired
	private ProfitibilityStatementDetailRepository profitibilityStatementDetailRepository;
	
	@Autowired
	private AssetsDetailsRepository assetsDetailsRepository;
	
	@Autowired
	private LiabilitiesDetailsRepository liabilitiesDetailsRepository;
	
	@Autowired
	private BalanceSheetDetailRepository balanceSheetDetailRepository;
	
	@Autowired
	private ReferenceRetailDetailsRepository referenceRetailDetailsRepository;
	
	@Autowired
	private SecurityCorporateDetailsService securityCorporateDetailsService;
	
	@Autowired
	private DirectorBackgroundDetailsService backgroundDetailsService;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
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
			saveFinancialSummary(ddrFormDetailsRequest.getdDRFinancialSummaryList(), userId,dDRFormDetails.getId());
			saveFamilyDirectorsDetails(ddrFormDetailsRequest.getdDRFamilyDirectorsList(), userId, dDRFormDetails.getId());
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
	public DDRFormDetailsRequest get(Long appId,Long userId) {
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
			dDRFormDetailsRequest.setdDRFinancialSummaryList(getFinancialSummary(ddrFormId));
			dDRFormDetailsRequest.setdDRFamilyDirectorsList(getFamilyDirectorsDetails(ddrFormId,appId,userId));
			dDRFormDetailsRequest.setProvisionalTotalSales(getCMATotalSalesByAppIdAndYear(appId, "2018"));
			dDRFormDetailsRequest.setLastYearTotalSales(getCMATotalSalesByAppIdAndYear(appId, "2017"));
			dDRFormDetailsRequest.setLastToLastYearTotalSales(getCMATotalSalesByAppIdAndYear(appId, "2016"));
			dDRFormDetailsRequest.setCurrency(getCurrency(appId, userId));
		} else {
			dDRFormDetailsRequest = new DDRFormDetailsRequest();
			dDRFormDetailsRequest.setdDRFamilyDirectorsList(getFamilyDirectorsDetails(null,appId,userId));
			dDRFormDetailsRequest.setdDRFinancialSummaryList(getFinancialSummary(null));
			dDRFormDetailsRequest.setCurrency(getCurrency(appId, userId));
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
				ddrAuthorizedSignDetails.setModifyBy(userId);
				ddrAuthorizedSignDetails.setModifyDate(new Date());
			}
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
				saveObj.setModifyBy(userId);
				saveObj.setModifyDate(new Date());
			}
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
				saveObj.setModifyBy(userId);
				saveObj.setModifyDate(new Date());
			}
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
				saveObj.setModifyBy(userId);
				saveObj.setModifyDate(new Date());
			}
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
				saveObj.setModifyBy(userId);
				saveObj.setModifyDate(new Date());
			}
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
				saveObj.setModifyBy(userId);
				saveObj.setModifyDate(new Date());
			}
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
		logger.info("Enter in Save VehiclesOwnedDetails =======================>" +requestList.size());
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
				saveObj.setModifyBy(userId);
				saveObj.setModifyDate(new Date());
			}
			vehiclesOwnedDetailsRepository.save(saveObj);
		}
	}
	
	/**
	 * GET FINANCIAL SUMMARY DETAILS BY DDR FORM ID
	 * @param ddrFormId
	 * @return
	 */
	public List<DDRFinancialSummaryRequest> getFinancialSummary(Long ddrFormId){
		
		List<DDRFinancialSummaryRequest> responseList = null;
		if(!CommonUtils.isObjectNullOrEmpty(ddrFormId)) {
			List<DDRFinancialSummary> objList = financialSummaryRepository.getListByDDRFormId(ddrFormId);
			if(!CommonUtils.isListNullOrEmpty(objList)) {
				responseList = new ArrayList<>(objList.size());
				for(DDRFinancialSummary obj : objList) {
					DDRFinancialSummaryRequest response = new DDRFinancialSummaryRequest();
					BeanUtils.copyProperties(obj, response);
					response.setDiffPfPrvsnlAndLastYear(CommonUtils.checkDouble(obj.getDiffPfPrvsnlAndLastYear()));
				     response.setLastToLastYear(CommonUtils.checkDouble(obj.getLastToLastYear()));
				     response.setLastYear(CommonUtils.checkDouble(obj.getLastYear()));
				     response.setProvisionalYear(CommonUtils.checkDouble(obj.getProvisionalYear()));
				     
				     response.setDiffPfPrvsnlAndLastYearString(CommonUtils.checkString(obj.getDiffPfPrvsnlAndLastYear()));
				     response.setLastToLastYearString(CommonUtils.checkString(obj.getLastToLastYear()));
				     response.setLastYearString(CommonUtils.checkString(obj.getLastYear()));
				     response.setProvisionalYearString(CommonUtils.checkString(obj.getProvisionalYear()));
					responseList.add(response);
				}
				return responseList;
			}	
		}
		DDRFinancialSummaryToBeFields[] values = DDRFinancialSummaryToBeFields.values();
		responseList = new ArrayList<>(values.length);
		DDRFinancialSummaryRequest response = null;
		for(int i = 0; i < values.length; i++) {
			response = new DDRFinancialSummaryRequest();
			response.setPerticularId(values[i].getId());
			response.setPerticularName(values[i].getValue());
			responseList.add(response);
		}
		return responseList;
		
	}
	
	public void saveFinancialSummary(List<DDRFinancialSummaryRequest> requestList, Long userId, Long ddrFormId) {
		for(DDRFinancialSummaryRequest reqObj : requestList) {
			DDRFinancialSummary saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = financialSummaryRepository.getByIdAndIsActive(reqObj.getId());
			}
			
			if(CommonUtils.isObjectNullOrEmpty(saveObj)){
				saveObj = new DDRFinancialSummary();
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId","isActive");
				saveObj.setDdrFormId(ddrFormId);
				saveObj.setPerticularName(DDRFinancialSummaryToBeFields.getType(reqObj.getPerticularId()).getValue());
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
			} else {
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId");
				saveObj.setPerticularName(DDRFinancialSummaryToBeFields.getType(reqObj.getPerticularId()).getValue());
				saveObj.setModifyBy(userId);
				saveObj.setModifyDate(new Date());
			}
			financialSummaryRepository.save(saveObj);
		}
	}
	
	/**
	 * GET FAMILY DIRECTOR DETAILS BY DDR FORM ID
	 * @param ddrFormId
	 * @return
	 */
	public List<DDRFamilyDirectorsDetailsRequest> getFamilyDirectorsDetails(Long ddrFormId, Long appId, Long userId){
		List<DDRFamilyDirectorsDetailsRequest> responseList = null;
		if(!CommonUtils.isObjectNullOrEmpty(ddrFormId)) {
			List<DDRFamilyDirectorsDetails> objList = familyDirectorsDetailsRepository.getListByDDRFormId(ddrFormId);
			if(!CommonUtils.isListNullOrEmpty(objList)) {
				responseList = new ArrayList<>(objList.size());
				DDRFamilyDirectorsDetailsRequest response = null;
				for(DDRFamilyDirectorsDetails obj : objList) {
					response = new DDRFamilyDirectorsDetailsRequest();
					BeanUtils.copyProperties(obj, response);
					try {
						DDRFamilyDirectorsDetailsRequest.printFields(response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					responseList.add(response);
				}
				return responseList;
			}	
		}
		
		try {
			List<PromotorBackgroundDetailRequest> promoBackReqList = promotorBackgroundDetailsService.getPromotorBackgroundDetailList(appId, userId);
			DDRFamilyDirectorsDetailsRequest response = null;
			responseList = new ArrayList<>(promoBackReqList.size());
			for(PromotorBackgroundDetailRequest promoBackReq : promoBackReqList) {
				response = new DDRFamilyDirectorsDetailsRequest();
				response.setBackgroundId(promoBackReq.getId());
				response.setName(promoBackReq.getPromotorsName());
				responseList.add(response);
			}
		} catch (Exception e) {
			logger.info("Throw Exception While Get Background Details");
			e.printStackTrace();
		}
		return responseList;
		
	}
	
	public void saveFamilyDirectorsDetails(List<DDRFamilyDirectorsDetailsRequest> requestList, Long userId, Long ddrFormId) {
		for(DDRFamilyDirectorsDetailsRequest reqObj : requestList) {
			DDRFamilyDirectorsDetails saveObj = null;
			if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
				saveObj = familyDirectorsDetailsRepository.getByIdAndIsActive(reqObj.getId());
			}
			
			if(CommonUtils.isObjectNullOrEmpty(saveObj)){
				saveObj = new DDRFamilyDirectorsDetails();
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId","isActive");
				saveObj.setDdrFormId(ddrFormId);
				saveObj.setCreatedBy(userId);
				saveObj.setCreatedDate(new Date());
				saveObj.setIsActive(true);
			} else {
				BeanUtils.copyProperties(reqObj, saveObj,"id","createdBy","createdDate","modifyBy","modifyDate","ddrFormId");
				saveObj.setModifyBy(userId);
				saveObj.setModifyDate(new Date());
			}
			familyDirectorsDetailsRepository.save(saveObj);
		}
	}
	
	private String getCurrency(Long applicationId, Long userId) {
		Integer currencyId = loanApplicationRepository.getCurrencyId(applicationId, userId);
		Integer denominationId = loanApplicationRepository.getDenominationId(applicationId, userId);
		return CommonDocumentUtils.getCurrency(currencyId) + " in " + CommonDocumentUtils.getDenomination(denominationId);
	}
	
	@SuppressWarnings("unchecked")
	public DDROneFormResponse getOneFormDetails(Long userId, Long applicationId) {

		DDROneFormResponse response = new DDROneFormResponse();

		//---------------------------------------------------PROFILE ------------------------------------------------------------------------
		logger.info("Before Call Corporate Profile UserId is :- " + userId);
		CorporateApplicantDetail applicantDetail = corporateApplicantDetailRepository.getByApplicationAndUserId(userId,applicationId);
		if(CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
			logger.info("Corporate Profile Details NUll or Empty!! ----------------->" + applicationId);
			return response;
		}
		
		//ORGANIZATION NAME :- LINENO:6
		response.setNameOfBorrower(applicantDetail.getOrganisationName());
		response.setCurrency(getCurrency(applicationId, userId));
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
		regOfficeAdd += !CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredPincode())?applicantDetail.getRegisteredPincode() : "";
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
		admntOfficeAdd += !CommonUtils.isObjectNullOrEmpty(applicantDetail.getAdministrativePincode()) ? applicantDetail.getAdministrativePincode() : ""; 
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
				promoBackResp.setAchievements(promBackReq.getAchivements());
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
				if(!CommonUtils.isObjectNullOrEmpty(fincArrngDetailReq.getRelationshipSince())) {
					try {
						OneFormResponse establishmentYearResponse = oneFormClient.getYearByYearId(fincArrngDetailReq.getRelationshipSince().longValue());
						List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) establishmentYearResponse
								.getListData();
						if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
							MasterResponse masterResponse = MultipleJSONObjectHelper
									.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
							fincArragDetailResp.setRelationshipSinceInYear(masterResponse.getValue());
						}	
					} catch (Exception e) {
						logger.info("Throw Exception while get relationship sinc year in DDR OneForm");
						e.printStackTrace();
					}	
				}
				fincArrngDetailResList.add(fincArragDetailResp);
			}
			response.setFincArrngDetailResList(fincArrngDetailResList);
		} catch (Exception e) {
			logger.info("Throw Exception While Get Current Financial Arangement Details in DDR OneForm");
			e.printStackTrace();
		}
		
		
		//SECURITY DETAIL :- LINENO:12
		try {
			response.setSecurityCorporateDetailList(securityCorporateDetailsService.getsecurityCorporateDetailsList(applicationId, userId));
		} catch (Exception e) {
			logger.info("Throw Exception While Get Primary Security Details in DDR OneForm");
			e.printStackTrace();
		}
		
		
		//DIRECTOR BACKGROUND  DETAIL :- LINENO:12
		try {
			response.setDirectorBackgroundDetailList(backgroundDetailsService.getDirectorBackgroundDetailList(applicationId, userId));
		} catch (Exception e) {
			logger.info("Throw Exception While Get Primary Directory Backgoud Details in DDR OneForm");
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
		
		//ASSOCIATES CONCERN :- LINENO:17
		try {
			response.setAssociatedConcernDetailList(associatedConcernDetailService.getAssociatedConcernsDetailList(applicationId, userId));
		} catch (Exception e) {
			logger.info("Throw Exception While Get associates concern in DDR OneForm");
			e.printStackTrace();
		}
		response.setdDRCMACalculationList(getCMAandCOActDetails(applicationId));
		
		
		try {
			List<ReferencesRetailDetail> referencesRetailList = referenceRetailDetailsRepository.listReferencesRetailFromAppId(applicationId);
			List<ReferenceRetailDetailsRequest> referencesResponseList = new ArrayList<>(referencesRetailList.size());
			ReferenceRetailDetailsRequest referencesResponse = null;
			for(ReferencesRetailDetail referencesRetail : referencesRetailList) {
				referencesResponse = new ReferenceRetailDetailsRequest();
				BeanUtils.copyProperties(referencesRetail, referencesResponse);
				referencesResponseList.add(referencesResponse);
			}
			response.setReferencesResponseList(referencesResponseList);
		} catch (Exception e) {
			logger.info("Throw Exception While Get Reference Details in DDR OneForm");
			e.printStackTrace();
		}
		return response; 
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
	
	private Double getCMATotalSalesByAppIdAndYear(Long applicationId,String year) {
		OperatingStatementDetails operatingStatementDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, year);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails)) {
			ProfitibilityStatementDetail profitibilityStatementDetail = profitibilityStatementDetailRepository.getProfitibilityStatementDetail(applicationId, year);
			if(!CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail)) {
				return CommonUtils.checkDouble(profitibilityStatementDetail.getNetSales());
			}
		} else {
			if(!CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getNetSales())) {
				return CommonUtils.checkDouble(operatingStatementDetails.getNetSales());
			}
		}
		return 0.0;
	}
	
	
	public List<DDRCMACalculationResponse> getCMAandCOActDetails(Long applicationId) {
		

		
		List<DDRCMACalculationResponse> responseList = new ArrayList<>();
		
		boolean isCMAUpload = false;
		
		List<OperatingStatementDetails> operatingStatementDetails = operatingStatementDetailsRepository.getByApplicationId(applicationId);
		List<ProfitibilityStatementDetail> profitibilityStatementList = null;
		
		OperatingStatementDetails cma2018OSDetails = null;
		OperatingStatementDetails cma2017OSDetails = null;
		OperatingStatementDetails cma2016OSDetails = null;
		
		ProfitibilityStatementDetail coAct2018OSDetails = null;
		ProfitibilityStatementDetail coAct2017OSDetails = null;
		ProfitibilityStatementDetail coAct2016OSDetails = null;
		
		if(CommonUtils.isObjectListNull(operatingStatementDetails)) {
			profitibilityStatementList = profitibilityStatementDetailRepository.getByApplicationId(applicationId);
			if(CommonUtils.isObjectListNull(profitibilityStatementList)) {
				logger.info("User not filled CMA or CO Act Sheet");
				return responseList;
			}
			coAct2018OSDetails = profitibilityStatementList.stream().filter(a -> "2018".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(coAct2018OSDetails)) {
				coAct2018OSDetails = new ProfitibilityStatementDetail();
			}
			coAct2017OSDetails = profitibilityStatementList.stream().filter(a -> "2017".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(coAct2017OSDetails)) {
				coAct2017OSDetails = new ProfitibilityStatementDetail();
			}
			coAct2016OSDetails = profitibilityStatementList.stream().filter(a -> "2016".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(coAct2016OSDetails)) {
				coAct2016OSDetails = new ProfitibilityStatementDetail();
			}
		} else {
			isCMAUpload = true;
			cma2018OSDetails = operatingStatementDetails.stream().filter(a -> "2018".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(cma2018OSDetails)) {
				cma2018OSDetails = new OperatingStatementDetails();
			}
			cma2017OSDetails = operatingStatementDetails.stream().filter(a -> "2017".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(cma2017OSDetails)) {
				cma2017OSDetails = new OperatingStatementDetails();
			}
			cma2016OSDetails = operatingStatementDetails.stream().filter(a -> "2016".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(cma2016OSDetails)) {
				cma2016OSDetails = new OperatingStatementDetails();
			}
		}
		
		
		List<AssetsDetails> cmaAssetsDetails = null;
		AssetsDetails cma2018AssetDetails = null;
		AssetsDetails cma2017AssetDetails = null;
		AssetsDetails cma2016AssetDetails = null;
		AssetsDetails cma2015AssetDetails = null;
		if(isCMAUpload) {
			cmaAssetsDetails = assetsDetailsRepository.getByApplicationId(applicationId);
			cma2018AssetDetails = cmaAssetsDetails.stream().filter(a -> "2018".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(cma2018AssetDetails)) {
				cma2018AssetDetails = new AssetsDetails();
			}
			cma2017AssetDetails = cmaAssetsDetails.stream().filter(a -> "2017".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(cma2017AssetDetails)) {
				cma2017AssetDetails = new AssetsDetails();
			}
			cma2016AssetDetails = cmaAssetsDetails.stream().filter(a -> "2016".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(cma2016AssetDetails)) {
				cma2016AssetDetails = new AssetsDetails();
			}
			cma2015AssetDetails = cmaAssetsDetails.stream().filter(a -> "2015".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(cma2015AssetDetails)) {
				cma2015AssetDetails = new AssetsDetails();
			}
		}
		
		List<LiabilitiesDetails> liabilitiesDetailsList = null;
		LiabilitiesDetails cma2018Liabilities = null;
		LiabilitiesDetails cma2017Liabilities = null;
		LiabilitiesDetails cma2016Liabilities = null;
		LiabilitiesDetails cma2015Liabilities = null;
		if(isCMAUpload) {
			liabilitiesDetailsList = liabilitiesDetailsRepository.getByApplicationId(applicationId);
			cma2018Liabilities = liabilitiesDetailsList.stream().filter(a -> "2018".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(cma2018Liabilities)) {
				cma2018Liabilities = new LiabilitiesDetails();
			}
			cma2017Liabilities = liabilitiesDetailsList.stream().filter(a -> "2017".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(cma2017Liabilities)) {
				cma2017Liabilities = new LiabilitiesDetails();
			}
			cma2016Liabilities = liabilitiesDetailsList.stream().filter(a -> "2016".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(cma2016Liabilities)) {
				cma2016Liabilities = new LiabilitiesDetails();
			}
			cma2015Liabilities = liabilitiesDetailsList.stream().filter(a -> "2015".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(cma2015Liabilities)) {
				cma2015Liabilities = new LiabilitiesDetails();
			}
		}
		
		List<BalanceSheetDetail> balanceSheetDetailList = null;
		BalanceSheetDetail coAct2018BalanceSheet = null;
		BalanceSheetDetail coAct2017BalanceSheet = null;
		BalanceSheetDetail coAct2016BalanceSheet = null;
		BalanceSheetDetail coAct2015BalanceSheet = null;
		if(!isCMAUpload) {
			balanceSheetDetailList = balanceSheetDetailRepository.getByApplicationId(applicationId);
			coAct2018BalanceSheet = balanceSheetDetailList.stream().filter(a -> "2018".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(coAct2018BalanceSheet)) {
				coAct2018BalanceSheet = new BalanceSheetDetail();
			}
			coAct2017BalanceSheet = balanceSheetDetailList.stream().filter(a -> "2017".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(coAct2017BalanceSheet)) {
				coAct2017BalanceSheet = new BalanceSheetDetail();
			}
			coAct2016BalanceSheet = balanceSheetDetailList.stream().filter(a -> "2016".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(coAct2016BalanceSheet)) {
				coAct2016BalanceSheet = new BalanceSheetDetail();
			}
			coAct2015BalanceSheet = balanceSheetDetailList.stream().filter(a -> "2015".equals(a.getYear())).findFirst().orElse(null);
			if(CommonUtils.isObjectNullOrEmpty(coAct2015BalanceSheet)) {
				coAct2015BalanceSheet = new BalanceSheetDetail();
			}
		}
		
		
		
		DDRCMACalculationResponse totalSalesResponse = new DDRCMACalculationResponse();
		totalSalesResponse.setKeyId(DDRFinancialSummaryFields.FIRST_TOTAL_SALES.getId());
		totalSalesResponse.setKeyName(DDRFinancialSummaryFields.FIRST_TOTAL_SALES.getValue());
		totalSalesResponse.setProvisionalYear(isCMAUpload ? CommonUtils.checkDouble(cma2018OSDetails.getNetSales()) : CommonUtils.checkDouble(coAct2018OSDetails.getNetSales()));
		totalSalesResponse.setLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2017OSDetails.getNetSales()) : CommonUtils.checkDouble(coAct2017OSDetails.getNetSales()));
		totalSalesResponse.setLastToLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2016OSDetails.getNetSales()) : CommonUtils.checkDouble(coAct2016OSDetails.getNetSales()));
		totalSalesResponse.setDiffPvsnlAndLastYear(CommonUtils.checkDouble(((totalSalesResponse.getProvisionalYear() - totalSalesResponse.getLastYear()) / totalSalesResponse.getLastYear()) * 100));
		
		totalSalesResponse.setProvisionalYearString(isCMAUpload ? CommonUtils.checkString(cma2018OSDetails.getNetSales()) : CommonUtils.checkString(coAct2018OSDetails.getNetSales()));
		totalSalesResponse.setLastYearString(isCMAUpload ? CommonUtils.checkString(cma2017OSDetails.getNetSales()) : CommonUtils.checkString(coAct2017OSDetails.getNetSales()));
		totalSalesResponse.setLastToLastYearString(isCMAUpload ? CommonUtils.checkString(cma2016OSDetails.getNetSales()) : CommonUtils.checkString(coAct2016OSDetails.getNetSales()));
		totalSalesResponse.setDiffPvsnlAndLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(((totalSalesResponse.getProvisionalYear() - totalSalesResponse.getLastYear()) / totalSalesResponse.getLastYear()) * 100)));
		responseList.add(totalSalesResponse);
		
		DDRCMACalculationResponse interestCostResponse = new DDRCMACalculationResponse();
		interestCostResponse.setKeyId(DDRFinancialSummaryFields.INTEREST_COST.getId());
		interestCostResponse.setKeyName(DDRFinancialSummaryFields.INTEREST_COST.getValue());
	
		interestCostResponse.setProvisionalYear(isCMAUpload ? CommonUtils.checkDouble(cma2018OSDetails.getInterest()) : CommonUtils.checkDouble(coAct2018OSDetails.getFinanceCost()));
		interestCostResponse.setLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2017OSDetails.getInterest()) : CommonUtils.checkDouble(coAct2017OSDetails.getFinanceCost()));
		interestCostResponse.setLastToLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2016OSDetails.getInterest()) : CommonUtils.checkDouble(coAct2016OSDetails.getFinanceCost()));
		interestCostResponse.setDiffPvsnlAndLastYear(calculateFinancialSummary(interestCostResponse.getProvisionalYear(),interestCostResponse.getLastYear()));
		
		
		interestCostResponse.setProvisionalYearString(isCMAUpload ? CommonUtils.checkString(cma2018OSDetails.getInterest()) : CommonUtils.checkString(coAct2018OSDetails.getFinanceCost()));
		interestCostResponse.setLastYearString(isCMAUpload ? CommonUtils.checkString(cma2017OSDetails.getInterest()) : CommonUtils.checkString(coAct2017OSDetails.getFinanceCost()));
		interestCostResponse.setLastToLastYearString(isCMAUpload ? CommonUtils.checkString(cma2016OSDetails.getInterest()) : CommonUtils.checkString(coAct2016OSDetails.getFinanceCost()));
		interestCostResponse.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(interestCostResponse.getProvisionalYear(),interestCostResponse.getLastYear()));
		responseList.add(interestCostResponse);
		
		DDRCMACalculationResponse  profitBeforeTaxResponse = new DDRCMACalculationResponse();
		profitBeforeTaxResponse.setKeyId(DDRFinancialSummaryFields.PROFIT_BEFORE_TAX.getId());
		profitBeforeTaxResponse.setKeyName(DDRFinancialSummaryFields.PROFIT_BEFORE_TAX.getValue());
		
		profitBeforeTaxResponse.setProvisionalYear(isCMAUpload ? CommonUtils.checkDouble(cma2018OSDetails.getProfitBeforeTaxOrLoss()) : CommonUtils.checkDouble(coAct2018OSDetails.getProfitBeforeTax()));
		profitBeforeTaxResponse.setLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2017OSDetails.getProfitBeforeTaxOrLoss()) : CommonUtils.checkDouble(coAct2017OSDetails.getProfitBeforeTax()));
		profitBeforeTaxResponse.setLastToLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2016OSDetails.getProfitBeforeTaxOrLoss()) : CommonUtils.checkDouble(coAct2016OSDetails.getProfitBeforeTax()));
		profitBeforeTaxResponse.setDiffPvsnlAndLastYear(calculateFinancialSummary(profitBeforeTaxResponse.getProvisionalYear(),profitBeforeTaxResponse.getLastYear()));
		
		
		profitBeforeTaxResponse.setProvisionalYearString(isCMAUpload ? CommonUtils.checkString(cma2018OSDetails.getProfitBeforeTaxOrLoss()) : CommonUtils.checkString(coAct2018OSDetails.getProfitBeforeTax()));
		profitBeforeTaxResponse.setLastYearString(isCMAUpload ? CommonUtils.checkString(cma2017OSDetails.getProfitBeforeTaxOrLoss()) : CommonUtils.checkString(coAct2017OSDetails.getProfitBeforeTax()));
		profitBeforeTaxResponse.setLastToLastYearString(isCMAUpload ? CommonUtils.checkString(cma2016OSDetails.getProfitBeforeTaxOrLoss()) : CommonUtils.checkString(coAct2016OSDetails.getProfitBeforeTax()));
		profitBeforeTaxResponse.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(profitBeforeTaxResponse.getProvisionalYear(),profitBeforeTaxResponse.getLastYear()));
		responseList.add(profitBeforeTaxResponse);
		
		DDRCMACalculationResponse profitAfterTaxResponse = new DDRCMACalculationResponse();
		profitAfterTaxResponse.setKeyId(DDRFinancialSummaryFields.PROFIT_AFTER_TAX.getId());
		profitAfterTaxResponse.setKeyName(DDRFinancialSummaryFields.PROFIT_AFTER_TAX.getValue());
		
		profitAfterTaxResponse.setProvisionalYear(isCMAUpload ? CommonUtils.checkDouble(cma2018OSDetails.getNetProfitOrLoss()) : CommonUtils.checkDouble(coAct2018OSDetails.getProfitAfterTax()));
		profitAfterTaxResponse.setLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2017OSDetails.getNetProfitOrLoss()) : CommonUtils.checkDouble(coAct2017OSDetails.getProfitAfterTax()));
		profitAfterTaxResponse.setLastToLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2016OSDetails.getNetProfitOrLoss()) : CommonUtils.checkDouble(coAct2016OSDetails.getProfitAfterTax()));
		profitAfterTaxResponse.setDiffPvsnlAndLastYear(calculateFinancialSummary(profitAfterTaxResponse.getProvisionalYear(),profitAfterTaxResponse.getLastYear()));
		
		
		profitAfterTaxResponse.setProvisionalYearString(isCMAUpload ? CommonUtils.checkString(cma2018OSDetails.getNetProfitOrLoss()) : CommonUtils.checkString(coAct2018OSDetails.getProfitAfterTax()));
		profitAfterTaxResponse.setLastYearString(isCMAUpload ? CommonUtils.checkString(cma2017OSDetails.getNetProfitOrLoss()) : CommonUtils.checkString(coAct2017OSDetails.getProfitAfterTax()));
		profitAfterTaxResponse.setLastToLastYearString(isCMAUpload ? CommonUtils.checkString(cma2016OSDetails.getNetProfitOrLoss()) : CommonUtils.checkString(coAct2016OSDetails.getProfitAfterTax()));
		profitAfterTaxResponse.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(profitAfterTaxResponse.getProvisionalYear(),profitAfterTaxResponse.getLastYear()));
		responseList.add(profitAfterTaxResponse);
		
		DDRCMACalculationResponse netWorthResponse = new DDRCMACalculationResponse();
		netWorthResponse.setKeyId(DDRFinancialSummaryFields.NET_WORTH.getId());
		netWorthResponse.setKeyName(DDRFinancialSummaryFields.NET_WORTH.getValue());
		
		if(isCMAUpload) {
			netWorthResponse.setProvisionalYear(CommonUtils.checkDouble(cma2018AssetDetails.getTangibleNetWorth()));
			netWorthResponse.setLastYear(CommonUtils.checkDouble(cma2017AssetDetails.getTangibleNetWorth()));
			netWorthResponse.setLastToLastYear(CommonUtils.checkDouble(cma2016AssetDetails.getTangibleNetWorth()));
		} else {
			double totalPrvsl2018Year = CommonUtils.checkDouble(coAct2018BalanceSheet.getGrandTotal()) - CommonUtils.checkDouble(coAct2018BalanceSheet.getOtherNonCurrentLiability()) 
					- CommonUtils.checkDouble(coAct2018BalanceSheet.getOtherNonCurrentLiability()) 
					- CommonUtils.checkDouble(coAct2018BalanceSheet.getOthersCurrentLiability()) - CommonUtils.checkDouble(coAct2018BalanceSheet.getIntangibleAssets())
							- CommonUtils.checkDouble(coAct2018BalanceSheet.getRevaluationReserve());
			netWorthResponse.setProvisionalYear(totalPrvsl2018Year);
			double totalPrvsl2017Year = CommonUtils.checkDouble(coAct2017BalanceSheet.getGrandTotal()) - CommonUtils.checkDouble(coAct2017BalanceSheet.getOtherNonCurrentLiability()) 
					- CommonUtils.checkDouble(coAct2017BalanceSheet.getOtherNonCurrentLiability()) 
					- CommonUtils.checkDouble(coAct2017BalanceSheet.getOthersCurrentLiability()) - CommonUtils.checkDouble(coAct2017BalanceSheet.getIntangibleAssets()) 
					- CommonUtils.checkDouble(coAct2017BalanceSheet.getRevaluationReserve());
			netWorthResponse.setLastYear(totalPrvsl2017Year);
			double totalPrvsl2016Year = CommonUtils.checkDouble(coAct2016BalanceSheet.getGrandTotal()) - CommonUtils.checkDouble(coAct2016BalanceSheet.getOtherNonCurrentLiability()) 
					- CommonUtils.checkDouble(coAct2016BalanceSheet.getOtherNonCurrentLiability()) 
					- CommonUtils.checkDouble(coAct2016BalanceSheet.getOthersCurrentLiability()) - CommonUtils.checkDouble(coAct2016BalanceSheet.getIntangibleAssets())
							- CommonUtils.checkDouble(coAct2016BalanceSheet.getRevaluationReserve());
			netWorthResponse.setLastToLastYear(totalPrvsl2016Year);
		}
		netWorthResponse.setDiffPvsnlAndLastYear(calculateFinancialSummary(netWorthResponse.getProvisionalYear(),netWorthResponse.getLastYear()));
		
		
		if(isCMAUpload) {
			netWorthResponse.setProvisionalYearString(CommonUtils.checkString(cma2018AssetDetails.getTangibleNetWorth()));
			netWorthResponse.setLastYearString(CommonUtils.checkString(cma2017AssetDetails.getTangibleNetWorth()));
			netWorthResponse.setLastToLastYearString(CommonUtils.checkString(cma2016AssetDetails.getTangibleNetWorth()));
		} else {
			double totalPrvsl2018Year = CommonUtils.checkDouble(coAct2018BalanceSheet.getGrandTotal()) - CommonUtils.checkDouble(coAct2018BalanceSheet.getOtherNonCurrentLiability()) 
					- CommonUtils.checkDouble(coAct2018BalanceSheet.getOtherNonCurrentLiability()) 
					- CommonUtils.checkDouble(coAct2018BalanceSheet.getOthersCurrentLiability()) - CommonUtils.checkDouble(coAct2018BalanceSheet.getIntangibleAssets())
							- CommonUtils.checkDouble(coAct2018BalanceSheet.getRevaluationReserve());
			netWorthResponse.setProvisionalYearString(CommonUtils.checkString(totalPrvsl2018Year));
			double totalPrvsl2017Year = CommonUtils.checkDouble(coAct2017BalanceSheet.getGrandTotal()) - CommonUtils.checkDouble(coAct2017BalanceSheet.getOtherNonCurrentLiability()) 
					- CommonUtils.checkDouble(coAct2017BalanceSheet.getOtherNonCurrentLiability()) 
					- CommonUtils.checkDouble(coAct2017BalanceSheet.getOthersCurrentLiability()) - CommonUtils.checkDouble(coAct2017BalanceSheet.getIntangibleAssets()) 
					- CommonUtils.checkDouble(coAct2017BalanceSheet.getRevaluationReserve());
			netWorthResponse.setLastYearString(CommonUtils.checkString(totalPrvsl2017Year));
			double totalPrvsl2016Year = CommonUtils.checkDouble(coAct2016BalanceSheet.getGrandTotal()) - CommonUtils.checkDouble(coAct2016BalanceSheet.getOtherNonCurrentLiability()) 
					- CommonUtils.checkDouble(coAct2016BalanceSheet.getOtherNonCurrentLiability()) 
					- CommonUtils.checkDouble(coAct2016BalanceSheet.getOthersCurrentLiability()) - CommonUtils.checkDouble(coAct2016BalanceSheet.getIntangibleAssets())
							- CommonUtils.checkDouble(coAct2016BalanceSheet.getRevaluationReserve());
			netWorthResponse.setLastToLastYearString(CommonUtils.checkString(totalPrvsl2016Year));
		}
		netWorthResponse.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(netWorthResponse.getProvisionalYear(),netWorthResponse.getLastYear()));
		responseList.add(netWorthResponse);
		
		
		DDRCMACalculationResponse adjustedNetWorth = new DDRCMACalculationResponse();
		adjustedNetWorth.setKeyId(DDRFinancialSummaryFields.ADJUSTED_NET_WORTH.getId());
		adjustedNetWorth.setKeyName(DDRFinancialSummaryFields.ADJUSTED_NET_WORTH.getValue());
		
		if(isCMAUpload) {
			adjustedNetWorth.setProvisionalYear(CommonUtils.checkDouble(netWorthResponse.getProvisionalYear()) - CommonUtils.checkDouble(cma2018Liabilities.getOtherNclUnsecuredLoansFromPromoters()));
			adjustedNetWorth.setLastYear(CommonUtils.checkDouble(netWorthResponse.getLastYear()) - CommonUtils.checkDouble(cma2017Liabilities.getOtherNclUnsecuredLoansFromPromoters()));
			adjustedNetWorth.setLastToLastYear(CommonUtils.checkDouble(netWorthResponse.getLastToLastYear()) - CommonUtils.checkDouble(cma2016Liabilities.getOtherNclUnsecuredLoansFromPromoters()));
		} else {
			adjustedNetWorth.setProvisionalYear(CommonUtils.checkDouble(netWorthResponse.getProvisionalYear()) - CommonUtils.checkDouble(coAct2018BalanceSheet.getUnsecuredLoansFromPromoters()));
			adjustedNetWorth.setLastYear(CommonUtils.checkDouble(netWorthResponse.getLastYear()) - CommonUtils.checkDouble(coAct2017BalanceSheet.getUnsecuredLoansFromPromoters()));
			adjustedNetWorth.setLastToLastYear(CommonUtils.checkDouble(netWorthResponse.getLastToLastYear()) - CommonUtils.checkDouble(coAct2016BalanceSheet.getUnsecuredLoansFromPromoters()));
		}
		adjustedNetWorth.setDiffPvsnlAndLastYear(calculateFinancialSummary(adjustedNetWorth.getProvisionalYear(),adjustedNetWorth.getLastYear()));
		
		
		if(isCMAUpload) {
			adjustedNetWorth.setProvisionalYearString(CommonUtils.checkString(CommonUtils.checkDouble(netWorthResponse.getProvisionalYear()) - CommonUtils.checkDouble(cma2018Liabilities.getOtherNclUnsecuredLoansFromPromoters())));
			adjustedNetWorth.setLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(netWorthResponse.getLastYear()) - CommonUtils.checkDouble(cma2017Liabilities.getOtherNclUnsecuredLoansFromPromoters())));
			adjustedNetWorth.setLastToLastYearString(CommonUtils.checkString( CommonUtils.checkDouble(netWorthResponse.getLastToLastYear()) - CommonUtils.checkDouble(cma2016Liabilities.getOtherNclUnsecuredLoansFromPromoters())));
		} else {
			adjustedNetWorth.setProvisionalYearString(CommonUtils.checkString(CommonUtils.checkDouble(netWorthResponse.getProvisionalYear()) - CommonUtils.checkDouble(coAct2018BalanceSheet.getUnsecuredLoansFromPromoters())));
			adjustedNetWorth.setLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(netWorthResponse.getLastYear()) - CommonUtils.checkDouble(coAct2017BalanceSheet.getUnsecuredLoansFromPromoters())));
			adjustedNetWorth.setLastToLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(netWorthResponse.getLastToLastYear()) - CommonUtils.checkDouble(coAct2016BalanceSheet.getUnsecuredLoansFromPromoters())));
		}
		adjustedNetWorth.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(adjustedNetWorth.getProvisionalYear(),adjustedNetWorth.getLastYear()));
		responseList.add(adjustedNetWorth);
		
		DDRCMACalculationResponse totalDebt = new DDRCMACalculationResponse();
		totalDebt.setKeyId(DDRFinancialSummaryFields.TOTAL_DEBT.getId());
		totalDebt.setKeyName(DDRFinancialSummaryFields.TOTAL_DEBT.getValue());
		
		if(isCMAUpload) {
			totalDebt.setProvisionalYear(CommonUtils.checkDouble(cma2018Liabilities.getTotalOutsideLiabilities()));
			totalDebt.setLastYear(CommonUtils.checkDouble(cma2017Liabilities.getTotalOutsideLiabilities()));
			totalDebt.setLastToLastYear(CommonUtils.checkDouble(cma2016Liabilities.getTotalOutsideLiabilities()));
		} else {
			totalDebt.setProvisionalYear(CommonUtils.checkDouble(coAct2018BalanceSheet.getOtherNonCurrentLiability()) + CommonUtils.checkDouble(coAct2018BalanceSheet.getOthersCurrentLiability()));
			totalDebt.setLastYear(CommonUtils.checkDouble(coAct2017BalanceSheet.getOtherNonCurrentLiability()) + CommonUtils.checkDouble(coAct2017BalanceSheet.getOthersCurrentLiability()));
			totalDebt.setLastToLastYear(CommonUtils.checkDouble(coAct2016BalanceSheet.getOtherNonCurrentLiability()) + CommonUtils.checkDouble(coAct2016BalanceSheet.getOthersCurrentLiability()));
		}
		totalDebt.setDiffPvsnlAndLastYear(calculateFinancialSummary(totalDebt.getProvisionalYear(),totalDebt.getLastYear()));
		
		
		if(isCMAUpload) {
			totalDebt.setProvisionalYearString(CommonUtils.checkString(cma2018Liabilities.getTotalOutsideLiabilities()));
			totalDebt.setLastYearString(CommonUtils.checkString(cma2017Liabilities.getTotalOutsideLiabilities()));
			totalDebt.setLastToLastYearString(CommonUtils.checkString(cma2016Liabilities.getTotalOutsideLiabilities()));
		} else {
			totalDebt.setProvisionalYearString(CommonUtils.checkString(CommonUtils.checkDouble(coAct2018BalanceSheet.getOtherNonCurrentLiability()) + CommonUtils.checkDouble(coAct2018BalanceSheet.getOthersCurrentLiability())));
			totalDebt.setLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(coAct2017BalanceSheet.getOtherNonCurrentLiability()) + CommonUtils.checkDouble(coAct2017BalanceSheet.getOthersCurrentLiability())));
			totalDebt.setLastToLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(coAct2016BalanceSheet.getOtherNonCurrentLiability()) + CommonUtils.checkDouble(coAct2016BalanceSheet.getOthersCurrentLiability())));
		}
		totalDebt.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(totalDebt.getProvisionalYear(),totalDebt.getLastYear()));
		responseList.add(totalDebt);
		
		DDRCMACalculationResponse secureLoanResponse = new DDRCMACalculationResponse();
		secureLoanResponse.setKeyId(DDRFinancialSummaryFields.SECURE_LOAN.getId());
		secureLoanResponse.setKeyName(DDRFinancialSummaryFields.SECURE_LOAN.getValue());
		
		secureLoanResponse.setProvisionalYear(isCMAUpload ? CommonUtils.checkDouble(cma2018Liabilities.getTermLiabilitiesSecured())  : CommonUtils.checkDouble(coAct2018BalanceSheet.getTermLoansSecured()));
		secureLoanResponse.setLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2017Liabilities.getTermLiabilitiesSecured()) : CommonUtils.checkDouble(coAct2017BalanceSheet.getTermLoansSecured()));
		secureLoanResponse.setLastToLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2016Liabilities.getTermLiabilitiesSecured()) : CommonUtils.checkDouble(coAct2016BalanceSheet.getTermLoansSecured()));
		secureLoanResponse.setDiffPvsnlAndLastYear(calculateFinancialSummary(secureLoanResponse.getProvisionalYear(),secureLoanResponse.getLastYear()));
		
		
		secureLoanResponse.setProvisionalYearString(isCMAUpload ? CommonUtils.checkString(cma2018Liabilities.getTermLiabilitiesSecured())  : CommonUtils.checkString(coAct2018BalanceSheet.getTermLoansSecured()));
		secureLoanResponse.setLastYearString(isCMAUpload ? CommonUtils.checkString(cma2017Liabilities.getTermLiabilitiesSecured()) : CommonUtils.checkString(coAct2017BalanceSheet.getTermLoansSecured()));
		secureLoanResponse.setLastToLastYearString(isCMAUpload ? CommonUtils.checkString(cma2016Liabilities.getTermLiabilitiesSecured()) : CommonUtils.checkString(coAct2016BalanceSheet.getTermLoansSecured()));
		secureLoanResponse.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(secureLoanResponse.getProvisionalYear(),secureLoanResponse.getLastYear()));
		responseList.add(secureLoanResponse);
		
		DDRCMACalculationResponse unsecureLoanResp = new DDRCMACalculationResponse();
		unsecureLoanResp.setKeyId(DDRFinancialSummaryFields.UNSECURE_LOAN.getId());
		unsecureLoanResp.setKeyName(DDRFinancialSummaryFields.UNSECURE_LOAN.getValue());
		
		if(isCMAUpload) {
			unsecureLoanResp.setProvisionalYear(CommonUtils.checkDouble(cma2018Liabilities.getTermLiabilitiesUnsecured()) + CommonUtils.checkDouble(cma2018Liabilities.getOtherNclUnsecuredLoansFromPromoters()) 
					+ CommonUtils.checkDouble(cma2018Liabilities.getOtherNclUnsecuredLoansFromOther()));
			unsecureLoanResp.setLastYear(CommonUtils.checkDouble(cma2017Liabilities.getTermLiabilitiesUnsecured()) + CommonUtils.checkDouble(cma2017Liabilities.getOtherNclUnsecuredLoansFromPromoters())
					+ CommonUtils.checkDouble(cma2017Liabilities.getOtherNclUnsecuredLoansFromOther()));
			unsecureLoanResp.setLastToLastYear(CommonUtils.checkDouble(cma2016Liabilities.getTermLiabilitiesUnsecured()) + CommonUtils.checkDouble(cma2016Liabilities.getOtherNclUnsecuredLoansFromPromoters())
					+ CommonUtils.checkDouble(cma2016Liabilities.getOtherNclUnsecuredLoansFromOther()));
		} else {
			unsecureLoanResp.setProvisionalYear(CommonUtils.checkDouble(coAct2018BalanceSheet.getTermLoansUnsecured()) + CommonUtils.checkDouble(coAct2018BalanceSheet.getUnsecuredLoansFromPromoters())
					+ CommonUtils.checkDouble(coAct2018BalanceSheet.getUnsecuredLoansFromOthers()));
			unsecureLoanResp.setLastYear(CommonUtils.checkDouble(coAct2017BalanceSheet.getTermLoansUnsecured()) + CommonUtils.checkDouble(coAct2017BalanceSheet.getUnsecuredLoansFromPromoters())
					+ CommonUtils.checkDouble(coAct2017BalanceSheet.getUnsecuredLoansFromOthers()));
			unsecureLoanResp.setLastToLastYear(CommonUtils.checkDouble(coAct2016BalanceSheet.getTermLoansUnsecured()) + CommonUtils.checkDouble(coAct2016BalanceSheet.getUnsecuredLoansFromPromoters())
					+ CommonUtils.checkDouble(coAct2016BalanceSheet.getUnsecuredLoansFromOthers()));
		}
		unsecureLoanResp.setDiffPvsnlAndLastYear(calculateFinancialSummary(unsecureLoanResp.getProvisionalYear(),unsecureLoanResp.getLastYear()));
		
		
		if(isCMAUpload) {
			unsecureLoanResp.setProvisionalYearString(CommonUtils.checkString(CommonUtils.checkDouble(cma2018Liabilities.getTermLiabilitiesUnsecured()) + CommonUtils.checkDouble(cma2018Liabilities.getOtherNclUnsecuredLoansFromPromoters()) 
					+ CommonUtils.checkDouble(cma2018Liabilities.getOtherNclUnsecuredLoansFromOther())));
			unsecureLoanResp.setLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(cma2017Liabilities.getTermLiabilitiesUnsecured()) + CommonUtils.checkDouble(cma2017Liabilities.getOtherNclUnsecuredLoansFromPromoters())
					+ CommonUtils.checkDouble(cma2017Liabilities.getOtherNclUnsecuredLoansFromOther())));
			unsecureLoanResp.setLastToLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(cma2016Liabilities.getTermLiabilitiesUnsecured()) + CommonUtils.checkDouble(cma2016Liabilities.getOtherNclUnsecuredLoansFromPromoters())
					+ CommonUtils.checkDouble(cma2016Liabilities.getOtherNclUnsecuredLoansFromOther())));
		} else {
			unsecureLoanResp.setProvisionalYearString(CommonUtils.checkString(CommonUtils.checkDouble(coAct2018BalanceSheet.getTermLoansUnsecured()) + CommonUtils.checkDouble(coAct2018BalanceSheet.getUnsecuredLoansFromPromoters())
					+ CommonUtils.checkDouble(coAct2018BalanceSheet.getUnsecuredLoansFromOthers())));
			unsecureLoanResp.setLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(coAct2017BalanceSheet.getTermLoansUnsecured()) + CommonUtils.checkDouble(coAct2017BalanceSheet.getUnsecuredLoansFromPromoters())
					+ CommonUtils.checkDouble(coAct2017BalanceSheet.getUnsecuredLoansFromOthers())));
			unsecureLoanResp.setLastToLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(coAct2016BalanceSheet.getTermLoansUnsecured()) + CommonUtils.checkDouble(coAct2016BalanceSheet.getUnsecuredLoansFromPromoters())
					+ CommonUtils.checkDouble(coAct2016BalanceSheet.getUnsecuredLoansFromOthers())));
		}
		unsecureLoanResp.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(unsecureLoanResp.getProvisionalYear(),unsecureLoanResp.getLastYear()));
		responseList.add(unsecureLoanResp);
		
		
		DDRCMACalculationResponse unSecureLoanFromFriends = new DDRCMACalculationResponse();
		unSecureLoanFromFriends.setKeyId(DDRFinancialSummaryFields.UNSECURE_LOAN_FROM_FRIEND.getId());
		unSecureLoanFromFriends.setKeyName(DDRFinancialSummaryFields.UNSECURE_LOAN_FROM_FRIEND.getValue());
		
		unSecureLoanFromFriends.setProvisionalYear(isCMAUpload ? CommonUtils.checkDouble(cma2018Liabilities.getOtherNclUnsecuredLoansFromPromoters())  : CommonUtils.checkDouble(coAct2018BalanceSheet.getUnsecuredLoansFromPromoters()));
		unSecureLoanFromFriends.setLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2017Liabilities.getOtherNclUnsecuredLoansFromPromoters())  : CommonUtils.checkDouble(coAct2017BalanceSheet.getUnsecuredLoansFromPromoters()));
		unSecureLoanFromFriends.setLastToLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2016Liabilities.getOtherNclUnsecuredLoansFromPromoters())  : CommonUtils.checkDouble(coAct2016BalanceSheet.getUnsecuredLoansFromPromoters()));
		unSecureLoanFromFriends.setDiffPvsnlAndLastYear(calculateFinancialSummary(unSecureLoanFromFriends.getProvisionalYear(),unSecureLoanFromFriends.getLastYear()));
		
		unSecureLoanFromFriends.setProvisionalYearString(isCMAUpload ? CommonUtils.checkString(cma2018Liabilities.getOtherNclUnsecuredLoansFromPromoters())  : CommonUtils.checkString(coAct2018BalanceSheet.getUnsecuredLoansFromPromoters()));
		unSecureLoanFromFriends.setLastYearString(isCMAUpload ? CommonUtils.checkString(cma2017Liabilities.getOtherNclUnsecuredLoansFromPromoters())  : CommonUtils.checkString(coAct2017BalanceSheet.getUnsecuredLoansFromPromoters()));
		unSecureLoanFromFriends.setLastToLastYearString(isCMAUpload ? CommonUtils.checkString(cma2016Liabilities.getOtherNclUnsecuredLoansFromPromoters())  : CommonUtils.checkString(coAct2016BalanceSheet.getUnsecuredLoansFromPromoters()));
		unSecureLoanFromFriends.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(unSecureLoanFromFriends.getProvisionalYear(),unSecureLoanFromFriends.getLastYear()));
		responseList.add(unSecureLoanFromFriends);
		
		
		DDRCMACalculationResponse capitalResponse = new DDRCMACalculationResponse();
		capitalResponse.setKeyId(DDRFinancialSummaryFields.CAPITAL.getId());
		capitalResponse.setKeyName(DDRFinancialSummaryFields.CAPITAL.getValue());
		
		if(isCMAUpload) {
			capitalResponse.setProvisionalYear(CommonUtils.checkDouble(cma2018Liabilities.getOrdinarySharesCapital()));
			capitalResponse.setLastYear(CommonUtils.checkDouble(cma2017Liabilities.getOrdinarySharesCapital()));
			capitalResponse.setLastToLastYear(CommonUtils.checkDouble(cma2016Liabilities.getOrdinarySharesCapital()));
		} else {
			capitalResponse.setProvisionalYear(CommonUtils.checkDouble(coAct2018BalanceSheet.getOrdinaryShareCapital()) + CommonUtils.checkDouble(coAct2018BalanceSheet.getPreferenceShareCapital())
					+ CommonUtils.checkDouble(coAct2018BalanceSheet.getShareApplicationPendingAllotment()));
			capitalResponse.setLastYear(CommonUtils.checkDouble(coAct2017BalanceSheet.getOrdinaryShareCapital()) + CommonUtils.checkDouble(coAct2017BalanceSheet.getPreferenceShareCapital())
					+ CommonUtils.checkDouble(coAct2017BalanceSheet.getShareApplicationPendingAllotment()));
			capitalResponse.setLastToLastYear(CommonUtils.checkDouble(coAct2016BalanceSheet.getOrdinaryShareCapital()) + CommonUtils.checkDouble(coAct2016BalanceSheet.getPreferenceShareCapital())
					+ CommonUtils.checkDouble(coAct2016BalanceSheet.getShareApplicationPendingAllotment()));
		}
		capitalResponse.setDiffPvsnlAndLastYear(calculateFinancialSummary(capitalResponse.getProvisionalYear(),capitalResponse.getLastYear()));
		
		if(isCMAUpload) {
			capitalResponse.setProvisionalYearString(CommonUtils.checkString(cma2018Liabilities.getOrdinarySharesCapital()));
			capitalResponse.setLastYearString(CommonUtils.checkString(cma2017Liabilities.getOrdinarySharesCapital()));
			capitalResponse.setLastToLastYearString(CommonUtils.checkString(cma2016Liabilities.getOrdinarySharesCapital()));
		} else {
			capitalResponse.setProvisionalYearString(CommonUtils.checkString(CommonUtils.checkDouble(coAct2018BalanceSheet.getOrdinaryShareCapital()) + CommonUtils.checkDouble(coAct2018BalanceSheet.getPreferenceShareCapital())
					+ CommonUtils.checkDouble(coAct2018BalanceSheet.getShareApplicationPendingAllotment())));
			capitalResponse.setLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(coAct2017BalanceSheet.getOrdinaryShareCapital()) + CommonUtils.checkDouble(coAct2017BalanceSheet.getPreferenceShareCapital())
					+ CommonUtils.checkDouble(coAct2017BalanceSheet.getShareApplicationPendingAllotment())));
			capitalResponse.setLastToLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(coAct2016BalanceSheet.getOrdinaryShareCapital()) + CommonUtils.checkDouble(coAct2016BalanceSheet.getPreferenceShareCapital())
					+ CommonUtils.checkDouble(coAct2016BalanceSheet.getShareApplicationPendingAllotment())));
		}
		capitalResponse.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(capitalResponse.getProvisionalYear(),capitalResponse.getLastYear()));
		responseList.add(capitalResponse);
		
		
		DDRCMACalculationResponse totalCurrentAsset = new DDRCMACalculationResponse();
		totalCurrentAsset.setKeyId(DDRFinancialSummaryFields.TOTAL_CURRENT_ASSET.getId());
		totalCurrentAsset.setKeyName(DDRFinancialSummaryFields.TOTAL_CURRENT_ASSET.getValue());
		
		totalCurrentAsset.setProvisionalYear(isCMAUpload ? CommonUtils.checkDouble(cma2018AssetDetails.getTotalCurrentAssets())  : CommonUtils.checkDouble(coAct2018BalanceSheet.getOthersCurrentAssets()));
		totalCurrentAsset.setLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2017AssetDetails.getTotalCurrentAssets())  : CommonUtils.checkDouble(coAct2017BalanceSheet.getOthersCurrentAssets()));
		totalCurrentAsset.setLastToLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2016AssetDetails.getTotalCurrentAssets())  : CommonUtils.checkDouble(coAct2016BalanceSheet.getOthersCurrentAssets()));
		totalCurrentAsset.setDiffPvsnlAndLastYear(calculateFinancialSummary(totalCurrentAsset.getProvisionalYear(),totalCurrentAsset.getLastYear()));
		
		totalCurrentAsset.setProvisionalYearString(isCMAUpload ? CommonUtils.checkString(cma2018AssetDetails.getTotalCurrentAssets())  : CommonUtils.checkString(coAct2018BalanceSheet.getOthersCurrentAssets()));
		totalCurrentAsset.setLastYearString(isCMAUpload ? CommonUtils.checkString(cma2017AssetDetails.getTotalCurrentAssets())  : CommonUtils.checkString(coAct2017BalanceSheet.getOthersCurrentAssets()));
		totalCurrentAsset.setLastToLastYearString(isCMAUpload ? CommonUtils.checkString(cma2016AssetDetails.getTotalCurrentAssets())  : CommonUtils.checkString(coAct2016BalanceSheet.getOthersCurrentAssets()));
		totalCurrentAsset.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(totalCurrentAsset.getProvisionalYear(),totalCurrentAsset.getLastYear()));
		responseList.add(totalCurrentAsset);
		
		DDRCMACalculationResponse totalCurrentLiability = new DDRCMACalculationResponse();
		totalCurrentLiability.setKeyId(DDRFinancialSummaryFields.TOTAL_CURRENT_LIABILITY.getId());
		totalCurrentLiability.setKeyName(DDRFinancialSummaryFields.TOTAL_CURRENT_LIABILITY.getValue());
		
		totalCurrentLiability.setProvisionalYear(isCMAUpload ? CommonUtils.checkDouble(cma2018Liabilities.getTotalCurrentLiabilities()) : CommonUtils.checkDouble(coAct2018BalanceSheet.getOthersCurrentLiability()));
		totalCurrentLiability.setLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2017Liabilities.getTotalCurrentLiabilities()) : CommonUtils.checkDouble(coAct2017BalanceSheet.getOthersCurrentLiability()));
		totalCurrentLiability.setLastToLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2016Liabilities.getTotalCurrentLiabilities()) : CommonUtils.checkDouble(coAct2016BalanceSheet.getOthersCurrentLiability()));
		totalCurrentLiability.setDiffPvsnlAndLastYear(calculateFinancialSummary(totalCurrentLiability.getProvisionalYear(),totalCurrentLiability.getLastYear()));
		
		totalCurrentLiability.setProvisionalYearString(isCMAUpload ? CommonUtils.checkString(cma2018Liabilities.getTotalCurrentLiabilities()) : CommonUtils.checkString(coAct2018BalanceSheet.getOthersCurrentLiability()));
		totalCurrentLiability.setLastYearString(isCMAUpload ? CommonUtils.checkString(cma2017Liabilities.getTotalCurrentLiabilities()) : CommonUtils.checkString(coAct2017BalanceSheet.getOthersCurrentLiability()));
		totalCurrentLiability.setLastToLastYearString(isCMAUpload ? CommonUtils.checkString(cma2016Liabilities.getTotalCurrentLiabilities()) : CommonUtils.checkString(coAct2016BalanceSheet.getOthersCurrentLiability()));
		totalCurrentLiability.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(totalCurrentLiability.getProvisionalYear(),totalCurrentLiability.getLastYear()));
		responseList.add(totalCurrentLiability);
		
		DDRCMACalculationResponse totalLiability = new DDRCMACalculationResponse();
		totalLiability.setKeyId(DDRFinancialSummaryFields.TOTAL_LIABILITY.getId());
		totalLiability.setKeyName(DDRFinancialSummaryFields.TOTAL_LIABILITY.getValue());
		
		totalLiability.setProvisionalYear(isCMAUpload ? CommonUtils.checkDouble(cma2018Liabilities.getTotalOutsideLiabilities()) : 
			CommonUtils.checkDouble(coAct2018BalanceSheet.getOthersCurrentLiability()) + CommonUtils.checkDouble(coAct2018BalanceSheet.getTotalCurrentAndNonCurrentLiability()));
		totalLiability.setLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2017Liabilities.getTotalOutsideLiabilities()) : 
			CommonUtils.checkDouble(coAct2017BalanceSheet.getOthersCurrentLiability()) + CommonUtils.checkDouble(coAct2017BalanceSheet.getTotalCurrentAndNonCurrentLiability()));
		totalLiability.setLastToLastYear(isCMAUpload ? CommonUtils.checkDouble(cma2016Liabilities.getTotalOutsideLiabilities()) : 
			CommonUtils.checkDouble(coAct2016BalanceSheet.getOthersCurrentLiability()) + CommonUtils.checkDouble(coAct2016BalanceSheet.getTotalCurrentAndNonCurrentLiability()));
		totalLiability.setDiffPvsnlAndLastYear(calculateFinancialSummary(totalLiability.getProvisionalYear(),totalLiability.getLastYear()));
		
		totalLiability.setProvisionalYearString(isCMAUpload ? CommonUtils.checkString(cma2018Liabilities.getTotalOutsideLiabilities()) : 
			CommonUtils.checkString(CommonUtils.checkDouble(coAct2018BalanceSheet.getOthersCurrentLiability()) + CommonUtils.checkDouble(coAct2018BalanceSheet.getTotalCurrentAndNonCurrentLiability())));
		totalLiability.setLastYearString(isCMAUpload ? CommonUtils.checkString(cma2017Liabilities.getTotalOutsideLiabilities()) : 
			CommonUtils.checkString(CommonUtils.checkDouble(coAct2017BalanceSheet.getOthersCurrentLiability()) + CommonUtils.checkDouble(coAct2017BalanceSheet.getTotalCurrentAndNonCurrentLiability())));
		totalLiability.setLastToLastYearString(isCMAUpload ? CommonUtils.checkString(cma2016Liabilities.getTotalOutsideLiabilities()) : 
			CommonUtils.checkString(CommonUtils.checkDouble(coAct2016BalanceSheet.getOthersCurrentLiability()) + CommonUtils.checkDouble(coAct2016BalanceSheet.getTotalCurrentAndNonCurrentLiability())));
		totalLiability.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(totalLiability.getProvisionalYear(),totalLiability.getLastYear()));
		responseList.add(totalLiability);
		
		DDRCMACalculationResponse leverage = new DDRCMACalculationResponse();
        leverage.setKeyId(DDRFinancialSummaryFields.LEVERAGE.getId());
        leverage.setKeyName(DDRFinancialSummaryFields.LEVERAGE.getValue());
        
        leverage.setProvisionalYear( CommonUtils.checkDouble(netWorthResponse.getProvisionalYear() > 0 ? totalLiability.getProvisionalYear() / netWorthResponse.getProvisionalYear() : 0.0));
        leverage.setLastYear( CommonUtils.checkDouble(netWorthResponse.getLastYear() > 0 ? totalLiability.getLastYear() / netWorthResponse.getLastYear()  : 0.0));
        leverage.setLastToLastYear( CommonUtils.checkDouble(netWorthResponse.getLastToLastYear() > 0 ? totalLiability.getLastToLastYear() / netWorthResponse.getLastToLastYear()  : 0.0));
        leverage.setDiffPvsnlAndLastYear(calculateFinancialSummary(leverage.getProvisionalYear(),leverage.getLastYear()));
        
        leverage.setProvisionalYearString( CommonUtils.checkString(netWorthResponse.getProvisionalYear() > 0 ? totalLiability.getProvisionalYear() / netWorthResponse.getProvisionalYear() : 0.0));
        leverage.setLastYearString( CommonUtils.checkString(netWorthResponse.getLastYear() > 0 ? totalLiability.getLastYear() / netWorthResponse.getLastYear()  : 0.0));
        leverage.setLastToLastYearString( CommonUtils.checkString(netWorthResponse.getLastToLastYear() > 0 ? totalLiability.getLastToLastYear() / netWorthResponse.getLastToLastYear()  : 0.0));
        leverage.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(leverage.getProvisionalYear(),leverage.getLastYear()));
        responseList.add(leverage);
        
        DDRCMACalculationResponse adjustedLeverage = new DDRCMACalculationResponse();
        adjustedLeverage.setKeyId(DDRFinancialSummaryFields.ADJUSTED_LEVERAGE.getId());
        adjustedLeverage.setKeyName(DDRFinancialSummaryFields.ADJUSTED_LEVERAGE.getValue());
        
        adjustedLeverage.setProvisionalYear( CommonUtils.checkDouble(adjustedNetWorth.getProvisionalYear() > 0 ? totalLiability.getProvisionalYear() / adjustedNetWorth.getProvisionalYear() : 0.0));
        adjustedLeverage.setLastYear( CommonUtils.checkDouble(adjustedNetWorth.getLastYear() > 0 ? totalLiability.getLastYear() / adjustedNetWorth.getLastYear() : 0.0));
        adjustedLeverage.setLastToLastYear( CommonUtils.checkDouble(adjustedNetWorth.getLastToLastYear() > 0 ? totalLiability.getLastToLastYear() / adjustedNetWorth.getLastToLastYear() : 0.0));
        adjustedLeverage.setDiffPvsnlAndLastYear(calculateFinancialSummary(adjustedLeverage.getProvisionalYear(),adjustedLeverage.getLastYear()));
                
        adjustedLeverage.setProvisionalYearString( CommonUtils.checkString(adjustedNetWorth.getProvisionalYear() > 0 ? totalLiability.getProvisionalYear() / adjustedNetWorth.getProvisionalYear() : 0.0));
        adjustedLeverage.setLastYearString( CommonUtils.checkString(adjustedNetWorth.getLastYear() > 0 ? totalLiability.getLastYear() / adjustedNetWorth.getLastYear() : 0.0));
        adjustedLeverage.setLastToLastYearString( CommonUtils.checkString(adjustedNetWorth.getLastToLastYear() > 0 ? totalLiability.getLastToLastYear() / adjustedNetWorth.getLastToLastYear() : 0.0));
        adjustedLeverage.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(adjustedLeverage.getProvisionalYear(),adjustedLeverage.getLastYear()));
        responseList.add(adjustedLeverage);
        
		
		DDRCMACalculationResponse capitalEmployed = new DDRCMACalculationResponse();
		capitalEmployed.setKeyId(DDRFinancialSummaryFields.CAPITAL_EMPLOYED.getId());
		capitalEmployed.setKeyName(DDRFinancialSummaryFields.CAPITAL_EMPLOYED.getValue());
		
		if(isCMAUpload) {
			capitalEmployed.setProvisionalYear(CommonUtils.checkDouble(cma2018AssetDetails.getTotalAssets())- CommonUtils.checkDouble(cma2018Liabilities.getTotalCurrentLiabilities()));
			capitalEmployed.setLastYear(CommonUtils.checkDouble(cma2017AssetDetails.getTotalAssets())- CommonUtils.checkDouble(cma2017Liabilities.getTotalCurrentLiabilities()));
			capitalEmployed.setLastToLastYear(CommonUtils.checkDouble(cma2016AssetDetails.getTotalAssets())- CommonUtils.checkDouble(cma2016Liabilities.getTotalCurrentLiabilities()));
		} else {
			capitalEmployed.setProvisionalYear(CommonUtils.checkDouble(coAct2018BalanceSheet.getOrdinaryShareCapital()) + CommonUtils.checkDouble(coAct2018BalanceSheet.getPreferenceShareCapital())
					+ CommonUtils.checkDouble(coAct2018BalanceSheet.getShareApplicationPendingAllotment()));
			capitalEmployed.setLastYear(CommonUtils.checkDouble(coAct2017BalanceSheet.getOrdinaryShareCapital()) + CommonUtils.checkDouble(coAct2017BalanceSheet.getPreferenceShareCapital())
					+ CommonUtils.checkDouble(coAct2017BalanceSheet.getShareApplicationPendingAllotment()));
			capitalEmployed.setLastToLastYear(CommonUtils.checkDouble(coAct2016BalanceSheet.getOrdinaryShareCapital()) + CommonUtils.checkDouble(coAct2016BalanceSheet.getPreferenceShareCapital())
					+ CommonUtils.checkDouble(coAct2016BalanceSheet.getShareApplicationPendingAllotment()));
		}
		capitalEmployed.setDiffPvsnlAndLastYear(calculateFinancialSummary(capitalEmployed.getProvisionalYear(),capitalEmployed.getLastYear()));
		
		if(isCMAUpload) {
			capitalEmployed.setProvisionalYearString(CommonUtils.checkString(CommonUtils.checkDouble(cma2018AssetDetails.getTotalAssets())- CommonUtils.checkDouble(cma2018Liabilities.getTotalCurrentLiabilities())));
			capitalEmployed.setLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(cma2017AssetDetails.getTotalAssets())- CommonUtils.checkDouble(cma2017Liabilities.getTotalCurrentLiabilities())));
			capitalEmployed.setLastToLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(cma2016AssetDetails.getTotalAssets())- CommonUtils.checkDouble(cma2016Liabilities.getTotalCurrentLiabilities())));
		} else {
			capitalEmployed.setProvisionalYearString(CommonUtils.checkString(CommonUtils.checkDouble(coAct2018BalanceSheet.getOrdinaryShareCapital()) + CommonUtils.checkDouble(coAct2018BalanceSheet.getPreferenceShareCapital())
					+ CommonUtils.checkDouble(coAct2018BalanceSheet.getShareApplicationPendingAllotment())));
			capitalEmployed.setLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(coAct2017BalanceSheet.getOrdinaryShareCapital()) + CommonUtils.checkDouble(coAct2017BalanceSheet.getPreferenceShareCapital())
					+ CommonUtils.checkDouble(coAct2017BalanceSheet.getShareApplicationPendingAllotment())));
			capitalEmployed.setLastToLastYearString(CommonUtils.checkString(CommonUtils.checkDouble(coAct2016BalanceSheet.getOrdinaryShareCapital()) + CommonUtils.checkDouble(coAct2016BalanceSheet.getPreferenceShareCapital())
					+ CommonUtils.checkDouble(coAct2016BalanceSheet.getShareApplicationPendingAllotment())));
		}
		capitalEmployed.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(capitalEmployed.getProvisionalYear(),capitalEmployed.getLastYear()));
		responseList.add(capitalEmployed);
		

        DDRCMACalculationResponse gearingResp = new DDRCMACalculationResponse();
        gearingResp.setKeyId(DDRFinancialSummaryFields.GEARING.getId());
        gearingResp.setKeyName(DDRFinancialSummaryFields.GEARING.getValue());
        
        gearingResp.setProvisionalYear( CommonUtils.checkDouble(netWorthResponse.getProvisionalYear() > 0 ? totalDebt.getProvisionalYear() / netWorthResponse.getProvisionalYear() : 0.0));
        gearingResp.setLastYear( CommonUtils.checkDouble(netWorthResponse.getLastYear() > 0  ? totalDebt.getLastYear() / netWorthResponse.getLastYear() : 0.0));
        gearingResp.setLastToLastYear( CommonUtils.checkDouble(netWorthResponse.getLastToLastYear() > 0 ? totalDebt.getLastToLastYear() / netWorthResponse.getLastToLastYear() : 0.0));
        gearingResp.setDiffPvsnlAndLastYear(calculateFinancialSummary(gearingResp.getProvisionalYear(),gearingResp.getLastYear()));
        
        
        gearingResp.setProvisionalYearString( CommonUtils.checkString(netWorthResponse.getProvisionalYear() > 0 ? totalDebt.getProvisionalYear() / netWorthResponse.getProvisionalYear() : 0.0));
        gearingResp.setLastYearString( CommonUtils.checkString(netWorthResponse.getLastYear() > 0  ? totalDebt.getLastYear() / netWorthResponse.getLastYear() : 0.0));
        gearingResp.setLastToLastYearString( CommonUtils.checkString(netWorthResponse.getLastToLastYear() > 0 ? totalDebt.getLastToLastYear() / netWorthResponse.getLastToLastYear() : 0.0));
        gearingResp.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(gearingResp.getProvisionalYear(),gearingResp.getLastYear()));
        responseList.add(gearingResp);
        
        DDRCMACalculationResponse adjustedGearingResp = new DDRCMACalculationResponse();
        adjustedGearingResp.setKeyId(DDRFinancialSummaryFields.ADJUSTED_GEARING.getId());
        adjustedGearingResp.setKeyName(DDRFinancialSummaryFields.ADJUSTED_GEARING.getValue());
        
        adjustedGearingResp.setProvisionalYear( CommonUtils.checkDouble(adjustedNetWorth.getProvisionalYear() > 0 ? totalDebt.getProvisionalYear() / adjustedNetWorth.getProvisionalYear() : 0.0));
        adjustedGearingResp.setLastYear( CommonUtils.checkDouble(adjustedNetWorth.getLastYear() > 0 ? totalDebt.getLastYear() / adjustedNetWorth.getLastYear() : 0.0));
        adjustedGearingResp.setLastToLastYear( CommonUtils.checkDouble(adjustedNetWorth.getLastToLastYear() > 0 ? totalDebt.getLastToLastYear() / adjustedNetWorth.getLastToLastYear() : 0.0));
        adjustedGearingResp.setDiffPvsnlAndLastYear(calculateFinancialSummary(adjustedGearingResp.getProvisionalYear(),adjustedGearingResp.getLastYear()));
        
        adjustedGearingResp.setProvisionalYearString( CommonUtils.checkString(adjustedNetWorth.getProvisionalYear() > 0 ? totalDebt.getProvisionalYear() / adjustedNetWorth.getProvisionalYear() : 0.0));
        adjustedGearingResp.setLastYearString( CommonUtils.checkString(adjustedNetWorth.getLastYear() > 0 ? totalDebt.getLastYear() / adjustedNetWorth.getLastYear() : 0.0));
        adjustedGearingResp.setLastToLastYearString( CommonUtils.checkString(adjustedNetWorth.getLastToLastYear() > 0 ? totalDebt.getLastToLastYear() / adjustedNetWorth.getLastToLastYear() : 0.0));
        adjustedGearingResp.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(adjustedGearingResp.getProvisionalYear(),adjustedGearingResp.getLastYear()));
        responseList.add(adjustedGearingResp);
		
        DDRCMACalculationResponse currentRatio = new DDRCMACalculationResponse();
        currentRatio.setKeyId(DDRFinancialSummaryFields.CURRENT_RATIO.getId());
        currentRatio.setKeyName(DDRFinancialSummaryFields.CURRENT_RATIO.getValue());
        
        if(isCMAUpload) {
            currentRatio.setProvisionalYear(CommonUtils.checkDouble(cma2018Liabilities.getTotalCurrentLiabilities()) > 0 ? CommonUtils.checkDouble(CommonUtils.checkDouble(cma2018AssetDetails.getTotalCurrentAssets()) / cma2018Liabilities.getTotalCurrentLiabilities()) : 0.0);
            currentRatio.setLastYear(CommonUtils.checkDouble(cma2017Liabilities.getTotalCurrentLiabilities()) > 0 ?CommonUtils.checkDouble(CommonUtils.checkDouble(cma2017AssetDetails.getTotalCurrentAssets()) /  cma2017Liabilities.getTotalCurrentLiabilities()) : 0.0);
            currentRatio.setLastToLastYear(CommonUtils.checkDouble(cma2016Liabilities.getTotalCurrentLiabilities()) > 0 ? CommonUtils.checkDouble(CommonUtils.checkDouble(cma2016AssetDetails.getTotalCurrentAssets()) / cma2016Liabilities.getTotalCurrentLiabilities()) : 0.0);
        } else {
            currentRatio.setProvisionalYear(CommonUtils.checkDouble(coAct2018BalanceSheet.getOthersCurrentLiability()) > 0 ?CommonUtils.checkDouble(CommonUtils.checkDouble(coAct2018BalanceSheet.getOthersCurrentAssets()) / coAct2018BalanceSheet.getOthersCurrentLiability()) : 0.0);
            currentRatio.setLastYear(CommonUtils.checkDouble(coAct2017BalanceSheet.getOthersCurrentLiability()) > 0 ? CommonUtils.checkDouble(CommonUtils.checkDouble(coAct2017BalanceSheet.getOthersCurrentAssets()) / coAct2017BalanceSheet.getOthersCurrentLiability()) : 0.0);
            currentRatio.setLastToLastYear(CommonUtils.checkDouble(coAct2016BalanceSheet.getOthersCurrentLiability()) > 0 ? CommonUtils.checkDouble(CommonUtils.checkDouble(coAct2016BalanceSheet.getOthersCurrentAssets()) / coAct2016BalanceSheet.getOthersCurrentLiability()) : 0.0);
        }
        currentRatio.setDiffPvsnlAndLastYear(calculateFinancialSummary(currentRatio.getProvisionalYear(),currentRatio.getLastYear()));
        
        if(isCMAUpload) {
            currentRatio.setProvisionalYearString(CommonUtils.checkDouble(cma2018Liabilities.getTotalCurrentLiabilities()) > 0 ? CommonUtils.checkString(CommonUtils.checkDouble(CommonUtils.checkDouble(cma2018AssetDetails.getTotalCurrentAssets()) / cma2018Liabilities.getTotalCurrentLiabilities())) : "0.00");
            currentRatio.setLastYearString(CommonUtils.checkDouble(cma2017Liabilities.getTotalCurrentLiabilities()) > 0 ?CommonUtils.checkString(CommonUtils.checkDouble(cma2017AssetDetails.getTotalCurrentAssets()) /  cma2017Liabilities.getTotalCurrentLiabilities()) : "0.00");
            currentRatio.setLastToLastYearString(CommonUtils.checkDouble(cma2016Liabilities.getTotalCurrentLiabilities()) > 0 ? CommonUtils.checkString(CommonUtils.checkDouble(cma2016AssetDetails.getTotalCurrentAssets()) / cma2016Liabilities.getTotalCurrentLiabilities()) : "0.00");
        } else {
            currentRatio.setProvisionalYearString(CommonUtils.checkDouble(coAct2018BalanceSheet.getOthersCurrentLiability()) > 0 ?CommonUtils.checkString(CommonUtils.checkDouble(coAct2018BalanceSheet.getOthersCurrentAssets()) / coAct2018BalanceSheet.getOthersCurrentLiability()) : "0.00");
            currentRatio.setLastYearString(CommonUtils.checkDouble(coAct2017BalanceSheet.getOthersCurrentLiability()) > 0 ? CommonUtils.checkString(CommonUtils.checkDouble(coAct2017BalanceSheet.getOthersCurrentAssets()) / coAct2017BalanceSheet.getOthersCurrentLiability()) : "0.00");
            currentRatio.setLastToLastYearString(CommonUtils.checkDouble(coAct2016BalanceSheet.getOthersCurrentLiability()) > 0 ? CommonUtils.checkString(CommonUtils.checkDouble(coAct2016BalanceSheet.getOthersCurrentAssets()) / coAct2016BalanceSheet.getOthersCurrentLiability()) : "0.00");
        }
        currentRatio.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(currentRatio.getProvisionalYear(),currentRatio.getLastYear()));
        responseList.add(currentRatio);
        
        
        DDRCMACalculationResponse inventoryTurnOver = new DDRCMACalculationResponse();
        inventoryTurnOver.setKeyId(DDRFinancialSummaryFields.INVENTORY_TURNOVER.getId());
        inventoryTurnOver.setKeyName(DDRFinancialSummaryFields.INVENTORY_TURNOVER.getValue());
        
        if(isCMAUpload) {
        	double proPriviousCal = CommonUtils.checkDouble(cma2018AssetDetails.getInventory()) + CommonUtils.checkDouble(cma2017AssetDetails.getInventory());
        	double provisionalYear = proPriviousCal > 0 ? totalSalesResponse.getProvisionalYear() / proPriviousCal : 0.0;
            inventoryTurnOver.setProvisionalYear(CommonUtils.checkDouble(provisionalYear / 2));
            double lastPriviousCal = CommonUtils.checkDouble(cma2017AssetDetails.getInventory()) + CommonUtils.checkDouble(cma2016AssetDetails.getInventory());
        	double lastYear = lastPriviousCal > 0 ? totalSalesResponse.getLastYear() / lastPriviousCal : 0.0;
            inventoryTurnOver.setLastYear(CommonUtils.checkDouble(lastYear / 2));
            double lastToLastPriviousCal = CommonUtils.checkDouble(cma2016AssetDetails.getInventory()) + CommonUtils.checkDouble(cma2015AssetDetails.getInventory());
        	double lastToLastYear = lastToLastPriviousCal > 0 ? totalSalesResponse.getLastToLastYear() / lastToLastPriviousCal : 0.0;
            inventoryTurnOver.setLastToLastYear(CommonUtils.checkDouble(lastToLastYear / 2));
        } else {
        	double proPriviousCal = CommonUtils.checkDouble(coAct2018BalanceSheet.getInventory()) + CommonUtils.checkDouble(coAct2017BalanceSheet.getInventory());
        	double provisionalYear = proPriviousCal > 0 ? totalSalesResponse.getProvisionalYear() / proPriviousCal : 0.0;
            inventoryTurnOver.setProvisionalYear(CommonUtils.checkDouble(provisionalYear / 2));
            double lastPriviousCal = CommonUtils.checkDouble(coAct2017BalanceSheet.getInventory()) + CommonUtils.checkDouble(coAct2016BalanceSheet.getInventory());
        	double lastYear = lastPriviousCal > 0 ? totalSalesResponse.getLastYear() / lastPriviousCal : 0.0;
            inventoryTurnOver.setLastYear(CommonUtils.checkDouble(lastYear / 2));
            double lastToLastPriviousCal = CommonUtils.checkDouble(coAct2016BalanceSheet.getInventory()) + CommonUtils.checkDouble(coAct2015BalanceSheet.getInventory());
        	double lastToLastYear = lastToLastPriviousCal > 0 ? totalSalesResponse.getLastToLastYear() / lastToLastPriviousCal : 0.0;
            inventoryTurnOver.setLastToLastYear(CommonUtils.checkDouble(lastToLastYear / 2));
        }
        inventoryTurnOver.setDiffPvsnlAndLastYear(calculateFinancialSummary(inventoryTurnOver.getProvisionalYear(),inventoryTurnOver.getLastYear()));
        
        
        if(isCMAUpload) {
        	double proPriviousCal = CommonUtils.checkDouble(cma2018AssetDetails.getInventory()) + CommonUtils.checkDouble(cma2017AssetDetails.getInventory());
        	double provisionalYear = proPriviousCal > 0 ? totalSalesResponse.getProvisionalYear() / proPriviousCal : 0.0;
            inventoryTurnOver.setProvisionalYearString(CommonUtils.checkString(provisionalYear / 2));
            double lastPriviousCal = CommonUtils.checkDouble(cma2017AssetDetails.getInventory()) + CommonUtils.checkDouble(cma2016AssetDetails.getInventory());
        	double lastYear = lastPriviousCal > 0 ? totalSalesResponse.getLastYear() / lastPriviousCal : 0.0;
            inventoryTurnOver.setLastYearString(CommonUtils.checkString(lastYear / 2));
            double lastToLastPriviousCal = CommonUtils.checkDouble(cma2016AssetDetails.getInventory()) + CommonUtils.checkDouble(cma2015AssetDetails.getInventory());
        	double lastToLastYear = lastToLastPriviousCal > 0 ? totalSalesResponse.getLastToLastYear() / lastToLastPriviousCal : 0.0;
            inventoryTurnOver.setLastToLastYearString(CommonUtils.checkString(lastToLastYear / 2));
        } else {
        	double proPriviousCal = CommonUtils.checkDouble(coAct2018BalanceSheet.getInventory()) + CommonUtils.checkDouble(coAct2017BalanceSheet.getInventory());
        	double provisionalYear = proPriviousCal > 0 ? totalSalesResponse.getProvisionalYear() / proPriviousCal : 0.0;
            inventoryTurnOver.setProvisionalYearString(CommonUtils.checkString(provisionalYear / 2));
            double lastPriviousCal = CommonUtils.checkDouble(coAct2017BalanceSheet.getInventory()) + CommonUtils.checkDouble(coAct2016BalanceSheet.getInventory());
        	double lastYear = lastPriviousCal > 0 ? totalSalesResponse.getLastYear() / lastPriviousCal : 0.0;
            inventoryTurnOver.setLastYearString(CommonUtils.checkString(lastYear / 2));
            double lastToLastPriviousCal = CommonUtils.checkDouble(coAct2016BalanceSheet.getInventory()) + CommonUtils.checkDouble(coAct2015BalanceSheet.getInventory());
        	double lastToLastYear = lastToLastPriviousCal > 0 ? totalSalesResponse.getLastToLastYear() / lastToLastPriviousCal : 0.0;
            inventoryTurnOver.setLastToLastYearString(CommonUtils.checkString(lastToLastYear / 2));
        }
        inventoryTurnOver.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(inventoryTurnOver.getProvisionalYear(),inventoryTurnOver.getLastYear()));
        responseList.add(inventoryTurnOver);
        
        
        DDRCMACalculationResponse workingCapitalCycle = new DDRCMACalculationResponse();
        workingCapitalCycle.setKeyId(DDRFinancialSummaryFields.WORKING_CAPITAL_CYCLE.getId());
        workingCapitalCycle.setKeyName(DDRFinancialSummaryFields.WORKING_CAPITAL_CYCLE.getValue());
    	
        double workingCapital2018 = totalCurrentAsset.getProvisionalYear() - totalCurrentLiability.getProvisionalYear();
    	double workingCapital2017 = totalCurrentAsset.getLastYear() - totalCurrentLiability.getLastYear();
    	double workingCapital2016 = totalCurrentAsset.getLastToLastYear() - totalCurrentLiability.getLastToLastYear();
    	double totalCurrentAsset2015 = isCMAUpload ? CommonUtils.checkDouble(cma2015AssetDetails.getTotalCurrentAssets())  : CommonUtils.checkDouble(coAct2015BalanceSheet.getOthersCurrentAssets());
    	double totalCurrentLiability2015 = isCMAUpload ? CommonUtils.checkDouble(cma2015Liabilities.getTotalCurrentLiabilities()) : CommonUtils.checkDouble(coAct2015BalanceSheet.getOthersCurrentLiability());
    	double workingCapital2015 = totalCurrentAsset2015 - totalCurrentLiability2015;
    	
    	double avgWorkingCapital2018 = (workingCapital2018  + workingCapital2017) / 2;
    	double avgWorkingCapital2017 = (workingCapital2017  + workingCapital2016) / 2;
    	double avgWorkingCapital2016 = (workingCapital2016  + workingCapital2015) / 2;
    	

        workingCapitalCycle.setProvisionalYear(totalSalesResponse.getProvisionalYear() > 0 
        		? CommonUtils.checkDouble((avgWorkingCapital2018 / totalSalesResponse.getProvisionalYear()) * 356) : 0.0);
        workingCapitalCycle.setLastYear(totalSalesResponse.getLastYear() > 0 
        		? CommonUtils.checkDouble((avgWorkingCapital2017 / totalSalesResponse.getLastYear()) * 356) : 0.0);
        workingCapitalCycle.setLastToLastYear(totalSalesResponse.getLastToLastYear() > 0 
        		? CommonUtils.checkDouble((avgWorkingCapital2016 / totalSalesResponse.getLastToLastYear()) * 356) : 0.0);
        workingCapitalCycle.setDiffPvsnlAndLastYear(calculateFinancialSummary(workingCapitalCycle.getProvisionalYear(),workingCapitalCycle.getLastYear()));
		
    	
        workingCapitalCycle.setProvisionalYearString(totalSalesResponse.getProvisionalYear() > 0 
        		? CommonUtils.checkString(CommonUtils.checkDouble((avgWorkingCapital2018 / totalSalesResponse.getProvisionalYear()) * 356) ): "0.00");
        workingCapitalCycle.setLastYearString(totalSalesResponse.getLastYear() > 0 
        		? CommonUtils.checkString(CommonUtils.checkDouble((avgWorkingCapital2017 / totalSalesResponse.getLastYear()) * 356) ): "0.00");
        workingCapitalCycle.setLastToLastYearString(totalSalesResponse.getLastToLastYear() > 0 
        		?CommonUtils.checkString( CommonUtils.checkDouble((avgWorkingCapital2016 / totalSalesResponse.getLastToLastYear()) * 356) ): "0.00");
        workingCapitalCycle.setDiffPvsnlAndLastYearString(calculateFinancialSummaryString(workingCapitalCycle.getProvisionalYear(),workingCapitalCycle.getLastYear()));
        responseList.add(workingCapitalCycle);
		
		return responseList;
	
		
	}
	
	private double calculateFinancialSummary(Double provisinalYear, Double lastYear) {
		try{
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
    if(!CommonUtils.isObjectNullOrEmpty(provisinalYear) && !CommonUtils.isObjectNullOrEmpty(lastYear)) {
        if((provisinalYear > 0 && lastYear > 0) || (provisinalYear > 0 || lastYear > 0)) {
            return Double.valueOf(decimalFormat.format(((provisinalYear-lastYear) / lastYear) * 100));
        }
    }
    return 0.0;
		}
		catch (Exception e) {
			return 0.00;
		}
    }
	
	private String calculateFinancialSummaryString(Double provisinalYear, Double lastYear) {
		try{
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
    if(!CommonUtils.isObjectNullOrEmpty(provisinalYear) && !CommonUtils.isObjectNullOrEmpty(lastYear)) {
        if((provisinalYear > 0 && lastYear > 0) || (provisinalYear > 0 || lastYear > 0)) {
            return decimalFormat.format(((provisinalYear-lastYear) / lastYear) * 100);
        }
    }
    return "0.00";
		}
		catch (Exception e) {
			return "0.00";
		}
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JSONObject> getFinancialSummaryFieldsList() {
		List<JSONObject> responseList = new ArrayList<>();
		for(DDRFinancialSummaryFields dDRFinancialSummary : DDRFinancialSummaryFields.values()) {
			JSONObject obj = new JSONObject();
			obj.put("id", dDRFinancialSummary.getId());
			obj.put("value", dDRFinancialSummary.getValue());
			responseList.add(obj);
		}
		return responseList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JSONObject> getFinancialSummaryToBeFieldsList() {
		List<JSONObject> responseList = new ArrayList<>();
		for(DDRFinancialSummaryToBeFields dDRFinancialSummary : DDRFinancialSummaryToBeFields.values()) {
			JSONObject obj = new JSONObject();
			obj.put("id", dDRFinancialSummary.getId());
			obj.put("value", dDRFinancialSummary.getValue());
			responseList.add(obj);
		}
		return responseList;
	}

	@Override
	public Long saveDocumentFLag(DocumentUploadFlagRequest documentUploadFlagRequest) throws Exception {
//		DDRFormDetailsRequest
		try{
		DDRFormDetails dDRFormDetails = ddrFormDetailsRepository.getByAppIdAndIsActive(documentUploadFlagRequest.getApplicationId());
		if(CommonUtils.isObjectNullOrEmpty(dDRFormDetails)){
			dDRFormDetails = new DDRFormDetails();
			dDRFormDetails.setApplicationId(documentUploadFlagRequest.getApplicationId());
			dDRFormDetails.setUserId(documentUploadFlagRequest.getUserId());
		}
		
		final int switchCase = documentUploadFlagRequest.getDocumentMappingId().intValue();
		switch (switchCase) {
		case 10:
			dDRFormDetails.setSanctionLetter("Yes");
			break;

		default:
			break;
		}
		ddrFormDetailsRepository.save(dDRFormDetails);
		return 1L;
		}
		catch (Exception e) {
			return 0L;
		}
	}

	
}
