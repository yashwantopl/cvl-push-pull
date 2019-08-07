package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.model.api_model.BalanceSheetAssetReq;
import com.capitaworld.service.loans.model.api_model.BalanceSheetLiabilitiesReq;
import com.capitaworld.service.loans.model.api_model.FinancialRequest;
import com.capitaworld.service.loans.model.api_model.ProfitAndLossStmntReq;
import com.capitaworld.service.loans.model.api_model.RatioDetailsReq;
import com.capitaworld.service.loans.model.corporate.AssetsDetailsRequest;
import com.capitaworld.service.loans.model.corporate.CMARequest;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.corporate.LiabilitiesDetailsRequest;
import com.capitaworld.service.loans.model.corporate.OperatingStatementDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CMAService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class CMAServiceImpl implements CMAService {

	private static final Logger logger = LoggerFactory.getLogger(CMAServiceImpl.class);
	
	@Autowired
	private LiabilitiesDetailsRepository liabilitiesDetailsRepository;
	
	@Autowired
	private AssetsDetailsRepository assetsDetailsRepository;
	
	@Autowired 
	private OperatingStatementDetailsRepository operatingStatementDetailsRepository;
	
	@Autowired 
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	CorporateApplicantDetailRepository corporateApplicantDetailRepository;
	
	public void saveCMA(CMARequest cmaRequest) {
		logger.info("ENTER IN CMA SAVE SERVICE IMPLEMENTATION");
		//SAVE LIABILITY DETAILS BY APPLICATION ID
		try {
			try {
				if(!CommonUtils.isObjectNullOrEmpty(cmaRequest.getApplicationId())) {
					liabilitiesDetailsRepository.inActiveByAppId(cmaRequest.getApplicationId());	
				}
			} catch (Exception e) {
				logger.error("Exception while inactive liability --->",e);
			}
			for(LiabilitiesDetailsRequest liabilitiesDetailsRequest : cmaRequest.getLiabilitiesRequestList()) {
				if(CommonUtils.isObjectNullOrEmpty(liabilitiesDetailsRequest)) {
					logger.info("Current Object is null or Empty in FOR LOOP");
					continue;
				}
				LiabilitiesDetails liabilitiesDetails = null;
				if(!CommonUtils.isObjectNullOrEmpty(liabilitiesDetailsRequest.getId())) {
					liabilitiesDetails = liabilitiesDetailsRepository.findByIdAndIsActive(liabilitiesDetailsRequest.getId(),true);	
				} else {
					liabilitiesDetails = liabilitiesDetailsRepository.getLiabilitiesDetails(liabilitiesDetailsRequest.getApplicationId(), liabilitiesDetailsRequest.getYear());
				}
				if(!CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)) {
					BeanUtils.copyProperties(liabilitiesDetailsRequest, liabilitiesDetails,"id",CommonUtils.APPLICATION_ID,CommonUtils.CREATED_BY,CommonUtils.CREATED_DATE,CommonUtils.IS_ACTIVE);
					liabilitiesDetails.setModifiedBy(cmaRequest.getUserId());
					liabilitiesDetails.setModifiedDate(new Date());
				} else {
					liabilitiesDetails = new LiabilitiesDetails();
					BeanUtils.copyProperties(liabilitiesDetailsRequest, liabilitiesDetails);
					liabilitiesDetails.setFsLoanApplicationMaster(loanApplicationRepository.findOne(liabilitiesDetailsRequest.getApplicationId()));
					liabilitiesDetails.setCreatedBy(cmaRequest.getUserId());
					liabilitiesDetails.setCreatedDate(new Date());
					liabilitiesDetails.setIsActive(true);
				}
				liabilitiesDetails.setYear(CommonUtils.getCMAFilterYear(liabilitiesDetails.getYear()));
				liabilitiesDetailsRepository.save(liabilitiesDetails);
				
			}	
		} catch (Exception e) {
			logger.error("THROW EXCEPTION WHILE SAVE LIABILITY DETAILS : ",e);
		}
		
		
		
		//SAVE ASSET DETAILS BY APPLICATION ID
		try {
			try {
				if(!CommonUtils.isObjectNullOrEmpty(cmaRequest.getApplicationId())) {
					assetsDetailsRepository.inActiveByAppId(cmaRequest.getApplicationId());	
				}
			} catch (Exception e) {
				logger.error("Exception while inactive assets --->",e);
			}
			for(AssetsDetailsRequest assetsDetailsRequest : cmaRequest.getAssetsRequestList()) {
				AssetsDetails assetsDetails = null;
				if(!CommonUtils.isObjectNullOrEmpty(assetsDetailsRequest.getId())) {
					assetsDetails = assetsDetailsRepository.findByIdAndIsActive(assetsDetailsRequest.getId(),true);	
				} else {
					assetsDetails = assetsDetailsRepository.getAssetsDetails(assetsDetailsRequest.getApplicationId(), assetsDetailsRequest.getYear());
				}
				if(!CommonUtils.isObjectNullOrEmpty(assetsDetails)) {
					BeanUtils.copyProperties(assetsDetailsRequest, assetsDetails,"id",CommonUtils.APPLICATION_ID,CommonUtils.CREATED_BY,CommonUtils.CREATED_DATE,CommonUtils.IS_ACTIVE);
					assetsDetails.setModifiedBy(cmaRequest.getUserId());
					assetsDetails.setModifiedDate(new Date());
				} else {
					assetsDetails = new AssetsDetails();
					BeanUtils.copyProperties(assetsDetailsRequest, assetsDetails);
					assetsDetails.setLoanApplicationMaster(loanApplicationRepository.findOne(assetsDetailsRequest.getApplicationId()));
					assetsDetails.setCreatedBy(cmaRequest.getUserId());
					assetsDetails.setCreatedDate(new Date());
					assetsDetails.setIsActive(true);
				}
				assetsDetails.setYear(CommonUtils.getCMAFilterYear(assetsDetails.getYear()));
				assetsDetailsRepository.save(assetsDetails);
			}	
		} catch (Exception e) {
			logger.error("THROW EXCEPTION WHILE SAVE ASSET DETAILS DETAILS",e);
		}
		
		
		//SAVE OPERATING STATEMENT DETAILS BY APPLICATION ID
		try {
			try {
				if(!CommonUtils.isObjectNullOrEmpty(cmaRequest.getApplicationId())) {
					operatingStatementDetailsRepository.inActiveByAppId(cmaRequest.getApplicationId());	
				}
			} catch (Exception e) {
				logger.error("Exception while inactive operating statement --->",e);
			}
			for(OperatingStatementDetailsRequest operatingStatementDetailsRequest : cmaRequest.getOperatingStatementRequestList()) {
				OperatingStatementDetails operatingStatementDetails = null;
				if(!CommonUtils.isObjectNullOrEmpty(operatingStatementDetailsRequest.getId())) {
					operatingStatementDetails = operatingStatementDetailsRepository.findByIdAndIsActive(operatingStatementDetailsRequest.getId(),true);	
				} else {
					operatingStatementDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(operatingStatementDetailsRequest.getApplicationId(), operatingStatementDetailsRequest.getYear());
				}
				if(!CommonUtils.isObjectNullOrEmpty(operatingStatementDetails)) {
					BeanUtils.copyProperties(operatingStatementDetailsRequest, operatingStatementDetails,"id",CommonUtils.APPLICATION_ID,CommonUtils.CREATED_BY,CommonUtils.CREATED_DATE,CommonUtils.IS_ACTIVE);
					operatingStatementDetails.setModifiedBy(cmaRequest.getUserId());
					operatingStatementDetails.setModifiedDate(new Date());
				} else {
					operatingStatementDetails = new OperatingStatementDetails();
					BeanUtils.copyProperties(operatingStatementDetailsRequest, operatingStatementDetails);
					operatingStatementDetails.setLoanApplicationMaster(loanApplicationRepository.findOne(operatingStatementDetailsRequest.getApplicationId()));
					operatingStatementDetails.setCreatedBy(cmaRequest.getUserId());
					operatingStatementDetails.setCreatedDate(new Date());
					operatingStatementDetails.setIsActive(true);
				}
				operatingStatementDetails.setYear(CommonUtils.getCMAFilterYear(operatingStatementDetails.getYear()));
				operatingStatementDetailsRepository.save(operatingStatementDetails);
			}
		} catch (Exception e) {
			logger.error("THROW EXCEPTION WHILE SAVE OPERATING STATEMENT DETAILS DETAILS : ",e);
		}
		logger.info("CMA DETAILS SAVED SUCCESSFULLY");
		
	}
	
	public CMARequest getCMA(Long applicationId) {
		
		CMARequest cmaRequest = new CMARequest();
		
		//GET LIABILITY DETAILS BY APPLICATION ID
		List<LiabilitiesDetails> liabilitiesDetailList = liabilitiesDetailsRepository.getByApplicationId(applicationId);
		List<LiabilitiesDetailsRequest> liabilitiesRequestsList = new ArrayList<>(liabilitiesDetailList.size());
		LiabilitiesDetailsRequest liabilitiesRequest = null;
		for(LiabilitiesDetails liabilitiesDetails : liabilitiesDetailList) {
			liabilitiesRequest = new LiabilitiesDetailsRequest();
			BeanUtils.copyProperties(liabilitiesDetails, liabilitiesRequest);
			liabilitiesRequest.setApplicationId(liabilitiesDetails.getFsLoanApplicationMaster().getId());
			liabilitiesRequestsList.add(liabilitiesRequest);
		}
		cmaRequest.setLiabilitiesRequestList(liabilitiesRequestsList);
		
		//GET ASSET DETAILS BY APPLICATION ID
		List<AssetsDetails> assetsDetailsList = assetsDetailsRepository.getByApplicationId(applicationId);
		List<AssetsDetailsRequest> assetsRequestsList = new ArrayList<>(assetsDetailsList.size());
		AssetsDetailsRequest assetsDetailsRequest = null;
		for(AssetsDetails assetsDetails : assetsDetailsList) {
			assetsDetailsRequest = new AssetsDetailsRequest();
			BeanUtils.copyProperties(assetsDetails, assetsDetailsRequest);
			assetsDetailsRequest.setApplicationId(assetsDetails.getLoanApplicationMaster().getId());
			assetsRequestsList.add(assetsDetailsRequest);
		}
		cmaRequest.setAssetsRequestList(assetsRequestsList);
		
		//GET OPERATING STATEMENT DETAILS BY APPLICATION ID
		List<OperatingStatementDetails> operatingStatementList = operatingStatementDetailsRepository.getByApplicationId(applicationId);
		List<OperatingStatementDetailsRequest> operatingStatementRequestList = new ArrayList<>(operatingStatementList.size());
		OperatingStatementDetailsRequest operatingStatementRequest = null;
		for(OperatingStatementDetails operatingStatement : operatingStatementList) {
			operatingStatementRequest = new OperatingStatementDetailsRequest();
			BeanUtils.copyProperties(operatingStatement, operatingStatementRequest);
			operatingStatementRequest.setApplicationId(operatingStatement.getLoanApplicationMaster().getId());
			operatingStatementRequestList.add(operatingStatementRequest);
		}
		cmaRequest.setOperatingStatementRequestList(operatingStatementRequestList);
		
		return cmaRequest;
		
	}
	
	/** API FOR SIDBI INTEGRATION 
	 * @author harshit
	 * Date : 22-Jun-2018
	 */
	@Override
	public FinancialRequest getFinancialDetailsForBankIntegration(Long applicationId, Long proposalId) {
		
		Set<String> yearList = new HashSet<>();
		
		Integer totalMonth = 12;

		//GET OPERATING DETAILS BY APPLICATION ID
		List<OperatingStatementDetails> operatingStatementList = null;
		if(proposalId != null) {
			operatingStatementList = operatingStatementDetailsRepository.getByApplicationIdAndProposalIdForPushAPI(applicationId, proposalId);
		} else {
			operatingStatementList = operatingStatementDetailsRepository.getByApplicationId(applicationId);			
		}
		if(operatingStatementList == null || operatingStatementList.size() <= 0) {
			logger.info("CMA Operating Statement List is null or empty AppId --->" + applicationId + "--- ProposalId---->" + proposalId);
			return null;
		}
		
		//GET LIABILITY DETAILS BY APPLICATION ID
		List<LiabilitiesDetails> liabilitiesDetailList = null;
		if(proposalId != null) {
			liabilitiesDetailList = liabilitiesDetailsRepository.getByApplicationIdAndProposalIdForPushAPI(applicationId, proposalId);
		} else {
			liabilitiesDetailList = liabilitiesDetailsRepository.getByApplicationId(applicationId);
		}
		if(liabilitiesDetailList == null || liabilitiesDetailList.size() <= 0) {
			return null;
		}
		
		//GET ASSETS DETAILS BY APPLICATION ID
		List<AssetsDetails> assetsDetailsList = null;
		if(proposalId != null) {
			assetsDetailsList = assetsDetailsRepository.getByApplicationIdAndProposalIdForPushAPI(applicationId, proposalId);
		} else {
			assetsDetailsList = assetsDetailsRepository.getByApplicationId(applicationId);
		}
		if(assetsDetailsList == null || assetsDetailsList.size() <= 0) {
			return null;
		}
		
		CorporateApplicantDetail corporateApplicantDetail = null;
		if(proposalId != null) {
			corporateApplicantDetail = corporateApplicantDetailRepository.getByAppIdAndProposalId(applicationId,proposalId);
		} else {
			corporateApplicantDetail = corporateApplicantDetailRepository.findByApplicationIdIdAndIsActive(applicationId, true);
		}
		
		double shareFaceVal = 1.0;
		if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail) && !CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getSharePriceFace())) {
			shareFaceVal = corporateApplicantDetail.getSharePriceFace();
		}
		
		FinancialRequest response = new FinancialRequest();
		response.setApplicationId(applicationId);
		
		//NOW READ DATA FROM LIST AND CALCULATE 
		
		List<ProfitAndLossStmntReq> profiltAndLossStmntReqList = new ArrayList<>(operatingStatementList.size());
		ProfitAndLossStmntReq prlossStmntReq = null;
		for(OperatingStatementDetails operatingStatement : operatingStatementList) {
			operatingStatement.setYear(CommonUtils.getCMAFilterYear(operatingStatement.getYear()));
			yearList.add(CommonUtils.getCMAFilterYear(operatingStatement.getYear()));
			
				
			prlossStmntReq = new ProfitAndLossStmntReq();
			prlossStmntReq.setApplicationId(applicationId);
			prlossStmntReq.setYear(operatingStatement.getYear());
			prlossStmntReq.setCurrency(getCurrency(applicationId));
			prlossStmntReq.setSubTotalOfIncome(CommonUtils.checkDoubleNull(operatingStatement.getSubTotalOfIncome()));
			//B19
			double totalGrossSales = CommonUtils.checkDoubleNull(operatingStatement.getTotalGrossSales()) + CommonUtils.checkDoubleNull(operatingStatement.getExportSales());
			prlossStmntReq.setTotalGrossSales(totalGrossSales);
			//B20
			double lessExciseDuty = CommonUtils.checkDoubleNull(operatingStatement.getLessExciseDuty()) + CommonUtils.checkDoubleNull(operatingStatement.getDeductOtherItems());
			prlossStmntReq.setLessExciseDuty(lessExciseDuty);
			//B21 -------------->   =B19-B20
			double netSales = totalGrossSales - lessExciseDuty;
			prlossStmntReq.setNetSales(netSales);
			//B23             (D38-D42)+(D46-D50)
			double inDecStock = (CommonUtils.checkDoubleNull(operatingStatement.getAddOperatingStock()) - CommonUtils.checkDoubleNull(operatingStatement.getDeductStockInProcess()))
					+ (CommonUtils.checkDoubleNull(operatingStatement.getAddOperatingStockFg()) - CommonUtils.checkDoubleNull(operatingStatement.getDeductClStockFg()));
			prlossStmntReq.setInDecStock(inDecStock);
			//B24
			double rawMaterials = CommonUtils.checkDoubleNull(operatingStatement.getRawMaterials())
					+ CommonUtils.checkDoubleNull(operatingStatement.getOtherSpares());
			prlossStmntReq.setRawMaterials(rawMaterials);
			
			//B25
			double powerAndFuel = CommonUtils.checkDoubleNull(operatingStatement.getPowerAndFuel());
			prlossStmntReq.setPowerAndFuel(powerAndFuel);
			
			//B26
			double employeeCost = CommonUtils.checkDoubleNull(operatingStatement.getDirectLabour());
			prlossStmntReq.setEmployeeCost(employeeCost);
			
			//B28
			//prlossStmntReq.setOtherMfgExpenses(CommonUtils.checkDoubleNull(operatingStatement.getOtherMfgExpenses()));
			
			//B27
			double generalAdminExp = CommonUtils.checkDoubleNull(operatingStatement.getSellingGenlAdmnExpenses());
			prlossStmntReq.setGeneralAdminExp(generalAdminExp);
			//B28
			double sellingAndDistExp = CommonUtils.checkDoubleNull(operatingStatement.getSellingAndDistributionExpenses());
			prlossStmntReq.setSellingAndDistributionExpenses(sellingAndDistExp);
			
			//B31
			double expensesAmortised = CommonUtils.checkDoubleNull(operatingStatement.getExpensesAmortised());
			prlossStmntReq.setExpensesAmortised(expensesAmortised);
			
			//B29
			double miscellaneousExpenses = CommonUtils.checkDoubleNull(operatingStatement.getOtherMfgExpenses());
			prlossStmntReq.setMiscellaneousExpenses(miscellaneousExpenses);
			
			//B30
			double otherIncomeNeedTocCheckOp = CommonUtils.checkDoubleNull(operatingStatement.getOtherIncomeNeedTocCheckOp());
			prlossStmntReq.setOtherIncomeNeedTocCheckOp(otherIncomeNeedTocCheckOp);
			
			
			//B32  --------------------->   =SUM(B23:B30)-B31
			double totalExpenditure = (inDecStock + otherIncomeNeedTocCheckOp + rawMaterials + powerAndFuel +
					employeeCost + generalAdminExp + miscellaneousExpenses + sellingAndDistExp) - expensesAmortised;
			prlossStmntReq.setTotalExpenditure(totalExpenditure);
			
			//B33  ---------------------> =B21-B32
			double opProfitOT = netSales - totalExpenditure;
			prlossStmntReq.setOperatingProfitOI(opProfitOT);
			
			//B34
			double addOtherRevenueIncome = CommonUtils.checkDoubleNull(operatingStatement.getAddOtherRevenueIncome());
			prlossStmntReq.setAddOtherRevenueIncome(addOtherRevenueIncome);
			
			//B35 ------------------> =B33+B34
			double opProfitEBITDA = opProfitOT + addOtherRevenueIncome;
			prlossStmntReq.setOperatingProfitEBITDA(opProfitEBITDA);
			
			//B36
			double interest = CommonUtils.checkDoubleNull(operatingStatement.getInterest());
			prlossStmntReq.setInterest(interest);
			
			//B37 -----------------------> =B35-B36
			double pbdt = opProfitEBITDA - interest;
			prlossStmntReq.setPbdt(pbdt);
			
			//B38
			double depreciation = CommonUtils.checkDoubleNull(operatingStatement.getDepreciation()) +
					CommonUtils.checkDoubleNull(operatingStatement.getExpensesAmortised());
			prlossStmntReq.setDepreciation(depreciation);
			
			//B39 ------------------------->=B37-B38
			double prftBfrTaxAndExp = pbdt - depreciation;
			prlossStmntReq.setProfitBeforeTaxAndExpItems(prftBfrTaxAndExp);
			
			//B40
			double exceptionalIncomeExp =  CommonUtils.checkDoubleNull(operatingStatement.getNetofNonOpIncomeOrExpenses());
			prlossStmntReq.setExceptionalIncomeExp(exceptionalIncomeExp);
			
			//B41 ------------------> =B39+B40
			double profitBeforeTax = prftBfrTaxAndExp + exceptionalIncomeExp;
			prlossStmntReq.setProfitBeforeTax(profitBeforeTax);
			
			//B42 ----------------> Operating Statement: C75+C77
			double provisionForTaxes = CommonUtils.checkDoubleNull(operatingStatement.getProvisionForTaxes())  + CommonUtils.checkDoubleNull(operatingStatement.getProvisionForDeferredTax());
			prlossStmntReq.setProvisionForTaxes(provisionForTaxes);
			
			//B43  -------------------->=B41-B42
			double profitAfterTax = profitBeforeTax - provisionForTaxes;
			prlossStmntReq.setProfitAfterTax(profitAfterTax);
			//B44
			double eqDvdntPaidAmt = CommonUtils.checkDoubleNull(operatingStatement.getEquityDeividendPaidAmt());
			prlossStmntReq.setDividendRate(eqDvdntPaidAmt);
			
			
			LiabilitiesDetails liabilitiesDetails = liabilitiesDetailList.stream().filter(a -> operatingStatement.getYear().equals(a.getYear())).findFirst().orElse(null);
			double ordinarySharesCapital = 0.0;
			if(!CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)) {
				ordinarySharesCapital = CommonUtils.checkDoubleNull(liabilitiesDetails.getOrdinarySharesCapital());
			}
			//B46 -----------------------> =IF(B44=0,0,B44*$B10/B52)
			if(eqDvdntPaidAmt != 0 && ordinarySharesCapital != 0) {
				double equityDividend = (eqDvdntPaidAmt * shareFaceVal) / ordinarySharesCapital;
				prlossStmntReq.setEquityDividend(CommonUtils.checkDouble(equityDividend * 100));
			}
			
			//B47 -------------------------------> =(B43)*$B10/B52
			if(ordinarySharesCapital != 0) {
				double earningsPerShare = profitAfterTax * (shareFaceVal / ordinarySharesCapital);
				prlossStmntReq.setEarningsPerShare(CommonUtils.checkDouble(earningsPerShare));
			}
			profiltAndLossStmntReqList.add(prlossStmntReq);
		}
		response.setProfiltAndLossStmntReqList(profiltAndLossStmntReqList);
		
		
		List<BalanceSheetLiabilitiesReq> liabilitiesReqList = new ArrayList<>(liabilitiesDetailList.size());
		BalanceSheetLiabilitiesReq liabilitiesReq = null;
		for(LiabilitiesDetails liabilitiesDetails : liabilitiesDetailList) {
			liabilitiesDetails.setYear(CommonUtils.getCMAFilterYear(liabilitiesDetails.getYear()));
			yearList.add(CommonUtils.getCMAFilterYear(liabilitiesDetails.getYear()));
			
			liabilitiesReq = new BalanceSheetLiabilitiesReq();
			liabilitiesReq.setApplicationId(applicationId);
			liabilitiesReq.setYear(liabilitiesDetails.getYear());
			liabilitiesReq.setCurrency(getCurrency(applicationId));
			//B52
			double sharesCapital = CommonUtils.checkDoubleNull(liabilitiesDetails.getPreferencesShares()) + liabilitiesDetails.getOrdinarySharesCapital();
			liabilitiesReq.setSharesCapital(sharesCapital);
			//B53
			double shareWarntsOutstanding = CommonUtils.checkDoubleNull(liabilitiesDetails.getShareWarrentsOutstanding());
			liabilitiesReq.setShareWarrentsOutstanding(shareWarntsOutstanding);
			
			//B54
			double revaluationReservse =  CommonUtils.checkDoubleNull(liabilitiesDetails.getRevaluationReservse());
			liabilitiesReq.setRevaluationReservse(revaluationReservse);
			
			//B55      Liabilities: C73+C77+C79+C83
			//23. General Reserve Add 25. Other reserves [excluding provisions] Add 26. Surplus(+) or Deficit(-) in Profit & Loss Account. Add 27 b. Others [specify]
			double otherReservesAndSurplus = CommonUtils.checkDoubleNull(liabilitiesDetails.getGeneralReserve()) + CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherReservse()) 
			+ CommonUtils.checkDoubleNull(liabilitiesDetails.getSurplusOrDeficit()) + CommonUtils.checkDoubleNull(liabilitiesDetails.getOthers());
			liabilitiesReq.setOtherReservesAndSurplus(otherReservesAndSurplus);
			
			//B56 -------------------------> =SUM(B52:B55)
			double shareholderFunds = sharesCapital + shareWarntsOutstanding + revaluationReservse + otherReservesAndSurplus;
			liabilitiesReq.setShareholderFunds(shareholderFunds);
			//B57
			double minorityInterest = CommonUtils.checkDoubleNull(liabilitiesDetails.getMinorityInterest());
			liabilitiesReq.setMinorityInterest(minorityInterest);
			//B58
			double securedLoans = CommonUtils.checkDoubleNull(liabilitiesDetails.getTermLiabilitiesSecured());
			liabilitiesReq.setSecuredLoans(securedLoans);
			//B59
			double unsecuredLoansPromoters = CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters());
			liabilitiesReq.setUnsecuredLoansPromoters(unsecuredLoansPromoters);
			//B60 -----------------> Liabilities: D47+D59
			double unsecuredLoansOthers = CommonUtils.checkDoubleNull(liabilitiesDetails.getTermLiabilitiesUnsecured())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther());
			liabilitiesReq.setUnsecuredLoansOthers(unsecuredLoansOthers);
			
			//B61
			double deferredTaxLiability = CommonUtils.checkDoubleNull(liabilitiesDetails.getDeferredTaxLiability());
			liabilitiesReq.setDeferredTaxLiability(deferredTaxLiability);
			
			//B62    Liabilities: D61+D49+D51+D41+D53
			double otherLongTermLiabilities = CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherNclOthers())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getDeferredPaymentsCredits())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getTermDeposits())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getDebentures())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherTermLiabilies());
			liabilitiesReq.setOtherLongTermLiabilities(otherLongTermLiabilities);
			
			//B63     Liabilities: D15+D17
			double otherBorrowings = CommonUtils.checkDoubleNull(liabilitiesDetails.getSubTotalA())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getShortTermBorrowingFromOthers());
			liabilitiesReq.setOtherBorrowings(otherBorrowings);
			
			//B64
			double longTermProvisions = CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherNclLongTermProvisions());
			liabilitiesReq.setLongTermProvisions(longTermProvisions);
			
			//B65 --------------------> =SUM(B57:B64)
			double totalNCLiabilities = minorityInterest + securedLoans + unsecuredLoansPromoters 
					+ unsecuredLoansOthers + deferredTaxLiability + otherLongTermLiabilities + otherBorrowings + longTermProvisions ;
			liabilitiesReq.setTotalNonCurrentLiabilities(totalNCLiabilities);
			
			//B66
			double tradePayables = CommonUtils.checkDoubleNull(liabilitiesDetails.getSundryCreditors());
			liabilitiesReq.setTradePayables(tradePayables);
			
			//B67 ------------->  Liabilities: D21+D25+D27+D32+D29
			double otherCurrentLiabilities = CommonUtils.checkDoubleNull(liabilitiesDetails.getAdvancePaymentsFromCustomers())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getDividendPayable())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherStatutoryLiability())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherCurrentLiability())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans());
			liabilitiesReq.setOtherCurrentLiabilities(otherCurrentLiabilities);
			
			//B68
			double shortTermProvisions = CommonUtils.checkDoubleNull(liabilitiesDetails.getProvisionalForTaxation());
			liabilitiesReq.setShortTermProvisions(shortTermProvisions);
			
			//B69 -----------------------> =SUM(B66:B68)
			double totalCurrentLiabilities = tradePayables + otherCurrentLiabilities + shortTermProvisions;
			liabilitiesReq.setTotalCurrentLiabilities(totalCurrentLiabilities);
			
			//B70
			double otherIncomeNeedTocCheckLia = CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherIncomeNeedTocCheckLia());
			liabilitiesReq.setOtherIncomeNeedTocCheckLia(otherIncomeNeedTocCheckLia);
			
			//B71  -------------------> =B56+B65+B69+B70
			liabilitiesReq.setTotalLiabilities(shareholderFunds + totalNCLiabilities + totalCurrentLiabilities + otherIncomeNeedTocCheckLia);
			
			liabilitiesReqList.add(liabilitiesReq);
		}
		response.setBalanceSheetLiabilitiesReqList(liabilitiesReqList);
		
		
		
		List<BalanceSheetAssetReq> bsAssetReqList = new ArrayList<>(assetsDetailsList.size());
		BalanceSheetAssetReq bsAssetReq = null;
		for(AssetsDetails assetsDetails : assetsDetailsList) {
			
			assetsDetails.setYear(CommonUtils.getCMAFilterYear(assetsDetails.getYear()));
			yearList.add(CommonUtils.getCMAFilterYear(assetsDetails.getYear()));
			
			bsAssetReq = new BalanceSheetAssetReq();
			bsAssetReq.setApplicationId(applicationId);
			bsAssetReq.setCurrency(getCurrency(applicationId));
			bsAssetReq.setYear(assetsDetails.getYear());
			
			//B74
			double grossBlock = CommonUtils.checkDoubleNull(assetsDetails.getGrossBlock());
			bsAssetReq.setGrossBlock(grossBlock);
			
			//B75
			double depreciationToDate = CommonUtils.checkDoubleNull(assetsDetails.getDepreciationToDate());
			bsAssetReq.setDepreciationToDate(depreciationToDate);
			
			//B76
			double impairmentAsset = CommonUtils.checkDoubleNull(assetsDetails.getImpairmentAsset());
			bsAssetReq.setImpairmentAsset(impairmentAsset);
			
			//B77 -----------------> =B74-B75-B76
			double netBlock = grossBlock - depreciationToDate - impairmentAsset;
			bsAssetReq.setNetBlock(netBlock);
			
			//B78
			double capitalWorkInprogress = CommonUtils.checkDoubleNull(assetsDetails.getOtherNcaOtherCapitalWorkInprogress());
			bsAssetReq.setCapitalWorkInprogress(capitalWorkInprogress);
			//B79
			double intangibleAssets = CommonUtils.checkDoubleNull(assetsDetails.getIntangibleAssets());
			bsAssetReq.setIntangibleAssets(intangibleAssets);
			//B80
			double preOperativeExpensesPending = CommonUtils.checkDoubleNull(assetsDetails.getOthersPreOperativeExpensesPending());
			bsAssetReq.setPreOperativeExpensesPending(preOperativeExpensesPending);
			//B81
			double assetsInTransit = CommonUtils.checkDoubleNull(assetsDetails.getOthersAssetsInTransit());
			bsAssetReq.setAssetsInTransit(assetsInTransit);
			//B82
			double investmentsInSubsidiary = CommonUtils.checkDoubleNull(assetsDetails.getInvestmentsInSubsidiary());
			bsAssetReq.setInvestmentsInSubsidiary(investmentsInSubsidiary);
			
			//B83  Asset: D65+D69+D74
			//44. Investments/book debts/advances/deposits which are not Current Assets((b) Others Add [iii]  Deferred receivables [maturity exceeding 1 yr] Add [iv]  Others (Others) )
			double otherInvestments = CommonUtils.checkDoubleNull(assetsDetails.getOthersOther()) + CommonUtils.checkDoubleNull(assetsDetails.getDeferredReceviables())
			+ CommonUtils.checkDoubleNull(assetsDetails.getOthers());
			bsAssetReq.setOtherInvestments(otherInvestments);
			
			//B84
			double longTermLoansAndAdvances = CommonUtils.checkDoubleNull(assetsDetails.getAdvanceToSuppliersCapitalGoods());
			bsAssetReq.setLongTermLoansAndAdvances(longTermLoansAndAdvances);
			
			//B85   Asset: D76+D78
			double otherNCAsset = CommonUtils.checkDoubleNull(assetsDetails.getNonConsumableStoreAndSpares()) + CommonUtils.checkDoubleNull(assetsDetails.getOtherNonCurrentAssets());
			bsAssetReq.setOtherNonCurrentAssets(otherNCAsset);
			
			//B86 ---------------> =SUM(B78:B85)
			double totalNCAssets = capitalWorkInprogress + intangibleAssets + preOperativeExpensesPending + assetsInTransit + investmentsInSubsidiary + otherInvestments + longTermLoansAndAdvances + otherNCAsset;
			bsAssetReq.setTotalNonCurrentAssets(totalNCAssets);
			
			//B87
			double inventory = CommonUtils.checkDoubleNull(assetsDetails.getInventory());
			bsAssetReq.setInventory(inventory);
			
			//B88   ---Asset: D16+D18
			double sundryDebtors = CommonUtils.checkDoubleNull(assetsDetails.getReceivableOtherThanDefferred()) + CommonUtils.checkDoubleNull(assetsDetails.getExportReceivables());
			bsAssetReq.setSundryDebtors(sundryDebtors);
			//B89
			double cashAndBankBalance = CommonUtils.checkDoubleNull(assetsDetails.getCashAndBankBalance());
			bsAssetReq.setCashAndBankBalance(cashAndBankBalance);
			//B90  --- Asset: D11+D20+D41
			double otherCurrentAssets = CommonUtils.checkDoubleNull(assetsDetails.getInvestments()) 
					+ CommonUtils.checkDoubleNull(assetsDetails.getInstalmentsDeferred())
					+ CommonUtils.checkDoubleNull(assetsDetails.getOtherCurrentAssets());
			bsAssetReq.setOtherCurrentAssets(otherCurrentAssets);
			//B91    ----Asset:D37+D39
			double shortTermLoansandAdvances = CommonUtils.checkDoubleNull(assetsDetails.getAdvanceToSupplierRawMaterials()) + CommonUtils.checkDoubleNull(assetsDetails.getAdvancePaymentTaxes());
			bsAssetReq.setShortTermLoansandAdvances(shortTermLoansandAdvances);
			
			//B92 ---------------> =SUM(B87:B91)
			double totalCurrentAssets = inventory + sundryDebtors + cashAndBankBalance + otherCurrentAssets + shortTermLoansandAdvances;
			bsAssetReq.setTotalCurrentAssets(totalCurrentAssets);
			
			//B93---------------- oTHER
			double otherIncomeNeedTocCheckAsset = CommonUtils.checkDoubleNull(assetsDetails.getOtherIncomeNeedTocCheckAsset());
			bsAssetReq.setOtherIncomeNeedTocCheckAsset(otherIncomeNeedTocCheckAsset);
			
			//B94  ----------------> =B77+B86+B92+B93
			bsAssetReq.setTotalAssets(netBlock + totalNCAssets + totalCurrentAssets + otherIncomeNeedTocCheckAsset);
			
			//B95
			if(corporateApplicantDetail == null) {
				bsAssetReq.setContingentLiabilities(0.0);
			} else {
				bsAssetReq.setContingentLiabilities(CommonUtils.checkDoubleNull(corporateApplicantDetail.getContLiabilityFyAmt()));
			}
			//B96  ------------------> =B56/(B52/$B$10)
			bsAssetReq.setBookValue(0.0);
			if(shareFaceVal != 0) {
				BalanceSheetLiabilitiesReq liabilitiesDetails = liabilitiesReqList.stream().filter(a -> assetsDetails.getYear().equals(a.getYear())).findFirst().orElse(null);
				double shareCapital = 0.0;
				double shareHolderFunds = 0.0;
				if(!CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)) {
					shareCapital = CommonUtils.checkDouble(liabilitiesDetails.getSharesCapital());
					shareHolderFunds = CommonUtils.checkDouble(liabilitiesDetails.getShareholderFunds());
				}
				
				double bookValue = shareCapital / shareFaceVal;
				bsAssetReq.setBookValue(bookValue != 0 ? CommonUtils.checkDouble(shareHolderFunds / bookValue) : 0.0);
			}
			bsAssetReqList.add(bsAssetReq);
		}
		response.setBalanceSheetAssetReqList(bsAssetReqList);
		
		//SAVE RATION ANALYSIS
		List<RatioDetailsReq> ratioDetailsReqList = new ArrayList<>();
		RatioDetailsReq ratioDetailsReq = null;
		List<String> sortedList = new ArrayList<String>(yearList);
		Collections.sort(sortedList);
		for(String year : sortedList) {
			
			String previousYear = String.valueOf(Integer.parseInt(year) - 1);
			String previousToPreYear = String.valueOf(Integer.parseInt(year) - 2);
			ProfitAndLossStmntReq profitAndLossStmntReq = profiltAndLossStmntReqList.stream().filter(a -> year.equals(a.getYear())).findFirst().orElse(null);
			ProfitAndLossStmntReq profitAndLossForPreviousYear = profiltAndLossStmntReqList.stream().filter(a -> previousYear.equals(a.getYear())).findFirst().orElse(new ProfitAndLossStmntReq());
			ProfitAndLossStmntReq profitAndLossForPreviousToPreYear = profiltAndLossStmntReqList.stream().filter(a -> previousToPreYear.equals(a.getYear())).findFirst().orElse(new ProfitAndLossStmntReq());
			
			BalanceSheetLiabilitiesReq liabilitiesDetails = liabilitiesReqList.stream().filter(a -> year.equals(a.getYear())).findFirst().orElse(null);
			BalanceSheetLiabilitiesReq liabilitiesForPreviousYear = liabilitiesReqList.stream().filter(a -> previousYear.equals(a.getYear())).findFirst().orElse(new BalanceSheetLiabilitiesReq());
			BalanceSheetLiabilitiesReq liabilitiesForPreviousToPreYear = liabilitiesReqList.stream().filter(a -> previousToPreYear.equals(a.getYear())).findFirst().orElse(new BalanceSheetLiabilitiesReq());
			
			BalanceSheetAssetReq balanceSheetAssetReq = bsAssetReqList.stream().filter(a -> year.equals(a.getYear())).findFirst().orElse(null);
			BalanceSheetAssetReq balanceSheetAssetForPreviousYear = bsAssetReqList.stream().filter(a -> previousYear.equals(a.getYear())).findFirst().orElse(new BalanceSheetAssetReq());
			BalanceSheetAssetReq balanceSheetAssetForPreviousToPreYear = bsAssetReqList.stream().filter(a -> previousToPreYear.equals(a.getYear())).findFirst().orElse(new BalanceSheetAssetReq());
			
			if(CommonUtils.isObjectNullOrEmpty(profitAndLossStmntReq) || CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)
					|| CommonUtils.isObjectNullOrEmpty(balanceSheetAssetReq)) {
				logger.info("Skip On Ratio Calculation ----------------->" + year);
				continue;
			}
			
			RatioDetailsReq ratioDtlsForNextYear = ratioDetailsReqList.stream().filter(a -> previousYear.equals(a.getYear())).findFirst().orElse(new RatioDetailsReq());
			
			ratioDetailsReq = new RatioDetailsReq();
			ratioDetailsReq.setYear(year);
			ratioDetailsReq.setApplicationId(applicationId);
			
			//B109 -----> =B35/B21
			if(profitAndLossStmntReq.getNetSales() != 0) {
				ratioDetailsReq.setEbitda(CommonUtils.checkDouble((profitAndLossStmntReq.getOperatingProfitEBITDA() / profitAndLossStmntReq.getNetSales()) * 100));
			}
			
			//B110 -----> =B43/B21
			if(profitAndLossStmntReq.getNetSales() != 0) {
				ratioDetailsReq.setPatm(CommonUtils.checkDouble((profitAndLossStmntReq.getProfitAfterTax() / profitAndLossStmntReq.getNetSales()) * 100));
			}
			
			//B111 -----> ==(B35*2/(B56+C56+B65+C65))*12/B17
			double totalSum = liabilitiesDetails.getShareholderFunds() + liabilitiesForPreviousYear.getShareholderFunds() + liabilitiesDetails.getTotalNonCurrentLiabilities() +  liabilitiesForPreviousYear.getTotalNonCurrentLiabilities();
			if(totalSum != 0) {
				double roce = CommonUtils.checkDouble((profitAndLossStmntReq.getOperatingProfitEBITDA() * 2 / totalSum) * 12 / totalMonth);
				ratioDetailsReq.setRoce(CommonUtils.checkDouble(roce * 100));
			}
			
			//B112 -----> =B21*12/(B94*B17)
			double assetMulti = balanceSheetAssetReq.getTotalAssets() * totalMonth;
			if(assetMulti != 0) {
				ratioDetailsReq.setAssetTurnover(CommonUtils.checkDouble((profitAndLossStmntReq.getNetSales() * 12) / assetMulti));	
			}
			
			//B113 -----> =365/(B32*12/(B87*B17))
			double inventoryMulti = balanceSheetAssetReq.getInventory() * totalMonth;
			if(inventoryMulti != 0) {
				double inventSubMulti = (profitAndLossStmntReq.getTotalExpenditure() * 12) / inventoryMulti;
				ratioDetailsReq.setInventoryTurnover(CommonUtils.checkDouble(inventSubMulti != 0 ? (365 / inventSubMulti) : 0.0));
			}
			
			//B114 -----> =365/((B21*12/(B88*B17)))
			double devMulti = balanceSheetAssetReq.getSundryDebtors() * totalMonth;
			if(devMulti != 0) {
				double devSubMulti = (profitAndLossStmntReq.getNetSales() * 12) / devMulti;
				ratioDetailsReq.setDebtorsTurnover(CommonUtils.checkDouble(devSubMulti != 0 ? (365 / devSubMulti) : 0.0));
			}
			
			//B115 -----> =(365/((B24+B25)/B66))*12/B17
			double creditorsTrnovr = profitAndLossStmntReq.getRawMaterials() + profitAndLossStmntReq.getPowerAndFuel() + profitAndLossStmntReq.getOtherMfgExpenses();
			if(liabilitiesDetails.getTradePayables() != 0) {
				double creditors = (365 / (creditorsTrnovr / liabilitiesDetails.getTradePayables())) * 12 / totalMonth;
				ratioDetailsReq.setCreditorsTurnover(CommonUtils.checkDouble(creditors));
			}
			
			//B116 -----> =(365/(B21/(B87+B88-B66)))*12/B17
			double salesAndWcMulti = balanceSheetAssetReq.getInventory() + balanceSheetAssetReq.getSundryDebtors() - liabilitiesDetails.getTradePayables();
			if(salesAndWcMulti != 0) {
				double salesAndWcSubMulti = profitAndLossStmntReq.getNetSales() / salesAndWcMulti;
				ratioDetailsReq.setSalesAndWorkingCapital(CommonUtils.checkDouble(salesAndWcSubMulti != 0 ? ((365 / salesAndWcSubMulti) * 12 / totalMonth) : 0.0));
			}
			
			//B117 -----> =B21/C21-1
			ratioDetailsReq.setNetSalesGrowth(profitAndLossForPreviousToPreYear.getNetSales() != 0 ? CommonUtils.checkDouble((profitAndLossStmntReq.getNetSales() / profitAndLossForPreviousToPreYear.getNetSales() -1) * 100) : 0.0);
			
			//B118 -----> =B43/C43-1
			ratioDetailsReq.setPatGrowth(profitAndLossForPreviousToPreYear.getProfitAfterTax() != 0 ? CommonUtils.checkDouble((profitAndLossStmntReq.getProfitAfterTax() / profitAndLossForPreviousToPreYear.getProfitAfterTax() - 1) * 100) : 0.0);
			
			//B119 ----->=(B65-B64-B59)/(B56+B59)
			double adjTotalDbt = liabilitiesDetails.getShareholderFunds() + liabilitiesDetails.getUnsecuredLoansPromoters(); 
			double adjstedTotalDebtAndEquity = 0.0;
			if(adjTotalDbt != 0) {
				adjstedTotalDebtAndEquity = (liabilitiesDetails.getTotalNonCurrentLiabilities() - liabilitiesDetails.getLongTermProvisions() - liabilitiesDetails.getUnsecuredLoansPromoters()) / adjTotalDbt;
				ratioDetailsReq.setAdjustedTotalDebtAndEquity(CommonUtils.checkDouble(adjstedTotalDebtAndEquity));	
			}
			
			
			//B120 -----> =(B119-C119)/C119
			ratioDetailsReq.setGrowthInDebtAndEquity(ratioDtlsForNextYear.getAdjustedTotalDebtAndEquity() != 0 ? CommonUtils.checkDouble(((adjstedTotalDebtAndEquity - ratioDtlsForNextYear.getAdjustedTotalDebtAndEquity()) / ratioDtlsForNextYear.getAdjustedTotalDebtAndEquity()) * 100) : 0.0);
			
			//B121 -----> =(B87+B88)/B66
			ratioDetailsReq.setCurrentRatio(liabilitiesDetails.getTradePayables() != 0 ? CommonUtils.checkDouble((balanceSheetAssetReq.getInventory() + balanceSheetAssetReq.getSundryDebtors()) / liabilitiesDetails.getTradePayables()) : 0.0);
			
			//B122 =B92/B69
			ratioDetailsReq.setCurrentRatioAsPerCma(liabilitiesDetails.getTotalCurrentLiabilities() != 0 ? CommonUtils.checkDouble((balanceSheetAssetReq.getTotalCurrentAssets() + balanceSheetAssetReq.getSundryDebtors()) / liabilitiesDetails.getTotalCurrentLiabilities()) : 0.0);
			
			//B123 -----> =B88/B66
			ratioDetailsReq.setQuickRatio(liabilitiesDetails.getTradePayables() != 0 ? CommonUtils.checkDouble(balanceSheetAssetReq.getSundryDebtors() / liabilitiesDetails.getTradePayables()) : 0.0);
			
			
			
			double cashFromOperatingCurrentYear = getCashFromOperating(profitAndLossStmntReq, balanceSheetAssetReq, balanceSheetAssetForPreviousYear, liabilitiesDetails, liabilitiesForPreviousYear);
			double cashFromOperatingPreYear = getCashFromOperating(profitAndLossForPreviousYear, balanceSheetAssetForPreviousYear, balanceSheetAssetForPreviousToPreYear, liabilitiesForPreviousYear, liabilitiesForPreviousToPreYear);
			
			
			//B124 -----> =(B35-B42)/B36
			ratioDetailsReq.setCashInterestCover(profitAndLossStmntReq.getInterest() != 0 ? CommonUtils.checkDouble((profitAndLossStmntReq.getOperatingProfitEBITDA() + profitAndLossStmntReq.getProvisionForTaxes()) / profitAndLossStmntReq.getInterest()) : 0.0);
			
			//B125 -----> =(B65-B59-B64)/(12*B35/B17)
			double debtEbitdaMulti = (12 * profitAndLossStmntReq.getOperatingProfitEBITDA()) / totalMonth;
			ratioDetailsReq.setDebtEbitda(debtEbitdaMulti != 0 ? CommonUtils.checkDouble((liabilitiesDetails.getTotalNonCurrentLiabilities() - liabilitiesDetails.getUnsecuredLoansPromoters() - liabilitiesDetails.getLongTermProvisions()) / debtEbitdaMulti) : 0.0);
			
			//B126 -----> =B55/(B52+B53)
			double freeReserveEq = liabilitiesDetails.getSharesCapital() + liabilitiesDetails.getShareWarrentsOutstanding();
			ratioDetailsReq.setFreeReservesEquity(freeReserveEq != 0 ? CommonUtils.checkDouble(liabilitiesDetails.getOtherReservesAndSurplus() / freeReserveEq) : 0.0);
			
			//B127 -----> =B105/B21
			double cfoCurrentYearMargin = profitAndLossStmntReq.getNetSales() != 0 ? CommonUtils.checkDouble((cashFromOperatingCurrentYear/profitAndLossStmntReq.getNetSales()) * 100) : 0.0;
			double cfoPreYearMargin = profitAndLossForPreviousYear.getNetSales() != 0 ? CommonUtils.checkDouble((cashFromOperatingPreYear/profitAndLossForPreviousYear.getNetSales()) * 100) : 0.0;
			ratioDetailsReq.setCfoMargin(cfoCurrentYearMargin);
			
			//B128 -----> =(B127-C127)/C127
			ratioDetailsReq.setGrowthInCfoMargin(cfoPreYearMargin != 0 ? ((cfoCurrentYearMargin - cfoPreYearMargin) / cfoPreYearMargin) : 0.0);
			
			ratioDetailsReqList.add(ratioDetailsReq);
		}
		response.setRatioDetailsReqList(ratioDetailsReqList);
		logger.info("FINANCIAL DETAILS GET SUCCESSFuLLY --------------------->" + applicationId);
		return response;
		
	}
	
	private double getCashFromOperating(ProfitAndLossStmntReq profitAndLossStmntReq,BalanceSheetAssetReq balanceSheetAssetReq,BalanceSheetAssetReq balanceSheetAssetForNextYear,BalanceSheetLiabilitiesReq liabilitiesDetails,BalanceSheetLiabilitiesReq liabilitiesForNextYear) {
		// B101
		double ebitda = profitAndLossStmntReq.getOperatingProfitEBITDA();
		//B102
		double interestPaid = profitAndLossStmntReq.getInterest();
		//B103 ------------------> =(B87+B88+B90-C87-C88-C90+C66+C67+C68-B66-B67-B68)
		double incrsInWL = balanceSheetAssetReq.getInventory() + balanceSheetAssetReq.getSundryDebtors() + balanceSheetAssetReq.getOtherCurrentAssets() 
			- balanceSheetAssetForNextYear.getInventory() - balanceSheetAssetForNextYear.getSundryDebtors() - balanceSheetAssetForNextYear.getOtherCurrentAssets()
			+ liabilitiesForNextYear.getTradePayables() + liabilitiesForNextYear.getOtherCurrentLiabilities() + liabilitiesForNextYear.getShortTermProvisions()
			- liabilitiesDetails.getTradePayables() - liabilitiesDetails.getOtherCurrentLiabilities() - liabilitiesDetails.getShortTermProvisions();
		//B104
		double taxPaid = profitAndLossStmntReq.getProvisionForTaxes();
		
		//B105 --------> =B101-B102-B103-B104
		return ebitda - interestPaid - incrsInWL -taxPaid;
	}
	
	private String getCurrency(Long applicationId) {
		
		String currencyAndDenomination = "NA";
		LoanApplicationMaster applicationMaster = loanApplicationRepository.getById(applicationId);
		if(CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
			return currencyAndDenomination;	
		}
		if (!CommonUtils.isObjectNullOrEmpty(applicationMaster.getCurrencyId())
				&& !CommonUtils.isObjectNullOrEmpty(applicationMaster.getDenominationId())) {
			try {
				currencyAndDenomination = CommonDocumentUtils.getCurrency(applicationMaster.getCurrencyId());
				currencyAndDenomination = currencyAndDenomination.concat(
						" in " + CommonDocumentUtils.getDenomination(applicationMaster.getDenominationId()));
			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
		} else if (!CommonUtils.isObjectNullOrEmpty(applicationMaster.getCurrencyId())){
			currencyAndDenomination = CommonDocumentUtils.getCurrency(applicationMaster.getCurrencyId());
		}
		return currencyAndDenomination;
	}
	
	
	
}
