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
import com.capitaworld.service.loans.domain.fundprovider.EmpStatusMappingDetail;
import com.capitaworld.service.loans.domain.fundprovider.EmpStatusMappingDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.EmpWithMappingDetail;
import com.capitaworld.service.loans.domain.fundprovider.EmpWithMappingDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.FpRatingValueMapping;
import com.capitaworld.service.loans.domain.fundprovider.FpRatingValueMappingTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCityDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCityDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.PersonalLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.PersonalLoanParameterTemp;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.retail.CreditRatingPlParameter;
import com.capitaworld.service.loans.model.retail.PersonalLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.FpEmpStatusRepository;
import com.capitaworld.service.loans.repository.fundprovider.FpEmpStatusTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.FpEmpWithRepository;
import com.capitaworld.service.loans.repository.fundprovider.FpEmpWithTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.FpRatingAgencyRepository;
import com.capitaworld.service.loans.repository.fundprovider.FpRatingAgencyTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.PersonalLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.PersonalLoanParameterTempRepository;
import com.capitaworld.service.loans.service.fundprovider.PersonalLoanParameterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.OneFormResponse;
@Transactional
@Service
public class  PersonalLoanParameterServiceImpl implements PersonalLoanParameterService {

	private static final Logger logger = LoggerFactory.getLogger(PersonalLoanParameterServiceImpl.class);

	private static final String ERROR_WHILE_GET_PERSONAL_LOAN_PARAMETER_REQUEST_MSG = "error while getPersonalLoanParameterRequest : ";

	@Autowired
	private PersonalLoanParameterRepository personalLoanParameterRepository;
	
	@Autowired
	private PersonalLoanParameterTempRepository personalLoanParameterTempRepository;
	
	@Autowired 
	private GeographicalCountryRepository geographicalCountryRepository;
	
	@Autowired
	private GeographicalStateRepository geographicalStateRepository;
	
	@Autowired
	private GeographicalCityRepository geographicalCityRepository;
	
	@Autowired
	private GeographicalStateTempRepository geographicalStateTempRepository;
	
	@Autowired
	private GeographicalCityTempRepository geographicalCityTempRepository;
 	
	@Autowired
	private OneFormClient oneFormClient;
	
	@Autowired
	private WorkflowClient workflowClient; 
	
	@Autowired
	private GeographicalCountryTempRepository geographicalCountryTempRepository;
	
	@Autowired
	private FpEmpStatusRepository fpEmpStatusRepository;
	
	@Autowired
	private FpEmpStatusTempRepository fpEmpStatusTempRepository;
	
	@Autowired
	private FpEmpWithRepository fpEmpWithRepository;
	
	@Autowired
	private FpEmpWithTempRepository fpEmpWithTempRepository;
	
	@Autowired
	private FpRatingAgencyTempRepository fpRatingAgencyTempRepository;
	
	@Autowired 
	private FpRatingAgencyRepository fpRatingAgencyRepository;
	
