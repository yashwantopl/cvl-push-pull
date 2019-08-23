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
import com.capitaworld.service.loans.domain.fundprovider.AutoLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.AutoLoanParameterTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCityDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCityDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetailTemp;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.retail.AutoLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.AutoLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.AutoLoanParameterTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateTempRepository;
import com.capitaworld.service.loans.service.fundprovider.AutoLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.FPParameterMappingService;
import com.capitaworld.service.loans.service.fundprovider.LoanPurposeAmountMappingService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Transactional
@Service
public class AutoLoanParameterServiceImpl implements AutoLoanParameterService {
	private static final Logger logger = LoggerFactory.getLogger(AutoLoanParameterServiceImpl.class);
	
	private static final String ERROR_WHILE_GET_AUTO_LOAN_PARAMETER_REQUEST_MSG = "error while getAutoLoanParameterRequest : ";

	@Autowired
	private AutoLoanParameterRepository autoLoanParameterRepository;
	
	@Autowired
	private FPParameterMappingService fPParameterMappingService;
	
	@Autowired
	private AutoLoanParameterTempRepository autoLoanParameterTempRepository;
	
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
	public boolean saveOrUpdate(AutoLoanParameterRequest autoLoanParameterRequest) {

		logger.info("saveOrUpdate starts");
		AutoLoanParameterTemp  loanParameter = autoLoanParameterTempRepository.findById(autoLoanParameterRequest.getId());

		AutoLoanParameter autoLoanParameter = null;
		if (loanParameter.getFpProductMappingId() != null) {
			autoLoanParameter = autoLoanParameterRepository.findOne(loanParameter.getFpProductMappingId());
		}
		if (autoLoanParameter == null) {
			autoLoanParameter = new AutoLoanParameter();
		}

		loanParameter.setStatusId(CommonUtils.Status.APPROVED);
		loanParameter.setIsDeleted(false);
		loanParameter.setIsEdit(false);
		loanParameter.setIsCopied(true);
		loanParameter.setIsApproved(true);
		loanParameter.setApprovalDate(new Date());
		loanParameter.setFpProductMappingId(autoLoanParameter.getId());
		autoLoanParameterTempRepository.save(loanParameter);

		if (!CommonUtils.isObjectListNull(autoLoanParameterRequest.getMaxTenure()))
			autoLoanParameterRequest.setMaxTenure(autoLoanParameterRequest.getMaxTenure() * 12);
		if (!CommonUtils.isObjectListNull(autoLoanParameterRequest.getMinTenure()))
			autoLoanParameterRequest.setMinTenure(autoLoanParameterRequest.getMinTenure() * 12);

		BeanUtils.copyProperties(autoLoanParameterRequest, autoLoanParameter, CommonUtils.IgnorableCopy.getFpProduct());
		autoLoanParameter.setIsParameterFilled(true);
		autoLoanParameter.setProductId(autoLoanParameterRequest.getProductId());
		autoLoanParameter.setUserId(autoLoanParameterRequest.getUserId());
		autoLoanParameter.setModifiedBy(autoLoanParameterRequest.getUserId());
		autoLoanParameter.setIsParameterFilled(true);
		autoLoanParameter.setModifiedDate(new Date());
		autoLoanParameter = autoLoanParameterRepository.save(autoLoanParameter);
		autoLoanParameterRequest.setId(autoLoanParameter.getId());

		geographicalCountryRepository.inActiveMappingByFpProductId(autoLoanParameterRequest.getId());
		// country data save
		saveCountry(autoLoanParameterRequest);
		// state data save
		geographicalStateRepository.inActiveMappingByFpProductId(autoLoanParameterRequest.getId());
		saveState(autoLoanParameterRequest);
		// city data save
		geographicalCityRepository.inActiveMappingByFpProductId(autoLoanParameterRequest.getId());
		saveCity(autoLoanParameterRequest);

		// Saving Mapping Current CURRENT_EMPLOYMENT
		fPParameterMappingService.inactiveAndSave(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.CURRENT_EMPLOYMENT,
				autoLoanParameterRequest.getCurrentEmploymentStatusIds());

		// Saving Mapping Current RESIDENTIAL
		fPParameterMappingService.inactiveAndSave(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.RESIDENTIAL, autoLoanParameterRequest.getResidentialStatusIds());

		// Saving Mapping Current BORROWER_TYPE
		fPParameterMappingService.inactiveAndSave(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.BORROWER_TYPE, autoLoanParameterRequest.getBorrowerTypeIds());

		// Saving Mapping Current SALARY_MODE
		fPParameterMappingService.inactiveAndSave(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.SALARY_MODE, autoLoanParameterRequest.getSalaryModeIds());

		// Saving Mapping Current BORROWER_SALARY_ACCOUNT
		fPParameterMappingService.inactiveAndSave(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.BORROWER_SALARY_ACCOUNT, autoLoanParameterRequest.getBorrSalAccIds());
		
		fPParameterMappingService.inactiveAndSave(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.EMPLOYMENT_WITH, autoLoanParameterRequest.getEmploymentWithIds());
		
		fPParameterMappingService.inactiveAndSave(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.SLEF_EMPLOYMENT_WITH, autoLoanParameterRequest.getSelfEmployedWithIds());
		
		fPParameterMappingService.inactiveAndSave(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.REPAYMENT_MODE, autoLoanParameterRequest.getRepaymentModeIds());
		
		//Saving Loan Purpose Amount Mapping
		loanPurposeAmountMappingService.deleteAndSave(autoLoanParameterRequest.getLoanPurposeAmountMappingRequests(), autoLoanParameterRequest.getId());
		
		logger.info("saveOrUpdate Ends");
		return true;
	}

