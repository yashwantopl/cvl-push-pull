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
import com.capitaworld.service.analyzer.model.common.Xn;
import com.capitaworld.service.gst.GstCalculation;
import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.gst.yuva.request.GSTR1Request;
import com.capitaworld.service.loans.domain.ScoringRequestDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.*;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.score.ScoreParameterRequestLoans;
import com.capitaworld.service.loans.model.score.ScoringRequestLoans;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundseeker.ScoringRequestDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantIncomeRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.utils.scoreexcel.ScoreExcelFileGenerator;
import com.capitaworld.service.loans.utils.scoreexcel.ScoreExcelReader;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.EmploymentWithPL;
import com.capitaworld.service.oneform.enums.EmploymentWithPLScoring;
import com.capitaworld.service.oneform.enums.scoring.EnvironmentCategory;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.rating.model.IndustryResponse;
import com.capitaworld.service.rating.model.IrrRequest;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.exception.ScoringException;
import com.capitaworld.service.scoring.model.*;
import com.capitaworld.service.scoring.model.scoringmodel.ScoringModelReqRes;
import com.capitaworld.service.scoring.utils.ScoreParameter;
import com.capitaworld.service.thirdparty.model.CGTMSEDataResponse;
import com.capitaworld.service.thirdpaty.client.ThirdPartyClient;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;

import com.google.gson.Gson;
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
public class ScoringServiceImpl implements ScoringService {


    private final Logger logger = LoggerFactory.getLogger(ScoringServiceImpl.class);

    @Autowired
    private OperatingStatementDetailsRepository operatingStatementDetailsRepository;

    @Autowired
    private LiabilitiesDetailsRepository liabilitiesDetailsRepository;

    @Autowired
    private AssetsDetailsRepository assetsDetailsRepository;

    @Autowired
    private BalanceSheetDetailRepository balanceSheetDetailRepository;

    @Autowired
    private ProfitibilityStatementDetailRepository profitibilityStatementDetailRepository;

    @Autowired
    private DirectorBackgroundDetailsRepository directorBackgroundDetailsRepository;

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

    @Autowired
    private RetailApplicantDetailRepository retailApplicantDetailRepository;

    @Autowired
    private RetailApplicantIncomeRepository retailApplicantIncomeRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private LoanApplicationService loanApplicationService;

    @Autowired
    private RatingClient ratingClient;

    @Autowired
    private OneFormClient oneFormClient;

    @Autowired
    private ScoringRequestDetailRepository scoringRequestDetailRepository;

    private static final String ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING = "Error while getting retail applicant detail for personal loan scoring : ";
    private static final String ERROR_WHILE_GETTING_FIELD_LIST = "error while getting field list : ";
    private static final String ERROR_WHILE_CALLING_SCORING = "error while calling scoring : ";

    private static final String SAVING_SCORING_REQUEST_DATA_FOR = "Saving Scoring Request Data for  =====> ";
    private static final String SCORE_IS_SUCCESSFULLY_CALCULATED = "score is successfully calculated";
    private static final String MSG_APPLICATION_ID = " APPLICATION ID   :: ";
    private static final String MSG_FP_PRODUCT_ID = " FP PRODUCT ID    :: ";
    private static final String MSG_SCORING_MODEL_ID = " SCORING MODEL ID :: ";
    private static final String MSG_SCORE_PARAMETER = "SCORE PARAMETER ::::::::::";
    private static final String ORG_ID_IS_NULL_OR_EMPTY  = "org id is null or empty : ";


