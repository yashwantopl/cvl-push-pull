package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import com.capitaworld.service.loans.model.corporate.AssetsDetailsRequest;
import com.capitaworld.service.loans.model.corporate.CMARequest;
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
import com.capitaworld.sidbi.integration.model.financial.BalanceSheetAssetReq;
import com.capitaworld.sidbi.integration.model.financial.BalanceSheetLiabilitiesReq;
import com.capitaworld.sidbi.integration.model.financial.FinancialRequest;
import com.capitaworld.sidbi.integration.model.financial.ProfitAndLossStmntReq;
import com.capitaworld.sidbi.integration.model.financial.RatioDetailsReq;

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
			logger.info("TOTAL LIABILITY OBJECT --------------------------------->"+cmaRequest.getLiabilitiesRequestList().size());
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
					BeanUtils.copyProperties(liabilitiesDetailsRequest, liabilitiesDetails,"id","applicationId","createdBy","createdDate","isActive");
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
			logger.info("THROW EXCEPTION WHILE SAVE LIABILITY DETAILS");
			e.printStackTrace();
		}
		
		
		
		//SAVE ASSET DETAILS BY APPLICATION ID
		try {
			logger.info("TOTAL ASSET OBJECT --------------------------------->"+cmaRequest.getAssetsRequestList().size());
			for(AssetsDetailsRequest assetsDetailsRequest : cmaRequest.getAssetsRequestList()) {
				AssetsDetails assetsDetails = null;
				if(!CommonUtils.isObjectNullOrEmpty(assetsDetailsRequest.getId())) {
					assetsDetails = assetsDetailsRepository.findByIdAndIsActive(assetsDetailsRequest.getId(),true);	
				} else {
					assetsDetails = assetsDetailsRepository.getAssetsDetails(assetsDetailsRequest.getApplicationId(), assetsDetailsRequest.getYear());
				}
				if(!CommonUtils.isObjectNullOrEmpty(assetsDetails)) {
					BeanUtils.copyProperties(assetsDetailsRequest, assetsDetails,"id","applicationId","createdBy","createdDate","isActive");
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
			logger.info("THROW EXCEPTION WHILE SAVE ASSET DETAILS DETAILS");
			e.printStackTrace();
		}
		
		
		//SAVE OPERATING STATEMENT DETAILS BY APPLICATION ID
		try {
			logger.info("TOTAL OPEATING STATEMENT OBJECT --------------------------------->"+cmaRequest.getOperatingStatementRequestList().size());
			for(OperatingStatementDetailsRequest operatingStatementDetailsRequest : cmaRequest.getOperatingStatementRequestList()) {
				OperatingStatementDetails operatingStatementDetails = null;
				if(!CommonUtils.isObjectNullOrEmpty(operatingStatementDetailsRequest.getId())) {
					operatingStatementDetails = operatingStatementDetailsRepository.findByIdAndIsActive(operatingStatementDetailsRequest.getId(),true);	
				} else {
					operatingStatementDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(operatingStatementDetailsRequest.getApplicationId(), operatingStatementDetailsRequest.getYear());
				}
				if(!CommonUtils.isObjectNullOrEmpty(operatingStatementDetails)) {
					BeanUtils.copyProperties(operatingStatementDetailsRequest, operatingStatementDetails,"id","applicationId","createdBy","createdDate","isActive");
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
			logger.info("THROW EXCEPTION WHILE SAVE OPERATING STATEMENT DETAILS DETAILS");
			e.printStackTrace();
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
	public FinancialRequest getFinancialDetailsForBankIntegration(Long applicationId) {
		
		Set<String> yearList = new HashSet<>();
		
		double shareFaceVal = 0.0;
		Integer totalMonth = 12;

		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
		if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
			shareFaceVal = CommonUtils.checkDouble(corporateApplicantDetail.getSharePriceFace());
		}
		
		//GET OPERATING DETAILS BY APPLICATION ID
		List<OperatingStatementDetails> operatingStatementList = operatingStatementDetailsRepository.getByApplicationId(applicationId);
		//REMOVE . FROM YEAR 
		for(OperatingStatementDetails operatingStatement : operatingStatementList) {
			operatingStatement.setYear(CommonUtils.getCMAFilterYear(operatingStatement.getYear()));
			yearList.add(CommonUtils.getCMAFilterYear(operatingStatement.getYear()));
		}
		
		//GET LIABILITY DETAILS BY APPLICATION ID
		List<LiabilitiesDetails> liabilitiesDetailList = liabilitiesDetailsRepository.getByApplicationId(applicationId);
		for(LiabilitiesDetails liabilitiesDetails : liabilitiesDetailList) {
			liabilitiesDetails.setYear(CommonUtils.getCMAFilterYear(liabilitiesDetails.getYear()));
			yearList.add(CommonUtils.getCMAFilterYear(liabilitiesDetails.getYear()));
		}
		
		//GET ASSETS DETAILS BY APPLICATION ID
		List<AssetsDetails> assetsDetailsList = assetsDetailsRepository.getByApplicationId(applicationId);
		for(AssetsDetails assetsDetails : assetsDetailsList) {
			assetsDetails.setYear(CommonUtils.getCMAFilterYear(assetsDetails.getYear()));
			yearList.add(CommonUtils.getCMAFilterYear(assetsDetails.getYear()));
		}
		
		if(operatingStatementList.size() <= 0 || liabilitiesDetailList.size() <= 0 || assetsDetailsList.size() <= 0) {
			return null;
		}
		
		FinancialRequest response = new FinancialRequest();
		response.setApplicationId(applicationId);
		
		//NOW READ DATA FROM LIST AND CALCULATE 
		
		List<ProfitAndLossStmntReq> profiltAndLossStmntReqList = new ArrayList<>(operatingStatementList.size());
		ProfitAndLossStmntReq prlossStmntReq = null;
		for(OperatingStatementDetails operatingStatement : operatingStatementList) {
			prlossStmntReq = new ProfitAndLossStmntReq();
			prlossStmntReq.setApplicationId(applicationId);
			prlossStmntReq.setYear(CommonUtils.getCMAFilterYear(operatingStatement.getYear()));
			prlossStmntReq.setCurrency(getCurrency(applicationId));
			prlossStmntReq.setSubTotalOfIncome(CommonUtils.checkDoubleNull(operatingStatement.getSubTotalOfIncome()));
			//B20
			prlossStmntReq.setTotalGrossSales(CommonUtils.checkDoubleNull(operatingStatement.getTotalGrossSales()));
			//B21
			prlossStmntReq.setLessExciseDuty(CommonUtils.checkDoubleNull(operatingStatement.getLessExciseDuty()));
			//B22 -------------->   =B20-B21
			double netSales = CommonUtils.checkDoubleNull(operatingStatement.getTotalGrossSales()) - CommonUtils.checkDoubleNull(operatingStatement.getLessExciseDuty());
			prlossStmntReq.setNetSales(netSales);
			//B24             (D38-D42)+(D46-D50)
			double inDecStock = (CommonUtils.checkDoubleNull(operatingStatement.getAddOperatingStock()) - CommonUtils.checkDoubleNull(operatingStatement.getDeductStockInProcess()))
					+ (CommonUtils.checkDoubleNull(operatingStatement.getAddOperatingStockFg()) - CommonUtils.checkDoubleNull(operatingStatement.getDeductClStockFg()));
			prlossStmntReq.setInDecStock(inDecStock);
			//B25
			prlossStmntReq.setRawMaterials(CommonUtils.checkDoubleNull(operatingStatement.getRawMaterials()));
			//B26
			prlossStmntReq.setPowerAndFuel(CommonUtils.checkDoubleNull(operatingStatement.getPowerAndFuel()));
			//B27
			prlossStmntReq.setEmployeeCost(CommonUtils.checkDoubleNull(operatingStatement.getDirectLabour()));
			//B28
			prlossStmntReq.setOtherMfgExpenses(CommonUtils.checkDoubleNull(operatingStatement.getOtherMfgExpenses()));
			//B29
			prlossStmntReq.setGeneralAdminExp(CommonUtils.checkDoubleNull(operatingStatement.getGeneralAdminExp()));
			//B30
			prlossStmntReq.setSellingAndDistributionExpenses(CommonUtils.checkDoubleNull(operatingStatement.getSellingAndDistributionExpenses()));
			//B31
			prlossStmntReq.setMiscellaneousExpenses(CommonUtils.checkDoubleNull(operatingStatement.getOtherMfgExpenses()));
			//B32
			prlossStmntReq.setExpensesAmortised(CommonUtils.checkDoubleNull(operatingStatement.getExpensesAmortised()));
			//B33  --------------------->   =SUM(B24:B31)-B32
			double totalExpenditure = (inDecStock + CommonUtils.checkDoubleNull(operatingStatement.getOtherMfgExpenses())) - CommonUtils.checkDoubleNull(operatingStatement.getExpensesAmortised());
			prlossStmntReq.setTotalExpenditure(totalExpenditure);
			
			//B34  ---------------------> =B22-B33
			double opProfitOT = netSales - totalExpenditure;
			prlossStmntReq.setOperatingProfitOI(opProfitOT);
			//B35
			prlossStmntReq.setAddOtherRevenueIncome(CommonUtils.checkDoubleNull(operatingStatement.getAddOtherRevenueIncome()));
			
			//B36 ------------------> =B34+B35
			double opProfitEBITDA = opProfitOT + CommonUtils.checkDoubleNull(operatingStatement.getAddOtherRevenueIncome());
			prlossStmntReq.setOperatingProfitEBITDA(opProfitEBITDA);
			//B37
			prlossStmntReq.setInterest(CommonUtils.checkDoubleNull(operatingStatement.getInterest()));
			
			//B38 -----------------------> =B36-B37
			double pbdt = opProfitEBITDA - CommonUtils.checkDoubleNull(operatingStatement.getInterest());
			prlossStmntReq.setPbdt(pbdt);
			//B39
			prlossStmntReq.setDepreciation(CommonUtils.checkDoubleNull(operatingStatement.getDepreciation()));
			
			//B40 ------------------------->=B38-B39
			double prftBfrTaxAndExp = pbdt - CommonUtils.checkDoubleNull(operatingStatement.getDepreciation());
			prlossStmntReq.setProfitBeforeTaxAndExpItems(prftBfrTaxAndExp);
			//B41
			prlossStmntReq.setExceptionalIncomeExp(CommonUtils.checkDoubleNull(operatingStatement.getNetofNonOpIncomeOrExpenses()));
			
			//B42 ------------------> =B40+B41
			double profitBeforeTax = prftBfrTaxAndExp + CommonUtils.checkDoubleNull(operatingStatement.getNetofNonOpIncomeOrExpenses());
			prlossStmntReq.setProfitBeforeTax(profitBeforeTax);
			
			//B43 ----------------> Operating Statement: C75+C77
			double provisionForTaxes = CommonUtils.checkDoubleNull(operatingStatement.getProvisionForTaxes())  + CommonUtils.checkDoubleNull(operatingStatement.getProfitBeforeTaxOrLoss());
			prlossStmntReq.setProvisionForTaxes(provisionForTaxes);
			
			//B44  -------------------->=B42-B43
			double profitAfterTax = profitBeforeTax - provisionForTaxes;
			prlossStmntReq.setProfitAfterTax(profitAfterTax);
			//B45
			double eqDvdntPaidAmt = CommonUtils.checkDoubleNull(operatingStatement.getEquityDeividendPaidAmt());
			prlossStmntReq.setDividendRate(eqDvdntPaidAmt);
			
			
			LiabilitiesDetails liabilitiesDetails = liabilitiesDetailList.stream().filter(a -> operatingStatement.getYear().equals(a.getYear())).findFirst().orElse(null);
			double ordinarySharesCapital = 0.0;
			if(!CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)) {
				ordinarySharesCapital = CommonUtils.checkDoubleNull(liabilitiesDetails.getOrdinarySharesCapital());
			}
			//B46 -----------------------> =IF(B45=0,0,B45*$B11/B53)
			if(eqDvdntPaidAmt != 0 && ordinarySharesCapital != 0) {
				double equityDividend = (eqDvdntPaidAmt * shareFaceVal) / ordinarySharesCapital;
				prlossStmntReq.setEquityDividend(CommonUtils.checkDouble(equityDividend * 100));
			}
			
			//B47 -------------------------------> =(B44)*$B11/B53
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
			liabilitiesReq = new BalanceSheetLiabilitiesReq();
			liabilitiesReq.setApplicationId(applicationId);
			liabilitiesReq.setYear(CommonUtils.getCMAFilterYear(liabilitiesDetails.getYear()));
			liabilitiesReq.setCurrency(getCurrency(applicationId));
			//B53
			liabilitiesReq.setOrdinarySharesCapital(CommonUtils.checkDoubleNull(liabilitiesDetails.getOrdinarySharesCapital()));
			//B54
			liabilitiesReq.setShareWarrentsOutstanding(CommonUtils.checkDoubleNull(liabilitiesDetails.getShareWarrentsOutstanding()));
			//B55
			liabilitiesReq.setRevaluationReservse(CommonUtils.checkDoubleNull(liabilitiesDetails.getRevaluationReservse()));
			
			//B56      Liabilities: C73+C77+C79+C83
			//23. General Reserve Add 25. Other reserves [excluding provisions] Add 26. Surplus(+) or Deficit(-) in Profit & Loss Account. Add 27 b. Others [specify]
			double otherReservesAndSurplus = CommonUtils.checkDoubleNull(liabilitiesDetails.getGeneralReserve()) + CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherReservse()) 
			+ CommonUtils.checkDoubleNull(liabilitiesDetails.getSurplusOrDeficit()) + CommonUtils.checkDoubleNull(liabilitiesDetails.getOthers());
			liabilitiesReq.setOtherReservesAndSurplus(otherReservesAndSurplus);
			//B57 -------------------------> =SUM(B53:B56)
			double shareholderFunds = CommonUtils.checkDoubleNull(liabilitiesDetails.getOrdinarySharesCapital()) + otherReservesAndSurplus;
			liabilitiesReq.setShareholderFunds(shareholderFunds);
			//B58
			liabilitiesReq.setMinorityInterest(CommonUtils.checkDoubleNull(liabilitiesDetails.getMinorityInterest()));
			//B59
			liabilitiesReq.setTermLiabilitiesSecured(CommonUtils.checkDoubleNull(liabilitiesDetails.getTermLiabilitiesSecured()));
			//B60
			liabilitiesReq.setOtherNclUnsecuredLoansFromPromoters(CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters()));
			//B61 -----------------> Liabilities: D47+D59
			liabilitiesReq.setUnsecuredLoansOthers(CommonUtils.checkDoubleNull(liabilitiesDetails.getTermLiabilitiesUnsecured())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther()));
			//B62
			liabilitiesReq.setDeferredTaxLiability(CommonUtils.checkDoubleNull(liabilitiesDetails.getDeferredTaxLiability()));
			//B63    Liabilities: D61+D49+D51+D41+D53
			liabilitiesReq.setOtherLongTermLiabilities(CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherNclOthers())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getDeferredPaymentsCredits())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getTermDeposits())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getDebentures())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherTermLiabilies()));
			//B64     Liabilities: D15+D17
			liabilitiesReq.setOtherBorrowings(CommonUtils.checkDoubleNull(liabilitiesDetails.getSubTotalA())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getShortTermBorrowingFromOthers()));
			//B65
			liabilitiesReq.setOtherNclLongTermProvisions(CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherNclLongTermProvisions()));
			//B66 --------------------> =SUM(B58:B65)
			double totalNCLiabilities = CommonUtils.checkDoubleNull(liabilitiesDetails.getMinorityInterest()) + CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherNclLongTermProvisions());
			liabilitiesReq.setTotalNonCurrentLiabilities(totalNCLiabilities);
			//B67
			liabilitiesReq.setSundryCreditors(CommonUtils.checkDoubleNull(liabilitiesDetails.getSundryCreditors()));
			//B68 ------------->  Liabilities: D21+D25+D27+D32+D29
			liabilitiesReq.setOtherCurrentLiabilities(CommonUtils.checkDoubleNull(liabilitiesDetails.getAdvancePaymentsFromCustomers())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getDividendPayable())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherStatutoryLiability())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherCurrentLiability())
					+ CommonUtils.checkDoubleNull(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans()));
			//B69
			liabilitiesReq.setProvisionalForTaxation(CommonUtils.checkDoubleNull(liabilitiesDetails.getProvisionalForTaxation()));
			//B70 -----------------------> =SUM(B67:B69)
			double totalCurrentLiabilities = CommonUtils.checkDoubleNull(liabilitiesDetails.getSundryCreditors()) + CommonUtils.checkDoubleNull(liabilitiesDetails.getProvisionalForTaxation());
			liabilitiesReq.setTotalCurrentLiabilities(totalCurrentLiabilities);
			//B71  -------------------> =B57+B66+B70
			liabilitiesReq.setTotalLiabilities(shareholderFunds + totalNCLiabilities + totalCurrentLiabilities);
			liabilitiesReqList.add(liabilitiesReq);
		}
		response.setBalanceSheetLiabilitiesReqList(liabilitiesReqList);
		
		
		
		List<BalanceSheetAssetReq> bsAssetReqList = new ArrayList<>(assetsDetailsList.size());
		BalanceSheetAssetReq bsAssetReq = null;
		for(AssetsDetails assetsDetails : assetsDetailsList) {
			bsAssetReq = new BalanceSheetAssetReq();
			bsAssetReq.setApplicationId(applicationId);
			bsAssetReq.setCurrency(getCurrency(applicationId));
			bsAssetReq.setYear(CommonUtils.getCMAFilterYear(assetsDetails.getYear()));
			//B74
			bsAssetReq.setGrossBlock(CommonUtils.checkDoubleNull(assetsDetails.getGrossBlock()));
			//B75
			bsAssetReq.setDepreciationToDate(CommonUtils.checkDoubleNull(assetsDetails.getDepreciationToDate()));
			//B76
			bsAssetReq.setImpairmentAsset(CommonUtils.checkDoubleNull(assetsDetails.getImpairmentAsset()));
			//B77 -----------------> =B74-B75-B76
			double netBlock = CommonUtils.checkDoubleNull(assetsDetails.getGrossBlock()) - CommonUtils.checkDoubleNull(assetsDetails.getDepreciationToDate()) - 
					CommonUtils.checkDoubleNull(assetsDetails.getImpairmentAsset());
			bsAssetReq.setNetBlock(netBlock);
			//B78
			bsAssetReq.setOtherNcaOtherCapitalWorkInprogress(CommonUtils.checkDoubleNull(assetsDetails.getOtherNcaOtherCapitalWorkInprogress()));
			//B79
			bsAssetReq.setIntangibleAssets(CommonUtils.checkDoubleNull(assetsDetails.getIntangibleAssets()));
			//B80
			bsAssetReq.setOthersPreOperativeExpensesPending(CommonUtils.checkDoubleNull(assetsDetails.getOthersPreOperativeExpensesPending()));
			//B81
			bsAssetReq.setOthersAssetsInTransit(CommonUtils.checkDoubleNull(assetsDetails.getOthersAssetsInTransit()));
			//B82
			bsAssetReq.setInvestmentsInSubsidiary(CommonUtils.checkDoubleNull(assetsDetails.getInvestmentsInSubsidiary()));
			
			//B83  Asset: D65+D69+D74
			//44. Investments/book debts/advances/deposits which are not Current Assets((b) Others Add [iii]  Deferred receivables [maturity exceeding 1 yr] Add [iv]  Others (Others) )
			double otherInvestments = CommonUtils.checkDoubleNull(assetsDetails.getInvestmentsOrBookDebts()) + CommonUtils.checkDoubleNull(assetsDetails.getDeferredReceviables())
			+ CommonUtils.checkDoubleNull(assetsDetails.getOthersOther());
			bsAssetReq.setOtherInvestments(otherInvestments);
			
			//B84
			bsAssetReq.setAdvanceToSuppliersCapitalGoods(CommonUtils.checkDoubleNull(assetsDetails.getAdvanceToSuppliersCapitalGoods()));
			//B85   Asset: D76+D78
			double otherNCAsset = CommonUtils.checkDoubleNull(assetsDetails.getNonConsumableStoreAndSpares()) + CommonUtils.checkDoubleNull(assetsDetails.getOtherNonCurrentAssets());
			bsAssetReq.setOtherNonCurrentAssets(otherNCAsset);
			//B86 ---------------> =SUM(B78:B85)
			double totalNCAssets = CommonUtils.checkDoubleNull(assetsDetails.getOtherNcaOtherCapitalWorkInprogress()) + otherNCAsset;
			bsAssetReq.setTotalNonCurrentAssets(totalNCAssets);
			//B87
			bsAssetReq.setInventory(CommonUtils.checkDoubleNull(assetsDetails.getInventory()));
			//B88   ---Asset: D16+D18
			bsAssetReq.setSundryDebtors(CommonUtils.checkDoubleNull(assetsDetails.getReceivableOtherThanDefferred()) + CommonUtils.checkDoubleNull(assetsDetails.getExportReceivables()));
			//B89
			bsAssetReq.setCashAndBankBalance(CommonUtils.checkDoubleNull(assetsDetails.getCashAndBankBalance()));
			//B90  --- Asset: D11+D20+D41
			bsAssetReq.setOtherCurrentAssets(CommonUtils.checkDoubleNull(assetsDetails.getInvestments()) 
					+ CommonUtils.checkDoubleNull(assetsDetails.getInstalmentsDeferred())
					+ CommonUtils.checkDoubleNull(assetsDetails.getOtherCurrentAssets()));
			//B91    ----Asset:D37+D39
			double shortTermLoansandAdvances = CommonUtils.checkDoubleNull(assetsDetails.getAdvanceToSupplierRawMaterials()) + CommonUtils.checkDoubleNull(assetsDetails.getAdvancePaymentTaxes());
			bsAssetReq.setShortTermLoansandAdvances(shortTermLoansandAdvances);
			//B92 ---------------> =SUM(B87:B91)
			double totalCurrentAssets = CommonUtils.checkDoubleNull(assetsDetails.getInventory()) + shortTermLoansandAdvances;
			bsAssetReq.setTotalCurrentAssets(totalCurrentAssets);
			//B93  ----------------> =B77+B86+B92
			bsAssetReq.setTotalAssets(netBlock + totalNCAssets + totalCurrentAssets);
			//B94
			bsAssetReq.setContingentLiabilities(0.0);
			
			
			BalanceSheetLiabilitiesReq liabilitiesDetails = liabilitiesReqList.stream().filter(a -> assetsDetails.getYear().equals(a.getYear())).findFirst().orElse(null);
			double shareCapital = 0.0;
			double shareHolderFunds = 0.0;
			if(!CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)) {
				shareCapital = CommonUtils.checkDouble(liabilitiesDetails.getOrdinarySharesCapital());
				shareHolderFunds = CommonUtils.checkDouble(liabilitiesDetails.getShareholderFunds());
			}
			//B95  ------------------> =B57/(B53/$B$11)
			bsAssetReq.setBookValue(0.0);
			if(shareFaceVal != 0) {
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
			ProfitAndLossStmntReq profitAndLossStmntReq = profiltAndLossStmntReqList.stream().filter(a -> year.equals(a.getYear())).findFirst().orElse(null);
			ProfitAndLossStmntReq profitAndLossForNextYear = profiltAndLossStmntReqList.stream().filter(a -> previousYear.equals(a.getYear())).findFirst().orElse(new ProfitAndLossStmntReq());
			
			BalanceSheetLiabilitiesReq liabilitiesDetails = liabilitiesReqList.stream().filter(a -> year.equals(a.getYear())).findFirst().orElse(null);
			BalanceSheetLiabilitiesReq liabilitiesForNextYear = liabilitiesReqList.stream().filter(a -> previousYear.equals(a.getYear())).findFirst().orElse(new BalanceSheetLiabilitiesReq());
			
			BalanceSheetAssetReq balanceSheetAssetReq = bsAssetReqList.stream().filter(a -> year.equals(a.getYear())).findFirst().orElse(null);
			BalanceSheetAssetReq balanceSheetAssetForNextYear = bsAssetReqList.stream().filter(a -> previousYear.equals(a.getYear())).findFirst().orElse(new BalanceSheetAssetReq());
			
			if(CommonUtils.isObjectNullOrEmpty(profitAndLossStmntReq) || CommonUtils.isObjectNullOrEmpty(liabilitiesDetails)
					|| CommonUtils.isObjectNullOrEmpty(balanceSheetAssetReq)) {
				logger.info("Skip On Ratio Calculation ----------------->" + year);
				continue;
			}
			
			RatioDetailsReq ratioDtlsForNextYear = ratioDetailsReqList.stream().filter(a -> previousYear.equals(a.getYear())).findFirst().orElse(new RatioDetailsReq());
			
			ratioDetailsReq = new RatioDetailsReq();
			ratioDetailsReq.setYear(year);
			ratioDetailsReq.setApplicationId(applicationId);
			
			//B108 -----> =B36/B22
			if(profitAndLossStmntReq.getNetSales() != 0) {
				ratioDetailsReq.setEbitda(CommonUtils.checkDouble((profitAndLossStmntReq.getOperatingProfitEBITDA() / profitAndLossStmntReq.getNetSales()) * 100));
			}
			
			//B109 -----> =B44/B22
			if(profitAndLossStmntReq.getNetSales() != 0) {
				ratioDetailsReq.setPatm(CommonUtils.checkDouble((profitAndLossStmntReq.getProfitAfterTax() / profitAndLossStmntReq.getNetSales()) * 100));
			}
			
			//B110 -----> =(B36*2/(B57+C57+B66+C66))*12/B18
			double totalSum = liabilitiesDetails.getShareholderFunds() + liabilitiesForNextYear.getShareholderFunds() + liabilitiesDetails.getTotalNonCurrentLiabilities() +  liabilitiesForNextYear.getTotalNonCurrentLiabilities();
			if(totalSum != 0) {
				double roce = CommonUtils.checkDouble((profitAndLossStmntReq.getOperatingProfitEBITDA() * 2 / totalSum) * 12 / totalMonth);
				ratioDetailsReq.setRoce(CommonUtils.checkDouble(roce * 100));
			}
			
			//B111 -----> =B22*12/(B93*B18)
			double assetMulti = balanceSheetAssetReq.getTotalAssets() * totalMonth;
			if(assetMulti != 0) {
				ratioDetailsReq.setAssetTurnover(CommonUtils.checkDouble((profitAndLossStmntReq.getNetSales() * 12) / assetMulti));	
			}
			
			//B112 -----> =365/(B33*12/(B87*B18))
			double inventoryMulti = balanceSheetAssetReq.getInventory() * totalMonth;
			if(inventoryMulti != 0) {
				double inventSubMulti = (profitAndLossStmntReq.getTotalExpenditure() * 12) / inventoryMulti;
				ratioDetailsReq.setInventoryTurnover(CommonUtils.checkDouble(inventSubMulti != 0 ? (365 / inventSubMulti) : 0.0));
			}
			
			//B113 -----> =365/((B22*12/(B88*B18)))
			double devMulti = balanceSheetAssetReq.getSundryDebtors() * totalMonth;
			if(devMulti != 0) {
				double devSubMulti = (profitAndLossStmntReq.getNetSales() * 12) / devMulti;
				ratioDetailsReq.setDebtorsTurnover(CommonUtils.checkDouble(devSubMulti != 0 ? (365 / devSubMulti) : 0.0));
			}
			
			//B114 -----> =(365/((B25+B26+B28)/B67))*12/B18
			double creditorsTrnovr = profitAndLossStmntReq.getRawMaterials() + profitAndLossStmntReq.getPowerAndFuel() + profitAndLossStmntReq.getOtherMfgExpenses();
			if(liabilitiesDetails.getSundryCreditors() != 0) {
				double creditors = (365 / (creditorsTrnovr / liabilitiesDetails.getSundryCreditors())) * 12 / totalMonth;
				ratioDetailsReq.setCreditorsTurnover(CommonUtils.checkDouble(creditors));
			}
			
			//B115 -----> =(365/(B22/(B87+B88-B67)))*12/B18
			double salesAndWcMulti = balanceSheetAssetReq.getInventory() + balanceSheetAssetReq.getSundryDebtors() - liabilitiesDetails.getSundryCreditors();
			if(salesAndWcMulti != 0) {
				double salesAndWcSubMulti = profitAndLossStmntReq.getNetSales() / salesAndWcMulti;
				ratioDetailsReq.setSalesAndWorkingCapital(CommonUtils.checkDouble(salesAndWcSubMulti != 0 ? ((365 / salesAndWcSubMulti) * 12 / totalMonth) : 0.0));
			}
			
			//B116 -----> =B22/C22-1
			ratioDetailsReq.setNetSalesGrowth(profitAndLossForNextYear.getNetSales() != 0 ? CommonUtils.checkDouble((profitAndLossStmntReq.getNetSales() / profitAndLossForNextYear.getNetSales() -1) * 100) : 0.0);
			
			//B117 -----> =B44/C44-1
			ratioDetailsReq.setPatGrowth(profitAndLossForNextYear.getProfitAfterTax() != 0 ? CommonUtils.checkDouble((profitAndLossStmntReq.getProfitAfterTax() / profitAndLossForNextYear.getProfitAfterTax() - 1) * 100) : 0.0);
			
			//B118 -----> =(B66-B65-B60)/(B57+B60)
			double adjTotalDbt = liabilitiesDetails.getShareholderFunds() + liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters(); 
			double adjstedTotalDebtAndEquity = 0.0;
			if(adjTotalDbt != 0) {
				adjstedTotalDebtAndEquity = (liabilitiesDetails.getTotalNonCurrentLiabilities() - liabilitiesDetails.getOtherNclLongTermProvisions() - liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters()) / adjTotalDbt;
				ratioDetailsReq.setAdjustedTotalDebtAndEquity(CommonUtils.checkDouble(adjstedTotalDebtAndEquity));	
			}
			
			
			//B119 -----> =(B118-C118)/C118
			ratioDetailsReq.setGrowthInDebtAndEquity(ratioDtlsForNextYear.getAdjustedTotalDebtAndEquity() != 0 ? CommonUtils.checkDouble(((adjstedTotalDebtAndEquity - ratioDtlsForNextYear.getAdjustedTotalDebtAndEquity()) / ratioDtlsForNextYear.getAdjustedTotalDebtAndEquity()) * 100) : 0.0);
			
			//B120 -----> =(B87+B88)/B67
			ratioDetailsReq.setCurrentRatio(liabilitiesDetails.getSundryCreditors() != 0 ? CommonUtils.checkDouble((balanceSheetAssetReq.getInventory() + balanceSheetAssetReq.getSundryDebtors()) / liabilitiesDetails.getSundryCreditors()) : 0.0);
			
			//B121 -----> =B88/B67
			ratioDetailsReq.setQuickRatio(liabilitiesDetails.getSundryCreditors() != 0 ? CommonUtils.checkDouble(balanceSheetAssetReq.getSundryDebtors() / liabilitiesDetails.getSundryCreditors()) : 0.0);
			
			
			//B100
			double ebitda = profitAndLossStmntReq.getOperatingProfitEBITDA();
			//B101
			double interestPaid = profitAndLossStmntReq.getInterest();
			//B102 ------------------> =(B87+B88+B90-C87-C88-C90+C67+C68+C69-B67-B68-B69)
			double incrsInWL = balanceSheetAssetReq.getInventory() + balanceSheetAssetReq.getSundryDebtors() + balanceSheetAssetReq.getCashAndBankBalance() 
				- balanceSheetAssetForNextYear.getInventory() - balanceSheetAssetForNextYear.getSundryDebtors() - balanceSheetAssetForNextYear.getCashAndBankBalance()
				+ liabilitiesForNextYear.getSundryCreditors() + liabilitiesForNextYear.getOtherCurrentLiabilities() + liabilitiesForNextYear.getOtherNclLongTermProvisions()
				- liabilitiesDetails.getSundryCreditors() - liabilitiesDetails.getOtherCurrentLiabilities() - liabilitiesDetails.getOtherNclLongTermProvisions();
			//B103
			double taxPaid = profitAndLossStmntReq.getProvisionForTaxes();
			
			//B104 --------> =B100-B101-B102-B103
			double cashFromOperating = ebitda - interestPaid - incrsInWL -taxPaid;
			
			//B122 -----> =(B104+B101)/B101
			ratioDetailsReq.setCashInterestCover(interestPaid != 0 ? CommonUtils.checkDouble((cashFromOperating + interestPaid) / interestPaid) : 0.0);
			
			//B123 -----> =(B66-B60-B65)/(12*B36/B18)
			double debtEbitdaMulti = (12 * profitAndLossStmntReq.getOperatingProfitEBITDA()) / totalMonth;
			ratioDetailsReq.setDebtEbitda(debtEbitdaMulti != 0 ? CommonUtils.checkDouble((liabilitiesDetails.getTotalNonCurrentLiabilities() - liabilitiesDetails.getUnsecuredLoansOthers() - liabilitiesDetails.getOtherNclLongTermProvisions()) / debtEbitdaMulti) : 0.0);
			
			//B124 -----> =B56/(B53+B54)
			double freeReserveEq = liabilitiesDetails.getOrdinarySharesCapital() + liabilitiesDetails.getShareWarrentsOutstanding();
			ratioDetailsReq.setFreeReservesEquity(freeReserveEq != 0 ? CommonUtils.checkDouble(liabilitiesDetails.getOtherReservesAndSurplus() / freeReserveEq) : 0.0);
			
			//B125 -----> =B104/B22
			ratioDetailsReq.setCfoMargin(profitAndLossStmntReq.getNetSales() != 0 ? CommonUtils.checkDouble((cashFromOperating/profitAndLossStmntReq.getNetSales()) * 100) : 0.0);
			
			//B126 -----> =(B125-C125)/C125
			ratioDetailsReq.setGrowthInCfoMargin(ratioDtlsForNextYear.getCfoMargin() != 0 ? CommonUtils.checkDouble((ratioDetailsReq.getCfoMargin() - ratioDtlsForNextYear.getCfoMargin() / ratioDtlsForNextYear.getCfoMargin()) * 100) : 0.0);
			
			ratioDetailsReqList.add(ratioDetailsReq);
		}
		response.setRatioDetailsReqList(ratioDetailsReqList);
		return response;
		
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
				e.printStackTrace();
			}
		} else if (!CommonUtils.isObjectNullOrEmpty(applicationMaster.getCurrencyId())){
			currencyAndDenomination = CommonDocumentUtils.getCurrency(applicationMaster.getCurrencyId());
		}
		return currencyAndDenomination;
	}
	
	
	
}
