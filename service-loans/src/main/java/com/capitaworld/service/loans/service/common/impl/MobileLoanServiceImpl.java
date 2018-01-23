package com.capitaworld.service.loans.service.common.impl;

import java.util.Date;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryTermLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.GuarantorDetails;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryCarLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLapLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLasLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryPersonalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.mobile.MApplicantProfileResponse;
import com.capitaworld.service.loans.model.mobile.MHLPrimaryResponse;
import com.capitaworld.service.loans.model.mobile.MRetailApplicantResponse;
import com.capitaworld.service.loans.model.mobile.MRetailCoAppGuarResponse;
import com.capitaworld.service.loans.model.mobile.MobileFrameDetailsRequest;
import com.capitaworld.service.loans.model.mobile.MobileFrameRequest;
import com.capitaworld.service.loans.model.mobile.MobileLoanRequest;
import com.capitaworld.service.loans.model.retail.PrimaryCarLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryHomeLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryLapLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryPersonalLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryHomeLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.common.ApplicationSequenceService;
import com.capitaworld.service.loans.service.common.MobileService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryCarLoanService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryHomeLoanService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryLapLoanService;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryPersonalLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@Service
@Transactional
public class MobileLoanServiceImpl implements MobileService {

	private static final Logger logger = LoggerFactory.getLogger(MobileLoanServiceImpl.class.getName());
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;
	
	@Autowired
	private PrimaryHomeLoanService primaryHomeLoanService;
	
	@Autowired
	private PrimaryCarLoanService primaryCarLoanService;
	
	@Autowired
	private PrimaryPersonalLoanService primaryPersonalLoanService;
	
	@Autowired
	private PrimaryLapLoanService primaryLapLoanService;
	
	@Autowired
	private GuarantorDetailsRepository guarantorDetailsRepository;

	@Autowired
	private CoApplicantDetailRepository coApplicantDetailRepository;
	