    @Override
    public ResponseEntity<LoansResponse> calculateScoring(ScoringRequestLoans scoringRequestLoans) {

        PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOneByApplicationIdId(scoringRequestLoans.getApplicationId());

        /*if(CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)){
            RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(scoringRequestLoans.getApplicationId());
        }*/
        RetailApplicantDetail retailApplicantDetail = null;
        if (CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail) || CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getBusinessTypeId())) {
            retailApplicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(scoringRequestLoans.getApplicationId());
            if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
                logger.warn("Business type id is null or empty");
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse("Business type id is null or empty.", HttpStatus.BAD_REQUEST.value()),
                        HttpStatus.OK);
            }
        }
        if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
            return calculateRetailPersonalLoanScoring(scoringRequestLoans);
        }
        Long businessTypeId = primaryCorporateDetail.getBusinessTypeId().longValue();

        if (ScoreParameter.BusinessType.EXISTING_BUSINESS == businessTypeId) {
            return calculateExistingBusinessScoring(scoringRequestLoans);
        } else if (ScoreParameter.BusinessType.NTB == businessTypeId) {
            return calculateNTBScoring(scoringRequestLoans, primaryCorporateDetail);
        } else if (ScoreParameter.BusinessType.RETAIL_PERSONAL_LOAN == businessTypeId) {
            return calculateRetailPersonalLoanScoring(scoringRequestLoans);
        }

        return null;
    }

    private ResponseEntity<LoansResponse> calculateRetailPersonalLoanScoring(ScoringRequestLoans scoringRequestLoans) {

        ScoreParameterRetailRequest scoreParameterRetailRequest = new ScoreParameterRetailRequest();

        Long scoreModelId = scoringRequestLoans.getScoringModelId();
        Long applicationId = scoringRequestLoans.getApplicationId();
        Long fpProductId = scoringRequestLoans.getFpProductId();


        ///////// Start Getting Old Request ///////


        List<ScoringRequestDetail> scoringRequestDetailList = scoringRequestDetailRepository.getScoringRequestDetailByApplicationIdAndIsActive(applicationId);

        ScoringRequestDetail scoringRequestDetailSaved;

        if (scoringRequestDetailList.size() > 0) {
            logger.info("Getting Old Scoring request Data for  =====> " + applicationId);
            scoringRequestDetailSaved = scoringRequestDetailList.get(0);
            Gson gson = new Gson();
            scoreParameterRetailRequest = gson.fromJson(scoringRequestDetailSaved.getRequest(), ScoreParameterRetailRequest.class);
        }


        ScoringRequest scoringRequest = new ScoringRequest();
        scoringRequest.setScoringModelId(scoreModelId);
        scoringRequest.setFpProductId(fpProductId);
        scoringRequest.setApplicationId(applicationId);
        scoringRequest.setUserId(scoringRequestLoans.getUserId());
        scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.RETAIL_PERSONAL_LOAN);

        if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
            scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
        } else {
            scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
        }

        ///////// End  Getting Old Request ///////

        if (!(scoringRequestDetailList.size() > 0)) {
            logger.info("----------------------------START RETAIL PL ------------------------------");
            logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

            // GET SCORE RETAIL PERSONAL LOAN PARAMETERS

            RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(applicationId);

            if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
                logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }


            if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                ScoringResponse scoringResponse = null;
                try {
                    scoringResponse = scoringClient.listFieldByBusinessTypeId(scoringRequest);
                } catch (Exception e) {
                    logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
                }

                List<Map<String, Object>> dataList = (List<Map<String, Object>>) scoringResponse.getDataList();


                for (int i = 0; i < dataList.size(); i++) {

                    ModelParameterResponse modelParameterResponse = null;
                    try {
                        modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                                ModelParameterResponse.class);
                    } catch (IOException e) {
                        logger.error(CommonUtils.EXCEPTION,e);
                    }

                    FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                    fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                    fundSeekerInputRequest.setName(modelParameterResponse.getName());

                    switch (modelParameterResponse.getName()) {

                        case ScoreParameter.Retail.WORKING_EXPERIENCE_PL: {

                            try {
                                Double totalExperience = 0.0;
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceYear()))
                                    totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceYear());

                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceMonth()))
                                    totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceMonth() / 10);

                                scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                                scoreParameterRetailRequest.setWorkingExperience_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting WORKING_EXPERIENCE_PL parameter : ",e);
                                scoreParameterRetailRequest.setWorkingExperience_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.CIBIL_SCORE_PL: {

                            Double cibil_score = null;
                            try {

                                CibilRequest cibilRequest = new CibilRequest();
                                cibilRequest.setPan(retailApplicantDetail.getPan());
                                cibilRequest.setApplicationId(applicationId);

                                CibilScoreLogRequest cibilResponse = cibilClient.getCibilScoreByPanCard(cibilRequest);
                                if (!CommonUtils.isObjectNullOrEmpty(cibilResponse.getScore())) {
                                    cibil_score = Double.parseDouble(cibilResponse.getScore());
                                    scoreParameterRetailRequest.setCibilScore(cibil_score);
                                    scoreParameterRetailRequest.setCibilScore_p(true);
                                } else {
                                    scoreParameterRetailRequest.setCibilScore_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting CIBIL_SCORE_PL parameter from CIBIL client : ",e);
                                scoreParameterRetailRequest.setCibilScore_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.AGE_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
                                    scoreParameterRetailRequest.setAge(Math.ceil(CommonUtils.getAgeFromBirthDate(retailApplicantDetail.getBirthDate()).doubleValue()));
                                    scoreParameterRetailRequest.setAge_p(true);
                                } else {
                                    scoreParameterRetailRequest.setAge_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting AGE_PL parameter : ",e);
                                scoreParameterRetailRequest.setAge_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.EDUCATION_QUALI_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getEducationQualification())) {
                                    scoreParameterRetailRequest.setEducationQualification(retailApplicantDetail.getEducationQualification().longValue());
                                    scoreParameterRetailRequest.setEducationQualifaction_p(true);
                                } else {
                                    scoreParameterRetailRequest.setEducationQualifaction_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting EDUCATION_QUALI_PL parameter : ",e);
                                scoreParameterRetailRequest.setEducationQualifaction_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.EMPLOYEMENT_TYPE_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getEmploymentStatus())) {
                                    scoreParameterRetailRequest.setEmploymentType(retailApplicantDetail.getEmploymentStatus().longValue());
                                    scoreParameterRetailRequest.setEmployementType_p(true);
                                } else {
                                    scoreParameterRetailRequest.setEmployementType_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting EMPLOYEMENT_TYPE_PL parameter : ",e);
                                scoreParameterRetailRequest.setEmployementType_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.HOUSE_OWNERSHIP_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getResidenceType())) {
                                    scoreParameterRetailRequest.setHouseOwnership(retailApplicantDetail.getResidenceType().longValue());
                                    scoreParameterRetailRequest.setHouseOwnership_p(true);
                                } else {
                                    scoreParameterRetailRequest.setHouseOwnership_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting HOUSE_OWNERSHIP_PL parameter : ",e);
                                scoreParameterRetailRequest.setHouseOwnership_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.MARITAL_STATUS_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getStatusId())) {
                                    scoreParameterRetailRequest.setMaritalStatus(retailApplicantDetail.getStatusId().longValue());
                                    scoreParameterRetailRequest.setMaritalStatus_p(true);
                                } else {
                                    scoreParameterRetailRequest.setMaritalStatus_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting MARITAL_STATUS_PL parameter : ",e);
                                scoreParameterRetailRequest.setMaritalStatus_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.CATEGORY_INFO_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getEmploymentWith())) {

                                    Boolean salaryWithBank=isSalaryAccountWithBank(applicationId);
                                    Long employmentWithPlValue=null;

                                    if(EmploymentWithPL.CENTRAL_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith())
                                    {
                                        if(true == salaryWithBank)
                                            employmentWithPlValue= EmploymentWithPLScoring.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                        else
                                            employmentWithPlValue= EmploymentWithPLScoring.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();

                                    }
                                    else if(EmploymentWithPL.STATE_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith())
                                    {
                                        if(true == salaryWithBank)
                                            employmentWithPlValue= EmploymentWithPLScoring.STATE_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                        else
                                            employmentWithPlValue= EmploymentWithPLScoring.STATE_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.PSU.getId() == retailApplicantDetail.getEmploymentWith())
                                    {
                                        if(true == salaryWithBank)
                                            employmentWithPlValue= EmploymentWithPLScoring.PSU_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                        else
                                            employmentWithPlValue= EmploymentWithPLScoring.PSU_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.CORPORATE.getId() == retailApplicantDetail.getEmploymentWith())
                                    {
                                        if(true == salaryWithBank)
                                            employmentWithPlValue= EmploymentWithPLScoring.CORPORATE_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                        else
                                            employmentWithPlValue= EmploymentWithPLScoring.CORPORATE_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.EDUCATIONAL_INSTITUTE.getId() == retailApplicantDetail.getEmploymentWith())
                                    {
                                        employmentWithPlValue= EmploymentWithPLScoring.EDUCATIONAL_INSTITUTE.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.OTHERS.getId() == retailApplicantDetail.getEmploymentWith())
                                    {
                                        employmentWithPlValue= EmploymentWithPLScoring.OTHERS.getId().longValue();
                                    }
                                    scoreParameterRetailRequest.setCategoryInfo(employmentWithPlValue);
                                    scoreParameterRetailRequest.setCategoryInfo_p(true);
                                } else {
                                    scoreParameterRetailRequest.setCategoryInfo_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting CATEGORY_INFO_PL parameter : ",e);
                                scoreParameterRetailRequest.setCategoryInfo_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.FIXED_OBLI_INFO_RATIO_PL: {

                            Double totalEMI = financialArrangementDetailsRepository.getTotalEmiByApplicationId(applicationId);
                            if (CommonUtils.isObjectNullOrEmpty(totalEMI)) {
                                totalEMI = 0.0;
                            }
                            Double totalIncomeLastYear = 0.0;
                            try {
                                totalIncomeLastYear = retailApplicantIncomeRepository.getTotalIncomeOfMaxYearByApplicationId(applicationId);
                                if (CommonUtils.isObjectNullOrEmpty(totalIncomeLastYear)) {
                                    totalIncomeLastYear = 0.0;
                                }
                            } catch (Exception e) {
                                logger.error("error while getting total income from retail applicant income detail : ",e);
                            }

                            scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
                            scoreParameterRetailRequest.setLastYearTotalIncomeFromITR(totalIncomeLastYear);
                            scoreParameterRetailRequest.setFixedObligationRatio_p(true);
                            break;
                        }
                        case ScoreParameter.Retail.CHEQUE_BOUNCE_PAST_SIX_MONTH_PL: {

                            try {
                                Double noOfChequeBounce = null;
                                ReportRequest reportRequest = new ReportRequest();
                                reportRequest.setApplicationId(applicationId);

                                AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportByDirector(reportRequest);

                                Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),
                                        Data.class);
                                if (!CommonUtils.isObjectNullOrEmpty(data) && !CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month())) {
                                    {
                                        if (!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month().doubleValue())) {
                                            noOfChequeBounce = data.getCheckBounceForLast6Month().doubleValue();
                                        } else {
                                            noOfChequeBounce = 0.0;
                                        }

                                    }
                                } else {
                                    noOfChequeBounce = 0.0;
                                }

                                scoreParameterRetailRequest.setChequeBounce(noOfChequeBounce);
                                scoreParameterRetailRequest.setChequeBounce_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting CHEQUE_BOUNCE_PAST_SIX_MONTH_PL parameter : ",e);
                                scoreParameterRetailRequest.setChequeBounce_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.Retail.DAY_PAST_DUE_PL: {
                            try {

                                CibilResponse cibilResponse = cibilClient.getDPDLastXMonth(applicationId,retailApplicantDetail.getPan());
                                if (!CommonUtils.isObjectNullOrEmpty(cibilResponse) && !CommonUtils.isListNullOrEmpty(cibilResponse.getListData())) {
                                    List<Integer> listDPD = (List<Integer>) cibilResponse.getListData();

                                    Integer maxDPD = Collections.max(listDPD);
                                    if (!CommonUtils.isObjectNullOrEmpty(maxDPD)) {
                                        scoreParameterRetailRequest.setDpd(maxDPD.doubleValue());
                                    } else {
                                        scoreParameterRetailRequest.setDpd(0.0);
                                    }
                                } else {
                                    scoreParameterRetailRequest.setDpd(0.0);
                                }
                                scoreParameterRetailRequest.setDPD_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting DAY_PAST_DUE_PL parameter from CIBIL client : ",e);
                                scoreParameterRetailRequest.setDPD_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.NET_ANNUAL_INCOME_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMonthlyIncome())) {
                                    scoreParameterRetailRequest.setNetAnnualIncome(retailApplicantDetail.getMonthlyIncome() * 12);
                                    scoreParameterRetailRequest.setNetAnnualIncome_p(true);
                                } else {
                                    scoreParameterRetailRequest.setNetAnnualIncome_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting NET_ANNUAL_INCOME_PL parameter : ",e);
                                scoreParameterRetailRequest.setNetAnnualIncome_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.EMI_NMI_PL: {
                        /*scoreParameterRetailRequest.setEminmi();
                        scoreParameterRetailRequest.setEmiNmi_p();*/

                            break;
                        }
                        default:
                            break;

                    }
                }

                logger.info(MSG_SCORE_PARAMETER + scoreParameterRetailRequest.toString());

                logger.info("----------------------------END-------------------------------------------");

                Gson g = new Gson();
                ScoringRequestDetail scoringRequestDetail = new ScoringRequestDetail();

                try {
                    scoringRequestDetail.setApplicationId(applicationId);
                    scoringRequestDetail.setRequest(g.toJson(scoreParameterRetailRequest));
                    scoringRequestDetail.setCreatedDate(new Date());
                    scoringRequestDetail.setIsActive(true);
                    scoringRequestDetailRepository.save(scoringRequestDetail);

                    logger.info(SAVING_SCORING_REQUEST_DATA_FOR + applicationId);
                } catch (Exception e) {
                    logger.error(CommonUtils.EXCEPTION,e);
                }
            }
        }

        scoringRequest.setScoreParameterRetailRequest(scoreParameterRetailRequest);

        try {
            scoringClient.calculateScore(scoringRequest);

            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING,e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }

    public ResponseEntity<LoansResponse> calculateRetailPersonalLoanScoringList(List<ScoringRequestLoans> scoringRequestLoansList) {

        List<ScoringRequest> scoringRequestList=new ArrayList<ScoringRequest>();

        ScoreParameterRetailRequest scoreParameterRetailRequest = null;

        for(ScoringRequestLoans scoringRequestLoans:scoringRequestLoansList)
        {

            Long scoreModelId = scoringRequestLoans.getScoringModelId();
            Long applicationId = scoringRequestLoans.getApplicationId();
            Long fpProductId = scoringRequestLoans.getFpProductId();


            ///////// Start Getting Old Request ///////


            /*List<ScoringRequestDetail> scoringRequestDetailList = scoringRequestDetailRepository.getScoringRequestDetailByApplicationIdAndIsActive(applicationId);

            ScoringRequestDetail scoringRequestDetailSaved = new ScoringRequestDetail();

            if (scoringRequestDetailList.size() > 0) {
                logger.info("Getting Old Scoring request Data for  =====> " + applicationId);
                scoringRequestDetailSaved = scoringRequestDetailList.get(0);
                Gson gson = new Gson();
                scoreParameterRetailRequest = gson.fromJson(scoringRequestDetailSaved.getRequest(), ScoreParameterRetailRequest.class);
            }*/

            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.RETAIL_PERSONAL_LOAN);

            if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
                scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
            } else {
                scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
            }

            ///////// End  Getting Old Request ///////

            if (CommonUtils.isObjectNullOrEmpty(scoreParameterRetailRequest)) {
                scoreParameterRetailRequest= new ScoreParameterRetailRequest();
                logger.info("----------------------------START RETAIL PL ------------------------------");

                logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

                // GET SCORE RETAIL PERSONAL LOAN PARAMETERS

                RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(applicationId);

                if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
                    logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                    break;
                }


                if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                    // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                    ScoringResponse scoringResponse = null;
                    try {
                        scoringResponse = scoringClient.listFieldByBusinessTypeId(scoringRequest);
                    } catch (Exception e) {
                        logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
                    }

                    List<Map<String, Object>> dataList = new ArrayList<>();
                    if (scoringResponse != null && scoringResponse.getDataList() != null) {
                        dataList = (List<Map<String, Object>>) scoringResponse.getDataList();
                    }

                    for (int i = 0; i < dataList.size(); i++) {

                        ModelParameterResponse modelParameterResponse = null;
                        try {
                            modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                                    ModelParameterResponse.class);
                            if(modelParameterResponse == null){
                                continue;
                            }
                        } catch (IOException | NullPointerException e) {
                            logger.error(CommonUtils.EXCEPTION,e);
                            continue;
                        }

                        FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                        fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                        fundSeekerInputRequest.setName(modelParameterResponse.getName());

                        switch (modelParameterResponse.getName()) {

                            case ScoreParameter.Retail.WORKING_EXPERIENCE_PL: {

                                try {
                                    Double totalExperience = 0.0;
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceYear()))
                                        totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceYear());

                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceMonth()))
                                        totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceMonth() / 10);

                                    scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                                    scoreParameterRetailRequest.setWorkingExperience_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting WORKING_EXPERIENCE_PL parameter : ",e);
                                    scoreParameterRetailRequest.setWorkingExperience_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.CIBIL_SCORE_PL: {

                                Double cibil_score = null;
                                try {

                                    CibilRequest cibilRequest = new CibilRequest();
                                    cibilRequest.setPan(retailApplicantDetail.getPan());
                                    cibilRequest.setApplicationId(applicationId);

                                    CibilScoreLogRequest cibilResponse = cibilClient.getCibilScoreByPanCard(cibilRequest);
                                    if (!CommonUtils.isObjectNullOrEmpty(cibilResponse.getScore())) {
                                        cibil_score = Double.parseDouble(cibilResponse.getScore());
                                        scoreParameterRetailRequest.setCibilScore(cibil_score);
                                        scoreParameterRetailRequest.setCibilScore_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setCibilScore_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting CIBIL_SCORE_PL parameter from CIBIL client : ",e);
                                    scoreParameterRetailRequest.setCibilScore_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.AGE_PL: {

                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
                                        scoreParameterRetailRequest.setAge(Math.ceil(CommonUtils.getAgeFromBirthDate(retailApplicantDetail.getBirthDate()).doubleValue()));
                                        scoreParameterRetailRequest.setAge_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setAge_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting AGE_PL parameter : ",e);
                                    scoreParameterRetailRequest.setAge_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.EDUCATION_QUALI_PL: {

                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getEducationQualification())) {
                                        scoreParameterRetailRequest.setEducationQualification(retailApplicantDetail.getEducationQualification().longValue());
                                        scoreParameterRetailRequest.setEducationQualifaction_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setEducationQualifaction_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting EDUCATION_QUALI_PL parameter : ",e);
                                    scoreParameterRetailRequest.setEducationQualifaction_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.EMPLOYEMENT_TYPE_PL: {

                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getEmploymentStatus())) {
                                        scoreParameterRetailRequest.setEmploymentType(retailApplicantDetail.getEmploymentStatus().longValue());
                                        scoreParameterRetailRequest.setEmployementType_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setEmployementType_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting EMPLOYEMENT_TYPE_PL parameter : ",e);
                                    scoreParameterRetailRequest.setEmployementType_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.HOUSE_OWNERSHIP_PL: {

                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getResidenceType())) {
                                        scoreParameterRetailRequest.setHouseOwnership(retailApplicantDetail.getResidenceType().longValue());
                                        scoreParameterRetailRequest.setHouseOwnership_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setHouseOwnership_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting HOUSE_OWNERSHIP_PL parameter : ",e);
                                    scoreParameterRetailRequest.setHouseOwnership_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.MARITAL_STATUS_PL: {

                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getStatusId())) {
                                        scoreParameterRetailRequest.setMaritalStatus(retailApplicantDetail.getStatusId().longValue());
                                        scoreParameterRetailRequest.setMaritalStatus_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setMaritalStatus_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting MARITAL_STATUS_PL parameter : ",e);
                                    scoreParameterRetailRequest.setMaritalStatus_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.CATEGORY_INFO_PL: {

                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getEmploymentWith())) {

                                        Boolean salaryWithBank=isSalaryAccountWithBank(applicationId);
                                        Long employmentWithPlValue=null;

                                        if(EmploymentWithPL.CENTRAL_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith())
                                        {
                                            if(true == salaryWithBank)
                                                employmentWithPlValue= EmploymentWithPLScoring.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue= EmploymentWithPLScoring.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();

                                        }
                                        else if(EmploymentWithPL.STATE_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith())
                                        {
                                            if(true == salaryWithBank)
                                                employmentWithPlValue= EmploymentWithPLScoring.STATE_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue= EmploymentWithPLScoring.STATE_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.PSU.getId() == retailApplicantDetail.getEmploymentWith())
                                        {
                                            if(true == salaryWithBank)
                                                employmentWithPlValue= EmploymentWithPLScoring.PSU_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue= EmploymentWithPLScoring.PSU_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.CORPORATE.getId() == retailApplicantDetail.getEmploymentWith())
                                        {
                                            if(true == salaryWithBank)
                                                employmentWithPlValue= EmploymentWithPLScoring.CORPORATE_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue= EmploymentWithPLScoring.CORPORATE_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.EDUCATIONAL_INSTITUTE.getId() == retailApplicantDetail.getEmploymentWith())
                                        {
                                            employmentWithPlValue= EmploymentWithPLScoring.EDUCATIONAL_INSTITUTE.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.OTHERS.getId() == retailApplicantDetail.getEmploymentWith())
                                        {
                                            employmentWithPlValue= EmploymentWithPLScoring.OTHERS.getId().longValue();
                                        }
                                        scoreParameterRetailRequest.setCategoryInfo(employmentWithPlValue);
                                        scoreParameterRetailRequest.setCategoryInfo_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setCategoryInfo_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting CATEGORY_INFO_PL parameter : ",e);
                                    scoreParameterRetailRequest.setCategoryInfo_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.FIXED_OBLI_INFO_RATIO_PL: {

                                Double totalEMI = financialArrangementDetailsRepository.getTotalEmiByApplicationId(applicationId);
                                if (CommonUtils.isObjectNullOrEmpty(totalEMI)) {
                                    totalEMI = 0.0;
                                }
                                Double totalIncomeLastYear = 0.0;
                                try {
                                    totalIncomeLastYear = retailApplicantIncomeRepository.getTotalIncomeOfMaxYearByApplicationId(applicationId);
                                    if (CommonUtils.isObjectNullOrEmpty(totalIncomeLastYear)) {
                                        totalIncomeLastYear = 0.0;
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting total income from retail applicant income detail : ",e);
                                }

                                scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
                                scoreParameterRetailRequest.setLastYearTotalIncomeFromITR(totalIncomeLastYear);
                                scoreParameterRetailRequest.setFixedObligationRatio_p(true);
                                break;
                            }
                            case ScoreParameter.Retail.CHEQUE_BOUNCE_PAST_SIX_MONTH_PL: {

                                try {
                                    Double noOfChequeBounce = null;
                                    ReportRequest reportRequest = new ReportRequest();
                                    reportRequest.setApplicationId(applicationId);

                                    AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportByDirector(reportRequest);

                                    Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),
                                            Data.class);
                                    if (!CommonUtils.isObjectNullOrEmpty(data) && !CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month())) {
                                        {
                                            if (!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month().doubleValue())) {
                                                noOfChequeBounce = data.getCheckBounceForLast6Month().doubleValue();
                                            } else {
                                                noOfChequeBounce = 0.0;
                                            }

                                        }
                                    } else {
                                        noOfChequeBounce = 0.0;
                                    }

                                    scoreParameterRetailRequest.setChequeBounce(noOfChequeBounce);
                                    scoreParameterRetailRequest.setChequeBounce_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting CHEQUE_BOUNCE_PAST_SIX_MONTH_PL parameter : ",e);
                                    scoreParameterRetailRequest.setChequeBounce_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.Retail.DAY_PAST_DUE_PL: {
                                try {

                                    CibilResponse cibilResponse = cibilClient.getDPDLastXMonth(applicationId,retailApplicantDetail.getPan());
                                    if (!CommonUtils.isObjectNullOrEmpty(cibilResponse) && !CommonUtils.isListNullOrEmpty(cibilResponse.getListData())) {
                                        List<Integer> listDPD = (List<Integer>) cibilResponse.getListData();

                                        Integer maxDPD = Collections.max(listDPD);
                                        if (!CommonUtils.isObjectNullOrEmpty(maxDPD)) {
                                            scoreParameterRetailRequest.setDpd(maxDPD.doubleValue());
                                        } else {
                                            scoreParameterRetailRequest.setDpd(0.0);
                                        }
                                    } else {
                                        scoreParameterRetailRequest.setDpd(0.0);
                                    }
                                    scoreParameterRetailRequest.setDPD_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting DAY_PAST_DUE_PL parameter from CIBIL client : ",e);
                                    scoreParameterRetailRequest.setDPD_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.NET_ANNUAL_INCOME_PL: {

                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMonthlyIncome())) {
                                        scoreParameterRetailRequest.setNetAnnualIncome(retailApplicantDetail.getMonthlyIncome() * 12);
                                        scoreParameterRetailRequest.setNetAnnualIncome_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setNetAnnualIncome_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting NET_ANNUAL_INCOME_PL parameter : ",e);
                                    scoreParameterRetailRequest.setNetAnnualIncome_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.EMI_NMI_PL: {
                        /*scoreParameterRetailRequest.setEminmi();
                        scoreParameterRetailRequest.setEmiNmi_p();*/

                                break;
                            }
                            default:
                                break;

                        }
                    }

                    logger.info(MSG_SCORE_PARAMETER + scoreParameterRetailRequest.toString());

                    logger.info("----------------------------END-------------------------------------------");

                    Gson g = new Gson();
                    ScoringRequestDetail scoringRequestDetail = new ScoringRequestDetail();

                    try {
                        scoringRequestDetail.setApplicationId(applicationId);
                        scoringRequestDetail.setRequest(g.toJson(scoreParameterRetailRequest));
                        scoringRequestDetail.setCreatedDate(new Date());
                        scoringRequestDetail.setIsActive(true);
                        scoringRequestDetailRepository.save(scoringRequestDetail);

                        logger.info(SAVING_SCORING_REQUEST_DATA_FOR + applicationId);
                    } catch (Exception e) {
                        logger.error(CommonUtils.EXCEPTION,e);
                    }
                }
            }

            scoringRequest.setScoreParameterRetailRequest(scoreParameterRetailRequest);
            scoringRequestList.add(scoringRequest);
        }

        try {
            scoringClient.calculateScoreList(scoringRequestList);

            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING,e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }

    private Boolean isSalaryAccountWithBank(Long applicationId) {

        Boolean salaryWithBank=false;

        AnalyzerResponse analyzerResponse=null;
        Data data=null;

        try {
            ReportRequest reportRequest = new ReportRequest();
            reportRequest.setApplicationId(applicationId);
            analyzerResponse = analyzerClient.getDetailsFromReport(reportRequest);
            if (!CommonUtils.isObjectNullOrEmpty(analyzerResponse)) {
                     data = MultipleJSONObjectHelper
                        .getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(), Data.class);
            }
        } catch (Exception e) {
            logger.error("Exception while getting perfios data======={}", e.getMessage());
        }


        //Check BankStatement Last 6 Month Transaction
        try {
            if (data != null) {
                List<Xn> xns = data.getXns().getXn();
                for (Xn xn : xns) {
                    if (xn.getCategory().equalsIgnoreCase("Salary")) {
                        salaryWithBank=true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.info("EXCEPTION IS GETTING WHILE GETTING BANK STATEMENT DATA------------>>>>>>" + e.getMessage());
        }
        return salaryWithBank;
    }

    @Override
    public ResponseEntity<LoansResponse> calculateExistingBusinessScoring(ScoringRequestLoans scoringRequestLoans) {

        ScoringParameterRequest scoringParameterRequest = new ScoringParameterRequest();

        Long scoreModelId = scoringRequestLoans.getScoringModelId();
        Long applicationId = scoringRequestLoans.getApplicationId();
        Long fpProductId = scoringRequestLoans.getFpProductId();

        ///////// Get Financial Type Id from ITR////////

        Integer financialTypeId = 3;

        ITRConnectionResponse itrConnectionResponse = null;
        try {
            itrConnectionResponse = itrClient.getIsUploadAndYearDetails(applicationId);

            if (!CommonUtils.isObjectNullOrEmpty(itrConnectionResponse) && !CommonUtils.isObjectNullOrEmpty(itrConnectionResponse.getData())) {
                Map<String, Object> map = (Map<String, Object>) itrConnectionResponse.getData();
                ITRBasicDetailsResponse res = MultipleJSONObjectHelper.getObjectFromMap(map, ITRBasicDetailsResponse.class);
                if (!CommonUtils.isObjectNullOrEmpty(res) && !CommonUtils.isObjectNullOrEmpty(res.getItrFinancialType())) {
                    financialTypeId = Integer.valueOf(res.getItrFinancialType());
                }
            }
        } catch (IOException e) {
            logger.error("error while getting Financial Type Id from itr response : ",e);
        }

        /////////


        List<ScoringRequestDetail> scoringRequestDetailList = scoringRequestDetailRepository.getScoringRequestDetailByApplicationIdAndIsActive(applicationId);


        ScoringRequestDetail scoringRequestDetailSaved;

        if (scoringRequestDetailList.size() > 0) {
            logger.info("Getting Old Scoring request Data for  =====> " + applicationId);
            scoringRequestDetailSaved = scoringRequestDetailList.get(0);
            Gson gson = new Gson();
            scoringParameterRequest = gson.fromJson(scoringRequestDetailSaved.getRequest(), ScoringParameterRequest.class);
        }


        ScoringResponse scoringResponseMain = null;

        ScoringRequest scoringRequest = new ScoringRequest();
        scoringRequest.setScoringModelId(scoreModelId);
        scoringRequest.setFpProductId(fpProductId);
        scoringRequest.setApplicationId(applicationId);
        scoringRequest.setUserId(scoringRequestLoans.getUserId());
        scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.EXISTING_BUSINESS);

        if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
            scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
        } else {
            scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
        }

        logger.info("Financial Type Id ::::::::::::::::================>" + scoringRequest.getFinancialTypeId());
        if (!(scoringRequestDetailList.size() > 0)) {

            logger.info("Scoring Data Fetched First Time  =====> " + applicationId);

            logger.info("----------------------------START EXISTING LOAN ------------------------------");

            logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

            // start Get GST Parameter

            String gstNumber = corporateApplicantDetailRepository.getGstInByApplicationId(applicationId);
            Double loanAmount = primaryCorporateDetailRepository.getLoanAmountByApplication(applicationId);

            CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);


            GstResponse gstResponse = null;
            GstResponse gstResponseScoring = null;
            GstCalculation gstCalculation = new GstCalculation();

            try {
                GSTR1Request gstr1Request = new GSTR1Request();
                gstr1Request.setGstin(gstNumber);
                gstResponse = gstClient.getCalculations(gstr1Request);

                if (!CommonUtils.isObjectNullOrEmpty(gstResponse) && !CommonUtils.isObjectNullOrEmpty(gstResponse.getData())) {
                    gstCalculation = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) gstResponse.getData(),
                            GstCalculation.class);
                }

            } catch (Exception e) {
                logger.error("error while getting GST parameter : ",e);
            }


            // get GST Data for Sales Show A Rising Trend

            try {
                gstResponseScoring = gstClient.getCalculationForScoring(gstNumber);
            } catch (Exception e) {
                logger.error("error while getting GST parameter for GST Sales Show A Rising Trend : ",e);
            }

            // end Get GST Parameter

            int currentYear = getFinYear(applicationId);
            if (CommonUtils.isObjectNullOrEmpty(currentYear)) {
                logger.error("error while getting current year from itr");
                LoansResponse loansResponse = new LoansResponse("error while getting current year from itr.", HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }

            // CMA
            OperatingStatementDetails operatingStatementDetailsFY = new OperatingStatementDetails();
            OperatingStatementDetails operatingStatementDetailsSY = new OperatingStatementDetails();
            OperatingStatementDetails operatingStatementDetailsTY = new OperatingStatementDetails();


            LiabilitiesDetails liabilitiesDetailsFY;
            LiabilitiesDetails liabilitiesDetailsSY;
            LiabilitiesDetails liabilitiesDetailsTY = new LiabilitiesDetails();

            AssetsDetails assetsDetailsFY;
            AssetsDetails assetsDetailsSY = new AssetsDetails();
            AssetsDetails assetsDetailsTY = new AssetsDetails();

            if (ScoreParameter.FinancialTypeForITR.THREE_YEAR_ITR == financialTypeId) {
                operatingStatementDetailsTY = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear - 1 + "");
                operatingStatementDetailsSY = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear - 2 + "");
                operatingStatementDetailsFY = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear - 3 + "");

                liabilitiesDetailsTY = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear - 1 + "");
              /*  liabilitiesDetailsSY = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear - 2 + "");
                  liabilitiesDetailsFY = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear - 3 + ""); */

                assetsDetailsTY = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear - 1 + "");
                assetsDetailsSY = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear - 2 + "");