	@Override
	public boolean saveOrUpdate(PersonalLoanParameterRequest personalLoanParameterRequest,Long mappingId) {
		CommonDocumentUtils.startHook(logger, "saveOrUpdate");
		PersonalLoanParameterTemp loanParameter = personalLoanParameterTempRepository
				.getPlParameterTempByFpProductId(mappingId);
		
		
		PersonalLoanParameter personalLoanParameter= null;

		if(loanParameter.getFpProductMappingId()!=null)
		{
			personalLoanParameter = personalLoanParameterRepository.findOne(loanParameter.getFpProductMappingId());
		}
		if (personalLoanParameter == null) {
			personalLoanParameter=new PersonalLoanParameter();
			
		}
		
		
		loanParameter.setStatusId(CommonUtils.Status.APPROVED); 
		loanParameter.setIsDeleted(false);
		loanParameter.setIsEdit(false);
		loanParameter.setIsCopied(true);
		loanParameter.setIsApproved(true);
		loanParameter.setApprovalDate(new Date());
		loanParameter.setFpProductMappingId(personalLoanParameter.getId());
		personalLoanParameterTempRepository.save(loanParameter);

		if (!CommonUtils.isObjectListNull(personalLoanParameterRequest.getMaxTenure()))
			personalLoanParameterRequest.setMaxTenure(personalLoanParameterRequest.getMaxTenure() * 12);
		if (!CommonUtils.isObjectListNull(personalLoanParameterRequest.getMinTenure()))
			personalLoanParameterRequest.setMinTenure(personalLoanParameterRequest.getMinTenure() * 12);

		BeanUtils.copyProperties(personalLoanParameterRequest, personalLoanParameter,"id");
		
		personalLoanParameter.setUserId(personalLoanParameterRequest.getUserId()!=null?personalLoanParameterRequest.getUserId():null);
		personalLoanParameter.setProductId(personalLoanParameterRequest.getProductId()!=null?personalLoanParameterRequest.getProductId():null);
		
		personalLoanParameter.setModifiedBy(personalLoanParameterRequest.getUserId());
		personalLoanParameter.setIsActive(true);
		personalLoanParameter.setModifiedDate(new Date());
		personalLoanParameter.setIsParameterFilled(true);
		personalLoanParameter.setJobId(personalLoanParameterRequest.getJobId());
		PersonalLoanParameter workingCapitalParameter2=personalLoanParameterRepository.save(personalLoanParameter);
		personalLoanParameterRequest.setId(workingCapitalParameter2.getId());
		geographicalCountryRepository.inActiveMappingByFpProductId(personalLoanParameterRequest.getId());
		// country data save
		saveCountry(personalLoanParameterRequest);
		// state data save
		geographicalStateRepository.inActiveMappingByFpProductId(personalLoanParameterRequest.getId());
		saveState(personalLoanParameterRequest);
		// city data save
		geographicalCityRepository.inActiveMappingByFpProductId(personalLoanParameterRequest.getId());
		saveCity(personalLoanParameterRequest);

		

		//save empwith
		fpEmpWithRepository.inActiveEmpWithByFpProductId(personalLoanParameterRequest.getId());
		saveEmpWithMaster(personalLoanParameterRequest);
		
		//save empstatus
		fpEmpStatusRepository.inActiveEmpStatusByFpProductId(personalLoanParameterRequest.getId());
		saveEmpStatusMaster(personalLoanParameterRequest);
		
		//save rating info
		fpRatingAgencyRepository.inActiveEmpWithByFpProductId(personalLoanParameterRequest.getId());
		saveRatingAgencyInfoMaster(personalLoanParameterRequest);
		

		/*//Dhaval
		boolean isUpdate = msmeValueMappingService.updateMsmeValueMapping(false, mappingId,workingCapitalParameter2.getId());
		logger.info("updated = {}",isUpdate);*/

		logger.info("end saveOrUpdate");
		return true;
	}

