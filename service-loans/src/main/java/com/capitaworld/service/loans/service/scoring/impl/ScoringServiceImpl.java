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
import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.score.ScoreParameterRequestLoans;
import com.capitaworld.service.loans.model.score.ScoringRequestLoans;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.utils.scoreexcel.ScoreExcelFileGenerator;
import com.capitaworld.service.loans.utils.scoreexcel.ScoreExcelReader;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.model.*;
import com.capitaworld.service.scoring.model.scoringmodel.ScoringModelReqRes;
import com.capitaworld.service.scoring.utils.ScoreParameter;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.ibm.icu.util.Calendar;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
	private Environment environment;

    @Autowired
    private UsersClient usersClient;

    @Autowired
    private ProductMasterRepository productMasterRepository;

    @Override
    public ResponseEntity<LoansResponse> calculateScoring(ScoringRequestLoans scoringRequestLoans) {

        ScoringParameterRequest scoringParameterRequest=new ScoringParameterRequest();

        Long scoreModelId=scoringRequestLoans.getScoringModelId();
        Long applicationId=scoringRequestLoans.getApplicationId();
        Long fpProductId=scoringRequestLoans.getFpProductId();

        logger.info("----------------------------START------------------------------");
        logger.info("---------------------------------------------------------------");
        logger.info("---------------------------------------------------------------");

        logger.info("APPLICATION ID   :: "+ applicationId);
        logger.info("FP PRODUCT ID    :: "+ fpProductId);
        logger.info("SCORING MODEL ID :: "+ scoreModelId);

        ScoringResponse scoringResponseMain=null;

        // start Get GST Parameter

        String gstNumber=corporateApplicantDetailRepository.getGstInByApplicationId(applicationId);
        Double loanAmount=primaryCorporateDetailRepository.getLoanAmountByApplication(applicationId);


        GstResponse gstResponse=null;
        GstCalculation gstCalculation=new GstCalculation();

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

                switch (modelParameterResponse.getName()) {

                    case ScoreParameter.COMBINED_NETWORTH:
                    {
                        try
                        {
                            Double networthSum = directorBackgroundDetailsRepository.getSumOfDirectorsNetworth(applicationId);
                            if (CommonUtils.isObjectNullOrEmpty(networthSum))
                                networthSum = 0.0;

                            Double termLoansTy = liabilitiesDetailsTY.getTermLoans();
                            if (CommonUtils.isObjectNullOrEmpty(termLoansTy))
                                termLoansTy = 0.0;

                            scoringParameterRequest.setNetworthSum(networthSum);
                            scoringParameterRequest.setTermLoanTy(termLoansTy);
                            scoringParameterRequest.setLoanAmount(loanAmount);
                            scoringParameterRequest.setCombinedNetworth_p(true);

                            /*if((termLoansTy+loanAmount)!=0.0)
                                map.put("COMBINED_NETWORTH", ((networthSum)/(termLoansTy+loanAmount))*100);
                            else
                                map.put("COMBINED_NETWORTH",101.0);*/

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting COMBINED_NETWORTH parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setCombinedNetworth_p(false);
                            /*map.put("COMBINED_NETWORTH",null);*/
                        }

                        break;
                    }
                    case ScoreParameter.CUSTOMER_ASSOCIATE_CONCERN:
                    {
                        Double customer_ass_concern_year=null;
                        try {

                            CibilResponse cibilResponse=cibilClient.getDPDYears(applicationId);
                            if(!CommonUtils.isObjectNullOrEmpty(cibilResponse) && !CommonUtils.isObjectNullOrEmpty(cibilResponse.getData()))
                            {
                                customer_ass_concern_year = (Double)cibilResponse.getData();

                                scoringParameterRequest.setCustomerAssociateConcern(customer_ass_concern_year);
                                scoringParameterRequest.setCustomerAsscociateConcern_p(true);

                                /*map.put("CUSTOMER_ASSOCIATE_CONCERN",customer_ass_concern_year);*/
                            }
                            else
                            {
                                /*map.put("CUSTOMER_ASSOCIATE_CONCERN",null);*/
                                scoringParameterRequest.setCustomerAsscociateConcern_p(false);
                            }

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CUSTOMER_ASSOCIATE_CONCERN parameter from CIBIL client");
                            e.printStackTrace();
                            /*map.put("CUSTOMER_ASSOCIATE_CONCERN",null);*/
                            scoringParameterRequest.setCustomerAsscociateConcern_p(false);
                        }
                        break;

                    }
                    case ScoreParameter.CIBIL_TRANSUNION_SCORE:
                    {
                        Double cibil_score_avg_promotor=null;
                        try {

                            CibilRequest cibilRequest=new CibilRequest();
                            cibilRequest.setApplicationId(applicationId);

                            CibilResponse cibilResponse=cibilClient.getCibilScore(cibilRequest);
                            if(!CommonUtils.isObjectNullOrEmpty(cibilResponse.getData()))
                            {
                                cibil_score_avg_promotor = (Double)cibilResponse.getData();
                                scoringParameterRequest.setCibilTransuniunScore(cibil_score_avg_promotor);
                                scoringParameterRequest.setCibilTransunionScore_p(true);
                            }
                            else
                            {
                                scoringParameterRequest.setCibilTransunionScore_p(false);
                            }

                            /*map.put("CIBIL_TRANSUNION_SCORE",cibil_score_avg_promotor);*/

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CIBIL_TRANSUNION_SCORE parameter from CIBIL client");
                            e.printStackTrace();
                            scoringParameterRequest.setCibilTransunionScore_p(false);
                            /*map.put("CIBIL_TRANSUNION_SCORE",null);*/
                        }

                        break;
                    }

                    case ScoreParameter.EXPERIENCE_IN_THE_BUSINESS:
                    {
                        Double directorExperience=directorBackgroundDetailsRepository.getMaxOfDirectorsExperience(applicationId);

                        if(!CommonUtils.isObjectNullOrEmpty(directorExperience))
                        {
                            scoringParameterRequest.setExperienceInTheBusiness(directorExperience);
                            scoringParameterRequest.setExperienceInTheBusiness_p(true);

                            /*map.put("EXPERIENCE_IN_THE_BUSINESS",directorExperience);*/

                        }
                        else
                        {
                            scoringParameterRequest.setExperienceInTheBusiness_p(false);

                            /*map.put("EXPERIENCE_IN_THE_BUSINESS",null);*/
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

                            scoringParameterRequest.setDebt(debt);
                            scoringParameterRequest.setEquity(equity);
                            scoringParameterRequest.setDebtEquityRatio_p(true);

                            /*if(equity!=0.0)
                                    map.put("DEBT_EQUITY_RATIO",debt/equity);
                            else
                                map.put("DEBT_EQUITY_RATIO",3.0);*/

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting DEBT_EQUITY_RATIO parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setDebtEquityRatio_p(false);

                            /*map.put("DEBT_EQUITY_RATIO",null);*/
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

                            scoringParameterRequest.setTol(tol);
                            scoringParameterRequest.setTnw(tnw);
                            scoringParameterRequest.setTolTnw_p(true);

                            /*if(tnw!=0.0)
                                map.put("TOL_TNW",(tol+loanAmount)/tnw);
                            else
                                map.put("TOL_TNW",4.0);*/

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting TOL_TNW parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setTolTnw_p(false);
                            /*map.put("TOL_TNW",null);*/
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

                            scoringParameterRequest.setAvgCurrentRatio(currentRatio);
                            scoringParameterRequest.setAvgCurrentRatio_p(true);

                            /*map.put("AVERAGE_CURRENT_RATIO", currentRatio);*/

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_CURRENT_RATIO parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAvgCurrentRatio_p(false);
                            /*map.put("AVERAGE_CURRENT_RATIO",null);*/
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


                            scoringParameterRequest.setDebtorsDays(debtorsDays);
                            scoringParameterRequest.setAvgInventory(averageInventory);
                            scoringParameterRequest.setCogs(cogs);
                            scoringParameterRequest.setCreditorsDays(creditorsDays);
                            scoringParameterRequest.setWorkingCapitalCycle_p(true);

                          /*  if(cogs!=0.0)
                                map.put("WORKING_CAPITAL_CYCLE",debtorsDays+((averageInventory/cogs)*365)-creditorsDays);
                            else
                                map.put("WORKING_CAPITAL_CYCLE",0.0);*/

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting WORKING_CAPITAL_CYCLE parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setWorkingCapitalCycle_p(false);
                            /*map.put("WORKING_CAPITAL_CYCLE",null);*/
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

                            scoringParameterRequest.setNetProfitOrLossFY(netProfitOrLossFY);
                            scoringParameterRequest.setNetProfitOrLossSY(netProfitOrLossSY);
                            scoringParameterRequest.setNetProfitOrLossTY(netProfitOrLossTY);

                            scoringParameterRequest.setDepriciationFy(depreciationFy);
                            scoringParameterRequest.setDepriciationSy(depreciationSy);
                            scoringParameterRequest.setDepriciationTy(depreciationTy);

                            scoringParameterRequest.setInterestFy(interestFy);
                            scoringParameterRequest.setInterestSy(interestSy);
                            scoringParameterRequest.setInterestTy(interestTy);

                            scoringParameterRequest.setAvgAnnualGrowthGrossCash_p(true);


                            /*Double avgAnnualGrowthGrossCash=null;

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
                            map.put("AVERAGE_ANNUAL_GROWTH_GROSS_CASH", avgAnnualGrowthGrossCash);*/

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_ANNUAL_GROWTH_GROSS_CASH parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAvgAnnualGrowthGrossCash_p(false);
                            /*map.put("AVERAGE_ANNUAL_GROWTH_GROSS_CASH",null);*/
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

                            scoringParameterRequest.setTotalSaleFy(totalSale_FY);
                            scoringParameterRequest.setTotalSaleSy(totalSale_SY);
                            scoringParameterRequest.setTotalSaleTy(totalSale_TY);
                            scoringParameterRequest.setAvgAnnualGrowthNetSale_p(true);

                            /*avgAnnualGrowthNetSale = (((((totalSale_TY) - (totalSale_SY)) / (totalSale_SY)) * 100) + ((((totalSale_SY) - (totalSale_FY)) / (totalSale_FY)) * 100)) / 2;
                            map.put("AVERAGE_ANNUAL_GROWTH_NET_SALE", avgAnnualGrowthNetSale);*/

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_ANNUAL_GROWTH_NET_SALE parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAvgAnnualGrowthNetSale_p(false);
                            /*map.put("AVERAGE_ANNUAL_GROWTH_NET_SALE",null);*/
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


                            Double termLoansTy = liabilitiesDetailsTY.getTermLoans();
                            if (CommonUtils.isObjectNullOrEmpty(termLoansTy))
                                termLoansTy = 0.0;


                            scoringParameterRequest.setProfitBeforeTaxOrLossTy(profitBeforeTaxOrLossTy);
                            scoringParameterRequest.setProfitBeforeTaxOrLossSy(profitBeforeTaxOrLossSy);
                            scoringParameterRequest.setInterestTy(interestTy);
                            scoringParameterRequest.setInterestSy(interestSy);
                            scoringParameterRequest.setDepriciationTy(depreciationTy);
                            scoringParameterRequest.setDepriciationSy(depreciationSy);
                            scoringParameterRequest.setTermLoanTy(termLoansTy);
                            scoringParameterRequest.setLoanAmount(loanAmount);

                            scoringParameterRequest.setAvgEBIDTA_p(true);

                            /*Double avgEBIDTA = ((profitBeforeTaxOrLossTy + interestTy + depreciationTy) + (profitBeforeTaxOrLossSy + interestSy + depreciationSy)) / 2;

                            logger.info("avgEBIDTA::"+avgEBIDTA);


                            Double termLoansEBIDTA = termLoansTy + loanAmount;

                            logger.info("termLoansEBIDTA::"+termLoansEBIDTA);


                            if(termLoansEBIDTA!=0.0) {
                                map.put("AVERAGE_EBIDTA", (avgEBIDTA / termLoansEBIDTA) * 100);
                                logger.info("AVERAGE_EBIDTA::"+(avgEBIDTA / termLoansEBIDTA) * 100);
                            }
                            else {
                                map.put("AVERAGE_EBIDTA", 100.0);
                            }*/

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_EBIDTA parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAvgEBIDTA_p(false);
                            /*map.put("AVERAGE_EBIDTA",null);*/
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

                            Double totalAsset = assetsDetailsTY.getTotalAssets();
                            if (CommonUtils.isObjectNullOrEmpty(totalAsset))
                                totalAsset = 0.0;

                            scoringParameterRequest.setNetProfitOrLossSY(netProfitOrLossSY);
                            scoringParameterRequest.setNetProfitOrLossTY(netProfitOrLossTY);
                            scoringParameterRequest.setInterestSy(interestSy);
                            scoringParameterRequest.setInterestTy(interestTy);
                            scoringParameterRequest.setDepriciationSy(depreciationSy);
                            scoringParameterRequest.setDepriciationTy(depreciationTy);
                            scoringParameterRequest.setTotalAsset(totalAsset);

                            scoringParameterRequest.setAvgAnnualGrossCashAccuruals_p(true);

                            /*Double avgGrossCashAccruals = ((netProfitOrLossTY + depreciationTy + interestTy) + (netProfitOrLossSY + depreciationSy + interestSy)) / 2;
                            if (CommonUtils.isObjectNullOrEmpty(avgGrossCashAccruals))
                                avgGrossCashAccruals = 0.0;


                            if(totalAsset!=0.0)
                                map.put("AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS", (avgGrossCashAccruals/totalAsset)*100);
                            else
                                map.put("AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS", 20.0);*/

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAvgAnnualGrossCashAccuruals_p(false);
                            /*map.put("AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS",null);*/
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

                            scoringParameterRequest.setOpProfitBeforeInterestTy(opProfitBeforeIntrestTy);
                            scoringParameterRequest.setOpProfitBeforeInterestSy(opProfitBeforeIntrestSy);
                            scoringParameterRequest.setInterestTy(interestTy);
                            scoringParameterRequest.setInterestSy(interestSy);

                            scoringParameterRequest.setAvgInterestCovRatio_p(true);


                         /*   try {

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
                            }*/
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_INTEREST_COV_RATIO parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAvgInterestCovRatio_p(false);
                            /*map.put("AVERAGE_INTEREST_COV_RATIO",null);*/
                        }

                        break;
                    }
                    case ScoreParameter.NO_OF_CUSTOMER:
                    {
                        try {
                            if(!CommonUtils.isObjectNullOrEmpty(gstCalculation) && !CommonUtils.isObjectNullOrEmpty(gstCalculation.getNoOfCustomer()))
                            {
                                scoringParameterRequest.setNoOfCustomenr(gstCalculation.getNoOfCustomer());
                                scoringParameterRequest.setNoOfCustomer_p(true);
                                /*map.put("NO_OF_CUSTOMER",gstCalculation.getNoOfCustomer());*/
                            }
                            else
                            {
                                scoringParameterRequest.setNoOfCustomer_p(false);
                                /*map.put("NO_OF_CUSTOMER",null);*/
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting NO_OF_CUSTOMER parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setNoOfCustomer_p(false);
                            /*map.put("NO_OF_CUSTOMER",null);*/
                        }
                        break;
                    }
                    case ScoreParameter.CONCENTRATION_CUSTOMER:
                    {
                        try {
                            if(!CommonUtils.isObjectNullOrEmpty(gstCalculation) && !CommonUtils.isObjectNullOrEmpty(gstCalculation.getConcentration()))
                            {
                                scoringParameterRequest.setConcentrationCustomer(gstCalculation.getConcentration());
                                scoringParameterRequest.setConcentrationCustomer_p(true);
                                /*map.put("CONCENTRATION_CUSTOMER",gstCalculation.getConcentration());*/
                            }
                            else
                            {
                                scoringParameterRequest.setConcentrationCustomer_p(false);
                                /*map.put("CONCENTRATION_CUSTOMER",null);*/
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CONCENTRATION_CUSTOMER parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setConcentrationCustomer_p(false);
                            /*map.put("CONCENTRATION_CUSTOMER",null);*/
                        }
                        break;
                    }
                    case ScoreParameter.CREDIT_SUMMATION:
                    {

                        Double totalCredit=null;
                        Double projctedSales=null;

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
                            totalCredit=0.0;
                            e.printStackTrace();
                            logger.error("error while calling analyzer client");
                        }

                        // get get total credit from Analyser

                        // start get projected sales from GST client

                        projctedSales=gstCalculation.getProjectedSales();

                        // end get projected sales from GST client


                        scoringParameterRequest.setTotalCredit(totalCredit);
                        scoringParameterRequest.setProjectedSale(projctedSales);
                        scoringParameterRequest.setCreditSummation_p(true);

                       /* if(!(CommonUtils.isObjectNullOrEmpty(projctedSales) || projctedSales == 0.0))
                        {
                            creditSummation=(totalCredit*12)/(projctedSales*12);
                            map.put("CREDIT_SUMMATION",creditSummation);
                        }
                        else
                        {
                            map.put("CREDIT_SUMMATION",0.0);
                        }*/
                        break;
                    }
                }
                fundSeekerInputRequestList.add(fundSeekerInputRequest);
            }

            logger.info("SCORE PARAMETER ::::::::::"+scoringParameterRequest.toString());

            logger.info("---------------------------------------------------------------");
            logger.info("---------------------------------------------------------------");
            logger.info("----------------------------END--------------------------------");

            scoringRequest.setDataList(fundSeekerInputRequestList);
            scoringRequest.setScoringParameterRequest(scoringParameterRequest);

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




    @Override
    public ResponseEntity<LoansResponse> calculateScoringTest(ScoringRequestLoans scoringRequestLoans) {

        ScoringParameterRequest scoringParameterRequest=new ScoringParameterRequest();

        logger.info("SCORE PARAMETER BEFORE::::::::::"+scoringRequestLoans.getScoreParameterRequestLoans().toString());

        BeanUtils.copyProperties(scoringRequestLoans.getScoreParameterRequestLoans(),scoringParameterRequest);

        Long scoreModelId=scoringRequestLoans.getScoringModelId();
        Long applicationId=scoringRequestLoans.getApplicationId();

        logger.info("----------------------------START------------------------------");
        logger.info("---------------------------------------------------------------");
        logger.info("---------------------------------------------------------------");

        logger.info("SCORING MODEL ID :: "+ scoreModelId);

        ScoringResponse scoringResponseMain=null;

        ///////////////

        // GET SCORE CORPORATE LOAN PARAMETERS


        if(!CommonUtils.isObjectNullOrEmpty(scoreModelId))
        {
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
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
                fundSeekerInputRequestList.add(fundSeekerInputRequest);
            }

            logger.info("SCORE PARAMETER ::::::::::"+scoringParameterRequest.toString());

            logger.info("---------------------------------------------------------------");
            logger.info("---------------------------------------------------------------");
            logger.info("----------------------------END--------------------------------");

            scoringRequest.setDataList(fundSeekerInputRequestList);
            scoringRequest.setScoringParameterRequest(scoringParameterRequest);
            scoringRequest.setTestingApiCall(true);

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
                logger.info("score is successfully calculated");
                LoansResponse loansResponse = new LoansResponse("score is successfully calculated", HttpStatus.OK.value());
                loansResponse.setData(scoringResponseMain.getDataObject());
                loansResponse.setListData(scoringResponseMain.getDataList());
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

    @SuppressWarnings("resource")
	@Override
	public Workbook readScoringExcel(MultipartFile multipartFile) throws IllegalStateException, InvalidFormatException , IOException, LoansException{
		logger.info("-----------------------------Enter in readScoringExcel()-----------------------------------> MultiPartfile "+ multipartFile);
          InputStream file;
		Workbook workbook=null;
		Sheet scoreSheet;
		List<ScoreParameterRequestLoans> scoreParameterRequestLoansList = null;
		try {
			file = new ByteArrayInputStream(multipartFile.getBytes());
			workbook = new XSSFWorkbook(file);
			scoreSheet = workbook.getSheetAt(0);
			scoreParameterRequestLoansList = ScoreExcelReader.extractCellFromSheet(scoreSheet);
	
			// ScoringRequestLoans List
			List<LoansResponse> loansResponseList = new ArrayList<LoansResponse>();
     		ScoringRequestLoans scoringRequestLoans = null;
			logger.info("calculating scorring()----------------------------------->");          
			for (ScoreParameterRequestLoans scoreParameterRequestLoans : scoreParameterRequestLoansList) {
				scoringRequestLoans = new ScoringRequestLoans();
				scoringRequestLoans.setScoreParameterRequestLoans(scoreParameterRequestLoans);
				scoringRequestLoans.setApplicationId(scoreParameterRequestLoans.getTestId().longValue());
				scoringRequestLoans.setScoringModelId(1l);
		           	
				loansResponseList.add(calculateScoringTest(scoringRequestLoans).getBody());
				
			}
			logger.info("calculating scorring() list size-----------------------> "+ loansResponseList.size());
		     workbook=generateScoringExcel(loansResponseList);
		     logger.info("------------------------Exit from readScoringExcel() ---------------name of sheet in workook -----------------------> " + workbook.getSheetName(0));
		     
		} catch (NullPointerException | IOException e) {
			e.printStackTrace();
			logger.error("----------------Error/Exception while calculating scorring()------------------------------> "+ e.getMessage());
		    throw e; 
		}
		return workbook;
	}

	@Override
	public Workbook generateScoringExcel(List<LoansResponse> loansResponseList) throws  LoansException {
		logger.info("----------------Enter in  generateScoringExcel() ------------------------------>");
	   return  new ScoreExcelFileGenerator().scoreResultExcel(loansResponseList,environment);

	}

    @Override
    public ScoringModelReqRes getScoringModelList(ScoringModelReqRes scoringModelReqRes) {
        try {
            /*scoringModelReqRes.setOrgId(1l);*/
            UserResponse userResponse=usersClient.getOrgIdFromUserId(scoringModelReqRes.getUserId());

            if(!CommonUtils.isObjectNullOrEmpty(userResponse) && !CommonUtils.isObjectNullOrEmpty(userResponse.getData()))
            {
                scoringModelReqRes.setOrgId(Long.parseLong(userResponse.getData().toString()));
                scoringModelReqRes.setOrgId(1l);
            }
            else
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            logger.error("org id is null or empty");
            e.printStackTrace();
            return  new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
        }

        try {

            return scoringClient.getScoringModelList(scoringModelReqRes);
        }
        catch (Exception e)
        {
            logger.error("error while geting score model list from scoring");
            e.printStackTrace();
            return  new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
        }

    }

    @Override
    public ScoringModelReqRes saveScoringModel(ScoringModelReqRes scoringModelReqRes) {

        try {
            /*scoringModelReqRes.getScoringModelResponse().setOrgId(1l);*/

            UserResponse userResponse=usersClient.getOrgIdFromUserId(scoringModelReqRes.getUserId());

            if(!CommonUtils.isObjectNullOrEmpty(userResponse) && !CommonUtils.isObjectNullOrEmpty(userResponse.getData()))
            {
                scoringModelReqRes.getScoringModelResponse().setOrgId(Long.parseLong(userResponse.getData().toString()));
            }
            else
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            logger.error("org id is null or empty");
            e.printStackTrace();
            return  new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
        }

        try {

            return scoringClient.saveScoringModel(scoringModelReqRes);
        }
        catch (Exception e)
        {
            logger.error("error while saving score model from scoring");
            e.printStackTrace();
            return  new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringModelReqRes getScoringModelDetail(ScoringModelReqRes scoringModelReqRes) {
        try {
            Long fpProductId=scoringModelReqRes.getFpProductId();
            try {
                ProductMaster productMaster=productMasterRepository.findOne(fpProductId);
                scoringModelReqRes.setLoanTypeId(Long.parseLong(productMaster.getProductId().toString()));
                return scoringClient.getScoringModelDetail(scoringModelReqRes);
            }
            catch (Exception e)
            {
                logger.error("error while accessing fp product id for scoring");
                e.printStackTrace();
                return  new ScoringModelReqRes("Error while accessing fp product id for scoring",HttpStatus.BAD_REQUEST.value());
            }

        }
        catch (Exception e)
        {
            logger.error("error while getting score model detail from scoring");
            e.printStackTrace();
            return  new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringModelReqRes saveScoringModelDetail(ScoringModelReqRes scoringModelReqRes) {
        try {

            return scoringClient.saveScoringModelDetail(scoringModelReqRes);
        }
        catch (Exception e)
        {
            logger.error("error while saving score model detail from scoring");
            e.printStackTrace();
            return  new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
        }
    }
}
