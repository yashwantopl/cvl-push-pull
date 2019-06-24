package com.capitaworld.service.loans.service.fundprovider.impl;

import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.loans.domain.fundprovider.*;
import com.capitaworld.service.loans.model.*;
import com.capitaworld.service.loans.model.corporate.AddProductRequest;
import com.capitaworld.service.loans.repository.fundprovider.*;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorTempRepository;
import com.capitaworld.service.loans.service.common.FundProviderSequenceService;
import com.capitaworld.service.loans.service.fundprovider.*;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.enums.LoanType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    //if use retail Loan Id
    private Integer[] productIds = {CommonUtils.LoanType.HOME_LOAN.getValue(), CommonUtils.LoanType.PERSONAL_LOAN.getValue()};

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
            if (!CommonUtils.isObjectNullOrEmpty(addProductRequest.getProductMappingId())) {
                //for Approval stage
                if (addProductRequest.getStage() == 2) {
                    //for approval stage change name
                    productMasterRepository.changeProductName(userId, addProductRequest.getProductMappingId(), addProductRequest.getName());
                } else {
                    //for pending stage change name
                    productMasterTempRepository.changeProductName(userId, addProductRequest.getProductMappingId(), addProductRequest.getName());
                }
                CommonDocumentUtils.endHook(logger, "saveOrUpdate");
                return 0l;
            } else {
                //if product id is null than create new product in temp table
                ProductMasterTemp productMasterTemp = new ProductMasterTemp();
                LoanType loanType = LoanType.getById(Integer.parseInt(addProductRequest.getProductId().toString()));
                WorkflowResponse workflowResponse = workflowClient.createJobForMasters(WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL, userId);
                Long jobId = null;
                if (workflowResponse != null) {
                    jobId = workflowResponse.getData() != null ? Long.valueOf(workflowResponse.getData().toString()) : null;
                    productMasterTemp.setJobId(jobId);
                }
                logger.info("addProductRequest.getProductId() === >" + addProductRequest.getProductId());
                productMasterTemp.setProductId(addProductRequest.getProductId());
                productMasterTemp.setIsMatched(false);
                productMasterTemp.setName(addProductRequest.getName());
                productMasterTemp.setFpName(addProductRequest.getFpName());
                productMasterTemp.setUserId(userId);
                productMasterTemp.setCreatedBy(userId);
                productMasterTemp.setCreatedDate(new Date());
                productMasterTemp.setModifiedBy(userId);
                productMasterTemp.setIsParameterFilled(false);
                productMasterTemp.setModifiedDate(new Date());
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
            }

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
        FpProductConditions fpProductConditions;
        if (CommonUtils.isObjectNullOrEmpty(productParameterRequest.getId())) {
            fpProductConditions = new FpProductConditions();
        } else {
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
                level = level + 1;
                FpProductParameters fpProductParameters;
                if (CommonUtils.isObjectNullOrEmpty(request.getId())) {
                    fpProductParameters = new FpProductParameters();
                } else {
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

        if (!CommonUtils.isObjectNullOrEmpty(stage) && stage == 1) {
            List<ProductMasterTemp> results = null;
            if (userType == 1) {
                if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
                    results = productMasterTempRepository.getUserRetailProductListByOrgId(userOrgId, Arrays.asList(productIds));
                } else {
                    results = productMasterTempRepository.getUserRetailProductList(userId, Arrays.asList(productIds));
                }
                for (ProductMasterTemp productMaster : results) {
                    ProductMasterRequest productMasterRequest = new ProductMasterRequest();
                    BeanUtils.copyProperties(productMaster, productMasterRequest);
                    productMasterRequest.setFinId(productMaster.getFinId());
                    List<Integer> gstTypes = fpGstTypeMappingTempRepository.getIdsByFpProductId(productMaster.getId());
                    productMasterRequest.setGstType(gstTypes);
                    productMasterRequests.add(productMasterRequest);
                }
            } else {

                if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
                    results = productMasterTempRepository.getUserCorporateProductListByOrgId(userOrgId);
                } else {
                    results = productMasterTempRepository.getUserCorporateProductList(userId);
                }
                for (ProductMasterTemp productMaster : results) {
                    ProductMasterRequest productMasterRequest = new ProductMasterRequest();
                    BeanUtils.copyProperties(productMaster, productMasterRequest);
                    productMasterRequest.setFinId(productMaster.getFinId());
                    List<Integer> gstTypes = fpGstTypeMappingTempRepository.getIdsByFpProductId(productMaster.getId());
                    productMasterRequest.setGstType(gstTypes);
                    productMasterRequests.add(productMasterRequest);
                }
            }
        } else {
            List<ProductMaster> results = null;
            if (userType == 1) {
                if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
                    results = productMasterRepository.getUserRetailProductListByOrgId(userOrgId, Arrays.asList(productIds));
                } else {
                    results = productMasterRepository.getUserRetailProductList(userId, Arrays.asList(productIds));
                }

            } else {
                if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
                    results = productMasterRepository.getUserCorporateProductListByOrgId(userOrgId);
                } else {
                    results = productMasterRepository.getUserCorporateProductList(userId);
                }
            }
            for (ProductMaster productMaster : results) {
                ProductMasterRequest productMasterRequest = new ProductMasterRequest();
                BeanUtils.copyProperties(productMaster, productMasterRequest);
                productMasterRequest.setFinId(productMaster.getFinId());
                List<Integer> gstTypes = fpGstTypeMappingRepository.getIdsByFpProductId(productMaster.getId());
                productMasterRequest.setGstType(gstTypes);
                productMasterRequests.add(productMasterRequest);
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
     * @param role
     * @param userId
     * @return
     */
    public ProductMasterRequest getProductDetails(Long id,Integer stage,Long role,Long userId){
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

}