	@Override
	public PersonalLoanParameterRequest getPersonalLoanParameterRequest(Long id) {
		CommonDocumentUtils.startHook(logger, "getPersonalLoanParameterRequest");
		PersonalLoanParameterRequest personalLoanParameterRequest= new PersonalLoanParameterRequest();
		PersonalLoanParameter personalLoanParameter = personalLoanParameterRepository.getByID(id);
		if(personalLoanParameter==null)
			return null;
		BeanUtils.copyProperties(personalLoanParameter, personalLoanParameterRequest);
		
		if (!CommonUtils.isObjectListNull(personalLoanParameterRequest.getMaxTenure()))
			personalLoanParameterRequest.setMaxTenure(personalLoanParameterRequest.getMaxTenure() / 12);
		if (!CommonUtils.isObjectListNull(personalLoanParameterRequest.getMinTenure()))
			personalLoanParameterRequest.setMinTenure(personalLoanParameterRequest.getMinTenure() / 12);
		
		List<Long> countryList=geographicalCountryRepository.getCountryByFpProductId(personalLoanParameterRequest.getId());
		if(!countryList.isEmpty())
		{
		try {
			OneFormResponse formResponse =oneFormClient.getCountryByCountryListId(countryList);
			personalLoanParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GET_PERSONAL_LOAN_PARAMETER_REQUEST_MSG,e);
		}
		}
		
		
		
		
		List<Long> stateList=geographicalStateRepository.getStateByFpProductId(personalLoanParameterRequest.getId());
		if(!stateList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
			personalLoanParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GET_PERSONAL_LOAN_PARAMETER_REQUEST_MSG,e);
		}
		}
		
		
		List<Long> cityList=geographicalCityRepository.getCityByFpProductId(personalLoanParameterRequest.getId());
		if(!cityList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
			personalLoanParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GET_PERSONAL_LOAN_PARAMETER_REQUEST_MSG,e);
		}
		}
		
		
		//get emp status
				
				List<Integer> empStatusIds = fpEmpStatusRepository
						.getEmpStatusByFpProductId(personalLoanParameterRequest.getId());
				if (!empStatusIds.isEmpty()) {
									
						personalLoanParameterRequest.setEmpStatusIds(empStatusIds);
				}
				
				
				//get emp with
				
				List<Integer> empWithIds = fpEmpWithRepository
						.getEmpWithByFpProductId(personalLoanParameterRequest.getId());
				if (!empWithIds.isEmpty()) {
									
						personalLoanParameterRequest.setEmpWithIds(empWithIds);
				}
				
				//get rating info
				List<FpRatingValueMapping> ratingInfo = fpRatingAgencyRepository
						.getEmpWithByFpProductId(personalLoanParameterRequest.getId());
				if (!ratingInfo.isEmpty()) {
					List<CreditRatingPlParameter> creditRatingPlParameters=new ArrayList<>(ratingInfo.size());
					
					for(FpRatingValueMapping fpRatingValueMapping:ratingInfo)
					{
						CreditRatingPlParameter creditRatingPlParameter=new CreditRatingPlParameter();
						creditRatingPlParameter.setRatingId(fpRatingValueMapping.getAgencyId());
						creditRatingPlParameter.setRatingValue(fpRatingValueMapping.getRatingId());
						creditRatingPlParameters.add(creditRatingPlParameter);
					}
					personalLoanParameterRequest.setCreditRatingSelectedList(creditRatingPlParameters);
						
				}
				else
				{
					personalLoanParameterRequest.setCreditRatingSelectedList(null);
				}
		CommonDocumentUtils.endHook(logger, "getPersonalLoanParameterRequest");
		return personalLoanParameterRequest;
	}
	
	
