package com.capitaworld.service.loans.service.irr.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.BalanceSheetDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalTermLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalUnsecureLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryTermLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.ProfitibilityStatementDetail;
import com.capitaworld.service.loans.model.retail.PastFinancialEstimatesDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.BalanceSheetDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinalTermLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinalUnsecuredLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinalWorkingCapitalLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryTermLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ProfitibilityStatementDetailRepository;
import com.capitaworld.service.loans.service.common.DocumentManagementService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PastFinancialEstiamateDetailsService;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.rating.model.FinancialInputRequest;
import com.capitaworld.service.rating.model.IndustryResponse;
import com.capitaworld.service.rating.model.IrrRequest;
import com.capitaworld.service.rating.model.QualitativeInputSheetManuRequest;
import com.capitaworld.service.rating.model.QualitativeInputSheetServRequest;
import com.capitaworld.service.rating.model.QualitativeInputSheetTradRequest;
import com.capitaworld.service.rating.model.RatingResponse;
import com.ibm.icu.util.Calendar;

@Service
@Transactional
public class IrrServiceImpl implements IrrService{
	
	@Autowired
	private DocumentManagementService documentManagementService;
	
	@Autowired
	RatingClient ratingClient;
	
	@Autowired
	OperatingStatementDetailsRepository operatingStatementDetailsRepository;
	
	@Autowired
	LiabilitiesDetailsRepository liabilitiesDetailsRepository;
	
	@Autowired
	AssetsDetailsRepository assetsDetailsRepository;
	
	@Autowired
	PastFinancialEstiamateDetailsService pastFinancialEstiamateDetailsService;
	
	@Autowired
	ProfitibilityStatementDetailRepository profitibilityStatementDetailRepository;

	@Autowired
	BalanceSheetDetailRepository balanceSheetDetailRepository;

	@Autowired
	FinalTermLoanDetailRepository finalTermLoanDetailRepository;
	
	@Autowired
	FinalWorkingCapitalLoanDetailRepository finalWorkingCapitalLoanDetailRepository;
	
	@Autowired
	FinalUnsecuredLoanDetailRepository finalUnsecuredLoanDetailRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private CorporateApplicantService applicantService;
	
	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;
	
	@Autowired
	private PrimaryTermLoanDetailRepository primaryTermLoanDetailRepository;
	