	@Autowired
	private ApplicationSequenceService applicationSequenceService;
	

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private PrimaryHomeLoanDetailRepository primaryHomeLoanDetailRepository;

	
	@Override
	public MRetailApplicantResponse getApplicantDetails(MobileLoanRequest mobileUserRequest) throws Exception {
		logger.info("Get Applicant Details From RetailApplicantDetail");
		RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.getByApplicationAndUserId(mobileUserRequest.getUserId(), mobileUserRequest.getApplicationId());
		if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
			MRetailApplicantResponse response = new MRetailApplicantResponse();
			BeanUtils.copyProperties(retailApplicantDetail, response);
			response.setApplicationId(mobileUserRequest.getApplicationId());
			if(!CommonUtils.isObjectNullOrEmpty(mobileUserRequest.getProductId())) {
				response.setProductId(mobileUserRequest.getProductId());
				LoanType loantype = CommonUtils.LoanType.getType(mobileUserRequest.getProductId());
				if(loantype.getValue() == LoanType.HOME_LOAN.getValue()) {
					logger.info("Get Applicant Home Loan Primary Details");
					response.setData(primaryHomeLoanService.get(mobileUserRequest.getApplicationId(), mobileUserRequest.getUserId()));
				
				} else if(loantype.getValue() == LoanType.PERSONAL_LOAN.getValue()) {
					logger.info("Get Applicant Personal Loan Primary Details");
					response.setData(primaryPersonalLoanService.get(mobileUserRequest.getApplicationId(), mobileUserRequest.getUserId()));
				
				} else if(loantype.getValue() == LoanType.CAR_LOAN.getValue()) {
					logger.info("Get Applicant Car Loan Primary Details");
					response.setData(primaryCarLoanService.get(mobileUserRequest.getApplicationId(), mobileUserRequest.getUserId()));
				
				} else if(loantype.getValue() == LoanType.LAP_LOAN.getValue()) {
					logger.info("Get Applicant LAP Loan Primary Details");
					response.setData(primaryLapLoanService.get(mobileUserRequest.getApplicationId(), mobileUserRequest.getUserId()));
				}
			}
			logger.info("Get Successfully Profile and Primary Applicant Details");
			return response;
		}
		return null;
	}
	
	@Override
	public Long saveApplicantDetails(MApplicantProfileResponse mApplicantProfileResponse) throws Exception {
		logger.info("Save Applicant Details...");
		RetailApplicantDetail retailApplicantDetail = new RetailApplicantDetail();
		if(!CommonUtils.isObjectNullOrEmpty(mApplicantProfileResponse.getId())) {
			retailApplicantDetail = retailApplicantDetailRepository.findOne(mApplicantProfileResponse.getId());
		}
		BeanUtils.copyProperties(mApplicantProfileResponse, retailApplicantDetail,"applicationId","userId","data");
		retailApplicantDetail.setModifiedDate(new Date());
		retailApplicantDetail.setModifiedBy(mApplicantProfileResponse.getUserId());
		retailApplicantDetail = retailApplicantDetailRepository.save(retailApplicantDetail);
		if(!CommonUtils.isObjectNullOrEmpty(mApplicantProfileResponse.getProductId()) && !CommonUtils.isObjectNullOrEmpty(mApplicantProfileResponse.getData())) {
			LoanType loantype = CommonUtils.LoanType.getType(mApplicantProfileResponse.getProductId());
			if(loantype.getValue() == LoanType.HOME_LOAN.getValue()) {
				logger.info("Start Save Applicant Home Loan Primary Details...");
				MHLPrimaryResponse mhlPrimaryResponse = new MHLPrimaryResponse();
				PrimaryHomeLoanDetailRequest homeLoanRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<String,Object>) mhlPrimaryResponse.getData(), PrimaryHomeLoanDetailRequest.class);
				BeanUtils.copyProperties(mhlPrimaryResponse, homeLoanRequest);
				primaryHomeLoanService.saveOrUpdate(homeLoanRequest, mApplicantProfileResponse.getUserId());
				
			} else if(loantype.getValue() == LoanType.PERSONAL_LOAN.getValue()) {
				logger.info("Start Save Applicant Personal Loan Primary Details...");
				PrimaryPersonalLoanRequest personalLoanRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<String,Object>) mApplicantProfileResponse.getData(),PrimaryPersonalLoanRequest.class);
				primaryPersonalLoanService.saveOrUpdate(personalLoanRequest, mApplicantProfileResponse.getUserId());
				
			} else if(loantype.getValue() == LoanType.CAR_LOAN.getValue()) {
				logger.info("Start Save Applicant Car Loan Primary Details...");
				PrimaryCarLoanDetailRequest carLoanRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<String,Object>) mApplicantProfileResponse.getData(),PrimaryCarLoanDetailRequest.class);
				primaryCarLoanService.saveOrUpdate(carLoanRequest, mApplicantProfileResponse.getUserId());
				
			} else if(loantype.getValue() == LoanType.LAP_LOAN.getValue()) {
				logger.info("Start Save Applicant LAP Loan Primary Details...");
				PrimaryLapLoanDetailRequest lapLoanDetailRequest =  MultipleJSONObjectHelper.getObjectFromMap((Map<String,Object>) mApplicantProfileResponse.getData(),PrimaryLapLoanDetailRequest.class);;
				primaryLapLoanService.saveOrUpdate(lapLoanDetailRequest, mApplicantProfileResponse.getUserId());
			}
		}
		logger.info("Saved Successfully All Profile And Primary Data For Mobile App...");
		return retailApplicantDetail.getId();
	}
	
	@Override
	public MRetailCoAppGuarResponse getGuarantorDetails(MobileLoanRequest mobileUserRequest) {
		GuarantorDetails guaDetail = guarantorDetailsRepository.get(mobileUserRequest.getApplicationId(), mobileUserRequest.getUserId(), mobileUserRequest.getId());
		MRetailCoAppGuarResponse response = new MRetailCoAppGuarResponse();
		if(!CommonUtils.isObjectNullOrEmpty(guaDetail)) {
			BeanUtils.copyProperties(guaDetail, response);
			response.setDateOfBirth(guaDetail.getBirthDate());
			response.setContactNo(guaDetail.getContactNo());
		}
		return response;
	}
	
	@Override
	public Long saveGuarantorDetails(MRetailCoAppGuarResponse response) {
		GuarantorDetails guaDetail = null;
		if(!CommonUtils.isObjectNullOrEmpty(response.getId())) {
			guaDetail = guarantorDetailsRepository.findOne(response.getId());	
			guaDetail.setModifiedBy(response.getUserId());
			guaDetail.setModifiedDate(new Date());
		} else {
			Long count = guarantorDetailsRepository.getGuarantorCountByApplicationAndUserId(response.getApplicationId(), response.getUserId());
			if(count == 2) {
				return null;
			}
			guaDetail = new GuarantorDetails();
			guaDetail.setCreatedBy(response.getUserId());
			guaDetail.setCreatedDate(new Date());
			guaDetail.setIsActive(true);
			guaDetail.setApplicationId(new LoanApplicationMaster(response.getApplicationId()));
		}
		BeanUtils.copyProperties(response, guaDetail,"id","userId","isActive","applicationId");
		guaDetail.setBirthDate(response.getDateOfBirth());
		guaDetail = guarantorDetailsRepository.save(guaDetail);
		return guaDetail.getId();
	}
	
	@Override
	public MRetailCoAppGuarResponse getCoApplicantDetails(MobileLoanRequest mobileUserRequest) {
		CoApplicantDetail coAppDetail = coApplicantDetailRepository.get(mobileUserRequest.getApplicationId(), mobileUserRequest.getUserId(), mobileUserRequest.getId());
		MRetailCoAppGuarResponse response = new MRetailCoAppGuarResponse();
		if(!CommonUtils.isObjectNullOrEmpty(coAppDetail)) {
			BeanUtils.copyProperties(coAppDetail, response);
			response.setDateOfBirth(coAppDetail.getBirthDate());
			response.setContactNo(coAppDetail.getContactNo());
		}
		return response;
	}
	
	@Override
	public Long saveCoApplicantDetails(MRetailCoAppGuarResponse response) {
		CoApplicantDetail coAppDetail = null;
		if(!CommonUtils.isObjectNullOrEmpty(response.getId())) {
			coAppDetail = coApplicantDetailRepository.get(response.getApplicationId(), response.getUserId(), response.getId());
			coAppDetail.setModifiedBy(response.getUserId());
			coAppDetail.setModifiedDate(new Date());
		} else {
			Long count = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(response.getApplicationId(), response.getUserId());
			if(count == 2) {
				return null;
			}
			coAppDetail = new CoApplicantDetail();
			coAppDetail.setCreatedBy(response.getUserId());
			coAppDetail.setCreatedDate(new Date());
			coAppDetail.setIsActive(true);
			coAppDetail.setApplicationId(new LoanApplicationMaster(response.getApplicationId()));			
			coAppDetail.setRelationshipWithApplicant(response.getRelationshipWithApplicant());
		}
		BeanUtils.copyProperties(response, coAppDetail,"id","userId","isActive","applicationId");
		coAppDetail.setBirthDate(response.getDateOfBirth());
		coAppDetail = coApplicantDetailRepository.save(coAppDetail);
		return coAppDetail.getId();	
	}
	
	
	@Override
	public Long saveLoanApplicationDetails(MobileFrameRequest commonRequest) throws Exception {
		try {
			LoanApplicationMaster applicationMaster = null;
			for (Map<String, Object> obj : commonRequest.getDataList()) {
				MobileFrameDetailsRequest loanApplicationRequest = (MobileFrameDetailsRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, MobileFrameDetailsRequest.class);
				LoanType type = CommonUtils.LoanType.getType(commonRequest.getProductId());
				if (type == null) {
					continue;
				}

				switch (type) {
				case WORKING_CAPITAL:
					applicationMaster = new PrimaryWorkingCapitalLoanDetail();
					break;
				case TERM_LOAN:
					applicationMaster = new PrimaryTermLoanDetail();
					break;
				case LAS_LOAN:
					applicationMaster = new PrimaryLasLoanDetail();
					break;
				case LAP_LOAN:
					applicationMaster = new PrimaryLapLoanDetail();
					break;
				case PERSONAL_LOAN:
					applicationMaster = new PrimaryPersonalLoanDetail();
					break;
				case HOME_LOAN:
					applicationMaster = new PrimaryHomeLoanDetail();
					break;
				case CAR_LOAN:
					applicationMaster = new PrimaryCarLoanDetail();
					break;

				default:
					continue;
				}

				logger.info("userId==>" + (commonRequest.getUserId()));
//				BeanUtils.copyProperties(loanApplicationRequest, applicationMaster, "name");
				applicationMaster.setUserId(commonRequest.getUserId());
				applicationMaster.setCreatedBy(commonRequest.getUserId());
				applicationMaster.setCreatedDate(new Date());
				applicationMaster.setModifiedBy(commonRequest.getUserId());
				applicationMaster.setModifiedDate(new Date());
				applicationMaster.setName(loanApplicationRequest.getName());
				
				if (!CommonUtils.isObjectNullOrEmpty(loanApplicationRequest.getTenure())) {
					applicationMaster.setTenure(loanApplicationRequest.getTenure() * 12);
				}
				applicationMaster.setCategoryCode(loanApplicationRequest.getCategoryCode());  // categaoryCode set
				applicationMaster.setProductId(commonRequest.getProductId());
				
				
				applicationMaster
						.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue()));
				applicationMaster = loanApplicationRepository.save(applicationMaster);
				
				
				//SAVE PROFILE DETAILS
				
				if(CommonUtils.getUserMainType(type.getValue()) == CommonUtils.UserMainType.RETAIL) {
					
					RetailApplicantDetail retailApplicantDetail = new RetailApplicantDetail();
					retailApplicantDetail.setApplicationId(applicationMaster);
					retailApplicantDetail.setOccupationId(loanApplicationRequest.getOccupationId());
					retailApplicantDetail.setBirthDate(loanApplicationRequest.getDateOfBirth());
					retailApplicantDetail.setMonthlyIncome(loanApplicationRequest.getMonthlyIncome());
					retailApplicantDetail.setAnnualTurnover(loanApplicationRequest.getAnnualTurnover());
					retailApplicantDetail.setMonthlyLoanObligation(loanApplicationRequest.getMonthlyLoanObligation());
					retailApplicantDetail.setBonusPerAnnum(loanApplicationRequest.getBonusPerAnnum());
					retailApplicantDetail.setIncentivePerAnnum(loanApplicationRequest.getIncentivePerAnnum());
					retailApplicantDetail.setOtherIncome(loanApplicationRequest.getOtherIncome());
					retailApplicantDetail.setOtherInvestment(loanApplicationRequest.getOtherInvestment());
					retailApplicantDetail.setTaxPaidLastYear(loanApplicationRequest.getTaxPaidLastYear());
					retailApplicantDetail.setPermanentPincode(loanApplicationRequest.getPermanentPincode());
					retailApplicantDetail.setEmployedWithId(loanApplicationRequest.getEmployedWithId());
					retailApplicantDetail.setIsActive(true);
					retailApplicantDetail.setTotalExperienceMonth(loanApplicationRequest.getTotalExperienceMonth());
					retailApplicantDetail.setTotalExperienceYear(loanApplicationRequest.getTotalExperienceYear());
					retailApplicantDetail.setCurrentJobMonth(loanApplicationRequest.getCurrentJobMonth());
					retailApplicantDetail.setCurrentJobYear(loanApplicationRequest.getCurrentJobYear());
					retailApplicantDetail.setCreatedBy(applicationMaster.getUserId());
					retailApplicantDetail.setCreatedDate(new Date());
					retailApplicantDetail.setModifiedBy(applicationMaster.getUserId());
					retailApplicantDetail.setModifiedDate(new Date());
					retailApplicantDetailRepository.save(retailApplicantDetail);
				}
				
				
				
				// for save primary details

				switch (type) {
				case WORKING_CAPITAL:

					break;
				case TERM_LOAN:

					break;
				/*
				 * case LAS_LOAN: applicationMaster = new
				 * PrimaryLasLoanDetail(); break;
				 */
				case LAP_LOAN:
					break;
				case PERSONAL_LOAN:
					// create record in fs retail applicant
					break;
				case HOME_LOAN:
					PrimaryHomeLoanDetail primaryHomeLoanDetail = primaryHomeLoanDetailRepository
							.findOne(applicationMaster.getId());
					primaryHomeLoanDetail.setPropertyPrice(loanApplicationRequest.getMarketValue());
					primaryHomeLoanDetail.setPropertyType(loanApplicationRequest.getPropertyType());
					primaryHomeLoanDetail.setProjectCity(loanApplicationRequest.getPropertyLocation());
					primaryHomeLoanDetail.setLoanType(loanApplicationRequest.getLoanType());
					primaryHomeLoanDetail.setDownPayment(loanApplicationRequest.getDownPayment());
					primaryHomeLoanDetail.setBunglowCost(loanApplicationRequest.getBunglowCost());
					primaryHomeLoanDetail.setConstructionCost(loanApplicationRequest.getConstructionCost());
					primaryHomeLoanDetail.setRenovationType(loanApplicationRequest.getRenovationType());
					primaryHomeLoanDetail.setIsLoanTaken(loanApplicationRequest.getIsLoanTaken());
					primaryHomeLoanDetail.setMarketValProp(loanApplicationRequest.getMarketValProp());
					primaryHomeLoanDetail.setPropertyPrice(loanApplicationRequest.getPropertyPrice());
					primaryHomeLoanDetailRepository.save(primaryHomeLoanDetail);
					// create record in fs retail applicant
					/*saveRetailApplicantDetailForMobileApplication(applicationMaster, loanApplicationRequest);*/
					break;
				case CAR_LOAN:
					break;

				default:
					continue;
				}
			}
			return applicationMaster.getId();
		} catch (Exception e) {
			logger.error("Error while Saving Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	private Boolean saveRetailApplicantDetailForMobileApplication(LoanApplicationMaster applicationMaster,
			MobileFrameDetailsRequest loanApplicationRequest) {
		try {
			RetailApplicantDetail retailApplicantDetail = new RetailApplicantDetail();
			retailApplicantDetail.setApplicationId(applicationMaster);
			retailApplicantDetail.setOccupationId(loanApplicationRequest.getOccupationId());
			retailApplicantDetail.setBirthDate(loanApplicationRequest.getDateOfBirth());
			retailApplicantDetail.setMonthlyIncome(loanApplicationRequest.getMonthlyIncome());
			retailApplicantDetail.setAnnualTurnover(loanApplicationRequest.getAnnualTurnover());
			retailApplicantDetail.setMonthlyLoanObligation(loanApplicationRequest.getMonthlyLoanObligation());
			retailApplicantDetail.setBonusPerAnnum(loanApplicationRequest.getBonusPerAnnum());
			retailApplicantDetail.setIncentivePerAnnum(loanApplicationRequest.getIncentivePerAnnum());
			retailApplicantDetail.setOtherIncome(loanApplicationRequest.getOtherIncome());
			retailApplicantDetail.setOtherInvestment(loanApplicationRequest.getOtherInvestment());
			retailApplicantDetail.setTaxPaidLastYear(loanApplicationRequest.getTaxPaidLastYear());
			retailApplicantDetail.setPermanentPincode(loanApplicationRequest.getPermanentPincode());
			retailApplicantDetail.setEmployedWithId(loanApplicationRequest.getEmployedWithId());
			retailApplicantDetail.setIsActive(true);
			retailApplicantDetail.setTotalExperienceMonth(loanApplicationRequest.getTotalExperienceMonth());
			retailApplicantDetail.setTotalExperienceYear(loanApplicationRequest.getTotalExperienceYear());
			retailApplicantDetail.setCurrentJobMonth(loanApplicationRequest.getCurrentJobMonth());
			retailApplicantDetail.setCurrentJobYear(loanApplicationRequest.getCurrentJobYear());
			retailApplicantDetail.setCreatedBy(applicationMaster.getUserId());
			retailApplicantDetail.setCreatedDate(new Date());
			retailApplicantDetail.setModifiedBy(applicationMaster.getUserId());
			retailApplicantDetail.setModifiedDate(new Date());
			retailApplicantDetailRepository.save(retailApplicantDetail);
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving RetailApplicantDetailFromLoanEligibility:-");
			e.printStackTrace();
			return false;
		}
	}
}