	@Override
	public AutoLoanParameterRequest getAutoLoanParameterRequest(Long id) {

		CommonDocumentUtils.startHook(logger, "getautoLoanParameterRequest");
		AutoLoanParameter autoLoanParameter = autoLoanParameterRepository.findById(id);
		if (autoLoanParameter == null) {
			return null;
		}
		AutoLoanParameterRequest autoLoanParameterRequest = new AutoLoanParameterRequest();
		BeanUtils.copyProperties(autoLoanParameter, autoLoanParameterRequest);

		if (!CommonUtils.isObjectListNull(autoLoanParameterRequest.getMaxTenure()))
			autoLoanParameterRequest.setMaxTenure(autoLoanParameterRequest.getMaxTenure() / 12);
		if (!CommonUtils.isObjectListNull(autoLoanParameterRequest.getMinTenure()))
			autoLoanParameterRequest.setMinTenure(autoLoanParameterRequest.getMinTenure() / 12);

		List<Long> countryList = geographicalCountryRepository
				.getCountryByFpProductId(autoLoanParameterRequest.getId());
		if (!countryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				autoLoanParameterRequest.setCountryList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_AUTO_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> stateList = geographicalStateRepository.getStateByFpProductId(autoLoanParameterRequest.getId());
		if (!stateList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				autoLoanParameterRequest.setStateList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_AUTO_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> cityList = geographicalCityRepository.getCityByFpProductId(autoLoanParameterRequest.getId());
		if (!cityList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				autoLoanParameterRequest.setCityList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_AUTO_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		// Getting Mapping Current CURRENT_EMPLOYMENT
		autoLoanParameterRequest.setCurrentEmploymentStatusIds(fPParameterMappingService
				.getParameters(autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.CURRENT_EMPLOYMENT));

		// Saving Mapping Current RESIDENTIAL
		autoLoanParameterRequest.setResidentialStatusIds(fPParameterMappingService
				.getParameters(autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.RESIDENTIAL));

		// Saving Mapping Current BORROWER_TYPE
		autoLoanParameterRequest.setBorrowerTypeIds(fPParameterMappingService
				.getParameters(autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BORROWER_TYPE));

		// Saving Mapping Current SALARY_MODE
		autoLoanParameterRequest.setSalaryModeIds(fPParameterMappingService
				.getParameters(autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.SALARY_MODE));

		// Saving Mapping Current BORROWER_SALARY_ACCOUNT
		autoLoanParameterRequest.setBorrSalAccIds(fPParameterMappingService
				.getParameters(autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BORROWER_SALARY_ACCOUNT));
		
		autoLoanParameterRequest.setEmploymentWithIds(fPParameterMappingService
				.getParameters(autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.EMPLOYMENT_WITH));
		
		autoLoanParameterRequest.setSelfEmployedWithIds(fPParameterMappingService
				.getParameters(autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.SLEF_EMPLOYMENT_WITH));
		
		autoLoanParameterRequest.setRepaymentModeIds(fPParameterMappingService
				.getParameters(autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.REPAYMENT_MODE));

		//Getting Loan Purpose Amount Mapping
		autoLoanParameterRequest.setLoanPurposeAmountMappingRequests(loanPurposeAmountMappingService.getByFpProductId(autoLoanParameterRequest.getId()));
		
		CommonDocumentUtils.endHook(logger, "getautoLoanParameterRequest");
		return autoLoanParameterRequest;
	}

	@Override
	public AutoLoanParameterRequest getTemp(Long id, Long role, Long userId) {
		logger.info("Start getTemp==>");
		AutoLoanParameterTemp autoLoanParameter = autoLoanParameterTempRepository.findById(id);
		if (autoLoanParameter == null) {
			return null;
		}
		AutoLoanParameterRequest autoLoanParameterRequest = new AutoLoanParameterRequest();
		BeanUtils.copyProperties(autoLoanParameter, autoLoanParameterRequest);

		if (!CommonUtils.isObjectListNull(autoLoanParameterRequest.getMaxTenure()))
			autoLoanParameterRequest.setMaxTenure(autoLoanParameterRequest.getMaxTenure() / 12);
		if (!CommonUtils.isObjectListNull(autoLoanParameterRequest.getMinTenure()))
			autoLoanParameterRequest.setMinTenure(autoLoanParameterRequest.getMinTenure() / 12);

		List<Long> countryList = geographicalCountryTempRepository
				.getCountryByFpProductId(autoLoanParameterRequest.getId());
		if (!countryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
				if(!CommonUtils.isListNullOrEmpty(formResponse.getListData())) {
					List<DataRequest> cList = new ArrayList<>(formResponse.getListData().size());
					for(Object o : formResponse.getListData()) {
						cList.add(MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object> ) o, DataRequest.class));
					}
					autoLoanParameterRequest.setCountryList(cList);
				}
			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_AUTO_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> stateList = geographicalStateTempRepository.getStateByFpProductId(autoLoanParameterRequest.getId());
		if (!stateList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
				if(!CommonUtils.isListNullOrEmpty(formResponse.getListData())) {
					List<DataRequest> sList = new ArrayList<>(formResponse.getListData().size());
					for(Object o : formResponse.getListData()) {
						sList.add(MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object> ) o, DataRequest.class));
					}
					autoLoanParameterRequest.setStateList(sList);
				}
			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_AUTO_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> cityList = geographicalCityTempRepository.getCityByFpProductId(autoLoanParameterRequest.getId());
		if (!cityList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
				if(!CommonUtils.isListNullOrEmpty(formResponse.getListData())) {
					List<DataRequest> ctList = new ArrayList<>(formResponse.getListData().size());
					for(Object o : formResponse.getListData()) {
						ctList.add(MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object> ) o, DataRequest.class));
					}
					autoLoanParameterRequest.setCityList(ctList);
				}

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_AUTO_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}
		
