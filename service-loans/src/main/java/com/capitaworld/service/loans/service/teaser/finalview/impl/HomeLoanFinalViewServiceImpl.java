package com.capitaworld.service.loans.service.teaser.finalview.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.FinalHomeLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.AddressResponse;
import com.capitaworld.service.loans.model.retail.BankAccountHeldDetailsRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailResponse;
import com.capitaworld.service.loans.model.retail.ExistingLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.FixedDepositsDetailsRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailResponse;
import com.capitaworld.service.loans.model.retail.OtherIncomeDetailRequest;
import com.capitaworld.service.loans.model.retail.OtherIncomeDetailResponse;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;
import com.capitaworld.service.loans.model.teaser.finalview.HomeLoanFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewCommonResponse;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.FinalHomeLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.BankAccountHeldDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.retail.CreditCardsDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.ExistingLoanDetailsService;
import com.capitaworld.service.loans.service.fundseeker.retail.FixedDepositsDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.GuarantorService;
import com.capitaworld.service.loans.service.fundseeker.retail.OtherCurrentAssetDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.OtherIncomeDetailService;
import com.capitaworld.service.loans.service.fundseeker.retail.ReferenceRetailDetailsService;
import com.capitaworld.service.loans.service.teaser.finalview.HomeLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.HomeLoanPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.CityByCityListIdClient;
import com.capitaworld.service.oneform.client.CountryByCountryListIdClient;
import com.capitaworld.service.oneform.client.StateListByStateListIdClient;
import com.capitaworld.service.oneform.enums.Assets;
import com.capitaworld.service.oneform.enums.CastCategory;
import com.capitaworld.service.oneform.enums.EducationStatusRetailMst;
import com.capitaworld.service.oneform.enums.EmploymentStatusRetailMst;
import com.capitaworld.service.oneform.enums.IncomeDetails;
import com.capitaworld.service.oneform.enums.InterestRateRetailMst;
import com.capitaworld.service.oneform.enums.OfficeTypeRetailMst;
import com.capitaworld.service.oneform.enums.Options;
import com.capitaworld.service.oneform.enums.OwnershipTypeRetailMst;
import com.capitaworld.service.oneform.enums.PropertyUsedSubType;
import com.capitaworld.service.oneform.enums.ReligionRetailMst;
import com.capitaworld.service.oneform.enums.RepaymentModeRetailMst;
import com.capitaworld.service.oneform.enums.ResidenceStatusRetailMst;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
@Transactional
public class HomeLoanFinalViewServiceImpl implements HomeLoanFinalViewService{

	private static final Logger logger = LoggerFactory.getLogger(HomeLoanFinalViewServiceImpl.class);

	@Autowired
	private RetailApplicantDetailRepository applicantRepository;
    
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
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
	private HomeLoanPrimaryViewService homeLoanPrimaryViewService;
	
	@Autowired
	private CoApplicantService coApplicantService;
	
	@Autowired
	private GuarantorService guarantorService;
	
	@Autowired
	private FinalHomeLoanDetailRepository finalHomeLoanRepository;
	
	@Autowired
	private Environment environment;
	