	private final Logger log = LoggerFactory.getLogger(IrrServiceImpl.class);
	
	
	@Override
	public ResponseEntity<RatingResponse> calculateIrrRating(Long appId, Long userId) {
		// TODO Auto-generated method stub
		Integer businessTypeId=null; // get from irr-cw industry mapping
		businessTypeId=1;   // temp
		IrrRequest irrIndustryRequest=new IrrRequest();
		Double industryRiskScore=8.5; //temp
		
		IrrRequest irrRequest = new IrrRequest();
		LoanApplicationMaster applicationMaster = null;
		CorporateApplicantDetail corporateApplicantDetail=null;
		try {
			
			applicationMaster = loanApplicationRepository.findOne(appId);
			
			/*if(!(true == applicationMaster.getIsFinalLocked()))
			{
				log.info("final section is not locked");	
				return new ResponseEntity<RatingResponse>(
						new RatingResponse("Submit your final one form section for MSME score", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}*/
			
			// start getting irr industry and business type
					/*	try {
										
								irrIndustryRequest.setIrrIndustryId(1L);
								irrIndustryRequest=ratingClient.getIrrIndustry(irrIndustryRequest);
								IndustryResponse industryResponse=irrIndustryRequest.getIndustryResponse();
								if(CommonUtils.isObjectNullOrEmpty(industryResponse))
								{
									log.info("Error while getting irr id from rating");	
									return new ResponseEntity<RatingResponse>(
											new RatingResponse("Something went wrong please try again after some times", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
								}
										
								businessTypeId=industryResponse.getBusinessTypeId();
								industryRiskScore=industryResponse.getScore();
										
							} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
							}*/
						
						// end getting irr industry and business type
			
			corporateApplicantDetail=corporateApplicantDetailRepository.getByApplicationAndUserId(userId,appId.longValue());
			
			irrRequest.setApplicationId(appId);
			irrRequest.setCompanyName(corporateApplicantDetail.getOrganisationName());
			irrRequest.setBusinessTypeId(businessTypeId);
			irrRequest.setUserId(userId);
			
			Boolean isCmaUploaded=isCMAUploaded(appId,applicationMaster.getProductId());
			Boolean isCoActUploaded=isCoActUploaded(appId,applicationMaster.getProductId());
			if((false == isCmaUploaded) && (false == isCoActUploaded))
			{
				log.info("cma and coAct are not uploaded.");	
				return new ResponseEntity<RatingResponse>(
						new RatingResponse("Upload either of CMA Or Company's Act in final section.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if(com.capitaworld.service.rating.utils.CommonUtils.BusinessType.MANUFACTURING == businessTypeId)
			{
				//---- Manufacturing
				irrRequest.setQualitativeInputSheetManuRequest(qualitativeInputServiceManu(appId, userId, applicationMaster.getProductId(), isCmaUploaded, isCoActUploaded,industryRiskScore));
			}
			else if(com.capitaworld.service.rating.utils.CommonUtils.BusinessType.SERVICE == businessTypeId)
			{
				//---- Service
				irrRequest.setQualitativeInputSheetServRequest(qualitativeInputServiceService(appId, userId, applicationMaster.getProductId()));
			}
			else if(com.capitaworld.service.rating.utils.CommonUtils.BusinessType.TRADING == businessTypeId)
			{
				//---- Trading
				irrRequest.setQualitativeInputSheetTradRequest(qualitativeInputServiceTrading(appId, userId, applicationMaster.getProductId()));
			}
			
		
			
			// if CMA filled
			if(isCmaUploaded)			
			irrRequest.setFinancialInputRequest(cmaIrrMappingService(appId));
			
			// if coAct filled
			if(isCoActUploaded)
			irrRequest.setFinancialInputRequest(coActIrrMappingService(appId));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			log.info("Error while mapping irr request and qualitative input from db");	
			return new ResponseEntity<RatingResponse>(
					new RatingResponse("Something went wrong please try again after some times", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		
		try {
			return new ResponseEntity<RatingResponse>(
					new RatingResponse(ratingClient.calculateIrrRating(irrRequest),"Irr rating generated", HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			log.info("Error while callling rating client");	
			return new ResponseEntity<RatingResponse>(
					new RatingResponse("Something went wrong please try again after some times", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
	}
	
	private Boolean isCMAUploaded(Long appId, Integer productId) {
		// TODO Auto-generated method stub
		
		try{
			
			LoanType type = CommonUtils.LoanType.getType(productId);
			switch (type) {
			case WORKING_CAPITAL:
				return  (documentManagementService.getDocumentDetails(appId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_CMA)).size()>0?true:false);
			case TERM_LOAN:
				return  (documentManagementService.getDocumentDetails(appId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.TL_CMA)).size()>0?true:false);
			case UNSECURED_LOAN :
				return  (documentManagementService.getDocumentDetails(appId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.USL_CMA)).size()>0?true:false);
			}
		}catch(DocumentException e){
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	private Boolean isCoActUploaded(Long appId, Integer productId) {
		// TODO Auto-generated method stub
		
		try{
			
			LoanType type = CommonUtils.LoanType.getType(productId);
			switch (type) {
			case WORKING_CAPITAL:
				return  (documentManagementService.getDocumentDetails(appId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_COMPANY_ACT)).size()>0?true:false);
			case TERM_LOAN:
				return  (documentManagementService.getDocumentDetails(appId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.TL_COMPANY_ACT)).size()>0?true:false);
			case UNSECURED_LOAN :
				return  (documentManagementService.getDocumentDetails(appId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.USL_COMPANY_ACT)).size()>0?true:false);
			}
		}catch(DocumentException e){
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
	
	@Override
	public FinancialInputRequest cmaIrrMappingService(Long aplicationId) throws Exception {
		// TODO Auto-generated method stub
		/*JSONObject jSONObject = new JSONObject();
		IrrRequest irrRequest = new IrrRequest();*/
		FinancialInputRequest financialInputRequest = new FinancialInputRequest();
		OperatingStatementDetails operatingStatementDetails = new OperatingStatementDetails();
		LiabilitiesDetails liabilitiesDetails = new LiabilitiesDetails();
		AssetsDetails assetsDetails = new AssetsDetails();
		List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequest = new ArrayList<PastFinancialEstimatesDetailRequest>();
		pastFinancialEstimatesDetailRequest=pastFinancialEstiamateDetailsService.getPastFinancialEstimateDetailsList(aplicationId);
		
		financialInputRequest.setShareFaceValue(10.00); // ------CAlculation Remained
		financialInputRequest.setNoOfMonthTy(12.0);
		financialInputRequest.setNoOfMonthSy(12.0);
		financialInputRequest.setNoOfMonthFy(12.0);
		// -------------------------------------------------------THIRD year data-------------------------------------------------------------------------
		//========= ==========================================OPERATINGSTATEMENT DETAIL 3 YR========================================================
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);		
		operatingStatementDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(aplicationId, currentYear+"");
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDomesticSales()))
			operatingStatementDetails.setDomesticSales(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDomesticSales()))
			operatingStatementDetails.setExportSales(0.0);		
		financialInputRequest.setGrossSalesTy(operatingStatementDetails.getDomesticSales()+operatingStatementDetails.getExportSales());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getLessExciseDuty()))
			operatingStatementDetails.setLessExciseDuty(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDeductOtherItems()))
			operatingStatementDetails.setDeductOtherItems(0.0);	
		financialInputRequest.setLessExciseDuityTy(operatingStatementDetails.getLessExciseDuty()+operatingStatementDetails.getDeductOtherItems());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getAddOperatingStock()))
			operatingStatementDetails.setAddOperatingStock(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDeductStockInProcess()))
			operatingStatementDetails.setDeductStockInProcess(0.0);	
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getAddOperatingStockFg()))
			operatingStatementDetails.setAddOperatingStockFg(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDeductClStockFg()))
			operatingStatementDetails.setDeductClStockFg(0.0);	
		financialInputRequest.setIncreaseDecreaseStockTy((operatingStatementDetails.getAddOperatingStock()-operatingStatementDetails.getDeductStockInProcess()) + (operatingStatementDetails.getAddOperatingStockFg()-operatingStatementDetails.getDeductClStockFg()));
		
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getRawMaterials()))
			operatingStatementDetails.setRawMaterials(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getOtherSpares()))
			operatingStatementDetails.setOtherSpares(0.0);	
		financialInputRequest.setRawMaterialConsumedTy(operatingStatementDetails.getRawMaterials()+operatingStatementDetails.getOtherSpares());
		
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getPowerAndFuel()))
			operatingStatementDetails.setPowerAndFuel(0.0);
		financialInputRequest.setPowerAndFuelCostTy(operatingStatementDetails.getPowerAndFuel());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDirectLabour()))
			operatingStatementDetails.setDirectLabour(0.0);
		financialInputRequest.setEmployeeCostTy(operatingStatementDetails.getDirectLabour());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getGeneralAdminExp()))
			operatingStatementDetails.setGeneralAdminExp(0.0);
		financialInputRequest.setGeneralAndAdminExpeTy(operatingStatementDetails.getGeneralAdminExp());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getSellingAndDistributionExpenses()))
			operatingStatementDetails.setSellingAndDistributionExpenses(0.0);
		financialInputRequest.setSellingAndDistriExpeTy(operatingStatementDetails.getSellingAndDistributionExpenses());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getExpensesAmortised()))
			operatingStatementDetails.setExpensesAmortised(0.0);
		financialInputRequest.setLessExpeCapitaTy(operatingStatementDetails.getExpensesAmortised());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getOtherMfgExpenses()))
			operatingStatementDetails.setOtherMfgExpenses(0.0);
		financialInputRequest.setMiscelExpeTy(operatingStatementDetails.getOtherMfgExpenses());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getAddOtherRevenueIncome()))
			operatingStatementDetails.setAddOtherRevenueIncome(0.0);
		financialInputRequest.setOtherIncomeTy(operatingStatementDetails.getAddOtherRevenueIncome());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getInterest()))
			operatingStatementDetails.setInterest(0.0);
		financialInputRequest.setInterestTy(operatingStatementDetails.getInterest());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDepreciation()))
			operatingStatementDetails.setDepreciation(0.0);
		financialInputRequest.setDepriciationTy(operatingStatementDetails.getDepreciation());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getNetofNonOpIncomeOrExpenses()))
			operatingStatementDetails.setNetofNonOpIncomeOrExpenses(0.0);
		financialInputRequest.setExceptionalIncomeTy(operatingStatementDetails.getNetofNonOpIncomeOrExpenses());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getProvisionForTaxes()))
			operatingStatementDetails.setProvisionForTaxes(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getProvisionForDeferredTax()))
			operatingStatementDetails.setProvisionForDeferredTax(0.0);
		financialInputRequest.setProvisionForTaxTy(operatingStatementDetails.getProvisionForTaxes() + operatingStatementDetails.getProvisionForDeferredTax());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getEquityDeividendPaidAmt()))
			operatingStatementDetails.setEquityDeividendPaidAmt(0.0);
		financialInputRequest.setDividendPayOutTy(operatingStatementDetails.getEquityDeividendPaidAmt());
		
		//========= ===============================================LIABILITIES DETAIL 3 YR==================================================================
		liabilitiesDetails = liabilitiesDetailsRepository.getLiabilitiesDetails(aplicationId, currentYear+"");
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getPreferencesShares()))
			liabilitiesDetails.setPreferencesShares(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOrdinarySharesCapital()))
			liabilitiesDetails.setOrdinarySharesCapital(0.0);
		financialInputRequest.setShareCapitalTy(liabilitiesDetails.getPreferencesShares() + liabilitiesDetails.getOrdinarySharesCapital());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getShareWarrentsOutstanding()))
			liabilitiesDetails.setShareWarrentsOutstanding(0.0);
		financialInputRequest.setShareWarrantOutstandingsTy(liabilitiesDetails.getShareWarrentsOutstanding());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getRevaluationReservse()))
			liabilitiesDetails.setRevaluationReservse(0.0);
		financialInputRequest.setRevalationReserveTy(liabilitiesDetails.getRevaluationReservse());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getGeneralReserve()))
			liabilitiesDetails.setGeneralReserve(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherReservse()))
			liabilitiesDetails.setOtherReservse(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getSurplusOrDeficit()))
			liabilitiesDetails.setSurplusOrDeficit(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOthers()))
			liabilitiesDetails.setOthers(0.0);
		financialInputRequest.setOtherReserveAndSurplusTy(liabilitiesDetails.getGeneralReserve() + liabilitiesDetails.getOtherReservse() + liabilitiesDetails.getSurplusOrDeficit() + liabilitiesDetails.getOthers());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getMinorityInterest()))
			liabilitiesDetails.setMinorityInterest(0.0);
		financialInputRequest.setMinorityInterestTy(liabilitiesDetails.getMinorityInterest());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getTermLiabilitiesSecured()))
			liabilitiesDetails.setTermLiabilitiesSecured(0.0);
		financialInputRequest.setSecuredLoansTy(liabilitiesDetails.getTermLiabilitiesSecured());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters()))
			liabilitiesDetails.setOtherNclUnsecuredLoansFromPromoters(0.0);
		financialInputRequest.setUnsecuredLoansPromotersTy(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther()))
			liabilitiesDetails.setOtherNclUnsecuredLoansFromOther(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getTermLiabilitiesUnsecured()))
			liabilitiesDetails.setTermLiabilitiesUnsecured(0.0);
		financialInputRequest.setUnsecuredLoansOthersTy(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther() + liabilitiesDetails.getTermLiabilitiesUnsecured());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getSubTotalA()))
			liabilitiesDetails.setSubTotalA(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getShortTermBorrowingFromOthers()))
			liabilitiesDetails.setShortTermBorrowingFromOthers(0.0);
		financialInputRequest.setOtherBorrowingTy(liabilitiesDetails.getSubTotalA() + liabilitiesDetails.getShortTermBorrowingFromOthers());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDeferredTaxLiability()))
			liabilitiesDetails.setDeferredTaxLiability(0.0);
		financialInputRequest.setDeferredTaxLiablitiesTy(liabilitiesDetails.getDeferredTaxLiability());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherNclOthers()))
			liabilitiesDetails.setOtherNclOthers(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDeferredPaymentsCredits()))
			liabilitiesDetails.setDeferredPaymentsCredits(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getTermDeposits()))
			liabilitiesDetails.setTermDeposits(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDebentures()))
			liabilitiesDetails.setDebentures(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherTermLiabilies()))
			liabilitiesDetails.setOtherTermLiabilies(0.0);
		financialInputRequest.setOtherLongTermLiablitiesTy(liabilitiesDetails.getOtherNclOthers() + liabilitiesDetails.getDeferredPaymentsCredits() + liabilitiesDetails.getTermDeposits() + liabilitiesDetails.getDebentures() + liabilitiesDetails.getOtherTermLiabilies());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherNclLongTermProvisions()))
			liabilitiesDetails.setOtherNclLongTermProvisions(0.0);
		financialInputRequest.setLongTermProvisionTy(liabilitiesDetails.getOtherNclLongTermProvisions());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getSundryCreditors()))
			liabilitiesDetails.setSundryCreditors(0.0);
		financialInputRequest.setTradePayablesTy(liabilitiesDetails.getSundryCreditors());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getAdvancePaymentsFromCustomers()))
			liabilitiesDetails.setAdvancePaymentsFromCustomers(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDividendPayable()))
			liabilitiesDetails.setDividendPayable(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherStatutoryLiability()))
			liabilitiesDetails.setOtherStatutoryLiability(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherCurrentLiability()))
			liabilitiesDetails.setOtherCurrentLiability(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()))
			liabilitiesDetails.setDepositsOrInstalmentsOfTermLoans(0.0);
		financialInputRequest.setOtherCurruntLiablitiesTy(liabilitiesDetails.getAdvancePaymentsFromCustomers() + liabilitiesDetails.getDividendPayable() + liabilitiesDetails.getOtherStatutoryLiability() + liabilitiesDetails.getOtherCurrentLiability() + liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getProvisionalForTaxation()))
			liabilitiesDetails.setProvisionalForTaxation(0.0);
		financialInputRequest.setShortTermProvisionTy(liabilitiesDetails.getProvisionalForTaxation());
		
		//========= ===============================================ASSET DETAIL 3 YR==================================================================
		assetsDetails = assetsDetailsRepository.getAssetsDetails(aplicationId, currentYear+"");
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getGrossBlock()))
			assetsDetails.setGrossBlock(0.0);
		financialInputRequest.setGrossBlockTy(assetsDetails.getGrossBlock());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getDepreciationToDate()))
			assetsDetails.setDepreciationToDate(0.0);
		financialInputRequest.setLessAccumulatedDepreTy(assetsDetails.getDepreciationToDate());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getImpairmentAsset()))
			assetsDetails.setImpairmentAsset(0.0);
		financialInputRequest.setImpairmentofAssetTy(assetsDetails.getImpairmentAsset());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOtherNcaOtherCapitalWorkInprogress()))
			assetsDetails.setOtherNcaOtherCapitalWorkInprogress(0.0);
		financialInputRequest.setCapitalWorkInProgressTy(assetsDetails.getOtherNcaOtherCapitalWorkInprogress());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getIntangibleAssets()))
			assetsDetails.setIntangibleAssets(0.0);
		financialInputRequest.setIntengibleAssetsTy(assetsDetails.getIntangibleAssets());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOthersPreOperativeExpensesPending()))
			assetsDetails.setOthersPreOperativeExpensesPending(0.0);
		financialInputRequest.setPreOperativeExpeTy(assetsDetails.getOthersPreOperativeExpensesPending());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOthersAssetsInTransit()))
			assetsDetails.setOthersAssetsInTransit(0.0);
		financialInputRequest.setAssetInTransitTy(assetsDetails.getOthersAssetsInTransit());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getInvestmentsInSubsidiary()))
			assetsDetails.setInvestmentsInSubsidiary(0.0);
		financialInputRequest.setInvestmentInSubsidiariesTy(assetsDetails.getInvestmentsInSubsidiary());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getTotalOtherNonCurrentAssets()))
			assetsDetails.setTotalOtherNonCurrentAssets(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getDeferredReceviables()))
			assetsDetails.setDeferredReceviables(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOthers()))
			assetsDetails.setOthers(0.0);
		financialInputRequest.setOtherInvestmentTy(assetsDetails.getTotalOtherNonCurrentAssets() + assetsDetails.getDeferredReceviables() + assetsDetails.getOthers());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getAdvanceToSuppliersCapitalGoods()))
			assetsDetails.setAdvanceToSuppliersCapitalGoods(0.0);
		financialInputRequest.setLongTermLoansAndAdvaTy(assetsDetails.getAdvanceToSuppliersCapitalGoods());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getNonConsumableStoreAndSpares()))
			assetsDetails.setNonConsumableStoreAndSpares(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOtherNonCurrentAssets()))
			assetsDetails.setOtherNonCurrentAssets(0.0);
		financialInputRequest.setOtheNonCurruntAssetTy(assetsDetails.getNonConsumableStoreAndSpares() + assetsDetails.getOtherNonCurrentAssets());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getInventory()))
			assetsDetails.setInventory(0.0);
		financialInputRequest.setInventoriesTy(assetsDetails.getInventory());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getReceivableOtherThanDefferred()))
			assetsDetails.setReceivableOtherThanDefferred(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getExportReceivables()))
			assetsDetails.setExportReceivables(0.0);
		financialInputRequest.setSundryDebtorsTy(assetsDetails.getReceivableOtherThanDefferred() + assetsDetails.getExportReceivables());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getCashAndBankBalance()))
			assetsDetails.setCashAndBankBalance(0.0);
		financialInputRequest.setCashAndBankTy(assetsDetails.getCashAndBankBalance());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getInvestments()))
			assetsDetails.setInvestments(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getInstalmentsDeferred()))
			assetsDetails.setInstalmentsDeferred(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getAdvancePaymentTaxes()))
			assetsDetails.setAdvancePaymentTaxes(0.0);
		financialInputRequest.setOtherCurruntAssetTy(assetsDetails.getInvestments() + assetsDetails.getInstalmentsDeferred() + assetsDetails.getAdvancePaymentTaxes());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getAdvanceToSupplierRawMaterials()))
			assetsDetails.setAdvanceToSupplierRawMaterials(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getAdvancePaymentTaxes()))
			assetsDetails.setAdvancePaymentTaxes(0.0);
		financialInputRequest.setShortTermLoansAdvancesTy(assetsDetails.getAdvanceToSupplierRawMaterials() + assetsDetails.getAdvancePaymentTaxes());
		// -----CONTIGENT LIABILITIES				
		financialInputRequest.setContingentLiablitiestTy(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest) ? 0.0 : pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability());
		//----------------------------------------------------------------SECOND YEAR DATA---------------------------------------------------------------------
		//========= ================================================OPERATINGSTATEMENT DETAIL 2 YR=========================================================
		operatingStatementDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(aplicationId, currentYear-1+"");
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDomesticSales()))
			operatingStatementDetails.setDomesticSales(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDomesticSales()))
			operatingStatementDetails.setExportSales(0.0);		
		financialInputRequest.setGrossSalesSy(operatingStatementDetails.getDomesticSales()+operatingStatementDetails.getExportSales());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getLessExciseDuty()))
			operatingStatementDetails.setLessExciseDuty(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDeductOtherItems()))
			operatingStatementDetails.setDeductOtherItems(0.0);	
		financialInputRequest.setLessExciseDuitySy(operatingStatementDetails.getLessExciseDuty()+operatingStatementDetails.getDeductOtherItems());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getAddOperatingStock()))
			operatingStatementDetails.setAddOperatingStock(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDeductStockInProcess()))
			operatingStatementDetails.setDeductStockInProcess(0.0);	
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getAddOperatingStockFg()))
			operatingStatementDetails.setAddOperatingStockFg(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDeductClStockFg()))
			operatingStatementDetails.setDeductClStockFg(0.0);	
		financialInputRequest.setIncreaseDecreaseStockSy((operatingStatementDetails.getAddOperatingStock()-operatingStatementDetails.getDeductStockInProcess()) + (operatingStatementDetails.getAddOperatingStockFg()-operatingStatementDetails.getDeductClStockFg()));
		
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getRawMaterials()))
			operatingStatementDetails.setRawMaterials(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getOtherSpares()))
			operatingStatementDetails.setOtherSpares(0.0);	
		financialInputRequest.setRawMaterialConsumedSy(operatingStatementDetails.getRawMaterials()+operatingStatementDetails.getOtherSpares());
		
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getPowerAndFuel()))
			operatingStatementDetails.setPowerAndFuel(0.0);
		financialInputRequest.setPowerAndFuelCostSy(operatingStatementDetails.getPowerAndFuel());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDirectLabour()))
			operatingStatementDetails.setDirectLabour(0.0);
		financialInputRequest.setEmployeeCostSy(operatingStatementDetails.getDirectLabour());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getGeneralAdminExp()))
			operatingStatementDetails.setGeneralAdminExp(0.0);
		financialInputRequest.setGeneralAndAdminExpeSy(operatingStatementDetails.getGeneralAdminExp());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getSellingAndDistributionExpenses()))
			operatingStatementDetails.setSellingAndDistributionExpenses(0.0);
		financialInputRequest.setSellingAndDistriExpeSy(operatingStatementDetails.getSellingAndDistributionExpenses());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getExpensesAmortised()))
			operatingStatementDetails.setExpensesAmortised(0.0);
		financialInputRequest.setLessExpeCapitaSy(operatingStatementDetails.getExpensesAmortised());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getOtherMfgExpenses()))
			operatingStatementDetails.setOtherMfgExpenses(0.0);
		financialInputRequest.setMiscelExpeSy(operatingStatementDetails.getOtherMfgExpenses());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getAddOtherRevenueIncome()))
			operatingStatementDetails.setAddOtherRevenueIncome(0.0);
		financialInputRequest.setOtherIncomeSy(operatingStatementDetails.getAddOtherRevenueIncome());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getInterest()))
			operatingStatementDetails.setInterest(0.0);
		financialInputRequest.setInterestSy(operatingStatementDetails.getInterest());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDepreciation()))
			operatingStatementDetails.setDepreciation(0.0);
		financialInputRequest.setDepriciationSy(operatingStatementDetails.getDepreciation());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getNetofNonOpIncomeOrExpenses()))
			operatingStatementDetails.setNetofNonOpIncomeOrExpenses(0.0);
		financialInputRequest.setExceptionalIncomeSy(operatingStatementDetails.getNetofNonOpIncomeOrExpenses());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getProvisionForTaxes()))
			operatingStatementDetails.setProvisionForTaxes(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getProvisionForDeferredTax()))
			operatingStatementDetails.setProvisionForDeferredTax(0.0);
		financialInputRequest.setProvisionForTaxSy(operatingStatementDetails.getProvisionForTaxes() + operatingStatementDetails.getProvisionForDeferredTax());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getEquityDeividendPaidAmt()))
			operatingStatementDetails.setEquityDeividendPaidAmt(0.0);
		financialInputRequest.setDividendPayOutSy(operatingStatementDetails.getEquityDeividendPaidAmt());

		//========= ===============================================LIABILITIES DETAIL 2 YR==================================================================
		liabilitiesDetails = liabilitiesDetailsRepository.getLiabilitiesDetails(aplicationId, currentYear-1+"");
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getPreferencesShares()))
			liabilitiesDetails.setPreferencesShares(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOrdinarySharesCapital()))
			liabilitiesDetails.setOrdinarySharesCapital(0.0);
		financialInputRequest.setShareCapitalSy(liabilitiesDetails.getPreferencesShares() + liabilitiesDetails.getOrdinarySharesCapital());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getShareWarrentsOutstanding()))
			liabilitiesDetails.setShareWarrentsOutstanding(0.0);
		financialInputRequest.setShareWarrantOutstandingsSy(liabilitiesDetails.getShareWarrentsOutstanding());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getRevaluationReservse()))
			liabilitiesDetails.setRevaluationReservse(0.0);
		financialInputRequest.setRevalationReserveSy(liabilitiesDetails.getRevaluationReservse());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getGeneralReserve()))
			liabilitiesDetails.setGeneralReserve(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherReservse()))
			liabilitiesDetails.setOtherReservse(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getSurplusOrDeficit()))
			liabilitiesDetails.setSurplusOrDeficit(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOthers()))
			liabilitiesDetails.setOthers(0.0);
		financialInputRequest.setOtherReserveAndSurplusSy(liabilitiesDetails.getGeneralReserve() + liabilitiesDetails.getOtherReservse() + liabilitiesDetails.getSurplusOrDeficit() + liabilitiesDetails.getOthers());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getMinorityInterest()))
			liabilitiesDetails.setMinorityInterest(0.0);
		financialInputRequest.setMinorityInterestSy(liabilitiesDetails.getMinorityInterest());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getTermLiabilitiesSecured()))
			liabilitiesDetails.setTermLiabilitiesSecured(0.0);
		financialInputRequest.setSecuredLoansSy(liabilitiesDetails.getTermLiabilitiesSecured());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters()))
			liabilitiesDetails.setOtherNclUnsecuredLoansFromPromoters(0.0);
		financialInputRequest.setUnsecuredLoansPromotersSy(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther()))
			liabilitiesDetails.setOtherNclUnsecuredLoansFromOther(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getTermLiabilitiesUnsecured()))
			liabilitiesDetails.setTermLiabilitiesUnsecured(0.0);
		financialInputRequest.setUnsecuredLoansOthersSy(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther() + liabilitiesDetails.getTermLiabilitiesUnsecured());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getSubTotalA()))
			liabilitiesDetails.setSubTotalA(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getShortTermBorrowingFromOthers()))
			liabilitiesDetails.setShortTermBorrowingFromOthers(0.0);
		financialInputRequest.setOtherBorrowingSy(liabilitiesDetails.getSubTotalA() + liabilitiesDetails.getShortTermBorrowingFromOthers());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDeferredTaxLiability()))
			liabilitiesDetails.setDeferredTaxLiability(0.0);
		financialInputRequest.setDeferredTaxLiablitiesSy(liabilitiesDetails.getDeferredTaxLiability());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherNclOthers()))
			liabilitiesDetails.setOtherNclOthers(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDeferredPaymentsCredits()))
			liabilitiesDetails.setDeferredPaymentsCredits(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getTermDeposits()))
			liabilitiesDetails.setTermDeposits(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDebentures()))
			liabilitiesDetails.setDebentures(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherTermLiabilies()))
			liabilitiesDetails.setOtherTermLiabilies(0.0);
		financialInputRequest.setOtherLongTermLiablitiesSy(liabilitiesDetails.getOtherNclOthers() + liabilitiesDetails.getDeferredPaymentsCredits() + liabilitiesDetails.getTermDeposits() + liabilitiesDetails.getDebentures() + liabilitiesDetails.getOtherTermLiabilies());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherNclLongTermProvisions()))
			liabilitiesDetails.setOtherNclLongTermProvisions(0.0);
		financialInputRequest.setLongTermProvisionSy(liabilitiesDetails.getOtherNclLongTermProvisions());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getSundryCreditors()))
			liabilitiesDetails.setSundryCreditors(0.0);
		financialInputRequest.setTradePayablesSy(liabilitiesDetails.getSundryCreditors());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getAdvancePaymentsFromCustomers()))
			liabilitiesDetails.setAdvancePaymentsFromCustomers(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDividendPayable()))
			liabilitiesDetails.setDividendPayable(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherStatutoryLiability()))
			liabilitiesDetails.setOtherStatutoryLiability(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherCurrentLiability()))
			liabilitiesDetails.setOtherCurrentLiability(0.0);
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()))
			liabilitiesDetails.setDepositsOrInstalmentsOfTermLoans(0.0);
		financialInputRequest.setOtherCurruntLiablitiesSy(liabilitiesDetails.getAdvancePaymentsFromCustomers() + liabilitiesDetails.getDividendPayable() + liabilitiesDetails.getOtherStatutoryLiability() + liabilitiesDetails.getOtherCurrentLiability() + liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans());
		
		if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getProvisionalForTaxation()))
			liabilitiesDetails.setProvisionalForTaxation(0.0);
		financialInputRequest.setShortTermProvisionSy(liabilitiesDetails.getProvisionalForTaxation());
		
		//========= ===============================================ASSET DETAIL 3 YR==================================================================
		assetsDetails = assetsDetailsRepository.getAssetsDetails(aplicationId, currentYear-1+"");
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getGrossBlock()))
			assetsDetails.setGrossBlock(0.0);
		financialInputRequest.setGrossBlockSy(assetsDetails.getGrossBlock());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getDepreciationToDate()))
			assetsDetails.setDepreciationToDate(0.0);
		financialInputRequest.setLessAccumulatedDepreSy(assetsDetails.getDepreciationToDate());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getImpairmentAsset()))
			assetsDetails.setImpairmentAsset(0.0);
		financialInputRequest.setImpairmentofAssetSy(assetsDetails.getImpairmentAsset());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOtherNcaOtherCapitalWorkInprogress()))
			assetsDetails.setOtherNcaOtherCapitalWorkInprogress(0.0);
		financialInputRequest.setCapitalWorkInProgressSy(assetsDetails.getOtherNcaOtherCapitalWorkInprogress());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getIntangibleAssets()))
			assetsDetails.setIntangibleAssets(0.0);
		financialInputRequest.setIntengibleAssetsSy(assetsDetails.getIntangibleAssets());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOthersPreOperativeExpensesPending()))
			assetsDetails.setOthersPreOperativeExpensesPending(0.0);
		financialInputRequest.setPreOperativeExpeSy(assetsDetails.getOthersPreOperativeExpensesPending());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOthersAssetsInTransit()))
			assetsDetails.setOthersAssetsInTransit(0.0);
		financialInputRequest.setAssetInTransitSy(assetsDetails.getOthersAssetsInTransit());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getInvestmentsInSubsidiary()))
			assetsDetails.setInvestmentsInSubsidiary(0.0);
		financialInputRequest.setInvestmentInSubsidiariesSy(assetsDetails.getInvestmentsInSubsidiary());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getTotalOtherNonCurrentAssets()))
			assetsDetails.setTotalOtherNonCurrentAssets(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getDeferredReceviables()))
			assetsDetails.setDeferredReceviables(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOthers()))
			assetsDetails.setOthers(0.0);
		financialInputRequest.setOtherInvestmentSy(assetsDetails.getTotalOtherNonCurrentAssets() + assetsDetails.getDeferredReceviables() + assetsDetails.getOthers());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getAdvanceToSuppliersCapitalGoods()))
			assetsDetails.setAdvanceToSuppliersCapitalGoods(0.0);
		financialInputRequest.setLongTermLoansAndAdvaSy(assetsDetails.getAdvanceToSuppliersCapitalGoods());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getNonConsumableStoreAndSpares()))
			assetsDetails.setNonConsumableStoreAndSpares(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOtherNonCurrentAssets()))
			assetsDetails.setOtherNonCurrentAssets(0.0);
		financialInputRequest.setOtheNonCurruntAssetSy(assetsDetails.getNonConsumableStoreAndSpares() + assetsDetails.getOtherNonCurrentAssets());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getInventory()))
			assetsDetails.setInventory(0.0);
		financialInputRequest.setInventoriesSy(assetsDetails.getInventory());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getReceivableOtherThanDefferred()))
			assetsDetails.setReceivableOtherThanDefferred(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getExportReceivables()))
			assetsDetails.setExportReceivables(0.0);
		financialInputRequest.setSundryDebtorsSy(assetsDetails.getReceivableOtherThanDefferred() + assetsDetails.getExportReceivables());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getCashAndBankBalance()))
			assetsDetails.setCashAndBankBalance(0.0);
		financialInputRequest.setCashAndBankSy(assetsDetails.getCashAndBankBalance());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getInvestments()))
			assetsDetails.setInvestments(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getInstalmentsDeferred()))
			assetsDetails.setInstalmentsDeferred(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getAdvancePaymentTaxes()))
			assetsDetails.setAdvancePaymentTaxes(0.0);
		financialInputRequest.setOtherCurruntAssetSy(assetsDetails.getInvestments() + assetsDetails.getInstalmentsDeferred() + assetsDetails.getAdvancePaymentTaxes());
		
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getAdvanceToSupplierRawMaterials()))
			assetsDetails.setAdvanceToSupplierRawMaterials(0.0);
		if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getAdvancePaymentTaxes()))
			assetsDetails.setAdvancePaymentTaxes(0.0);
		financialInputRequest.setShortTermLoansAdvancesSy(assetsDetails.getAdvanceToSupplierRawMaterials() + assetsDetails.getAdvancePaymentTaxes());
		// -----CONTIGENT LIABILITIES		
		financialInputRequest.setContingentLiablitiesSy(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest) ? 0.0 : pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-2).getContingentLiability());
		
		
		// ----------------------------------------FIRST YEAR DATA---------------------------------------------------------------------------------------
		//========= ==========================================OPERATINGSTATEMENT DETAIL 1 YR========================================================
		operatingStatementDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(aplicationId, currentYear-2+"");
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDomesticSales()))
			operatingStatementDetails.setDomesticSales(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDomesticSales()))
			operatingStatementDetails.setExportSales(0.0);		
		financialInputRequest.setGrossSalesFy(operatingStatementDetails.getDomesticSales()+operatingStatementDetails.getExportSales());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getLessExciseDuty()))
			operatingStatementDetails.setLessExciseDuty(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDeductOtherItems()))
			operatingStatementDetails.setDeductOtherItems(0.0);	
		financialInputRequest.setLessExciseDuityFy(operatingStatementDetails.getLessExciseDuty()+operatingStatementDetails.getDeductOtherItems());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getAddOperatingStock()))
			operatingStatementDetails.setAddOperatingStock(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDeductStockInProcess()))
			operatingStatementDetails.setDeductStockInProcess(0.0);	
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getAddOperatingStockFg()))
			operatingStatementDetails.setAddOperatingStockFg(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDeductClStockFg()))
			operatingStatementDetails.setDeductClStockFg(0.0);	
		financialInputRequest.setIncreaseDecreaseStockFy((operatingStatementDetails.getAddOperatingStock()-operatingStatementDetails.getDeductStockInProcess()) + (operatingStatementDetails.getAddOperatingStockFg()-operatingStatementDetails.getDeductClStockFg()));
		
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getRawMaterials()))
			operatingStatementDetails.setRawMaterials(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getOtherSpares()))
			operatingStatementDetails.setOtherSpares(0.0);	
		financialInputRequest.setRawMaterialConsumedFy(operatingStatementDetails.getRawMaterials()+operatingStatementDetails.getOtherSpares());
		
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getPowerAndFuel()))
			operatingStatementDetails.setPowerAndFuel(0.0);
		financialInputRequest.setPowerAndFuelCostFy(operatingStatementDetails.getPowerAndFuel());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDirectLabour()))
			operatingStatementDetails.setDirectLabour(0.0);
		financialInputRequest.setEmployeeCostFy(operatingStatementDetails.getDirectLabour());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getGeneralAdminExp()))
			operatingStatementDetails.setGeneralAdminExp(0.0);
		financialInputRequest.setGeneralAndAdminExpeFy(operatingStatementDetails.getGeneralAdminExp());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getSellingAndDistributionExpenses()))
			operatingStatementDetails.setSellingAndDistributionExpenses(0.0);
		financialInputRequest.setSellingAndDistriExpeFy(operatingStatementDetails.getSellingAndDistributionExpenses());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getExpensesAmortised()))
			operatingStatementDetails.setExpensesAmortised(0.0);
		financialInputRequest.setLessExpeCapitaFy(operatingStatementDetails.getExpensesAmortised());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getOtherMfgExpenses()))
			operatingStatementDetails.setOtherMfgExpenses(0.0);
		financialInputRequest.setMiscelExpeFy(operatingStatementDetails.getOtherMfgExpenses());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getAddOtherRevenueIncome()))
			operatingStatementDetails.setAddOtherRevenueIncome(0.0);
		financialInputRequest.setOtherIncomeFy(operatingStatementDetails.getAddOtherRevenueIncome());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getInterest()))
			operatingStatementDetails.setInterest(0.0);
		financialInputRequest.setInterestFy(operatingStatementDetails.getInterest());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDepreciation()))
			operatingStatementDetails.setDepreciation(0.0);
		financialInputRequest.setDepriciationFy(operatingStatementDetails.getDepreciation());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getNetofNonOpIncomeOrExpenses()))
			operatingStatementDetails.setNetofNonOpIncomeOrExpenses(0.0);
		financialInputRequest.setExceptionalIncomeFy(operatingStatementDetails.getNetofNonOpIncomeOrExpenses());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getProvisionForTaxes()))
			operatingStatementDetails.setProvisionForTaxes(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getProvisionForDeferredTax()))
			operatingStatementDetails.setProvisionForDeferredTax(0.0);
		financialInputRequest.setProvisionForTaxFy(operatingStatementDetails.getProvisionForTaxes() + operatingStatementDetails.getProvisionForDeferredTax());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getEquityDeividendPaidAmt()))
			operatingStatementDetails.setEquityDeividendPaidAmt(0.0);
		financialInputRequest.setDividendPayOutFy(operatingStatementDetails.getEquityDeividendPaidAmt());
		
		//========= ===============================================LIABILITIES DETAIL 1 YR==================================================================
				liabilitiesDetails = liabilitiesDetailsRepository.getLiabilitiesDetails(aplicationId, currentYear-2+"");
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getPreferencesShares()))
					liabilitiesDetails.setPreferencesShares(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOrdinarySharesCapital()))
					liabilitiesDetails.setOrdinarySharesCapital(0.0);
				financialInputRequest.setShareCapitalFy(liabilitiesDetails.getPreferencesShares() + liabilitiesDetails.getOrdinarySharesCapital());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getShareWarrentsOutstanding()))
					liabilitiesDetails.setShareWarrentsOutstanding(0.0);
				financialInputRequest.setShareWarrantOutstandingsSy(liabilitiesDetails.getShareWarrentsOutstanding());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getRevaluationReservse()))
					liabilitiesDetails.setRevaluationReservse(0.0);
				financialInputRequest.setRevalationReserveFy(liabilitiesDetails.getRevaluationReservse());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getGeneralReserve()))
					liabilitiesDetails.setGeneralReserve(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherReservse()))
					liabilitiesDetails.setOtherReservse(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getSurplusOrDeficit()))
					liabilitiesDetails.setSurplusOrDeficit(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOthers()))
					liabilitiesDetails.setOthers(0.0);
				financialInputRequest.setOtherReserveAndSurplusFy(liabilitiesDetails.getGeneralReserve() + liabilitiesDetails.getOtherReservse() + liabilitiesDetails.getSurplusOrDeficit() + liabilitiesDetails.getOthers());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getMinorityInterest()))
					liabilitiesDetails.setMinorityInterest(0.0);
				financialInputRequest.setMinorityInterestFy(liabilitiesDetails.getMinorityInterest());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getTermLiabilitiesSecured()))
					liabilitiesDetails.setTermLiabilitiesSecured(0.0);
				financialInputRequest.setSecuredLoansFy(liabilitiesDetails.getTermLiabilitiesSecured());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters()))
					liabilitiesDetails.setOtherNclUnsecuredLoansFromPromoters(0.0);
				financialInputRequest.setUnsecuredLoansPromotersFy(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther()))
					liabilitiesDetails.setOtherNclUnsecuredLoansFromOther(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getTermLiabilitiesUnsecured()))
					liabilitiesDetails.setTermLiabilitiesUnsecured(0.0);
				financialInputRequest.setUnsecuredLoansOthersFy(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther() + liabilitiesDetails.getTermLiabilitiesUnsecured());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getSubTotalA()))
					liabilitiesDetails.setSubTotalA(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getShortTermBorrowingFromOthers()))
					liabilitiesDetails.setShortTermBorrowingFromOthers(0.0);
				financialInputRequest.setOtherBorrowingFy(liabilitiesDetails.getSubTotalA() + liabilitiesDetails.getShortTermBorrowingFromOthers());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDeferredTaxLiability()))
					liabilitiesDetails.setDeferredTaxLiability(0.0);
				financialInputRequest.setDeferredTaxLiablitiesFy(liabilitiesDetails.getDeferredTaxLiability());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherNclOthers()))
					liabilitiesDetails.setOtherNclOthers(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDeferredPaymentsCredits()))
					liabilitiesDetails.setDeferredPaymentsCredits(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getTermDeposits()))
					liabilitiesDetails.setTermDeposits(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDebentures()))
					liabilitiesDetails.setDebentures(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherTermLiabilies()))
					liabilitiesDetails.setOtherTermLiabilies(0.0);
				financialInputRequest.setOtherLongTermLiablitiesFy(liabilitiesDetails.getOtherNclOthers() + liabilitiesDetails.getDeferredPaymentsCredits() + liabilitiesDetails.getTermDeposits() + liabilitiesDetails.getDebentures() + liabilitiesDetails.getOtherTermLiabilies());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherNclLongTermProvisions()))
					liabilitiesDetails.setOtherNclLongTermProvisions(0.0);
				financialInputRequest.setLongTermProvisionFy(liabilitiesDetails.getOtherNclLongTermProvisions());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getSundryCreditors()))
					liabilitiesDetails.setSundryCreditors(0.0);
				financialInputRequest.setTradePayablesFy(liabilitiesDetails.getSundryCreditors());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getAdvancePaymentsFromCustomers()))
					liabilitiesDetails.setAdvancePaymentsFromCustomers(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDividendPayable()))
					liabilitiesDetails.setDividendPayable(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherStatutoryLiability()))
					liabilitiesDetails.setOtherStatutoryLiability(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getOtherCurrentLiability()))
					liabilitiesDetails.setOtherCurrentLiability(0.0);
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()))
					liabilitiesDetails.setDepositsOrInstalmentsOfTermLoans(0.0);
				financialInputRequest.setOtherCurruntLiablitiesFy(liabilitiesDetails.getAdvancePaymentsFromCustomers() + liabilitiesDetails.getDividendPayable() + liabilitiesDetails.getOtherStatutoryLiability() + liabilitiesDetails.getOtherCurrentLiability() + liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans());
				
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetails.getProvisionalForTaxation()))
					liabilitiesDetails.setProvisionalForTaxation(0.0);
				financialInputRequest.setShortTermProvisionFy(liabilitiesDetails.getProvisionalForTaxation());
				
				//========= ===============================================ASSET DETAIL 3 YR==================================================================
				assetsDetails = assetsDetailsRepository.getAssetsDetails(aplicationId, currentYear-2+"");
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getGrossBlock()))
					assetsDetails.setGrossBlock(0.0);
				financialInputRequest.setGrossBlockFy(assetsDetails.getGrossBlock());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getDepreciationToDate()))
					assetsDetails.setDepreciationToDate(0.0);
				financialInputRequest.setLessAccumulatedDepreFy(assetsDetails.getDepreciationToDate());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getImpairmentAsset()))
					assetsDetails.setImpairmentAsset(0.0);
				financialInputRequest.setImpairmentofAssetFy(assetsDetails.getImpairmentAsset());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOtherNcaOtherCapitalWorkInprogress()))
					assetsDetails.setOtherNcaOtherCapitalWorkInprogress(0.0);
				financialInputRequest.setCapitalWorkInProgressFy(assetsDetails.getOtherNcaOtherCapitalWorkInprogress());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getIntangibleAssets()))
					assetsDetails.setIntangibleAssets(0.0);
				financialInputRequest.setIntengibleAssetsFy(assetsDetails.getIntangibleAssets());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOthersPreOperativeExpensesPending()))
					assetsDetails.setOthersPreOperativeExpensesPending(0.0);
				financialInputRequest.setPreOperativeExpeFy(assetsDetails.getOthersPreOperativeExpensesPending());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOthersAssetsInTransit()))
					assetsDetails.setOthersAssetsInTransit(0.0);
				financialInputRequest.setAssetInTransitFy(assetsDetails.getOthersAssetsInTransit());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getInvestmentsInSubsidiary()))
					assetsDetails.setInvestmentsInSubsidiary(0.0);
				financialInputRequest.setInvestmentInSubsidiariesFy(assetsDetails.getInvestmentsInSubsidiary());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getTotalOtherNonCurrentAssets()))
					assetsDetails.setTotalOtherNonCurrentAssets(0.0);
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getDeferredReceviables()))
					assetsDetails.setDeferredReceviables(0.0);
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOthers()))
					assetsDetails.setOthers(0.0);
				financialInputRequest.setOtherInvestmentFy(assetsDetails.getTotalOtherNonCurrentAssets() + assetsDetails.getDeferredReceviables() + assetsDetails.getOthers());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getAdvanceToSuppliersCapitalGoods()))
					assetsDetails.setAdvanceToSuppliersCapitalGoods(0.0);
				financialInputRequest.setLongTermLoansAndAdvaFy(assetsDetails.getAdvanceToSuppliersCapitalGoods());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getNonConsumableStoreAndSpares()))
					assetsDetails.setNonConsumableStoreAndSpares(0.0);
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getOtherNonCurrentAssets()))
					assetsDetails.setOtherNonCurrentAssets(0.0);
				financialInputRequest.setOtheNonCurruntAssetFy(assetsDetails.getNonConsumableStoreAndSpares() + assetsDetails.getOtherNonCurrentAssets());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getInventory()))
					assetsDetails.setInventory(0.0);
				financialInputRequest.setInventoriesFy(assetsDetails.getInventory());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getReceivableOtherThanDefferred()))
					assetsDetails.setReceivableOtherThanDefferred(0.0);
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getExportReceivables()))
					assetsDetails.setExportReceivables(0.0);
				financialInputRequest.setSundryDebtorsFy(assetsDetails.getReceivableOtherThanDefferred() + assetsDetails.getExportReceivables());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getCashAndBankBalance()))
					assetsDetails.setCashAndBankBalance(0.0);
				financialInputRequest.setCashAndBankFy(assetsDetails.getCashAndBankBalance());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getInvestments()))
					assetsDetails.setInvestments(0.0);
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getInstalmentsDeferred()))
					assetsDetails.setInstalmentsDeferred(0.0);
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getAdvancePaymentTaxes()))
					assetsDetails.setAdvancePaymentTaxes(0.0);
				financialInputRequest.setOtherCurruntAssetFy(assetsDetails.getInvestments() + assetsDetails.getInstalmentsDeferred() + assetsDetails.getAdvancePaymentTaxes());
				
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getAdvanceToSupplierRawMaterials()))
					assetsDetails.setAdvanceToSupplierRawMaterials(0.0);
				if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getAdvancePaymentTaxes()))
					assetsDetails.setAdvancePaymentTaxes(0.0);
				financialInputRequest.setShortTermLoansAdvancesFy(assetsDetails.getAdvanceToSupplierRawMaterials() + assetsDetails.getAdvancePaymentTaxes());
				// -----CONTIGENT LIABILITIES		
				financialInputRequest.setContingentLiablitiesFy(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest) ? 0.0 : pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-3).getContingentLiability());
				
				
		// FinancialInput Object Set
		/*irrRequest.setFinancialInputRequest(financialInputRequest);
		jSONObject.put("irrRequest",irrRequest);*/
		return financialInputRequest;
	}

	@Override
	public FinancialInputRequest coActIrrMappingService(Long aplicationId) throws Exception {
		// TODO Auto-generated method stub
		/*JSONObject jSONObject = new JSONObject();
		IrrRequest irrRequest = new IrrRequest();*/
		FinancialInputRequest financialInputRequest = new FinancialInputRequest();
		ProfitibilityStatementDetail profitibilityStatementDetail = new ProfitibilityStatementDetail();
		BalanceSheetDetail balanceSheetDetail = new BalanceSheetDetail();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);	
		List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequest = new ArrayList<PastFinancialEstimatesDetailRequest>();
		pastFinancialEstimatesDetailRequest=pastFinancialEstiamateDetailsService.getPastFinancialEstimateDetailsList(aplicationId);
		
		financialInputRequest.setShareFaceValue(10.00); // ------CAlculation Remained
		financialInputRequest.setNoOfMonthTy(12.0);
		financialInputRequest.setNoOfMonthSy(12.0);
		financialInputRequest.setNoOfMonthFy(12.0);
		// ----------------------------------------THIRD YEAR DATA---------------------------------------------------------------------------------------
		//========= ==========================================PROFITIBILITYSTATEMENTDETAIL DETAIL 3 YR========================================================
		profitibilityStatementDetail = profitibilityStatementDetailRepository.getProfitibilityStatementDetail(aplicationId, currentYear+"");
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getSales()))
			profitibilityStatementDetail.setSales(0.0);
		financialInputRequest.setGrossSalesTy(profitibilityStatementDetail.getSales());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getLessExciseDutyOrVatOrServiceTax()))
			profitibilityStatementDetail.setLessExciseDutyOrVatOrServiceTax(0.0);
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getLessAnyOtherItem()))
			profitibilityStatementDetail.setLessAnyOtherItem(0.0);
		financialInputRequest.setLessExciseDuityTy(profitibilityStatementDetail.getLessExciseDutyOrVatOrServiceTax() + profitibilityStatementDetail.getLessAnyOtherItem());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getIncreaseOrDecreaseInInventoryFg()))
			profitibilityStatementDetail.setIncreaseOrDecreaseInInventoryFg(0.0);
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getIncreaseOrDecreaseInInventoryWip()))
			profitibilityStatementDetail.setIncreaseOrDecreaseInInventoryWip(0.0);
		financialInputRequest.setIncreaseDecreaseStockTy(profitibilityStatementDetail.getIncreaseOrDecreaseInInventoryFg() + profitibilityStatementDetail.getIncreaseOrDecreaseInInventoryWip());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getCostRawMaterialConsumed()))
			profitibilityStatementDetail.setCostRawMaterialConsumed(0.0);
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getPurchasesStockTnTrade()))
			profitibilityStatementDetail.setPurchasesStockTnTrade(0.0);
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getStoreAndSpares()))
			profitibilityStatementDetail.setStoreAndSpares(0.0);
		financialInputRequest.setRawMaterialConsumedTy(profitibilityStatementDetail.getCostRawMaterialConsumed() + profitibilityStatementDetail.getPurchasesStockTnTrade()  + profitibilityStatementDetail.getStoreAndSpares());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getPowerAndFuel()))
			profitibilityStatementDetail.setPowerAndFuel(0.0);
		financialInputRequest.setPowerAndFuelCostTy(profitibilityStatementDetail.getPowerAndFuel());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getEmployeeBenefitExpenses()))
			profitibilityStatementDetail.setEmployeeBenefitExpenses(0.0);
		financialInputRequest.setEmployeeCostTy(profitibilityStatementDetail.getEmployeeBenefitExpenses());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getGeneralAdminExpenses()))
			profitibilityStatementDetail.setGeneralAdminExpenses(0.0);
		financialInputRequest.setGeneralAndAdminExpeTy(profitibilityStatementDetail.getGeneralAdminExpenses());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getSellingDistributionExpenses()))
			profitibilityStatementDetail.setSellingDistributionExpenses(0.0);
		financialInputRequest.setSellingAndDistriExpeTy(profitibilityStatementDetail.getSellingDistributionExpenses());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getExpensesCapitalised()))
			profitibilityStatementDetail.setExpensesCapitalised(0.0);
		financialInputRequest.setLessExpeCapitaTy(profitibilityStatementDetail.getExpensesCapitalised());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getOtherPlsSpecify())) // ------ others pls specify
			profitibilityStatementDetail.setOtherPlsSpecify(0.0);
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getExpensesCapitalised()))
			profitibilityStatementDetail.setExpensesCapitalised(0.0);
		financialInputRequest.setMiscelExpeTy(profitibilityStatementDetail.getOtherPlsSpecify() - profitibilityStatementDetail.getExpensesCapitalised()); //----------------others pls specify field
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getOtherOperatingRevenue()))
			profitibilityStatementDetail.setOtherOperatingRevenue(0.0);
		financialInputRequest.setOtherIncomeTy(profitibilityStatementDetail.getOtherOperatingRevenue());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getFinanceCost()))
			profitibilityStatementDetail.setFinanceCost(0.0);
		financialInputRequest.setInterestTy(profitibilityStatementDetail.getFinanceCost());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getDepreciationAndAmortisation()))
			profitibilityStatementDetail.setDepreciationAndAmortisation(0.0);
		financialInputRequest.setDepriciationTy(profitibilityStatementDetail.getDepreciationAndAmortisation());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getNonOperatingIncome()))
			profitibilityStatementDetail.setNonOperatingIncome(0.0);
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getNonOperatingExpenses()))
			profitibilityStatementDetail.setNonOperatingExpenses(0.0);
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getExtraordinaryItems()))
			profitibilityStatementDetail.setExtraordinaryItems(0.0);
		financialInputRequest.setExceptionalIncomeTy(profitibilityStatementDetail.getNonOperatingIncome() + profitibilityStatementDetail.getNonOperatingExpenses() + profitibilityStatementDetail.getExtraordinaryItems());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getProvisionForTax()))
			profitibilityStatementDetail.setProvisionForTax(0.0);
		financialInputRequest.setProvisionForTaxTy(profitibilityStatementDetail.getProvisionForTax());
		
		if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getDividend()))
			profitibilityStatementDetail.setDividend(0.0);
		financialInputRequest.setDividendPayOutTy(profitibilityStatementDetail.getDividend());
		//========= ==========================================LIABILITY(BS) DETAIL 3 YR========================================================
		balanceSheetDetail = balanceSheetDetailRepository.getBalanceSheetDetail(aplicationId, currentYear+"");
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOrdinaryShareCapital()))
			balanceSheetDetail.setOrdinaryShareCapital(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getPreferenceShareCapital()))
			balanceSheetDetail.setPreferenceShareCapital(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getShareApplicationPendingAllotment()))
			balanceSheetDetail.setShareApplicationPendingAllotment(0.0);
		financialInputRequest.setShareCapitalTy(balanceSheetDetail.getOrdinaryShareCapital() + balanceSheetDetail.getPreferenceShareCapital() + balanceSheetDetail.getShareApplicationPendingAllotment());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getMoneyReceivedAgainstShareWarrants()))
			balanceSheetDetail.setMoneyReceivedAgainstShareWarrants(0.0);
		financialInputRequest.setShareWarrantOutstandingsTy(balanceSheetDetail.getMoneyReceivedAgainstShareWarrants());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getRevaluationReserve()))
			balanceSheetDetail.setRevaluationReserve(0.0);
		financialInputRequest.setRevalationReserveTy(balanceSheetDetail.getRevaluationReserve());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getReservesAndSurplus()))
			balanceSheetDetail.setReservesAndSurplus(0.0);
		financialInputRequest.setOtherReserveAndSurplusTy(balanceSheetDetail.getReservesAndSurplus() - balanceSheetDetail.getRevaluationReserve());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getMinorityInterest()))
			balanceSheetDetail.setMinorityInterest(0.0);
		financialInputRequest.setMinorityInterestTy(balanceSheetDetail.getMinorityInterest());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTermLoansSecured()))
			balanceSheetDetail.setTermLoansSecured(0.0);
		financialInputRequest.setSecuredLoansTy(balanceSheetDetail.getTermLoansSecured());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTermLoansUnsecured()))
			balanceSheetDetail.setTermLoansUnsecured(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getUnsecuredLoansFromOthers()))
			balanceSheetDetail.setUnsecuredLoansFromOthers(0.0);
		financialInputRequest.setUnsecuredLoansOthersTy(balanceSheetDetail.getTermLoansUnsecured() + balanceSheetDetail.getUnsecuredLoansFromOthers());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getShortTermBorrowings()))
			balanceSheetDetail.setShortTermBorrowings(0.0);
		financialInputRequest.setOtherBorrowingTy(balanceSheetDetail.getShortTermBorrowings());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getUnsecuredLoansFromPromoters()))
			balanceSheetDetail.setUnsecuredLoansFromPromoters(0.0);
		financialInputRequest.setUnsecuredLoansPromotersTy(balanceSheetDetail.getUnsecuredLoansFromPromoters());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDeferredTaxLiability()))
			balanceSheetDetail.setDeferredTaxLiability(0.0);
		financialInputRequest.setDeferredTaxLiablitiesTy(balanceSheetDetail.getDeferredTaxLiability());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDeferredPaymentCredits()))
			balanceSheetDetail.setDeferredPaymentCredits(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTermDeposits()))
			balanceSheetDetail.setTermDeposits(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOthers())) // ------ others 
			balanceSheetDetail.setOthers(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDebentures()))
			balanceSheetDetail.setDebentures(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getUnsecuredLoansFromOthers()))
			balanceSheetDetail.setUnsecuredLoansFromOthers(0.0);
		financialInputRequest.setOtherLongTermLiablitiesTy((balanceSheetDetail.getDeferredPaymentCredits() + balanceSheetDetail.getTermDeposits() + balanceSheetDetail.getOthers() + balanceSheetDetail.getDebentures()) - balanceSheetDetail.getUnsecuredLoansFromOthers()); // ------ others
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getLongTermProvisions()))
			balanceSheetDetail.setLongTermProvisions(0.0);
		financialInputRequest.setLongTermProvisionTy(balanceSheetDetail.getLongTermProvisions());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTradePayables()))
			balanceSheetDetail.setTradePayables(0.0);
		financialInputRequest.setTradePayablesTy(balanceSheetDetail.getTradePayables());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getAdvanceFromCustomers()))
			balanceSheetDetail.setAdvanceFromCustomers(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDividendPayable()))
			balanceSheetDetail.setDividendPayable(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getStatutoryLiabilityDues()))
			balanceSheetDetail.setStatutoryLiabilityDues(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDepositsAndInstallments()))
			balanceSheetDetail.setDepositsAndInstallments(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOthersPlsSpecify())) // ------ others pls specify
			balanceSheetDetail.setOthersPlsSpecify(0.0);
		financialInputRequest.setOtherCurruntLiablitiesTy(balanceSheetDetail.getAdvanceFromCustomers() + balanceSheetDetail.getDividendPayable() + balanceSheetDetail.getStatutoryLiabilityDues() + balanceSheetDetail.getDepositsAndInstallments() + balanceSheetDetail.getOthersPlsSpecify());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getProvisionForTax()))
			balanceSheetDetail.setProvisionForTax(0.0);
		financialInputRequest.setShortTermProvisionTy(balanceSheetDetail.getProvisionForTax());
		
		//========= ==========================================ASSET(BS) DETAIL 3 YR========================================================
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getGrossFixedAssets()))
			balanceSheetDetail.setGrossFixedAssets(0.0);
		financialInputRequest.setGrossBlockTy(balanceSheetDetail.getGrossFixedAssets());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDepreciationToDate()))
			balanceSheetDetail.setDepreciationToDate(0.0);
		financialInputRequest.setLessAccumulatedDepreTy(balanceSheetDetail.getDepreciationToDate());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getImpairmentsOfAssests()))
			balanceSheetDetail.setImpairmentsOfAssests(0.0);
		financialInputRequest.setImpairmentofAssetTy(balanceSheetDetail.getImpairmentsOfAssests());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCapitalWorkInProgress()))
			balanceSheetDetail.setCapitalWorkInProgress(0.0);
		financialInputRequest.setCapitalWorkInProgressTy(balanceSheetDetail.getCapitalWorkInProgress());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getIntangibleAssets()))
			balanceSheetDetail.setIntangibleAssets(0.0);
		financialInputRequest.setIntengibleAssetsTy(balanceSheetDetail.getIntangibleAssets());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getPreOperativeExpensesPending()))
			balanceSheetDetail.setPreOperativeExpensesPending(0.0);
		financialInputRequest.setPreOperativeExpeTy(balanceSheetDetail.getPreOperativeExpensesPending());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getAssetsInTransit()))
			balanceSheetDetail.setAssetsInTransit(0.0);
		financialInputRequest.setAssetInTransitTy(balanceSheetDetail.getAssetsInTransit());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getInvestmentInSubsidiaries()))
			balanceSheetDetail.setInvestmentInSubsidiaries(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getInvestmentInAssociates()))
			balanceSheetDetail.setInvestmentInAssociates(0.0);
		financialInputRequest.setInvestmentInSubsidiariesTy(balanceSheetDetail.getInvestmentInSubsidiaries() + balanceSheetDetail.getInvestmentInAssociates());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getInvestmentInQuoted()))
			balanceSheetDetail.setInvestmentInQuoted(0.0);
		financialInputRequest.setOtherInvestmentTy(balanceSheetDetail.getInvestmentInQuoted());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getLongTermLoansAndAdvance()))
			balanceSheetDetail.setLongTermLoansAndAdvance(0.0);
		financialInputRequest.setLongTermLoansAndAdvaTy(balanceSheetDetail.getLongTermLoansAndAdvance());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCapitalAdvance()))
			balanceSheetDetail.setCapitalAdvance(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOthersTotals())) // ------ others totals
			balanceSheetDetail.setOthersTotals(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getPreOperativeExpensesPending()))
			balanceSheetDetail.setPreOperativeExpensesPending(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getAssetsInTransit()))
			balanceSheetDetail.setAssetsInTransit(0.0);
		financialInputRequest.setOtheNonCurruntAssetTy((balanceSheetDetail.getCapitalAdvance() + balanceSheetDetail.getOthersTotals()) - balanceSheetDetail.getPreOperativeExpensesPending() - balanceSheetDetail.getAssetsInTransit());// ------ others pls specify
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getInventory()))
			balanceSheetDetail.setInventory(0.0);
		financialInputRequest.setInventoriesTy(balanceSheetDetail.getInventory());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTradeReceivables()))
			balanceSheetDetail.setTradeReceivables(0.0);
		financialInputRequest.setSundryDebtorsTy(balanceSheetDetail.getTradeReceivables());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCashAndCashEquivalents()))
			balanceSheetDetail.setCashAndCashEquivalents(0.0);
		financialInputRequest.setCashAndBankTy(balanceSheetDetail.getCashAndCashEquivalents());
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCurrentInvestments()))
			balanceSheetDetail.setCurrentInvestments(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDeferredTaxAsset()))
			balanceSheetDetail.setDeferredTaxAsset(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getMiscExpences()))
			balanceSheetDetail.setMiscExpences(0.0);
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOtherDetails()))// ------ others details
			balanceSheetDetail.setOtherDetails(0.0);
		financialInputRequest.setOtherCurruntAssetTy(balanceSheetDetail.getCurrentInvestments() + balanceSheetDetail.getDeferredTaxAsset() + balanceSheetDetail.getMiscExpences() + balanceSheetDetail.getOtherDetails());// ------ others pls specify
		
		if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCashAndCashEquivalents()))
			balanceSheetDetail.setCashAndCashEquivalents(0.0);
		financialInputRequest.setShortTermLoansAdvancesTy(balanceSheetDetail.getShortTermLoansAndAdvances());
		// -----CONTIGENT LIABILITIES				
		financialInputRequest.setContingentLiablitiestTy(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest) ? 0.0 : pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability());
		
		// ----------------------------------------SECOND YEAR DATA---------------------------------------------------------------------------------------
				//========= ==========================================PROFITIBILITYSTATEMENTDETAIL DETAIL 2 YR========================================================
				profitibilityStatementDetail = profitibilityStatementDetailRepository.getProfitibilityStatementDetail(aplicationId, currentYear-1+"");
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getSales()))
					profitibilityStatementDetail.setSales(0.0);
				financialInputRequest.setGrossSalesSy(profitibilityStatementDetail.getSales());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getLessExciseDutyOrVatOrServiceTax()))
					profitibilityStatementDetail.setLessExciseDutyOrVatOrServiceTax(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getLessAnyOtherItem()))
					profitibilityStatementDetail.setLessAnyOtherItem(0.0);
				financialInputRequest.setLessExciseDuitySy(profitibilityStatementDetail.getLessExciseDutyOrVatOrServiceTax() + profitibilityStatementDetail.getLessAnyOtherItem());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getIncreaseOrDecreaseInInventoryFg()))
					profitibilityStatementDetail.setIncreaseOrDecreaseInInventoryFg(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getIncreaseOrDecreaseInInventoryWip()))
					profitibilityStatementDetail.setIncreaseOrDecreaseInInventoryWip(0.0);
				financialInputRequest.setIncreaseDecreaseStockSy(profitibilityStatementDetail.getIncreaseOrDecreaseInInventoryFg() + profitibilityStatementDetail.getIncreaseOrDecreaseInInventoryWip());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getCostRawMaterialConsumed()))
					profitibilityStatementDetail.setCostRawMaterialConsumed(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getPurchasesStockTnTrade()))
					profitibilityStatementDetail.setPurchasesStockTnTrade(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getStoreAndSpares()))
					profitibilityStatementDetail.setStoreAndSpares(0.0);
				financialInputRequest.setRawMaterialConsumedSy(profitibilityStatementDetail.getCostRawMaterialConsumed() + profitibilityStatementDetail.getPurchasesStockTnTrade()  + profitibilityStatementDetail.getStoreAndSpares());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getPowerAndFuel()))
					profitibilityStatementDetail.setPowerAndFuel(0.0);
				financialInputRequest.setPowerAndFuelCostSy(profitibilityStatementDetail.getPowerAndFuel());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getEmployeeBenefitExpenses()))
					profitibilityStatementDetail.setEmployeeBenefitExpenses(0.0);
				financialInputRequest.setEmployeeCostSy(profitibilityStatementDetail.getEmployeeBenefitExpenses());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getGeneralAdminExpenses()))
					profitibilityStatementDetail.setGeneralAdminExpenses(0.0);
				financialInputRequest.setGeneralAndAdminExpeSy(profitibilityStatementDetail.getGeneralAdminExpenses());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getSellingDistributionExpenses()))
					profitibilityStatementDetail.setSellingDistributionExpenses(0.0);
				financialInputRequest.setSellingAndDistriExpeSy(profitibilityStatementDetail.getSellingDistributionExpenses());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getExpensesCapitalised()))
					profitibilityStatementDetail.setExpensesCapitalised(0.0);
				financialInputRequest.setLessExpeCapitaSy(profitibilityStatementDetail.getExpensesCapitalised());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getOtherPlsSpecify())) // ------ others pls specify
					profitibilityStatementDetail.setOtherPlsSpecify(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getExpensesCapitalised()))
					profitibilityStatementDetail.setExpensesCapitalised(0.0);
				financialInputRequest.setMiscelExpeSy(profitibilityStatementDetail.getOtherPlsSpecify() - profitibilityStatementDetail.getExpensesCapitalised()); //----------------others pls specify field
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getOtherOperatingRevenue()))
					profitibilityStatementDetail.setOtherOperatingRevenue(0.0);
				financialInputRequest.setOtherIncomeSy(profitibilityStatementDetail.getOtherOperatingRevenue());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getFinanceCost()))
					profitibilityStatementDetail.setFinanceCost(0.0);
				financialInputRequest.setInterestSy(profitibilityStatementDetail.getFinanceCost());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getDepreciationAndAmortisation()))
					profitibilityStatementDetail.setDepreciationAndAmortisation(0.0);
				financialInputRequest.setDepriciationSy(profitibilityStatementDetail.getDepreciationAndAmortisation());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getNonOperatingIncome()))
					profitibilityStatementDetail.setNonOperatingIncome(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getNonOperatingExpenses()))
					profitibilityStatementDetail.setNonOperatingExpenses(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getExtraordinaryItems()))
					profitibilityStatementDetail.setExtraordinaryItems(0.0);
				financialInputRequest.setExceptionalIncomeSy(profitibilityStatementDetail.getNonOperatingIncome() + profitibilityStatementDetail.getNonOperatingExpenses() + profitibilityStatementDetail.getExtraordinaryItems());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getProvisionForTax()))
					profitibilityStatementDetail.setProvisionForTax(0.0);
				financialInputRequest.setProvisionForTaxSy(profitibilityStatementDetail.getProvisionForTax());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getDividend()))
					profitibilityStatementDetail.setDividend(0.0);
				financialInputRequest.setDividendPayOutSy(profitibilityStatementDetail.getDividend());
				//========= ==========================================LIABILITY(BS) DETAIL 2 YR========================================================
				balanceSheetDetail = balanceSheetDetailRepository.getBalanceSheetDetail(aplicationId, currentYear-1+"");
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOrdinaryShareCapital()))
					balanceSheetDetail.setOrdinaryShareCapital(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getPreferenceShareCapital()))
					balanceSheetDetail.setPreferenceShareCapital(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getShareApplicationPendingAllotment()))
					balanceSheetDetail.setShareApplicationPendingAllotment(0.0);
				financialInputRequest.setShareCapitalSy(balanceSheetDetail.getOrdinaryShareCapital() + balanceSheetDetail.getPreferenceShareCapital() + balanceSheetDetail.getShareApplicationPendingAllotment());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getMoneyReceivedAgainstShareWarrants()))
					balanceSheetDetail.setMoneyReceivedAgainstShareWarrants(0.0);
				financialInputRequest.setShareWarrantOutstandingsSy(balanceSheetDetail.getMoneyReceivedAgainstShareWarrants());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getRevaluationReserve()))
					balanceSheetDetail.setRevaluationReserve(0.0);
				financialInputRequest.setRevalationReserveSy(balanceSheetDetail.getRevaluationReserve());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getReservesAndSurplus()))
					balanceSheetDetail.setReservesAndSurplus(0.0);
				financialInputRequest.setOtherReserveAndSurplusSy(balanceSheetDetail.getReservesAndSurplus() - balanceSheetDetail.getRevaluationReserve());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getMinorityInterest()))
					balanceSheetDetail.setMinorityInterest(0.0);
				financialInputRequest.setMinorityInterestSy(balanceSheetDetail.getMinorityInterest());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTermLoansSecured()))
					balanceSheetDetail.setTermLoansSecured(0.0);
				financialInputRequest.setSecuredLoansSy(balanceSheetDetail.getTermLoansSecured());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTermLoansUnsecured()))
					balanceSheetDetail.setTermLoansUnsecured(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getUnsecuredLoansFromOthers()))
					balanceSheetDetail.setUnsecuredLoansFromOthers(0.0);
				financialInputRequest.setUnsecuredLoansOthersSy(balanceSheetDetail.getTermLoansUnsecured() + balanceSheetDetail.getUnsecuredLoansFromOthers());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getShortTermBorrowings()))
					balanceSheetDetail.setShortTermBorrowings(0.0);
				financialInputRequest.setOtherBorrowingSy(balanceSheetDetail.getShortTermBorrowings());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getUnsecuredLoansFromPromoters()))
					balanceSheetDetail.setUnsecuredLoansFromPromoters(0.0);
				financialInputRequest.setUnsecuredLoansPromotersSy(balanceSheetDetail.getUnsecuredLoansFromPromoters());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDeferredTaxLiability()))
					balanceSheetDetail.setDeferredTaxLiability(0.0);
				financialInputRequest.setDeferredTaxLiablitiesSy(balanceSheetDetail.getDeferredTaxLiability());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDeferredPaymentCredits()))
					balanceSheetDetail.setDeferredPaymentCredits(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTermDeposits()))
					balanceSheetDetail.setTermDeposits(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOthers())) // ------ others 
					balanceSheetDetail.setOthers(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDebentures()))
					balanceSheetDetail.setDebentures(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getUnsecuredLoansFromOthers()))
					balanceSheetDetail.setUnsecuredLoansFromOthers(0.0);
				financialInputRequest.setOtherLongTermLiablitiesSy((balanceSheetDetail.getDeferredPaymentCredits() + balanceSheetDetail.getTermDeposits() + balanceSheetDetail.getOthers() + balanceSheetDetail.getDebentures()) - balanceSheetDetail.getUnsecuredLoansFromOthers()); // ------ others
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getLongTermProvisions()))
					balanceSheetDetail.setLongTermProvisions(0.0);
				financialInputRequest.setLongTermProvisionSy(balanceSheetDetail.getLongTermProvisions());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTradePayables()))
					balanceSheetDetail.setTradePayables(0.0);
				financialInputRequest.setTradePayablesSy(balanceSheetDetail.getTradePayables());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getAdvanceFromCustomers()))
					balanceSheetDetail.setAdvanceFromCustomers(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDividendPayable()))
					balanceSheetDetail.setDividendPayable(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getStatutoryLiabilityDues()))
					balanceSheetDetail.setStatutoryLiabilityDues(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDepositsAndInstallments()))
					balanceSheetDetail.setDepositsAndInstallments(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOthersPlsSpecify())) // ------ others pls specify
					balanceSheetDetail.setOthersPlsSpecify(0.0);
				financialInputRequest.setOtherCurruntLiablitiesSy(balanceSheetDetail.getAdvanceFromCustomers() + balanceSheetDetail.getDividendPayable() + balanceSheetDetail.getStatutoryLiabilityDues() + balanceSheetDetail.getDepositsAndInstallments() + balanceSheetDetail.getOthersPlsSpecify());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getProvisionForTax()))
					balanceSheetDetail.setProvisionForTax(0.0);
				financialInputRequest.setShortTermProvisionSy(balanceSheetDetail.getProvisionForTax());
				
				//========= ==========================================ASSET(BS) DETAIL 2 YR========================================================
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getGrossFixedAssets()))
					balanceSheetDetail.setGrossFixedAssets(0.0);
				financialInputRequest.setGrossBlockSy(balanceSheetDetail.getGrossFixedAssets());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDepreciationToDate()))
					balanceSheetDetail.setDepreciationToDate(0.0);
				financialInputRequest.setLessAccumulatedDepreSy(balanceSheetDetail.getDepreciationToDate());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getImpairmentsOfAssests()))
					balanceSheetDetail.setImpairmentsOfAssests(0.0);
				financialInputRequest.setImpairmentofAssetSy(balanceSheetDetail.getImpairmentsOfAssests());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCapitalWorkInProgress()))
					balanceSheetDetail.setCapitalWorkInProgress(0.0);
				financialInputRequest.setCapitalWorkInProgressSy(balanceSheetDetail.getCapitalWorkInProgress());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getIntangibleAssets()))
					balanceSheetDetail.setIntangibleAssets(0.0);
				financialInputRequest.setIntengibleAssetsSy(balanceSheetDetail.getIntangibleAssets());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getPreOperativeExpensesPending()))
					balanceSheetDetail.setPreOperativeExpensesPending(0.0);
				financialInputRequest.setPreOperativeExpeSy(balanceSheetDetail.getPreOperativeExpensesPending());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getAssetsInTransit()))
					balanceSheetDetail.setAssetsInTransit(0.0);
				financialInputRequest.setAssetInTransitSy(balanceSheetDetail.getAssetsInTransit());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getInvestmentInSubsidiaries()))
					balanceSheetDetail.setInvestmentInSubsidiaries(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getInvestmentInAssociates()))
					balanceSheetDetail.setInvestmentInAssociates(0.0);
				financialInputRequest.setInvestmentInSubsidiariesSy(balanceSheetDetail.getInvestmentInSubsidiaries() + balanceSheetDetail.getInvestmentInAssociates());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getInvestmentInQuoted()))
					balanceSheetDetail.setInvestmentInQuoted(0.0);
				financialInputRequest.setOtherInvestmentSy(balanceSheetDetail.getInvestmentInQuoted());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getLongTermLoansAndAdvance()))
					balanceSheetDetail.setLongTermLoansAndAdvance(0.0);
				financialInputRequest.setLongTermLoansAndAdvaSy(balanceSheetDetail.getLongTermLoansAndAdvance());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCapitalAdvance()))
					balanceSheetDetail.setCapitalAdvance(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOthersTotals())) // ------ others totals
					balanceSheetDetail.setOthersTotals(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getPreOperativeExpensesPending()))
					balanceSheetDetail.setPreOperativeExpensesPending(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getAssetsInTransit()))
					balanceSheetDetail.setAssetsInTransit(0.0);
				financialInputRequest.setOtheNonCurruntAssetSy((balanceSheetDetail.getCapitalAdvance() + balanceSheetDetail.getOthersTotals()) - balanceSheetDetail.getPreOperativeExpensesPending() - balanceSheetDetail.getAssetsInTransit());// ------ others totals
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getInventory()))
					balanceSheetDetail.setInventory(0.0);
				financialInputRequest.setInventoriesSy(balanceSheetDetail.getInventory());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTradeReceivables()))
					balanceSheetDetail.setTradeReceivables(0.0);
				financialInputRequest.setSundryDebtorsSy(balanceSheetDetail.getTradeReceivables());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCashAndCashEquivalents()))
					balanceSheetDetail.setCashAndCashEquivalents(0.0);
				financialInputRequest.setCashAndBankSy(balanceSheetDetail.getCashAndCashEquivalents());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCurrentInvestments()))
					balanceSheetDetail.setCurrentInvestments(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDeferredTaxAsset()))
					balanceSheetDetail.setDeferredTaxAsset(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getMiscExpences()))
					balanceSheetDetail.setMiscExpences(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOtherDetails()))// ------ others details
					balanceSheetDetail.setOtherDetails(0.0);
				financialInputRequest.setOtherCurruntAssetSy(balanceSheetDetail.getCurrentInvestments() + balanceSheetDetail.getDeferredTaxAsset() + balanceSheetDetail.getMiscExpences() + balanceSheetDetail.getOtherDetails());// ------ others details
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCashAndCashEquivalents()))
					balanceSheetDetail.setCashAndCashEquivalents(0.0);
				financialInputRequest.setShortTermLoansAdvancesSy(balanceSheetDetail.getShortTermLoansAndAdvances());
				// -----CONTIGENT LIABILITIES				
				financialInputRequest.setContingentLiablitiesSy(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest) ? 0.0 : pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-2).getContingentLiability());
				
				// ----------------------------------------FIRST YEAR DATA---------------------------------------------------------------------------------------
				//========= ==========================================PROFITIBILITYSTATEMENTDETAIL DETAIL 1 YR========================================================
				profitibilityStatementDetail = profitibilityStatementDetailRepository.getProfitibilityStatementDetail(aplicationId, currentYear-2+"");
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getSales()))
					profitibilityStatementDetail.setSales(0.0);
				financialInputRequest.setGrossSalesFy(profitibilityStatementDetail.getSales());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getLessExciseDutyOrVatOrServiceTax()))
					profitibilityStatementDetail.setLessExciseDutyOrVatOrServiceTax(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getLessAnyOtherItem()))
					profitibilityStatementDetail.setLessAnyOtherItem(0.0);
				financialInputRequest.setLessExciseDuityFy(profitibilityStatementDetail.getLessExciseDutyOrVatOrServiceTax() + profitibilityStatementDetail.getLessAnyOtherItem());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getIncreaseOrDecreaseInInventoryFg()))
					profitibilityStatementDetail.setIncreaseOrDecreaseInInventoryFg(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getIncreaseOrDecreaseInInventoryWip()))
					profitibilityStatementDetail.setIncreaseOrDecreaseInInventoryWip(0.0);
				financialInputRequest.setIncreaseDecreaseStockFy(profitibilityStatementDetail.getIncreaseOrDecreaseInInventoryFg() + profitibilityStatementDetail.getIncreaseOrDecreaseInInventoryWip());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getCostRawMaterialConsumed()))
					profitibilityStatementDetail.setCostRawMaterialConsumed(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getPurchasesStockTnTrade()))
					profitibilityStatementDetail.setPurchasesStockTnTrade(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getStoreAndSpares()))
					profitibilityStatementDetail.setStoreAndSpares(0.0);
				financialInputRequest.setRawMaterialConsumedFy(profitibilityStatementDetail.getCostRawMaterialConsumed() + profitibilityStatementDetail.getPurchasesStockTnTrade()  + profitibilityStatementDetail.getStoreAndSpares());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getPowerAndFuel()))
					profitibilityStatementDetail.setPowerAndFuel(0.0);
				financialInputRequest.setPowerAndFuelCostFy(profitibilityStatementDetail.getPowerAndFuel());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getEmployeeBenefitExpenses()))
					profitibilityStatementDetail.setEmployeeBenefitExpenses(0.0);
				financialInputRequest.setEmployeeCostFy(profitibilityStatementDetail.getEmployeeBenefitExpenses());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getGeneralAdminExpenses()))
					profitibilityStatementDetail.setGeneralAdminExpenses(0.0);
				financialInputRequest.setGeneralAndAdminExpeFy(profitibilityStatementDetail.getGeneralAdminExpenses());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getSellingDistributionExpenses()))
					profitibilityStatementDetail.setSellingDistributionExpenses(0.0);
				financialInputRequest.setSellingAndDistriExpeFy(profitibilityStatementDetail.getSellingDistributionExpenses());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getExpensesCapitalised()))
					profitibilityStatementDetail.setExpensesCapitalised(0.0);
				financialInputRequest.setLessExpeCapitaFy(profitibilityStatementDetail.getExpensesCapitalised());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getOtherPlsSpecify())) // ------ others pls specify
					profitibilityStatementDetail.setOtherPlsSpecify(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getExpensesCapitalised()))
					profitibilityStatementDetail.setExpensesCapitalised(0.0);
				financialInputRequest.setMiscelExpeFy(profitibilityStatementDetail.getOtherPlsSpecify() - profitibilityStatementDetail.getExpensesCapitalised()); //----------------others pls specify field
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getOtherOperatingRevenue()))
					profitibilityStatementDetail.setOtherOperatingRevenue(0.0);
				financialInputRequest.setOtherIncomeFy(profitibilityStatementDetail.getOtherOperatingRevenue());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getFinanceCost()))
					profitibilityStatementDetail.setFinanceCost(0.0);
				financialInputRequest.setInterestFy(profitibilityStatementDetail.getFinanceCost());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getDepreciationAndAmortisation()))
					profitibilityStatementDetail.setDepreciationAndAmortisation(0.0);
				financialInputRequest.setDepriciationFy(profitibilityStatementDetail.getDepreciationAndAmortisation());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getNonOperatingIncome()))
					profitibilityStatementDetail.setNonOperatingIncome(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getNonOperatingExpenses()))
					profitibilityStatementDetail.setNonOperatingExpenses(0.0);
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getExtraordinaryItems()))
					profitibilityStatementDetail.setExtraordinaryItems(0.0);
				financialInputRequest.setExceptionalIncomeFy(profitibilityStatementDetail.getNonOperatingIncome() + profitibilityStatementDetail.getNonOperatingExpenses() + profitibilityStatementDetail.getExtraordinaryItems());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getProvisionForTax()))
					profitibilityStatementDetail.setProvisionForTax(0.0);
				financialInputRequest.setProvisionForTaxFy(profitibilityStatementDetail.getProvisionForTax());
				
				if(CommonUtils.isObjectNullOrEmpty(profitibilityStatementDetail.getDividend()))
					profitibilityStatementDetail.setDividend(0.0);
				financialInputRequest.setDividendPayOutFy(profitibilityStatementDetail.getDividend());
				//========= ==========================================LIABILITY(BS) DETAIL 1 YR========================================================
				balanceSheetDetail = balanceSheetDetailRepository.getBalanceSheetDetail(aplicationId, currentYear-2+"");
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOrdinaryShareCapital()))
					balanceSheetDetail.setOrdinaryShareCapital(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getPreferenceShareCapital()))
					balanceSheetDetail.setPreferenceShareCapital(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getShareApplicationPendingAllotment()))
					balanceSheetDetail.setShareApplicationPendingAllotment(0.0);
				financialInputRequest.setShareCapitalFy(balanceSheetDetail.getOrdinaryShareCapital() + balanceSheetDetail.getPreferenceShareCapital() + balanceSheetDetail.getShareApplicationPendingAllotment());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getMoneyReceivedAgainstShareWarrants()))
					balanceSheetDetail.setMoneyReceivedAgainstShareWarrants(0.0);
				financialInputRequest.setShareWarrantOutstandingsFy(balanceSheetDetail.getMoneyReceivedAgainstShareWarrants());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getRevaluationReserve()))
					balanceSheetDetail.setRevaluationReserve(0.0);
				financialInputRequest.setRevalationReserveFy(balanceSheetDetail.getRevaluationReserve());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getReservesAndSurplus()))
					balanceSheetDetail.setReservesAndSurplus(0.0);
				financialInputRequest.setOtherReserveAndSurplusFy(balanceSheetDetail.getReservesAndSurplus() - balanceSheetDetail.getRevaluationReserve());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getMinorityInterest()))
					balanceSheetDetail.setMinorityInterest(0.0);
				financialInputRequest.setMinorityInterestFy(balanceSheetDetail.getMinorityInterest());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTermLoansSecured()))
					balanceSheetDetail.setTermLoansSecured(0.0);
				financialInputRequest.setSecuredLoansFy(balanceSheetDetail.getTermLoansSecured());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTermLoansUnsecured()))
					balanceSheetDetail.setTermLoansUnsecured(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getUnsecuredLoansFromOthers()))
					balanceSheetDetail.setUnsecuredLoansFromOthers(0.0);
				financialInputRequest.setUnsecuredLoansOthersFy(balanceSheetDetail.getTermLoansUnsecured() + balanceSheetDetail.getUnsecuredLoansFromOthers());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getShortTermBorrowings()))
					balanceSheetDetail.setShortTermBorrowings(0.0);
				financialInputRequest.setOtherBorrowingFy(balanceSheetDetail.getShortTermBorrowings());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getUnsecuredLoansFromPromoters()))
					balanceSheetDetail.setUnsecuredLoansFromPromoters(0.0);
				financialInputRequest.setUnsecuredLoansPromotersFy(balanceSheetDetail.getUnsecuredLoansFromPromoters());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDeferredTaxLiability()))
					balanceSheetDetail.setDeferredTaxLiability(0.0);
				financialInputRequest.setDeferredTaxLiablitiesFy(balanceSheetDetail.getDeferredTaxLiability());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDeferredPaymentCredits()))
					balanceSheetDetail.setDeferredPaymentCredits(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTermDeposits()))
					balanceSheetDetail.setTermDeposits(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOthers())) // ------ others
					balanceSheetDetail.setOthers(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDebentures()))
					balanceSheetDetail.setDebentures(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getUnsecuredLoansFromOthers()))
					balanceSheetDetail.setUnsecuredLoansFromOthers(0.0);
				financialInputRequest.setOtherLongTermLiablitiesFy((balanceSheetDetail.getDeferredPaymentCredits() + balanceSheetDetail.getTermDeposits() + balanceSheetDetail.getOthers() + balanceSheetDetail.getDebentures()) - balanceSheetDetail.getUnsecuredLoansFromOthers()); // ------ others 
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getLongTermProvisions()))
					balanceSheetDetail.setLongTermProvisions(0.0);
				financialInputRequest.setLongTermProvisionFy(balanceSheetDetail.getLongTermProvisions());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTradePayables()))
					balanceSheetDetail.setTradePayables(0.0);
				financialInputRequest.setTradePayablesFy(balanceSheetDetail.getTradePayables());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getAdvanceFromCustomers()))
					balanceSheetDetail.setAdvanceFromCustomers(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDividendPayable()))
					balanceSheetDetail.setDividendPayable(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getStatutoryLiabilityDues()))
					balanceSheetDetail.setStatutoryLiabilityDues(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDepositsAndInstallments()))
					balanceSheetDetail.setDepositsAndInstallments(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOthersPlsSpecify())) // ------ others
					balanceSheetDetail.setOthersPlsSpecify(0.0);
				financialInputRequest.setOtherCurruntLiablitiesFy(balanceSheetDetail.getAdvanceFromCustomers() + balanceSheetDetail.getDividendPayable() + balanceSheetDetail.getStatutoryLiabilityDues() + balanceSheetDetail.getDepositsAndInstallments() +balanceSheetDetail.getOthersPlsSpecify());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getProvisionForTax()))
					balanceSheetDetail.setProvisionForTax(0.0);
				financialInputRequest.setShortTermProvisionFy(balanceSheetDetail.getProvisionForTax());
				
				//========= ==========================================ASSET(BS) DETAIL 1 YR========================================================
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getGrossFixedAssets()))
					balanceSheetDetail.setGrossFixedAssets(0.0);
				financialInputRequest.setGrossBlockFy(balanceSheetDetail.getGrossFixedAssets());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDepreciationToDate()))
					balanceSheetDetail.setDepreciationToDate(0.0);
				financialInputRequest.setLessAccumulatedDepreFy(balanceSheetDetail.getDepreciationToDate());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getImpairmentsOfAssests()))
					balanceSheetDetail.setImpairmentsOfAssests(0.0);
				financialInputRequest.setImpairmentofAssetFy(balanceSheetDetail.getImpairmentsOfAssests());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCapitalWorkInProgress()))
					balanceSheetDetail.setCapitalWorkInProgress(0.0);
				financialInputRequest.setCapitalWorkInProgressFy(balanceSheetDetail.getCapitalWorkInProgress());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getIntangibleAssets()))
					balanceSheetDetail.setIntangibleAssets(0.0);
				financialInputRequest.setIntengibleAssetsFy(balanceSheetDetail.getIntangibleAssets());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getPreOperativeExpensesPending()))
					balanceSheetDetail.setPreOperativeExpensesPending(0.0);
				financialInputRequest.setPreOperativeExpeFy(balanceSheetDetail.getPreOperativeExpensesPending());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getAssetsInTransit()))
					balanceSheetDetail.setAssetsInTransit(0.0);
				financialInputRequest.setAssetInTransitFy(balanceSheetDetail.getAssetsInTransit());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getInvestmentInSubsidiaries()))
					balanceSheetDetail.setInvestmentInSubsidiaries(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getInvestmentInAssociates()))
					balanceSheetDetail.setInvestmentInAssociates(0.0);
				financialInputRequest.setInvestmentInSubsidiariesFy(balanceSheetDetail.getInvestmentInSubsidiaries() + balanceSheetDetail.getInvestmentInAssociates());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getInvestmentInQuoted()))
					balanceSheetDetail.setInvestmentInQuoted(0.0);
				financialInputRequest.setOtherInvestmentFy(balanceSheetDetail.getInvestmentInQuoted());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getLongTermLoansAndAdvance()))
					balanceSheetDetail.setLongTermLoansAndAdvance(0.0);
				financialInputRequest.setLongTermLoansAndAdvaFy(balanceSheetDetail.getLongTermLoansAndAdvance());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCapitalAdvance()))
					balanceSheetDetail.setCapitalAdvance(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOthersTotals())) // ------ others totals
					balanceSheetDetail.setOthersTotals(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getPreOperativeExpensesPending()))
					balanceSheetDetail.setPreOperativeExpensesPending(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getAssetsInTransit()))
					balanceSheetDetail.setAssetsInTransit(0.0);
				financialInputRequest.setOtheNonCurruntAssetFy((balanceSheetDetail.getCapitalAdvance() + balanceSheetDetail.getOthersTotals()) - balanceSheetDetail.getPreOperativeExpensesPending() - balanceSheetDetail.getAssetsInTransit());// ------ others totals
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getInventory()))
					balanceSheetDetail.setInventory(0.0);
				financialInputRequest.setInventoriesFy(balanceSheetDetail.getInventory());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getTradeReceivables()))
					balanceSheetDetail.setTradeReceivables(0.0);
				financialInputRequest.setSundryDebtorsFy(balanceSheetDetail.getTradeReceivables());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCashAndCashEquivalents()))
					balanceSheetDetail.setCashAndCashEquivalents(0.0);
				financialInputRequest.setCashAndBankFy(balanceSheetDetail.getCashAndCashEquivalents());
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getCurrentInvestments()))
					balanceSheetDetail.setCurrentInvestments(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getDeferredTaxAsset()))
					balanceSheetDetail.setDeferredTaxAsset(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getMiscExpences()))
					balanceSheetDetail.setMiscExpences(0.0);
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getOtherDetails()))// ------ others details
					balanceSheetDetail.setOtherDetails(0.0);
				financialInputRequest.setOtherCurruntAssetFy(balanceSheetDetail.getCurrentInvestments() + balanceSheetDetail.getDeferredTaxAsset() + balanceSheetDetail.getMiscExpences() + balanceSheetDetail.getOtherDetails());// ------ others details
				
				if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getShortTermLoansAndAdvances()))
					balanceSheetDetail.setShortTermLoansAndAdvances(0.0);
				financialInputRequest.setShortTermLoansAdvancesFy(balanceSheetDetail.getShortTermLoansAndAdvances());
				// -----CONTIGENT LIABILITIES				
				financialInputRequest.setContingentLiablitiesFy(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest) ? 0.0 : pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-3).getContingentLiability());
				
		// FinancialInput Object Set		
		return financialInputRequest;
	}

	
	
	
	
	@Override
	public QualitativeInputSheetManuRequest qualitativeInputServiceManu(Long aplicationId, Long userId, Integer productId, Boolean isCmaUploaded, Boolean isCoActUploaded,Double industryRiskScore)
			throws Exception {
		// TODO Auto-generated method stub
		QualitativeInputSheetManuRequest qualitativeInputSheetManuRequest = null;
		LoanType type = CommonUtils.LoanType.getType(productId);
		switch (type) {
		case WORKING_CAPITAL:
			// set 
			qualitativeInputSheetManuRequest = setWCManufacturingQualitativeInput(aplicationId,userId,industryRiskScore);
			break;
		case TERM_LOAN:
			qualitativeInputSheetManuRequest = setTLManufacturingQualitativeInput(aplicationId,userId,isCmaUploaded,isCoActUploaded,industryRiskScore);
			break;
		case UNSECURED_LOAN :
			qualitativeInputSheetManuRequest= setUSLManufacturingQualitativeInput(aplicationId,userId,industryRiskScore);
			break;
				
		}
		return qualitativeInputSheetManuRequest;
	}

	public QualitativeInputSheetManuRequest setWCManufacturingQualitativeInput(Long aplicationId,Long userId,Double industryRiskScore) throws Exception{
		QualitativeInputSheetManuRequest qualitativeInputSheetManuRequest = new QualitativeInputSheetManuRequest();
		List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequest = new ArrayList<PastFinancialEstimatesDetailRequest>();
		pastFinancialEstimatesDetailRequest=pastFinancialEstiamateDetailsService.getPastFinancialEstimateDetailsList(aplicationId);
		
		FinalWorkingCapitalLoanDetail finalWorkingCapitalLoanDetail = null; 
		finalWorkingCapitalLoanDetail = finalWorkingCapitalLoanDetailRepository.getByApplicationAndUserId(aplicationId,userId);
		
		qualitativeInputSheetManuRequest.setAccountingQuality(finalWorkingCapitalLoanDetail.getAccountingQuality().longValue());		
		qualitativeInputSheetManuRequest.setUnhedgedForeignCurrencyExposure(finalWorkingCapitalLoanDetail.getUnhedgedForeignCurrency().longValue());
		qualitativeInputSheetManuRequest.setFinancialRestructuringHistory(finalWorkingCapitalLoanDetail.getFinancialRestructuringHistory().longValue());
		qualitativeInputSheetManuRequest.setIndustryRiskScore(industryRiskScore); //-----Industry mapping -- Remaining
		qualitativeInputSheetManuRequest.setCustomerQuality(finalWorkingCapitalLoanDetail.getCustomerQuality().longValue());
		qualitativeInputSheetManuRequest.setSupplierQuality(finalWorkingCapitalLoanDetail.getSupplierQuality().longValue());
		qualitativeInputSheetManuRequest.setOrderBookPosition(finalWorkingCapitalLoanDetail.getOrderBookPosition().longValue());
		qualitativeInputSheetManuRequest.setIndustrialEmployeeRelations(finalWorkingCapitalLoanDetail.getEmployeeRelations().longValue());
		qualitativeInputSheetManuRequest.setIntegrity(finalWorkingCapitalLoanDetail.getIntegrity().longValue());
		qualitativeInputSheetManuRequest.setBusinessCommitment(finalWorkingCapitalLoanDetail.getBusinessCommitment().longValue());
		qualitativeInputSheetManuRequest.setManagementCompetence(finalWorkingCapitalLoanDetail.getManagementCompetence().longValue());
		qualitativeInputSheetManuRequest.setBusinessExperience(finalWorkingCapitalLoanDetail.getBusinessCommitment().longValue());
		qualitativeInputSheetManuRequest.setSuccsessionPlanning(finalWorkingCapitalLoanDetail.getSuccessionPlanning().longValue());
		qualitativeInputSheetManuRequest.setFinancialStrength(finalWorkingCapitalLoanDetail.getFinancialStrength().longValue());
		qualitativeInputSheetManuRequest.setAbilityToRaise(finalWorkingCapitalLoanDetail.getAbilityToRaiseFunds().longValue());
		qualitativeInputSheetManuRequest.setIntraCompanyConflicts(finalWorkingCapitalLoanDetail.getIntraCompany().longValue());		
		qualitativeInputSheetManuRequest.setStatusProjectClearances(finalWorkingCapitalLoanDetail.getStatusOfProjectClearances().longValue());
		qualitativeInputSheetManuRequest.setStatusFinancialClosure(finalWorkingCapitalLoanDetail.getStatusOfFinancialClosure().longValue());
		qualitativeInputSheetManuRequest.setProjectDebtService(finalWorkingCapitalLoanDetail.getProjectedDebtService().longValue());
		qualitativeInputSheetManuRequest.setInternalRateReturn(finalWorkingCapitalLoanDetail.getInternalRateReturn().longValue());
		qualitativeInputSheetManuRequest.setSensititivityAnalysis(finalWorkingCapitalLoanDetail.getSensititivityAnalysis().longValue());
		qualitativeInputSheetManuRequest.setInfrastructureAvailability(finalWorkingCapitalLoanDetail.getInfrastructureAvailability().longValue());
		qualitativeInputSheetManuRequest.setConstructionContract(finalWorkingCapitalLoanDetail.getConstructionContract().longValue());
		qualitativeInputSheetManuRequest.setDesignTechnologyRisk(finalWorkingCapitalLoanDetail.getTechnologyRiskId().longValue());
		qualitativeInputSheetManuRequest.setNumberCheckReturned(finalWorkingCapitalLoanDetail.getNumberOfCheques().longValue());
		qualitativeInputSheetManuRequest.setNumberTimesDpLimits(finalWorkingCapitalLoanDetail.getNumberOfTimesDp().longValue());
		qualitativeInputSheetManuRequest.setCumulativeDaysDpLimits(finalWorkingCapitalLoanDetail.getCumulativeNoOfDaysDp().longValue());
		qualitativeInputSheetManuRequest.setCompliancesWithSancationed(finalWorkingCapitalLoanDetail.getComplianceWithSanctioned().longValue());
		qualitativeInputSheetManuRequest.setSubmissionProgressReport(finalWorkingCapitalLoanDetail.getProgressReports().longValue());
		qualitativeInputSheetManuRequest.setDelayInReceiptPrincipal(finalWorkingCapitalLoanDetail.getDelayInReceipt().longValue());
		qualitativeInputSheetManuRequest.setDelayInSubmissionAudited(finalWorkingCapitalLoanDetail.getDelayInSubmission().longValue());
		qualitativeInputSheetManuRequest.setVarianceInProjectedSales(finalWorkingCapitalLoanDetail.getVarianceInProjectedSales().longValue());
		qualitativeInputSheetManuRequest.setNumberOfLcBgIssuedInFavor(finalWorkingCapitalLoanDetail.getNumberOfLc().longValue());	
		
		//---Contigent Liabilities set
		if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest))
			qualitativeInputSheetManuRequest.setContingentLiabilities(0.0);
		else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability()))
			qualitativeInputSheetManuRequest.setContingentLiabilities(0.0);
		else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth()))
			qualitativeInputSheetManuRequest.setContingentLiabilities(0.0);
		else
			qualitativeInputSheetManuRequest.setContingentLiabilities(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability() / pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth());//-----formula based
		//---project size 0.0 WC
		qualitativeInputSheetManuRequest.setProjectSize(0.0);//----- formula based
		
		return qualitativeInputSheetManuRequest;
	}
	
	public QualitativeInputSheetManuRequest setTLManufacturingQualitativeInput(Long aplicationId,Long userId, Boolean isCmaUploaded, Boolean isCoActUploaded,Double industryRiskScore) throws Exception{
		QualitativeInputSheetManuRequest qualitativeInputSheetManuRequest = new QualitativeInputSheetManuRequest();
		List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequest = new ArrayList<PastFinancialEstimatesDetailRequest>();
		pastFinancialEstimatesDetailRequest=pastFinancialEstiamateDetailsService.getPastFinancialEstimateDetailsList(aplicationId);
		
		FinalTermLoanDetail finalTermLoanDetail = null;
		finalTermLoanDetail = finalTermLoanDetailRepository.getByApplicationAndUserId(aplicationId,userId);
		
		qualitativeInputSheetManuRequest.setAccountingQuality(finalTermLoanDetail.getAccountingQuality().longValue());
		qualitativeInputSheetManuRequest.setUnhedgedForeignCurrencyExposure(finalTermLoanDetail.getUnhedgedForeignCurrency().longValue());
		qualitativeInputSheetManuRequest.setFinancialRestructuringHistory(finalTermLoanDetail.getFinancialRestructuringHistory().longValue());
		qualitativeInputSheetManuRequest.setIndustryRiskScore(industryRiskScore); //-----Industry mapping
		qualitativeInputSheetManuRequest.setCustomerQuality(finalTermLoanDetail.getCustomerQuality().longValue());
		qualitativeInputSheetManuRequest.setSupplierQuality(finalTermLoanDetail.getSupplierQuality().longValue());
		qualitativeInputSheetManuRequest.setOrderBookPosition(finalTermLoanDetail.getOrderBookPosition().longValue());
		qualitativeInputSheetManuRequest.setIndustrialEmployeeRelations(finalTermLoanDetail.getEmployeeRelations().longValue());
		qualitativeInputSheetManuRequest.setIntegrity(finalTermLoanDetail.getIntegrity().longValue());
		qualitativeInputSheetManuRequest.setBusinessCommitment(finalTermLoanDetail.getBusinessCommitment().longValue());
		qualitativeInputSheetManuRequest.setManagementCompetence(finalTermLoanDetail.getManagementCompetence().longValue());
		qualitativeInputSheetManuRequest.setBusinessExperience(finalTermLoanDetail.getBusinessCommitment().longValue());
		qualitativeInputSheetManuRequest.setSuccsessionPlanning(finalTermLoanDetail.getSuccessionPlanning().longValue());
		qualitativeInputSheetManuRequest.setFinancialStrength(finalTermLoanDetail.getFinancialStrength().longValue());
		qualitativeInputSheetManuRequest.setAbilityToRaise(finalTermLoanDetail.getAbilityToRaiseFunds().longValue());
		qualitativeInputSheetManuRequest.setIntraCompanyConflicts(finalTermLoanDetail.getIntraCompany().longValue());
		qualitativeInputSheetManuRequest.setStatusProjectClearances(finalTermLoanDetail.getStatusOfProjectClearances().longValue());
		qualitativeInputSheetManuRequest.setStatusFinancialClosure(finalTermLoanDetail.getStatusOfFinancialClosure().longValue());
		qualitativeInputSheetManuRequest.setProjectDebtService(finalTermLoanDetail.getProjectedDebtService().longValue());
		qualitativeInputSheetManuRequest.setInternalRateReturn(finalTermLoanDetail.getInternalRateReturn().longValue());
		qualitativeInputSheetManuRequest.setSensititivityAnalysis(finalTermLoanDetail.getSensititivityAnalysis().longValue());
		qualitativeInputSheetManuRequest.setInfrastructureAvailability(finalTermLoanDetail.getInfrastructureAvailability().longValue());
		qualitativeInputSheetManuRequest.setConstructionContract(finalTermLoanDetail.getConstructionContract().longValue());
		qualitativeInputSheetManuRequest.setDesignTechnologyRisk(finalTermLoanDetail.getTechnologyRiskId().longValue());
		qualitativeInputSheetManuRequest.setNumberCheckReturned(finalTermLoanDetail.getNumberOfCheques().longValue());
		qualitativeInputSheetManuRequest.setNumberTimesDpLimits(finalTermLoanDetail.getNumberOfTimesDp().longValue());
		qualitativeInputSheetManuRequest.setCumulativeDaysDpLimits(finalTermLoanDetail.getCumulativeNoOfDaysDp().longValue());
		qualitativeInputSheetManuRequest.setCompliancesWithSancationed(finalTermLoanDetail.getComplianceWithSanctioned().longValue());
		qualitativeInputSheetManuRequest.setSubmissionProgressReport(finalTermLoanDetail.getProgressReports().longValue());
		qualitativeInputSheetManuRequest.setDelayInReceiptPrincipal(finalTermLoanDetail.getDelayInReceipt().longValue());
		qualitativeInputSheetManuRequest.setDelayInSubmissionAudited(finalTermLoanDetail.getDelayInSubmission().longValue());
		qualitativeInputSheetManuRequest.setVarianceInProjectedSales(finalTermLoanDetail.getVarianceInProjectedSales().longValue());
		qualitativeInputSheetManuRequest.setNumberOfLcBgIssuedInFavor(finalTermLoanDetail.getNumberOfLc().longValue());		
		
		//---Contigent Liabilities set
			if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest))
				qualitativeInputSheetManuRequest.setContingentLiabilities(0.0);
			else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability()))
				qualitativeInputSheetManuRequest.setContingentLiabilities(0.0);
			else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth()))
				qualitativeInputSheetManuRequest.setContingentLiabilities(0.0);
			else
				qualitativeInputSheetManuRequest.setContingentLiabilities(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability() / pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth());//-----formula based
		//---project size TL
			PrimaryTermLoanDetail primaryTermLoanDetail = null;
			primaryTermLoanDetail = primaryTermLoanDetailRepository.findOne(aplicationId);			
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			
			if(isCmaUploaded){
				AssetsDetails assetsDetails = new AssetsDetails();
				assetsDetails = assetsDetailsRepository.getAssetsDetails(aplicationId, currentYear+"");
				if(CommonUtils.isObjectNullOrEmpty(primaryTermLoanDetail.getTotalCostOfEstimate()))
					qualitativeInputSheetManuRequest.setProjectSize(0.0);
				else if(CommonUtils.isObjectNullOrEmpty(assetsDetails.getTotalAssets()))
					qualitativeInputSheetManuRequest.setProjectSize(0.0);
				else
					qualitativeInputSheetManuRequest.setProjectSize((primaryTermLoanDetail.getTotalCostOfEstimate() / assetsDetails.getTotalAssets()) * 100);//----- formula based
			}else if(isCoActUploaded){
				BalanceSheetDetail balanceSheetDetail = new BalanceSheetDetail();
				balanceSheetDetail = balanceSheetDetailRepository.getBalanceSheetDetail(aplicationId, currentYear+"");
				if(CommonUtils.isObjectNullOrEmpty(primaryTermLoanDetail.getTotalCostOfEstimate()))
					qualitativeInputSheetManuRequest.setProjectSize(0.0);
				else if(CommonUtils.isObjectNullOrEmpty(balanceSheetDetail.getGrandTotal()))
					qualitativeInputSheetManuRequest.setProjectSize(0.0);
				else
					qualitativeInputSheetManuRequest.setProjectSize((primaryTermLoanDetail.getTotalCostOfEstimate() / balanceSheetDetail.getGrandTotal()) * 100);//----- formula based
			}
				
		return qualitativeInputSheetManuRequest;
	}
	
	public QualitativeInputSheetManuRequest setUSLManufacturingQualitativeInput(Long aplicationId,Long userId,Double industryRiskScore) throws Exception{
		QualitativeInputSheetManuRequest qualitativeInputSheetManuRequest = new QualitativeInputSheetManuRequest();
		List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequest = new ArrayList<PastFinancialEstimatesDetailRequest>();
		pastFinancialEstimatesDetailRequest=pastFinancialEstiamateDetailsService.getPastFinancialEstimateDetailsList(aplicationId);
		
		FinalUnsecureLoanDetail finalUnsecureLoanDetail = null;
		finalUnsecureLoanDetail = finalUnsecuredLoanDetailRepository.getByApplicationAndUserId(aplicationId,userId);
		
		qualitativeInputSheetManuRequest.setAccountingQuality(finalUnsecureLoanDetail.getAccountingQuality().longValue());
		qualitativeInputSheetManuRequest.setUnhedgedForeignCurrencyExposure(finalUnsecureLoanDetail.getUnhedgedForeignCurrency().longValue());
		qualitativeInputSheetManuRequest.setFinancialRestructuringHistory(finalUnsecureLoanDetail.getFinancialRestructuringHistory().longValue());
		qualitativeInputSheetManuRequest.setIndustryRiskScore(industryRiskScore); //-----Industry mapping-- Remaining
		qualitativeInputSheetManuRequest.setCustomerQuality(finalUnsecureLoanDetail.getCustomerQuality().longValue());
		qualitativeInputSheetManuRequest.setSupplierQuality(finalUnsecureLoanDetail.getSupplierQuality().longValue());
		qualitativeInputSheetManuRequest.setOrderBookPosition(finalUnsecureLoanDetail.getOrderBookPosition().longValue());
		qualitativeInputSheetManuRequest.setIndustrialEmployeeRelations(finalUnsecureLoanDetail.getEmployeeRelations().longValue());
		qualitativeInputSheetManuRequest.setIntegrity(finalUnsecureLoanDetail.getIntegrity().longValue());
		qualitativeInputSheetManuRequest.setBusinessCommitment(finalUnsecureLoanDetail.getBusinessCommitment().longValue());
		qualitativeInputSheetManuRequest.setManagementCompetence(finalUnsecureLoanDetail.getManagementCompetence().longValue());
		qualitativeInputSheetManuRequest.setBusinessExperience(finalUnsecureLoanDetail.getBusinessCommitment().longValue());
		qualitativeInputSheetManuRequest.setSuccsessionPlanning(finalUnsecureLoanDetail.getSuccessionPlanning().longValue());
		qualitativeInputSheetManuRequest.setFinancialStrength(finalUnsecureLoanDetail.getFinancialStrength().longValue());
		qualitativeInputSheetManuRequest.setAbilityToRaise(finalUnsecureLoanDetail.getAbilityToRaiseFunds().longValue());
		qualitativeInputSheetManuRequest.setIntraCompanyConflicts(finalUnsecureLoanDetail.getIntraCompany().longValue());
		qualitativeInputSheetManuRequest.setStatusProjectClearances(finalUnsecureLoanDetail.getStatusOfProjectClearances().longValue());
		qualitativeInputSheetManuRequest.setStatusFinancialClosure(finalUnsecureLoanDetail.getStatusOfFinancialClosure().longValue());
		qualitativeInputSheetManuRequest.setProjectDebtService(finalUnsecureLoanDetail.getProjectedDebtService().longValue());
		qualitativeInputSheetManuRequest.setInternalRateReturn(finalUnsecureLoanDetail.getInternalRateReturn().longValue());
		qualitativeInputSheetManuRequest.setSensititivityAnalysis(finalUnsecureLoanDetail.getSensititivityAnalysis().longValue());
		qualitativeInputSheetManuRequest.setInfrastructureAvailability(finalUnsecureLoanDetail.getInfrastructureAvailability().longValue());
		qualitativeInputSheetManuRequest.setConstructionContract(finalUnsecureLoanDetail.getConstructionContract().longValue());
		qualitativeInputSheetManuRequest.setDesignTechnologyRisk(finalUnsecureLoanDetail.getTechnologyRiskId().longValue());
		qualitativeInputSheetManuRequest.setNumberCheckReturned(finalUnsecureLoanDetail.getNumberOfCheques().longValue());
		qualitativeInputSheetManuRequest.setNumberTimesDpLimits(finalUnsecureLoanDetail.getNumberOfTimesDp().longValue());
		qualitativeInputSheetManuRequest.setCumulativeDaysDpLimits(finalUnsecureLoanDetail.getCumulativeNoOfDaysDp().longValue());
		qualitativeInputSheetManuRequest.setCompliancesWithSancationed(finalUnsecureLoanDetail.getComplianceWithSanctioned().longValue());
		qualitativeInputSheetManuRequest.setSubmissionProgressReport(finalUnsecureLoanDetail.getProgressReports().longValue());
		qualitativeInputSheetManuRequest.setDelayInReceiptPrincipal(finalUnsecureLoanDetail.getDelayInReceipt().longValue());
		qualitativeInputSheetManuRequest.setDelayInSubmissionAudited(finalUnsecureLoanDetail.getDelayInSubmission().longValue());
		qualitativeInputSheetManuRequest.setVarianceInProjectedSales(finalUnsecureLoanDetail.getVarianceInProjectedSales().longValue());
		qualitativeInputSheetManuRequest.setNumberOfLcBgIssuedInFavor(finalUnsecureLoanDetail.getNumberOfLc().longValue());	
		
		//---Contigent Liabilities set
		if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest))
				qualitativeInputSheetManuRequest.setContingentLiabilities(0.0);
		else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability()))
				qualitativeInputSheetManuRequest.setContingentLiabilities(0.0);
		else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth()))
				qualitativeInputSheetManuRequest.setContingentLiabilities(0.0);
		else
				qualitativeInputSheetManuRequest.setContingentLiabilities(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability() / pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth());//-----formula based
		//---project size 0.0 USL
			qualitativeInputSheetManuRequest.setProjectSize(0.0);//----- formula based
				
		return qualitativeInputSheetManuRequest;
	}
	
	
	
	
	@Override
	public QualitativeInputSheetServRequest qualitativeInputServiceService(Long aplicationId,Long userId , Integer productId)
			throws Exception {
		// TODO Auto-generated method stub
		QualitativeInputSheetServRequest qualitativeInputSheetServRequest = new QualitativeInputSheetServRequest();
		LoanType type = CommonUtils.LoanType.getType(productId);
		switch (type) {
		case WORKING_CAPITAL:
			// set 
			qualitativeInputSheetServRequest = setWCServiceQualitativeInput(aplicationId,userId);
			break;
		case TERM_LOAN:
			qualitativeInputSheetServRequest = setTLServiceQualitativeInput(aplicationId,userId);
			break;
		case UNSECURED_LOAN :
			qualitativeInputSheetServRequest= setUSLServiceQualitativeInput(aplicationId,userId);
			break;
				
		}
		return qualitativeInputSheetServRequest;
	}

	public QualitativeInputSheetServRequest setWCServiceQualitativeInput(Long aplicationId,Long userId) throws Exception{
		QualitativeInputSheetServRequest qualitativeInputSheetServRequest = new QualitativeInputSheetServRequest();
		List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequest = new ArrayList<PastFinancialEstimatesDetailRequest>();
		pastFinancialEstimatesDetailRequest=pastFinancialEstiamateDetailsService.getPastFinancialEstimateDetailsList(aplicationId);
		
		FinalWorkingCapitalLoanDetail finalWorkingCapitalLoanDetail = null; 
		finalWorkingCapitalLoanDetail = finalWorkingCapitalLoanDetailRepository.getByApplicationAndUserId(aplicationId,userId);
		
		qualitativeInputSheetServRequest.setAccountingQuality(finalWorkingCapitalLoanDetail.getAccountingQuality().longValue());
		qualitativeInputSheetServRequest.setCustomerQuality(finalWorkingCapitalLoanDetail.getCustomerQuality().longValue());
		qualitativeInputSheetServRequest.setSupplierQuality(finalWorkingCapitalLoanDetail.getSupplierQuality().longValue());
		qualitativeInputSheetServRequest.setSustainabilityProductDemand(finalWorkingCapitalLoanDetail.getSustainabilityProduct().longValue());
		qualitativeInputSheetServRequest.setProductSeasonality(finalWorkingCapitalLoanDetail.getProductSeasonality().longValue());
		qualitativeInputSheetServRequest.setImpactOnOperatingMargins(finalWorkingCapitalLoanDetail.getImpactOnOperatingMargins().longValue());
		qualitativeInputSheetServRequest.setEnvironmentImpact(finalWorkingCapitalLoanDetail.getEnvironmentalImpact().longValue());
		qualitativeInputSheetServRequest.setIntegrity(finalWorkingCapitalLoanDetail.getIntegrity().longValue());
		qualitativeInputSheetServRequest.setBusinessCommitment(finalWorkingCapitalLoanDetail.getBusinessCommitment().longValue());
		qualitativeInputSheetServRequest.setManagementCompetence(finalWorkingCapitalLoanDetail.getManagementCompetence().longValue());
		qualitativeInputSheetServRequest.setBusinessExperience(finalWorkingCapitalLoanDetail.getBusinessExperience().longValue());
		qualitativeInputSheetServRequest.setSuccsessionPlanning(finalWorkingCapitalLoanDetail.getSuccessionPlanning().longValue());
		qualitativeInputSheetServRequest.setFinancialStrength(finalWorkingCapitalLoanDetail.getFinancialStrength().longValue());
		qualitativeInputSheetServRequest.setInternalControl(finalWorkingCapitalLoanDetail.getInternalControl().longValue());
		qualitativeInputSheetServRequest.setCreditTrackRecord(finalWorkingCapitalLoanDetail.getCreditTrackRecord().longValue());
		qualitativeInputSheetServRequest.setNumberCheckReturned(finalWorkingCapitalLoanDetail.getNumberOfCheques().longValue());
		qualitativeInputSheetServRequest.setNumberTimesDpLimits(finalWorkingCapitalLoanDetail.getNumberOfTimesDp().longValue());
		qualitativeInputSheetServRequest.setCumulativeDaysDpLimits(finalWorkingCapitalLoanDetail.getCumulativeNoOfDaysDp().longValue());
		qualitativeInputSheetServRequest.setCompliancesWithSancationed(finalWorkingCapitalLoanDetail.getComplianceWithSanctioned().longValue());
		qualitativeInputSheetServRequest.setSubmissionProgressReport(finalWorkingCapitalLoanDetail.getProgressReports().longValue());
		qualitativeInputSheetServRequest.setDelayInReceiptPrincipal(finalWorkingCapitalLoanDetail.getDelayInReceipt().longValue());
		qualitativeInputSheetServRequest.setDelayInSubmissionAudited(finalWorkingCapitalLoanDetail.getDelayInSubmission().longValue());
		qualitativeInputSheetServRequest.setVarianceInProjectedSales(finalWorkingCapitalLoanDetail.getVarianceInProjectedSales().longValue());
		qualitativeInputSheetServRequest.setNumberOfLcBgIssuedInFavor(finalWorkingCapitalLoanDetail.getNumberOfLc().longValue());
		
		//---Contigent Liabilities set
				if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest))
					qualitativeInputSheetServRequest.setContingentLiabilities(0.0);
				else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability()))
					qualitativeInputSheetServRequest.setContingentLiabilities(0.0);
				else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth()))
					qualitativeInputSheetServRequest.setContingentLiabilities(0.0);
				else
					qualitativeInputSheetServRequest.setContingentLiabilities(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability() / pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth());//-----formula based
				
		return qualitativeInputSheetServRequest;		
	}
	
	public QualitativeInputSheetServRequest setTLServiceQualitativeInput(Long aplicationId,Long userId) throws Exception{
		QualitativeInputSheetServRequest qualitativeInputSheetServRequest = new QualitativeInputSheetServRequest();
		List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequest = new ArrayList<PastFinancialEstimatesDetailRequest>();
		pastFinancialEstimatesDetailRequest=pastFinancialEstiamateDetailsService.getPastFinancialEstimateDetailsList(aplicationId);
		
		FinalTermLoanDetail finalTermLoanDetail = null;
		finalTermLoanDetail = finalTermLoanDetailRepository.getByApplicationAndUserId(aplicationId,userId);
		
		qualitativeInputSheetServRequest.setAccountingQuality(finalTermLoanDetail.getAccountingQuality().longValue());
		qualitativeInputSheetServRequest.setCustomerQuality(finalTermLoanDetail.getCustomerQuality().longValue());
		qualitativeInputSheetServRequest.setSupplierQuality(finalTermLoanDetail.getSupplierQuality().longValue());
		qualitativeInputSheetServRequest.setSustainabilityProductDemand(finalTermLoanDetail.getSustainabilityProduct().longValue());
		qualitativeInputSheetServRequest.setProductSeasonality(finalTermLoanDetail.getProductSeasonality().longValue());
		qualitativeInputSheetServRequest.setImpactOnOperatingMargins(finalTermLoanDetail.getImpactOnOperatingMargins().longValue());
		qualitativeInputSheetServRequest.setEnvironmentImpact(finalTermLoanDetail.getEnvironmentalImpact().longValue());
		qualitativeInputSheetServRequest.setIntegrity(finalTermLoanDetail.getIntegrity().longValue());
		qualitativeInputSheetServRequest.setBusinessCommitment(finalTermLoanDetail.getBusinessCommitment().longValue());
		qualitativeInputSheetServRequest.setManagementCompetence(finalTermLoanDetail.getManagementCompetence().longValue());
		qualitativeInputSheetServRequest.setBusinessExperience(finalTermLoanDetail.getBusinessExperience().longValue());
		qualitativeInputSheetServRequest.setSuccsessionPlanning(finalTermLoanDetail.getSuccessionPlanning().longValue());
		qualitativeInputSheetServRequest.setFinancialStrength(finalTermLoanDetail.getFinancialStrength().longValue());
		qualitativeInputSheetServRequest.setInternalControl(finalTermLoanDetail.getInternalControl().longValue());
		qualitativeInputSheetServRequest.setCreditTrackRecord(finalTermLoanDetail.getCreditTrackRecord().longValue());
		qualitativeInputSheetServRequest.setNumberCheckReturned(finalTermLoanDetail.getNumberOfCheques().longValue());
		qualitativeInputSheetServRequest.setNumberTimesDpLimits(finalTermLoanDetail.getNumberOfTimesDp().longValue());
		qualitativeInputSheetServRequest.setCumulativeDaysDpLimits(finalTermLoanDetail.getCumulativeNoOfDaysDp().longValue());
		qualitativeInputSheetServRequest.setCompliancesWithSancationed(finalTermLoanDetail.getComplianceWithSanctioned().longValue());
		qualitativeInputSheetServRequest.setSubmissionProgressReport(finalTermLoanDetail.getProgressReports().longValue());
		qualitativeInputSheetServRequest.setDelayInReceiptPrincipal(finalTermLoanDetail.getDelayInReceipt().longValue());
		qualitativeInputSheetServRequest.setDelayInSubmissionAudited(finalTermLoanDetail.getDelayInSubmission().longValue());
		qualitativeInputSheetServRequest.setVarianceInProjectedSales(finalTermLoanDetail.getVarianceInProjectedSales().longValue());
		qualitativeInputSheetServRequest.setNumberOfLcBgIssuedInFavor(finalTermLoanDetail.getNumberOfLc().longValue());
		
		//---Contigent Liabilities set
		if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest))
			qualitativeInputSheetServRequest.setContingentLiabilities(0.0);
		else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability()))
			qualitativeInputSheetServRequest.setContingentLiabilities(0.0);
		else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth()))
			qualitativeInputSheetServRequest.setContingentLiabilities(0.0);
		else
			qualitativeInputSheetServRequest.setContingentLiabilities(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability() / pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth());//-----formula based

		return qualitativeInputSheetServRequest;		
	}
	
	public QualitativeInputSheetServRequest setUSLServiceQualitativeInput(Long aplicationId,Long userId) throws Exception{
		QualitativeInputSheetServRequest qualitativeInputSheetServRequest = new QualitativeInputSheetServRequest();
		List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequest = new ArrayList<PastFinancialEstimatesDetailRequest>();
		pastFinancialEstimatesDetailRequest=pastFinancialEstiamateDetailsService.getPastFinancialEstimateDetailsList(aplicationId);
		
		FinalUnsecureLoanDetail finalUnsecureLoanDetail = null;
		finalUnsecureLoanDetail = finalUnsecuredLoanDetailRepository.getByApplicationAndUserId(aplicationId,userId);
		
		qualitativeInputSheetServRequest.setAccountingQuality(finalUnsecureLoanDetail.getAccountingQuality().longValue());
		qualitativeInputSheetServRequest.setCustomerQuality(finalUnsecureLoanDetail.getCustomerQuality().longValue());
		qualitativeInputSheetServRequest.setSupplierQuality(finalUnsecureLoanDetail.getSupplierQuality().longValue());
		qualitativeInputSheetServRequest.setSustainabilityProductDemand(finalUnsecureLoanDetail.getSustainabilityProduct().longValue());
		qualitativeInputSheetServRequest.setProductSeasonality(finalUnsecureLoanDetail.getProductSeasonality().longValue());
		qualitativeInputSheetServRequest.setImpactOnOperatingMargins(finalUnsecureLoanDetail.getImpactOnOperatingMargins().longValue());
		qualitativeInputSheetServRequest.setEnvironmentImpact(finalUnsecureLoanDetail.getEnvironmentalImpact().longValue());
		qualitativeInputSheetServRequest.setIntegrity(finalUnsecureLoanDetail.getIntegrity().longValue());
		qualitativeInputSheetServRequest.setBusinessCommitment(finalUnsecureLoanDetail.getBusinessCommitment().longValue());
		qualitativeInputSheetServRequest.setManagementCompetence(finalUnsecureLoanDetail.getManagementCompetence().longValue());
		qualitativeInputSheetServRequest.setBusinessExperience(finalUnsecureLoanDetail.getBusinessExperience().longValue());
		qualitativeInputSheetServRequest.setSuccsessionPlanning(finalUnsecureLoanDetail.getSuccessionPlanning().longValue());
		qualitativeInputSheetServRequest.setFinancialStrength(finalUnsecureLoanDetail.getFinancialStrength().longValue());
		qualitativeInputSheetServRequest.setInternalControl(finalUnsecureLoanDetail.getInternalControl().longValue());
		qualitativeInputSheetServRequest.setCreditTrackRecord(finalUnsecureLoanDetail.getCreditTrackRecord().longValue());
		qualitativeInputSheetServRequest.setNumberCheckReturned(finalUnsecureLoanDetail.getNumberOfCheques().longValue());
		qualitativeInputSheetServRequest.setNumberTimesDpLimits(finalUnsecureLoanDetail.getNumberOfTimesDp().longValue());
		qualitativeInputSheetServRequest.setCumulativeDaysDpLimits(finalUnsecureLoanDetail.getCumulativeNoOfDaysDp().longValue());
		qualitativeInputSheetServRequest.setCompliancesWithSancationed(finalUnsecureLoanDetail.getComplianceWithSanctioned().longValue());
		qualitativeInputSheetServRequest.setSubmissionProgressReport(finalUnsecureLoanDetail.getProgressReports().longValue());
		qualitativeInputSheetServRequest.setDelayInReceiptPrincipal(finalUnsecureLoanDetail.getDelayInReceipt().longValue());
		qualitativeInputSheetServRequest.setDelayInSubmissionAudited(finalUnsecureLoanDetail.getDelayInSubmission().longValue());
		qualitativeInputSheetServRequest.setVarianceInProjectedSales(finalUnsecureLoanDetail.getVarianceInProjectedSales().longValue());
		qualitativeInputSheetServRequest.setNumberOfLcBgIssuedInFavor(finalUnsecureLoanDetail.getNumberOfLc().longValue());
		
		//---Contigent Liabilities set
		if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest))
			qualitativeInputSheetServRequest.setContingentLiabilities(0.0);
		else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability()))
			qualitativeInputSheetServRequest.setContingentLiabilities(0.0);
		else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth()))
			qualitativeInputSheetServRequest.setContingentLiabilities(0.0);
		else
			qualitativeInputSheetServRequest.setContingentLiabilities(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability() / pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth());//-----formula based

		return qualitativeInputSheetServRequest;		
	}
	
	
	
	
	@Override
	public QualitativeInputSheetTradRequest qualitativeInputServiceTrading(Long aplicationId, Long userId, Integer productId)
			throws Exception {
		// TODO Auto-generated method stub
		QualitativeInputSheetTradRequest qualitativeInputSheetTradRequest = new QualitativeInputSheetTradRequest();
		LoanType type = CommonUtils.LoanType.getType(productId);
		switch (type) {
		case WORKING_CAPITAL:
			// set 
			qualitativeInputSheetTradRequest = setWCTradingQualitativeInput(aplicationId,userId);
			break;
		case TERM_LOAN:
			qualitativeInputSheetTradRequest = setTLTradingQualitativeInput(aplicationId,userId);
			break;
		case UNSECURED_LOAN :
			qualitativeInputSheetTradRequest= setUSLTradingQualitativeInput(aplicationId,userId);
			break;
				
		}
		return qualitativeInputSheetTradRequest;
	}

	public QualitativeInputSheetTradRequest setWCTradingQualitativeInput(Long aplicationId,Long userId) throws Exception{
		QualitativeInputSheetTradRequest qualitativeInputSheetTradRequest = new QualitativeInputSheetTradRequest();
		List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequest = new ArrayList<PastFinancialEstimatesDetailRequest>();
		pastFinancialEstimatesDetailRequest=pastFinancialEstiamateDetailsService.getPastFinancialEstimateDetailsList(aplicationId);
		
		FinalWorkingCapitalLoanDetail finalWorkingCapitalLoanDetail = null; 
		finalWorkingCapitalLoanDetail = finalWorkingCapitalLoanDetailRepository.getByApplicationAndUserId(aplicationId,userId);
		
		qualitativeInputSheetTradRequest.setAccountingQuality(finalWorkingCapitalLoanDetail.getAccountingQuality().longValue());
		qualitativeInputSheetTradRequest.setCustomerQuality(finalWorkingCapitalLoanDetail.getCustomerQuality().longValue());
		qualitativeInputSheetTradRequest.setSupplierQuality(finalWorkingCapitalLoanDetail.getSupplierQuality().longValue());
		qualitativeInputSheetTradRequest.setSustainabilityProductDemand(finalWorkingCapitalLoanDetail.getSustainabilityProduct().longValue());
		qualitativeInputSheetTradRequest.setProductSeasonality(finalWorkingCapitalLoanDetail.getProductSeasonality().longValue());
		qualitativeInputSheetTradRequest.setImpactOnOperatingMargins(finalWorkingCapitalLoanDetail.getImpactOnOperatingMargins().longValue());
		qualitativeInputSheetTradRequest.setEnvironmentImpact(finalWorkingCapitalLoanDetail.getEnvironmentalImpact().longValue());
		qualitativeInputSheetTradRequest.setIntegrity(finalWorkingCapitalLoanDetail.getIntegrity().longValue());
		qualitativeInputSheetTradRequest.setBusinessCommitment(finalWorkingCapitalLoanDetail.getBusinessCommitment().longValue());
		qualitativeInputSheetTradRequest.setManagementCompetence(finalWorkingCapitalLoanDetail.getManagementCompetence().longValue());
		qualitativeInputSheetTradRequest.setBusinessExperience(finalWorkingCapitalLoanDetail.getBusinessExperience().longValue());
		qualitativeInputSheetTradRequest.setSuccsessionPlanning(finalWorkingCapitalLoanDetail.getSuccessionPlanning().longValue());
		qualitativeInputSheetTradRequest.setFinancialStrength(finalWorkingCapitalLoanDetail.getFinancialStrength().longValue());
		qualitativeInputSheetTradRequest.setInternalControl(finalWorkingCapitalLoanDetail.getInternalControl().longValue());
		qualitativeInputSheetTradRequest.setCreditTrackRecord(finalWorkingCapitalLoanDetail.getCreditTrackRecord().longValue());
		qualitativeInputSheetTradRequest.setNumberCheckReturned(finalWorkingCapitalLoanDetail.getNumberOfCheques().longValue());
		qualitativeInputSheetTradRequest.setNumberTimesDpLimits(finalWorkingCapitalLoanDetail.getNumberOfTimesDp().longValue());
		qualitativeInputSheetTradRequest.setCumulativeDaysDpLimits(finalWorkingCapitalLoanDetail.getCumulativeNoOfDaysDp().longValue());
		qualitativeInputSheetTradRequest.setCompliancesWithSancationed(finalWorkingCapitalLoanDetail.getComplianceWithSanctioned().longValue());
		qualitativeInputSheetTradRequest.setSubmissionProgressReport(finalWorkingCapitalLoanDetail.getProgressReports().longValue());
		qualitativeInputSheetTradRequest.setDelayInReceiptPrincipal(finalWorkingCapitalLoanDetail.getDelayInReceipt().longValue());
		qualitativeInputSheetTradRequest.setDelayInSubmissionAudited(finalWorkingCapitalLoanDetail.getDelayInSubmission().longValue());
		qualitativeInputSheetTradRequest.setVarianceInProjectedSales(finalWorkingCapitalLoanDetail.getVarianceInProjectedSales().longValue());
		qualitativeInputSheetTradRequest.setNumberOfLcBgIssuedInFavor(finalWorkingCapitalLoanDetail.getNumberOfLc().longValue());
		
		//---Contigent Liabilities set
		if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest))
			qualitativeInputSheetTradRequest.setContingentLiabilities(0.0);
		else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability()))
			qualitativeInputSheetTradRequest.setContingentLiabilities(0.0);
		else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth()))
			qualitativeInputSheetTradRequest.setContingentLiabilities(0.0);
		else
			qualitativeInputSheetTradRequest.setContingentLiabilities(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability() / pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth());//-----formula based

		return qualitativeInputSheetTradRequest;		
	}
	
	public QualitativeInputSheetTradRequest setTLTradingQualitativeInput(Long aplicationId,Long userId) throws Exception{
		QualitativeInputSheetTradRequest qualitativeInputSheetTradRequest = new QualitativeInputSheetTradRequest();
		List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequest = new ArrayList<PastFinancialEstimatesDetailRequest>();
		pastFinancialEstimatesDetailRequest=pastFinancialEstiamateDetailsService.getPastFinancialEstimateDetailsList(aplicationId);
		
		FinalTermLoanDetail finalTermLoanDetail = null;
		finalTermLoanDetail = finalTermLoanDetailRepository.getByApplicationAndUserId(aplicationId,userId);
		
		qualitativeInputSheetTradRequest.setAccountingQuality(finalTermLoanDetail.getAccountingQuality().longValue());
		qualitativeInputSheetTradRequest.setCustomerQuality(finalTermLoanDetail.getCustomerQuality().longValue());
		qualitativeInputSheetTradRequest.setSupplierQuality(finalTermLoanDetail.getSupplierQuality().longValue());
		qualitativeInputSheetTradRequest.setSustainabilityProductDemand(finalTermLoanDetail.getSustainabilityProduct().longValue());
		qualitativeInputSheetTradRequest.setProductSeasonality(finalTermLoanDetail.getProductSeasonality().longValue());
		qualitativeInputSheetTradRequest.setImpactOnOperatingMargins(finalTermLoanDetail.getImpactOnOperatingMargins().longValue());
		qualitativeInputSheetTradRequest.setEnvironmentImpact(finalTermLoanDetail.getEnvironmentalImpact().longValue());
		qualitativeInputSheetTradRequest.setIntegrity(finalTermLoanDetail.getIntegrity().longValue());
		qualitativeInputSheetTradRequest.setBusinessCommitment(finalTermLoanDetail.getBusinessCommitment().longValue());
		qualitativeInputSheetTradRequest.setManagementCompetence(finalTermLoanDetail.getManagementCompetence().longValue());
		qualitativeInputSheetTradRequest.setBusinessExperience(finalTermLoanDetail.getBusinessExperience().longValue());
		qualitativeInputSheetTradRequest.setSuccsessionPlanning(finalTermLoanDetail.getSuccessionPlanning().longValue());
		qualitativeInputSheetTradRequest.setFinancialStrength(finalTermLoanDetail.getFinancialStrength().longValue());
		qualitativeInputSheetTradRequest.setInternalControl(finalTermLoanDetail.getInternalControl().longValue());
		qualitativeInputSheetTradRequest.setCreditTrackRecord(finalTermLoanDetail.getCreditTrackRecord().longValue());
		qualitativeInputSheetTradRequest.setNumberCheckReturned(finalTermLoanDetail.getNumberOfCheques().longValue());
		qualitativeInputSheetTradRequest.setNumberTimesDpLimits(finalTermLoanDetail.getNumberOfTimesDp().longValue());
		qualitativeInputSheetTradRequest.setCumulativeDaysDpLimits(finalTermLoanDetail.getCumulativeNoOfDaysDp().longValue());
		qualitativeInputSheetTradRequest.setCompliancesWithSancationed(finalTermLoanDetail.getComplianceWithSanctioned().longValue());
		qualitativeInputSheetTradRequest.setSubmissionProgressReport(finalTermLoanDetail.getProgressReports().longValue());
		qualitativeInputSheetTradRequest.setDelayInReceiptPrincipal(finalTermLoanDetail.getDelayInReceipt().longValue());
		qualitativeInputSheetTradRequest.setDelayInSubmissionAudited(finalTermLoanDetail.getDelayInSubmission().longValue());
		qualitativeInputSheetTradRequest.setVarianceInProjectedSales(finalTermLoanDetail.getVarianceInProjectedSales().longValue());
		qualitativeInputSheetTradRequest.setNumberOfLcBgIssuedInFavor(finalTermLoanDetail.getNumberOfLc().longValue());
		
		//---Contigent Liabilities set
				if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest))
					qualitativeInputSheetTradRequest.setContingentLiabilities(0.0);
				else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability()))
					qualitativeInputSheetTradRequest.setContingentLiabilities(0.0);
				else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth()))
					qualitativeInputSheetTradRequest.setContingentLiabilities(0.0);
				else
					qualitativeInputSheetTradRequest.setContingentLiabilities(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability() / pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth());//-----formula based

		return qualitativeInputSheetTradRequest;		
	}
	
	public QualitativeInputSheetTradRequest setUSLTradingQualitativeInput(Long aplicationId,Long userId) throws Exception{
		QualitativeInputSheetTradRequest qualitativeInputSheetTradRequest = new QualitativeInputSheetTradRequest();
		List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequest = new ArrayList<PastFinancialEstimatesDetailRequest>();
		pastFinancialEstimatesDetailRequest=pastFinancialEstiamateDetailsService.getPastFinancialEstimateDetailsList(aplicationId);
		
		FinalUnsecureLoanDetail finalUnsecureLoanDetail = null;
		finalUnsecureLoanDetail = finalUnsecuredLoanDetailRepository.getByApplicationAndUserId(aplicationId,userId);
		
		qualitativeInputSheetTradRequest.setAccountingQuality(finalUnsecureLoanDetail.getAccountingQuality().longValue());
		qualitativeInputSheetTradRequest.setCustomerQuality(finalUnsecureLoanDetail.getCustomerQuality().longValue());
		qualitativeInputSheetTradRequest.setSupplierQuality(finalUnsecureLoanDetail.getSupplierQuality().longValue());
		qualitativeInputSheetTradRequest.setSustainabilityProductDemand(finalUnsecureLoanDetail.getSustainabilityProduct().longValue());
		qualitativeInputSheetTradRequest.setProductSeasonality(finalUnsecureLoanDetail.getProductSeasonality().longValue());
		qualitativeInputSheetTradRequest.setImpactOnOperatingMargins(finalUnsecureLoanDetail.getImpactOnOperatingMargins().longValue());
		qualitativeInputSheetTradRequest.setEnvironmentImpact(finalUnsecureLoanDetail.getEnvironmentalImpact().longValue());
		qualitativeInputSheetTradRequest.setIntegrity(finalUnsecureLoanDetail.getIntegrity().longValue());
		qualitativeInputSheetTradRequest.setBusinessCommitment(finalUnsecureLoanDetail.getBusinessCommitment().longValue());
		qualitativeInputSheetTradRequest.setManagementCompetence(finalUnsecureLoanDetail.getManagementCompetence().longValue());
		qualitativeInputSheetTradRequest.setBusinessExperience(finalUnsecureLoanDetail.getBusinessExperience().longValue());
		qualitativeInputSheetTradRequest.setSuccsessionPlanning(finalUnsecureLoanDetail.getSuccessionPlanning().longValue());
		qualitativeInputSheetTradRequest.setFinancialStrength(finalUnsecureLoanDetail.getFinancialStrength().longValue());
		qualitativeInputSheetTradRequest.setInternalControl(finalUnsecureLoanDetail.getInternalControl().longValue());
		qualitativeInputSheetTradRequest.setCreditTrackRecord(finalUnsecureLoanDetail.getCreditTrackRecord().longValue());
		qualitativeInputSheetTradRequest.setNumberCheckReturned(finalUnsecureLoanDetail.getNumberOfCheques().longValue());
		qualitativeInputSheetTradRequest.setNumberTimesDpLimits(finalUnsecureLoanDetail.getNumberOfTimesDp().longValue());
		qualitativeInputSheetTradRequest.setCumulativeDaysDpLimits(finalUnsecureLoanDetail.getCumulativeNoOfDaysDp().longValue());
		qualitativeInputSheetTradRequest.setCompliancesWithSancationed(finalUnsecureLoanDetail.getComplianceWithSanctioned().longValue());
		qualitativeInputSheetTradRequest.setSubmissionProgressReport(finalUnsecureLoanDetail.getProgressReports().longValue());
		qualitativeInputSheetTradRequest.setDelayInReceiptPrincipal(finalUnsecureLoanDetail.getDelayInReceipt().longValue());
		qualitativeInputSheetTradRequest.setDelayInSubmissionAudited(finalUnsecureLoanDetail.getDelayInSubmission().longValue());
		qualitativeInputSheetTradRequest.setVarianceInProjectedSales(finalUnsecureLoanDetail.getVarianceInProjectedSales().longValue());
		qualitativeInputSheetTradRequest.setNumberOfLcBgIssuedInFavor(finalUnsecureLoanDetail.getNumberOfLc().longValue());
		
		//---Contigent Liabilities set
				if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest))
					qualitativeInputSheetTradRequest.setContingentLiabilities(0.0);
				else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability()))
					qualitativeInputSheetTradRequest.setContingentLiabilities(0.0);
				else if(CommonUtils.isObjectNullOrEmpty(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth()))
					qualitativeInputSheetTradRequest.setContingentLiabilities(0.0);
				else
					qualitativeInputSheetTradRequest.setContingentLiabilities(pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getContingentLiability() / pastFinancialEstimatesDetailRequest.get(pastFinancialEstimatesDetailRequest.size()-1).getNetWorth());//-----formula based

		return qualitativeInputSheetTradRequest;		
	}
}