		// Getting Mapping Current CURRENT_EMPLOYMENT
		autoLoanParameterRequest.setCurrentEmploymentStatusIds(fPParameterMappingService
				.getParametersTemp(autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.CURRENT_EMPLOYMENT));

		// Saving Mapping Current RESIDENTIAL
		autoLoanParameterRequest.setResidentialStatusIds(fPParameterMappingService
				.getParametersTemp(autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.RESIDENTIAL));

		// Saving Mapping Current BORROWER_TYPE
		autoLoanParameterRequest.setBorrowerTypeIds(fPParameterMappingService
				.getParametersTemp(autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BORROWER_TYPE));

		// Saving Mapping Current SALARY_MODE
		autoLoanParameterRequest.setSalaryModeIds(fPParameterMappingService
				.getParametersTemp(autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.SALARY_MODE));

		// Saving Mapping Current BORROWER_SALARY_ACCOUNT
		autoLoanParameterRequest.setBorrSalAccIds(fPParameterMappingService.getParametersTemp(
				autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BORROWER_SALARY_ACCOUNT));
		
		autoLoanParameterRequest.setEmploymentWithIds(fPParameterMappingService.getParametersTemp(
				autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.EMPLOYMENT_WITH));
		
		autoLoanParameterRequest.setSelfEmployedWithIds(fPParameterMappingService.getParametersTemp(
				autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.SLEF_EMPLOYMENT_WITH));
		
		autoLoanParameterRequest.setRepaymentModeIds(fPParameterMappingService.getParametersTemp(
				autoLoanParameterRequest.getId(), CommonUtils.ParameterTypes.REPAYMENT_MODE));

		//Getting Loan Purpose Amount Mapping
		autoLoanParameterRequest.setLoanPurposeAmountMappingRequests(loanPurposeAmountMappingService.getByFpProductId(autoLoanParameterRequest.getId()));
		
		//set workflow buttons
		
