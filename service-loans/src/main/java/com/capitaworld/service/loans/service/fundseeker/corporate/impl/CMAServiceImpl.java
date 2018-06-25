package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.model.corporate.AssetsDetailsRequest;
import com.capitaworld.service.loans.model.corporate.CMARequest;
import com.capitaworld.service.loans.model.corporate.LiabilitiesDetailsRequest;
import com.capitaworld.service.loans.model.corporate.OperatingStatementDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
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
	
	public FinancialRequest getFinancialDetailsForBankIntegration(Long applicationId) {
		
		FinancialRequest response = new FinancialRequest();
		response.setApplicationId(applicationId);
		List<OperatingStatementDetails> operatingStatementList = operatingStatementDetailsRepository.getByApplicationId(applicationId);
		List<ProfitAndLossStmntReq> profiltAndLossStmntReqList = new ArrayList<>(operatingStatementList.size());
		ProfitAndLossStmntReq prlossStmntReq = null;
		for(OperatingStatementDetails operatingStatement : operatingStatementList) {
			prlossStmntReq = new ProfitAndLossStmntReq();
			prlossStmntReq.setApplicationId(applicationId);
			prlossStmntReq.setCurrency(getCurrency(applicationId));
			prlossStmntReq.setSubTotalOfIncome(operatingStatement.getSubTotalOfIncome());
			prlossStmntReq.setTotalGrossSales(operatingStatement.getTotalGrossSales());
			prlossStmntReq.setLessExciseDuty(operatingStatement.getLessExciseDuty());
			prlossStmntReq.setNetSales(operatingStatement.getNetSales());
			//(D38-D42)+(D46-D50)
			prlossStmntReq.setInDecStock((CommonUtils.checkDoubleNull(operatingStatement.getAddOperatingStock()) - CommonUtils.checkDoubleNull(operatingStatement.getDeductStockInProcess()))
					+ (CommonUtils.checkDoubleNull(operatingStatement.getAddOperatingStockFg()) - CommonUtils.checkDoubleNull(operatingStatement.getDeductClStockFg())));
			prlossStmntReq.setRawMaterials(operatingStatement.getRawMaterials());
			prlossStmntReq.setPowerAndFuel(operatingStatement.getPowerAndFuel());
			prlossStmntReq.setEmployeeCost(operatingStatement.getDirectLabour());
			prlossStmntReq.setOtherMfgExpenses(operatingStatement.getOtherMfgExpenses());
			prlossStmntReq.setGeneralAdminExp(operatingStatement.getGeneralAdminExp());
			prlossStmntReq.setSellingAndDistributionExpenses(operatingStatement.getSellingAndDistributionExpenses());
			prlossStmntReq.setMiscellaneousExpenses(operatingStatement.getOtherMfgExpenses());
			prlossStmntReq.setExpensesAmortised(operatingStatement.getExpensesAmortised());
			prlossStmntReq.setTotalExpenditure(0.0);
			prlossStmntReq.setOperatingProfitOI(0.0);
			prlossStmntReq.setAddOtherRevenueIncome(operatingStatement.getAddOtherRevenueIncome());
			prlossStmntReq.setOperatingProfitEBITDA(0.0);
			prlossStmntReq.setInterest(operatingStatement.getInterest());
			prlossStmntReq.setPbdt(0.0);
			prlossStmntReq.setDepreciation(operatingStatement.getDepreciation());
			prlossStmntReq.setProfitBeforeTaxAndExpItems(0.0);
			prlossStmntReq.setExceptionalIncomeExp(operatingStatement.getNetofNonOpIncomeOrExpenses());
			prlossStmntReq.setProfitBeforeTax(0.0);
			//Operating Statement: C75+C77
			prlossStmntReq.setProvisionForTaxes(CommonUtils.checkDoubleNull(operatingStatement.getProvisionForTaxes())  + CommonUtils.checkDoubleNull(operatingStatement.getProfitBeforeTaxOrLoss()));
			prlossStmntReq.setProfitAfterTax(0.0);
			prlossStmntReq.setDividendRate(operatingStatement.getEquityDeividendPaidAmt());
			prlossStmntReq.setEquityDividend(0.0);
			prlossStmntReq.setEarningsPerShare(0.0);
			profiltAndLossStmntReqList.add(prlossStmntReq);
		}
		response.setProfiltAndLossStmntReqList(profiltAndLossStmntReqList);
		
		List<LiabilitiesDetails> liabilitiesDetailList = liabilitiesDetailsRepository.getByApplicationId(applicationId);
		List<BalanceSheetLiabilitiesReq> liabilitiesReqList = new ArrayList<>(liabilitiesDetailList.size());
		BalanceSheetLiabilitiesReq liabilitiesReq = null;
		for(LiabilitiesDetails liabilitiesDetails : liabilitiesDetailList) {
			liabilitiesReq = new BalanceSheetLiabilitiesReq();
			liabilitiesReq.setApplicationId(applicationId);
			liabilitiesReq.setCurrency(getCurrency(applicationId));
			liabilitiesReq.setOrdinarySharesCapital(liabilitiesDetails.getOrdinarySharesCapital());
			liabilitiesReq.setShareWarrentsOutstanding(liabilitiesDetails.getShareWarrentsOutstanding());
			liabilitiesReq.setRevaluationReservse(liabilitiesDetails.getRevaluationReservse());
			
			
			//Liabilities: C73+C77+C79+C83
			//23. General Reserve Add 25. Other reserves [excluding provisions] Add 26. Surplus(+) or Deficit(-) in Profit & Loss Account. Add 27 b. Others [specify] 
			liabilitiesReq.setOtherReservesAndSurplus(CommonUtils.checkDoubleNull(liabilitiesDetails.getGeneralReserve()) + CommonUtils.checkDoubleNull(liabilitiesDetails.getOtherReservse()) 
			+ CommonUtils.checkDoubleNull(liabilitiesDetails.getSurplusOrDeficit()) + CommonUtils.checkDoubleNull(liabilitiesDetails.getOthers()));
			liabilitiesReq.setShareholderFunds(0.0);
			liabilitiesReq.setMinorityInterest(liabilitiesDetails.getMinorityInterest());
			liabilitiesReq.setTermLiabilitiesSecured(liabilitiesDetails.getTermLiabilitiesSecured());
			liabilitiesReq.setOtherNclUnsecuredLoansFromPromoters(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters());
			liabilitiesReq.setUnsecuredLoansOthers(0.0);
			liabilitiesReq.setDeferredTaxLiability(liabilitiesDetails.getDeferredTaxLiability());
			liabilitiesReq.setOtherLongTermLiabilities(0.0);
			liabilitiesReq.setOtherBorrowings(0.0);
			liabilitiesReq.setOtherNclLongTermProvisions(liabilitiesDetails.getOtherNclLongTermProvisions());
			liabilitiesReq.setTotalNonCurrentLiabilities(0.0);
			liabilitiesReq.setSundryCreditors(liabilitiesDetails.getSundryCreditors());
			liabilitiesReq.setOtherCurrentLiabilities(0.0);
			liabilitiesReq.setProvisionalForTaxation(liabilitiesDetails.getProvisionalForTaxation());
			liabilitiesReq.setTotalCurrentLiabilities(0.0);
			liabilitiesReq.setTotalLiabilities(0.0);
			liabilitiesReqList.add(liabilitiesReq);
		}
		
		response.setBalanceSheetLiabilitiesReqList(liabilitiesReqList);
		
		
		List<AssetsDetails> assetsDetailsList = assetsDetailsRepository.getByApplicationId(applicationId);
		List<BalanceSheetAssetReq> bsAssetReqList = new ArrayList<>(assetsDetailsList.size());
		BalanceSheetAssetReq bsAssetReq = null;
		for(AssetsDetails assetsDetails : assetsDetailsList) {
			bsAssetReq = new BalanceSheetAssetReq();
			bsAssetReq.setApplicationId(applicationId);
			bsAssetReq.setCurrency(getCurrency(applicationId));
			bsAssetReq.setGrossBlock(assetsDetails.getGrossBlock());
			bsAssetReq.setDepreciationToDate(assetsDetails.getDepreciationToDate());
			bsAssetReq.setImpairmentAsset(assetsDetails.getImpairmentAsset());
			bsAssetReq.setNetBlock(assetsDetails.getNetBlock());
			bsAssetReq.setOtherNcaOtherCapitalWorkInprogress(assetsDetails.getOtherNcaOtherCapitalWorkInprogress());
			bsAssetReq.setIntangibleAssets(assetsDetails.getIntangibleAssets());
			bsAssetReq.setOthersPreOperativeExpensesPending(assetsDetails.getOthersPreOperativeExpensesPending());
			bsAssetReq.setOthersAssetsInTransit(assetsDetails.getOthersAssetsInTransit());
			bsAssetReq.setInvestmentsInSubsidiary(assetsDetails.getInvestmentsInSubsidiary());
			
			//Asset: D65+D69+D74
			//44. Investments/book debts/advances/deposits which are not Current Assets((b) Others Add [iii]  Deferred receivables [maturity exceeding 1 yr] Add [iv]  Others (Others) )
			bsAssetReq.setOtherInvestments(CommonUtils.checkDoubleNull(assetsDetails.getInvestmentsOrBookDebts()) + CommonUtils.checkDoubleNull(assetsDetails.getDeferredReceviables())
			+ CommonUtils.checkDoubleNull(assetsDetails.getOthersOther()));
			
			bsAssetReq.setAdvanceToSuppliersCapitalGoods(assetsDetails.getAdvanceToSuppliersCapitalGoods());
			bsAssetReq.setOtherNonCurrentAssets(0.0);
			bsAssetReq.setTotalNonCurrentAssets(0.0);
			bsAssetReq.setInventory(assetsDetails.getInventory());
			bsAssetReq.setSundryDebtors(0.0);
			bsAssetReq.setCashAndBankBalance(assetsDetails.getCashAndBankBalance());
			bsAssetReq.setOtherCurrentAssets(assetsDetails.getOtherCurrentAssets());
			bsAssetReq.setShortTermLoansandAdvances(0.0);
			bsAssetReq.setTotalCurrentAssets(0.0);
			bsAssetReq.setTotalAssets(0.0);
			bsAssetReq.setContingentLiabilities(0.0);
			bsAssetReq.setBookValue(0.0);
			bsAssetReqList.add(bsAssetReq);
		}
		
		response.setBalanceSheetAssetReqList(bsAssetReqList);
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
