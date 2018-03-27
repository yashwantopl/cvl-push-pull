package com.capitaworld.service.loans.service.scoring.impl;

import com.capitaworld.service.loans.domain.fundseeker.corporate.*;
import com.capitaworld.service.loans.model.retail.PastFinancialEstimatesDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.fundseeker.corporate.PastFinancialEstiamateDetailsService;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.rating.model.RatingResponse;
import com.ibm.icu.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ScoringServiceImpl implements ScoringService{


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


        ///// start getting COMBINED_NETWORTH  /////

        Double networthSum=directorBackgroundDetailsRepository.getSumOfDirectorsNetworth(applicationId);
        if(CommonUtils.isObjectNullOrEmpty(networthSum))
            networthSum=0.0;

        Double termLoans=0.0;
        termLoans=liabilitiesDetailsTY.getTermLoans();

        if(CommonUtils.isObjectNullOrEmpty(termLoans))
            termLoans=0.0;

        Double loanAmount=0.0; // remaining



        ///// end getting COMBINED_NETWORTH /////

        ///// start CUSTOMER_ASSOCIATE_CONCERN /////

            // remaining

        ///// end CUSTOMER_ASSOCIATE_CONCERN /////


        ///// start CIBIL_TRANSUNION_SCORE  /////

            Double avgCibilScorePromotor=0.0; // remaining

        ///// end CIBIL_TRANSUNION_SCORE /////


        ///// start EXPERIENCE_IN_THE_BUSINESS /////

            // remaining

        ///// end EXPERIENCE_IN_THE_BUSINESS /////


        ///// start DEBT_EQUITY_RATIO  /////

        Double debt=0.0;
        Double equity=0.0;

        debt=liabilitiesDetailsTY.getSubTotalA()+
                liabilitiesDetailsTY.getShortTermBorrowingFromOthers()+
                liabilitiesDetailsTY.getTotalTermLiabilities() -
                liabilitiesDetailsTY.getPreferencesShares()+
                liabilitiesDetailsTY.getOtherNclUnsecuredLoansFromOther()+
                liabilitiesDetailsTY.getOtherNclOthers()+
                liabilitiesDetailsTY.getMinorityInterest()+
                liabilitiesDetailsTY.getDeferredTaxLiability();

        equity=liabilitiesDetailsTY.getPreferencesShares()+
                liabilitiesDetailsTY.getNetWorth()-
                liabilitiesDetailsTY.getMinorityInterest()-
                liabilitiesDetailsTY.getDeferredTaxLiability();


        if(CommonUtils.isObjectNullOrEmpty(debt))
            debt=0.0;

        if(CommonUtils.isObjectNullOrEmpty(debt))
            equity=0.0;

        ///// end DEBT_EQUITY_RATIO /////


        ///// start TOL_TNW  /////

        Double totalOutsideLiabilities=0.0;
        Double tangibleNetworth=0.0;
        Double tol=0.0;
        Double tnw=0.0;

        loanAmount=0.0; // remaining

        tol=liabilitiesDetailsTY.getTotalOutsideLiabilities();
        tnw=assetsDetailsTY.getTangibleNetWorth();

        if(CommonUtils.isObjectNullOrEmpty(tol))
            tol=0.0;

        if(CommonUtils.isObjectNullOrEmpty(tnw))
            tnw=0.0;


        ///// end TOL_TNW  /////


        ///// start AVERAGE_CURRENT_RATIO  /////

        Double currentRatio=0.0;

        currentRatio=assetsDetailsTY.getCurrentRatio()+assetsDetailsSY.getCurrentRatio();

        if(CommonUtils.isObjectNullOrEmpty(currentRatio))
        {
            currentRatio=0.0;
        }


        ///// end AVERAGE_CURRENT_RATIO  /////

        ///// start WORKING_CAPITAL_CYCLE  /////

        Double debtorsDays=((assetsDetailsTY.getReceivableOtherThanDefferred()+assetsDetailsTY.getExportReceivables()) / (operatingStatementDetailsTY.getTotalGrossSales()-operatingStatementDetailsTY.getAddOtherRevenueIncome())) *365;

        if(CommonUtils.isObjectNullOrEmpty(debtorsDays))
        {
            debtorsDays=0.0;
        }
        /////////////
            Double averageInventory=(operatingStatementDetailsTY.getAddOperatingStockFg()+operatingStatementDetailsTY.getDeductClStockFg())/2;

            if(CommonUtils.isObjectNullOrEmpty(averageInventory))
            {
                averageInventory=0.0;
            }

            Double cogs=operatingStatementDetailsTY.getRawMaterials()+operatingStatementDetailsTY.getAddOperatingStockFg()-operatingStatementDetailsTY.getDeductClStockFg();

            if(CommonUtils.isObjectNullOrEmpty(cogs))
            {
                cogs=0.0;
            }
        /////////////

        Double inventoryDays=(averageInventory/cogs)*365;

        Double creditorsDays=(liabilitiesDetailsTY.getSundryCreditors()/(operatingStatementDetailsTY.getTotalGrossSales()-operatingStatementDetailsTY.getAddOtherRevenueIncome())) *365;

        if(CommonUtils.isObjectNullOrEmpty(inventoryDays))
        {
            inventoryDays=0.0;
        }

        if(CommonUtils.isObjectNullOrEmpty(creditorsDays))
        {
            creditorsDays=0.0;
        }

        ///// end WORKING_CAPITAL_CYCLE  /////


        ///// start AVERAGE_ANNUAL_GROWTH_GROSS_CASH  /////

        Double netProfitOrLossTY=operatingStatementDetailsTY.getNetProfitOrLoss();
        if(CommonUtils.isObjectNullOrEmpty(netProfitOrLossTY))
            netProfitOrLossTY=0.0;
        Double depreciationTy=operatingStatementDetailsTY.getDepreciation();
        if(CommonUtils.isObjectNullOrEmpty(depreciationTy))
            depreciationTy=0.0;
        Double provisionForDeferredTaxTy=operatingStatementDetailsTY.getProvisionForDeferredTax();
        if(CommonUtils.isObjectNullOrEmpty(netProfitOrLossTY))
            netProfitOrLossTY=0.0;

        Double netProfitOrLossSY=operatingStatementDetailsSY.getNetProfitOrLoss();
        if(CommonUtils.isObjectNullOrEmpty(netProfitOrLossSY))
            netProfitOrLossSY=0.0;
        Double depreciationSy=operatingStatementDetailsSY.getDepreciation();
        if(CommonUtils.isObjectNullOrEmpty(depreciationSy))
            depreciationSy=0.0;
        Double provisionForDeferredTaxSy=operatingStatementDetailsSY.getProvisionForDeferredTax();
        if(CommonUtils.isObjectNullOrEmpty(provisionForDeferredTaxSy))
            provisionForDeferredTaxSy=0.0;

        Double netProfitOrLossFY=operatingStatementDetailsFY.getNetProfitOrLoss();
        if(CommonUtils.isObjectNullOrEmpty(netProfitOrLossFY))
            netProfitOrLossFY=0.0;
        Double depreciationFy=operatingStatementDetailsFY.getDepreciation();
        if(CommonUtils.isObjectNullOrEmpty(depreciationFy))
            depreciationFy=0.0;
        Double provisionForDeferredTaxFy=operatingStatementDetailsFY.getProvisionForDeferredTax();
        if(CommonUtils.isObjectNullOrEmpty(provisionForDeferredTaxFy))
            provisionForDeferredTaxFy=0.0;


        Double avgAnnualGrowthGrossCash=     (( (((netProfitOrLossTY + depreciationTy +provisionForDeferredTaxTy) - (netProfitOrLossSY + depreciationSy+provisionForDeferredTaxSy)) / (netProfitOrLossTY + depreciationTy +provisionForDeferredTaxTy))*100 ) + ( (((netProfitOrLossSY + depreciationSy+ provisionForDeferredTaxSy) - (netProfitOrLossFY + depreciationFy + provisionForDeferredTaxFy)) / (netProfitOrLossSY + depreciationSy+provisionForDeferredTaxSy))*100 ) ) / 2;

        ///// end AVERAGE_ANNUAL_GROWTH_GROSS_CASH  /////

        ///// start AVERAGE_ANNUAL_GROWTH_NET_SALE  /////

        Double domesticSalesTy=operatingStatementDetailsTY.getDomesticSales();
        if(CommonUtils.isObjectNullOrEmpty(domesticSalesTy))
            domesticSalesTy=0.0;
        Double exportSalesTy=operatingStatementDetailsTY.getExportSales();
        if(CommonUtils.isObjectNullOrEmpty(exportSalesTy))
            exportSalesTy=0.0;

        Double domesticSalesSy=operatingStatementDetailsSY.getDomesticSales();
        if(CommonUtils.isObjectNullOrEmpty(domesticSalesSy))
            domesticSalesSy=0.0;

        Double exportSalesSy=operatingStatementDetailsSY.getExportSales();
        if(CommonUtils.isObjectNullOrEmpty(exportSalesSy))
            exportSalesSy=0.0;


        Double domesticSalesFy=operatingStatementDetailsFY.getDomesticSales();
        if(CommonUtils.isObjectNullOrEmpty(domesticSalesFy))
            domesticSalesFy=0.0;

        Double exportSalesFy=operatingStatementDetailsFY.getExportSales();
        if(CommonUtils.isObjectNullOrEmpty(exportSalesFy))
            exportSalesFy=0.0;

        Double avgAnnualGrowthNetSale = ( ( ((( domesticSalesTy + exportSalesTy ) - ( domesticSalesSy + exportSalesSy )) / (domesticSalesTy + exportSalesTy))*100 ) + ( (((domesticSalesSy + exportSalesSy) - (domesticSalesFy + exportSalesFy)) / (domesticSalesSy + exportSalesSy))*100 ) ) / 2;

        ///// end AVERAGE_ANNUAL_GROWTH_NET_SALE  /////


        ///// start AVERAGE_EBIDTA  /////

        Double profitBeforeTaxOrLossTy=operatingStatementDetailsTY.getProfitBeforeTaxOrLoss();
        if(CommonUtils.isObjectNullOrEmpty(profitBeforeTaxOrLossTy))
            profitBeforeTaxOrLossTy=0.0;

        Double interestTy=operatingStatementDetailsTY.getInterest();
        if(CommonUtils.isObjectNullOrEmpty(interestTy))
            interestTy=0.0;

        //Double depreciationTy=operatingStatementDetailsTY.getDepreciation();


        Double profitBeforeTaxOrLossSy=operatingStatementDetailsSY.getProfitBeforeTaxOrLoss();
        if(CommonUtils.isObjectNullOrEmpty(profitBeforeTaxOrLossSy))
            profitBeforeTaxOrLossSy=0.0;

        Double interestSy=operatingStatementDetailsSY.getInterest();
        if(CommonUtils.isObjectNullOrEmpty(interestSy))
            interestSy=0.0;

        //Double depreciationSy=operatingStatementDetailsSY.getDepreciation();


        Double avgEbidta= ((profitBeforeTaxOrLossTy + interestTy + depreciationTy) + (profitBeforeTaxOrLossSy + interestSy + depreciationSy)) / 2;

        Double termLoansTy=liabilitiesDetailsTY.getTermLoans();
        if(CommonUtils.isObjectNullOrEmpty(termLoansTy))
            termLoansTy=0.0;

        Double termLoansEBIDTA= termLoansTy + loanAmount;



        ///// end AVERAGE_EBIDTA  /////


        ///// start AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS  /////

        Double avgGrossCashAccruals= ((netProfitOrLossTY + depreciationTy +provisionForDeferredTaxTy) + (netProfitOrLossSY + depreciationSy +provisionForDeferredTaxSy)) / 2;
        if(CommonUtils.isObjectNullOrEmpty(avgGrossCashAccruals))
            avgGrossCashAccruals=0.0;


        Double totalAsset=assetsDetailsTY.getTotalAssets();
        if(CommonUtils.isObjectNullOrEmpty(totalAsset))
            totalAsset=0.0;



        ///// end AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS  /////


        ///// start AVERAGE_INTEREST_COV_RATIO  /////

        Double opProfitBeforeIntrestTy=operatingStatementDetailsTY.getOpProfitBeforeIntrest();
        if(CommonUtils.isObjectNullOrEmpty(opProfitBeforeIntrestTy))
            opProfitBeforeIntrestTy=0.0;



        Double opProfitBeforeIntrestSy=operatingStatementDetailsSY.getOpProfitBeforeIntrest();
        if(CommonUtils.isObjectNullOrEmpty(opProfitBeforeIntrestSy))
            opProfitBeforeIntrestSy=0.0;

        Double avgInterestCovRatio=( (opProfitBeforeIntrestTy / interestTy) + (opProfitBeforeIntrestSy / interestSy) ) / 2;


        ///// end AVERAGE_INTEREST_COV_RATIO  /////


        ///// start NO_OF_CUSTOMER  /////

            // remaining

        ///// end NO_OF_CUSTOMER  /////



        ///// start CONCENTRATION_CUSTOMER  /////

        // remaining

        ///// end CONCENTRATION_CUSTOMER  /////
    }

}
