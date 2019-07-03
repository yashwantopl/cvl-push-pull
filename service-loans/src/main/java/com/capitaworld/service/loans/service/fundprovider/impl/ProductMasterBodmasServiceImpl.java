package com.capitaworld.service.loans.service.fundprovider.impl;

import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.bodmas.domain.BodmasReqRes;
import com.capitaworld.bodmas.domain.CalculationReqRes;
import com.capitaworld.bodmas.domain.FormulaReqRes;
import com.capitaworld.bodmas.exception.BodmasException;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.BodmasClient;
import com.capitaworld.service.loans.domain.fundprovider.*;
import com.capitaworld.service.loans.model.*;
import com.capitaworld.service.loans.model.corporate.AddProductRequest;
import com.capitaworld.service.loans.repository.fundprovider.*;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorTempRepository;
import com.capitaworld.service.loans.service.common.FundProviderSequenceService;
import com.capitaworld.service.loans.service.fundprovider.*;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtility;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.enums.LoanType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

/**
 * @author jaimin.darji
 */
@Service
@Transactional
public class ProductMasterBodmasServiceImpl implements ProductMasterBodmasService {
    private static final Logger logger = LoggerFactory.getLogger(ProductMasterBodmasServiceImpl.class);

    @Autowired
    private ProductMasterRepository productMasterRepository;

    @Autowired
    private ProductMasterTempRepository productMasterTempRepository;

    @Autowired
    private WorkflowClient workflowClient;

    @Autowired
    private FundProviderSequenceService fundProviderSequenceService;

    @Autowired
    private IndustrySectorTempRepository industrySectorTempRepository;

    @Autowired
    private FpGstTypeMappingTempRepository fpGstTypeMappingTempRepository;

    @Autowired
    private FpProductParametersRepository parametersRepository;

    @Autowired
    private FpProductConditionsRepository conditionsRepository;

    @Autowired
    private FpGstTypeMappingRepository fpGstTypeMappingRepository;

    @Autowired
    private BodmasClient bodmasClient;
    @Autowired
    private FpProductMatchValueAuditRepository auditRepository;

    //if use retail Loan Id
    private Integer[] retailProductIds = {CommonUtils.LoanType.HOME_LOAN.getValue(), CommonUtils.LoanType.PERSONAL_LOAN.getValue()};
    private Integer[] corpProductIds = {CommonUtils.LoanType.WORKING_CAPITAL.getValue(), CommonUtils.LoanType.TERM_LOAN.getValue(), CommonUtils.LoanType.UNSECURED_LOAN.getValue(), CommonUtils.LoanType.WCTL_LOAN.getValue()};

