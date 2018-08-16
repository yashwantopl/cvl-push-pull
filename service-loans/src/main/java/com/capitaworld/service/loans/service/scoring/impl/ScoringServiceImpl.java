package com.capitaworld.service.loans.service.scoring.impl;

import com.capitaworld.cibil.api.model.CibilRequest;
import com.capitaworld.cibil.api.model.CibilResponse;
import com.capitaworld.cibil.api.model.CibilScoreLogRequest;
import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.itr.api.model.ITRBasicDetailsResponse;
import com.capitaworld.itr.api.model.ITRConnectionResponse;
import com.capitaworld.itr.client.ITRClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.gst.GstCalculation;
import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.gst.yuva.request.GSTR1Request;
import com.capitaworld.service.loans.domain.fundseeker.corporate.*;
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
import com.capitaworld.service.scoring.exception.ScoringException;
import com.capitaworld.service.scoring.model.*;
import com.capitaworld.service.scoring.model.scoringmodel.ScoringModelReqRes;
import com.capitaworld.service.scoring.utils.ScoreParameter;
import com.capitaworld.service.thirdparty.model.CGTMSEDataResponse;
import com.capitaworld.service.thirdpaty.client.ThirdPartyClient;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
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

    @Autowired
    private Environment environment;

    @Autowired
    private UsersClient usersClient;

    @Autowired
    private ProductMasterRepository productMasterRepository;

    @Autowired
    private ThirdPartyClient thirdPartyClient;

    @Autowired
    private CorporateDirectorIncomeDetailsRepository corporateDirectorIncomeDetailsRepository;

    @Autowired
    private FinancialArrangementDetailsRepository financialArrangementDetailsRepository;

    @Autowired
    private ITRClient itrClient;


    @Override
    public ResponseEntity<LoansResponse> calculateScoring(ScoringRequestLoans scoringRequestLoans) {

        PrimaryCorporateDetail primaryCorporateDetail=primaryCorporateDetailRepository.findOneByApplicationIdId(scoringRequestLoans.getApplicationId());

        if(CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail) || CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getBusinessTypeId()))
        {
            logger.warn("Business type id is null or empty");
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse("Business type id is null or empty.", HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.OK);
        }

        Long businessTypeId=primaryCorporateDetail.getBusinessTypeId().longValue();

        if(ScoreParameter.BusinessType.EXISTING_BUSINESS == businessTypeId)
        {
            return calculateExistingBusinessScoring(scoringRequestLoans);
        }
        else if(ScoreParameter.BusinessType.NTB == businessTypeId)
        {
            return calculateNTBScoring(scoringRequestLoans,primaryCorporateDetail);
        }

        return null;
    }

    @Override
    public ResponseEntity<LoansResponse> calculateExistingBusinessScoring(ScoringRequestLoans scoringRequestLoans) {
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

        CorporateApplicantDetail corporateApplicantDetail=corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);


        GstResponse gstResponse=null;
        GstResponse gstResponseScoring=null;
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


        // get GST Data for Sales Show A Rising Trend

        try
        {
            gstResponseScoring=gstClient.getCalculationForScoring(gstNumber);
        }
        catch (Exception e)
        {
            logger.error("error while getting GST parameter for GST Sales Show A Rising Trend");
            e.printStackTrace();
        }

        // end Get GST Parameter

        int currentYear = getFinYear(applicationId);
        if(CommonUtils.isObjectNullOrEmpty(currentYear))
        {
            logger.error("error while getting current year from itr");
            LoansResponse loansResponse = new LoansResponse("error while getting current year from itr.", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }

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

        // Get Director Background detail

        DirectorBackgroundDetail mainDirectorBackgroundDetail=directorBackgroundDetailsRepository.getMainDirectorByApplicationId(applicationId);

        // get Primary Corporate Detail

        PrimaryCorporateDetail primaryCorporateDetail=primaryCorporateDetailRepository.findOneByApplicationIdId(applicationId);

        // GET SCORE CORPORATE LOAN PARAMETERS


        if(!CommonUtils.isObjectNullOrEmpty(scoreModelId))
        {
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.EXISTING_BUSINESS);

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

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting COMBINED_NETWORTH parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setCombinedNetworth_p(false);
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
                            }
                            else
                            {
                                scoringParameterRequest.setCustomerAsscociateConcern_p(false);
                            }

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CUSTOMER_ASSOCIATE_CONCERN parameter from CIBIL client");
                            e.printStackTrace();
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
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CIBIL_TRANSUNION_SCORE parameter from CIBIL client");
                            e.printStackTrace();
                            scoringParameterRequest.setCibilTransunionScore_p(false);
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
                        }
                        else
                        {
                            scoringParameterRequest.setExperienceInTheBusiness_p(false);
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

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting DEBT_EQUITY_RATIO parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setDebtEquityRatio_p(false);
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
                            scoringParameterRequest.setLoanAmount(loanAmount);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting TOL_TNW parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setTolTnw_p(false);
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

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_CURRENT_RATIO parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAvgCurrentRatio_p(false);
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
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting WORKING_CAPITAL_CYCLE parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setWorkingCapitalCycle_p(false);
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

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_ANNUAL_GROWTH_GROSS_CASH parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAvgAnnualGrowthGrossCash_p(false);
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
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_ANNUAL_GROWTH_NET_SALE parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAvgAnnualGrowthNetSale_p(false);
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

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_EBIDTA parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAvgEBIDTA_p(false);
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

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAvgAnnualGrossCashAccuruals_p(false);
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
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AVERAGE_INTEREST_COV_RATIO parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAvgInterestCovRatio_p(false);
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
                            }
                            else
                            {
                                scoringParameterRequest.setNoOfCustomer_p(false);
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
                            }
                            else
                            {
                                scoringParameterRequest.setConcentrationCustomer_p(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CONCENTRATION_CUSTOMER parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setConcentrationCustomer_p(false);
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

                        break;
                    }
                    case ScoreParameter.AGE:
                    {
                        try {

                            if(!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDob()))
                            {
                                scoringParameterRequest.setAge(Math.ceil(CommonUtils.getAgeFromBirthDate(mainDirectorBackgroundDetail.getDob()).doubleValue()));
                                scoringParameterRequest.setAge_p(true);
                            }
                            else {
                                scoringParameterRequest.setAge_p(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AGE parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAge_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.NO_OF_CHILDREN:
                    {

                        try {

                            if(!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getNoOfChildren()))
                            {
                                scoringParameterRequest.setNoOfChildren(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getNoOfChildren().doubleValue());
                                scoringParameterRequest.setNoOfChildren_p(true);
                            }
                            else {
                                scoringParameterRequest.setNoOfChildren_p(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting NO_OF_CHILDREN parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setNoOfChildren_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.OWNING_HOUSE:
                    {

                        try {

                            if(!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getOwningHouse()))
                            {
                                scoringParameterRequest.setOwningHouse(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getOwningHouse().longValue());
                                scoringParameterRequest.setOwningHouse_p(true);
                            }
                            else {
                                scoringParameterRequest.setOwningHouse_p(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting OWNING_HOUSE parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setOwningHouse_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.ACADEMIC_QUALIFICATION:
                    {

                        try {

                            if(!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getEducationalStatus()))
                            {
                                scoringParameterRequest.setAcadamicQualification(mainDirectorBackgroundDetail.getEducationalStatus().longValue());
                                scoringParameterRequest.setAcadamicQualification_p(true);
                            }
                            else {
                                scoringParameterRequest.setAcadamicQualification_p(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting ACADEMIC_QUALIFICATION parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAcadamicQualification_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.EXPERIENCE_IN_THE_LINE_OF_TRADE:
                    {

                        try {

                            if(!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getTotalExperience()))
                            {
                                scoringParameterRequest.setExperienceInLineOfBusiness(mainDirectorBackgroundDetail.getTotalExperience());
                                scoringParameterRequest.setExpLineOfTrade_p(true);
                            }
                            else {
                                scoringParameterRequest.setExpLineOfTrade_p(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting EXPERIENCE_IN_THE_LINE_OF_TRADE parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setExpLineOfTrade_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.SPOUSE_DETAILS:
                    {

                        try {

                            if(!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getSpouseDetail()))
                            {
                                scoringParameterRequest.setSpouceDetails(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getSpouseDetail().longValue());
                                scoringParameterRequest.setSpouseDetails_p(true);
                            }
                            else {
                                scoringParameterRequest.setSpouseDetails_p(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting SPOUSE_DETAILS parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setSpouseDetails_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.ASSESSED_FOR_INCOME_TAX:
                    {

                        try {

                            if(!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getAssessedForIt()))
                            {
                                scoringParameterRequest.setAssessedFOrIT(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getAssessedForIt().longValue());
                                scoringParameterRequest.setAssessedForIncomeTax_p(true);
                            }
                            else {
                                scoringParameterRequest.setAssessedForIncomeTax_p(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting ASSESSED_FOR_INCOME_TAX parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setAssessedForIncomeTax_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.HAVE_LIFE_INSURANCE_POLICY:
                    {

                        try {

                            if(!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getHaveLiPolicy()))
                            {
                                scoringParameterRequest.setHaveLIPolicy(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getHaveLiPolicy().longValue());
                                scoringParameterRequest.setHaveLifeIncPolicy_p(true);
                            }
                            else {
                                scoringParameterRequest.setHaveLifeIncPolicy_p(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting HAVE_LIFE_INSURANCE_POLICY parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setHaveLifeIncPolicy_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.YEARS_IN_BUSINESS:
                    {
                        try {

                            java.util.Calendar todayDate = java.util.Calendar.getInstance();
                            todayDate.setTime(new Date());

                            Integer yearsInBetween = todayDate.get(java.util.Calendar.YEAR) - corporateApplicantDetail.getEstablishmentYear();
                            Integer monthsDiff = 0;

                            monthsDiff = todayDate.get(java.util.Calendar.MONTH) - corporateApplicantDetail.getEstablishmentMonth();

                            Double yearsInBusiness = Double.valueOf((yearsInBetween * 12 + monthsDiff)/12);

                            scoringParameterRequest.setYearsInBusiness(yearsInBusiness);
                            scoringParameterRequest.setYearsInBusiness_p(true);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting YEARS_IN_BUSINESS parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setYearsInBusiness_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.REPAYMENT_PERIOD:
                    {

                        try {

                                // get repayment period from one form // remaining
                                scoringParameterRequest.setRepaymentPeriod(5.0);
                                scoringParameterRequest.setRepaymentPeriod_p(true);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting REPAYMENT_PERIOD parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setRepaymentPeriod_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.CONTINUOUS_NET_PROFIT:
                    {

                        try {

                            Double netProfitOrLossFY = operatingStatementDetailsFY.getProfitBeforeTaxOrLoss();
                            Double netProfitOrLossSY = operatingStatementDetailsSY.getProfitBeforeTaxOrLoss();
                            Double netProfitOrLossTY = operatingStatementDetailsTY.getProfitBeforeTaxOrLoss();

                            scoringParameterRequest.setContinuousNetProfitOrLossFY(netProfitOrLossFY);
                            scoringParameterRequest.setContinuousNetProfitOrLossSY(netProfitOrLossSY);
                            scoringParameterRequest.setContinuousNetProfitOrLossTY(netProfitOrLossTY);

                            scoringParameterRequest.setContinousNetProfit_p(true);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CONTINUOUS_NET_PROFIT parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setContinousNetProfit_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.QUALITY_OF_RECEIVABLES:
                    {

                        try {

                            Double totalSaleTY = operatingStatementDetailsTY.getDomesticSales()+operatingStatementDetailsTY.getExportSales();
                            Double grossSaleTy = operatingStatementDetailsTY.getTotalGrossSales();

                            scoringParameterRequest.setTotalSaleTy(totalSaleTY);
                            scoringParameterRequest.setGrossSaleTy(grossSaleTy);

                            scoringParameterRequest.setQualityOfReceivable_p(true);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting QUALITY_OF_RECEIVABLES parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setQualityOfReceivable_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.QUALITY_OF_FINISHED_GOODS_INVENTORY:
                    {

                        try {

                            Double totalCostSaleTy = operatingStatementDetailsTY.getTotalCostSales();
                            Double finishedGoodTy = assetsDetailsTY.getFinishedGoods();

                            scoringParameterRequest.setTotalCostSaleTy(totalCostSaleTy);
                            scoringParameterRequest.setFinishedGoodTy(finishedGoodTy);

                            scoringParameterRequest.setQualityOfFinishedGood_p(true);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting QUALITY_OF_FINISHED_GOODS_INVENTORY parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setQualityOfFinishedGood_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.KNOW_HOW:
                    {

                        try {

                            if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getKnowHow()))
                            {
                                scoringParameterRequest.setKnowHow(primaryCorporateDetail.getKnowHow().longValue());
                                scoringParameterRequest.setKnowHow_p(true);
                            }
                            else {
                                scoringParameterRequest.setKnowHow_p(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting KNOW_HOW parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setKnowHow_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.LINE_OF_ACTIVITY:
                    {
                        scoringParameterRequest.setLineOfActivity(1l);
                        scoringParameterRequest.setLineOfActivity_p(true);

                        break;
                    }
                    case ScoreParameter.COMPETITION:
                    {

                        try {

                            if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCompetition()))
                            {
                                scoringParameterRequest.setCompetition(primaryCorporateDetail.getCompetition().longValue());
                                scoringParameterRequest.setCompetition_p(true);
                            }
                            else {
                                scoringParameterRequest.setCompetition_p(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting COMPETITION parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setCompetition_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.FACTORY_PREMISES:
                    {
                        try {

                            if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getFactoryPremise()))
                            {
                                scoringParameterRequest.setFactoryPremises(primaryCorporateDetail.getFactoryPremise().longValue());
                                scoringParameterRequest.setFactoryPremises_p(true);
                            }
                            else {
                                scoringParameterRequest.setFactoryPremises_p(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting FACTORY_PREMISES parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setFactoryPremises_p(false);
                        }
                        break;
                    }
                    case ScoreParameter.SALES_SHOW_A_RISING_TREND:
                    {

                        try {

                            // getting Gst Current Year Sale from GST Client

                            if(!CommonUtils.isObjectNullOrEmpty(gstResponseScoring) && !CommonUtils.isObjectNullOrEmpty(gstResponseScoring.getData()))
                            {
                                scoringParameterRequest.setGstSaleCurrentYear((Double) gstResponseScoring.getData());
                            }
                            else
                            {
                                scoringParameterRequest.setGstSaleCurrentYear(0.0);
                                logger.error("Error while getting Gst data for Scoring from GST client");
                            }

                            scoringParameterRequest.setNetSaleFy(operatingStatementDetailsFY.getNetSales());
                            scoringParameterRequest.setNetSaleSy(operatingStatementDetailsSY.getNetSales());
                            scoringParameterRequest.setNetSaleTy(operatingStatementDetailsTY.getNetSales());

                            scoringParameterRequest.setSalesShowArisingTrend_p(true);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting SALES_SHOW_A_RISING_TREND parameter");
                            e.printStackTrace();
                            scoringParameterRequest.setSalesShowArisingTrend_p(false);
                        }
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
    public ResponseEntity<LoansResponse> calculateNTBScoring(ScoringRequestLoans scoringRequestLoans,PrimaryCorporateDetail primaryCorporateDetail)
    {


        // Fetch Data for Calculate Director Score

        List<DirectorBackgroundDetail> directorBackgroundDetailsList =  directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(scoringRequestLoans.getApplicationId());

        logger.info("directorBackgroundDetailsList.size()==========>>"+directorBackgroundDetailsList.size());

        if(directorBackgroundDetailsList.size() > 0)
        {
            for(DirectorBackgroundDetail directorBackgroundDetail : directorBackgroundDetailsList) {
                Boolean flag = calculateDirectorScore(scoringRequestLoans,directorBackgroundDetail,primaryCorporateDetail);
            }
        }

        // Fetch Data for Calculate Company Score

        com.capitaworld.service.scoring.model.scoringmodel.ScoreParameterNTBRequest scoreParameterNTBRequest=new com.capitaworld.service.scoring.model.scoringmodel.ScoreParameterNTBRequest();

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

        // GET SCORE NTB LOAN PARAMETERS


        if(!CommonUtils.isObjectNullOrEmpty(scoreModelId))
        {
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.NTB);

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

            logger.info("Field List ==============>>>>>"+dataList.size());

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

                    case ScoreParameter.NTB.WORKING_EXPERIENCE: {
                        scoreParameterNTBRequest.setIsWorkingExperience(true);
                        break;
                    }
                    case ScoreParameter.NTB.IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS: {
                        scoreParameterNTBRequest.setIsFamilyMemberInLineOfBusiness(true);
                        break;
                    }
                    case ScoreParameter.NTB.CIBIL_TRANSUNION_SCORE: {
                        scoreParameterNTBRequest.setIsCibilTransunionScore(true);
                        break;
                    }
                    case ScoreParameter.NTB.AGE_OF_PROMOTOR: {
                        scoreParameterNTBRequest.setIsAgeOfPromotor(true);
                        break;
                    }
                    case ScoreParameter.NTB.EDUCATION_QUALIFICATION: {
                        scoreParameterNTBRequest.setIsEducationQualification(true);
                        break;
                    }
                    case ScoreParameter.NTB.EMPLOYMENT_TYPE: {
                        scoreParameterNTBRequest.setIsEmploymentType(true);
                        break;
                    }
                    case ScoreParameter.NTB.HOUSE_OWNERSHIP: {
                        scoreParameterNTBRequest.setIsHouseOwnership(true);
                        break;
                    }
                    case ScoreParameter.NTB.MARITIAL_STATUS: {
                        scoreParameterNTBRequest.setIsMaritialStatus(true);
                        break;
                    }
                    case ScoreParameter.NTB.ITR_SALARY_INCOME: {
                        scoreParameterNTBRequest.setIsItrSalaryIncome(true);
                        break;
                    }
                    case ScoreParameter.NTB.FIXED_OBLIGATION_RATIO: {
                        scoreParameterNTBRequest.setIsFixedObligationRatio(true);
                        break;
                    }
                    case ScoreParameter.NTB.CHEQUE_BOUNCES: {
                        scoreParameterNTBRequest.setIsChequeBounces(true);
                        break;
                    }
                    case ScoreParameter.NTB.DPD: {
                        scoreParameterNTBRequest.setIsDPD(true);
                        break;
                    }
                    case ScoreParameter.NTB.CONSTITUTION_OF_BORROWER:
                    {
                        try
                        {
                            Long proposedConstitutionOfUnit=Long.parseLong(primaryCorporateDetail.getProposedConstitutionOfUnit().toString());

                            if(!CommonUtils.isObjectNullOrEmpty(proposedConstitutionOfUnit))
                            {
                                scoreParameterNTBRequest.setConstitutionOfBorrowe(proposedConstitutionOfUnit);
                                scoreParameterNTBRequest.setIsConstitutionOfBorrower(true);
                            }
                            else
                            {
                                scoreParameterNTBRequest.setIsConstitutionOfBorrower(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CONSTITUTION_OF_BORROWER parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsConstitutionOfBorrower(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.ASSET_COVERAGE_RATIO:
                    {
                        try
                        {

                            Double collatralValue=0.0;
                            CGTMSEDataResponse cgtmseDataResponse=thirdPartyClient.getCalulation(applicationId);
                            if(!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse)
                                    && !CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getColleteralCoverage()))
                            {
                                collatralValue=cgtmseDataResponse.getColleteralCoverage();
                            }

                            scoreParameterNTBRequest.setColatralValue(collatralValue);
                            scoreParameterNTBRequest.setIsAssetCoverageRatio(true);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting ASSET_COVERAGE_RATIO parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsAssetCoverageRatio(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.UNIT_FACTORY_PREMISES:
                    {
                        try
                        {
                            Long unitFactoryPremises=primaryCorporateDetail.getProposedDetailsOfUnit().longValue();
                            scoreParameterNTBRequest.setUnitFactoryPremisesDetails(unitFactoryPremises);
                            scoreParameterNTBRequest.setIsUnitFactoryPremises(true);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting UNIT_FACTORY_PREMISES parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsUnitFactoryPremises(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.BALANCE_GESTATION_PERIOD:
                    {
                        try
                        {

                            Integer years = 0;
                            java.util.Calendar proposedDate = java.util.Calendar.getInstance();
                            proposedDate.setTime(primaryCorporateDetail.getProposedOperationDate());

                            java.util.Calendar appCreatedDate = java.util.Calendar.getInstance();
                            appCreatedDate.setTime(primaryCorporateDetail.getCreatedDate());

                            Integer yearsInBetween = proposedDate.get(java.util.Calendar.YEAR) - appCreatedDate.get(java.util.Calendar.YEAR);
                            Integer monthsDiff = 1;

                            monthsDiff = monthsDiff + proposedDate.get(java.util.Calendar.MONTH) - appCreatedDate.get(java.util.Calendar.MONTH);

                            Integer months = yearsInBetween * 12 + monthsDiff;

                            scoreParameterNTBRequest.setBalanceGestationPeriod(months.doubleValue());

                            if(months.doubleValue()>=0)
                                scoreParameterNTBRequest.setIsBalanceGestationPeriod(true);
                            else
                                scoreParameterNTBRequest.setIsBalanceGestationPeriod(false);

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting BALANCE_GESTATION_PERIOD parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsBalanceGestationPeriod(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.ENVIRONMENT_CATEGORY:
                    {
                        try
                        {
                            Long environmentCategory=null; // remaining
                            if(!CommonUtils.isObjectNullOrEmpty(environmentCategory))
                            {
                                scoreParameterNTBRequest.setEnvironmentCategory(environmentCategory);
                                scoreParameterNTBRequest.setIsEnvironmentCategory(true);
                            }
                            else
                            {
                                scoreParameterNTBRequest.setEnvironmentCategory(environmentCategory);
                                scoreParameterNTBRequest.setIsEnvironmentCategory(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting ENVIRONMENT_CATEGORY parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsEnvironmentCategory(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.CNW: {
                        try
                        {
                            Double networth=directorBackgroundDetailsRepository.getSumOfDirectorsNetworth(applicationId);
                            if(CommonUtils.isObjectNullOrEmpty(networth))
                            {
                                networth=0.0;
                            }
                            scoreParameterNTBRequest.setNetworth(networth);

                            Double loanAmount=primaryCorporateDetailRepository.getLoanAmountByApplication(applicationId);

                            if(!CommonUtils.isObjectNullOrEmpty(loanAmount))
                            {
                                scoreParameterNTBRequest.setLoanAmount(loanAmount);
                                scoreParameterNTBRequest.setIsCNW(true);
                            }
                            else
                            {
                                scoreParameterNTBRequest.setIsCNW(false);
                            }

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CNW parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsCNW(false);
                        }
                        break;
                    }
                }
                fundSeekerInputRequestList.add(fundSeekerInputRequest);
            }

            logger.info("SCORE PARAMETER ::::::::::"+scoreParameterNTBRequest.toString());

            logger.info("---------------------------------------------------------------");
            logger.info("---------------------------------------------------------------");
            logger.info("----------------------------END--------------------------------");

            scoringRequest.setDataList(fundSeekerInputRequestList);
            scoringRequest.setScoreParameterNTBRequest(scoreParameterNTBRequest);

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

    public Boolean calculateDirectorScore(ScoringRequestLoans scoringRequestLoans,DirectorBackgroundDetail directorBackgroundDetail,PrimaryCorporateDetail primaryCorporateDetail)
    {


        // Fetch Data for Calculate Director Score

        com.capitaworld.service.scoring.model.scoringmodel.ScoreParameterNTBRequest scoreParameterNTBRequest=new com.capitaworld.service.scoring.model.scoringmodel.ScoreParameterNTBRequest();

        Long scoreModelId=scoringRequestLoans.getScoringModelId();
        Long applicationId=scoringRequestLoans.getApplicationId();
        Long fpProductId=scoringRequestLoans.getFpProductId();

        logger.info("----------------------------START------------------------------");
        logger.info("---------------------------------------------------------------");
        logger.info("---------------------------------------------------------------");

        logger.info("DIRECTOR ID :: "+ directorBackgroundDetail.getId());
        logger.info("APPLICATION ID   :: "+ applicationId);
        logger.info("FP PRODUCT ID    :: "+ fpProductId);
        logger.info("SCORING MODEL ID :: "+ scoreModelId);

        ScoringResponse scoringResponseMain=null;

        // GET SCORE NTB LOAN PARAMETERS


        if(!CommonUtils.isObjectNullOrEmpty(scoreModelId))
        {
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.NTB);
            scoringRequest.setDirectorId(directorBackgroundDetail.getId());

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

                    case ScoreParameter.NTB.WORKING_EXPERIENCE: {

                        try
                        {
                            Double totalExperience= directorBackgroundDetail.getTotalExperience();
                            if(CommonUtils.isObjectNullOrEmpty(totalExperience))
                            {
                                totalExperience=0.0;
                            }
                            scoreParameterNTBRequest.setTotalworkingExperience(totalExperience);
                            scoreParameterNTBRequest.setIsWorkingExperience(true);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting WORKING_EXPERIENCE parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsWorkingExperience(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS: {
                        try
                        {
                            Boolean isFamilyMemberInBusiness=directorBackgroundDetail.getFamilyMemberInBusiness();
                            if(CommonUtils.isObjectNullOrEmpty(isFamilyMemberInBusiness) || isFamilyMemberInBusiness == false)
                            {
                                scoreParameterNTBRequest.setFamilyMemberInLineOfBusiness(2l);
                            }
                            else
                            {
                                scoreParameterNTBRequest.setFamilyMemberInLineOfBusiness(1l);
                            }
                            scoreParameterNTBRequest.setIsFamilyMemberInLineOfBusiness(true);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsFamilyMemberInLineOfBusiness(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.CIBIL_TRANSUNION_SCORE: {
                        try
                        {
                            CibilRequest cibilRequest=new CibilRequest();
                            cibilRequest.setApplicationId(applicationId);
                            cibilRequest.setPan(directorBackgroundDetail.getPanNo());

                            CibilScoreLogRequest cibilScoreLogRequest=cibilClient.getCibilScoreByPanCard(cibilRequest);
                            if(!CommonUtils.isObjectNullOrEmpty(cibilScoreLogRequest) && !CommonUtils.isObjectNullOrEmpty(cibilScoreLogRequest.getScore()))
                            {
                                Double cibilScore = Double.parseDouble(cibilScoreLogRequest.getScore());
                                scoreParameterNTBRequest.setCibilTransunionScore(cibilScore);
                                scoreParameterNTBRequest.setIsCibilTransunionScore(false);
                            }
                            else
                            {
                                scoreParameterNTBRequest.setIsCibilTransunionScore(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CIBIL_TRANSUNION_SCORE parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsCibilTransunionScore(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.AGE_OF_PROMOTOR: {
                        try
                        {

                            if(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetail.getDob()))
                            {
                                scoreParameterNTBRequest.setAgeOfPromotor(Math.ceil(CommonUtils.getAgeFromBirthDate(directorBackgroundDetail.getDob()).doubleValue()));
                                scoreParameterNTBRequest.setIsAgeOfPromotor(true);
                            }
                            else
                            {
                                scoreParameterNTBRequest.setIsAgeOfPromotor(false);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting AGE_OF_PROMOTOR parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsAgeOfPromotor(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.EDUCATION_QUALIFICATION: {
                        try
                        {
                            Long qualificationId= directorBackgroundDetail.getQualificationId().longValue();
                            scoreParameterNTBRequest.setEducationQualification(qualificationId);
                            scoreParameterNTBRequest.setIsEducationQualification(true);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting EDUCATION_QUALIFICATION parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsEducationQualification(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.EMPLOYMENT_TYPE: {
                        try
                        {
                            Long empType= directorBackgroundDetail.getEmploymentDetail().getEmploymentStatus();

                            if(!CommonUtils.isObjectNullOrEmpty(empType))
                            {
                                scoreParameterNTBRequest.setEmployeeType(empType);
                                scoreParameterNTBRequest.setIsEmploymentType(true);
                            }
                            else
                            {
                                scoreParameterNTBRequest.setIsEmploymentType(false);
                            }

                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting EMPLOYMENT_TYPE parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsEmploymentType(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.HOUSE_OWNERSHIP: {
                        try
                        {

                            Long residentType= directorBackgroundDetail.getResidenceType().longValue();
                            scoreParameterNTBRequest.setHouseOwnerShip(residentType);
                            scoreParameterNTBRequest.setIsHouseOwnership(true);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting HOUSE_OWNERSHIP parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsHouseOwnership(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.MARITIAL_STATUS: {
                        try
                        {

                            Long maritialStatus=directorBackgroundDetail.getMaritalStatus().longValue();
                            scoreParameterNTBRequest.setMaritialStatus(maritialStatus);
                            scoreParameterNTBRequest.setIsMaritialStatus(true);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting MARITIAL_STATUS parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsMaritialStatus(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.ITR_SALARY_INCOME: {
                        try
                        {
                            logger.info("Application id ===========>"+applicationId);
                            logger.info("directorBackgroundDetail id ===========>"+directorBackgroundDetail.getId());
                            Double avgSalary=corporateDirectorIncomeDetailsRepository.getTotalSalaryByApplicationIdAndDirectorId(applicationId,directorBackgroundDetail.getId());
                            if(avgSalary!=0)
                            {
                                avgSalary=avgSalary/3;
                            }

                            Double promotorContribution=primaryCorporateDetail.getPromoterContribution();

                            if(CommonUtils.isObjectNullOrEmpty(promotorContribution))
                            {
                                promotorContribution=0.0;
                            }

                            scoreParameterNTBRequest.setItrSalaryIncomeAvg(avgSalary);
                            scoreParameterNTBRequest.setItrPromotorContribution(promotorContribution);
                            scoreParameterNTBRequest.setIsItrSalaryIncome(true);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting ITR_SALARY_INCOME parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsItrSalaryIncome(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.FIXED_OBLIGATION_RATIO: {
                        try
                        {

                            Double totalIncome=corporateDirectorIncomeDetailsRepository.getTotalIncomeByApplicationIdAndDirectorId(applicationId,directorBackgroundDetail.getId());
                            Double totalEMI=financialArrangementDetailsRepository.getTotalEmiByApplicationIdAndDirectorId(applicationId,directorBackgroundDetail.getId());

                            if(CommonUtils.isObjectNullOrEmpty(totalIncome))
                            {
                                totalIncome=0.0;
                            }

                            if(CommonUtils.isObjectNullOrEmpty(totalEMI))
                            {
                                totalEMI=0.0;
                            }

                            scoreParameterNTBRequest.setItrSalaryIncome(totalIncome);
                            scoreParameterNTBRequest.setTotalEmiPaid(totalEMI);
                            scoreParameterNTBRequest.setIsFixedObligationRatio(true);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting FIXED_OBLIGATION_RATIO parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsFixedObligationRatio(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.CHEQUE_BOUNCES: {
                        try
                        {
                            Double noOfChequeBounce=null;
                            ReportRequest reportRequest=new ReportRequest();
                            reportRequest.setApplicationId(applicationId);
                            reportRequest.setDirectorId(directorBackgroundDetail.getId());

                            AnalyzerResponse analyzerResponse=analyzerClient.getDetailsFromReportByDirector(reportRequest);

                            Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)analyzerResponse.getData(),
                                    Data.class);
                            if(!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month())){
                                {
                                    if(!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month().doubleValue()))
                                    {
                                        noOfChequeBounce=data.getCheckBounceForLast6Month().doubleValue();
                                    }
                                    else
                                    {
                                        noOfChequeBounce=0.0;
                                    }

                                }
                            }
                            else
                            {
                                noOfChequeBounce=0.0;
                            }

                            scoreParameterNTBRequest.setChequeBouncesPastSixMonths(noOfChequeBounce);
                            scoreParameterNTBRequest.setIsChequeBounces(true);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting CHEQUE_BOUNCES parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsChequeBounces(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.DPD: {
                        try
                        {

                            //remaining
                            scoreParameterNTBRequest.setIsDPD(false);
                        }
                        catch (Exception e)
                        {
                            logger.error("error while getting DPD parameter");
                            e.printStackTrace();
                            scoreParameterNTBRequest.setIsDPD(false);
                        }
                        break;
                    }
                }
                fundSeekerInputRequestList.add(fundSeekerInputRequest);
            }

            logger.info("SCORE PARAMETER ::::::::::"+scoreParameterNTBRequest.toString());

            logger.info("---------------------------------------------------------------");
            logger.info("---------------------------------------------------------------");
            logger.info("----------------------------END--------------------------------");

            scoringRequest.setDataList(fundSeekerInputRequestList);
            scoringRequest.setScoreParameterNTBRequest(scoreParameterNTBRequest);

            try {
                scoringResponseMain = scoringClient.calculateScore(scoringRequest);
            }
            catch (Exception e) {
                logger.error("error while calling scoring");
                e.printStackTrace();
                LoansResponse loansResponse = new LoansResponse("error while calling scoring.", HttpStatus.INTERNAL_SERVER_ERROR.value());
                return false;
                //return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }

            if(scoringResponseMain.getStatus() == HttpStatus.OK.value())
            {
                logger.error("score is successfully calculated");
                LoansResponse loansResponse = new LoansResponse("score is successfully calculated", HttpStatus.OK.value());
                return true;
                //return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }
            else
            {
                logger.error("error while calling scoring");
                LoansResponse loansResponse = new LoansResponse("error while calling scoring.", HttpStatus.INTERNAL_SERVER_ERROR.value());
                return false;
                //return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }
        }

        return null;
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
    public ScoringModelReqRes getScoringModelTempList(ScoringModelReqRes scoringModelReqRes) {
        try {
            /*scoringModelReqRes.setOrgId(1l);*/
            UserResponse userResponse=usersClient.getOrgIdFromUserId(scoringModelReqRes.getUserId());

            if(!CommonUtils.isObjectNullOrEmpty(userResponse) && !CommonUtils.isObjectNullOrEmpty(userResponse.getData()))
            {
                scoringModelReqRes.setOrgId(Long.parseLong(userResponse.getData().toString()));
                /*scoringModelReqRes.setOrgId(1l);*/
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

            return scoringClient.getScoringModelTempList(scoringModelReqRes);
        }
        catch (Exception e)
        {
            logger.error("error while geting score model list from scoring");
            e.printStackTrace();
            return  new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
        }

    }

    @Override
    public ScoringModelReqRes saveScoringModelTemp(ScoringModelReqRes scoringModelReqRes) {

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

            return scoringClient.saveScoringModelTemp(scoringModelReqRes);
        }
        catch (Exception e)
        {
            logger.error("error while saving score model from scoring");
            e.printStackTrace();
            return  new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringModelReqRes getScoringModelTempDetail(ScoringModelReqRes scoringModelReqRes) {
        try {
            Long fpProductId=scoringModelReqRes.getFpProductId();
            try {
                return scoringClient.getScoringModelTempDetail(scoringModelReqRes);
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
    public ScoringModelReqRes saveScoringModelTempDetail(ScoringModelReqRes scoringModelReqRes) {
        try {

            return scoringClient.saveScoringModelTempDetail(scoringModelReqRes);
        }
        catch (Exception e)
        {
            logger.error("error while saving score model detail from scoring");
            e.printStackTrace();
            return  new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public List<GenericCheckerReqRes> sendToChecker(List<GenericCheckerReqRes> genericCheckerReqResList , Long userId) throws ScoringException {
        return scoringClient.sendToChecker(genericCheckerReqResList, userId);
    }
    
    @Override
    public ScoringModelReqRes getScoringModelMasterList(ScoringModelReqRes scoringModelReqRes) {
        try {
            /*scoringModelReqRes.setOrgId(1l);*/
            UserResponse userResponse=usersClient.getOrgIdFromUserId(scoringModelReqRes.getUserId());

            if(!CommonUtils.isObjectNullOrEmpty(userResponse) && !CommonUtils.isObjectNullOrEmpty(userResponse.getData()))
            {
                scoringModelReqRes.setOrgId(Long.parseLong(userResponse.getData().toString()));
                /*scoringModelReqRes.setOrgId(1l);*/
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

            return scoringClient.getScoringModelMasterList(scoringModelReqRes);
        }
        catch (Exception e)
        {
            logger.error("error while geting score model list from scoring");
            e.printStackTrace();
            return  new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
        }

    }
    
    @Override
    public ScoringModelReqRes getScoringModelMasterDetail(ScoringModelReqRes scoringModelReqRes) {
        try {
            Long fpProductId=scoringModelReqRes.getFpProductId();
            try {
                return scoringClient.getScoringModelMasterDetail(scoringModelReqRes);
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


    public Integer getFinYear(Long applicationId)
    {
        Integer year = 0;
        ITRConnectionResponse itrConnectionResponse = null;
        try
        {
                itrConnectionResponse = itrClient.getIsUploadAndYearDetails(applicationId);
        }
        catch (Exception e)
        {
            logger.error("error while calling itr client for getIsUploadAndYearDetails()"); e.printStackTrace();
        }
        try
        {
            if(!CommonUtils.isObjectNullOrEmpty(itrConnectionResponse) && !CommonUtils.isObjectNullOrEmpty(itrConnectionResponse.getData()))
            {
                Map<String,Object> map = (Map<String,Object>)itrConnectionResponse.getData();
                ITRBasicDetailsResponse res = MultipleJSONObjectHelper.getObjectFromMap(map, ITRBasicDetailsResponse.class);
                if(!CommonUtils.isObjectNullOrEmpty(res))
                {
                    year = Integer.valueOf(res.getYear());
                }
            }
        }
        catch (IOException e)
        {
            logger.error("error while getting year from itr response"); e.printStackTrace();
        }
        return year+1;
    }
}
