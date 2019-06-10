package com.capitaworld.service.loans.service.fundprovider.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.workflow.model.WorkflowJobsTrackerRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.MultipleJSONObjectHelper;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCityDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCityDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.HomeLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.HomeLoanParameterTemp;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.retail.HomeLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.HomeLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.HomeLoanParameterTempRepository;
import com.capitaworld.service.loans.service.fundprovider.FPParameterMappingService;
import com.capitaworld.service.loans.service.fundprovider.HomeLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.LoanPurposeAmountMappingService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Transactional
@Service
public class HomeLoanParameterServiceImpl implements HomeLoanParameterService {

	private static final Logger logger = LoggerFactory.getLogger(HomeLoanParameterServiceImpl.class);

	private static final String ERROR_WHILE_GET_HOME_LOAN_PARAMETER_REQUEST_MSG = "error while getHomeLoanParameterRequest : ";

	@Autowired
	private HomeLoanParameterRepository homeLoanParameterRepository;

	@Autowired
	private FPParameterMappingService fPParameterMappingService;

	@Autowired
	private HomeLoanParameterTempRepository homeLoanParameterTempRepository;

	@Autowired
	private GeographicalCountryRepository geographicalCountryRepository;

	@Autowired
	private GeographicalStateRepository geographicalStateRepository;

	@Autowired
	private GeographicalCityRepository geographicalCityRepository;

	@Autowired
	private GeographicalCountryTempRepository geographicalCountryTempRepository;

	@Autowired
	private GeographicalStateTempRepository geographicalStateTempRepository;

	@Autowired
	private GeographicalCityTempRepository geographicalCityTempRepository;

	@Autowired
	private OneFormClient oneFormClient;
	
	@Autowired
	private LoanPurposeAmountMappingService loanPurposeAmountMappingService; 
	
	@Autowired
	private WorkflowClient workflowClient;
	