    /**
     * save Method For new product
     * @param addProductRequest
     * @param userOrgId
     * @return
     */
    @Override
    public Long saveOrUpdate(AddProductRequest addProductRequest, Long userOrgId) {
        CommonDocumentUtils.startHook(logger, "saveOrUpdate");
        //if client id is null than select userid
        Long userId = (CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId()) ? addProductRequest.getUserId() : addProductRequest.getClientId());
        try {
                //if product id is null than create new product in temp table
                ProductMasterTemp productMasterTemp;
                if (!CommonUtils.isObjectNullOrEmpty(addProductRequest.getProductMappingId())) {
                    productMasterTemp = productMasterTempRepository.findOne(addProductRequest.getProductMappingId());
                    productMasterTemp.setModifiedDate(new Date());
                    productMasterTemp.setModifiedBy(userId);
                } else{
                    productMasterTemp = new ProductMasterTemp();
                    LoanType loanType = LoanType.getById(Integer.parseInt(addProductRequest.getProductId().toString()));
                    WorkflowResponse workflowResponse = workflowClient.createJobForMasters(WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL, userId);

                    if (workflowResponse != null) {
                        productMasterTemp.setJobId(workflowResponse.getData() != null ? Long.valueOf(workflowResponse.getData().toString()) : null);
                    }
                    productMasterTemp.setCreatedBy(userId);
                    productMasterTemp.setCreatedDate(new Date());
                }

                logger.info("addProductRequest.getProductId() === >" + addProductRequest.getProductId());
                productMasterTemp.setProductId(addProductRequest.getProductId());
                productMasterTemp.setIsMatched(false);
                productMasterTemp.setName(addProductRequest.getName());
                productMasterTemp.setFpName(addProductRequest.getFpName());
                productMasterTemp.setUserId(userId);
                productMasterTemp.setModifiedBy(userId);
                productMasterTemp.setIsParameterFilled(false);
                productMasterTemp.setBusinessTypeId(addProductRequest.getBusinessTypeId());// set business type id
                productMasterTemp.setWcRenewalStatus(addProductRequest.getWcRenewalStatus());
                productMasterTemp.setFinId(addProductRequest.getFinId());
                productMasterTemp.setIsCopied(false);
                productMasterTemp.setIsActive(true);
                productMasterTemp.setUserOrgId(userOrgId);
                productMasterTemp.setStatusId(1);
                productMasterTemp.setProductCode(fundProviderSequenceService.getFundProviderSequenceNumber(addProductRequest.getProductId()));
                productMasterTemp.setIsGst(addProductRequest.getIsGst());
                productMasterTemp.setIsItr(addProductRequest.getIsItr());
                productMasterTemp.setIsBankStatement(addProductRequest.getIsBankStatement());
                productMasterTemp.setIsMca(addProductRequest.getIsMca());
                productMasterTemp.setIsBureuPersonal(addProductRequest.getIsBureuPersonal());
                productMasterTemp.setIsBureuCommercial(addProductRequest.getIsBureuCommercial());
                productMasterTemp.setIsManualFill(addProductRequest.getIsManualFill());
                productMasterTemp.setImportFromId(addProductRequest.getLoanId());
                ProductMasterTemp productMaster2 = productMasterTempRepository.save(productMasterTemp);
                //For copy product by product id which you selected
                if (!CommonUtils.isObjectNullOrEmpty(addProductRequest.getLoanId())) {
                    List<ProductConditionResponse> conditionsLists = getConditionsByProductId(addProductRequest.getLoanId());
                    for (ProductConditionResponse conditionResponse : conditionsLists) {
                        ProductParameterRequest productParameterRequest = new ProductParameterRequest();
                        BeanUtils.copyProperties(conditionResponse, productParameterRequest);
                        productParameterRequest.setProductId(productMaster2.getId());
                        saveCondition(productParameterRequest);
                    }
                }
                return productMaster2.getId();
        } catch (Exception e) {
            logger.error("error while saveOrUpdate : ", e);
            return null;
        }
    }

    /**
     * save condition for products
     * @param productParameterRequest
     * @return
     */
    @Override
    public boolean saveCondition(ProductParameterRequest productParameterRequest) {
//        List<ProductConditionResponse> allByFpProductId = conditionsRepository.findAllByFpProductId(productParameterRequest.getProductId());
//        if(allByFpProductId.size() > 0){
//            return true;
//        }
        FpProductConditions fpProductConditions;
        if (CommonUtils.isObjectNullOrEmpty(productParameterRequest.getId())) {
            fpProductConditions = new FpProductConditions();
        } else {
            logger.info("product condition id ===> {}",productParameterRequest.getId());
            fpProductConditions = conditionsRepository.findOne(productParameterRequest.getId());
        }

        fpProductConditions.setConditionName(productParameterRequest.getConditionName());
        fpProductConditions.setIsAllMandatory(productParameterRequest.getIsAllMandatory());
        fpProductConditions.setIsMandatory(productParameterRequest.getIsMandatory());
        fpProductConditions.setAllLogicalCondition(productParameterRequest.getLogicalCondition());
        fpProductConditions.setFpProductId(productParameterRequest.getProductId());
        fpProductConditions.setActive(true);
        fpProductConditions.setCreatedBy(productParameterRequest.getUserId());
        fpProductConditions.setCreatedDate(new Date());
        conditionsRepository.save(fpProductConditions);

        //for add group or parameter
        if (!CommonUtils.isObjectListNull(productParameterRequest.getParameters())) {
            //recursive method for save group and parameters
            return saveParameterWithGroup(productParameterRequest.getParameters(), fpProductConditions.getId(), productParameterRequest.getUserId(), 0, null, productParameterRequest.getProductId());
        }
        return false;
    }

    /**
     * save parameters with group and recursive add product parameters
     * @param parameters
     * @param conditionId
     * @param userId
     * @param level
     * @param parentId
     * @param productId
     * @return
     */
    private boolean saveParameterWithGroup(List<ProductParameterRequest> parameters, Long conditionId, Long userId, int level, Long parentId, Long productId) {
        for (ProductParameterRequest request : parameters) {
            if (CommonUtils.isObjectNullOrEmpty(request.getIsGroup()) || !request.getIsGroup()) {
                FpProductParameters fpProParameter;
                if (CommonUtils.isObjectNullOrEmpty(request.getId())) {
                    fpProParameter = new FpProductParameters();
                } else {
                    logger.info("product Parameter id ===> {}",request.getId());
                    fpProParameter = parametersRepository.findOne(request.getId());
                }

                fpProParameter.setBodmasFormulaId(request.getBodmasFormulaId());
                fpProParameter.setCompareValue(request.getCompareValue());
                fpProParameter.setMinValue(request.getMinValue());
                fpProParameter.setMaxValue(request.getMaxValue());
                fpProParameter.setParameterOperator(request.getParameterOperator());
                fpProParameter.setParentId(CommonUtils.isObjectNullOrEmpty(parentId) ? null : parentId);
                fpProParameter.setConditionId(conditionId);
                fpProParameter.setIsGroup(request.getIsGroup());
                fpProParameter.setIsActive(true);
                fpProParameter.setCreatedDate(new Date());
                fpProParameter.setCreatedBy(userId);
                fpProParameter.setProductId(productId);
                parametersRepository.save(fpProParameter);
            } else {
                //for level of sub parameter condtions
                level = level + 1;
                FpProductParameters fpProductParameters;
                if (CommonUtils.isObjectNullOrEmpty(request.getId())) {
                    fpProductParameters = new FpProductParameters();
                } else {
                    logger.info("product Parameter id in else condition ===> {}",request.getId());
                    fpProductParameters = parametersRepository.findOne(request.getId());
                }
                fpProductParameters.setLogicalCondition(request.getLogicalCondition());
                fpProductParameters.setLevel(level);
                fpProductParameters.setIsActive(true);
                fpProductParameters.setConditionId(conditionId);
                fpProductParameters.setIsGroup(request.getIsGroup() ? request.getIsGroup() : false);
                fpProductParameters.setCreatedDate(new Date());
                fpProductParameters.setCreatedBy(userId);
                fpProductParameters.setProductId(productId);
                fpProductParameters.setParentId(CommonUtils.isObjectNullOrEmpty(parentId) ? null : parentId);
                parametersRepository.save(fpProductParameters);
                if (!CommonUtils.isListNullOrEmpty(request.getParameters())) {
                    //call recursive when sub group and sub parameters are available
                    saveParameterWithGroup(request.getParameters(), conditionId, userId, level, fpProductParameters.getId(), productId);
                }
            }
        }
        return true;
    }

    /**
     * get List of products
     * @param userId
     * @param userOrgId
     * @return
     */
    @Override
    public List<ProductMasterRequest> getList(Long userId, Long userOrgId) {
        CommonDocumentUtils.startHook(logger, "getList");
        List<ProductMaster> results;
        if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
            results = productMasterRepository.getUserProductListByOrgId(userOrgId);
        } else {
            results = productMasterRepository.getUserProductList(userId);
        }
        List<ProductMasterRequest> requests = new ArrayList<>(results.size());
        for (ProductMaster master : results) {
            ProductMasterRequest request = new ProductMasterRequest();
            BeanUtils.copyProperties(master, request);
            request.setIsMatched(productMasterRepository.getMatchedAndActiveProduct(userId).size() > 0 ? true : false);
            requests.add(request);
        }
        CommonDocumentUtils.endHook(logger, "getList");
        return requests;
    }

    @Override
    public List<ProductMasterRequest> getListByUserType(Long userId, Integer userType, Integer stage, Integer status, Long userOrgId) {
        CommonDocumentUtils.startHook(logger, "getListByUserType");
        List<ProductMasterRequest> productMasterRequests = new ArrayList<>();
        Integer[] productIds = null;
        if (userType == 1) {
            productIds = retailProductIds;//for retail products
        } else {
            productIds = corpProductIds;//for Corporate Products
        }
        Boolean isActive = (status == 1 ? true : false);
        //for pending stage
        if (!CommonUtils.isObjectNullOrEmpty(stage) && stage == 1) {
            List<ProductMasterTemp> results = null;
            if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
                //if data get from User org id
                if(status == 0){
                    results = productMasterTempRepository.getProductListByUserOrgId(userOrgId, Arrays.asList(productIds));
                }else{
                    results = productMasterTempRepository.getProductListByUserOrgId(userOrgId, Arrays.asList(productIds),isActive);
                }

            } else {
                //if data get from User id
                if(status == 0){
                    results = productMasterTempRepository.getProductListByUserId(userId, Arrays.asList(productIds));
                }else{
                    results = productMasterTempRepository.getProductListByUserId(userId, Arrays.asList(productIds),isActive);
                }

            }
            if(!CommonUtils.isListNullOrEmpty(results)) {
                for (ProductMasterTemp productMasterTemp : results) {
                    ProductMasterRequest productMasterTempRequest = new ProductMasterRequest();
                    BeanUtils.copyProperties(productMasterTemp, productMasterTempRequest);
                    productMasterTempRequest.setFinId(productMasterTemp.getFinId());
                    List<Integer> gstTypes = fpGstTypeMappingTempRepository.getIdsByFpProductId(productMasterTemp.getId());
                    productMasterTempRequest.setGstType(gstTypes);
                    productMasterRequests.add(productMasterTempRequest);
                }
            }
        } else {
            //for approve stage
            List<ProductMaster> results = null;
            if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
                //if data get from User org id
                if(status == 0){
                    results = productMasterRepository.getProductListByUserOrgId(userOrgId, Arrays.asList(productIds));
                }else{
                    results = productMasterRepository.getProductListByUserOrgId(userOrgId, Arrays.asList(productIds),isActive);
                }
            } else {
                //if data get from User id
                if(status == 0){
                    results = productMasterRepository.getProductListByUserId(userId, Arrays.asList(productIds));
                }else{
                    results = productMasterRepository.getProductListByUserId(userId, Arrays.asList(productIds),isActive);
                }
            }
            if(!CommonUtils.isListNullOrEmpty(results)) {
                for (ProductMaster productMaster : results) {
                    ProductMasterRequest productMasterRequest = new ProductMasterRequest();
                    BeanUtils.copyProperties(productMaster, productMasterRequest);
                    productMasterRequest.setFinId(productMaster.getFinId());
                    List<Integer> gstTypes = fpGstTypeMappingRepository.getIdsByFpProductId(productMaster.getId());
                    productMasterRequest.setGstType(gstTypes);
                    productMasterRequests.add(productMasterRequest);
                }
            }
        }
        CommonDocumentUtils.endHook(logger, "getListByUserType");
        return productMasterRequests;
    }

    /**
     * get All condition by product Id group wise condition and parameter add
     * @param productId
     * @return
     */
    @Override
    public List<ProductConditionResponse> getConditionsByProductId(Long productId) {
        List<ProductConditionResponse> conditionResponses = conditionsRepository.findAllByFpProductId(productId);
        for (ProductConditionResponse condition : conditionResponses) {
            List<ProductParameterResponse> parameterResponses = parametersRepository.findAllByConditionId(condition.getId());
            condition.setParameters(getParameters(parameterResponses)); //set parameters and get respose group wise
        }
        return conditionResponses;
    }

    /**
     * get all parameters
     * @param parameterResponses
     * @return
     */
    private List<ProductParameterResponse> getParameters(List<ProductParameterResponse> parameterResponses) {
        for (ProductParameterResponse response : parameterResponses) {
            if (response.getBodmasFormulaId() != null) {
                response.setFormulaName(getFormulaNameById(response.getBodmasFormulaId()));
            }
            if (response.getIsGroup()) {
                List<ProductParameterResponse> childResponses = parametersRepository.findAllByConditionIdWithParent(response.getId(), response.getConditionId());
                response.setParameters(childResponses);
                getParameters(childResponses);
            }
        }
        return parameterResponses;
    }

    /**
     * get Formula name by formula id which generated in Bodmas equation
     * @param formulaId
     * @return
     */
    private String getFormulaNameById(Long formulaId) {
        if (formulaId != null) {
            List<String> formulaNameById = parametersRepository.getFormulaNameById(formulaId);
            return CommonUtils.isObjectListNull(formulaNameById) ? null : formulaNameById.get(0);
        }
        return null;
    }

    /**
     * get Product by product id and role
     * @param id
     * @param stage
     * @return
     */
    public ProductMasterRequest getProductDetails(Long id,Integer stage){
        ProductMasterRequest masterRequest = new ProductMasterRequest();
        if(stage == 1) {
            logger.info("Get stage 1 for pending products");
            ProductMasterTemp master = productMasterTempRepository.findOne(id);
            BeanUtils.copyProperties(master,masterRequest);
        } else {
            logger.info("Get stage 2 for Approved products");
            ProductMaster master = productMasterRepository.findOne(id);
            BeanUtils.copyProperties(master,masterRequest);
        }
        return masterRequest;
    }


    public boolean activeInActiveProduct(Long userId, Long id, Integer stage, Boolean status){
        if(stage == 1) {
            productMasterTempRepository.changeStatus(userId , id, status);
        } else {
            productMasterRepository.changeStatus(userId , id, status);
        }
        return true;
    }

    /**
     * Matching Parameters
     * @param applicationId
     * @param productId
     * @return
     */
    public BodmasReqRes getMatchingParameters(Long applicationId,Long productId) {
        try {
            List<Long> productIds = new ArrayList<>();
            productIds.add(productId);//Note : add List of products from Loan type currently static add
            List<FpProductParameters> allByProductId = parametersRepository.findAllByProductId(productIds);
            Object[] connectData = parametersRepository.findByApplicationId(applicationId);
            if(!CommonUtils.isObjectListNull(connectData) && !CommonUtils.isObjectListNull(allByProductId)){
                CalculationReqRes reqRes = new CalculationReqRes();
                Map<Long, FormulaReqRes> formulaMap = new HashMap<>();
                Object[] obj = (Object[]) connectData[0];
                reqRes.setApplicationId(Long.valueOf(obj[0].toString()));
                reqRes.setGstin(String.valueOf(obj[1].toString()));
                reqRes.setPanNo(String.valueOf(obj[2].toString()));
                for (FpProductParameters  parameterResponse :allByProductId) {
                    formulaMap.put(parameterResponse.getBodmasFormulaId(), null);
                }
                reqRes.setFormulaMap(formulaMap);
                BodmasReqRes bodmasReqRes = bodmasClient.calculateFormulaList(reqRes);
                if(!CommonUtils.isObjectNullOrEmpty(bodmasReqRes) && !CommonUtils.isObjectNullOrEmpty(bodmasReqRes.getData())){
                    reqRes = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) bodmasReqRes.getData(),CalculationReqRes.class);
                    // inactived last Matches value code By application Id
                    auditRepository.inactiveAllByProductId(applicationId);
                    for (FpProductParameters  parameterResponse :allByProductId) {
                        for (Map.Entry<Long, FormulaReqRes> entry : reqRes.getFormulaMap().entrySet()) {
                            if(parameterResponse.getBodmasFormulaId() == entry.getKey()){
                                FormulaReqRes formulaReqRes = (FormulaReqRes) entry.getValue();
                                //save data in Table matched and Ans
                                saveMatchesValues(parameterResponse,formulaReqRes.getFormulaAnswer(), applicationId);
                            }
                        }
                    }
                }
                List<FpProductMatchValueAudit> allByProductId1 = auditRepository.findAllByProductId(applicationId);
                List<ProductMatchValueAuditResponse> responses = new ArrayList<>();
                for (FpProductMatchValueAudit matchValueAudit: allByProductId1) {
                    ProductMatchValueAuditResponse auditResponse = new ProductMatchValueAuditResponse();
                    auditResponse.setCondition(CommonUtility.logicalCondition(matchValueAudit.getConditionId()));
                    BeanUtils.copyProperties(matchValueAudit,auditResponse);
                    responses.add(auditResponse);
                }
                bodmasReqRes.setDataList(responses);
                return bodmasReqRes;
            }
        } catch (BodmasException e) {
            logger.info("Error while Bodmas Calculations : ", e);
        } catch (IOException e) {
            logger.info("IOException : ", e);
        }
        return null;
    }

    /**
     * Save Match parameters
     * @param parameterResponse
     * @param formulaAns
     * @param applicationId
     * @return
     */
    private boolean saveMatchesValues(FpProductParameters  parameterResponse, Double formulaAns, Long applicationId){

        FpProductMatchValueAudit formulaValueAudit = new FpProductMatchValueAudit();
        formulaValueAudit.setProductId(parameterResponse.getProductId());
        formulaValueAudit.setParameterId(parameterResponse.getId());
        formulaValueAudit.setBodmasFormulaId(parameterResponse.getBodmasFormulaId());
        formulaValueAudit.setFormulaAnswer(formulaAns);
        formulaValueAudit.setCompareVal(CommonUtils.isObjectNullOrEmpty(parameterResponse.getCompareValue()) ? 0 : parameterResponse.getCompareValue());
        formulaValueAudit.setMinVal(CommonUtils.isObjectNullOrEmpty(parameterResponse.getMinValue()) ? 0 : parameterResponse.getMinValue());
        formulaValueAudit.setMaxVal(CommonUtils.isObjectNullOrEmpty(parameterResponse.getMaxValue()) ? 0 : parameterResponse.getMaxValue());
        formulaValueAudit.setConditionId(parameterResponse.getParameterOperator());
        formulaValueAudit.setIsMatch(matchParameterValue(parameterResponse.getParameterOperator(),formulaAns,parameterResponse.getMinValue(),
                parameterResponse.getMaxValue(),parameterResponse.getCompareValue()));
        formulaValueAudit.setCreatedBy(parameterResponse.getCreatedBy());
        formulaValueAudit.setCreatedDate(new Date());
        formulaValueAudit.setIsActive(true);
        formulaValueAudit.setApplicationId(applicationId);
        auditRepository.save(formulaValueAudit);
        return true;
    }

    /**
     * calculate by
     * @param condition
     * @param formulaAns
     * @param minValue
     * @param maxValue
     * @param compareValue
     * @return
     */
    private boolean matchParameterValue(int condition,Double formulaAns,Double minValue, Double maxValue, Double compareValue){
        if(condition == 1){//For Greter than
            return formulaAns > compareValue;
        } else if(condition == 2) {//For Less than
            return formulaAns < compareValue;
        } else if(condition == 3) {//For Range
            return formulaAns > minValue && formulaAns < maxValue;
        } else if(condition == 4) {//For Equals
            return formulaAns == compareValue;
        } else {
            return false;
        }
    }

}
