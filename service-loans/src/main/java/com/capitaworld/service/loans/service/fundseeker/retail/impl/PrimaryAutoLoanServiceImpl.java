package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryAutoLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.ALOneformPrimaryRes;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryAutoLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryAutoLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryAutoLoanServiceImpl implements PrimaryAutoLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryAutoLoanServiceImpl.class.getName());
	
	@Autowired
	private PrimaryAutoLoanDetailRepository autoLoanDetailRepository;
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;

	
	/** 
	 * Save auto loan details 
	 * Author : Rohit
	 * Date : 20/08/2019
	 * @param applicationId, userid
	 * @return
	 */
	@Override
	public boolean saveOrUpdate(ALOneformPrimaryRes alLoanDetailRequest, Long userId) throws LoansException {
	
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
				primaryAutoLoanDetail.setVechicleUse(alLoanDetailRequest.getVechicleUse());
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
	 * @param applicationId, userid
	 * @return
	 */
	@Override
	public ALOneformPrimaryRes getOneformPrimaryDetails(Long applicationId, Long userId) throws LoansException {
		
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
			res.setVechicleUse(primaryAutoLoanDetail.getVechicleUse());
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
}
