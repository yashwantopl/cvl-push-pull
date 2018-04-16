package com.capitaworld.service.loans.service.scoring.impl;

import com.capitaworld.cibil.api.model.CibilRequest;
import com.capitaworld.cibil.api.model.CibilResponse;
import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.gst.GstCalculation;
import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.gst.yuva.request.GSTR1Request;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.score.ScoringRequestLoans;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.model.FundSeekerInputRequest;
import com.capitaworld.service.scoring.model.ModelParameterResponse;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.scoring.utils.ScoreParameter;
import com.ibm.icu.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
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

    @Autowired
    private CorporateApplicantDetailRepository corporateApplicantDetailRepository;


    @Autowired
    private PrimaryCorporateDetailRepository primaryCorporateDetailRepository;

    @Autowired
    private AnalyzerClient analyzerClient;

    @Autowired
    private CIBILClient cibilClient;


    @Override
    public ResponseEntity<LoansResponse> calculateScoring(ScoringRequestLoans scoringRequestLoans) {

        Long scoreModelId=scoringRequestLoans.getScoringModelId();
        Long applicationId=scoringRequestLoans.getApplicationId();
        Long fpProductId=scoringRequestLoans.getFpProductId();

        logger.info("----------------------------START------------------------------");

        logger.info("Application Id   ::"+applicationId);
        logger.info("Fp Product Id    ::"+fpProductId);
        logger.info("Scoring Model Id ::"+scoreModelId);

        ScoringResponse scoringResponseMain=null;

        // start Get GST Parameter

        //String gstNumber="33GSPTN1361G1ZD";

        String gstNumber=corporateApplicantDetailRepository.getGstInByApplicationId(applicationId);
        Double loanAmount=primaryCorporateDetailRepository.getLoanAmountByApplication(applicationId);

        if(CommonUtils.isObjectNullOrEmpty(loanAmount))
        {
            loanAmount=0.0;
        }

        logger.info("LOAN AMOUNT :::: "+loanAmount);

        logger.info("APPLICATION ID :::: "+applicationId);

        GstResponse gstResponse=null;
        GstCalculation gstCalculation=new GstCalculation();
       /* gstCalculation.setConcentration(20d);
        gstCalculation.setNoOfCustomer(223d);
        gstCalculation.setProjectedSales(1000000d);*/
        try
        {
            GSTR1Request gstr1Request=new GSTR1Request();
            gstr1Request.setGstin(gstNumber);
            gstResponse=gstClient.getCalculations(gstr1Request);

            if(!CommonUtils.isObjectNullOrEmpty(gstResponse) && !CommonUtils.isObjectNullOrEmpty(gstResponse.getData()))
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

        logger.info("START GET SCORE CORPORATE LOAN PARAMETERS");
        // GET SCORE CORPORATE LOAN PARAMETERS


        if(!CommonUtils.isObjectNullOrEmpty(scoreModelId))
        {
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);

            // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
            ScoringResponse scoringResponse=null;
            try {
                scoringResponse = scoringClient.listField(scoringRequest);
            }
            catch (Exception e) {
                logger.error("error while getting field list");
                e.printStackTrace();
            }

            List<Map<String, Object>> dataList = (List<Map<String, Object>>) scoringResponse.getDataList();

            List<FundSeekerInputRequest> fundSeekerInputRequestList = new ArrayList<>(dataList.size());

            for (int i=0;i<dataList.size();i++){

                ModelParameterResponse modelParameterResponse = null;
                try {
                    modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                            ModelParameterResponse.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                fundSeekerInputRequest.setName(modelParameterResponse.getName());

                Map<String, Object> map = new HashMap<>();
                switch (modelParameterResponse.getName()) {

                    case ScoreParameter.COMBINED_NETWORTH:
                    {
                        try
                        {
                            Double networthSum = directorBackgroundDetailsRepository.getSumOfDirectorsNetworth(applicationId);
                            if (CommonUtils.isObjectNullOrEmpty(networthSum))
                                networthSum = 0.0;

                            Double termLoans = liabilitiesDetailsTY.getTermLoans();
                            if (CommonUtils.isObjectNullOrEmpty(termLoans))
                                termLoans = 0.0;

                            if((termLoans+loanAmount)!=0.0)
                                map.put("COMBINED_NETWORTH", ((networthSum)/(termLoans+loanAmount))*100);
                            else
                                map.put("COMBINED_NETWORTH",101.0);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting COMBINED_NETWORTH parameter");
                            e.printStackTrace();
                            map.put("COMBINED_NETWORTH",null);
                        }

                        break;
                    }
                    case ScoreParameter.CUSTOMER_ASSOCIATE_CONCERN:
                    {
                        Double customer_ass_concern_year=6.0;
                        /*try {

                            CibilResponse cibilResponse=cibilClient.getDPDYears(applicationId);
                            customer_ass_concern_year = (Double)cibilResponse.getData();
                            map.put("CUSTOMER_ASSOCIATE_CONCERN",customer_ass_concern_year);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CUSTOMER_ASSOCIATE_CONCERN parameter from CIBIL client");
                            e.printStackTrace();
                            map.put("CUSTOMER_ASSOCIATE_CONCERN",null);
                        }*/
                        map.put("CUSTOMER_ASSOCIATE_CONCERN",customer_ass_concern_year);
                        break;

                    }
                    case ScoreParameter.CIBIL_TRANSUNION_SCORE:
                    {

                        Double cibil_score_avg_promotor=700.0;
                        /*try {

                            CibilRequest cibilRequest=new CibilRequest();
                            cibilRequest.setApplicationId(applicationId);

                            CibilResponse cibilResponse=cibilClient.getCibilScore(cibilRequest);
                            cibil_score_avg_promotor = (Double)cibilResponse.getData();
                            map.put("CIBIL_TRANSUNION_SCORE",cibil_score_avg_promotor);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CIBIL_TRANSUNION_SCORE parameter from CIBIL client");
                            e.printStackTrace();
                            map.put("CIBIL_TRANSUNION_SCORE",null);
                        }*/
                        map.put("CIBIL_TRANSUNION_SCORE",cibil_score_avg_promotor);
                        break;
                    }

                    case ScoreParameter.EXPERIENCE_IN_THE_BUSINESS:
                    {
                        Double directorExperience=directorBackgroundDetailsRepository.getMaxOfDirectorsExperience(applicationId);

                        if(!CommonUtils.isObjectNullOrEmpty(directorExperience))
                        {
                            map.put("EXPERIENCE_IN_THE_BUSINESS",directorExperience);
                        }
                        else
                        {
                            map.put("EXPERIENCE_IN_THE_BUSINESS",null);
                        }

                        break;
                    }
                    case ScoreParameter.DEBT_EQUITY_RATIO: {

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

                            if(equity!=0.0)
                                    map.put("DEBT_EQUITY_RATIO",debt/equity);
                            else
                                map.put("DEBT_EQUITY_RATIO",3.0);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting DEBT_EQUITY_RATIO parameter");
                            e.printStackTrace();
                            map.put("DEBT_EQUITY_RATIO",null);
                        }

                        break;
                    }
                    case ScoreParameter.TOL_TNW : {

                        try
                        {
                            Double tol = liabilitiesDetailsTY.getTotalOutsideLiabilities();
                            if (CommonUtils.isObjectNullOrEmpty(tol))
                                tol = 0.0;

                            Double tnw = assetsDetailsTY.getTangibleNetWorth();
                            if (CommonUtils.isObjectNullOrEmpty(tnw))
                                tnw = 0.0;


                            if(tnw!=0.0)
                                map.put("TOL_TNW",(tol+loanAmount)/tnw);
                            else
                                map.put("TOL_TNW",4.0);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting TOL_TNW parameter");
                            e.printStackTrace();
                            map.put("TOL_TNW",null);
                        }

                        break;
                    }
                    case ScoreParameter.AVERAGE_CURRENT_RATIO:
                    {
                        try
                        {

                            Double currentRatio = (assetsDetailsTY.getCurrentRatio() + assetsDetailsSY.getCurrentRatio())/2;
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
                    case ScoreParameter.WORKING_CAPITAL_CYCLE: {

                        try
                        {
                            Double debtorsDays=null;
                            if((operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())!=0.0)
                            {
                                debtorsDays= ((assetsDetailsTY.getReceivableOtherThanDefferred() + assetsDetailsTY.getExportReceivables()) / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
                            }
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

                            Double creditorsDays=null;
                            if((operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())!=0)
                            {
                                creditorsDays = (liabilitiesDetailsTY.getSundryCreditors() / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
                            }
                            if (CommonUtils.isObjectNullOrEmpty(creditorsDays))
                                creditorsDays = 0.0;


                            if(cogs!=0.0)
                                map.put("WORKING_CAPITAL_CYCLE",debtorsDays+((averageInventory/cogs)*365)-creditorsDays);
                            else
                                map.put("WORKING_CAPITAL_CYCLE",0.0);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting WORKING_CAPITAL_CYCLE parameter");
                            e.printStackTrace();
                            map.put("WORKING_CAPITAL_CYCLE",null);
                        }

                        break;
                    }
                    case ScoreParameter.AVERAGE_ANNUAL_GROWTH_GROSS_CASH:
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

                            Double avgAnnualGrowthGrossCash=null;

                            Double cashAccrualsSY=0.0;

                            if(netProfitOrLossSY + depreciationSy + interestSy ==0.0)
                            {
                                cashAccrualsSY=1.0;
                            }
                            else
                            {
                                cashAccrualsSY=netProfitOrLossSY + depreciationSy + interestSy;
                            }

                            Double cashAccrualsFY=0.0;

                            if(netProfitOrLossFY + depreciationFy + interestFy == 0.0)
                            {
                                cashAccrualsFY=1.0;
                            }
                            else
                            {
                                cashAccrualsFY=netProfitOrLossFY + depreciationFy + interestFy;
                            }

                            avgAnnualGrowthGrossCash = (((((netProfitOrLossTY + depreciationTy + interestTy) - (netProfitOrLossSY + depreciationSy + interestSy)) / (cashAccrualsSY)) * 100) + ((((netProfitOrLossSY + depreciationSy + interestSy) - (netProfitOrLossFY + depreciationFy + interestFy)) / (cashAccrualsFY)) * 100)) / 2;
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
                    case ScoreParameter.AVERAGE_ANNUAL_GROWTH_NET_SALE: {

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

                            Double totalSale_FY=0.0;
                            if(domesticSalesFy + exportSalesFy == 0.0)
                            {
                                totalSale_FY=1.0;
                            }
                            else
                            {
                                totalSale_FY=domesticSalesFy + exportSalesFy;
                            }

                            Double totalSale_SY=0.0;
                            if(domesticSalesSy + exportSalesSy == 0.0)
                            {
                                totalSale_SY=1.0;
                            }
                            else
                            {
                                totalSale_SY=domesticSalesSy + exportSalesSy;
                            }

                            Double totalSale_TY=0.0;
                            if(domesticSalesTy + exportSalesTy == 0.0)
                            {
                                totalSale_TY=1.0;
                            }
                            else
                            {
                                totalSale_TY=domesticSalesTy + exportSalesTy;
                            }

                            avgAnnualGrowthNetSale = (((((totalSale_TY) - (totalSale_SY)) / (totalSale_SY)) * 100) + ((((totalSale_SY) - (totalSale_FY)) / (totalSale_FY)) * 100)) / 2;
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
                    case ScoreParameter.AVERAGE_EBIDTA: {

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

                            Double termLoansEBIDTA = termLoansTy + loanAmount;


                            if(termLoansEBIDTA!=0.0)
                                map.put("AVERAGE_EBIDTA", (avgEBIDTA/termLoansEBIDTA)*100);
                            else
                                map.put("AVERAGE_EBIDTA", 100.0);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_EBIDTA parameter");
                            e.printStackTrace();
                            map.put("AVERAGE_EBIDTA",null);
                        }

                        break;
                    }
                    case ScoreParameter.AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS: {

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

                            if(totalAsset!=0.0)
                                map.put("AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS", (avgGrossCashAccruals/totalAsset)*100);
                            else
                                map.put("AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS", 20.0);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS parameter");
                            e.printStackTrace();
                            map.put("AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS",null);
                        }

                        break;
                    }
                    case ScoreParameter.AVERAGE_INTEREST_COV_RATIO:
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

                                if(interestTy!= 0.0 && interestSy!=0.0)
                                {
                                    Double avgInterestCovRatio = ((opProfitBeforeIntrestTy / interestTy) + (opProfitBeforeIntrestSy / interestSy)) / 2;
                                    map.put("AVERAGE_INTEREST_COV_RATIO",avgInterestCovRatio);
                                }
                                else
                                {
                                    map.put("AVERAGE_INTEREST_COV_RATIO",0.0);
                                }

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
                    case ScoreParameter.NO_OF_CUSTOMER:
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
                    case ScoreParameter.CONCENTRATION_CUSTOMER:
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
                    case ScoreParameter.CREDIT_SUMMATION:
                    {

                        Double totalCredit=null;
                        Double projctedSales=null;
                        Double creditSummation=null;

                        // start get total credit from Analyser
                        ReportRequest reportRequest = new ReportRequest();
                        reportRequest.setApplicationId(applicationId);
                        try {
                            AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReport(reportRequest);
                            Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)analyzerResponse.getData(),
                                    Data.class);
                            if(!CommonUtils.isObjectNullOrEmpty(analyzerResponse.getData())){
                                {
                                    if(!CommonUtils.isObjectNullOrEmpty(data.getTotalCredit()))
                                    {
                                        totalCredit=data.getTotalCredit();
                                    }
                                    else
                                    {
                                        totalCredit=0.0;
                                    }

                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.error("error while calling analyzer client");
                        }

                        // get get total credit from Analyser

                        // start get projected sales from GST client

                        projctedSales=gstCalculation.getProjectedSales();

                        // end get projected sales from GST client


                        if(!(CommonUtils.isObjectNullOrEmpty(projctedSales) || projctedSales == 0.0))
                        {
                            creditSummation=(totalCredit*12)/(projctedSales*12);
                            map.put("CREDIT_SUMMATION",creditSummation);
                        }
                        else
                        {
                            map.put("CREDIT_SUMMATION",0.0);
                        }
                        break;
                    }
                }

                System.out.println("MAP::"+map.toString());

                logger.info("----------------------------END------------------------------");

                fundSeekerInputRequest.setMap(map);
                fundSeekerInputRequestList.add(fundSeekerInputRequest);
            }

            scoringRequest.setDataList(fundSeekerInputRequestList);

            try {
                scoringResponseMain = scoringClient.calculateScore(scoringRequest);
            }
            catch (Exception e) {
                logger.error("error while calling scoring");
                e.printStackTrace();
                LoansResponse loansResponse = new LoansResponse("error while calling scoring.", HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }

            if(scoringResponseMain.getStatus() == HttpStatus.OK.value())
            {
                logger.error("score is successfully calculated");
                LoansResponse loansResponse = new LoansResponse("score is successfully calculated", HttpStatus.OK.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }
            else
            {
                logger.error("error while calling scoring");
                LoansResponse loansResponse = new LoansResponse("error while calling scoring.", HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }
        }

        LoansResponse loansResponse = new LoansResponse("score is successfully calculated", HttpStatus.OK.value());
        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
    }

}
