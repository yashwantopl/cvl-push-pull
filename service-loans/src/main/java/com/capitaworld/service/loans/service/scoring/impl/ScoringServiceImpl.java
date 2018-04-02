package com.capitaworld.service.loans.service.scoring.impl;

import com.capitaworld.service.dms.model.StorageDetailsResponse;
import com.capitaworld.service.gst.GstCalculation;
import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.gst.yuva.request.GSTR1Request;
import com.capitaworld.service.loans.domain.fundseeker.corporate.*;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.ScoringRequestLoans;
import com.capitaworld.service.loans.model.ScoringResponseLoans;
import com.capitaworld.service.loans.model.retail.PastFinancialEstimatesDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.fundseeker.corporate.PastFinancialEstiamateDetailsService;
import com.capitaworld.service.loans.service.irr.impl.IrrServiceImpl;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.*;

@Service
@Transactional
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

    @Autowired
    private GstClient gstClient;


    @Override
    public ResponseEntity<LoansResponse> calculateScoring(ScoringRequestLoans scoringRequestLoans) {

        Long applicationId=scoringRequestLoans.getApplicationId();
        Long scoreModelId = scoringRequestLoans.getScoringModelId();

        ScoringResponse calculateScore=null;

        // start Get GST Parameter
        String gstNumber="33GSPTN1361G1ZD";
        GstResponse gstResponse=null;
        GstCalculation gstCalculation=null;
        try
        {
            GSTR1Request gstr1Request=new GSTR1Request();
            gstr1Request.setGstin(gstNumber);
            gstResponse=gstClient.getCalculations(gstr1Request);

            if(!CommonUtils.isObjectNullOrEmpty(gstResponse) && CommonUtils.isObjectNullOrEmpty(gstResponse.getData()))
            {
                gstCalculation = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)gstResponse.getData(),
                        GstCalculation.class);
            }

        }
        catch (Exception e)
        {
            logger.error("error while getting GST parameter");
            e.printStackTrace();
        }
        // end Get GST Parameter

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
        Double scale = null;
        String interpretation = null;

        logger.info("START GET SCORE CORPORATE LOAN PARAMETERS");
        // GET SCORE CORPORATE LOAN PARAMETERS


        if(!CommonUtils.isObjectNullOrEmpty(scoreModelId))
        {
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);

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
                        try
                        {
                            Double networthSum = directorBackgroundDetailsRepository.getSumOfDirectorsNetworth(applicationId);
                            if (CommonUtils.isObjectNullOrEmpty(networthSum))
                                networthSum = 0.0;

                            Double termLoans = liabilitiesDetailsTY.getTermLoans();
                            if (CommonUtils.isObjectNullOrEmpty(termLoans))
                                termLoans = 0.0;

                            Double loanAmount = 0.0; // remaining

                            if((termLoans+loanAmount)!=0)
                                map.put("COMBINED_NETWORTH", ((networthSum)/(termLoans+loanAmount))*100);
                            else
                                map.put("COMBINED_NETWORTH",null);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting COMBINED_NETWORTH parameter");
                            e.printStackTrace();
                            map.put("COMBINED_NETWORTH",null);
                        }

                        break;
                    }
                    case "CUSTOMER_ASSOCIATE_CONCERN":
                    {
                        // remaining

                        map.put("CUSTOMER_ASSOCIATE_CONCERN",null);

                        break;

                    }
                    case "CIBIL_TRANSUNION_SCORE":
                    {
                        // remaining

                        map.put("CIBIL_TRANSUNION_SCORE",null);

                        break;
                    }

                    case "EXPERIENCE_IN_THE_BUSINESS":
                    {
                        // remaining

                        map.put("EXPERIENCE_IN_THE_BUSINESS",null);

                        break;
                    }
                    case "DEBT_EQUITY_RATIO": {

                        try
                        {
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


                            Double equity = liabilitiesDetailsTY.getPreferencesShares() +
                                    liabilitiesDetailsTY.getNetWorth() -
                                    liabilitiesDetailsTY.getMinorityInterest() -
                                    liabilitiesDetailsTY.getDeferredTaxLiability();
                            if (CommonUtils.isObjectNullOrEmpty(debt))
                                equity = 0.0;

                            if(equity!=0)
                                map.put("DEBT_EQUITY_RATIO",debt/equity);
                            else
                                map.put("DEBT_EQUITY_RATIO",null);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting DEBT_EQUITY_RATIO parameter");
                            e.printStackTrace();
                            map.put("DEBT_EQUITY_RATIO",null);
                        }

                        break;
                    }
                    case "TOL_TNW ": {

                        try
                        {
                            Double tol = liabilitiesDetailsTY.getTotalOutsideLiabilities();
                            if (CommonUtils.isObjectNullOrEmpty(tol))
                                tol = 0.0;


                            Double loanAmount = 0.0; // remaining

                            Double tnw = assetsDetailsTY.getTangibleNetWorth();
                            if (CommonUtils.isObjectNullOrEmpty(tnw))
                                tnw = 0.0;


                            if(tnw!=0)
                                map.put("TOL_TNW",(tol+loanAmount)/tnw);
                            else
                                map.put("TOL_TNW",null);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting TOL_TNW parameter");
                            e.printStackTrace();
                            map.put("TOL_TNW",null);
                        }

                        break;
                    }
                    case "AVERAGE_CURRENT_RATIO":
                    {
                        try
                        {

                            Double currentRatio = assetsDetailsTY.getCurrentRatio() + assetsDetailsSY.getCurrentRatio();
                            if (CommonUtils.isObjectNullOrEmpty(currentRatio))
                                currentRatio = 0.0;

                            map.put("AVERAGE_CURRENT_RATIO", currentRatio);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_CURRENT_RATIO parameter");
                            e.printStackTrace();
                            map.put("AVERAGE_CURRENT_RATIO",null);
                        }

                        break;
                    }
                    case "WORKING_CAPITAL_CYCLE": {

                        try
                        {
                            Double debtorsDays = ((assetsDetailsTY.getReceivableOtherThanDefferred() + assetsDetailsTY.getExportReceivables()) / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
                            if (CommonUtils.isObjectNullOrEmpty(debtorsDays))
                                debtorsDays = 0.0;


                            /////////////

                            Double averageInventory = (operatingStatementDetailsTY.getAddOperatingStockFg() + operatingStatementDetailsTY.getDeductClStockFg()) / 2;
                            if (CommonUtils.isObjectNullOrEmpty(averageInventory))
                                averageInventory = 0.0;

                            Double cogs = operatingStatementDetailsTY.getRawMaterials() + operatingStatementDetailsTY.getAddOperatingStockFg() - operatingStatementDetailsTY.getDeductClStockFg();
                            if (CommonUtils.isObjectNullOrEmpty(cogs))
                                cogs = 0.0;


                            /////////////

                            Double creditorsDays = (liabilitiesDetailsTY.getSundryCreditors() / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
                            if (CommonUtils.isObjectNullOrEmpty(creditorsDays))
                                creditorsDays = 0.0;


                            if(cogs!=0)
                                map.put("WORKING_CAPITAL_CYCLE",debtorsDays+((averageInventory/cogs)*365)-creditorsDays);
                            else
                                map.put("WORKING_CAPITAL_CYCLE",null);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting WORKING_CAPITAL_CYCLE parameter");
                            e.printStackTrace();
                            map.put("WORKING_CAPITAL_CYCLE",null);
                        }

                        break;
                    }
                    case "AVERAGE_ANNUAL_GROWTH_GROSS_CASH":
                    {
                        try
                        {
                            Double netProfitOrLossTY = operatingStatementDetailsTY.getNetProfitOrLoss();
                            if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossTY))
                                netProfitOrLossTY = 0.0;
                            Double depreciationTy = operatingStatementDetailsTY.getDepreciation();
                            if (CommonUtils.isObjectNullOrEmpty(depreciationTy))
                                depreciationTy = 0.0;
                            Double interestTy = operatingStatementDetailsTY.getInterest();
                            if (CommonUtils.isObjectNullOrEmpty(interestTy))
                                interestTy = 0.0;

                            Double netProfitOrLossSY = operatingStatementDetailsSY.getNetProfitOrLoss();
                            if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossSY))
                                netProfitOrLossSY = 0.0;
                            Double depreciationSy = operatingStatementDetailsSY.getDepreciation();
                            if (CommonUtils.isObjectNullOrEmpty(depreciationSy))
                                depreciationSy = 0.0;
                            Double interestSy = operatingStatementDetailsSY.getInterest();
                            if (CommonUtils.isObjectNullOrEmpty(interestSy))
                                interestSy = 0.0;

                            Double netProfitOrLossFY = operatingStatementDetailsFY.getNetProfitOrLoss();
                            if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossFY))
                                netProfitOrLossFY = 0.0;
                            Double depreciationFy = operatingStatementDetailsFY.getDepreciation();
                            if (CommonUtils.isObjectNullOrEmpty(depreciationFy))
                                depreciationFy = 0.0;
                            Double interestFy = operatingStatementDetailsFY.getInterest();
                            if (CommonUtils.isObjectNullOrEmpty(interestFy))
                                interestFy = 0.0;

                            Double avgAnnualGrowthGrossCash = (((((netProfitOrLossTY + depreciationTy + interestTy) - (netProfitOrLossSY + depreciationSy + interestSy)) / (netProfitOrLossTY + depreciationTy + interestTy)) * 100) + ((((netProfitOrLossSY + depreciationSy + interestSy) - (netProfitOrLossFY + depreciationFy + interestFy)) / (netProfitOrLossSY + depreciationSy + interestSy)) * 100)) / 2;
                            if (CommonUtils.isObjectNullOrEmpty(avgAnnualGrowthGrossCash))
                                avgAnnualGrowthGrossCash = 0.0;

                            map.put("AVERAGE_ANNUAL_GROWTH_GROSS_CASH", avgAnnualGrowthGrossCash);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_ANNUAL_GROWTH_GROSS_CASH parameter");
                            e.printStackTrace();
                            map.put("AVERAGE_ANNUAL_GROWTH_GROSS_CASH",null);
                        }
                        break;
                    }
                    case "AVERAGE_ANNUAL_GROWTH_NET_SALE": {

                        try
                        {
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

                            Double avgAnnualGrowthNetSale=null;


                            avgAnnualGrowthNetSale = (((((domesticSalesTy + exportSalesTy) - (domesticSalesSy + exportSalesSy)) / (domesticSalesTy + exportSalesTy)) * 100) + ((((domesticSalesSy + exportSalesSy) - (domesticSalesFy + exportSalesFy)) / (domesticSalesSy + exportSalesSy)) * 100)) / 2;
                            if (CommonUtils.isObjectNullOrEmpty(avgAnnualGrowthNetSale))
                                avgAnnualGrowthNetSale = 0.0;

                            map.put("AVERAGE_ANNUAL_GROWTH_NET_SALE", avgAnnualGrowthNetSale);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_ANNUAL_GROWTH_NET_SALE parameter");
                            e.printStackTrace();
                            map.put("AVERAGE_ANNUAL_GROWTH_NET_SALE",null);
                        }

                        break;
                    }
                    case "AVERAGE_EBIDTA": {

                        try
                        {
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

                            Double termLoansTy = liabilitiesDetailsTY.getTermLoans();
                            if (CommonUtils.isObjectNullOrEmpty(termLoansTy))
                                termLoansTy = 0.0;

                            Double loanAmount = 0.0; // remaining

                            Double termLoansEBIDTA = termLoansTy + loanAmount;


                            if(termLoansEBIDTA!=0)
                                map.put("AVERAGE_EBIDTA", (avgEBIDTA/termLoansEBIDTA)*100);
                            else
                                map.put("AVERAGE_EBIDTA", null);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_EBIDTA parameter");
                            e.printStackTrace();
                            map.put("AVERAGE_EBIDTA",null);
                        }

                        break;
                    }
                    case "AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS": {

                        try
                        {

                            Double netProfitOrLossTY = operatingStatementDetailsTY.getNetProfitOrLoss();
                            if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossTY))
                                netProfitOrLossTY = 0.0;

                            Double netProfitOrLossSY = operatingStatementDetailsSY.getNetProfitOrLoss();
                            if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossSY))
                                netProfitOrLossSY = 0.0;

                            Double interestTy = operatingStatementDetailsTY.getInterest();
                            if (CommonUtils.isObjectNullOrEmpty(interestTy))
                                interestTy = 0.0;

                            Double interestSy = operatingStatementDetailsSY.getInterest();
                            if (CommonUtils.isObjectNullOrEmpty(interestSy))
                                interestSy = 0.0;

                            Double depreciationTy = operatingStatementDetailsTY.getDepreciation();
                            if (CommonUtils.isObjectNullOrEmpty(depreciationTy))
                                depreciationTy = 0.0;

                            Double depreciationSy = operatingStatementDetailsSY.getDepreciation();
                            if (CommonUtils.isObjectNullOrEmpty(depreciationSy))
                                depreciationSy = 0.0;

                            Double avgGrossCashAccruals = ((netProfitOrLossTY + depreciationTy + interestTy) + (netProfitOrLossSY + depreciationSy + interestSy)) / 2;
                            if (CommonUtils.isObjectNullOrEmpty(avgGrossCashAccruals))
                                avgGrossCashAccruals = 0.0;


                            Double totalAsset = assetsDetailsTY.getTotalAssets();
                            if (CommonUtils.isObjectNullOrEmpty(totalAsset))
                                totalAsset = 0.0;

                            if(totalAsset!=0)
                                map.put("AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS", (avgGrossCashAccruals/totalAsset)*100);
                            else
                                map.put("AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS", null);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS parameter");
                            e.printStackTrace();
                            map.put("AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS",null);
                        }

                        break;
                    }
                    case "AVERAGE_INTEREST_COV_RATIO":
                    {
                        try
                        {
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

                            try {
                                Double avgInterestCovRatio = ((opProfitBeforeIntrestTy / interestTy) + (opProfitBeforeIntrestSy / interestSy)) / 2;
                                map.put("AVERAGE_INTEREST_COV_RATIO",avgInterestCovRatio);
                            }
                            catch (Exception e)
                            {
                                logger.error("error while calculating AVERAGE_INTEREST_COV_RATIO");
                                e.printStackTrace();
                                map.put("AVERAGE_INTEREST_COV_RATIO",null);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_INTEREST_COV_RATIO parameter");
                            e.printStackTrace();
                            map.put("AVERAGE_INTEREST_COV_RATIO",null);
                        }

                        break;
                    }
                    case "NO_OF_CUSTOMER":
                    {
                        try {
                            if(!CommonUtils.isObjectNullOrEmpty(gstCalculation) && !CommonUtils.isObjectNullOrEmpty(gstCalculation.getNoOfCustomer()))
                            {
                                map.put("NO_OF_CUSTOMER",gstCalculation.getNoOfCustomer());
                            }
                            else
                            {
                                map.put("NO_OF_CUSTOMER",null);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting NO_OF_CUSTOMER parameter");
                            e.printStackTrace();
                            map.put("NO_OF_CUSTOMER",null);
                        }

                        map.put("NO_OF_CUSTOMER",null);
                        break;
                    }
                    case "CONCENTRATION_CUSTOMER":
                    {
                        try {
                            if(!CommonUtils.isObjectNullOrEmpty(gstCalculation) && !CommonUtils.isObjectNullOrEmpty(gstCalculation.getConcentration()))
                            {
                                map.put("CONCENTRATION_CUSTOMER",gstCalculation.getConcentration());
                            }
                            else
                            {
                                map.put("CONCENTRATION_CUSTOMER",null);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CONCENTRATION_CUSTOMER parameter");
                            e.printStackTrace();
                            map.put("CONCENTRATION_CUSTOMER",null);
                        }

                        map.put("CONCENTRATION_CUSTOMER",null);
                        break;
                    }
                    case "CREDIT_SUMMATION":
                    {
                        // remaining
                        map.put("CREDIT_SUMMATION",null);
                        break;
                    }
                }

                fundSeekerInputRequest.setMap(map);
                fundSeekerInputRequestList.add(fundSeekerInputRequest);
                i = i + 1;
            }

            scoringRequest.setDataList(fundSeekerInputRequestList);

            try {
                 calculateScore = scoringClient.calculateScore(scoringRequest);
            }
            catch (Exception e) {
                logger.error("error while calling scoring");
                e.printStackTrace();
                LoansResponse loansResponse = new LoansResponse("error while calling scoring.", HttpStatus.OK.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            if (!com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(calculateScore)) {
                score = calculateScore.getScore();
                scale = calculateScore.getScale();
                interpretation = calculateScore.getInterpretation();

                logger.info("Total Use score from scoring module------->" + score);
                logger.info("Scale from scoring module------->" + scale);
                logger.info("Interpretation from scoring module------->" + interpretation);
            }
        }

        if(CommonUtils.isObjectNullOrEmpty(calculateScore) || CommonUtils.isObjectNullOrEmpty(calculateScore.getScore()))
        {
            LoansResponse loansResponse = new LoansResponse("error while calling scoring.", HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ScoringResponseLoans scoringResponseLoans=new ScoringResponseLoans();
        BeanUtils.copyProperties(calculateScore,scoringResponseLoans);

        LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
        loansResponse.setData(scoringResponseLoans);
        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
    }

}
