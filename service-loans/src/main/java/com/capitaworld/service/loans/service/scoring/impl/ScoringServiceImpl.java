package com.capitaworld.service.loans.service.scoring.impl;

import com.capitaworld.service.loans.domain.fundseeker.corporate.*;
import com.capitaworld.service.loans.model.retail.PastFinancialEstimatesDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.fundseeker.corporate.PastFinancialEstiamateDetailsService;
import com.capitaworld.service.loans.service.irr.impl.IrrServiceImpl;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.rating.model.RatingResponse;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.model.FundSeekerInputRequest;
import com.capitaworld.service.scoring.model.ModelParameterResponse;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.ibm.icu.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoringServiceImpl implements ScoringService{


    private final Logger logger = LoggerFactory.getLogger(ScoringServiceImpl.class);

    @Autowired
    OperatingStatementDetailsRepository operatingStatementDetailsRepository;

    @Autowired
    LiabilitiesDetailsRepository liabilitiesDetailsRepository;

    @Autowired
    AssetsDetailsRepository assetsDetailsRepository;

    @Autowired
    BalanceSheetDetailRepository balanceSheetDetailRepository;

    @Autowired
    ProfitibilityStatementDetailRepository profitibilityStatementDetailRepository;

    @Autowired
    DirectorBackgroundDetailsRepository directorBackgroundDetailsRepository;

    @Autowired
    private ScoringClient scoringClient;


    @Override
    public void calculateScoring(Long applicationId) {

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        // CMA
        OperatingStatementDetails operatingStatementDetailsFY = new OperatingStatementDetails();
        OperatingStatementDetails operatingStatementDetailsSY = new OperatingStatementDetails();
        OperatingStatementDetails operatingStatementDetailsTY = new OperatingStatementDetails();

        operatingStatementDetailsTY = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear-1+"");
        operatingStatementDetailsSY = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear-2+"");
        operatingStatementDetailsFY = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear-3+"");

        LiabilitiesDetails liabilitiesDetailsFY = new LiabilitiesDetails();
        LiabilitiesDetails liabilitiesDetailsSY = new LiabilitiesDetails();
        LiabilitiesDetails liabilitiesDetailsTY = new LiabilitiesDetails();

        liabilitiesDetailsTY = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear-1+"");
        liabilitiesDetailsSY = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear-2+"");
        liabilitiesDetailsFY = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear-3+"");

        AssetsDetails assetsDetailsFY = new AssetsDetails();
        AssetsDetails assetsDetailsSY = new AssetsDetails();
        AssetsDetails assetsDetailsTY = new AssetsDetails();

        assetsDetailsTY = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear-1+"");
        assetsDetailsSY = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear-2+"");
        assetsDetailsFY = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear-3+"");


        ///////////////

        Double score = 0.0;
        logger.info("START GET SCORE CORPORATE LOAN PARAMETERS");
        // GET SCORE CORPORATE LOAN PARAMETERS

        // GET SCORE MODEL IDA
        BigInteger scoreModelId = null;

        if(!CommonUtils.isObjectNullOrEmpty(scoreModelId))
        {
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId.longValue());

            // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
            ScoringResponse scoringResponse=null;
            try {
                scoringResponse = scoringClient.listField(scoringRequest);
            }
            catch (Exception e) {
                logger.error("error while getting field list");
                e.printStackTrace();
            }

            List<ModelParameterResponse> dataList = scoringResponse.getDataList();

            List<FundSeekerInputRequest> fundSeekerInputRequestList = new ArrayList<>(dataList.size());

            int i = 0;
            for (ModelParameterResponse modelParameterResponse : dataList) {
                FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                fundSeekerInputRequest.setName(modelParameterResponse.getName());

                Map<String, Object> map = new HashMap<>();
                switch (modelParameterResponse.getName()) {

                    case "COMBINED_NETWORTH":
                    {
                        Double networthSum = directorBackgroundDetailsRepository.getSumOfDirectorsNetworth(applicationId);
                        if (CommonUtils.isObjectNullOrEmpty(networthSum))
                            networthSum = 0.0;

                        map.put("NETWORTH_SUM", networthSum);

                        Double termLoans = liabilitiesDetailsTY.getTermLoans();
                        if (CommonUtils.isObjectNullOrEmpty(termLoans))
                            termLoans = 0.0;

                        map.put("EXISTING_LONGTERM_BORROWINGS", termLoans);


                        Double loanAmount = 0.0; // remaining

                        map.put("LOAN_AMOUNT", loanAmount);


                        break;
                    }
                    case "CUSTOMER_ASSOCIATE_CONCERN":
                    {
                        // remaining
                        break;

                    }
                    case "CIBIL_TRANSUNION_SCORE":
                    {
                        Double avgCibilScorePromotor=0.0; // remaining
                        break;
                    }

                    case "EXPERIENCE_IN_THE_BUSINESS": {
                        // remaining
                        break;
                    }
                    case "DEBT_EQUITY_RATIO": {

                        Double debt = liabilitiesDetailsTY.getSubTotalA() +
                                liabilitiesDetailsTY.getShortTermBorrowingFromOthers() +
                                liabilitiesDetailsTY.getTotalTermLiabilities() -
                                liabilitiesDetailsTY.getPreferencesShares() +
                                liabilitiesDetailsTY.getOtherNclUnsecuredLoansFromOther() +
                                liabilitiesDetailsTY.getOtherNclOthers() +
                                liabilitiesDetailsTY.getMinorityInterest() +
                                liabilitiesDetailsTY.getDeferredTaxLiability();
                        if (CommonUtils.isObjectNullOrEmpty(debt))
                            debt = 0.0;

                        map.put("DEBT", debt);

                        Double equity = liabilitiesDetailsTY.getPreferencesShares() +
                                liabilitiesDetailsTY.getNetWorth() -
                                liabilitiesDetailsTY.getMinorityInterest() -
                                liabilitiesDetailsTY.getDeferredTaxLiability();
                        if (CommonUtils.isObjectNullOrEmpty(debt))
                            equity = 0.0;

                        map.put("EQUITY", equity);

                        break;
                    }
                    case "TOL_TNW ": {
                        Double tol = liabilitiesDetailsTY.getTotalOutsideLiabilities();
                        if (CommonUtils.isObjectNullOrEmpty(tol))
                            tol = 0.0;

                        map.put("TOL", tol);

                        Double loanAmount = 0.0; // remaining

                        map.put("LOAN_AMOUNT", loanAmount);

                        Double tnw = assetsDetailsTY.getTangibleNetWorth();
                        if (CommonUtils.isObjectNullOrEmpty(tnw))
                            tnw = 0.0;

                        map.put("TNW", tnw);

                        break;
                    }
                    case "AVERAGE_CURRENT_RATIO": {
                        Double currentRatio = assetsDetailsTY.getCurrentRatio() + assetsDetailsSY.getCurrentRatio();
                        if (CommonUtils.isObjectNullOrEmpty(currentRatio))
                            currentRatio = 0.0;

                        map.put("CURRENT_RATIO", currentRatio);

                        break;
                    }
                    case "WORKING_CAPITAL_CYCLE": {
                        Double debtorsDays = ((assetsDetailsTY.getReceivableOtherThanDefferred() + assetsDetailsTY.getExportReceivables()) / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
                        if (CommonUtils.isObjectNullOrEmpty(debtorsDays))
                            debtorsDays = 0.0;

                        map.put("DEBTORS_DAYS", debtorsDays);

                        /////////////

                        Double averageInventory = (operatingStatementDetailsTY.getAddOperatingStockFg() + operatingStatementDetailsTY.getDeductClStockFg()) / 2;
                        if (CommonUtils.isObjectNullOrEmpty(averageInventory))
                            averageInventory = 0.0;

                        Double cogs = operatingStatementDetailsTY.getRawMaterials() + operatingStatementDetailsTY.getAddOperatingStockFg() - operatingStatementDetailsTY.getDeductClStockFg();
                        if (CommonUtils.isObjectNullOrEmpty(cogs))
                            cogs = 0.0;


                        /////////////

                        Double inventoryDays = (averageInventory / cogs) * 365;
                        if (CommonUtils.isObjectNullOrEmpty(inventoryDays))
                            inventoryDays = 0.0;

                        map.put("INVENTORY_DAYS", inventoryDays);

                        Double creditorsDays = (liabilitiesDetailsTY.getSundryCreditors() / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
                        if (CommonUtils.isObjectNullOrEmpty(creditorsDays))
                            creditorsDays = 0.0;

                        map.put("CREDITORS_DAYS", creditorsDays);

                        break;
                    }
                    case "AVERAGE_ANNUAL_GROWTH_GROSS_CASH": {
                        Double netProfitOrLossTY = operatingStatementDetailsTY.getNetProfitOrLoss();
                        if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossTY))
                            netProfitOrLossTY = 0.0;
                        Double depreciationTy = operatingStatementDetailsTY.getDepreciation();
                        if (CommonUtils.isObjectNullOrEmpty(depreciationTy))
                            depreciationTy = 0.0;
                        Double provisionForDeferredTaxTy = operatingStatementDetailsTY.getProvisionForDeferredTax();
                        if (CommonUtils.isObjectNullOrEmpty(provisionForDeferredTaxTy))
                            provisionForDeferredTaxTy = 0.0;

                        Double netProfitOrLossSY = operatingStatementDetailsSY.getNetProfitOrLoss();
                        if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossSY))
                            netProfitOrLossSY = 0.0;
                        Double depreciationSy = operatingStatementDetailsSY.getDepreciation();
                        if (CommonUtils.isObjectNullOrEmpty(depreciationSy))
                            depreciationSy = 0.0;
                        Double provisionForDeferredTaxSy = operatingStatementDetailsSY.getProvisionForDeferredTax();
                        if (CommonUtils.isObjectNullOrEmpty(provisionForDeferredTaxSy))
                            provisionForDeferredTaxSy = 0.0;

                        Double netProfitOrLossFY = operatingStatementDetailsFY.getNetProfitOrLoss();
                        if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossFY))
                            netProfitOrLossFY = 0.0;
                        Double depreciationFy = operatingStatementDetailsFY.getDepreciation();
                        if (CommonUtils.isObjectNullOrEmpty(depreciationFy))
                            depreciationFy = 0.0;
                        Double provisionForDeferredTaxFy = operatingStatementDetailsFY.getProvisionForDeferredTax();
                        if (CommonUtils.isObjectNullOrEmpty(provisionForDeferredTaxFy))
                            provisionForDeferredTaxFy = 0.0;


                        Double avgAnnualGrowthGrossCash = (((((netProfitOrLossTY + depreciationTy + provisionForDeferredTaxTy) - (netProfitOrLossSY + depreciationSy + provisionForDeferredTaxSy)) / (netProfitOrLossTY + depreciationTy + provisionForDeferredTaxTy)) * 100) + ((((netProfitOrLossSY + depreciationSy + provisionForDeferredTaxSy) - (netProfitOrLossFY + depreciationFy + provisionForDeferredTaxFy)) / (netProfitOrLossSY + depreciationSy + provisionForDeferredTaxSy)) * 100)) / 2;
                        if (CommonUtils.isObjectNullOrEmpty(avgAnnualGrowthGrossCash))
                            avgAnnualGrowthGrossCash = 0.0;

                        map.put("AVERAGE_ANNUAL_GROWTH_GROSS_CASH", avgAnnualGrowthGrossCash);

                        break;
                    }
                    case "AVERAGE_ANNUAL_GROWTH_NET_SALE": {

                        Double domesticSalesTy = operatingStatementDetailsTY.getDomesticSales();
                        if (CommonUtils.isObjectNullOrEmpty(domesticSalesTy))
                            domesticSalesTy = 0.0;
                        Double exportSalesTy = operatingStatementDetailsTY.getExportSales();
                        if (CommonUtils.isObjectNullOrEmpty(exportSalesTy))
                            exportSalesTy = 0.0;

                        Double domesticSalesSy = operatingStatementDetailsSY.getDomesticSales();
                        if (CommonUtils.isObjectNullOrEmpty(domesticSalesSy))
                            domesticSalesSy = 0.0;

                        Double exportSalesSy = operatingStatementDetailsSY.getExportSales();
                        if (CommonUtils.isObjectNullOrEmpty(exportSalesSy))
                            exportSalesSy = 0.0;


                        Double domesticSalesFy = operatingStatementDetailsFY.getDomesticSales();
                        if (CommonUtils.isObjectNullOrEmpty(domesticSalesFy))
                            domesticSalesFy = 0.0;

                        Double exportSalesFy = operatingStatementDetailsFY.getExportSales();
                        if (CommonUtils.isObjectNullOrEmpty(exportSalesFy))
                            exportSalesFy = 0.0;

                        Double avgAnnualGrowthNetSale = (((((domesticSalesTy + exportSalesTy) - (domesticSalesSy + exportSalesSy)) / (domesticSalesTy + exportSalesTy)) * 100) + ((((domesticSalesSy + exportSalesSy) - (domesticSalesFy + exportSalesFy)) / (domesticSalesSy + exportSalesSy)) * 100)) / 2;
                        if (CommonUtils.isObjectNullOrEmpty(avgAnnualGrowthNetSale))
                            avgAnnualGrowthNetSale = 0.0;

                        map.put("AVERAGE_ANNUAL_GROWTH_NET_SALE", avgAnnualGrowthNetSale);

                        break;
                    }
                    case "AVERAGE_EBIDTA": {
                        Double profitBeforeTaxOrLossTy = operatingStatementDetailsTY.getProfitBeforeTaxOrLoss();
                        if (CommonUtils.isObjectNullOrEmpty(profitBeforeTaxOrLossTy))
                            profitBeforeTaxOrLossTy = 0.0;

                        Double interestTy = operatingStatementDetailsTY.getInterest();
                        if (CommonUtils.isObjectNullOrEmpty(interestTy))
                            interestTy = 0.0;

                        Double profitBeforeTaxOrLossSy = operatingStatementDetailsSY.getProfitBeforeTaxOrLoss();
                        if (CommonUtils.isObjectNullOrEmpty(profitBeforeTaxOrLossSy))
                            profitBeforeTaxOrLossSy = 0.0;

                        Double interestSy = operatingStatementDetailsSY.getInterest();
                        if (CommonUtils.isObjectNullOrEmpty(interestSy))
                            interestSy = 0.0;

                        Double depreciationTy = operatingStatementDetailsTY.getDepreciation();
                        if (CommonUtils.isObjectNullOrEmpty(depreciationTy))
                            depreciationTy = 0.0;

                        Double depreciationSy = operatingStatementDetailsSY.getDepreciation();
                        if (CommonUtils.isObjectNullOrEmpty(depreciationSy))
                            depreciationSy = 0.0;




                        Double avgEBIDTA = ((profitBeforeTaxOrLossTy + interestTy + depreciationTy) + (profitBeforeTaxOrLossSy + interestSy + depreciationSy)) / 2;

                        map.put("AVERAGE_EBIDTA", avgEBIDTA);

                        Double termLoansTy = liabilitiesDetailsTY.getTermLoans();
                        if (CommonUtils.isObjectNullOrEmpty(termLoansTy))
                            termLoansTy = 0.0;

                        Double loanAmount = 0.0; // remaining

                        Double termLoansEBIDTA = termLoansTy + loanAmount;

                        map.put("TERMLOANS_EBIDTA", termLoansEBIDTA);


                        break;
                    }
                    case "AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS": {

                        Double netProfitOrLossTY = operatingStatementDetailsTY.getNetProfitOrLoss();
                        if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossTY))
                            netProfitOrLossTY = 0.0;

                        Double netProfitOrLossSY = operatingStatementDetailsSY.getNetProfitOrLoss();
                        if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossSY))
                            netProfitOrLossSY = 0.0;

                        Double provisionForDeferredTaxTy = operatingStatementDetailsTY.getProvisionForDeferredTax();
                        if (CommonUtils.isObjectNullOrEmpty(provisionForDeferredTaxTy))
                            provisionForDeferredTaxTy = 0.0;

                        Double provisionForDeferredTaxSy = operatingStatementDetailsSY.getProvisionForDeferredTax();
                        if (CommonUtils.isObjectNullOrEmpty(provisionForDeferredTaxSy))
                            provisionForDeferredTaxSy = 0.0;

                        Double depreciationTy = operatingStatementDetailsTY.getDepreciation();
                        if (CommonUtils.isObjectNullOrEmpty(depreciationTy))
                            depreciationTy = 0.0;

                        Double depreciationSy = operatingStatementDetailsSY.getDepreciation();
                        if (CommonUtils.isObjectNullOrEmpty(depreciationSy))
                            depreciationSy = 0.0;

                        Double avgGrossCashAccruals = ((netProfitOrLossTY + depreciationTy + provisionForDeferredTaxTy) + (netProfitOrLossSY + depreciationSy + provisionForDeferredTaxSy)) / 2;
                        if (CommonUtils.isObjectNullOrEmpty(avgGrossCashAccruals))
                            avgGrossCashAccruals = 0.0;

                        map.put("AVERAGE_GROSS_CASH_ACCRUALS", avgGrossCashAccruals);


                        Double totalAsset = assetsDetailsTY.getTotalAssets();
                        if (CommonUtils.isObjectNullOrEmpty(totalAsset))
                            totalAsset = 0.0;

                        map.put("TOTAL_ASSET", totalAsset);


                        break;
                    }
                    case "AVERAGE_INTEREST_COV_RATIO": {
                        Double opProfitBeforeIntrestTy = operatingStatementDetailsTY.getOpProfitBeforeIntrest();
                        if (CommonUtils.isObjectNullOrEmpty(opProfitBeforeIntrestTy))
                            opProfitBeforeIntrestTy = 0.0;


                        Double opProfitBeforeIntrestSy = operatingStatementDetailsSY.getOpProfitBeforeIntrest();
                        if (CommonUtils.isObjectNullOrEmpty(opProfitBeforeIntrestSy))
                            opProfitBeforeIntrestSy = 0.0;

                        Double interestTy = operatingStatementDetailsTY.getInterest();
                        if (CommonUtils.isObjectNullOrEmpty(interestTy))
                            interestTy = 0.0;

                        Double interestSy = operatingStatementDetailsSY.getInterest();
                        if (CommonUtils.isObjectNullOrEmpty(interestSy))
                            interestSy = 0.0;

                        Double avgInterestCovRatio = ((opProfitBeforeIntrestTy / interestTy) + (opProfitBeforeIntrestSy / interestSy)) / 2;
                        if (CommonUtils.isObjectNullOrEmpty(avgInterestCovRatio))
                            avgInterestCovRatio = 0.0;

                        map.put("AVERAGE_INTEREST_COV_RATIO", avgInterestCovRatio);

                        break;
                    }
                    case "NO_OF_CUSTOMER": {
                        // remaining

                        break;
                    }
                    case "CONCENTRATION_CUSTOMER": {
                        // remaining

                        break;
                    }


                }

                fundSeekerInputRequest.setMap(map);
                fundSeekerInputRequestList.add(fundSeekerInputRequest);
                i = i + 1;
            }

            scoringRequest.setDataList(fundSeekerInputRequestList);

            ScoringResponse calculateScore=null;
            try {
                 calculateScore = scoringClient.calculateScore(scoringRequest);
            }
            catch (Exception e) {
                logger.error("error while calling scoring");
                e.printStackTrace();
            }

            if (!com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(calculateScore)) {
                score = calculateScore.getScore();
                logger.info("Total Use score from scoring module------->" + score);
            }
        }
    }

}