		 if (!CommonUtils.isObjectNullOrEmpty(autoLoanParameter.getJobId()) && !CommonUtils.isObjectNullOrEmpty(role)) {
            WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(autoLoanParameter.getJobId(),Arrays.asList(role), userId);
            if (!CommonUtils.isObjectNullOrEmpty(workflowResponse) && !CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
                try {
                    WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) workflowResponse.getData(), WorkflowJobsTrackerRequest.class);
                    if (!CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
                    	autoLoanParameterRequest.setWorkflowData(workflowJobsTrackerRequest.getStep().getStepActions());
                    } else {
                        logger.info("response from workflow NULL jobId = {} and roleId = {}", autoLoanParameter.getJobId(), role);
                    }
                } catch (IOException e) {
                    logger.error("Error While getting data from workflow {}", e);
                }
            }
        } else {
            logger.info("you set jobId or list of roleId NULL for calling workflow");
        }
		
		logger.info("End getTemp==>");
		return autoLoanParameterRequest;
	}

	@Override
	public Boolean saveOrUpdateTemp(AutoLoanParameterRequest autoLoanParameterRequest) {
		logger.info("saveOrUpdateTemp Start");
		AutoLoanParameterTemp autoLoanParameter = autoLoanParameterTempRepository.findOne(autoLoanParameterRequest.getId());
 		if (autoLoanParameter == null) {
			return false;
		}

		if (!CommonUtils.isObjectListNull(autoLoanParameterRequest.getMaxTenure()))
			autoLoanParameterRequest.setMaxTenure(autoLoanParameterRequest.getMaxTenure() * 12);
		if (!CommonUtils.isObjectListNull(autoLoanParameterRequest.getMinTenure()))
			autoLoanParameterRequest.setMinTenure(autoLoanParameterRequest.getMinTenure() * 12);

		BeanUtils.copyProperties(autoLoanParameterRequest, autoLoanParameter, CommonUtils.IgnorableCopy.getFpProduct());
		autoLoanParameter.setModifiedBy(autoLoanParameterRequest.getUserId());
		autoLoanParameter.setIsParameterFilled(true);
		autoLoanParameter.setModifiedDate(new Date());
		if (CommonUtils.isObjectNullOrEmpty(autoLoanParameter.getJobId())) {
			WorkflowResponse workflowResponse = workflowClient.createJobForMasters(WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL,autoLoanParameter.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
				autoLoanParameter.setJobId(Long.valueOf(workflowResponse.getData().toString()));
			}
		}
		autoLoanParameterTempRepository.save(autoLoanParameter);
		
		
		geographicalCountryTempRepository.inActiveMappingByFpProductId(autoLoanParameterRequest.getId());
		// country data save
		saveCountryTemp(autoLoanParameterRequest);
		// state data save
		geographicalStateTempRepository.inActiveMappingByFpProductId(autoLoanParameterRequest.getId());
		saveStateTemp(autoLoanParameterRequest);
		// city data save
		geographicalCityTempRepository.inActiveMappingByFpProductId(autoLoanParameterRequest.getId());
		saveCityTemp(autoLoanParameterRequest);

		// Saving Mapping Current CURRENT_EMPLOYMENT
		fPParameterMappingService.inactiveAndSaveTemp(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.CURRENT_EMPLOYMENT,
				autoLoanParameterRequest.getCurrentEmploymentStatusIds());

		// Saving Mapping Current RESIDENTIAL
		fPParameterMappingService.inactiveAndSaveTemp(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.RESIDENTIAL, autoLoanParameterRequest.getResidentialStatusIds());

		// Saving Mapping Current BORROWER_TYPE
		fPParameterMappingService.inactiveAndSaveTemp(autoLoanParameterRequest.getId(),	
				CommonUtils.ParameterTypes.BORROWER_TYPE, autoLoanParameterRequest.getBorrowerTypeIds());

		// Saving Mapping Current SALARY_MODE
		fPParameterMappingService.inactiveAndSaveTemp(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.SALARY_MODE, autoLoanParameterRequest.getSalaryModeIds());

		// Saving Mapping Current BORROWER_SALARY_ACCOUNT
		fPParameterMappingService.inactiveAndSaveTemp(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.BORROWER_SALARY_ACCOUNT, autoLoanParameterRequest.getBorrSalAccIds());
		
		fPParameterMappingService.inactiveAndSaveTemp(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.EMPLOYMENT_WITH, autoLoanParameterRequest.getEmploymentWithIds());
		
		fPParameterMappingService.inactiveAndSaveTemp(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.SLEF_EMPLOYMENT_WITH, autoLoanParameterRequest.getSelfEmployedWithIds());
		
		fPParameterMappingService.inactiveAndSaveTemp(autoLoanParameterRequest.getId(),
				CommonUtils.ParameterTypes.REPAYMENT_MODE, autoLoanParameterRequest.getRepaymentModeIds());
		
		//Saving Loan Purpose Amount Mapping
		loanPurposeAmountMappingService.deleteAndSave(autoLoanParameterRequest.getLoanPurposeAmountMappingRequests(), autoLoanParameterRequest.getId());
		logger.info("saveOrUpdateTemp End");
		return true;
	}

	@Override
	public Boolean saveMasterFromTemp(Long mappingId) throws LoansException {
		return saveOrUpdate(getTemp(mappingId, null, null));
	}
	
	private void saveCountry(AutoLoanParameterRequest autoLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCountry");

		GeographicalCountryDetail geographicalCountryDetail = null;
		for (DataRequest dataRequest : autoLoanParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetail();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(autoLoanParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(autoLoanParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(autoLoanParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryRepository.save(geographicalCountryDetail);
			CommonDocumentUtils.endHook(logger, "saveCountry");
		}
	}

	private void saveState(AutoLoanParameterRequest autoLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveState");
		GeographicalStateDetail geographicalStateDetail = null;
		for (DataRequest dataRequest : autoLoanParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetail();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(autoLoanParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(autoLoanParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(autoLoanParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateRepository.save(geographicalStateDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveState");
	}

	private void saveCity(AutoLoanParameterRequest autoLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCity");
		GeographicalCityDetail geographicalCityDetail = null;
		for (DataRequest dataRequest : autoLoanParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetail();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(autoLoanParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(autoLoanParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(autoLoanParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityRepository.save(geographicalCityDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCity");
	}
	
	private void saveCountryTemp(AutoLoanParameterRequest autoLoanParameterRequest) {

		logger.info("save saveCountryTemp");
		if (!CommonUtils.isListNullOrEmpty(autoLoanParameterRequest.getCountryList())) {
			GeographicalCountryDetailTemp geographicalCountryDetail = null;
			for (DataRequest dataRequest : autoLoanParameterRequest.getCountryList()) {
				geographicalCountryDetail = new GeographicalCountryDetailTemp();
				geographicalCountryDetail.setCountryId(dataRequest.getId());
				geographicalCountryDetail.setFpProductMaster(autoLoanParameterRequest.getId());
				geographicalCountryDetail.setCreatedBy(autoLoanParameterRequest.getUserId());
				geographicalCountryDetail.setModifiedBy(autoLoanParameterRequest.getUserId());
				geographicalCountryDetail.setCreatedDate(new Date());
				geographicalCountryDetail.setModifiedDate(new Date());
				geographicalCountryDetail.setIsActive(true);
				// create by and update
				geographicalCountryTempRepository.save(geographicalCountryDetail);
			}
		}
		logger.info("end saveCountryTemp");

	}

	private void saveStateTemp(AutoLoanParameterRequest autoLoanParameterRequest) {

		logger.info("start saveStateTemp");
		if (!CommonUtils.isListNullOrEmpty(autoLoanParameterRequest.getStateList())) {
			GeographicalStateDetailTemp geographicalStateDetail = null;
			for (DataRequest dataRequest : autoLoanParameterRequest.getStateList()) {
				geographicalStateDetail = new GeographicalStateDetailTemp();
				geographicalStateDetail.setStateId(dataRequest.getId());
				geographicalStateDetail.setFpProductMaster(autoLoanParameterRequest.getId());
				geographicalStateDetail.setCreatedBy(autoLoanParameterRequest.getUserId());
				geographicalStateDetail.setModifiedBy(autoLoanParameterRequest.getUserId());
				geographicalStateDetail.setCreatedDate(new Date());
				geographicalStateDetail.setModifiedDate(new Date());
				geographicalStateDetail.setIsActive(true);
				// create by and update
				geographicalStateTempRepository.save(geographicalStateDetail);
			}
		}
		logger.info("end saveStateTemp");

	}

	private void saveCityTemp(AutoLoanParameterRequest autoLoanParameterRequest) {

		logger.info("start saveCityTemp");
		GeographicalCityDetailTemp geographicalCityDetail = null;
		for (DataRequest dataRequest : autoLoanParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetailTemp();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(autoLoanParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(autoLoanParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(autoLoanParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityTempRepository.save(geographicalCityDetail);
		}
		logger.info("end saveCityTemp");

	}
}