//                assetsDetailsFY = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear - 3 + "");
            } else if (ScoreParameter.FinancialTypeForITR.ONE_YEAR_ITR == financialTypeId || ScoreParameter.FinancialTypeForITR.PRESUMPTIVE == financialTypeId) {
                operatingStatementDetailsTY = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear - 1 + "");
                liabilitiesDetailsTY = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear - 1 + "");
                assetsDetailsTY = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear - 1 + "");
            }

            ///////////////

            // Get Director Background detail

            DirectorBackgroundDetail mainDirectorBackgroundDetail = directorBackgroundDetailsRepository.getMainDirectorByApplicationId(applicationId);

            // get Primary Corporate Detail

            PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOneByApplicationIdId(applicationId);

            // GET SCORE CORPORATE LOAN PARAMETERS


            if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                ScoringResponse scoringResponse = null;
                try {
                    scoringResponse = scoringClient.listFieldByBusinessTypeId(scoringRequest);
                } catch (Exception e) {
                    logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
                }

                List<Map<String, Object>> dataList = (List<Map<String, Object>>) scoringResponse.getDataList();

                //List<FundSeekerInputRequest> fundSeekerInputRequestList = new ArrayList<>(dataList.size());

                logger.info("dataList=====================================>>>>>>>>>>>>>>>>>>>>>>" + dataList.size());

                for (int i=0;i<dataList.size();i++){

                    ModelParameterResponse modelParameterResponse = null;
                    try {
                        modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                                ModelParameterResponse.class);
                    } catch (IOException e) {
                        logger.error(CommonUtils.EXCEPTION,e);
                    }

                /*FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                fundSeekerInputRequest.setName(modelParameterResponse.getName());*/

                    switch (modelParameterResponse.getName()) {

                        case ScoreParameter.COMBINED_NETWORTH: {
                            try {
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

                            } catch (Exception e) {
                                logger.error("error while getting COMBINED_NETWORTH parameter : ",e);
                                scoringParameterRequest.setCombinedNetworth_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.CUSTOMER_ASSOCIATE_CONCERN: {
                            Double customer_ass_concern_year = null;
                            try {

                                CibilResponse cibilResponse = cibilClient.getDPDYears(applicationId);
                                if (!CommonUtils.isObjectNullOrEmpty(cibilResponse) && !CommonUtils.isObjectNullOrEmpty(cibilResponse.getData())) {
                                    customer_ass_concern_year = (Double) cibilResponse.getData();

                                    scoringParameterRequest.setCustomerAssociateConcern(customer_ass_concern_year);
                                    scoringParameterRequest.setCustomerAsscociateConcern_p(true);
                                } else {
                                    scoringParameterRequest.setCustomerAsscociateConcern_p(false);
                                }

                            } catch (Exception e) {
                                logger.error("error while getting CUSTOMER_ASSOCIATE_CONCERN parameter from CIBIL client : ",e);
                                scoringParameterRequest.setCustomerAsscociateConcern_p(false);
                            }
                            break;

                        }
                        case ScoreParameter.CIBIL_TRANSUNION_SCORE: {
                            Double cibil_score_avg_promotor = null;
                            try {

                                CibilRequest cibilRequest = new CibilRequest();
                                cibilRequest.setApplicationId(applicationId);

                                CibilResponse cibilResponse = cibilClient.getCibilScore(cibilRequest);
                                if (!CommonUtils.isObjectNullOrEmpty(cibilResponse.getData())) {
                                    cibil_score_avg_promotor = (Double) cibilResponse.getData();
                                    scoringParameterRequest.setCibilTransuniunScore(cibil_score_avg_promotor);
                                    scoringParameterRequest.setCibilTransunionScore_p(true);
                                } else {
                                    scoringParameterRequest.setCibilTransunionScore_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting CIBIL_TRANSUNION_SCORE parameter from CIBIL client : ",e);
                                scoringParameterRequest.setCibilTransunionScore_p(false);
                            }

                            break;
                        }

                        case ScoreParameter.EXPERIENCE_IN_THE_BUSINESS: {
                            Double directorExperience = directorBackgroundDetailsRepository.getMaxOfDirectorsExperience(applicationId);

                            if (!CommonUtils.isObjectNullOrEmpty(directorExperience)) {
                                scoringParameterRequest.setExperienceInTheBusiness(directorExperience);
                                scoringParameterRequest.setExperienceInTheBusiness_p(true);
                            } else {
                                scoringParameterRequest.setExperienceInTheBusiness_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.DEBT_EQUITY_RATIO: {

                            try {
                            /*Double debt = liabilitiesDetailsTY.getSubTotalA() +
                                    liabilitiesDetailsTY.getShortTermBorrowingFromOthers() +
                                    liabilitiesDetailsTY.getTotalTermLiabilities() -
                                    liabilitiesDetailsTY.getPreferencesShares() +
                                    liabilitiesDetailsTY.getOtherNclUnsecuredLoansFromOther() +
                                    liabilitiesDetailsTY.getOtherNclOthers() +
                                    liabilitiesDetailsTY.getMinorityInterest() +
                                    liabilitiesDetailsTY.getDeferredTaxLiability();*/

                                // 27-9-2018 9:19 PM Rahul Khudai Removed iabilitiesDetailsTY.getSubTotalA()
                                // + liabilitiesDetailsTY.getShortTermBorrowingFromOthers()  from Debt calculation

                                Double debt = liabilitiesDetailsTY.getTotalTermLiabilities() -
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

                            } catch (Exception e) {
                                logger.error("error while getting DEBT_EQUITY_RATIO parameter : ",e);
                                scoringParameterRequest.setDebtEquityRatio_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.TOL_TNW: {

                            try {
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

                            } catch (Exception e) {
                                logger.error("error while getting TOL_TNW parameter : ",e);
                                scoringParameterRequest.setTolTnw_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.AVERAGE_CURRENT_RATIO: {
                            try {

                                Double currentRatio = (assetsDetailsTY.getCurrentRatio() + assetsDetailsSY.getCurrentRatio()) / 2;
                                if (CommonUtils.isObjectNullOrEmpty(currentRatio))
                                    currentRatio = 0.0;

                                scoringParameterRequest.setAvgCurrentRatio(currentRatio);
                                scoringParameterRequest.setAvgCurrentRatio_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting AVERAGE_CURRENT_RATIO parameter : ",e);
                                scoringParameterRequest.setAvgCurrentRatio_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.WORKING_CAPITAL_CYCLE: {

                            try {
                                Double debtorsDays = null;
                                if ((operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome()) != 0.0) {
                                    debtorsDays = ((assetsDetailsTY.getReceivableOtherThanDefferred() + assetsDetailsTY.getExportReceivables()) / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
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

                                Double creditorsDays = null;
                                if ((operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome()) != 0) {
                                    creditorsDays = (liabilitiesDetailsTY.getSundryCreditors() / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
                                }
                                if (CommonUtils.isObjectNullOrEmpty(creditorsDays))
                                    creditorsDays = 0.0;


                                scoringParameterRequest.setDebtorsDays(debtorsDays);
                                scoringParameterRequest.setAvgInventory(averageInventory);
                                scoringParameterRequest.setCogs(cogs);
                                scoringParameterRequest.setCreditorsDays(creditorsDays);
                                scoringParameterRequest.setWorkingCapitalCycle_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting WORKING_CAPITAL_CYCLE parameter : ",e);
                                scoringParameterRequest.setWorkingCapitalCycle_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.AVERAGE_ANNUAL_GROWTH_GROSS_CASH: {
                            try {
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

                            } catch (Exception e) {
                                logger.error("error while getting AVERAGE_ANNUAL_GROWTH_GROSS_CASH parameter : ",e);
                                scoringParameterRequest.setAvgAnnualGrowthGrossCash_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.AVERAGE_ANNUAL_GROWTH_NET_SALE: {

                            try {
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

                                Double totalSale_FY;
                                if (domesticSalesFy + exportSalesFy == 0.0) {
                                    totalSale_FY = 1.0;
                                } else {
                                    totalSale_FY = domesticSalesFy + exportSalesFy;
                                }

                                Double totalSale_SY;
                                if (domesticSalesSy + exportSalesSy == 0.0) {
                                    totalSale_SY = 1.0;
                                } else {
                                    totalSale_SY = domesticSalesSy + exportSalesSy;
                                }

                                Double totalSale_TY;
                                if (domesticSalesTy + exportSalesTy == 0.0) {
                                    totalSale_TY = 1.0;
                                } else {
                                    totalSale_TY = domesticSalesTy + exportSalesTy;
                                }

                                scoringParameterRequest.setTotalSaleFy(totalSale_FY);
                                scoringParameterRequest.setTotalSaleSy(totalSale_SY);
                                scoringParameterRequest.setTotalSaleTy(totalSale_TY);
                                scoringParameterRequest.setAvgAnnualGrowthNetSale_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting AVERAGE_ANNUAL_GROWTH_NET_SALE parameter : ",e);
                                scoringParameterRequest.setAvgAnnualGrowthNetSale_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.AVERAGE_EBIDTA: {

                            try {
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

                            } catch (Exception e) {
                                logger.error("error while getting AVERAGE_EBIDTA parameter : ",e);
                                scoringParameterRequest.setAvgEBIDTA_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS: {

                            try {

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

                            } catch (Exception e) {
                                logger.error("error while getting AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS parameter : ",e);
                                scoringParameterRequest.setAvgAnnualGrossCashAccuruals_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.AVERAGE_INTEREST_COV_RATIO: {
                            try {
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
                            } catch (Exception e) {
                                logger.error("error while getting AVERAGE_INTEREST_COV_RATIO parameter : ",e);
                                scoringParameterRequest.setAvgInterestCovRatio_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.NO_OF_CUSTOMER: {
                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(gstCalculation) && !CommonUtils.isObjectNullOrEmpty(gstCalculation.getNoOfCustomer())) {
                                    scoringParameterRequest.setNoOfCustomenr(gstCalculation.getNoOfCustomer());
                                    scoringParameterRequest.setNoOfCustomer_p(true);
                                } else {
                                    scoringParameterRequest.setNoOfCustomer_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting NO_OF_CUSTOMER parameter : ",e);
                                scoringParameterRequest.setNoOfCustomer_p(false);
                                /*map.put("NO_OF_CUSTOMER",null);*/
                            }
                            break;
                        }
                        case ScoreParameter.CONCENTRATION_CUSTOMER: {
                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(gstCalculation) && !CommonUtils.isObjectNullOrEmpty(gstCalculation.getConcentration())) {
                                    scoringParameterRequest.setConcentrationCustomer(gstCalculation.getConcentration());
                                    scoringParameterRequest.setConcentrationCustomer_p(true);
                                } else {
                                    scoringParameterRequest.setConcentrationCustomer_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting CONCENTRATION_CUSTOMER parameter : ",e);
                                scoringParameterRequest.setConcentrationCustomer_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.CREDIT_SUMMATION: {

                            Double totalCredit = null;
                            Double projctedSales = null;

                            // start get total credit from Analyser
                            ReportRequest reportRequest = new ReportRequest();
                            reportRequest.setApplicationId(applicationId);
                            try {
                                AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReport(reportRequest);
                                Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),
                                        Data.class);
                                if (!CommonUtils.isObjectNullOrEmpty(analyzerResponse.getData())) {
                                    {
                                        if (!CommonUtils.isObjectNullOrEmpty(data.getTotalCredit())) {
                                            totalCredit = data.getTotalCredit();
                                        } else {
                                            totalCredit = 0.0;
                                        }

                                    }

                                }
                            } catch (Exception e) {
                                totalCredit = 0.0;
                                logger.error("error while calling analyzer client : ",e);
                            }

                            // get get total credit from Analyser

                            // start get projected sales from GST client

                            projctedSales = gstCalculation.getProjectedSales();

                            // end get projected sales from GST client


                            scoringParameterRequest.setTotalCredit(totalCredit);
                            scoringParameterRequest.setProjectedSale(projctedSales);
                            scoringParameterRequest.setCreditSummation_p(true);

                            break;
                        }
                        case ScoreParameter.AGE: {
                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDob())) {
                                    scoringParameterRequest.setAge(Math.ceil(CommonUtils.getAgeFromBirthDate(mainDirectorBackgroundDetail.getDob()).doubleValue()));
                                    scoringParameterRequest.setAge_p(true);
                                } else {
                                    scoringParameterRequest.setAge_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting AGE parameter",e);
                                scoringParameterRequest.setAge_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.NO_OF_CHILDREN: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getNoOfChildren())) {
                                    scoringParameterRequest.setNoOfChildren(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getNoOfChildren().doubleValue());
                                    scoringParameterRequest.setNoOfChildren_p(true);
                                } else {
                                    scoringParameterRequest.setNoOfChildren_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting NO_OF_CHILDREN parameter",e);
                                scoringParameterRequest.setNoOfChildren_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.OWNING_HOUSE: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getOwningHouse())) {
                                    scoringParameterRequest.setOwningHouse(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getOwningHouse().longValue());
                                    scoringParameterRequest.setOwningHouse_p(true);
                                } else {
                                    scoringParameterRequest.setOwningHouse_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting OWNING_HOUSE parameter : ",e);
                                scoringParameterRequest.setOwningHouse_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.ACADEMIC_QUALIFICATION: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getEducationalStatus())) {
                                    scoringParameterRequest.setAcadamicQualification(mainDirectorBackgroundDetail.getEducationalStatus().longValue());
                                    scoringParameterRequest.setAcadamicQualification_p(true);
                                } else {
                                    scoringParameterRequest.setAcadamicQualification_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting ACADEMIC_QUALIFICATION parameter : ",e);
                                scoringParameterRequest.setAcadamicQualification_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.EXPERIENCE_IN_THE_LINE_OF_TRADE: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getTotalExperience())) {
                                    scoringParameterRequest.setExperienceInLineOfBusiness(mainDirectorBackgroundDetail.getTotalExperience());
                                    scoringParameterRequest.setExpLineOfTrade_p(true);
                                } else {
                                    scoringParameterRequest.setExpLineOfTrade_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting EXPERIENCE_IN_THE_LINE_OF_TRADE parameter : ",e);
                                scoringParameterRequest.setExpLineOfTrade_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.SPOUSE_DETAILS: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getSpouseDetail())) {
                                    scoringParameterRequest.setSpouceDetails(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getSpouseDetail().longValue());
                                    scoringParameterRequest.setSpouseDetails_p(true);
                                } else {
                                    scoringParameterRequest.setSpouseDetails_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting SPOUSE_DETAILS parameter : ",e);
                                scoringParameterRequest.setSpouseDetails_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.ASSESSED_FOR_INCOME_TAX: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getAssessedForIt())) {
                                    scoringParameterRequest.setAssessedFOrIT(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getAssessedForIt().longValue());
                                    scoringParameterRequest.setAssessedForIncomeTax_p(true);
                                } else {
                                    scoringParameterRequest.setAssessedForIncomeTax_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting ASSESSED_FOR_INCOME_TAX parameter : ",e);
                                scoringParameterRequest.setAssessedForIncomeTax_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.HAVE_LIFE_INSURANCE_POLICY: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getHaveLiPolicy())) {
                                    scoringParameterRequest.setHaveLIPolicy(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getHaveLiPolicy().longValue());
                                    scoringParameterRequest.setHaveLifeIncPolicy_p(true);
                                } else {
                                    scoringParameterRequest.setHaveLifeIncPolicy_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting HAVE_LIFE_INSURANCE_POLICY parameter : ",e);
                                scoringParameterRequest.setHaveLifeIncPolicy_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.YEARS_IN_BUSINESS: {
                            try {

                            	Double yearsInBusiness = null;
                            	Integer yearsInBetween = corporateApplicantDetail.getBusinessSinceYear();
                            	Integer monthsDiff = null;
                            	if(yearsInBetween == null) {
                            		java.util.Calendar todayDate = java.util.Calendar.getInstance();
                                    todayDate.setTime(new Date());

                                    yearsInBetween = todayDate.get(java.util.Calendar.YEAR) - corporateApplicantDetail.getEstablishmentYear();

                                    monthsDiff = todayDate.get(java.util.Calendar.MONTH) - corporateApplicantDetail.getEstablishmentMonth();

                                    yearsInBusiness = (((double)yearsInBetween * 12 + (double)monthsDiff) / 12);
                            	}else {
                            		monthsDiff = corporateApplicantDetail.getBusinessSinceMonth();
                            		if(monthsDiff > 6)
                            			yearsInBusiness = (double)yearsInBetween + 1;
                            		else
                            			yearsInBusiness = (double)yearsInBetween;
                            	}

                                scoringParameterRequest.setYearsInBusiness(yearsInBusiness);
                                scoringParameterRequest.setYearsInBusiness_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting YEARS_IN_BUSINESS parameter : ",e);
                                scoringParameterRequest.setYearsInBusiness_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.REPAYMENT_PERIOD: {

                            try {

                                // get repayment period from one form // remaining
                                scoringParameterRequest.setRepaymentPeriod(5.0);
                                scoringParameterRequest.setRepaymentPeriod_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting REPAYMENT_PERIOD parameter : ",e);
                                scoringParameterRequest.setRepaymentPeriod_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.CONTINUOUS_NET_PROFIT: {

                            try {

                                Double netProfitOrLossFY = operatingStatementDetailsFY.getProfitBeforeTaxOrLoss();
                                Double netProfitOrLossSY = operatingStatementDetailsSY.getProfitBeforeTaxOrLoss();
                                Double netProfitOrLossTY = operatingStatementDetailsTY.getProfitBeforeTaxOrLoss();

                                scoringParameterRequest.setContinuousNetProfitOrLossFY(netProfitOrLossFY);
                                scoringParameterRequest.setContinuousNetProfitOrLossSY(netProfitOrLossSY);
                                scoringParameterRequest.setContinuousNetProfitOrLossTY(netProfitOrLossTY);

                                scoringParameterRequest.setContinousNetProfit_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting CONTINUOUS_NET_PROFIT parameter : ",e);
                                scoringParameterRequest.setContinousNetProfit_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.QUALITY_OF_RECEIVABLES: {

                            try {

                                Double totalSaleTY = operatingStatementDetailsTY.getDomesticSales() + operatingStatementDetailsTY.getExportSales();
                                Double grossSaleTy = operatingStatementDetailsTY.getTotalGrossSales();

                                scoringParameterRequest.setTotalSaleTy(totalSaleTY);
                                scoringParameterRequest.setGrossSaleTy(grossSaleTy);

                                scoringParameterRequest.setQualityOfReceivable_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting QUALITY_OF_RECEIVABLES parameter : ",e);
                                scoringParameterRequest.setQualityOfReceivable_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.QUALITY_OF_FINISHED_GOODS_INVENTORY: {

                            try {

                                Double totalCostSaleTy = operatingStatementDetailsTY.getTotalCostSales();
                                Double finishedGoodTy = assetsDetailsTY.getFinishedGoods();

                                scoringParameterRequest.setTotalCostSaleTy(totalCostSaleTy);
                                scoringParameterRequest.setFinishedGoodTy(finishedGoodTy);

                                scoringParameterRequest.setQualityOfFinishedGood_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting QUALITY_OF_FINISHED_GOODS_INVENTORY parameter : ",e);
                                scoringParameterRequest.setQualityOfFinishedGood_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.KNOW_HOW: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getKnowHow())) {
                                    scoringParameterRequest.setKnowHow(primaryCorporateDetail.getKnowHow().longValue());
                                    scoringParameterRequest.setKnowHow_p(true);
                                } else {
                                    scoringParameterRequest.setKnowHow_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting KNOW_HOW parameter : ",e);
                                scoringParameterRequest.setKnowHow_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.LINE_OF_ACTIVITY: {
                            scoringParameterRequest.setLineOfActivity(1l);
                            scoringParameterRequest.setLineOfActivity_p(true);

                            break;
                        }
                        case ScoreParameter.COMPETITION: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCompetition())) {
                                    scoringParameterRequest.setCompetition(primaryCorporateDetail.getCompetition().longValue());
                                    scoringParameterRequest.setCompetition_p(true);
                                } else {
                                    scoringParameterRequest.setCompetition_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting COMPETITION parameter : ",e);
                                scoringParameterRequest.setCompetition_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.FACTORY_PREMISES: {
                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getFactoryPremise())) {
                                    scoringParameterRequest.setFactoryPremises(primaryCorporateDetail.getFactoryPremise().longValue());
                                    scoringParameterRequest.setFactoryPremises_p(true);
                                } else {
                                    scoringParameterRequest.setFactoryPremises_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting FACTORY_PREMISES parameter : ",e);
                                scoringParameterRequest.setFactoryPremises_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.SALES_SHOW_A_RISING_TREND: {

                            try {

                                // getting Gst Current Year Sale from GST Client

                                if (!CommonUtils.isObjectNullOrEmpty(gstResponseScoring) && !CommonUtils.isObjectNullOrEmpty(gstResponseScoring.getData())) {
                                    scoringParameterRequest.setGstSaleCurrentYear((Double) gstResponseScoring.getData());
                                } else {
                                    scoringParameterRequest.setGstSaleCurrentYear(0.0);
                                    logger.error("Error while getting Gst data for Scoring from GST client");
                                }

                                scoringParameterRequest.setNetSaleFy(operatingStatementDetailsFY.getNetSales());
                                scoringParameterRequest.setNetSaleSy(operatingStatementDetailsSY.getNetSales());
                                scoringParameterRequest.setNetSaleTy(operatingStatementDetailsTY.getNetSales());

                                scoringParameterRequest.setSalesShowArisingTrend_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting SALES_SHOW_A_RISING_TREND parameter : ",e);
                                scoringParameterRequest.setSalesShowArisingTrend_p(false);
                            }
                            break;
                        }
                        default : break;
                    }
                    //fundSeekerInputRequestList.add(fundSeekerInputRequest);
                }

                logger.info(MSG_SCORE_PARAMETER + scoringParameterRequest.toString());

                logger.info("----------------------------END-----------------------------------------------");
            }
            Gson g = new Gson();
            ScoringRequestDetail scoringRequestDetail = new ScoringRequestDetail();

            try {
                scoringRequestDetail.setApplicationId(applicationId);
                scoringRequestDetail.setRequest(g.toJson(scoringParameterRequest));
                scoringRequestDetail.setCreatedDate(new Date());
                scoringRequestDetail.setIsActive(true);
                scoringRequestDetailRepository.save(scoringRequestDetail);

                logger.info(SAVING_SCORING_REQUEST_DATA_FOR + applicationId);
            } catch (Exception e) {
                logger.error(CommonUtils.EXCEPTION,e);
            }
        }

        scoringRequest.setScoringParameterRequest(scoringParameterRequest);

        try {
            scoringResponseMain = scoringClient.calculateScore(scoringRequest);
        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING,e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }

        if (scoringResponseMain.getStatus() == HttpStatus.OK.value()) {
            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        } else {
            logger.error(ERROR_WHILE_CALLING_SCORING);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<LoansResponse> calculateExistingBusinessScoringList(List<ScoringRequestLoans> scoringRequestLoansList) {

        ScoringResponse scoringResponseMain = null;

        List<ScoringRequest> scoringRequestList=new ArrayList<ScoringRequest>();

        ScoringParameterRequest scoringParameterRequest = null;

        for(ScoringRequestLoans scoringRequestLoans:scoringRequestLoansList)
        {
            Long scoreModelId = scoringRequestLoans.getScoringModelId();
            Long applicationId = scoringRequestLoans.getApplicationId();
            Long fpProductId = scoringRequestLoans.getFpProductId();

            ///////// Get Financial Type Id from ITR////////

            //Integer financialTypeId = 3;

          /*  List<ScoringRequestDetail> scoringRequestDetailList = scoringRequestDetailRepository.getScoringRequestDetailByApplicationIdAndIsActive(applicationId);


            ScoringRequestDetail scoringRequestDetailSaved = new ScoringRequestDetail();

            if (scoringRequestDetailList.size() > 0) {
                logger.info("Getting Old Scoring request Data for  =====> " + applicationId);
                scoringRequestDetailSaved = scoringRequestDetailList.get(0);
                Gson gson = new Gson();
                scoringParameterRequest = gson.fromJson(scoringRequestDetailSaved.getRequest(), ScoringParameterRequest.class);
            }*/


            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.EXISTING_BUSINESS);



            if (CommonUtils.isObjectNullOrEmpty(scoringParameterRequest)) {


               /* ITRConnectionResponse itrConnectionResponse = null;
                try {
                    itrConnectionResponse = itrClient.getIsUploadAndYearDetails(applicationId);

                    if (!CommonUtils.isObjectNullOrEmpty(itrConnectionResponse) && !CommonUtils.isObjectNullOrEmpty(itrConnectionResponse.getData())) {
                        Map<String, Object> map = (Map<String, Object>) itrConnectionResponse.getData();
                        ITRBasicDetailsResponse res = MultipleJSONObjectHelper.getObjectFromMap(map, ITRBasicDetailsResponse.class);
                        if (!CommonUtils.isObjectNullOrEmpty(res) && !CommonUtils.isObjectNullOrEmpty(res.getItrFinancialType())) {
                            financialTypeId = Integer.valueOf(res.getItrFinancialType());
                        }
                    }
                } catch (IOException e) {
                    logger.error("error while getting Financial Type Id from itr response : ",e);
                }*/

                if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
                    scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
                } else {
                    scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
                }


                logger.info("Financial Type Id ::::::::::::::::================>" + scoringRequest.getFinancialTypeId());

                scoringParameterRequest=new ScoringParameterRequest();

                logger.info("Scoring Data Fetched First Time  =====> " + applicationId);

                logger.info("----------------------------START EXISTING LOAN ------------------------------");

                logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

                // start Get GST Parameter

                String gstNumber = corporateApplicantDetailRepository.getGstInByApplicationId(applicationId);
                Double loanAmount = primaryCorporateDetailRepository.getLoanAmountByApplication(applicationId);

                CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);


                GstResponse gstResponse = null;
                GstResponse gstResponseScoring = null;
                GstCalculation gstCalculation = new GstCalculation();

                try {
                    GSTR1Request gstr1Request = new GSTR1Request();
                    gstr1Request.setGstin(gstNumber);
                    gstResponse = gstClient.getCalculations(gstr1Request);

                    if (!CommonUtils.isObjectNullOrEmpty(gstResponse) && !CommonUtils.isObjectNullOrEmpty(gstResponse.getData())) {
                        gstCalculation = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) gstResponse.getData(),
                                GstCalculation.class);
                    }

                } catch (Exception e) {
                    logger.error("error while getting GST parameter : ",e);
                }


                // get GST Data for Sales Show A Rising Trend

                try {
                    gstResponseScoring = gstClient.getCalculationForScoring(gstNumber);
                } catch (Exception e) {
                    logger.error("error while getting GST parameter for GST Sales Show A Rising Trend : ",e);
                }

                // end Get GST Parameter

                int currentYear = getFinYear(applicationId);
                if (CommonUtils.isObjectNullOrEmpty(currentYear)) {
                    logger.error("error while getting current year from itr");
                    LoansResponse loansResponse = new LoansResponse("error while getting current year from itr.", HttpStatus.INTERNAL_SERVER_ERROR.value());
                    return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
                }

                // CMA
                OperatingStatementDetails operatingStatementDetailsFY = new OperatingStatementDetails();
                OperatingStatementDetails operatingStatementDetailsSY = new OperatingStatementDetails();
                OperatingStatementDetails operatingStatementDetailsTY = new OperatingStatementDetails();


                LiabilitiesDetails liabilitiesDetailsFY;
                LiabilitiesDetails liabilitiesDetailsSY;
                LiabilitiesDetails liabilitiesDetailsTY = new LiabilitiesDetails();

                AssetsDetails assetsDetailsFY;
                AssetsDetails assetsDetailsSY = new AssetsDetails();
                AssetsDetails assetsDetailsTY = new AssetsDetails();

                if (ScoreParameter.FinancialTypeForITR.THREE_YEAR_ITR == scoringRequest.getFinancialTypeId()) {
                    operatingStatementDetailsTY = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear - 1 + "");
                    operatingStatementDetailsSY = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear - 2 + "");
                    operatingStatementDetailsFY = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear - 3 + "");

                    liabilitiesDetailsTY = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear - 1 + "");
                 /*   liabilitiesDetailsSY = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear - 2 + "");
                    liabilitiesDetailsFY = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear - 3 + ""); */

                    assetsDetailsTY = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear - 1 + "");
                    assetsDetailsSY = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear - 2 + "");
//                    assetsDetailsFY = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear - 3 + "");
                } else if (ScoreParameter.FinancialTypeForITR.ONE_YEAR_ITR == scoringRequest.getFinancialTypeId() || ScoreParameter.FinancialTypeForITR.PRESUMPTIVE == scoringRequest.getFinancialTypeId()) {
                    operatingStatementDetailsTY = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear - 1 + "");
                    liabilitiesDetailsTY = liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId, currentYear - 1 + "");
                    assetsDetailsTY = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear - 1 + "");
                }

                ///////////////

                // Get Director Background detail

                DirectorBackgroundDetail mainDirectorBackgroundDetail = directorBackgroundDetailsRepository.getMainDirectorByApplicationId(applicationId);

                // get Primary Corporate Detail

                PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOneByApplicationIdId(applicationId);

                // GET SCORE CORPORATE LOAN PARAMETERS


                if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                    // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                    ScoringResponse scoringResponse = null;
                    try {
                        scoringResponse = scoringClient.listFieldByBusinessTypeId(scoringRequest);
                    } catch (Exception e) {
                        logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
                    }

                    List<Map<String, Object>> dataList = (List<Map<String, Object>>) scoringResponse.getDataList();

                    //List<FundSeekerInputRequest> fundSeekerInputRequestList = new ArrayList<>(dataList.size());

                    logger.info("dataList=====================================>>>>>>>>>>>>>>>>>>>>>>" + dataList.size());

                    for (int i=0;i<dataList.size();i++){

                        ModelParameterResponse modelParameterResponse = null;
                        try {
                            modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                                    ModelParameterResponse.class);
                        } catch (IOException e) {
                            logger.error(CommonUtils.EXCEPTION,e);
                        }

                /*FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                fundSeekerInputRequest.setName(modelParameterResponse.getName());*/

                        switch (modelParameterResponse.getName()) {

                            case ScoreParameter.COMBINED_NETWORTH: {
                                try {
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

                                } catch (Exception e) {
                                    logger.error("error while getting COMBINED_NETWORTH parameter : ",e);
                                    scoringParameterRequest.setCombinedNetworth_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.CUSTOMER_ASSOCIATE_CONCERN: {
                                Double customer_ass_concern_year = null;
                                try {

                                    CibilResponse cibilResponse = cibilClient.getDPDYears(applicationId);
                                    if (!CommonUtils.isObjectNullOrEmpty(cibilResponse) && !CommonUtils.isObjectNullOrEmpty(cibilResponse.getData())) {
                                        customer_ass_concern_year = (Double) cibilResponse.getData();

                                        scoringParameterRequest.setCustomerAssociateConcern(customer_ass_concern_year);
                                        scoringParameterRequest.setCustomerAsscociateConcern_p(true);
                                    } else {
                                        scoringParameterRequest.setCustomerAsscociateConcern_p(false);
                                    }

                                } catch (Exception e) {
                                    logger.error("error while getting CUSTOMER_ASSOCIATE_CONCERN parameter from CIBIL client : ",e);
                                    scoringParameterRequest.setCustomerAsscociateConcern_p(false);
                                }
                                break;

                            }
                            case ScoreParameter.CIBIL_TRANSUNION_SCORE: {
                                Double cibil_score_avg_promotor = null;
                                try {

                                    CibilRequest cibilRequest = new CibilRequest();
                                    cibilRequest.setApplicationId(applicationId);

                                    CibilResponse cibilResponse = cibilClient.getCibilScore(cibilRequest);
                                    if (!CommonUtils.isObjectNullOrEmpty(cibilResponse.getData())) {
                                        cibil_score_avg_promotor = (Double) cibilResponse.getData();
                                        scoringParameterRequest.setCibilTransuniunScore(cibil_score_avg_promotor);
                                        scoringParameterRequest.setCibilTransunionScore_p(true);
                                    } else {
                                        scoringParameterRequest.setCibilTransunionScore_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting CIBIL_TRANSUNION_SCORE parameter from CIBIL client : ",e);
                                    scoringParameterRequest.setCibilTransunionScore_p(false);
                                }

                                break;
                            }

                            case ScoreParameter.EXPERIENCE_IN_THE_BUSINESS: {
                                Double directorExperience = directorBackgroundDetailsRepository.getMaxOfDirectorsExperience(applicationId);

                                if (!CommonUtils.isObjectNullOrEmpty(directorExperience)) {
                                    scoringParameterRequest.setExperienceInTheBusiness(directorExperience);
                                    scoringParameterRequest.setExperienceInTheBusiness_p(true);
                                } else {
                                    scoringParameterRequest.setExperienceInTheBusiness_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.DEBT_EQUITY_RATIO: {

                                try {
                            /*Double debt = liabilitiesDetailsTY.getSubTotalA() +
                                    liabilitiesDetailsTY.getShortTermBorrowingFromOthers() +
                                    liabilitiesDetailsTY.getTotalTermLiabilities() -
                                    liabilitiesDetailsTY.getPreferencesShares() +
                                    liabilitiesDetailsTY.getOtherNclUnsecuredLoansFromOther() +
                                    liabilitiesDetailsTY.getOtherNclOthers() +
                                    liabilitiesDetailsTY.getMinorityInterest() +
                                    liabilitiesDetailsTY.getDeferredTaxLiability();*/

                                    // 27-9-2018 9:19 PM Rahul Khudai Removed iabilitiesDetailsTY.getSubTotalA()
                                    // + liabilitiesDetailsTY.getShortTermBorrowingFromOthers()  from Debt calculation

                                    Double debt = liabilitiesDetailsTY.getTotalTermLiabilities() -
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

                                } catch (Exception e) {
                                    logger.error("error while getting DEBT_EQUITY_RATIO parameter : ",e);
                                    scoringParameterRequest.setDebtEquityRatio_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.TOL_TNW: {

                                try {
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

                                } catch (Exception e) {
                                    logger.error("error while getting TOL_TNW parameter : ",e);
                                    scoringParameterRequest.setTolTnw_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.AVERAGE_CURRENT_RATIO: {
                                try {

                                    Double currentRatio = (assetsDetailsTY.getCurrentRatio() + assetsDetailsSY.getCurrentRatio()) / 2;
                                    if (CommonUtils.isObjectNullOrEmpty(currentRatio))
                                        currentRatio = 0.0;

                                    scoringParameterRequest.setAvgCurrentRatio(currentRatio);
                                    scoringParameterRequest.setAvgCurrentRatio_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting AVERAGE_CURRENT_RATIO parameter : ",e);
                                    scoringParameterRequest.setAvgCurrentRatio_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.WORKING_CAPITAL_CYCLE: {

                                try {
                                    Double debtorsDays = null;
                                    if ((operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome()) != 0.0) {
                                        debtorsDays = ((assetsDetailsTY.getReceivableOtherThanDefferred() + assetsDetailsTY.getExportReceivables()) / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
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

                                    Double creditorsDays = null;
                                    if ((operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome()) != 0) {
                                        creditorsDays = (liabilitiesDetailsTY.getSundryCreditors() / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
                                    }
                                    if (CommonUtils.isObjectNullOrEmpty(creditorsDays))
                                        creditorsDays = 0.0;


                                    scoringParameterRequest.setDebtorsDays(debtorsDays);
                                    scoringParameterRequest.setAvgInventory(averageInventory);
                                    scoringParameterRequest.setCogs(cogs);
                                    scoringParameterRequest.setCreditorsDays(creditorsDays);
                                    scoringParameterRequest.setWorkingCapitalCycle_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting WORKING_CAPITAL_CYCLE parameter : ",e);
                                    scoringParameterRequest.setWorkingCapitalCycle_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.AVERAGE_ANNUAL_GROWTH_GROSS_CASH: {
                                try {
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

                                } catch (Exception e) {
                                    logger.error("error while getting AVERAGE_ANNUAL_GROWTH_GROSS_CASH parameter : ",e);
                                    scoringParameterRequest.setAvgAnnualGrowthGrossCash_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.AVERAGE_ANNUAL_GROWTH_NET_SALE: {

                                try {
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

                                    Double totalSale_FY;
                                    if (domesticSalesFy + exportSalesFy == 0.0) {
                                        totalSale_FY = 1.0;
                                    } else {
                                        totalSale_FY = domesticSalesFy + exportSalesFy;
                                    }

                                    Double totalSale_SY;
                                    if (domesticSalesSy + exportSalesSy == 0.0) {
                                        totalSale_SY = 1.0;
                                    } else {
                                        totalSale_SY = domesticSalesSy + exportSalesSy;
                                    }

                                    Double totalSale_TY;
                                    if (domesticSalesTy + exportSalesTy == 0.0) {
                                        totalSale_TY = 1.0;
                                    } else {
                                        totalSale_TY = domesticSalesTy + exportSalesTy;
                                    }

                                    scoringParameterRequest.setTotalSaleFy(totalSale_FY);
                                    scoringParameterRequest.setTotalSaleSy(totalSale_SY);
                                    scoringParameterRequest.setTotalSaleTy(totalSale_TY);
                                    scoringParameterRequest.setAvgAnnualGrowthNetSale_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting AVERAGE_ANNUAL_GROWTH_NET_SALE parameter : ",e);
                                    scoringParameterRequest.setAvgAnnualGrowthNetSale_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.AVERAGE_EBIDTA: {

                                try {
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

                                } catch (Exception e) {
                                    logger.error("error while getting AVERAGE_EBIDTA parameter : ",e);
                                    scoringParameterRequest.setAvgEBIDTA_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS: {

                                try {

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

                                } catch (Exception e) {
                                    logger.error("error while getting AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS parameter : ",e);
                                    scoringParameterRequest.setAvgAnnualGrossCashAccuruals_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.AVERAGE_INTEREST_COV_RATIO: {
                                try {
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
                                } catch (Exception e) {
                                    logger.error("error while getting AVERAGE_INTEREST_COV_RATIO parameter : ",e);
                                    scoringParameterRequest.setAvgInterestCovRatio_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.NO_OF_CUSTOMER: {
                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(gstCalculation) && !CommonUtils.isObjectNullOrEmpty(gstCalculation.getNoOfCustomer())) {
                                        scoringParameterRequest.setNoOfCustomenr(gstCalculation.getNoOfCustomer());
                                        scoringParameterRequest.setNoOfCustomer_p(true);
                                    } else {
                                        scoringParameterRequest.setNoOfCustomer_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting NO_OF_CUSTOMER parameter : ",e);
                                    scoringParameterRequest.setNoOfCustomer_p(false);
                                    /*map.put("NO_OF_CUSTOMER",null);*/
                                }
                                break;
                            }
                            case ScoreParameter.CONCENTRATION_CUSTOMER: {
                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(gstCalculation) && !CommonUtils.isObjectNullOrEmpty(gstCalculation.getConcentration())) {
                                        scoringParameterRequest.setConcentrationCustomer(gstCalculation.getConcentration());
                                        scoringParameterRequest.setConcentrationCustomer_p(true);
                                    } else {
                                        scoringParameterRequest.setConcentrationCustomer_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting CONCENTRATION_CUSTOMER parameter : ",e);
                                    scoringParameterRequest.setConcentrationCustomer_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.CREDIT_SUMMATION: {

                                Double totalCredit = null;
                                Double projctedSales = null;

                                // start get total credit from Analyser
                                ReportRequest reportRequest = new ReportRequest();
                                reportRequest.setApplicationId(applicationId);
                                try {
                                    AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReport(reportRequest);
                                    Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),
                                            Data.class);
                                    if (!CommonUtils.isObjectNullOrEmpty(analyzerResponse.getData())) {
                                        {
                                            if (!CommonUtils.isObjectNullOrEmpty(data.getTotalCredit())) {
                                                totalCredit = data.getTotalCredit();
                                            } else {
                                                totalCredit = 0.0;
                                            }

                                        }

                                    }
                                } catch (Exception e) {
                                    totalCredit = 0.0;
                                    logger.error("error while calling analyzer client : ",e);
                                }

                                // get get total credit from Analyser

                                // start get projected sales from GST client

                                projctedSales = gstCalculation.getProjectedSales();

                                // end get projected sales from GST client


                                scoringParameterRequest.setTotalCredit(totalCredit);
                                scoringParameterRequest.setProjectedSale(projctedSales);
                                scoringParameterRequest.setCreditSummation_p(true);

                                break;
                            }
                            case ScoreParameter.AGE: {
                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDob())) {
                                        scoringParameterRequest.setAge(Math.ceil(CommonUtils.getAgeFromBirthDate(mainDirectorBackgroundDetail.getDob()).doubleValue()));
                                        scoringParameterRequest.setAge_p(true);
                                    } else {
                                        scoringParameterRequest.setAge_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting AGE parameter : ",e);
                                    scoringParameterRequest.setAge_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.NO_OF_CHILDREN: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getNoOfChildren())) {
                                        scoringParameterRequest.setNoOfChildren(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getNoOfChildren().doubleValue());
                                        scoringParameterRequest.setNoOfChildren_p(true);
                                    } else {
                                        scoringParameterRequest.setNoOfChildren_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting NO_OF_CHILDREN parameter : ",e);
                                    scoringParameterRequest.setNoOfChildren_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.OWNING_HOUSE: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getOwningHouse())) {
                                        scoringParameterRequest.setOwningHouse(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getOwningHouse().longValue());
                                        scoringParameterRequest.setOwningHouse_p(true);
                                    } else {
                                        scoringParameterRequest.setOwningHouse_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting OWNING_HOUSE parameter : ",e);
                                    scoringParameterRequest.setOwningHouse_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.ACADEMIC_QUALIFICATION: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getEducationalStatus())) {
                                        scoringParameterRequest.setAcadamicQualification(mainDirectorBackgroundDetail.getEducationalStatus().longValue());
                                        scoringParameterRequest.setAcadamicQualification_p(true);
                                    } else {
                                        scoringParameterRequest.setAcadamicQualification_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting ACADEMIC_QUALIFICATION parameter : ",e);
                                    scoringParameterRequest.setAcadamicQualification_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.EXPERIENCE_IN_THE_LINE_OF_TRADE: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getTotalExperience())) {
                                        scoringParameterRequest.setExperienceInLineOfBusiness(mainDirectorBackgroundDetail.getTotalExperience());
                                        scoringParameterRequest.setExpLineOfTrade_p(true);
                                    } else {
                                        scoringParameterRequest.setExpLineOfTrade_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting EXPERIENCE_IN_THE_LINE_OF_TRADE parameter : ",e);
                                    scoringParameterRequest.setExpLineOfTrade_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.SPOUSE_DETAILS: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getSpouseDetail())) {
                                        scoringParameterRequest.setSpouceDetails(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getSpouseDetail().longValue());
                                        scoringParameterRequest.setSpouseDetails_p(true);
                                    } else {
                                        scoringParameterRequest.setSpouseDetails_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting SPOUSE_DETAILS parameter : ",e);
                                    scoringParameterRequest.setSpouseDetails_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.ASSESSED_FOR_INCOME_TAX: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getAssessedForIt())) {
                                        scoringParameterRequest.setAssessedFOrIT(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getAssessedForIt().longValue());
                                        scoringParameterRequest.setAssessedForIncomeTax_p(true);
                                    } else {
                                        scoringParameterRequest.setAssessedForIncomeTax_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting ASSESSED_FOR_INCOME_TAX parameter : ",e);
                                    scoringParameterRequest.setAssessedForIncomeTax_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.HAVE_LIFE_INSURANCE_POLICY: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getHaveLiPolicy())) {
                                        scoringParameterRequest.setHaveLIPolicy(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getHaveLiPolicy().longValue());
                                        scoringParameterRequest.setHaveLifeIncPolicy_p(true);
                                    } else {
                                        scoringParameterRequest.setHaveLifeIncPolicy_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting HAVE_LIFE_INSURANCE_POLICY parameter : ",e);
                                    scoringParameterRequest.setHaveLifeIncPolicy_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.YEARS_IN_BUSINESS: {
                                try {
                                	Double yearsInBusiness = null;
                                	Integer yearsInBetween = corporateApplicantDetail.getBusinessSinceYear();
                                	Integer monthsDiff = null;
                                	if(yearsInBetween == null) {
                                		java.util.Calendar todayDate = java.util.Calendar.getInstance();
                                        todayDate.setTime(new Date());

                                        yearsInBetween = todayDate.get(java.util.Calendar.YEAR) - corporateApplicantDetail.getEstablishmentYear();

                                        monthsDiff = todayDate.get(java.util.Calendar.MONTH) - corporateApplicantDetail.getEstablishmentMonth();

                                        yearsInBusiness = (((double)yearsInBetween * 12 + (double)monthsDiff) / 12);
                                	}else {
                                		monthsDiff = corporateApplicantDetail.getBusinessSinceMonth();
                                		if(monthsDiff > 6)
                                			yearsInBusiness = (double)yearsInBetween + 1;
                                		else
                                			yearsInBusiness = (double)yearsInBetween;
                                	}

                                    scoringParameterRequest.setYearsInBusiness(yearsInBusiness);
                                    scoringParameterRequest.setYearsInBusiness_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting YEARS_IN_BUSINESS parameter : ",e);
                                    scoringParameterRequest.setYearsInBusiness_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.REPAYMENT_PERIOD: {

                                try {

                                    // get repayment period from one form // remaining
                                    scoringParameterRequest.setRepaymentPeriod(5.0);
                                    scoringParameterRequest.setRepaymentPeriod_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting REPAYMENT_PERIOD parameter : ",e);
                                    scoringParameterRequest.setRepaymentPeriod_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.CONTINUOUS_NET_PROFIT: {

                                try {

                                    Double netProfitOrLossFY = operatingStatementDetailsFY.getProfitBeforeTaxOrLoss();
                                    Double netProfitOrLossSY = operatingStatementDetailsSY.getProfitBeforeTaxOrLoss();
                                    Double netProfitOrLossTY = operatingStatementDetailsTY.getProfitBeforeTaxOrLoss();

                                    scoringParameterRequest.setContinuousNetProfitOrLossFY(netProfitOrLossFY);
                                    scoringParameterRequest.setContinuousNetProfitOrLossSY(netProfitOrLossSY);
                                    scoringParameterRequest.setContinuousNetProfitOrLossTY(netProfitOrLossTY);

                                    scoringParameterRequest.setContinousNetProfit_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting CONTINUOUS_NET_PROFIT parameter : ",e);
                                    scoringParameterRequest.setContinousNetProfit_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.QUALITY_OF_RECEIVABLES: {

                                try {

                                    Double totalSaleTY = operatingStatementDetailsTY.getDomesticSales() + operatingStatementDetailsTY.getExportSales();
                                    Double grossSaleTy = operatingStatementDetailsTY.getTotalGrossSales();

                                    scoringParameterRequest.setTotalSaleTy(totalSaleTY);
                                    scoringParameterRequest.setGrossSaleTy(grossSaleTy);

                                    scoringParameterRequest.setQualityOfReceivable_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting QUALITY_OF_RECEIVABLES parameter : ",e);
                                    scoringParameterRequest.setQualityOfReceivable_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.QUALITY_OF_FINISHED_GOODS_INVENTORY: {

                                try {

                                    Double totalCostSaleTy = operatingStatementDetailsTY.getTotalCostSales();
                                    Double finishedGoodTy = assetsDetailsTY.getFinishedGoods();

                                    scoringParameterRequest.setTotalCostSaleTy(totalCostSaleTy);
                                    scoringParameterRequest.setFinishedGoodTy(finishedGoodTy);

                                    scoringParameterRequest.setQualityOfFinishedGood_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting QUALITY_OF_FINISHED_GOODS_INVENTORY parameter : ",e);
                                    scoringParameterRequest.setQualityOfFinishedGood_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.KNOW_HOW: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getKnowHow())) {
                                        scoringParameterRequest.setKnowHow(primaryCorporateDetail.getKnowHow().longValue());
                                        scoringParameterRequest.setKnowHow_p(true);
                                    } else {
                                        scoringParameterRequest.setKnowHow_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting KNOW_HOW parameter : ",e);
                                    scoringParameterRequest.setKnowHow_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.LINE_OF_ACTIVITY: {
                                scoringParameterRequest.setLineOfActivity(1l);
                                scoringParameterRequest.setLineOfActivity_p(true);

                                break;
                            }
                            case ScoreParameter.COMPETITION: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCompetition())) {
                                        scoringParameterRequest.setCompetition(primaryCorporateDetail.getCompetition().longValue());
                                        scoringParameterRequest.setCompetition_p(true);
                                    } else {
                                        scoringParameterRequest.setCompetition_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting COMPETITION parameter : ",e);
                                    scoringParameterRequest.setCompetition_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.FACTORY_PREMISES: {
                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getFactoryPremise())) {
                                        scoringParameterRequest.setFactoryPremises(primaryCorporateDetail.getFactoryPremise().longValue());
                                        scoringParameterRequest.setFactoryPremises_p(true);
                                    } else {
                                        scoringParameterRequest.setFactoryPremises_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting FACTORY_PREMISES parameter : ",e);
                                    scoringParameterRequest.setFactoryPremises_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.SALES_SHOW_A_RISING_TREND: {

                                try {

                                    // getting Gst Current Year Sale from GST Client

                                    if (!CommonUtils.isObjectNullOrEmpty(gstResponseScoring) && !CommonUtils.isObjectNullOrEmpty(gstResponseScoring.getData())) {
                                        scoringParameterRequest.setGstSaleCurrentYear((Double) gstResponseScoring.getData());
                                    } else {
                                        scoringParameterRequest.setGstSaleCurrentYear(0.0);
                                        logger.error("Error while getting Gst data for Scoring from GST client");
                                    }

                                    scoringParameterRequest.setNetSaleFy(operatingStatementDetailsFY.getNetSales());
                                    scoringParameterRequest.setNetSaleSy(operatingStatementDetailsSY.getNetSales());
                                    scoringParameterRequest.setNetSaleTy(operatingStatementDetailsTY.getNetSales());

                                    scoringParameterRequest.setSalesShowArisingTrend_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting SALES_SHOW_A_RISING_TREND parameter : ",e);
                                    scoringParameterRequest.setSalesShowArisingTrend_p(false);
                                }
                                break;
                            }

                            default: break;
                        }

                        //fundSeekerInputRequestList.add(fundSeekerInputRequest);
                    }

                    logger.info(MSG_SCORE_PARAMETER + scoringParameterRequest.toString());

                    logger.info("----------------------------END-----------------------------------------------");
                }
                Gson g = new Gson();
                ScoringRequestDetail scoringRequestDetail = new ScoringRequestDetail();

                try {
                    scoringRequestDetail.setApplicationId(applicationId);
                    scoringRequestDetail.setRequest(g.toJson(scoringParameterRequest));
                    scoringRequestDetail.setCreatedDate(new Date());
                    scoringRequestDetail.setIsActive(true);
                    scoringRequestDetailRepository.save(scoringRequestDetail);

                    logger.info(SAVING_SCORING_REQUEST_DATA_FOR + applicationId);
                } catch (Exception e) {
                    logger.error(CommonUtils.EXCEPTION,e);
                }
            }

            scoringRequest.setScoringParameterRequest(scoringParameterRequest);
            scoringRequestList.add(scoringRequest);
        }

        try {
            scoringResponseMain = scoringClient.calculateScoreList(scoringRequestList);
        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING,e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }

        if (scoringResponseMain.getStatus() == HttpStatus.OK.value()) {
            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        } else {
            logger.error(ERROR_WHILE_CALLING_SCORING);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<LoansResponse> calculateNTBScoring(ScoringRequestLoans scoringRequestLoans, PrimaryCorporateDetail primaryCorporateDetail) {


        CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findByApplicationIdIdAndIsActive(primaryCorporateDetail.getApplicationId().getId(), true);

        // Fetch Data for Calculate Director Score

        List<DirectorBackgroundDetail> directorBackgroundDetailsList = directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(scoringRequestLoans.getApplicationId());

        logger.info("directorBackgroundDetailsList.size()==========>>" + directorBackgroundDetailsList.size());

        if (directorBackgroundDetailsList.size() > 0) {
            for (DirectorBackgroundDetail directorBackgroundDetail : directorBackgroundDetailsList) {
                 calculateDirectorScore(scoringRequestLoans, directorBackgroundDetail, primaryCorporateDetail);
            }
        }

        // Fetch Data for Calculate Company Score

        com.capitaworld.service.scoring.model.scoringmodel.ScoreParameterNTBRequest scoreParameterNTBRequest = new com.capitaworld.service.scoring.model.scoringmodel.ScoreParameterNTBRequest();

        Long scoreModelId = scoringRequestLoans.getScoringModelId();
        Long applicationId = scoringRequestLoans.getApplicationId();
        Long fpProductId = scoringRequestLoans.getFpProductId();

        logger.info("----------------------------START NTB LOAN------------------------------");

        logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

        ScoringResponse scoringResponseMain = null;

        // GET SCORE NTB LOAN PARAMETERS


        if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.NTB);

            // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
            ScoringResponse scoringResponse = null;
            try {
                scoringResponse = scoringClient.listField(scoringRequest);
            } catch (Exception e) {
                logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
            }

            List<Map<String, Object>> dataList = (List<Map<String, Object>>) scoringResponse.getDataList();

            List<FundSeekerInputRequest> fundSeekerInputRequestList = new ArrayList<>(dataList.size());

            for (int i = 0; i < dataList.size(); i++) {

                ModelParameterResponse modelParameterResponse = null;
                try {
                    modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                            ModelParameterResponse.class);
                } catch (IOException e) {
                    logger.error(CommonUtils.EXCEPTION,e);
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
                    case ScoreParameter.NTB.CONSTITUTION_OF_BORROWER: {
                        try {
                            Long proposedConstitutionOfUnit = Long.parseLong(primaryCorporateDetail.getProposedConstitutionOfUnit().toString());

                            if (!CommonUtils.isObjectNullOrEmpty(proposedConstitutionOfUnit)) {
                                scoreParameterNTBRequest.setConstitutionOfBorrowe(proposedConstitutionOfUnit);
                                scoreParameterNTBRequest.setIsConstitutionOfBorrower(true);
                            } else {
                                scoreParameterNTBRequest.setIsConstitutionOfBorrower(false);
                            }
                        } catch (Exception e) {
                            logger.error("error while getting CONSTITUTION_OF_BORROWER parameter : ",e);
                            scoreParameterNTBRequest.setIsConstitutionOfBorrower(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.ASSET_COVERAGE_RATIO: {
                        try {

                            Double collatralValue = 0.0;
                            CGTMSEDataResponse cgtmseDataResponse = thirdPartyClient.getCalulation(applicationId);
                            if (!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse)
                                    && !CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getColleteralCoverage())) {
                                collatralValue = cgtmseDataResponse.getColleteralCoverage();
                            }

                            scoreParameterNTBRequest.setColatralValue(collatralValue);
                            scoreParameterNTBRequest.setIsAssetCoverageRatio(true);
                        } catch (Exception e) {
                            logger.error("error while getting ASSET_COVERAGE_RATIO parameter : ",e);
                            scoreParameterNTBRequest.setIsAssetCoverageRatio(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.UNIT_FACTORY_PREMISES: {
                        try {
                            Long unitFactoryPremises = primaryCorporateDetail.getProposedDetailsOfUnit().longValue();
                            scoreParameterNTBRequest.setUnitFactoryPremisesDetails(unitFactoryPremises);
                            scoreParameterNTBRequest.setIsUnitFactoryPremises(true);
                        } catch (Exception e) {
                            logger.error("error while getting UNIT_FACTORY_PREMISES parameter : ",e);
                            scoreParameterNTBRequest.setIsUnitFactoryPremises(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.BALANCE_GESTATION_PERIOD: {
                        try {

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

                            if (months.doubleValue() >= 0)
                                scoreParameterNTBRequest.setIsBalanceGestationPeriod(true);
                            else
                                scoreParameterNTBRequest.setIsBalanceGestationPeriod(false);

                        } catch (Exception e) {
                            logger.error("error while getting BALANCE_GESTATION_PERIOD parameter : ",e);
                            scoreParameterNTBRequest.setIsBalanceGestationPeriod(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.ENVIRONMENT_CATEGORY: {

                        // start get business type id from irr

                        Integer businessTypeId = null; // get from irr-cw industry mapping

                        IrrRequest irrIndustryRequest = new IrrRequest();

                        Long irrId = null;
                        try {
                            irrId = loanApplicationService.getIrrByApplicationId(applicationId);

                        } catch (Exception e) {
                            logger.error("error while getting irr id from one form : ",e);
                        }

                        // start getting irr industry and business type
                        try {

                            irrIndustryRequest.setIrrIndustryId(irrId);
                            irrIndustryRequest = ratingClient.getIrrIndustry(irrIndustryRequest);
                            IndustryResponse industryResponse = irrIndustryRequest.getIndustryResponse();
                            if (CommonUtils.isObjectNullOrEmpty(industryResponse)) {
                                logger.info("Error while getting irr id from rating");
                            }

                            businessTypeId = industryResponse.getBusinessTypeId();
                        } catch (Exception e) {
                            logger.error("error while getting irr industry detail from rating : ",e);
                        }

                        // end get business type id from irr

                        try {

                            logger.info("businessTypeId:===============:::::::::::::==========" + businessTypeId);
                            if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) && com.capitaworld.service.rating.utils.CommonUtils.BusinessType.MANUFACTURING == businessTypeId) {
                                Long envCategoryId = corporateApplicantDetail.getEnvironmentalImpactId();

                                logger.info("envCategoryId:===============:::::::::::::==========" + envCategoryId);
                                if (!CommonUtils.isObjectNullOrEmpty(envCategoryId)) {
                                    try {

                                        OneFormResponse oneFormResponse = oneFormClient.getEnvironmentCategoryIdById(envCategoryId);

                                        Integer environmentCategoryId = (Integer) oneFormResponse.getData();
                                        scoreParameterNTBRequest.setEnvironmentCategory(environmentCategoryId.longValue());
                                        logger.info("environmentCategoryId from one form client:===============:::::::::::::==========" + environmentCategoryId);
                                        scoreParameterNTBRequest.setIsEnvironmentCategory(true);
                                    } catch (Exception e) {
                                        logger.error("Error while calling one form client : ",e);
                                    }
                                } else {
                                    scoreParameterNTBRequest.setIsEnvironmentCategory(false);
                                }

                            } else if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) && com.capitaworld.service.rating.utils.CommonUtils.BusinessType.SERVICE == businessTypeId) {
                                scoreParameterNTBRequest.setEnvironmentCategory(EnvironmentCategory.SERVICE.getId().longValue());
                                scoreParameterNTBRequest.setIsEnvironmentCategory(true);
                            } else if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) && com.capitaworld.service.rating.utils.CommonUtils.BusinessType.TRADING == businessTypeId) {
                                scoreParameterNTBRequest.setEnvironmentCategory(EnvironmentCategory.TRADING.getId().longValue());
                                scoreParameterNTBRequest.setIsEnvironmentCategory(true);
                            } else {
                                scoreParameterNTBRequest.setIsEnvironmentCategory(false);
                            }
                        } catch (Exception e) {
                            logger.error("error while getting ENVIRONMENT_CATEGORY parameter : ",e);
                            scoreParameterNTBRequest.setIsEnvironmentCategory(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.CNW: {
                        try {
                            Double networth = directorBackgroundDetailsRepository.getSumOfDirectorsNetworth(applicationId);
                            if (CommonUtils.isObjectNullOrEmpty(networth)) {
                                networth = 0.0;
                            }
                            scoreParameterNTBRequest.setNetworth(networth);

                            Double loanAmount = primaryCorporateDetailRepository.getLoanAmountByApplication(applicationId);

                            if (!CommonUtils.isObjectNullOrEmpty(loanAmount)) {
                                scoreParameterNTBRequest.setLoanAmount(loanAmount);
                                scoreParameterNTBRequest.setIsCNW(true);
                            } else {
                                scoreParameterNTBRequest.setIsCNW(false);
                            }

                        } catch (Exception e) {
                            logger.error("error while getting CNW parameter : ",e);
                            scoreParameterNTBRequest.setIsCNW(false);
                        }
                        break;
                    }
                        default : break;
                }
                fundSeekerInputRequestList.add(fundSeekerInputRequest);
            }

            logger.info(MSG_SCORE_PARAMETER + scoreParameterNTBRequest.toString());

            logger.info("----------------------------END-----------------------------------------");

            scoringRequest.setDataList(fundSeekerInputRequestList);
            scoringRequest.setScoreParameterNTBRequest(scoreParameterNTBRequest);

            try {
                scoringResponseMain = scoringClient.calculateScore(scoringRequest);
            } catch (Exception e) {
                logger.error(ERROR_WHILE_CALLING_SCORING,e);
                LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }

            if (scoringResponseMain.getStatus() == HttpStatus.OK.value()) {
                logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
                LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            } else {
                logger.error(ERROR_WHILE_CALLING_SCORING);
                LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }
        }

        LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
    }

    public Boolean calculateDirectorScore(ScoringRequestLoans scoringRequestLoans, DirectorBackgroundDetail directorBackgroundDetail, PrimaryCorporateDetail primaryCorporateDetail) {


        // Fetch Data for Calculate Director Score

        com.capitaworld.service.scoring.model.scoringmodel.ScoreParameterNTBRequest scoreParameterNTBRequest = new com.capitaworld.service.scoring.model.scoringmodel.ScoreParameterNTBRequest();

        Long scoreModelId = scoringRequestLoans.getScoringModelId();
        Long applicationId = scoringRequestLoans.getApplicationId();
        Long fpProductId = scoringRequestLoans.getFpProductId();

        logger.info("----------------------------START NTB DIRECTOR------------------------------");

        logger.info("DIRECTOR ID :: " + directorBackgroundDetail.getId() + MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId );

        ScoringResponse scoringResponseMain = null;

        // GET SCORE NTB LOAN PARAMETERS


        if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.NTB);
            scoringRequest.setDirectorId(directorBackgroundDetail.getId());

            // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
            ScoringResponse scoringResponse = null;
            try {
                scoringResponse = scoringClient.listField(scoringRequest);
            } catch (Exception e) {
                logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
            }

            List<Map<String, Object>> dataList = (List<Map<String, Object>>) scoringResponse.getDataList();

            List<FundSeekerInputRequest> fundSeekerInputRequestList = new ArrayList<>(dataList.size());

            for (int i = 0; i < dataList.size(); i++) {

                ModelParameterResponse modelParameterResponse = null;
                try {
                    modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                            ModelParameterResponse.class);
                } catch (IOException e) {
                    logger.error(CommonUtils.EXCEPTION,e);
                }

                FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                fundSeekerInputRequest.setName(modelParameterResponse.getName());

                switch (modelParameterResponse.getName()) {

                    case ScoreParameter.NTB.WORKING_EXPERIENCE: {

                        try {
                            Double totalExperience = directorBackgroundDetail.getTotalExperience();
                            if (CommonUtils.isObjectNullOrEmpty(totalExperience)) {
                                totalExperience = 0.0;
                            }
                            scoreParameterNTBRequest.setTotalworkingExperience(totalExperience);
                            scoreParameterNTBRequest.setIsWorkingExperience(true);
                        } catch (Exception e) {
                            logger.error("error while getting WORKING_EXPERIENCE parameter : ",e);
                            scoreParameterNTBRequest.setIsWorkingExperience(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS: {
                        try {
                            Boolean isFamilyMemberInBusiness = directorBackgroundDetail.getFamilyMemberInBusiness();
                            if (CommonUtils.isObjectNullOrEmpty(isFamilyMemberInBusiness) || isFamilyMemberInBusiness == false) {
                                scoreParameterNTBRequest.setFamilyMemberInLineOfBusiness(2l);
                            } else {
                                scoreParameterNTBRequest.setFamilyMemberInLineOfBusiness(1l);
                            }
                            scoreParameterNTBRequest.setIsFamilyMemberInLineOfBusiness(true);
                        } catch (Exception e) {
                            logger.error("error while getting IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS parameter : ",e);
                            scoreParameterNTBRequest.setIsFamilyMemberInLineOfBusiness(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.CIBIL_TRANSUNION_SCORE: {
                        try {
                            CibilRequest cibilRequest = new CibilRequest();
                            cibilRequest.setApplicationId(applicationId);
                            cibilRequest.setPan(directorBackgroundDetail.getPanNo());

                            CibilScoreLogRequest cibilScoreLogRequest = cibilClient.getCibilScoreByPanCard(cibilRequest);
                            if (!CommonUtils.isObjectNullOrEmpty(cibilScoreLogRequest) && !CommonUtils.isObjectNullOrEmpty(cibilScoreLogRequest.getScore())) {
                                Double cibilScore = Double.parseDouble(cibilScoreLogRequest.getScore());
                                scoreParameterNTBRequest.setCibilTransunionScore(cibilScore);
                                scoreParameterNTBRequest.setIsCibilTransunionScore(true);
                            } else {
                                scoreParameterNTBRequest.setIsCibilTransunionScore(false);
                            }
                        } catch (Exception e) {
                            logger.error("error while getting CIBIL_TRANSUNION_SCORE parameter : ",e);
                            scoreParameterNTBRequest.setIsCibilTransunionScore(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.AGE_OF_PROMOTOR: {
                        try {

                            if (!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetail.getDob())) {
                                scoreParameterNTBRequest.setAgeOfPromotor(Math.ceil(CommonUtils.getAgeFromBirthDate(directorBackgroundDetail.getDob()).doubleValue()));
                                scoreParameterNTBRequest.setIsAgeOfPromotor(true);
                            } else {
                                scoreParameterNTBRequest.setIsAgeOfPromotor(false);
                            }
                        } catch (Exception e) {
                            logger.error("error while getting AGE_OF_PROMOTOR parameter : ",e);
                            scoreParameterNTBRequest.setIsAgeOfPromotor(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.EDUCATION_QUALIFICATION: {
                        try {
                            Long qualificationId = directorBackgroundDetail.getQualificationId().longValue();
                            scoreParameterNTBRequest.setEducationQualification(qualificationId);
                            scoreParameterNTBRequest.setIsEducationQualification(true);
                        } catch (Exception e) {
                            logger.error("error while getting EDUCATION_QUALIFICATION parameter : ",e);
                            scoreParameterNTBRequest.setIsEducationQualification(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.EMPLOYMENT_TYPE: {
                        try {
                            Long empType = directorBackgroundDetail.getEmploymentDetail().getEmploymentStatus();

                            if (!CommonUtils.isObjectNullOrEmpty(empType)) {
                                scoreParameterNTBRequest.setEmployeeType(empType);
                                scoreParameterNTBRequest.setIsEmploymentType(true);
                            } else {
                                scoreParameterNTBRequest.setIsEmploymentType(false);
                            }

                        } catch (Exception e) {
                            logger.error("error while getting EMPLOYMENT_TYPE parameter : ",e);
                            scoreParameterNTBRequest.setIsEmploymentType(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.HOUSE_OWNERSHIP: {
                        try {

                            Long residentType = directorBackgroundDetail.getResidenceType().longValue();
                            scoreParameterNTBRequest.setHouseOwnerShip(residentType);
                            scoreParameterNTBRequest.setIsHouseOwnership(true);
                        } catch (Exception e) {
                            logger.error("error while getting HOUSE_OWNERSHIP parameter : ",e);
                            scoreParameterNTBRequest.setIsHouseOwnership(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.MARITIAL_STATUS: {
                        try {

                            Long maritialStatus = directorBackgroundDetail.getMaritalStatus().longValue();
                            scoreParameterNTBRequest.setMaritialStatus(maritialStatus);
                            scoreParameterNTBRequest.setIsMaritialStatus(true);
                        } catch (Exception e) {
                            logger.error("error while getting MARITIAL_STATUS parameter : ",e);
                            scoreParameterNTBRequest.setIsMaritialStatus(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.ITR_SALARY_INCOME: {
                        try {
                            logger.info("Application id ===========>" + applicationId);
                            logger.info("directorBackgroundDetail id ===========>" + directorBackgroundDetail.getId());
                            Double avgSalary = corporateDirectorIncomeDetailsRepository.getTotalSalaryByApplicationIdAndDirectorId(applicationId, directorBackgroundDetail.getId());
                            if (avgSalary != 0) {
                                avgSalary = avgSalary / 3;
                            }

                            Double promotorContribution = primaryCorporateDetail.getPromoterContribution();

                            if (CommonUtils.isObjectNullOrEmpty(promotorContribution)) {
                                promotorContribution = 0.0;
                            }

                            scoreParameterNTBRequest.setItrSalaryIncomeAvg(avgSalary);
                            scoreParameterNTBRequest.setItrPromotorContribution(promotorContribution);
                            scoreParameterNTBRequest.setIsItrSalaryIncome(true);
                        } catch (Exception e) {
                            logger.error("error while getting ITR_SALARY_INCOME parameter : ",e);
                            scoreParameterNTBRequest.setIsItrSalaryIncome(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.FIXED_OBLIGATION_RATIO: {
                        try {

                            Double totalIncome = corporateDirectorIncomeDetailsRepository.getTotalIncomeByApplicationIdAndDirectorId(applicationId, directorBackgroundDetail.getId());
                            Double totalEMI = financialArrangementDetailsRepository.getTotalEmiByApplicationIdAndDirectorId(applicationId, directorBackgroundDetail.getId());

                            if (CommonUtils.isObjectNullOrEmpty(totalIncome)) {
                                totalIncome = 0.0;
                            }

                            if (CommonUtils.isObjectNullOrEmpty(totalEMI)) {
                                totalEMI = 0.0;
                            }

                            scoreParameterNTBRequest.setItrSalaryIncome(totalIncome);
                            scoreParameterNTBRequest.setTotalEmiPaid(totalEMI);
                            scoreParameterNTBRequest.setIsFixedObligationRatio(true);
                        } catch (Exception e) {
                            logger.error("error while getting FIXED_OBLIGATION_RATIO parameter : ",e);
                            scoreParameterNTBRequest.setIsFixedObligationRatio(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.CHEQUE_BOUNCES: {
                        try {
                            Double noOfChequeBounce = null;
                            ReportRequest reportRequest = new ReportRequest();
                            reportRequest.setApplicationId(applicationId);
                            reportRequest.setDirectorId(directorBackgroundDetail.getId());

                            AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportByDirector(reportRequest);

                            Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),
                                    Data.class);
                            if (!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month())) {
                                {
                                    if (!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month().doubleValue())) {
                                        noOfChequeBounce = data.getCheckBounceForLast6Month().doubleValue();
                                    } else {
                                        noOfChequeBounce = 0.0;
                                    }

                                }
                            } else {
                                noOfChequeBounce = 0.0;
                            }

                            scoreParameterNTBRequest.setChequeBouncesPastSixMonths(noOfChequeBounce);
                            scoreParameterNTBRequest.setIsChequeBounces(true);
                        } catch (Exception e) {
                            logger.error("error while getting CHEQUE_BOUNCES parameter : ",e);
                            scoreParameterNTBRequest.setIsChequeBounces(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.DPD: {
                        try {

                            //remaining
                            scoreParameterNTBRequest.setIsDPD(false);
                        } catch (Exception e) {
                            logger.error("error while getting DPD parameter : ",e);
                            scoreParameterNTBRequest.setIsDPD(false);
                        }
                        break;
                    }
                        default : break;
                }
                fundSeekerInputRequestList.add(fundSeekerInputRequest);
            }

            logger.info(MSG_SCORE_PARAMETER + scoreParameterNTBRequest.toString());

            logger.info("----------------------------END---------------------------------------------");

            scoringRequest.setDataList(fundSeekerInputRequestList);
            scoringRequest.setScoreParameterNTBRequest(scoreParameterNTBRequest);

            try {
                scoringResponseMain = scoringClient.calculateScore(scoringRequest);
            } catch (Exception e) {
                logger.error(ERROR_WHILE_CALLING_SCORING,e);
                return false;
            }

            if (scoringResponseMain.getStatus() == HttpStatus.OK.value()) {
                logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
                return true;
            } else {
                logger.error(ERROR_WHILE_CALLING_SCORING);
                return false;
            }
        }

        return null;
    }


    @Override
    public ResponseEntity<LoansResponse> calculateScoringTest(ScoringRequestLoans scoringRequestLoans) {

        ScoringParameterRequest scoringParameterRequest = new ScoringParameterRequest();

        logger.info("SCORE PARAMETER BEFORE::::::::::" + scoringRequestLoans.getScoreParameterRequestLoans().toString());

        BeanUtils.copyProperties(scoringRequestLoans.getScoreParameterRequestLoans(), scoringParameterRequest);

        Long scoreModelId = scoringRequestLoans.getScoringModelId();
        Long applicationId = scoringRequestLoans.getApplicationId();

        logger.info("----------------------------START------------------------------");

        logger.info(MSG_SCORING_MODEL_ID + scoreModelId);

        ScoringResponse scoringResponseMain = null;

        ///////////////

        // GET SCORE CORPORATE LOAN PARAMETERS


        if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setApplicationId(applicationId);

            // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
            ScoringResponse scoringResponse = null;
            try {
                scoringResponse = scoringClient.listField(scoringRequest);
            } catch (Exception e) {
                logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
            }

            List<Map<String, Object>> dataList = new ArrayList<>();
            if (scoringResponse != null && scoringResponse.getDataList() != null ) {
                dataList = (List<Map<String, Object>>) scoringResponse.getDataList();
            }

            List<FundSeekerInputRequest> fundSeekerInputRequestList = new ArrayList<>(dataList.size());

            for (int i = 0; i < dataList.size(); i++) {

                ModelParameterResponse modelParameterResponse = null;
                try {
                    modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                            ModelParameterResponse.class);
                } catch (IOException | NullPointerException e) {
                    logger.error(CommonUtils.EXCEPTION,e);
                }

                FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                if (modelParameterResponse != null) {
                    fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                    fundSeekerInputRequest.setName(modelParameterResponse.getName());
                }
                fundSeekerInputRequestList.add(fundSeekerInputRequest);
            }

            logger.info(MSG_SCORE_PARAMETER + scoringParameterRequest.toString());

            logger.info("----------------------------END--------------------------------");

            scoringRequest.setDataList(fundSeekerInputRequestList);
            scoringRequest.setScoringParameterRequest(scoringParameterRequest);
            scoringRequest.setTestingApiCall(true);

            try {
                scoringResponseMain = scoringClient.calculateScore(scoringRequest);
            } catch (Exception e) {
                logger.error(ERROR_WHILE_CALLING_SCORING,e);
                LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }

            if (scoringResponseMain.getStatus() == HttpStatus.OK.value()) {
                logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
                LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
                loansResponse.setData(scoringResponseMain.getDataObject());
                loansResponse.setListData(scoringResponseMain.getDataList());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            } else {
                logger.error(ERROR_WHILE_CALLING_SCORING);
                LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }
        }

        LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
    }

    @SuppressWarnings("resource")
    @Override
    public Workbook readScoringExcel(MultipartFile multipartFile) throws IllegalStateException, InvalidFormatException, IOException, LoansException {
        logger.info("-----------------------------Enter in readScoringExcel()-----------------------------------> MultiPartfile " + multipartFile);
        InputStream file;
        Workbook workbook = null;
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
            logger.info("calculating scorring() list size-----------------------> " + loansResponseList.size());
            workbook = generateScoringExcel(loansResponseList);
            logger.info("------------------------Exit from readScoringExcel() ---------------name of sheet in workook -----------------------> " + workbook.getSheetName(0));

        } catch (NullPointerException | IOException e) {
            logger.error("----------------Error/Exception while calculating scorring()------------------------------> " + e.getMessage());
            throw new LoansException(e);
        }
        return workbook;
    }

    @Override
    public Workbook generateScoringExcel(List<LoansResponse> loansResponseList) throws LoansException {
        logger.info("----------------Enter in  generateScoringExcel() ------------------------------>");
        return new ScoreExcelFileGenerator().scoreResultExcel(loansResponseList, environment);

    }

    @Override
    public ScoringModelReqRes getScoringModelTempList(ScoringModelReqRes scoringModelReqRes) {
        try {
            UserResponse userResponse = usersClient.getOrgIdFromUserId(scoringModelReqRes.getUserId());

            if (!CommonUtils.isObjectNullOrEmpty(userResponse) && !CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
                scoringModelReqRes.setOrgId(Long.parseLong(userResponse.getData().toString()));
            } else {
                logger.debug(ORG_ID_IS_NULL_OR_EMPTY + "In getScoringModelTempList");
                return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception e) {
            logger.error(ORG_ID_IS_NULL_OR_EMPTY,e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }

        try {

            return scoringClient.getScoringModelTempList(scoringModelReqRes);
        } catch (Exception e) {
            logger.error("error while geting score model list from scoring : ",e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }

    }

    @Override
    public ScoringModelReqRes saveScoringModelTemp(ScoringModelReqRes scoringModelReqRes) {

        try {

            UserResponse userResponse = usersClient.getOrgIdFromUserId(scoringModelReqRes.getUserId());

            if (!CommonUtils.isObjectNullOrEmpty(userResponse) && !CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
                scoringModelReqRes.getScoringModelResponse().setOrgId(Long.parseLong(userResponse.getData().toString()));
            } else {
                logger.error(ORG_ID_IS_NULL_OR_EMPTY + " In saveScoringModelTemp ");
                return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception e) {
            logger.error(ORG_ID_IS_NULL_OR_EMPTY,e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }

        try {

            return scoringClient.saveScoringModelTemp(scoringModelReqRes);
        } catch (Exception e) {
            logger.error("error while saving score model from scoring : ",e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringModelReqRes getScoringModelTempDetail(ScoringModelReqRes scoringModelReqRes) {
        try {
            try {
                return scoringClient.getScoringModelTempDetail(scoringModelReqRes);
            } catch (Exception e) {
                logger.error("error while accessing fp product id for scoring : ",e);
                return new ScoringModelReqRes("Error while accessing fp product id for scoring", HttpStatus.BAD_REQUEST.value());
            }

        } catch (Exception e) {
            logger.error("error while getting score model detail from scoring : ",e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringModelReqRes saveScoringModelTempDetail(ScoringModelReqRes scoringModelReqRes) {
        try {

            return scoringClient.saveScoringModelTempDetail(scoringModelReqRes);
        } catch (Exception e) {
            logger.error("error while saving score model detail from scoring : ",e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public List<GenericCheckerReqRes> sendToChecker(List<GenericCheckerReqRes> genericCheckerReqResList, Long userId) throws ScoringException {
        return scoringClient.sendToChecker(genericCheckerReqResList, userId);
    }

    @Override
    public ScoringModelReqRes getScoringModelMasterList(ScoringModelReqRes scoringModelReqRes) {
        try {
            UserResponse userResponse = usersClient.getOrgIdFromUserId(scoringModelReqRes.getUserId());

            if (!CommonUtils.isObjectNullOrEmpty(userResponse) && !CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
                scoringModelReqRes.setOrgId(Long.parseLong(userResponse.getData().toString()));
            } else {
                logger.error(ORG_ID_IS_NULL_OR_EMPTY + " In getScoringModelMasterList ");
                return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception e) {
            logger.error(ORG_ID_IS_NULL_OR_EMPTY,e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }

        try {

            return scoringClient.getScoringModelMasterList(scoringModelReqRes);
        } catch (Exception e) {
            logger.error("error while geting score model list from scoring : ",e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }

    }

    @Override
    public ScoringModelReqRes getScoringModelMasterDetail(ScoringModelReqRes scoringModelReqRes) {
        try {
            try {
                return scoringClient.getScoringModelMasterDetail(scoringModelReqRes);
            } catch (Exception e) {
                logger.error("error while accessing fp product id for scoring : ",e);
                return new ScoringModelReqRes("Error while accessing fp product id for scoring", HttpStatus.BAD_REQUEST.value());
            }

        } catch (Exception e) {
            logger.error("error while getting score model detail from scoring : ",e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }


    public Integer getFinYear(Long applicationId) {
        Integer year = 0;
        ITRConnectionResponse itrConnectionResponse = null;
        try {
            itrConnectionResponse = itrClient.getIsUploadAndYearDetails(applicationId);
        } catch (Exception e) {
            logger.error("error while calling itr client for getIsUploadAndYearDetails()",e);
        }
        try {
            if (itrConnectionResponse != null && !CommonUtils.isObjectNullOrEmpty(itrConnectionResponse) && !CommonUtils.isObjectNullOrEmpty(itrConnectionResponse.getData())) {
                Map<String, Object> map = (Map<String, Object>) itrConnectionResponse.getData();
                ITRBasicDetailsResponse res = MultipleJSONObjectHelper.getObjectFromMap(map, ITRBasicDetailsResponse.class);
                if (!CommonUtils.isObjectNullOrEmpty(res)) {
                    year = Integer.valueOf(res.getYear());
                }
            }
        } catch (IOException | NullPointerException e) {
            logger.error("error while getting year from itr response : ",e);
        }
        return year + 1;
    }
}