private void saveCountry(PersonalLoanParameterRequest personalLoanParameterRequest) {
	CommonDocumentUtils.startHook(logger, "saveCountry");
		
		GeographicalCountryDetail geographicalCountryDetail= null;
		for (DataRequest dataRequest : personalLoanParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetail();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(personalLoanParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(personalLoanParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(personalLoanParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryRepository.save(geographicalCountryDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCountry");
	}
	
	private void saveState(PersonalLoanParameterRequest personalLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveState");
		GeographicalStateDetail geographicalStateDetail= null;
		for (DataRequest dataRequest : personalLoanParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetail();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(personalLoanParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(personalLoanParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(personalLoanParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateRepository.save(geographicalStateDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveState");
	}
	
	private void saveCity(PersonalLoanParameterRequest personalLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCity");
		GeographicalCityDetail geographicalCityDetail= null;
		for (DataRequest dataRequest : personalLoanParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetail();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(personalLoanParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(personalLoanParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(personalLoanParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityRepository.save(geographicalCityDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCity");
	}

	@Override
	public PersonalLoanParameterRequest getPersonalLoanParameterRequestTemp(Long id, Long role, Long userId) {

		logger.info("start getWorkingCapitalParameterTemp");
		PersonalLoanParameterRequest personalLoanParameterRequest= new PersonalLoanParameterRequest();
		PersonalLoanParameterTemp loanParameter = personalLoanParameterTempRepository
				.getPlParameterTempByFpProductId(id);
		if (loanParameter == null)
			return null;
		BeanUtils.copyProperties(loanParameter, personalLoanParameterRequest);

		if (!CommonUtils.isObjectListNull(personalLoanParameterRequest.getMaxTenure()))
			personalLoanParameterRequest.setMaxTenure(personalLoanParameterRequest.getMaxTenure() / 12);
		if (!CommonUtils.isObjectListNull(personalLoanParameterRequest.getMinTenure()))
			personalLoanParameterRequest.setMinTenure(personalLoanParameterRequest.getMinTenure() / 12);

		

		List<Long> countryList = geographicalCountryTempRepository
				.getCountryByFpProductId(personalLoanParameterRequest.getId());
		if (!countryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
				
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				
				personalLoanParameterRequest.setCountryList(dataRequests);

			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
		}

		List<Long> stateList = geographicalStateTempRepository
				.getStateByFpProductId(personalLoanParameterRequest.getId());
		if (!stateList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				
				personalLoanParameterRequest.setStateList(dataRequests);

			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
		}

		List<Long> cityList = geographicalCityTempRepository
				.getCityByFpProductId(personalLoanParameterRequest.getId());
		if (!cityList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				
				personalLoanParameterRequest.setCityList(dataRequests);

			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
		}

		
		/*//get emp with
		
		List<Long> empWithList = fpEmpWithTempRepository
				.getEmpWithByFpProductId(personalLoanParameterRequest.getId());
		if (!empWithList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				
				personalLoanParameterRequest.setCityList(dataRequests);

			} catch (Exception e) {
				logger.error(e.toString());
			}
		}

		
		
		//get emp status
*/		
		List<Integer> empStatusIds = fpEmpStatusTempRepository
				.getEmpStatusTempByFpProductId(personalLoanParameterRequest.getId());
		if (!empStatusIds.isEmpty()) {
							
				personalLoanParameterRequest.setEmpStatusIds(empStatusIds);
		}
		
		
		//get emp with
		
		List<Integer> empWithIds = fpEmpWithTempRepository
				.getEmpWithByFpProductId(personalLoanParameterRequest.getId());
		if (!empWithIds.isEmpty()) {
							
				personalLoanParameterRequest.setEmpWithIds(empWithIds);
		}
		
		//get rating info
		List<FpRatingValueMappingTemp> ratingInfo = fpRatingAgencyTempRepository
				.getEmpWithByFpProductId(personalLoanParameterRequest.getId());
		if (!ratingInfo.isEmpty()) {
			List<CreditRatingPlParameter> creditRatingPlParameters=new ArrayList<>(ratingInfo.size());
			
			for(FpRatingValueMappingTemp fpRatingValueMapping:ratingInfo)
			{
				CreditRatingPlParameter creditRatingPlParameter=new CreditRatingPlParameter();
				creditRatingPlParameter.setRatingId(fpRatingValueMapping.getAgencyId());
				creditRatingPlParameter.setRatingValue(fpRatingValueMapping.getRatingId());
				creditRatingPlParameters.add(creditRatingPlParameter);
			}
			personalLoanParameterRequest.setCreditRatingSelectedList(creditRatingPlParameters);
				
		}
		else
		{
			personalLoanParameterRequest.setCreditRatingSelectedList(null);
		}
		
		personalLoanParameterRequest.setJobId(loanParameter.getJobId());
		
		//set workflow buttons
		
		 if (!CommonUtils.isObjectNullOrEmpty(loanParameter.getJobId()) && !CommonUtils.isObjectNullOrEmpty(role)) {
             WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(loanParameter.getJobId(),Arrays.asList(role), userId);
             if (!CommonUtils.isObjectNullOrEmpty(workflowResponse) && !CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
                 try {
                     WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) workflowResponse.getData(), WorkflowJobsTrackerRequest.class);
                     if (!CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
                    	 personalLoanParameterRequest.setWorkflowData(workflowJobsTrackerRequest.getStep().getStepActions());
                     } else {
                         logger.info("response from workflow NULL jobId = {} and roleId = {}", loanParameter.getJobId(), role);
                     }
                 } catch (IOException e) {
                     logger.error("Error While getting data from workflow {}", e);
                 }
             }
         } else {
             logger.info("you set jobId or list of roleId NULL for calling workflow");
         }

		//workingCapitalParameterRequest.setMsmeFundingIds(msmeValueMappingService.getDataListFromFpProductId(1,id, userId));
		logger.info("end getWorkingCapitalParameterTemp");
		return personalLoanParameterRequest;
	}

	@Override
	public Boolean saveOrUpdateTemp(PersonalLoanParameterRequest personalLoanParameterRequest) {

		logger.info("start saveOrUpdateTemp");

		PersonalLoanParameterTemp personalLoanParameterTemp= null;

		if(personalLoanParameterRequest.getAppstage()==1)
		{
			personalLoanParameterTemp = personalLoanParameterTempRepository.findOne(personalLoanParameterRequest.getId());
		}
		else
		{
			
			personalLoanParameterTemp = personalLoanParameterTempRepository.getPlParameterTempByFpProductMappingId(personalLoanParameterRequest.getId());
			
		}
		
		if (personalLoanParameterTemp == null) {
			personalLoanParameterTemp=new PersonalLoanParameterTemp();
		}

		if (!CommonUtils.isObjectListNull(personalLoanParameterRequest.getMaxTenure()))
			personalLoanParameterRequest.setMaxTenure(personalLoanParameterRequest.getMaxTenure() * 12);
		if (!CommonUtils.isObjectListNull(personalLoanParameterRequest.getMinTenure()))
			personalLoanParameterRequest.setMinTenure(personalLoanParameterRequest.getMinTenure() * 12);

		if(personalLoanParameterRequest.getAppstage()!=1)
		{
			personalLoanParameterTemp.setFpProductMappingId(personalLoanParameterRequest.getId());
		}
		if(personalLoanParameterRequest.getAppstage()==1)
		{
		BeanUtils.copyProperties(personalLoanParameterRequest, personalLoanParameterTemp,"id");
		}
		else
		{
		BeanUtils.copyProperties(personalLoanParameterRequest, personalLoanParameterTemp,"jobId","id");
		}
		
		personalLoanParameterTemp.setUserId(personalLoanParameterRequest.getUserId()!=null?personalLoanParameterRequest.getUserId():null);
		personalLoanParameterTemp.setProductId(personalLoanParameterRequest.getProductId()!=null?personalLoanParameterRequest.getProductId():null);
		personalLoanParameterTemp.setModifiedBy(personalLoanParameterRequest.getUserId());
		personalLoanParameterTemp.setIsActive(true);
		personalLoanParameterTemp.setModifiedDate(new Date());
		personalLoanParameterTemp.setIsParameterFilled(true);
		personalLoanParameterTemp.setStatusId(CommonUtils.Status.OPEN);
		personalLoanParameterTemp.setIsApproved(false);
		personalLoanParameterTemp.setIsDeleted(false);
		personalLoanParameterTemp.setIsCopied(false);
		personalLoanParameterTemp.setApprovalDate(null);

		if (CommonUtils.isObjectNullOrEmpty(personalLoanParameterTemp.getJobId())) {
			WorkflowResponse workflowResponse = workflowClient.createJobForMasters(
					WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL,
					personalLoanParameterRequest.getUserId());
			Long jobId = null;
			if (!CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
				jobId = Long.valueOf(workflowResponse.getData().toString());
			}

			personalLoanParameterTemp.setJobId(jobId);
		}
		
		personalLoanParameterTemp = personalLoanParameterTempRepository.save(personalLoanParameterTemp);
		personalLoanParameterRequest.setId(personalLoanParameterTemp.getId());
		
		geographicalCountryTempRepository.inActiveMappingByFpProductId(personalLoanParameterTemp.getId());
		// country data save
		saveCountryTemp(personalLoanParameterRequest);
		// state data save
		geographicalStateTempRepository.inActiveMappingByFpProductId(personalLoanParameterTemp.getId());
		saveStateTemp(personalLoanParameterRequest);
		// city data save
		geographicalCityTempRepository.inActiveMappingByFpProductId(personalLoanParameterTemp.getId());
		saveCityTemp(personalLoanParameterRequest);
		
		//save empwith
		fpEmpWithTempRepository.inActiveEmpWithByFpProductId(personalLoanParameterTemp.getId());
		saveEmpWith(personalLoanParameterRequest);
		
		//save empstatus
		fpEmpStatusTempRepository.inActiveEmpStatusTempByFpProductId(personalLoanParameterTemp.getId());
		saveEmpStatus(personalLoanParameterRequest);
		
		//save rating info
		fpRatingAgencyTempRepository.inActiveEmpWithByFpProductId(personalLoanParameterTemp.getId());
		saveRatingAgencyInfo(personalLoanParameterRequest);
		
		
		/*//Dhaval
		boolean isUpdate = msmeValueMappingService.updateMsmeValueMappingTemp(workingCapitalParameterRequest.getMsmeFundingIds(),workingCapitalParameterRequest.getId(), workingCapitalParameterRequest.getUserId());
		logger.info("updated = {}",isUpdate);*/
		logger.info("end saveOrUpdateTemp");
		return true;
	}

	private void saveRatingAgencyInfo(PersonalLoanParameterRequest personalLoanParameterRequest) {

		logger.info("save saveRatingAgencyInfo");
		if(!CommonUtils.isListNullOrEmpty(personalLoanParameterRequest.getCreditRatingSelectedList()))
		{
		FpRatingValueMappingTemp empWithMappingDetailTemp = null;
		for (CreditRatingPlParameter dataRequest : personalLoanParameterRequest.getCreditRatingSelectedList()) {
			empWithMappingDetailTemp = new FpRatingValueMappingTemp();
			empWithMappingDetailTemp.setAgencyId(dataRequest.getRatingId());
			empWithMappingDetailTemp.setRatingId(dataRequest.getRatingValue());
			empWithMappingDetailTemp.setFpProductId(personalLoanParameterRequest.getId());
			empWithMappingDetailTemp.setCreatedBy(personalLoanParameterRequest.getUserId());
			empWithMappingDetailTemp.setModifiedBy(personalLoanParameterRequest.getUserId());
			empWithMappingDetailTemp.setCreatedDate(new Date());
			empWithMappingDetailTemp.setModifiedDate(new Date());
			empWithMappingDetailTemp.setActive(true);
			// create by and update
			fpRatingAgencyTempRepository.save(empWithMappingDetailTemp);
		}
		}
		logger.info("end saveRatingAgencyInfo");
		
	}

	
	private void saveRatingAgencyInfoMaster(PersonalLoanParameterRequest personalLoanParameterRequest) {

		logger.info("save saveRatingAgencyInfoMaster");
		if(!CommonUtils.isListNullOrEmpty(personalLoanParameterRequest.getCreditRatingSelectedList()))
		{
		FpRatingValueMapping empWithMappingDetailTemp = null;
		for (CreditRatingPlParameter dataRequest : personalLoanParameterRequest.getCreditRatingSelectedList()) {
			empWithMappingDetailTemp = new FpRatingValueMapping();
			empWithMappingDetailTemp.setAgencyId(dataRequest.getRatingId());
			empWithMappingDetailTemp.setRatingId(dataRequest.getRatingValue());
			empWithMappingDetailTemp.setFpProductId(personalLoanParameterRequest.getId());
			empWithMappingDetailTemp.setCreatedBy(personalLoanParameterRequest.getUserId());
			empWithMappingDetailTemp.setModifiedBy(personalLoanParameterRequest.getUserId());
			empWithMappingDetailTemp.setCreatedDate(new Date());
			empWithMappingDetailTemp.setModifiedDate(new Date());
			empWithMappingDetailTemp.setActive(true);
			// create by and update
			fpRatingAgencyRepository.save(empWithMappingDetailTemp);
		}
		}
		logger.info("end saveRatingAgencyInfoMaster");
		
	}
	
	private void saveEmpStatus(PersonalLoanParameterRequest personalLoanParameterRequest) {

		logger.info("save saveEmpStatus");
		if(!CommonUtils.isListNullOrEmpty(personalLoanParameterRequest.getEmpStatusIds()))
		{
		EmpStatusMappingDetailTemp empWithMappingDetailTemp = null;
		for (Integer dataRequest : personalLoanParameterRequest.getEmpStatusIds()) {
			empWithMappingDetailTemp = new EmpStatusMappingDetailTemp();
			empWithMappingDetailTemp.setEmpStatusId(dataRequest);
			empWithMappingDetailTemp.setFpProductId(personalLoanParameterRequest.getId());
			empWithMappingDetailTemp.setCreatedBy(personalLoanParameterRequest.getUserId());
			empWithMappingDetailTemp.setModifiedBy(personalLoanParameterRequest.getUserId());
			empWithMappingDetailTemp.setCreatedDate(new Date());
			empWithMappingDetailTemp.setModifiedDate(new Date());
			empWithMappingDetailTemp.setActive(true);
			// create by and update
			fpEmpStatusTempRepository.save(empWithMappingDetailTemp);
		}
		}
		logger.info("end saveEmpStatus");
		
	}
	
	private void saveEmpStatusMaster(PersonalLoanParameterRequest personalLoanParameterRequest) {

		logger.info("save saveEmpStatusMaster");
		if(!CommonUtils.isListNullOrEmpty(personalLoanParameterRequest.getEmpStatusIds()))
		{
		EmpStatusMappingDetail empWithMappingDetailTemp = null;
		for (Integer dataRequest : personalLoanParameterRequest.getEmpStatusIds()) {
			empWithMappingDetailTemp = new EmpStatusMappingDetail();
			empWithMappingDetailTemp.setEmpStatusId(dataRequest);
			empWithMappingDetailTemp.setFpProductId(personalLoanParameterRequest.getId());
			empWithMappingDetailTemp.setCreatedBy(personalLoanParameterRequest.getUserId());
			empWithMappingDetailTemp.setModifiedBy(personalLoanParameterRequest.getUserId());
			empWithMappingDetailTemp.setCreatedDate(new Date());
			empWithMappingDetailTemp.setModifiedDate(new Date());
			empWithMappingDetailTemp.setActive(true);
			// create by and update
			fpEmpStatusRepository.save(empWithMappingDetailTemp);
		}
		}
		logger.info("end saveEmpStatusMaster");
		
	}

	private void saveEmpWith(PersonalLoanParameterRequest personalLoanParameterRequest) {

		logger.info("save saveEmpWith");
		if(!CommonUtils.isListNullOrEmpty(personalLoanParameterRequest.getEmpWithIds()))
		{
		EmpWithMappingDetailTemp empWithMappingDetailTemp= null;
		for (Integer dataRequest : personalLoanParameterRequest.getEmpWithIds()) {
			empWithMappingDetailTemp = new EmpWithMappingDetailTemp();
			empWithMappingDetailTemp.setEmpTypeId(dataRequest);
			empWithMappingDetailTemp.setFpProductId(personalLoanParameterRequest.getId());
			empWithMappingDetailTemp.setCreatedBy(personalLoanParameterRequest.getUserId());
			empWithMappingDetailTemp.setModifiedBy(personalLoanParameterRequest.getUserId());
			empWithMappingDetailTemp.setCreatedDate(new Date());
			empWithMappingDetailTemp.setModifiedDate(new Date());
			empWithMappingDetailTemp.setActive(true);
			// create by and update
			fpEmpWithTempRepository.save(empWithMappingDetailTemp);
		}
		}
		logger.info("end saveEmpWith");
	}
	
	private void saveEmpWithMaster(PersonalLoanParameterRequest personalLoanParameterRequest) {

		logger.info("save saveEmpWithMaster");
		if(!CommonUtils.isListNullOrEmpty(personalLoanParameterRequest.getEmpWithIds()))
		{
		EmpWithMappingDetail empWithMappingDetailTemp= null;
		for (Integer dataRequest : personalLoanParameterRequest.getEmpWithIds()) {
			empWithMappingDetailTemp = new EmpWithMappingDetail();
			empWithMappingDetailTemp.setEmpTypeId(dataRequest);
			empWithMappingDetailTemp.setFpProductId(personalLoanParameterRequest.getId());
			empWithMappingDetailTemp.setCreatedBy(personalLoanParameterRequest.getUserId());
			empWithMappingDetailTemp.setModifiedBy(personalLoanParameterRequest.getUserId());
			empWithMappingDetailTemp.setCreatedDate(new Date());
			empWithMappingDetailTemp.setModifiedDate(new Date());
			empWithMappingDetailTemp.setActive(true);
			// create by and update
			fpEmpWithRepository.save(empWithMappingDetailTemp);
		}
		}
		logger.info("end saveEmpWithMaster");
	}

	private void saveCountryTemp(PersonalLoanParameterRequest personalLoanParameterRequest) {

		logger.info("save saveCountryTemp");
		if(!CommonUtils.isListNullOrEmpty(personalLoanParameterRequest.getCountryList()))
		{
		GeographicalCountryDetailTemp geographicalCountryDetail = null;
		for (DataRequest dataRequest : personalLoanParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetailTemp();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(personalLoanParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(personalLoanParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(personalLoanParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryTempRepository.save(geographicalCountryDetail);
		}
		}
		logger.info("end saveCountryTemp");
		
	}

	private void saveStateTemp(PersonalLoanParameterRequest personalLoanParameterRequest) {

		logger.info("start saveStateTemp");
		if(!CommonUtils.isListNullOrEmpty(personalLoanParameterRequest.getStateList()))
		{
		GeographicalStateDetailTemp geographicalStateDetail = null;
		for (DataRequest dataRequest : personalLoanParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetailTemp();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(personalLoanParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(personalLoanParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(personalLoanParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateTempRepository.save(geographicalStateDetail);
		}
		}
		logger.info("end saveStateTemp");
		
	}

	private void saveCityTemp(PersonalLoanParameterRequest personalLoanParameterRequest) {

		logger.info("start saveCityTemp");
		GeographicalCityDetailTemp geographicalCityDetail = null;
		for (DataRequest dataRequest : personalLoanParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetailTemp();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(personalLoanParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(personalLoanParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(personalLoanParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityTempRepository.save(geographicalCityDetail);
		}
		logger.info("end saveCityTemp");
		
	}

	@Override
	public Boolean saveMasterFromTempPl(Long mappingId) throws Exception {
		try {
			PersonalLoanParameterRequest personalLoanParameterRequest= getPersonalLoanParameterRequestTemp(mappingId,null,null);
			return saveOrUpdate(personalLoanParameterRequest,mappingId);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return false;
		}
	}


}
