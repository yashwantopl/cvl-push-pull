package com.capitaworld.service.loans.service.fundprovider.impl;

import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.loans.domain.IndustrySectorDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.*;
import com.capitaworld.service.loans.model.*;
import com.capitaworld.service.loans.model.corporate.AddProductRequest;
import com.capitaworld.service.loans.model.corporate.TermLoanParameterRequest;
import com.capitaworld.service.loans.model.corporate.WcTlParameterRequest;
import com.capitaworld.service.loans.model.corporate.WorkingCapitalParameterRequest;
import com.capitaworld.service.loans.model.retail.PersonalLoanParameterRequest;
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
    private WorkingCapitalParameterService workingCapitalParameterService;

    @Autowired
    private TermLoanParameterService termLoanParameterService;

    @Autowired
    private PersonalLoanParameterService personalLoanParameterService;

    @Autowired
    private WcTlParameterService wcTlParameterService;

    @Autowired
    private FundProviderSequenceService fundProviderSequenceService;

    @Autowired
    private GeographicalCountryTempRepository geographicalCountryTempRepository;

    @Autowired
    private GeographicalStateTempRepository geographicalStateTempRepository;

    @Autowired
    private GeographicalCityTempRepository geographicalCityTempRepository;

    @Autowired
    private IndustrySectorTempRepository industrySectorTempRepository;

    @Autowired
    private FpGstTypeMappingTempRepository fpGstTypeMappingTempRepository;

    @Autowired
    private NegativeIndustryTempRepository negativeIndustryTempRepository;

    @Autowired
    private MsmeValueMappingRepository masterRepository;

    @Autowired
    private MsmeValueMappingTempRepository tempRepository;

    @Autowired
    private FpProductParametersRepository parametersRepository;

    @Autowired
    private FpProductConditionsRepository conditionsRepository;

    @Autowired
    private FpGstTypeMappingRepository fpGstTypeMappingRepository;

    private Integer[] productIds = {CommonUtils.LoanType.HOME_LOAN.getValue(), CommonUtils.LoanType.PERSONAL_LOAN.getValue()};

    @Override
    public Long saveOrUpdate(AddProductRequest addProductRequest, Long userOrgId) {
        CommonDocumentUtils.startHook(logger, "saveOrUpdate");

        try {

            if (!CommonUtils.isObjectNullOrEmpty(addProductRequest.getProductMappingId())) {
                //for Approval stage
                if (addProductRequest.getStage() == 2) {
                    productMasterRepository.changeProductName((CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId()) ? addProductRequest.getUserId() : addProductRequest.getClientId()), addProductRequest.getProductMappingId(), addProductRequest.getName());
                } else {
                    //for pending stage
                    productMasterTempRepository.changeProductName((CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId()) ? addProductRequest.getUserId() : addProductRequest.getClientId()), addProductRequest.getProductMappingId(), addProductRequest.getName());
                }
                CommonDocumentUtils.endHook(logger, "saveOrUpdate");
                return 0l;
            } else {
                ProductMasterTemp productMaster = null;
                LoanType loanType = LoanType.getById(Integer.parseInt(addProductRequest.getProductId().toString()));
                WorkflowResponse workflowResponse = workflowClient.createJobForMasters(
                        WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL,
                        (CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
                                ? addProductRequest.getUserId() : addProductRequest.getClientId()));
                Long jobId = null;

                switch (loanType) {
                    case WORKING_CAPITAL:
                        productMaster = new WorkingCapitalParameterTemp();
                        break;
                    case TERM_LOAN:
                        if (addProductRequest.getBusinessTypeId() == 2) {
                            productMaster = new NtbTermLoanParameterTemp();
                        } else {
                            productMaster = new TermLoanParameterTemp();
                        }
                        break;
                    case WCTL_LOAN:
                        productMaster = new WcTlParameterTemp();
                        break;
                    case PERSONAL_LOAN:
                        productMaster = new PersonalLoanParameterTemp();
                        break;

                    default:
                        break;
                }
                List<DataRequest> industrySecIdList = null, secIdList = null, geogaphicallyCountry = null, geogaphicallyState = null, geogaphicallyCity = null, negativeIndList = null;
                if (!CommonUtils.isObjectNullOrEmpty(addProductRequest.getLoanId())) {
                    switch (loanType) {
                        case WORKING_CAPITAL:
                            WorkingCapitalParameterTemp workingCapitalParameterTemp = new WorkingCapitalParameterTemp();
                            WorkingCapitalParameterRequest workingCapitalParameterRequest = workingCapitalParameterService.getWorkingCapitalParameter(addProductRequest.getLoanId());

                            //set multiple value in temp
                            industrySecIdList = workingCapitalParameterRequest.getIndustrylist();
                            secIdList = workingCapitalParameterRequest.getSectorlist();
                            geogaphicallyCountry = workingCapitalParameterRequest.getCountryList();
                            geogaphicallyState = workingCapitalParameterRequest.getStateList();
                            geogaphicallyCity = workingCapitalParameterRequest.getCityList();
                            negativeIndList = workingCapitalParameterRequest.getUnInterestedIndustrylist();
                            if (addProductRequest.getFinId() == null || addProductRequest.getFinId() == 4) {
                                workingCapitalParameterRequest.setIsNewTolTnwCheck(false);
                                workingCapitalParameterRequest.setNewTolTnw(null);
                            }
                            //END set multiple value in temp
                            BeanUtils.copyProperties(workingCapitalParameterRequest, workingCapitalParameterTemp, "id");
                            productMaster = workingCapitalParameterTemp;
                            productMaster.setIsParameterFilled(true);
                            break;
                        case TERM_LOAN:
                            if (addProductRequest.getBusinessTypeId() == 2) {

                                NtbTermLoanParameterTemp ntbTermLoanParameterTemp = new NtbTermLoanParameterTemp();
                                TermLoanParameterRequest termLoanParameterRequest = termLoanParameterService.getNtbTermLoanParameterRequest(addProductRequest.getLoanId());

                                //set multiple value in temp
                                industrySecIdList = termLoanParameterRequest.getIndustrylist();
                                secIdList = termLoanParameterRequest.getSectorlist();
                                geogaphicallyCountry = termLoanParameterRequest.getCountryList();
                                geogaphicallyState = termLoanParameterRequest.getStateList();
                                geogaphicallyCity = termLoanParameterRequest.getCityList();
                                negativeIndList = termLoanParameterRequest.getUnInterestedIndustrylist();
                                //END set multiple value in temp
                                if (addProductRequest.getFinId() == null || addProductRequest.getFinId() == 4) {
                                    termLoanParameterRequest.setIsNewTolTnwCheck(false);
                                    termLoanParameterRequest.setNewTolTnw(null);
                                }
                                BeanUtils.copyProperties(termLoanParameterRequest, ntbTermLoanParameterTemp, "id");
                                productMaster = ntbTermLoanParameterTemp;
                                productMaster.setIsParameterFilled(true);
                            } else {
                                TermLoanParameterTemp termLoanParameterTemp = new TermLoanParameterTemp();
                                TermLoanParameterRequest termLoanParameterRequest = termLoanParameterService.getTermLoanParameterRequest(addProductRequest.getLoanId());

                                //set multiple value in temp
                                industrySecIdList = termLoanParameterRequest.getIndustrylist();
                                secIdList = termLoanParameterRequest.getSectorlist();
                                geogaphicallyCountry = termLoanParameterRequest.getCountryList();
                                geogaphicallyState = termLoanParameterRequest.getStateList();
                                geogaphicallyCity = termLoanParameterRequest.getCityList();
                                negativeIndList = termLoanParameterRequest.getUnInterestedIndustrylist();
                                //END set multiple value in temp
                                if (addProductRequest.getFinId() == null || addProductRequest.getFinId() == 4) {
                                    termLoanParameterRequest.setIsNewTolTnwCheck(false);
                                    termLoanParameterRequest.setNewTolTnw(null);
                                }
                                BeanUtils.copyProperties(termLoanParameterRequest, termLoanParameterTemp, "id");
                                productMaster = termLoanParameterTemp;
                                productMaster.setIsParameterFilled(true);
                            }
                            break;
                        case WCTL_LOAN:
                            WcTlParameterTemp wcTlParameterTemp = new WcTlParameterTemp();
                            WcTlParameterRequest wcTlParameterRequest = wcTlParameterService.getWcTlRequest(addProductRequest.getLoanId());

                            //set multiple value in temp
                            industrySecIdList = wcTlParameterRequest.getIndustrylist();
                            secIdList = wcTlParameterRequest.getSectorlist();
                            geogaphicallyCountry = wcTlParameterRequest.getCountryList();
                            geogaphicallyState = wcTlParameterRequest.getStateList();
                            geogaphicallyCity = wcTlParameterRequest.getCityList();
                            negativeIndList = wcTlParameterRequest.getUnInterestedIndustrylist();
                            //END set multiple value in temp
                            if (addProductRequest.getFinId() == null || addProductRequest.getFinId() == 4) {
                                wcTlParameterRequest.setIsNewTolTnwCheck(false);
                                wcTlParameterRequest.setNewTolTnw(null);
                            }
                            BeanUtils.copyProperties(wcTlParameterRequest, wcTlParameterTemp, "id");
                            productMaster = wcTlParameterTemp;
                            productMaster.setIsParameterFilled(true);
                            break;
                        case PERSONAL_LOAN:

                            PersonalLoanParameterTemp personalLoanParameterTemp = new PersonalLoanParameterTemp();
                            PersonalLoanParameterRequest personalLoanParameterRequest = personalLoanParameterService.getPersonalLoanParameterRequest(addProductRequest.getLoanId());

                            geogaphicallyCountry = personalLoanParameterRequest.getCountryList();
                            geogaphicallyState = personalLoanParameterRequest.getStateList();
                            geogaphicallyCity = personalLoanParameterRequest.getCityList();
                            //negativeIndList=personalLoanParameterRequest.getUnInterestedIndustrylist();
                            //END set multiple value in temp
                            BeanUtils.copyProperties(personalLoanParameterRequest, personalLoanParameterTemp, "id");
                            productMaster = personalLoanParameterTemp;
                            productMaster.setIsParameterFilled(true);
                            break;

                        default:
                            break;
                    }


                }
                // productMaster.setJobId(null);
                if (workflowResponse != null) {
                    jobId = workflowResponse.getData() != null ? Long.valueOf(workflowResponse.getData().toString()) : null;
                }

                productMaster.setJobId(jobId);

                productMaster.setProductId(addProductRequest.getProductId());
                productMaster.setIsMatched(false);
                productMaster.setName(addProductRequest.getName());
                productMaster.setFpName(addProductRequest.getFpName());
                productMaster.setUserId((CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
                        ? addProductRequest.getUserId() : addProductRequest.getClientId()));
                productMaster.setCreatedBy((CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
                        ? addProductRequest.getUserId() : addProductRequest.getClientId()));
                productMaster.setCreatedDate(new Date());
                productMaster.setModifiedBy((CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
                        ? addProductRequest.getUserId() : addProductRequest.getClientId()));
                productMaster.setIsParameterFilled(false);
                productMaster.setModifiedDate(new Date());
                // set business type id
                productMaster.setBusinessTypeId(addProductRequest.getBusinessTypeId());
                productMaster.setWcRenewalStatus(addProductRequest.getWcRenewalStatus());
                productMaster.setFinId(addProductRequest.getFinId());
                productMaster.setIsCopied(false);
                productMaster.setIsActive(true);
                productMaster.setUserOrgId(userOrgId);
                productMaster.setStatusId(1);
                productMaster.setProductCode(
                        fundProviderSequenceService.getFundProviderSequenceNumber(addProductRequest.getProductId()));
                productMaster.setIsGst(addProductRequest.getIsGst());
                productMaster.setIsItr(addProductRequest.getIsItr());
                productMaster.setIsBankStatement(addProductRequest.getIsBankStatement());
                productMaster.setIsMca(addProductRequest.getIsMca());
                productMaster.setIsBureuPersonal(addProductRequest.getIsBureuPersonal());
                productMaster.setIsBureuCommercial(addProductRequest.getIsBureuCommercial());
                productMaster.setIsManualFill(addProductRequest.getIsManualFill());
                productMaster.setImportFromId(addProductRequest.getLoanId());
                ProductMasterTemp productMaster2 = productMasterTempRepository.save(productMaster);

                //save gst type for only WC

                if (loanType == LoanType.WORKING_CAPITAL) {
                    fpGstTypeMappingTempRepository.inActiveMasterByFpProductId(productMaster2.getId());
                    if (!CommonUtils.isListNullOrEmpty(addProductRequest.getGstType()))
                        saveGstTypeTemp(productMaster2.getId(), addProductRequest.getGstType(), (CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
                                ? addProductRequest.getUserId() : addProductRequest.getClientId()));
                }

                industrySectorTempRepository.inActiveMappingByFpProductId(productMaster2.getId());
                // industry data save
                if (!CommonUtils.isListNullOrEmpty(industrySecIdList))
                    saveIndustryTemp(productMaster2.getId(), industrySecIdList, (CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
                            ? addProductRequest.getUserId() : addProductRequest.getClientId()));
                // Sector data save
                if (!CommonUtils.isListNullOrEmpty(secIdList))
                    saveSectorTemp(productMaster2.getId(), secIdList, (CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
                            ? addProductRequest.getUserId() : addProductRequest.getClientId()));
                geographicalCountryTempRepository.inActiveMappingByFpProductId(productMaster2.getId());
                // country data save
                if (!CommonUtils.isListNullOrEmpty(geogaphicallyCountry))
                    saveCountryTemp(productMaster2.getId(), geogaphicallyCountry, (CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
                            ? addProductRequest.getUserId() : addProductRequest.getClientId()));
                // state data save
                geographicalStateTempRepository.inActiveMappingByFpProductId(productMaster2.getId());
                if (!CommonUtils.isListNullOrEmpty(geogaphicallyState))
                    saveStateTemp(productMaster2.getId(), geogaphicallyState, (CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
                            ? addProductRequest.getUserId() : addProductRequest.getClientId()));
                // city data save
                geographicalCityTempRepository.inActiveMappingByFpProductId(productMaster2.getId());
                if (!CommonUtils.isListNullOrEmpty(geogaphicallyCity))
                    saveCityTemp(productMaster2.getId(), geogaphicallyCity, (CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
                            ? addProductRequest.getUserId() : addProductRequest.getClientId()));
                // negative industry save
                negativeIndustryTempRepository.inActiveMappingByFpProductMasterId(productMaster2.getId());
                if (!CommonUtils.isListNullOrEmpty(negativeIndList))
                    saveNegativeIndustryTemp(productMaster2.getId(), negativeIndList, (CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
                            ? addProductRequest.getUserId() : addProductRequest.getClientId()));


                //msme value
                tempRepository.inActiveTempByFpProductId(productMaster2.getId());
                List<MsmeValueMapping> masterList = masterRepository.findByFpProductIdAndIsActive(addProductRequest.getLoanId(), true);

                if (!CommonUtils.isListNullOrEmpty(masterList)) {
                    for (MsmeValueMapping master : masterList) {
                        MsmeValueMappingTemp temp = new MsmeValueMappingTemp();
                        BeanUtils.copyProperties(master, temp, "id");
                        temp.setActive(true);
                        temp.setFpProductId(productMaster2.getId());
                        temp.setCreatedBy(CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
                                ? addProductRequest.getUserId() : addProductRequest.getClientId());
                        temp.setCreatedDate(new Date());
                        temp.setModifiedBy(CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
                                ? addProductRequest.getUserId() : addProductRequest.getClientId());
                        temp.setModifiedDate(new Date());
                        tempRepository.save(temp);
                    }
                }

                return productMaster2.getId();
            }

        } catch (Exception e) {
            logger.error("error while saveOrUpdate : ", e);
            return null;
        }
    }

    private void saveStateTemp(Long id, List<DataRequest> geogaphicallyState, Long userId) {
        logger.info("start saveStateTemp");
        GeographicalStateDetailTemp geographicalStateDetail = null;
        for (DataRequest dataRequest : geogaphicallyState) {
            geographicalStateDetail = new GeographicalStateDetailTemp();
            geographicalStateDetail.setStateId(dataRequest.getId());
            geographicalStateDetail.setFpProductMaster(id);
            geographicalStateDetail.setCreatedBy(userId);
            geographicalStateDetail.setModifiedBy(userId);
            geographicalStateDetail.setCreatedDate(new Date());
            geographicalStateDetail.setModifiedDate(new Date());
            geographicalStateDetail.setIsActive(true);
            // create by and update
            geographicalStateTempRepository.save(geographicalStateDetail);
        }
        logger.info("end saveStateTemp");

    }

    private void saveCountryTemp(Long id, List<DataRequest> geogaphicallyCountry, Long userId) {

        logger.info("save saveCountryTemp");
        GeographicalCountryDetailTemp geographicalCountryDetail = null;
        for (DataRequest dataRequest : geogaphicallyCountry) {
            geographicalCountryDetail = new GeographicalCountryDetailTemp();
            geographicalCountryDetail.setCountryId(dataRequest.getId());
            geographicalCountryDetail.setFpProductMaster(id);
            geographicalCountryDetail.setCreatedBy(userId);
            geographicalCountryDetail.setModifiedBy(userId);
            geographicalCountryDetail.setCreatedDate(new Date());
            geographicalCountryDetail.setModifiedDate(new Date());
            geographicalCountryDetail.setIsActive(true);
            // create by and update
            geographicalCountryTempRepository.save(geographicalCountryDetail);
        }
        logger.info("end saveCountryTemp");

    }

    private void saveSectorTemp(Long id, List<DataRequest> secIdList, Long userId) {

        logger.info("start saveSectorTemp");
        IndustrySectorDetailTemp industrySectorDetail = null;
        for (DataRequest dataRequest : secIdList) {
            industrySectorDetail = new IndustrySectorDetailTemp();
            industrySectorDetail.setFpProductId(id);
            industrySectorDetail.setSectorId(dataRequest.getId());
            industrySectorDetail.setCreatedBy(userId);
            industrySectorDetail.setModifiedBy(userId);
            industrySectorDetail.setCreatedDate(new Date());
            industrySectorDetail.setModifiedDate(new Date());
            industrySectorDetail.setIsActive(true);
            // create by and update
            industrySectorTempRepository.save(industrySectorDetail);
        }
        logger.info("end saveSectorTemp");

    }

    private void saveIndustryTemp(Long id, List<DataRequest> industrySecIdList, Long userId) {

        logger.info("start saveIndustryTemp");
        IndustrySectorDetailTemp industrySectorDetail = null;
        for (DataRequest dataRequest : industrySecIdList) {
            industrySectorDetail = new IndustrySectorDetailTemp();
            industrySectorDetail.setFpProductId(id);
            industrySectorDetail.setIndustryId(dataRequest.getId());
            industrySectorDetail.setCreatedBy(userId);
            industrySectorDetail.setModifiedBy(userId);
            industrySectorDetail.setCreatedDate(new Date());
            industrySectorDetail.setModifiedDate(new Date());
            industrySectorDetail.setIsActive(true);
            // create by and update
            industrySectorTempRepository.save(industrySectorDetail);
        }
        logger.info("end saveIndustryTemp");

    }

    private void saveGstTypeTemp(Long id, List<Integer> gstType, Long userId) {
        CommonDocumentUtils.startHook(logger, "saveGstTypeTemp");
        FpGstTypeMappingTemp fpGstTypeMappingTemp = null;
        for (Integer dataRequest : gstType) {
            fpGstTypeMappingTemp = new FpGstTypeMappingTemp();
            fpGstTypeMappingTemp.setFpProductId(id);
            fpGstTypeMappingTemp.setGstTypeId(dataRequest);
            fpGstTypeMappingTemp.setCreatedBy(userId);
            fpGstTypeMappingTemp.setModifiedBy(userId);
            fpGstTypeMappingTemp.setCreatedDate(new Date());
            fpGstTypeMappingTemp.setModifiedDate(new Date());
            fpGstTypeMappingTemp.setIsActive(true);
            // create by and update
            fpGstTypeMappingTempRepository.save(fpGstTypeMappingTemp);
        }
        CommonDocumentUtils.endHook(logger, "saveGstTypeTemp");
    }

    private void saveCityTemp(Long id, List<DataRequest> geogaphicallyCity, Long userId) {

        logger.info("start saveCity");
        GeographicalCityDetailTemp geographicalCityDetail = null;
        //List<GeographicalCityDetailTemp> geographicalCityDetailTemps=new ArrayList<>(geogaphicallyCity.size());
        for (DataRequest dataRequest : geogaphicallyCity) {
            geographicalCityDetail = new GeographicalCityDetailTemp();
            geographicalCityDetail.setCityId(dataRequest.getId());
            geographicalCityDetail.setFpProductMaster(id);
            geographicalCityDetail.setCreatedBy(userId);
            geographicalCityDetail.setModifiedBy(userId);
            geographicalCityDetail.setCreatedDate(new Date());
            geographicalCityDetail.setModifiedDate(new Date());
            geographicalCityDetail.setIsActive(true);
            // create by and update
            geographicalCityTempRepository.save(geographicalCityDetail);
        }
        logger.info("end saveCity");
    }

    private void saveNegativeIndustryTemp(Long id, List<DataRequest> negativeIndList, Long userId) {
        CommonDocumentUtils.startHook(logger, "saveNegativeIndustryTemp");
        NegativeIndustryTemp negativeIndustry = null;
        for (DataRequest dataRequest : negativeIndList) {
            negativeIndustry = new NegativeIndustryTemp();
            negativeIndustry.setFpProductMasterId(id);
            negativeIndustry.setIndustryId(dataRequest.getId());
            negativeIndustry.setCreatedBy(userId);
            negativeIndustry.setModifiedBy(userId);
            negativeIndustry.setCreatedDate(new Date());
            negativeIndustry.setModifiedDate(new Date());
            negativeIndustry.setIsActive(true);
            // create by and update
            negativeIndustryTempRepository.save(negativeIndustry);
        }
        CommonDocumentUtils.endHook(logger, "saveNegativeIndustryTemp");
    }

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

    @Override
    public List<ProductConditionResponse> getConditionsByProductId(Long productId) {
        List<ProductConditionResponse> conditionResponses = conditionsRepository.findAllByFpProductId(productId);
        for (ProductConditionResponse condition : conditionResponses) {
            List<ProductParameterResponse> parameterResponses = parametersRepository.findAllByConditionId(condition.getId());
            condition.setParameters(getParameters(parameterResponses));
        }
        return conditionResponses;
    }

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

    private String getFormulaNameById(Long formulaId) {
        if (formulaId != null) {
            List<String> formulaNameById = parametersRepository.getFormulaNameById(formulaId);
            return CommonUtils.isObjectListNull(formulaNameById) ? null : formulaNameById.get(0);
        }
        return null;
    }

}
