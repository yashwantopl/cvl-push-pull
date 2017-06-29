package com.capitaworld.service.loans.service.teaser.finalview.impl;

import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.retail.*;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewCommonResponse;
import com.capitaworld.service.loans.service.common.DocumentManagementService;
import com.capitaworld.service.loans.service.fundseeker.retail.*;
import com.capitaworld.service.loans.service.teaser.finalview.RetailFinalCommonApplicantService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RetailFinalCommonServiceImpl implements RetailFinalCommonApplicantService{
	private static final Logger logger = LoggerFactory.getLogger(RetailFinalCommonServiceImpl.class);
	
	@Autowired
	private CreditCardsDetailService creditCardDetailsService; 
	
	@Autowired
	private ExistingLoanDetailsService existingLoanService;
	
	@Autowired
	private BankAccountHeldDetailService bankAccountsHeldService;
	
	@Autowired
	private FixedDepositsDetailService fixedDepositService;
	
	@Autowired
	private OtherCurrentAssetDetailService otherCurrentAssetService;
	
	@Autowired
	private OtherIncomeDetailService otherIncomeService;
	
	@Autowired
	private ReferenceRetailDetailsService referenceService;
	
	@Autowired
	private DocumentManagementService documentManagementService;
	
	@Override
	public RetailFinalViewCommonResponse getApplicantCommonInfo(Long applicantId,RetailApplicantDetail applicantDetail) {
		RetailFinalViewCommonResponse finalViewCommonResponse = new RetailFinalViewCommonResponse();
		try {
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCastId())){
				finalViewCommonResponse.setCaste(CastCategory.getById(applicantDetail.getCastId()).getValue());	
				if(applicantDetail.getCastId() == 6){
					finalViewCommonResponse.setCasteOther(applicantDetail.getCastOther());
				}
			}else{
				finalViewCommonResponse.setCaste("-");
			}
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getReligion())){
				finalViewCommonResponse.setReligion(ReligionRetailMst.getById(applicantDetail.getReligion()).getValue());	
				if(applicantDetail.getReligion() == 8){
					finalViewCommonResponse.setReligionOther(applicantDetail.getReligionOther());
				}
			}else{
				finalViewCommonResponse.setReligion("-");
			}
			finalViewCommonResponse.setBirthPlace(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getBirthPlace()) ? applicantDetail.getBirthPlace() : "-");
			finalViewCommonResponse.setFatherFullName(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getFatherName()) ? applicantDetail.getFatherName() : "-");
			finalViewCommonResponse.setMotherName(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getMotherName()) ? applicantDetail.getMotherName() : "-");
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getStatusId())){
				if(applicantDetail.getStatusId() == 2){
					finalViewCommonResponse.setSpouseName(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getSpouseName()) ? applicantDetail.getSpouseName() :"-");
					finalViewCommonResponse.setSpouseEmployed(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getIsSpouseEmployed()) ? Options.getById((applicantDetail.getIsSpouseEmployed() ? 1 : 0)).getValue() :"-");
					finalViewCommonResponse.setNoOfChildren(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getNoChildren()) ? applicantDetail.getNoChildren().toString() : "-");
				}
			}
			finalViewCommonResponse.setNoOfDependents(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getNoDependent()) ? applicantDetail.getNoDependent().toString() : "-");
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getHighestQualification())){
				finalViewCommonResponse.setHighestQualification(EducationStatusRetailMst.getById(applicantDetail.getHighestQualification()).getValue());
				if(applicantDetail.getHighestQualification() == 6){
					finalViewCommonResponse.setHighestQualificationOther(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getHighestQualificationOther()) ? applicantDetail.getHighestQualificationOther() : "-");
				}
			}else{
				finalViewCommonResponse.setHighestQualification("-");
			}
			SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
			SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
			finalViewCommonResponse.setQualifyingYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getQualifyingYear())
					? monthFormat.format(applicantDetail.getQualifyingYear()) +"/"+ yearFormat.format(applicantDetail.getQualifyingYear())
					: "-");
			finalViewCommonResponse.setInstituteName(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getInstitute()) ? applicantDetail.getInstitute() : "-");
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getResidenceType())){
				finalViewCommonResponse.setResidenceType(ResidenceStatusRetailMst.getById(applicantDetail.getResidenceType()).getValue());
				if(applicantDetail.getResidenceType() == 2){
					finalViewCommonResponse.setAnnualRent(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getAnnualRent()) ? applicantDetail.getAnnualRent().toString() : "-");
				}
			}else{
				finalViewCommonResponse.setResidenceType("-");
			}
			finalViewCommonResponse.setYearAtCurrentResident(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getResidingYear()) ? applicantDetail.getResidingYear().toString() : "-");
			finalViewCommonResponse.setMonthsAtCurrentResident(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getResidingMonth()) ? applicantDetail.getResidingMonth().toString() : "-");
			finalViewCommonResponse.setWebsite(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getWebsiteAddress()) ? applicantDetail.getWebsiteAddress() : "-");
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getOccupationId())){
				if(applicantDetail.getOccupationId() == 2){//salaried
					finalViewCommonResponse.setEmploymentStatus(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getEmploymentStatus()) ? EmploymentStatusRetailMst.getById(applicantDetail.getEmploymentStatus()).getValue() : "-");
					finalViewCommonResponse.setCurrentIndustry(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCurrentIndustry()) ?  applicantDetail.getCurrentIndustry() : "-");
					finalViewCommonResponse.setCurrentDepartment(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCurrentDepartment()) ?  applicantDetail.getCurrentDepartment() : "-");
					finalViewCommonResponse.setCurrentDesignation(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCurrentDesignation()) ?  applicantDetail.getCurrentDesignation() : "-");
					finalViewCommonResponse.setYearsInCurrentJob(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCurrentJobYear()) ?  applicantDetail.getCurrentJobYear().toString() : "-");
					finalViewCommonResponse.setMonthsInCurrentJob(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCurrentJobMonth()) ?  applicantDetail.getCurrentJobMonth().toString() : "-");
					finalViewCommonResponse.setTotalExperienceInMonths(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTotalExperienceMonth()) ?  applicantDetail.getTotalExperienceMonth().toString() : "-");
					finalViewCommonResponse.setTotalExperienceInYears(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTotalExperienceYear()) ?  applicantDetail.getTotalExperienceYear().toString() : "-");
					finalViewCommonResponse.setPreviousExperienceInMonths(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPreviousJobMonth()) ?  applicantDetail.getPreviousJobMonth().toString() : "-");
					finalViewCommonResponse.setPreviousExperienceInYears(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPreviousJobYear()) ?  applicantDetail.getPreviousJobYear().toString() : "-");
					finalViewCommonResponse.setPreviousEmployerName(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPreviousEmployersName()) ?  applicantDetail.getPreviousEmployersName() : "-");
					finalViewCommonResponse.setPreviousEmployerAddress(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPreviousEmployersAddress()) ?  applicantDetail.getPreviousEmployersAddress() : "-");
				}else if(applicantDetail.getOccupationId() == 6){//agriculturist
					finalViewCommonResponse.setTotalLandOwned(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTotalLandOwned()) ?  applicantDetail.getTotalLandOwned().toString() : "-");
					finalViewCommonResponse.setPresentlyIrrigated(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPresentlyIrrigated()) ?  applicantDetail.getPresentlyIrrigated() : "-");
					finalViewCommonResponse.setSeasonalIrrigated(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getSeasonalIrrigated()) ?  applicantDetail.getSeasonalIrrigated() : "-");
					finalViewCommonResponse.setRainFed(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRainFed()) ?  applicantDetail.getRainFed() : "-");
					finalViewCommonResponse.setUnAttended(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getUnattended()) ?  applicantDetail.getUnattended() : "-");
				}else if(applicantDetail.getOccupationId() == 3 || applicantDetail.getOccupationId() == 4 || applicantDetail.getOccupationId() == 5){//business/self employed prof/self employed
					finalViewCommonResponse.setEntityName(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getNameOfEntity()) ?  applicantDetail.getNameOfEntity() : "-");
					finalViewCommonResponse.setOwnershipType(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getOwnershipType()) ?  OwnershipTypeRetailMst.getById(applicantDetail.getOwnershipType()).getValue() : "-");
					finalViewCommonResponse.setOfficeType(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getOfficeType()) ?  OfficeTypeRetailMst.getById(applicantDetail.getOfficeType()).getValue() : "-");
					finalViewCommonResponse.setNoOfPartners(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getNoPartners()) ?  applicantDetail.getNoPartners().toString() : "-");
					finalViewCommonResponse.setNameOfPartners(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPartnersName()) ?  applicantDetail.getPartnersName() : "-");
					finalViewCommonResponse.setBusinessEstablishmentYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getBusinessStartDate()) ?  applicantDetail.getBusinessStartDate().getMonth()+"/"+applicantDetail.getBusinessStartDate().getYear() : "-");
					finalViewCommonResponse.setShareHolding(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getShareHolding()) ?  applicantDetail.getShareHolding() : "-");
					finalViewCommonResponse.setAnnualTurnover(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getAnnualTurnover()) ?  applicantDetail.getAnnualTurnover().toString() : "-");
					finalViewCommonResponse.setTradeLicenseNo(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTradeLicenseNumber()) ?  applicantDetail.getTradeLicenseNumber() : "-");
					finalViewCommonResponse.setTradeExpiryDate(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTradeLicenseExpiryDate()) ?  applicantDetail.getTradeLicenseExpiryDate().getMonth()+"/"+applicantDetail.getTradeLicenseExpiryDate().getYear() : "-");
					finalViewCommonResponse.setNameOfPoaHolder(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPoaHolderName()) ?  applicantDetail.getPoaHolderName() : "-");
				}
			}
			List<ExistingLoanDetailRequest> existingLoanDetailRequestList = existingLoanService.getExistingLoanDetailList(applicantId,CommonUtils.ApplicantType.APPLICANT);
			finalViewCommonResponse.setExistingLoanDetailRequest(existingLoanDetailRequestList);
			
			List<BankAccountHeldDetailsRequest> accountHeldDetailsRequestList = bankAccountsHeldService.getExistingLoanDetailList(applicantId, CommonUtils.ApplicantType.APPLICANT);
			finalViewCommonResponse.setBankAccountHeldDetailsRequest(accountHeldDetailsRequestList);
			
			List<CreditCardsDetailRequest> creditCardsDetailRequestList = creditCardDetailsService.getExistingLoanDetailList(applicantId, CommonUtils.ApplicantType.APPLICANT);
			List<CreditCardsDetailResponse> creditCardsDetailResponseList = new ArrayList<CreditCardsDetailResponse>();
			for(CreditCardsDetailRequest cardsDetailRequest:creditCardsDetailRequestList){
				CreditCardsDetailResponse cardsDetailResponse = new CreditCardsDetailResponse();
				cardsDetailResponse.setCardNumber(!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getCardNumber()) ? cardsDetailRequest.getCardNumber()  : "-");
				cardsDetailResponse.setIssuerName(!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getIssuerName()) ? cardsDetailRequest.getIssuerName()  : "-");
				/*cardsDetailResponse.setCreditCardTypes(!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getCreditCardTypesId()) ? C  cardsDetailRequest.getIssuerName()  : "-");*/
				cardsDetailResponse.setOutstandingBalance(!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getOutstandingBalance()) ? cardsDetailRequest.getOutstandingBalance().toString() : "-");
				creditCardsDetailResponseList.add(cardsDetailResponse);
			}
			finalViewCommonResponse.setCreditCardsDetailResponse(creditCardsDetailResponseList);
			
			List<FixedDepositsDetailsRequest> depositsDetailsRequestList = fixedDepositService.getFixedDepositsDetailList(applicantId, CommonUtils.ApplicantType.APPLICANT);
			finalViewCommonResponse.setFixedDepositsDetailsRequest(depositsDetailsRequestList);
			
			List<OtherCurrentAssetDetailRequest> otherCurrentAssetDetailRequestList = otherCurrentAssetService.getOtherCurrentAssetDetailList(applicantId, CommonUtils.ApplicantType.APPLICANT);
			List<OtherCurrentAssetDetailResponse> assetDetailResponseList = new ArrayList<OtherCurrentAssetDetailResponse>();
			for(OtherCurrentAssetDetailRequest assetDetailRequest:otherCurrentAssetDetailRequestList){
				OtherCurrentAssetDetailResponse assetDetailResponse = new OtherCurrentAssetDetailResponse();
				assetDetailResponse.setAssetType(!CommonUtils.isObjectNullOrEmpty(assetDetailRequest.getAssetTypesId()) ? Assets.getById(assetDetailRequest.getAssetTypesId()).getValue() : "-");
				assetDetailResponse.setAssetDescription(!CommonUtils.isObjectNullOrEmpty(assetDetailRequest.getAssetDescription()) ? assetDetailRequest.getAssetDescription() : "-");
				assetDetailResponse.setAssetValue(!CommonUtils.isObjectNullOrEmpty(assetDetailRequest.getAssetValue()) ? assetDetailRequest.getAssetValue().toString() : "-");
				assetDetailResponseList.add(assetDetailResponse);
			}
			finalViewCommonResponse.setAssetDetailResponseList(assetDetailResponseList);
			
			List<OtherIncomeDetailRequest> otherIncomeDetailRequestsList = otherIncomeService.getOtherIncomeDetailList(applicantId, CommonUtils.ApplicantType.APPLICANT);
			List<OtherIncomeDetailResponse> incomeDetailResponseList = new ArrayList<OtherIncomeDetailResponse>();
			for(OtherIncomeDetailRequest detailRequest : otherIncomeDetailRequestsList){
				OtherIncomeDetailResponse detailResponse = new OtherIncomeDetailResponse();
				detailResponse.setIncomeDetails(!CommonUtils.isObjectNullOrEmpty(detailRequest.getIncomeDetailsId()) ? IncomeDetails.getById(detailRequest.getIncomeDetailsId()).getValue() : "-");
				detailResponse.setIncomeHead(!CommonUtils.isObjectNullOrEmpty(detailRequest.getIncomeHead()) ? detailRequest.getIncomeHead() : "-");
				detailResponse.setGrossIncome(!CommonUtils.isObjectNullOrEmpty(detailRequest.getGrossIncome()) ? detailRequest.getGrossIncome().toString() : "-");
				detailResponse.setNetIncome(!CommonUtils.isObjectNullOrEmpty(detailRequest.getNetIncome()) ? detailRequest.getNetIncome().toString() : "-");
				incomeDetailResponseList.add(detailResponse);
			}
			finalViewCommonResponse.setIncomeDetailResponseList(incomeDetailResponseList);
			
			finalViewCommonResponse.setRepaymentMode(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRepaymentMode()) ? RepaymentModeRetailMst.getById(applicantDetail.getRepaymentMode()).getValue() :  "-");
			finalViewCommonResponse.setRepaymentCycle(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRepaymentCycle()) ? RepaymentModeRetailMst.getById(applicantDetail.getRepaymentCycle()).getValue() :  "-");
			finalViewCommonResponse.setInterestRateOption(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getInterestRate()) ? InterestRateRetailMst.getById(applicantDetail.getInterestRate()).getValue() :  "-");
			
			List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequestList = referenceService.getReferenceRetailDetailList(applicantId, CommonUtils.ApplicantType.APPLICANT);
			finalViewCommonResponse.setReferenceRetailDetailsRequest(referenceRetailDetailsRequestList);
			
			//for uploaded documents
			finalViewCommonResponse.setApplicant_BankACStatments(documentManagementService.getDocumentDetails(applicantId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.HOME_LOAN_APPLICANT_STATEMENT_OF_BANK_ACCOUNT_FOR_LAST_6_MONTHS));
			finalViewCommonResponse.setApplicant_SalaraySlip(documentManagementService.getDocumentDetails(applicantId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.HOME_LOAN_APPLICANT_INCOME_PROOF_LATEST_SALARY_SLIP));
			finalViewCommonResponse.setApplicant_ItReturn(documentManagementService.getDocumentDetails(applicantId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.HOME_LOAN_APPLICANT_INCOME_TAX_RETURNS_OR_FORM_16_FOR_THE_LAST_2_YEARS));
			finalViewCommonResponse.setApplicant_BalanceSheet(documentManagementService.getDocumentDetails(applicantId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.HOME_LOAN_APPLICANT_AUDITED_UNAUDITED_BALANCE_SHEET_PROFIT_LOSS_STATEMENT_FOR_3_YEARS));
			finalViewCommonResponse.setApplicant_AddressProof(documentManagementService.getDocumentDetails(applicantId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.HOME_LOAN_APPLICANT_ADDRESS_PROOF));
			finalViewCommonResponse.setApplicant_IncomeProof(documentManagementService.getDocumentDetails(applicantId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.HOME_LOAN_APPLICANT_INCOME_PROOF_OF_ENTITY___INCOME_TAX_RETURN_FOR_LAST_2_YEARS));
			finalViewCommonResponse.setApplicant_CropCultivation(documentManagementService.getDocumentDetails(applicantId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.HOME_LOAN_APPLICANT_CROP_CULTIVATION_SHOWING_CROPPING_PATTERN_LAND_HOLDING_WITH_PHOTOGRAPH));
			finalViewCommonResponse.setApplicant_AlliedActivities(documentManagementService.getDocumentDetails(applicantId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.HOME_LOAN_CO_APPLICANT_DOCUMENTARY_PROOF_OF_ALLIED_AGRICULTURAL_ACTIVITIES));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		return finalViewCommonResponse;
	}

}