	@Override
	public boolean saveOrUpdate(HomeLoanParameterRequest homeLoanParameterRequest) {

		logger.info("saveOrUpdate starts");
		HomeLoanParameterTemp loanParameter = homeLoanParameterTempRepository.findById(homeLoanParameterRequest.getId());

		HomeLoanParameter homeLoanParameter = null;
		if (loanParameter.getFpProductMappingId() != null) {
			homeLoanParameter = homeLoanParameterRepository.findOne(loanParameter.getFpProductMappingId());
		}
		if (homeLoanParameter == null) {
			homeLoanParameter = new HomeLoanParameter();
		}

		loanParameter.setStatusId(CommonUtils.Status.APPROVED);
		loanParameter.setIsDeleted(false);
		loanParameter.setIsEdit(false);
		loanParameter.setIsCopied(true);
		loanParameter.setIsApproved(true);
		loanParameter.setApprovalDate(new Date());
		loanParameter.setFpProductMappingId(homeLoanParameter.getId());
		homeLoanParameterTempRepository.save(loanParameter);

		if (!CommonUtils.isObjectListNull(homeLoanParameterRequest.getMaxTenure()))
			homeLoanParameterRequest.setMaxTenure(homeLoanParameterRequest.getMaxTenure() * 12);
		if (!CommonUtils.isObjectListNull(homeLoanParameterRequest.getMinTenure()))
			homeLoanParameterRequest.setMinTenure(homeLoanParameterRequest.getMinTenure() * 12);

		BeanUtils.copyProperties(homeLoanParameterRequest, homeLoanParameter, CommonUtils.IgnorableCopy.getFpProduct());
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameterRequest.getMinBankRelation())) {
			homeLoanParameter.setMinBankRelation(homeLoanParameterRequest.getMinBankRelation().intValue());	
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameterRequest.getMinBureauScore())) {
			homeLoanParameter.setMinBureauScore(homeLoanParameterRequest.getMinBureauScore().intValue());	
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameterRequest.getMaxBureauScore())) {
			homeLoanParameter.setMaxBureauScore(homeLoanParameterRequest.getMaxBureauScore().intValue());	
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameterRequest.getMinTotalJobExp())) {
			homeLoanParameter.setMinTotalJobExp(homeLoanParameterRequest.getMinTotalJobExp().intValue());	
		}
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameterRequest.getMaxTotalJobExp())) {
			homeLoanParameter.setMaxTotalJobExp(homeLoanParameterRequest.getMaxTotalJobExp().intValue());	
		}
		
		homeLoanParameter.setIsParameterFilled(true);
		homeLoanParameter.setProductId(homeLoanParameterRequest.getProductId());
		homeLoanParameter.setUserId(homeLoanParameterRequest.getUserId());
		homeLoanParameter.setModifiedBy(homeLoanParameterRequest.getUserId());
		homeLoanParameter.setIsParameterFilled(true);
		homeLoanParameter.setModifiedDate(new Date());
		homeLoanParameter = homeLoanParameterRepository.save(homeLoanParameter);
		homeLoanParameterRequest.setId(homeLoanParameter.getId());

		geographicalCountryRepository.inActiveMappingByFpProductId(homeLoanParameterRequest.getId());
		// country data save
		saveCountry(homeLoanParameterRequest);
		// state data save
		geographicalStateRepository.inActiveMappingByFpProductId(homeLoanParameterRequest.getId());
		saveState(homeLoanParameterRequest);
		// city data save
		geographicalCityRepository.inActiveMappingByFpProductId(homeLoanParameterRequest.getId());
		saveCity(homeLoanParameterRequest);

		// Saving Mapping Current CURRENT_EMPLOYMENT
		fPParameterMappingService.inactiveAndSave(homeLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.CURRENT_EMPLOYMENT,
				homeLoanParameterRequest.getCurrentEmploymentStatusIds());

		// Saving Mapping Current RESIDENTIAL
		fPParameterMappingService.inactiveAndSave(homeLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.RESIDENTIAL, homeLoanParameterRequest.getResidentialStatusIds());

		// Saving Mapping Current BORROWER_TYPE
		fPParameterMappingService.inactiveAndSave(homeLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.BORROWER_TYPE, homeLoanParameterRequest.getBorrowerTypeIds());

		// Saving Mapping Current SALARY_MODE
		fPParameterMappingService.inactiveAndSave(homeLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.SALARY_MODE, homeLoanParameterRequest.getSalaryModeIds());

		// Saving Mapping Current BORROWER_SALARY_ACCOUNT
		fPParameterMappingService.inactiveAndSave(homeLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.BORROWER_SALARY_ACCOUNT, homeLoanParameterRequest.getBorrSalAccIds());
		
		fPParameterMappingService.inactiveAndSave(homeLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.EMPLOYMENT_WITH, homeLoanParameterRequest.getEmploymentWithIds());
		
		fPParameterMappingService.inactiveAndSave(homeLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.SLEF_EMPLOYMENT_WITH, homeLoanParameterRequest.getSelfEmployedWithIds());
		
		//Saving Loan Purpose Amount Mapping
		loanPurposeAmountMappingService.deleteAndSave(homeLoanParameterRequest.getLoanPurposeAmountMappingRequests(), homeLoanParameterRequest.getId());

		logger.info("saveOrUpdate Ends");
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HomeLoanParameterRequest getHomeLoanParameterRequest(Long id) {

		CommonDocumentUtils.startHook(logger, "getHomeLoanParameterRequest");
		HomeLoanParameter homeLoanParameter = homeLoanParameterRepository.findById(id);
		if (homeLoanParameter == null) {
			return null;
		}
		HomeLoanParameterRequest homeLoanParameterRequest = new HomeLoanParameterRequest();
		BeanUtils.copyProperties(homeLoanParameter, homeLoanParameterRequest);

		if (!CommonUtils.isObjectListNull(homeLoanParameterRequest.getMaxTenure()))
			homeLoanParameterRequest.setMaxTenure(homeLoanParameterRequest.getMaxTenure() / 12);
		if (!CommonUtils.isObjectListNull(homeLoanParameterRequest.getMinTenure()))
			homeLoanParameterRequest.setMinTenure(homeLoanParameterRequest.getMinTenure() / 12);

		List<Long> countryList = geographicalCountryRepository
				.getCountryByFpProductId(homeLoanParameterRequest.getId());
		if (!countryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
				homeLoanParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_HOME_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> stateList = geographicalStateRepository.getStateByFpProductId(homeLoanParameterRequest.getId());
		if (!stateList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
				homeLoanParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_HOME_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> cityList = geographicalCityRepository.getCityByFpProductId(homeLoanParameterRequest.getId());
		if (!cityList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
				homeLoanParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_HOME_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		//Setting mannual as Datatype is Diff. So need to Set.
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameter.getMinBankRelation())) {
			homeLoanParameterRequest.setMinBankRelation(homeLoanParameter.getMinBankRelation().doubleValue());	
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameter.getMinBureauScore())) {
			homeLoanParameterRequest.setMinBureauScore(homeLoanParameter.getMinBureauScore().doubleValue());	
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameter.getMaxBureauScore())) {
			homeLoanParameterRequest.setMaxBureauScore(homeLoanParameter.getMaxBureauScore().doubleValue());	
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameter.getMinTotalJobExp())) {
			homeLoanParameterRequest.setMinTotalJobExp(homeLoanParameter.getMinTotalJobExp().doubleValue());	
		}
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameter.getMaxTotalJobExp())) {
			homeLoanParameterRequest.setMaxTotalJobExp(homeLoanParameter.getMaxTotalJobExp().doubleValue());	
		}
		
		
		// Getting Mapping Current CURRENT_EMPLOYMENT
		homeLoanParameterRequest.setCurrentEmploymentStatusIds(fPParameterMappingService
				.getParameters(homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.CURRENT_EMPLOYMENT));

		// Saving Mapping Current RESIDENTIAL
		homeLoanParameterRequest.setResidentialStatusIds(fPParameterMappingService
				.getParameters(homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.RESIDENTIAL));

		// Saving Mapping Current BORROWER_TYPE
		homeLoanParameterRequest.setBorrowerTypeIds(fPParameterMappingService
				.getParameters(homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BORROWER_TYPE));

		// Saving Mapping Current SALARY_MODE
		homeLoanParameterRequest.setSalaryModeIds(fPParameterMappingService
				.getParameters(homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.SALARY_MODE));

		// Saving Mapping Current BORROWER_SALARY_ACCOUNT
		homeLoanParameterRequest.setBorrSalAccIds(fPParameterMappingService
				.getParameters(homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BORROWER_SALARY_ACCOUNT));
		
		homeLoanParameterRequest.setEmploymentWithIds(fPParameterMappingService
				.getParameters(homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.EMPLOYMENT_WITH));
		
		homeLoanParameterRequest.setSelfEmployedWithIds(fPParameterMappingService
				.getParameters(homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.SLEF_EMPLOYMENT_WITH));

		//Getting Loan Purpose Amount Mapping
		homeLoanParameterRequest.setLoanPurposeAmountMappingRequests(loanPurposeAmountMappingService.getByFpProductId(homeLoanParameterRequest.getId()));
		
		CommonDocumentUtils.endHook(logger, "getHomeLoanParameterRequest");
		return homeLoanParameterRequest;
	}

	private void saveCountry(HomeLoanParameterRequest homeLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCountry");

		GeographicalCountryDetail geographicalCountryDetail = null;
		for (DataRequest dataRequest : homeLoanParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetail();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(homeLoanParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(homeLoanParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(homeLoanParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryRepository.save(geographicalCountryDetail);
			CommonDocumentUtils.endHook(logger, "saveCountry");
		}
	}

	private void saveState(HomeLoanParameterRequest homeLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveState");
		GeographicalStateDetail geographicalStateDetail = null;
		for (DataRequest dataRequest : homeLoanParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetail();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(homeLoanParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(homeLoanParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(homeLoanParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateRepository.save(geographicalStateDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveState");
	}

	private void saveCity(HomeLoanParameterRequest homeLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCity");
		GeographicalCityDetail geographicalCityDetail = null;
		for (DataRequest dataRequest : homeLoanParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetail();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(homeLoanParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(homeLoanParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(homeLoanParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityRepository.save(geographicalCityDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCity");
	}

	@SuppressWarnings("unchecked")
	@Override
	public HomeLoanParameterRequest getTemp(Long id, Long role, Long userId) {

		logger.info("Start getTemp==>");
		HomeLoanParameterTemp homeLoanParameter = homeLoanParameterTempRepository.findById(id);
		if (homeLoanParameter == null) {
			return null;
		}
		HomeLoanParameterRequest homeLoanParameterRequest = new HomeLoanParameterRequest();
		BeanUtils.copyProperties(homeLoanParameter, homeLoanParameterRequest);

		if (!CommonUtils.isObjectListNull(homeLoanParameterRequest.getMaxTenure()))
			homeLoanParameterRequest.setMaxTenure(homeLoanParameterRequest.getMaxTenure() / 12);
		if (!CommonUtils.isObjectListNull(homeLoanParameterRequest.getMinTenure()))
			homeLoanParameterRequest.setMinTenure(homeLoanParameterRequest.getMinTenure() / 12);

		List<Long> countryList = geographicalCountryTempRepository
				.getCountryByFpProductId(homeLoanParameterRequest.getId());
		if (!countryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
				if(!CommonUtils.isListNullOrEmpty(formResponse.getListData())) {
					List<DataRequest> cList = new ArrayList<>(formResponse.getListData().size());
					for(Object o : formResponse.getListData()) {
						cList.add(MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object> ) o, DataRequest.class));
					}
					homeLoanParameterRequest.setCountryList(cList);
				}
			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_HOME_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> stateList = geographicalStateTempRepository.getStateByFpProductId(homeLoanParameterRequest.getId());
		if (!stateList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
				if(!CommonUtils.isListNullOrEmpty(formResponse.getListData())) {
					List<DataRequest> sList = new ArrayList<>(formResponse.getListData().size());
					for(Object o : formResponse.getListData()) {
						sList.add(MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object> ) o, DataRequest.class));
					}
					homeLoanParameterRequest.setStateList(sList);
				}
			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_HOME_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> cityList = geographicalCityTempRepository.getCityByFpProductId(homeLoanParameterRequest.getId());
		if (!cityList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
				if(!CommonUtils.isListNullOrEmpty(formResponse.getListData())) {
					List<DataRequest> ctList = new ArrayList<>(formResponse.getListData().size());
					for(Object o : formResponse.getListData()) {
						ctList.add(MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object> ) o, DataRequest.class));
					}
					homeLoanParameterRequest.setCityList(ctList);
				}

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_HOME_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}
		
		//Setting mannual as Datatype is Diff. So need to Set.
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameter.getMinBankRelation())) {
			homeLoanParameterRequest.setMinBankRelation(homeLoanParameter.getMinBankRelation().doubleValue());	
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameter.getMinBureauScore())) {
			homeLoanParameterRequest.setMinBureauScore(homeLoanParameter.getMinBureauScore().doubleValue());	
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameter.getMaxBureauScore())) {
			homeLoanParameterRequest.setMaxBureauScore(homeLoanParameter.getMaxBureauScore().doubleValue());	
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameter.getMinTotalJobExp())) {
			homeLoanParameterRequest.setMinTotalJobExp(homeLoanParameter.getMinTotalJobExp().doubleValue());	
		}
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameter.getMaxTotalJobExp())) {
			homeLoanParameterRequest.setMaxTotalJobExp(homeLoanParameter.getMaxTotalJobExp().doubleValue());	
		}
		
		// Getting Mapping Current CURRENT_EMPLOYMENT
		homeLoanParameterRequest.setCurrentEmploymentStatusIds(fPParameterMappingService
				.getParametersTemp(homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.CURRENT_EMPLOYMENT));

		// Saving Mapping Current RESIDENTIAL
		homeLoanParameterRequest.setResidentialStatusIds(fPParameterMappingService
				.getParametersTemp(homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.RESIDENTIAL));

		// Saving Mapping Current BORROWER_TYPE
		homeLoanParameterRequest.setBorrowerTypeIds(fPParameterMappingService
				.getParametersTemp(homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BORROWER_TYPE));

		// Saving Mapping Current SALARY_MODE
		homeLoanParameterRequest.setSalaryModeIds(fPParameterMappingService
				.getParametersTemp(homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.SALARY_MODE));

		// Saving Mapping Current BORROWER_SALARY_ACCOUNT
		homeLoanParameterRequest.setBorrSalAccIds(fPParameterMappingService.getParametersTemp(
				homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BORROWER_SALARY_ACCOUNT));
		
		homeLoanParameterRequest.setEmploymentWithIds(fPParameterMappingService.getParametersTemp(
				homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.EMPLOYMENT_WITH));
		
		homeLoanParameterRequest.setSelfEmployedWithIds(fPParameterMappingService.getParametersTemp(
				homeLoanParameterRequest.getId(), CommonUtils.ParameterTypes.SLEF_EMPLOYMENT_WITH));

		//Getting Loan Purpose Amount Mapping
		homeLoanParameterRequest.setLoanPurposeAmountMappingRequests(loanPurposeAmountMappingService.getByFpProductId(homeLoanParameterRequest.getId()));
		
		//set workflow buttons
		
		 if (!CommonUtils.isObjectNullOrEmpty(homeLoanParameter.getJobId()) && !CommonUtils.isObjectNullOrEmpty(role)) {
            WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(homeLoanParameter.getJobId(),Arrays.asList(role), userId);
            if (!CommonUtils.isObjectNullOrEmpty(workflowResponse) && !CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
                try {
                    WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) workflowResponse.getData(), WorkflowJobsTrackerRequest.class);
                    if (!CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
                    	homeLoanParameterRequest.setWorkflowData(workflowJobsTrackerRequest.getStep().getStepActions());
                    } else {
                        logger.info("response from workflow NULL jobId = {} and roleId = {}", homeLoanParameter.getJobId(), role);
                    }
                } catch (IOException e) {
                    logger.error("Error While getting data from workflow {}", e);
                }
            }
        } else {
            logger.info("you set jobId or list of roleId NULL for calling workflow");
        }
		
		logger.info("End getTemp==>");
		return homeLoanParameterRequest;
	}

	@Override
	public Boolean saveOrUpdateTemp(HomeLoanParameterRequest homeLoanParameterRequest) {
		logger.info("saveOrUpdateTemp Start");
		HomeLoanParameterTemp homeLoanParameter = null;

		homeLoanParameter = homeLoanParameterTempRepository.findOne(homeLoanParameterRequest.getId());
		if (homeLoanParameter == null) {
			return false;
		}

		if (!CommonUtils.isObjectListNull(homeLoanParameterRequest.getMaxTenure()))
			homeLoanParameterRequest.setMaxTenure(homeLoanParameterRequest.getMaxTenure() * 12);
		if (!CommonUtils.isObjectListNull(homeLoanParameterRequest.getMinTenure()))
			homeLoanParameterRequest.setMinTenure(homeLoanParameterRequest.getMinTenure() * 12);

		BeanUtils.copyProperties(homeLoanParameterRequest, homeLoanParameter, CommonUtils.IgnorableCopy.getFpProduct());
		//Setting mannual as Datatype is Diff. So need to Set.
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameterRequest.getMinBankRelation())) {
			homeLoanParameter.setMinBankRelation(homeLoanParameterRequest.getMinBankRelation().intValue());	
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameterRequest.getMinBureauScore())) {
			homeLoanParameter.setMinBureauScore(homeLoanParameterRequest.getMinBureauScore().intValue());	
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameterRequest.getMaxBureauScore())) {
			homeLoanParameter.setMaxBureauScore(homeLoanParameterRequest.getMaxBureauScore().intValue());	
		}
		
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameterRequest.getMinTotalJobExp())) {
			homeLoanParameter.setMinTotalJobExp(homeLoanParameterRequest.getMinTotalJobExp().intValue());	
		}
		if(!CommonUtils.isObjectNullOrEmpty(homeLoanParameterRequest.getMaxTotalJobExp())) {
			homeLoanParameter.setMaxTotalJobExp(homeLoanParameterRequest.getMaxTotalJobExp().intValue());	
		}
		
		homeLoanParameter.setModifiedBy(homeLoanParameterRequest.getUserId());
		homeLoanParameter.setIsParameterFilled(true);
		homeLoanParameter.setModifiedDate(new Date());
		if (CommonUtils.isObjectNullOrEmpty(homeLoanParameter.getJobId())) {
			WorkflowResponse workflowResponse = workflowClient.createJobForMasters(WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL,homeLoanParameter.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
				homeLoanParameter.setJobId(Long.valueOf(workflowResponse.getData().toString()));
			}
		}
		homeLoanParameterTempRepository.save(homeLoanParameter);
		
		
		geographicalCountryTempRepository.inActiveMappingByFpProductId(homeLoanParameterRequest.getId());
		// country data save
		saveCountryTemp(homeLoanParameterRequest);
		// state data save
		geographicalStateTempRepository.inActiveMappingByFpProductId(homeLoanParameterRequest.getId());
		saveStateTemp(homeLoanParameterRequest);
		// city data save
		geographicalCityTempRepository.inActiveMappingByFpProductId(homeLoanParameterRequest.getId());
		saveCityTemp(homeLoanParameterRequest);

		// Saving Mapping Current CURRENT_EMPLOYMENT
		fPParameterMappingService.inactiveAndSaveTemp(homeLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.CURRENT_EMPLOYMENT,
				homeLoanParameterRequest.getCurrentEmploymentStatusIds());

		// Saving Mapping Current RESIDENTIAL
		fPParameterMappingService.inactiveAndSaveTemp(homeLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.RESIDENTIAL, homeLoanParameterRequest.getResidentialStatusIds());

		// Saving Mapping Current BORROWER_TYPE
		fPParameterMappingService.inactiveAndSaveTemp(homeLoanParameterRequest.getId(),	
				CommonUtils.ParameterTypes.BORROWER_TYPE, homeLoanParameterRequest.getBorrowerTypeIds());

		// Saving Mapping Current SALARY_MODE
		fPParameterMappingService.inactiveAndSaveTemp(homeLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.SALARY_MODE, homeLoanParameterRequest.getSalaryModeIds());

		// Saving Mapping Current BORROWER_SALARY_ACCOUNT
		fPParameterMappingService.inactiveAndSaveTemp(homeLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.BORROWER_SALARY_ACCOUNT, homeLoanParameterRequest.getBorrSalAccIds());
		
		fPParameterMappingService.inactiveAndSaveTemp(homeLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.EMPLOYMENT_WITH, homeLoanParameterRequest.getEmploymentWithIds());
		
		fPParameterMappingService.inactiveAndSaveTemp(homeLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.SLEF_EMPLOYMENT_WITH, homeLoanParameterRequest.getSelfEmployedWithIds());
		
		//Saving Loan Purpose Amount Mapping
		loanPurposeAmountMappingService.deleteAndSave(homeLoanParameterRequest.getLoanPurposeAmountMappingRequests(), homeLoanParameterRequest.getId());
		logger.info("saveOrUpdateTemp End");
		return true;
	}

	@Override
	public Boolean saveMasterFromTemp(Long mappingId) throws LoansException {
		HomeLoanParameterRequest homeLoanParameterRequest = getTemp(mappingId, null, null);
		return saveOrUpdate(homeLoanParameterRequest);
	}

	private void saveCountryTemp(HomeLoanParameterRequest homeLoanParameterRequest) {

		logger.info("save saveCountryTemp");
		if (!CommonUtils.isListNullOrEmpty(homeLoanParameterRequest.getCountryList())) {
			GeographicalCountryDetailTemp geographicalCountryDetail = null;
			for (DataRequest dataRequest : homeLoanParameterRequest.getCountryList()) {
				geographicalCountryDetail = new GeographicalCountryDetailTemp();
				geographicalCountryDetail.setCountryId(dataRequest.getId());
				geographicalCountryDetail.setFpProductMaster(homeLoanParameterRequest.getId());
				geographicalCountryDetail.setCreatedBy(homeLoanParameterRequest.getUserId());
				geographicalCountryDetail.setModifiedBy(homeLoanParameterRequest.getUserId());
				geographicalCountryDetail.setCreatedDate(new Date());
				geographicalCountryDetail.setModifiedDate(new Date());
				geographicalCountryDetail.setIsActive(true);
				// create by and update
				geographicalCountryTempRepository.save(geographicalCountryDetail);
			}
		}
		logger.info("end saveCountryTemp");

	}

	private void saveStateTemp(HomeLoanParameterRequest homeLoanParameterRequest) {

		logger.info("start saveStateTemp");
		if (!CommonUtils.isListNullOrEmpty(homeLoanParameterRequest.getStateList())) {
			GeographicalStateDetailTemp geographicalStateDetail = null;
			for (DataRequest dataRequest : homeLoanParameterRequest.getStateList()) {
				geographicalStateDetail = new GeographicalStateDetailTemp();
				geographicalStateDetail.setStateId(dataRequest.getId());
				geographicalStateDetail.setFpProductMaster(homeLoanParameterRequest.getId());
				geographicalStateDetail.setCreatedBy(homeLoanParameterRequest.getUserId());
				geographicalStateDetail.setModifiedBy(homeLoanParameterRequest.getUserId());
				geographicalStateDetail.setCreatedDate(new Date());
				geographicalStateDetail.setModifiedDate(new Date());
				geographicalStateDetail.setIsActive(true);
				// create by and update
				geographicalStateTempRepository.save(geographicalStateDetail);
			}
		}
		logger.info("end saveStateTemp");

	}

	private void saveCityTemp(HomeLoanParameterRequest homeLoanParameterRequest) {

		logger.info("start saveCityTemp");
		GeographicalCityDetailTemp geographicalCityDetail = null;
		for (DataRequest dataRequest : homeLoanParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetailTemp();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(homeLoanParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(homeLoanParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(homeLoanParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityTempRepository.save(geographicalCityDetail);
		}
		logger.info("end saveCityTemp");

	}

}