	@Override
	public HomeLoanFinalViewResponse getHomeLoanPrimaryViewDetails(Long applicantId, Long userId) throws Exception {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.getOne(applicantId);
		HomeLoanFinalViewResponse homeLoanFinalViewResponse = new HomeLoanFinalViewResponse();
		RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(applicationMaster.getUserId(), applicantId);
		if (!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
			RetailFinalViewResponse finalViewResponse = new RetailFinalViewResponse();
			RetailFinalViewCommonResponse finalViewCommonResponse = new RetailFinalViewCommonResponse();
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCastId())){
				finalViewCommonResponse.setCaste(CastCategory.getById(applicantDetail.getCastId()).getValue());	
				if(applicantDetail.getCastId() == 6){
					finalViewCommonResponse.setCasteOther(applicantDetail.getCastOther());
				}
			}else{
				finalViewCommonResponse.setCaste("NA");
			}
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getReligion())){
				finalViewCommonResponse.setReligion(ReligionRetailMst.getById(applicantDetail.getReligion()).getValue());	
				if(applicantDetail.getReligion() == 8){
					finalViewCommonResponse.setReligionOther(applicantDetail.getReligionOther());
				}
			}else{
				finalViewCommonResponse.setReligion("NA");
			}
			finalViewCommonResponse.setBirthPlace(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getBirthPlace()) ? applicantDetail.getBirthPlace() : "NA");
			finalViewCommonResponse.setFatherFullName(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getFatherName()) ? applicantDetail.getFatherName() : "NA");
			finalViewCommonResponse.setMotherName(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getMotherName()) ? applicantDetail.getMotherName() : "NA");
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getStatusId())){
				if(applicantDetail.getStatusId() == 2){
					finalViewCommonResponse.setSpouseName(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getSpouseName()) ? applicantDetail.getSpouseName() :"NA");
					finalViewCommonResponse.setSpouseEmployed(!CommonUtils.isObjectNullOrEmpty(applicantDetail.isSpouseEmployed()) ? Options.getById((applicantDetail.isSpouseEmployed() ? 1 : 0)).getValue() :"NA");
					finalViewCommonResponse.setNoOfChildren(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getNoChildren()) ? applicantDetail.getNoChildren().toString() : "NA");
				}
			}
			finalViewCommonResponse.setNoOfDependents(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getNoDependent()) ? applicantDetail.getNoDependent().toString() : "NA");
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getHighestQualification())){
				finalViewCommonResponse.setHighestQualification(EducationStatusRetailMst.getById(applicantDetail.getHighestQualification()).getValue());
				if(applicantDetail.getHighestQualification() == 6){
					finalViewCommonResponse.setHighestQualificationOther(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getHighestQualificationOther()) ? applicantDetail.getHighestQualificationOther() : "NA");
				}
			}else{
				finalViewCommonResponse.setHighestQualification("NA");	
			}
			finalViewCommonResponse.setQualifyingYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getQualifyingYear()) ? applicantDetail.getQualifyingYear().getMonth() +"/"+ applicantDetail.getQualifyingYear().getYear() : "NA");
			finalViewCommonResponse.setInstituteName(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getInstitute()) ? applicantDetail.getInstitute() : "NA");
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getResidenceType())){
				finalViewCommonResponse.setResidenceType(ResidenceStatusRetailMst.getById(applicantDetail.getResidenceType()).getValue());
				if(applicantDetail.getResidenceType() == 2){
					finalViewCommonResponse.setAnnualRent(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getAnnualRent()) ? applicantDetail.getAnnualRent().toString() : "NA");
				}
			}else{
				finalViewCommonResponse.setResidenceType("NA");
			}
			finalViewCommonResponse.setYearAtCurrentResident(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getResidingYear()) ? applicantDetail.getResidingYear().toString() : "NA");
			finalViewCommonResponse.setMonthsAtCurrentResident(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getResidingMonth()) ? applicantDetail.getResidingMonth().toString() : "NA");
			finalViewCommonResponse.setWebsite(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getWebsiteAddress()) ? applicantDetail.getWebsiteAddress() : "NA");
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getOccupationId())){
				if(applicantDetail.getOccupationId() == 2){//salaried
					finalViewCommonResponse.setEmploymentStatus(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getEmploymentStatus()) ? EmploymentStatusRetailMst.getById(applicantDetail.getEmploymentStatus()).getValue() : "NA");
					finalViewCommonResponse.setCurrentIndustry(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCurrentIndustry()) ?  applicantDetail.getCurrentIndustry() : "NA");
					finalViewCommonResponse.setCurrentDepartment(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCurrentDepartment()) ?  applicantDetail.getCurrentDepartment() : "NA");
					finalViewCommonResponse.setCurrentDesignation(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCurrentDesignation()) ?  applicantDetail.getCurrentDesignation() : "NA");
					finalViewCommonResponse.setYearsInCurrentJob(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCurrentJobYear()) ?  applicantDetail.getCurrentJobYear().toString() : "NA");
					finalViewCommonResponse.setMonthsInCurrentJob(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getCurrentJobMonth()) ?  applicantDetail.getCurrentJobMonth().toString() : "NA");
					finalViewCommonResponse.setTotalExperienceInMonths(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTotalExperienceMonth()) ?  applicantDetail.getTotalExperienceMonth().toString() : "NA");
					finalViewCommonResponse.setTotalExperienceInYears(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTotalExperienceYear()) ?  applicantDetail.getTotalExperienceYear().toString() : "NA");
					finalViewCommonResponse.setPreviousExperienceInMonths(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPreviousJobMonth()) ?  applicantDetail.getPreviousJobMonth().toString() : "NA");
					finalViewCommonResponse.setPreviousExperienceInYears(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPreviousJobYear()) ?  applicantDetail.getPreviousJobYear().toString() : "NA");
					finalViewCommonResponse.setPreviousEmployerName(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPreviousEmployersName()) ?  applicantDetail.getPreviousEmployersName() : "NA");
					finalViewCommonResponse.setPreviousEmployerAddress(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPreviousEmployersAddress()) ?  applicantDetail.getPreviousEmployersAddress() : "NA");
				}else if(applicantDetail.getOccupationId() == 6){//agriculturist
					finalViewCommonResponse.setTotalLandOwned(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTotalLandOwned()) ?  applicantDetail.getTotalLandOwned().toString() : "NA");
					finalViewCommonResponse.setPresentlyIrrigated(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPresentlyIrrigated()) ?  applicantDetail.getPresentlyIrrigated() : "NA");
					finalViewCommonResponse.setSeasonalIrrigated(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getSeasonalIrrigated()) ?  applicantDetail.getSeasonalIrrigated() : "NA");
					finalViewCommonResponse.setRainFed(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRainFed()) ?  applicantDetail.getRainFed() : "NA");
					finalViewCommonResponse.setUnAttended(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getUnattended()) ?  applicantDetail.getUnattended() : "NA");
				}else if(applicantDetail.getOccupationId() == 3 || applicantDetail.getOccupationId() == 4 || applicantDetail.getOccupationId() == 5){//business/self employed prof/self employed
					finalViewCommonResponse.setEntityName(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getNameOfEntity()) ?  applicantDetail.getNameOfEntity() : "NA");
					finalViewCommonResponse.setOwnershipType(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getOwnershipType()) ?  OwnershipTypeRetailMst.getById(applicantDetail.getOwnershipType()).getValue() : "NA");
					finalViewCommonResponse.setOfficeType(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getOfficeType()) ?  OfficeTypeRetailMst.getById(applicantDetail.getOfficeType()).getValue() : "NA");
					finalViewCommonResponse.setNoOfPartners(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getNoPartners()) ?  applicantDetail.getNoPartners().toString() : "NA");
					finalViewCommonResponse.setNameOfPartners(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPartnersName()) ?  applicantDetail.getPartnersName() : "NA");
					finalViewCommonResponse.setBusinessEstablishmentYear(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getBusinessStartDate()) ?  applicantDetail.getBusinessStartDate().getMonth()+"/"+applicantDetail.getBusinessStartDate().getYear() : "NA");
					finalViewCommonResponse.setShareHolding(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getShareHolding()) ?  applicantDetail.getShareHolding() : "NA");
					finalViewCommonResponse.setAnnualTurnover(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getAnnualTurnover()) ?  applicantDetail.getAnnualTurnover().toString() : "NA");
					finalViewCommonResponse.setTradeLicenseNo(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTradeLicenseNumber()) ?  applicantDetail.getTradeLicenseNumber() : "NA");
					finalViewCommonResponse.setTradeExpiryDate(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getTradeLicenseExpiryDate()) ?  applicantDetail.getTradeLicenseExpiryDate().getMonth()+"/"+applicantDetail.getTradeLicenseExpiryDate().getYear() : "NA");
					finalViewCommonResponse.setNameOfPoaHolder(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getPoaHolderName()) ?  applicantDetail.getPoaHolderName() : "NA");
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
				cardsDetailResponse.setCardNumber(!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getCardNumber()) ? cardsDetailRequest.getCardNumber()  : "NA");
				cardsDetailResponse.setIssuerName(!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getIssuerName()) ? cardsDetailRequest.getIssuerName()  : "NA");
				/*cardsDetailResponse.setCreditCardTypes(!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getCreditCardTypesId()) ? C  cardsDetailRequest.getIssuerName()  : "NA");*/
				cardsDetailResponse.setOutstandingBalance(!CommonUtils.isObjectNullOrEmpty(cardsDetailRequest.getOutstandingBalance()) ? cardsDetailRequest.getOutstandingBalance().toString() : "NA");
				creditCardsDetailResponseList.add(cardsDetailResponse);
			}
			finalViewCommonResponse.setCreditCardsDetailResponse(creditCardsDetailResponseList);
			
			List<FixedDepositsDetailsRequest> depositsDetailsRequestList = fixedDepositService.getFixedDepositsDetailList(applicantId, CommonUtils.ApplicantType.APPLICANT);
			finalViewCommonResponse.setFixedDepositsDetailsRequest(depositsDetailsRequestList);
			
			List<OtherCurrentAssetDetailRequest> otherCurrentAssetDetailRequestList = otherCurrentAssetService.getOtherCurrentAssetDetailList(applicantId, CommonUtils.ApplicantType.APPLICANT);
			List<OtherCurrentAssetDetailResponse> assetDetailResponseList = new ArrayList<OtherCurrentAssetDetailResponse>();
			for(OtherCurrentAssetDetailRequest assetDetailRequest:otherCurrentAssetDetailRequestList){
				OtherCurrentAssetDetailResponse assetDetailResponse = new OtherCurrentAssetDetailResponse();
				assetDetailResponse.setAssetType(!CommonUtils.isObjectNullOrEmpty(assetDetailRequest.getAssetTypesId()) ? Assets.getById(assetDetailRequest.getAssetTypesId()).getValue() : "NA");
				assetDetailResponse.setAssetDescription(!CommonUtils.isObjectNullOrEmpty(assetDetailRequest.getAssetDescription()) ? assetDetailRequest.getAssetDescription() : "NA");
				assetDetailResponse.setAssetValue(!CommonUtils.isObjectNullOrEmpty(assetDetailRequest.getAssetValue()) ? assetDetailRequest.getAssetValue().toString() : "NA");
				assetDetailResponseList.add(assetDetailResponse);
			}
			finalViewCommonResponse.setAssetDetailResponseList(assetDetailResponseList);
			
			List<OtherIncomeDetailRequest> otherIncomeDetailRequestsList = otherIncomeService.getOtherIncomeDetailList(applicantId, CommonUtils.ApplicantType.APPLICANT);
			List<OtherIncomeDetailResponse> incomeDetailResponseList = new ArrayList<OtherIncomeDetailResponse>();
			for(OtherIncomeDetailRequest detailRequest : otherIncomeDetailRequestsList){
				OtherIncomeDetailResponse detailResponse = new OtherIncomeDetailResponse();
				detailResponse.setIncomeDetails(!CommonUtils.isObjectNullOrEmpty(detailRequest.getIncomeDetailsId()) ? IncomeDetails.getById(detailRequest.getIncomeDetailsId()).getValue() : "NA");
				detailResponse.setIncomeHead(!CommonUtils.isObjectNullOrEmpty(detailRequest.getIncomeHead()) ? detailRequest.getIncomeHead() : "NA");
				detailResponse.setGrossIncome(!CommonUtils.isObjectNullOrEmpty(detailRequest.getGrossIncome()) ? detailRequest.getGrossIncome().toString() : "NA");
				detailResponse.setNetIncome(!CommonUtils.isObjectNullOrEmpty(detailRequest.getNetIncome()) ? detailRequest.getNetIncome().toString() : "NA");
				incomeDetailResponseList.add(detailResponse);
			}
			finalViewCommonResponse.setIncomeDetailResponseList(incomeDetailResponseList);
			
			finalViewCommonResponse.setRepaymentMode(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRepaymentMode()) ? RepaymentModeRetailMst.getById(applicantDetail.getRepaymentMode()).getValue() :  "NA");
			finalViewCommonResponse.setRepaymentCycle(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRepaymentCycle()) ? RepaymentModeRetailMst.getById(applicantDetail.getRepaymentCycle()).getValue() :  "NA");
			finalViewCommonResponse.setInterestRateOption(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getInterestRate()) ? InterestRateRetailMst.getById(applicantDetail.getInterestRate()).getValue() :  "NA");
			
			List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequestList = referenceService.getReferenceRetailDetailList(applicantId, CommonUtils.ApplicantType.APPLICANT);
			finalViewCommonResponse.setReferenceRetailDetailsRequest(referenceRetailDetailsRequestList);
			finalViewResponse.setApplicantCommonDetails(finalViewCommonResponse);
			
			//co-applicant final common details
			try {
				finalViewResponse.setCoApplicantCommonDetails(coApplicantService.getCoApplicantFinalResponse(applicantId, applicationMaster.getUserId()));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting CoApplicant final details");
			}
			
			//guarantor final common details
			try {
				finalViewResponse.setGuarantorCommonDetails(guarantorService.getGuarantorFinalViewResponse(applicantId, applicationMaster.getUserId()));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting Guarantor final details");
			}
			
			//Home Loan primary details
			try { 
				homeLoanFinalViewResponse.setHomeLoanPrimaryViewResponse(homeLoanPrimaryViewService.getHomeLoanPrimaryViewDetails(applicantId));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting HL primary details");
			}
			
			//Home Loan final details
			FinalHomeLoanDetail finalHomeLoanDetails = finalHomeLoanRepository.getByApplicationAndUserId(applicantId, applicationMaster.getUserId());
			try {
				homeLoanFinalViewResponse.setPropPremiseNo(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressPremise()) ? finalHomeLoanDetails.getPropertyAddressPremise() : "NA" );
				homeLoanFinalViewResponse.setPropLandmark(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressLandmark()) ? finalHomeLoanDetails.getPropertyAddressLandmark() : "NA");
				homeLoanFinalViewResponse.setPropStreetName(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressStreet()) ? finalHomeLoanDetails.getPropertyAddressStreet() : "NA");
				homeLoanFinalViewResponse.setPropPinCode(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressPincode()) ? finalHomeLoanDetails.getPropertyAddressPincode().toString() : "NA");
				
                AddressResponse permanentAddress = new AddressResponse();
                CityByCityListIdClient cityByCityListIdClient = new CityByCityListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
                try {
                    List<Long> permanentCity = new ArrayList<Long>(1);
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressCity())) {
                    	permanentCity.add(Long.valueOf(finalHomeLoanDetails.getPropertyAddressCity()));
                        OneFormResponse formResponsePermanentCity = cityByCityListIdClient.send(permanentCity);
                        MasterResponse dataCity = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) formResponsePermanentCity.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCity)){
                        	homeLoanFinalViewResponse.setPropCity(dataCity.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setPropCity("NA");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setPropCity("NA");
                    }
                } catch (Exception e) {

                }
                CountryByCountryListIdClient countryByCountryListIdClient = new CountryByCountryListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
                try {
                    List<Long> permanentCountry = new ArrayList<Long>(1);
                    Long permanentCountryLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressCountry())) {
                        permanentCountryLong = Long.valueOf(finalHomeLoanDetails.getPropertyAddressCountry());
                        permanentCountry.add(permanentCountryLong);
                        OneFormResponse countryPermanent = countryByCountryListIdClient.send(permanentCountry);
                        MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) countryPermanent.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCountry)){
                        	homeLoanFinalViewResponse.setPropCountry(dataCountry.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setPropCountry("NA");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setPropCountry("NA");
                    }
                } catch (Exception e) {

                }
                StateListByStateListIdClient stateListByStateListIdClient = new StateListByStateListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
                try {
                    List<Long> permanentState = new ArrayList<Long>(1);
                    Long permanentStateLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyAddressState())) {
                        permanentStateLong = Long.valueOf(finalHomeLoanDetails.getPropertyAddressState());
                        permanentState.add(permanentStateLong);
                        OneFormResponse statePermanent = stateListByStateListIdClient.send(permanentState);
                        MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) statePermanent.getListData().get(0), MasterResponse.class);
                        if (!CommonUtils.isObjectNullOrEmpty(dataState)){
                        	homeLoanFinalViewResponse.setPropState(dataState.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setPropState("NA");	
                        }
                    }else{
                    	homeLoanFinalViewResponse.setPropState("NA");
                    }
                } catch (Exception e) {

                }
				
				homeLoanFinalViewResponse.setBuiltUpArea(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getBuiltUpArea()) ? finalHomeLoanDetails.getBuiltUpArea().toString() : "NA");
				homeLoanFinalViewResponse.setCarpetArea(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getCarpetArea()) ? finalHomeLoanDetails.getCarpetArea().toString() : "NA");
				homeLoanFinalViewResponse.setSuperBuiltuparea(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSuperBuiltUpArea()) ? finalHomeLoanDetails.getSuperBuiltUpArea().toString() : "NA");
				homeLoanFinalViewResponse.setSellerName(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellerName()) ? finalHomeLoanDetails.getSellerName() :  "NA");
				homeLoanFinalViewResponse.setSellerPremiseNo(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellersAddressPremise()) ? finalHomeLoanDetails.getSellersAddressPremise() : "NA");
				homeLoanFinalViewResponse.setSellerStreetName(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellersAddressStreet()) ? finalHomeLoanDetails.getSellersAddressStreet() : "NA");
				homeLoanFinalViewResponse.setSellerLandmark(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellersAddressLandmark()) ? finalHomeLoanDetails.getSellersAddressLandmark() : "NA");
				
				try {
                    List<Long> permanentCity = new ArrayList<Long>(1);
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellersAddressCity())) {
                    	permanentCity.add(Long.valueOf(finalHomeLoanDetails.getSellersAddressCity()));
                        OneFormResponse formResponsePermanentCity = cityByCityListIdClient.send(permanentCity);
                        MasterResponse dataCity = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) formResponsePermanentCity.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCity)){
                        	homeLoanFinalViewResponse.setSellerCity(dataCity.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setSellerCity("NA");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setSellerCity("NA");
                    }
                } catch (Exception e) {

                }
				try {
                    List<Long> permanentState = new ArrayList<Long>(1);
                    Long permanentStateLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellersAddressState())) {
                        permanentStateLong = Long.valueOf(finalHomeLoanDetails.getSellersAddressState());
                        permanentState.add(permanentStateLong);
                        OneFormResponse statePermanent = stateListByStateListIdClient.send(permanentState);
                        MasterResponse dataState = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) statePermanent.getListData().get(0), MasterResponse.class);
                        if (!CommonUtils.isObjectNullOrEmpty(dataState)){
                        	homeLoanFinalViewResponse.setSellerState(dataState.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setSellerState("NA");	
                        }
                    }else{
                    	homeLoanFinalViewResponse.setSellerState("NA");
                    }
                } catch (Exception e) {

                }
				
				try {
                    List<Long> permanentCountry = new ArrayList<Long>(1);
                    Long permanentCountryLong = null;
                    if (!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getSellersAddressCountry())) {
                        permanentCountryLong = Long.valueOf(finalHomeLoanDetails.getSellersAddressCountry());
                        permanentCountry.add(permanentCountryLong);
                        OneFormResponse countryPermanent = countryByCountryListIdClient.send(permanentCountry);
                        MasterResponse dataCountry = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) countryPermanent.getListData().get(0), MasterResponse.class);
                        if(!CommonUtils.isObjectNullOrEmpty(dataCountry)){
                        	homeLoanFinalViewResponse.setSellerCountry(dataCountry.getValue());
                        }else{
                        	homeLoanFinalViewResponse.setSellerCountry("NA");
                        }
                    }else{
                    	homeLoanFinalViewResponse.setSellerCountry("NA");
                    }
                } catch (Exception e) {

                }
				homeLoanFinalViewResponse.setPropertyUse(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getPropertyUsed()) ? PropertyUsedSubType.getById(finalHomeLoanDetails.getPropertyUsed()).getValue() : "NA");
				homeLoanFinalViewResponse.setRentalIncome(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetails.getEstimatedRentalIncome()) ? finalHomeLoanDetails.getEstimatedRentalIncome().toString() : "NA");
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("error while getting HL final details");
			}
		}
		return homeLoanFinalViewResponse;
	}
	
}
