package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.service.loans.domain.fundseeker.FsNegativeFpList;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.BankingRelation;
import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.retail.BankRelationshipRequest;
import com.capitaworld.service.loans.model.retail.HLOneformPrimaryRes;
import com.capitaworld.service.loans.model.retail.HLOneformRequest;
import com.capitaworld.service.loans.model.retail.HLOnefromResponse;
import com.capitaworld.service.loans.model.retail.PrimaryHomeLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.FsNegativeFpListRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinancialArrangementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.BankingRelationlRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryHomeLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryHomeLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryHomeLoanServiceImpl implements PrimaryHomeLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryHomeLoanServiceImpl.class.getName());
	
	@Autowired
	private PrimaryHomeLoanDetailRepository primaryHomeLoanDetailRepository;
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository; 

	@Autowired
	private FsNegativeFpListRepository fsNegativeFpListRepository;
	
	@Autowired
	private CoApplicantDetailRepository coApplicantDetailRepository;
	
	@Autowired
	private ConnectClient connectClient;
	
	@Autowired
    private FinancialArrangementDetailsRepository financialArrangementDetailsRepository;
	
	@Autowired
    private BankingRelationlRepository bankingRelationlRepository;
	
	@Override
	public boolean saveOrUpdate(PrimaryHomeLoanDetailRequest homeLoanDetailRequest,Long userId) throws LoansException {
		// ID must not be null
		try{
		Long finalUserId = (CommonUtils.isObjectNullOrEmpty(homeLoanDetailRequest.getClientId()) ? userId : homeLoanDetailRequest.getClientId());
		PrimaryHomeLoanDetail primaryHomeLoanDetail= primaryHomeLoanDetailRepository.getByApplicationAndUserId(homeLoanDetailRequest.getId(),finalUserId);
		if (primaryHomeLoanDetail == null) {
			throw new NullPointerException(
					"PrimaryHomeLoanDetail not exist in DB with Application Id=>" + homeLoanDetailRequest.getId() + " and user Id ==>" + userId); 
		}
		BeanUtils.copyProperties(homeLoanDetailRequest, primaryHomeLoanDetail, CommonUtils.IgnorableCopy.getCORPORATE());
		primaryHomeLoanDetail.setTenure(CommonUtils.isObjectNullOrEmpty(homeLoanDetailRequest.getTenure()) ? null : (homeLoanDetailRequest.getTenure() * 12));
		primaryHomeLoanDetail.setModifiedBy(userId);
		primaryHomeLoanDetail.setModifiedDate(new Date());
		primaryHomeLoanDetail.setIsActive(true);
		primaryHomeLoanDetail = primaryHomeLoanDetailRepository.save(primaryHomeLoanDetail);
		
		//Updating Primary Flag
		loanApplicationRepository.setPrimaryFilledCount(primaryHomeLoanDetail.getId(), finalUserId, homeLoanDetailRequest.getPrimaryFilledCount());
		//save negative list
		fsNegativeFpListRepository.inActiveMappingByApplicationId(primaryHomeLoanDetail.getApplicationId().getId());
		saveNegativeList(primaryHomeLoanDetail.getApplicationId().getId(), homeLoanDetailRequest.getNegativeList());
		return true;
		} catch (Exception e) {
			logger.error("Error while saving Primary Working Details Profile:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	private void saveNegativeList(Long id, List<Long> negativeList) {
		FsNegativeFpList fsNegativeFpList= null;
		for (Long fpId : negativeList) {
			fsNegativeFpList = new FsNegativeFpList();
			fsNegativeFpList.setApplicationId(id);
			fsNegativeFpList.setFpId(fpId);
			fsNegativeFpList.setCreatedBy(id);
			fsNegativeFpList.setModifiedBy(id);
			fsNegativeFpList.setCreatedDate(new Date());
			fsNegativeFpList.setModifiedDate(new Date());
			fsNegativeFpList.setIsActive(true);
			// create by and update
			fsNegativeFpListRepository.save(fsNegativeFpList);
		}
		
	}
	
	@Override
	public PrimaryHomeLoanDetailRequest get(Long applicationId,Long userId) throws LoansException {
		try{
		PrimaryHomeLoanDetail loanDetail = primaryHomeLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
		if (loanDetail == null) {
			throw new NullPointerException("PrimaryHomeLoanDetail not exist in DB with applicationId=>" + applicationId + " and UserId==>"+ userId);
		}
		PrimaryHomeLoanDetailRequest primaryHomeLoanDetailRequest= new PrimaryHomeLoanDetailRequest();
		//get fp negative list
		primaryHomeLoanDetailRequest.setNegativeList(fsNegativeFpListRepository.getListByApplicationId(applicationId));
		BeanUtils.copyProperties(loanDetail, primaryHomeLoanDetailRequest);
		primaryHomeLoanDetailRequest.setTenure(CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? null : (loanDetail.getTenure() / 12));
		Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
		primaryHomeLoanDetailRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
		return primaryHomeLoanDetailRequest;
		} catch (Exception e) {
			logger.error("Error while getting Primary Home Loan Details Profile:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	/**
	 * Author : Harshit
	 * Date : 26/04/2019
	 * GET ONEFORM DETAILS BY APPLICATION ID OR CO-APPLICANT ID in FS JOURNEY
	 */
	@Override
	public HLOneformRequest getProfileDetailsById(Long applicationId,Long coAppId) {
		HLOneformRequest res = new HLOneformRequest();
		res.setApplicationId(applicationId);
		if(!CommonUtils.isObjectNullOrEmpty(coAppId)) {
			CoApplicantDetail coApplicantDetail = coApplicantDetailRepository.findByIdAndIsActive(coAppId, true);
			if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail)) {
				BeanUtils.copyProperties(coApplicantDetail, res);
				if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBusinessStartDate())) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(coApplicantDetail.getBusinessStartDate());
					res.setBusinessStartMonth(cal.get(Calendar.MONTH));
					res.setBusinessStartYear(cal.get(Calendar.YEAR));
				}
				if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAddressCity())) {
					res.setAddressCity(coApplicantDetail.getAddressCity().longValue());	
				}
				if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAddressState())) {
					res.setAddressState(coApplicantDetail.getAddressState().longValue());				
				}
				res.setAddressCountry(coApplicantDetail.getAddressCountry());
				if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAddressPincode())) {
					res.setAddressPincode(coApplicantDetail.getAddressPincode().longValue());	
				}
				res.setCoAppId(coAppId);
			}
		} else {
			RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
				BeanUtils.copyProperties(retailApplicantDetail, res);
				if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBusinessStartDate())) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(retailApplicantDetail.getBusinessStartDate());
					res.setBusinessStartMonth(cal.get(Calendar.MONTH));
					res.setBusinessStartYear(cal.get(Calendar.YEAR));
				}
				return res;
			}
		}
		return res;  
	}
	
	/**
	 * Author : Harshit
	 * Date : 27/04/2019
	 * GET LIST OF CO-APPLICANT AND APPLICANT BASIC DETAILS BY APPLICATION ID IN ONEFORM PAGE (FS JOURNEY)
	 */
	@Override
	public HLOnefromResponse getListForOneForm(Long applicationId) {
		
		HLOnefromResponse res = new HLOnefromResponse();
		
		Boolean isOneformProfileCompl = false;//CHECK IS PROFILE COMPLETE OR NOT 
		Boolean isOneformPrimaryCompl = false;//CHECK IS PRIMARY COMPLETE OR NOT
		
		List<HLOnefromResponse> resList = new ArrayList<>();
		Object[] obj = retailApplicantDetailRepository.getBasicDetailsByAppId(applicationId);
		if(obj != null && obj.length > 0) {
			Object[] objs = (Object[])obj[0];
			HLOnefromResponse onefromResponse = setValueFromObj(objs, applicationId,false);
			if(!CommonUtils.isObjectNullOrEmpty(onefromResponse.getIsCibilComplete()) && !CommonUtils.isObjectNullOrEmpty(onefromResponse.getIsOneformComplete())) {
				if(onefromResponse.getIsCibilComplete() && onefromResponse.getIsOneformComplete()) {
					isOneformProfileCompl = true;
				}	
			}
			isOneformPrimaryCompl = CommonUtils.convertBoolean(objs[4]);
			resList.add(onefromResponse);
		}
		List<Object[]> objList = coApplicantDetailRepository.getBasicDetailsByAppId(applicationId);
		for(Object[] objs : objList) {
			HLOnefromResponse onefromResponse = setValueFromObj(objs, applicationId,true);
			if(!CommonUtils.isObjectNullOrEmpty(onefromResponse.getIsCibilComplete()) && !CommonUtils.isObjectNullOrEmpty(onefromResponse.getIsOneformComplete())) {
				if(onefromResponse.getIsCibilComplete() && onefromResponse.getIsOneformComplete()) {
					isOneformProfileCompl = true;
				} else {
					isOneformProfileCompl = false;
				}	
			}
			resList.add(onefromResponse);
		}
		res.setIsProfileComplete(isOneformProfileCompl);
		res.setIsPrimaryComplete(isOneformPrimaryCompl);
		res.setResList(resList);
		return res;
	}
	
	private HLOnefromResponse setValueFromObj(Object[] obj,Long applicationId,boolean isCoApp) {
		HLOnefromResponse res = new HLOnefromResponse();
		res.setApplicationId(applicationId);
		res.setName(CommonUtils.convertString(obj[0]) +" " + CommonUtils.convertString(obj[1]));
		res.setIsOneformComplete(CommonUtils.convertBoolean(obj[2]));
		res.setIsCibilComplete(CommonUtils.convertBoolean(obj[3]));
		if(isCoApp) {
			res.setCoAppId(CommonUtils.convertLong(obj[4]));	
		}
		return res;
	}
	
	/**
	 * Author : Harshit
	 * Date : 27/04/2019
	 * SAVE ONEFORM PROFILE DETAILS FOR APPLICANT AND CO-APPLICANT
	 */
	@Override
	public boolean saveProfileOneForm(HLOneformRequest req) throws LoansException {
		if(!CommonUtils.isObjectNullOrEmpty(req.getCoAppId())) {
			CoApplicantDetail coApplicantDetail = coApplicantDetailRepository.findByIdAndIsActive(req.getCoAppId(), true);
			if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail)) {
				BeanUtils.copyProperties(req, coApplicantDetail,"applicationId","userId","id");
				if(!CommonUtils.isObjectNullOrEmpty(req.getAddressCity())) {
					coApplicantDetail.setAddressCity(req.getAddressCity().intValue());	
				}
				if(!CommonUtils.isObjectNullOrEmpty(req.getAddressState())) {
					coApplicantDetail.setAddressState(req.getAddressState().intValue());				
				}
				coApplicantDetail.setAddressCountry(req.getAddressCountry());
				if(!CommonUtils.isObjectNullOrEmpty(req.getAddressPincode())) {
					coApplicantDetail.setAddressPincode(BigInteger.valueOf(req.getAddressPincode()));	
				}
				if(!CommonUtils.isObjectNullOrEmpty(req.getBusinessStartMonth()) && !CommonUtils.isObjectNullOrEmpty(req.getBusinessStartYear())) {
					Calendar cal = Calendar.getInstance();
					cal.set(req.getBusinessStartYear(), req.getBusinessStartMonth(), 01);
					coApplicantDetail.setBusinessStartDate(cal.getTime());
				}
				coApplicantDetail.setEmail(req.getEmail());
				coApplicantDetail.setModifiedBy(req.getUserId());
				coApplicantDetail.setModifiedDate(new Date());
				coApplicantDetail.setIsOneFormCompleted(req.getIsOneFormCompleted());
				coApplicantDetail.setIsCibilCompleted(req.getIsCibilCompleted());
				coApplicantDetailRepository.save(coApplicantDetail);
				return true;
			}
		} else {
			RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(req.getApplicationId());
			if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
				BeanUtils.copyProperties(req, retailApplicantDetail,"applicationId","userId","id");
				if(!CommonUtils.isObjectNullOrEmpty(req.getBusinessStartMonth()) && !CommonUtils.isObjectNullOrEmpty(req.getBusinessStartYear())) {
					Calendar cal = Calendar.getInstance();
					cal.set(req.getBusinessStartYear(), req.getBusinessStartMonth(), 01);
					retailApplicantDetail.setBusinessStartDate(cal.getTime());
				}
				retailApplicantDetail.setEmail(req.getEmail());
				retailApplicantDetail.setModifiedBy(req.getUserId());
				retailApplicantDetail.setModifiedDate(new Date());
				retailApplicantDetail.setIsOneFormCompleted(req.getIsOneFormCompleted());
				retailApplicantDetail.setIsCibilCompleted(req.getIsCibilCompleted());
				retailApplicantDetailRepository.save(retailApplicantDetail);
				
				try {
					ConnectResponse connectResponse = connectClient.postOneForm(req.getApplicationId(), req.getUserId(), CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId());
					if(!CommonUtils.isObjectNullOrEmpty(connectResponse) && connectResponse.getProceed()) {
						return true;
					}
					throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
				} catch (Exception e) {
					logger.error("Exception while call Connect Post Oneform",e);
					throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
				}
				
			}
		}
		return false;
	}
	
	/** 
	 * GET LOAN REQUIREMENT DETAILS 
	 * Author : Harshit
	 * Date : 29/04/2019
	 * @param applicationId
	 * @return
	 */
	@Override
	public HLOneformPrimaryRes getOneformPrimaryDetails(Long applicationId) {
		RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(applicationId);
		if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
			HLOneformPrimaryRes res = new HLOneformPrimaryRes();
			res.setApplicationId(applicationId);
			res.setLoanAmountRequired(retailApplicantDetail.getLoanAmountRequired());
			res.setLoanPurpose(retailApplicantDetail.getLoanPurpose());
			res.setLoanPurposeOther(retailApplicantDetail.getLoanPurposeOther());
			res.setTenureRequired(retailApplicantDetail.getTenureRequired());
			res.setRepayment(retailApplicantDetail.getRepayment());
			res.setSalaryMode(retailApplicantDetail.getSalaryMode());
			res.setSalaryBankName(retailApplicantDetail.getSalaryBankName());
			res.setIsOtherSalaryBank(retailApplicantDetail.getIsOtherSalaryBank());
			res.setSalaryBankYear(retailApplicantDetail.getSalaryBankYear());
			res.setSalaryBankMonth(retailApplicantDetail.getSalaryBankMonth());
			
			PrimaryHomeLoanDetail prHlDetails = primaryHomeLoanDetailRepository.getByApplication(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(prHlDetails)) {
				res.setPropPremiseName(prHlDetails.getPropPremiseName());
				res.setPropLandmark(prHlDetails.getPropLandmark());
				res.setPropStreetName(prHlDetails.getPropStreetName());
				res.setPropCountry(prHlDetails.getPropCountry());
				res.setPropState(prHlDetails.getPropState());
				res.setPropCity(prHlDetails.getPropCity());
				res.setPropdistrictMappingId(prHlDetails.getPropdistrictMappingId());
				res.setPropertyPrice(prHlDetails.getPropertyPrice());
				res.setOldPropMonth(prHlDetails.getOldPropMonth());
				res.setOldPropYear(prHlDetails.getOldPropYear());
			}
			
			// GET EXISTING BANK DETAILS 
			List<FinancialArrangementsDetail> finArngDetailList= financialArrangementDetailsRepository.listSecurityCorporateDetailByAppId(applicationId);
            List<FinancialArrangementsDetailRequest> finArngDetailReqList= new ArrayList<>(finArngDetailList.size());

            FinancialArrangementsDetailRequest finReq = null;
            for(FinancialArrangementsDetail financialDetail : finArngDetailList){
                finReq = new FinancialArrangementsDetailRequest();
                BeanUtils.copyProperties(financialDetail, finReq);
                finArngDetailReqList.add(finReq);
            }
            res.setFinArrangementsDetailList(finArngDetailReqList);

            // GET BANK RELATIONSHIP DETAILS 
            List<BankRelationshipRequest> bankRelReqList = new ArrayList<>();
            List<BankingRelation> bankRel = bankingRelationlRepository.listBankRelationAppId(applicationId);
            
            BankRelationshipRequest bankRelationshipRequest = null;
            for(BankingRelation bankingRelation : bankRel) {
            	bankRelationshipRequest = new BankRelationshipRequest();
            	BeanUtils.copyProperties(bankingRelation, bankRelationshipRequest);
            	bankRelReqList.add(bankRelationshipRequest);
            }
            res.setBankRelationshipList(bankRelReqList);

			return res;
		}
		return null;
	}
	
	/** 
	 * SAVE LOAN REQUIREMENT DETAILS 
	 * Author : Harshit
	 * Date : 29/04/2019
	 * @param applicationId
	 * @return
	 */
	@Override
	public boolean saveOneformPrimaryDetails(HLOneformPrimaryRes hlOneformPrimaryRes) throws LoansException {
		try {
			//************************ SAVE LOAN REQUIREMENT DATA *******************
			RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(hlOneformPrimaryRes.getApplicationId());
			if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
				retailApplicantDetail.setLoanAmountRequired(hlOneformPrimaryRes.getLoanAmountRequired());
				retailApplicantDetail.setLoanPurpose(hlOneformPrimaryRes.getLoanPurpose());
				retailApplicantDetail.setLoanPurposeOther(hlOneformPrimaryRes.getLoanPurposeOther());
				retailApplicantDetail.setTenureRequired(hlOneformPrimaryRes.getTenureRequired());
				retailApplicantDetail.setRepayment(hlOneformPrimaryRes.getRepayment());
				retailApplicantDetail.setModifiedDate(new Date());
				retailApplicantDetail.setModifiedBy(hlOneformPrimaryRes.getUserId());
				retailApplicantDetail.setIsOneformPrimaryComplete(true);
				retailApplicantDetail.setSalaryMode(hlOneformPrimaryRes.getSalaryMode());
				retailApplicantDetail.setSalaryBankName(hlOneformPrimaryRes.getSalaryBankName());
				retailApplicantDetail.setIsOtherSalaryBank(hlOneformPrimaryRes.getIsOtherSalaryBank());
				retailApplicantDetail.setSalaryBankYear(hlOneformPrimaryRes.getSalaryBankYear());
				retailApplicantDetail.setSalaryBankMonth(hlOneformPrimaryRes.getSalaryBankMonth());
				retailApplicantDetailRepository.save(retailApplicantDetail);
				
				//************************ SAVE PRIMARY DETAILS *****************************
				PrimaryHomeLoanDetail prHlDetails = primaryHomeLoanDetailRepository.getByApplication(hlOneformPrimaryRes.getApplicationId());
				if(!CommonUtils.isObjectNullOrEmpty(prHlDetails)) {
					prHlDetails.setModifiedDate(new Date());
					prHlDetails.setModifiedBy(hlOneformPrimaryRes.getUserId());
				} else {
					prHlDetails = new PrimaryHomeLoanDetail();
					prHlDetails.setApplicationId(new LoanApplicationMaster(hlOneformPrimaryRes.getApplicationId()));
					prHlDetails.setIsActive(true);
					prHlDetails.setCreatedDate(new Date());
					prHlDetails.setCreatedBy(hlOneformPrimaryRes.getUserId());
				}
				
				prHlDetails.setPropPremiseName(hlOneformPrimaryRes.getPropPremiseName());
				prHlDetails.setPropLandmark(hlOneformPrimaryRes.getPropLandmark());
				prHlDetails.setPropStreetName(hlOneformPrimaryRes.getPropStreetName());
				prHlDetails.setPropCountry(hlOneformPrimaryRes.getPropCountry());
				prHlDetails.setPropState(hlOneformPrimaryRes.getPropState());
				prHlDetails.setPropCity(hlOneformPrimaryRes.getPropCity());
				prHlDetails.setPropdistrictMappingId(hlOneformPrimaryRes.getPropdistrictMappingId());
				prHlDetails.setPropertyPrice(hlOneformPrimaryRes.getPropertyPrice());
				prHlDetails.setOldPropMonth(hlOneformPrimaryRes.getOldPropMonth());
				prHlDetails.setOldPropYear(hlOneformPrimaryRes.getOldPropYear());
			}
			
			
			// ****************************** SAVE BANK RELATION DETAILS ***********************
			List<BankingRelation> bankingRelations = new ArrayList<>();
			BankingRelation bankingRelation = null;
	        for(BankRelationshipRequest bankRelationshipRequest : hlOneformPrimaryRes.getBankRelationshipList()) {
	        	bankingRelation = new BankingRelation();
	            BeanUtils.copyProperties(bankRelationshipRequest, bankingRelation);
	            bankingRelation.setApplicationId(hlOneformPrimaryRes.getApplicationId());
	            if(bankRelationshipRequest.getId() == null) {
	            	bankingRelation.setCreatedBy(hlOneformPrimaryRes.getUserId());
	                bankingRelation.setCreatedDate(new Date());
	                bankingRelation.setIsActive(true);
	            } else {
	            	bankingRelation.setModifiedBy(hlOneformPrimaryRes.getUserId());
		        	bankingRelation.setModifiedDate(new Date());
	            }
	            bankingRelations.add(bankingRelation);
	        }
	        bankingRelationlRepository.save(bankingRelations);
	        
	        // ****************************** SAVE EXISTING LOAN DETAILS ***********************
	        List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestsList = hlOneformPrimaryRes.getFinArrangementsDetailList();
            if(!CommonUtils.isListNullOrEmpty(financialArrangementsDetailRequestsList)) {
                for (FinancialArrangementsDetailRequest reqObj : financialArrangementsDetailRequestsList) {
                    FinancialArrangementsDetail saveFinObj = null;
                    if (!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
                        saveFinObj = financialArrangementDetailsRepository.findByIdAndIsActive(reqObj.getId(), true);
                    }
                    if (saveFinObj == null || CommonUtils.isObjectNullOrEmpty(saveFinObj)) {
                        saveFinObj = new FinancialArrangementsDetail();
                        BeanUtils.copyProperties(reqObj, saveFinObj, "id", CommonUtils.CREATED_BY, CommonUtils.CREATED_DATE, CommonUtils.MODIFIED_BY,CommonUtils.MODIFIED_DATE, "isActive");

                        saveFinObj.setApplicationId(new LoanApplicationMaster(hlOneformPrimaryRes.getApplicationId()));
                        saveFinObj.setCreatedBy(hlOneformPrimaryRes.getUserId());
                        saveFinObj.setCreatedDate(new Date());
                        saveFinObj.setIsActive(true);
                    } else {
                        BeanUtils.copyProperties(reqObj, saveFinObj, "id","applicationId", CommonUtils.CREATED_BY, CommonUtils.CREATED_DATE, CommonUtils.MODIFIED_BY,CommonUtils.MODIFIED_DATE);
                        saveFinObj.setModifiedBy(hlOneformPrimaryRes.getUserId());
                        saveFinObj.setModifiedDate(new Date());
                    }
                    financialArrangementDetailsRepository.save(saveFinObj);
                }
            }
	        return true;
		} catch (Exception e) {
			logger.error("Exception while save Oneform Loan Requirement details===> {}",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
}
