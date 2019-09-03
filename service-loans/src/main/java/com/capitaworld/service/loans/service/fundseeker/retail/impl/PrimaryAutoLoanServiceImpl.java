package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.FsNegativeFpList;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryAutoLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.ALOneformPrimaryRes;
import com.capitaworld.service.loans.model.retail.PrimaryAutoLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryHomeLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.FsNegativeFpListRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryAutoLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryAutoLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryAutoLoanServiceImpl implements PrimaryAutoLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryAutoLoanServiceImpl.class.getName());
	
	@Autowired
	private PrimaryAutoLoanDetailRepository autoLoanDetailRepository;
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private FsNegativeFpListRepository fsNegativeFpListRepository;


	
	/** 
	 * Save auto loan details 
	 * Author : Rohit
	 * Date : 20/08/2019
	 * @param applicationId, userid
	 * @return
	 */
	@Override
	public boolean saveOneformPrimaryDetails(ALOneformPrimaryRes alLoanDetailRequest, Long userId) throws LoansException {
	
		try {
			//************************ SAVE LOAN REQUIREMENT DATA *******************
			RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(alLoanDetailRequest.getApplicationId());
			if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
				retailApplicantDetail.setLoanAmountRequired(alLoanDetailRequest.getLoanAmountRequired());
				retailApplicantDetail.setTenureRequired(alLoanDetailRequest.getTenureRequired());
				retailApplicantDetail.setRepaymentMode(alLoanDetailRequest.getRepaymentMode());
				retailApplicantDetail.setModifiedDate(new Date());
				retailApplicantDetail.setModifiedBy(alLoanDetailRequest.getUserId());
				retailApplicantDetail.setGrossMonthlyIncome(alLoanDetailRequest.getGrossMonthlyIncome());
				retailApplicantDetail.setMonthlyIncome(alLoanDetailRequest.getMonthlyIncome());
				retailApplicantDetail.setBorrowerContribution(alLoanDetailRequest.getBorrowerContribution());
				retailApplicantDetail.setLoanPurpose(alLoanDetailRequest.getLoanPurpose());
				retailApplicantDetail.setLoanPurposeQueType(alLoanDetailRequest.getLoanPurposeQueType());
				retailApplicantDetailRepository.save(retailApplicantDetail);
			}
			
			//************************ SAVE VEHICLE DETAILS *****************************
			PrimaryAutoLoanDetail primaryAutoLoanDetail = autoLoanDetailRepository.findById(alLoanDetailRequest.getApplicationId()); 
			if(!CommonUtils.isObjectNullOrEmpty(primaryAutoLoanDetail)) {
				primaryAutoLoanDetail.setVehicleType(alLoanDetailRequest.getVehicleType());
				primaryAutoLoanDetail.setVehicleCategory(alLoanDetailRequest.getVehicleCategory());
				primaryAutoLoanDetail.setVehicleSegment(alLoanDetailRequest.getVehicleSegment());
				primaryAutoLoanDetail.setVehicleAge(alLoanDetailRequest.getVehicleAge());
				primaryAutoLoanDetail.setVehicleEngineVolume(alLoanDetailRequest.getVehicleEngineVolume());
				primaryAutoLoanDetail.setVechicleUse(alLoanDetailRequest.getVehicleUse());
				primaryAutoLoanDetail.setVehicleExShowRoomPrice(alLoanDetailRequest.getVehicleExShowRoomPrice());
				primaryAutoLoanDetail.setVehicleOnRoadPrice(alLoanDetailRequest.getVehicleOnRoadPrice());
				primaryAutoLoanDetail.setVehicleAgreedPurchasePrice(alLoanDetailRequest.getVehicleAgreedPurchasePrice());
				primaryAutoLoanDetail.setIsVehicleHypothecation(alLoanDetailRequest.getIsVehicleHypothecation());
				primaryAutoLoanDetail.setIsCheckOffAgreeToPayOutstanding(alLoanDetailRequest.getIsCheckOffAgreeToPayOutstanding());
				primaryAutoLoanDetail.setIsCheckOffDirectPayEmi(alLoanDetailRequest.getIsCheckOffDirectPayEmi());
				primaryAutoLoanDetail.setIsCheckOffNotChangeSalAcc(alLoanDetailRequest.getIsCheckOffNotChangeSalAcc());
				primaryAutoLoanDetail.setIsCheckOffPayOutstndAmount(alLoanDetailRequest.getIsCheckOffPayOutstndAmount());
				primaryAutoLoanDetail.setIsCheckOffShiftSalAcc(alLoanDetailRequest.getIsCheckOffShiftSalAcc());
				autoLoanDetailRepository.save(primaryAutoLoanDetail); 
			}
			
			return true; 
		} catch (Exception e) {
			logger.error("Error while saving PrimaryAutoLoan Details : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	
	/** 
	 * Get auto loan details 
	 * Author : Rohit
	 * Date : 20/08/2019
	 * @param applicationId
	 * @return
	 */
	@Override
	public ALOneformPrimaryRes getOneformPrimaryDetails(Long applicationId) {
		
		ALOneformPrimaryRes res = new ALOneformPrimaryRes();
		RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(applicationId);
		
		if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
			res.setApplicationId(applicationId);
			res.setLoanAmountRequired(retailApplicantDetail.getLoanAmountRequired());
			res.setBorrowerContribution(retailApplicantDetail.getBorrowerContribution());
			res.setLoanPurposeOther(retailApplicantDetail.getLoanPurposeOther());
			res.setLoanPurposeQueValue(retailApplicantDetail.getLoanPurposeQueValue());
			res.setTenureRequired(retailApplicantDetail.getTenureRequired());
			res.setEmploymentType(retailApplicantDetail.getEmploymentType());
			res.setRepaymentMode(retailApplicantDetail.getRepaymentMode());		
			res.setMonthlyIncome(retailApplicantDetail.getMonthlyIncome());
			res.setGrossMonthlyIncome(retailApplicantDetail.getGrossMonthlyIncome());
			res.setLoanPurpose(retailApplicantDetail.getLoanPurpose());
			res.setLoanPurposeQueType(retailApplicantDetail.getLoanPurposeQueType());
		}
		PrimaryAutoLoanDetail primaryAutoLoanDetail = autoLoanDetailRepository.findById(applicationId);
		if(!CommonUtils.isObjectNullOrEmpty(primaryAutoLoanDetail)) {
			res.setVehicleType(primaryAutoLoanDetail.getVehicleType());
			res.setVehicleCategory(primaryAutoLoanDetail.getVehicleCategory());
			res.setVehicleSegment(primaryAutoLoanDetail.getVehicleSegment());
			res.setVehicleAge(primaryAutoLoanDetail.getVehicleAge());
			res.setVehicleEngineVolume(primaryAutoLoanDetail.getVehicleEngineVolume());
			res.setVehicleUse(primaryAutoLoanDetail.getVechicleUse());
			res.setVehicleExShowRoomPrice(primaryAutoLoanDetail.getVehicleExShowRoomPrice());
			res.setVehicleOnRoadPrice(primaryAutoLoanDetail.getVehicleOnRoadPrice());
			res.setVehicleAgreedPurchasePrice(primaryAutoLoanDetail.getVehicleAgreedPurchasePrice());
			res.setIsVehicleHypothecation(primaryAutoLoanDetail.getIsVehicleHypothecation()); 
			res.setIsCheckOffAgreeToPayOutstanding(primaryAutoLoanDetail.getIsCheckOffAgreeToPayOutstanding());
			res.setIsCheckOffDirectPayEmi(primaryAutoLoanDetail.getIsCheckOffDirectPayEmi());
			res.setIsCheckOffNotChangeSalAcc(primaryAutoLoanDetail.getIsCheckOffNotChangeSalAcc());
			res.setIsCheckOffPayOutstndAmount(primaryAutoLoanDetail.getIsCheckOffPayOutstndAmount());
			res.setIsCheckOffShiftSalAcc(primaryAutoLoanDetail.getIsCheckOffShiftSalAcc());
		}
		return res;
	}


//	@Override
//	public ALOneformPrimaryRes get(Long id, Long userId) throws LoansException {
//		ALOneformPrimaryRes res = new ALOneformPrimaryRes();
//		PrimaryAutoLoanDetail primaryAutoLoanDetail = autoLoanDetailRepository.findById(id);
//		if(!CommonUtils.isObjectNullOrEmpty(primaryAutoLoanDetail)) {
//			res.setVehicleType(primaryAutoLoanDetail.getVehicleType());
//			res.setVehicleCategory(primaryAutoLoanDetail.getVehicleCategory());
//			res.setVehicleSegment(primaryAutoLoanDetail.getVehicleSegment());
//			res.setVehicleAge(primaryAutoLoanDetail.getVehicleAge());
//			res.setVehicleEngineVolume(primaryAutoLoanDetail.getVehicleEngineVolume());
//			res.setVehicleUse(primaryAutoLoanDetail.getVechicleUse());
//			res.setVehicleExShowRoomPrice(primaryAutoLoanDetail.getVehicleExShowRoomPrice());
//			res.setVehicleOnRoadPrice(primaryAutoLoanDetail.getVehicleOnRoadPrice());
//			res.setVehicleAgreedPurchasePrice(primaryAutoLoanDetail.getVehicleAgreedPurchasePrice());
//			res.setIsVehicleHypothecation(primaryAutoLoanDetail.getIsVehicleHypothecation()); 
//			res.setIsCheckOffAgreeToPayOutstanding(primaryAutoLoanDetail.getIsCheckOffAgreeToPayOutstanding());
//			res.setIsCheckOffDirectPayEmi(primaryAutoLoanDetail.getIsCheckOffDirectPayEmi());
//			res.setIsCheckOffNotChangeSalAcc(primaryAutoLoanDetail.getIsCheckOffNotChangeSalAcc());
//			res.setIsCheckOffPayOutstndAmount(primaryAutoLoanDetail.getIsCheckOffPayOutstndAmount());
//			res.setIsCheckOffShiftSalAcc(primaryAutoLoanDetail.getIsCheckOffShiftSalAcc());
//		}
//		return res;
//	}
	
	@Override
	public PrimaryAutoLoanDetailRequest get(Long applicationId,Long userId) throws LoansException {
		try{
			
			PrimaryAutoLoanDetail primaryAutoLoanDetail = autoLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
			if (primaryAutoLoanDetail == null) {
				throw new NullPointerException("PrimaryAutoLoanDetail not exist in DB with applicationId=>" + applicationId + " and UserId==>"+ userId);
			}
			PrimaryAutoLoanDetailRequest res = new PrimaryAutoLoanDetailRequest();
	
//			get fp negative list
			res.setNegativeList(fsNegativeFpListRepository.getListByApplicationId(applicationId));
			
			RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
				res.setApplicationId(applicationId);
				res.setLoanAmountRequired(retailApplicantDetail.getLoanAmountRequired());
				res.setBorrowerContribution(retailApplicantDetail.getBorrowerContribution());
				res.setLoanPurposeOther(retailApplicantDetail.getLoanPurposeOther());
				res.setLoanPurposeQueValue(retailApplicantDetail.getLoanPurposeQueValue());
				res.setTenureRequired(retailApplicantDetail.getTenureRequired());
				res.setEmploymentType(retailApplicantDetail.getEmploymentType());
				res.setRepaymentMode(retailApplicantDetail.getRepaymentMode());		
				res.setMonthlyIncome(retailApplicantDetail.getMonthlyIncome());
				res.setGrossMonthlyIncome(retailApplicantDetail.getGrossMonthlyIncome());
				res.setLoanPurpose(retailApplicantDetail.getLoanPurpose());
				res.setLoanPurposeQueType(retailApplicantDetail.getLoanPurposeQueType());
			}
			if(!CommonUtils.isObjectNullOrEmpty(primaryAutoLoanDetail)) {
				res.setVehicleType(primaryAutoLoanDetail.getVehicleType());
				res.setVehicleCategory(primaryAutoLoanDetail.getVehicleCategory());
				res.setVehicleSegment(primaryAutoLoanDetail.getVehicleSegment());
				res.setVehicleAge(primaryAutoLoanDetail.getVehicleAge());
				res.setVehicleEngineVolume(primaryAutoLoanDetail.getVehicleEngineVolume());
				res.setVehicleUse(primaryAutoLoanDetail.getVechicleUse());
				res.setVehicleExShowRoomPrice(primaryAutoLoanDetail.getVehicleExShowRoomPrice());
				res.setVehicleOnRoadPrice(primaryAutoLoanDetail.getVehicleOnRoadPrice());
				res.setVehicleAgreedPurchasePrice(primaryAutoLoanDetail.getVehicleAgreedPurchasePrice());
				res.setIsVehicleHypothecation(primaryAutoLoanDetail.getIsVehicleHypothecation()); 
				res.setIsCheckOffAgreeToPayOutstanding(primaryAutoLoanDetail.getIsCheckOffAgreeToPayOutstanding());
				res.setIsCheckOffDirectPayEmi(primaryAutoLoanDetail.getIsCheckOffDirectPayEmi());
				res.setIsCheckOffNotChangeSalAcc(primaryAutoLoanDetail.getIsCheckOffNotChangeSalAcc());
				res.setIsCheckOffPayOutstndAmount(primaryAutoLoanDetail.getIsCheckOffPayOutstndAmount());
				res.setIsCheckOffShiftSalAcc(primaryAutoLoanDetail.getIsCheckOffShiftSalAcc());
			}
			
			Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
			res.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
			
			return res;
		} catch (Exception e) {
			logger.error("Error while getting Primary Auto Loan Details Profile:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}


	@Override
	public boolean saveOrUpdate(PrimaryAutoLoanDetailRequest alDetail, Long userId) throws LoansException {
		// ID must not be null
		try{
		Long finalUserId = (CommonUtils.isObjectNullOrEmpty(alDetail.getClientId()) ? userId : alDetail.getClientId());
		PrimaryAutoLoanDetail primaryAutoLoanDetail= autoLoanDetailRepository.getByApplicationAndUserId(alDetail.getApplicationId(), finalUserId);
		if (primaryAutoLoanDetail == null) {
			throw new NullPointerException("PrimaryAutoLoanDetail not exist in DB with Application Id=>" + alDetail.getApplicationId()+ " and user Id ==>" + userId); 
		}
		 
		if(!CommonUtils.isObjectNullOrEmpty(primaryAutoLoanDetail)) {
			primaryAutoLoanDetail.setVehicleType(alDetail.getVehicleType());
			primaryAutoLoanDetail.setVehicleCategory(alDetail.getVehicleCategory());
			primaryAutoLoanDetail.setVehicleSegment(alDetail.getVehicleSegment());
			primaryAutoLoanDetail.setVehicleAge(alDetail.getVehicleAge());
			primaryAutoLoanDetail.setVehicleEngineVolume(alDetail.getVehicleEngineVolume());
			primaryAutoLoanDetail.setVechicleUse(alDetail.getVehicleUse());
			primaryAutoLoanDetail.setVehicleExShowRoomPrice(alDetail.getVehicleExShowRoomPrice());
			primaryAutoLoanDetail.setVehicleOnRoadPrice(alDetail.getVehicleOnRoadPrice());
			primaryAutoLoanDetail.setVehicleAgreedPurchasePrice(alDetail.getVehicleAgreedPurchasePrice());
			primaryAutoLoanDetail.setIsVehicleHypothecation(alDetail.getIsVehicleHypothecation());
			primaryAutoLoanDetail.setIsCheckOffAgreeToPayOutstanding(alDetail.getIsCheckOffAgreeToPayOutstanding());
			primaryAutoLoanDetail.setIsCheckOffDirectPayEmi(alDetail.getIsCheckOffDirectPayEmi());
			primaryAutoLoanDetail.setIsCheckOffNotChangeSalAcc(alDetail.getIsCheckOffNotChangeSalAcc());
			primaryAutoLoanDetail.setIsCheckOffPayOutstndAmount(alDetail.getIsCheckOffPayOutstndAmount());
			primaryAutoLoanDetail.setIsCheckOffShiftSalAcc(alDetail.getIsCheckOffShiftSalAcc());
			primaryAutoLoanDetail.setModifiedBy(userId);
			primaryAutoLoanDetail.setModifiedDate(new Date());
			primaryAutoLoanDetail.setIsActive(true);
			autoLoanDetailRepository.save(primaryAutoLoanDetail); 
		}
		//Updating Primary Flag
//		loanApplicationRepository.setPrimaryFilledCount(primaryAutoLoanDetail.getId(), finalUserId, alOneformPrimaryRes.getPrimaryFilledCount());
		//save negative list
		fsNegativeFpListRepository.inActiveMappingByApplicationId(primaryAutoLoanDetail.getApplicationId().getId());
		saveNegativeList(primaryAutoLoanDetail.getApplicationId().getId(), alDetail.getNegativeList());
		return true;
		} catch (Exception e) {
			logger.error("Error while saving Primary Auto Details Profile:-",e);
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

	
	
}
